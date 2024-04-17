/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB034001;

import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.BIZ_APP_ID;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_ACCT_DT;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_AR_TRX_BAL_PK;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_BILL_TO_CUST_ACCT_CD;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_BILL_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_CFS_APP_NUM;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_CFS_LEASE_PKG_PO_HDR_PK;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_CFS_LEASE_PO_INFO_PK;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_CFS_PO_AMT;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_CFS_PO_NUM;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_COA_BR_CD;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_CR_REBIL_RSN_CATG_CD;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_DS_ACCT_NM;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_ENT_CPO_TOT_DEAL_NET_AMT;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_INV_DT;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_INV_NUM;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_INV_TOT_DEAL_DISC_AMT;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_INV_TOT_DEAL_FRT_AMT;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_INV_TOT_DEAL_NET_AMT;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_INV_TOT_DEAL_SLS_AMT;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_INV_TOT_DEAL_TAX_AMT;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_INV_TP_CD;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_LEASE_CMPY_USR_ID;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_LEASE_CMPY_USR_NM;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_NEGO_DEAL_AMT;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_ORD_BOOK_REQ_USR_ID;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_PSN_FIRST_NM;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_PSN_LAST_NM;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_SHIP_TO_CUST_ACCT_CD;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_SLS_REP_TOC_CD;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.COL_SLS_REP_TOC_NM;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.DB_PARAM_ATTRB_KEY_NM;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.DB_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.DB_PARAM_MODE_CD;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.DB_PARAM_ORD_HDR_STS_CANC;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.DB_PARAM_ORD_HDR_STS_CD;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.INV_THRHD;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.NWCM0109E;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.NWCM0110E;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.NWCM0112E;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.ONE_HUNDRED;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.RATE_FRAC_DIGIT;
import static com.canon.cusa.s21.batch.NWC.NWCB034001.constant.NWCB034001Constant.ZZZM9025E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CFS_LEASE_PKG_PO_DTLTMsg;
import business.db.CFS_LEASE_PKG_PO_HDRTMsg;
import business.db.CFS_LEASE_PO_INFOTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CFS_MAN_HLD_ACT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * NWCB0340:CFS Lease Package Extract Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/07/2016   CITS            T.Gotoda        Create          N/A
 * 07/26/2016   Fujitsu         W.Honda         Update          Therefore
 * 09/21/2017   Fujitsu         H.Ikeda         Update          QC#20381
 * 01/10/2018   Fujitsu         W.Honda         Update          QC#21706-2
 * 01/22/2018   Fujitsu         K.Ishizuka      Update          QC#23439
 * 02/22/2018   Fujitsu         W.Honda         Update          QC#24315
 * 05/24/2018   Fujitsu         H.Kumagai       Update          QC#24431
 * 06/08/2018   Fujitsu         W.Honda         Update          QC#24431
 * 08/20/2018   Fujitsu         W.Honda         Update          QC#27439
 * 02/28/2019   Fujitsu         Y.Kanefusa      Update          S21_NA#30523
 * 01/17/2020   Fujitsu         M.Ohno          Update          S21_NA#54948
 * 09/30/2022   Hitachi         H.Watanabe      Update          QC#60253
 * 11/02/2022   Hitachi         N.Takatsu       Update          QC#60253
 *</pre>
 */
public class NWCB034001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd;

    /** Invoice Threshold Rate */
    private BigDecimal invThrhdRate = BigDecimal.ZERO;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    // Therefore 2016/07/26 Add start
    /** Parameter Mode Code */
    private String paramModeCd;
    // Therefore 2016/07/26 Add end

    /** normal count */
    private int normalCount = 0;

    /** error count */
    private int errorCount = 0;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameter.
     */
    public static void main(String[] args) {

        // Initialization S21BatchMain
        new NWCB034001().executeBatch(NWCB034001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // S21SsmBatchClient
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code"});
        }

        this.invThrhdRate = getAttrbValNum();
        if (!ZYPCommonFunc.hasValue(this.invThrhdRate)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Invoice Threshold Rate"});
        }

        // Therefore 2016/07/26 Add start
        this.paramModeCd = getUserVariable1();
        if (this.paramModeCd == null || "".equals(this.paramModeCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Var User1" });
        }
        // Therefore 2016/07/26 Add end
    }

    @Override
    protected void termRoutine() {

        if (errorCount > 0) {
            termCd = TERM_CD.WARNING_END;
        }

        setTermState(termCd, normalCount, errorCount);
    }

    @Override
    protected void mainRoutine() {
        // work count
        int normalWorkCnt = 0;
        int errorWorkCnt = 0;

        // S21SsmLowLevelCodintClient Setup
        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        execParam.setMaxRows(0);

        PreparedStatement ps = null;
        ResultSet rs = null;

        // Search Target Invoice for CFS
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        // QC#24431 2018/06/08 Add Start
        ssmParam.put(DB_PARAM_ORD_HDR_STS_CANC, ORD_HDR_STS.CANCELLED);
        // QC#24431 2018/06/08 Add End
        // QC#24431 2018/06/08 Del Start
        // Therefore 2016/07/26 Add start
//        if (MODE_CD_02.equals(this.paramModeCd)) {
        // QC#24431 2018/06/08 Del End
            ssmParam.put(DB_PARAM_MODE_CD, this.paramModeCd);
        // QC#24431 2018/06/08 Del Start
//        }
        // Therefore 2016/07/26 Add start
        // QC#24431 2018/06/08 Del End
        //2022/09/30 QC#60253 Add Start
            ssmParam.put(DB_PARAM_ORD_HDR_STS_CD, ORD_HDR_STS.CANCELLED);
        //2022/09/30 QC#60253 Add End

        try {
            // Therefore 2016/07/26 Mod start
//            ps = ssmLlcClient.createPreparedStatement("findCfsInv", ssmParam, execParam);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//
//                // Main Process
//                if (!mainProcess(rs)) {
//                    rollback();
//                    errorCount++;
//                } else {
//                  commit();
//                    normalCount++;
//                }
//            }
            // QC#24431 2018/06/08 Mod Start
            // ps = ssmLlcClient.createPreparedStatement("findCfsInv", ssmParam, execParam);
            ps = ssmLlcClient.createPreparedStatement("findCfsHdr", ssmParam, execParam);
            // QC#24431 2018/06/08 Mod End
            rs = ps.executeQuery();
            String prevNum = null;
            String commitDecisionColNm = null;
            // QC#24431 2018/06/08 Del Start
//            if (MODE_CD_01.equals(this.paramModeCd)) {
//                commitDecisionColNm = COL_CPO_ORD_NUM;
//            } else {
            // QC#24431 2018/06/08 Del End
                commitDecisionColNm = COL_CFS_PO_NUM;
            // QC#24431 2018/06/08 Del Start
//            }
            // QC#24431 2018/06/08 Del End

            CFS_LEASE_PKG_PO_HDRTMsg cfsPoHdrTMsg = null;
            BigDecimal ordTotDealNetAmt = BigDecimal.ZERO;
            Boolean isNewFlg = false;
            while (rs.next()) {

                if (ZYPCommonFunc.hasValue(prevNum)
                        // QC#30756 2019/04/04 Mod Start
                        //&& !prevNum.equals(rs.getString(commitDecisionColNm))) {
                        && !prevNum.equalsIgnoreCase(rs.getString(commitDecisionColNm))) {
                        // QC#30756 2019/04/04 Mod End
                    if (errorWorkCnt > 0) {
                        rollback();
                    } else {
                        if (!ZYPCommonFunc.hasValue(ordTotDealNetAmt)) {
                            ordTotDealNetAmt = BigDecimal.ZERO;
                        }
                        if (!ZYPCommonFunc.hasValue(cfsPoHdrTMsg.ordTotDealNetAmt)) {
                            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.ordTotDealNetAmt, BigDecimal.ZERO);
                        }

                        boolean isErrFlg = false;
                        if (!isNewFlg
                                && ordTotDealNetAmt.compareTo(cfsPoHdrTMsg.ordTotDealNetAmt.getValue()) != 0) {
                            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.ordTotDealNetAmt, ordTotDealNetAmt);
 
                            // Update Header
                            // Calculate Amount Rate
                            BigDecimal rate = BigDecimal.ZERO;

                            if (cfsPoHdrTMsg.ordTotDealNetAmt.getValue().compareTo(BigDecimal.ZERO) == 0) {
                                rate = ONE_HUNDRED;
                            } else {
                                rate = cfsPoHdrTMsg.invCpltTotDealNetAmt.getValue().divide(cfsPoHdrTMsg.ordTotDealNetAmt.getValue(), RATE_FRAC_DIGIT, BigDecimal.ROUND_FLOOR).multiply(ONE_HUNDRED);
                            }

                            // Update header rate
                            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.invCpltAmtRate, rate);

                            // Threshold check
                            if (cfsPoHdrTMsg.invCpltAmtRate.getValue().compareTo(this.invThrhdRate) >= 0) {
                                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.thrhdHldFlg, ZYPConstant.FLG_OFF_N);

                             // 2022/09/30 QC#60253 Mod Start
                                if (CFS_MAN_HLD_ACT_TP.RELEASE_OVER_THRESHOLD.equals(cfsPoHdrTMsg.cfsManHldActTpCd.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsLeasePkgHldFlg, ZYPConstant.FLG_OFF_N);

                                    if (ZYPCommonFunc.hasValue(cfsPoHdrTMsg.cfsLeasePoInfoPk)) {
                                        CFS_LEASE_PO_INFOTMsg cfsLeasePoInfoTMsg = new CFS_LEASE_PO_INFOTMsg();
                                        ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.glblCmpyCd, this.glblCmpyCd);
                                        ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.cfsLeasePoInfoPk, cfsPoHdrTMsg.cfsLeasePoInfoPk.getValue());
                                        cfsLeasePoInfoTMsg = (CFS_LEASE_PO_INFOTMsg) EZDTBLAccessor.findByKey(cfsLeasePoInfoTMsg);
                                        if (cfsLeasePoInfoTMsg == null) {
                                            String[] paramArray = new String[] {"CFS_LEASE_PO_INFO", String.format("CFS_LEASE_PO_INFO_PK = %s", cfsPoHdrTMsg.cfsLeasePoInfoPk.getValue()) };
                                            String errMsgText = S21MessageFunc.clspGetMessage(NWCM0112E, paramArray);
                                            writeLogLn(errMsgText);
                                            isErrFlg = true;
                                        }

                                        ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.poInfoProcFlg, ZYPConstant.FLG_ON_Y);
                                        EZDTBLAccessor.update(cfsLeasePoInfoTMsg);
                                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsLeasePoInfoTMsg.getReturnCode())) {
                                            String[] paramArray = new String[] {"CFS_LEASE_PO_INFO", String.format("CFS_LEASE_PO_INFO_PK = %s", cfsPoHdrTMsg.cfsLeasePoInfoPk.getValue()) };
                                            String errMsgText = S21MessageFunc.clspGetMessage(NWCM0110E, paramArray);
                                            writeLogLn(errMsgText);
                                            isErrFlg = true;
                                        }
                                    }
                                }
                            }
                            // 2022/09/30 QC#60253 Mod End
                            EZDTBLAccessor.update(cfsPoHdrTMsg);
                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsPoHdrTMsg.getReturnCode())) {
                                String[] paramArray = new String[] {"CFS_LEASE_PKG_PO_HDR", String.format("CFS_LEASE_PKG_PO_HDR_PK = %s", cfsPoHdrTMsg.cfsLeasePkgPoHdrPk.getValue())};
                                String errMsgText = S21MessageFunc.clspGetMessage(NWCM0110E, paramArray);
                                writeLogLn(errMsgText);
                                isErrFlg = true;
                            }
                        }

                        if (!isErrFlg) {
                            if(isNewFlg) {
                                // QC#27439 2018/08/20 Add Start
                                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.ordTotDealNetAmt, ordTotDealNetAmt);
                                // QC#27439 2018/08/20 Add End

                                EZDTBLAccessor.insert(cfsPoHdrTMsg);
                                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsPoHdrTMsg.getReturnCode())) {
                                    String[] paramArray = null;
                                    paramArray = new String[] {"CFS_LEASE_PKG_PO_HDR", String.format("CFS_PO_NUM = '%s'", cfsPoHdrTMsg.cfsPoNum.getValue()) };
                                    String errMsgText = S21MessageFunc.clspGetMessage(NWCM0109E, paramArray);
                                    writeLogLn(errMsgText);
                                    isErrFlg = true;
                                }
                            }
                        }
                        if (isErrFlg) {
                            rollback();
                        } else {
                            commit();
                        }

                    }
                    isNewFlg = false;
                    errorWorkCnt = 0;
                    normalWorkCnt = 0;
                    cfsPoHdrTMsg = null;
                    ordTotDealNetAmt = BigDecimal.ZERO;
                // QC#24431 2018/06/08 Mod End
                // 2020/01/17 S21_NA#54948 Mod Start
