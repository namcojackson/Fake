/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB021001;

import static com.canon.cusa.s21.batch.NSA.NSAB021001.constant.NSAB021001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.DS_CONTRTMsg;
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
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * <pre>
 * Meter Read Validation Status Update Batch.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/12/2015   Hitachi         T.Harada        Create
 * 02/23/2016   Hitachi         K.Kishimoto     Update          QC#3687
 * 03/04/2016   Hitachi         K.Kishimoto     Update          QC#3687
 * 06/30/2016   Hitachi         Y.Tsuchimoto    Update          QC#10692
 * 2017/06/29   Hitachi         K.Kitachi       Update          QC#18436
 * 2017/08/04   Hitachi         A.Kohinata      Update          QC#18799
 * 2017/08/23   Hitachi         M.Kidokoro      Update          QC#20079
 * 2023/05/09   Hitachi         K.Kitachi       Update          QC#61469
 * </pre>
 */
public class NSAB021001 extends S21BatchMain {

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

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB021001().executeBatch(NSAB021001.class.getSimpleName());
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

        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount = 0;

    }

    @Override
    protected void mainRoutine() {
        updateMeterHoldFlag();

    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            // START 2016/06/30 [QC#10692, MOD]
            this.termCd = TERM_CD.WARNING_END;
            // END   2016/06/30 [QC#10692, MOD]
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    private void updateMeterHoldFlag() {
        S21InfoLogOutput.println("updateMeterHoldFlag Method Start");

        // START 2017/08/23 M.Kidokoro [QC#20079, DEL]
//        List<DS_CONTRTMsg> dsContrTMsgList = new ArrayList<DS_CONTRTMsg>();
        // END 2017/08/23 M.Kidokoro [QC#20079, DEL]
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrTMsgList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
        List<DS_CONTR_PRC_EFFTMsg> dsContrPrcEffTMsgList = new ArrayList<DS_CONTR_PRC_EFFTMsg>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            int commitCount = 0;
            // Del Start 03/04/2016 <QC#3687>
//            Boolean exeFlg = false;
            // Del End 03/04/2016 <QC#3687>
            Boolean errFlg = false;
            BigDecimal dsContrPk = null;
            BigDecimal dsContrPkBef = null;

            Map<String, Object> param = setUpdateKeyListStmtParam();
            ps = this.ssmLLClient.createPreparedStatement("getUpdateKeyList", param, ssmExecParam);
            rs = ps.executeQuery();

            while (rs.next()) {

                dsContrPk = rs.getBigDecimal("DS_CONTR_PK");

                // Del Start 03/04/2016 <QC#3687>
//                if (commitCount == this.commitNumber) {
//                    exeFlg = true;
//                }
                // Del End   03/04/2016 <QC#3687>
                /** Execute Update */
                // Mod Start 03/04/2016 <QC#3687>
                if (dsContrPkBef != null && dsContrPk.compareTo(dsContrPkBef) != 0) {
                // Mod End  03/04/2016 <QC#3687>

                    // Mod Start 02/23/2016 <QC#3687>
                    // START 2017/08/23 M.Kidokoro [QC#20079, MOD]
//                    errFlg = updateDsContrsData(dsContrPkBef, dsContrTMsgList, dsContrDtlTMsgList, dsContrBllgMtrTMsgList, dsContrPrcEffTMsgList);
                    errFlg = updateDsContrsData(dsContrPkBef, dsContrDtlTMsgList, dsContrBllgMtrTMsgList, dsContrPrcEffTMsgList);
                    // END 2017/08/23 M.Kidokoro [QC#20079, MOD]
                    // Mod End   02/23/2016 <QC#3687>

                    /** Result Check */
                    if (!errFlg) {
                        this.normalCount += commitCount;
                        commit();
                    } else {
                        this.errorCount += commitCount;
                        rollback();
                    }

                    /** Initialize & Set Update Parameter */
                    // Del Start 03/04/2016 <QC#3687>
//                    exeFlg = false;
                    // Del End   03/04/2016 <QC#3687>
                    commitCount = 0;
                    errFlg = false;
                    // START 2017/08/23 M.Kidokoro [QC#20079, DEL]
//                    dsContrTMsgList = new ArrayList<DS_CONTRTMsg>();
                    // END 2017/08/23 M.Kidokoro [QC#20079, DEL]
                    dsContrDtlTMsgList = new ArrayList<DS_CONTR_DTLTMsg>();
                    dsContrBllgMtrTMsgList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
                    dsContrPrcEffTMsgList = new ArrayList<DS_CONTR_PRC_EFFTMsg>();

                }

                /** Set Update Parameter */
                // START 2017/08/23 M.Kidokoro [QC#20079, MOD]
//                if (rs.getString("UPTADE_TABLE").equals("DS_CONTR")) {
//                    dsContrTMsgList.add(setDsContrTMsg(rs));
//                } else if (rs.getString("UPTADE_TABLE").equals("DS_CONTR_DTL")) {
                if (rs.getString("UPTADE_TABLE").equals("DS_CONTR_DTL")) {
                // END 2017/08/23 M.Kidokoro [QC#20079, MOD]
                    dsContrDtlTMsgList.add(setDsContrDtlTMsg(rs));
                } else if (rs.getString("UPTADE_TABLE").equals("DS_CONTR_BLLG_MTR")) {
                    dsContrBllgMtrTMsgList.add(setDsContrBllgMtrTMsg(rs));
                } else if (rs.getString("UPTADE_TABLE").equals("DS_CONTR_PRC_EFF")) {
                    dsContrPrcEffTMsgList.add(setDsContrPrcEffTMsg(rs));
                }

                commitCount++;
                dsContrPkBef = dsContrPk;
            }

            if (commitCount > 0) {

                // Mod Start 02/23/2016 <QC#3687>
                // START 2017/08/23 M.Kidokoro [QC#20079, MOD]
//                errFlg = updateDsContrsData(dsContrPkBef, dsContrTMsgList, dsContrDtlTMsgList, dsContrBllgMtrTMsgList, dsContrPrcEffTMsgList);
                errFlg = updateDsContrsData(dsContrPkBef, dsContrDtlTMsgList, dsContrBllgMtrTMsgList, dsContrPrcEffTMsgList);
                // END 2017/08/23 M.Kidokoro [QC#20079, MOD]
                // Mod End   02/23/2016 <QC#3687>

                /** Result Check */
                if (!errFlg) {
                    this.normalCount += commitCount;
                    commit();
                } else {
                    this.errorCount += commitCount;
                    rollback();
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

        S21InfoLogOutput.println("update count is " + this.normalCount + ".");
        S21InfoLogOutput.println("error  count is " + this.errorCount + ".");
        S21InfoLogOutput.println("updateMeterHoldFlag Method End");

    }

    private Map<String, Object> setUpdateKeyListStmtParam() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);

        // del start 2017/08/04 QC#18799
//        Integer windowBefDays;
//        if (ZYPCodeDataUtil.getNumConstValue(BLLG_MTR_READ_WINDOW_BEF_DAYS, this.glblCmpyCd) == null) {
//            windowBefDays = 0;
//        } else {
//            windowBefDays = ZYPCodeDataUtil.getNumConstValue(BLLG_MTR_READ_WINDOW_BEF_DAYS, this.glblCmpyCd).intValue();
//        }
//        param.put("bllgSchdThruDt", ZYPDateUtil.addDays(this.salesDate, windowBefDays));
        // del end 2017/08/04 QC#18799

        // add start 2017/08/04 QC#18799
        param.put("salesDate", this.salesDate);
        param.put("dateFormat", DATE_FORMAT);
        param.put("months", BLLG_CYCLE_UOM.MONTHS);
        param.put("defBefDays", DEF_WINDOW_BEF_DAYS);
        // add end 2017/08/04 QC#18799
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

        param.put("skipRecovTpCd", SKIP_RECOV_TP.SKIP);
        param.put("usgChrgFlg", ZYPConstant.FLG_ON_Y);
        param.put("mtrEntryCpltFlg", ZYPConstant.FLG_OFF_N);

        List<String> dsContrDtlTpCdList = new ArrayList<String>();
        dsContrDtlTpCdList.add(DS_CONTR_DTL_TP.FLEET);
        dsContrDtlTpCdList.add(DS_CONTR_DTL_TP.AGGREGATE);
        param.put("dsContrDtlTpCdArray", dsContrDtlTpCdList);

        List<String> dsContrStsCdList = new ArrayList<String>();
        // START 2017/06/29 K.Kitachi [QC#18436, MOD]
//        dsContrStsCdList.add(DS_CONTR_STS.APPROVED);
        dsContrStsCdList.add(DS_CONTR_STS.EFFECTIVE);
        dsContrStsCdList.add(DS_CONTR_STS.EXPIRED);
        dsContrStsCdList.add(DS_CONTR_STS.TERMINATED);
        // END 2017/06/29 K.Kitachi [QC#18436, MOD]
        param.put("dsContrStsCdArray", dsContrStsCdList);

        param.put("mtrHldFlg", ZYPConstant.FLG_OFF_N);

        List<String> dsContrCtrlStsCdList = new ArrayList<String>();
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.ACTIVE);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.TERMINATED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.EXPIRED);
        param.put("dsContrCtrlStsCdArray", dsContrCtrlStsCdList);

        return param;
    }

    private DS_CONTRTMsg setDsContrTMsg(ResultSet rs) {
        DS_CONTRTMsg tmsg = new DS_CONTRTMsg();
        try {
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
            ZYPEZDItemValueSetter.setValue(tmsg.dsContrPk, rs.getBigDecimal("DS_CONTR_PK"));
            tmsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);

            ZYPEZDItemValueSetter.setValue(tmsg.mtrHldFlg, ZYPConstant.FLG_ON_Y);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return tmsg;
    }

    private DS_CONTR_DTLTMsg setDsContrDtlTMsg(ResultSet rs) {
        DS_CONTR_DTLTMsg tmsg = new DS_CONTR_DTLTMsg();
        try {
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
            ZYPEZDItemValueSetter.setValue(tmsg.dsContrDtlPk, rs.getBigDecimal("DS_CONTR_DTL_PK"));
            tmsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);

            ZYPEZDItemValueSetter.setValue(tmsg.mtrHldFlg, ZYPConstant.FLG_ON_Y);

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return tmsg;
    }

    private DS_CONTR_BLLG_MTRTMsg setDsContrBllgMtrTMsg(ResultSet rs) {
        DS_CONTR_BLLG_MTRTMsg tmsg = new DS_CONTR_BLLG_MTRTMsg();
        try {
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
            ZYPEZDItemValueSetter.setValue(tmsg.dsContrBllgMtrPk, rs.getBigDecimal("DS_CONTR_BLLG_MTR_PK"));
            tmsg = (DS_CONTR_BLLG_MTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);

            ZYPEZDItemValueSetter.setValue(tmsg.mtrHldFlg, ZYPConstant.FLG_ON_Y);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return tmsg;
    }

    private DS_CONTR_PRC_EFFTMsg setDsContrPrcEffTMsg(ResultSet rs) {
        DS_CONTR_PRC_EFFTMsg tmsg = new DS_CONTR_PRC_EFFTMsg();
        try {
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));

            ZYPEZDItemValueSetter.setValue(tmsg.dsContrPrcEffPk, rs.getBigDecimal("DS_CONTR_PRC_EFF_PK"));
            tmsg = (DS_CONTR_PRC_EFFTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);

            ZYPEZDItemValueSetter.setValue(tmsg.mtrHldFlg, ZYPConstant.FLG_ON_Y);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return tmsg;
    }

    // START 2017/08/23 M.Kidokoro [QC#20079, MOD]
