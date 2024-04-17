/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB254001;

import static com.canon.cusa.s21.batch.NWA.NWAB254001.constant.NWAB254001Constant.BIZ_APP_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB254001.constant.NWAB254001Constant.COL_CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB254001.constant.NWAB254001Constant.COL_DOC_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB254001.constant.NWAB254001Constant.COL_DOC_MGT_FLD_VAL_TXT;
import static com.canon.cusa.s21.batch.NWA.NWAB254001.constant.NWAB254001Constant.COL_IDX_ATTRB_TRGT_COL_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB254001.constant.NWAB254001Constant.COL_ORD_TOT_DEAL_NET_AMT;
import static com.canon.cusa.s21.batch.NWA.NWAB254001.constant.NWAB254001Constant.DB_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB254001.constant.NWAB254001Constant.DB_PARAM_PROC_PGM_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB254001.constant.NWAB254001Constant.DOC_MGT_CTAG_SLS_ORD;
import static com.canon.cusa.s21.batch.NWA.NWAB254001.constant.NWAB254001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NWA.NWAB254001.constant.NWAB254001Constant.NWAM0429E;
import static com.canon.cusa.s21.batch.NWA.NWAB254001.constant.NWAB254001Constant.NWAM0877E;
import static com.canon.cusa.s21.batch.NWA.NWAB254001.constant.NWAB254001Constant.NWAM0878E;
import static com.canon.cusa.s21.batch.NWA.NWAB254001.constant.NWAB254001Constant.NWAM0879E;
import static com.canon.cusa.s21.batch.NWA.NWAB254001.constant.NWAB254001Constant.ZZZM9025E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DOC_MGT_CATGTMsg;
import business.db.DOC_MGT_INTFC_TRXTMsg;
import business.db.DOC_MGT_INTFC_TRXTMsgArray;
import business.db.PROC_LAST_EXECTMsg;
import business.parts.NWZC224001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC224001.NWZC224001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDownConst;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * NWAB2540:Therefore Order Indexed Attributes Update Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/24   Fujitsu         W.Honda         Create          N/A
 * 2016/09/16   Fujitsu         W.Honda         Update          QC#14359
 * 2016/11/17   Fujitsu         S.Iidaka        Update          QC#15929
 *</pre>
 */

