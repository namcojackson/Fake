/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB130001;

import static com.canon.cusa.s21.batch.NFB.NFBB130001.constant.NFBB130001Constant.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.AR_ADJTMsg;
import business.db.AR_RCPTTMsg;
import business.db.AR_RF_TRXTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RF_STS;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Update PAID status for AR Refund
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/03/2015   Hitachi         J.Kim           Create          N/A
 * 2022/08/08   Hitachi         M.Kikushima     Update          QC#57083
 * 2022/12/23   Hitachi         R.Takau         Update          QC#60843
 * </pre>
 */
public class NFBB130001 extends S21BatchMain {

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** total Count */
    private int totalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    /** Normal Count */
    private int normalCount = 0;

    /** Commit Count */
    private int commitCount = 0;

    /** Commit Max Number */
    private int commitNumber = 0;

    @Override
    protected void initRoutine() {

        // "Global Company Code" Mandatory
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NFBM0199E);
        }

        // initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }
    }

    @Override
    protected void mainRoutine() {
        doProcess();
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount, this.totalCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFBB130001().executeBatch(NFBB130001.class.getSimpleName());

    }

    private void doProcess() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AR_RF_TRXTMsg> arRfTrxInfoList = new ArrayList<AR_RF_TRXTMsg>();
        // START 2022/08/10 M.Kikushima [QC#57083,ADD]
        List<AR_RCPTTMsg> arRcptInfoList = new ArrayList<AR_RCPTTMsg>();
        List<AR_ADJTMsg> arAdjInfoList = new ArrayList<AR_ADJTMsg>();
        // END 2022/08/10 M.Kikushima [QC#57083,ADD]

        Map<String, Object> stesParam = new HashMap<String, Object>();
        stesParam.put("glblCmpyCd", this.glblCmpyCd);
        stesParam.put("stsCd", AR_RF_STS.AP_INOVICE_CREATED);

        try {

            ps = this.ssmLLClient.createPreparedStatement("getArRfTrxInfo", stesParam, execParam());
            rs = ps.executeQuery();

            while (rs.next()) {

                this.totalCount++;
                AR_RF_TRXTMsg inTMsg = new AR_RF_TRXTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
                ZYPEZDItemValueSetter.setValue(inTMsg.arRfTrxPk, rs.getBigDecimal(AR_RF_TRX_PK));
                AR_RF_TRXTMsg outTMsg = (AR_RF_TRXTMsg) S21FastTBLAccessor.findByKeyForUpdate(inTMsg);

                if (outTMsg != null) {
                    String acctInvStsCd = rs.getString(ACCT_INV_STS_CD);
                    ZYPEZDItemValueSetter.setValue(outTMsg.acctInvStsCd, acctInvStsCd);
                    ZYPEZDItemValueSetter.setValue(outTMsg.apPmtChkNum, rs.getString(AP_PMT_CHK_NUM));
                    if (ACCT_INV_STS.PAID.equals(acctInvStsCd) || ACCT_INV_STS.VOIDED.equals(acctInvStsCd)) {
                        ZYPEZDItemValueSetter.setValue(outTMsg.arRfStsCd, AR_RF_STS.CLOSED);
                        // START 2022/08/10 M.Kikushima [QC#57083,ADD]
                        AR_RCPTTMsg arRcptTMsg = new AR_RCPTTMsg();
                        AR_ADJTMsg arAdjTMsg = new AR_ADJTMsg();
                        String appendCmntTxt = "";
                        if (ZYPCommonFunc.hasValue(rs.getString(AP_PMT_CHK_NUM))) {
                            if(ZYPCommonFunc.hasValue(rs.getString(AR_RF_CRAT_DT))) {
                                // START 2022/12/23 R.Takau [QC#60843,MOD]
                                //appendCmntTxt += " " + rs.getString(AR_RF_CRAT_DT) + " ";
                                appendCmntTxt += ZYPDateUtil.convertFormat(rs.getString(AR_RF_CRAT_DT), ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MMDDYYYY, null) + " ";
                                // END 2022/12/23 R.Takau [QC#60843,MOD]
                            }
                            appendCmntTxt += rs.getString(AP_VND_INV_NUM) + " Check ";
                            if (ZYPCommonFunc.hasValue(rs.getString(AP_PMT_CHK_NUM))) {
                                appendCmntTxt += rs.getString(AP_PMT_CHK_NUM) + " ";
                            }
                            ZYPEZDItemValueSetter.setValue(arRcptTMsg.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
                            ZYPEZDItemValueSetter.setValue(arRcptTMsg.rcptNum, rs.getString(RCPT_NUM));
                            ZYPEZDItemValueSetter.setValue(arRcptTMsg.arRcptMiscCmntTxt, appendCmntTxt);
                            arRcptInfoList.add(arRcptTMsg);
                            ZYPEZDItemValueSetter.setValue(arAdjTMsg.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
                            ZYPEZDItemValueSetter.setValue(arAdjTMsg.arAdjNum, rs.getString(AR_ADJ_NUM));
                            ZYPEZDItemValueSetter.setValue(arAdjTMsg.adjCmntTxt, appendCmntTxt);
                            arAdjInfoList.add(arAdjTMsg);
                        }
                        // END 2022/08/10 M.Kikushima [QC#57083,ADD]
                    }
                    arRfTrxInfoList.add(outTMsg);
                }

                if (this.commitNumber == arRfTrxInfoList.size()) {
                    this.commitCount = this.executionUpdate(arRfTrxInfoList);
                    arRfTrxInfoList = new ArrayList<AR_RF_TRXTMsg>();
                    // START 2022/08/10 M.Kikushima [QC#57083,ADD]
                    // Update AR_RCPT
                    arRcptUpdate(arRcptInfoList);
                    // Update AR_ADJ
                    arAdjUpdate(arAdjInfoList);
                    arRcptInfoList = new ArrayList<AR_RCPTTMsg>();
                    arAdjInfoList = new ArrayList<AR_ADJTMsg>();
                    // END 2022/08/10 M.Kikushima [QC#57083,ADD]
                    this.normalCount += this.commitCount;
                    commitCount = 0;
                }
            }

            if (arRfTrxInfoList.size() > 0) {
                this.commitCount = executionUpdate(arRfTrxInfoList);
                // START 2022/08/10 M.Kikushima [QC#57083,ADD]
                // Update AR_RCPT
                arRcptUpdate(arRcptInfoList);
                // Update AR_ADJ
                arAdjUpdate(arAdjInfoList);
                // END 2022/08/10 M.Kikushima [QC#57083,ADD]
                this.normalCount += this.commitCount;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private S21SsmExecutionParameter execParam() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    private int executionUpdate(List<AR_RF_TRXTMsg> inTMsgList) {

        AR_RF_TRXTMsg[] inTMsgArray = new AR_RF_TRXTMsg[inTMsgList.size()];
        int updateCont = S21FastTBLAccessor.update(inTMsgList.toArray(inTMsgArray));

        if (updateCont != inTMsgArray.length) {
            this.errorCount += inTMsgArray.length - updateCont;
        }
        commit();
        return updateCont;
    }

    // START 2022/08/10 M.Kikushima [QC#57083,ADD]
    private void arRcptUpdate(List<AR_RCPTTMsg> inTMsgList) {

        for (int i=0; i<inTMsgList.size(); i++) {
            AR_RCPTTMsg updateArRcptTMsg = (AR_RCPTTMsg)inTMsgList.get(i);
            AR_RCPTTMsg inMsg = new AR_RCPTTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.rcptNum, updateArRcptTMsg.rcptNum);
            AR_RCPTTMsg outMsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
            if (outMsg != null) {
                ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, updateArRcptTMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(outMsg.rcptNum, updateArRcptTMsg.rcptNum);
                String updateArRcptMiscCmntTxt = "";
                if(ZYPCommonFunc.hasValue(outMsg.arRcptMiscCmntTxt.getValue())) {
                    // START 2022/12/23 R.Takau [QC#60843,MOD]
                    //updateArRcptMiscCmntTxt = outMsg.arRcptMiscCmntTxt.getValue() + updateArRcptTMsg.arRcptMiscCmntTxt.getValue();
                    updateArRcptMiscCmntTxt = outMsg.arRcptMiscCmntTxt.getValue() + " " + updateArRcptTMsg.arRcptMiscCmntTxt.getValue();
                    // END 2022/12/23 R.Takau [QC#60843,MOD]
                } else {
                    updateArRcptMiscCmntTxt += updateArRcptTMsg.arRcptMiscCmntTxt.getValue();
                }
                if(updateArRcptMiscCmntTxt.length() > 1000) {
                    updateArRcptMiscCmntTxt = updateArRcptMiscCmntTxt.substring(0, 1000);
                }
                ZYPEZDItemValueSetter.setValue(outMsg.arRcptMiscCmntTxt, updateArRcptMiscCmntTxt);
                if(updateArRcptTMsg.rcptNum != null && ZYPCommonFunc.hasValue(updateArRcptTMsg.rcptNum.toString())) {
                    EZDTBLAccessor.update(outMsg);

                }
            }
        }
    }

    private void arAdjUpdate(List<AR_ADJTMsg> inTMsgList) {

        for (int i=0; i<inTMsgList.size(); i++) {
            AR_ADJTMsg updateArAdjTMsg = (AR_ADJTMsg)inTMsgList.get(i);
            AR_ADJTMsg inMsg = new AR_ADJTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.arAdjNum, updateArAdjTMsg.arAdjNum);
            AR_ADJTMsg outMsg = (AR_ADJTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
            if (outMsg != null) {
                ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, updateArAdjTMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(outMsg.arAdjNum, updateArAdjTMsg.arAdjNum);
                String updateAdjCmntTxt = "";
                if(outMsg.adjCmntTxt.getValue() != null) {
                    // START 2022/12/23 R.Takau [QC#60843,MOD]
                    //updateAdjCmntTxt += outMsg.adjCmntTxt.getValue() + updateArAdjTMsg.adjCmntTxt.getValue();
                    updateAdjCmntTxt += outMsg.adjCmntTxt.getValue() + " " + updateArAdjTMsg.adjCmntTxt.getValue();
                    // END 2022/12/23 R.Takau [QC#60843,MOD]
                } else {
                    updateAdjCmntTxt += updateArAdjTMsg.adjCmntTxt.getValue();
                }
                if(updateAdjCmntTxt.length() > 65) {
                    updateAdjCmntTxt = updateAdjCmntTxt.substring(0, 65);
                }
                ZYPEZDItemValueSetter.setValue(outMsg.adjCmntTxt, updateAdjCmntTxt);
                if(updateArAdjTMsg.arAdjNum != null && !"".equals(updateArAdjTMsg.arAdjNum.toString())) {
                    EZDTBLAccessor.update(outMsg);
                }
            }
        }
    }
    // END 2022/08/10 M.Kikushima [QC#57083,ADD]

}