//                } else {
                }
                 prevNum = rs.getString(commitDecisionColNm);
//                }
                 // 2020/01/17 S21_NA#54948 Mod End

                // QC#24431 2018/06/08 Mod Start
                // Header Process
//                if (!mainProcess(rs)) {
//                    errorWorkCnt++;
//                } else {
//                    normalWorkCnt++;
//                }

                BigDecimal cfsLeasePkgPoHdrPk = rs.getBigDecimal(COL_CFS_LEASE_PKG_PO_HDR_PK);

                if (cfsLeasePkgPoHdrPk == null) {

                    // insert
                    if (cfsPoHdrTMsg == null) {
                        isNewFlg = true;

                        cfsPoHdrTMsg = new CFS_LEASE_PKG_PO_HDRTMsg();

                        cfsLeasePkgPoHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CFS_LEASE_PKG_PO_HDR_SQ);

                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.glblCmpyCd, this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsLeasePkgPoHdrPk, cfsLeasePkgPoHdrPk);
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsAppNum, rs.getString(COL_CFS_APP_NUM));
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsPoNum, rs.getString(COL_CFS_PO_NUM));
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cpoOrdNum, rs.getString(COL_CPO_ORD_NUM));
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.dsAcctNum, rs.getString(COL_SHIP_TO_CUST_ACCT_CD));
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.dsAcctNm, rs.getString(COL_DS_ACCT_NM));
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsPoAmt, rs.getBigDecimal(COL_CFS_PO_AMT));
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsRepTocCd, rs.getString(COL_SLS_REP_TOC_CD));
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsLeasePkgHldFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.thrhdHldFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.crRebilHldFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsManHldActTpCd, CFS_MAN_HLD_ACT_TP.RELEASE_OVER_THRESHOLD);
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.invCpltTotDealNetAmt, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.invCpltAmtRate, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.invCpltFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.leasePkgCratFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsLeasePoInfoPk, rs.getBigDecimal(COL_CFS_LEASE_PO_INFO_PK));
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.leaseCmpyUsrId, rs.getString(COL_LEASE_CMPY_USR_ID));
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.leaseCmpyUsrNm, rs.getString(COL_LEASE_CMPY_USR_NM));

                        ordTotDealNetAmt = ordTotDealNetAmt.add(rs.getBigDecimal(COL_ENT_CPO_TOT_DEAL_NET_AMT));
                    } else {

                        if (ZYPCommonFunc.hasValue(ordTotDealNetAmt)
                                && ZYPCommonFunc.hasValue(rs.getBigDecimal(COL_ENT_CPO_TOT_DEAL_NET_AMT))) {
                            ordTotDealNetAmt = ordTotDealNetAmt.add(rs.getBigDecimal(COL_ENT_CPO_TOT_DEAL_NET_AMT));
                        } else if(!ZYPCommonFunc.hasValue(ordTotDealNetAmt)
                                && ZYPCommonFunc.hasValue(rs.getBigDecimal(COL_ENT_CPO_TOT_DEAL_NET_AMT))) {
                            ordTotDealNetAmt = rs.getBigDecimal(COL_ENT_CPO_TOT_DEAL_NET_AMT);
                        }
                    }

                } else {
                    // Update
                    if (cfsPoHdrTMsg == null) {
                        // QC#27439 2018/08/20 Mod Start
//                        cfsPoHdrTMsg = new CFS_LEASE_PKG_PO_HDRTMsg();
//
//                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.glblCmpyCd, this.glblCmpyCd);
//                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsLeasePkgPoHdrPk, cfsLeasePkgPoHdrPk);
//                        cfsPoHdrTMsg = (CFS_LEASE_PKG_PO_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(cfsPoHdrTMsg);
                        cfsPoHdrTMsg = getCfsLeasePkgPoHdrTMsg(cfsLeasePkgPoHdrPk);
                        // QC#27439 2018/08/20 Mod End
                        if (cfsPoHdrTMsg == null) {
                            String[] paramArray = new String[] {"CFS_LEASE_PKG_PO_HDR", String.format("CFS_LEASE_PKG_PO_HDR_PK = %s", cfsLeasePkgPoHdrPk) };
                            String errMsgText = S21MessageFunc.clspGetMessage(NWCM0112E, paramArray);
                            writeLogLn(errMsgText);
                            errorWorkCnt++;
                            continue;
                        }
                    }

                    // Update OrderTotalDealNetAmount
                    if (ZYPCommonFunc.hasValue(ordTotDealNetAmt)
                            && ZYPCommonFunc.hasValue(rs.getBigDecimal(COL_ENT_CPO_TOT_DEAL_NET_AMT))) {
                        ordTotDealNetAmt = ordTotDealNetAmt.add(rs.getBigDecimal(COL_ENT_CPO_TOT_DEAL_NET_AMT));
                    } else if(!ZYPCommonFunc.hasValue(ordTotDealNetAmt)
                            && ZYPCommonFunc.hasValue(rs.getBigDecimal(COL_ENT_CPO_TOT_DEAL_NET_AMT))) {
                        ordTotDealNetAmt = rs.getBigDecimal(COL_ENT_CPO_TOT_DEAL_NET_AMT);
                    }

                }
                // QC#24431 2018/06/08 Mod End
            }

            // Loop End
            // QC#24431 2018/06/08 Mod Start
