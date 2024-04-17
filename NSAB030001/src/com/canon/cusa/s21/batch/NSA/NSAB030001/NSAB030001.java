/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB030001;

import static com.canon.cusa.s21.batch.NSA.NSAB030001.constant.NSAB030001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_WIN_DAYSTMsg;
import business.parts.NSZC077001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.api.NSZ.NSZC077001.NSZC077001;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_TP;
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

/**
 * <pre>
 * Meter Validation Status Revert Batch.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   Hitachi         T.Harada        Create
 * 01/13/2016   Hitachi         T.Harada        Update          CSA QC#3063
 * 2016/10/17   Hitachi         T.Kanasaka      Update          CSA QC#14849
 * 2017/06/27   Hitachi         K.Kojima        Update          QC#19530
 * 2017/08/07   Hitachi         A.Kohinata      Update          QC#18799
 * 2017/08/23   Hitachi         M.Kidokoro      Update          QC#20079
 * 2023/05/09   Hitachi         K.Kitachi       Update          QC#61469
 * </pre>
 */
public class NSAB030001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Commit Number */
    private int commitNumber;

    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    /** Sales Date */
    private String salesDate;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Execution Parameter */
    private S21SsmExecutionParameter ssmExecParam = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB030001().executeBatch(NSAB030001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSZM0589E, new String[] {MSG_TXT_GLBL_CMPY_CD });
        }
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            throw new S21AbendException(NSZM0589E, new String[] {MSG_TXT_SALES_DATE });
        }

        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmExecParam = new S21SsmExecutionParameter();
        ssmExecParam.setFetchSize(this.commitNumber);
        ssmExecParam.setMaxRows(0);
        ssmExecParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmExecParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmExecParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount = 0;

    }

    @Override
    protected void mainRoutine() {
        updateMeterHoldFlag();
        // START 2017/06/27 K.Kojima [QC#19530,ADD]
        // START 2017/08/23 M.Kidokoro [QC#20079, DEL]
//        updateMeterHoldFlagForHeader();
        // END 2017/08/23 M.Kidokoro [QC#20079, DEL]
        // END 2017/06/27 K.Kojima [QC#19530,ADD]
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    private void updateMeterHoldFlag() {
        S21InfoLogOutput.println("updateMeterHoldFlag Method Start");

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            int commitCount = 0;
            boolean commitFlg = false;
            BigDecimal dsContrPkBef = null;

            Map<String, Object> param = setDsContrUpdateKeyParam();
            ps = this.ssmLLClient.createPreparedStatement("getDsContrUpdateKeyList", param, ssmExecParam);
            rs = ps.executeQuery();

            while (rs.next()) {

                BigDecimal dsContrPrcEffPk = rs.getBigDecimal("DS_CONTR_PRC_EFF_PK");
                BigDecimal dsContrBllgMtrPk = rs.getBigDecimal("DS_CONTR_BLLG_MTR_PK");
                BigDecimal dsContrDtlPk = rs.getBigDecimal("DS_CONTR_DTL_PK");
                BigDecimal dsContrPk = rs.getBigDecimal("DS_CONTR_PK");

                if (commitCount == this.commitNumber) {
                    commitFlg = true;
                }
                if (commitFlg && dsContrPkBef != null && !dsContrPk.equals(dsContrPkBef)) {
                    commit();
                    // START 2017/06/27 K.Kojima [QC#19530,MOD]
                    // this.normalCount++;
                    this.normalCount += commitCount;
                    // END 2017/06/27 K.Kojima [QC#19530,MOD]
                    commitFlg = false;
                    commitCount = 0;
                }

                // CSA QC#3063 START
                if (dsContrPrcEffPk != null) {
                    updateDsContrPrcEff(dsContrPk, dsContrPrcEffPk);
                    if (isDsContrBllgMtr(dsContrBllgMtrPk)) {
                        updateDsContrBllgMtr(dsContrPk, dsContrBllgMtrPk);
                        if (isDsContrDtl(dsContrDtlPk)) {
                            updateDsContrDtl(dsContrDtlPk);
                            // START 2017/08/23 M.Kidokoro [QC#20079, DEL]
//                            if (isDsContr(dsContrPk)) {
//                                updateDsContr(dsContrPk);
//                            }
                            // END 2017/08/23 M.Kidokoro [QC#20079, DEL]
                        }
                    }
                } else {
                    updateDsContrBllgMtr(dsContrPk, dsContrBllgMtrPk);
                    if (isDsContrDtl(dsContrDtlPk)) {
                        updateDsContrDtl(dsContrDtlPk);
                        // START 2017/08/23 M.Kidokoro [QC#20079, DEL]
//                        if (isDsContr(dsContrPk)) {
//                            updateDsContr(dsContrPk);
//                        }
                        // END 2017/08/23 M.Kidokoro [QC#20079, DEL]
                    }
                }
                // CSA QC#3063 END

                commitCount++;
                dsContrPkBef = dsContrPk;

            }
            if (commitCount > 0) {
                commit();
                // START 2017/06/27 K.Kojima [QC#19530,MOD]
                // this.normalCount++;
                this.normalCount += commitCount;
                // END 2017/06/27 K.Kojima [QC#19530,MOD]
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

        // START 2017/06/27 K.Kojima [QC#19530,DEL]
        // S21InfoLogOutput.println("update count is " + this.normalCount + ".");
        // S21InfoLogOutput.println("error  count is " + this.errorCount + ".");
        // END 2017/06/27 K.Kojima [QC#19530,DEL]
        S21InfoLogOutput.println("updateMeterHoldFlag Method End");
    }

    // START 2017/06/27 K.Kojima [QC#19530,ADD]
    // START 2017/08/23 M.Kidokoro [QC#20079, DEL]
//    private void updateMeterHoldFlagForHeader() {
//        S21InfoLogOutput.println("updateMeterHoldFlagForHeader Method Start");
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            int commitCount = 0;
//            Map<String, Object> param = setDsContrUpdateKeyParamForHeader();
//            ps = this.ssmLLClient.createPreparedStatement("getDsContrUpdateKeyListForHeader", param, ssmExecParam);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                BigDecimal dsContrPk = rs.getBigDecimal("DS_CONTR_PK");
//                if (commitCount == this.commitNumber) {
//                    commit();
//                    this.normalCount += commitCount;
//                    commitCount = 0;
//                }
//                updateDsContr(dsContrPk);
//                commitCount++;
//            }
//            if (commitCount > 0) {
//                commit();
//                this.normalCount += commitCount;
//            }
//        } catch (SQLException e) {
//            sqlExceptionHandler(e);
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(ps, rs);
//        }
//        S21InfoLogOutput.println("update count is " + this.normalCount + ".");
//        S21InfoLogOutput.println("error  count is " + this.errorCount + ".");
//        S21InfoLogOutput.println("updateMeterHoldFlagForHeader Method End");
//    }

    // END 2017/08/23 M.Kidokoro [QC#20079, DEL]
    // END 2017/06/27 K.Kojima [QC#19530,ADD]

    private Map<String, Object> setDsContrUpdateKeyParam() {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", this.glblCmpyCd);
        List<String> dsContrCtrlStsCdList = new ArrayList<String>();
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.ACTIVE);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.TERMINATED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.EXPIRED);
        param.put("dsContrCtrlStsCdArray", dsContrCtrlStsCdList);
        param.put("usgChrgFlgY", ZYPConstant.FLG_ON_Y);
        param.put("mtrHldFlgY", ZYPConstant.FLG_ON_Y);

        // del start 2017/08/07 QC#18799
//        Integer windowBefDays;
//        if (ZYPCodeDataUtil.getNumConstValue(BLLG_MTR_READ_WINDOW_BEF_DAYS, this.glblCmpyCd) == null) {
//            windowBefDays = 0;
//        } else {
//            windowBefDays = ZYPCodeDataUtil.getNumConstValue(BLLG_MTR_READ_WINDOW_BEF_DAYS, this.glblCmpyCd).intValue();
//        }
//        param.put("bllgSchdThruDt", ZYPDateUtil.addDays(this.salesDate, windowBefDays));
        // del end 2017/08/07 QC#18799

        // add start 2017/08/07 QC#18799
        param.put("salesDate", this.salesDate);
        param.put("dateFormat", DATE_FORMAT);
        param.put("months", BLLG_CYCLE_UOM.MONTHS);
        param.put("defBefDays", DEF_WINDOW_BEF_DAYS);
        // add end 2017/08/07 QC#18799
        // START 2023/05/09 K.Kitachi [QC#61469, ADD]
        BigDecimal mtrReadWinMlyDaysAot = BigDecimal.ZERO;
        BigDecimal mtrReadWinOthDaysAot = BigDecimal.ZERO;
        DS_WIN_DAYSTMsg dsWinDaysTMsg = new DS_WIN_DAYSTMsg();
        ZYPEZDItemValueSetter.setValue(dsWinDaysTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsWinDaysTMsg.dsWinDaysTrgtPerTxt, "*");
        ZYPEZDItemValueSetter.setValue(dsWinDaysTMsg.svcLineBizCd, "*");
        dsWinDaysTMsg = (DS_WIN_DAYSTMsg) S21FastTBLAccessor.findByKey(dsWinDaysTMsg);
        if (dsWinDaysTMsg != null) {
            mtrReadWinMlyDaysAot = dsWinDaysTMsg.mtrReadWinMlyDaysAot.getValue();
            mtrReadWinOthDaysAot = dsWinDaysTMsg.mtrReadWinOthDaysAot.getValue();
        }
        param.put("mtrReadWinMlyDaysAot", mtrReadWinMlyDaysAot);
        param.put("mtrReadWinOthDaysAot", mtrReadWinOthDaysAot);
        // END 2023/05/09 K.Kitachi [QC#61469, ADD]

        param.put("mtrEntryCpltFlgN", ZYPConstant.FLG_OFF_N);
        // START 2016/10/17 T.Kanasaka [QC#14849, ADD]
        param.put("dsContrCatgCdFleet", DS_CONTR_CATG.FLEET);
        // END 2016/10/17 T.Kanasaka [QC#14849, ADD]

        return param;
    }

    // START 2017/06/27 K.Kojima [QC#19530,ADD]
    // START 2017/08/23 M.Kidokoro [QC#20079, DEL]
