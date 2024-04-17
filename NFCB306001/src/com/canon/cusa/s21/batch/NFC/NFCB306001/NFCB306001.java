/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB306001;

import static com.canon.cusa.s21.batch.NFC.NFCB306001.constant.NFCB306001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDBItem;
import parts.common.EZDMsg;
import parts.common.EZDPMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.db.AR_ACCT_DTTMsg;
import business.db.AR_ADJTMsg;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_CASH_APPTMsg;
import business.db.AR_RCPTTMsg;
import business.db.AR_RCPT_DTLTMsg;
import business.db.AR_RF_RQSTTMsg;
import business.db.AR_RF_TRXTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.db.GLBL_CMPYTMsg;
import business.parts.NFZC301001PMsg;
import business.parts.NFZC310001PMsg;
import business.parts.NFZC202001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC202001.NFZC202001;
import com.canon.cusa.s21.api.NFC.NFZC301001.NFZC301001;
import com.canon.cusa.s21.api.NFZ.NFZC310001.NFZC310001;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFCNumbering;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFXC3060Bean;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_APPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CRAT_METH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 * <pre>
 * Update Refund
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/04   Hitachi         J.Kim           Create          N/A
 * 2016/04/22   Hitachi         T.Tsuchida      Update          QC#7464
 * 2016/04/22   Hitachi         T.Tsuchida      Update          QC#7716
 * 2016/05/10   Hitachi         T.Tsuchida      Update          QC#8024
 * 2016/05/10   Hitachi         T.Tsuchida      Update          QC#8025
 * 2016/05/11   Hitachi         T.Tsuchida      Update          QC#7796
 * 2016/05/16   Hitachi         T.Tsuchida      Update          QC#7881
 * 2016/05/18   Hitachi         T.Tsuchida      Update          QC#8023
 * 2016/07/05   Hitachi         T.Tomita        Update          QC#10692
 * 2016/07/29   Hitachi         K.Kojima        Update          QC#12657
 * 2016/07/29   Hitachi         K.Kojima        Update          QC#12649
 * 2016/08/05   Hitachi         K.Kojima        Update          QC#12657
 * 2016/12/08   Hitachi         E.Kameishi      Update          QC#16286
 * 2017/10/12   CITS            T.Kikuhara      Update          QC#21689
 * 2017/12/15   Hitachi         J.Kim           Update          QC#23041
 * 2017/12/19   Hitachi         J.Kim           Update          QC#22199
 * 2018/04/03   Hitachi         E.Kameishi      Update          QC#23164
 * 2018/06/06   Hitachi         E.Kameishi      Update          QC#23164
 * 2018/07/13   Fujitsu         Y.Matsui        Update          QC#26993
 * 2018/08/23   Hitachi         E.Kameishi      Update          QC#26008
 * 2018/09/20   Fujitsu         T.Ogura         Update          QC#28097
 * 2022/03/04   CITS            D.Mamaril       Update          QC#59333
 * 2022/07/25   Hitachi         A.Kohinata      Update          QC#57417
 * 2022/08/08   Hitachi         M.Kikushima     Update          QC#57083
 * </pre>
 */
public class NFCB306001 extends S21BatchMain {

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** S21UserProfileService */
    private S21UserProfileService profileService;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** AR Batch Date */
    private String aRBatchDt = null;

    /** API Error message list */
    private List<String> errrMsgList = new ArrayList<String>();

    /** Error Count */
    private int errorCount = 0;

    /** Normal Count */
    private int normalCount = 0;

    /** Commit Count:AR_RF_TRX */
    private int commitCount = 0;

    /** Commit Count:AR_RF_TRX */
    private int commitCountCrm = 0;

    /** Commit Count:AR_RF_TRX */
    private int commitCountRcp = 0;

    /** Commit Max Number */
    private int commitNumber = 0;

