/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB126001;

import static com.canon.cusa.s21.batch.NFB.NFBB126001.constant.NFBB126001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import business.db.AP_LSE_BO_WF_RQSTTMsg;
import business.parts.NMZC201001PMsg;
import business.parts.NMZC201002PMsg;
import business.parts.NMZC201003PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC201001.NMZC201001;
import com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PAY_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRNT_VND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_PMT_METH;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizProcess;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizWorkItem;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.nwf.util.process.S21NwfUtilProcessFactory;
import com.canon.cusa.s21.framework.nwf.util.token.S21NwfUtilTokenObj;

/**
 * <pre>
 * Lease Buyout Receive
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/15   Hitachi         K.Kojima        Create          N/A
 * 2016/03/09   Hitachi         K.Kojima        Update          CSA QC#5200
 * 2016/03/10   Hitachi         K.Kojima        Update          CSA QC#4998
 * 2016/03/15   Hitachi         K.Kojima        Update          CSA QC#5115
 * 2016/03/16   Hitachi         K.Kojima        Update          CSA QC#5297
 * 2016/03/16   Hitachi         K.Kojima        Update          CSA QC#5146
 * 2016/04/05   Hitachi         K.Kojima        Update          CSA QC#5531
 * 2016/07/05   Hitach          A.Kohinata      Update          CSA QC#10692
 * 2016/09/16   Hitachi         K.Kojima        Update          QC#14412
 * 2016/10/12   Hitachi         K.Kojima        Update          QC#13088
 * 2016/10/25   Hitachi         K.Kojima        Update          QC#13088
 * 2016/12/01   Hitachi         Y.Tsuchimoto    Update          QC#13088
 * 2016/12/12   Fujitsu         H.Ikeda         Update          QC#15823
 * 2017/06/23   CITS            T.Kikuhara      Update          QC#19412
 * 2019/08/03   Fujitsu         S.Takami        Update          QC#52191
 * 2020/04/09   Fujitsu         H.Mizukami      Update          QC#56153
 * 2021/03/25   CITS            R.Azucena       Update          QC#58394
 * 2022/08/26   Hitachi         S.Naya          Update          QC#60114
 * 2023/01/13   CITS            T.Goushi        Update          QC#61049
 * </pre>
 */
public class NFBB126001 extends S21BatchMain {

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales date */
    private String batProcDate = null;

    /** Error Count */
    private int errorCount = 0;

    /** Normal Count */
    private int normalCount = 0;

    /** Commit Max Number */
    private int commitNumber = 0;

    /** VAR CHAR CONST VALUE : LSE_BO_DS_ORD_LINE_CATG_CD */
    private String lseBoDsOrdLineCatgCd = null;

    /** VAR CHAR CONST VALUE : CFS_COA_AFFL_CD */
    private String cfsCoaAfflCd = null;

    /** VAR CHAR CONST VALUE : LEASE_BYOT_APVL_WF_ID */
    private String leaseByotApvlWfId = null;

    // START 2016/03/16 K.Kojima [QC#5146,ADD]
    /** VAR CHAR CONST VALUE : LSE_BO_DEFAULT_SPLY_NM */
    private String lseBoDefaultSplyNm = null;

    // END 2016/03/16 K.Kojima [QC#5146,ADD]

    // START 2016/09/16 K.Kojima [QC#14412,ADD]
    /** VAR CHAR CONST VALUE : SPLY_COA_VALUE */
    private String splyCoaValue = null;

    /** VAR CHAR CONST VALUE : PRE_PMT_COA_VALUE */
    private String prePmtCoaValue = null;

    // END 2016/09/16 K.Kojima [QC#14412,ADD]

    // START 2023/01/13 T.Goushi [QC#61049,ADD]
    private Map<String, Integer> apInvSeqMap = null;
    // END 2023/01/13 T.Goushi [QC#61049,ADD]

