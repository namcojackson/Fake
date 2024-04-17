/**
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB081001;

import static com.canon.cusa.s21.batch.NSA.NSAB081001.constant.NSAB081001Constant.MAX_COMMIT_NUMBER;
import static com.canon.cusa.s21.batch.NSA.NSAB081001.constant.NSAB081001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NSA.NSAB081001.constant.NSAB081001Constant.MSG_ITEM_SALES_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB081001.constant.NSAB081001Constant.NSAM0178E;
import static com.canon.cusa.s21.batch.NSA.NSAB081001.constant.NSAB081001Constant.NSZM0392E;
import static com.canon.cusa.s21.batch.NSA.NSAB081001.constant.NSAB081001Constant.SPACE;
import static com.canon.cusa.s21.batch.NSA.NSAB081001.constant.NSAB081001Constant.TERM_COND_UPDATE_STS_CD_01_UPDATE;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CONTR_DTLTMsg;
import business.db.SVC_TERM_CONDTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * SLA Time Update Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/06/2018   Fujitsu         W.Honda         Create          N/A
 * </pre>
 */
public class NSAB081001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;


    /** Sales Date */
    private String salesDate = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Normal Count */
    private int normalCount;

    /** Error Count */
    private int errorCount;

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Division Number */
    private String divisionNum = null;

    /** Residue */
    private String residue = null;

    /** Get Commit Number */
    private int commitNumber;

    @Override
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NSZM0392E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NSAM0178E, new String[] {MSG_ITEM_SALES_DATE });
        }

        // Get Division Number
        this.divisionNum = getUserVariable1();

        // Get Residue
        this.residue = getUserVariable2();

        // Get Commit Number
        this.commitNumber = getCommitCount();

        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {

        // Accessory
        updateAccTermCond();

        // Main Unit
        updateMainTermCond();

    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);

    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB081001().executeBatch(NSAB081001.class.getSimpleName());
    }

    private void updateAccTermCond() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("divisionNum", this.divisionNum);
        paramMap.put("residue", this.residue);
        // TERM_COND_UPD_STS : '01'(Updated)
        paramMap.put("termCondUpdStsCdUpdated", "01");
        paramMap.put("space", SPACE);

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getChangedAccessoryData", paramMap, execParam);

            rs = stmt.executeQuery();

            SVC_TERM_CONDTMsg svcTermCondMsg = null;

            ArrayList<SVC_TERM_CONDTMsg> insertSvcTermCondMsgList = new ArrayList<SVC_TERM_CONDTMsg>();
            ArrayList<SVC_TERM_CONDTMsg> updateSvcTermCondMsgList = new ArrayList<SVC_TERM_CONDTMsg>();

            BigDecimal preDsContrDtlPk = null;

            while (rs.next()) {
                if (ZYPCommonFunc.hasValue(preDsContrDtlPk) && rs.getBigDecimal("DS_CONTR_DTL_PK").compareTo(preDsContrDtlPk) != 0) {
                    if (insertSvcTermCondMsgList.size() != 0) {
                        // insert SVC_TERM_COND
                        insertSvcTermCond(insertSvcTermCondMsgList);

                        commit();

                        insertSvcTermCondMsgList.clear();
                    }

                    if (updateSvcTermCondMsgList.size() != 0) {
                        // update SVC_TERM_COND
                        updateSvcTermCond(updateSvcTermCondMsgList);

                        commit();

                        updateSvcTermCondMsgList.clear();
                    }
                }

                if (!ZYPCommonFunc.hasValue(rs.getString("SVC_TERM_COND_PK"))) {
                    // set SVC_TERM_COND
                    svcTermCondMsg = setInsertSvcTermCond(rs);
                    insertSvcTermCondMsgList.add(svcTermCondMsg);

                } else {
                    // get SVC_TERM_COND
                    svcTermCondMsg = getUpdateSvcTermCond(rs.getBigDecimal("SVC_TERM_COND_PK"));
                    if (svcTermCondMsg == null) {
                        continue;
                    }
                    ZYPEZDItemValueSetter.setValue(svcTermCondMsg.svcTermAttrbMapValCd, rs.getString("TERM_COND_OPT_VAL_TXT"));
                    updateSvcTermCondMsgList.add(svcTermCondMsg);

                }

                preDsContrDtlPk = rs.getBigDecimal("DS_CONTR_DTL_PK");
            }

            if (insertSvcTermCondMsgList.size() != 0) {
                // insert SVC_TERM_COND
                insertSvcTermCond(insertSvcTermCondMsgList);

                commit();
            }

            if (updateSvcTermCondMsgList.size() != 0) {
                // update SVC_TERM_COND
                updateSvcTermCond(updateSvcTermCondMsgList);

                commit();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }

    }

    private void updateMainTermCond() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("divisionNum", this.divisionNum);
        paramMap.put("residue", this.residue);
        // TERM_COND_UPD_STS : '01'(Updated)
        paramMap.put("termCondUpdStsCdUpdated", "01");
        paramMap.put("space", SPACE);

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getChangedMainUnitData", paramMap, execParam);

            rs = stmt.executeQuery();

            SVC_TERM_CONDTMsg svcTermCondMsg = null;

            ArrayList<SVC_TERM_CONDTMsg> insertSvcTermCondMsgList = new ArrayList<SVC_TERM_CONDTMsg>();
            ArrayList<SVC_TERM_CONDTMsg> updateSvcTermCondMsgList = new ArrayList<SVC_TERM_CONDTMsg>();
            ArrayList<DS_CONTR_DTLTMsg> updateDsContrDtlMsgList = new ArrayList<DS_CONTR_DTLTMsg>();
            ArrayList<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>();

            BigDecimal preDsContrDtlPk = null;

            while (rs.next()) {
                if (ZYPCommonFunc.hasValue(preDsContrDtlPk)
                        && preDsContrDtlPk.compareTo(rs.getBigDecimal("DS_CONTR_DTL_PK")) != 0) {
                    if (insertSvcTermCondMsgList.size() != 0) {
                        // insert SVC_TERM_COND
                        int insertCnt = insertSvcTermCond(insertSvcTermCondMsgList);
                        if (insertCnt != insertSvcTermCondMsgList.size()) {
                            this.errorCount = this.errorCount + (insertSvcTermCondMsgList.size() - insertCnt);
                        }
                        this.normalCount = this.normalCount + insertCnt;
                        insertSvcTermCondMsgList.clear();
                    }

                    if (updateSvcTermCondMsgList.size() != 0) {
                        // update SVC_TERM_COND
                        int updateCnt = updateSvcTermCond(updateSvcTermCondMsgList);
                        if (updateCnt != updateSvcTermCondMsgList.size()) {
                            this.errorCount = this.errorCount + (updateSvcTermCondMsgList.size() - updateCnt);
                        }
                        this.normalCount = this.normalCount + updateCnt;
                        updateSvcTermCondMsgList.clear();
                    }

                    if (updateDsContrDtlMsgList.size() != 0) {

                        updateDsContrDtl(updateDsContrDtlMsgList);
                        updateDsContrDtlMsgList.clear();
                        dsContrDtlPkList.clear();
                    }

                    commit();
                }

                if (!ZYPCommonFunc.hasValue(rs.getString("SVC_TERM_COND_PK"))) {
                    // set SVC_TERM_COND
                    svcTermCondMsg = setInsertSvcTermCond(rs);
                    insertSvcTermCondMsgList.add(svcTermCondMsg);
                } else {
                    // get SVC_TERM_COND
                    svcTermCondMsg = getUpdateSvcTermCond(rs.getBigDecimal("SVC_TERM_COND_PK"));
                    if (svcTermCondMsg == null) {
                        this.errorCount++;
                        continue;
                    }
                    ZYPEZDItemValueSetter.setValue(svcTermCondMsg.svcTermAttrbMapValCd, rs.getString("TERM_COND_OPT_VAL_TXT"));
                    updateSvcTermCondMsgList.add(svcTermCondMsg);
                }

                // get DS_CONTR_DTL
                if (!dsContrDtlPkList.contains(rs.getBigDecimal("ADDL_DS_CONTR_DTL_PK"))) {
                    DS_CONTR_DTLTMsg dsContrDtlMsg = getUpdateDsContrDtl(rs.getBigDecimal("ADDL_DS_CONTR_DTL_PK"));
                    ZYPEZDItemValueSetter.setValue(dsContrDtlMsg.termCondUpdStsCd, TERM_COND_UPDATE_STS_CD_01_UPDATE);

                    updateDsContrDtlMsgList.add(dsContrDtlMsg);
                    dsContrDtlPkList.add(rs.getBigDecimal("ADDL_DS_CONTR_DTL_PK"));
                }

                preDsContrDtlPk = rs.getBigDecimal("DS_CONTR_DTL_PK");
            }

            if (insertSvcTermCondMsgList.size() != 0) {
                // insert SVC_TERM_COND
                int insertCnt = insertSvcTermCond(insertSvcTermCondMsgList);
                if (insertCnt != insertSvcTermCondMsgList.size()) {
                    this.errorCount = this.errorCount + (insertSvcTermCondMsgList.size() - insertCnt);
                }
                this.normalCount = this.normalCount + insertCnt;
            }

            if (updateSvcTermCondMsgList.size() != 0) {
                // update SVC_TERM_COND
                int updateCnt = updateSvcTermCond(updateSvcTermCondMsgList);
                if (updateCnt != updateSvcTermCondMsgList.size()) {
                    this.errorCount = this.errorCount + (updateSvcTermCondMsgList.size() - updateCnt);
                }
                this.normalCount = this.normalCount + updateCnt;
            }

            if (updateDsContrDtlMsgList.size() != 0) {

                updateDsContrDtl(updateDsContrDtlMsgList);
                updateDsContrDtlMsgList.clear();
                dsContrDtlPkList.clear();
            }

            commit();
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }

    }

    private SVC_TERM_CONDTMsg setInsertSvcTermCond(ResultSet rs) throws SQLException {

        SVC_TERM_CONDTMsg inMsg = new SVC_TERM_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.svcTermCondPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TERM_COND_SQ));
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrPk, rs.getBigDecimal("DS_CONTR_PK"));
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrDtlPk, rs.getBigDecimal("DS_CONTR_DTL_PK"));
        ZYPEZDItemValueSetter.setValue(inMsg.svcTermCondAttrbPk, rs.getBigDecimal("SVC_TERM_COND_ATTRB_PK"));
        ZYPEZDItemValueSetter.setValue(inMsg.svcTermAttrbMapValCd, rs.getString("TERM_COND_OPT_VAL_TXT"));

        return inMsg;
    }

    private SVC_TERM_CONDTMsg getUpdateSvcTermCond(BigDecimal svcTermCondPk) {
        // get SVC_TERM_COND
        SVC_TERM_CONDTMsg inMsg = new SVC_TERM_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.svcTermCondPk, svcTermCondPk);

        SVC_TERM_CONDTMsg outMsg = (SVC_TERM_CONDTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);

        if (outMsg == null) {
            return null;
        }

        return outMsg;
    }

    private DS_CONTR_DTLTMsg getUpdateDsContrDtl(BigDecimal dsContrDtlPk) {
        // get SVC_TERM_COND
        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrDtlPk, dsContrDtlPk);

        DS_CONTR_DTLTMsg outMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);

        if (outMsg == null) {
            return null;
        }

        return outMsg;
    }

    private int insertSvcTermCond(ArrayList<SVC_TERM_CONDTMsg> inMsgLst) {
        SVC_TERM_CONDTMsg[] inMsgArray;
        inMsgArray = new SVC_TERM_CONDTMsg[inMsgLst.size()];
        return S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));
    }

    private int updateSvcTermCond(ArrayList<SVC_TERM_CONDTMsg> inMsgLst) {
        SVC_TERM_CONDTMsg[] inMsgArray;
        inMsgArray = new SVC_TERM_CONDTMsg[inMsgLst.size()];
        return S21FastTBLAccessor.update(inMsgLst.toArray(inMsgArray));
    }

    private int updateDsContrDtl(ArrayList<DS_CONTR_DTLTMsg> inMsgLst) {
        DS_CONTR_DTLTMsg[] inMsgArray;
        inMsgArray = new DS_CONTR_DTLTMsg[inMsgLst.size()];
        return S21FastTBLAccessor.update(inMsgLst.toArray(inMsgArray));
    }

}
