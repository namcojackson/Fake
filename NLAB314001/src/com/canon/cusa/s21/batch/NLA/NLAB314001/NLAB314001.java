/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */package com.canon.cusa.s21.batch.NLA.NLAB314001;

import static com.canon.cusa.s21.batch.NLA.NLAB314001.constant.NLAB314001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.DS_COND_CONSTTMsg;
import business.db.DS_COND_CONSTTMsgArray;
import business.db.PRT_USG_DLY_SMRYTMsg;
import business.db.PRT_USG_RPT_WRKTMsg;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Create Parts Usage Work Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/13/2015   Hitachi         T.Iwamoto       Create          Create Parts Usage Work Batch
 * 05/12/2016   Hitachi         T.Tomita        Update          QC#8159
 * 08/22/2016   Hitachi         Y.Takeno        Update          QC#13701
 * 10/17/2016   Hitachi         N.Arai          Update          QC#15230
 * </pre>
 */
public class NLAB314001 extends S21BatchMain {
    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface Id */
    private String excuteMode;

    /** Commit Number */
    private int commitNumber;

    /** Normal Count */
    private int normalCount;

    /** Error Count */
    private int errorCount;

    /** Sales Date */
    private String salesDate;

    /** process Date */
    private String processDate;

    /** combDsCondConst */
    private String combDsCondConst;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /**
     * Initialize method.
     */
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NLAM1118E, new String[] {MSG_TXT_GLBL_CMPY_CD });
        }

        // Get excuteMode
        this.excuteMode = S21BatchUtil.getUserVariable1();

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Get Process Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            throw new S21AbendException(NLAM1285E, new String[] {MSG_TXT_SALES_DATE });
        }
// START 2016/10/17 N.Arai [QC#15230, MOD]
//        this.processDate = ZYPDateUtil.addDays(this.salesDate, -1);
        this.processDate = this.salesDate;
