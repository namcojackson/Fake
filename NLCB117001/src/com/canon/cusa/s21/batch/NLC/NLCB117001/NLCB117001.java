package com.canon.cusa.s21.batch.NLC.NLCB117001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.PHYS_INVTY_ADJTMsg;
import business.db.PHYS_INVTY_CNT_DTLTMsg;
import business.db.PHYS_INVTY_CNT_HDRTMsg;
import business.db.PHYS_INVTY_CTRLTMsg;
import business.parts.NLZC004001PMsg;
import business.parts.NLZC061001PMsg;
import business.parts.NLZC063001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC004001.NLZC004001;
import com.canon.cusa.s21.api.NLZ.NLZC004001.constant.NLZC004001Constant;
import com.canon.cusa.s21.api.NLZ.NLZC061001.NLZC061001;
import com.canon.cusa.s21.api.NLZ.NLZC063001.NLZC063001;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_ADJ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_CNT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Adjust Physical Inventory from Tech PI
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/10/2018   CITS            Y.Iwasaki       Create          QC#10572
 * 05/07/2019   CITS            T.Tokutomi      Update          QC#50029
 * </pre>
 */

public class NLCB117001 extends S21BatchMain {

    // *********************************************************
    // Instance Variables: Basic
    // *********************************************************

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    /** Transaction table accessor */
    private S21TransactionTableAccessor trxAccess = new S21TransactionTableAccessor();

    /** User Profile Service */
    private S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList = new ArrayList<Map<String, String>>();

    /** Counter of Records: Successful */
    private int successCount = 0;

    /** Counter of Records: Error */
    private int errorCount = 0;

    /** Termination Code of Execution Process */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Adjustment Date. */
    private String adjSubmtTs;

    // *********************************************************
    // Instance Variables: Constant values on load
    // *********************************************************

    // *********************************************************
    // Logics
    // *********************************************************

    /**
     * main
     * @param args
     */
    public static void main(String[] args) {

        (new NLCB117001()).executeBatch(NLCB117001.class.getSimpleName());
    }

    /**
     * putError
     * @param msgId MessageId
     * @param msgParam MessageParameters
     */
    private void putError(String msgId, String[] msgParam) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
        errMsgList.add(mailParam);

