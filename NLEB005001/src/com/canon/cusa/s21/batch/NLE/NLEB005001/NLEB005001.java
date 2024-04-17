/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLE.NLEB005001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.ACCT_MTH_CTRLTMsg;
import business.db.GLBL_CMPYTMsg;
import business.parts.NLZC305001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC305001.NLZC305001;
import com.canon.cusa.s21.api.NLZ.NLZC305001.constant.NLZC305001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_MTH_CTRL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DEPC_LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_MODE;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Monthly Asset Depreciation Calculation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 06/18/2013   Fujitsu         Y.Taoka         Create          R-WH002 Serial Number Asset Management
 * 08/14/2013   CSAI            Y.Imazu         Update          Defect#1509
 * 01/21/2016   Fujitsu         Y.Taoka         Update          CSA UnitTest#143
 * 08/26/2016   Fujitsu         C.Tanaka        Update          QC#13820
 * 03/13/2017   Hitachi         E.Kameishi      Update          QC#17980
 * 03/13/2017   CITS            T.Kikuhara      Update          RS#3097
 * 03/13/2017   CITS            T.Kikuhara      Update          QC#19710
 * 10/19/2017   CITS            K.Ogino         Update          QC#21914
 * 11/07/2017   CITS            K.Ogino         Update          QC#22263
 * 10/02/2018   Hitachi         J.Kim           Update          QC#28580
 *</pre>
 */

public class NLEB005001 extends S21BatchMain {

    /** Counter : Normal finished Records */
    private int counterNomalRec = 0;

    /** Counter : Error finished Records */
    private int counterErrorRec = 0;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Batch Process Date */
    private String batchProcDt = null;

    /** Batch Process Year Month */
    private String batchProcYrMth = null;

    /** Term Code */
    private TERM_CD termCd;

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Fatch size for SSM */
    private static final int FETCH_SIZE_MAX = 1000;

    /** MLY_ASSET_DEPC_MODE */
    private String mlyAssetDepcMode = null;

    // Add 01/21/2016
    /** RUN_DATE */
    private String runDate = null;

    /** ACCT_YR_MTH */
    private String acctYrMth = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** declPlace */
    private BigDecimal declPlace = null;

    /** roundMode */
    private BigDecimal roundMode = null;

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {

        new NLEB005001().executeBatch(NLEB005001.class.getSimpleName());

    }

    @Override
    protected void initRoutine() {

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.termCd = TERM_CD.NORMAL_END;

        S21UserProfileService prof = S21UserProfileServiceFactory.getInstance().getService();
        glblCmpyCd = prof.getGlobalCompanyCode();

        if (S21StringUtil.isEmpty(glblCmpyCd)) {

            throw new S21AbendException(NLEB005001Constant.NASM0010E);
        }

        // Value check(Global Company Code)
        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);

        if (null == glblCmpyTMsg) {

            throw new S21AbendException(NLEB005001Constant.NWZM0650E);
        }

        batchProcDt = ZYPDateUtil.getBatProcDate(glblCmpyCd, NLEB005001Constant.BUSINESS_ID);

        if (S21StringUtil.isEmpty(batchProcDt)) {

            throw new S21AbendException(NLEB005001Constant.NDMM0016E);
        }

        batchProcYrMth = ZYPDateUtil.DateFormatter(batchProcDt, "yyyyMMdd", "yyyyMM");

        mlyAssetDepcMode = ZYPCodeDataUtil.getVarCharConstValue(NLEB005001Constant.MLY_ASSET_DEPC_MODE, glblCmpyCd);

        if (S21StringUtil.isEmpty(mlyAssetDepcMode)) {

            throw new S21AbendException(NLEB005001Constant.NLEM0002E, new String[] {"VAR_CHAR_CONST", "searching", "MLY_ASSET_DEPC_MODE" });
        }

