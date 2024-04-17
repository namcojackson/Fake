/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB400001;

import static com.canon.cusa.s21.batch.NFC.NFCB400001.constant.NFCB400001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.INV_LINETMsg;
import business.db.PPS_COMP_ORDTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RMNF_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Compensation IF(PPS)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/08   Hitachi         A.Kohinata      Create          N/A
 * 2018/05/25   Fujitsu         Hd.Sugawara     Update          QC#21841(Sol#495)
 * 2018/06/04   CITS            T.Wada          Update          QC#15410(Sol#246)
 * 2018/07/11   Fujitsu         W.Honda         Update          QC#27086
 * 2018/07/11   Fujitsu         W.Honda         Update          QC#27085
 * 2018/09/11   Fujitsu         T.Noguchi       Update          QC#28118,28123
 * 2018/09/25   Fujitsu         T.Noguchi       Update          QC#28231
 * 2018/10/17   Fujitsu         Hd.Sugawara     Update          QC#28842
 * 2018/10/30   Fujitsu         T.Noguchi       Update          QC#29033
 * 2018/11/09   Fujitsu         Mz.Takahashi    Update          QC#29131
 * 2019/08/15   Fujitsu         S.Yamamoto      Update          QC#52707
 * 2019/09/13   Fujitsu         Hd.Sugawara     Update          QC#52943
 * 2122/03/01   CITS            K.Ogino         Update          QC#59744
 * </pre>
 */