//            if (errorWorkCnt > 0) {
//                rollback();
//                this.errorCount = this.errorCount + (errorWorkCnt + normalWorkCnt);
//            } else {
//                commit();
//                this.normalCount = this.normalCount + normalWorkCnt;
//            }
            // Therefore 2016/07/26 Mod end
            if (errorWorkCnt > 0) {
                rollback();
                this.errorCount = this.errorCount + (errorWorkCnt + normalWorkCnt);
            } else if (cfsPoHdrTMsg != null) {
                if (!ZYPCommonFunc.hasValue(ordTotDealNetAmt)) {
                    ordTotDealNetAmt = BigDecimal.ZERO;
                }
                if (!ZYPCommonFunc.hasValue(cfsPoHdrTMsg.ordTotDealNetAmt)) {
                    ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.ordTotDealNetAmt, BigDecimal.ZERO);
                }

                boolean isErrFlg = false;
                if (!isNewFlg
                        && ordTotDealNetAmt.compareTo(cfsPoHdrTMsg.ordTotDealNetAmt.getValue()) != 0) {
                    ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.ordTotDealNetAmt, ordTotDealNetAmt);

                    // Update Header
                    // Calculate Amount Rate
                    BigDecimal rate = BigDecimal.ZERO;

                    if (cfsPoHdrTMsg.ordTotDealNetAmt.getValue().compareTo(BigDecimal.ZERO) == 0) {
                        rate = ONE_HUNDRED;
                    } else {
                        rate = cfsPoHdrTMsg.invCpltTotDealNetAmt.getValue().divide(cfsPoHdrTMsg.ordTotDealNetAmt.getValue(), RATE_FRAC_DIGIT, BigDecimal.ROUND_FLOOR).multiply(ONE_HUNDRED);
                    }

                    // Update header rate
                    ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.invCpltAmtRate, rate);

                    // Threshold check
                    if (cfsPoHdrTMsg.invCpltAmtRate.getValue().compareTo(this.invThrhdRate) >= 0) {
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.thrhdHldFlg, ZYPConstant.FLG_OFF_N);

                        // 2022/09/30 QC#60253 Mod Start
                        if (CFS_MAN_HLD_ACT_TP.RELEASE_OVER_THRESHOLD.equals(cfsPoHdrTMsg.cfsManHldActTpCd.getValue())) {
                            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsLeasePkgHldFlg, ZYPConstant.FLG_OFF_N);

                            if (ZYPCommonFunc.hasValue(cfsPoHdrTMsg.cfsLeasePoInfoPk)) {
                                CFS_LEASE_PO_INFOTMsg cfsLeasePoInfoTMsg = new CFS_LEASE_PO_INFOTMsg();
                                ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.glblCmpyCd, this.glblCmpyCd);
                                ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.cfsLeasePoInfoPk, cfsPoHdrTMsg.cfsLeasePoInfoPk.getValue());
                                cfsLeasePoInfoTMsg = (CFS_LEASE_PO_INFOTMsg) EZDTBLAccessor.findByKey(cfsLeasePoInfoTMsg);
                                if (cfsLeasePoInfoTMsg == null) {
                                    String[] paramArray = new String[] {"CFS_LEASE_PO_INFO", String.format("CFS_LEASE_PO_INFO_PK = %s", cfsPoHdrTMsg.cfsLeasePoInfoPk.getValue()) };
                                    String errMsgText = S21MessageFunc.clspGetMessage(NWCM0112E, paramArray);
                                    writeLogLn(errMsgText);
                                    isErrFlg = true;
                                }

                                ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.poInfoProcFlg, ZYPConstant.FLG_ON_Y);
                                EZDTBLAccessor.update(cfsLeasePoInfoTMsg);
                                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsLeasePoInfoTMsg.getReturnCode())) {
                                    String[] paramArray = new String[] {"CFS_LEASE_PO_INFO", String.format("CFS_LEASE_PO_INFO_PK = %s", cfsPoHdrTMsg.cfsLeasePoInfoPk.getValue()) };
                                    String errMsgText = S21MessageFunc.clspGetMessage(NWCM0110E, paramArray);
                                    writeLogLn(errMsgText);
                                    isErrFlg = true;
                                }
                            }
                        }
                        // 2022/09/30 QC#60253 Mod End
                    }
                    EZDTBLAccessor.update(cfsPoHdrTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsPoHdrTMsg.getReturnCode())) {
                        String[] paramArray = new String[] {"CFS_LEASE_PKG_PO_HDR", String.format("CFS_LEASE_PKG_PO_HDR_PK = %s", cfsPoHdrTMsg.cfsLeasePkgPoHdrPk.getValue())};
                        String errMsgText = S21MessageFunc.clspGetMessage(NWCM0110E, paramArray);
                        writeLogLn(errMsgText);
                        isErrFlg = true;
                    }
                }

                if (!isErrFlg) {
                    if(isNewFlg) {
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.ordTotDealNetAmt, ordTotDealNetAmt);

                        EZDTBLAccessor.insert(cfsPoHdrTMsg);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsPoHdrTMsg.getReturnCode())) {
                            String[] paramArray = null;
                            paramArray = new String[] {"CFS_LEASE_PKG_PO_HDR", String.format("CFS_PO_NUM = '%s'", cfsPoHdrTMsg.cfsPoNum.getValue()) };
                            String errMsgText = S21MessageFunc.clspGetMessage(NWCM0109E, paramArray);
                            writeLogLn(errMsgText);
                            isErrFlg = true;
                        }
                    }
                }
                if (isErrFlg) {
                    rollback();
                } else {
                    commit();
                }
            }

            // Detail Process
            // QC#27439 2018/08/20 Add Start
            CFS_LEASE_PKG_PO_HDRTMsg cfsPoHdrTMsgForDtl = null;
            // QC#27439 2018/08/20 Add End

            // get INV
            PreparedStatement stmt = null;
            ResultSet rsInv = null;
            Map<String, Object> ssmDtlParam = new HashMap<String, Object>();
            ssmDtlParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
            ssmDtlParam.put(DB_PARAM_ORD_HDR_STS_CANC, ORD_HDR_STS.CANCELLED);

            stmt = ssmLlcClient.createPreparedStatement("getCfsDtl", ssmDtlParam);
            rsInv = stmt.executeQuery();

            // QC#27439 2018/08/20 Mod Start
//            String prevHdrPk = null;
            BigDecimal prevHdrPk = null;
            // QC#27439 2018/08/20 Mod End

            while (rsInv.next()) {

                if (ZYPCommonFunc.hasValue(prevHdrPk)
//                        && !prevHdrPk.equals(rsInv.getString(COL_CFS_LEASE_PKG_PO_HDR_PK))) {
                        && prevHdrPk.compareTo(rsInv.getBigDecimal(COL_CFS_LEASE_PKG_PO_HDR_PK)) != 0) {
                    if (errorWorkCnt > 0) {
                        rollback();
                        this.errorCount = this.errorCount + (errorWorkCnt + normalWorkCnt);
                    } else {
                        commit();
                        this.normalCount = this.normalCount + normalWorkCnt;
                    }
                    errorWorkCnt = 0;
                    normalWorkCnt = 0;
                    // QC#27439 2018/08/20 Add Start
                    cfsPoHdrTMsgForDtl = getCfsLeasePkgPoHdrTMsg(rsInv.getBigDecimal(COL_CFS_LEASE_PKG_PO_HDR_PK));
                    if (cfsPoHdrTMsgForDtl == null) {
                        String[] paramArray = new String[] {"CFS_LEASE_PKG_PO_HDR", String.format("CFS_LEASE_PKG_PO_HDR_PK = %s", rsInv.getBigDecimal(COL_CFS_LEASE_PKG_PO_HDR_PK)) };
                        String errMsgText = S21MessageFunc.clspGetMessage(NWCM0112E, paramArray);
                        writeLogLn(errMsgText);
                        errorWorkCnt++;
                        continue;
                    }
                    // QC#27439 2018/08/20 Add End
                } else {
                    // QC#27439 2018/08/20 Mod Start
//                    prevHdrPk = rsInv.getString(COL_CFS_LEASE_PKG_PO_HDR_PK);
                    prevHdrPk = rsInv.getBigDecimal(COL_CFS_LEASE_PKG_PO_HDR_PK);

                    if (cfsPoHdrTMsgForDtl == null) {
                        cfsPoHdrTMsgForDtl = getCfsLeasePkgPoHdrTMsg(rsInv.getBigDecimal(COL_CFS_LEASE_PKG_PO_HDR_PK));
                        if (cfsPoHdrTMsgForDtl == null) {
                            String[] paramArray = new String[] {"CFS_LEASE_PKG_PO_HDR", String.format("CFS_LEASE_PKG_PO_HDR_PK = %s", rsInv.getBigDecimal(COL_CFS_LEASE_PKG_PO_HDR_PK)) };
                            String errMsgText = S21MessageFunc.clspGetMessage(NWCM0112E, paramArray);
                            writeLogLn(errMsgText);
                            errorWorkCnt++;
                            continue;
                        }
                    }
                    // QC#27439 2018/08/20 Mod End
                }

                BigDecimal cfsLeasePkgPoHdrPk = rsInv.getBigDecimal(COL_CFS_LEASE_PKG_PO_HDR_PK);

                // Detail
                CFS_LEASE_PKG_PO_DTLTMsg cfsPoDtlTMsg = new CFS_LEASE_PKG_PO_DTLTMsg();

                BigDecimal cfsLeasePkgPoDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CFS_LEASE_PKG_PO_DTL_SQ);

                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.cfsLeasePkgPoDtlPk, cfsLeasePkgPoDtlPk);
                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.cfsLeasePkgPoHdrPk, cfsLeasePkgPoHdrPk);
                // QC#27439 2018/08/20 Mod Start