    @Override
    protected void initRoutine() {

        // "Global Company Code" Mandatory
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NFBM0199E);
        }

        // "AR Batch Date"
        this.aRBatchDt = findArAcctDt(this.glblCmpyCd, NFCConst.CST_ONL_BAT_TP_CD_BAT);
        if (!ZYPCommonFunc.hasValue(this.aRBatchDt)) {
            throw new S21AbendException(NFCM0502E, new String[] {"AR Batch Date" });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.profileService = getUserProfileService();
    }

    @Override
    protected void mainRoutine() {
        doProcess();
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount, this.normalCount + this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFCB306001().executeBatch(NFCB306001.class.getSimpleName());
    }

    private void doProcess() {

        ResultSet rs = null;
        ResultSet rsDtl = null;
        PreparedStatement ps = null;
        PreparedStatement psDtl = null;
        List<Map<String, Object>> arRfRqstDtlMapList = null;
        String ccyCd = null;
        boolean errFlg = false;

        Map<String, Object> arRfRqstParam = new HashMap<String, Object>();
        arRfRqstParam.put("glblCmpyCd", this.glblCmpyCd);
        arRfRqstParam.put("arDsWfStsCd", AR_DS_WF_STS.APPROVED);

        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {

            GLBL_CMPYTMsg outBlblCmpyTMsg = findGlblCmpy();
            if (outBlblCmpyTMsg != null) {
                ccyCd = outBlblCmpyTMsg.stdCcyCd.getValue();
            }
            BigDecimal actlExchRate = this.findAcctDlyActlExchRatest(ccyCd, this.aRBatchDt);
            String userId = profileService.getContextUserInfo().getUserId();

            ps = this.ssmLLClient.createPreparedStatement("getArRfRqstInfo", arRfRqstParam, execPrm);
            rs = ps.executeQuery();

            while (rs.next()) {

                BigDecimal arRfRqstPk = rs.getBigDecimal("AR_RF_RQST_PK");
                // del start 2022/07/25 QC#57417
                //String arRcptRfRsnCd = rs.getString("AR_RCPT_RF_RSN_CD");
                // del end 2022/07/25 QC#57417
                // START 2016/07/29 K.Kojima [QC#12657,ADD]
                boolean creditCardRefund = isCreditCardRefund(rs.getString("AR_RF_TP_CD"));
                // END 2016/07/29 K.Kojima [QC#12657,ADD]
                Map<String, Object> arRfRqstDtlParam = new HashMap<String, Object>();
                arRfRqstDtlParam.put("glblCmpyCd", this.glblCmpyCd);
                arRfRqstDtlParam.put("arRfRqstPk", arRfRqstPk);

                psDtl = this.ssmLLClient.createPreparedStatement("getArRfRqstDtlInfo", arRfRqstDtlParam, execPrm);
                rsDtl = psDtl.executeQuery();
                errFlg = false;
                arRfRqstDtlMapList = new ArrayList<Map<String, Object>>();

                while (rsDtl.next()) {
                    if (!isSameTimeStamp(rs, rsDtl)) {
                        errFlg = true;
                        break;
                    }
                    arRfRqstDtlMapList.add(setArRfRqstDtlMapList(rsDtl));
                }
                S21SsmLowLevelCodingClient.closeResource(psDtl, rsDtl);

                if (errFlg) {
                    continue;
                }

                // Update AR Refund Request
                getArfRqstInfo(this.glblCmpyCd, arRfRqstPk, AR_RF_STS.CREATED);

                // START 2017/12/15 J.Kim [QC#23041,DEL]
                // String billToCustBf = "";
                // END 2017/12/15 J.Kim [QC#23041,DEL]
                String aGrKey = "";
                for (int index = 0; index < arRfRqstDtlMapList.size(); index++) {

                    Map<String, Object> arRfRqstDtlMap = arRfRqstDtlMapList.get(index);
                    String billToCust = (String) arRfRqstDtlMap.get("BILL_TO_CUST_CD");
                    // START 2017/12/15 J.Kim [QC#23041,DEL]
                    // String arTrxTpCd = (String) arRfRqstDtlMap.get("AR_TRX_TP_CD");
                    // END 2017/12/15 J.Kim [QC#23041,DEL]
                    BigDecimal dealRfAmt = (BigDecimal) arRfRqstDtlMap.get("RF_AMT");
                    BigDecimal orgArTrxBalPk = (BigDecimal) arRfRqstDtlMap.get("AR_TRX_BAL_PK");

                    // START 2017/12/15 J.Kim [QC#23041,DEL]
                    // if (billToCustBf.equals(billToCust)
                    //         && AR_TRX_TP.CREDIT_MEMO.equals(arTrxTpCd)) {
                    //    continue;
                    // }
                    // END 2017/12/15 J.Kim [QC#23041,DEL]

                    // Create AR Refund Transaction
                    // START 2016/07/29 K.Kojima [QC#12657,MOD]
                    // creationArRefundTransaction(rs,
                    // arRfRqstDtlMap);
                    // START 2022/08/08 M.Kikushima [QC#57083,MOD]
                    // creationArRefundTransaction(rs, arRfRqstDtlMap, creditCardRefund);
                    AR_RF_TRXTMsg outArRfTrxtMsg = creationArRefundTransaction(rs, arRfRqstDtlMap, creditCardRefund);
                    // END 2022/08/08 M.Kikushima [QC#57083,MOD]
                    // END 2016/07/29 K.Kojima [QC#12657,MOD]

                    String rcptNum = ZYPNumbering.getUniqueID(BIZAPL_RCPTNUMKEY);
                    if (rcptNum == null) {
                        this.setErrorInfo(NFCM0002E, new String[] {"Numbering" });
                        break;
                    }

                    BigDecimal arTrxBalPk = null;
                    NFCNumbering afcNumbering = new NFCNumbering();
                    NFXC3060Bean outGetNumber = afcNumbering.getNumber(ZYPOracleSeqAccessor.AR_TRX_BAL_SQ, "", AR_TRX_BAL_SQ_START_NUM);
                    // START 2018/04/03 E.Kameishi [QC#23164,ADD]
                    String arTrxTpCd =  (String) arRfRqstDtlMap.get("AR_TRX_TP_CD");
                    // END 2018/04/03 E.Kameishi [QC#23164,ADD]
                    if (AR_TRX_BAL_SQ_RTNCD_NORMAL.equals(outGetNumber.getRtrnNo())) {
                        arTrxBalPk = outGetNumber.getOraSqNo();
                    }

                    // Credit Memo - Insert
                    if (AR_TRX_TP.CREDIT_MEMO.equals((String) arRfRqstDtlMap.get("AR_TRX_TP_CD"))) {
                        this.commitCountCrm++;
                        // AR Receipt - Insert
                        // START 2016/07/29 K.Kojima [QC#12649,MOD]
                        // creationArReceipt(rcptNum, actlExchRate,
                        // ccyCd, billToCust);
                        creationArReceipt(rcptNum, actlExchRate, ccyCd, billToCust, creditCardRefund, rs.getString("BILL_TO_CUST_ACCT_CD"));
                        // END 2016/07/29 K.Kojima [QC#12649,MOD]
                        // AR Receipt Detail - Insert
                        creationArReceiptDetail(rcptNum);
                        // AR Transaction Balance - Insert
                        creationArTransactionBalance(rs, rcptNum, actlExchRate, ccyCd, arTrxBalPk, billToCust);
                        // AR Apply Interface Work - Insert
                        aGrKey = userId.concat(ZYPDateUtil.getCurrentSystemTime(YYYYMMDDHHMMSSMMM));
                        // START 2018/04/03 E.Kameishi [QC#23164,ADD]
                        AR_APPLY_INTFC_WRKTMsg outTMsg = creationArApplyInterfaceWork(rcptNum, ccyCd, arTrxBalPk, null, userId, billToCust, dealRfAmt, aGrKey, NFCConst.CST_XX_PROC_CASE_TP_CD_NEW_RCPT, arTrxTpCd, rs.getString("BILL_TO_CUST_ACCT_CD"));
                        // END 2018/04/03 E.Kameishi [QC#23164,ADD]
                        if (outTMsg == null) {
                            setErrorInfo(NFCM0025E, null);
                            return;
                        }
                        // Receipt Data Creation Common (NFZC301001)
                        callRecieptDataCreationAPI(aGrKey);
                        // AR Apply Interface Work - Insert
                        aGrKey = userId.concat(ZYPDateUtil.getCurrentSystemTime(YYYYMMDDHHMMSSMMM));
                        // START 2018/04/03 E.Kameishi [QC#23164,ADD]
                        outTMsg = creationArApplyInterfaceWork(rcptNum, ccyCd, arTrxBalPk, orgArTrxBalPk, userId, billToCust, dealRfAmt, aGrKey, NFCConst.CST_XX_PROC_CASE_TP_CD_PMT, arTrxTpCd, rs.getString("BILL_TO_CUST_ACCT_CD"));
                        // END 2018/04/03 E.Kameishi [QC#23164,ADD]
                        if (outTMsg == null) {
                            setErrorInfo(NFCM0025E, null);
                            return;
                        }
                        // Receipt Data Creation Common (NFZC301001)
                        callRecieptDataCreationAPI(aGrKey);

                      // START 2022/03/04 D.Mamaril [QC#59333,ADD]

                        // START 2022/08/08 M.Kikushima [QC#57083,ADD]
                        // Find AR Apply Interface Work Primary Key
                        AR_APPLY_INTFC_WRKTMsg outApplyIntfcWrkMsg = findApplyIntfcWrk(aGrKey);
                        // Update AR_RF_TRX
                        updateArRfTrx(outArRfTrxtMsg, outApplyIntfcWrkMsg.arAdjNum.getValue());
                        // END 2022/08/08 M.Kikushima [QC#57083,ADD]

                      // On Account Refund
                    } else if (AR_TRX_TP.ON_ACCOUNT.equals((String) arRfRqstDtlMap.get("AR_TRX_TP_CD"))) {
                        this.commitCountRcp++;
                        String arAdjNum = (String) arRfRqstDtlMap.get("ATT_ADJ_NUM");
                        String arTrxNum = (String) arRfRqstDtlMap.get("ORIG_AR_TRX_NUM");

                        // Find Receipt Transaction Primary Key
                        AR_TRX_BALTMsg outArTrxBalMsg = findRcptArTrxBalInfo(arTrxNum);
                        if (outArTrxBalMsg == null) {
                            setErrorInfo(NFCM0025E, null);
                            return;
                        }
                        arTrxBalPk = outArTrxBalMsg.arTrxBalPk.getValue();

                        // Update AR_CASH_APP
                        AR_CASH_APPTMsg outArCashAppMsg = updateArCashApp(orgArTrxBalPk, arAdjNum);
                        if (outArCashAppMsg == null) {
                            setErrorInfo(NFCM0025E, null);
                            return;
                        }

                        AR_APPLY_INTFC_WRKTMsg outTMsg = null;

                        // Cancel On Account
                        aGrKey = userId.concat(ZYPDateUtil.getCurrentSystemTime(YYYYMMDDHHMMSSMMM));
                        outTMsg = creationArApplyInterfaceWork(arTrxNum, ccyCd, arTrxBalPk, orgArTrxBalPk, userId, billToCust, dealRfAmt, aGrKey, NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_ADD_DED_OR_ACC, creditCardRefund, arTrxTpCd, arAdjNum, rs.getString("BILL_TO_CUST_ACCT_CD"));
                        if (outTMsg == null) {
                            setErrorInfo(NFCM0025E, null);
                            return;
                        }
                        callRecieptDataCreationAPI(aGrKey);

                        // Create Refund
                        aGrKey = userId.concat(ZYPDateUtil.getCurrentSystemTime(YYYYMMDDHHMMSSMMM));
                        outTMsg = creationArApplyInterfaceWork(arTrxNum, ccyCd, arTrxBalPk, orgArTrxBalPk, userId, billToCust, dealRfAmt, aGrKey, NFCConst.CST_XX_PROC_CASE_TP_CD_PMT, creditCardRefund, arTrxTpCd, arAdjNum, rs.getString("BILL_TO_CUST_ACCT_CD"));
                        if (outTMsg == null) {
                            setErrorInfo(NFCM0025E, null);
                            return;
                        }
                        callRecieptDataCreationAPI(aGrKey);
                      // END 2022/03/04 D.Mamaril [QC#59333,ADD]

                        // START 2022/08/08 M.Kikushima [QC#57083,ADD]
                        // Find AR Apply Interface Work Primary Key
                        AR_APPLY_INTFC_WRKTMsg outApplyIntfcWrkMsg = findApplyIntfcWrk(aGrKey);
                        // Update AR_RF_TRX
                        updateArRfTrx(outArRfTrxtMsg, outApplyIntfcWrkMsg.arAdjNum.getValue());
                        // END 2022/08/08 M.Kikushima [QC#57083,ADD]

                      // Receipt Refund
                    } else {
                        this.commitCountRcp++;
                        String arTrxNum = (String) arRfRqstDtlMap.get("AR_TRX_NUM");
                        arTrxBalPk = (BigDecimal) arRfRqstDtlMap.get("AR_TRX_BAL_PK");
                        // START 2018/04/03 E.Kameishi [QC#23164,ADD]
                        String arAdjNum = (String) arRfRqstDtlMap.get("ATT_ADJ_NUM");
                        if (AR_TRX_TP.ON_ACCOUNT.equals(arTrxTpCd)) {
                            arTrxNum = (String) arRfRqstDtlMap.get("ORIG_AR_TRX_NUM");
                        }
                        // AR Receipt - Update
                        if (AR_TRX_TP.RECEIPT.equals(arTrxTpCd)) {
                            // START 2018/07/13 Y.Matsui [QC#26993, MOD]
                            // add start 2022/07/25 QC#57417
                            String arRcptRfRsnCd = (String) arRfRqstDtlMap.get("AR_RCPT_RF_RSN_CD");
                            // add end 2022/07/25 QC#57417
                            AR_RCPTTMsg rcptTMsg = updateArReceipt(arRcptRfRsnCd, arRfRqstDtlMap);
                            if (rcptTMsg == null) {
                                return;
                            }
                            if (!updateArBatRcptStatus(rcptTMsg.arBatRcptNm.getValue())) {
                                return;
                            }
                            // END   2018/07/13 Y.Matsui [QC#26993, MOD]
                        }
                        // END 2018/04/03 E.Kameishi [QC#23164,ADD]
                        // AR Transaction Balance - Update
                        AR_TRX_BALTMsg outArTrxBalTMsg = updateArTransactionBalance(arRfRqstDtlMap);
                        if (outArTrxBalTMsg == null) {
                            return;
                        }
                        // AR Apply Interface Work - Insert
                        aGrKey = userId.concat(ZYPDateUtil.getCurrentSystemTime(YYYYMMDDHHMMSSMMM));
                        // START 2016/07/29 K.Kojima [QC#12657,MOD]
                        //AR_APPLY_INTFC_WRKTMsg outTMsg = creationArApplyInterfaceWork(arTrxNum, ccyCd, arTrxBalPk, orgArTrxBalPk, userId, billToCust, dealRfAmt, aGrKey, NFCConst.CST_XX_PROC_CASE_TP_CD_RF);
                        // START 2018/04/03 E.Kameishi [QC#23164,ADD]
                        AR_APPLY_INTFC_WRKTMsg outTMsg = null;
                        if (AR_TRX_TP.RECEIPT.equals(arTrxTpCd)) {
                            outTMsg = creationArApplyInterfaceWork(arTrxNum, ccyCd, arTrxBalPk, orgArTrxBalPk, userId, billToCust, dealRfAmt, aGrKey, NFCConst.CST_XX_PROC_CASE_TP_CD_RF, creditCardRefund, arTrxTpCd, arAdjNum, rs.getString("BILL_TO_CUST_ACCT_CD"));
                        } else {
                            outTMsg = creationArApplyInterfaceWork(arTrxNum, ccyCd, arTrxBalPk, orgArTrxBalPk, userId, billToCust, dealRfAmt, aGrKey, NFCConst.CST_XX_PROC_CASE_TP_CD_PMT, creditCardRefund, arTrxTpCd, arAdjNum, rs.getString("BILL_TO_CUST_ACCT_CD"));
                        }
                        // END 2018/04/03 E.Kameishi [QC#23164,ADD]
                        // END 2016/07/29 K.Kojima [QC#12657,MOD]
                        if (outTMsg == null) {
                            setErrorInfo(NFCM0025E, null);
                            return;
                        }
                        // Receipt Data Creation Common (NFZC301001)
                        callRecieptDataCreationAPI(aGrKey);

                        // START 2022/08/08 M.Kikushima [QC#57083,ADD]
                        if (AR_TRX_TP.RECEIPT.equals(arTrxTpCd)) {
                            // Find AR Apply Interface Work Primary Key
                            AR_APPLY_INTFC_WRKTMsg outApplyIntfcWrkMsg = findApplyIntfcWrk(aGrKey);
                            // Update AR_RF_TRX
                            updateArRfTrx(outArRfTrxtMsg, outApplyIntfcWrkMsg.arAdjNum.getValue());
                        }
                        // END 2022/08/08 M.Kikushima [QC#57083,ADD]

                    }

                    // START 2017/12/15 J.Kim [QC#23041,DEL]
                    // if (index != (arRfRqstDtlMapList.size() - 1)) {
                    //    billToCustBf = (String) arRfRqstDtlMapList.get(index).get("BILL_TO_CUST_CD");
                    // }
                    // END 2017/12/15 J.Kim [QC#23041,DEL]

                    // START 2018/08/23 E.Kameishi [QC#26008, ADD]
                    //Update Credit Profile
                    NFZC202001 crPrflUpdApi = new NFZC202001();
                    NFZC202001PMsg nfzc2020Pmsg = new NFZC202001PMsg();
                    ZYPEZDItemValueSetter.setValue(nfzc2020Pmsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(nfzc2020Pmsg.procDt, this.aRBatchDt);
                    
                    if (ZYPCommonFunc.hasValue(billToCust)) {
                        ZYPEZDItemValueSetter.setValue(nfzc2020Pmsg.xxModeCd, NFZC202001.MODE_BILL_TO_CUST);
                        ZYPEZDItemValueSetter.setValue(nfzc2020Pmsg.billToCustCd, billToCust);
                    } else {
                        ZYPEZDItemValueSetter.setValue(nfzc2020Pmsg.xxModeCd, NFZC202001.MODE_CUST_ACCT);
                        ZYPEZDItemValueSetter.setValue(nfzc2020Pmsg.sellToCustCd, rs.getString("BILL_TO_CUST_ACCT_CD"));
                    }

                    crPrflUpdApi.execute(nfzc2020Pmsg, ONBATCH_TYPE.BATCH);
                    List<String> msgList = S21ApiUtil.getXxMsgIdList(nfzc2020Pmsg);
                    for (String msgId : msgList) {
                        if (msgId.endsWith("E")) {
                            return;
                        }
                    }
                    // END 2018/08/23 E.Kameishi [QC#26008, ADD]
                    if (this.errrMsgList.size() > 0) {
                        this.errrMsgList.clear();
                        rollback();
                        continue;
                    }
                    commitCount();
                }
            }

            if (this.commitCountCrm > 0 || commitCountRcp > 0) {
                this.normalCount += this.commitCount;
                this.commitCountCrm = 0;
                this.commitCountRcp = 0;
                this.commitCount = 0;
                commit();
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
            S21SsmLowLevelCodingClient.closeResource(psDtl, rsDtl);
        }
    }

    private void commitCount() {

        if (this.commitNumber == this.commitCountCrm) {
            this.normalCount += this.commitCountCrm;
            this.commitCountCrm = 0;
            commit();
        }
        if (this.commitNumber == this.commitCountRcp) {
            this.normalCount += this.commitCountRcp;
            this.commitCountRcp = 0;
            commit();
        }
    }

    private Map<String, Object> setArRfRqstDtlMapList(ResultSet rs) throws SQLException {

        Map<String, Object> arRfRqstDtl = new HashMap<String, Object>();
        arRfRqstDtl.put("GLBL_CMPY_CD", rs.getString("GLBL_CMPY_CD"));
        arRfRqstDtl.put("AR_RF_RQST_DTL_PK", (BigDecimal) rs.getBigDecimal("AR_RF_RQST_DTL_PK"));
        arRfRqstDtl.put("AR_TRX_BAL_PK", (BigDecimal) rs.getBigDecimal("AR_TRX_BAL_PK"));
        arRfRqstDtl.put("AR_RF_RQST_PK", (String) rs.getString("AR_RF_RQST_PK"));
        arRfRqstDtl.put("BILL_TO_CUST_CD", (String) rs.getString("BILL_TO_CUST_CD"));
        arRfRqstDtl.put("AR_TRX_TP_CD", (String) rs.getString("AR_TRX_TP_CD"));
        arRfRqstDtl.put("DEAL_ORIG_GRS_AMT", (BigDecimal) rs.getBigDecimal("DEAL_ORIG_GRS_AMT"));
        arRfRqstDtl.put("DEAL_RMNG_BAL_GRS_AMT", (BigDecimal) rs.getBigDecimal("DEAL_RMNG_BAL_GRS_AMT"));
        arRfRqstDtl.put("DEAL_RF_AMT", (BigDecimal) rs.getBigDecimal("DEAL_RF_AMT"));
        arRfRqstDtl.put("DEAL_CCY_CD", (String) rs.getString("DEAL_CCY_CD"));
        arRfRqstDtl.put("RF_EXCH_RATE", (BigDecimal) rs.getBigDecimal("RF_EXCH_RATE"));
        arRfRqstDtl.put("FUNC_ORIG_GRS_AMT", (BigDecimal) rs.getBigDecimal("FUNC_ORIG_GRS_AMT"));
        arRfRqstDtl.put("FUNC_RMNG_BAL_GRS_AMT", (BigDecimal) rs.getBigDecimal("FUNC_RMNG_BAL_GRS_AMT"));
        arRfRqstDtl.put("FUNC_RF_AMT", (BigDecimal) rs.getBigDecimal("FUNC_RF_AMT"));
        arRfRqstDtl.put("EZUPTIME_ARRD", (String) rs.getString("EZUPTIME_ARRD"));
        arRfRqstDtl.put("AR_TRX_NUM", (String) rs.getString("AR_TRX_NUM"));
        arRfRqstDtl.put("ATB_EZUPTIME", (String) rs.getString("ATB_EZUPTIME"));
        arRfRqstDtl.put("ATB_EZUPTIMEZONE", (String) rs.getString("ATB_EZUPTIMEZONE"));
        arRfRqstDtl.put("AR_EZUPTIME", (String) rs.getString("AR_EZUPTIME"));
        arRfRqstDtl.put("AR_EZUPTIMEZONE", (String) rs.getString("AR_EZUPTIMEZONE"));
        arRfRqstDtl.put("RF_AMT", (BigDecimal) rs.getBigDecimal("RF_AMT"));
        // START 2018/04/03 E.Kameishi [QC#23164,ADD]
        arRfRqstDtl.put("ATT_ADJ_NUM", (String) rs.getString("ATT_ADJ_NUM"));
        arRfRqstDtl.put("ORIG_AR_TRX_NUM", (String) rs.getString("ORIG_AR_TRX_NUM"));
        // END 2018/04/03 E.Kameishi [QC#23164,ADD]
        // add start 2022/07/25 QC#57417
        arRfRqstDtl.put("AR_RCPT_RF_RSN_CD", (String) rs.getString("AR_RCPT_RF_RSN_CD"));
        // add end 2022/07/25 QC#57417
        return arRfRqstDtl;
    }

    private boolean setApiErrMsgList(EZDPMsg apiPMsg) {

        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
            for (String xxMsgId : xxMsgIdList) {
                S21InfoLogOutput.println(xxMsgId);
                this.errrMsgList.add(xxMsgId);
            }
            return false;
        }
        return true;
    }

    private void setErrorInfo(String msgId, String[] params) {
        S21InfoLogOutput.println(msgId, params);
        this.errrMsgList.add(S21MessageFunc.clspGetMessage(msgId, params));
        this.errorCount++;
    }

    // START 2016/07/29 K.Kojima [QC#12649,MOD]
    // private AR_RCPTTMsg creationArReceipt(String rcptNum,
    // BigDecimal actlExchRate, String ccyCd, String billToCust) {
    private AR_RCPTTMsg creationArReceipt(String rcptNum, BigDecimal actlExchRate, String ccyCd, String billToCust, boolean creditCardRefund, String billToCustAcct) {
        // END 2016/07/29 K.Kojima [QC#12649,MOD]

        AR_RCPTTMsg inArRcptMsg = new AR_RCPTTMsg();
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.rcptNum, rcptNum);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.rcptBatNum, ZYPCodeDataUtil.getVarCharConstValue("AR_PUR_RCPT_BAT_NUM", this.glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.rcptBatSqNum, ZYPCodeDataUtil.getVarCharConstValue("AR_PUR_RCPT_BAT_SQ_NUM", this.glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.arRcptTrxTpCd, ZYPCodeDataUtil.getVarCharConstValue("AR_PUR_RCPT_TRX_TP_CD", this.glblCmpyCd));
        // START 2016/07/29 K.Kojima [QC#12649,MOD]
        // ZYPEZDItemValueSetter.setValue(inArRcptMsg.arRcptTpCd,
        // ZYPCodeDataUtil.getVarCharConstValue("AR_PUR_RCPT_TP_CD",
        // this.glblCmpyCd));
        if (creditCardRefund) {
            ZYPEZDItemValueSetter.setValue(inArRcptMsg.arRcptTpCd, AR_RCPT_TP.CREDIT_CARD);
        } else {
            ZYPEZDItemValueSetter.setValue(inArRcptMsg.arRcptTpCd, ZYPCodeDataUtil.getVarCharConstValue("AR_PUR_RCPT_TP_CD", this.glblCmpyCd));
        }
        // END 2016/07/29 K.Kojima [QC#12649,MOD]
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.dealCcyCd, ccyCd);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.dealRcptAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.dealApplyAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.dealApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.dealRfAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.dealVoidAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.dealRcptRmngBalAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.exchRate, actlExchRate);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.funcCcyCd, ccyCd);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.funcRcptAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.funcApplyAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.funcApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.funcRfAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.funcVoidAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.funcRvalVarAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.funcRcptRmngBalAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.rcptDt, this.aRBatchDt);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.glDt, this.aRBatchDt);
        // START 2018/06/08 E.Kameishi [QC#23164,MOD]
        //ZYPEZDItemValueSetter.setValue(inArRcptMsg.payerCustCd, billToCust);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.payerCustCd, billToCustAcct);
        // END 2018/06/08 E.Kameishi [QC#23164,MOD]
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.rcptChkNum, ZYPCommonFunc.leftPad(PAD_STR_0, MAX_LENGTH_10, PAD_STR_0));
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.arBankAcctCd, ZYPCodeDataUtil.getVarCharConstValue("AR_PUR_BANK_ACCT_CD", this.glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.tocCd, ZYPCodeDataUtil.getVarCharConstValue("AR_RCPT_TOC_CD", this.glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.coaProdCd, ZYPCodeDataUtil.getVarCharConstValue("AR_RCPT_PROD_CD", this.glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.arCashApplyStsCd, AR_CASH_APPLY_STS.APPLIED);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.voidFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.exptFirstBankChrgCcyCd, inArRcptMsg.dealCcyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.dealFirstExptChrgAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.funcFirstExptChrgAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.exptScdBankChrgCcyCd, inArRcptMsg.dealCcyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.dealScdExptChrgAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.funcScdExptChrgAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.dealNetRcptAmt, inArRcptMsg.dealRcptAmt.getValue());
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.funcNetRcptAmt, inArRcptMsg.funcRcptAmt.getValue());
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.fgnExchLossGainAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.cratMethTpCd, AR_CRAT_METH_TP.MANUAL_ENTRY);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.exptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.ajeCratCpltFlg, ZYPConstant.FLG_OFF_N);
        // START 2016/08/05 K.Kojima [QC#12657,ADD]
        // START 2018/09/20 T.Ogura [QC#28097,MOD]
