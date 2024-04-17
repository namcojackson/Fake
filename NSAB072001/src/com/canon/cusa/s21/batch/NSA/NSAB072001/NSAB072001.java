/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB072001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import static com.canon.cusa.s21.batch.NSA.NSAB072001.constant.NSAB072001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.common.NSX.NSXC001001.CovTmplInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetCovTmpl;
import business.db.MLY_SPLY_REV_COSTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>    
 * Monthly Supply Revenue Cost Creation
 *  
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/18   Hitachi         K.Ochiai        Create          N/A
 * 2016/11/21   Hitachi         K.Ochiai        Update          QC#16012
 *</pre>
 */

public class NSAB072001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    //START 2016/11/22 K.Ochiai [QC#16012, MOD]
    /** Sales Date */
    private String slsDt = null;
    //END 2016/11/22 K.Ochiai [QC#16012, MOD]

    /** Commit Number */
    private int commitNumber;

    /** Total Commit Count */
    private int totalCommitCount;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;

    /** Result cratDay */
    private String cratDays = null;

    /** Result splyAcct */
    private List<Map<String, Object>> splyAcct = null;

    /** Result slpyOrd */
    private List<Map<String, Object>> slpyOrd = null;

    @Override
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }
        // START 2016/11/22 K.Ochiai [QC#16012, MOD]
        // Get Sales Date