//                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.custPoNum, cfsPoHdrTMsg.custPoNum);
                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.custPoNum, cfsPoHdrTMsgForDtl.custPoNum);
                // QC#27439 2018/08/20 Mod End
                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.cpoOrdNum, rsInv.getString(COL_CPO_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invNum, rsInv.getString(COL_INV_NUM));
                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invDt, rsInv.getString(COL_INV_DT));
                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.glDt, rsInv.getString(COL_ACCT_DT));
                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.dsAcctNum, rsInv.getString(COL_BILL_TO_CUST_ACCT_CD));
                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.billToCustCd, rsInv.getString(COL_BILL_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.negoDealAmt, rsInv.getBigDecimal(COL_NEGO_DEAL_AMT));
                String invTpCd = rsInv.getString(COL_INV_TP_CD);
                if (INV_TP.CREDIT_MEMO.equals(invTpCd)) {
                    BigDecimal invTotDealNetAmt = rsInv.getBigDecimal(COL_INV_TOT_DEAL_NET_AMT);
                    ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invTotDealNetAmt, invTotDealNetAmt.negate());
                } else {
                    ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invTotDealNetAmt, rsInv.getBigDecimal(COL_INV_TOT_DEAL_NET_AMT));;
                }
                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invTotDealSlsAmt, rsInv.getBigDecimal(COL_INV_TOT_DEAL_SLS_AMT));
                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invTotDealTaxAmt, rsInv.getBigDecimal(COL_INV_TOT_DEAL_TAX_AMT));
                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invTotDealDiscAmt, rsInv.getBigDecimal(COL_INV_TOT_DEAL_DISC_AMT));
                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invTotDealFrtAmt, rsInv.getBigDecimal(COL_INV_TOT_DEAL_FRT_AMT));
                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.hdrBrCd, rsInv.getString(COL_COA_BR_CD));
                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.tocNm, rsInv.getString(COL_SLS_REP_TOC_NM));
                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.tocCd, rsInv.getString(COL_SLS_REP_TOC_CD));
                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.adminPsnFirstNm, rsInv.getString(COL_PSN_FIRST_NM));
                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.adminPsnLastNm, rsInv.getString(COL_PSN_LAST_NM));
                //ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.adminPsnCd, rsInv.getString(COL_SLS_ADMIN_PSN_CD));
                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.adminPsnCd, rsInv.getString(COL_ORD_BOOK_REQ_USR_ID));
                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.arTrxBalPk, rsInv.getBigDecimal(COL_AR_TRX_BAL_PK));
                EZDTBLAccessor.insert(cfsPoDtlTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsPoDtlTMsg.getReturnCode())) {
                    String[] paramArray = new String[] {"CFS_LEASE_PKG_PO_DTL", String.format("CFS_LEASE_PKG_PO_HDR_PK = %s, CPO_ORD_NUM = '%s'", cfsLeasePkgPoHdrPk, rs.getString(COL_CPO_ORD_NUM))};
                    String errMsgText = S21MessageFunc.clspGetMessage(NWCM0109E, paramArray);
                    writeLogLn(errMsgText);
                    errorWorkCnt++;
                    continue;
                }

                // Update Header
                String crRebillRsnCatgCd = rsInv.getString(COL_CR_REBIL_RSN_CATG_CD);

                // QC#27439 2018/08/20 Mod Start
//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsRepTocNm, rsInv.getString(COL_SLS_REP_TOC_NM));
                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsgForDtl.cfsRepTocNm, rsInv.getString(COL_SLS_REP_TOC_NM));
                // QC#27439 2018/08/20 Mod End

                if (crRebillRsnCatgCd != null) {
                    // QC#27439 2018/08/20 Mod Start
//                    ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.crRebilHldFlg, ZYPConstant.FLG_ON_Y);
//                    ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsManHldActTpCd, CFS_MAN_HLD_ACT_TP.HOLD);
                    ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsgForDtl.crRebilHldFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsgForDtl.cfsManHldActTpCd, CFS_MAN_HLD_ACT_TP.HOLD);
                    // QC#27439 2018/08/20 Mod End
                }

                // Calculate Total Amount
                // QC#27439 2018/08/20 Mod Start
//                BigDecimal invCpltTotDealNetAmt = cfsPoHdrTMsg.invCpltTotDealNetAmt.getValue();
                BigDecimal invCpltTotDealNetAmt = cfsPoHdrTMsgForDtl.invCpltTotDealNetAmt.getValue();
                BigDecimal slsAmt = null;
                // 2022/11/02 QC#60253 Mod Start N.Takatsu
                slsAmt = cfsPoDtlTMsg.invTotDealNetAmt.getValue();
                // slsAmt = cfsPoDtlTMsg.invTotDealSlsAmt.getValue().add(cfsPoDtlTMsg.invTotDealDiscAmt.getValue());
                // 2022/11/02 QC#60253 Mod END N.Takatsu

                if (INV_TP.CREDIT_MEMO.equals(invTpCd)) {
                    invCpltTotDealNetAmt = invCpltTotDealNetAmt.subtract(slsAmt);
                } else {
                    invCpltTotDealNetAmt = invCpltTotDealNetAmt.add(slsAmt);
                }

//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.invCpltTotDealNetAmt, invCpltTotDealNetAmt);
                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsgForDtl.invCpltTotDealNetAmt, invCpltTotDealNetAmt);

                // Calculate Amount Rate
                BigDecimal rate = BigDecimal.ZERO;

//                if (cfsPoHdrTMsg.ordTotDealNetAmt.getValue().compareTo(BigDecimal.ZERO) == 0) {
                //2022/09/30 QC#60253 Mod Start
                if (!ZYPCommonFunc.hasValue(cfsPoHdrTMsgForDtl.ordTotDealNetAmt)) {
                    rate = ONE_HUNDRED;
                } else if (cfsPoHdrTMsgForDtl.ordTotDealNetAmt.getValue().compareTo(BigDecimal.ZERO) == 0) {
                    rate = ONE_HUNDRED;
                } else {
//                    rate = cfsPoHdrTMsg.invCpltTotDealNetAmt.getValue().divide(cfsPoHdrTMsg.ordTotDealNetAmt.getValue(), RATE_FRAC_DIGIT, BigDecimal.ROUND_FLOOR).multiply(ONE_HUNDRED);
                    rate = cfsPoHdrTMsgForDtl.invCpltTotDealNetAmt.getValue().divide(cfsPoHdrTMsgForDtl.ordTotDealNetAmt.getValue(), RATE_FRAC_DIGIT, BigDecimal.ROUND_FLOOR).multiply(ONE_HUNDRED);
                }
                //2022/09/30 QC#60253 Mod End

//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.invCpltAmtRate, rate);
                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsgForDtl.invCpltAmtRate, rate);

//                if (cfsPoHdrTMsg.invCpltAmtRate.getValue().compareTo(this.invThrhdRate) >= 0) {
                if (cfsPoHdrTMsgForDtl.invCpltAmtRate.getValue().compareTo(this.invThrhdRate) >= 0) {
//                    ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.thrhdHldFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsgForDtl.thrhdHldFlg, ZYPConstant.FLG_OFF_N);

//                    if (CFS_MAN_HLD_ACT_TP.RELEASE_OVER_THRESHOLD.equals(cfsPoHdrTMsg.cfsManHldActTpCd.getValue())) {
                    if (CFS_MAN_HLD_ACT_TP.RELEASE_OVER_THRESHOLD.equals(cfsPoHdrTMsgForDtl.cfsManHldActTpCd.getValue())) {
//                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsLeasePkgHldFlg, ZYPConstant.FLG_OFF_N);
                        // 2022/11/02 QC#60253 Mod Start N.Takatsu
                        // 2022/09/30 QC#60253 Mod Start
                        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsgForDtl.cfsLeasePkgHldFlg, ZYPConstant.FLG_OFF_N);

                        if (ZYPCommonFunc.hasValue(cfsPoHdrTMsgForDtl.cfsLeasePoInfoPk)) {
//                            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsgForDtl.cfsLeasePkgHldFlg, ZYPConstant.FLG_OFF_N);
                            CFS_LEASE_PO_INFOTMsg cfsLeasePoInfoTMsg = new CFS_LEASE_PO_INFOTMsg();
                            ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.glblCmpyCd, this.glblCmpyCd);
//                            ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.cfsLeasePoInfoPk, cfsPoHdrTMsg.cfsLeasePoInfoPk.getValue());
                            ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.cfsLeasePoInfoPk, cfsPoHdrTMsgForDtl.cfsLeasePoInfoPk.getValue());
                            cfsLeasePoInfoTMsg = (CFS_LEASE_PO_INFOTMsg) EZDTBLAccessor.findByKey(cfsLeasePoInfoTMsg);
                            if (cfsLeasePoInfoTMsg == null) {
//                                String[] paramArray = new String[] {"CFS_LEASE_PO_INFO", String.format("CFS_LEASE_PO_INFO_PK = %s", cfsPoHdrTMsg.cfsLeasePoInfoPk.getValue())};
                                String[] paramArray = new String[] {"CFS_LEASE_PO_INFO", String.format("CFS_LEASE_PO_INFO_PK = %s", cfsPoHdrTMsgForDtl.cfsLeasePoInfoPk.getValue())};
                                String errMsgText = S21MessageFunc.clspGetMessage(NWCM0112E, paramArray);
                                writeLogLn(errMsgText);
                                errorWorkCnt++;
                                continue;
                            }

                            ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.poInfoProcFlg, ZYPConstant.FLG_ON_Y);
                            EZDTBLAccessor.update(cfsLeasePoInfoTMsg);
                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsLeasePoInfoTMsg.getReturnCode())) {
//                                String[] paramArray = new String[] {"CFS_LEASE_PO_INFO", String.format("CFS_LEASE_PO_INFO_PK = %s", cfsPoHdrTMsg.cfsLeasePoInfoPk.getValue())};
                                String[] paramArray = new String[] {"CFS_LEASE_PO_INFO", String.format("CFS_LEASE_PO_INFO_PK = %s", cfsPoHdrTMsgForDtl.cfsLeasePoInfoPk.getValue())};
                                String errMsgText = S21MessageFunc.clspGetMessage(NWCM0110E, paramArray);
                                writeLogLn(errMsgText);
                                errorWorkCnt++;
                                continue;
                            }
                        }
                        // 2022/09/30 QC#60253 Mod End
                        // 2022/11/02 QC#60253 Mod End N.Takatsu
                    }
                }