//    private Map<String, Object> setDsContrUpdateKeyParamForHeader() {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", this.glblCmpyCd);
//        param.put("mtrHldFlgY", ZYPConstant.FLG_ON_Y);
//        param.put("usgChrgFlgY", ZYPConstant.FLG_ON_Y);
//        List<String> dsContrCtrlStsCdList = new ArrayList<String>();
//        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.ACTIVE);
//        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.TERMINATED);
//        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.EXPIRED);
//        param.put("dsContrCtrlStsCdArray", dsContrCtrlStsCdList);
//        return param;
//    }
    // END 2017/08/23 M.Kidokoro [QC#20079, DEL]
    // END 2017/06/27 K.Kojima [QC#19530,ADD]

    private boolean isDsContrBllgMtr(BigDecimal dsContrBllgMtrPk) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        param.put("mtrHldFlgY", ZYPConstant.FLG_ON_Y);
        List<String> dsContrCtrlStsCdList = new ArrayList<String>();
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.ACTIVE);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.TERMINATED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.EXPIRED);
        param.put("dsContrCtrlStsCdArray", dsContrCtrlStsCdList);

        Map<String, Object> rs =  (Map<String, Object>) this.ssmBatchClient.queryObject("isDsContrBllgMtrPk", param);

        if (rs != null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isDsContrDtl(BigDecimal dsContrDtlPk) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("mtrHldFlgY", ZYPConstant.FLG_ON_Y);
        List<String> dsContrCtrlStsCdList = new ArrayList<String>();
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.ACTIVE);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.TERMINATED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.EXPIRED);
        param.put("dsContrCtrlStsCdArray", dsContrCtrlStsCdList);

        Map<String, Object> rs = null;
        rs = (Map<String, Object>) this.ssmBatchClient.queryObject("isDsContrDtlPk", param);

        if (rs != null) {
            return true;
        } else {
            return false;
        }
    }

    // START 2017/08/23 M.Kidokoro [QC#20079, DEL]