//        this.batDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd, PROGRAM_ID);
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, PROGRAM_ID);
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            throw new S21AbendException(NSAM0178E);
        }
        // END 2016/11/22 K.Ochiai [QC#16012, MOD]
        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.totalCommitCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // Get CRAT_DAYS
        List<Map<String, Object>> cratDays = getRptCondConst("getCratDays", setReportSearchCondition(CRAT_DAYS_CONST));
        if (cratDays.size() == 0) {
            throw new S21AbendException(NSZM0543E, new String[] {CRAT_DAYS_CONST });
        }
        BigDecimal cratDay = (BigDecimal) cratDays.get(0).get("CRAT_DAYS");
        // START 2016/11/22 K.Ochiai [QC#16012, MOD]
        this.cratDays = ZYPDateUtil.addDays(this.slsDt, cratDay.intValue() * (-1));
        // END 2016/11/22 K.Ochiai [QC#16012, MOD]

        // Get SLPY_ACCT
        this.splyAcct = getRptCondConst("getdSplyAcct", setReportSearchCondition(SPLY_ACCT_CONST));
        if (this.splyAcct.size() == 0) {
            throw new S21AbendException(NSZM0543E, new String[] {SPLY_ACCT_CONST });
        }
        // Get SLPY_ORD
        this.slpyOrd = getRptCondConst("getSlpyOrd", setReportSearchCondition(SPLY_ORD_CONST));
        if (this.slpyOrd.size() == 0) {
            throw new S21AbendException(NSZM0543E, new String[] {SPLY_ORD_CONST });
        }
    }

    @Override
    protected void mainRoutine() {
        // Delete Data
        deleteRevenueCostData("getDeleteData", setDeleteDataCondition());
        try {
            // Insert Revenue Data
            insertRevenueCostData("getRevenueData", setRevenueSearchCondition());
            // Insert Cost Data
            insertRevenueCostData("getCostData", setCostSearchCondition());
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.totalCommitCount, 0);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB072001().executeBatch(NSAB072001.class.getSimpleName());
    }

    private List<Map<String, Object>> getRptCondConst(String sqlId, Map<String, Object> paramMap) {
        List<Map<String, Object>> rtnList = new ArrayList<Map<String, Object>>();
        rtnList = this.ssmBatchClient.queryObjectList(sqlId, paramMap);
        return rtnList;
    }

    private Map<String, Object> setReportSearchCondition(String rptGrpId) {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", this.glblCmpyCd);
        inParam.put("rptGrpId", rptGrpId);
        return inParam;
    }

    private Map<String, Object> setDeleteDataCondition() {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", this.glblCmpyCd);
        // START 2016/11/22 K.Ochiai [QC#16012, MOD]
        inParam.put("cratDt", this.slsDt);
        // END 2016/11/22 K.Ochiai [QC#16012, MOD]
        return inParam;
    }

    // Delete the record of process date
    private void deleteRevenueCostData(String sqlId, Map<String, Object> paramMap) {
        List<MLY_SPLY_REV_COSTTMsg> inTMsgList = new ArrayList<MLY_SPLY_REV_COSTTMsg>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {

            stmt = this.ssmLLClient.createPreparedStatement(sqlId, paramMap, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                MLY_SPLY_REV_COSTTMsg tMsgDelete = new MLY_SPLY_REV_COSTTMsg();
                setValue(tMsgDelete.glblCmpyCd, this.glblCmpyCd);
                setValue(tMsgDelete.mlySplyRevCostPk, rs.getBigDecimal("MLY_SPLY_REV_COST_PK"));
                // START 2016/11/22 K.Ochiai [QC#16012, MOD]
                setValue(tMsgDelete.fctCratDt, this.slsDt);
                // END 2016/11/22 K.Ochiai [QC#16012, MOD]
                inTMsgList.add(tMsgDelete);
                if (this.commitNumber == inTMsgList.size()) {
                    deleteData(inTMsgList);
                    inTMsgList = new ArrayList<MLY_SPLY_REV_COSTTMsg>();
                }
            }
            if (inTMsgList.size() != 0) {
                deleteData(inTMsgList);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private int deleteData(List<MLY_SPLY_REV_COSTTMsg> inMsgLst) {
        MLY_SPLY_REV_COSTTMsg[] inMsgArray;
        inMsgArray = new MLY_SPLY_REV_COSTTMsg[inMsgLst.size()];
        int deleteCount = S21FastTBLAccessor.removePhysical(inMsgLst.toArray(inMsgArray));

        if (deleteCount != inMsgArray.length) {
            throw new S21AbendException(NSAM0475E, new String[] {inMsgArray[0].getTableName(), inMsgArray[0].getTableName() });
        }
        commit();
        return deleteCount;
    }

    private List<String> convertList(List<Map<String, Object>> list, String colNm) {
        List<String> rtnList = new ArrayList<String>();
        for (Map<String, Object> map : list) {
            rtnList.add((String) map.get(colNm));
        }
        return rtnList;
    }

    private Map<String, Object> setRevenueSearchCondition() throws SQLException {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", this.glblCmpyCd);
        inParam.put("splyAcct", convertList(this.splyAcct, "COA_ACCT_CD"));
        inParam.put("glDt", this.cratDays);
        inParam.put("dsContrCatgFlt", DS_CONTR_CATG.FLEET);
        inParam.put("serNumFlt", SER_NUM_FLT);
        inParam.put("noMdlNm", NO_MDL_NM);
        inParam.put("noSvcSegDescTxt", NO_SVC_SEG_DESC_TXT);
        inParam.put("noCoaProdCd", NO_COA_PROD_CD);
        inParam.put("svcInvChrgTpBc", SVC_INV_CHRG_TP.BASE_CHARGE);
        inParam.put("invTpDescTxtBc", INV_TP_DESC_TXT_BC);
        inParam.put("svcInvChrgTpMc", SVC_INV_CHRG_TP.METER_CHARGE);
        inParam.put("invTpDescTxtMc", INV_TP_DESC_TXT_MC);
        inParam.put("invTpDescTxtOthr", INV_TP_DESC_TXT_OTHR);
        inParam.put("drCrTpCredit", DR_CR_TP_CREDIT);
        return inParam;
    }

    private Map<String, Object> setCostSearchCondition() throws SQLException {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", this.glblCmpyCd);
        // START 2016/11/22 K.Ochiai [QC#16012, MOD]
        inParam.put("contrDt", this.slsDt);
        // END 2016/11/22 K.Ochiai [QC#16012, MOD]
        inParam.put("cratDays", this.cratDays);
        inParam.put("ordCatgRsn", getOrdCatgRsn(this.slpyOrd));
        inParam.put("dsContrCatgFlt", DS_CONTR_CATG.FLEET);
        inParam.put("serNumFlt", SER_NUM_FLT);
        inParam.put("noMdlNm", NO_MDL_NM);
        inParam.put("noSvcSegDescTxt", NO_SVC_SEG_DESC_TXT);
        inParam.put("noCoaProdCd", NO_COA_PROD_CD);
        inParam.put("svcMachTpMachine", SVC_MACH_TP.MACHINE);
        return inParam;
    }

    private String getOrdCatgRsn(List<Map<String, Object>> list) {
        StringBuilder rtnStrBuf = new StringBuilder();
        String ordCatgCd;
        String ordRsnCd;
        for (Map<String, Object> map : list) {
            ordCatgCd = (String) map.get("ORD_CATG_CD");
            ordRsnCd = (String) map.get("ORD_RSN_CD");
            if (rtnStrBuf.length() > 0) {
                rtnStrBuf.append(" OR ");
            }
            if (hasValue(ordRsnCd)) {
                rtnStrBuf.append(" (P.DS_ORD_CATG_CD = '");
                rtnStrBuf.append(ordCatgCd);
                rtnStrBuf.append("'  AND P.DS_ORD_RSN_CD = '");
                rtnStrBuf.append(ordRsnCd);
                rtnStrBuf.append("') ");
            } else {
                rtnStrBuf.append(" (P.DS_ORD_CATG_CD = '");
                rtnStrBuf.append(ordCatgCd);
                rtnStrBuf.append("') ");
            }
        }
        return rtnStrBuf.toString();
    }

    private void insertRevenueCostData(String sqlId, Map<String, Object> paramMap) {
        List<MLY_SPLY_REV_COSTTMsg> inTMsgList = new ArrayList<MLY_SPLY_REV_COSTTMsg>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement(sqlId, paramMap, execParam);
            rs = stmt.executeQuery();

            int commitCount = 0;

            // Insert MLY_SPLY_REV_COST
            while (rs.next()) {

                inTMsgList.add(setCreateValue(rs, sqlId));

                if (this.commitNumber == inTMsgList.size()) {
                    commitCount = insertData(inTMsgList);
                    inTMsgList = new ArrayList<MLY_SPLY_REV_COSTTMsg>();
                    this.totalCommitCount += commitCount;
                }
            }
            if (inTMsgList.size() != 0) {
                commitCount = insertData(inTMsgList);
                this.totalCommitCount += commitCount;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private int insertData(List<MLY_SPLY_REV_COSTTMsg> inMsgLst) {
        MLY_SPLY_REV_COSTTMsg[] inMsgArray;
        inMsgArray = new MLY_SPLY_REV_COSTTMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            throw new S21AbendException(NSAM0469E, new String[] {inMsgArray[0].getTableName(), inMsgArray[0].getTableName() });
        }
        commit();
        return insertCount;
    }

    private MLY_SPLY_REV_COSTTMsg setCreateValue(ResultSet rs, String sqlId) throws SQLException {
        MLY_SPLY_REV_COSTTMsg inParam = new MLY_SPLY_REV_COSTTMsg();

        // get splyInclFlg
        NSXC001001GetCovTmpl covTmpl = new NSXC001001GetCovTmpl();
        CovTmplInfo tmplInfo = new CovTmplInfo();
        tmplInfo.setGlblCmpyCd(rs.getString("GLBL_CMPY_CD"));
        // START 2016/11/22 K.Ochiai [QC#16012, MOD]
        tmplInfo.setSlsDt(this.slsDt);
        // END 2016/11/22 K.Ochiai [QC#16012, MOD]
        tmplInfo.setSvcPgmMdseCd(rs.getString("SVC_PGM_MDSE_CD"));
        boolean isSuplIncl = covTmpl.isSuplIncl(tmplInfo);
        String splyInclFlg;
        if (isSuplIncl) {
            splyInclFlg = FLG_ON_Y;
        } else {
            splyInclFlg = FLG_OFF_N;
        }
        setValue(inParam.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
        setValue(inParam.mlySplyRevCostPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MLY_SPLY_REV_COST_SQ));
        // START 2016/11/22 K.Ochiai [QC#16012, MOD]
        setValue(inParam.fctCratDt, this.slsDt);
        // END 2016/11/22 K.Ochiai [QC#16012, MOD]
        setValue(inParam.dwhTrgtDt, rs.getString("DWH_TRGT_DT"));
        setValue(inParam.dwhTrgtYrMth, rs.getString("DWH_TRGT_YR_MTH"));
        setValue(inParam.dwhTrgtYr, rs.getString("DWH_TRGT_YR"));
        setValue(inParam.dwhTrgtQtrNum, rs.getBigDecimal("DWH_TRGT_QTR_NUM"));
        if (sqlId.equals("getRevenueData")) {
            setValue(inParam.fctCatgCd, "REVSPLY");
        } else {
            setValue(inParam.fctCatgCd, "COSTSPLY");
        }
        setValue(inParam.dsContrPk, rs.getBigDecimal("DS_CONTR_PK"));
        setValue(inParam.dsContrNum, rs.getString("DS_CONTR_NUM"));
        setValue(inParam.dsContrDtlPk, rs.getBigDecimal("DS_CONTR_DTL_PK"));
        setValue(inParam.dsContrDtlTpCd, rs.getString("DS_CONTR_DTL_TP_CD"));
        setValue(inParam.dsContrDtlTpDescTxt, rs.getString("DS_CONTR_DTL_TP_DESC_TXT"));
        setValue(inParam.svcContrBrCd, rs.getString("SVC_CONTR_BR_CD"));
        setValue(inParam.dsContrCatgCd, rs.getString("DS_CONTR_CATG_CD"));
        setValue(inParam.dsContrCatgDescTxt, rs.getString("DS_CONTR_CATG_DESC_TXT"));
        setValue(inParam.svcLineBizCd, rs.getString("SVC_LINE_BIZ_CD"));
        setValue(inParam.dsAcctNum, rs.getString("DS_ACCT_NUM"));
        setValue(inParam.dsAcctNm, rs.getString("DS_ACCT_NM"));
        setValue(inParam.altPayerCustCd, rs.getString("ALT_PAYER_CUST_CD"));
        setValue(inParam.curLocAcctNum, rs.getString("CUR_LOC_ACCT_NUM"));
        setValue(inParam.curLocAcctNm, rs.getString("CUR_LOC_ACCT_NM"));
        setValue(inParam.curLocNum, rs.getString("CUR_LOC_NUM"));
        setValue(inParam.indCurLocNum, rs.getString("IND_CUR_LOC_NUM"));
        setValue(inParam.svcMachMstrPk, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        setValue(inParam.serNum, rs.getString("SER_NUM"));
        setValue(inParam.mdlId, rs.getBigDecimal("MDL_ID"));
        setValue(inParam.t_MdlNm, rs.getString("T_MDL_NM"));
        setValue(inParam.svcSegCd, rs.getString("SVC_SEG_CD"));
        setValue(inParam.svcSegDescTxt, rs.getString("SVC_SEG_DESC_TXT"));
        setValue(inParam.coaCmpyCd, rs.getString("COA_CMPY_CD"));
        setValue(inParam.coaAfflCd, rs.getString("COA_AFFL_CD"));
        setValue(inParam.coaBrCd, rs.getString("COA_BR_CD"));
        setValue(inParam.coaChCd, rs.getString("COA_CH_CD"));
        setValue(inParam.coaCcCd, rs.getString("COA_CC_CD"));
        setValue(inParam.coaAcctCd, rs.getString("COA_ACCT_CD"));
        setValue(inParam.coaProdCd, rs.getString("COA_PROD_CD"));
        setValue(inParam.coaProjCd, rs.getString("COA_PROJ_CD"));
        setValue(inParam.coaExtnCd, rs.getString("COA_EXTN_CD"));
        setValue(inParam.mdseCd, rs.getString("MDSE_CD"));
        // START 2016/11/22 K.Ochiai [QC#16012, MOD]
        setValue(inParam.mdseDescShortTxt, rs.getString("MDSE_DESC_SHORT_TXT"));
        // END 2016/11/22 K.Ochiai [QC#16012, MOD]
        setValue(inParam.shipQty, rs.getBigDecimal("SHIP_QTY"));
        setValue(inParam.unitCostAmt, rs.getBigDecimal("UNIT_COST_AMT"));
        setValue(inParam.shipCpltCostAmt, rs.getBigDecimal("SHIP_CPLT_COST_AMT"));
        setValue(inParam.svcInvChrgTpCd, rs.getString("SVC_INV_CHRG_TP_CD"));
        setValue(inParam.svcInvChrgTpDescTxt, rs.getString("SVC_INV_CHRG_TP_DESC_TXT"));
        setValue(inParam.dsInvTpCd, rs.getString("DS_INV_TP_CD"));
        setValue(inParam.invTpDescTxt, rs.getString("INV_TP_DESC_TXT"));
        setValue(inParam.invNum, rs.getString("INV_NUM"));
        setValue(inParam.invBolLineNum, rs.getString("INV_BOL_LINE_NUM"));
        setValue(inParam.invLineNum, rs.getString("INV_LINE_NUM"));
        setValue(inParam.invLineSubNum, rs.getString("INV_LINE_SUB_NUM"));
        setValue(inParam.invLineSubTrxNum, rs.getString("INV_LINE_SUB_TRX_NUM"));
        setValue(inParam.svcInvLinePk, rs.getBigDecimal("SVC_INV_LINE_PK"));
        setValue(inParam.invDt, rs.getString("INV_DT"));
        setValue(inParam.acctDt, rs.getString("ACCT_DT"));
        if (FLG_ON_Y.equals(rs.getString("BLACK_COLOR_FLG"))) {
            if (hasValue(rs.getBigDecimal("MTR_CHRG_DEAL_AMT"))) {
                setValue(inParam.bwMtrChrgDealAmt, rs.getBigDecimal("MTR_CHRG_DEAL_AMT"));
                setValue(inParam.bwCostAmt, BigDecimal.ZERO);
            } else {
                setValue(inParam.bwMtrChrgDealAmt, BigDecimal.ZERO);
                setValue(inParam.bwCostAmt, rs.getBigDecimal("COST_AMT"));
            }
            setValue(inParam.clrMtrChrgDealAmt, BigDecimal.ZERO);
            setValue(inParam.clrCostAmt, BigDecimal.ZERO);
            setValue(inParam.othInvLineDealNetAmt, BigDecimal.ZERO);
            setValue(inParam.othCostAmt, BigDecimal.ZERO);
        } else if (FLG_OFF_N.equals(rs.getString("BLACK_COLOR_FLG"))) {
            setValue(inParam.bwMtrChrgDealAmt, BigDecimal.ZERO);
            setValue(inParam.bwCostAmt, BigDecimal.ZERO);
            if (hasValue(rs.getBigDecimal("MTR_CHRG_DEAL_AMT"))) {
                setValue(inParam.clrMtrChrgDealAmt, rs.getBigDecimal("MTR_CHRG_DEAL_AMT"));
                setValue(inParam.clrCostAmt, BigDecimal.ZERO);
            } else {
                setValue(inParam.clrMtrChrgDealAmt, BigDecimal.ZERO);
                setValue(inParam.clrCostAmt, rs.getBigDecimal("COST_AMT"));
            }
            setValue(inParam.othInvLineDealNetAmt, BigDecimal.ZERO);
            setValue(inParam.othCostAmt, BigDecimal.ZERO);
        } else {
            setValue(inParam.bwMtrChrgDealAmt, BigDecimal.ZERO);
            setValue(inParam.bwCostAmt, BigDecimal.ZERO);
            setValue(inParam.clrMtrChrgDealAmt, BigDecimal.ZERO);
            setValue(inParam.clrCostAmt, BigDecimal.ZERO);
            if (hasValue(rs.getBigDecimal("MTR_CHRG_DEAL_AMT"))) {
                setValue(inParam.othInvLineDealNetAmt, rs.getBigDecimal("MTR_CHRG_DEAL_AMT"));
                setValue(inParam.othCostAmt, BigDecimal.ZERO);
            } else {
                setValue(inParam.othInvLineDealNetAmt, BigDecimal.ZERO);
                setValue(inParam.othCostAmt, rs.getBigDecimal("COST_AMT"));
            }
        }
        setValue(inParam.bllgPerFromDt, rs.getString("BLLG_PER_FROM_DT"));
        setValue(inParam.bllgPerThruDt, rs.getString("BLLG_PER_THRU_DT"));
        setValue(inParam.actlMachQty, rs.getBigDecimal("ACTL_MACH_QTY"));
        setValue(inParam.ordQty, rs.getBigDecimal("ORD_QTY"));
        setValue(inParam.cpoOrdNum, rs.getString("CPO_ORD_NUM"));
        setValue(inParam.cpoDtlLineNum, rs.getString("CPO_DTL_LINE_NUM"));
        setValue(inParam.cpoOrdDt, rs.getString("CPO_ORD_DT"));
        setValue(inParam.imgSplyYieldCnt, rs.getBigDecimal("IMG_SPLY_YIELD_CNT"));
        setValue(inParam.splyInclFlg, splyInclFlg);
        setValue(inParam.invLineSplTpCd, rs.getString("INV_LINE_SPL_TP_CD"));
        setValue(inParam.invLineSplTpDescTxt, rs.getString("INV_LINE_SPL_TP_DESC_TXT"));
        setValue(inParam.prtUsedByTechLocCd, rs.getString("PRT_USED_BY_TECH_LOC_CD"));
        setValue(inParam.imgSplyTpCd, rs.getString("IMG_SPLY_TP_CD"));
        setValue(inParam.imgSplyTpDescTxt, rs.getString("IMG_SPLY_TP_DESC_TXT"));
        setValue(inParam.imgSplyColorTpCd, rs.getString("IMG_SPLY_COLOR_TP_CD"));
        setValue(inParam.imgSplyColorTpDescTxt, rs.getString("IMG_SPLY_COLOR_TP_DESC_TXT"));
        setValue(inParam.usedMultCnt, rs.getBigDecimal("USED_MULT_CNT"));
        if (hasValue(rs.getBigDecimal("USED_MULT_CNT")) && hasValue(rs.getBigDecimal("SHIP_QTY"))) {
            setValue(inParam.usedEquaCnt, rs.getBigDecimal("SHIP_QTY").multiply(rs.getBigDecimal("USED_MULT_CNT")).negate());
        }
        // START 2016/11/22 K.Ochiai [QC#16012, ADD]
        setValue(inParam.coaCmpyDescTxt, rs.getString("COA_CMPY_DESC_TXT"));
        // END 2016/11/22 K.Ochiai [QC#16012, ADD]
        return inParam;
    }

}