        // Add 01/21/2016
        // Get ACCT_MTH_CTRL.NEXT_RUN_DT, ACCT_MTH_CTRL.ACCT_YR_MTH
        Map<String, Object> acctMthCtrlDt = getAcctMthCtrlDt();
        if (acctMthCtrlDt == null) {
            throw new S21AbendException(NLEB005001Constant.NLEM0002E, new String[] {"ACCT_MTH_CTRL", "searching", ""});
        }
        runDate = (String) acctMthCtrlDt.get("NEXT_RUN_DT");
        if (S21StringUtil.isEmpty(runDate)) {
            throw new S21AbendException(NLEB005001Constant.NLEM0002E, new String[] {"ACCT_MTH_CTRL", "searching", "NEXT_RUN_DT" });
        }
        acctYrMth = (String) acctMthCtrlDt.get("ACCT_YR_MTH");
        if (S21StringUtil.isEmpty(acctYrMth)) {
            throw new S21AbendException(NLEB005001Constant.NLEM0002E, new String[] {"ACCT_MTH_CTRL", "searching", "ACCT_YR_MTH" });
        }

        // QC#22263 Add
        declPlace = ZYPCodeDataUtil.getNumConstValue(NLEB005001Constant.DEPC_DECIMAL_PLACE, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(declPlace)) {
            declPlace = BigDecimal.valueOf(2);
        }
        roundMode = ZYPCodeDataUtil.getNumConstValue(NLEB005001Constant.DEPC_ROUNDING_MODE, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(roundMode)) {
            roundMode = BigDecimal.valueOf(1);
        }
        // QC#22263 End
    }

    @Override
    protected void mainRoutine() {

        PreparedStatement stmtDsAssetMstr = null;
        ResultSet rsDsAssetMstr = null;

        // Add 01/21/2016
        // Is run process date?
        if (!isRunProcDt()) {
            return;
        }
        batchProcYrMth = acctYrMth;
        // End 01/21/2016

        try {

            /*************************************************************
             * 1. Get Depreciation Target Asset Data
             ************************************************************/
            S21SsmExecutionParameter excParam = new S21SsmExecutionParameter();
            excParam.setFetchSize(FETCH_SIZE_MAX);
            excParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            excParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            excParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NLEB005001Constant.BIND_GLBL_CMPY_CD, glblCmpyCd);
            paramMap.put(NLEB005001Constant.BIND_BATCH_YEAR_MONTH, batchProcYrMth);
            paramMap.put(NLEB005001Constant.BIND_ACTV_ASSET_FLG, ZYPConstant.FLG_ON_Y);
            paramMap.put(NLEB005001Constant.BIND_DEPC_LOC_TP_BOTH, DEPC_LOC_TP.BOTH);
            paramMap.put(NLEB005001Constant.BIND_DEPC_LOC_TP_CUST, DEPC_LOC_TP.CUSTOMER);
            paramMap.put(NLEB005001Constant.BIND_DEPC_LOC_TP_WH, DEPC_LOC_TP.WAREHOUSE);
            // QC#21914 Start
            List<String> assetStsCdList = new ArrayList<String>();
            assetStsCdList.add(ASSET_STS.ACTIVATE);
            assetStsCdList.add(ASSET_STS.MERGED);
            paramMap.put(NLEB005001Constant.BIND_ASSET_STS, assetStsCdList);
            // QC#21914 End

            stmtDsAssetMstr = this.ssmLLClient.createPreparedStatement("getTargetDsAssetMstr", paramMap, excParam);
            rsDsAssetMstr = stmtDsAssetMstr.executeQuery();

            // 
            BigDecimal prePrntAssetPk = null;
            boolean hasErrPrntAsset = false;
            List<Map<String, Object>> assetMstrAmtList = new ArrayList<Map<String, Object>>();

            dsAssetMstr: while (rsDsAssetMstr.next()) {

                if (prePrntAssetPk != null && !(prePrntAssetPk.compareTo(rsDsAssetMstr.getBigDecimal(NLEB005001Constant.PRNT_DS_ASSET_MSTR_PK)) == 0)) {
                    if (!hasErrPrntAsset) {
                        commit();
                    }
                    // Reset
                    hasErrPrntAsset = false;
                }

                // START 2018/08/23 J.Kim [QC#22267,ADD]
                BigDecimal oldCurValAmt = BigDecimal.ZERO;
                BigDecimal oldInitAmtValue = BigDecimal.ZERO;
                BigDecimal prntAssetMstrPk = rsDsAssetMstr.getBigDecimal(NLEB005001Constant.PRNT_DS_ASSET_MSTR_PK);
                if (prntAssetMstrPk.compareTo(rsDsAssetMstr.getBigDecimal(NLEB005001Constant.DS_ASSET_MSTR_PK)) == 0) {
                    Map<String, Object> assetMstrAmtMap = new HashMap<String, Object>();
                    Map<String, Object> amountSummary = doProcessAmountSummary(prntAssetMstrPk);
                    oldCurValAmt = (BigDecimal) amountSummary.get(NLEB005001Constant.CUR_VAL_AMT);
                    oldInitAmtValue = (BigDecimal) amountSummary.get(NLEB005001Constant.INIT_BOOK_AMT);
                    assetMstrAmtMap.put(NLEB005001Constant.CUR_VAL_AMT, oldCurValAmt);
                    assetMstrAmtMap.put(NLEB005001Constant.INIT_BOOK_AMT, oldInitAmtValue);
                    assetMstrAmtMap.put(NLEB005001Constant.PRNT_DS_ASSET_MSTR_PK, prntAssetMstrPk);
                    assetMstrAmtList.add(assetMstrAmtMap);
                }
                // END 2018/08/23 J.Kim [QC#22267,ADD]
                /*************************************************************
                 * 2. Set Value for Depreciation
                 ************************************************************/
                // Current Asset Master Value
                BigDecimal curDepcCnt = rsDsAssetMstr.getBigDecimal(NLEB005001Constant.DEPC_CNT);
                BigDecimal depcMthNum = rsDsAssetMstr.getBigDecimal(NLEB005001Constant.DEPC_MTH_NUM);
                BigDecimal rsdlValAmt = rsDsAssetMstr.getBigDecimal(NLEB005001Constant.RSDL_VAL_AMT);
                String depcReStartDt = rsDsAssetMstr.getString(NLEB005001Constant.DEPC_RE_START_DT);
                String lastDepcYrMth = rsDsAssetMstr.getString(NLEB005001Constant.LAST_DEPC_YR_MTH);
                String firstDepcYrMth = rsDsAssetMstr.getString(NLEB005001Constant.FIRST_DEPC_YR_MTH);

                // Next Depreciation Year Month (Last + 1)
                String nextDepcYrMth = null;

                if (ZYPCommonFunc.hasValue(lastDepcYrMth)) {

                    nextDepcYrMth = addMonth(lastDepcYrMth, 1);
                }

                // Depreciation Re-Start Year Month
                String depcReStartYrMth = null;

                if (ZYPCommonFunc.hasValue(depcReStartDt)) {

                    depcReStartYrMth = ZYPDateUtil.DateFormatter(depcReStartDt, "yyyyMMdd", "yyyyMM");
                }

                String depcStartYrMth = null;
                String depcEndYrMth = null;
                BigDecimal thisDepcCnt = null;

                if (NLEB005001Constant.MLY_ASSET_DEPC_MODE_ONE_TRX.equals(mlyAssetDepcMode)) {

                    // This Month Only
                    thisDepcCnt = BigDecimal.ONE;
                    depcStartYrMth = batchProcYrMth;
                    depcEndYrMth = batchProcYrMth;

                } else {

                    // First Depreciation
                    if (!ZYPCommonFunc.hasValue(lastDepcYrMth)) {

                        if (batchProcYrMth.equals(firstDepcYrMth)) {

                            // This Month Only
                            thisDepcCnt = BigDecimal.ONE;
                            depcStartYrMth = batchProcYrMth;
                            depcEndYrMth = batchProcYrMth;

                        } else {

                            if (!ZYPCommonFunc.hasValue(depcReStartYrMth) || compareYrMth(firstDepcYrMth, depcReStartYrMth) > 0) {

                                // First to Batch
                                thisDepcCnt = setThisDepcCnt(rsDsAssetMstr.getBigDecimal(NLEB005001Constant.FIRST_BATCH_MTH).add(BigDecimal.ONE), curDepcCnt, depcMthNum);
                                depcStartYrMth = firstDepcYrMth;
                                depcEndYrMth = addMonth(depcStartYrMth, thisDepcCnt.subtract(BigDecimal.ONE).intValue());

                            } else {

                                // Re-Start to Batch
                                thisDepcCnt = setThisDepcCnt(rsDsAssetMstr.getBigDecimal(NLEB005001Constant.RESTART_BATCH_MTH).add(BigDecimal.ONE), curDepcCnt, depcMthNum);
                                depcStartYrMth = depcReStartYrMth;
                                depcEndYrMth = addMonth(depcStartYrMth, thisDepcCnt.subtract(BigDecimal.ONE).intValue());
                            }
                        }

                    } else {

                        if (batchProcYrMth.equals(nextDepcYrMth)) {

                            // This Month Only
                            thisDepcCnt = BigDecimal.ONE;
                            depcStartYrMth = batchProcYrMth;
                            depcEndYrMth = batchProcYrMth;

                        } else {

                            if (!ZYPCommonFunc.hasValue(depcReStartYrMth) || compareYrMth(nextDepcYrMth, depcReStartYrMth) > 0) {

                                // Next to Batch
                                thisDepcCnt = setThisDepcCnt(rsDsAssetMstr.getBigDecimal(NLEB005001Constant.LAST_BATCH_MTH), curDepcCnt, depcMthNum);
                                depcStartYrMth = nextDepcYrMth;
                                depcEndYrMth = addMonth(depcStartYrMth, thisDepcCnt.subtract(BigDecimal.ONE).intValue());

                            } else {

                                // Re-Start to Batch
                                thisDepcCnt = setThisDepcCnt(rsDsAssetMstr.getBigDecimal(NLEB005001Constant.RESTART_BATCH_MTH).add(BigDecimal.ONE), curDepcCnt, depcMthNum);
                                depcStartYrMth = depcReStartYrMth;
                                depcEndYrMth = addMonth(depcStartYrMth, thisDepcCnt.subtract(BigDecimal.ONE).intValue());
                            }
                        }
                    }
                }

                /*************************************************************
                 * 3. Execute Asset Update API
                 ************************************************************/
                BigDecimal nextDepcCnt = curDepcCnt.add(thisDepcCnt);

                NLZC305001PMsg pMsg = new NLZC305001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, batchProcDt);

                pMsg.updDtlList.setValidCount(1);
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, NLZC305001Constant.PROC_MODE_DEPC);
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetMstrPk, rsDsAssetMstr.getBigDecimal(NLEB005001Constant.DS_ASSET_MSTR_PK));
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, PROC_MODE.DEPRECIATION_MONTHLY);

                // Asset Mode == 00,01,02
                if (NLEB005001Constant.MLY_ASSET_DEPC_MODE_ONE_TRX.equals(mlyAssetDepcMode) || NLEB005001Constant.MLY_ASSET_DEPC_MODE_SUM_TRX.equals(mlyAssetDepcMode)
                        || NLEB005001Constant.MLY_ASSET_DEPC_MODE_ROSS_TRX.equals(mlyAssetDepcMode)) {

                    // Past depreciation in case of Asset Mode 02
                    if (NLEB005001Constant.MLY_ASSET_DEPC_MODE_ROSS_TRX.equals(mlyAssetDepcMode) && batchProcYrMth.equals(depcEndYrMth) && thisDepcCnt.compareTo(BigDecimal.ONE) > 0) {

                        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).curValAmt, culcCurValAmt(rsDsAssetMstr, thisDepcCnt.subtract(BigDecimal.ONE)));
                        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).depcCnt, nextDepcCnt.subtract(BigDecimal.ONE));
                        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).lastDepcYrMth, addMonth(depcEndYrMth, -1));

                        // Call API
                        if (callDsAssetUpdateAPI(pMsg)) {

                            // Error
                            rollback();
                            counterErrorRec++;
                            // Add 01/21/2016
                            hasErrPrntAsset = true;
                            continue dsAssetMstr;
                        }
                    }

                    // Current Value Amount
                    if (nextDepcCnt.compareTo(depcMthNum) < 0) {

                        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).curValAmt, culcCurValAmt(rsDsAssetMstr, thisDepcCnt));

                    } else {

                        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).curValAmt, rsdlValAmt);
                    }

                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).depcCnt, nextDepcCnt);
                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).lastDepcYrMth, depcEndYrMth);

                    // Call API
                    if (callDsAssetUpdateAPI(pMsg)) {

                        // Error
                        rollback();
                        counterErrorRec++;
                        // Add 01/21/2016
                        hasErrPrntAsset = true;
                        continue dsAssetMstr;
                    }

                    // Asset Mode == 03
                } else if (NLEB005001Constant.MLY_ASSET_DEPC_MODE_SEPT_TRX.equals(mlyAssetDepcMode)) {

                    int index = 0;
                    BigDecimal trxCnt = BigDecimal.ONE;

                    while (thisDepcCnt.compareTo(trxCnt) >= 0) {

                        BigDecimal depcCnt = trxCnt.add(curDepcCnt);

                        // Current Value Amount
                        if (depcCnt.compareTo(depcMthNum) < 0) {

                            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).curValAmt, culcCurValAmt(rsDsAssetMstr, trxCnt));

                        } else {

                            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).curValAmt, rsdlValAmt);
                        }

                        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).depcCnt, depcCnt);
                        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).lastDepcYrMth, addMonth(depcStartYrMth, index));

                        index++;
                        trxCnt = trxCnt.add(BigDecimal.ONE);

                        // Call API
                        if (callDsAssetUpdateAPI(pMsg)) {

                            // Error
                            rollback();
                            counterErrorRec++;
                            // Add 01/21/2016
                            hasErrPrntAsset = true;
                            continue dsAssetMstr;
                        }
                    }
                }

                // Modify 01/21/2016
                prePrntAssetPk = rsDsAssetMstr.getBigDecimal(NLEB005001Constant.PRNT_DS_ASSET_MSTR_PK);
                counterNomalRec++;
            }

            if (prePrntAssetPk == null) {
                // Inclued Error Asset
                rollback();
                counterErrorRec = counterErrorRec++;
            }

            // START 2017/03/13 E.Kameishi [QC#17980,ADD]
            updateAcctMthCtrl();
            // END 2017/03/13 E.Kameishi [QC#17980,ADD]

            // START 2018/08/23 J.Kim [QC#22267,ADD]
            for (Map<String, Object> assetMstrAmt : assetMstrAmtList) {
                NLZC305001PMsg pMsg = new NLZC305001PMsg();
                pMsg.updDtlList.setValidCount(1);
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, batchProcDt);
                BigDecimal curValAmt = (BigDecimal) assetMstrAmt.get(NLEB005001Constant.CUR_VAL_AMT);
                BigDecimal initAmtValue = (BigDecimal) assetMstrAmt.get(NLEB005001Constant.INIT_BOOK_AMT);
                BigDecimal prntAssetMstrPk = (BigDecimal) assetMstrAmt.get(NLEB005001Constant.PRNT_DS_ASSET_MSTR_PK);
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, PROC_MODE.DEPRECIATION_MONTHLY);
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, NLZC305001Constant.PROC_MODE_SUMMARY);
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).prntDsAssetMstrPk, prntAssetMstrPk);
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).curValAmt, curValAmt);
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).initBookAmt, initAmtValue);
                if (callDsAssetUpdateAPI(pMsg)) {
                    rollback();
                }
            }
            // END 2018/08/23 J.Kim [QC#22267,ADD]

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        // QC#13820 ADD Start
        } catch (ParseException e) {

            S21InfoLogOutput.println("Perse Exception Error occurs.");
        // QC#13820 ADD End

        } finally {

            S21SsmLowLevelCodingClient.closeResource(stmtDsAssetMstr, rsDsAssetMstr);
        }
    }

    @Override
    protected void termRoutine() {

        // Print result.
        if (counterErrorRec == 0) {

            S21InfoLogOutput.println("Monthly Asset Depreciation Calculation Batch is normally end.");

        } else {

            S21InfoLogOutput.println("Monthly Asset Depreciation Calculation Batch is abnormally end.");
        }

        // Set term code and total count.
        setTermState(this.termCd, counterNomalRec, counterErrorRec, (counterNomalRec + counterErrorRec));
    }

    /**
     * Set This Depreciation Count
     * @param baseDepcCnt BigDecimal
     * @param curDepcCnt BigDecimal
     * @param depcMthNum BigDecimal
     * @return thisDepcCnt
     */
    private BigDecimal setThisDepcCnt(BigDecimal baseDepcCnt, BigDecimal curDepcCnt, BigDecimal depcMthNum) {

        BigDecimal thisDepcCnt = baseDepcCnt;

        if (curDepcCnt.add(thisDepcCnt).compareTo(depcMthNum) >= 0) {

            thisDepcCnt = depcMthNum.subtract(curDepcCnt);
        }

        return thisDepcCnt;
    }

    /**
     * Compare Year Month
     * @param strYrMth1 String
     * @param strYrMth2 String
     * @return Integer
     */
    private int compareYrMth(String strYrMth1, String strYrMth2) {

        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        calendar1.set(Integer.parseInt(strYrMth1.substring(0, 4)), Integer.parseInt(strYrMth1.substring(4, 6)) - 1, 01);
        calendar2.set(Integer.parseInt(strYrMth2.substring(0, 4)), Integer.parseInt(strYrMth2.substring(4, 6)) - 1, 01);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        String strYrMthDt1 = dateFormat.format(calendar1.getTime());
        String strYrMthDt2 = dateFormat.format(calendar2.getTime());

        return ZYPDateUtil.compare(strYrMthDt1, strYrMthDt2);
    }

    /**
     * culcCurValAmt
     * @param rsDsAssetMstr ResultSet
     * @param multiplyVal BigDecimal
     * @return BigDecimal
     * @throws SQLException
     */
    private BigDecimal culcCurValAmt(ResultSet rsDsAssetMstr, BigDecimal multiplyVal) throws SQLException {

        int roundingMode = BigDecimal.ROUND_DOWN;

        if (NLEB005001Constant.ROUND_UP.equals(roundMode)) {

            roundingMode = BigDecimal.ROUND_UP;

        } else if (NLEB005001Constant.ROUND_HALF_UP.equals(roundMode)) {

            roundingMode = BigDecimal.ROUND_HALF_UP;
        }

        BigDecimal depcMthNum = rsDsAssetMstr.getBigDecimal(NLEB005001Constant.DEPC_MTH_NUM);
        BigDecimal prevValAmt = rsDsAssetMstr.getBigDecimal(NLEB005001Constant.CUR_VAL_AMT);
        BigDecimal rsdlValAmt = rsDsAssetMstr.getBigDecimal(NLEB005001Constant.RSDL_VAL_AMT);
        BigDecimal depcCnt = rsDsAssetMstr.getBigDecimal(NLEB005001Constant.DEPC_CNT);
        BigDecimal valAmt = null;
        BigDecimal curValAmt = null;

        BigDecimal depcValAmt = prevValAmt.subtract(rsdlValAmt);
        BigDecimal curDepcCnt = depcMthNum.subtract(depcCnt);

        valAmt = depcValAmt.divide(curDepcCnt, 8, roundingMode);

        if (ZYPCommonFunc.hasValue(multiplyVal)) {

            valAmt = valAmt.multiply(multiplyVal);
        }

        valAmt = valAmt.setScale(declPlace.intValue(), roundingMode);

        curValAmt = prevValAmt.subtract(valAmt);

        return curValAmt;
    }

    /**
     * add Month
     * @param baseYrMth String
     * @param addMth Integer
     * @return String
     */
    private String addMonth(String baseYrMth, int addMth) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(baseYrMth.substring(0, 4)), Integer.parseInt(baseYrMth.substring(4, 6)) - 1, 01);

        calendar.add(Calendar.MONTH, addMth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        String addMthYrMth = dateFormat.format(calendar.getTime());

        return addMthYrMth;
    }

    /**
     * Call Asset Update API
     * @param params APIParameterList
     * @return OperationErrorFlag (true: OperationError)
     */
    private boolean callDsAssetUpdateAPI(NLZC305001PMsg param) {

        boolean operationErrFlg = false;

        NLZC305001 api = new NLZC305001();
        api.execute(param, S21ApiInterface.ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(param)) {

            String[] errMsg = new String[] {param.updDtlList.no(0).dsAssetMstrPk.getValue().toString() };
            S21InfoLogOutput.println(NLEB005001Constant.NLBM1335E, errMsg);

            List<String> msgIds = S21ApiUtil.getXxMsgIdList(param);

            for (String msgId : msgIds) {

                S21InfoLogOutput.println(msgId);
            }

            rollback();
            this.termCd = TERM_CD.WARNING_END;
            return true;
        }

        return operationErrFlg;
    }

    // Add Start 01/21/2016
    /**
     * Get AcctMthCtrlDt
     * @return Map<String, Object>
     */
    private Map<String, Object> getAcctMthCtrlDt() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("acctMthCtrlCd", ACCT_MTH_CTRL.DEPRICIATION_CONTROL);
        Map<String, Object> getAcctMthCtrlDtMap = (Map<String, Object>) ssmBatchClient.queryObject("getAcctMthCtrlDt", ssmParam);

        return getAcctMthCtrlDtMap;
    }

    /**
     * Is Run Process Date
     * @return
     */
    private boolean isRunProcDt() {
        if (runDate.equals(batchProcDt)) {
            return true;
        }
        return false;
    }

    // QC#13820 ADD Start
    private void updateAcctMthCtrl() throws ParseException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat();
        sdf1.applyPattern(ZYPDateUtil.TYPE_YYYYMMDD);
        cal.setTime(sdf1.parse(runDate));
        cal.add(Calendar.MONTH, 1);
        // START 2018/10/12 J.Kim [QC#28580,ADD]
        int nextDt = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DATE, nextDt);
        // END 2018/10/12 J.Kim [QC#28580,ADD]
        String nextRunDt = sdf1.format(cal.getTime());

        SimpleDateFormat sdf2 = new SimpleDateFormat();
        sdf2.applyPattern("yyyyMM");
        String acctYrMth = sdf2.format(cal.getTime());

        // QC#19710 ADD Start
        if (!this.acctYrMth.equals(this.runDate.substring(0, 6))) {
            acctYrMth = this.runDate.substring(0, 6);
        }
        // QC#19710 ADD End

        ACCT_MTH_CTRLTMsg tMsg = new ACCT_MTH_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.acctMthCtrlCd, ACCT_MTH_CTRL.DEPRICIATION_CONTROL);
        tMsg = (ACCT_MTH_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
        ZYPEZDItemValueSetter.setValue(tMsg.lastRunDt, runDate);
        ZYPEZDItemValueSetter.setValue(tMsg.nextRunDt, nextRunDt);
        ZYPEZDItemValueSetter.setValue(tMsg.acctYrMth, acctYrMth);

        // START 2017/03/13 E.Kameishi [QC#17980,MOD]
        if (tMsg != null) {
            S21FastTBLAccessor.update(tMsg);
        }
        // END 2017/03/13 E.Kameishi [QC#17980,MOD]
    }
    // QC#13820 ADD End

    // START 2018/08/23 J.Kim [QC#22267,ADD]
    /**
     * doProcessAmountSummary
     * @param dsAssetMstrPk BigDecimal
     */
    private Map<String, Object> doProcessAmountSummary(BigDecimal dsAssetMstrPk) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("prntDsAssetMstrPk", dsAssetMstrPk);

        Map<String, Object> assetMstrInfoMap = (Map<String, Object>) ssmBatchClient.queryObject("getAmount", queryParam);
        return assetMstrInfoMap;
    }
    // END 2018/08/23 J.Kim [QC#22267,ADD]
}