//    private boolean isDsContr(BigDecimal dsContrPk) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//
//        param.put("glblCmpyCd", this.glblCmpyCd);
//        param.put("dsContrPk", dsContrPk);
//        param.put("mtrHldFlgY", ZYPConstant.FLG_ON_Y);
//        List<String> dsContrCtrlStsCdList = new ArrayList<String>();
//        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.ACTIVE);
//        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.TERMINATED);
//        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.EXPIRED);
//        param.put("dsContrCtrlStsCdArray", dsContrCtrlStsCdList);
//
//        Map<String, Object> rs = null;
//        rs = (Map<String, Object>) this.ssmBatchClient.queryObject("isDsContrPk", param);
//
//        if (rs != null) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    // END 2017/08/23 M.Kidokoro [QC#20079, DEL]

    private void updateDsContrPrcEff(BigDecimal dsContrPk, BigDecimal dsContrPrcEffPk) {
        DS_CONTR_PRC_EFFTMsg tmsg = new DS_CONTR_PRC_EFFTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.dsContrPrcEffPk, dsContrPrcEffPk);
        tmsg = (DS_CONTR_PRC_EFFTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);

        ZYPEZDItemValueSetter.setValue(tmsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
        S21FastTBLAccessor.update(tmsg);
        // Add Start 02/23/2016 <QC#3687>
        callContractTrackingAPI(glblCmpyCd, dsContrPk, tmsg.dsContrDtlPk.getValue(), tmsg.dsContrBllgMtrPk.getValue(), tmsg.dsContrPrcEffPk.getValue(), tmsg.contrPrcEffFromDt.getValue(), tmsg.contrPrcEffThruDt.getValue(), tmsg.baseChrgFlg
                .getValue());
        // Add End   02/23/2016 <QC#3687>
    }

    private void updateDsContrBllgMtr(BigDecimal dsContrPk, BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_BLLG_MTRTMsg tmsg = new DS_CONTR_BLLG_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        tmsg = (DS_CONTR_BLLG_MTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);

        ZYPEZDItemValueSetter.setValue(tmsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
        S21FastTBLAccessor.update(tmsg);
        // Add Start 02/23/2016 <QC#3687>
        callContractTrackingAPI(glblCmpyCd, dsContrPk, tmsg.dsContrDtlPk.getValue(), tmsg.dsContrBllgMtrPk.getValue(), null, null, null, null);
        // Add End   02/23/2016 <QC#3687>
    }

    private void updateDsContrDtl(BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg tmsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.dsContrDtlPk, dsContrDtlPk);
        tmsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);

        ZYPEZDItemValueSetter.setValue(tmsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
        S21FastTBLAccessor.update(tmsg);
        // Add Start 02/23/2016 <QC#3687>
        callContractTrackingAPI(glblCmpyCd, tmsg.dsContrPk.getValue(), tmsg.dsContrDtlPk.getValue(), null, null, null, null, null);
        // Add End   02/23/2016 <QC#3687>
    }

    // START 2017/08/23 M.Kidokoro [QC#20079, DEL]