// END 2016/10/17 N.Arai [QC#15230, MOD]

        // Get DSCondConst
        this.combDsCondConst = getCombDsCondConst();

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        if (!ZYPCommonFunc.hasValue(this.excuteMode)) {
            insertPartsUsageDailySummary();
            insertPartsUsageReportWork();

        } else if (MODE_1.equals(this.excuteMode)) {
            insertPartsUsageDailySummary();

        } else if (MODE_2.equals(this.excuteMode)) {
            insertPartsUsageReportWork();

        }
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * main method
     * @param args String[]
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NLAB314001().executeBatch(NLAB314001.class.getSimpleName());
    }

    private void insertPartsUsageDailySummary() {
        Map<String, String> deleteParam = new HashMap<String, String>();
        List<PRT_USG_DLY_SMRYTMsg> deleteTMsgList = new ArrayList<PRT_USG_DLY_SMRYTMsg>();
        PreparedStatement deleteStmt = null;
        ResultSet deleteRs = null;

        List<PRT_USG_DLY_SMRYTMsg> inTMsgList = new ArrayList<PRT_USG_DLY_SMRYTMsg>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {

            // delete sameDateData
            deleteParam.put("glblCmpyCd", this.glblCmpyCd);
            deleteParam.put("prcDt", this.processDate);

            deleteStmt = this.ssmLLClient.createPreparedStatement("getPartsUsagePK", deleteParam, execParam);
            deleteRs = deleteStmt.executeQuery();
            while (deleteRs.next()) {
                deleteTMsgList.add(setPrtUsgDlySmryPk(deleteRs));
                if (this.commitNumber == deleteTMsgList.size()) {
                    deletePartsUsageDailySummary(deleteTMsgList);
                    inTMsgList = new ArrayList<PRT_USG_DLY_SMRYTMsg>();
                }
            }
            if (deleteTMsgList.size() > 0) {
                deletePartsUsageDailySummary(deleteTMsgList);
            }
            // Insert Data
            stmt = this.ssmLLClient.createPreparedStatement("getPartsUsageInfo", setDailySummarySearchCondition(), execParam);
            rs = stmt.executeQuery();

            int commitCount = 0;

            while (rs.next()) {

                inTMsgList.add(setDailySummaryCreateValue(rs));

                if (this.commitNumber == inTMsgList.size()) {
                    commitCount = insertPartsUsageDailySummary(inTMsgList);
                    inTMsgList = new ArrayList<PRT_USG_DLY_SMRYTMsg>();
                    this.normalCount += commitCount;
                    commitCount = 0;
                }
            }
            if (inTMsgList.size() > 0) {
                commitCount = insertPartsUsageDailySummary(inTMsgList);
                this.normalCount += commitCount;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(deleteStmt, deleteRs);
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void insertPartsUsageReportWork() {
        Map<String, String> deleteParam = new HashMap<String, String>();
        List<PRT_USG_RPT_WRKTMsg> deleteTMsgList = new ArrayList<PRT_USG_RPT_WRKTMsg>();
        PreparedStatement deleteStmt = null;
        ResultSet deleteRs = null;

        List<PRT_USG_RPT_WRKTMsg> inTMsgList = new ArrayList<PRT_USG_RPT_WRKTMsg>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {

            // delete sameDateData
            deleteParam.put("glblCmpyCd", this.glblCmpyCd);
            deleteParam.put("prcDt", this.processDate);

            deleteStmt = this.ssmLLClient.createPreparedStatement("getPartsUsageWkPK", deleteParam, execParam);
            deleteRs = deleteStmt.executeQuery();
            while (deleteRs.next()) {
                deleteTMsgList.add(setPrtUsgRptWrkPk(deleteRs));
                if (this.commitNumber == deleteTMsgList.size()) {
                    deletePartsUsageReportWork(deleteTMsgList);
                    deleteTMsgList = new ArrayList<PRT_USG_RPT_WRKTMsg>();
                }
            }
            if (deleteTMsgList.size() > 0) {
                deletePartsUsageReportWork(deleteTMsgList);
            }

            // Insert Data
            stmt = this.ssmLLClient.createPreparedStatement("getPartsUsageReport", setReportWorkSearchCondition(), execParam);
            rs = stmt.executeQuery();

            int commitCount = 0;

            while (rs.next()) {

                inTMsgList.add(setReportWorkCreateValue(rs));

                if (this.commitNumber == inTMsgList.size()) {
                    commitCount = insertPartsUsageReportWork(inTMsgList);
                    inTMsgList = new ArrayList<PRT_USG_RPT_WRKTMsg>();
                    this.normalCount += commitCount;
                    commitCount = 0;
                }
            }
            if (inTMsgList.size() > 0) {
                commitCount = insertPartsUsageReportWork(inTMsgList);
                this.normalCount += commitCount;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(deleteStmt, deleteRs);
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void deletePartsUsageDailySummary(List<PRT_USG_DLY_SMRYTMsg> inMsgLst) {
        PRT_USG_DLY_SMRYTMsg[] inMsgArray;
        inMsgArray = new PRT_USG_DLY_SMRYTMsg[inMsgLst.size()];
        S21FastTBLAccessor.removePhysical(inMsgLst.toArray(inMsgArray));

        commit();
        return;
    }

    private void deletePartsUsageReportWork(List<PRT_USG_RPT_WRKTMsg> inMsgLst) {
        PRT_USG_RPT_WRKTMsg[] inMsgArray;
        inMsgArray = new PRT_USG_RPT_WRKTMsg[inMsgLst.size()];
        S21FastTBLAccessor.removePhysical(inMsgLst.toArray(inMsgArray));

        commit();
        return;
    }

    private int insertPartsUsageDailySummary(List<PRT_USG_DLY_SMRYTMsg> inMsgLst) {
        PRT_USG_DLY_SMRYTMsg[] inMsgArray;
        inMsgArray = new PRT_USG_DLY_SMRYTMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            this.errorCount += inMsgArray.length - insertCount;
        }
        commit();
        return insertCount;
    }

    private int insertPartsUsageReportWork(List<PRT_USG_RPT_WRKTMsg> inMsgLst) {
        PRT_USG_RPT_WRKTMsg[] inMsgArray;
        inMsgArray = new PRT_USG_RPT_WRKTMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            this.errorCount += inMsgArray.length - insertCount;
        }
        commit();
        return insertCount;
    }

    private Map<String, Object> setDailySummarySearchCondition() {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", this.glblCmpyCd);
        inParam.put("prcDt", this.processDate);
        inParam.put("combDsCondConst", this.combDsCondConst);
        inParam.put("locStsCd", LOC_STS.DC_STOCK);
        inParam.put("stkStsCd", STK_STS.GOOD);
        return inParam;
    }

    private Map<String, Object> setReportWorkSearchCondition() {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", this.glblCmpyCd);
        inParam.put("prcDt", this.processDate);
        inParam.put("locStsCd", LOC_STS.DC_STOCK);
        inParam.put("locTpCd", LOC_TP.TECHNICIAN);
        inParam.put("gnrnTpCd", GNRN_TP.CURRENT);

// del start 2016/08/22 CSA Defect#13701
//        List<String> orgFuncRoleTpCdList = new ArrayList<String>();
//        orgFuncRoleTpCdList.add(ORG_FUNC_ROLE_TP.TECHNICIAN);
//        orgFuncRoleTpCdList.add("021"); // TODO ORG_FUNC_ROLE_TP.TECHNICIAN_MANAGER
//        inParam.put("orgFuncRoleTpCdList", orgFuncRoleTpCdList);
// del end 2016/08/22 CSA Defect#13701

        return inParam;
    }

    private PRT_USG_DLY_SMRYTMsg setPrtUsgDlySmryPk(ResultSet rs) {
        PRT_USG_DLY_SMRYTMsg inParam = new PRT_USG_DLY_SMRYTMsg();

        try {
            ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.prtUsgDlySmryPk, rs.getBigDecimal("PRT_USG_DLY_SMRY_PK"));

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return inParam;
    }

    private PRT_USG_RPT_WRKTMsg setPrtUsgRptWrkPk(ResultSet rs) {
        PRT_USG_RPT_WRKTMsg inParam = new PRT_USG_RPT_WRKTMsg();

        try {
            ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.prtUsgRptWrkPk, rs.getBigDecimal("PRT_USG_RPT_WRK_PK"));

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return inParam;
    }

    private PRT_USG_DLY_SMRYTMsg setDailySummaryCreateValue(ResultSet rs) {
        PRT_USG_DLY_SMRYTMsg inParam = new PRT_USG_DLY_SMRYTMsg();

        try {
            ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.prtUsgDlySmryPk, (BigDecimal) ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRT_USG_DLY_SMRY_SQ));
            ZYPEZDItemValueSetter.setValue(inParam.invtyTrxDt, rs.getString("INVTY_TRX_DT"));
            ZYPEZDItemValueSetter.setValue(inParam.mdseCd, rs.getString("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.invtyLocCd, rs.getString("INVTY_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.invtyQty, rs.getBigDecimal("INVTY_QTY"));
            ZYPEZDItemValueSetter.setValue(inParam.locStsCd, rs.getString("LOC_STS_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.stkStsCd, rs.getString("STK_STS_CD"));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return inParam;
    }

    private PRT_USG_RPT_WRKTMsg setReportWorkCreateValue(ResultSet rs) {
        PRT_USG_RPT_WRKTMsg inParam = new PRT_USG_RPT_WRKTMsg();

        try {
            ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.prtUsgRptWrkPk, (BigDecimal) ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRT_USG_RPT_WRK_SQ));
            ZYPEZDItemValueSetter.setValue(inParam.prtUsgRptWrkDt, rs.getString("PRT_USG_RPT_WRK_DT"));
            ZYPEZDItemValueSetter.setValue(inParam.invtyLocCd, rs.getString("INVTY_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.rtlWhCd, rs.getString("RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.rtlSwhCd, rs.getString("RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.techTocCd, rs.getString("TECH_TOC_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.techNm, rs.getString("TECH_NM"));
            ZYPEZDItemValueSetter.setValue(inParam.orgCd, rs.getString("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.mdseCd, rs.getString("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.mdseNm, rs.getString("MDSE_NM"));
            ZYPEZDItemValueSetter.setValue(inParam.thisMthTotStdCostAmt, rs.getBigDecimal("THIS_MTH_TOT_STD_COST_AMT"));
            ZYPEZDItemValueSetter.setValue(inParam.mrpCtrlFlg, rs.getString("MRP_CTRL_FLG"));
            ZYPEZDItemValueSetter.setValue(inParam.minOrdQty, rs.getBigDecimal("MIN_ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(inParam.maxInvtyQty, rs.getBigDecimal("MAX_INVTY_QTY"));
            ZYPEZDItemValueSetter.setValue(inParam.invtyQty, rs.getBigDecimal("INVTY_QTY"));
            // mod start 2016/05/12 CSA Defect#8159
            ZYPEZDItemValueSetter.setValue(inParam.avgUsgNum, convNullToZero(rs.getBigDecimal("AVG_USG_NUM")));
            // mod end 2016/05/12 CSA Defect#8159
            ZYPEZDItemValueSetter.setValue(inParam.sixthMthUsgQty, rs.getBigDecimal("SIXTH_MTH_USG_QTY"));
            ZYPEZDItemValueSetter.setValue(inParam.fifthMthUsgQty, rs.getBigDecimal("FIFTH_MTH_USG_QTY"));
            ZYPEZDItemValueSetter.setValue(inParam.frthMthUsgQty, rs.getBigDecimal("FRTH_MTH_USG_QTY"));
            ZYPEZDItemValueSetter.setValue(inParam.thirdMthUsgQty, rs.getBigDecimal("THIRD_MTH_USG_QTY"));
            ZYPEZDItemValueSetter.setValue(inParam.scdMthUsgQty, rs.getBigDecimal("SCD_MTH_USG_QTY"));
            ZYPEZDItemValueSetter.setValue(inParam.firstMthUsgQty, rs.getBigDecimal("FIRST_MTH_USG_QTY"));
            ZYPEZDItemValueSetter.setValue(inParam.lastTrxDt, rs.getString("LAST_TRX_DT"));
            ZYPEZDItemValueSetter.setValue(inParam.lastRcptDt, rs.getString("LAST_RCPT_DT"));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return inParam;
    }

    private String getCombDsCondConst() {
        DS_COND_CONSTTMsgArray invtyTrxCdArray = getDsCondConst(INVTY_TRX_CD);
        DS_COND_CONSTTMsgArray invtyTrxRsnCdArray = getDsCondConst(INVTY_TRX_RSN_CD);
        DS_COND_CONSTTMsgArray sysSrcCdArray = getDsCondConst(SYS_SRC_CD);

        if (invtyTrxCdArray == null || invtyTrxRsnCdArray == null || sysSrcCdArray == null) {
            throw new S21AbendException(NLAM1285E, new String[] {MSG_TXT_DS_COND_CONST });
        }

        if (!ZYPCommonFunc.hasValue((String) invtyTrxCdArray.no(0).dsCondConstValTxt_01.getValue()) || !ZYPCommonFunc.hasValue((String) invtyTrxRsnCdArray.no(0).dsCondConstValTxt_01.getValue())
                || !ZYPCommonFunc.hasValue((String) sysSrcCdArray.no(0).dsCondConstValTxt_01.getValue())) {
            throw new S21AbendException(NLAM1285E, new String[] {MSG_TXT_DS_COND_CONST });
        }

        StringBuilder combDsCondConstBuffer = new StringBuilder();
        combDsCondConstBuffer.append(COMB_INIT);
        combDsCondConstBuffer.append((String) invtyTrxCdArray.no(0).dsCondConstValTxt_01.getValue());
        combDsCondConstBuffer.append(COMB_MIDDLE);
        combDsCondConstBuffer.append((String) invtyTrxRsnCdArray.no(0).dsCondConstValTxt_01.getValue());
        combDsCondConstBuffer.append(COMB_MIDDLE);
        combDsCondConstBuffer.append((String) sysSrcCdArray.no(0).dsCondConstValTxt_01.getValue());
        combDsCondConstBuffer.append(COMB_END);

        if (ZYPCommonFunc.hasValue((String) invtyTrxCdArray.no(0).dsCondConstValTxt_02.getValue())) {
            combDsCondConstBuffer.append(COMB_START);
            combDsCondConstBuffer.append((String) invtyTrxCdArray.no(0).dsCondConstValTxt_02.getValue());
            combDsCondConstBuffer.append(COMB_MIDDLE);
            combDsCondConstBuffer.append((String) invtyTrxRsnCdArray.no(0).dsCondConstValTxt_02.getValue());
            combDsCondConstBuffer.append(COMB_MIDDLE);
            combDsCondConstBuffer.append((String) sysSrcCdArray.no(0).dsCondConstValTxt_02.getValue());
            combDsCondConstBuffer.append(COMB_END);
        }
        if (ZYPCommonFunc.hasValue((String) invtyTrxCdArray.no(0).dsCondConstValTxt_03.getValue())) {
            combDsCondConstBuffer.append(COMB_START);
            combDsCondConstBuffer.append((String) invtyTrxCdArray.no(0).dsCondConstValTxt_03.getValue());
            combDsCondConstBuffer.append(COMB_MIDDLE);
            combDsCondConstBuffer.append((String) invtyTrxRsnCdArray.no(0).dsCondConstValTxt_03.getValue());
            combDsCondConstBuffer.append(COMB_MIDDLE);
            combDsCondConstBuffer.append((String) sysSrcCdArray.no(0).dsCondConstValTxt_03.getValue());
            combDsCondConstBuffer.append(COMB_END);
        }
        if (ZYPCommonFunc.hasValue((String) invtyTrxCdArray.no(0).dsCondConstValTxt_04.getValue())) {
            combDsCondConstBuffer.append(COMB_START);
            combDsCondConstBuffer.append((String) invtyTrxCdArray.no(0).dsCondConstValTxt_04.getValue());
            combDsCondConstBuffer.append(COMB_MIDDLE);
            combDsCondConstBuffer.append((String) invtyTrxRsnCdArray.no(0).dsCondConstValTxt_04.getValue());
            combDsCondConstBuffer.append(COMB_MIDDLE);
            combDsCondConstBuffer.append((String) sysSrcCdArray.no(0).dsCondConstValTxt_04.getValue());
            combDsCondConstBuffer.append(COMB_END);
        }
        if (ZYPCommonFunc.hasValue((String) invtyTrxCdArray.no(0).dsCondConstValTxt_05.getValue())) {
            combDsCondConstBuffer.append(COMB_START);
            combDsCondConstBuffer.append((String) invtyTrxCdArray.no(0).dsCondConstValTxt_05.getValue());
            combDsCondConstBuffer.append(COMB_MIDDLE);
            combDsCondConstBuffer.append((String) invtyTrxRsnCdArray.no(0).dsCondConstValTxt_05.getValue());
            combDsCondConstBuffer.append(COMB_MIDDLE);
            combDsCondConstBuffer.append((String) sysSrcCdArray.no(0).dsCondConstValTxt_05.getValue());
            combDsCondConstBuffer.append(COMB_END);
        }

        if (ZYPCommonFunc.hasValue((String) invtyTrxCdArray.no(0).dsCondConstValTxt_06.getValue())) {
            combDsCondConstBuffer.append(COMB_START);
            combDsCondConstBuffer.append((String) invtyTrxCdArray.no(0).dsCondConstValTxt_06.getValue());
            combDsCondConstBuffer.append(COMB_MIDDLE);
            combDsCondConstBuffer.append((String) invtyTrxRsnCdArray.no(0).dsCondConstValTxt_06.getValue());
            combDsCondConstBuffer.append(COMB_MIDDLE);
            combDsCondConstBuffer.append((String) sysSrcCdArray.no(0).dsCondConstValTxt_06.getValue());
            combDsCondConstBuffer.append(COMB_END);
        }

        if (ZYPCommonFunc.hasValue((String) invtyTrxCdArray.no(0).dsCondConstValTxt_07.getValue())) {
            combDsCondConstBuffer.append(COMB_START);
            combDsCondConstBuffer.append((String) invtyTrxCdArray.no(0).dsCondConstValTxt_07.getValue());
            combDsCondConstBuffer.append(COMB_MIDDLE);
            combDsCondConstBuffer.append((String) invtyTrxRsnCdArray.no(0).dsCondConstValTxt_07.getValue());
            combDsCondConstBuffer.append(COMB_MIDDLE);
            combDsCondConstBuffer.append((String) sysSrcCdArray.no(0).dsCondConstValTxt_07.getValue());
            combDsCondConstBuffer.append(COMB_END);
        }

        if (ZYPCommonFunc.hasValue((String) invtyTrxCdArray.no(0).dsCondConstValTxt_08.getValue())) {
            combDsCondConstBuffer.append(COMB_START);
            combDsCondConstBuffer.append((String) invtyTrxCdArray.no(0).dsCondConstValTxt_08.getValue());
            combDsCondConstBuffer.append(COMB_MIDDLE);
            combDsCondConstBuffer.append((String) invtyTrxRsnCdArray.no(0).dsCondConstValTxt_08.getValue());
            combDsCondConstBuffer.append(COMB_MIDDLE);
            combDsCondConstBuffer.append((String) sysSrcCdArray.no(0).dsCondConstValTxt_08.getValue());
            combDsCondConstBuffer.append(COMB_END);
        }

        if (ZYPCommonFunc.hasValue((String) invtyTrxCdArray.no(0).dsCondConstValTxt_09.getValue())) {
            combDsCondConstBuffer.append(COMB_START);
            combDsCondConstBuffer.append((String) invtyTrxCdArray.no(0).dsCondConstValTxt_09.getValue());
            combDsCondConstBuffer.append(COMB_MIDDLE);
            combDsCondConstBuffer.append((String) invtyTrxRsnCdArray.no(0).dsCondConstValTxt_09.getValue());
            combDsCondConstBuffer.append(COMB_MIDDLE);
            combDsCondConstBuffer.append((String) sysSrcCdArray.no(0).dsCondConstValTxt_09.getValue());
            combDsCondConstBuffer.append(COMB_END);
        }

        if (ZYPCommonFunc.hasValue((String) invtyTrxCdArray.no(0).dsCondConstValTxt_10.getValue())) {
            combDsCondConstBuffer.append(COMB_START);
            combDsCondConstBuffer.append((String) invtyTrxCdArray.no(0).dsCondConstValTxt_10.getValue());
            combDsCondConstBuffer.append(COMB_MIDDLE);
            combDsCondConstBuffer.append((String) invtyTrxRsnCdArray.no(0).dsCondConstValTxt_10.getValue());
            combDsCondConstBuffer.append(COMB_MIDDLE);
            combDsCondConstBuffer.append((String) sysSrcCdArray.no(0).dsCondConstValTxt_10.getValue());
            combDsCondConstBuffer.append(COMB_END);
        }

        combDsCondConst = combDsCondConstBuffer.toString();
        return combDsCondConst;
    }

    private DS_COND_CONSTTMsgArray getDsCondConst(String dsCondConstCd) {
        DS_COND_CONSTTMsg inMsg = new DS_COND_CONSTTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsCondConstGrpId01", NLAB3140);
        inMsg.setConditionValue("dsCondConstCd01", dsCondConstCd);
        DS_COND_CONSTTMsgArray outArray = (DS_COND_CONSTTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return null;
        }
        return outArray;
    }

    // add start 2016/05/12 CSA Defect#8159
    private BigDecimal convNullToZero(BigDecimal val) {
        if (ZYPCommonFunc.hasValue(val)) {
            return val;
        }
        return BigDecimal.ZERO;
    }
    // add end 2016/05/12 CSA Defect#8159
}