//                EZDTBLAccessor.update(cfsPoHdrTMsg);
                EZDTBLAccessor.update(cfsPoHdrTMsgForDtl);
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsPoHdrTMsg.getReturnCode())) {
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsPoHdrTMsgForDtl.getReturnCode())) {
                    String[] paramArray = new String[] {"CFS_LEASE_PKG_PO_HDR", String.format("CFS_LEASE_PKG_PO_HDR_PK = %s", cfsLeasePkgPoHdrPk)};
                    String errMsgText = S21MessageFunc.clspGetMessage(NWCM0110E, paramArray);
                    writeLogLn(errMsgText);
                    errorWorkCnt++;
                    continue;
                }
                // QC#27439 2018/08/20 Mod End

                normalWorkCnt++;
            }

            if (errorWorkCnt > 0) {
                rollback();
                this.errorCount = this.errorCount + (errorWorkCnt + normalWorkCnt);
            } else {
                commit();
                this.normalCount = this.normalCount + normalWorkCnt;
            }
            // QC#24431 2018/06/08 Mod End
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    // QC#24431 2018/05/24 Add Start
    // ****************************************************************
    // Main Process
    // ****************************************************************
//    private boolean mainProcess(ResultSet rs) throws SQLException {
//
//        CFS_LEASE_PKG_PO_HDRTMsg cfsPoHdrTMsg = CreateCfsLeasePkgPoHdr(rs);
//        if (cfsPoHdrTMsg == null) {
//            return true;
//        }
//
//        // CreateDetail
//        return CreateDetail(rs, cfsPoHdrTMsg);
//
//    }

//    private CFS_LEASE_PKG_PO_HDRTMsg CreateCfsLeasePkgPoHdr(ResultSet rs) throws SQLException {
//
//        String cpoOrdNum = null;
//        String appNum = null;
//        if (MODE_CD_01.equals(this.paramModeCd)) {
//            cpoOrdNum = rs.getString(COL_CPO_ORD_NUM);
//        } else {
//            cpoOrdNum = rs.getString(COL_CFS_PO_NUM);
//            appNum = rs.getString(COL_CFS_APP_NUM);
//        }
//        CFS_LEASE_PKG_PO_HDRTMsg cfsPoHdrTMsg = new CFS_LEASE_PKG_PO_HDRTMsg();
//
//        BigDecimal cfsLeasePkgPoHdrPk = getCfsLeasePkgPoHdr(cpoOrdNum, appNum);
//        if (cfsLeasePkgPoHdrPk == null) {
//            // insert
//            cfsLeasePkgPoHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CFS_LEASE_PKG_PO_HDR_SQ);
//
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.glblCmpyCd, this.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsLeasePkgPoHdrPk, cfsLeasePkgPoHdrPk);
//            if (MODE_CD_02.equals(this.paramModeCd)) {
//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsAppNum, rs.getString(COL_CFS_APP_NUM));
//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsPoNum, rs.getString(COL_CFS_PO_NUM));
//            }
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cpoOrdNum, rs.getString(COL_CPO_ORD_NUM));
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.dsAcctNum, rs.getString(COL_SHIP_TO_CUST_ACCT_CD));
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.dsAcctNm, rs.getString(COL_DS_ACCT_NM));
//            if (MODE_CD_01.equals(this.paramModeCd)) {
//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsPoAmt, rs.getBigDecimal(COL_CPO_TOT_DEAL_NET_AMT));
//            } else {
//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsPoAmt, rs.getBigDecimal(COL_CFS_PO_AMT));
//            }
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsRepTocCd, rs.getString(COL_SLS_REP_TOC_CD));
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsLeasePkgHldFlg, ZYPConstant.FLG_ON_Y);
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.thrhdHldFlg, ZYPConstant.FLG_ON_Y);
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.crRebilHldFlg, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsManHldActTpCd, CFS_MAN_HLD_ACT_TP.RELEASE_OVER_THRESHOLD);
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.invCpltTotDealNetAmt, BigDecimal.ZERO);
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.invCpltAmtRate, BigDecimal.ZERO);
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.invCpltFlg, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.leasePkgCratFlg, ZYPConstant.FLG_OFF_N);
//            if (MODE_CD_02.equals(this.paramModeCd)) {
//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsLeasePoInfoPk, rs.getBigDecimal(COL_CFS_LEASE_PO_INFO_PK));
//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.leaseCmpyUsrId, rs.getString(COL_LEASE_CMPY_USR_ID));
//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.leaseCmpyUsrNm, rs.getString(COL_LEASE_CMPY_USR_NM));
//            }
//
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.ordTotDealNetAmt, rs.getBigDecimal(COL_ENT_CPO_TOT_DEAL_NET_AMT));
//
//            EZDTBLAccessor.insert(cfsPoHdrTMsg);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsPoHdrTMsg.getReturnCode())) {
//                String[] paramArray = null;
//                if (MODE_CD_01.equals(this.paramModeCd)) {
//                    paramArray = new String[] {"CFS_LEASE_PKG_PO_HDR", String.format("CPO_ORD_NUM = '%s'", cpoOrdNum) };
//                } else {
//                    paramArray = new String[] {"CFS_LEASE_PKG_PO_HDR", String.format("CFS_PO_NUM = '%s'", cpoOrdNum) };
//                }
//                String errMsgText = S21MessageFunc.clspGetMessage(NWCM0109E, paramArray);
//                writeLogLn(errMsgText);
//                return null;
//            }
//        } else {
//            // Get cfsLeasePkgPoHdr
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.glblCmpyCd, this.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsLeasePkgPoHdrPk, cfsLeasePkgPoHdrPk);
//            cfsPoHdrTMsg = (CFS_LEASE_PKG_PO_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(cfsPoHdrTMsg);
//            if (cfsPoHdrTMsg == null) {
//                String[] paramArray = new String[] {"CFS_LEASE_PKG_PO_HDR", String.format("CFS_LEASE_PKG_PO_HDR_PK = %s", cfsLeasePkgPoHdrPk) };
//                String errMsgText = S21MessageFunc.clspGetMessage(NWCM0112E, paramArray);
//                writeLogLn(errMsgText);
//                return null;
//            }
//
//            // Update
//            // Update check
//            if ( cfsPoHdrTMsg.ordTotDealNetAmt.getValue().compareTo(rs.getBigDecimal(COL_ENT_CPO_TOT_DEAL_NET_AMT)) == 0 ){
//                return cfsPoHdrTMsg;
//            }
//
//            // Update OrderTotalDealNetAmount
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.ordTotDealNetAmt, rs.getBigDecimal(COL_ENT_CPO_TOT_DEAL_NET_AMT));
//
//            // Update Header
//            // Calculate Amount Rate
//            BigDecimal rate = BigDecimal.ZERO;
//
//            if (cfsPoHdrTMsg.ordTotDealNetAmt.getValue().compareTo(BigDecimal.ZERO) == 0) {
//                rate = ONE_HUNDRED;
//            } else {
//                rate = cfsPoHdrTMsg.invCpltTotDealNetAmt.getValue().divide(cfsPoHdrTMsg.ordTotDealNetAmt.getValue(), RATE_FRAC_DIGIT, BigDecimal.ROUND_FLOOR).multiply(ONE_HUNDRED);
//            }
//
//            // Update header rate
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.invCpltAmtRate, rate);
//
//            // Threshold check
//            if (cfsPoHdrTMsg.invCpltAmtRate.getValue().compareTo(this.invThrhdRate) >= 0) {
//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.thrhdHldFlg, ZYPConstant.FLG_OFF_N);
//
//                if (CFS_MAN_HLD_ACT_TP.RELEASE_OVER_THRESHOLD.equals(cfsPoHdrTMsg.cfsManHldActTpCd.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsLeasePkgHldFlg, ZYPConstant.FLG_OFF_N);
//
//                    if (MODE_CD_02.equals(this.paramModeCd)) {
//                        CFS_LEASE_PO_INFOTMsg cfsLeasePoInfoTMsg = new CFS_LEASE_PO_INFOTMsg();
//                        ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.glblCmpyCd, this.glblCmpyCd);
//                        ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.cfsLeasePoInfoPk, cfsPoHdrTMsg.cfsLeasePoInfoPk.getValue());
//                        cfsLeasePoInfoTMsg = (CFS_LEASE_PO_INFOTMsg) EZDTBLAccessor.findByKey(cfsLeasePoInfoTMsg);
//                        if (cfsLeasePoInfoTMsg == null) {
//                            String[] paramArray = new String[] {"CFS_LEASE_PO_INFO", String.format("CFS_LEASE_PO_INFO_PK = %s", cfsPoHdrTMsg.cfsLeasePoInfoPk.getValue())};
//                            String errMsgText = S21MessageFunc.clspGetMessage(NWCM0112E, paramArray);
//                            writeLogLn(errMsgText);
//                            return null;
//                        }
//
//                        ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.poInfoProcFlg, ZYPConstant.FLG_ON_Y);
//                        EZDTBLAccessor.update(cfsLeasePoInfoTMsg);
//                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsLeasePoInfoTMsg.getReturnCode())) {
//                            String[] paramArray = new String[] {"CFS_LEASE_PO_INFO", String.format("CFS_LEASE_PO_INFO_PK = %s", cfsPoHdrTMsg.cfsLeasePoInfoPk.getValue())};
//                            String errMsgText = S21MessageFunc.clspGetMessage(NWCM0110E, paramArray);
//                            writeLogLn(errMsgText);
//                            return null;
//                        }
//                    }
//                }
//            }
//            EZDTBLAccessor.update(cfsPoHdrTMsg);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsPoHdrTMsg.getReturnCode())) {
//                String[] paramArray = new String[] {"CFS_LEASE_PKG_PO_HDR", String.format("CFS_LEASE_PKG_PO_HDR_PK = %s", cfsLeasePkgPoHdrPk)};
//                String errMsgText = S21MessageFunc.clspGetMessage(NWCM0110E, paramArray);
//                writeLogLn(errMsgText);
//                return null;
//            }
//
//        }
//
//        return cfsPoHdrTMsg;
//    }