        S21InfoLogOutput.println(msgId, msgParam);
    }

    /**
     * convertToMap
     * @param rs ResultSet
     * @return Map<String, Object>
     * @throws SQLException
     */
    private Map<String, Object> convertToMap(ResultSet rs) throws SQLException {
        Map<String, Object> result = new HashMap<String, Object>();

        ResultSetMetaData meta = rs.getMetaData();
        int count = meta.getColumnCount();
        for (int n = 1; n <= count; ++n) {
            String name = meta.getColumnName(n);
            Object value = rs.getObject(n);

            result.put(name, value);
        }

        return result;
    }

    /**
     * initRoutine
     */
    @Override
    protected void initRoutine() {

        // Initialize Variables
        this.glblCmpyCd = profileService.getGlobalCompanyCode();

        adjSubmtTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
        if (!ZYPCommonFunc.hasValue(adjSubmtTs)) {
            throw new S21AbendException(NLCB117001Constant.ZZM9000E, new String[] {"Sales Date" });
        }

    }

    /**
     * mainRoutine
     */
    @Override
    protected void mainRoutine() {

        try {
            completeTechPI();
        } catch (S21AbendException e) {
            rollback();
            throw e;
        } finally {
            if (errMsgList.size() > 0) {
                this.termCd = TERM_CD.WARNING_END;
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(NLCB117001Constant.BUSINESS_ID, errMsgList);
                commit();
                // rollback();
            }
        }
    }

    /**
     * termRoutine
     */
    @Override
    protected void termRoutine() {

        int totalCount = successCount + errorCount;

        // The number of cases : Insert is output
        S21InfoLogOutput.println(NLCB117001Constant.ZZBM0009I, new String[] {NLCB117001Constant.BUSINESS_ID, "completed", Integer.toString(totalCount) });

        // Setting of termination code
        setTermState(termCd, successCount, errorCount, totalCount);
    }

    private void xxxSampleLogics() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
    }

    private void completeTechPI() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        execParam.setFetchSize(NLCB117001Constant.FETCH_SIZE);

        // Find opened PHYS_INVTY_NUM
        PreparedStatement pinStmt = null;
        ResultSet pinRs = null;
        try {
            Map<String, Object> pinBindParam = new HashMap<String, Object>();
            pinBindParam.put(NLCB117001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
            pinBindParam.put(NLCB117001Constant.COL_PHYS_INVTY_STS_CD, PHYS_INVTY_STS.OPEN);
            pinBindParam.put(NLCB117001Constant.COL_PHYS_INVTY_CNT_STS_CD, PHYS_INVTY_CNT_STS.COUNTING);

            pinStmt = this.ssmLLClient.createPreparedStatement("getPhysInvtyNum", pinBindParam, execParam);
            pinRs = pinStmt.executeQuery();

            while (pinRs.next()) {
                // Process in each PHYS_INVTY_NUM
                String physInvtyNum = pinRs.getString(NLCB117001Constant.COL_PHYS_INVTY_NUM);

                // Remove HDR/DTL records corresponding to
                // PHYS_INVTY_NUM - in case of re-run.
                if (!removePhysInvtyCntHdrDtls(glblCmpyCd, physInvtyNum)) {
                    // Error
                    rollback();
                    ++errorCount;
                    continue;
                }

                // Find details related to HDR_PK and PHYS_INVTY_NUM
                PreparedStatement picStmt = null;
                ResultSet picRs = null;
                boolean success = true;
                boolean isVariant = false;
                try {
                    Map<String, Object> picBindParam = new HashMap<String, Object>();
                    picBindParam.put(NLCB117001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
                    picBindParam.put(NLCB117001Constant.COL_PHYS_INVTY_NUM, physInvtyNum);
                    picBindParam.put(NLCB117001Constant.COL_LOC_STS_CD, LOC_STS.DC_STOCK);
                    picBindParam.put(NLCB117001Constant.COL_STK_STS_CD, STK_STS.GOOD);
                    picBindParam.put(NLCB117001Constant.COL_LOC_TP_CD, LOC_TP.TECHNICIAN);
                    picBindParam.put(NLCB117001Constant.COL_SVC_MACH_MSTR_STS_CD, new String[] {SVC_MACH_MSTR_STS.CREATED, SVC_MACH_MSTR_STS.REMOVED });

                    picStmt = this.ssmLLClient.createPreparedStatement("getPhysInvtyCntDtls", picBindParam, execParam);
                    picRs = picStmt.executeQuery();

                    BigDecimal lastPicHdrPk = BigDecimal.ONE.negate();
                    BigDecimal newPicHdrPk = BigDecimal.ONE.negate();
                    while (picRs.next()) {
                        // Process in each DTL_PK
                        Map<String, Object> rec = convertToMap(picRs);

                        BigDecimal picHdrPk = (BigDecimal) rec.get(NLCB117001Constant.COL_PHYS_INVTY_CNT_HDR_PK);

                        // Check variance
                        success = evaludateVariance(rec);
                        if (!success) {
                            // Error
                            break;
                        }
                        if (ZYPConstant.FLG_ON_Y.equals(rec.get(NLCB117001Constant.COL_ADJ_VAR_FLG))) {
                            isVariant = true;
                        }

                        // Re-number new PK
                        rec.put(NLCB117001Constant.COL_PHYS_INVTY_CNT_HDR_NEW_PK, newPicHdrPk);
                        // rec.put(NLCB117001Constant.COL_PHYS_INVTY_CNT_DTL_NEW_PK,
                        // ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_CNT_DTL_SQ));
                        rec.put(NLCB117001Constant.COL_PHYS_INVTY_CNT_DTL_NEW_PK, ZYPOracleSeqAccessor.getNumberBigDecimal("PHYS_INVTY_CNT_DTL_SQ"));

                        if (lastPicHdrPk.compareTo(picHdrPk) != 0) {
                            // New header. Insert new header
                            // newPicHdrPk=ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_CNT_HDR_SQ);
                            newPicHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal("PHYS_INVTY_CNT_HDR_SQ");
                            rec.put(NLCB117001Constant.COL_PHYS_INVTY_CNT_HDR_NEW_PK, newPicHdrPk);

                            success = insertPhysInvtyCntHdr(rec);
                            if (!success) {
                                break;
                            }

                            lastPicHdrPk = picHdrPk;
                        }

                        success = insertPhysInvtyCntDtl(rec);
                        if (!success) {
                            break;
                        }
                    }
                } catch (SQLException e) {
                    EZDDebugOutput.println(NLCB117001Constant.DEBUG_MSG_LVL, e.getMessage(), this);
                    sqlExceptionHandler(e);
                } finally {
                    S21SsmLowLevelCodingClient.closeResource(picStmt, picRs);
                }

                if (!success) {
                    // Error
                    rollback();
                    ++errorCount;
                    continue;
                }

                success = updatePhysInvtyCntHdrs(glblCmpyCd, physInvtyNum);
                if (!success) {
                    // Error
                    rollback();
                    ++errorCount;
                    continue;
                }

                if (isVariant) {

                    // QC#50029 Update.Delete Workflow.
                    success = updateAdjustmentOrder(physInvtyNum);
                    if (success) {
                        success = closeTechPiVariance(glblCmpyCd, physInvtyNum);
                    }
                } else {
                    // Update
                    success = updatePhysInvtyCtrls(glblCmpyCd, physInvtyNum, PHYS_INVTY_CNT_STS.PI_COMPLETED);
                    if (success) {
                        success = closeTechPI(glblCmpyCd, physInvtyNum);
                    }
                }

                if (!success) {
                    // Error
                    rollback();
                    ++errorCount;
                    continue;
                }

                commit();
                // rollback();
                ++successCount;
            }
        } catch (SQLException e) {
            EZDDebugOutput.println(NLCB117001Constant.DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(pinStmt, pinRs);
        }
    }

    private List<Map<String, Object>> getPhysInvtyCntHdrsByPhysInvtyNum(String glblCmpyCd, String physInvtyNum) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        execParam.setFetchSize(NLCB117001Constant.FETCH_SIZE);

        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(NLCB117001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(NLCB117001Constant.COL_PHYS_INVTY_NUM, physInvtyNum);

        return this.ssmBatchClient.queryObjectList("getPhysInvtyCntHdrsByPhysInvtyNum", bindParam, execParam);
    }

    private List<Map<String, Object>> getPhysInvtyCntDtlsByPhysInvtyNum(String glblCmpyCd, String physInvtyNum) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        execParam.setFetchSize(NLCB117001Constant.FETCH_SIZE);

        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(NLCB117001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(NLCB117001Constant.COL_PHYS_INVTY_NUM, physInvtyNum);

        return this.ssmBatchClient.queryObjectList("getPhysInvtyCntDtlsByPhysInvtyNum", bindParam, execParam);
    }

    private boolean removePhysInvtyCntHdrDtls(String glblCmpyCd, String physInvtyNum) {
        // PHYS_INVTY_CNT_DTL
        List<Map<String, Object>> picDtlList = getPhysInvtyCntDtlsByPhysInvtyNum(glblCmpyCd, physInvtyNum);
        for (Map<String, Object> rec : picDtlList) {
            BigDecimal picDtlPk = (BigDecimal) rec.get(NLCB117001Constant.COL_PHYS_INVTY_CNT_DTL_PK);

            PHYS_INVTY_CNT_DTLTMsg tMsg = new PHYS_INVTY_CNT_DTLTMsg();

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.physInvtyCntDtlPk, picDtlPk);

            EZDTBLAccessor.logicalRemove(tMsg);
        }

        // PHYS_INVTY_CNT_HDR
        List<Map<String, Object>> picHdrList = getPhysInvtyCntHdrsByPhysInvtyNum(glblCmpyCd, physInvtyNum);
        for (Map<String, Object> rec : picHdrList) {
            BigDecimal picHdrPk = (BigDecimal) rec.get(NLCB117001Constant.COL_PHYS_INVTY_CNT_HDR_PK);

            PHYS_INVTY_CNT_HDRTMsg tMsg = new PHYS_INVTY_CNT_HDRTMsg();

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.physInvtyCntHdrPk, picHdrPk);

            EZDTBLAccessor.logicalRemove(tMsg);
        }

        return true;
    }

    private boolean evaludateVariance(Map<String, Object> rec) {

        String sysTime = ZYPDateUtil.getCurrentSystemTime(NLCB117001Constant.FMT_YYYYMMDDHHMMSSSSS);
        String adjVarFlg = ZYPConstant.FLG_OFF_N;
        BigDecimal adjVarQty = BigDecimal.ZERO;
        BigDecimal adjVarAmt = BigDecimal.ZERO;
        String rcvSerTakeFlg = (String) rec.get(NLCB117001Constant.COL_RCV_SER_TAKE_FLG);

        if (ZYPConstant.FLG_ON_Y.equals(rcvSerTakeFlg)) {
            // Serial items
            // Serial item won't be registered by handy terminal. This
            // logic won't be used in this batch.
            BigDecimal cntInpQty = (BigDecimal) rec.get(NLCB117001Constant.COL_CNT_INP_QTY);
            String svcMachMstrAvalFlg = (String) rec.get(NLCB117001Constant.COL_SVC_MACH_MSTR_AVAL_FLG);

            rec.put(NLCB117001Constant.COL_LTST_CNT_INP_QTY, cntInpQty);
            rec.put(NLCB117001Constant.COL_LTST_CNT_INP_TS, rec.get(NLCB117001Constant.COL_FIRST_CNT_INP_TS));
            rec.put(NLCB117001Constant.COL_INVTY_REGD_TS, sysTime);

            if (ZYPConstant.FLG_ON_Y.equals(svcMachMstrAvalFlg)) {
                rec.put(NLCB117001Constant.COL_INVTY_AVAL_DTL_QTY, BigDecimal.ONE);
            } else {
                rec.put(NLCB117001Constant.COL_INVTY_AVAL_DTL_QTY, BigDecimal.ZERO);
            }

            if (BigDecimal.ZERO.compareTo(cntInpQty) != 0 && ZYPConstant.FLG_OFF_N.equals(svcMachMstrAvalFlg)) {
                // Item exists on hand but not in invty
                adjVarFlg = ZYPConstant.FLG_ON_Y;
                adjVarQty = cntInpQty;
            } else if (BigDecimal.ZERO.compareTo(cntInpQty) == 0 && ZYPConstant.FLG_ON_Y.equals(svcMachMstrAvalFlg)) {
                // Item exists in invty but not on hand
                adjVarFlg = ZYPConstant.FLG_ON_Y;
                adjVarQty = BigDecimal.ONE.negate();
            }
        } else {
            // Non-Serial items
            BigDecimal cntInpQty = (BigDecimal) rec.get(NLCB117001Constant.COL_CNT_INP_QTY);
            BigDecimal invtyAvalQty = (BigDecimal) rec.get(NLCB117001Constant.COL_INVTY_AVAL_QTY);

            rec.put(NLCB117001Constant.COL_LTST_CNT_INP_QTY, cntInpQty);
            rec.put(NLCB117001Constant.COL_LTST_CNT_INP_TS, rec.get(NLCB117001Constant.COL_FIRST_CNT_INP_TS));
            rec.put(NLCB117001Constant.COL_INVTY_REGD_TS, sysTime);
            rec.put(NLCB117001Constant.COL_INVTY_AVAL_DTL_QTY, invtyAvalQty);

            adjVarQty = cntInpQty.subtract(invtyAvalQty);
            if (BigDecimal.ZERO.compareTo(adjVarQty) != 0) {
                adjVarFlg = ZYPConstant.FLG_ON_Y;
            }
        }

        if (BigDecimal.ZERO.compareTo(adjVarQty) != 0) {
            adjVarFlg = ZYPConstant.FLG_ON_Y;

            NLXC001001GetInventoryItemCostBean bean = new NLXC001001GetInventoryItemCostBean();
            bean.setGlblCmpyCd(glblCmpyCd);
            bean.setInvtyLocCd((String) rec.get(NLCB117001Constant.COL_WH_CD));
            bean.setMdseCd((String) rec.get(NLCB117001Constant.COL_MDSE_CD));
            bean.setQty(adjVarQty);
            NLXC001001GetInventoryItemCost.getInventoryItemCost(bean);
            List<String> errList = bean.getErrList();
            if (errList.size() > 0) {
                putError(errList.get(0), new String[0]);
                return false;
            }

            adjVarAmt = bean.getTotPrcAmt();
        }

        rec.put(NLCB117001Constant.COL_ADJ_VAR_FLG, adjVarFlg);
        rec.put(NLCB117001Constant.COL_ADJ_VAR_QTY, adjVarQty);
        rec.put(NLCB117001Constant.COL_ADJ_VAR_AMT, adjVarAmt);
        // rec.put(NLCB117001Constant.COL_, );

        return true;
    }

    private boolean insertPhysInvtyCntHdr(Map<String, Object> rec) {

        PHYS_INVTY_CNT_HDRTMsg hdrTMsg = new PHYS_INVTY_CNT_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(hdrTMsg.glblCmpyCd, (String) rec.get(NLCB117001Constant.COL_GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(hdrTMsg.physInvtyCntHdrPk, (BigDecimal) rec.get(NLCB117001Constant.COL_PHYS_INVTY_CNT_HDR_NEW_PK));
        ZYPEZDItemValueSetter.setValue(hdrTMsg.physInvtyCtrlPk, (BigDecimal) rec.get(NLCB117001Constant.COL_PHYS_INVTY_CTRL_PK));
        ZYPEZDItemValueSetter.setValue(hdrTMsg.physInvtyCtrlNm, (String) rec.get(NLCB117001Constant.COL_PHYS_INVTY_CTRL_NM));
        ZYPEZDItemValueSetter.setValue(hdrTMsg.physInvtyDt, (String) rec.get(NLCB117001Constant.COL_PHYS_INVTY_DT));
        ZYPEZDItemValueSetter.setValue(hdrTMsg.whCd, (String) rec.get(NLCB117001Constant.COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(hdrTMsg.rtlWhCd, (String) rec.get(NLCB117001Constant.COL_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(hdrTMsg.rtlSwhCd, (String) rec.get(NLCB117001Constant.COL_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(hdrTMsg.mdseCd, (String) rec.get(NLCB117001Constant.COL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(hdrTMsg.stkStsCd, STK_STS.GOOD);
        ZYPEZDItemValueSetter.setValue(hdrTMsg.techTocCd, (String) rec.get(NLCB117001Constant.COL_TECH_TOC_CD));
        ZYPEZDItemValueSetter.setValue(hdrTMsg.sysCntRegdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(hdrTMsg.firstCntInpQty, (BigDecimal) rec.get(NLCB117001Constant.COL_FIRST_CNT_INP_QTY));
        ZYPEZDItemValueSetter.setValue(hdrTMsg.firstCntInpTs, (String) rec.get(NLCB117001Constant.COL_FIRST_CNT_INP_TS));
        ZYPEZDItemValueSetter.setValue(hdrTMsg.ltstCntInpQty, (BigDecimal) rec.get(NLCB117001Constant.COL_FIRST_CNT_INP_QTY));
        ZYPEZDItemValueSetter.setValue(hdrTMsg.ltstCntInpTs, (String) rec.get(NLCB117001Constant.COL_FIRST_CNT_INP_TS));
        ZYPEZDItemValueSetter.setValue(hdrTMsg.invtyAvalQty, (BigDecimal) rec.get(NLCB117001Constant.COL_INVTY_AVAL_QTY));
        ZYPEZDItemValueSetter.setValue(hdrTMsg.invtyRegdTs, (String) rec.get(NLCB117001Constant.COL_INVTY_REGD_TS));
        ZYPEZDItemValueSetter.setValue(hdrTMsg.adjVarFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(hdrTMsg.adjVarQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(hdrTMsg.adjVarAmt, BigDecimal.ZERO);
        // ZYPEZDItemValueSetter.setValue(hdrTMsg.,
        // rec.get(NLCB117001Constant.COL_));

        EZDTBLAccessor.insert(hdrTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(hdrTMsg.getReturnCode())) {
            throw new S21AbendException(NLCB117001Constant.NLGM0045E, new String[] {NLCB117001Constant.TBL_PHYS_INVTY_CNT_HDR, NLCB117001Constant.COL_PHYS_INVTY_NUM, (String) rec.get(NLCB117001Constant.COL_PHYS_INVTY_NUM) });
        }

        return true;
    }

    private boolean insertPhysInvtyCntDtl(Map<String, Object> rec) {

        PHYS_INVTY_CNT_DTLTMsg dtlTMsg = new PHYS_INVTY_CNT_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(dtlTMsg.glblCmpyCd, (String) rec.get(NLCB117001Constant.COL_GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(dtlTMsg.physInvtyCntDtlPk, (BigDecimal) rec.get(NLCB117001Constant.COL_PHYS_INVTY_CNT_DTL_NEW_PK));
        ZYPEZDItemValueSetter.setValue(dtlTMsg.physInvtyCntHdrPk, (BigDecimal) rec.get(NLCB117001Constant.COL_PHYS_INVTY_CNT_HDR_NEW_PK));
        ZYPEZDItemValueSetter.setValue(dtlTMsg.physInvtyCtrlPk, (BigDecimal) rec.get(NLCB117001Constant.COL_PHYS_INVTY_CTRL_PK));
        ZYPEZDItemValueSetter.setValue(dtlTMsg.rtlWhCd, (String) rec.get(NLCB117001Constant.COL_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(dtlTMsg.rtlSwhCd, (String) rec.get(NLCB117001Constant.COL_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(dtlTMsg.mdseCd, (String) rec.get(NLCB117001Constant.COL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(dtlTMsg.serNum, (String) rec.get(NLCB117001Constant.COL_SER_NUM));
        ZYPEZDItemValueSetter.setValue(dtlTMsg.cntInpQty, (BigDecimal) rec.get(NLCB117001Constant.COL_CNT_INP_QTY));
        ZYPEZDItemValueSetter.setValue(dtlTMsg.firstCntInpQty, (BigDecimal) rec.get(NLCB117001Constant.COL_CNT_INP_QTY));
        ZYPEZDItemValueSetter.setValue(dtlTMsg.firstCntInpTs, (String) rec.get(NLCB117001Constant.COL_FIRST_CNT_INP_TS));
        ZYPEZDItemValueSetter.setValue(dtlTMsg.ltstCntInpQty, (BigDecimal) rec.get(NLCB117001Constant.COL_CNT_INP_QTY));
        ZYPEZDItemValueSetter.setValue(dtlTMsg.ltstCntInpTs, (String) rec.get(NLCB117001Constant.COL_FIRST_CNT_INP_TS));
        ZYPEZDItemValueSetter.setValue(dtlTMsg.invtyAvalQty, (BigDecimal) rec.get(NLCB117001Constant.COL_INVTY_AVAL_DTL_QTY));
        ZYPEZDItemValueSetter.setValue(dtlTMsg.invtyRegdTs, (String) rec.get(NLCB117001Constant.COL_INVTY_REGD_TS));
        ZYPEZDItemValueSetter.setValue(dtlTMsg.adjVarFlg, (String) rec.get(NLCB117001Constant.COL_ADJ_VAR_FLG));
        ZYPEZDItemValueSetter.setValue(dtlTMsg.adjVarQty, (BigDecimal) rec.get(NLCB117001Constant.COL_ADJ_VAR_QTY));
        ZYPEZDItemValueSetter.setValue(dtlTMsg.adjVarAmt, (BigDecimal) rec.get(NLCB117001Constant.COL_ADJ_VAR_AMT));
        ZYPEZDItemValueSetter.setValue(dtlTMsg.physInvtyAdjStsCd, PHYS_INVTY_ADJ_STS.NONE);
        // ZYPEZDItemValueSetter.setValue(hdrTMsg.,
        // rec.get(NLCB117001Constant.COL_));

        EZDTBLAccessor.insert(dtlTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dtlTMsg.getReturnCode())) {
            throw new S21AbendException(NLCB117001Constant.NLGM0045E, new String[] {NLCB117001Constant.TBL_PHYS_INVTY_CNT_DTL, NLCB117001Constant.COL_PHYS_INVTY_NUM, (String) rec.get(NLCB117001Constant.COL_PHYS_INVTY_NUM) });
        }

        return true;
    }

    private boolean updatePhysInvtyCntHdrs(String glblCmpyCd, String physInvtyNum) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        execParam.setFetchSize(NLCB117001Constant.FETCH_SIZE);

        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(NLCB117001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(NLCB117001Constant.COL_PHYS_INVTY_NUM, physInvtyNum);

        List<Map<String, Object>> list = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getPhysInvtyCntHdrs", bindParam, execParam);
        for (Map<String, Object> res : list) {
            BigDecimal picHdrPk = (BigDecimal) res.get(NLCB117001Constant.COL_PHYS_INVTY_CNT_HDR_PK);
            BigDecimal adjVarYCnt = (BigDecimal) res.get(NLCB117001Constant.COL_ADJ_VAR_Y_CNT);
            BigDecimal adjVarQty = (BigDecimal) res.get(NLCB117001Constant.COL_ADJ_VAR_QTY);
            BigDecimal adjVarAmt = (BigDecimal) res.get(NLCB117001Constant.COL_ADJ_VAR_AMT);

            PHYS_INVTY_CNT_HDRTMsg hdrTMsg = new PHYS_INVTY_CNT_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(hdrTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(hdrTMsg.physInvtyCntHdrPk, picHdrPk);
            hdrTMsg = (PHYS_INVTY_CNT_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(hdrTMsg);
            if (hdrTMsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(hdrTMsg.getReturnCode())) {
                String adjVarFlg = ZYPConstant.FLG_OFF_N;
                if (BigDecimal.ZERO.compareTo(adjVarYCnt) != 0) {
                    adjVarFlg = ZYPConstant.FLG_ON_Y;
                }
                ZYPEZDItemValueSetter.setValue(hdrTMsg.adjVarFlg, adjVarFlg);
                ZYPEZDItemValueSetter.setValue(hdrTMsg.adjVarQty, adjVarQty);
                ZYPEZDItemValueSetter.setValue(hdrTMsg.adjVarAmt, adjVarAmt);

                EZDTBLAccessor.update(hdrTMsg);
            }
            if (hdrTMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(hdrTMsg.getReturnCode())) {
                throw new S21AbendException(NLCB117001Constant.NLGM0045E, new String[] {NLCB117001Constant.TBL_PHYS_INVTY_CNT_HDR, NLCB117001Constant.COL_PHYS_INVTY_NUM, physInvtyNum });
            }
        }

        return true;
    }

    private List<Map<String, Object>> getPhysInvtyCtrls(String glblCmpyCd, String physInvtyNum) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        execParam.setFetchSize(NLCB117001Constant.FETCH_SIZE);

        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(NLCB117001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(NLCB117001Constant.COL_PHYS_INVTY_NUM, physInvtyNum);

        return this.ssmBatchClient.queryObjectList("getPhysInvtyCtrls", bindParam, execParam);
    }

    private boolean updatePhysInvtyCtrls(String glblCmpyCd, String physInvtyNum, String physInvtyCntStsCd) {

        List<Map<String, Object>> list = getPhysInvtyCtrls(glblCmpyCd, physInvtyNum);

        // Get SystemDate
        String adjSubmtDt = getDateFromTimestamp(adjSubmtTs);

        for (Map<String, Object> rec : list) {
            BigDecimal picPk = (BigDecimal) rec.get(NLCB117001Constant.COL_PHYS_INVTY_CTRL_PK);

            PHYS_INVTY_CTRLTMsg tMsg = new PHYS_INVTY_CTRLTMsg();

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.physInvtyCtrlPk, picPk);

            tMsg = (PHYS_INVTY_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
            if (tMsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                ZYPEZDItemValueSetter.setValue(tMsg.physInvtyCntStsCd, physInvtyCntStsCd);
                // Set ADJ_SUBMT_TS
                ZYPEZDItemValueSetter.setValue(tMsg.adjSubmtTs, adjSubmtTs);

                if (PHYS_INVTY_CNT_STS.PI_COMPLETED.equals(physInvtyCntStsCd)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.adjGrsAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(tMsg.adjNetAmt, BigDecimal.ZERO);
                }

                EZDTBLAccessor.update(tMsg);
            }
            if (tMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NLCB117001Constant.NLGM0045E, new String[] {NLCB117001Constant.TBL_PHYS_INVTY_CTRL, NLCB117001Constant.COL_PHYS_INVTY_NUM, physInvtyNum });
            }
        }

        return true;
    }

    private boolean executeTechPIWorkflow(String glblCmpyCd, String physInvtyNum) {

        String physInvtyCtrlNm = null;
        String physInvtyDt = null;
        String techTocCd = null;
        String techNm = null;
        List<Map<String, Object>> list = getPhysInvtyCtrls(glblCmpyCd, physInvtyNum);
        for (Map<String, Object> rec : list) {
            physInvtyCtrlNm = (String) rec.get(NLCB117001Constant.COL_PHYS_INVTY_CTRL_NM);
            physInvtyDt = (String) rec.get(NLCB117001Constant.COL_PHYS_INVTY_DT);
            techTocCd = (String) rec.get(NLCB117001Constant.COL_TECH_TOC_CD);
            techNm = (String) rec.get(NLCB117001Constant.COL_TECH_NM);
            break;
        }

        NLZC061001PMsg pMsg = new NLZC061001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.salesDate, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(pMsg.xxMode, NLCB117001Constant.VAR_CREATE_MODE);
        ZYPEZDItemValueSetter.setValue(pMsg.physInvtyCtrlNm, physInvtyCtrlNm);
        ZYPEZDItemValueSetter.setValue(pMsg.physInvtyDt, physInvtyDt);
        ZYPEZDItemValueSetter.setValue(pMsg.physInvtyNum, physInvtyNum);
        ZYPEZDItemValueSetter.setValue(pMsg.techTocCd, techTocCd);
        ZYPEZDItemValueSetter.setValue(pMsg.techNm, techNm);

        String userInfo = profileService.getContextUserInfo().getUserId();
        if (userInfo.length() > NLCB117001Constant.DATE_ENDINDEX) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxUserId, userInfo.substring(NLCB117001Constant.DATE_BEGININDEX, NLCB117001Constant.DATE_ENDINDEX));
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.xxUserId, userInfo);
        }

        NLZC061001 api = new NLZC061001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);
        if (pMsg.xxMsgIdList.getValidCount() != 0) {
            putError(pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), new String[0]);
            return false;
        }

        return true;
    }

    private boolean closeTechPI(String glblCmpyCd, String physInvtyNum) {
        NLZC063001PMsg pMsg = new NLZC063001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.salesDate, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(pMsg.physInvtyCntStsCd, PHYS_INVTY_CNT_STS.PI_COMPLETED);
        ZYPEZDItemValueSetter.setValue(pMsg.physInvtyNum, physInvtyNum);

        NLZC063001 api = new NLZC063001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);
        if (pMsg.xxMsgIdList.getValidCount() != 0) {
            putError(pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), new String[0]);
            return false;
        }

        return true;
    }

    private boolean closeTechPiVariance(String glblCmpyCd, String physInvtyNum) {
        NLZC063001PMsg pMsg = new NLZC063001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.salesDate, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(pMsg.physInvtyCntStsCd, PHYS_INVTY_CNT_STS.PI_VARIANCE);
        ZYPEZDItemValueSetter.setValue(pMsg.physInvtyNum, physInvtyNum);

        NLZC063001 api = new NLZC063001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);
        if (pMsg.xxMsgIdList.getValidCount() != 0) {
            putError(pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), new String[0]);
            return false;
        }

        return true;
    }
    
    /**
     * getDateFromTimestamp.
     * @param timestamp String
     * @return date String
     */
    private String getDateFromTimestamp(String timestamp) {

        String date = "";

        if (ZYPCommonFunc.hasValue(timestamp)) {
            date = timestamp.substring(NLCB117001Constant.DATE_BEGININDEX, NLCB117001Constant.DATE_ENDINDEX);
        }

        return date;
    }

    private boolean updateAdjustmentOrder(String physInvtyNum) {

        // Get PhysInfo
        List<Map<String, Object>> resultList = getAdjustmentPiCount(physInvtyNum);

        // Init adjustmentOrder.
        ArrayList<NLZC004001PMsg> pMsgList = new ArrayList<NLZC004001PMsg>();
        List<PHYS_INVTY_ADJTMsg> adjMsgList = new ArrayList<PHYS_INVTY_ADJTMsg>();
        HashMap<Integer, BigDecimal> relnMap = new HashMap<Integer, BigDecimal>();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        String preRtlWhCd = "";
        String preWhCd = "";
        String preMdseCd = "";
        String preStkStsCd = "";
        BigDecimal preAdjVarQty = BigDecimal.ZERO;

        for (Map<String, Object> record : resultList) {

            if (pMsgList.isEmpty()) {
                // Set Header Param
                pMsgList.add(createHeaderParam(slsDt, record));
            }

            // Set Detail Param
            NLZC004001PMsg lastMsg = pMsgList.get(pMsgList.size() - 1);
            if(ZYPConstant.FLG_ON_Y.equals(record.get(NLCB117001Constant.COL_RCV_SER_TAKE_FLG))
                    && preWhCd.equals(record.get(NLCB117001Constant.COL_WH_CD))
                    && preMdseCd.equals(record.get(NLCB117001Constant.COL_MDSE_CD))
                    && preStkStsCd.equals(record.get(NLCB117001Constant.COL_STK_STS_CD))
                    && preAdjVarQty.signum() == ((BigDecimal) record.get(NLCB117001Constant.COL_ADJ_VAR_QTY)).signum()
                    && lastMsg.serialInfoList.getValidCount() < lastMsg.serialInfoList.length()){
                // AddSerial
                addSerial(lastMsg, record);
            } else {
                pMsgList.add(createDetailParam(slsDt, record));
                relnMap.put(pMsgList.size() - 1, (BigDecimal)record.get(NLCB117001Constant.COL_PHYS_INVTY_CNT_HDR_PK));
            }

            // Set Adjustment
            adjMsgList.add(createPIAdjustMsg(record));

            preRtlWhCd = (String) record.get(NLCB117001Constant.COL_RTL_WH_CD);
            preWhCd = (String) record.get(NLCB117001Constant.COL_WH_CD);
            preMdseCd = (String) record.get(NLCB117001Constant.COL_MDSE_CD);
            preStkStsCd = (String) record.get(NLCB117001Constant.COL_STK_STS_CD);
            preAdjVarQty = (BigDecimal) record.get(NLCB117001Constant.COL_ADJ_VAR_QTY);

        }

        // Run API
        NLZC004001 api = new NLZC004001();
        api.execute(pMsgList, ONBATCH_TYPE.BATCH);

        // Check API Result
        boolean isErrorForAPI = false;
        for (NLZC004001PMsg pMsg : pMsgList) {
            if (S21ApiUtil.isXxMsgId(pMsg)) {
                List<String> msgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
                for (String msgId : msgIdList) {
                    // cmt:Error/NG, Warning:OK
                    if (msgId.endsWith("E")) {
                        isErrorForAPI = true;
                    }
                    putError(msgId, new String[0]);
                }
            }
        }

        if (isErrorForAPI) {
            // Error.
            return false;
        }

        // Update physInvtyCntHdr. Set invtyOrdNum.
        for (Map.Entry<Integer, BigDecimal> map : relnMap.entrySet()) {
            NLZC004001PMsg pMsg = pMsgList.get(map.getKey());

            PHYS_INVTY_CNT_HDRTMsg inMsg = new PHYS_INVTY_CNT_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCntHdrPk, map.getValue());
            PHYS_INVTY_CNT_HDRTMsg tMsg = (PHYS_INVTY_CNT_HDRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (tMsg == null) {
                putError(NLCB117001Constant.NLZM2454E, new String[0]);
                return false;
            }
            ZYPEZDItemValueSetter.setValue(tMsg.invtyOrdNum, pMsg.invtyOrdNum);
            S21ApiTBLAccessor.update(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                putError(NLCB117001Constant.NLZM2454E, new String[0]);
                return false;
            }
        }

        // Insert PhysInvtyAdj table.
        if (!piAdjustInsert(adjMsgList, pMsgList)) {
            // Insert Error.
            return false;
        }

        // Summary Update.
        if (!summaryPICountHeader(physInvtyNum, pMsgList)){
            // Error.
            return false;
        }
        if (!summaryPIControl(physInvtyNum)){
            // Error.
            return false;
        }
        
        
        return true;
    }

    private List<Map<String, Object>> getAdjustmentPiCount(String physInvtyNum) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        execParam.setFetchSize(NLCB117001Constant.FETCH_SIZE);

        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(NLCB117001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(NLCB117001Constant.COL_PHYS_INVTY_NUM, physInvtyNum);
        bindParam.put(NLCB117001Constant.COL_DC_STOCK, LOC_STS.DC_STOCK); // ADJ_VAR_FLG
        bindParam.put(NLCB117001Constant.COL_FLG_ON_Y, ZYPConstant.FLG_ON_Y); // LOC_STS_CD

        return this.ssmBatchClient.queryObjectList("adjustmentPiCount", bindParam, execParam);
    }

    // -----------------------------------------------------------
    // Create NLZC0040 Parameter
    // -----------------------------------------------------------
    /**
     * Header Param
     * @param tokenObj NLZC061001TokenObject
     * @param map Map<String, Object>
     * @return NLZC004001PMsg
     */
    private NLZC004001PMsg createHeaderParam(String slsDt, Map<String, Object> record) {
        NLZC004001PMsg pMsg = new NLZC004001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcTpCd, NLZC004001Constant.PROC_TP_CRAT_CLO);
        ZYPEZDItemValueSetter.setValue(pMsg.xxDtTpCd, NLZC004001Constant.DT_TP_HDR);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.invtyOrdTpCd, INVTY_ORD_TP.ADJUSTMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, (String) record.get(NLCB117001Constant.COL_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(pMsg.adjTrxTpCd, ADJ_TRX_TP.PHYSICAL_INVENTORY_ADJUSTMENT);
        String physInvtyAdjNm = (String) record.get(NLCB117001Constant.COL_PHYS_INVTY_ADJ_NM);
        if (ZYPCommonFunc.hasValue(physInvtyAdjNm)) {
            if (physInvtyAdjNm.length() > NLCB117001Constant.MAX_NM_LENGTH) {
                ZYPEZDItemValueSetter.setValue(pMsg.firstInvtyOrdCmntTxt, physInvtyAdjNm.substring(0, NLCB117001Constant.MAX_NM_LENGTH));
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.firstInvtyOrdCmntTxt, physInvtyAdjNm);
            }
        }

        return pMsg;
    }

    /**
     * Detail Param
     * @param tokenObj NLZC061001TokenObject
     * @param map Map<String, Object>
     * @return NLZC004001PMsg
     */
    private NLZC004001PMsg createDetailParam(String slsDt, Map<String, Object> record) {
        NLZC004001PMsg pMsg = new NLZC004001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcTpCd, NLZC004001Constant.PROC_TP_CRAT_CLO);
        ZYPEZDItemValueSetter.setValue(pMsg.xxDtTpCd, NLZC004001Constant.DT_TP_DTL);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
        BigDecimal adjVarQty = (BigDecimal) record.get(NLCB117001Constant.COL_ADJ_VAR_QTY);
        if (adjVarQty.compareTo(BigDecimal.ZERO) == 1) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NLZC004001Constant.RQST_STOCK_IN);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NLZC004001Constant.RQST_STOCK_OUT);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.invtyOrdTpCd, INVTY_ORD_TP.ADJUSTMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, (String) record.get(NLCB117001Constant.COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(pMsg.adjTrxTpCd, ADJ_TRX_TP.PHYSICAL_INVENTORY_ADJUSTMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, (String) record.get(NLCB117001Constant.COL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, (String) record.get(NLCB117001Constant.COL_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd_D1, (String) record.get(NLCB117001Constant.COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.locStsCd_D1, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pMsg.ordQty, adjVarQty);
        String physInvtyAdjNm = (String) record.get(NLCB117001Constant.COL_PHYS_INVTY_ADJ_NM);
        if (ZYPCommonFunc.hasValue(physInvtyAdjNm)) {
            if (physInvtyAdjNm.length() > NLCB117001Constant.MAX_NM_LENGTH) {
                ZYPEZDItemValueSetter.setValue(pMsg.firstInvtyOrdCmntTxt, physInvtyAdjNm.substring(0, NLCB117001Constant.MAX_NM_LENGTH));
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.firstInvtyOrdCmntTxt, physInvtyAdjNm);
            }
        }

        ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, (BigDecimal) record.get(NLCB117001Constant.COL_SVC_CONFIG_MSTR_PK));
        if (ZYPConstant.FLG_ON_Y.equals(record.get(NLCB117001Constant.COL_RCV_SER_TAKE_FLG))) {
            ZYPEZDItemValueSetter.setValue(pMsg.serialInfoList.no(pMsg.serialInfoList.getValidCount()).serNum, (String) record.get(NLCB117001Constant.COL_SER_NUM));
            pMsg.serialInfoList.setValidCount(pMsg.serialInfoList.getValidCount() + 1);
        }
        return pMsg;
    }
    
    /**
     * Add Serial
     * @param pMsg NLZC004001PMsg
     * @param tokenObj NLZC061001TokenObject
     * @param map Map<String, Object>
     */
    private void addSerial(NLZC004001PMsg pMsg, Map<String, Object> map) {
        BigDecimal adjVarQty = (BigDecimal) map.get(NLCB117001Constant.COL_ADJ_VAR_QTY);
        ZYPEZDItemValueSetter.setValue(pMsg.ordQty, pMsg.ordQty.getValue().add(adjVarQty));
        ZYPEZDItemValueSetter.setValue(pMsg.serialInfoList.no(pMsg.serialInfoList.getValidCount()).serNum, (String) map.get(NLCB117001Constant.COL_SER_NUM));
        pMsg.serialInfoList.setValidCount(pMsg.serialInfoList.getValidCount() + 1);
    }

    private PHYS_INVTY_ADJTMsg createPIAdjustMsg(Map<String, Object> map) {
        PHYS_INVTY_ADJTMsg tMsg = new PHYS_INVTY_ADJTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.physInvtyCtrlPk, (BigDecimal) map.get(NLCB117001Constant.COL_PHYS_INVTY_CTRL_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.whCd, (String) map.get(NLCB117001Constant.COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, (String) map.get(NLCB117001Constant.COL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.stkStsCd, (String) map.get(NLCB117001Constant.COL_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.adjVarQty, (BigDecimal) map.get(NLCB117001Constant.COL_ADJ_VAR_QTY));
        if (tMsg.adjVarQty.getValue().signum() == 1) {
            ZYPEZDItemValueSetter.setValue(tMsg.adjVarSgnCd, "+");
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.adjVarSgnCd, "-");
        }
        if (ZYPConstant.FLG_ON_Y.equals(map.get(NLCB117001Constant.COL_RCV_SER_TAKE_FLG))) {
            ZYPEZDItemValueSetter.setValue(tMsg.serNum, (String) map.get(NLCB117001Constant.COL_SER_NUM));
        }
        return tMsg;
    }
    
    private boolean piAdjustInsert(List<PHYS_INVTY_ADJTMsg> adjMsgList, List<NLZC004001PMsg> pMsgList) {

        boolean result = true;

        for (PHYS_INVTY_ADJTMsg inMsg : adjMsgList) {
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyAdjPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_ADJ_SQ));
            for (int i = 1; i < pMsgList.size(); i++) {
                NLZC004001PMsg pMsg = pMsgList.get(i);
                if (pMsg.mdseCd.getValue().equals(inMsg.mdseCd.getValue()) && pMsg.invtyLocCd_D1.getValue().equals(inMsg.whCd.getValue()) && pMsg.stkStsCd.getValue().equals(inMsg.stkStsCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(inMsg.invtyOrdNum, pMsg.invtyOrdNum);
                    ZYPEZDItemValueSetter.setValue(inMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk);
                    ZYPEZDItemValueSetter.setValue(inMsg.physInvtyAdjNm, pMsg.firstInvtyOrdCmntTxt);
                }
            }
            S21ApiTBLAccessor.insert(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                putError(NLCB117001Constant.NLZM2453E, new String[0]);
                result = false;
            }
        }

        return result;
    }
    
    private boolean summaryPICountHeader(String physInvtyNum, List<NLZC004001PMsg> pMsgList) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(NLCB117001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        paramMap.put(NLCB117001Constant.COL_PHYS_INVTY_NUM, physInvtyNum);
        paramMap.put(NLCB117001Constant.COL_PHYS_INVTY_ADJ_STS_CD, PHYS_INVTY_ADJ_STS.SKIP_ADJUSTMENT_DUE_TO_ERROR);
        List<Map<String, Object>> result = this.ssmBatchClient.queryObjectList("summaryPICountHeader", paramMap);
        if (result == null) {
            putError(NLCB117001Constant.NLZM2454E, new String[0]);
            return false;
        }

        for (Map<String, Object> record : result) {
            PHYS_INVTY_CNT_HDRTMsg inMsg = new PHYS_INVTY_CNT_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCntHdrPk, (BigDecimal) record.get(NLCB117001Constant.COL_PHYS_INVTY_CNT_HDR_PK));
            PHYS_INVTY_CNT_HDRTMsg outMsg = (PHYS_INVTY_CNT_HDRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (outMsg == null) {
                putError(NLCB117001Constant.NLZM2454E, new String[0]);
                return false;
            }

            ZYPEZDItemValueSetter.setValue(outMsg.adjVarQty, (BigDecimal) record.get(NLCB117001Constant.COL_SUB_TOT_ADJ_VAR_QTY));
            ZYPEZDItemValueSetter.setValue(outMsg.adjVarAmt, (BigDecimal) record.get(NLCB117001Constant.COL_SUB_TOT_ADJ_VAR_AMT));

            for (NLZC004001PMsg pMsg : pMsgList) {
                if (NLZC004001Constant.DT_TP_HDR.equals(pMsg.xxDtTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(outMsg.invtyOrdNum, pMsg.invtyOrdNum);
                    break;
                }
            }
            S21ApiTBLAccessor.update(outMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                putError(NLCB117001Constant.NLZM2454E, new String[0]);
                return false;
            }
        }

        return true;
    }

    private boolean summaryPIControl(String physInvtyNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(NLCB117001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        paramMap.put(NLCB117001Constant.COL_PHYS_INVTY_NUM, physInvtyNum);
        paramMap.put(NLCB117001Constant.COL_PHYS_INVTY_ADJ_STS_CD, PHYS_INVTY_ADJ_STS.SKIP_ADJUSTMENT_DUE_TO_ERROR);
        List<Map<String, Object>> result = this.ssmBatchClient.queryObjectList("summaryPIControl", paramMap);
        if (result == null) {
            putError(NLCB117001Constant.NLCM0163E, new String[0]);
            return false;
        }

        for (Map<String, Object> record : result) {
            PHYS_INVTY_CTRLTMsg inMsg = new PHYS_INVTY_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlPk, (BigDecimal) record.get(NLCB117001Constant.COL_PHYS_INVTY_CTRL_PK));
            PHYS_INVTY_CTRLTMsg outMsg = (PHYS_INVTY_CTRLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (outMsg == null) {
                putError(NLCB117001Constant.NLCM0163E, new String[0]);
                return false;
            }

            ZYPEZDItemValueSetter.setValue(outMsg.adjGrsAmt, (BigDecimal) record.get(NLCB117001Constant.COL_TOT_ADJ_VAR_GRS_AMT));
            ZYPEZDItemValueSetter.setValue(outMsg.adjNetAmt, (BigDecimal) record.get(NLCB117001Constant.COL_TOT_ADJ_VAR_NET_AMT));
            S21ApiTBLAccessor.update(outMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                putError(NLCB117001Constant.NLCM0163E, new String[0]);
                return false;
            }
        }

        return true;
    }
}
