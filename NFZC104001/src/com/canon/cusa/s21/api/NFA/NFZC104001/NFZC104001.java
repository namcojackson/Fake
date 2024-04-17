/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NFA.NFZC104001;

import static com.canon.cusa.s21.api.NFA.NFZC104001.constant.NFZC104001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.GLBL_CMPYTMsg;
import business.db.JRNL_ENTRYTMsg;
import business.db.JRNL_ENTRYTMsgArray;
import business.db.SYS_SRCTMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFZC104001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GLBL_CMPY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;

/**
 * <pre>
 * Journal Entry Creation API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/08   Fujitsu         S.Fujita        Create          N/A.
 * 2016/06/28   Hitachi         T.Mizuki        Update          N/A.
 * 2016/08/05   Fujitsu         C.Tanaka        Update          QC#13077
 * 2016/09/01   Hitachi         J.Kim           Update          QC#12851
 * </pre>
 */
public class NFZC104001 extends S21ApiCommonBase {
    /** ssm LowLevel Client */
    private S21SsmLowLevelCodingClient ssmLowClient;
    /**  Journal Entry Common Module */
    private NFACommonJrnlEntry commonJrnlEntry = new NFACommonJrnlEntry();

    /** Constructor */
    public NFZC104001() {
        super();
        ssmLowClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    /**
     * Journal Entry Creation API.
     * @param pMsgList List<NFZC104001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NFZC104001PMsg> pMsgList,  final ONBATCH_TYPE onBatchType) {
        for (NFZC104001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * Journal Entry Creation API.
     * @param pMsg NFZC104001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NFZC104001PMsg pMsg,  final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        try {
            doProcess(pMsg, onBatchType, msgMap);
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            // Transfer of message
            msgMap.flush();
        }
    }

    /**
     * doProcess
     * @param pMsg NFZC104001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @param msgMap S21ApiMessageMap
     */
    private void doProcess(NFZC104001PMsg pMsg, final ONBATCH_TYPE onBatchType, S21ApiMessageMap msgMap) throws SQLException {

        PreparedStatement stmtManJrnlEntry = null;
        ResultSet rsltSetManJrnlEntry = null;

            // Parameter Check
            if (!checkParam(msgMap)) {
                return;
            }

            NFZC104001Bean bean = new NFZC104001Bean();

            // Get SYS_SRC_NM
            SYS_SRCTMsg sysSrcTMsg = (SYS_SRCTMsg) ZYPCodeDataUtil.findByCode(SYS_SRC.class, pMsg.glblCmpyCd.getValue(), NFA);
            if (sysSrcTMsg == null) {
                msgMap.addXxMsgId(NFZM0024E);
                return;
            }
            bean.setSysSrcNm(sysSrcTMsg.sysSrcNm.getValue());

            // Get Currency Code
            GLBL_CMPYTMsg glblCmpyTMsg = (GLBL_CMPYTMsg) ZYPCodeDataUtil.findByCode(GLBL_CMPY.class, pMsg.glblCmpyCd.getValue(), pMsg.glblCmpyCd.getValue());
            if (glblCmpyTMsg == null) {
                msgMap.addXxMsgId(NFZM0025E);
                return;
            }
            bean.setCcyCd(glblCmpyTMsg.stdCcyCd.getValue());

            // mod start 2016/06/27 CSA QC#10536
            JRNL_ENTRYTMsg tMsg = new JRNL_ENTRYTMsg();
            tMsg.setSQLID("001");
            tMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
            tMsg.setConditionValue("manJrnlEntryHdrPk01", pMsg.manJrnlEntryHdrPk.getValue());
            JRNL_ENTRYTMsgArray tMsgArray = (JRNL_ENTRYTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

            // Exist Check JRNL_ENTRY
            for (int i = 0; i < tMsgArray.length(); i++) {
                if (ZYPConstant.FLG_OFF_N.equals(tMsgArray.no(i).glSendCpltFlg.getValue())) {
                    S21ApiTBLAccessor.remove(tMsgArray.no(i));
                } else {
                    msgMap.addXxMsgId(NFZM0026E);
                    return;
                }
            }
            // mod end 2016/06/27 CSA QC#10536

            // START 2016/09/01 J.Kim [QC#12851,ADD]
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(INSERT_MAX_COUNT);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            ssmParam.put("manJrnlEntryHdrPk", pMsg.manJrnlEntryHdrPk.getValue());
            // END 2016/09/01 J.Kim [QC#12851,ADD]
        try {
            // Get Target Manual Journal Entry
            // START 2016/09/01 J.Kim [QC#12851,MOD]
            // stmtManJrnlEntry = getTrgtManJrnlEntry(pMsg);
            stmtManJrnlEntry = this.ssmLowClient.createPreparedStatement("getTrgtManJrnlEntry", ssmParam, execParam);
            // END 2016/09/01 J.Kim [QC#12851,MOD]
            rsltSetManJrnlEntry = stmtManJrnlEntry.executeQuery();

            // START 2016/09/01 J.Kim [QC#12851,MOD]
            List<JRNL_ENTRYTMsg> jrnlEntryTMsgList = new ArrayList<JRNL_ENTRYTMsg>();
            // END 2016/09/01 J.Kim [QC#12851,MOD]
            while (rsltSetManJrnlEntry.next()) {

                // START 2016/09/01 J.Kim [QC#12851,MOD]
                // Create Journal Entry
                //if (!createJrnlEntry(pMsg, bean, rsltSetManJrnlEntry)) {
                //    msgMap.addXxMsgId(NFZM0013E);
                //    return;
                //}
                jrnlEntryTMsgList.add(createJrnlEntry(pMsg, bean, rsltSetManJrnlEntry));
                if (INSERT_MAX_COUNT == jrnlEntryTMsgList.size()) {
                    if (!insert(jrnlEntryTMsgList)) {
                        msgMap.addXxMsgId(NFZM0013E);
                        return;
                    }
                    jrnlEntryTMsgList.clear();
                }
                // END 2016/09/01 J.Kim [QC#12851,MOD]
            }

            // START 2016/09/01 J.Kim [QC#12851,ADD]
            if (jrnlEntryTMsgList.size() > 0) {
                if (!insert(jrnlEntryTMsgList)) {
                    msgMap.addXxMsgId(NFZM0013E);
                    return;
                }
                jrnlEntryTMsgList.clear();
            }
            // END 2016/09/01 J.Kim [QC#12851,ADD]
        } catch (SQLException e) {
            throw e;

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtManJrnlEntry, rsltSetManJrnlEntry);
        }
    }

    /**
     * checkParam
     * @param msgMap S21ApiMessageMap
     */
    private boolean checkParam(S21ApiMessageMap msgMap) {

        NFZC104001PMsg param = (NFZC104001PMsg) msgMap.getPmsg();

        boolean res = true;
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NFZM0021E);
            res = false;
        }
        if (!ZYPCommonFunc.hasValue(param.manJrnlEntryHdrPk)) {
            msgMap.addXxMsgId(NFZM0023E);
            res = false;
        }
        if (!ZYPCommonFunc.hasValue(param.procDt)) {
            msgMap.addXxMsgId(NFZM0022E);
            res = false;
        }
        return res;
    }

    // START 2016/09/01 J.Kim [QC#12851,DEL]
    ///**
    //  * getTrgtManJrnlEntry
    //  * @param pMsg NFZC104001PMsg
    //  */
    // private PreparedStatement getTrgtManJrnlEntry(NFZC104001PMsg pMsg) throws SQLException {
    //     final Map<String, Object> ssmParam = new HashMap<String, Object>();
    //     ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
    //     ssmParam.put("manJrnlEntryHdrPk", pMsg.manJrnlEntryHdrPk.getValue());
    //     return ssmLowClient.createPreparedStatement("getTrgtManJrnlEntry", ssmParam);
    // }
    // END 2016/09/01 J.Kim [QC#12851,DEL]

    /**
     * createJrnlEntry
     * @param pMsg NFZC104001PMsg
     * @param bean NFZC104001Bean
     * @param rsltSetManJrnlEntry ResultSet
     */
    private JRNL_ENTRYTMsg createJrnlEntry(NFZC104001PMsg pMsg, NFZC104001Bean bean, ResultSet rsltSetManJrnlEntry) throws SQLException {

        JRNL_ENTRYTMsg tMsg = new JRNL_ENTRYTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.jrnlEntryPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.JRNL_ENTRY_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.ajeIntfcTpCd, AJE_INTFC_TP_CD_MAN);
        ZYPEZDItemValueSetter.setValue(tMsg.ajeIntfcPk, rsltSetManJrnlEntry.getBigDecimal("MAN_JRNL_ENTRY_DTL_PK"));
        ZYPEZDItemValueSetter.setValue(tMsg.procDt, pMsg.procDt);
        ZYPEZDItemValueSetter.setValue(tMsg.glDt, rsltSetManJrnlEntry.getString("GL_DT"));
        ZYPEZDItemValueSetter.setValue(tMsg.glSendCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.ajeId, AJE_ID_MAN);
        ZYPEZDItemValueSetter.setValue(tMsg.sysSrcCd, NFA);
        ZYPEZDItemValueSetter.setValue(tMsg.sysSrcNm, bean.getSysSrcNm());
        ZYPEZDItemValueSetter.setValue(tMsg.trxCd, TRX_CD_MAN);
        ZYPEZDItemValueSetter.setValue(tMsg.trxNm, TRX_NM_MAN);
        ZYPEZDItemValueSetter.setValue(tMsg.trxRsnCd, TRX_RSN_CD_MAN);
        ZYPEZDItemValueSetter.setValue(tMsg.trxRsnNm, TRX_RSN_NM_MAN);
        ZYPEZDItemValueSetter.setValue(tMsg.jrnlSrcCd, NFA);
        ZYPEZDItemValueSetter.setValue(tMsg.jrnlSrcNm, bean.getSysSrcNm());
        ZYPEZDItemValueSetter.setValue(tMsg.jrnlCatgCd, rsltSetManJrnlEntry.getString("JRNL_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.jrnlCatgNm, rsltSetManJrnlEntry.getString("JRNL_CATG_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.ajeLineIdxNum, IDX_NUM);

        if (DR_CR_TP_DEBIT.equals(rsltSetManJrnlEntry.getString("DR_CR_TP_CD"))) {
            ZYPEZDItemValueSetter.setValue(tMsg.drAjeLineIdxDescTxt, rsltSetManJrnlEntry.getString("COA_ACCT_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(tMsg.drCoaCmpyCd, rsltSetManJrnlEntry.getString("COA_CMPY_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.drCoaBrCd, rsltSetManJrnlEntry.getString("COA_BR_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.drCoaCcCd, rsltSetManJrnlEntry.getString("COA_CC_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.drCoaAcctCd, rsltSetManJrnlEntry.getString("COA_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.drCoaProdCd, rsltSetManJrnlEntry.getString("COA_PROD_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.drCoaChCd, rsltSetManJrnlEntry.getString("COA_CH_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.drCoaAfflCd, rsltSetManJrnlEntry.getString("COA_AFFL_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.drCoaProjCd, rsltSetManJrnlEntry.getString("COA_PROJ_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.drCoaExtnCd, rsltSetManJrnlEntry.getString("COA_EXTN_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.jrnlDealDrAmt, rsltSetManJrnlEntry.getBigDecimal("JRNL_DEAL_AMT"));
            ZYPEZDItemValueSetter.setValue(tMsg.jrnlDealCrAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tMsg.jrnlFuncDrAmt, rsltSetManJrnlEntry.getBigDecimal("JRNL_FUNC_AMT"));
            ZYPEZDItemValueSetter.setValue(tMsg.jrnlFuncCrAmt, BigDecimal.ZERO);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.crAjeLineIdxDescTxt, rsltSetManJrnlEntry.getString("COA_ACCT_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(tMsg.crCoaCmpyCd, rsltSetManJrnlEntry.getString("COA_CMPY_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.crCoaBrCd, rsltSetManJrnlEntry.getString("COA_BR_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.crCoaCcCd, rsltSetManJrnlEntry.getString("COA_CC_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.crCoaAcctCd, rsltSetManJrnlEntry.getString("COA_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.crCoaProdCd, rsltSetManJrnlEntry.getString("COA_PROD_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.crCoaChCd, rsltSetManJrnlEntry.getString("COA_CH_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.crCoaAfflCd, rsltSetManJrnlEntry.getString("COA_AFFL_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.crCoaProjCd, rsltSetManJrnlEntry.getString("COA_PROJ_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.crCoaExtnCd, rsltSetManJrnlEntry.getString("COA_EXTN_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.jrnlDealDrAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tMsg.jrnlDealCrAmt, rsltSetManJrnlEntry.getBigDecimal("JRNL_DEAL_AMT"));
            ZYPEZDItemValueSetter.setValue(tMsg.jrnlFuncDrAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tMsg.jrnlFuncCrAmt, rsltSetManJrnlEntry.getBigDecimal("JRNL_FUNC_AMT"));
        }

        //QC#13077 ADD Start
        ZYPEZDItemValueSetter.setValue(tMsg.jrnlEntryScdRefTxt, rsltSetManJrnlEntry.getString("MAN_JRNL_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.jrnlEntryScdAttrbNm, JRNL_ENTRY_SCD_ATTRB_NM);
        //QC#13077 ADD End
        ZYPEZDItemValueSetter.setValue(tMsg.dealCcyCd, rsltSetManJrnlEntry.getString("DEAL_CCY_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.funcCcyCd, bean.getCcyCd());
        ZYPEZDItemValueSetter.setValue(tMsg.exchRate, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tMsg.ajeIntfcCmntTxt, rsltSetManJrnlEntry.getString("MAN_JRNL_LINE_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, rsltSetManJrnlEntry.getString("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.serNum, rsltSetManJrnlEntry.getString("SER_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.jrnlEntryFirstRefTxt, rsltSetManJrnlEntry.getString("JRNL_ENTRY_SRC_VAL_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.jrnlEntryFirstAttrbNm, rsltSetManJrnlEntry.getString("JRNL_ENTRY_SRC_TP_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.jrnlEntryRefTxt, commonJrnlEntry.getJrnlEntryRefTxt(tMsg));
        ZYPEZDItemValueSetter.setValue(tMsg.manJrnlEntryHdrPk, pMsg.manJrnlEntryHdrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.manJrnlEntryDtlPk, rsltSetManJrnlEntry.getBigDecimal("MAN_JRNL_ENTRY_DTL_PK"));

        // START 2016/09/01 J.Kim [QC#12851,MOD]
        //S21ApiTBLAccessor.insert(tMsg);
        //if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
        //    return false;
        //}
        // return true;
        return tMsg;
        // END 2016/09/01 J.Kim [QC#12851,MOD]
    }

    // START 2016/09/01 J.Kim [QC#12851,ADD]
    private boolean insert(List<JRNL_ENTRYTMsg> jrnlEntryTMsgList) {

        JRNL_ENTRYTMsg[] inTMsgArray = new JRNL_ENTRYTMsg[jrnlEntryTMsgList.size()];
        int insertCount = S21FastTBLAccessor.insert(jrnlEntryTMsgList.toArray(inTMsgArray));
        if (insertCount != inTMsgArray.length) {
            return false;
        }
        return true;
    }
    // END 2016/09/01 J.Kim [QC#12851,ADD]
}
