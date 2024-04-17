/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB713001;

import static com.canon.cusa.s21.batch.NMA.NMAB713001.Constant.NMAB713001Constant.DATA_UPDATED;
import static com.canon.cusa.s21.batch.NMA.NMAB713001.Constant.NMAB713001Constant.DEFAULT_COMMIT_UNIT;
import static com.canon.cusa.s21.batch.NMA.NMAB713001.Constant.NMAB713001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB713001.Constant.NMAB713001Constant.INV_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB713001.Constant.NMAB713001Constant.MMAM0004E;
import static com.canon.cusa.s21.batch.NMA.NMAB713001.Constant.NMAB713001Constant.PRC_CONTR_MAIN_MACH;
import static com.canon.cusa.s21.batch.NMA.NMAB713001.Constant.NMAB713001Constant.ZZBM0009I;
import static com.canon.cusa.s21.batch.NMA.NMAB713001.Constant.NMAB713001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NMA.NMAB713001.Constant.NMAB713001Constant.ZZZM9026E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.GLBL_CMPYTMsg;
import business.db.PRC_CONTRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Current Contract Summary Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   Fujitsu         K.Minamide      Create          N/A
 *</pre>
 */
public class NMAB713001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** commitUnit */
    private int commitUnit = 0;

    /** process date time */
    private String procDt = null;

    /** CUR_CONTR_SMRY_CALC_BASE_DAY */
    private String curContrSmryCalcBaseDay = null;

    /** CUR_CONTR_SMRY_EXCL_WH */
    private String curContrSmryExclWh = null;

    /** calc base date time */
    private String calcBaseDt = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Total Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    @Override
    protected void initRoutine() {
        this.glblCmpyCd = super.getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);

        if (glblCmpyTMsg == null) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Global Company Code" });
        }

        this.commitUnit = getCommitCount();
        if (this.commitUnit <= 0 || DEFAULT_COMMIT_UNIT < this.commitUnit) {
            this.commitUnit = DEFAULT_COMMIT_UNIT;
        }

        this.procDt = ZYPDateUtil.getBatProcDate();

        this.curContrSmryCalcBaseDay = ZYPCodeDataUtil.getVarCharConstValue("CUR_CONTR_SMRY_CALC_BASE_DAY", this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.curContrSmryCalcBaseDay)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"CUR_CONTR_SMRY_CALC_BASE_DAY" });
        }

        this.calcBaseDt = ZYPDateUtil.addDays(this.procDt, new BigDecimal(this.curContrSmryCalcBaseDay).negate().intValue());

        this.curContrSmryExclWh = ZYPCodeDataUtil.getVarCharConstValue("CUR_CONTR_SMRY_EXCL_WH", this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.curContrSmryExclWh)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"CUR_CONTR_SMRY_EXCL_WH" });
        }

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> ssmParamGetPrcContr = new HashMap<String, Object>();
        ssmParamGetPrcContr.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamGetPrcContr.put("calcBaseDt", this.calcBaseDt);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getPrcContr", ssmParamGetPrcContr, execParam);
            rsSet = stmt.executeQuery();

            List<PRC_CONTRTMsg> updDataList = new ArrayList<PRC_CONTRTMsg>(this.commitUnit);

            while (rsSet.next()) {

                PRC_CONTRTMsg prcContrTMsg = new PRC_CONTRTMsg();
                ZYPEZDItemValueSetter.setValue(prcContrTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(prcContrTMsg.prcContrPk, rsSet.getBigDecimal("PRC_CONTR_PK"));

                prcContrTMsg = (PRC_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(prcContrTMsg);

                if (prcContrTMsg != null) {
                    BigDecimal prevEquipRevSumAmt = prcContrTMsg.equipRevSumAmt.getValue();

                    Map<String, Object> ssmParamEquipRevSumAmt = new HashMap<String, Object>();
                    ssmParamEquipRevSumAmt.put("invTpCd", INV_TP.CREDIT_MEMO);
                    ssmParamEquipRevSumAmt.put("glblCmpyCd", this.glblCmpyCd);
                    ssmParamEquipRevSumAmt.put("effFromDt", prcContrTMsg.effFromDt.getValue());
                    ssmParamEquipRevSumAmt.put("effThruDt", prcContrTMsg.effThruDt.getValue());
                    ssmParamEquipRevSumAmt.put("trxCd", TRX.SALES);
                    ssmParamEquipRevSumAmt.put("ordCatgCtxTpCd", "CUR_CONTR_SMRY_AMT");
                    ssmParamEquipRevSumAmt.put("prcContrNum", prcContrTMsg.prcContrNum.getValue());

                    BigDecimal equipRevSumAmt = (BigDecimal) this.ssmBatchClient.queryObject("getEquipmentRevenueAchivedContractStartToDate", ssmParamEquipRevSumAmt);

                    BigDecimal prevMainUnitSumCnt = prcContrTMsg.mainUnitSumCnt.getValue();

                    Map<String, Object> ssmParamMainUnitSumCnt = new HashMap<String, Object>();
                    ssmParamMainUnitSumCnt.put("invTpCd", INV_TP.INVOICE);
                    ssmParamMainUnitSumCnt.put("glblCmpyCd", this.glblCmpyCd);
                    ssmParamMainUnitSumCnt.put("effFromDt", prcContrTMsg.effFromDt.getValue());
                    ssmParamMainUnitSumCnt.put("effThruDt", prcContrTMsg.effThruDt.getValue());
                    ssmParamMainUnitSumCnt.put("curContrSmryExclWhList", this.curContrSmryExclWh.split(","));

                    ssmParamMainUnitSumCnt.put("invLineSubNum", INV_LINE_SUB_NUM);
                    ssmParamMainUnitSumCnt.put("mdseTpCtxTpCd", PRC_CONTR_MAIN_MACH);
                    ssmParamMainUnitSumCnt.put("curContrSmryQty", "CUR_CONTR_SMRY_QTY");
                    ssmParamMainUnitSumCnt.put("prcContrNum", prcContrTMsg.prcContrNum.getValue());

                    BigDecimal mainUnitSumCnt = (BigDecimal) this.ssmBatchClient.queryObject("getNumberOfMainUnitsSoldToDate", ssmParamMainUnitSumCnt);

                    if (this.isNeedToUpdate(prevEquipRevSumAmt, equipRevSumAmt, prevMainUnitSumCnt, mainUnitSumCnt)) {

                        ZYPEZDItemValueSetter.setValue(prcContrTMsg.equipRevSumAmt, equipRevSumAmt);
                        ZYPEZDItemValueSetter.setValue(prcContrTMsg.mainUnitSumCnt, mainUnitSumCnt);
                        updDataList.add(prcContrTMsg);
                        this.totalCount++;
                    }
                }

                if (updDataList.size() >= this.commitUnit) {
                    this.executeBulkUpd(updDataList);
                    updDataList.clear();
                    super.commit();
                }
            }

            // not reach the max commit count in the repeat
            if (updDataList.size() > 0) {
                this.executeBulkUpd(updDataList);
                super.commit();
            }
            S21InfoLogOutput.printlnv(ZZBM0009I, "PRC_CONTR", DATA_UPDATED, this.totalNmlCount);

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    /**
     * execute BulkUpdate to InterfaceTbl and commit
     * @param updDataList
     */
    private void executeBulkUpd(List<PRC_CONTRTMsg> updDataList) {

        // BulkUpdate
        int updCount = S21FastTBLAccessor.update(updDataList.toArray(new PRC_CONTRTMsg[0]));
        int updDataListSize = updDataList.size();

        if (updCount != updDataListSize) {
            this.totalErrCount += updDataListSize - updCount;
            super.rollback();
            throw new S21AbendException(MMAM0004E, new String[] {"PRC_CONTR" });
        }

        this.totalNmlCount += updDataListSize;
    }

    /**
     * @param a1
     * @param a2
     * @param b1
     * @param b2
     * @return
     */
    private boolean isNeedToUpdate(BigDecimal a1, BigDecimal a2, BigDecimal b1, BigDecimal b2) {

        return (a1 == null && a2 != null) || (a1 != null && !a1.equals(a2)) || (b1 == null && b2 != null) || (b1 != null && !b1.equals(b2));

    }

    @Override
    protected void termRoutine() {
        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);
    }

    /**
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NMAB713001().executeBatch(NMAB713001.class.getSimpleName());
    }

}