public class NWAB254001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** normal count */
    private int normalCount = 0;

    /** error count */
    private int errorCount = 0;

    /** SSM Low Level Coding Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** DOC_MGT_CATG : DOC_MGT_CATG_CD */
    private String docMgtCatgCd;

    /** DOC_MGT_CATG : ATT_DOC_TP_CD */
    private String attDocTpCd;

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameter.
     */
    public static void main(String[] args) {
        // Initialization S21BatchMain
        new NWAB254001().executeBatch(NWAB254001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code"});
        }

        // S21SsmLowLevelCodingClient
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        /** S21SsmBatchClient */
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // Parameter check
        this.docMgtCatgCd = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(this.docMgtCatgCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"VAR_USER1"});
        }

        DOC_MGT_CATGTMsg docMgtCatg = new DOC_MGT_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(docMgtCatg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(docMgtCatg.docMgtCatgCd, this.docMgtCatgCd);
        docMgtCatg = (DOC_MGT_CATGTMsg) EZDTBLAccessor.findByKey(docMgtCatg);
        if (docMgtCatg == null) {
            throw new S21AbendException(NWAM0878E, new String[] {docMgtCatgCd });
        }

        if (!ZYPCommonFunc.hasValue(docMgtCatg.attDocTpCd.getValue())) {
            throw new S21AbendException(NWAM0879E, new String[] {docMgtCatgCd });
        }

        this.attDocTpCd = docMgtCatg.attDocTpCd.getValue();
    }

    @Override
    protected void mainRoutine() {
        // S21SsmLowLevelCodintClient Setup
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        execParam.setMaxRows(0);

        PreparedStatement ps = null;
        ResultSet rs = null;

        // Search Target Invoice for CFS
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(DB_PARAM_PROC_PGM_ID, BIZ_APP_ID);

        String lastExecTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");

        try {
            ps = this.ssmLLClient.createPreparedStatement("getChangeOrder", ssmParam, execParam);
            rs = ps.executeQuery();

            while (rs.next()) {
                if (!checkOrdValChange(rs)) {
                    continue;
                }

                //NWCB2240 call
                if (callThereforeIdxAttrbUpdateApi(rs)) {
                    this.normalCount = this.normalCount + 1;
                    commit();
                } else {
                    this.errorCount = this.errorCount + 1;
                    rollback();
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

        // purge For DOC_MGT_INTFC_TRX
        purgeDocMgtIntfcTrx();
        updateLastExec(lastExecTs);
        commit();
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }

        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    private boolean checkOrdValChange(ResultSet ordValRes) throws SQLException {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("cpoOrdNum", ordValRes.getString(COL_CPO_ORD_NUM));
        ssmParam.put("docMgtCatgCd", this.docMgtCatgCd); // 2016/11/17 QC#15929 Add

        List<Map<String, Object>> trxResList = ssmBatchClient.queryObjectList("getDocMgtIntfcTrx", ssmParam);

        boolean isChange = false;

        for (Map<String, Object> trxRes : trxResList) {
            String oldVal = (String) trxRes.get(COL_DOC_MGT_FLD_VAL_TXT);
            String newVal = null;
            if (COL_ORD_TOT_DEAL_NET_AMT.equals((String) trxRes.get(COL_IDX_ATTRB_TRGT_COL_NM))) {
                if (ZYPCommonFunc.hasValue(ordValRes.getBigDecimal((String) trxRes.get(COL_IDX_ATTRB_TRGT_COL_NM)))) {
                    newVal = ordValRes.getBigDecimal((String) trxRes.get(COL_IDX_ATTRB_TRGT_COL_NM)).toString();
                }
            } else {
                if (ZYPCommonFunc.hasValue(ordValRes.getString((String) trxRes.get(COL_IDX_ATTRB_TRGT_COL_NM)))) {
                    newVal = ordValRes.getString((String) trxRes.get(COL_IDX_ATTRB_TRGT_COL_NM));
                }
            }

            // both Null, New value , old value
            if (!ZYPCommonFunc.hasValue(newVal)
                    && !ZYPCommonFunc.hasValue(oldVal)) {
                continue;
            }

            // Values ​​match, New value , old value
            if (ZYPCommonFunc.hasValue(newVal)
                    && ZYPCommonFunc.hasValue(oldVal)
                    && newVal.equals(oldVal)) {
                continue;
            }

            isChange = true;
            break;
        }

        return isChange;
    }

    /**
     * writeLogLn
     * @param format String
     * @param param Object...
     */
    private static void writeLogLn(String format, Object... param) {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[%s]", BIZ_APP_ID));

        if (param.length > 0) {
            sb.append(String.format(format, param));
        } else {
            sb.append(format);
        }

        S21InfoLogOutput.println(sb.toString());
    }

    private void purgeDocMgtIntfcTrx() {

        // S21SsmLowLevelCodintClient Setup
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        execParam.setMaxRows(0);

        PreparedStatement ps = null;
        ResultSet rs = null;

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("ordStsCancelled", ORD_HDR_STS.CANCELLED);
        ssmParam.put("ordStsClosed", ORD_HDR_STS.CLOSED);
        ssmParam.put("procPgmId", BIZ_APP_ID);

        try {
            ps = this.ssmLLClient.createPreparedStatement("getPurgeTargetOrder", ssmParam, execParam);
            rs = ps.executeQuery();

            while (rs.next()) {
                DOC_MGT_INTFC_TRXTMsg inMsg = new DOC_MGT_INTFC_TRXTMsg();
                inMsg.setSQLID("001");
                inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
                inMsg.setConditionValue("docMgtBizDocNum01", rs.getString(COL_CPO_ORD_NUM));
                inMsg.setConditionValue("docMgtCatgCd01", this.docMgtCatgCd);

                DOC_MGT_INTFC_TRXTMsgArray outMsg = (DOC_MGT_INTFC_TRXTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(inMsg);

                if (outMsg == null || outMsg.length() == 0) {
                    continue;
                }

                List<DOC_MGT_INTFC_TRXTMsg> deleteTMsgList = new ArrayList<DOC_MGT_INTFC_TRXTMsg>();
                for (int i = 0; i < outMsg.getValidCount(); i++) {
                    deleteTMsgList.add(outMsg.no(i));
                }
                DOC_MGT_INTFC_TRXTMsg[] outMsgArray = new DOC_MGT_INTFC_TRXTMsg[outMsg.length()];
                S21FastTBLAccessor.removePhysical(deleteTMsgList.toArray(outMsgArray));
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * Call Therefore Indexed Attribute Update API
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException
     */
    private boolean callThereforeIdxAttrbUpdateApi(ResultSet rs) throws SQLException {
        // Get Therefore Document ID
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("attDataGrpTxt", rs.getString(COL_CPO_ORD_NUM));
        ssmParam.put("attDocTpCd", this.attDocTpCd);

        List<Map<String, Object>> resultList = ssmBatchClient.queryObjectList("getDocIdFromAttData", ssmParam);

        boolean isSuccessFlg = true;

        if (resultList == null || resultList.size() == 0) {
            String[] paramArray = new String[] {"ATT_DATA", String.format("ATT_DATA_GRP_TXT = %s, Document Catgory Number = %s", rs.getString(COL_CPO_ORD_NUM), attDocTpCd)};
            String errMsgText = S21MessageFunc.clspGetMessage(NWAM0877E, paramArray);
            writeLogLn(errMsgText);
            isSuccessFlg = false;
        }

        List <NWZC224001PMsg> pMsgList = new ArrayList<NWZC224001PMsg>();
        for (Map<String, Object> result : resultList) {
            NWZC224001PMsg pMsg = new NWZC224001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(pMsg.docMgtDocId, BigDecimal.valueOf(Integer.valueOf((String) result.get(COL_DOC_ID))));
            ZYPEZDItemValueSetter.setValue(pMsg.docMgtCatgCd, DOC_MGT_CTAG_SLS_ORD);
            ZYPEZDItemValueSetter.setValue(pMsg.docMgtPrntDocNum, rs.getString(COL_CPO_ORD_NUM));

            pMsgList.add(pMsg);
        }

        if (pMsgList.size() > 0) {
            NWZC224001 thereforeIdxAttrbUpdateApi = new NWZC224001();

            // 2016/09/16 QC#14359 Mod Start
//            thereforeIdxAttrbUpdateApi.execute(pMsgList, ONBATCH_TYPE.ONLINE, ZYPFileUpDownConst.BIZAPI_TYPE_CREATE);
            thereforeIdxAttrbUpdateApi.execute(pMsgList, ONBATCH_TYPE.BATCH, ZYPFileUpDownConst.BIZAPI_TYPE_CREATE);
            // 2016/09/16 QC#14359 Mod end

            for (NWZC224001PMsg pMsg : pMsgList) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                for (int i = 0; i < msgList.size(); i++) {
                    S21ApiMessage msg = msgList.get(i);
                    String msgId = msg.getXxMsgid();
                    String errMsgText = S21MessageFunc.clspGetMessage(msgId);
                    writeLogLn(errMsgText);
                    isSuccessFlg = false;
                }
            }
        }

        return isSuccessFlg;
    }

    private void updateLastExec(String lastExecTs) {
        PROC_LAST_EXECTMsg updateLastExecTMsg = new PROC_LAST_EXECTMsg();
        ZYPEZDItemValueSetter.setValue(updateLastExecTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(updateLastExecTMsg.procPgmId, BIZ_APP_ID);
        // 2016/09/16 QC#14359 Mod Start
//        updateLastExecTMsg = (PROC_LAST_EXECTMsg) S21CacheTBLAccessor.findByKey(updateLastExecTMsg);
        updateLastExecTMsg = (PROC_LAST_EXECTMsg) EZDTBLAccessor.findByKey(updateLastExecTMsg);
        // 2016/09/16 QC#14359 Mod end
        if (updateLastExecTMsg != null) {
            ZYPEZDItemValueSetter.setValue(updateLastExecTMsg.lastExecTs,lastExecTs);
            S21FastTBLAccessor.update(updateLastExecTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateLastExecTMsg.getReturnCode())) {
                rollback();
                throw new S21AbendException(NWAM0429E, new String[] {"PROC_LAST_EXEC", "PROC_PGM_ID", BIZ_APP_ID });
            }
        } else {
            updateLastExecTMsg = new PROC_LAST_EXECTMsg();
            ZYPEZDItemValueSetter.setValue(updateLastExecTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(updateLastExecTMsg.procPgmId, BIZ_APP_ID);
            ZYPEZDItemValueSetter.setValue(updateLastExecTMsg.lastExecTs,lastExecTs);
            EZDTBLAccessor.create(updateLastExecTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateLastExecTMsg.getReturnCode())) {
                super.rollback();
                throw new S21AbendException("ZZMM0001E", new String[] {"PROC_LAST_EXEC", "PROC_PGM_ID", BIZ_APP_ID });
            }
        }
    }

}
