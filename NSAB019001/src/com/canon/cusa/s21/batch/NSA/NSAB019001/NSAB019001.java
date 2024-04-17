/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB019001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;
import business.db.FLD_ADCV_BY_MDLTMsg;
import business.db.SVC_ADCV_BY_MDLTMsg;
import business.db.SVC_BAT_ERR_LOGTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SendMailForErrorInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * ADCV by Model.
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/01   CUSA            SRAA            Create          N/A
 * 2020/03/03   Hitachi         K.Kishimoto     Update          QC#56123
 *</pre>
 */
public class NSAB019001 extends S21BatchMain {

    /**
     * Failed to insert "@".
     */
    public static final String NSAM0032E = "NSAM0032E";

    /**
     * System Error : @
     */
    private static final String NSAM0219E = "NSAM0219E";

    /**
     * Model[@] could not be inserted into the ADCV table.
     */
    private static final String NSAM0356E = "NSAM0356E";

    /**
     * Scale of AVG_MTR_READ_CNT
     */
    private static final int AVG_MTR_READ_CNT_SCL = 8;

    /**
     * Low level coding client
     */
    private S21SsmLowLevelCodingClient ssmLlcClnt = null;

    /**
     * Termination code
     */
    private TERM_CD termCd = null;

    /**
     * Global company code
     */
    private String glblCmpyCd = null;

    /**
     * Normal record count
     */
    private int normRecCnt = 0;

    /**
     * Error record count
     */
    private int errRecCnt = 0;

    /**
     * Error message list
     */
    private List<String> errMsgList;

    /**
     * Error TMsg list
     */
    private List<EZDTMsg> errTMsgList;

    /**
     * System time stamp
     */
    private String sysTs = null;

