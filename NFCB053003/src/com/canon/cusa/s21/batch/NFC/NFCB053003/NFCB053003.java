package com.canon.cusa.s21.batch.NFC.NFCB053003;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDFastTBLAccessor;
import business.db.AR_RCPT_RCV_INTFCTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_LOCK_BOX_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOCK_BOX_NTFY_STS;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * NFCB053003 Receive Err Data Status Change
 *
 * Date         Company       Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/07/26   Fujitsu      H.Ikeda          Create          N/A
 * 2019/08/21   Fujitsu      H.Ikeda          Update          QC#52114
 *
 */
public class NFCB053003 extends S21BatchMain  implements ZYPConstant, NFCB053003Constant{

    /** Termination Code */
    private TERM_CD termCd;

    /** GlobalCompanyCode */
    private String glblCmpyCd = "";

    /** SQL Access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    // START 2019/08/21 [QC#52114, DEL]
//    /** Common component for Database accessing */
//    private S21SsmBatchClient ssmBatchClient = null;
    // END   2019/08/21 [QC#52114, DEL]

    /** Batch Proc Date */
    private String batchProcDt = "";

    /** Processing Count */
    private int procCount = 0;

    /** normal Count */
    private int normalCnt = 0;

    /** err Count */
    private int errCnt = 0;

    @Override
    protected void initRoutine() {
        debugLog("initRoutine start");

        S21InfoLogOutput.println(NFCM0584I, PROGRAM_ID);

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // START 2019/08/21 [QC#52114, DEL]
//        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        // END   2019/08/21 [QC#52114, DEL]
        if (!getGlblCmpyCd()) {
            execAbendException(NFCM0501E, MSG_STR_GLBL_CMPY_CD);
        }

        this.batchProcDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);
        if (S21StringUtil.isEmpty(this.batchProcDt)) {
            execAbendException(NFCM0574E);
        }

        this.termCd = TERM_CD.NORMAL_END;

        debugLog("initRoutine end");
     }

    @Override
    protected void mainRoutine() {
        debugLog("mainRoutine start");

        execute();

        debugLog("mainRoutine end");
    }

    /**
     * Receipt Data Creation
     */
    private void execute() {
        debugLog("execute start");

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = getArLockBoxFileNm();
            rs = stmt.executeQuery();
            while (rs.next()) {
                String arLockBoxFileNum = rs.getString("AR_LOCK_BOX_FILE_NM");
                // START 2019/08/21 [QC#52114, MOD]
//                if (chkArLockBoxFileNmData(arLockBoxFileNum)) {
                // update AR_RCPT_RCV_INTFC
                updateArRcptRcvIntfc(arLockBoxFileNum);
//                }
                // END  2019/08/21 [QC#52114, MOD]
            }
        } catch (SQLException e) {
            this.errCnt = this.errCnt + 1;
            if (this.procCount == 0) {
                this.procCount = this.errCnt;
            } else {
                this.procCount = this.procCount - this.errCnt;
            }
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            debugLog("execute : closeResource");
        }

        debugLog("execute end");
    }

      // START 2019/08/21 [QC#52114, MOD]