//    private boolean CreateDetail(ResultSet rs, CFS_LEASE_PKG_PO_HDRTMsg cfsPoHdrTMsg) throws SQLException {
//
//        // get INV
//        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
//        PreparedStatement stmt = null;
//        ResultSet rsInv = null;
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
//        ssmParam.put(DB_PARAM_CPO_ORD_NUM, cfsPoHdrTMsg.cpoOrdNum);
//        if (MODE_CD_02.equals(this.paramModeCd)) {
//            ssmParam.put(DB_PARAM_MODE_CD, this.paramModeCd);
//        }
//
//        stmt = ssmLlcClient.createPreparedStatement("getInv", ssmParam);
//        rsInv = stmt.executeQuery();
//
//        while (rsInv.next()) {
//
//            String cpoOrdNum = null;
//            String appNum = null;
//            if (MODE_CD_01.equals(this.paramModeCd)) {
//                cpoOrdNum = rs.getString(COL_CPO_ORD_NUM);
//            } else {
//                cpoOrdNum = rs.getString(COL_CFS_PO_NUM);
//                appNum = rs.getString(COL_CFS_APP_NUM);
//            }
//
//            BigDecimal cfsLeasePkgPoHdrPk = getCfsLeasePkgPoHdr(cpoOrdNum, appNum);
//
//            // Detail
//            CFS_LEASE_PKG_PO_DTLTMsg cfsPoDtlTMsg = new CFS_LEASE_PKG_PO_DTLTMsg();
//
//            BigDecimal cfsLeasePkgPoDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CFS_LEASE_PKG_PO_DTL_SQ);
//
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.glblCmpyCd, this.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.cfsLeasePkgPoDtlPk, cfsLeasePkgPoDtlPk);
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.cfsLeasePkgPoHdrPk, cfsPoHdrTMsg.cfsLeasePkgPoHdrPk);
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.custPoNum, cfsPoHdrTMsg.custPoNum);
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.cpoOrdNum, rs.getString(COL_CPO_ORD_NUM));
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invNum, rsInv.getString(COL_INV_NUM));
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invDt, rsInv.getString(COL_INV_DT));
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.glDt, rsInv.getString(COL_ACCT_DT));
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.dsAcctNum, rsInv.getString(COL_BILL_TO_CUST_ACCT_CD));
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.billToCustCd, rsInv.getString(COL_BILL_TO_CUST_CD));
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.negoDealAmt, rsInv.getBigDecimal(COL_NEGO_DEAL_AMT));
//            String invTpCd = rsInv.getString(COL_INV_TP_CD);
//            if (INV_TP.CREDIT_MEMO.equals(invTpCd)) {
//                BigDecimal invTotDealNetAmt = rsInv.getBigDecimal(COL_INV_TOT_DEAL_NET_AMT);
//                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invTotDealNetAmt, invTotDealNetAmt.negate());
//            } else {
//                ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invTotDealNetAmt, rsInv.getBigDecimal(COL_INV_TOT_DEAL_NET_AMT));;
//            }
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invTotDealSlsAmt, rsInv.getBigDecimal(COL_INV_TOT_DEAL_SLS_AMT));
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invTotDealTaxAmt, rsInv.getBigDecimal(COL_INV_TOT_DEAL_TAX_AMT));
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invTotDealDiscAmt, rsInv.getBigDecimal(COL_INV_TOT_DEAL_DISC_AMT));
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invTotDealFrtAmt, rsInv.getBigDecimal(COL_INV_TOT_DEAL_FRT_AMT));
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.hdrBrCd, rsInv.getString(COL_COA_BR_CD));
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.tocNm, rsInv.getString(COL_SLS_REP_TOC_NM));
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.tocCd, rsInv.getString(COL_SLS_REP_TOC_CD));
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.adminPsnFirstNm, rsInv.getString(COL_PSN_FIRST_NM));
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.adminPsnLastNm, rsInv.getString(COL_PSN_LAST_NM));
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.adminPsnCd, rsInv.getString(COL_SLS_ADMIN_PSN_CD));
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.arTrxBalPk, rsInv.getBigDecimal(COL_AR_TRX_BAL_PK));
//
//            EZDTBLAccessor.insert(cfsPoDtlTMsg);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsPoDtlTMsg.getReturnCode())) {
//                String[] paramArray = new String[] {"CFS_LEASE_PKG_PO_DTL", String.format("CFS_LEASE_PKG_PO_HDR_PK = %s, CPO_ORD_NUM = '%s'", cfsLeasePkgPoHdrPk, rs.getString(COL_CPO_ORD_NUM))};
//                String errMsgText = S21MessageFunc.clspGetMessage(NWCM0109E, paramArray);
//                writeLogLn(errMsgText);
//                return false;
//            }
//
//            // Update Header
//            String crRebillRsnCatgCd = rsInv.getString(COL_CR_REBIL_RSN_CATG_CD);
//
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsRepTocNm, rsInv.getString(COL_SLS_REP_TOC_NM));
//
//            if (crRebillRsnCatgCd != null) {
//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.crRebilHldFlg, ZYPConstant.FLG_ON_Y);
//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsManHldActTpCd, CFS_MAN_HLD_ACT_TP.HOLD);
//            }
//
//            // Calculate Total Amount
//            BigDecimal invCpltTotDealNetAmt = cfsPoHdrTMsg.invCpltTotDealNetAmt.getValue();
//            BigDecimal slsAmt = null;
//            if (MODE_CD_01.equals(this.paramModeCd)) {
//                slsAmt = cfsPoDtlTMsg.invTotDealNetAmt.getValue()
//                .subtract(cfsPoDtlTMsg.invTotDealTaxAmt.getValue())
//                .subtract(cfsPoDtlTMsg.invTotDealFrtAmt.getValue());
//            } else {
//                slsAmt = cfsPoDtlTMsg.invTotDealSlsAmt.getValue()
//                .add(cfsPoDtlTMsg.invTotDealDiscAmt.getValue());
//            }
//
//            if (INV_TP.CREDIT_MEMO.equals(invTpCd)) {
//                invCpltTotDealNetAmt = invCpltTotDealNetAmt.subtract(slsAmt);
//            } else {
//                invCpltTotDealNetAmt = invCpltTotDealNetAmt.add(slsAmt);
//            }
//
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.invCpltTotDealNetAmt, invCpltTotDealNetAmt);
//
//            // Calculate Amount Rate
//            BigDecimal rate = BigDecimal.ZERO;
//
//            if (cfsPoHdrTMsg.ordTotDealNetAmt.getValue().compareTo(BigDecimal.ZERO) == 0) {
//                rate = ONE_HUNDRED;
//            } else {
//                rate = cfsPoHdrTMsg.invCpltTotDealNetAmt.getValue().divide(cfsPoHdrTMsg.ordTotDealNetAmt.getValue(), RATE_FRAC_DIGIT, BigDecimal.ROUND_FLOOR).multiply(ONE_HUNDRED);
//            }
//
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.invCpltAmtRate, rate);
//
//            if (cfsPoHdrTMsg.invCpltAmtRate.getValue().compareTo(this.invThrhdRate) >= 0) {
//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.thrhdHldFlg, ZYPConstant.FLG_OFF_N);
//
//                if (CFS_MAN_HLD_ACT_TP.RELEASE_OVER_THRESHOLD.equals(cfsPoHdrTMsg.cfsManHldActTpCd.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsLeasePkgHldFlg, ZYPConstant.FLG_OFF_N);
//
//                    if (MODE_CD_02.equals(this.paramModeCd)) {
//                        CFS_LEASE_PO_INFOTMsg cfsLeasePoInfoTMsg = new CFS_LEASE_PO_INFOTMsg();
//                        ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.glblCmpyCd, this.glblCmpyCd);
//                        ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.cfsLeasePoInfoPk, cfsPoHdrTMsg.cfsLeasePoInfoPk.getValue());
//                        cfsLeasePoInfoTMsg = (CFS_LEASE_PO_INFOTMsg) EZDTBLAccessor.findByKey(cfsLeasePoInfoTMsg);
//                        if (cfsLeasePoInfoTMsg == null) {
//                            String[] paramArray = new String[] {"CFS_LEASE_PO_INFO", String.format("CFS_LEASE_PO_INFO_PK = %s", cfsPoHdrTMsg.cfsLeasePoInfoPk.getValue())};
//                            String errMsgText = S21MessageFunc.clspGetMessage(NWCM0112E, paramArray);
//                            writeLogLn(errMsgText);
//                            return false;
//                        }
//
//                        ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.poInfoProcFlg, ZYPConstant.FLG_ON_Y);
//                        EZDTBLAccessor.update(cfsLeasePoInfoTMsg);
//                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsLeasePoInfoTMsg.getReturnCode())) {
//                            String[] paramArray = new String[] {"CFS_LEASE_PO_INFO", String.format("CFS_LEASE_PO_INFO_PK = %s", cfsPoHdrTMsg.cfsLeasePoInfoPk.getValue())};
//                            String errMsgText = S21MessageFunc.clspGetMessage(NWCM0110E, paramArray);
//                            writeLogLn(errMsgText);
//                            return false;
//                        }
//                    }
//                }
//            }
//
//            EZDTBLAccessor.update(cfsPoHdrTMsg);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsPoHdrTMsg.getReturnCode())) {
//                String[] paramArray = new String[] {"CFS_LEASE_PKG_PO_HDR", String.format("CFS_LEASE_PKG_PO_HDR_PK = %s", cfsLeasePkgPoHdrPk)};
//                String errMsgText = S21MessageFunc.clspGetMessage(NWCM0110E, paramArray);
//                writeLogLn(errMsgText);
//                return false;
//            }
//        }
//        return true;
//
//    }

    // QC#24431 2018/05/24 Add End

    // QC#24431 2018/05/24 Del Start
    // ****************************************************************
    // Main Process
    // ****************************************************************
