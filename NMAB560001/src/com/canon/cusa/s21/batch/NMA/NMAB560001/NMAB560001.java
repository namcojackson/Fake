/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB560001;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_ACCT_TRX_INFOTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Open Transaction Check Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Fujitsu         K.Minamide      Create          N/A
 * 2016/04/14   SRAA            Y.Chen          Update          QC#6247
 * 2016/11/08   Fujitsu         T.Yoshida       Update          QC#15767-1(Add Bulk Process)
 * 2016/12/21   Fujitsu         T.Yoshida       Update          QC#16723
 * 2019/12/13   Fujitsu         M.Nakamura      Update          QC#54882
 * 2020/01/07   Fujitsu         C.Hara          Update          QC#54882-1
 * 2021/05/20   CITS            M.Furugoori     Update          QC#58743
 * 2022/07/28   CITS            A.Cullano       Update          QC#60173
 *</pre>
 */
public class NMAB560001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    // 2019/12/13 QC#54882 Del Start
//    /** One Year Past */
//    private String slsDtPast = null;
    // 2019/12/13 QC#54882 Del End

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** commitUnit */
    private int commitUnit = 0;

    /** Total Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** DEFAULT_COMMIT_UNIT */
    public static final int DEFAULT_COMMIT_UNIT = 1000;

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** Data insert failure. (table name is [@]) */
    public static final String MMAM0003E = "MMAM0003E";

    /** Data update failure. (table name is [@]) */
    public static final String MMAM0004E = "MMAM0004E";

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /**
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {
        new NMAB560001().executeBatch(NMAB560001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        this.glblCmpyCd = super.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        this.commitUnit = getCommitCount();
        if (this.commitUnit <= 0 || DEFAULT_COMMIT_UNIT < this.commitUnit) {
            this.commitUnit = DEFAULT_COMMIT_UNIT;
        }

        // 2019/12/13 QC#54882 Del Start
//        this.slsDtPast = getOneYearPast(ZYPDateUtil.getSalesDate());
        // 2019/12/13 QC#54882 Del End
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {

        Map<String, DS_ACCT_TRX_INFOTMsg> accountMap = new HashMap<String, DS_ACCT_TRX_INFOTMsg>();
        this.checkAccountNumber(accountMap);
        this.checkMachineTransaction(accountMap);
        this.checkContract(accountMap);
        this.checkServiceCallTransaction(accountMap);
        this.checkOrderTransaction(accountMap);
        this.checkOrderReturnTransaction(accountMap);
        this.checkScheduleAgreementTransaction(accountMap); // 2019/12/13 QC#54882 Add
        this.updateAccount(accountMap);
    }

    @Override
    protected void termRoutine() {

        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);
    }

    private void checkAccountNumber(Map<String, DS_ACCT_TRX_INFOTMsg> accountMap) {

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        Map<String, Object> ssmParamCheckAccountNumber = new HashMap<String, Object>();
        ssmParamCheckAccountNumber.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamCheckAccountNumber.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // 2019/12/13 QC#54882 Add Start
        ssmParamCheckAccountNumber.put("flgY", ZYPConstant.FLG_ON_Y);
        // 2019/12/13 QC#54882 Add End
        try {
            stmt = this.ssmLLClient.createPreparedStatement("checkAccountNumber", ssmParamCheckAccountNumber, getExecParam());
            rsSet = stmt.executeQuery();

            while (rsSet.next()) {
                String sellToCustCd = rsSet.getString("SELL_TO_CUST_CD");
                DS_ACCT_TRX_INFOTMsg dsACctTrxInfo = new DS_ACCT_TRX_INFOTMsg();
                ZYPEZDItemValueSetter.setValue(dsACctTrxInfo.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsACctTrxInfo.dsAcctNum, sellToCustCd);
                ZYPEZDItemValueSetter.setValue(dsACctTrxInfo.acctTrxExstFlg, ZYPConstant.FLG_OFF_N);
                accountMap.put(sellToCustCd, dsACctTrxInfo);
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    private void checkMachineTransaction(Map<String, DS_ACCT_TRX_INFOTMsg> accountMap) {

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        Map<String, Object> ssmParamCheckMachineTransaction = new HashMap<String, Object>();
        ssmParamCheckMachineTransaction.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamCheckMachineTransaction.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // 2019/12/13 QC#54882 Del Start
//        ssmParamCheckMachineTransaction.put("glblCmpyCd", this.glblCmpyCd);
//        ssmParamCheckMachineTransaction.put("slsDtPast", slsDtPast);
        // 2019/12/13 QC#54882 Del End

        // QC#16723 Mod Start
//      ssmParamCheckMachineTransaction.put("trmn", SVC_MACH_MSTR_STS.TERMINATED);
//      ssmParamCheckMachineTransaction.put("dup", SVC_MACH_MSTR_STS.DUPLICATE);
        ssmParamCheckMachineTransaction.put("w4i", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        ssmParamCheckMachineTransaction.put("instl", SVC_MACH_MSTR_STS.INSTALLED);
        ssmParamCheckMachineTransaction.put("w4r", SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
        ssmParamCheckMachineTransaction.put("dlrsv", SVC_MACH_MSTR_STS.DEALER_SERVICE);
        // QC#16723 Mod End
        // 2019/12/13 QC#54882 Add Start
        ssmParamCheckMachineTransaction.put("svcMachTpMachine", SVC_MACH_TP.MACHINE);
        ssmParamCheckMachineTransaction.put("maxEffThruDt", "99991231");
        // 2019/12/13 QC#54882 Add End
        ssmParamCheckMachineTransaction.put("locRoleTpShip", LOC_ROLE_TP.SHIP_TO); // 2020/01/07 QC#54882-1 Add

        try {
            stmt = this.ssmLLClient.createPreparedStatement("checkMachineTransaction", ssmParamCheckMachineTransaction, getExecParam());
            rsSet = stmt.executeQuery();
            String prevSellToCustCd = null;

            while (rsSet.next()) {
                String sellToCustCd = rsSet.getString("SELL_TO_CUST_CD");
                // 2019/12/13 QC#54882 Mod Start
//                if (!ZYPCommonFunc.hasValue(prevSellToCustCd) || !prevSellToCustCd.equals(sellToCustCd)) {
//                    DS_ACCT_TRX_INFOTMsg accountInfo = accountMap.get(sellToCustCd);
//                    ZYPEZDItemValueSetter.setValue(accountInfo.acctTrxExstFlg, ZYPConstant.FLG_ON_Y);
//                    ZYPEZDItemValueSetter.setValue(accountInfo.svcMachMstrTrxDt, rsSet.getString("EFF_THRU_DT"));
//                    ZYPEZDItemValueSetter.setValue(accountInfo.svcMachMstrTrxTxt, rsSet.getString("SVC_MACH_MSTR_PK"));
//                    accountMap.put(sellToCustCd, accountInfo);
//                }
                if (!ZYPCommonFunc.hasValue(prevSellToCustCd) || !prevSellToCustCd.equals(sellToCustCd)) {
                    DS_ACCT_TRX_INFOTMsg accountInfo = accountMap.get(sellToCustCd);
                    if (accountInfo == null) {
                        accountInfo = new DS_ACCT_TRX_INFOTMsg();
                        ZYPEZDItemValueSetter.setValue(accountInfo.glblCmpyCd, this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(accountInfo.dsAcctNum, sellToCustCd);
                    }
                    ZYPEZDItemValueSetter.setValue(accountInfo.acctTrxExstFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(accountInfo.svcMachMstrTrxDt, rsSet.getString("EFF_THRU_DT"));
                    ZYPEZDItemValueSetter.setValue(accountInfo.svcMachMstrTrxTxt, rsSet.getString("SVC_MACH_MSTR_PK"));
                    accountMap.put(sellToCustCd, accountInfo);
                }
                // 2019/12/13 QC#54882 Mod End
                prevSellToCustCd = sellToCustCd;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    private void checkContract(Map<String, DS_ACCT_TRX_INFOTMsg> accountMap) {

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        Map<String, Object> ssmParamCheckContract = new HashMap<String, Object>();
        ssmParamCheckContract.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamCheckContract.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // 2019/12/13 QC#54882 del Start
//        ssmParamCheckContract.put("avalFlg", ZYPConstant.FLG_ON_Y);
//        ssmParamCheckContract.put("slsDtPast", slsDtPast);
//
//        List<String> dsContrCtrlSts = new ArrayList<String>();
//        dsContrCtrlSts.add(DS_CONTR_CTRL_STS.TERMINATED);
//        dsContrCtrlSts.add(DS_CONTR_CTRL_STS.TERMINATED_HOLD);
//        dsContrCtrlSts.add(DS_CONTR_CTRL_STS.EXPIRED);
//        dsContrCtrlSts.add(DS_CONTR_CTRL_STS.EXPIRED_HOLD);
//        dsContrCtrlSts.add(DS_CONTR_CTRL_STS.CANCELLED);
//        dsContrCtrlSts.add(DS_CONTR_CTRL_STS.DRAFT);
//        ssmParamCheckContract.put("dsContrCtrlSts", dsContrCtrlSts);
        // 2019/12/13 QC#54882 del End
        // 2019/12/13 QC#54882 Add Start
        ssmParamCheckContract.put("locRoleTpShip", LOC_ROLE_TP.SHIP_TO);
        ssmParamCheckContract.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParamCheckContract.put("dsContrCtrlStsCancelled", DS_CONTR_CTRL_STS.CANCELLED);
        ssmParamCheckContract.put("dsContrCtrlStsDraft", DS_CONTR_CTRL_STS.DRAFT);
        ssmParamCheckContract.put("flgN", ZYPConstant.FLG_OFF_N);
        ssmParamCheckContract.put("dsContrCtrlStsDraft", DS_CONTR_CTRL_STS.DRAFT);
        // 2021/05/20 QC#58743 Add Start
        ssmParamCheckContract.put("dsContrCtrlStsTerminated", DS_CONTR_CTRL_STS.TERMINATED);
        // 2021/05/20 QC#58743 Add End
        ssmParamCheckContract.put("svcMachStsW4I", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        ssmParamCheckContract.put("svcMachStsInstl", SVC_MACH_MSTR_STS.INSTALLED);
        ssmParamCheckContract.put("svcMachStsW4R", SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
        ssmParamCheckContract.put("maxEffThruDt", "99991231");
        // 2019/12/13 QC#54882 Add End
        // START 2022/07/28 A.Cullano [QC#60173, ADD]
        ssmParamCheckContract.put("skipRecovTpSkip", SKIP_RECOV_TP.SKIP);
        // END 2022/07/28 A.Cullano [QC#60173, ADD]

        try {
            stmt = this.ssmLLClient.createPreparedStatement("checkContract", ssmParamCheckContract, getExecParam());
            rsSet = stmt.executeQuery();
            String prevSellToCustCd = null;

            while (rsSet.next()) {
                String sellToCustCd = rsSet.getString("SELL_TO_CUST_CD");
                // 2019/12/13 QC#54882 Mod Start
//                if (!ZYPCommonFunc.hasValue(prevSellToCustCd) || !prevSellToCustCd.equals(sellToCustCd)) {
//                    DS_ACCT_TRX_INFOTMsg accountInfo = accountMap.get(sellToCustCd);
//                    ZYPEZDItemValueSetter.setValue(accountInfo.acctTrxExstFlg, ZYPConstant.FLG_ON_Y);
//                    ZYPEZDItemValueSetter.setValue(accountInfo.dsContrTrxDt, rsSet.getString("CONTR_EFF_THRU_DT"));
//                    ZYPEZDItemValueSetter.setValue(accountInfo.dsContrTrxTxt, rsSet.getString("DS_CONTR_PK"));
//                    accountMap.put(sellToCustCd, accountInfo);
//                }

                if (!ZYPCommonFunc.hasValue(prevSellToCustCd) || !prevSellToCustCd.equals(sellToCustCd)) {
                    DS_ACCT_TRX_INFOTMsg accountInfo = accountMap.get(sellToCustCd);
                    if (accountInfo == null) {
                        accountInfo = new DS_ACCT_TRX_INFOTMsg();
                        ZYPEZDItemValueSetter.setValue(accountInfo.glblCmpyCd, this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(accountInfo.dsAcctNum, sellToCustCd);
                    }
                    ZYPEZDItemValueSetter.setValue(accountInfo.acctTrxExstFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(accountInfo.dsContrTrxDt, rsSet.getString("CONTR_EFF_THRU_DT"));
                    ZYPEZDItemValueSetter.setValue(accountInfo.dsContrTrxTxt, rsSet.getString("DS_CONTR_PK"));
                    accountMap.put(sellToCustCd, accountInfo);
                }
                // 2019/12/13 QC#54882 Mod End
                prevSellToCustCd = sellToCustCd;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    private void checkServiceCallTransaction(Map<String, DS_ACCT_TRX_INFOTMsg> accountMap) {

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        Map<String, Object> ssmParamCheckServiceCallTransaction = new HashMap<String, Object>();
        ssmParamCheckServiceCallTransaction.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamCheckServiceCallTransaction.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // 2019/12/13 QC#54882 Mod Start
//        ssmParamCheckServiceCallTransaction.put("slsDtPast", slsDtPast);

//        List<String> svcTaskSts = new ArrayList<String>();
//        svcTaskSts.add(SVC_TASK_STS.CLOSED);
//        svcTaskSts.add(SVC_TASK_STS.CANCELLED);
//        ssmParamCheckServiceCallTransaction.put("svcTaskSts", svcTaskSts);
        ssmParamCheckServiceCallTransaction.put("locRoleTpShip", LOC_ROLE_TP.SHIP_TO);
        ssmParamCheckServiceCallTransaction.put("svcTaskStsClosed", SVC_TASK_STS.CLOSED);
        ssmParamCheckServiceCallTransaction.put("svcTaskStsCancelled", SVC_TASK_STS.CANCELLED);
        ssmParamCheckServiceCallTransaction.put("maxEffThruDt", "99991231");
        // 2019/12/13 QC#54882 Mod End

        try {
            stmt = this.ssmLLClient.createPreparedStatement("checkServiceCallTransaction", ssmParamCheckServiceCallTransaction, getExecParam());
            rsSet = stmt.executeQuery();
            String prevSellToCustCd = null;

            while (rsSet.next()) {
                String sellToCustCd = rsSet.getString("SELL_TO_CUST_CD");
                // 2019/12/13 QC#54882 Mod Start
//                if (!ZYPCommonFunc.hasValue(prevSellToCustCd) || !prevSellToCustCd.equals(sellToCustCd)) {
//                    ZYPEZDItemValueSetter.setValue(accountInfo.acctTrxExstFlg, ZYPConstant.FLG_ON_Y);
//                    ZYPEZDItemValueSetter.setValue(accountInfo.fsrTrxDt, rsSet.getString("SVC_TASK_RCV_DT"));
//                    ZYPEZDItemValueSetter.setValue(accountInfo.fsrTrxTxt, rsSet.getString("SVC_TASK_NUM"));
//                    accountMap.put(sellToCustCd, accountInfo);
//                }
                if (!ZYPCommonFunc.hasValue(prevSellToCustCd) || !prevSellToCustCd.equals(sellToCustCd)) {
                    DS_ACCT_TRX_INFOTMsg accountInfo = accountMap.get(sellToCustCd);
                    if (accountInfo == null) {
                        accountInfo = new DS_ACCT_TRX_INFOTMsg();
                        ZYPEZDItemValueSetter.setValue(accountInfo.glblCmpyCd, this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(accountInfo.dsAcctNum, sellToCustCd);
                    }
                    ZYPEZDItemValueSetter.setValue(accountInfo.acctTrxExstFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(accountInfo.fsrTrxDt, rsSet.getString("SVC_TASK_RCV_DT"));
                    ZYPEZDItemValueSetter.setValue(accountInfo.fsrTrxTxt, rsSet.getString("SVC_TASK_NUM"));
                    accountMap.put(sellToCustCd, accountInfo);
                }
                // 2019/12/13 QC#54882 Mod End
                prevSellToCustCd = sellToCustCd;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    private void checkOrderTransaction(Map<String, DS_ACCT_TRX_INFOTMsg> accountMap) {

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        Map<String, Object> ssmParamCheckOrder = new HashMap<String, Object>();
        ssmParamCheckOrder.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamCheckOrder.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // 2019/12/13 QC#54882 Mod Start
//        ssmParamCheckOrder.put("slsDtPast", slsDtPast);
        ssmParamCheckOrder.put("locRoleTpShip", LOC_ROLE_TP.SHIP_TO);
        ssmParamCheckOrder.put("flgY", ZYPConstant.FLG_ON_Y);
        // 2019/12/13 QC#54882 Mod End

        try {
            stmt = this.ssmLLClient.createPreparedStatement("checkOrderTransaction", ssmParamCheckOrder, getExecParam());
            rsSet = stmt.executeQuery();
            String prevSellToCustCd = null;

            while (rsSet.next()) {
                String sellToCustCd = rsSet.getString("SELL_TO_CUST_CD");
                // 2019/12/13 QC#54882 Mod Start
//                if (!ZYPCommonFunc.hasValue(prevSellToCustCd) || !prevSellToCustCd.equals(sellToCustCd)) {
//                    DS_ACCT_TRX_INFOTMsg accountInfo = accountMap.get(sellToCustCd);
//                    ZYPEZDItemValueSetter.setValue(accountInfo.acctTrxExstFlg, ZYPConstant.FLG_ON_Y);
//                    ZYPEZDItemValueSetter.setValue(accountInfo.ordTrxDt, rsSet.getString("CPO_ORD_TS"));
//                    ZYPEZDItemValueSetter.setValue(accountInfo.ordTrxTxt, rsSet.getString("CPO_ORD_NUM"));
//                    accountMap.put(sellToCustCd, accountInfo);
//
//                }
                if (!ZYPCommonFunc.hasValue(prevSellToCustCd) || !prevSellToCustCd.equals(sellToCustCd)) {
                    DS_ACCT_TRX_INFOTMsg accountInfo = accountMap.get(sellToCustCd);
                    if (accountInfo == null) {
                        accountInfo = new DS_ACCT_TRX_INFOTMsg();
                        ZYPEZDItemValueSetter.setValue(accountInfo.glblCmpyCd, this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(accountInfo.dsAcctNum, sellToCustCd);
                    }
                    ZYPEZDItemValueSetter.setValue(accountInfo.acctTrxExstFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(accountInfo.ordTrxDt, rsSet.getString("CPO_ORD_TS"));
                    ZYPEZDItemValueSetter.setValue(accountInfo.ordTrxTxt, rsSet.getString("CPO_ORD_NUM"));
                    accountMap.put(sellToCustCd, accountInfo);

                }
                // 2019/12/13 QC#54882 Mod End
                prevSellToCustCd = sellToCustCd;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    private void checkOrderReturnTransaction(Map<String, DS_ACCT_TRX_INFOTMsg> accountMap) {

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        Map<String, Object> ssmParamCheckOrderReturn = new HashMap<String, Object>();
        ssmParamCheckOrderReturn.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamCheckOrderReturn.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // 2019/12/13 QC#54882 Mod Start
//        ssmParamCheckOrderReturn.put("slsDtPast", slsDtPast);
        ssmParamCheckOrderReturn.put("locRoleTpShip", LOC_ROLE_TP.SHIP_TO);
        ssmParamCheckOrderReturn.put("flgY", ZYPConstant.FLG_ON_Y);
        // 2019/12/13 QC#54882 Mod End

        try {
            stmt = this.ssmLLClient.createPreparedStatement("checkOrderReturnTransaction", ssmParamCheckOrderReturn, getExecParam());
            rsSet = stmt.executeQuery();
            String prevSellToCustCd = null;

            while (rsSet.next()) {
                String sellToCustCd = rsSet.getString("SELL_TO_CUST_CD");
                // 2019/12/13 QC#54882 Mod Start
//                if (!ZYPCommonFunc.hasValue(prevSellToCustCd) || !prevSellToCustCd.equals(sellToCustCd)) {
//                    DS_ACCT_TRX_INFOTMsg accountInfo = accountMap.get(sellToCustCd);
//                    ZYPEZDItemValueSetter.setValue(accountInfo.acctTrxExstFlg, ZYPConstant.FLG_ON_Y);
//                    ZYPEZDItemValueSetter.setValue(accountInfo.rtrnTrxDt, rsSet.getString("CPO_ORD_TS"));
//                    ZYPEZDItemValueSetter.setValue(accountInfo.rtrnTrxTxt, rsSet.getString("CPO_ORD_NUM"));
//                    accountMap.put(sellToCustCd, accountInfo);
//                }
                if (!ZYPCommonFunc.hasValue(prevSellToCustCd) || !prevSellToCustCd.equals(sellToCustCd)) {
                    DS_ACCT_TRX_INFOTMsg accountInfo = accountMap.get(sellToCustCd);
                    if (accountInfo == null) {
                        accountInfo = new DS_ACCT_TRX_INFOTMsg();
                        ZYPEZDItemValueSetter.setValue(accountInfo.glblCmpyCd, this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(accountInfo.dsAcctNum, sellToCustCd);
                    }
                    ZYPEZDItemValueSetter.setValue(accountInfo.acctTrxExstFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(accountInfo.rtrnTrxDt, rsSet.getString("CPO_ORD_TS"));
                    ZYPEZDItemValueSetter.setValue(accountInfo.rtrnTrxTxt, rsSet.getString("CPO_ORD_NUM"));
                    accountMap.put(sellToCustCd, accountInfo);
                }
                // 2019/12/13 QC#54882 Mod End
                prevSellToCustCd = sellToCustCd;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    // 2019/12/13 QC#54882 Add Start
    private void checkScheduleAgreementTransaction(Map<String, DS_ACCT_TRX_INFOTMsg> accountMap) {

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        Map<String, Object> ssmParamCheckScheduleAgreement = new HashMap<String, Object>();
        ssmParamCheckScheduleAgreement.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamCheckScheduleAgreement.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParamCheckScheduleAgreement.put("locRoleTpShip", LOC_ROLE_TP.SHIP_TO);
        ssmParamCheckScheduleAgreement.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParamCheckScheduleAgreement.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParamCheckScheduleAgreement.put("maxEffThruDt", "99991231");
        ssmParamCheckScheduleAgreement.put("flgN", ZYPConstant.FLG_OFF_N);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("checkScheduleAgreementTransaction", ssmParamCheckScheduleAgreement, getExecParam());
            rsSet = stmt.executeQuery();
            String prevSellToCustCd = null;

            while (rsSet.next()) {
                String sellToCustCd = rsSet.getString("SELL_TO_CUST_CD");
                if (!ZYPCommonFunc.hasValue(prevSellToCustCd) || !prevSellToCustCd.equals(sellToCustCd)) {
                    DS_ACCT_TRX_INFOTMsg accountInfo = accountMap.get(sellToCustCd);
                    if (accountInfo == null) {
                        accountInfo = new DS_ACCT_TRX_INFOTMsg();
                        ZYPEZDItemValueSetter.setValue(accountInfo.glblCmpyCd, this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(accountInfo.dsAcctNum, sellToCustCd);
                    }
                    ZYPEZDItemValueSetter.setValue(accountInfo.acctTrxExstFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(accountInfo.saTrxDt, rsSet.getString("SCHD_AGMT_CRAT_DT"));
                    ZYPEZDItemValueSetter.setValue(accountInfo.saTrxTxt, rsSet.getString("SCHD_AGMT_NUM"));
                    accountMap.put(sellToCustCd, accountInfo);
                }
                prevSellToCustCd = sellToCustCd;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }
    // 2019/12/13 QC#54882 Add End

    private void updateAccount(Map<String, DS_ACCT_TRX_INFOTMsg> accountMap) {

        List<DS_ACCT_TRX_INFOTMsg> insertList = new ArrayList<DS_ACCT_TRX_INFOTMsg>();
        List<DS_ACCT_TRX_INFOTMsg> updateList = new ArrayList<DS_ACCT_TRX_INFOTMsg>();

        for (String dsAcctNum : accountMap.keySet()) {
            DS_ACCT_TRX_INFOTMsg dsAcctTrxInfoMsg = accountMap.get(dsAcctNum);
            DS_ACCT_TRX_INFOTMsg currentMsg = new DS_ACCT_TRX_INFOTMsg();
            ZYPEZDItemValueSetter.setValue(currentMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(currentMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
            ZYPEZDItemValueSetter.setValue(currentMsg.dsAcctNum, dsAcctNum);
            currentMsg = (DS_ACCT_TRX_INFOTMsg) EZDTBLAccessor.findByKeyForUpdate(currentMsg);

            if (currentMsg != null) {
                if (!this.hasDifference(dsAcctTrxInfoMsg, currentMsg)) {
                    continue;
                }

                this.totalCount++;
                ZYPEZDItemValueSetter.setValue(currentMsg.acctTrxExstFlg, dsAcctTrxInfoMsg.acctTrxExstFlg);
                ZYPEZDItemValueSetter.setValue(currentMsg.svcMachMstrTrxDt, dsAcctTrxInfoMsg.svcMachMstrTrxDt);
                ZYPEZDItemValueSetter.setValue(currentMsg.svcMachMstrTrxTxt, dsAcctTrxInfoMsg.svcMachMstrTrxTxt);
                ZYPEZDItemValueSetter.setValue(currentMsg.dsContrTrxDt, dsAcctTrxInfoMsg.dsContrTrxDt);
                ZYPEZDItemValueSetter.setValue(currentMsg.dsContrTrxTxt, dsAcctTrxInfoMsg.dsContrTrxTxt);
                ZYPEZDItemValueSetter.setValue(currentMsg.fsrTrxDt, dsAcctTrxInfoMsg.fsrTrxDt);
                ZYPEZDItemValueSetter.setValue(currentMsg.fsrTrxTxt, dsAcctTrxInfoMsg.fsrTrxTxt);
                ZYPEZDItemValueSetter.setValue(currentMsg.ordTrxDt, dsAcctTrxInfoMsg.ordTrxDt);
                ZYPEZDItemValueSetter.setValue(currentMsg.ordTrxTxt, dsAcctTrxInfoMsg.ordTrxTxt);
                ZYPEZDItemValueSetter.setValue(currentMsg.rtrnTrxDt, dsAcctTrxInfoMsg.rtrnTrxDt);
                ZYPEZDItemValueSetter.setValue(currentMsg.rtrnTrxTxt, dsAcctTrxInfoMsg.rtrnTrxTxt);
                // 2019/12/13 QC#54882 Add Start
                ZYPEZDItemValueSetter.setValue(currentMsg.saTrxDt, dsAcctTrxInfoMsg.saTrxDt);
                ZYPEZDItemValueSetter.setValue(currentMsg.saTrxTxt, dsAcctTrxInfoMsg.saTrxTxt);
                // 2019/12/13 QC#54882 Add End
                updateList.add(currentMsg);
                
                if (updateList.size() == commitUnit) {
                    this.executeBulkUpd(updateList);
                    updateList.clear();
                }

            } else {
                this.totalCount++;
                insertList.add(dsAcctTrxInfoMsg);

                if (insertList.size() == commitUnit) {
                    this.executeBulkIns(insertList);
                    insertList.clear();
                }
            }
        }

        if (insertList.size() > 0) {
            this.executeBulkIns(insertList);
        }

        if (updateList.size() > 0) {
            this.executeBulkUpd(updateList);
        }
    }

    // 2019/12/13 QC#54882 Del Start
//    private String getOneYearPast(String yyyyMMdd) {
//
//        Calendar cal = Calendar.getInstance();
//        DateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
//        try {
//            cal.setTime(dateformat.parse(yyyyMMdd));
//        } catch (ParseException e) {
//            return null;
//        }
//        cal.add(Calendar.YEAR, -1);
//        return dateformat.format(cal.getTime());
//    }
    // 2019/12/13 QC#54882 Del End

    private boolean hasDifference(DS_ACCT_TRX_INFOTMsg updateMsg, DS_ACCT_TRX_INFOTMsg currentMsg) {

        if (updateMsg.acctTrxExstFlg.getValue().equals(currentMsg.acctTrxExstFlg.getValue())) {
            String updateSvcMachMstrTrxDt = nullToBlank(updateMsg.svcMachMstrTrxDt.getValue());
            String currentSvcMachMstrTrxDt = nullToBlank(currentMsg.svcMachMstrTrxDt.getValue());
            String updateSvcMachMstrTrxTxt = nullToBlank(updateMsg.svcMachMstrTrxTxt.getValue());
            String currentSvcMachMstrTrxTxt = nullToBlank(currentMsg.svcMachMstrTrxTxt.getValue());

            String updateDsContrTrxDt = nullToBlank(updateMsg.dsContrTrxDt.getValue());
            String currentDsContrTrxDt = nullToBlank(currentMsg.dsContrTrxDt.getValue());
            String updateDsContrTrxTxt = nullToBlank(updateMsg.dsContrTrxTxt.getValue());
            String currentDsContrTrxTxt = nullToBlank(currentMsg.dsContrTrxTxt.getValue());
            String updateFsrTrxDt = nullToBlank(updateMsg.fsrTrxDt.getValue());
            String currentFsrTrxDt = nullToBlank(currentMsg.fsrTrxDt.getValue());
            String updateFsrTrxTxt = nullToBlank(updateMsg.fsrTrxTxt.getValue());
            String currentFsrTrxTxtt = nullToBlank(currentMsg.fsrTrxTxt.getValue());

            String updateOrdTrxDt = nullToBlank(updateMsg.ordTrxDt.getValue());
            String currentOrdTrxDt = nullToBlank(currentMsg.ordTrxDt.getValue());
            String updateOrdTrxTxt = nullToBlank(updateMsg.ordTrxTxt.getValue());
            String currentOrdTrxTxt = nullToBlank(currentMsg.ordTrxTxt.getValue());
            String updateRtrnTrxDt = nullToBlank(updateMsg.rtrnTrxDt.getValue());
            String currentRtrnTrxDt = nullToBlank(currentMsg.rtrnTrxDt.getValue());
            String updateRtrnTrxTxt = nullToBlank(updateMsg.rtrnTrxTxt.getValue());
            String currentRtrnTrxTxt = nullToBlank(currentMsg.rtrnTrxTxt.getValue());
            // 2019/12/13 QC#54882 Add Start
            String updateSaTrxDt = nullToBlank(updateMsg.saTrxDt.getValue());
            String currentSaTrxDt = nullToBlank(currentMsg.saTrxDt.getValue());
            String updateSaTrxTxt = nullToBlank(updateMsg.saTrxTxt.getValue());
            String currentSaTrxTxt = nullToBlank(currentMsg.saTrxTxt.getValue());
            // 2019/12/13 QC#54882 Add End

            // 2019/12/13 QC#54882 Mod Start
//            if (updateSvcMachMstrTrxDt.equals(currentSvcMachMstrTrxDt) && updateSvcMachMstrTrxTxt.equals(currentSvcMachMstrTrxTxt) && updateDsContrTrxDt.equals(currentDsContrTrxDt) && updateDsContrTrxTxt.equals(currentDsContrTrxTxt)
//                    && updateFsrTrxDt.equals(currentFsrTrxDt) && updateFsrTrxTxt.equals(currentFsrTrxTxtt) && updateOrdTrxDt.equals(currentOrdTrxDt) && updateOrdTrxTxt.equals(currentOrdTrxTxt) && updateRtrnTrxDt.equals(currentRtrnTrxDt)
//                    && updateRtrnTrxTxt.equals(currentRtrnTrxTxt)) {
//                return false;
//            }
            if (updateSvcMachMstrTrxDt.equals(currentSvcMachMstrTrxDt) && updateSvcMachMstrTrxTxt.equals(currentSvcMachMstrTrxTxt)
                    && updateDsContrTrxDt.equals(currentDsContrTrxDt) && updateDsContrTrxTxt.equals(currentDsContrTrxTxt)
                    && updateFsrTrxDt.equals(currentFsrTrxDt) && updateFsrTrxTxt.equals(currentFsrTrxTxtt)
                    && updateOrdTrxDt.equals(currentOrdTrxDt) && updateOrdTrxTxt.equals(currentOrdTrxTxt)
                    && updateRtrnTrxDt.equals(currentRtrnTrxDt) && updateRtrnTrxTxt.equals(currentRtrnTrxTxt)
                    && updateSaTrxDt.equals(currentSaTrxDt) && updateSaTrxTxt.equals(currentSaTrxTxt)
                    ) {
                return false;
            }
            // 2019/12/13 QC#54882 Mod End

        }

        return true;
    }

    private String nullToBlank(String str) {

        if (str == null) {
            return "";
        }
        return str;
    }

    private S21SsmExecutionParameter getExecParam() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execParam;
    }

    private void executeBulkIns(List<DS_ACCT_TRX_INFOTMsg> insertList) {

        int insCount = S21FastTBLAccessor.insert(insertList.toArray(new DS_ACCT_TRX_INFOTMsg[0]));
        if (insCount != insertList.size()) {
            this.totalErrCount += insCount;
            super.rollback();
            throw new S21AbendException(MMAM0003E, new String[] {"DS_ACCT_TRX_INFO" });
        }

        this.totalNmlCount += insCount;
        super.commit();
    }

    private void executeBulkUpd(List<DS_ACCT_TRX_INFOTMsg> updateList) {

        int updCount = S21FastTBLAccessor.update(updateList.toArray(new DS_ACCT_TRX_INFOTMsg[0]));
        if (updCount != updateList.size()) {
            this.totalErrCount += updCount;
            super.rollback();
            throw new S21AbendException(MMAM0004E, new String[] {"DS_ACCT_TRX_INFO" });
        }

        this.totalNmlCount += updCount;
        super.commit();
    }
}