public class NFCB400001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** Commit Number */
    private int commitNumber = 0;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Send Compensation Complete Code */
    private String sendCompCpltCd = null;

    /** Send Compensation PPS Business Type Code */
    private String compPpsBizTpCd = null;

    @Override
    protected void initRoutine() {
        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NFCM0531E, new String[] {"Global Company Code" });
        }

        // Get Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, this.getClass().getSimpleName());
        // Mod Start 2018/10/17 QC#28842
        //if (!hasValue(this.glblCmpyCd)) {
        if (!hasValue(this.slsDt)) {
            // Mod End 2018/10/17 QC#28842
            throw new S21AbendException(NFCM0531E, new String[] {"Sales Date" });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Get Var Char Const (Send Compensation Complete Code)
        this.sendCompCpltCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SEND_COMP_CPLT_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.sendCompCpltCd)) {
            throw new S21AbendException(NFCM0531E, new String[] {VAR_CHAR_CONST_KEY_SEND_COMP_CPLT_CD });
        }

        // Get Var Char Const (Compensation PPS Business Type Code)
        this.compPpsBizTpCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_COMP_PPS_BIZ_TP_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.compPpsBizTpCd)) {
            throw new S21AbendException(NFCM0531E, new String[] {VAR_CHAR_CONST_KEY_COMP_PPS_BIZ_TP_CD });
        }

        // Initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {

        // 2018/09/25 QC#28231 Mod Start
        //createPpsCompOrd();
        createPpsCompOrd(SQL_STATEMENT_GET_COMP_INFO);
        createPpsCompOrd(SQL_STATEMENT_GET_COMP_INFO_RMA);
        // 2018/09/25 QC#28231 Mod End

        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFCB400001().executeBatch(NFCB400001.class.getSimpleName());

    }

    // 2018/09/25 QC#28231 Mod Start
    //private void createPpsCompOrd() {
    private void createPpsCompOrd(String statementId) {
    // 2018/09/25 QC#28231 Mod End

        List<PPS_COMP_ORDTMsg> ppsCompOrdTMsgList = new ArrayList<PPS_COMP_ORDTMsg>();
        List<INV_LINETMsg> invLineTMsgList = new ArrayList<INV_LINETMsg>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> preInvMap = new HashMap<String, Object>();
        Map<String, Object> errInvMap = new HashMap<String, Object>();

        try {
            // 2018/09/25 QC#28231 Mod Start
            //stmt = this.ssmLLClient.createPreparedStatement("getCompInfo", getParamMap(), getExecParam());
            stmt = this.ssmLLClient.createPreparedStatement(statementId, getParamMap(), getExecParam());
            // 2018/09/25 QC#28231 Mod End
            rs = stmt.executeQuery();

            while (rs.next()) {
                PPS_COMP_ORDTMsg ppsCompOrdTMsg = setPpsCompOrd(rs);
                ppsCompOrdTMsgList.add(ppsCompOrdTMsg);

                if (changeInv(rs, preInvMap) && !isErrInv(preInvMap, errInvMap)) {
                    INV_LINETMsg invLineTMsg = setInvLine(preInvMap);
                    invLineTMsgList.add(invLineTMsg);
                    errInvMap = new HashMap<String, Object>();
                }

                if (ppsCompOrdTMsgList.size() >= this.commitNumber) {
                    int insertCount = insertPpsCompOrd(ppsCompOrdTMsgList);
                    if (insertCount == ppsCompOrdTMsgList.size()) {
                        if (updateInvLine(invLineTMsgList)) {
                            this.normalCount += insertCount;
                            commit();
                        } else {
                            this.errorCount += ppsCompOrdTMsgList.size();
                            rollback();
                            errInvMap = setInvMap(rs);
                        }
                    } else {
                        this.errorCount += ppsCompOrdTMsgList.size();
                        rollback();
                        errInvMap = setInvMap(rs);
                    }
                    ppsCompOrdTMsgList.clear();
                    invLineTMsgList.clear();
                }
                preInvMap = setInvMap(rs);
            }

            if (!preInvMap.isEmpty() && !isErrInv(preInvMap, errInvMap)) {
                INV_LINETMsg invLineTMsg = setInvLine(preInvMap);
                invLineTMsgList.add(invLineTMsg);
            }

            if (ppsCompOrdTMsgList.size() > 0) {
                int insertCount = insertPpsCompOrd(ppsCompOrdTMsgList);
                if (insertCount == ppsCompOrdTMsgList.size()) {
                    if (updateInvLine(invLineTMsgList)) {
                        this.normalCount += insertCount;
                        commit();
                    } else {
                        this.errorCount += ppsCompOrdTMsgList.size();
                        rollback();
                    }
                } else {
                    this.errorCount += ppsCompOrdTMsgList.size();
                    rollback();
                }
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private Map<String, Object> getParamMap() throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.slsDt);
        paramMap.put("lineBizTpCd", this.compPpsBizTpCd);
        List<String> sendCompCpltCdList = S21StringUtil.toList(this.sendCompCpltCd);
        paramMap.put("compProcStsCdList", sendCompCpltCdList);
        paramMap.put("month01", STR_01);
        paramMap.put("month03", STR_03);
        paramMap.put("month04", STR_04);
        paramMap.put("month06", STR_06);
        paramMap.put("month07", STR_07);
        paramMap.put("month09", STR_09);
        paramMap.put("month10", STR_10);
        paramMap.put("month12", STR_12);
        paramMap.put("qtr01", STR_01);
        paramMap.put("qtr02", STR_02);
        paramMap.put("qtr03", STR_03);
        paramMap.put("qtr04", STR_04);
        paramMap.put("dateFormat", DATE_FORMAT);
        paramMap.put("ordCatgCtxTpLease", ORD_CATG_CTX_TP.LEASE_ORDER_VALUE_SET);
        paramMap.put("coaMdseTpMachine", COA_PROJ.MACHINE);
        paramMap.put("rmnfOrdStsCompleted", RMNF_ORD_STS.COMPLETED);
        paramMap.put("defSerNum", DEF_SER_NUM);
        // QC#15410 Mod Start
//        String[] dsContrDtlStsCdList = new String[] {DS_CONTR_DTL_STS.CANCELLED, DS_CONTR_DTL_STS.SAVED, DS_CONTR_DTL_STS.SUBMITED, DS_CONTR_DTL_STS.SIGNED, DS_CONTR_DTL_STS.ORDER };
        String[] dsContrDtlStsCdList = new String[] {DS_CONTR_DTL_STS.CANCELLED, DS_CONTR_DTL_STS.SAVED, DS_CONTR_DTL_STS.SUBMITED, DS_CONTR_DTL_STS.SIGNED, DS_CONTR_DTL_STS.ORDER, DS_CONTR_DTL_STS.TERMINATED };
        // QC#15410 Mod End
        paramMap.put("dsContrDtlStsCdList", dsContrDtlStsCdList);
        // Add Start 2018/07/11 QC#27086
        String[] dsContrDtlStsCdForRentalList = new String[] {DS_CONTR_DTL_STS.CANCELLED, DS_CONTR_DTL_STS.SAVED, DS_CONTR_DTL_STS.ORDER, DS_CONTR_DTL_STS.TERMINATED };
        paramMap.put("dsContrDtlStsCdForRentalList", dsContrDtlStsCdForRentalList);
        paramMap.put("ordCatgCtxTRetal", "RENTAL_ORDER");
        // Add End 2018/07/11 QC#27086
        // Add Start 2018/05/25 QC#21841(Sol#495)
        paramMap.put("invLineCatgCd", INV_LINE_CATG.ITEM);
        // Add End 2018/05/25 QC#21841(Sol#495)
        // 2018/09/11 QC#28118,28123 Add Start
        paramMap.put("crCdCredit", CR_REBIL.CREDIT);
        paramMap.put("crCdRebill", CR_REBIL.REBILL);
        // 2018/09/11 QC#28118,28123 Add End
        // 2018/09/25 QC#28231 Add Start
        paramMap.put("invLineCatgCharge", INV_LINE_CATG.CHARGE);
        paramMap.put("invLineCatgFreight", INV_LINE_CATG.FREIGHT);
        // 2018/09/25 QC#28231 Add End
        // 2018/10/30 QC#29033 Add Start
        paramMap.put("invTrxSrcTpWS", TRX_SRC_TP.WHOLE_SALES);
        paramMap.put("invTrxSrcTpWSReturn", TRX_SRC_TP.WHOLE_SALES_RETURN);
        // 2018/10/30 QC#29033 Add End

        // 2018/11/09 QC#29131 Add End
        paramMap.put("creditMemo", INV_TP.CREDIT_MEMO);
        // 2018/11/09 QC#29131 Add End

        // 2019/08/15 QC#52707 Add Start
        paramMap.put("rwsStsCancel", RWS_STS.CANCELED);
        // 2019/08/15 QC#52707 Add End
        // Add Start 2019/09/13 QC#52943
        paramMap.put("flgOnY", ZYPConstant.FLG_ON_Y);
        // Add End 2019/09/13 QC#52943

        return paramMap;
    }

    private S21SsmExecutionParameter getExecParam() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(MAX_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execParam;
    }

    private Map<String, Object> setInvMap(ResultSet rs) throws SQLException {
        Map<String, Object> invMap = new HashMap<String, Object>();
        invMap.put("INV_NUM", rs.getString("INV_NUM"));
        invMap.put("INV_BOL_LINE_NUM", rs.getString("INV_BOL_LINE_NUM"));
        invMap.put("INV_LINE_NUM", rs.getString("INV_LINE_NUM"));
        invMap.put("INV_LINE_SUB_NUM", rs.getString("INV_LINE_SUB_NUM"));
        invMap.put("INV_LINE_SUB_TRX_NUM", rs.getString("INV_LINE_SUB_TRX_NUM"));
        return invMap;
    }

    private boolean changeInv(ResultSet rs, Map<String, Object> invMap) throws SQLException {
        if (invMap.isEmpty()) {
            return false;
        }
        if (!rs.getString("INV_NUM").equals((String) invMap.get("INV_NUM"))) {
            return true;
        }
        if (!rs.getString("INV_BOL_LINE_NUM").equals((String) invMap.get("INV_BOL_LINE_NUM"))) {
            return true;
        }
        if (!rs.getString("INV_LINE_NUM").equals((String) invMap.get("INV_LINE_NUM"))) {
            return true;
        }
        if (!rs.getString("INV_LINE_SUB_NUM").equals((String) invMap.get("INV_LINE_SUB_NUM"))) {
            return true;
        }
        if (!rs.getString("INV_LINE_SUB_TRX_NUM").equals((String) invMap.get("INV_LINE_SUB_TRX_NUM"))) {
            return true;
        }
        return false;
    }

    private boolean isErrInv(Map<String, Object> invMap, Map<String, Object> errInvMap) throws SQLException {
        if (errInvMap.isEmpty()) {
            return false;
        }
        if (!((String) invMap.get("INV_NUM")).equals((String) errInvMap.get("INV_NUM"))) {
            return false;
        }
        if (!((String) invMap.get("INV_BOL_LINE_NUM")).equals((String) errInvMap.get("INV_BOL_LINE_NUM"))) {
            return false;
        }
        if (!((String) invMap.get("INV_LINE_NUM")).equals((String) errInvMap.get("INV_LINE_NUM"))) {
            return false;
        }
        if (!((String) invMap.get("INV_LINE_SUB_NUM")).equals((String) errInvMap.get("INV_LINE_SUB_NUM"))) {
            return false;
        }
        if (!((String) invMap.get("INV_LINE_SUB_TRX_NUM")).equals((String) errInvMap.get("INV_LINE_SUB_TRX_NUM"))) {
            return false;
        }
        return true;
    }

    private PPS_COMP_ORDTMsg setPpsCompOrd(ResultSet rs) throws SQLException {
        PPS_COMP_ORDTMsg tMsg = new PPS_COMP_ORDTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.ppsCompOrdPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PPS_COMP_ORD_SQ));
        setValue(tMsg.cpoOrdNum, rs.getString("CPO_ORD_NUM"));
        setValue(tMsg.dsOrdPosnNum, rs.getString("DS_ORD_POSN_NUM"));
        setValue(tMsg.cpoDtlLineNum, rs.getString("CPO_DTL_LINE_NUM"));
        setValue(tMsg.cpoDtlLineSubNum, rs.getString("CPO_DTL_LINE_SUB_NUM"));
        setValue(tMsg.newCustFlg, rs.getString("NEW_CUST_FLG"));
        setValue(tMsg.billToCustAcctCd, rs.getString("BILL_TO_CUST_ACCT_CD"));
        setValue(tMsg.billToCustAcctNm, rs.getString("BILL_TO_CUST_ACCT_NM"));
        setValue(tMsg.shipToCustAcctCd, rs.getString("SHIP_TO_CUST_ACCT_CD"));
        setValue(tMsg.shipToCustAcctNm, rs.getString("SHIP_TO_CUST_ACCT_NM"));
        setValue(tMsg.crCustCd, rs.getString("CR_CUST_CD"));
        setValue(tMsg.leaseOrdFlg, rs.getString("LEASE_FLG"));
        setValue(tMsg.svcConfigMstrPk, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
        setValue(tMsg.contrDurnAot, rs.getBigDecimal("CONTR_DURN_AOT"));
        setValue(tMsg.compQtrCd, rs.getString("COMP_QTR_CD"));
        setValue(tMsg.ordDt, rs.getString("ORD_DT"));
        setValue(tMsg.ordBookDt, rs.getString("ORD_BOOK_DT"));
        setValue(tMsg.ordRtrnDt, rs.getString("ORD_RTRN_DT"));
        setValue(tMsg.invDt, rs.getString("INV_DT"));
        setValue(tMsg.invNum, rs.getString("INV_NUM"));
        setValue(tMsg.invLineNum, rs.getString("INV_LINE_NUM"));
        setValue(tMsg.invLineSubNum, rs.getString("INV_LINE_SUB_NUM"));
        setValue(tMsg.slsRepPsnCd, rs.getString("SLS_REP_PSN_CD"));
        setValue(tMsg.istlRepPsnCd, rs.getString("ISTL_REP_PSN_CD"));
        setValue(tMsg.compBllgQty, rs.getBigDecimal("COMP_BLLG_QTY"));
        setValue(tMsg.mdseCd, rs.getString("MDSE_CD"));
        setValue(tMsg.mdseDescShortTxt, rs.getString("MDSE_DESC_SHORT_TXT"));
        setValue(tMsg.t_MdlNm, rs.getString("T_MDL_NM"));
        setValue(tMsg.serNum, rs.getString("SER_NUM"));
        setValue(tMsg.boxNewQty, rs.getBigDecimal("BOX_NEW_QTY"));
        setValue(tMsg.boxUsedQty, rs.getBigDecimal("BOX_USED_QTY"));
        setValue(tMsg.compValAmt, rs.getBigDecimal("COMP_VAL_AMT"));
        setValue(tMsg.dsOrdCatgCd, rs.getString("DS_ORD_CATG_CD"));
        setValue(tMsg.dsOrdTpCd, rs.getString("DS_ORD_TP_CD"));
        setValue(tMsg.compPrtrFlg, rs.getString("COMP_PRTR_FLG"));
        setValue(tMsg.crRebilRsnCd, rs.getString("CR_REBIL_RSN_CD"));
        setValue(tMsg.dsOrdRsnCd, rs.getString("DS_ORD_RSN_CD"));
        // Add Start 2018/10/17 QC#28842
        setValue(tMsg.invBolLineNum, rs.getString("INV_BOL_LINE_NUM"));
        // Add End 2018/10/17 QC#28842
        // Add Start 2022/03/01 QC#59744
        setValue(tMsg.aquNum, rs.getString("AQU_NUM"));
        setValue(tMsg.addShipToCtyAddr, rs.getString("ADD_SHIP_TO_CTY_ADDR"));
        setValue(tMsg.addShipToStCd, rs.getString("ADD_SHIP_TO_ST_CD"));
        setValue(tMsg.dealPrcListPrcAmt, rs.getBigDecimal("DEAL_PRC_LIST_PRC_AMT"));
        // Add End 2022/03/01 QC#59744
        return tMsg;
    }

    private INV_LINETMsg setInvLine(Map<String, Object> invMap) {
        INV_LINETMsg tMsg = new INV_LINETMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.invNum, (String) invMap.get("INV_NUM"));
        setValue(tMsg.invBolLineNum, (String) invMap.get("INV_BOL_LINE_NUM"));
        setValue(tMsg.invLineNum, (String) invMap.get("INV_LINE_NUM"));
        setValue(tMsg.invLineSubNum, (String) invMap.get("INV_LINE_SUB_NUM"));
        setValue(tMsg.invLineSubTrxNum, (String) invMap.get("INV_LINE_SUB_TRX_NUM"));
        return tMsg;
    }

    private int insertPpsCompOrd(List<PPS_COMP_ORDTMsg> tMsgList) {
        int insertCount = S21FastTBLAccessor.insert(tMsgList.toArray(new PPS_COMP_ORDTMsg[tMsgList.size()]));
        return insertCount;
    }

    private boolean updateInvLine(List<INV_LINETMsg> tMsgList) {
        if (tMsgList.size() == 0) {
            return true;
        }

        List<INV_LINETMsg> updTMsgList = new ArrayList<INV_LINETMsg>(tMsgList.size());
        for (INV_LINETMsg tMsg : tMsgList) {
            tMsg = (INV_LINETMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsg);
            if (tMsg == null) {
                continue;
            }
            setValue(tMsg.compProcStsCd, COMP_PROC_STS_COMPLETE);
            updTMsgList.add(tMsg);
        }

        int updateCount = S21FastTBLAccessor.update(updTMsgList.toArray(new INV_LINETMsg[updTMsgList.size()]));
        if (updateCount != updTMsgList.size()) {
            return false;
        }
        return true;
    }
}