//    private boolean mainProcess(ResultSet rs) throws SQLException {
//
//        // Therefore 2016/07/26 Mod start
////        String cpoOrdNum = rs.getString(COL_CPO_ORD_NUM);
//        String cpoOrdNum = null;
//        String appNum = null;
//        if (MODE_CD_01.equals(this.paramModeCd)) {
//            cpoOrdNum = rs.getString(COL_CPO_ORD_NUM);
//        } else {
//            cpoOrdNum = rs.getString(COL_CFS_PO_NUM);
//            appNum = rs.getString(COL_CFS_APP_NUM);
//        }
//        // Therefore 2016/07/26 Mod end
//        CFS_LEASE_PKG_PO_HDRTMsg cfsPoHdrTMsg = new CFS_LEASE_PKG_PO_HDRTMsg();
//
//        BigDecimal cfsLeasePkgPoHdrPk = getCfsLeasePkgPoHdr(cpoOrdNum, appNum);
//        if (cfsLeasePkgPoHdrPk == null) {
//
//            cfsLeasePkgPoHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CFS_LEASE_PKG_PO_HDR_SQ);
//
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.glblCmpyCd, this.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsLeasePkgPoHdrPk, cfsLeasePkgPoHdrPk);
//            // Therefore 2016/07/26 Add start
//            if (MODE_CD_02.equals(this.paramModeCd)) {
//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsAppNum, rs.getString(COL_CFS_APP_NUM));
//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsPoNum, rs.getString(COL_CFS_PO_NUM));
//            }
//            // Therefore 2016/07/26 Add end
//            // Therefore 2016/07/26 Mod start
////            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cpoOrdNum, cpoOrdNum);
//            // QC#20381 2017/09/21 Mod start
//            //if (MODE_CD_01.equals(this.paramModeCd)) {
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cpoOrdNum, rs.getString(COL_CPO_ORD_NUM));
//            //}
//            // QC#20381 2017/09/21 Mod End
//            // Therefore 2016/07/26 Mod end
//            // ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.dsAcctNum, rs.getString(COL_BILL_TO_CUST_ACCT_CD));
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.dsAcctNum, rs.getString(COL_SHIP_TO_CUST_ACCT_CD)); // 2018/01/22 S21_NA#23439 Mod
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.dsAcctNm, rs.getString(COL_DS_ACCT_NM));
//            // Therefore 2016/07/26 Mod start
////            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsPoAmt, rs.getBigDecimal(COL_CPO_TOT_DEAL_NET_AMT));
//            if (MODE_CD_01.equals(this.paramModeCd)) {
//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsPoAmt, rs.getBigDecimal(COL_CPO_TOT_DEAL_NET_AMT));
//            } else {
//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsPoAmt, rs.getBigDecimal(COL_CFS_PO_AMT));
//            }
//            // Therefore 2016/07/26 Mod end
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsRepTocNm, rs.getString(COL_SLS_REP_TOC_NM));
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsRepTocCd, rs.getString(COL_SLS_REP_TOC_CD));
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsLeasePkgHldFlg, ZYPConstant.FLG_ON_Y);
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.thrhdHldFlg, ZYPConstant.FLG_ON_Y);
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.crRebilHldFlg, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsManHldActTpCd, CFS_MAN_HLD_ACT_TP.RELEASE_OVER_THRESHOLD);
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.invCpltTotDealNetAmt, BigDecimal.ZERO);
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.invCpltAmtRate, BigDecimal.ZERO);
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.invCpltFlg, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.leasePkgCratFlg, ZYPConstant.FLG_OFF_N);
//            // Therefore 2016/07/26 Add start
//            if (MODE_CD_02.equals(this.paramModeCd)) {
//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsLeasePoInfoPk, rs.getBigDecimal(COL_CFS_LEASE_PO_INFO_PK));
//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.leaseCmpyUsrId, rs.getString(COL_LEASE_CMPY_USR_ID));
//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.leaseCmpyUsrNm, rs.getString(COL_LEASE_CMPY_USR_NM));
//            }
//            // Therefore 2016/07/26 Add end
//
//            EZDTBLAccessor.insert(cfsPoHdrTMsg);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsPoHdrTMsg.getReturnCode())) {
//                // Therefore 2016/07/26 Mod start
////                String[] paramArray = new String[] {"CFS_LEASE_PKG_PO_HDR", String.format("CPO_ORD_NUM = '%s'", cpoOrdNum)};
//                String[] paramArray = null;
//                if (MODE_CD_01.equals(this.paramModeCd)) {
//                    paramArray = new String[] {"CFS_LEASE_PKG_PO_HDR", String.format("CPO_ORD_NUM = '%s'", cpoOrdNum)};
//                } else {
//                    paramArray = new String[] {"CFS_LEASE_PKG_PO_HDR", String.format("CFS_PO_NUM = '%s'", cpoOrdNum)};
//                }
//                // Therefore 2016/07/26 Mod end
//                String errMsgText = S21MessageFunc.clspGetMessage(NWCM0109E, paramArray);
//                writeLogLn(errMsgText);
//                return false;
//            }
//        } else {
//            // Get cfsLeasePkgPoHdr
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.glblCmpyCd, this.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsLeasePkgPoHdrPk, cfsLeasePkgPoHdrPk);
//            cfsPoHdrTMsg = (CFS_LEASE_PKG_PO_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(cfsPoHdrTMsg);
//            if (cfsPoHdrTMsg == null) {
//                String[] paramArray = new String[] {"CFS_LEASE_PKG_PO_HDR", String.format("CFS_LEASE_PKG_PO_HDR_PK = %s", cfsLeasePkgPoHdrPk)};
//                String errMsgText = S21MessageFunc.clspGetMessage(NWCM0112E, paramArray);
//                writeLogLn(errMsgText);
//                return false;
//            }
//        }
//
//        // Detail
//        CFS_LEASE_PKG_PO_DTLTMsg cfsPoDtlTMsg = new CFS_LEASE_PKG_PO_DTLTMsg();
//
//        BigDecimal cfsLeasePkgPoDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CFS_LEASE_PKG_PO_DTL_SQ);
//
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.glblCmpyCd, this.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.cfsLeasePkgPoDtlPk, cfsLeasePkgPoDtlPk);
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.cfsLeasePkgPoHdrPk, cfsPoHdrTMsg.cfsLeasePkgPoHdrPk);
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.custPoNum, cfsPoHdrTMsg.custPoNum);
//        // Therefore 2016/07/26 Mod start
////        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.cpoOrdNum, cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.cpoOrdNum, rs.getString(COL_CPO_ORD_NUM));
//        // Therefore 2016/07/26 Mod end
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invNum, rs.getString(COL_INV_NUM));
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invDt, rs.getString(COL_INV_DT));
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.glDt, rs.getString(COL_ACCT_DT));
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.dsAcctNum, rs.getString(COL_BILL_TO_CUST_ACCT_CD));
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.billToCustCd, rs.getString(COL_BILL_TO_CUST_CD));
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.negoDealAmt, rs.getBigDecimal(COL_NEGO_DEAL_AMT));
//        // QC#24315 2018/02/22 Mod start
////        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invTotDealNetAmt, rs.getBigDecimal(COL_INV_TOT_DEAL_NET_AMT));
//        String invTpCd = rs.getString(COL_INV_TP_CD);
//        if (INV_TP.CREDIT_MEMO.equals(invTpCd)) {
//            BigDecimal invTotDealNetAmt = rs.getBigDecimal(COL_INV_TOT_DEAL_NET_AMT);
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invTotDealNetAmt, invTotDealNetAmt.negate());
//        } else {
//            ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invTotDealNetAmt, rs.getBigDecimal(COL_INV_TOT_DEAL_NET_AMT));;
//        }
//        // QC#24315 2018/02/22 Mod end
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invTotDealSlsAmt, rs.getBigDecimal(COL_INV_TOT_DEAL_SLS_AMT));
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invTotDealTaxAmt, rs.getBigDecimal(COL_INV_TOT_DEAL_TAX_AMT));
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invTotDealDiscAmt, rs.getBigDecimal(COL_INV_TOT_DEAL_DISC_AMT));
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.invTotDealFrtAmt, rs.getBigDecimal(COL_INV_TOT_DEAL_FRT_AMT));
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.hdrBrCd, rs.getString(COL_COA_BR_CD));
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.tocNm, rs.getString(COL_SLS_REP_TOC_NM));
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.tocCd, rs.getString(COL_SLS_REP_TOC_CD));
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.adminPsnFirstNm, rs.getString(COL_PSN_FIRST_NM));
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.adminPsnLastNm, rs.getString(COL_PSN_LAST_NM));
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.adminPsnCd, rs.getString(COL_SLS_ADMIN_PSN_CD));
//        ZYPEZDItemValueSetter.setValue(cfsPoDtlTMsg.arTrxBalPk, rs.getBigDecimal(COL_AR_TRX_BAL_PK));
//
//        EZDTBLAccessor.insert(cfsPoDtlTMsg);
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsPoDtlTMsg.getReturnCode())) {
//            // Therefore 2016/07/26 Mod start
////            String[] paramArray = new String[] {"CFS_LEASE_PKG_PO_DTL", String.format("CFS_LEASE_PKG_PO_HDR_PK = %s, CPO_ORD_NUM = '%s'", cfsLeasePkgPoHdrPk, cpoOrdNum)};
//            String[] paramArray = new String[] {"CFS_LEASE_PKG_PO_DTL", String.format("CFS_LEASE_PKG_PO_HDR_PK = %s, CPO_ORD_NUM = '%s'", cfsLeasePkgPoHdrPk, rs.getString(COL_CPO_ORD_NUM))};
//            // Therefore 2016/07/26 Mod end
//            String errMsgText = S21MessageFunc.clspGetMessage(NWCM0109E, paramArray);
//            writeLogLn(errMsgText);
//            return false;
//        }
//
//        // Update Header
//        String crRebillRsnCatgCd = rs.getString(COL_CR_REBIL_RSN_CATG_CD);
//
//        if (crRebillRsnCatgCd != null) {
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.crRebilHldFlg, ZYPConstant.FLG_ON_Y);
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsManHldActTpCd, CFS_MAN_HLD_ACT_TP.HOLD);
//        }
//
//        // Calculate Total Amount
//        BigDecimal invCpltTotDealNetAmt = cfsPoHdrTMsg.invCpltTotDealNetAmt.getValue();
//
//        // Therefore 2016/07/26 Mod start
////        BigDecimal slsAmt = cfsPoDtlTMsg.invTotDealNetAmt.getValue()
////                                            .subtract(cfsPoDtlTMsg.invTotDealTaxAmt.getValue())
////                                            .subtract(cfsPoDtlTMsg.invTotDealFrtAmt.getValue());
//        BigDecimal slsAmt = null;
//        if (MODE_CD_01.equals(this.paramModeCd)) {
//            slsAmt = cfsPoDtlTMsg.invTotDealNetAmt.getValue()
//                                                .subtract(cfsPoDtlTMsg.invTotDealTaxAmt.getValue())
//                                                .subtract(cfsPoDtlTMsg.invTotDealFrtAmt.getValue());
//        } else {
//            slsAmt = cfsPoDtlTMsg.invTotDealSlsAmt.getValue()
//                                                  .add(cfsPoDtlTMsg.invTotDealDiscAmt.getValue());
//        }
//        // Therefore 2016/07/26 Mod start
//
//        // QC#24315 2018/02/22 Del start
////        String invTpCd = rs.getString(COL_INV_TP_CD);
//        // QC#24315 2018/02/22 Del end
//
//        if (INV_TP.CREDIT_MEMO.equals(invTpCd)) {
//            invCpltTotDealNetAmt = invCpltTotDealNetAmt.subtract(slsAmt);
//        } else {
//            invCpltTotDealNetAmt = invCpltTotDealNetAmt.add(slsAmt);
//        }
//
//        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.invCpltTotDealNetAmt, invCpltTotDealNetAmt);
//
//        // Calculate Amount Rate
//        BigDecimal rate = BigDecimal.ZERO;
//
//        if (cfsPoHdrTMsg.cfsPoAmt.getValue().compareTo(BigDecimal.ZERO) == 0) {
//            rate = ONE_HUNDRED;
//        } else {
//            rate = cfsPoHdrTMsg.invCpltTotDealNetAmt.getValue().divide(cfsPoHdrTMsg.cfsPoAmt.getValue(), RATE_FRAC_DIGIT, BigDecimal.ROUND_FLOOR).multiply(ONE_HUNDRED);
//        }
//
//        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.invCpltAmtRate, rate);
//
//        if (cfsPoHdrTMsg.invCpltAmtRate.getValue().compareTo(this.invThrhdRate) >= 0) {
//            ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.thrhdHldFlg, ZYPConstant.FLG_OFF_N);
//
//            // QC#21706-2 2018/01/10 Mod start
//            if (CFS_MAN_HLD_ACT_TP.RELEASE_OVER_THRESHOLD.equals(cfsPoHdrTMsg.cfsManHldActTpCd.getValue())) {
//                ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsLeasePkgHldFlg, ZYPConstant.FLG_OFF_N);
//
//                if (MODE_CD_02.equals(this.paramModeCd)) {
//                    CFS_LEASE_PO_INFOTMsg cfsLeasePoInfoTMsg = new CFS_LEASE_PO_INFOTMsg();
//                    ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.glblCmpyCd, this.glblCmpyCd);
//                    ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.cfsLeasePoInfoPk, cfsPoHdrTMsg.cfsLeasePoInfoPk.getValue());
//                    cfsLeasePoInfoTMsg = (CFS_LEASE_PO_INFOTMsg) EZDTBLAccessor.findByKey(cfsLeasePoInfoTMsg);
//                    if (cfsLeasePoInfoTMsg == null) {
//                        String[] paramArray = new String[] {"CFS_LEASE_PO_INFO", String.format("CFS_LEASE_PO_INFO_PK = %s", cfsPoHdrTMsg.cfsLeasePoInfoPk.getValue())};
//                        String errMsgText = S21MessageFunc.clspGetMessage(NWCM0112E, paramArray);
//                        writeLogLn(errMsgText);
//                        return false;
//                    }
//
//                    ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.poInfoProcFlg, ZYPConstant.FLG_ON_Y);
//                    EZDTBLAccessor.update(cfsLeasePoInfoTMsg);
//                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsLeasePoInfoTMsg.getReturnCode())) {
//                        String[] paramArray = new String[] {"CFS_LEASE_PO_INFO", String.format("CFS_LEASE_PO_INFO_PK = %s", cfsPoHdrTMsg.cfsLeasePoInfoPk.getValue())};
//                        String errMsgText = S21MessageFunc.clspGetMessage(NWCM0110E, paramArray);
//                        writeLogLn(errMsgText);
//                        return false;
//                    }
//                }
//            }
//
////            // Therefore 2016/07/26 Mod start
////            if (MODE_CD_02.equals(this.paramModeCd)) {
////                CFS_LEASE_PO_INFOTMsg cfsLeasePoInfoTMsg = new CFS_LEASE_PO_INFOTMsg();
////                ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.glblCmpyCd, this.glblCmpyCd);
////                ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.cfsLeasePoInfoPk, cfsPoHdrTMsg.cfsLeasePoInfoPk.getValue());
////                cfsLeasePoInfoTMsg = (CFS_LEASE_PO_INFOTMsg) EZDTBLAccessor.findByKey(cfsLeasePoInfoTMsg);
////                if (cfsLeasePoInfoTMsg == null) {
////                    String[] paramArray = new String[] {"CFS_LEASE_PO_INFO", String.format("CFS_LEASE_PO_INFO_PK = %s", cfsPoHdrTMsg.cfsLeasePoInfoPk.getValue())};
////                    String errMsgText = S21MessageFunc.clspGetMessage(NWCM0112E, paramArray);
////                    writeLogLn(errMsgText);
////                    return false;
////                }
////
////                ZYPEZDItemValueSetter.setValue(cfsLeasePoInfoTMsg.poInfoProcFlg, ZYPConstant.FLG_ON_Y);
////                EZDTBLAccessor.update(cfsLeasePoInfoTMsg);
////                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsLeasePoInfoTMsg.getReturnCode())) {
////                    String[] paramArray = new String[] {"CFS_LEASE_PO_INFO", String.format("CFS_LEASE_PO_INFO_PK = %s", cfsPoHdrTMsg.cfsLeasePoInfoPk.getValue())};
////                    String errMsgText = S21MessageFunc.clspGetMessage(NWCM0110E, paramArray);
////                    writeLogLn(errMsgText);
////                    return false;
////                }
////            }
////            // Therefore 2016/07/26 Mod end
//            // QC#21706-2 2018/01/10 Mod end
//        }
//
//        EZDTBLAccessor.update(cfsPoHdrTMsg);
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsPoHdrTMsg.getReturnCode())) {
//            String[] paramArray = new String[] {"CFS_LEASE_PKG_PO_HDR", String.format("CFS_LEASE_PKG_PO_HDR_PK = %s", cfsLeasePkgPoHdrPk)};
//            String errMsgText = S21MessageFunc.clspGetMessage(NWCM0110E, paramArray);
//            writeLogLn(errMsgText);
//            return false;
//        }
//
//        return true;
//    }
    // QC#24431 2018/05/24 Del End

    // ****************************************************************
    // S21SsmBatchClient DB Access Method
    // ****************************************************************
    private BigDecimal getAttrbValNum() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(DB_PARAM_ATTRB_KEY_NM, INV_THRHD);

        return (BigDecimal) ssmBatchClient.queryObject("getAttrbValNum", ssmParam);
    }