    @Override
    protected void initRoutine() {
        // "Global Company Code" Mandatory
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NFBM0199E);
        }

        // "Batch Process Date"
        this.batProcDate = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.batProcDate)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NFBM0207E, new String[] {"Batch Process Date" });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // get VAR_CHAR_CONST
        this.lseBoDsOrdLineCatgCd = ZYPCodeDataUtil.getVarCharConstValue(LSE_BO_DS_ORD_LINE_CATG_CD, this.glblCmpyCd);
        this.cfsCoaAfflCd = ZYPCodeDataUtil.getVarCharConstValue(CFS_COA_AFFL_CD, this.glblCmpyCd);
        this.leaseByotApvlWfId = ZYPCodeDataUtil.getVarCharConstValue(LEASE_BYOT_APVL_WF_ID, this.glblCmpyCd);
        // START 2016/03/16 K.Kojima [QC#5146,ADD]
        this.lseBoDefaultSplyNm = ZYPCodeDataUtil.getVarCharConstValue(LSE_BO_DEFAULT_SPLY_NM, this.glblCmpyCd);
        // START 2021/03/25 R.Azucena [QC#58394, ADD]
        if (this.lseBoDefaultSplyNm != null) {
            this.lseBoDefaultSplyNm = this.lseBoDefaultSplyNm.toUpperCase();
        }
        // END 2021/03/25 R.Azucena [QC#58394, ADD]
        // END 2016/03/16 K.Kojima [QC#5146,ADD]
        // START 2016/09/16 K.Kojima [QC#14412,ADD]
        this.splyCoaValue = ZYPCodeDataUtil.getVarCharConstValue(SPLY_COA_VALUE, this.glblCmpyCd);
        this.prePmtCoaValue = ZYPCodeDataUtil.getVarCharConstValue(PRE_PMT_COA_VALUE, this.glblCmpyCd);
        // END 2016/09/16 K.Kojima [QC#14412,ADD]
        // START 2023/01/13 T.Goushi [QC#61049,ADD]
        this.apInvSeqMap = new HashMap<String, Integer>();
        // END 2023/01/13 T.Goushi [QC#61049,ADD]
    }

    @Override
    protected void mainRoutine() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AP_LSE_BO_WF_RQSTTMsg> tMsgList = new ArrayList<AP_LSE_BO_WF_RQSTTMsg>();
        // START 2016/12/01 Y.Tsuchimoto [QC#13088,ADD]
        List<String> dplyLineNumList = new ArrayList<String>();
        // END   2016/12/01 Y.Tsuchimoto [QC#13088,ADD]

        try {
            Map<String, Object> stesParam = new HashMap<String, Object>();
            stesParam.put("glblCmpyCd", this.glblCmpyCd);
            stesParam.put("ordLineStsCd", ORD_LINE_STS.CLOSED);
            stesParam.put("dsOrdLineCatgCd", this.lseBoDsOrdLineCatgCd);
            stesParam.put("limitThruDt", LIMIT_THRU_DT);

            ps = this.ssmLLClient.createPreparedStatement("getLeaseBuyoutLine", stesParam, execParam());
            rs = ps.executeQuery();

            while (rs.next()) {
                AP_LSE_BO_WF_RQSTTMsg tMsg = new AP_LSE_BO_WF_RQSTTMsg();
                // START 2016/03/31 K.Kojima [QC#5531,DEL]
                // Map<String, Object> apvlLimitData =
                // getApvlLimitData(rs.getString(MDSE_CD));
                // END 2016/03/31 K.Kojima [QC#5531,DEL]
                checkRebillInvoice(rs, tMsg);
                // START 2016/03/16 K.Kojima [QC#5146,ADD]
                String splyNm = rs.getString(SPLY_NM);
                if (splyNm == null || splyNm.length() == 0) {
                    splyNm = this.lseBoDefaultSplyNm;
                }
                // END 2016/03/16 K.Kojima [QC#5146,ADD]
                // START 2016/03/16 K.Kojima [QC#5146,MOD]
                // setSupplierInfo(rs, tMsg);
                // createTMsg(rs, tMsg, apvlLimitData);
                // 2016/07/05 QC#10692 Mod Start
                if (!setSupplierInfo(rs, tMsg, splyNm)) {
                    this.errorCount++;
                    continue;
                }
                // 2016/07/05 QC#10692 Mod End
                // START 2016/03/31 K.Kojima [QC#5531,MOD]
                // createTMsg(rs, tMsg, apvlLimitData, splyNm);
                createTMsg(rs, tMsg, splyNm);
                // END 2016/03/31 K.Kojima [QC#5531,MOD]
                // END 2016/03/16 K.Kojima [QC#5146,MOD]
                tMsgList.add(tMsg);
                // START 2016/12/01 Y.Tsuchimoto [QC#13088,ADD]
                dplyLineNumList.add(rs.getString(DPLY_LINE_NUM));
                // END   2016/12/01 Y.Tsuchimoto [QC#13088,ADD]
                if (this.commitNumber == tMsgList.size()) {
                    // START 2016/12/01 Y.Tsuchimoto [QC#13088,MOD]
                    //int commitCount = executionUpdate(tMsgList);
                    int commitCount = executionUpdate(tMsgList, dplyLineNumList);
                    // END   2016/12/01 Y.Tsuchimoto [QC#13088,MOD]
                    this.normalCount += commitCount;
                    tMsgList = new ArrayList<AP_LSE_BO_WF_RQSTTMsg>();
                    // START 2016/12/01 Y.Tsuchimoto [QC#13088,ADD]
                    dplyLineNumList = new ArrayList<String>();
                    // END   2016/12/01 Y.Tsuchimoto [QC#13088,ADD]
                }
            }
            if (tMsgList.size() > 0) {
                // START 2016/12/01 Y.Tsuchimoto [QC#13088,MOD]
                //int commitCount = executionUpdate(tMsgList);
                int commitCount = executionUpdate(tMsgList, dplyLineNumList);
                // END   2016/12/01 Y.Tsuchimoto [QC#13088,MOD]
                this.normalCount += commitCount;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            // 2016/07/05 QC#10692 Mod Start
            this.termCd = TERM_CD.WARNING_END;
            // 2016/07/05 QC#10692 Mod End
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFBB126001().executeBatch(NFBB126001.class.getSimpleName());
    }

    /**
     * execParam
     * @return S21SsmExecutionParameter
     */
    private S21SsmExecutionParameter execParam() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    /**
     * executionUpdate
     * @param inTMsgList List<AP_LSE_BO_WF_RQSTTMsg>
     * @param dplyLineNumList List<String>
     * @return int
     */
    // START 2016/12/01 Y.Tsuchimoto [QC#13088,MOD]
    //private int executionUpdate(List<AP_LSE_BO_WF_RQSTTMsg> inTMsgList) {
    private int executionUpdate(List<AP_LSE_BO_WF_RQSTTMsg> inTMsgList, List<String> dplyLineNumList) {
    // END   2016/12/01 Y.Tsuchimoto [QC#13088,MOD]
        // START 2016/12/13 H.Ikeda [QC#15823,MOD]
        AP_LSE_BO_WF_RQSTTMsg tMsgs[] = new AP_LSE_BO_WF_RQSTTMsg[inTMsgList.size()];
        int updateCount = 0;
        for (AP_LSE_BO_WF_RQSTTMsg tMsg : inTMsgList) {
            // START 2016/03/31 K.Kojima [QC#5531,DEL]
            // S21FastTBLAccessor.insert(tMsg);
            // END 2016/03/31 K.Kojima [QC#5531,DEL]
            // START 2017/1/27 H.Ikeda [QC#13088,MOD]
            // START 2016/12/01 Y.Tsuchimoto [QC#17091,MOD]
            //startWorkflow(tMsg);
            String dplyLineNum = dplyLineNumList.get(updateCount);
            startWorkflow(tMsg, dplyLineNum);
            // END   2016/12/01 Y.Tsuchimoto [QC#13088,MOD]
            Map<String, Object> apvlLimitData = getApvlLimitData(this.leaseByotApvlWfId, tMsg.wfPk.getValue().toString());
            setValue(tMsg.apvlLimitToAmt, (BigDecimal) apvlLimitData.get(COND_VAL_NUM_01));
            setValue(tMsg.apvlRspbNm, ZYPCodeDataUtil.getVarCharConstValue((String) apvlLimitData.get(ATTRB_COND_VAL_TXT_01), this.glblCmpyCd));
            // END 2017/1/27 H.Ikeda [QC#17091,MOD]
            tMsgs[updateCount] = tMsg;
            //S21FastTBLAccessor.insert(tMsg);
            updateCount++;
        }
        if (updateCount > 0) {
            int rCnt = S21FastTBLAccessor.insert(tMsgs);
            if (rCnt < 1) {
                throw new S21AbendException(NFZM0028E, new String[]{"AP_LSE_BO_WF_RQST"});
            }
        }
        // END   2016/12/13 H.Ikeda [QC#15823,MOD]
        commit();
        return updateCount;
    }

    /**
     * createTMsg
     * @param rs ResultSet
     * @param tMsg AP_LSE_BO_WF_RQSTTMsg
     * @param apvlLimitData Map<String, Object>
     * @param splyNm String
     * @return AP_LSE_BO_WF_RQSTTMsg
     * @throws SQLException SQLException
     */
    // START 2016/03/16 K.Kojima [QC#5146,MOD]
    // private AP_LSE_BO_WF_RQSTTMsg createTMsg(ResultSet rs,
    // AP_LSE_BO_WF_RQSTTMsg tMsg, Map<String, Object> apvlLimitData)
    // throws SQLException {
    // START 2016/03/31 K.Kojima [QC#5531,MOD]
    // private AP_LSE_BO_WF_RQSTTMsg createTMsg(ResultSet rs,
    // AP_LSE_BO_WF_RQSTTMsg tMsg, Map<String, Object> apvlLimitData,
    // String splyNm) throws SQLException {
    private AP_LSE_BO_WF_RQSTTMsg createTMsg(ResultSet rs, AP_LSE_BO_WF_RQSTTMsg tMsg, String splyNm) throws SQLException {
        // END 2016/03/31 K.Kojima [QC#5531,MOD]
        // END 2016/03/16 K.Kojima [QC#5146,MOD]
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.cpoOrdNum, rs.getString(CPO_ORD_NUM));
        setValue(tMsg.cpoOrdTpCd, rs.getString(CPO_ORD_TP_CD));
        setValue(tMsg.cpoDtlLineNum, rs.getString(CPO_DTL_LINE_NUM));
        // START 2016/03/10 K.Kojima [QC#4998,MOD]
        // setValue(tMsg.cpoOrdTs, rs.getString(CPO_ORD_TS));
        String cpoOrdTs = rs.getString(CPO_ORD_TS);
        if (cpoOrdTs != null) {
            cpoOrdTs = ZYPCommonFunc.rightPad(cpoOrdTs, 17, "0");
        }
        setValue(tMsg.cpoOrdTs, cpoOrdTs);
        // END 2016/03/10 K.Kojima [QC#4998,MOD]
        setValue(tMsg.dsOrdLineCatgCd, rs.getString(DS_ORD_LINE_CATG_CD));
        setValue(tMsg.apvlTpTxt, APVL_TP_TXT_ACCOUNTING);
        setValue(tMsg.cpoDtlFuncNetAmt, rs.getBigDecimal(CPO_DTL_DEAL_NET_AMT));
        setValue(tMsg.invNum, rs.getString(INV_NUM));
        setValue(tMsg.invBolNum, rs.getString(INV_BOL_LINE_NUM));
        setValue(tMsg.invLineNum, rs.getString(INV_LINE_NUM));
        setValue(tMsg.invLineSubNum, rs.getString(INV_LINE_SUB_NUM));
        setValue(tMsg.invLineSubTrxNum, rs.getString(INV_LINE_SUB_TRX_NUM));
        setValue(tMsg.shipToCustCd, rs.getString(SHIP_TO_CUST_CD));
        setValue(tMsg.shipToLocNm, rs.getString(SHIP_TO_LOC_NM));
        setValue(tMsg.coaBrCd, rs.getString(COA_BR_CD));
        setValue(tMsg.coaBrNm, rs.getString(COA_BR_NM));
        setValue(tMsg.mdseCd, rs.getString(MDSE_CD));
        setValue(tMsg.mdseNm, rs.getString(MDSE_NM));
        setValue(tMsg.apLseBoRqstAmt, rs.getBigDecimal(CPO_DTL_FUNC_NET_AMT));
        setValue(tMsg.splyTpCd, rs.getString(SPLY_TP_CD));
        // START 2016/03/16 K.Kojima [QC#5146,MOD]
        // setValue(tMsg.splyNm, rs.getString(SPLY_NM));
        // START 2019/08/03 S.Takami [QC#52191,MOD]
//        setValue(tMsg.splyNm, splyNm);
        setValue(tMsg.splyNm, getMaxLengthString(splyNm, tMsg.getAttr("splyNm").getDigit()));
        // End 2019/08/03 S.Takami [QC#52191,MOD]
        // END 2016/03/16 K.Kojima [QC#5146,MOD]
        setValue(tMsg.splyFirstAddr, rs.getString(SPLY_FIRST_ADDR));
        setValue(tMsg.splyCtyAddr, rs.getString(SPLY_CTY_ADDR));
        setValue(tMsg.splyStCd, rs.getString(SPLY_ST_CD));
        setValue(tMsg.splyPostCd, rs.getString(SPLY_POST_CD));
        setValue(tMsg.apvlSqNum, BigDecimal.ONE);
        // START 2016/03/31 K.Kojima [QC#5531,DEL]
        // if (apvlLimitData != null) {
        // setValue(tMsg.apvlLimitToAmt, (BigDecimal)
        // apvlLimitData.get(COND_VAL_NUM_01));
        // setValue(tMsg.apvlRspbNm,
        // ZYPCodeDataUtil.getVarCharConstValue((String)
        // apvlLimitData.get(ATTRB_COND_VAL_TXT_01),
        // this.glblCmpyCd));
        // }
        // END 2016/03/31 K.Kojima [QC#5531,DEL]
        // APVL_USR_ID is null.
        // APVL_USR_NM is null.
        // AP_WF_ML_NTFY_DT is null.
        // AP_WF_ML_NTFY_NUM is null.
        setValue(tMsg.apDsWfStsCd, AP_DS_WF_STS.PENDING);
        // AP_WF_RQST_CMNT_TXT is already set.
        // AP_WF_RQST_RSP_DT is null.
        setValue(tMsg.apWfRqstCratUsrId, rs.getString(ADMIN_PSN_CD));
        setValue(tMsg.apWfRqstCratDt, this.batProcDate);
        setValue(tMsg.apWfRqstModUsrId, rs.getString(ADMIN_PSN_CD));
        setValue(tMsg.apWfRqstModDt, this.batProcDate);
        // PRNT_VND_PK is already set.
        // PRNT_VND_CD is already set.
        // VND_PK is already set.
        // VND_CD is already set.
        // START 2016/03/16 K.Kojima [QC#5297,MOD]
        // setValue(tMsg.apInvNum, rs.getString(CPO_ORD_NUM) +
        // STR_FOR_BINDING_AP_INV_NUM +
        // rs.getString(CPO_DTL_LINE_NUM));
        String invNum = rs.getString(INV_NUM);
        if (invNum.length() > 11) {
            invNum = invNum.substring(invNum.length() - 11);
        }
        String apInvSeq = null;
        // START 2023/01/13 T.Goushi [QC#61049,ADD]
        if (this.apInvSeqMap.containsKey(invNum)) {
            Integer seqNo = this.apInvSeqMap.get(invNum);
            seqNo++;
            apInvSeq = ZYPCommonFunc.leftPad(seqNo.toString(), 4, "0");
            this.apInvSeqMap.put(invNum, seqNo);
        } else {
            apInvSeq = "0001";
            this.apInvSeqMap.put(invNum, new Integer(1));
        }
        setValue(tMsg.apInvNum, invNum + apInvSeq);
        // END 2023/01/13 T.Goushi [QC#61049,ADD]
        // START 2023/01/13 T.Goushi [QC#61049,DEL]
        // String invLineNum = rs.getString(INV_LINE_NUM);
        // if (invLineNum.length() > 4) {
        //     invLineNum = invLineNum.substring(invLineNum.length() - 4);
        // } else {
        //     invLineNum = ZYPCommonFunc.leftPad(invLineNum, 4, "0");
        // }
        // setValue(tMsg.apInvNum, invNum + invLineNum);
        // END 2023/01/13 T.Goushi [QC#61049,DEL]
        // END 2016/03/16 K.Kojima [QC#5297,MOD]
        setValue(tMsg.apInvAmt, rs.getBigDecimal(CPO_DTL_DEAL_NET_AMT));
        // START 2016/10/12 K.Kojima [QC#13088,ADD]
        setValue(tMsg.crCpoOrdNum, rs.getString(ORIG_CPO_ORD_NUM));
        setValue(tMsg.crArInvNum, rs.getString(ORIG_INV_NUM));
        // END 2016/10/12 K.Kojima [QC#13088,ADD]
        // CR_AP_INV_NUM is null.
        setValue(tMsg.wfPk, new BigDecimal(ZYPExtnNumbering.getUniqueID(this.glblCmpyCd, LSE_BO_WF_PK)));
        // START 2016/10/25 K.Kojima [QC#13088,ADD]
        setValue(tMsg.dsOrdCatgCd, rs.getString(DS_ORD_CATG_CD));
        // END 2016/10/25 K.Kojima [QC#13088,ADD]
        return tMsg;
    }

    /**
     * check RebillInvoice
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException
     */
    private boolean checkRebillInvoice(ResultSet rs, AP_LSE_BO_WF_RQSTTMsg tMsg) throws SQLException {
        String origInvNum = rs.getString(ORIG_INV_NUM);
        if (origInvNum != null && origInvNum.length() > 0) {
            // START 2020/04/09 [QC#56153, MOD]
            // START 2016/03/09 K.Kojima [QC#5200,MOD]
            // setValue(tMsg.apWfRqstCmntTxt, "**Warning** " +
            // S21MessageFunc.clspGetMessage(NFBM0209W, new String[]
            // {"" }).substring(10));
            //String message = S21MessageFunc.clspGetMessage(NFBM0209W, new String[] {"" });
            //if (message != null && message.length() > 10) {
            //    message = message.substring(10);
            //}
            //setValue(tMsg.apWfRqstCmntTxt, "**Warning** " + message);
            String message = S21MessageFunc.clspGetMessage(NFBM0209W);
            if (message != null && message.length() > 10) {
                message = message.substring(10);
            }
            setValue(tMsg.apWfRqstCmntTxt, message);
            // END 2016/03/09 K.Kojima [QC#5200,MOD]
            // END   2020/04/09 [QC#56153, MOD]
            return true;
        }
        return false;
    }

    /**
     * setSupplierInfo
     * @param rs ResultSet
     * @param tMsg AP_LSE_BO_WF_RQSTTMsg
     * @param splyNm String
     * @return boolean
     * @throws SQLException SQLException
     */
    // START 2016/03/16 K.Kojima [QC#5146,MOD]
    // private void setSupplierInfo(ResultSet rs,
    // AP_LSE_BO_WF_RQSTTMsg tMsg) throws SQLException {
    private boolean setSupplierInfo(ResultSet rs, AP_LSE_BO_WF_RQSTTMsg tMsg, String splyNm) throws SQLException {
        // END 2016/03/16 K.Kojima [QC#5146,MOD]
        String splyTpCd = rs.getString(SPLY_TP_CD);
        if (SPLY_TP.CFS.equals(splyTpCd)) {
            Map<String, Object> supplierInfo = getSupplierInfoCFS();
            if (supplierInfo != null) {
                setValue(tMsg.prntVndPk, (BigDecimal) supplierInfo.get(PRNT_VND_PK));
                setValue(tMsg.prntVndCd, (String) supplierInfo.get(PRNT_VND_CD));
                setValue(tMsg.vndPk, (BigDecimal) supplierInfo.get(VND_PK));
                setValue(tMsg.vndCd, (String) supplierInfo.get(VND_CD));
            }
        } else if (SPLY_TP.CUSTOMER.equals(splyTpCd)) {
            // START 2016/03/16 K.Kojima [QC#5146,MOD]
            // String prntVndNm = rs.getString(SPLY_NM);
            String prntVndNm = splyNm;
            // END 2016/03/16 K.Kojima [QC#5146,MOD]
            String firstLineAddr = rs.getString(SPLY_FIRST_ADDR);
            String ctyAddr = rs.getString(SPLY_CTY_ADDR);
            String stCd = rs.getString(SPLY_ST_CD);
            String postCd = rs.getString(SPLY_POST_CD);
            Map<String, Object> supplierInfo = getSupplierInfo(prntVndNm, firstLineAddr, ctyAddr, stCd, postCd);
            if (supplierInfo != null) {
                setValue(tMsg.prntVndPk, (BigDecimal) supplierInfo.get(PRNT_VND_PK));
                setValue(tMsg.prntVndCd, (String) supplierInfo.get(PRNT_VND_CD));
                setValue(tMsg.vndPk, (BigDecimal) supplierInfo.get(VND_PK));
                setValue(tMsg.vndCd, (String) supplierInfo.get(VND_CD));
            } else {
                // START 2016/03/16 K.Kojima [QC#5146,MOD]
                // NMZC201001PMsg pMsg01 = createPMsg01(rs);
                // List<NMZC201002PMsg> pMsg02list = createPMsg02(rs);
                // QC#19412 MOD START
                Map<String, Object> prntVndMap = chkPrntVnd(splyNm);
                NMZC201001PMsg pMsg01 = createPMsg01(rs, splyNm, prntVndMap);
                List<NMZC201002PMsg> pMsg02list = createPMsg02(rs, splyNm, prntVndMap);
                // QC#19412 MOD END
                // END 2016/03/16 K.Kojima [QC#5146,MOD]
                List<NMZC201003PMsg> pMsg03list = createPMsg03(rs);
                new NMZC201001().execute(pMsg01, pMsg02list, pMsg03list, ONBATCH_TYPE.BATCH);
                // 2016/07/05 QC#10692 Mod Start
                if (isApiError(pMsg01, rs.getString(CPO_ORD_NUM), rs.getString(CPO_DTL_LINE_NUM))) {
                    return false;
                }
                // 2016/07/05 QC#10692 Mod End
                supplierInfo = getSupplierInfo(prntVndNm, firstLineAddr, ctyAddr, stCd, postCd);
                if (supplierInfo != null) {
                    setValue(tMsg.prntVndPk, (BigDecimal) supplierInfo.get(PRNT_VND_PK));
                    setValue(tMsg.prntVndCd, (String) supplierInfo.get(PRNT_VND_CD));
                    setValue(tMsg.vndPk, (BigDecimal) supplierInfo.get(VND_PK));
                    setValue(tMsg.vndCd, (String) supplierInfo.get(VND_CD));
                }
            }
        }
        // 2016/07/05 QC#10692 Add Start
        return true;
        // 2016/07/05 QC#10692 Add End
    }

    /**
     * getSupplierInfoCFS
     * @return Map<String, Object>
     */
    private Map<String, Object> getSupplierInfoCFS() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("coaAfflCd", this.cfsCoaAfflCd);
        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getSupplierInfoCFS", params, execParam());
        return result;
    }

    /**
     * getSupplierInfo
     * @param prntVndNm String
     * @param firstLineAddr String
     * @param ctyAddr String
     * @param stCd String
     * @param postCd String
     * @return Map<String, Object>
     */
    private Map<String, Object> getSupplierInfo(String prntVndNm, String firstLineAddr, String ctyAddr, String stCd, String postCd) {
        if (prntVndNm == null) {
            prntVndNm = "";
        }
        if (firstLineAddr == null) {
            firstLineAddr = "";
        }
        if (ctyAddr == null) {
            ctyAddr = "";
        }
        if (stCd == null) {
            stCd = "";
        }
        if (postCd == null) {
            postCd = "";
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("prntVndNm", prntVndNm);
        params.put("firstLineAddr", firstLineAddr);
        params.put("ctyAddr", ctyAddr);
        params.put("stCd", stCd);
        params.put("postCd", postCd);
        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getSupplierInfo", params, execParam());
        return result;
    }

    // START 2016/03/31 K.Kojima [QC#5531,DEL]
    // /**
    // * getApvlLimitData
    // * @param mdseCd String
    // * @return Map<String, Object>
    // */
    // private Map<String, Object> getApvlLimitData(String mdseCd) {
    // Map<String, String> queryParam = new HashMap<String, String>();
    // queryParam.put("glblCmpyCd", this.glblCmpyCd);
    // queryParam.put("wfBizAppId", this.leaseByotApvlWfId);
    // queryParam.put("condValTxt01", mdseCd);
    // Map<String, Object> result = (Map<String, Object>)
    // this.ssmBatchClient.queryObject("getApvlLimitData", queryParam,
    // execParam());
    // return result;
    // }
    // END 2016/03/31 K.Kojima [QC#5531,DEL]

    /**
     * createPMsg01
     * @param rs ResultSet
     * @param splyNm String
     * @param prntVndMap Map<String, Object>
     * @return NMZC201001PMsg
     * @throws SQLException SQLException
     */
    // START 2016/03/16 K.Kojima [QC#5146,MOD]
    // private NMZC201001PMsg createPMsg01(ResultSet rs) throws
    // SQLException {
    private NMZC201001PMsg createPMsg01(ResultSet rs, String splyNm, Map<String, Object> prntVndMap) throws SQLException {
        // END 2016/03/16 K.Kojima [QC#5146,MOD]
        NMZC201001PMsg pMsg01 = new NMZC201001PMsg();

        // QC#19412 ADD START
        if (prntVndMap != null) {
            setValue(pMsg01.glblCmpyCd, this.glblCmpyCd);
            setValue(pMsg01.xxModeCd, NMZC201001Constant.UPDATE_MODE);
            setValue(pMsg01.procDt, this.batProcDate);
            setValue(pMsg01.prntVndCd, (String) prntVndMap.get("PRNT_VND_CD"));
            setValue(pMsg01.prntVndNm, (String) prntVndMap.get("PRNT_VND_NM"));
            setValue(pMsg01.prntVndTpCd, (String) prntVndMap.get("PRNT_VND_TP_CD"));
            setValue(pMsg01.taxPayerId, (String) prntVndMap.get("TAX_PAYER_ID"));
            setValue(pMsg01.inacDt, (String) prntVndMap.get("INAC_DT"));
            setValue(pMsg01.taxPayerRgNum, (String) prntVndMap.get("TAX_PAYER_RG_NUM"));
            setValue(pMsg01.arcsSplyNum, (String) prntVndMap.get("ARCS_SPLY_NUM"));
            setValue(pMsg01.arcsSplyId, (BigDecimal) prntVndMap.get("ARCS_SPLY_ID"));
            setValue(pMsg01.indyTpCd, (String) prntVndMap.get("INDY_TP_CD"));
            setValue(pMsg01.mnrtyOwndTpCd, (String) prntVndMap.get("MNRTY_OWND_TP_CD"));
            setValue(pMsg01.splyOrgTpCd, (String) prntVndMap.get("SPLY_ORG_TP_CD"));
            setValue(pMsg01.splyOneTmFlg, (String) prntVndMap.get("SPLY_ONE_TM_FLG"));
            setValue(pMsg01.smBizFlg, (String) prntVndMap.get("SM_BIZ_FLG"));
            setValue(pMsg01.womenOwndFlg, (String) prntVndMap.get("WOMEN_OWND_FLG"));
            setValue(pMsg01.payAloneFlg, (String) prntVndMap.get("PAY_ALONE_FLG"));
            setValue(pMsg01.discTakeFlg, (String) prntVndMap.get("DISC_TAKE_FLG"));
            setValue(pMsg01.poPmtHldFlg, (String) prntVndMap.get("PO_PMT_HLD_FLG"));
            setValue(pMsg01.fdRptFlg, (String) prntVndMap.get("FD_RPT_FLG"));
            setValue(pMsg01.incTaxTpCd, (String) prntVndMap.get("INC_TAX_TP_CD"));
            setValue(pMsg01.stTaxFlg, (String) prntVndMap.get("ST_TAX_FLG"));
            setValue(pMsg01.splyRptNm, (String) prntVndMap.get("SPLY_RPT_NM"));
            setValue(pMsg01.coaAfflCd, (String) prntVndMap.get("COA_AFFL_CD"));
            setValue(pMsg01.splyHubZnCd, (String) prntVndMap.get("SPLY_HUB_ZN_CD"));
            setValue(pMsg01.dsAcctNum, (String) prntVndMap.get("DS_ACCT_NUM"));
            setValue(pMsg01.vndPmtTermCd, (String) prntVndMap.get("VND_PMT_TERM_CD"));
            setValue(pMsg01.vndPmtTermDescTxt, (String) prntVndMap.get("VND_PMT_TERM_DESC_TXT"));
            setValue(pMsg01.vndPmtMethCd, (String) prntVndMap.get("VND_PMT_METH_CD"));
            setValue(pMsg01.payGrpCd, (String) prntVndMap.get("PAY_GRP_CD"));
        } else {
            setValue(pMsg01.xxModeCd, NMZC201001Constant.CREATE_MODE);
            setValue(pMsg01.glblCmpyCd, this.glblCmpyCd);
            setValue(pMsg01.procDt, this.batProcDate);
            // START 2016/03/16 K.Kojima [QC#5146,MOD]
            // setValue(pMsg01.prntVndNm, rs.getString(SPLY_NM));
            setValue(pMsg01.prntVndNm, splyNm);
            // END 2016/03/16 K.Kojima [QC#5146,MOD]
            setValue(pMsg01.prntVndTpCd, PRNT_VND_TP.LEASE_BUYOUT);
            setValue(pMsg01.splyOneTmFlg, ZYPConstant.FLG_OFF_N);
            setValue(pMsg01.smBizFlg, ZYPConstant.FLG_OFF_N);
            setValue(pMsg01.womenOwndFlg, ZYPConstant.FLG_OFF_N);
            setValue(pMsg01.payAloneFlg, ZYPConstant.FLG_ON_Y);
            setValue(pMsg01.discTakeFlg, ZYPConstant.FLG_OFF_N);
            setValue(pMsg01.poPmtHldFlg, ZYPConstant.FLG_OFF_N);
            setValue(pMsg01.dsAcctNum, rs.getString(BILL_TO_CUST_ACCT_CD));
            setValue(pMsg01.vndPmtTermDescTxt, "IMMEDIATE");
            setValue(pMsg01.vndPmtMethCd, VND_PMT_METH.CHECK);
            setValue(pMsg01.payGrpCd, PAY_GRP.ACMEC);
        }
        // QC#19412 ADD END

        return pMsg01;
    }

    /**
     * createPMsg02
     * @param rs ResultSet
     * @param splyNm String
     * @param prntVndMap Map<String, Object>
     * @return List<NMZC201002PMsg>
     * @throws SQLException SQLException
     */
    // START 2016/03/16 K.Kojima [QC#5146,MOD]
    // private List<NMZC201002PMsg> createPMsg02(ResultSet rs) throws
    // SQLException {
    private List<NMZC201002PMsg> createPMsg02(ResultSet rs, String splyNm, Map<String, Object> prntVndMap) throws SQLException {
        // END 2016/03/16 K.Kojima [QC#5146,MOD]
        List<NMZC201002PMsg> pMsg02list = new ArrayList<NMZC201002PMsg>();

        NMZC201002PMsg pMsg02 = new NMZC201002PMsg();
        setValue(pMsg02.xxModeCd, NMZC201001Constant.CREATE_MODE);
        setValue(pMsg02.firstLineAddr, rs.getString(SPLY_FIRST_ADDR));
        setValue(pMsg02.ctyAddr, rs.getString(SPLY_CTY_ADDR));
        setValue(pMsg02.stCd, rs.getString(SPLY_ST_CD));
        setValue(pMsg02.postCd, rs.getString(SPLY_POST_CD));
        setValue(pMsg02.ctryCd, CTRY.UNITED_STATES_OF_AMERICA);
        // START 2016/03/09 K.Kojima [QC#5200,MOD]
        // setValue(pMsg02.locNm, ("ACMEC" +
        // rs.getString(SPLY_NM)).substring(0, MAX_SIZE_LOC_NM));
        // START 2016/03/16 K.Kojima [QC#5146,MOD]
        // String locNm = "ACMEC" + rs.getString(SPLY_NM);
        String locNm = "ACMEC" + splyNm;
        // END 2016/03/16 K.Kojima [QC#5146,MOD]
        if (locNm != null && locNm.length() > MAX_SIZE_LOC_NM) {
            locNm = locNm.substring(0, MAX_SIZE_LOC_NM);
        }

        // QC#19412 ADD START
        locNm = getLocNm(prntVndMap, locNm);
        // QC#19412 ADD END

        setValue(pMsg02.locNm, locNm);
        // END 2016/03/09 K.Kojima [QC#5200,MOD]

        setValue(pMsg02.splyPmtFlg, ZYPConstant.FLG_ON_Y);
        setValue(pMsg02.splyPoFlg, ZYPConstant.FLG_ON_Y);

        // START 2016/03/15 K.Kojima [QC#5115,MOD]
        // String[] splyCoaValue = SPLY_COA_VALUE.split(",");
        // setValue(pMsg02.splyCoaCmpyCd,
        // splyCoaValue[SPLIT_CMPY_CD_NUMBER]);
        // setValue(pMsg02.splyCoaBrCd,
        // splyCoaValue[SPLIT_BR_CD_NUMBER]);
        // setValue(pMsg02.splyCoaCcCd,
        // splyCoaValue[SPLIT_CC_CD_NUMBER]);
        // setValue(pMsg02.splyCoaAcctCd,
        // splyCoaValue[SPLIT_ACCT_CD_NUMBER]);
        // setValue(pMsg02.splyCoaProdCd,
        // splyCoaValue[SPLIT_PROD_CD_NUMBER]);
        // setValue(pMsg02.splyCoaChCd,
        // splyCoaValue[SPLIT_CH_CD_NUMBER]);
        // setValue(pMsg02.splyCoaAfflCd,
        // splyCoaValue[SPLIT_AFFL_CD_NUMBER]);
        // setValue(pMsg02.splyCoaProjCd,
        // splyCoaValue[SPLIT_PROJ_CD_NUMBER]);
        // setValue(pMsg02.splyCoaExtnCd,
        // splyCoaValue[SPLIT_EXTN_CD_NUMBER]);
        // START 2016/09/16 K.Kojima [QC#14412,MOD]
        // setValue(pMsg02.splyCoaCmpyCd,
        // SPLY_COA_VALUE[SPLIT_CMPY_CD_NUMBER]);
        // setValue(pMsg02.splyCoaBrCd,
        // SPLY_COA_VALUE[SPLIT_BR_CD_NUMBER]);
        // setValue(pMsg02.splyCoaCcCd,
        // SPLY_COA_VALUE[SPLIT_CC_CD_NUMBER]);
        // setValue(pMsg02.splyCoaAcctCd,
        // SPLY_COA_VALUE[SPLIT_ACCT_CD_NUMBER]);
        // setValue(pMsg02.splyCoaProdCd,
        // SPLY_COA_VALUE[SPLIT_PROD_CD_NUMBER]);
        // setValue(pMsg02.splyCoaChCd,
        // SPLY_COA_VALUE[SPLIT_CH_CD_NUMBER]);
        // setValue(pMsg02.splyCoaAfflCd,
        // SPLY_COA_VALUE[SPLIT_AFFL_CD_NUMBER]);
        // setValue(pMsg02.splyCoaProjCd,
        // SPLY_COA_VALUE[SPLIT_PROJ_CD_NUMBER]);
        // setValue(pMsg02.splyCoaExtnCd,
        // SPLY_COA_VALUE[SPLIT_EXTN_CD_NUMBER]);
        setValue(pMsg02.splyCoaCmpyCd, getCoaSplitValue(splyCoaValue, SPLIT_CMPY_CD_NUMBER));
        setValue(pMsg02.splyCoaBrCd, getCoaSplitValue(splyCoaValue, SPLIT_BR_CD_NUMBER));
        setValue(pMsg02.splyCoaCcCd, getCoaSplitValue(splyCoaValue, SPLIT_CC_CD_NUMBER));
        setValue(pMsg02.splyCoaAcctCd, getCoaSplitValue(splyCoaValue, SPLIT_ACCT_CD_NUMBER));
        setValue(pMsg02.splyCoaProdCd, getCoaSplitValue(splyCoaValue, SPLIT_PROD_CD_NUMBER));
        setValue(pMsg02.splyCoaChCd, getCoaSplitValue(splyCoaValue, SPLIT_CH_CD_NUMBER));
        setValue(pMsg02.splyCoaAfflCd, getCoaSplitValue(splyCoaValue, SPLIT_AFFL_CD_NUMBER));
        setValue(pMsg02.splyCoaProjCd, getCoaSplitValue(splyCoaValue, SPLIT_PROJ_CD_NUMBER));
        setValue(pMsg02.splyCoaExtnCd, getCoaSplitValue(splyCoaValue, SPLIT_EXTN_CD_NUMBER));
        // END 2016/09/16 K.Kojima [QC#14412,MOD]
        // END 2016/03/15 K.Kojima [QC#5115,MOD]

        setValue(pMsg02.vndPmtTermDescTxt, "IMMEDIATE");
        setValue(pMsg02.vndPmtMethCd, VND_PMT_METH.CHECK);
        setValue(pMsg02.payGrpCd, PAY_GRP.ACMEC);

        // START 2016/03/15 K.Kojima [QC#5115,MOD]
        // String[] prePmtCoaValue = PRE_PMT_COA_VALUE.split(",");
        // setValue(pMsg02.prePmtCoaCmpyCd,
        // prePmtCoaValue[SPLIT_CMPY_CD_NUMBER]);
        // setValue(pMsg02.prePmtCoaBrCd,
        // prePmtCoaValue[SPLIT_BR_CD_NUMBER]);
        // setValue(pMsg02.prePmtCoaCcCd,
        // prePmtCoaValue[SPLIT_CC_CD_NUMBER]);
        // setValue(pMsg02.prePmtCoaAcctCd,
        // prePmtCoaValue[SPLIT_ACCT_CD_NUMBER]);
        // setValue(pMsg02.prePmtCoaProdCd,
        // prePmtCoaValue[SPLIT_PROD_CD_NUMBER]);
        // setValue(pMsg02.prePmtCoaChCd,
        // prePmtCoaValue[SPLIT_CH_CD_NUMBER]);
        // setValue(pMsg02.prePmtCoaAfflCd,
        // prePmtCoaValue[SPLIT_AFFL_CD_NUMBER]);
        // setValue(pMsg02.prePmtCoaProjCd,
        // prePmtCoaValue[SPLIT_PROJ_CD_NUMBER]);
        // setValue(pMsg02.prePmtCoaExtnCd,
        // prePmtCoaValue[SPLIT_EXTN_CD_NUMBER]);
        // START 2016/09/16 K.Kojima [QC#14412,MOD]
        // setValue(pMsg02.prePmtCoaCmpyCd,
        // PRE_PMT_COA_VALUE[SPLIT_CMPY_CD_NUMBER]);
        // setValue(pMsg02.prePmtCoaBrCd,
        // PRE_PMT_COA_VALUE[SPLIT_BR_CD_NUMBER]);
        // setValue(pMsg02.prePmtCoaCcCd,
        // PRE_PMT_COA_VALUE[SPLIT_CC_CD_NUMBER]);
        // setValue(pMsg02.prePmtCoaAcctCd,
        // PRE_PMT_COA_VALUE[SPLIT_ACCT_CD_NUMBER]);
        // setValue(pMsg02.prePmtCoaProdCd,
        // PRE_PMT_COA_VALUE[SPLIT_PROD_CD_NUMBER]);
        // setValue(pMsg02.prePmtCoaChCd,
        // PRE_PMT_COA_VALUE[SPLIT_CH_CD_NUMBER]);
        // setValue(pMsg02.prePmtCoaAfflCd,
        // PRE_PMT_COA_VALUE[SPLIT_AFFL_CD_NUMBER]);
        // setValue(pMsg02.prePmtCoaProjCd,
        // PRE_PMT_COA_VALUE[SPLIT_PROJ_CD_NUMBER]);
        // setValue(pMsg02.prePmtCoaExtnCd,
        // PRE_PMT_COA_VALUE[SPLIT_EXTN_CD_NUMBER]);
        setValue(pMsg02.prePmtCoaCmpyCd, getCoaSplitValue(prePmtCoaValue, SPLIT_CMPY_CD_NUMBER));
        setValue(pMsg02.prePmtCoaBrCd, getCoaSplitValue(prePmtCoaValue, SPLIT_BR_CD_NUMBER));
        setValue(pMsg02.prePmtCoaCcCd, getCoaSplitValue(prePmtCoaValue, SPLIT_CC_CD_NUMBER));
        setValue(pMsg02.prePmtCoaAcctCd, getCoaSplitValue(prePmtCoaValue, SPLIT_ACCT_CD_NUMBER));
        setValue(pMsg02.prePmtCoaProdCd, getCoaSplitValue(prePmtCoaValue, SPLIT_PROD_CD_NUMBER));
        setValue(pMsg02.prePmtCoaChCd, getCoaSplitValue(prePmtCoaValue, SPLIT_CH_CD_NUMBER));
        setValue(pMsg02.prePmtCoaAfflCd, getCoaSplitValue(prePmtCoaValue, SPLIT_AFFL_CD_NUMBER));
        setValue(pMsg02.prePmtCoaProjCd, getCoaSplitValue(prePmtCoaValue, SPLIT_PROJ_CD_NUMBER));
        setValue(pMsg02.prePmtCoaExtnCd, getCoaSplitValue(prePmtCoaValue, SPLIT_EXTN_CD_NUMBER));
        // END 2016/09/16 K.Kojima [QC#14412,MOD]
        // END 2016/03/15 K.Kojima [QC#5115,MOD]
        // START 2021/03/25 R.Azucena [QC#58394, ADD]
        setValue(pMsg02.effThruDt, NMZC201001Constant.DEF_THRU_DT);
        // END 2021/03/25 R.Azucena [QC#58394, ADD]

        pMsg02list.add(pMsg02);

        return pMsg02list;
    }

    /**
     * createPMsg03
     * @param rs ResultSet
     * @return List<NMZC201003PMsg>
     */
    private List<NMZC201003PMsg> createPMsg03(ResultSet rs) {
        List<NMZC201003PMsg> pMsg03list = new ArrayList<NMZC201003PMsg>();

        return pMsg03list;
    }

    /**
     * isApiError
     * @param pMsg NMZC201001PMsg
     * @param cpoOrdNum String
     * @param cpoDtlLineNum String
     * @return boolean
     */
    // 2016/07/05 QC#10692 Mod Start
    private boolean isApiError(NMZC201001PMsg pMsg, String cpoOrdNum, String cpoDtlLineNum) {
        if (pMsg.xxMsgIdList.getValidCount() != 0) {
            //setApiErrorInfo(cpoOrdNum, cpoDtlLineNum, PROGRAM_ID, pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
            S21InfoLogOutput.printlnv(NFBM0208E, PROGRAM_ID, pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), "cpoOrdNum=" + cpoOrdNum + ",cpoDtlLineNum=" + cpoDtlLineNum);
            return true;
        }
        return false;
    }
    // 2016/07/05 QC#10692 Mod End

    // 2016/07/05 QC#10692 Del Start
