package com.canon.cusa.s21.batch.NSA.NSAB087001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.dbcommon.EZDTBLAccessor;
import business.db.FRT_CHRG_REPL_DTLTMsg;
import business.db.FRT_CHRG_REPL_INFOTMsg;
import business.db.SVC_INV_LINETMsg;
import business.db.SVC_INV_LINE_ALLOCTMsg;

import com.canon.cusa.s21.batch.NSA.NSAB087001.constant.NSAB087001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_CHRG_REPL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Supply Freight Charge Replace Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/01/24   Hitachi         Y.Tamai         Create          QC#61468
 */
public class NSAB087001 extends S21BatchMain {

    /** SsmLLClient */
    private S21SsmLowLevelCodingClient ssmLLClient;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Sales Date */
    private String slsDate;

    /** Merchandise Code */
    private String mdseCd;

    /** Termination Code */
    private TERM_CD termCd;

    /** Normal Count */
    private int normalCount;

    /** Error Count */
    private int errorCount;

    /** Commit Number */
    private int commitCount;

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB087001().executeBatch(NSAB087001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSAB087001Constant.ZZM9000E, new String[] {"Global Company Code" });
        }
        // Get Sales Date
        this.slsDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd, NSAB087001.class.getSimpleName());
        if (!hasValue(this.slsDate)) {
            throw new S21AbendException(NSAB087001Constant.ZZM9000E, new String[] {"Sales Date" });
        }
        this.mdseCd = ZYPCodeDataUtil.getVarCharConstValue(NSAB087001Constant.FRT_TAX_DUMMY_MDSE_CD, this.glblCmpyCd);
        if (!hasValue(this.mdseCd)) {
            throw new S21AbendException(NSAB087001Constant.ZZM9000E, new String[] {"VarCharConstNm: FRT_TAX_DUMMY_MDSE_CD" });
        }

        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount = 0;
        // Get Commit Number
        this.commitCount = getCommitCount();
        if (this.commitCount <= 0 || this.commitCount >= NSAB087001Constant.MAX_COMMIT_COUNT) {
            this.commitCount = NSAB087001Constant.MAX_COMMIT_COUNT;
        }
    }

    @Override
    protected void mainRoutine() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<CandidateData> cdList = null;
        List<FreightChargeData> fcList = null;
        boolean commitFlg = false;
        BigDecimal targetInvLineDealNetAmt = BigDecimal.ZERO;
        FreightChargeData updateForFcData;

        cdList = getCandidateData(stmt, rs);
        // The subject exists
        if (cdList.size() != 0) {
            for (CandidateData cdData : cdList) {
                fcList = getFreightChargeData(stmt, rs, cdData);
                // Freight Charge Data not exists
                if (fcList.size() == 0) {
                    commitFlg = updateForFrtChrgReplInfo(cdData, FRT_CHRG_REPL_STS.NOT_AVAILABLE);
                    if (commitFlg) {
                        commitFlg = deleteForSvcInvLineInfo(cdData);
                    }
                    if (commitFlg) {
                        normalCount++;
                        commit();
                    } else {
                        errorCount++;
                        commitFlg = false;
                        rollback();
                    }
                } else { // Only one Freight Charge exists
                    if (fcList.size() == 1) {
                        updateForFcData = fcList.get(0);
                        targetInvLineDealNetAmt = fcList.get(0).getInvLineDealNetAmt();
                        commitFlg = updateForFrtChrgReplInfo(cdData, FRT_CHRG_REPL_STS.PROCESSED);
                        if (commitFlg) {
                            commitFlg = createForFrtChrgReplDtlInfo(cdData, updateForFcData);
                        }
                        if (commitFlg) {
                            commitFlg = updateForSvcInvLineInfo(updateForFcData, cdData, targetInvLineDealNetAmt);
                        }
                        if (commitFlg) {
                            commitFlg = createForSvcInvLineAllocInfo(rs, stmt, updateForFcData, cdData);
                        }
                        if (commitFlg) {
                            normalCount++;
                            commit();
                        } else {
                            errorCount++;
                            commitFlg = false;
                            rollback();
                        }
                    } else { // Several list exists
                        targetInvLineDealNetAmt = BigDecimal.ZERO;
                        // Get any single element
                        updateForFcData = fcList.get(0);
                        targetInvLineDealNetAmt = updateForFcData.getInvLineDealNetAmt();
                        // Update existing record
                        commitFlg = updateForSvcInvLineInfo(updateForFcData, cdData, targetInvLineDealNetAmt);
                        if (commitFlg) {
                            commitFlg = updateForFrtChrgReplInfo(cdData, FRT_CHRG_REPL_STS.PROCESSED);
                        }
                        if (commitFlg) {
                            commitFlg = createForFrtChrgReplDtlInfo(cdData, updateForFcData);
                        }
                        if (commitFlg) {
                            commitFlg = createForSvcInvLineAllocInfo(rs, stmt, updateForFcData, cdData);
                        }
                        // Create records for other fcList
                        targetInvLineDealNetAmt = BigDecimal.ZERO;
                        fcList.remove(0);
                        for (FreightChargeData fcData : fcList) {
                            targetInvLineDealNetAmt = fcData.getInvLineDealNetAmt();
                            if (commitFlg) {
                                commitFlg = createForSvcAndFreightInfo(rs, stmt, cdData, fcData, targetInvLineDealNetAmt);
                            }
                        }
                        if (commitFlg) {
                            normalCount++;
                            commit();
                        } else {
                            errorCount++;
                            commitFlg = false;
                            rollback();
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * Get Candidate data
     * @param stmt
     * @param rs
     * @return List<CandidateData>
     */
    private List<CandidateData> getCandidateData(PreparedStatement stmt, ResultSet rs) {
        Map<String, Object> paramMap = setParamForCadidateMap();
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        List<CandidateData> cdList = new ArrayList<CandidateData>();

        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_UPDATABLE);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getCandidateData", paramMap, execParam);
            rs = stmt.executeQuery();
            while (rs.next()) {
                CandidateData cdData = new CandidateData();
                cdData.setSvcInvNum(rs.getString("SVC_INV_NUM"));
                cdData.setSvcInvLineNum(rs.getString("SVC_INV_LINE_NUM"));
                cdData.setSvcMachMstrpk(rs.getBigDecimal("SVC_MACH_MSTR_PK"));
                cdData.setBllgPerFromDt(rs.getString("BLLG_PER_FROM_DT"));
                cdData.setBllgPerThruDt(rs.getString("BLLG_PER_THRU_DT"));
                cdData.setSvcInvLinePk(rs.getBigDecimal("SVC_INV_LINE_PK"));
                cdData.setDsContrNum(rs.getString("DS_CONTR_NUM"));
                cdData.setCcyCd(rs.getString("CCY_CD"));
                cdList.add(cdData);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        return cdList;
    }

    /**
     * Set parameter for Candidate Data
     * @return Map
     */
    private Map<String, Object> setParamForCadidateMap() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("frtChrgReplStsCd", FRT_CHRG_REPL_STS.GENERATED);
        return paramMap;
    }

    /**
     * Set parameter for DS_INV_SLS_CRT
     * @param fcData
     * @return Map
     */
    private Map<String, Object> setParamFordsInvSlsCrtMap(FreightChargeData fcData) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("invNum", fcData.getInvNum());
        paramMap.put("invBolLineNum", fcData.getInvBolLineNum());
        paramMap.put("invLineNum", fcData.getInvLineNum());
        return paramMap;
    }

    /**
     * Set parameter for Candidate Data
     * @return Map
     */
    private Map<String, Object> setParamForLatestInvNumLine(CandidateData cdData) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcInvNum", cdData.getSvcInvNum());
        return paramMap;
    }

    /**
     * Get Freight Charge Data
     * @param stmt
     * @param rs
     * @param cdData
     * @return List<FreightChargeData>
     */
    private List<FreightChargeData> getFreightChargeData(PreparedStatement stmt, ResultSet rs, CandidateData cdData) {
        Map<String, Object> paramMap = setParamForFreightChargeMap(cdData);
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        List<FreightChargeData> fcList = new ArrayList<FreightChargeData>();

        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_UPDATABLE);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getFreightChargeData", paramMap, execParam);
            rs = stmt.executeQuery();
            while (rs.next()) {
                FreightChargeData fcData = new FreightChargeData();
                fcData.setSlsRepTocCd(rs.getString("SLS_REP_TOC_CD"));
                fcData.setInvNum(rs.getString("INV_NUM"));
                fcData.setInvBolLineNum(rs.getString("INV_BOL_LINE_NUM"));
                fcData.setInvLineNum(rs.getString("INV_LINE_NUM"));
                fcData.setInvLineDealNetAmt(rs.getBigDecimal("INV_LINE_DEAL_NET_AMT"));
                fcList.add(fcData);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        return fcList;
    }

    /**
     * Set parameter for FreightCharge data
     * @param cdData
     * @return Map<String, Object>
     */
    private Map<String, Object> setParamForFreightChargeMap(CandidateData cdData) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsOrdCatgCd", DS_ORD_CATG.CONTRACT_SUPPLY_CSA);
        String frtReplOrdTp = ZYPCodeDataUtil.getVarCharConstValue(NSAB087001Constant.FRT_REPL_ORD_TP, this.glblCmpyCd);
        List<String> frtReplOrdTpList;
        if (ZYPCommonFunc.hasValue(frtReplOrdTp)) {
            frtReplOrdTpList = Arrays.asList(frtReplOrdTp.split(","));
        } else {
            frtReplOrdTpList = null;
        }
        paramMap.put("frtReplOrdTpList", frtReplOrdTpList);
        paramMap.put("dsContrNum", cdData.getDsContrNum());
        paramMap.put("dsInvTpCd", DS_INV_TP.INVOICE_SUPPLIES_CONTRACT);
        paramMap.put("bllgPerFromDt", cdData.getBllgPerFromDt());
        paramMap.put("bllgPerThruDt", cdData.getBllgPerThruDt());
        paramMap.put("mdseCd", mdseCd);
        paramMap.put("svcMachMstrpk", cdData.getSvcMachMstrpk());

        return paramMap;
    }

    /**
     * Update FRT_CHRG_REPL_INFO table
     * @param cdData
     * @param frtChrgReplSts
     * @return boolean
     */
    private boolean updateForFrtChrgReplInfo(CandidateData cdData, String frtChrgReplSts) {
        FRT_CHRG_REPL_INFOTMsg frtChrgReplInfoTmsg = new FRT_CHRG_REPL_INFOTMsg();
        setValue(frtChrgReplInfoTmsg.glblCmpyCd, glblCmpyCd);
        setValue(frtChrgReplInfoTmsg.svcInvLineNum, cdData.getSvcInvLineNum());
        setValue(frtChrgReplInfoTmsg.svcInvNum, cdData.getSvcInvNum());
        frtChrgReplInfoTmsg = (FRT_CHRG_REPL_INFOTMsg) EZDTBLAccessor.findByKeyForUpdateWait(frtChrgReplInfoTmsg);
        if (frtChrgReplInfoTmsg != null) {
            setValue(frtChrgReplInfoTmsg.frtChrgReplStsCd, frtChrgReplSts);
            EZDTBLAccessor.update(frtChrgReplInfoTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(frtChrgReplInfoTmsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Delete SVC_INV_LINE Table
     * @param cdData
     * @return boolean
     */
    private boolean deleteForSvcInvLineInfo(CandidateData cdData) {
        SVC_INV_LINETMsg svcInvLineTmsg = new SVC_INV_LINETMsg();
        setValue(svcInvLineTmsg.glblCmpyCd, glblCmpyCd);
        setValue(svcInvLineTmsg.svcInvLinePk, cdData.getSvcInvLinePk());
        svcInvLineTmsg = (SVC_INV_LINETMsg) EZDTBLAccessor.findByKeyForUpdateWait(svcInvLineTmsg);
        EZDTBLAccessor.logicalRemove(svcInvLineTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcInvLineTmsg.getReturnCode())) {
            return false;
        }

        return true;
    }

    /**
     * Update SVC_INV_LINE Table
     * @param fcData
     * @param sumInvLineDealNetAmt
     * @return boolean
     */
    private boolean updateForSvcInvLineInfo(FreightChargeData fcData, CandidateData cdData, BigDecimal invLineDealNetAmt) {
        SVC_INV_LINETMsg svcInvLineTmsg = new SVC_INV_LINETMsg();
        setValue(svcInvLineTmsg.glblCmpyCd, glblCmpyCd);
        setValue(svcInvLineTmsg.svcInvLinePk, cdData.getSvcInvLinePk());

        svcInvLineTmsg = (SVC_INV_LINETMsg) EZDTBLAccessor.findByKeyForUpdateWait(svcInvLineTmsg);
        if (svcInvLineTmsg != null) {
            setValue(svcInvLineTmsg.ezCancelFlag, NSAB087001Constant.ZERO);
            setValue(svcInvLineTmsg.glblCmpyCd, glblCmpyCd);
            setValue(svcInvLineTmsg.svcInvLinePk, cdData.getSvcInvLinePk());
            setValue(svcInvLineTmsg.slsRepTocCd, fcData.getSlsRepTocCd());
            setValue(svcInvLineTmsg.dealUnitPrcAmt, invLineDealNetAmt);
            setValue(svcInvLineTmsg.funcUnitPrcAmt, invLineDealNetAmt);
            setValue(svcInvLineTmsg.invLineDealSlsAmt, invLineDealNetAmt);
            setValue(svcInvLineTmsg.invLineFuncSlsAmt, invLineDealNetAmt);
            setValue(svcInvLineTmsg.invLineDealNetAmt, invLineDealNetAmt);
            setValue(svcInvLineTmsg.invLineFuncNetAmt, invLineDealNetAmt);
            setValue(svcInvLineTmsg.invDispUnitPrcAmt, invLineDealNetAmt);
            EZDTBLAccessor.update(svcInvLineTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcInvLineTmsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * If several fcList exists,Do the following.
     * Insert:SVC_INV_LINE,SVC_INV_LINE_ALLOC,FRT_CHRG_REPL_INFO
     * Update:SVC_INV
     * @param rs
     * @param stmt
     * @param cdData
     * @param fcList
     * @param targetSlsRepTocCdForInsert
     * @param sumInvLineDealNetAmt
     * @return boolean
     */
    private boolean createForSvcAndFreightInfo(ResultSet rs, PreparedStatement stmt, CandidateData cdData, FreightChargeData fcData, BigDecimal targetInvLineDealNetAmt) {
        SVC_INV_LINETMsg svcInvLineTmsg = new SVC_INV_LINETMsg();
        CandidateData copyBaseForSvcInvLineData = new CandidateData();
        String latestSvcInvLineNum = getLatestSvcInvLineNum(rs, stmt, cdData);

        setValue(svcInvLineTmsg.glblCmpyCd, glblCmpyCd);
        setValue(svcInvLineTmsg.svcInvLinePk, cdData.getSvcInvLinePk());
        svcInvLineTmsg = (SVC_INV_LINETMsg) EZDTBLAccessor.findByKeyForUpdateWait(svcInvLineTmsg);

        // Create SVC_INV_LINE Table Record
        if (svcInvLineTmsg != null) {
            setValue(svcInvLineTmsg.svcInvLinePk, ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_INV_LINE_SQ"));
            setValue(svcInvLineTmsg.svcInvLineNum, calcIncrementalSvcInvLineNum(latestSvcInvLineNum));
            setValue(svcInvLineTmsg.svcInvChrgTpCd, SVC_INV_CHRG_TP.FREIGHT_CHARGE);
            setValue(svcInvLineTmsg.mdseCd, mdseCd);
            setValue(svcInvLineTmsg.mdseNm, "SHIPPING");
            setValue(svcInvLineTmsg.trxRsnCd, "A5");
            setValue(svcInvLineTmsg.slsRepTocCd, fcData.getSlsRepTocCd());
            setValue(svcInvLineTmsg.svcInvQty, BigDecimal.ONE);
            setValue(svcInvLineTmsg.dealUnitPrcAmt, targetInvLineDealNetAmt);
            setValue(svcInvLineTmsg.funcUnitPrcAmt, targetInvLineDealNetAmt);
            setValue(svcInvLineTmsg.invLineDealSlsAmt, targetInvLineDealNetAmt);
            setValue(svcInvLineTmsg.invLineFuncSlsAmt, targetInvLineDealNetAmt);
            setValue(svcInvLineTmsg.invLineDealNetAmt, targetInvLineDealNetAmt);
            setValue(svcInvLineTmsg.invLineFuncNetAmt, targetInvLineDealNetAmt);
            setValue(svcInvLineTmsg.slsTaxRate, BigDecimal.ZERO);
            setValue(svcInvLineTmsg.invLineDealTaxAmt, BigDecimal.ZERO);
            setValue(svcInvLineTmsg.invLineFuncTaxAmt, BigDecimal.ZERO);
            setValue(svcInvLineTmsg.invLineDiscRate, BigDecimal.ZERO);
            setValue(svcInvLineTmsg.invLineDealDiscAmt, BigDecimal.ZERO);
            setValue(svcInvLineTmsg.invLineFuncDiscAmt, BigDecimal.ZERO);
            setValue(svcInvLineTmsg.dealDiscUnitPrcAmt, BigDecimal.ZERO);
            setValue(svcInvLineTmsg.funcDiscUnitPrcAmt, BigDecimal.ZERO);
            setValue(svcInvLineTmsg.svcInitChrgPct, BigDecimal.ZERO);
            setValue(svcInvLineTmsg.svcTaskNum, "");
            setValue(svcInvLineTmsg.svcInvTz, "");
            setValue(svcInvLineTmsg.svcTaskCpltDt, "");
            setValue(svcInvLineTmsg.svcTaskCpltTm, "");
            setValue(svcInvLineTmsg.crDrSubRsnCd, "");
            setValue(svcInvLineTmsg.svcInvLineTpCd, SVC_INV_LINE_TP.FREIGHT_CHARGE);
            setValue(svcInvLineTmsg.invDispUnitPrcAmt, targetInvLineDealNetAmt);
            setValue(svcInvLineTmsg.uomCd, "EA");
            setValue(svcInvLineTmsg.invDispQty, BigDecimal.ONE);
            EZDTBLAccessor.insert(svcInvLineTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcInvLineTmsg.getReturnCode())) {
                return false;
            }
            // Prepare for Creating table record
            copyBaseForSvcInvLineData.setSvcInvLinePk(svcInvLineTmsg.svcInvLinePk.getValue());
            copyBaseForSvcInvLineData.setSvcInvNum(svcInvLineTmsg.svcInvNum.getValue());
            copyBaseForSvcInvLineData.setSvcInvLineNum(svcInvLineTmsg.svcInvLineNum.getValue());
            copyBaseForSvcInvLineData.setCcyCd(cdData.getCcyCd());
        }

        // Create for FRT_CHRG_REPL_INFO table record
        if (!createForFrtChrgReplInfo(copyBaseForSvcInvLineData, FRT_CHRG_REPL_STS.PROCESSED)) {
            return false;
        }
        // Create for FRT_CHRG_REPL_DTL table record
        if (!createForFrtChrgReplDtlInfo(copyBaseForSvcInvLineData, fcData)) {
            return false;
        }
        // Create for SVC_INV_ALLOC table record
        if (!createForSvcInvLineAllocInfo(rs, stmt, fcData, copyBaseForSvcInvLineData)) {
            return false;
        }
        return true;
    }

    /**
     * Insert FRT_CHRG_REPL_INFO Table
     * @param cdData
     * @return
     */
    private boolean createForFrtChrgReplInfo(CandidateData cdData, String frtChrgReplSts) {
        FRT_CHRG_REPL_INFOTMsg frtChrgReplInfoTmsg = new FRT_CHRG_REPL_INFOTMsg();
        setValue(frtChrgReplInfoTmsg.glblCmpyCd, glblCmpyCd);
        setValue(frtChrgReplInfoTmsg.svcInvLineNum, cdData.getSvcInvLineNum());
        setValue(frtChrgReplInfoTmsg.svcInvNum, cdData.getSvcInvNum());
        setValue(frtChrgReplInfoTmsg.frtChrgReplStsCd, frtChrgReplSts);
        EZDTBLAccessor.insert(frtChrgReplInfoTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(frtChrgReplInfoTmsg.getReturnCode())) {
            return false;
        }
        return true;

    }

    /**
     * Insert FRT_CHRG_REPL_DTL Table
     * @param cdData
     * @param fcData
     * @return boolean
     */
    private boolean createForFrtChrgReplDtlInfo(CandidateData cdData, FreightChargeData fcData) {
        FRT_CHRG_REPL_DTLTMsg frtChrgReplDtlTMsg = new FRT_CHRG_REPL_DTLTMsg();
        setValue(frtChrgReplDtlTMsg.glblCmpyCd, glblCmpyCd);
        setValue(frtChrgReplDtlTMsg.svcInvNum, cdData.getSvcInvNum());
        setValue(frtChrgReplDtlTMsg.svcInvLineNum, cdData.getSvcInvLineNum());
        setValue(frtChrgReplDtlTMsg.relnInvNum, fcData.getInvNum());
        setValue(frtChrgReplDtlTMsg.relnInvBolLineNum, fcData.getInvBolLineNum());
        setValue(frtChrgReplDtlTMsg.relnInvLineNum, fcData.getInvLineNum());
        EZDTBLAccessor.insert(frtChrgReplDtlTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(frtChrgReplDtlTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * Insert SVC_INV_LINE_ALLOC Table
     * @param fcData
     * @return boolean
     */
    private boolean createForSvcInvLineAllocInfo(ResultSet rs, PreparedStatement stmt, FreightChargeData fcData, CandidateData cdData) {
        List<DsInvSlsCrData> dsInvSlsCrList = new ArrayList<DsInvSlsCrData>();
        Map<String, Object> paramMap = setParamFordsInvSlsCrtMap(fcData);
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_UPDATABLE);
        try {
            stmt = this.ssmLLClient.createPreparedStatement("getdsInvSlsCr", paramMap, execParam);
            rs = stmt.executeQuery();
            while (rs.next()) {
                DsInvSlsCrData dsInvSlsCrData = new DsInvSlsCrData();
                dsInvSlsCrData.setMdseCd(rs.getString("MDSE_CD"));
                dsInvSlsCrData.setSlsRepTocCd(rs.getString("SLS_REP_TOC_CD"));
                dsInvSlsCrData.setInvLineSplPct(rs.getBigDecimal("INV_LINE_SPL_PCT"));
                dsInvSlsCrData.setDealSlsCrAmt(rs.getBigDecimal("DEAL_SLS_CR_AMT"));
                dsInvSlsCrData.setCoaCmpyCd(rs.getString("COA_CMPY_CD"));
                dsInvSlsCrData.setCoaAfflCd(rs.getString("COA_AFFL_CD"));
                dsInvSlsCrData.setCoaBrCd(rs.getString("COA_BR_CD"));
                dsInvSlsCrData.setCoaChCd(rs.getString("COA_CH_CD"));
                dsInvSlsCrData.setCoaCcCd(rs.getString("COA_CC_CD"));
                dsInvSlsCrData.setCoaAcctCd(rs.getString("COA_ACCT_CD"));
                dsInvSlsCrData.setCoaProdCd(rs.getString("COA_PROD_CD"));
                dsInvSlsCrData.setCoaProjCd(rs.getString("COA_PROJ_CD"));
                dsInvSlsCrData.setCoaExtnCd(rs.getString("COA_EXTN_CD"));
                dsInvSlsCrData.setTrxCd(rs.getString("TRX_CD"));
                dsInvSlsCrData.setTrxRsnCd(rs.getString("TRX_RSN_CD"));
                dsInvSlsCrData.setDfrdAcctgRuleCd(rs.getString("DFRD_ACCTG_RULE_CD"));
                dsInvSlsCrList.add(dsInvSlsCrData);
            }
            for (DsInvSlsCrData dsInvSlsCrData : dsInvSlsCrList) {
                SVC_INV_LINE_ALLOCTMsg svcInvLineAlloc = new SVC_INV_LINE_ALLOCTMsg();
                setValue(svcInvLineAlloc.glblCmpyCd, glblCmpyCd);
                setValue(svcInvLineAlloc.svcInvLineAllocPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_INV_LINE_ALLOC_SQ"));
                setValue(svcInvLineAlloc.svcInvLinePk, cdData.getSvcInvLinePk());
                setValue(svcInvLineAlloc.svcInvNum, cdData.getSvcInvNum());
                setValue(svcInvLineAlloc.svcInvLineNum, cdData.getSvcInvLineNum());
                setValue(svcInvLineAlloc.svcInvLineAllocNum, "001");
                setValue(svcInvLineAlloc.contrPrcAllocTpCd, "FRT");
                setValue(svcInvLineAlloc.intgMdseCd, dsInvSlsCrData.getMdseCd());
                setValue(svcInvLineAlloc.tocCd, dsInvSlsCrData.getSlsRepTocCd());
                setValue(svcInvLineAlloc.slsAllocRate, dsInvSlsCrData.getInvLineSplPct());
                setValue(svcInvLineAlloc.svcContrChrgAllocAmt, BigDecimal.ZERO);
                setValue(svcInvLineAlloc.dealGrsUnitPrcAmt, dsInvSlsCrData.getDealSlsCrAmt());
                setValue(svcInvLineAlloc.dealDiscUnitPrcAmt, BigDecimal.ZERO);
                setValue(svcInvLineAlloc.dealNetUnitPrcAmt, dsInvSlsCrData.getDealSlsCrAmt());
                setValue(svcInvLineAlloc.dealGrsTotPrcAmt, dsInvSlsCrData.getDealSlsCrAmt());
                setValue(svcInvLineAlloc.invLineDealNetAmt, dsInvSlsCrData.getDealSlsCrAmt());
                setValue(svcInvLineAlloc.invLineDealTaxAmt, BigDecimal.ZERO);
                setValue(svcInvLineAlloc.funcGrsUnitPrcAmt, dsInvSlsCrData.getDealSlsCrAmt());
                setValue(svcInvLineAlloc.funcDiscUnitPrcAmt, BigDecimal.ZERO);
                setValue(svcInvLineAlloc.funcNetUnitPrcAmt, dsInvSlsCrData.getDealSlsCrAmt());
                setValue(svcInvLineAlloc.funcGrsTotPrcAmt, dsInvSlsCrData.getDealSlsCrAmt());
                setValue(svcInvLineAlloc.invLineFuncNetAmt, dsInvSlsCrData.getDealSlsCrAmt());
                setValue(svcInvLineAlloc.invLineFuncTaxAmt, BigDecimal.ZERO);
                setValue(svcInvLineAlloc.ccyCd, cdData.getCcyCd());
                setValue(svcInvLineAlloc.coaCmpyCd, dsInvSlsCrData.getCoaCmpyCd());
                setValue(svcInvLineAlloc.coaAfflCd, dsInvSlsCrData.getCoaAcctCd());
                setValue(svcInvLineAlloc.coaBrCd, dsInvSlsCrData.getCoaBrCd());
                setValue(svcInvLineAlloc.coaChCd, dsInvSlsCrData.getCoaChCd());
                setValue(svcInvLineAlloc.coaCcCd, dsInvSlsCrData.getCoaCcCd());
                setValue(svcInvLineAlloc.coaAcctCd, dsInvSlsCrData.getCoaAcctCd());
                setValue(svcInvLineAlloc.coaProdCd, dsInvSlsCrData.getCoaProdCd());
                setValue(svcInvLineAlloc.coaProjCd, dsInvSlsCrData.getCoaProjCd());
                setValue(svcInvLineAlloc.coaExtnCd, dsInvSlsCrData.getCoaExtnCd());
                setValue(svcInvLineAlloc.svcContrBllgAllocPk, BigDecimal.ZERO);
                setValue(svcInvLineAlloc.svcContrBaseBllgPk, BigDecimal.ZERO);
                setValue(svcInvLineAlloc.svcContrMtrBllgPk, BigDecimal.ZERO);
                setValue(svcInvLineAlloc.invLineSplTpCd, "30");
                setValue(svcInvLineAlloc.invLineSplRate, BigDecimal.ZERO);
                setValue(svcInvLineAlloc.slsRepTocAllocRate, BigDecimal.ZERO);
                setValue(svcInvLineAlloc.svcContrAddlChrgBllgPk, BigDecimal.ZERO);
                setValue(svcInvLineAlloc.trxCd, dsInvSlsCrData.getTrxCd());
                setValue(svcInvLineAlloc.trxRsnCd, dsInvSlsCrData.getTrxRsnCd());
                setValue(svcInvLineAlloc.dfrdAcctgRuleCd, dsInvSlsCrData.getDfrdAcctgRuleCd());
                setValue(svcInvLineAlloc.dfrdAcctgRuleDurnAot, BigDecimal.ZERO);
                setValue(svcInvLineAlloc.ajeInvLineAllocCd, "");
                EZDTBLAccessor.insert(svcInvLineAlloc);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcInvLineAlloc.getReturnCode())) {
                    return false;
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        return true;
    }

    /**
     * Suppress SvcInvLineNum→Increment SvcInvLineNum→Padding
     * SvcInvLineNum
     * @param svcInvLineNum
     * @return
     */
    private String calcIncrementalSvcInvLineNum(String svcInvLineNum) {
        BigDecimal numberSvcInvLineNum = BigDecimal.ZERO;
        Pattern p = Pattern.compile("^0+([0-9]+.*)");
        Matcher m = p.matcher(svcInvLineNum);
        if (m.matches()) {
            String supSvcInvLineNum = m.group(1);
            numberSvcInvLineNum = new BigDecimal(supSvcInvLineNum);
        }
        return ZYPCommonFunc.leftPad(numberSvcInvLineNum.add(BigDecimal.ONE).toString(), NSAB087001Constant.SVC_INV_LINE_NUM_SIZE, NSAB087001Constant.ZERO);
    }

    /**
     * Get Latest SvcInvLineNum
     * @param rs
     * @param stmt
     * @param cdData
     * @return String
     */
    private String getLatestSvcInvLineNum(ResultSet rs, PreparedStatement stmt, CandidateData cdData) {
        String latestSvcInvLineNum = "";
        Map<String, Object> paramMap = setParamForLatestInvNumLine(cdData);
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_UPDATABLE);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getLatestSvcInvLineNum", paramMap, execParam);
            rs = stmt.executeQuery();
            while (rs.next()) {
                latestSvcInvLineNum = (rs.getString("SVC_INV_LINE_NUM"));
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        return latestSvcInvLineNum;
    }
}