//    /**
//     * Update AR_RCPT_RCV_INTFC(AR_LOCK_BOX_STS_CD = E)
//     * 
//     * @param arLockBoxFileNum String
//     */
//    private void updateArRcptRcvIntfc(String arLockBoxFileNum) {
//        AR_RCPT_RCV_INTFCTMsg inMsg = new AR_RCPT_RCV_INTFCTMsg();
//
//        inMsg.setConditionValue("arLockBoxFileNm01", arLockBoxFileNum);
//        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
//        inMsg.setSQLID("001");
//
//        AR_RCPT_RCV_INTFCTMsgArray tMsgArray = (AR_RCPT_RCV_INTFCTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);
//
//        List<AR_RCPT_RCV_INTFCTMsg> updTMsgList = new ArrayList<AR_RCPT_RCV_INTFCTMsg>();
//
//        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
//            AR_RCPT_RCV_INTFCTMsg updTMsg = tMsgArray.no(i);
//
//            updTMsg.arLockBoxStsCd.setValue(AR_LOCK_BOX_STS.ERROR);  // AR_LOCK_BOX_STS_CD
//            updTMsgList.add(updTMsg);
//
//            if (updTMsgList.size() >= BULK_CNT) {
//                EZDFastTBLAccessor.update(updTMsgList.toArray(new AR_RCPT_RCV_INTFCTMsg[updTMsgList.size()]));
//                updTMsgList.clear();
//            }
//        }
//        if (!updTMsgList.isEmpty()) {
//            EZDFastTBLAccessor.update(updTMsgList.toArray(new AR_RCPT_RCV_INTFCTMsg[updTMsgList.size()]));
//        }
//        this.normalCnt = this.normalCnt + 1;
//        this.procCount = this.normalCnt;
//    }
    /**
     * Update AR_RCPT_RCV_INTFC(AR_LOCK_BOX_STS_CD = E)
     * 
     * @param arLockBoxFileNum String
     * @throws SQLException 
     */
    private void updateArRcptRcvIntfc(String arLockBoxFileNum) throws SQLException {
        List<AR_RCPT_RCV_INTFCTMsg> updTMsgList = new ArrayList<AR_RCPT_RCV_INTFCTMsg>();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        stmt = getArRcptRcvIntfcPk(arLockBoxFileNum);
        rs = stmt.executeQuery();
        while (rs.next()) {
            String arRcptRcvIntfcPk = rs.getString("AR_RCPT_RCV_INTFC_PK");
            AR_RCPT_RCV_INTFCTMsg inMsg = new AR_RCPT_RCV_INTFCTMsg();

            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.arRcptRcvIntfcPk,new BigDecimal(arRcptRcvIntfcPk));

            AR_RCPT_RCV_INTFCTMsg updTMsg= (AR_RCPT_RCV_INTFCTMsg) S21ApiTBLAccessor.findByKey(inMsg);
            updTMsg.arLockBoxStsCd.setValue(AR_LOCK_BOX_STS.ERROR);  // AR_LOCK_BOX_STS_CD
            updTMsgList.add(updTMsg);

            if (updTMsgList.size() >= BULK_CNT) {
                EZDFastTBLAccessor.update(updTMsgList.toArray(new AR_RCPT_RCV_INTFCTMsg[updTMsgList.size()]));
                updTMsgList.clear();
                this.normalCnt = this.normalCnt + updTMsgList.size();
            }
        }
        if (!updTMsgList.isEmpty()) {
            EZDFastTBLAccessor.update(updTMsgList.toArray(new AR_RCPT_RCV_INTFCTMsg[updTMsgList.size()]));
            this.normalCnt = this.normalCnt + updTMsgList.size();
        }
        this.procCount = this.normalCnt;
    }
    // END 2019/08/21 [QC#52114, MOD]

    private PreparedStatement getArLockBoxFileNm() throws SQLException {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("arLockBoxStsCd", AR_LOCK_BOX_STS.ERROR);
        // START 2019/08/21 [QC#52114, ADD]
        queryParam.put("arRcptRcvWrkCratFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("lockBoxntfyStsCd", LOCK_BOX_NTFY_STS.SEND);
        // END   2019/08/21 [QC#52114, ADD]
        return this.ssmLLClient.createPreparedStatement("getArLockBoxFileNm", queryParam, execParam);
    }

    // START 2019/08/21 [QC#52114, DEL]
//    private boolean chkArLockBoxFileNmData(String arLockBoxFileNm) {
//
//        Map<String, Object> sqlParam = new HashMap<String, Object>();
//        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
//        sqlParam.put("arLockBoxStsCd", AR_LOCK_BOX_STS.ERROR);
//        sqlParam.put("arLockBoxFileNm", arLockBoxFileNm);
//
//        BigDecimal cnt =(BigDecimal) ssmBatchClient.queryObject("getCountData", sqlParam);
//        if (!ZYPCommonFunc.hasValue(cnt) || cnt.compareTo(BigDecimal.ZERO) == 0) {
//            return false;
//        }
//
//        return true;
//    }
    // END 2019/08/21 [QC#52114, DEL]

    @Override
    protected void termRoutine() {
        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(termCd, this.normalCnt, this.errCnt, this.procCount);

        S21InfoLogOutput.println("termRoutine Method End");
    }

    /**
     * Output Debug Log
     * @param logmsg String
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(DEBUG_MSG_LVL, logmsg, this);
    }

    /**
     * Main method that is called from batch
     * @param args parameter
     */
    public static void main(String[] args) {
        /* Initialization S21BatchMain */
        new NFCB053003().executeBatch(NFCB053003.class.getSimpleName());
    }

    /**
     * Get Global Company Code
     * @return boolean true:Normal false:Failure
     */
    private boolean getGlblCmpyCd() {
        debugLog("getGlblCmpyCd start");

        /* Get Global Company Code */
        this.glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        if (S21StringUtil.isEmpty(this.glblCmpyCd)) {
            debugLog("getGlblCmpyCd : isEmpty error");
            return false;
        }

        debugLog("getGlblCmpyCd end");
        return true;
    }

    /**
     * Execute AbendException
     * @param msgId String
     */
    private void execAbendException(String msgId) {
        setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
        throw new S21AbendException(msgId);
    }

    /**
     * Execute AbendException
     * @param msgId String
     * @param msgStr String[]
     */
    private void execAbendException(String msgId, String[] msgStr) {
        setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
        throw new S21AbendException(msgId, msgStr);
    }

    // START 2019/08/21 [QC#52114, ADD]
    private PreparedStatement getArRcptRcvIntfcPk(String fileName) throws SQLException {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("arLockBoxStsCd", AR_LOCK_BOX_STS.ERROR);
        queryParam.put("arLockBoxFileNm", fileName);
        queryParam.put("arRcptRcvWrkCratFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("lockBoxntfyStsCd", LOCK_BOX_NTFY_STS.SEND);

        return this.ssmLLClient.createPreparedStatement("getArRcptRcvIntfcPk", queryParam, execParam);
    }
    // END   2019/08/21 [QC#52114, ADD]
}