//    private boolean updateDsContrsData(BigDecimal dsContrPk, List<DS_CONTRTMsg> dsContrTMsgList, List<DS_CONTR_DTLTMsg> dsContrDtlTMsgList, List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrTMsgList, List<DS_CONTR_PRC_EFFTMsg> dsContrPrcEffTMsgList) {
    private boolean updateDsContrsData(BigDecimal dsContrPk, List<DS_CONTR_DTLTMsg> dsContrDtlTMsgList, List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrTMsgList, List<DS_CONTR_PRC_EFFTMsg> dsContrPrcEffTMsgList) {
    // END 2017/08/23 M.Kidokoro [QC#20079, MOD]

        Boolean errFlg = false;
        int retCount = 0;

        // START 2017/08/23 M.Kidokoro [QC#20079, DEL]
//        if (dsContrTMsgList.size() > 0) {
//            DS_CONTRTMsg[] inMsgArray = new DS_CONTRTMsg[dsContrTMsgList.size()];
//            retCount = S21FastTBLAccessor.update(dsContrTMsgList.toArray(inMsgArray));
//            if (retCount != inMsgArray.length) {
//                errFlg = true;
//            }
//        }
//        // Add Start 02/23/2016 <QC#3687>
//        for (DS_CONTRTMsg dsContrTMsg : dsContrTMsgList) {
//            if (!callContractTrackingAPI(glblCmpyCd, dsContrPk, null, null, null, null, null, null)) {
//                errFlg = true;
//                break;
//            }
//        }
//        // Add End   02/23/2016 <QC#3687>
        // END 2017/08/23 M.Kidokoro [QC#20079, DEL]
        if (dsContrDtlTMsgList.size() > 0 && !errFlg) {
            DS_CONTR_DTLTMsg[] inMsgArray = new DS_CONTR_DTLTMsg[dsContrDtlTMsgList.size()];
            retCount = S21FastTBLAccessor.update(dsContrDtlTMsgList.toArray(inMsgArray));
            if (retCount != inMsgArray.length) {
                errFlg = true;
            }
        }
        // Add Start 02/23/2016 <QC#3687>
        for (DS_CONTR_DTLTMsg dsContrDtlTMsg : dsContrDtlTMsgList) {
            if (!callContractTrackingAPI(glblCmpyCd, dsContrPk, dsContrDtlTMsg.dsContrDtlPk.getValue(), null, null, null, null, null)) {
                errFlg = true;
                break;
            }
        }
        // Add End   02/23/2016 <QC#3687>
        if (dsContrBllgMtrTMsgList.size() > 0 && !errFlg) {
            DS_CONTR_BLLG_MTRTMsg[] inMsgArray = new DS_CONTR_BLLG_MTRTMsg[dsContrBllgMtrTMsgList.size()];
            retCount = S21FastTBLAccessor.update(dsContrBllgMtrTMsgList.toArray(inMsgArray));
            if (retCount != inMsgArray.length) {
                errFlg = true;
            }
        }
        // Add Start 02/23/2016 <QC#3687>
        for (DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg : dsContrBllgMtrTMsgList) {
            if (!callContractTrackingAPI(glblCmpyCd, dsContrPk, dsContrBllgMtrTMsg.dsContrDtlPk.getValue(), dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue(), null, null, null, null)) {
                errFlg = true;
                break;
            }
        }
        // Add End   02/23/2016 <QC#3687>
        if (dsContrPrcEffTMsgList.size() > 0 && !errFlg) {
            DS_CONTR_PRC_EFFTMsg[] inMsgArray = new DS_CONTR_PRC_EFFTMsg[dsContrPrcEffTMsgList.size()];
            retCount = S21FastTBLAccessor.update(dsContrPrcEffTMsgList.toArray(inMsgArray));
            if (retCount != inMsgArray.length) {
                errFlg = true;
            }
        }
        // Add Start 02/23/2016 <QC#3687>
        for (DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg : dsContrPrcEffTMsgList) {
            if (!callContractTrackingAPI(glblCmpyCd, dsContrPk, dsContrPrcEffTMsg.dsContrDtlPk.getValue(), dsContrPrcEffTMsg.dsContrBllgMtrPk.getValue(), dsContrPrcEffTMsg.dsContrPrcEffPk.getValue(), dsContrPrcEffTMsg.contrPrcEffFromDt
                    .getValue(), dsContrPrcEffTMsg.contrPrcEffThruDt.getValue(), dsContrPrcEffTMsg.baseChrgFlg.getValue())) {
                errFlg = true;
                break;
            }
        }
        // Add End   02/23/2016 <QC#3687>

        return errFlg;
    }

    // Add Start 02/23/2016 <QC#3687>
    public static boolean callContractTrackingAPI(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, BigDecimal dsContrPrcEffPk, String contrPrcEffFromDt, String contrPrcEffThruDt, String baseChrgFlg) {
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ContrTrkProcMode.METER_READ_VALIDATION.code);

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
