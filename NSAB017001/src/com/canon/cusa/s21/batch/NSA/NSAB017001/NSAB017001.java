/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB017001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;
import business.db.SVC_ADCV_BY_SER_SELTMsg;
import business.db.SVC_BAT_ERR_LOGTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SendMailForErrorInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * ADCV by Serial Selection.
 * <p>
 * Divide ADCV calculation target machines into chunks by service
 * machine master primary key for parallel processing.
 * </p>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/01   CUSA            SRAA            Create          N/A
 *</pre>
 */
public class NSAB017001 extends S21BatchMain {

    /**
     * Failed to insert "@".
     */
    public static final String NSAM0032E = "NSAM0032E";

    /**
     * System Error : @
     */
    private static final String NSAM0219E = "NSAM0219E";

    /**
     * Service Machine Master PK [@] cannot be inserted.
     */
    private static final String NSAM0349E = "NSAM0349E";

    /**
     * Client for prepared statement
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
     * Parallel process total task count
     */
    private BigDecimal parProcTotTaskCnt = null;

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
     * main
     * @param args
     */
    public static void main(String[] args) {
        new NSAB017001().executeBatch(NSAB017001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        ssmLlcClnt = S21SsmLowLevelCodingClient.getClient(NSAB017001.class);
        termCd = TERM_CD.NORMAL_END;
        glblCmpyCd = getGlobalCompanyCode();
        parProcTotTaskCnt = ZYPCodeDataUtil.getNumConstValue("NSAB0180_PAR_PROC_TOT_TASK_CNT", glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(parProcTotTaskCnt)) {
            parProcTotTaskCnt = BigDecimal.ONE;
        }
        errMsgList = new ArrayList<String>();
        errTMsgList = new ArrayList<EZDTMsg>();
        sysTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
    }

    @Override
    protected void mainRoutine() {

        delTempTbl();
        if (hasMsg()) {
            termCd = TERM_CD.ABNORMAL_END;
            return;
        }

        commit();

        divAdcvCalcTrgt();

        cratErrLog();
    }

    @Override
    protected void termRoutine() {
        sendEmail();
        setTermState(termCd, normRecCnt, errRecCnt);
    }

    private void delTempTbl() {
        SVC_ADCV_BY_SER_SELTMsg tMsg = new SVC_ADCV_BY_SER_SELTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        S21FastTBLAccessor.removeByPartialValue(tMsg, new String[] {"glblCmpyCd" });
        String rtnCd = tMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd) && !S21FastTBLAccessor.RTNCD_NOT_FOUND.equals(rtnCd)) {
            addMsg(null, null, NSAM0219E, "Work Table Deletion cannot be processed.");
        }
    }

    private void divAdcvCalcTrgt() {
        int normRecCnt = 0;
        int errRecCnt = 0;
        PreparedStatement prepStmt = null;
        ResultSet rsltSet = null;
        try {
            prepStmt = getPrepStmt();
            rsltSet = prepStmt.executeQuery();
            while (rsltSet.next()) {
                BigDecimal svcMachMstrPk = rsltSet.getBigDecimal("SVC_MACH_MSTR_PK");
                String serNum = rsltSet.getString("SER_NUM");
                BigDecimal parProcTaskNum = rsltSet.getBigDecimal("PAR_PROC_TASK_NUM");

                SVC_ADCV_BY_SER_SELTMsg tMsg = new SVC_ADCV_BY_SER_SELTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
                ZYPEZDItemValueSetter.setValue(tMsg.serAdcvSelGrpNum, parProcTaskNum);
                ZYPEZDItemValueSetter.setValue(tMsg.serNum, serNum);
                S21FastTBLAccessor.insert(tMsg);
                String rtnCd = tMsg.getReturnCode();
                if (S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    normRecCnt++;
                    commit();
                } else {
                    addMsg(svcMachMstrPk, serNum, NSAM0349E, svcMachMstrPk.toPlainString());
                    errRecCnt++;
                    rollback();
                }
            }
            this.normRecCnt = normRecCnt;
            this.errRecCnt = errRecCnt;
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prepStmt, rsltSet);
        }
    }

    private PreparedStatement getPrepStmt() throws SQLException {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setFetchSize(1000);
        execPrm.setMaxRows(0);
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("parProcTotTaskCnt", parProcTotTaskCnt);
        // TODO
        // prm.put("svcMachMstrStsCd", SVC_MACH_MSTR_STS.INSTALLED);
        // TODO
        // prm.put("dsContrDtlStsCd", DS_CONTR_DTL_STS.ACTIVE);
        // TODO
        // prm.put("dsContrStsCd", DS_CONTR_STS.EFFECTIVE);
        return ssmLlcClnt.createPreparedStatement("divAdcvCalcTrgt", prm, execPrm);
    }

    private void addMsg(BigDecimal svcMachMstrPk, String serNum, String errCd, String... prm) {
        // Message
        String errMsg = S21MessageFunc.clspGetMessage(errCd, prm);
        errMsgList.add(errMsg);

        // Log
        SVC_BAT_ERR_LOGTMsg tMsg = new SVC_BAT_ERR_LOGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_BAT_ERR_LOG_SQ"));
        ZYPEZDItemValueSetter.setValue(tMsg.bizAppId, "NSAB0170");
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrLogTs, sysTs);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNm_01, "SVC_MACH_MSTR_PK");
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNum_01, numToStr(svcMachMstrPk));
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNm_02, "SER_NUM");
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNum_02, serNum);
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
            String rtnCd = mailer.sendMail(glblCmpyCd, NSAB017001.class.getSimpleName());
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

    private static String numToStr(BigDecimal num) {
        if (ZYPCommonFunc.hasValue(num)) {
            return num.toPlainString();
        } else {
            return null;
        }
    }

}