//    private BigDecimal getCfsLeasePkgPoHdr(String cpoOrdNum, String appNum) {
//
//            Map<String, Object> ssmParam = new HashMap<String, Object>();
//            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
//            // Therefore 2016/07/26 Add start
////            if (MODE_CD_01.equals(this.paramModeCd)) {
////                ssmParam.put(DB_PARAM_CPO_ORD_NUM, cpoOrdNum);
////            } else {
//                ssmParam.put(DB_PARAM_CFS_PO_NUM, cpoOrdNum);
//                ssmParam.put(DB_PARAM_CFS_APP_NUM, appNum);
////            }
//            // Therefore 2016/07/26 Add end
//
//            return (BigDecimal) ssmBatchClient.queryObject("getCfsLeasePkgPoHdr", ssmParam);
//    }

    // QC#27439 2018/08/20 Add End
    private CFS_LEASE_PKG_PO_HDRTMsg getCfsLeasePkgPoHdrTMsg(BigDecimal cfsLeasePkgPoHdrPk) {
        CFS_LEASE_PKG_PO_HDRTMsg cfsPoHdrTMsg = new CFS_LEASE_PKG_PO_HDRTMsg();

        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cfsPoHdrTMsg.cfsLeasePkgPoHdrPk, cfsLeasePkgPoHdrPk);
        cfsPoHdrTMsg = (CFS_LEASE_PKG_PO_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(cfsPoHdrTMsg);

        return cfsPoHdrTMsg;
    }
    // QC#27439 2018/08/20 Add End

    private static void writeLogLn(String format, Object... param) {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[%s]", BIZ_APP_ID));

        if (param.length > 0) {
            sb.append(String.format(format, param));
        } else {
            sb.append(format);
        }

        S21InfoLogOutput.println(sb.toString());
    }
}
