/**
 * <pre>
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB034001;

import static com.canon.cusa.s21.batch.NSA.NSAB034001.constant.NSAB034001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NSZC034001PMsg;
import business.parts.NSZC056001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC034001.NSZC034001;
import com.canon.cusa.s21.api.NSZ.NSZC056001.NSZC056001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Aggregate Recalculation Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/15   Hitachi         T.Kanasaka      Create          N/A
 * 2016/02/01   Hitachi         T.Kanasaka      Create          QC3219,QC3952
 * </pre>
 */
public class NSAB034001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Normal Count */
    private int normalCount;

    /** Error Count */
    private int errorCount;

    /** Sales Date */
    private String slsDt = null;

    /** Billing Meter Read Window Before Days */
    private BigDecimal befWindowDays = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    @Override
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, NSAB034001.class.getSimpleName());
        if (!hasValue(this.slsDt)) {
            throw new S21AbendException(NSAM0178E);
        }

        // Billing Meter Read Window Before Days
        this.befWindowDays = ZYPCodeDataUtil.getNumConstValue(CONST_KEY_BLLG_MTR_READ_WINDOW_BEF_DAYS, this.glblCmpyCd);
        if (!hasValue(this.befWindowDays)) {
            this.befWindowDays = BigDecimal.ZERO;
        }

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        recalc();
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * Main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB034001().executeBatch(NSAB034001.class.getSimpleName());

    }

    private void recalc() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = getExecParam();
        Map<String, Object> ssmParam = getSsmParam();

        try {
            boolean isErr = false;
            String prevDsContrNum = null;
            String prevBllgSchdThruDt = null;
            stmt = this.ssmLLClient.createPreparedStatement("getInputData", ssmParam, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                BigDecimal dsContrDtlPk = rs.getBigDecimal("DS_CONTR_DTL_PK");
                BigDecimal dsContrBllgMtrPk = rs.getBigDecimal("DS_CONTR_BLLG_MTR_PK");
                String nextBllgDt = rs.getString("NEXT_BLLG_DT");
                String dsContrNum = rs.getString("DS_CONTR_NUM");
                String bllgSchdThruDt = rs.getString("BLLG_SCHD_THRU_DT");

                if ((hasValue(prevDsContrNum) && !prevDsContrNum.equals(dsContrNum)) || (hasValue(prevBllgSchdThruDt) && !prevBllgSchdThruDt.equals(bllgSchdThruDt))) {
                    if (!isErr) {
                        if (!callAggCalcApi(prevDsContrNum, prevBllgSchdThruDt)) {
                            isErr = true;
                        }
                    }

                    if (isErr) {
                        this.errorCount++;
                        rollback();

                        if (prevDsContrNum.equals(dsContrNum)) {
                            isErr = true;
                        } else {
                            isErr = false;
                        }
                    } else {
                        this.normalCount++;
                        commit();
                    }
                }

                if (!isErr) {
                    if (!callBllgCalcApi(dsContrDtlPk, dsContrBllgMtrPk, nextBllgDt)) {
                        isErr = true;
                    }
                }

                prevDsContrNum = dsContrNum;
                prevBllgSchdThruDt = bllgSchdThruDt;
            }

            if (hasValue(prevDsContrNum)) {
                if (!isErr) {
                    if (!callAggCalcApi(prevDsContrNum, prevBllgSchdThruDt)) {
                        isErr = true;
                    }
                }

                if (isErr) {
                    this.errorCount++;
                    rollback();
                } else {
                    this.normalCount++;
                    commit();
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private S21SsmExecutionParameter getExecParam() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execParam;
    }

    private Map<String, Object> getSsmParam() {
        Map<String, Object> ssmParam = new HashMap<String, Object>(SIZE_4);
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.AGGREGATE);
        ssmParam.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        ssmParam.put("bllgSchdThruDt", ZYPDateUtil.addDays(this.slsDt, this.befWindowDays.intValue()));
        return ssmParam;
    }

    private boolean callBllgCalcApi(BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String nextBllgDt) {
        NSZC056001PMsg pMsg = new NSZC056001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NSZC0560_MODE_BILLING_STAGE);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.baseChrgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg.usgChrgFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.estFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.nextBllgDt, nextBllgDt);
        NSZC056001 api = new NSZC056001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            String msgTxt = null;
            for (S21ApiMessage msg : msgList) {
                msgTxt = S21MessageFunc.clspGetMessage(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                S21InfoLogOutput.println(msgTxt);
            }
            return false;
        }
        return true;
    }

    private boolean callAggCalcApi(String dsContrNum, String bllgDt) {
        NSZC034001PMsg pMsg = new NSZC034001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrNum, dsContrNum);
        ZYPEZDItemValueSetter.setValue(pMsg.bllgDt, bllgDt);
        NSZC034001 api = new NSZC034001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            String msgTxt = null;
            for (S21ApiMessage msg : msgList) {
                msgTxt = S21MessageFunc.clspGetMessage(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                S21InfoLogOutput.println(msgTxt);
            }
            return false;
        }
        return true;
    }
}
