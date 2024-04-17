/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLE.NLEB008001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import business.db.DS_ASSET_DEPC_SMLTNTMsg;
import business.db.DS_ASSET_DEPC_SMLTN_RQSTTMsg;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DEPC_LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Latest Asset Depreciation Calculation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 02/02/2016   Fujitsu         Y.Taoka         Create          N/A
 * 11/07/2017   CITS            K.Ogino         Update          QC#22263
 *</pre>
 */

public class NLEB008001 extends S21BatchMain {

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

    /** Commit Count */
    private int commitCount = 0;

    /** Term Code */
    private TERM_CD termCd;

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Fatch size for SSM */
    private static final int FETCH_SIZE_MAX = 1000;

    /** MLY_ASSET_DEPC_MODE */
    private String mlyAssetDepcMode = null;

    /** DEPC_SMLTN_RQST_STS_CD for process */
    private String procDepcSmltnRqstStsCd = null;

    /** DEPC_SMLTN_RQST_STS_CD for completed */
    private String compDepcSmltnRqstStsCd = null;

    /** declPlace */
    private BigDecimal declPlace = null;

    /** roundMode */
    private BigDecimal roundMode = null;

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {

        new NLEB008001().executeBatch(NLEB008001.class.getSimpleName());

    }

    @Override
    protected void initRoutine() {

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.termCd = TERM_CD.NORMAL_END;

        // Get Global Company Code
        S21UserProfileService prof = S21UserProfileServiceFactory.getInstance().getService();
        this.glblCmpyCd = prof.getGlobalCompanyCode();
        if (S21StringUtil.isEmpty(glblCmpyCd)) {
            throw new S21AbendException(NLEB008001Constant.NLEM0001E, new String[]{"Global Company Code",  ""});
        }

        // Value check(Global Company Code)
        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);
        if (null == glblCmpyTMsg) {
            throw new S21AbendException(NLEB008001Constant.NWZM0650E);
        }

        // Get Batch Date 
        this.batchProcDt = ZYPDateUtil.getBatProcDate(glblCmpyCd, NLEB008001Constant.BUSINESS_ID);
        if (S21StringUtil.isEmpty(this.batchProcDt)) {
            throw new S21AbendException(NLEB008001Constant.NLEM0001E, new String[]{"Batch Process Date",  ""});
        }
        batchProcYrMth = ZYPDateUtil.DateFormatter(this.batchProcDt, "yyyyMMdd", "yyyyMM");

        // Get Depreciation Mode
        mlyAssetDepcMode = ZYPCodeDataUtil.getVarCharConstValue(NLEB008001Constant.MLY_ASSET_DEPC_MODE, glblCmpyCd);
        if (S21StringUtil.isEmpty(mlyAssetDepcMode)) {
            throw new S21AbendException(NLEB008001Constant.NLEM0002E, new String[] {"VAR_CHAR_CONST", "searching", "MLY_ASSET_DEPC_MODE" });
        }

        // Get Commit Count
        this.commitCount = getCommitCount();
        if (this.commitCount == 0) {
            throw new S21AbendException(NLEB008001Constant.NLEM0001E, new String[]{"Commit Count",  ""});
        }

        // Simulation Status for process
        this.procDepcSmltnRqstStsCd = ZYPCodeDataUtil.getVarCharConstValue(NLEB008001Constant.PROC_DEPC_SMLTN_RQST_STS_CD, glblCmpyCd);
        if (S21StringUtil.isEmpty(this.procDepcSmltnRqstStsCd)) {
            throw new S21AbendException(NLEB008001Constant.NLEM0002E, new String[] {"VAR_CHAR_CONST", "searching", "NLEB0080_DEPC_SMLTN_RQST_STS" });
        }
        // Simulation Status for completed
        this.compDepcSmltnRqstStsCd = ZYPCodeDataUtil.getVarCharConstValue(NLEB008001Constant.COMP_DEPC_SMLTN_RQST_STS_CD, glblCmpyCd);
        if (S21StringUtil.isEmpty(this.compDepcSmltnRqstStsCd)) {
            throw new S21AbendException(NLEB008001Constant.NLEM0002E, new String[] {"VAR_CHAR_CONST", "searching", "DEPC_SMLTN_RQST_STS_COMP" });
        }