//    private void updateDsContr(BigDecimal dsContrPk) {
//        DS_CONTRTMsg tmsg = new DS_CONTRTMsg();
//        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(tmsg.dsContrPk, dsContrPk);
//        tmsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);
//
//        ZYPEZDItemValueSetter.setValue(tmsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
//        S21FastTBLAccessor.update(tmsg);
//        // Add Start 02/23/2016 <QC#3687>
//        callContractTrackingAPI(glblCmpyCd, tmsg.dsContrPk.getValue(), null, null, null, null, null, null);
//        // Add End   02/23/2016 <QC#3687>
//    }
    // END 2017/08/23 M.Kidokoro [QC#20079, DEL]

    // Add Start 02/23/2016 <QC#3687>
    public static boolean callContractTrackingAPI(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, BigDecimal dsContrPrcEffPk, String contrPrcEffFromDt, String contrPrcEffThruDt, String baseChrgFlg) {
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ContrTrkProcMode.METER_VALIDATION_STATUS_REVERT.code);

        if (ZYPCommonFunc.hasValue(dsContrPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.CONTRACT_HEADER);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrPk, dsContrPk);
        }

        if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dsContrDtlPk);
        }

        if (ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        }

        if (ZYPCommonFunc.hasValue(dsContrPrcEffPk)) {
            if (ZYPConstant.FLG_ON_Y.equals(baseChrgFlg)) {
                ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE_PE);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE_PE);
            }
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrPrcEffPk, dsContrPrcEffPk);
            ZYPEZDItemValueSetter.setValue(pMsg.contrPrcEffFromDt, contrPrcEffFromDt);
            ZYPEZDItemValueSetter.setValue(pMsg.contrPrcEffThruDt, contrPrcEffThruDt);
        }

        NSZC077001 api = new NSZC077001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            return false;
        }

        return true;
    }
    // Add End   02/23/2016 <QC#3687>
}