//        ZYPEZDItemValueSetter.setValue(inArRcptMsg.arRcptStsCd, AR_RCPT_STS.NEW);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.arRcptStsCd, AR_RCPT_STS.UNAPPLIED);
        // END   2018/09/20 T.Ogura [QC#28097,MOD]
        // END 2016/08/05 K.Kojima [QC#12657,ADD]
        // START 2018/09/20 T.Ogura [QC#28097,ADD]
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.arRcptSrcCd, AR_RCPT_SRC.SYSTEM_CREATED);
        // END   2018/09/20 T.Ogura [QC#28097,ADD]
        EZDTBLAccessor.create(inArRcptMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inArRcptMsg.getReturnCode())) {
            throw new S21AbendException(NFCM0032E);
        }
        return inArRcptMsg;
    }

    private AR_RCPT_DTLTMsg creationArReceiptDetail(String rcptNum) {
        AR_RCPT_DTLTMsg inArRcptDtlMsg = new AR_RCPT_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inArRcptDtlMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inArRcptDtlMsg.rcptNum, rcptNum);
        ZYPEZDItemValueSetter.setValue(inArRcptDtlMsg.rcptDtlNum, ZYPCommonFunc.leftPad(STR_1, MAX_LENGTH_4, PAD_STR_0));
        ZYPEZDItemValueSetter.setValue(inArRcptDtlMsg.arCustRefNum, ZYPCommonFunc.leftPad(PAD_STR_0, MAX_LENGTH_10, PAD_STR_0));
        ZYPEZDItemValueSetter.setValue(inArRcptDtlMsg.arCustRefTpCd, AR_TRX_TP.INVOICE);
        ZYPEZDItemValueSetter.setValue(inArRcptDtlMsg.dealRcptDtlAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArRcptDtlMsg.funcRcptDtlAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArRcptDtlMsg.autoCratFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inArRcptDtlMsg.grpInvFlg, ZYPConstant.FLG_OFF_N);
        EZDTBLAccessor.create(inArRcptDtlMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inArRcptDtlMsg.getReturnCode())) {
            throw new S21AbendException(NFCM0032E);
        }
        return inArRcptDtlMsg;
    }

    private AR_TRX_BALTMsg creationArTransactionBalance(ResultSet rs, String rcptNum, BigDecimal actlExchRate, String ccyCd, BigDecimal arTrxBalPk, String billToCust) throws SQLException {

        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.arTrxBalPk, arTrxBalPk);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.arTrxNum, rcptNum);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.arTrxTpCd, AR_TRX_TP.RECEIPT);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.dealCcyCd, ccyCd);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.dealOrigGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.dealApplyGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.dealApplyCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.dealApplyCrAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.dealApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.dealRmngBalGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.dealNetSlsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.dealFrtAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.dealTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.dealInvDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.dealPrmoDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.dealRcptVoidAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.exchRate, actlExchRate);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.funcCcyCd, ccyCd);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.funcOrigGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.funcApplyGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.funcApplyCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.funcApplyCrAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.funcApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.funcApplyAdjRsvdAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.funcRvalVarAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.funcRmngBalGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.funcNetSlsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.funcFrtAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.funcTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.funcInvDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.funcPrmoDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.funcRcptVoidAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.cashDiscPct, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.arCashApplyStsCd, AR_CASH_APPLY_STS.APPLIED);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.arTrxDt, this.aRBatchDt);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.glDt, this.aRBatchDt);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.billToCustAcctCd, rs.getString("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.billToCustCd, billToCust);
        // START 2018/06/08 E.Kameishi [QC#23164,MOD]
        //ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.sellToCustCd, billToCust);
        //ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.payerCustCd, billToCust);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.sellToCustCd, rs.getString("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.payerCustCd, rs.getString("BILL_TO_CUST_ACCT_CD"));
        // END 2018/06/08 E.Kameishi [QC#23164,MOD]
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.tocCd, ZYPCodeDataUtil.getVarCharConstValue("AR_RCPT_TOC_CD", this.glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.coaProdCd, ZYPCodeDataUtil.getVarCharConstValue("AR_RCPT_PROD_CD", this.glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.arCustRefNum, ZYPCommonFunc.leftPad(PAD_STR_0, MAX_LENGTH_10, PAD_STR_0));
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.upperCustIssPoNum, ZYPCommonFunc.leftPad(PAD_STR_0, MAX_LENGTH_10, PAD_STR_0));
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.exptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.arAutoPurgeOfsFlg, ZYPConstant.FLG_OFF_N);
        EZDTBLAccessor.create(inArTrxBalMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inArTrxBalMsg.getReturnCode())) {
            throw new S21AbendException(NFCM0032E);
        }
        return inArTrxBalMsg;
    }

    // START 2016/07/29 K.Kojima [QC#12657,MOD]
    // private AR_APPLY_INTFC_WRKTMsg
    // creationArApplyInterfaceWork(String rcptNum, String ccyCd,
    // BigDecimal arTrxBalPk, BigDecimal orgArTrxBalPk, String userId,
    // String billToCust, BigDecimal dealRfAmt, String aGrKey, String
    // procCaseTpCd) {
    private AR_APPLY_INTFC_WRKTMsg creationArApplyInterfaceWork(String rcptNum, String ccyCd, BigDecimal arTrxBalPk, BigDecimal orgArTrxBalPk, String userId, String billToCust, BigDecimal dealRfAmt, String aGrKey, String procCaseTpCd, String arTrxTpCd, String billToCustAcct) {
        return creationArApplyInterfaceWork(rcptNum, ccyCd, arTrxBalPk, orgArTrxBalPk, userId, billToCust, dealRfAmt, aGrKey, procCaseTpCd, false, arTrxTpCd, null, billToCustAcct);
    }

    private AR_APPLY_INTFC_WRKTMsg creationArApplyInterfaceWork(String rcptNum, String ccyCd, BigDecimal arTrxBalPk, BigDecimal orgArTrxBalPk, String userId, String billToCust, BigDecimal dealRfAmt, String aGrKey, String procCaseTpCd,
            boolean creditCardRefund, String arTrxTpCd, String arAdjNum, String billToCustAcct) {
        // END 2016/07/29 K.Kojima [QC#12657,MOD]

        String ezUpTimeR = null;
        String ezUpdTimeZoneR = null;
        String ezUpTimeT = null;
        String ezUpdTimeZoneT = null;
        String orgEzUpTimeT = null;
        String orgEzUpdTimeZoneT = null;
        BigDecimal dealRmngBalAmt = BigDecimal.ZERO;

        AR_RCPTTMsg outArRcptMsg = findArRcptInfo(this.glblCmpyCd, rcptNum);

        if (outArRcptMsg == null) {
            return null;
        }
        ezUpTimeR = outArRcptMsg.ezUpTime.getValue();
        ezUpdTimeZoneR = outArRcptMsg.ezUpTimeZone.getValue();
        dealRmngBalAmt = outArRcptMsg.dealRcptRmngBalAmt.getValue();

        AR_TRX_BALTMsg outArTrxBalMsg = findArTrxBalInfo(this.glblCmpyCd, arTrxBalPk);
        if (outArTrxBalMsg == null) {
            return null;
        }

        ezUpTimeT = outArTrxBalMsg.ezUpTime.getValue();
        ezUpdTimeZoneT = outArTrxBalMsg.ezUpTimeZone.getValue();

        // AR_APPLY_INTFC_WRK
        AR_APPLY_INTFC_WRKTMsg inArApplyIntfcWrkMsg = new AR_APPLY_INTFC_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.applyGrpKey, aGrKey);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.applyGrpSubPk, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.bizAppId, BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.upldCsvRqstPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealSqNum, ZYPCommonFunc.leftPad(STR_1, MAX_LENGTH_8, PAD_STR_0));
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealSqDtlNum, ZYPCommonFunc.leftPad(STR_1, MAX_LENGTH_4, PAD_STR_0));
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.procStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.usrId, userId);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.rcptNum, rcptNum);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.rcptDtlNum, ZYPCommonFunc.leftPad(STR_1, MAX_LENGTH_4, PAD_STR_0));
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.rcptInProcSqPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.rcptGlDt, this.aRBatchDt);
        // START 2018/06/08 E.Kameishi [QC#23164,MOD]
        //ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.payerCustCd, billToCust);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.payerCustCd, billToCustAcct);
        // END 2018/06/08 E.Kameishi [QC#23164,MOD]
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.rcptTrxBalPk, arTrxBalPk);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.rcptHdrLastUpdTs, ezUpTimeR);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.rcptHdrTz, ezUpdTimeZoneR);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.rcptTrxBalLastUpdTs, ezUpTimeT);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.rcptTrxBalTz, ezUpdTimeZoneT);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.grpInvFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.invTrxBalPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.crTrxBalPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealCcyCd, ccyCd);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealOnAcctCashAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.arAdjTrxTpCd, AR_ADJ_TRX_TP.REFUND);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.tocCd, ZYPCodeDataUtil.getVarCharConstValue("AR_RCPT_TOC_CD", this.glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.coaProdCd, ZYPCodeDataUtil.getVarCharConstValue("AR_RCPT_PROD_CD", this.glblCmpyCd));
        if (NFCConst.CST_XX_PROC_CASE_TP_CD_NEW_RCPT.equals(procCaseTpCd)) {
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.procTpCd, NFCConst.CST_XX_PROC_TP_CD_ADD);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealApplyAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealApplyAdjAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.arAdjTpCd, ZYPCodeDataUtil.getVarCharConstValue("AR_ADJ_TP_CM_RFND", this.glblCmpyCd));
        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_PMT.equals(procCaseTpCd)) {
            AR_TRX_BALTMsg orgOutArTrxBalMsg = findArTrxBalInfo(this.glblCmpyCd, orgArTrxBalPk);
            if (orgOutArTrxBalMsg == null) {
                return null;
            }
            orgEzUpTimeT = orgOutArTrxBalMsg.ezUpTime.getValue();
            orgEzUpdTimeZoneT = orgOutArTrxBalMsg.ezUpTimeZone.getValue();

            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.procTpCd, NFCConst.CST_XX_PROC_TP_CD_ADD);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.invNum, orgOutArTrxBalMsg.arTrxNum);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.invTrxBalPk, orgArTrxBalPk);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.invTrxBalLastUpdTs, orgEzUpTimeT);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.invTrxBalTz, orgEzUpdTimeZoneT);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.arAdjTrxTpCd, AR_ADJ_TRX_TP.ADJUSTMENT);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.cashDiscPct, orgOutArTrxBalMsg.cashDiscPct);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealCashDiscAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.cashAppGlDt, this.aRBatchDt);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealApplyAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealApplyAdjAmt, dealRfAmt);
            // START 2018/06/06 E.Kameishi [QC#23164,MOD]
            if (AR_TRX_TP.ON_ACCOUNT.equals(arTrxTpCd)) {
                // START 2022/03/04 D.Mamaril [QC#59333,MOD]
                //ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.arAdjTpCd, ZYPCodeDataUtil.getVarCharConstValue("AR_ADJ_TP_OA_RFND", this.glblCmpyCd));
                ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.arAdjTpCd, ZYPCodeDataUtil.getVarCharConstValue("AR_ADJ_TP_AR_CASH_RFND", this.glblCmpyCd));
                // END 2022/03/04 D.Mamaril [QC#59333,MOD]

                // START 2022/03/04 D.Mamaril [QC#59333,ADD]
                ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealApplyAdjAmt, dealRfAmt.negate());
                ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.arAdjTrxTpCd, AR_ADJ_TRX_TP.REFUND);
                // END 2022/03/04 D.Mamaril [QC#59333,ADD]
            } else {
                ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.arAdjTpCd, ZYPCodeDataUtil.getVarCharConstValue("AR_ADJ_TP_CM_RFND", this.glblCmpyCd));
            }
            // END 2018/06/06 E.Kameishi [QC#23164,MOD]
        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_RF.equals(procCaseTpCd)) {
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.procTpCd, NFCConst.CST_XX_PROC_TP_CD_UPD);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.cashAppGlDt, this.aRBatchDt);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealApplyAmt, dealRmngBalAmt);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealApplyAdjAmt, dealRmngBalAmt);
            // START 2016/07/29 K.Kojima [QC#12657,MOD]
            // ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.arAdjTpCd,
            // ZYPCodeDataUtil.getVarCharConstValue("AR_ADJ_TP_AR_CASH_RFND",
            // this.glblCmpyCd));
            if (creditCardRefund) {
                ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.arAdjTpCd, ZYPCodeDataUtil.getVarCharConstValue("AR_ADJ_TP_CR_CARD_RFND", this.glblCmpyCd));
            } else {
                ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.arAdjTpCd, ZYPCodeDataUtil.getVarCharConstValue("AR_ADJ_TP_AR_CASH_RFND", this.glblCmpyCd));
            }
            // END 2016/07/29 K.Kojima [QC#12657,MOD]
         // START 2022/03/04 D.Mamaril [QC#59333,ADD]  
        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_ADD_DED_OR_ACC.equals(procCaseTpCd)) {

            // Get On Account Invoice Transaction Information
            AR_TRX_BALTMsg orgOutArTrxBalMsg = findArTrxBalInfo(this.glblCmpyCd, orgArTrxBalPk);
            if (orgOutArTrxBalMsg == null) {
                return null;
            }
            orgEzUpTimeT = orgOutArTrxBalMsg.ezUpTime.getValue();
            orgEzUpdTimeZoneT = orgOutArTrxBalMsg.ezUpTimeZone.getValue();

            // Get AR_APPLY_INTFC of On Account Information
            AR_APPLY_INTFC_WRKTMsg copyArApplyIntfcWrkMsg = (AR_APPLY_INTFC_WRKTMsg) getApplyIntfcWrk(rcptNum, orgOutArTrxBalMsg.arTrxNum.getValue(), orgOutArTrxBalMsg.attAdjNum.getValue());
            if (copyArApplyIntfcWrkMsg == null) {
                return null;
            }
            EZDMsg.copy(copyArApplyIntfcWrkMsg, null, inArApplyIntfcWrkMsg, null);

            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.applyGrpKey, aGrKey);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.procTpCd, NFCConst.CST_PROC_TP_CD_CANC);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.procStsCd, PROC_STS.IN_COMPLETED);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.arTrxTpCd, AR_TRX_TP.ON_ACCOUNT);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.rcptHdrLastUpdTs, ezUpTimeR);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.rcptHdrTz, ezUpdTimeZoneR);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.rcptTrxBalLastUpdTs, ezUpTimeT);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.rcptTrxBalTz, ezUpdTimeZoneT);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.invTrxBalLastUpdTs, orgEzUpTimeT);
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.invTrxBalTz, orgEzUpdTimeZoneT);

            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealApplyAmt, inArApplyIntfcWrkMsg.dealApplyAmt.getValue().negate());
            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealApplyAdjAmt, inArApplyIntfcWrkMsg.dealApplyAdjAmt.getValue().negate());

            checkArApplyIntfcWrk(inArApplyIntfcWrkMsg);
        }
        // END 2022/03/04 D.Mamaril [QC#59333,ADD]

        EZDTBLAccessor.create(inArApplyIntfcWrkMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inArApplyIntfcWrkMsg.getReturnCode())) {
            throw new S21AbendException(NFCM0032E);
        }
        return inArApplyIntfcWrkMsg;
    }

    private AR_RCPTTMsg updateArReceipt(String arRcptRfRsnCd, Map<String, Object> arRfRqstDtlMap) {

        AR_RCPTTMsg inMsg = new AR_RCPTTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptNum, (String) arRfRqstDtlMap.get("AR_TRX_NUM"));
        AR_RCPTTMsg outMsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
        if (outMsg == null) {
            setErrorInfo("NWZM0818E", null);
            return null;
        } else {
            ZYPEZDItemValueSetter.setValue(outMsg.rfDt, this.aRBatchDt);
            ZYPEZDItemValueSetter.setValue(outMsg.rfGlDt, this.aRBatchDt);
            ZYPEZDItemValueSetter.setValue(outMsg.arRcptRfRsnCd, arRcptRfRsnCd);
            ZYPEZDItemValueSetter.setValue(outMsg.arCashApplyStsCd, AR_CASH_APPLY_STS.APPLIED);
            // START 2016/12/08 E.Kameishi [QC#16286,ADD]
            ZYPEZDItemValueSetter.setValue(outMsg.arRcptStsCd, AR_RCPT_STS.REFUND);
            // END 2016/12/08 E.Kameishi [QC#16286,ADD]
            EZDTBLAccessor.update(outMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                throw new S21AbendException("NFCM0032E");
            }
        }
        return outMsg;
    }

    private AR_TRX_BALTMsg updateArTransactionBalance(Map<String, Object> rsDtl) throws SQLException {

        AR_TRX_BALTMsg inTMsg = new AR_TRX_BALTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.arTrxBalPk, (BigDecimal) rsDtl.get("AR_TRX_BAL_PK"));
        AR_TRX_BALTMsg outTMsg = (AR_TRX_BALTMsg) S21FastTBLAccessor.findByKeyForUpdate(inTMsg);
        if (outTMsg == null) {
            setErrorInfo("NFCM0032E", null);
            return null;
        } else {
            ZYPEZDItemValueSetter.setValue(outTMsg.arCashApplyStsCd, AR_CASH_APPLY_STS.APPLIED);
            EZDTBLAccessor.update(outTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outTMsg.getReturnCode())) {
                throw new S21AbendException("NFCM0032E");
            }
        }
        return inTMsg;
    }

    private boolean callRecieptDataCreationAPI(String aGrKey) {

        NFZC301001 api = new NFZC301001();
        NFZC301001PMsg apiMsg = new NFZC301001PMsg();
        apiMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        apiMsg.applyGrpKey.setValue(aGrKey);
        apiMsg.dealSqNum.setValue(DEAL_SQ_NUM);
        apiMsg.batDt.setValue(this.aRBatchDt);
        api.execute(apiMsg, S21ApiInterface.ONBATCH_TYPE.BATCH);
        setApiErrMsgList(apiMsg);

        String result = apiMsg.getReturnCode();
        // result == "1"
        if (!APPLY_RTNCD_NORMAL.equals(result)) {
            // result == "0"
            if (APPLY_RTNCD_UN_PROCCES.equals(result)) {
                setErrorInfo(NFCM0002E, new String[] {"Unprocessing" });
                return false;
                // result == "2"
            } else if (APPLY_RTNCD_CASHAPP_ERROR.equals(result)) {
                setErrorInfo(NFCM0002E, new String[] {"Cash Application Error" });
                return false;
                // result == "3"
            } else if (APPLY_RTNCD_EXCLUSION_ERROR.equals(result)) {
                setErrorInfo(NFCM0002E, new String[] {"Exclusion Error" });
                return false;
                // result == "4"
            } else if (APPLY_RTNCD_OTHERS_ERROR.equals(result)) {
                setErrorInfo(NFCM0002E, new String[] {"Others Error" });
                return false;
            } else {
                setErrorInfo(NFCM0002E, new String[] {"Others Error" });
                return false;
            }
        }
        return true;
    }

    // START 2016/07/29 K.Kojima [QC#12657,MOD]
    // private AR_RF_TRXTMsg creationArRefundTransaction(ResultSet rs,
    // Map<String, Object> arRfRqstDtlMap) throws SQLException {
    private AR_RF_TRXTMsg creationArRefundTransaction(ResultSet rs, Map<String, Object> arRfRqstDtlMap, boolean creditCardRefund) throws SQLException {
        // END 2016/07/29 K.Kojima [QC#12657,MOD]

        AR_RF_TRXTMsg inTMsg = new AR_RF_TRXTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, (String) arRfRqstDtlMap.get("GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.arRfTrxPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AR_RF_TRX_SQ));
        ZYPEZDItemValueSetter.setValue(inTMsg.arRfRqstPk, rs.getBigDecimal("AR_RF_RQST_PK"));
        ZYPEZDItemValueSetter.setValue(inTMsg.arRfRqstDtlPk, (BigDecimal) arRfRqstDtlMap.get("AR_RF_RQST_DTL_PK"));
        ZYPEZDItemValueSetter.setValue(inTMsg.arRfCratDt, this.aRBatchDt);
        // START 2016/07/29 K.Kojima [QC#12657,MOD]
        // ZYPEZDItemValueSetter.setValue(inTMsg.arRfStsCd,
        // AR_RF_STS.CREATED);
        if (creditCardRefund) {
            ZYPEZDItemValueSetter.setValue(inTMsg.arRfStsCd, AR_RF_STS.CLOSED);
        } else {
            ZYPEZDItemValueSetter.setValue(inTMsg.arRfStsCd, AR_RF_STS.CREATED);
        }
        // END 2016/07/29 K.Kojima [QC#12657,MOD]
        ZYPEZDItemValueSetter.setValue(inTMsg.arRfTpCd, rs.getString("AR_RF_TP_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.billToCustAcctCd, rs.getString("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.arTrxTpCd, (String) arRfRqstDtlMap.get("AR_TRX_TP_CD"));
        if (AR_RF_TP.CFS.equals(inTMsg.arRfTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(inTMsg.arOrgRcptNum, (String) arRfRqstDtlMap.get("AR_TRX_NUM"));
        }
        ZYPEZDItemValueSetter.setValue(inTMsg.dealOrigGrsAmt, (BigDecimal) arRfRqstDtlMap.get("DEAL_ORIG_GRS_AMT"));
        ZYPEZDItemValueSetter.setValue(inTMsg.dealRmngBalGrsAmt, (BigDecimal) arRfRqstDtlMap.get("DEAL_RMNG_BAL_GRS_AMT"));
        ZYPEZDItemValueSetter.setValue(inTMsg.dealRfAmt, (BigDecimal) arRfRqstDtlMap.get("DEAL_RF_AMT"));
        ZYPEZDItemValueSetter.setValue(inTMsg.dealCcyCd, (String) arRfRqstDtlMap.get("DEAL_CCY_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.rfExchRate, (BigDecimal) arRfRqstDtlMap.get("RF_EXCH_RATE"));
        ZYPEZDItemValueSetter.setValue(inTMsg.funcOrigGrsAmt, (BigDecimal) arRfRqstDtlMap.get("FUNC_ORIG_GRS_AMT"));
        ZYPEZDItemValueSetter.setValue(inTMsg.funcRmngBalGrsAmt, (BigDecimal) arRfRqstDtlMap.get("FUNC_RMNG_BAL_GRS_AMT"));
        ZYPEZDItemValueSetter.setValue(inTMsg.funcRfAmt, (BigDecimal) arRfRqstDtlMap.get("FUNC_RF_AMT"));
        ZYPEZDItemValueSetter.setValue(inTMsg.arRfChkCmntTxt, rs.getString("AR_RF_CHK_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(inTMsg.vndCd, rs.getString("VND_CD"));
        // START 2017/12/19 J.Kim [QC#22199,MOD]
        //if (ZYPCommonFunc.hasValue(inTMsg.arRfTrxPk)) {
        //    //QC#21689 UPD START
        //    ZYPEZDItemValueSetter.setValue(inTMsg.apVndInvNum, ZYPCommonFunc.leftPad(inTMsg.arRfTrxPk.getValue().toString(), MAX_LENGTH_10, PAD_STR_0));
        //    //QC#21689 UPD END
        //}
        if (ZYPCommonFunc.hasValue(inTMsg.arRfRqstPk)) {
            StringBuffer refundRequestPk = new StringBuffer();
            refundRequestPk.append(REFUND);
            refundRequestPk.append(inTMsg.arRfRqstPk.getValue().toString());
            ZYPEZDItemValueSetter.setValue(inTMsg.apVndInvNum, refundRequestPk.toString());

        }
        // END 2017/12/19 J.Kim [QC#22199,MOD]
        ZYPEZDItemValueSetter.setValue(inTMsg.arRfApvlUsrId, rs.getString("AR_RF_APVL_USR_ID"));
        ZYPEZDItemValueSetter.setValue(inTMsg.arRfApvlUsrNm, rs.getString("AR_RF_APVL_USR_NM"));
        ZYPEZDItemValueSetter.setValue(inTMsg.cfsIntfcCpltFlg, ZYPConstant.FLG_OFF_N);
        EZDTBLAccessor.insert(inTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            throw new S21AbendException(NFCM0032E);
        }
        return inTMsg;
    }

    private boolean isSameTimeStamp(ResultSet rs, ResultSet rsDtl) throws SQLException {

        AR_TRX_BALTMsg inTMsg = new AR_TRX_BALTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, rsDtl.getString("GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(inTMsg.arTrxBalPk, rsDtl.getBigDecimal("AR_TRX_BAL_PK"));
        AR_TRX_BALTMsg outTMsg = (AR_TRX_BALTMsg) S21FastTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return false;
        }

        String trxBalUpTime = outTMsg.ezUpTime.getValue().substring(0, SUBSTRING_LEN12);
        String arRfRqstDltUpTime = rsDtl.getString("EZUPTIME_ARRD").substring(0, SUBSTRING_LEN12);

        if (!trxBalUpTime.equals(arRfRqstDltUpTime)) {
            getArfRqstInfo(rsDtl.getString("GLBL_CMPY_CD"), rsDtl.getBigDecimal("AR_RF_RQST_PK"), AR_RF_STS.ERROR);
            if (AR_TRX_TP.CREDIT_MEMO.equals(rsDtl.getString("AR_TRX_TP_CD"))) {
                this.commitCountCrm++;
            } else {
                this.commitCountRcp++;
            }
            commitCount();
            return false;
        } else {
            return true;
        }
    }

    private AR_RF_RQSTTMsg getArfRqstInfo(String gbCmpyCd, BigDecimal arRfRqstPk, String stsCd) {

        AR_RF_RQSTTMsg arRfRqstinTMsg = new AR_RF_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(arRfRqstinTMsg.glblCmpyCd, gbCmpyCd);
        ZYPEZDItemValueSetter.setValue(arRfRqstinTMsg.arRfRqstPk, arRfRqstPk);
        AR_RF_RQSTTMsg arRfRqstOutTMsg = (AR_RF_RQSTTMsg) S21FastTBLAccessor.findByKeyForUpdate(arRfRqstinTMsg);
        if (arRfRqstOutTMsg != null) {
            ZYPEZDItemValueSetter.setValue(arRfRqstOutTMsg.arRfStsCd, stsCd);
            EZDTBLAccessor.update(arRfRqstOutTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(arRfRqstOutTMsg.getReturnCode())) {
                throw new S21AbendException(NFCM0032E);
            }
        }
        return arRfRqstOutTMsg;
    }

    private AR_RCPTTMsg findArRcptInfo(String glblCmpyCd, String rcptNum) {
        AR_RCPTTMsg inArRcptMsg = new AR_RCPTTMsg();
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.rcptNum, rcptNum);
        AR_RCPTTMsg outMsg = (AR_RCPTTMsg) S21FastTBLAccessor.findByKey(inArRcptMsg);
        return outMsg;
    }
    
    // START 2018/04/03 E.Kameishi [QC#23164,ADD]
    private AR_ADJTMsg findArAdj(String glblCmpyCd, String adjNum) {
        AR_ADJTMsg inArAdjMsg = new AR_ADJTMsg();
        ZYPEZDItemValueSetter.setValue(inArAdjMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inArAdjMsg.arAdjNum, adjNum);
        AR_ADJTMsg outMsg = (AR_ADJTMsg) S21FastTBLAccessor.findByKey(inArAdjMsg);
        return outMsg;
    }
    // END 2018/04/03 E.Kameishi [QC#23164,ADD]

    private AR_TRX_BALTMsg findArTrxBalInfo(String glblCmpyCd, BigDecimal arTrxBalPk) {
        AR_TRX_BALTMsg inTMsg = new AR_TRX_BALTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.arTrxBalPk, arTrxBalPk);
        AR_TRX_BALTMsg outMsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKey(inTMsg);
        return outMsg;
    }

    private GLBL_CMPYTMsg findGlblCmpy() {

        GLBL_CMPYTMsg inTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        GLBL_CMPYTMsg outTMsg = (GLBL_CMPYTMsg) S21FastTBLAccessor.findByKey(inTMsg);
        return outTMsg;
    }

    private String findArAcctDt(String glblCmpyCd, String batTp) {
        AR_ACCT_DTTMsg inTMsg = new AR_ACCT_DTTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.subSysCd, NFCConst.CST_SUB_SYS_CD_NFC);
        ZYPEZDItemValueSetter.setValue(inTMsg.onlBatTpCd, batTp);
        AR_ACCT_DTTMsg outTMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(inTMsg);
        if (outTMsg != null) {
            return outTMsg.acctDt.getValue();
        }
        return ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);
    }

    /**
     * findAcctDlyActlExchRatest
     * @param ccyCd String
     * @param slsDt String
     * @return S21SsmEZDResult
     */
    private BigDecimal findAcctDlyActlExchRatest(String ccyCd, String slsDt) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("ccyCd", ccyCd);
        paramMap.put("slsDt", this.aRBatchDt);
        BigDecimal exchRate = (BigDecimal) ssmBatchClient.queryObject("getExchRate", paramMap);
        if (exchRate == null) {
            return BigDecimal.ZERO;
        }
        return exchRate;
    }

    // START 2016/07/29 K.Kojima [QC#12665,ADD]
    /**
     * @param arRfTpCd String
     * @return boolean
     */
    private boolean isCreditCardRefund(String arRfTpCd) {
        if (arRfTpCd != null && arRfTpCd.equals(AR_RF_TP.CREDIT_CARD_REFUND)) {
            return true;
        }
        return false;
    }
    // END 2016/07/29 K.Kojima [QC#12665,ADD]

    // START 2018/07/13 Y.Matsui [QC#26993, ADD]
    private boolean updateArBatRcptStatus(String arBatRcptNm) {
        if (!ZYPCommonFunc.hasValue(arBatRcptNm)) {
            return true;
        }
        NFZC310001PMsg pMsg = new NFZC310001PMsg();
        pMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        pMsg.arBatRcptNm.setValue(arBatRcptNm);
        new NFZC310001().execute(pMsg, ONBATCH_TYPE.BATCH);
        List<S21ApiMessage> xxMsgList = S21ApiUtil.getXxMsgList(pMsg);
        if (!xxMsgList.isEmpty()) {
            for (S21ApiMessage xxMsg : xxMsgList) {
                setErrorInfo(xxMsg.getXxMsgid(), xxMsg.getXxMsgPrmArray());
                return false;
            }
        }
        return true;
    }
    // END   2018/07/13 Y.Matsui [QC#26993, ADD]

    // START 2022/03/04 D.Mamaril [QC#59333,ADD]
    /**
     * <dd>Find Receipt Transaction in AR_TRX_BAL using Receipt Number.
     * @param rcptNum Receipt Number
     * @return AR_TRX_BALTMsg
     */
    private AR_TRX_BALTMsg findRcptArTrxBalInfo(String rcptNum) {
        AR_TRX_BALTMsg prmTMsg = new AR_TRX_BALTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        prmTMsg.setConditionValue("arTrxNum01", rcptNum);
        AR_TRX_BALTMsgArray outTMsgArray = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(prmTMsg);

        if (outTMsgArray.getValidCount() == 0) {
            return null;
        }
        return outTMsgArray.no(0);
    }

    /**
     * <dd>Update the Source Cancel Flag of On Account in AR_CASH_APP.
     * @param arTrxBalPk On Account Transaction Primary Key
     * @param arAdjNum Adjustment Number or On Account
     * @return AR_CASH_APPTMsg
     */
    @SuppressWarnings("unchecked")
    private AR_CASH_APPTMsg updateArCashApp(BigDecimal arTrxBalPk, String arAdjNum) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("arTrxBalPk", arTrxBalPk);
        paramMap.put("flgN", ZYPConstant.FLG_OFF_N);
        paramMap.put("arApplyTpCdAdj", AR_APPLY_TP.ADJUSTMENT);
        paramMap.put("attAdjNum", arAdjNum);
        paramMap.put("rowNum", BigDecimal.ONE);

        Map<String, Object> cashAppInfo = (Map<String, Object>) ssmBatchClient.queryObject("findOnAccArCashAppInfo", paramMap);
        if (cashAppInfo == null) {
            return null;
        }

        AR_CASH_APPTMsg arCashAppParam = new AR_CASH_APPTMsg();
        ZYPEZDItemValueSetter.setValue(arCashAppParam.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(arCashAppParam.arCashAppPk, (BigDecimal) cashAppInfo.get("AR_CASH_APP_PK"));
        ZYPEZDItemValueSetter.setValue(arCashAppParam.arCashAppSqNum, (String) cashAppInfo.get("AR_CASH_APP_SQ_NUM"));

        AR_CASH_APPTMsg updateArCashAppTMsg = (AR_CASH_APPTMsg) S21FastTBLAccessor.findByKeyForUpdate(arCashAppParam);
        if (updateArCashAppTMsg == null) {
            setErrorInfo("NFCM0032E", null);
            return null;
        }

        ZYPEZDItemValueSetter.setValue(updateArCashAppTMsg.arScrCancFlg, ZYPConstant.FLG_ON_Y);
        S21FastTBLAccessor.update(updateArCashAppTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updateArCashAppTMsg.getReturnCode())) {
            throw new S21AbendException("NFCM0032E");
        }

        return updateArCashAppTMsg;
    }

    /**
     * <dd>Find the AR_APPLY_INTFC_WRK data of On Account.
     * @param rcptNum Receipt Number
     * @param invNum Invoice Number of On Account
     * @param arAdjNum Adjustment Number or On Account
     * @return AR_APPLY_INTFC_WRKTMsg
     */
    @SuppressWarnings("unchecked")
    private AR_APPLY_INTFC_WRKTMsg getApplyIntfcWrk(String rcptNum, String invNum, String arAdjNum) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("rcptNum", rcptNum);
        paramMap.put("invNum", invNum);
        paramMap.put("arAdjNum", arAdjNum);
        paramMap.put("arAdjTrxTpCdAcc", AR_ADJ_TRX_TP.ON_ACCOUNT);
        paramMap.put("rowNum", BigDecimal.ONE);

        Map<String, Object> arApplyIntfcWrkInfo = (Map<String, Object>) ssmBatchClient.queryObject("findCancelArApplyIntfcWrkInfo", paramMap);
        if (arApplyIntfcWrkInfo == null) {
            return null;
        }

        AR_APPLY_INTFC_WRKTMsg arApplyIntfcWrkParam = new AR_APPLY_INTFC_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(arApplyIntfcWrkParam.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(arApplyIntfcWrkParam.applyGrpKey, (String) arApplyIntfcWrkInfo.get("APPLY_GRP_KEY"));
        ZYPEZDItemValueSetter.setValue(arApplyIntfcWrkParam.applyGrpSubPk, (BigDecimal) arApplyIntfcWrkInfo.get("APPLY_GRP_SUB_PK"));

        AR_APPLY_INTFC_WRKTMsg arApplyIntfcWrkTMsg = (AR_APPLY_INTFC_WRKTMsg) S21FastTBLAccessor.findByKey(arApplyIntfcWrkParam);
        if (arApplyIntfcWrkTMsg == null) {
            setErrorInfo("NFCM0032E", null);
            return null;
        }

        return arApplyIntfcWrkTMsg;
    }

    /**
     * <dd>Check and update the values of AR_APPLY_INTFC_WRKTMsg.
     * @param inMsg AR_APPLY_INTFC_WRKTMsg
     */
    private static void checkArApplyIntfcWrk(AR_APPLY_INTFC_WRKTMsg inMsg) {

        if (!ZYPCommonFunc.hasValue(inMsg.dealApplyAmt)) {
            ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAmt, BigDecimal.ZERO);
        }
        if (!ZYPCommonFunc.hasValue(inMsg.cashDiscPct)) {
            ZYPEZDItemValueSetter.setValue(inMsg.cashDiscPct, BigDecimal.ZERO);
        }
        if (!ZYPCommonFunc.hasValue(inMsg.dealCashDiscAmt)) {
            ZYPEZDItemValueSetter.setValue(inMsg.dealCashDiscAmt, BigDecimal.ZERO);
        }
        if (!ZYPCommonFunc.hasValue(inMsg.dealOnAcctCashAmt)) {
            ZYPEZDItemValueSetter.setValue(inMsg.dealOnAcctCashAmt, BigDecimal.ZERO);
        }
        if (!ZYPCommonFunc.hasValue(inMsg.dealApplyAdjAmt)) {
            ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjAmt, BigDecimal.ZERO);
        }
        if (!ZYPCommonFunc.hasValue(inMsg.dealApplyAdjRsvdAmt)) {
            ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);
        }
    }
    // END 2022/03/04 D.Mamaril [QC#59333,ADD]

    // START 2022/08/08 M.Kikushima [QC#57083,ADD]
    private AR_RF_TRXTMsg updateArRfTrx(AR_RF_TRXTMsg inMsg, String arAdjNum) {

        AR_RF_TRXTMsg outMsg = (AR_RF_TRXTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
        if (outMsg == null) {
            setErrorInfo("NWZM0818E", null);
            return null;
        } else {
            ZYPEZDItemValueSetter.setValue(outMsg.arAdjNum, arAdjNum);
            EZDTBLAccessor.update(outMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                throw new S21AbendException("NFCM0032E");
            }
        }
        return outMsg;
    }

    private AR_APPLY_INTFC_WRKTMsg findApplyIntfcWrk(String aGrKey) {
        AR_APPLY_INTFC_WRKTMsg arApplyIntfcWrkParam = new AR_APPLY_INTFC_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(arApplyIntfcWrkParam.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(arApplyIntfcWrkParam.applyGrpKey, aGrKey);
        ZYPEZDItemValueSetter.setValue(arApplyIntfcWrkParam.applyGrpSubPk, BigDecimal.ONE);

        AR_APPLY_INTFC_WRKTMsg arApplyIntfcWrkTMsg = (AR_APPLY_INTFC_WRKTMsg) S21FastTBLAccessor.findByKey(arApplyIntfcWrkParam);
        if (arApplyIntfcWrkTMsg == null) {
            setErrorInfo("NFCM0032E", null);
            return null;
        }

        return arApplyIntfcWrkTMsg;
    }
    // END 2022/08/08 M.Kikushima [QC#57083,ADD]

}