//    /**
//     * setApiErrorInfo
//     * @param cpoOrdNum String
//     * @param cpoDtlLineNum String
//     * @param apiBizId String
//     * @param xxMsgId String
//     */
//    private void setApiErrorInfo(String cpoOrdNum, String cpoDtlLineNum, String apiBizId, String xxMsgId) {
//        String[] params = new String[] {apiBizId, xxMsgId, "cpoOrdNum=" + cpoOrdNum + ",cpoDtlLineNum=" + cpoDtlLineNum };
//        throw new S21AbendException(NFBM0208E, params);
//    }
    // 2016/07/05 QC#10692 Del End

    /**
     * startWorkflow
     * @param tMsg AP_LSE_BO_WF_RQSTTMsg
     * @param dplyLineNum String
     */
    // START 2016/12/01 Y.Tsuchimoto [QC#13088,MOD]
    //private void startWorkflow(AP_LSE_BO_WF_RQSTTMsg tMsg) {
    private void startWorkflow(AP_LSE_BO_WF_RQSTTMsg tMsg, String dplyLineNum) {
    // END   2016/12/01 Y.Tsuchimoto [QC#13088,MOD]
        BigDecimal wfPk = tMsg.wfPk.getValue();

        S21NwfUtilTokenObj tokenBiz = new S21NwfUtilTokenObj();

        tokenBiz.setBizId("NFBB126001");
        tokenBiz.setGlblCmpyCd(this.glblCmpyCd);
        tokenBiz.setCondStr1(tMsg.mdseCd.getValue());
        tokenBiz.setCondStr2(tMsg.cpoOrdNum.getValue());
        // START 2016/03/16 K.Kojima [QC#5297,MOD]
        // tokenBiz.setCondStr3(tMsg.cpoOrdTpCd.getValue());
        // tokenBiz.setCondStr4(tMsg.cpoDtlLineNum.getValue());
        // tokenBiz.setCondStr5(tMsg.cpoOrdTs.getValue());
        // tokenBiz.setCondStr6(tMsg.dsOrdLineCatgCd.getValue());
        // tokenBiz.setCondStr7(tMsg.apvlTpTxt.getValue());
        tokenBiz.setCondStr3(tMsg.cpoDtlLineNum.getValue());
        tokenBiz.setCondStr4(tMsg.invNum.getValue());
        tokenBiz.setCondStr5(tMsg.invBolNum.getValue());
        tokenBiz.setCondStr6(tMsg.invLineNum.getValue());
        tokenBiz.setCondStr7(tMsg.invLineSubNum.getValue());
        tokenBiz.setCondStr8(tMsg.invLineSubTrxNum.getValue());
        // END 2016/03/16 K.Kojima [QC#5297,MOD]
        // START 2016/03/31 K.Kojima [QC#5531,ADD]
        tokenBiz.setCondStr9(wfPk.toString());
        // END 2016/03/31 K.Kojima [QC#5531,ADD]
        tokenBiz.setCondNum1(tMsg.cpoDtlFuncNetAmt.getValue());
        tokenBiz.setAttribute1Lbl("Order#");
        tokenBiz.setAttribute1(tMsg.cpoOrdNum.getValue());
        tokenBiz.setAttribute2Lbl("Line#");
        // START 2016/12/01 Y.Tsuchimoto [QC#13088,MOD]
        //tokenBiz.setAttribute2(tMsg.cpoDtlLineNum.getValue());
        tokenBiz.setAttribute2(dplyLineNum);
        // END   2016/12/01 Y.Tsuchimoto [QC#13088,MOD]
        tokenBiz.setAttribute3Lbl("Amount");
        tokenBiz.setAttribute3(formatAmount(tMsg.cpoDtlFuncNetAmt.getValue()));
        tokenBiz.setAttribute4Lbl("Comment");
        // START 2016/03/16 K.Kojima [QC#5297,MOD]
        // tokenBiz.setAttribute4(tMsg.apWfRqstCmntTxt.getValue());
        if (ZYPCommonFunc.hasValue(tMsg.apWfRqstCmntTxt)) {
            tokenBiz.setAttribute4("**Warning**");
            tokenBiz.setComment(tMsg.apWfRqstCmntTxt.getValue());
        }
        // END 2016/03/16 K.Kojima [QC#5297,MOD]
        // START 2022/08/26 S.Naya [QC#60114,ADD]
        tokenBiz.setAttribute5Lbl("Supplier");
        tokenBiz.setAttribute5(tMsg.splyNm.getValue());
        // END   2022/08/26 S.Naya [QC#60114,ADD]

        tokenBiz.setBizScreenId("NFBL2100");
        tokenBiz.setBizScreenParams(tMsg.cpoOrdNum.getValue());

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;
        S21NwfProcess process = null;

        try {
            context = factory.getContex();
            context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());
            process = context.newProcess(this.leaseByotApvlWfId);
            process.setDocumentId(wfPk.toString());
        } catch (S21NwfSystemException e) {
            throw new S21AbendException(NFBM0028E);
        }
        S21NwfToken token = process.getToken();
        token.setTokenObj(tokenBiz);

        try {
            token.start();
        } catch (S21NwfBizException e) {
            throw new S21AbendException(NFBM0028E);
        } catch (S21NwfException e) {
            throw new S21AbendException(NFBM0028E);
        }
    }

    private String formatAmount(BigDecimal amt) {
        if (!ZYPCommonFunc.hasValue(amt)) {
            return null;
        }
        NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);
        return df.format(amt);
    }

    // START 2016/03/31 K.Kojima [QC#5531,ADD]
    /**
     * getApvlLimitData
     * @param leaseByotApvlWfId String
     * @param documentId String
     * @return returnMap Map<String, Object>
     * @throws S21NwfException S21NwfException
     */
    private Map<String, Object> getApvlLimitData(String leaseByotApvlWfId, String documentId) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
            S21NwfContextFactory factory = new S21NwfUtilContextFactory();
            S21NwfContext context = factory.getContex();
            List<S21NwfProcess> procs = context.getProcessForBiz(leaseByotApvlWfId, documentId);
            for (S21NwfProcess proc : procs) {
                S21NwfUtilBizProcess p = (S21NwfUtilBizProcess) proc;
                List<S21NwfUtilBizWorkItem> tasks = p.getTasks();
                for (S21NwfUtilBizWorkItem wi : tasks) {
                    if (wi.isApprovable()) {
                        returnMap.put(COND_VAL_NUM_01, wi.getCondition().getCondNum1());
                        returnMap.put(ATTRB_COND_VAL_TXT_01, wi.getGroups().get(0));
                        break;
                    }
                }
            }
        } catch (S21NwfSystemException e) {
            throw new S21AbendException(NFBM0028E);
        }
        return returnMap;
    }

    // END 2016/03/31 K.Kojima [QC#5531,ADD]

    // START 2016/09/16 K.Kojima [QC#14412,ADD]
    /**
     * getCoaSplitValue
     * @param str String
     * @param splitNumber int
     * @return String
     */
    private String getCoaSplitValue(String str, int splitNumber) {
        if (str == null) {
            return null;
        }
        String[] strs = str.split(",");
        if (strs.length - 1 < splitNumber) {
            return null;
        }
        return strs[splitNumber];
    }
    // END 2016/09/16 K.Kojima [QC#14412,ADD]

    // QC#19412 ADD START
    /**
     * chkPrntVnd
     * @param splyNm String
     * @return Map<String, Object>
     */
    private Map<String, Object> chkPrntVnd(String splyNm) {
        if (splyNm == null) {
            return null;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("prntVndNm", splyNm);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("chkPrntVnd", params);
    }

    /**
     * getLocNm
     * @param prntVndMap Map<String, Object>
     * @param locNm String
     * @return String
     */
    private String getLocNm(Map<String, Object> prntVndMap, String locNm) {
        if (prntVndMap == null || locNm == null) {
            return locNm;
        }

        // same Suplyer Site Name exist check
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("prntVndCd", (String) prntVndMap.get("PRNT_VND_CD"));
        params.put("locNm", locNm);
        String locNmWk = (String) this.ssmBatchClient.queryObject("getLocNm", params);

        // if not same locNm exist. then lokNm is ok
        if (!ZYPCommonFunc.hasValue(locNmWk)) {
            return locNm;
        }

        // if same name exist. then add cycle number for name
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("prntVndCd", (String) prntVndMap.get("PRNT_VND_CD"));
        params.put("locNm", REG_PTN_LOC_NM);
        locNmWk = (String) this.ssmBatchClient.queryObject("getMaxLocNum", params);

        if (!ZYPCommonFunc.hasValue(locNmWk)) {
            return DEFAULT_LOC_NM;
        }

        return LOC_NM_FIRST_NM + locNmWk;

    }
    // QC#19412 ADD START

    // START 2019/08/03 S.Takami [QC#52191,ADD]
    private String getMaxLengthString(String orig, int maxLength) {

        if (orig.length() > maxLength) {
            return orig.substring(0, maxLength);
        } else {
            return orig;
        }
    }
    // End 2019/08/03 S.Takami [QC#52191,ADD]
}