        // QC#22263 Add
        declPlace = ZYPCodeDataUtil.getNumConstValue(NLEB008001Constant.DEPC_DECIMAL_PLACE, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(declPlace)) {
            declPlace = BigDecimal.valueOf(2);
        }
        roundMode = ZYPCodeDataUtil.getNumConstValue(NLEB008001Constant.DEPC_ROUNDING_MODE, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(roundMode)) {
            roundMode = BigDecimal.valueOf(1);
        }
        // QC#22263 End
    }

    @Override
    protected void mainRoutine() {

        PreparedStatement stmtDsAssetMstr = null;
        ResultSet rsDsAssetMstr = null;

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
            paramMap.put(NLEB008001Constant.BIND_GLBL_CMPY_CD, this.glblCmpyCd);
            paramMap.put(NLEB008001Constant.BIND_BATCH_YEAR_MONTH, this.batchProcYrMth);
            paramMap.put(NLEB008001Constant.BIND_ACTV_ASSET_FLG, ZYPConstant.FLG_ON_Y);
            paramMap.put(NLEB008001Constant.BIND_DEPC_LOC_TP_BOTH, DEPC_LOC_TP.BOTH);
            paramMap.put(NLEB008001Constant.BIND_DEPC_LOC_TP_CUST, DEPC_LOC_TP.CUSTOMER);
            paramMap.put(NLEB008001Constant.BIND_DEPC_LOC_TP_WH, DEPC_LOC_TP.WAREHOUSE);
            paramMap.put(NLEB008001Constant.BIND_ASSET_STS, ASSET_STS.ACTIVATE);
            paramMap.put(NLEB008001Constant.BIND_DEPC_SMLTN_RQST_STS, this.procDepcSmltnRqstStsCd);

            stmtDsAssetMstr = this.ssmLLClient.createPreparedStatement("getTargetDsAssetMstr", paramMap, excParam);
            rsDsAssetMstr = stmtDsAssetMstr.executeQuery();

            String preRqstDt = "";
            int oneCmtCtr = 0;
            dsAssetMstr: while (rsDsAssetMstr.next()) {

                // Commit Control
                String rqstDt = rsDsAssetMstr.getString(NLEB008001Constant.DEPC_SMLTN_RQST_DT_TM_TS);
                if ((ZYPCommonFunc.hasValue(preRqstDt) && !preRqstDt.equals(rqstDt)) || this.commitCount <= oneCmtCtr) {
                    commit();
                    counterNomalRec = counterNomalRec + oneCmtCtr;
                    oneCmtCtr = 0;
                }

                // Table Lock
                DS_ASSET_DEPC_SMLTN_RQSTTMsg  assetDepcSmltnRqst = lockSmltnRqstForUpdate(rsDsAssetMstr);
                if (assetDepcSmltnRqst == null) {
                    // Faild to lock the table
                    throw new S21AbendException(NLEB008001Constant.NLEM0019E, new String[]{"DS_ASSET_DEPC_SMLTN_RQST", rsDsAssetMstr.getString(NLEB008001Constant.DS_ASSET_DEPC_SMLTN_RQST_PK)});
                }

                /*************************************************************
                 * 2. Set Value for Depreciation
                 ************************************************************/
                // Current Asset Master Value
                BigDecimal curDepcCnt = rsDsAssetMstr.getBigDecimal(NLEB008001Constant.DEPC_CNT);
                BigDecimal depcMthNum = rsDsAssetMstr.getBigDecimal(NLEB008001Constant.DEPC_MTH_NUM);
                BigDecimal rsdlValAmt = rsDsAssetMstr.getBigDecimal(NLEB008001Constant.RSDL_VAL_AMT);
                String depcReStartDt = rsDsAssetMstr.getString(NLEB008001Constant.DEPC_RE_START_DT);
                String lastDepcYrMth = rsDsAssetMstr.getString(NLEB008001Constant.LAST_DEPC_YR_MTH);
                String firstDepcYrMth = rsDsAssetMstr.getString(NLEB008001Constant.FIRST_DEPC_YR_MTH);

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

                if (NLEB008001Constant.MLY_ASSET_DEPC_MODE_ONE_TRX.equals(mlyAssetDepcMode)) {

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
                                thisDepcCnt = setThisDepcCnt(rsDsAssetMstr.getBigDecimal(NLEB008001Constant.FIRST_BATCH_MTH).add(BigDecimal.ONE), curDepcCnt, depcMthNum);
                                depcStartYrMth = firstDepcYrMth;
                                depcEndYrMth = addMonth(depcStartYrMth, thisDepcCnt.subtract(BigDecimal.ONE).intValue());

                            } else {

                                // Re-Start to Batch
                                thisDepcCnt = setThisDepcCnt(rsDsAssetMstr.getBigDecimal(NLEB008001Constant.RESTART_BATCH_MTH).add(BigDecimal.ONE), curDepcCnt, depcMthNum);
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
                                thisDepcCnt = setThisDepcCnt(rsDsAssetMstr.getBigDecimal(NLEB008001Constant.LAST_BATCH_MTH), curDepcCnt, depcMthNum);
                                depcStartYrMth = nextDepcYrMth;
                                depcEndYrMth = addMonth(depcStartYrMth, thisDepcCnt.subtract(BigDecimal.ONE).intValue());

                            } else {

                                // Re-Start to Batch
                                thisDepcCnt = setThisDepcCnt(rsDsAssetMstr.getBigDecimal(NLEB008001Constant.RESTART_BATCH_MTH).add(BigDecimal.ONE), curDepcCnt, depcMthNum);
                                depcStartYrMth = depcReStartYrMth;
                                depcEndYrMth = addMonth(depcStartYrMth, thisDepcCnt.subtract(BigDecimal.ONE).intValue());
                            }
                        }
                    }
                }

                /*************************************************************
                 * 3. Caluculate Current Value Amount
                 ************************************************************/
                BigDecimal curValAmtCalc = BigDecimal.ZERO;
                BigDecimal depcCntCalc = BigDecimal.ZERO;
                String lastDepcYrMthCalc = "";

                BigDecimal nextDepcCnt = curDepcCnt.add(thisDepcCnt);

                // Asset Mode == 00,01,02
                if (NLEB008001Constant.MLY_ASSET_DEPC_MODE_ONE_TRX.equals(mlyAssetDepcMode) || NLEB008001Constant.MLY_ASSET_DEPC_MODE_SUM_TRX.equals(mlyAssetDepcMode)
                        || NLEB008001Constant.MLY_ASSET_DEPC_MODE_ROSS_TRX.equals(mlyAssetDepcMode)) {

                    // Past depreciation in case of Asset Mode 02
                    if (NLEB008001Constant.MLY_ASSET_DEPC_MODE_ROSS_TRX.equals(mlyAssetDepcMode) && batchProcYrMth.equals(depcEndYrMth) && thisDepcCnt.compareTo(BigDecimal.ONE) > 0) {

                        curValAmtCalc = culcCurValAmt(rsDsAssetMstr, thisDepcCnt.subtract(BigDecimal.ONE));
                        depcCntCalc = nextDepcCnt.subtract(BigDecimal.ONE);
                        lastDepcYrMthCalc = addMonth(depcEndYrMth, -1);
                    }

                    // Current Value Amount
                    if (nextDepcCnt.compareTo(depcMthNum) < 0) {
                        curValAmtCalc = culcCurValAmt(rsDsAssetMstr, thisDepcCnt);

                    } else {
                        curValAmtCalc = rsdlValAmt;
                    }

                    depcCntCalc = nextDepcCnt;
                    lastDepcYrMthCalc = depcEndYrMth;

                    // Asset Mode == 03
                } else if (NLEB008001Constant.MLY_ASSET_DEPC_MODE_SEPT_TRX.equals(mlyAssetDepcMode)) {

                    int index = 0;
                    BigDecimal trxCnt = BigDecimal.ONE;

                    while (thisDepcCnt.compareTo(trxCnt) >= 0) {

                        BigDecimal depcCnt = trxCnt.add(curDepcCnt);

                        // Current Value Amount
                        if (depcCnt.compareTo(depcMthNum) < 0) {
                            curValAmtCalc = culcCurValAmt(rsDsAssetMstr, trxCnt);

                        } else {
                            curValAmtCalc = rsdlValAmt;
                        }

                        depcCntCalc = depcCnt;
                        lastDepcYrMthCalc = addMonth(depcStartYrMth, index);

                        index++;
                        trxCnt = trxCnt.add(BigDecimal.ONE);

                    }
                }

                /*************************************************************
                 * 4. Insert DS_ASSET_DEPC_SMLTN/ Update DS_ASSET_DEPC_SMLTN_RQST
                 ************************************************************/
                String[] msgParam = null;
                // Insert DS_ASSET_DEPC_SMLTN
                if (!insertDsAssetDepcSmltn(rsDsAssetMstr, curValAmtCalc)) {
                    // Failed to insert
                    rollback();
                    setTermState(TERM_CD.ABNORMAL_END, counterNomalRec, counterErrorRec, (counterNomalRec + counterErrorRec));
                    msgParam = new String[]{"Asset Depreciate Simulation", "DS_ASSET_DEPC_SMLTN", "DS_ASSET_MSTR_PK:" + rsDsAssetMstr.getBigDecimal(NLEB008001Constant.DS_ASSET_MSTR_PK).toString()};
                    S21InfoLogOutput.println(NLEB008001Constant.NLEM0020E,  msgParam);
                    throw new S21AbendException(NLEB008001Constant.NLEM0020E, msgParam);
                }
                // Update Status in DS_ASSET_DEPC_SMLTN_RQST
                if (!updateStsDsAssetDepcSmltnRqst(assetDepcSmltnRqst)) {
                    // Failed to update
                    rollback();
                    setTermState(TERM_CD.ABNORMAL_END, counterNomalRec, counterErrorRec, (counterNomalRec + counterErrorRec));
                    msgParam = new String[]{"Asset Depreciate Simulation Requset", "DS_ASSET_DEPC_SMLTN_RQST", "DS_ASSET_DEPC_SMLTN_RQST_PK:" + rsDsAssetMstr.getBigDecimal(NLEB008001Constant.DS_ASSET_DEPC_SMLTN_RQST_PK).toString()};
                    S21InfoLogOutput.println(NLEB008001Constant.NLEM0021E,  msgParam);
                    throw new S21AbendException(NLEB008001Constant.NLEM0021E, msgParam);
                }
                oneCmtCtr++;

                preRqstDt = rsDsAssetMstr.getString(NLEB008001Constant.DEPC_SMLTN_RQST_DT_TM_TS);
                continue dsAssetMstr;
            }
            commit();
            counterNomalRec = counterNomalRec + oneCmtCtr;


        } catch (SQLException e) {

            sqlExceptionHandler(e);

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

        if (NLEB008001Constant.ROUND_UP.equals(roundMode)) {

            roundingMode = BigDecimal.ROUND_UP;

        } else if (NLEB008001Constant.ROUND_HALF_UP.equals(roundMode)) {

            roundingMode = BigDecimal.ROUND_HALF_UP;
        }

        BigDecimal depcMthNum = rsDsAssetMstr.getBigDecimal(NLEB008001Constant.DEPC_MTH_NUM);
        BigDecimal prevValAmt = rsDsAssetMstr.getBigDecimal(NLEB008001Constant.CUR_VAL_AMT);
        BigDecimal rsdlValAmt = rsDsAssetMstr.getBigDecimal(NLEB008001Constant.RSDL_VAL_AMT);
        BigDecimal depcCnt = rsDsAssetMstr.getBigDecimal(NLEB008001Constant.DEPC_CNT);
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
     * insertDsAssetDepcSmltn
     * @param rsDsAssetMstr ResultSet
     * @param curValAmt BigDecimal
     * @return boolean
     * @throws SQLException
     */
    private boolean insertDsAssetDepcSmltn(ResultSet rsDsAssetMstr, BigDecimal curValAmt) throws SQLException {
        DS_ASSET_DEPC_SMLTNTMsg assetDepcSmltn  = new DS_ASSET_DEPC_SMLTNTMsg();

        ZYPEZDItemValueSetter.setValue(assetDepcSmltn.glblCmpyCd, this.glblCmpyCd);
        BigDecimal dsAssetDepcSmltnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ASSET_DEPC_SMLTN_SQ);
        ZYPEZDItemValueSetter.setValue(assetDepcSmltn.dsAssetDepcSmltnPk, dsAssetDepcSmltnPk);
        ZYPEZDItemValueSetter.setValue(assetDepcSmltn.depcSmltnRqstDtTmTs, rsDsAssetMstr.getString(NLEB008001Constant.DEPC_SMLTN_RQST_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(assetDepcSmltn.dsAssetDepcSmltnRqstPk, rsDsAssetMstr.getBigDecimal(NLEB008001Constant.DS_ASSET_DEPC_SMLTN_RQST_PK));
        ZYPEZDItemValueSetter.setValue(assetDepcSmltn.prntDsAssetMstrPk, rsDsAssetMstr.getBigDecimal(NLEB008001Constant.PRNT_DS_ASSET_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(assetDepcSmltn.assetTrxDt, this.batchProcDt);
        ZYPEZDItemValueSetter.setValue(assetDepcSmltn.dsAssetMstrPk, rsDsAssetMstr.getBigDecimal(NLEB008001Constant.DS_ASSET_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(assetDepcSmltn.mdseCd, rsDsAssetMstr.getString(NLEB008001Constant.MDSE_CD));
        ZYPEZDItemValueSetter.setValue(assetDepcSmltn.serNum, rsDsAssetMstr.getString(NLEB008001Constant.SER_NUM));
        ZYPEZDItemValueSetter.setValue(assetDepcSmltn.assetTpCd, rsDsAssetMstr.getString(NLEB008001Constant.ASSET_TP_CD));
        ZYPEZDItemValueSetter.setValue(assetDepcSmltn.trxCd, TRX.DEPRECIATION);
        ZYPEZDItemValueSetter.setValue(assetDepcSmltn.trxRsnCd, TRX_RSN.DEPRECIATION);

        ZYPEZDItemValueSetter.setValue(assetDepcSmltn.initBookAmt, rsDsAssetMstr.getBigDecimal(NLEB008001Constant.INIT_BOOK_AMT));
        ZYPEZDItemValueSetter.setValue(assetDepcSmltn.curValAmt, curValAmt);
        ZYPEZDItemValueSetter.setValue(assetDepcSmltn.prevValAmt, rsDsAssetMstr.getBigDecimal(NLEB008001Constant.CUR_VAL_AMT));
        ZYPEZDItemValueSetter.setValue(assetDepcSmltn.depcValAmt, subtract(rsDsAssetMstr.getBigDecimal(NLEB008001Constant.CUR_VAL_AMT), curValAmt));
        ZYPEZDItemValueSetter.setValue(assetDepcSmltn.ajeIfCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(assetDepcSmltn.ajeIfRqstFlg, ZYPConstant.FLG_OFF_N);

        S21FastTBLAccessor.insert(assetDepcSmltn);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(assetDepcSmltn.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * lockSmltnRqstForUpdate
     * @param rsDsAssetMstr ResultSet
     * @return DS_ASSET_DEPC_SMLTN_RQSTTMsg
     * @throws SQLException
     */
    private DS_ASSET_DEPC_SMLTN_RQSTTMsg lockSmltnRqstForUpdate(ResultSet rsDsAssetMstr) throws SQLException {
        DS_ASSET_DEPC_SMLTN_RQSTTMsg assetDepcSmltnRqst = new DS_ASSET_DEPC_SMLTN_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(assetDepcSmltnRqst.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(assetDepcSmltnRqst.dsAssetDepcSmltnRqstPk, rsDsAssetMstr.getBigDecimal(NLEB008001Constant.DS_ASSET_DEPC_SMLTN_RQST_PK));
        assetDepcSmltnRqst = (DS_ASSET_DEPC_SMLTN_RQSTTMsg) S21FastTBLAccessor.findByKeyForUpdate(assetDepcSmltnRqst);
        if (assetDepcSmltnRqst == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(assetDepcSmltnRqst.getReturnCode())) {
            return null;
        }
        return assetDepcSmltnRqst;
    }

    /**
     * updateStsDsAssetDepcSmltnRqst
     * @param assetDepcSmltnRqst DS_ASSET_DEPC_SMLTN_RQSTTMsg
     * @return boolean
     */
    private boolean updateStsDsAssetDepcSmltnRqst(DS_ASSET_DEPC_SMLTN_RQSTTMsg assetDepcSmltnRqst) {
        // Update status to "Complated".
        ZYPEZDItemValueSetter.setValue(assetDepcSmltnRqst.depcSmltnRqstStsCd, this.compDepcSmltnRqstStsCd);
        S21FastTBLAccessor.update(assetDepcSmltnRqst);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(assetDepcSmltnRqst.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * subtract
     * @param val1 BigDecimal
     * @param val2 BigDecimal
     * @return BigDecimal
     */
    private BigDecimal subtract(BigDecimal val1, BigDecimal val2) {
        if (!ZYPCommonFunc.hasValue(val1)) {
            val1 = BigDecimal.ZERO;
        }
        if (!ZYPCommonFunc.hasValue(val2)) {
            val2 = BigDecimal.ZERO;
        }
        return val1.subtract(val2);
    }

}