    /**
     * @param args
     */
    public static void main(String[] args) {
        new NSAB019001().executeBatch(NSAB019001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        ssmLlcClnt = S21SsmLowLevelCodingClient.getClient(NSAB019001.class);
        termCd = TERM_CD.NORMAL_END;
        glblCmpyCd = getGlobalCompanyCode();
        errMsgList = new ArrayList<String>();
        errTMsgList = new ArrayList<EZDTMsg>();
        sysTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
    }

    @Override
    protected void mainRoutine() {
        delTbl();
        if (hasMsg()) {
            termCd = TERM_CD.ABNORMAL_END;
            return;
        }

        commit();

        calcAdcvByMdl();
        // START 2020/03/03 [QC#56123, ADD]
        calcAdcvByMdlForFld();
        // END   2020/03/03 [QC#56123, ADD]

        cratErrLog();
    }

    @Override
    protected void termRoutine() {
        sendEmail();
        setTermState(termCd, normRecCnt, errRecCnt);
    }

    private void delTbl() {
        SVC_ADCV_BY_MDLTMsg tMsg = new SVC_ADCV_BY_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        S21FastTBLAccessor.removeByPartialValue(tMsg, new String[] {"glblCmpyCd" });
        String rtnCd = tMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd) && !S21FastTBLAccessor.RTNCD_NOT_FOUND.equals(rtnCd)) {
            addMsg(null, null, NSAM0219E, "Work Table Deletion cannot be processed.");
        }
        // START 2020/03/03 [QC#56123, ADD]
        FLD_ADCV_BY_MDLTMsg fldTMsg = new FLD_ADCV_BY_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(fldTMsg.glblCmpyCd, glblCmpyCd);
        S21FastTBLAccessor.removeByPartialValue(fldTMsg, new String[] {"glblCmpyCd" });
        rtnCd = fldTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd) && !S21FastTBLAccessor.RTNCD_NOT_FOUND.equals(rtnCd)) {
            addMsg(null, null, NSAM0219E, "Work Table Deletion cannot be processed.");
        }
        // END 2020/03/03 [QC#56123, ADD]
    }

    private void calcAdcvByMdl() {
        PreparedStatement prepStmt = null;
        ResultSet rsltSet = null;
        String preMdlNm = null;
        List<SVC_ADCV_BY_MDLTMsg> tMsgList = new ArrayList<SVC_ADCV_BY_MDLTMsg>();
        try {
            prepStmt = getPrepStmt();
            rsltSet = prepStmt.executeQuery();
            while (rsltSet.next()) {
                String mdlNm = rsltSet.getString("MDL_NM");
                String mtrLbCd = rsltSet.getString("MTR_LB_CD");
                BigDecimal avgMtrReadCnt = rsltSet.getBigDecimal("AVG_MTR_READ_CNT");

                SVC_ADCV_BY_MDLTMsg tMsg = new SVC_ADCV_BY_MDLTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.mdlNm, mdlNm);
                ZYPEZDItemValueSetter.setValue(tMsg.mtrLbCd, mtrLbCd);
                ZYPEZDItemValueSetter.setValue(tMsg.avgMtrReadCnt, avgMtrReadCnt);
                tMsgList.add(tMsg);

                if (preMdlNm != null && !preMdlNm.equals(mdlNm)) {
                    insert(tMsgList);
                    tMsgList.clear();
                    preMdlNm = mdlNm;
                }
            }
            if (tMsgList.size() > 0) {
                insert(tMsgList);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prepStmt, rsltSet);
        }
    }
    // START 2020/03/03 [QC#56123, ADD]
    private void calcAdcvByMdlForFld() {
        PreparedStatement prepStmt = null;
        ResultSet rsltSet = null;
        String preMdlNm = null;
        List<FLD_ADCV_BY_MDLTMsg> tMsgList = new ArrayList<FLD_ADCV_BY_MDLTMsg>();
        try {
            prepStmt = getPrepStmtForFld();
            rsltSet = prepStmt.executeQuery();
            while (rsltSet.next()) {
                String mdlNm = rsltSet.getString("MDL_NM");
                String mtrLbCd = rsltSet.getString("MTR_LB_CD");
                BigDecimal avgMtrReadCnt = rsltSet.getBigDecimal("AVG_MTR_READ_CNT");

                FLD_ADCV_BY_MDLTMsg tMsg = new FLD_ADCV_BY_MDLTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.mdlNm, mdlNm);
                ZYPEZDItemValueSetter.setValue(tMsg.mtrLbCd, mtrLbCd);
                ZYPEZDItemValueSetter.setValue(tMsg.avgMtrReadCnt, avgMtrReadCnt);
                tMsgList.add(tMsg);

                if (preMdlNm != null && !preMdlNm.equals(mdlNm)) {
                    insertForFld(tMsgList);
                    tMsgList.clear();
                    preMdlNm = mdlNm;
                }
            }
            if (tMsgList.size() > 0) {
                insertForFld(tMsgList);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prepStmt, rsltSet);
        }
    }

    private PreparedStatement getPrepStmtForFld() throws SQLException {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setFetchSize(1000);
        execPrm.setMaxRows(0);
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("avgMtrReadCntScl", AVG_MTR_READ_CNT_SCL);
        return ssmLlcClnt.createPreparedStatement("calcAdcvByMdlForFld", prm, execPrm);
    }

    private void insertForFld(List<FLD_ADCV_BY_MDLTMsg> tMsgList) {
        int ins = S21FastTBLAccessor.insert(tMsgList.toArray(new FLD_ADCV_BY_MDLTMsg[tMsgList.size()]));
        if (ins == tMsgList.size()) {
            normRecCnt++;
            commit();
        } else {
            String mdlNm = tMsgList.get(0).mdlNm.getValue();
            addMsg(mdlNm, null, NSAM0356E, mdlNm);
            errRecCnt++;
            rollback();
        }
    }
    // END   2020/03/03 [QC#56123, ADD]

    private PreparedStatement getPrepStmt() throws SQLException {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setFetchSize(1000);
        execPrm.setMaxRows(0);
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("avgMtrReadCntScl", AVG_MTR_READ_CNT_SCL);
        return ssmLlcClnt.createPreparedStatement("calcAdcvByMdl", prm, execPrm);
    }

    private void insert(List<SVC_ADCV_BY_MDLTMsg> tMsgList) {
        int ins = S21FastTBLAccessor.insert(tMsgList.toArray(new SVC_ADCV_BY_MDLTMsg[tMsgList.size()]));
        if (ins == tMsgList.size()) {
            normRecCnt++;
            commit();
        } else {
            String mdlNm = tMsgList.get(0).mdlNm.getValue();
            addMsg(mdlNm, null, NSAM0356E, mdlNm);
            errRecCnt++;
            rollback();
        }
    }

    private void addMsg(String mdlNm, String mdlMtrLbCd, String errCd, String... prm) {
        // Message
        String errMsg = S21MessageFunc.clspGetMessage(errCd, prm);
        errMsgList.add(errMsg);

        // Log
        SVC_BAT_ERR_LOGTMsg tMsg = new SVC_BAT_ERR_LOGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_BAT_ERR_LOG_SQ"));
        ZYPEZDItemValueSetter.setValue(tMsg.bizAppId, "NSAB0190");
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrLogTs, sysTs);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNm_01, "MDL_NM");
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNum_01, mdlNm);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNm_02, "MTR_LB_CD");
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNum_02, mdlMtrLbCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrMsgTxt, errMsg);
        errTMsgList.add(tMsg);
    }

    private boolean hasMsg() {
        return errMsgList.size() > 0;
    }

    private void sendEmail() {
        if (hasMsg()) {
            NSXC001001SendMailForErrorInfo mailer = new NSXC001001SendMailForErrorInfo();
            mailer.addErrMsgList(errMsgList);
            String rtnCd = mailer.sendMail(glblCmpyCd, NSAB019001.class.getSimpleName());
            if (ZYPCommonFunc.hasValue(rtnCd)) {
                S21InfoLogOutput.println(rtnCd);
            }
        }
    }

    private void cratErrLog() {
        if (hasMsg()) {
            int cnt = S21FastTBLAccessor.insert(errTMsgList.toArray(new SVC_BAT_ERR_LOGTMsg[errTMsgList.size()]));
            if (errTMsgList.size() != cnt) {
                S21InfoLogOutput.println(NSAM0032E, new String[] {"SVC_BAT_ERR_LOG" });
            }
        }
    }
}
