/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB274001;

import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.LOB_IS;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.LOB_LFS;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.LOB_PPS;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.MAX_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.NMAM0071E;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.NMAM8121E;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.NMAM8228E;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.NMAM8404E;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.NMAM8407E;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.NMAM8457E;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.NMAM8460E;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.NMAM8545E;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.NMAM8546E;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.NMAM8557E;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.NMAM8587I;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.NMAM8588I;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.NMAM8647E;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.NMAM8683E;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.NYXM0001I;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.NYXM0002E;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.ORG_NM_DEL;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.RESULT_MSG_ERR;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.RESULT_MSG_INS;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.RESULT_MSG_UPD;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.SLS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.TIME_STAMP_FORMAT;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.TRTY_GRP_TP_CD_IS;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.TRTY_GRP_TP_CD_LFS;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.TRTY_GRP_TP_CD_PPS;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.UPLD_CSV_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NMA.NMAB274001.constant.NMAB274001Constant.ZZZM9026E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCommonFunc;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ACCT_TRTY_RESRC_ASGTMsg;
import business.db.ACCT_TRTY_RESRC_RQST_DTLTMsg;
import business.db.ACCT_TRTY_RESRC_RQST_HDRTMsg;
import business.db.ACCT_TRTY_ROLE_ASGTMsg;
import business.db.ACCT_TRTY_ROLE_ASGTMsgArray;
import business.db.ART10FMsg;
import business.db.DS_ORG_UNITTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.PROS_PTY_LOC_WRKTMsg;
import business.db.PROS_PTY_LOC_WRKTMsgArray;
import business.db.PROS_TRTY_RESRC_ASGTMsg;
import business.db.PROS_TRTY_ROLE_ASGTMsg;
import business.db.PROS_TRTY_ROLE_ASGTMsgArray;
import business.db.PTY_LOC_WRKTMsg;
import business.db.PTY_LOC_WRKTMsgArray;
import business.db.TRTY_RULETMsg;
import business.db.TRTY_TPTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_CRAT_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_DTL_RSLT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_RSLT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_LOGIC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_OPRD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * Simplified Account Owner Mass Upload Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/03/29   Hitachi         S.Fujita        Create          QC#61113
 * 2023/05/24   Hitachi         S.Fujita        Update          QC#61533
 * 2023/10/23   CITS            K.Ikeda         Update          QC#61970
 * 2024/02/21   CITS            K.Ikeda         Update          QC#61970
 *</pre>
 */
public class NMAB274001 extends S21BatchMain {

    /** GlobalCompanyCode */
    private String glblCmpyCd = null;

    /** SsmLLClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** BatchHelper */
    private S21RequestBatchHelper batchHelper = null;

    /** ZYPCSVUploadMessenger */
    private ZYPCSVUploadMessenger messenger = null;

    /** process date time */
    private String procDt = null;

    /** sales date time */
    private String slsDt = null;

    /** TermCd */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** ONLINE errorMsg */
    private StringBuilder onlineMsg = new StringBuilder();

    /** ONLINE Information Message */
    private StringBuilder onlineInfoMsg = new StringBuilder();

    /** Total Process Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** Update Count (current File) */
    private int currentUpdateCount = 0;

    /** Error Count (current File) */
    private int currentErrorCount = 0;

    /** Upload CSV Request ID List */
    private BigDecimal[] upldCsvRqstPkArray = null;

    /**
     */
    @Override
    protected void initRoutine() {
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.glblCmpyCd = super.getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);

        if (glblCmpyTMsg == null) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Global Company Code" });
        }

        this.procDt = ZYPDateUtil.getBatProcDate();
        if (!ZYPCommonFunc.hasValue(procDt)) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Process Date" });
        }

        this.slsDt = ZYPDateUtil.getSalesDate();
    }

    /**
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NMAB274001().executeBatch(NMAB274001.class.getSimpleName());

    }

    @Override
    // 0 Get request information
    protected void mainRoutine() {

        batchHelper = new S21RequestBatchHelper();
        // START 2023/05/23 S.Fujita [QC#61533 ,ADD]
        if (!this.batchHelper.isRecord()) {
            this.termCd = TERM_CD.WARNING_END;
            return;
        }
        // END 2023/05/23 S.Fujita [QC#61533 ,ADD]

        List<BigDecimal> upldCsvRqstPkList = new ArrayList<BigDecimal>();
        for (ART10FMsg request : this.batchHelper.getRequestList()) {
            upldCsvRqstPkList.add(getUpldCsvReqPk(request));
        }
        this.upldCsvRqstPkArray = upldCsvRqstPkList.toArray(new BigDecimal[upldCsvRqstPkList.size()]);

        for (ART10FMsg request : this.batchHelper.getRequestList()) {
            this.doProcess(request);
        }
    }

    /**
     * @param fMsg ART10FMsg
     */
    // 1 Get work Table
    private void doProcess(ART10FMsg fMsg) {

        PreparedStatement psWrk = null;
        ResultSet rsSet = null;
        String upldCsvId = null;
        BigDecimal upldCsvRqstPk = null;

        upldCsvId = getUpldCsvId(fMsg);
        upldCsvRqstPk = getUpldCsvReqPk(fMsg);
        this.messenger = new ZYPCSVUploadMessenger(upldCsvId, upldCsvRqstPk);

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            Map<String, Object> inWrkMap = new HashMap<String, Object>();
            execParam.setFetchSize(DEFAULT_FETCH_SIZE);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            BigDecimal hdrPk = null;

            inWrkMap.put("glblCmpyCd", glblCmpyCd);
            inWrkMap.put("upldCsvRqstPk", upldCsvRqstPk);
            inWrkMap.put("upldCsvRqstPkArray", this.upldCsvRqstPkArray);

            psWrk = ssmLLClient.createPreparedStatement("getWrkInfo", inWrkMap, execParam);
            rsSet = psWrk.executeQuery();
            hdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ACCT_TRTY_RESRC_RQST_HDR_SQ);

            BigDecimal beforeHdrPk = null;
            String rsnCmntTxt = "";
            boolean isError = false;
            boolean isHeaderError = false;
            boolean hasUpdate = false;

            while (rsSet.next()) {
                // START 2023/05/23 S.Fujita [QC#61533 ,DEL]
                // hdrPk = rsSet.getBigDecimal("UPLD_CSV_RQST_PK");
                // END 2023/05/23 S.Fujita [QC#61533 ,DEL]

                rsnCmntTxt = rsSet.getString("UPLD_CSV_RQST_CMNT_TXT");

                String beforeCode = rsSet.getString("ACCT_TRTY_ORG_CD");
                List<String> afterCodeList = new ArrayList<String>(1);
                List<String> afterGrpCodeList = new ArrayList<String>(0);

                boolean hasAfter = false;
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                String acctTpCd = null;

                // get Acct Type
                Map<String, Object> acctChkParam = new HashMap<String, Object>();
                acctChkParam.put("glblCmpyCd", this.glblCmpyCd);
                acctChkParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
                acctChkParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
                acctChkParam.put("rgtnStsCdP01", RGTN_STS.PENDING_COMPLETION);

                Map<String, String> acctMap = null;
                if (ZYPCommonFunc.hasValue(rsSet.getString("DS_ACCT_NUM"))) {
                acctMap = (Map<String, String>) this.ssmBatchClient.queryObject("getAcct", acctChkParam);
                }
                if (acctMap != null && !acctMap.isEmpty()) {
                    acctTpCd = acctMap.get("DS_ACCT_TP_CD");
                }
                if (ZYPCommonFunc.hasValue(beforeCode)) {
                    ssmParam.put("glblCmpyCd", this.glblCmpyCd);
                    ssmParam.put("orgCd", beforeCode);
                    ssmParam.put("orgNm", "");
                    ssmParam.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
                    ssmParam.put("gnrnTpCd2", GNRN_TP.CURRENT);
                    ssmParam.put("gnrnTpCd3", GNRN_TP.FUTURE);
                    Map<String, String> orgCdMap = (Map<String, String>) this.ssmBatchClient.queryObject("getOrgCd", ssmParam);
                }

                afterCodeList.add(rsSet.getString("ACCT_TRTY_ORG_NM"));
                // 2. Validation
                boolean returnIsError = validationCheck(rsSet, beforeCode, afterCodeList, afterGrpCodeList, hasAfter);
                if (!isError) {
                    isError = returnIsError;
                }

                // 3 Update
                String afterCode = "";
                String afterGrpCode = "";
                if (!isError) {
                    afterCode = afterCodeList.get(1);
                    if (!ORG_NM_DEL.equals(afterCode)) {
                        afterGrpCode = afterGrpCodeList.get(0);
                    }
                    String befManEntryFlg = getManualEntryFlag(rsSet);
                    beforeHdrPk = hdrPk;
                    boolean isChngManFlg = isChangeManualEntryFlag(befManEntryFlg);

                    // Update for change only Manual Entry Flag
                    if (isChngManFlg) {
                        updateManualEntryFlag(afterCode, rsSet, acctTpCd);
                        endDetailProcess(rsSet, hdrPk, beforeCode, afterCode, acctTpCd, rsnCmntTxt, isError);
                        hasUpdate = true;
                        continue;
                    }

                    // 3.1.1 Update Territory Rule
                    // 2024/02/21 QC#61970 K.Ikeda START
                    // if (ZYPCommonFunc.hasValue(beforeCode) && !ZYPCommonFunc.hasValue(afterCode)) {
                    if (ZYPCommonFunc.hasValue(beforeCode)) {
                    // 2024/02/21 QC#61970 K.Ikeda END
                        Map<String, Object> getRuleParam = new HashMap<String, Object>();
                        getRuleParam.put("glblCmpyCd", this.glblCmpyCd);
                        getRuleParam.put("orgCd", rsSet.getString("ACCT_TRTY_ORG_CD"));
                        getRuleParam.put("gnrnTpCd", GNRN_TP.CURRENT);
                        getRuleParam.put("ruleTpCd", TRTY_RULE_TP.LOCATION_NUMBER);
                        getRuleParam.put("fromValTxt", rsSet.getString("LOC_NUM"));
                        getRuleParam.put("oprdTpCd", TRTY_RULE_OPRD_TP.EQUAL);
                        getRuleParam.put("logicTpCd", TRTY_RULE_LOGIC_TP.OR);
                        getRuleParam.put("procDt", this.procDt);
                        getRuleParam.put("maxDt", MAX_DT);

                        BigDecimal rulePk = (BigDecimal) this.ssmBatchClient.queryObject("getTrtyRule", getRuleParam);

                        if (ZYPCommonFunc.hasValue(rulePk)) {
                            TRTY_RULETMsg inTMsg = new TRTY_RULETMsg();
                            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                            ZYPEZDItemValueSetter.setValue(inTMsg.trtyRulePk, rulePk);
                            inTMsg = (TRTY_RULETMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

                            ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, ZYPDateUtil.addDays(this.procDt, -1));
                            EZDTBLAccessor.update(inTMsg);
                        }
                    }

                    // 3.1.2 Insert Territory Rule
                    // DEL and Null Check
                    if (ZYPCommonFunc.hasValue(afterCode) && !ORG_NM_DEL.equals(afterCode)) {

                        if (isGeoTerritory(afterCode)) {
                            if (!isExistsPostCdInTrtyRule(afterCode, rsSet.getString("POST_CD"))) {
                                insertTrtyRuleForPostalCode(afterCode, rsSet.getString("POST_CD"));
                            }
                        // START 2023/05/23 S.Fujita [QC#61533 ,MOD]
                        } else {
                        // END 2023/05/23 S.Fujita [QC#61533 ,MOD]
                            String orgStruTpCd = ORG_STRU_TP.TERRITORY_STRUCTURE;
                            String locNum = rsSet.getString("LOC_NUM");
                            String orgCd = afterCode;
                            if (existsTerritoryRule(locNum, orgCd, orgStruTpCd)) {
                                setInfoMsg(rsSet, NMAM8587I);
                            // START 2023/05/23 S.Fujita [QC#61533 ,MOD]
                            // }
                            // if (isRuleLogicAllOr(orgCd, orgStruTpCd)) {
                            } else if (isRuleLogicAllOr(orgCd, orgStruTpCd)) {
                            // MOD 2023/05/23 S.Fujita [QC#61533 ,MOD]
                                setInfoMsg(rsSet, NMAM8588I);
                            // START 2023/05/23 S.Fujita [QC#61533 ,MOD]
                            // }
                            } else {
                            // END 2023/05/23 S.Fujita [QC#61533 ,MOD]
                                Map<String, Object> getRuleParam = new HashMap<String, Object>();
                                getRuleParam.put("glblCmpyCd", this.glblCmpyCd);
                                getRuleParam.put("orgCd", afterCode);
                                getRuleParam.put("gnrnTpCd", GNRN_TP.CURRENT);
                                getRuleParam.put("ruleTpCd", TRTY_RULE_TP.LOCATION_NUMBER);
                                getRuleParam.put("fromValTxt", rsSet.getString("LOC_NUM"));
                                getRuleParam.put("oprdTpCd", TRTY_RULE_OPRD_TP.EQUAL);
                                getRuleParam.put("logicTpCd", TRTY_RULE_LOGIC_TP.OR);
                                getRuleParam.put("procDt", this.procDt);
                                getRuleParam.put("maxDt", MAX_DT);

                                BigDecimal rulePk = (BigDecimal) this.ssmBatchClient.queryObject("getTrtyRule", getRuleParam);

                                if (!ZYPCommonFunc.hasValue(rulePk)) {
                                    // 2024/02/21 QC#61970 K.Ikeda START
                                    // 2023/10/23 QC#61970 K.Ikeda START
//                                    Map<String, Object> getActiveRuleParam = new HashMap<String, Object>();
//                                    
//                                    getActiveRuleParam.put("glblCmpyCd", this.glblCmpyCd);
//                                    getActiveRuleParam.put("fromValTxt", rsSet.getString("LOC_NUM"));
//                                    getActiveRuleParam.put("procDt", this.procDt);
//                                    getActiveRuleParam.put("maxDt", MAX_DT);
//                                    BigDecimal activeRulePk = (BigDecimal) this.ssmBatchClient.queryObject("getActiveTrtyRule", getActiveRuleParam);
//                                    
//                                    if (activeRulePk != null) {
//                                        TRTY_RULETMsg upTMsg = new TRTY_RULETMsg();
//                                        ZYPEZDItemValueSetter.setValue(upTMsg.glblCmpyCd, this.glblCmpyCd);
//                                        ZYPEZDItemValueSetter.setValue(upTMsg.trtyRulePk, activeRulePk);
//                                        
//                                        upTMsg = (TRTY_RULETMsg) EZDTBLAccessor.findByKeyForUpdateWait(upTMsg);
//                                        ZYPEZDItemValueSetter.setValue(upTMsg.effThruDt, ZYPDateUtil.addDays(this.procDt, -1));
//
//                                        EZDTBLAccessor.update(upTMsg);
//                                    }
                                    // 2023/10/23 QC#61970 K.Ikeda END
                                    // 2024/02/21 QC#61970 K.Ikeda END
                                    TRTY_RULETMsg inTMsg = new TRTY_RULETMsg();

                                    ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                                    BigDecimal trtyRulePk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TRTY_RULE_SQ);
                                    ZYPEZDItemValueSetter.setValue(inTMsg.trtyRulePk, trtyRulePk);
                                    ZYPEZDItemValueSetter.setValue(inTMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
                                    ZYPEZDItemValueSetter.setValue(inTMsg.orgCd, afterCode.toString());
                                    ZYPEZDItemValueSetter.setValue(inTMsg.trtyRuleTpCd, TRTY_RULE_TP.LOCATION_NUMBER);
                                    ZYPEZDItemValueSetter.setValue(inTMsg.trtyRuleFromValTxt, rsSet.getString("LOC_NUM"));
                                    ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, this.procDt);
                                    ZYPEZDItemValueSetter.setValue(inTMsg.trtyRuleOprdTpCd, TRTY_RULE_OPRD_TP.EQUAL);
                                    ZYPEZDItemValueSetter.setValue(inTMsg.trtyRuleLogicTpCd, TRTY_RULE_LOGIC_TP.OR);

                                    EZDTBLAccessor.insert(inTMsg);
                                    
                                    // 2024/02/21 QC#61615 START
                                    BigDecimal futureRulePk = (BigDecimal) this.ssmBatchClient.queryObject("getFutureTrtyRule", getRuleParam);

                                    if (futureRulePk != null) {
                                        TRTY_RULETMsg upTMsg = new TRTY_RULETMsg();
                                        ZYPEZDItemValueSetter.setValue(upTMsg.glblCmpyCd, this.glblCmpyCd);
                                        ZYPEZDItemValueSetter.setValue(upTMsg.trtyRulePk, futureRulePk);

                                        upTMsg = (TRTY_RULETMsg) EZDTBLAccessor.findByKeyForUpdateWait(upTMsg);
                                        inTMsg = (TRTY_RULETMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

                                        if (ZYPCommonFunc.hasValue(upTMsg.effFromDt)) {
                                            ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, ZYPDateUtil.addDays(upTMsg.effFromDt.getValue(), -1));

                                            EZDTBLAccessor.update(inTMsg);
                                        }
                                    }
                                    // 2024/02/21 QC#61615 END
                                }
                            }
                        // START 2023/05/23 S.Fujita [QC#61533 ,MOD]
                        }
                        // END 2023/05/23 S.Fujita [QC#61533 ,MOD]
                    }
                    Map<String, Object> getLineParam = new HashMap<String, Object>();

                    int no = '0';
                    if (LOB_PPS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
                        no = TRTY_GRP_TP_CD_PPS;
                    } else if (LOB_LFS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
                        no = TRTY_GRP_TP_CD_LFS;
                    } else if (LOB_IS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
                        no = TRTY_GRP_TP_CD_IS;
                    }

                    getLineParam.put("glblCmpyCd", this.glblCmpyCd);
                    getLineParam.put("dsAcctTp", acctTpCd);
                    getLineParam.put("asgTrtyAttrb", no);
                    getLineParam.put("trtyGrpTp", afterGrpCode);
                    String lineRank = (String) this.ssmBatchClient.queryObject("getLineBizRankNum", getLineParam);

                    if (!ZYPCommonFunc.hasValue(rsSet.getString("ACCT_TRTY_ORG_CD")) && ZYPCommonFunc.hasValue(afterCode)) {
                        Map<String, Object> getAcctForNewModeParam = new HashMap<String, Object>();
                        getAcctForNewModeParam.put("glblCmpyCd", this.glblCmpyCd);
                        if (DS_ACCT_TP.CUSTOMER.equals(acctTpCd)) {
                            getAcctForNewModeParam.put("acctFlag", ZYPConstant.FLG_ON_Y);
                        } else {
                            getAcctForNewModeParam.put("acctFlag", ZYPConstant.FLG_OFF_N);
                            getAcctForNewModeParam.put("rgtnStsCd_P01", RGTN_STS.PENDING_COMPLETION);
                        }
                        getAcctForNewModeParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
                        getAcctForNewModeParam.put("locNum", rsSet.getString("LOC_NUM"));
                        getAcctForNewModeParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
                        getAcctForNewModeParam.put("attrbCd", String.valueOf(no));
                        getAcctForNewModeParam.put("trtyGrpTpCd", afterGrpCode);
                        getAcctForNewModeParam.put("rgtnStsCd_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
                        getAcctForNewModeParam.put("slsDt", this.slsDt);
                        getAcctForNewModeParam.put("maxDt", "99991231");
                        getAcctForNewModeParam.put("rowNum_1", 1);
                        getAcctForNewModeParam.put("rowNum_2", 2);
                        getAcctForNewModeParam.put("rowNum_3", 3);
                        getAcctForNewModeParam.put("rowNum_4", 4);
                        getAcctForNewModeParam.put("rowNum_5", 5);
                        Map<String, Object> acctInfoMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getAcctForNewMode", getAcctForNewModeParam);

                        // START 2023/05/23 S.Fujita [QC#61533 ,MOD]
                        // if (!ZYPCommonFunc.hasValue(acctInfoMap.toString()) && acctInfoMap.isEmpty()) {
                        if (acctInfoMap == null || acctInfoMap.isEmpty()) {  
                        // END 2023/05/23 S.Fujita [QC#61533 ,MOD]
                            setErrorMsg(rsSet, NMAM8683E);
                            isError = true;
                            // START 2023/05/23 S.Fujita [QC#61533 ,ADD]
                            endDetailProcess(rsSet, hdrPk, beforeCode, afterCode, acctTpCd, rsnCmntTxt, isError);
                            isHeaderError = true;
                            // END 2023/05/23 S.Fujita [QC#61533 ,ADD]
                            continue;
                        }
                        if (!ZYPCommonFunc.hasValue((String) acctInfoMap.get("LINE_BIZ_ROLE_TP_CD"))) {
                            setErrorMsg(rsSet, NMAM8647E);
                            isError = true;
                            // START 2023/05/23 S.Fujita [QC#61533 ,ADD]
                            endDetailProcess(rsSet, hdrPk, beforeCode, afterCode, acctTpCd, rsnCmntTxt, isError);
                            isHeaderError = true;
                            // END 2023/05/23 S.Fujita [QC#61533 ,ADD]
                            continue;
                        }

                        if (DS_ACCT_TP.CUSTOMER.equals(acctTpCd)) {
                            List<Map<String, Object>> insertRoleMapList = null;
                            if (ZYPCommonFunc.hasValue(lineRank)) {
                                insertRoleMapList = getTrtyInfo(afterCode, true);
                                if (insertRoleMapList.size() > 0) {
                                    ACCT_TRTY_ROLE_ASGTMsg insertTMsg = setInsertMsgForNewMode(acctInfoMap, insertRoleMapList.get(0), befManEntryFlg, String.valueOf(no), afterCode);
                                    EZDTBLAccessor.insert(insertTMsg);
                                }
                            } else {
                                insertRoleMapList = getTrtyInfo(afterCode, false);

                                for (Map<String, Object> insertRoleMap : insertRoleMapList) {
                                    ACCT_TRTY_ROLE_ASGTMsg insertTMsg = setInsertMsgForNewMode(acctInfoMap, insertRoleMap, befManEntryFlg, String.valueOf(no), afterCode);
                                    EZDTBLAccessor.insert(insertTMsg);
                                }
                            }

                        } else if (DS_ACCT_TP.PROSPECT.equals(acctTpCd)) {
                            List<Map<String, Object>> insertRoleMapList = null;
                            if (ZYPCommonFunc.hasValue(lineRank)) {
                                insertRoleMapList = getTrtyInfo(afterCode, true);
                                if (insertRoleMapList.size() > 0) {
                                    PROS_TRTY_ROLE_ASGTMsg insertTMsg = setInsertProsMsgForNewMode(acctInfoMap, insertRoleMapList.get(0), befManEntryFlg, String.valueOf(no), afterCode);
                                    EZDTBLAccessor.insert(insertTMsg);
                                }
                            } else {
                                insertRoleMapList = getTrtyInfo(afterCode, false);

                                for (Map<String, Object> insertRoleMap : insertRoleMapList) {
                                    PROS_TRTY_ROLE_ASGTMsg insertTMsg = setInsertProsMsgForNewMode(acctInfoMap, insertRoleMap, befManEntryFlg, String.valueOf(no), afterCode);
                                    EZDTBLAccessor.insert(insertTMsg);
                                }
                            }
                        }

                        // Update Mode
                    } else {
                        if (DS_ACCT_TP.CUSTOMER.equals(acctTpCd)) {
                            if (ZYPCommonFunc.hasValue(lineRank)) {
                                // 3.1.3 Update ACCT_TRTY_ROLE_ASG
                                // (SlsRep)
                                Map<String, Object> getAcctTrtyParam = new HashMap<String, Object>();
                                getAcctTrtyParam.put("glblCmpyCd", this.glblCmpyCd);
                                getAcctTrtyParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
                                getAcctTrtyParam.put("locNum", rsSet.getString("LOC_NUM"));
                                getAcctTrtyParam.put("orgCd", beforeCode);

                                Map<String, Object> acctRoleMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getAcctTrtyRoleAgs", getAcctTrtyParam);

                                if (acctRoleMap == null || acctRoleMap.isEmpty()) {
                                    setErrorMsg(rsSet, NMAM8121E, "ACCT_TRTY_ROLE_ASG");
                                    isError = true;
                                    // START 2023/05/23 S.Fujita [QC#61533 ,ADD]
                                    endDetailProcess(rsSet, hdrPk, beforeCode, afterCode, acctTpCd, rsnCmntTxt, isError);
                                    isHeaderError = true;
                                    // END 2023/05/23 S.Fujita [QC#61533 ,ADD]
                                    continue;
                                }

                                ACCT_TRTY_ROLE_ASGTMsg inTMsg = new ACCT_TRTY_ROLE_ASGTMsg();
                                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyRoleAsgPk, (BigDecimal) acctRoleMap.get("ACCT_TRTY_ROLE_ASG_PK"));

                                inTMsg = (ACCT_TRTY_ROLE_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

                                S21FastTBLAccessor.removeLogical(new ACCT_TRTY_ROLE_ASGTMsg[] {inTMsg });

                                // 3.1.4 Insert ACCT_TRTY_ROLE_ASG
                                // DEL and Null Check
                                if (ZYPCommonFunc.hasValue(afterCode) && !ORG_NM_DEL.equals(afterCode)) {

                                    // (SlsRep)
                                    Map<String, Object> getInsertCustParam = new HashMap<String, Object>();
                                    getInsertCustParam.put("glblCmpyCd", this.glblCmpyCd);
                                    getInsertCustParam.put("orgCd", afterCode);
                                    getInsertCustParam.put("isSlsRepFlg", ZYPConstant.FLG_ON_Y);
                                    getInsertCustParam.put("procDt", this.procDt);
                                    getInsertCustParam.put("maxDt", MAX_DT);
                                    getInsertCustParam.put("gnrnTpCd2", GNRN_TP.CURRENT);

                                    Map<String, Object> insertRoleMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getInsertData", getInsertCustParam);

                                    if (insertRoleMap == null || insertRoleMap.isEmpty()) {
                                        setErrorMsg(rsSet, NMAM8121E, "Salesrep");
                                        isError = true;
                                        // START 2023/05/23 S.Fujita [QC#61533 ,ADD]
                                        endDetailProcess(rsSet, hdrPk, beforeCode, afterCode, acctTpCd, rsnCmntTxt, isError);
                                        isHeaderError = true;
                                        // END 2023/05/23 S.Fujita [QC#61533 ,ADD]
                                        continue;
                                    }

                                    ACCT_TRTY_ROLE_ASGTMsg insertTMsg = setInsertMsgForCust(acctRoleMap, insertRoleMap, afterCode, befManEntryFlg);
                                    EZDTBLAccessor.insert(insertTMsg);
                                }
                            } else {
                                // 3.1.5 Update ACCT_TRTY_ROLE_ASG
                                // (Specialist)
                                Map<String, Object> getAcctTrtyParam = new HashMap<String, Object>();
                                getAcctTrtyParam.put("glblCmpyCd", this.glblCmpyCd);
                                getAcctTrtyParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
                                getAcctTrtyParam.put("locNum", rsSet.getString("LOC_NUM"));
                                getAcctTrtyParam.put("orgCd", beforeCode);

                                Map<String, Object> acctRoleMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getAcctTrtyRoleAgs", getAcctTrtyParam);

                                if (acctRoleMap == null || acctRoleMap.isEmpty()) {
                                    setErrorMsg(rsSet, NMAM8121E, "ACCT_TRTY_ROLE_ASG");
                                    isError = true;
                                    // START 2023/05/23 S.Fujita [QC#61533 ,ADD]
                                    endDetailProcess(rsSet, hdrPk, beforeCode, afterCode, acctTpCd, rsnCmntTxt, isError);
                                    isHeaderError = true;
                                    // END 2023/05/23 S.Fujita [QC#61533 ,ADD]
                                    continue;
                                }

                                ACCT_TRTY_ROLE_ASGTMsg inTMsg = new ACCT_TRTY_ROLE_ASGTMsg();
                                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyRoleAsgPk, (BigDecimal) acctRoleMap.get("ACCT_TRTY_ROLE_ASG_PK"));

                                inTMsg = (ACCT_TRTY_ROLE_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

                                if (inTMsg == null) {
                                    setErrorMsg(rsSet, NMAM8121E, "ACCT_TRTY_ROLE_ASG");
                                    isError = true;
                                    // START 2023/05/23 S.Fujita [QC#61533 ,ADD]
                                    endDetailProcess(rsSet, hdrPk, beforeCode, afterCode, acctTpCd, rsnCmntTxt, isError);
                                    isHeaderError = true;
                                    // END 2023/05/23 S.Fujita [QC#61533 ,ADD]
                                    continue;
                                }

                                S21FastTBLAccessor.removeLogical(new ACCT_TRTY_ROLE_ASGTMsg[] {inTMsg });

                                // 3.1.6 Insert ACCT_TRTY_ROLE_ASG
                                // (Specialist)
                                // DEL and Null Check
                                if (ZYPCommonFunc.hasValue(afterCode) && !ORG_NM_DEL.equals(afterCode)) {

                                    Map<String, Object> getInsertCustParam = new HashMap<String, Object>();
                                    getInsertCustParam.put("glblCmpyCd", this.glblCmpyCd);
                                    getInsertCustParam.put("orgCd", afterCode);
                                    getInsertCustParam.put("isSlsRepFlg", ZYPConstant.FLG_OFF_N);
                                    getInsertCustParam.put("procDt", this.procDt);
                                    getInsertCustParam.put("maxDt", MAX_DT);
                                    getInsertCustParam.put("gnrnTpCd2", GNRN_TP.CURRENT);
                                    getInsertCustParam.put("firstOrgCd", SLS_CD);

                                    Map<String, Object> insertRoleMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getInsertData", getInsertCustParam);

                                    if (insertRoleMap == null || insertRoleMap.isEmpty()) {
                                        setErrorMsg(rsSet, NMAM8121E, "Specialist");
                                        isError = true;
                                        // START 2023/05/23 S.Fujita [QC#61533 ,ADD]
                                        endDetailProcess(rsSet, hdrPk, beforeCode, afterCode, acctTpCd, rsnCmntTxt, isError);
                                        isHeaderError = true;
                                        continue;
                                        // END 2023/05/23 S.Fujita [QC#61533 ,ADD]
                                        // continue;
                                    }

                                    ACCT_TRTY_ROLE_ASGTMsg insertTMsg = setInsertMsgForCust(acctRoleMap, insertRoleMap, afterCode, befManEntryFlg);
                                    EZDTBLAccessor.insert(insertTMsg);
                                }
                            }
                        } else if (DS_ACCT_TP.PROSPECT.equals(acctTpCd)) {
                            if (ZYPCommonFunc.hasValue(lineRank)) {
                                // 3.1.7 Update PROS_TRTY_ROLE_ASG
                                // (SlsRep)
                                Map<String, Object> getProcTrtyParam = new HashMap<String, Object>();
                                getProcTrtyParam.put("glblCmpyCd", this.glblCmpyCd);
                                getProcTrtyParam.put("attrbCd", no);
                                getProcTrtyParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
                                getProcTrtyParam.put("locNum", rsSet.getString("LOC_NUM"));
                                getProcTrtyParam.put("orgCd", rsSet.getString("ACCT_TRTY_ORG_CD"));

                                Map<String, Object> procRoleMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getProcTrtyRoleAgs", getProcTrtyParam);

                                if (procRoleMap == null || procRoleMap.isEmpty()) {
                                    setErrorMsg(rsSet, NMAM8121E, "PROS_TRTY_ROLE_ASG");
                                    isError = true;
                                    // START 2023/05/23 S.Fujita [QC#61533 ,ADD]
                                    endDetailProcess(rsSet, hdrPk, beforeCode, afterCode, acctTpCd, rsnCmntTxt, isError);
                                    isHeaderError = true;
                                    continue;
                                    // END 2023/05/23 S.Fujita [QC#61533 ,ADD]
                                    // continue;
                                }

                                PROS_TRTY_ROLE_ASGTMsg inTMsg = new PROS_TRTY_ROLE_ASGTMsg();
                                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyRoleAsgPk, (BigDecimal) procRoleMap.get("ACCT_TRTY_ROLE_ASG_PK"));

                                inTMsg = (PROS_TRTY_ROLE_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

                                S21FastTBLAccessor.removeLogical(new PROS_TRTY_ROLE_ASGTMsg[] {inTMsg });

                                // 3.1.8 Insert PROS_TRTY_ROLE_ASG
                                // (SlsRep)
                                // DEL and Null Check
                                // START 2023/05/23 S.Fujita [QC#61533 ,MOD]
                                // if (ZYPCommonFunc.hasValue(afterCode) || ORG_NM_DEL.equals(afterCode)) {
                                if (ZYPCommonFunc.hasValue(afterCode) && !ORG_NM_DEL.equals(afterCode)) {
                                // END 2023/05/23 S.Fujita [QC#61533 ,MOD]
                                    Map<String, Object> getInsertCustParam = new HashMap<String, Object>();
                                    getInsertCustParam.put("glblCmpyCd", this.glblCmpyCd);
                                    getInsertCustParam.put("orgCd", afterCode);
                                    getInsertCustParam.put("isSlsRepFlg", ZYPConstant.FLG_ON_Y);
                                    getInsertCustParam.put("procDt", this.procDt);
                                    getInsertCustParam.put("maxDt", MAX_DT);
                                    getInsertCustParam.put("gnrnTpCd2", GNRN_TP.CURRENT);

                                    Map<String, Object> insertRoleMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getInsertData", getInsertCustParam);

                                    if (insertRoleMap == null || insertRoleMap.isEmpty()) {
                                        setErrorMsg(rsSet, NMAM8121E, "Salesrep");
                                        isError = true;
                                        // START 2023/05/23 S.Fujita [QC#61533 ,ADD]
                                        endDetailProcess(rsSet, hdrPk, beforeCode, afterCode, acctTpCd, rsnCmntTxt, isError);
                                        isHeaderError = true;
                                        continue;
                                        // END 2023/05/23 S.Fujita [QC#61533 ,ADD]
                                        // continue;
                                    }

                                    PROS_TRTY_ROLE_ASGTMsg insertTMsg = setInsertMsgForProc(procRoleMap, insertRoleMap, afterCode, befManEntryFlg);
                                    EZDTBLAccessor.insert(insertTMsg);
                                }
                            } else {
                                // 3.1.9 Update PROS_TRTY_ROLE_ASG
                                // (Specialist)
                                Map<String, Object> getProcTrtyParam = new HashMap<String, Object>();
                                getProcTrtyParam.put("glblCmpyCd", this.glblCmpyCd);
                                getProcTrtyParam.put("attrbCd", no);
                                getProcTrtyParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
                                getProcTrtyParam.put("locNum", rsSet.getString("LOC_NUM"));
                                getProcTrtyParam.put("orgCd", rsSet.getString("ACCT_TRTY_ORG_CD"));

                                Map<String, Object> procRoleMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getProcTrtyRoleAgs", getProcTrtyParam);

                                if (procRoleMap == null || procRoleMap.isEmpty()) {
                                    setErrorMsg(rsSet, NMAM8121E, "PROS_TRTY_ROLE_ASG");
                                    isError = true;
                                    // START 2023/05/23 S.Fujita [QC#61533 ,ADD]
                                    endDetailProcess(rsSet, hdrPk, beforeCode, afterCode, acctTpCd, rsnCmntTxt, isError);
                                    isHeaderError = true;
                                    continue;
                                    // END 2023/05/23 S.Fujita [QC#61533 ,ADD]
                                    // continue;
                                }

                                PROS_TRTY_ROLE_ASGTMsg inTMsg = new PROS_TRTY_ROLE_ASGTMsg();
                                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyRoleAsgPk, (BigDecimal) procRoleMap.get("ACCT_TRTY_ROLE_ASG_PK"));

                                inTMsg = (PROS_TRTY_ROLE_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

                                S21FastTBLAccessor.removeLogical(new PROS_TRTY_ROLE_ASGTMsg[] {inTMsg });

                                // 3.1.10 Insert PROS_TRTY_ROLE_ASG
                                // (Specialist)
                                // DEL and Null Check
                                if (ZYPCommonFunc.hasValue(afterCode) && !ORG_NM_DEL.equals(afterCode)) {

                                    Map<String, Object> getInsertCustParam = new HashMap<String, Object>();
                                    getInsertCustParam.put("glblCmpyCd", this.glblCmpyCd);
                                    getInsertCustParam.put("orgCd", afterCode);
                                    getInsertCustParam.put("isSlsRepFlg", ZYPConstant.FLG_OFF_N);
                                    getInsertCustParam.put("procDt", this.procDt);
                                    getInsertCustParam.put("maxDt", MAX_DT);
                                    getInsertCustParam.put("gnrnTpCd2", GNRN_TP.CURRENT);
                                    getInsertCustParam.put("firstOrgCd", SLS_CD);

                                    Map<String, Object> insertRoleMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getInsertData", getInsertCustParam);

                                    // START 2023/05/23 S.Fujita [QC#61533 ,MOD]
                                    // if (ZYPCommonFunc.hasValue(insertRoleMap.toString()) || insertRoleMap.isEmpty()) {
                                    if (insertRoleMap == null || insertRoleMap.isEmpty()) {
                                    // END 2023/05/23 S.Fujita [QC#61533 ,MOD]
                                        setErrorMsg(rsSet, NMAM8121E, "Specialist");
                                        // START 2023/05/23 S.Fujita [QC#61533 ,ADD]
                                        isError = true;
                                        endDetailProcess(rsSet, hdrPk, beforeCode, afterCode, acctTpCd, rsnCmntTxt, isError);
                                        isHeaderError = true;
                                        continue;
                                        // END 2023/05/23 S.Fujita [QC#61533 ,ADD]
                                        // continue;
                                    }

                                    PROS_TRTY_ROLE_ASGTMsg insertTMsg = setInsertMsgForProc(procRoleMap, insertRoleMap, afterCode, befManEntryFlg);
                                    EZDTBLAccessor.insert(insertTMsg);
                                }
                            }
                        }
                    }

                    // 3.1.11 Update ACCT_TRTY_RESRC_ASG
                    String psnCd = null;
                    String tocCd = null;

                    if (ZYPCommonFunc.hasValue(afterCode) && !ORG_NM_DEL.equals(afterCode)) {
                        Map<String, Object> getInsertCustParam = new HashMap<String, Object>();
                        getInsertCustParam.put("glblCmpyCd", this.glblCmpyCd);
                        getInsertCustParam.put("isSlsRepFlg", ZYPConstant.FLG_ON_Y);
                        getInsertCustParam.put("procDt", this.procDt);
                        getInsertCustParam.put("maxDt", MAX_DT);
                        getInsertCustParam.put("gnrnTpCd2", GNRN_TP.CURRENT);
                        getInsertCustParam.put("orgCd", afterCode);

                        Map<String, Object> insertRoleMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getInsertData", getInsertCustParam);

                        // START 2023/05/23 S.Fujita [QC#61533 ,MOD]
                        // if (ZYPCommonFunc.hasValue(insertRoleMap.toString()) && !insertRoleMap.isEmpty()) {
                        if (insertRoleMap != null && !insertRoleMap.isEmpty()) {
                        // END 2023/05/23 S.Fujita [QC#61533 ,MOD]
                            psnCd = (String) insertRoleMap.get("PSN_CD");
                            tocCd = (String) insertRoleMap.get("TOC_CD");
                        }
                    }

                    if (DS_ACCT_TP.CUSTOMER.equals(acctTpCd)) {
                        ACCT_TRTY_RESRC_ASGTMsg inTMsg = new ACCT_TRTY_RESRC_ASGTMsg();
                        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctNum, rsSet.getString("DS_ACCT_NUM"));
                        ZYPEZDItemValueSetter.setValue(inTMsg.locNum, rsSet.getString("LOC_NUM"));
                        ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctTpCd, acctTpCd);
                        inTMsg = (ACCT_TRTY_RESRC_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);
                        // START 2023/05/23 S.Fujita [QC#61533 ,MOD]
                        // if (!ZYPCommonFunc.hasValue(inTMsg.toString())) {
                        if (inTMsg == null) {
                        // END 2023/05/23 S.Fujita [QC#61533 ,MOD]
                            setErrorMsg(rsSet, NMAM8121E, "ACCT_TRTY_RESRC_ASG");
                            isError = true;
                        } else {
                            setAcctTrtyResrcAsgPrmForCust(inTMsg, afterCode, psnCd, tocCd, befManEntryFlg, rsSet);
                            EZDTBLAccessor.update(inTMsg);
                        }

                    } else if (DS_ACCT_TP.PROSPECT.equals(acctTpCd)) {
                        PROS_TRTY_RESRC_ASGTMsg inTMsg = new PROS_TRTY_RESRC_ASGTMsg();
                        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctNum, rsSet.getString("DS_ACCT_NUM"));
                        ZYPEZDItemValueSetter.setValue(inTMsg.locNum, rsSet.getString("LOC_NUM"));
                        ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctTpCd, acctTpCd);
                        inTMsg = (PROS_TRTY_RESRC_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);
                        // START 2023/05/23 S.Fujita [QC#61533 ,MOD]
                        // if (!ZYPCommonFunc.hasValue(inTMsg.toString())) {
                        if (inTMsg == null) {
                        // END 2023/05/23 S.Fujita [QC#61533 ,MOD]
                            setErrorMsg(rsSet, NMAM8121E, "PROS_TRTY_RESRC_ASG");
                            isError = true;
                        } else {
                            setAcctTrtyResrcAsgPrmForPros(inTMsg, afterCode, psnCd, tocCd, befManEntryFlg, rsSet);
                            EZDTBLAccessor.update(inTMsg);
                        }
                    }
                }
                // 4.Error
                if (isError) {
                    setErrorMsg(rsSet, NMAM8457E);
                    rollback();
                }

                // 5. Update ACCT_TRTY_RESRC_RQST_DTL,

                createUpdateDetail(rsSet, hdrPk, beforeCode, afterCode, acctTpCd, rsnCmntTxt, isError);
                // 6.Commit
                commit();
                hasUpdate = true;
                if (isError) {
                    isHeaderError = true;
                }
            }
            // 7. Update ACCT_TRTY_RESRC_RQST_HDR
            if (hasUpdate || isHeaderError) {
                if (!isHeaderError) {
                    updateHeader(isHeaderError, hdrPk, upldCsvId, fMsg);
                } else {
                    rollback();
                    updateHeader(isHeaderError, hdrPk, upldCsvId, fMsg);
                }
                // 8.Commit
                commit();
            }
        } catch (SQLException se) {
            sqlExceptionHandler(se);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(psWrk, rsSet);
        }
    }

    @Override
    protected void termRoutine() {
        this.totalCount = this.totalNmlCount + this.totalErrCount;
        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);

    }

    /**
     * endDetailProcess
     * @param rsSet ResultSet
     * @param hdrPk BigDecimal
     * @param beforeCode String
     * @param afterCode String
     * @param acctTpCd String
     * @param rsnCmntTxt String
     * @param isError boolean
     * @throws SQLException
     */
    private void endDetailProcess(ResultSet rsSet, BigDecimal hdrPk, String beforeCode, String afterCode, String acctTpCd, String rsnCmntTxt, boolean isError) throws SQLException {

        if (isError) {
            rollback();
        }

        createUpdateDetail(rsSet, hdrPk, beforeCode, afterCode, acctTpCd, rsnCmntTxt, isError);
        commit();
    }

    /**
     * @param fMsg ART10FMsg
     * @return Upload CSV ID
     */
    private String getUpldCsvId(ART10FMsg fMsg) {

        String uploadCsvId = fMsg.EZREQUserKey.getValue();

        if (S21StringUtil.isEmpty(uploadCsvId)) {
            String[] msg = {UPLD_CSV_ID };
            throw new S21AbendException(NMAM8228E, msg);
        }

        return S21RequestBatchHelper.removeDoubleQuotation(uploadCsvId);
    }

    /**
     * @param fMsg ART10FMsg
     * @return Upload CSV Request PK
     */
    private BigDecimal getUpldCsvReqPk(ART10FMsg fMsg) {

        String upldCsvReqPk = fMsg.EZREQCondition.getValue();
        String[] msg = {UPLD_CSV_RQST_PK };
        String removeDQRequestPK = S21RequestBatchHelper.removeDoubleQuotation(upldCsvReqPk);

        if (S21StringUtil.isEmpty(removeDQRequestPK)) {
            throw new S21AbendException(NMAM8228E, msg);
        }

        if (!EZDCommonFunc.isNumeric(removeDQRequestPK)) {
            throw new S21AbendException(NMAM0071E, msg);
        }
        return new BigDecimal(removeDQRequestPK);
    }

    /**
     * @return boolean
     */
    private boolean isChangeManualEntryFlag(String befManEntryFlg) {

        if (!ZYPCommonFunc.hasValue(befManEntryFlg)) {
            return true;
        }
        return false;
    }

    /**
     * @param rsSet ResultSet
     * @return String
     */
    private String getManualEntryFlag(ResultSet rsSet) throws SQLException {
        String beforeManFlg = null;
        if (!ZYPCommonFunc.hasValue(rsSet.getString("DS_ACCT_NUM")) || !ZYPCommonFunc.hasValue(rsSet.getString("LOC_NUM"))) {
             beforeManFlg = ZYPConstant.FLG_OFF_N;
        } else {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
            ssmParam.put("locNum", rsSet.getString("LOC_NUM"));

            beforeManFlg = (String) this.ssmBatchClient.queryObject("getManualFlag", ssmParam);

            if (!ZYPCommonFunc.hasValue(beforeManFlg) || beforeManFlg.isEmpty()) {
                beforeManFlg = ZYPConstant.FLG_OFF_N;
            }
        }
        return beforeManFlg;
    }

    /**
     * @param afterCode String
     * @param rsSet ResultSet
     * @param acctTpCd String
     */
    private void updateManualEntryFlag(String afterCode, ResultSet rsSet, String acctTpCd) throws SQLException {
        String befManEntryFlg = getManualEntryFlag(rsSet);
        if (DS_ACCT_TP.CUSTOMER.equals(acctTpCd)) {
            // Update ACCT_TRTY_ROLE_ASG
            ACCT_TRTY_ROLE_ASGTMsg inRoleTMsg = new ACCT_TRTY_ROLE_ASGTMsg();
            inRoleTMsg.setSQLID("003");
            inRoleTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            inRoleTMsg.setConditionValue("dsAcctNum01", rsSet.getString("DS_ACCT_NUM"));
            inRoleTMsg.setConditionValue("locNum01", rsSet.getString("LOC_NUM"));

            ACCT_TRTY_ROLE_ASGTMsgArray inRoleTMsgAry = (ACCT_TRTY_ROLE_ASGTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inRoleTMsg);

            if (inRoleTMsgAry.length() == 0) {
                return;
            }
            // set Update FlageData
            List<ACCT_TRTY_ROLE_ASGTMsg> acctTrtyRoleAsgList = new ArrayList<ACCT_TRTY_ROLE_ASGTMsg>();
            for (int i = 0; i < inRoleTMsgAry.length(); i++) {
                inRoleTMsgAry.no(i).manEntryFlg.setValue(befManEntryFlg);
                acctTrtyRoleAsgList.add(inRoleTMsgAry.no(i));
            }

            // for DataBase Update
            int updCnt = acctTrtyRoleAsgList.size();
            int rsltCnt = S21FastTBLAccessor.update(acctTrtyRoleAsgList.toArray(new ACCT_TRTY_ROLE_ASGTMsg[updCnt]));
            if (rsltCnt != updCnt) {
                return;
            }

            // Update ACCT_TRTY_RESRC_ASG
            ACCT_TRTY_RESRC_ASGTMsg inResrcTMsg = new ACCT_TRTY_RESRC_ASGTMsg();
            ZYPEZDItemValueSetter.setValue(inResrcTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inResrcTMsg.dsAcctNum, rsSet.getString("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(inResrcTMsg.locNum, rsSet.getString("LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(inResrcTMsg.dsAcctTpCd, acctTpCd);
            inResrcTMsg = (ACCT_TRTY_RESRC_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inResrcTMsg);

            ZYPEZDItemValueSetter.setValue(inResrcTMsg.manEntryFlg, befManEntryFlg);

            EZDTBLAccessor.update(inResrcTMsg);
        } else if (DS_ACCT_TP.PROSPECT.equals(acctTpCd)) {
            // Update ACCT_TRTY_ROLE_ASG
            PROS_TRTY_ROLE_ASGTMsg inRoleTMsg = new PROS_TRTY_ROLE_ASGTMsg();
            inRoleTMsg.setSQLID("003");
            inRoleTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            inRoleTMsg.setConditionValue("dsAcctNum01", rsSet.getString("DS_ACCT_NUM"));
            inRoleTMsg.setConditionValue("locNum01", rsSet.getString("LOC_NUM"));

            PROS_TRTY_ROLE_ASGTMsgArray inRoleTMsgAry = (PROS_TRTY_ROLE_ASGTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inRoleTMsg);

            if (inRoleTMsgAry.length() == 0) {
                return;
            }
            // set Update FlageData
            List<PROS_TRTY_ROLE_ASGTMsg> acctTrtyRoleAsgList = new ArrayList<PROS_TRTY_ROLE_ASGTMsg>();
            for (int i = 0; i < inRoleTMsgAry.length(); i++) {
                inRoleTMsgAry.no(i).manEntryFlg.setValue(befManEntryFlg);
                acctTrtyRoleAsgList.add(inRoleTMsgAry.no(i));
            }

            // for DataBase Update
            int updCnt = acctTrtyRoleAsgList.size();
            int rsltCnt = S21FastTBLAccessor.update(acctTrtyRoleAsgList.toArray(new PROS_TRTY_ROLE_ASGTMsg[updCnt]));
            if (rsltCnt != updCnt) {
                return;
            }

            // Update PROS_TRTY_RESRC_ASG
            PROS_TRTY_RESRC_ASGTMsg inResrcTMsg = new PROS_TRTY_RESRC_ASGTMsg();
            ZYPEZDItemValueSetter.setValue(inResrcTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inResrcTMsg.dsAcctNum, rsSet.getString("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(inResrcTMsg.locNum, rsSet.getString("LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(inResrcTMsg.dsAcctTpCd, acctTpCd);
            inResrcTMsg = (PROS_TRTY_RESRC_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inResrcTMsg);

            ZYPEZDItemValueSetter.setValue(inResrcTMsg.manEntryFlg, befManEntryFlg);

            EZDTBLAccessor.update(inResrcTMsg);
        }
    }

    private boolean validationCheck(ResultSet rsSet, String beforeCode, List<String> afterCodeList, List<String> afterGrpCodeList, boolean hasAfter) throws SQLException {
        boolean isError = false;
        // 2.1 Mandatory Check
        if (!ZYPCommonFunc.hasValue(rsSet.getString("LOC_NUM"))) {
            setErrorMsg(rsSet, ZZZM9025E, "Location");
            isError = true;
            return isError;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("DS_ACCT_NUM"))) {
            setErrorMsg(rsSet, ZZZM9025E, "Account Number");
            isError = true;
            return isError;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("TRTY_GRP_TP_CD"))) {
            setErrorMsg(rsSet, ZZZM9025E, "LOB");
            isError = true;
            return isError;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("ACCT_TRTY_ORG_NM"))) {
            setErrorMsg(rsSet, ZZZM9025E, "(Uploadable)Writer Territory Name");
            isError = true;
            return isError;
        }

        // 2.2 Duplicate Check
        Map<String, Object> dupChkParam = new HashMap<String, Object>();
        List<BigDecimal> dtlPk = null;
        dupChkParam.put("glblCmpyCd", this.glblCmpyCd);
        dupChkParam.put("upldCsvRqstPk", rsSet.getBigDecimal("UPLD_CSV_RQST_PK"));
        dupChkParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
        dupChkParam.put("locNum", rsSet.getString("LOC_NUM"));
        dupChkParam.put("trtyGrpTpCd", rsSet.getString("TRTY_GRP_TP_CD"));
        dtlPk = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("csvDupChk", dupChkParam);

        if (dtlPk.size() > 1) {
            setErrorMsg(rsSet, NMAM8404E);
            isError = true;
            return isError;
        }

        // 2.3 Master Check
        // 2.3.1 Acct Check
        String acctTpCd = null;
        Map<String, Object> acctChkParam = new HashMap<String, Object>();
        acctChkParam.put("glblCmpyCd", this.glblCmpyCd);
        acctChkParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
        acctChkParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
        acctChkParam.put("rgtnStsCdP01", RGTN_STS.PENDING_COMPLETION);
        Map<String, String> acctMap = null;

        acctMap = (Map<String, String>) this.ssmBatchClient.queryObject("getAcct", acctChkParam);

        if (acctMap == null || acctMap.isEmpty()) {
            setErrorMsg(rsSet, NMAM8121E, "Account");
            isError = true;
            return isError;
        }

        // 2.3.2 Loc Check
        Map<String, Object> locChkParam = new HashMap<String, Object>();
        locChkParam.put("glblCmpyCd", this.glblCmpyCd);
        locChkParam.put("locNum", rsSet.getString("LOC_NUM"));
        locChkParam.put("procDt", this.procDt);
        locChkParam.put("maxDt", MAX_DT);
        locChkParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
        BigDecimal lokPk = (BigDecimal) this.ssmBatchClient.queryObject("getLoc", locChkParam);

        if (!ZYPCommonFunc.hasValue(lokPk)) {
            setErrorMsg(rsSet, NMAM8121E, "Location");
            isError = true;
            return isError;
        }

        // 2.3.3 Acct and Loc Check
        Map<String, Object> acctLocChkParam = new HashMap<String, Object>();
        acctLocChkParam.put("glblCmpyCd", this.glblCmpyCd);
        acctLocChkParam.put("locNum", rsSet.getString("LOC_NUM"));
        acctLocChkParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
        acctLocChkParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
        acctLocChkParam.put("rgtnStsCdP01", RGTN_STS.PENDING_COMPLETION);
        acctLocChkParam.put("procDt", this.procDt);
        acctLocChkParam.put("maxDt", MAX_DT);

        BigDecimal acctLokPk = (BigDecimal) this.ssmBatchClient.queryObject("getAcctLoc", acctLocChkParam);

        if (!ZYPCommonFunc.hasValue(acctLokPk)) {
            setErrorMsg(rsSet, NMAM8557E);
            isError = true;
            return isError;
        }

        // 2.3.4 LOB Check
        Map<String, Object> lobChkParam = new HashMap<String, Object>();
        lobChkParam.put("glblCmpyCd", this.glblCmpyCd);
        lobChkParam.put("trtyGrpTpCd", rsSet.getString("TRTY_GRP_TP_CD"));
        String lobPk = (String) this.ssmBatchClient.queryObject("lobChk", lobChkParam);

        if (!LOB_PPS.equals(lobPk) && !LOB_LFS.equals(lobPk) && !LOB_IS.equals(lobPk)) {
            setErrorMsg(rsSet, NMAM0071E, "LOB");
            isError = true;
            return isError;
        }

        // 2.4 Before Check
        // 2.4.1 DS_ORG_UNIT Date Check
        if (ZYPCommonFunc.hasValue(rsSet.getString("ACCT_TRTY_ORG_CD"))) {
            Map<String, Object> dorChkParam = new HashMap<String, Object>();
            dorChkParam.put("glblCmpyCd", this.glblCmpyCd);
            dorChkParam.put("orgCd", beforeCode);
            dorChkParam.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
            dorChkParam.put("gnrnTpCd2", GNRN_TP.CURRENT);
            dorChkParam.put("gnrnTpCd3", GNRN_TP.FUTURE);
            Map<String, String> trtyDtMap = (Map<String, String>) this.ssmBatchClient.queryObject("getOrgUnitDt", dorChkParam);

            if (trtyDtMap == null) {
                setErrorMsg(rsSet, NMAM8121E, "Territory");
                isError = true;
                return isError;
            }

            if (ZYPDateUtil.compare(trtyDtMap.get("EFF_FROM_DT"), this.procDt) > 0 || ZYPDateUtil.compare(getMaxDt(trtyDtMap.get("EFF_THRU_DT")), this.procDt) < 0) {
                setErrorMsg(rsSet, NMAM8460E, "Territory");
                isError = true;
                return isError;
            }

        // 2.4.2 ACCT_TRTY_ROLE_ASG/PROS_TRTY_ROLE_ASG Check
            Map<String, Object> trtyRoleParam = new HashMap<String, Object>();
            BigDecimal trtyRolePk = null;
            trtyRoleParam.put("glblCmpyCd", this.glblCmpyCd);
            trtyRoleParam.put("locNum", rsSet.getString("LOC_NUM"));
            trtyRoleParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
            trtyRoleParam.put("orgCd", beforeCode);

            if (DS_ACCT_TP.CUSTOMER.equals(acctMap.get("DS_ACCT_TP_CD"))) {
                trtyRolePk = (BigDecimal) this.ssmBatchClient.queryObject("getAcctTrtyRole", trtyRoleParam);
            } else if (DS_ACCT_TP.PROSPECT.equals(acctMap.get("DS_ACCT_TP_CD"))) {
                trtyRolePk = (BigDecimal) this.ssmBatchClient.queryObject("getProsTrtyRole", trtyRoleParam);
            }

            if (!ZYPCommonFunc.hasValue(trtyRolePk)) {
                setErrorMsg(rsSet, NMAM8121E, "Territory visibility information");
                isError = true;
                return isError;
            }
            // 2.4.3 ACCT_TRTY_RESRC_ASG/PROS_TRTY_RESRC_ASG Check
            Map<String, Object> trtyResrcParam = new HashMap<String, Object>();
            String trty = null;
            trtyResrcParam.put("glblCmpyCd", this.glblCmpyCd);
            trtyResrcParam.put("locNum", rsSet.getString("LOC_NUM"));
            trtyResrcParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
            trtyResrcParam.put("dsAcctTpCd", acctMap.get("DS_ACCT_TP_CD"));

            if (DS_ACCT_TP.CUSTOMER.equals(acctMap.get("DS_ACCT_TP_CD"))) {
                trty = (String) this.ssmBatchClient.queryObject("getAcctTrtyResrc", trtyResrcParam);
            } else if (DS_ACCT_TP.PROSPECT.equals(acctMap.get("DS_ACCT_TP_CD"))) {
                trty = (String) this.ssmBatchClient.queryObject("getProsTrtyResrc", trtyResrcParam);
            }

            if (!ZYPCommonFunc.hasValue(trty)) {
                setErrorMsg(rsSet, NMAM8121E, "Territory visibility information");
                isError = true;
                return isError;
            }
        }
        // 2.5 After Check
        // 2.5.1 Get After Code
        if (ORG_NM_DEL.equals(afterCodeList.get(0))) {
            afterCodeList.add(ORG_NM_DEL);
        } else {
            Map<String, Object> dtChkParam = new HashMap<String, Object>();
            dtChkParam.put("glblCmpyCd", this.glblCmpyCd);
            dtChkParam.put("orgNm", rsSet.getString("ACCT_TRTY_ORG_NM"));
            dtChkParam.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
            dtChkParam.put("gnrnTpCd2", GNRN_TP.CURRENT);
            dtChkParam.put("gnrnTpCd3", GNRN_TP.FUTURE);
            Map<String, String> trtyMap = (Map<String, String>) this.ssmBatchClient.queryObject("getAftOrgUnitDt", dtChkParam);

            // START 2023/05/23 S.Fujita [QC#61533 ,MOD]
            // if (!ZYPCommonFunc.hasValue(trtyMap.toString())) {
            if (trtyMap == null || trtyMap.isEmpty()) {
            // END 2023/05/23 S.Fujita [QC#61533 ,MOD]
                setErrorMsg(rsSet, NMAM8121E, "Territory");
                isError = true;
                return isError;
            }

            if (ZYPDateUtil.compare(trtyMap.get("EFF_FROM_DT"), this.procDt) > 0 || ZYPDateUtil.compare(getMaxDt(trtyMap.get("EFF_THRU_DT")), this.procDt) < 0) {
                setErrorMsg(rsSet, NMAM8460E, "Territory");
                isError = true;
                return isError;
            }

            afterCodeList.add(trtyMap.get("ORG_CD"));
            afterGrpCodeList.add(trtyMap.get("TRTY_GRP_TP_CD"));
        }
        // 2.6 Before and After Check
        if (ZYPCommonFunc.hasValue(afterCodeList.get(1))) {
            if (afterCodeList.get(1).equals(beforeCode)) {
                setErrorMsg(rsSet, NMAM8407E);
                isError = true;
                return isError;
            }
            if (ORG_NM_DEL.equals(afterCodeList.get(1)) && !ZYPCommonFunc.hasValue(beforeCode)) {
                setErrorMsg(rsSet, ZZZM9025E, "Writer Territory ID");
                isError = true;
                return isError;
            }
        }

        // 2.7 Territory and Resource Check
        if (!ORG_NM_DEL.equals(afterCodeList.get(1))) {
            // START 2023/05/23 S.Fujita [QC#61533 ,ADD]
            int no = 0;
            if (LOB_PPS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
                no = TRTY_GRP_TP_CD_PPS;
            } else if (LOB_LFS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
                no = TRTY_GRP_TP_CD_LFS;
            } else if (LOB_IS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
                no = TRTY_GRP_TP_CD_IS;
            }
            // END 2023/05/23 S.Fujita [QC#61533 ,ADD]

            Map<String, Object> getLineParam = new HashMap<String, Object>();
            getLineParam.put("glblCmpyCd", this.glblCmpyCd);
            getLineParam.put("dsAcctTp", acctMap.get("DS_ACCT_TP_CD"));
            // START 2023/05/23 S.Fujita [QC#61533 ,MOD]
            // getLineParam.put("asgTrtyAttrb", SLS_CD);
            getLineParam.put("asgTrtyAttrb", no);
            // END 2023/05/23 S.Fujita [QC#61533 ,MOD]
            getLineParam.put("trtyGrpTp", afterGrpCodeList.get(0));
            String lineRank = (String) this.ssmBatchClient.queryObject("getLineBizRankNum", getLineParam);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("glblCmpyCd", getGlobalCompanyCode());
            params.put("trtyCd", afterCodeList.get(1));
            params.put("procDt", this.procDt);
            params.put("maxDt", MAX_DT);
            params.put("gnrnTp_Current", GNRN_TP.CURRENT);
            params.put("flgOnY", ZYPConstant.FLG_ON_Y);

            if (ZYPCommonFunc.hasValue(lineRank)) {
                params.put("salesRepRoleFlg", ZYPConstant.FLG_ON_Y);
            }

            int count = (Integer) this.ssmBatchClient.queryObject("checkActiveResorce", params);

            if (count <= 0) {
                if (ZYPCommonFunc.hasValue(lineRank)) {
                    setErrorMsg(rsSet, NMAM8545E);
                    isError = true;
                    return isError;
                } else {
                    setErrorMsg(rsSet, NMAM8546E);
                    isError = true;
                    return isError;
                }
            }
        }
        return isError;
    }

    private void setErrorMsg(ResultSet rsSet, String msgId, String msgStr) throws SQLException {

        String msgTxt = S21MessageFunc.clspGetMessage(msgId, new String[] {msgStr});
        if (this.onlineMsg.length() > 0) {
            this.onlineMsg.append(",");
        }
        this.onlineMsg.append(msgTxt);

            messenger.addMessageForRecord(rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"), msgId, msgStr);

    }

    private void setErrorMsg(ResultSet rsSet, String msgId) throws SQLException {

        String msgTxt = S21MessageFunc.clspGetMessage(msgId);
        if (this.onlineMsg.length() > 0) {
            this.onlineMsg.append(",");
        }
        this.onlineMsg.append(msgTxt);

            messenger.addMessageForRecord(rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"), msgId, null);

    }

    private void setInfoMsg(ResultSet rsSet, String msgId) throws SQLException {
        String msgTxt = S21MessageFunc.clspGetMessage(msgId);
        if (this.onlineInfoMsg.length() > 0) {
            this.onlineInfoMsg.append(",");
        }
        this.onlineInfoMsg.append(msgTxt);
    }

    private List<Map<String, Object>> getTrtyInfo(String orgCd, boolean isSlsRep) {
        Map<String, Object> getInsertCustParam = new HashMap<String, Object>();
        getInsertCustParam.put("glblCmpyCd", this.glblCmpyCd);
        getInsertCustParam.put("orgCd", orgCd);
        getInsertCustParam.put("isSlsRepFlg", isSlsRep);
        getInsertCustParam.put("procDt", this.procDt);
        getInsertCustParam.put("maxDt", MAX_DT);
        getInsertCustParam.put("gnrnTpCd2", GNRN_TP.CURRENT);
        getInsertCustParam.put("firstOrgCd", SLS_CD);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getInsertData", getInsertCustParam);
    }

    private ACCT_TRTY_ROLE_ASGTMsg setInsertMsgForNewMode(Map<String, Object> acctMap, Map<String, Object> insertRoleMap, String befManEntryFlg, String attrbCd, String orgCd) {
        ACCT_TRTY_ROLE_ASGTMsg insertTMsg = new ACCT_TRTY_ROLE_ASGTMsg();
        BigDecimal trtyRulePk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ACCT_TRTY_ROLE_ASG_SQ);
        ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.acctTrtyRoleAsgPk, trtyRulePk);
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNum, (String) acctMap.get("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.locNum, (String) acctMap.get("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.orgCd, orgCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizRoleTpCd, (String) acctMap.get("LINE_BIZ_ROLE_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNm, (String) acctMap.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.billToCustCd, (String) acctMap.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.shipToCustCd, (String) acctMap.get("SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctTpCd, (String) acctMap.get("DS_ACCT_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctClsCd, (String) acctMap.get("DS_ACCT_CLS_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.firstDsAcctGrpTpCd, (String) acctMap.get("FIRST_DS_ACCT_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.scdDsAcctGrpTpCd, (String) acctMap.get("SCD_DS_ACCT_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.thirdDsAcctGrpTpCd, (String) acctMap.get("THIRD_DS_ACCT_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.frthDsAcctGrpTpCd, (String) acctMap.get("FRTH_DS_ACCT_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.fifthDsAcctGrpTpCd, (String) acctMap.get("FIFTH_DS_ACCT_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.indyTpCd, (String) acctMap.get("INDY_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.firstLineAddr, (String) acctMap.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.scdLineAddr, (String) acctMap.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.thirdLineAddr, (String) acctMap.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.frthLineAddr, (String) acctMap.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.ctyAddr, (String) acctMap.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.stCd, (String) acctMap.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.provNm, (String) acctMap.get("PROV_NM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.cntyNm, (String) acctMap.get("CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.postCd, (String) acctMap.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.manEntryFlg, befManEntryFlg);
        insertTMsg.orgRankNum.clear();
        ZYPEZDItemValueSetter.setValue(insertTMsg.orgNm, (String) insertRoleMap.get("ORG_NM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.trtyGrpTpCd, (String) insertRoleMap.get("TRTY_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.tocCd, (String) insertRoleMap.get("TOC_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.psnCd, (String) insertRoleMap.get("PSN_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.trtyTpCd, (String) insertRoleMap.get("TRTY_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.nonSlsRepFlg, (String) insertRoleMap.get("NON_SLS_REP_FLG"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.asgTrtyAttrbCd, attrbCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizTpCd, (String) acctMap.get("LINE_BIZ_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsCustSicCd, (String) acctMap.get("DS_CUST_SIC_CD"));

        return insertTMsg;
    }

    private PROS_TRTY_ROLE_ASGTMsg setInsertProsMsgForNewMode(Map<String, Object> acctMap, Map<String, Object> insertRoleMap, String befManEntryFlg, String attrbCd, String orgCd) {
        PROS_TRTY_ROLE_ASGTMsg insertTMsg = new PROS_TRTY_ROLE_ASGTMsg();
        BigDecimal trtyRulePk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ACCT_TRTY_ROLE_ASG_SQ);
        ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.acctTrtyRoleAsgPk, trtyRulePk);
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNum, (String) acctMap.get("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.locNum, (String) acctMap.get("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.orgCd, orgCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizRoleTpCd, (String) acctMap.get("LINE_BIZ_ROLE_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNm, (String) acctMap.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.billToCustCd, (String) acctMap.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.shipToCustCd, (String) acctMap.get("SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctTpCd, (String) acctMap.get("DS_ACCT_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctClsCd, (String) acctMap.get("DS_ACCT_CLS_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.firstDsAcctGrpTpCd, (String) acctMap.get("FIRST_DS_ACCT_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.scdDsAcctGrpTpCd, (String) acctMap.get("SCD_DS_ACCT_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.thirdDsAcctGrpTpCd, (String) acctMap.get("THIRD_DS_ACCT_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.frthDsAcctGrpTpCd, (String) acctMap.get("FRTH_DS_ACCT_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.fifthDsAcctGrpTpCd, (String) acctMap.get("FIFTH_DS_ACCT_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.indyTpCd, (String) acctMap.get("INDY_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.firstLineAddr, (String) acctMap.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.scdLineAddr, (String) acctMap.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.thirdLineAddr, (String) acctMap.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.frthLineAddr, (String) acctMap.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.ctyAddr, (String) acctMap.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.stCd, (String) acctMap.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.provNm, (String) acctMap.get("PROV_NM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.cntyNm, (String) acctMap.get("CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.postCd, (String) acctMap.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.manEntryFlg, befManEntryFlg);
        insertTMsg.orgRankNum.clear();
        ZYPEZDItemValueSetter.setValue(insertTMsg.orgNm, (String) insertRoleMap.get("ORG_NM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.trtyGrpTpCd, (String) insertRoleMap.get("TRTY_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.tocCd, (String) insertRoleMap.get("TOC_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.psnCd, (String) insertRoleMap.get("PSN_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.trtyTpCd, (String) insertRoleMap.get("TRTY_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.nonSlsRepFlg, (String) insertRoleMap.get("NON_SLS_REP_FLG"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.asgTrtyAttrbCd, attrbCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizTpCd, (String) acctMap.get("LINE_BIZ_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsCustSicCd, (String) acctMap.get("DS_CUST_SIC_CD"));

        return insertTMsg;
    }

    private ACCT_TRTY_ROLE_ASGTMsg setInsertMsgForCust(Map<String, Object> acctRoleMap, Map<String, Object> insertRoleMap, String orgCd, String befManEntryFlg) {
        ACCT_TRTY_ROLE_ASGTMsg insertTMsg = new ACCT_TRTY_ROLE_ASGTMsg();
        BigDecimal trtyRulePk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ACCT_TRTY_ROLE_ASG_SQ);
        ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.acctTrtyRoleAsgPk, trtyRulePk);
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNum, (String) acctRoleMap.get("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.locNum, (String) acctRoleMap.get("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.orgCd, orgCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizRoleTpCd, (String) acctRoleMap.get("LINE_BIZ_ROLE_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNm, (String) acctRoleMap.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.billToCustCd, (String) acctRoleMap.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.shipToCustCd, (String) acctRoleMap.get("SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctTpCd, (String) acctRoleMap.get("DS_ACCT_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctClsCd, (String) acctRoleMap.get("DS_ACCT_CLS_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.firstDsAcctGrpTpCd, (String) acctRoleMap.get("FIRST_DS_ACCT_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.scdDsAcctGrpTpCd, (String) acctRoleMap.get("SCD_DS_ACCT_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.thirdDsAcctGrpTpCd, (String) acctRoleMap.get("THIRD_DS_ACCT_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.frthDsAcctGrpTpCd, (String) acctRoleMap.get("FRTH_DS_ACCT_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.fifthDsAcctGrpTpCd, (String) acctRoleMap.get("FIFTH_DS_ACCT_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.indyTpCd, (String) acctRoleMap.get("INDY_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.firstLineAddr, (String) acctRoleMap.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.scdLineAddr, (String) acctRoleMap.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.thirdLineAddr, (String) acctRoleMap.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.frthLineAddr, (String) acctRoleMap.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.ctyAddr, (String) acctRoleMap.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.stCd, (String) acctRoleMap.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.provNm, (String) acctRoleMap.get("PROV_NM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.cntyNm, (String) acctRoleMap.get("CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.postCd, (String) acctRoleMap.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.orgNm, (String) insertRoleMap.get("ORG_NM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.trtyGrpTpCd, (String) insertRoleMap.get("TRTY_GRP_TP_CD"));
        insertTMsg.orgRankNum.clear();
        ZYPEZDItemValueSetter.setValue(insertTMsg.tocCd, (String) insertRoleMap.get("TOC_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.psnCd, (String) insertRoleMap.get("PSN_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.manEntryFlg, befManEntryFlg);
        ZYPEZDItemValueSetter.setValue(insertTMsg.trtyTpCd, (String) insertRoleMap.get("TRTY_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.nonSlsRepFlg, (String) insertRoleMap.get("NON_SLS_REP_FLG"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.asgTrtyAttrbCd, (String) acctRoleMap.get("ASG_TRTY_ATTRB_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizTpCd, (String) acctRoleMap.get("LINE_BIZ_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsCustSicCd, (String) acctRoleMap.get("DS_CUST_SIC_CD"));

        return insertTMsg;
    }

    private PROS_TRTY_ROLE_ASGTMsg setInsertMsgForProc(Map<String, Object> procRoleMap, Map<String, Object> insertRoleMap, String orgCd, String befManEntryFlg) {
        PROS_TRTY_ROLE_ASGTMsg insertTMsg = new PROS_TRTY_ROLE_ASGTMsg();
        BigDecimal trtyRulePk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ACCT_TRTY_ROLE_ASG_SQ);
        ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.acctTrtyRoleAsgPk, trtyRulePk);
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNum, (String) procRoleMap.get("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.locNum, (String) procRoleMap.get("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.orgCd, orgCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizRoleTpCd, (String) procRoleMap.get("LINE_BIZ_ROLE_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNm, (String) procRoleMap.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.billToCustCd, (String) procRoleMap.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.shipToCustCd, (String) procRoleMap.get("SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctTpCd, (String) procRoleMap.get("DS_ACCT_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctClsCd, (String) procRoleMap.get("DS_ACCT_CLS_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.firstDsAcctGrpTpCd, (String) procRoleMap.get("FIRST_DS_ACCT_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.scdDsAcctGrpTpCd, (String) procRoleMap.get("SCD_DS_ACCT_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.thirdDsAcctGrpTpCd, (String) procRoleMap.get("THIRD_DS_ACCT_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.frthDsAcctGrpTpCd, (String) procRoleMap.get("FRTH_DS_ACCT_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.fifthDsAcctGrpTpCd, (String) procRoleMap.get("FIFTH_DS_ACCT_GRP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.indyTpCd, (String) procRoleMap.get("INDY_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.firstLineAddr, (String) procRoleMap.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.scdLineAddr, (String) procRoleMap.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.thirdLineAddr, (String) procRoleMap.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.frthLineAddr, (String) procRoleMap.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.ctyAddr, (String) procRoleMap.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.stCd, (String) procRoleMap.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.provNm, (String) procRoleMap.get("PROV_NM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.cntyNm, (String) procRoleMap.get("CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.postCd, (String) procRoleMap.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.orgNm, (String) insertRoleMap.get("ORG_NM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.trtyGrpTpCd, (String) insertRoleMap.get("TRTY_GRP_TP_CD"));
        insertTMsg.orgRankNum.clear();
        ZYPEZDItemValueSetter.setValue(insertTMsg.tocCd, (String) insertRoleMap.get("TOC_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.psnCd, (String) insertRoleMap.get("PSN_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.manEntryFlg, befManEntryFlg);
        ZYPEZDItemValueSetter.setValue(insertTMsg.trtyTpCd, (String) insertRoleMap.get("TRTY_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.nonSlsRepFlg, (String) insertRoleMap.get("NON_SLS_REP_FLG"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.asgTrtyAttrbCd, (String) procRoleMap.get("ASG_TRTY_ATTRB_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizTpCd, (String) procRoleMap.get("LINE_BIZ_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsCustSicCd, (String) procRoleMap.get("DS_CUST_SIC_CD"));

        return insertTMsg;
    }

    private void setAcctTrtyResrcAsgPrmForCust(ACCT_TRTY_RESRC_ASGTMsg inTMsg, String afterCode, String psnCd, String tocCd, String befManEntryFlg, ResultSet rsSet) throws SQLException {
        if (ORG_NM_DEL.equals(afterCode)) {
            // START 2023/05/23 S.Fujita [QC#61533 ,MOD]
            // inTMsg.acctTrtyOrgCd_01.clear();
            // inTMsg.acctTrtyPsnCd_01.clear();
            // inTMsg.acctTrtyTocCd_01.clear();
            if (LOB_PPS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
                inTMsg.acctTrtyOrgCd_05.clear();
                inTMsg.acctTrtyPsnCd_05.clear();
                inTMsg.acctTrtyTocCd_05.clear();
            } else if (LOB_LFS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
                inTMsg.acctTrtyOrgCd_07.clear();
                inTMsg.acctTrtyPsnCd_07.clear();
                inTMsg.acctTrtyTocCd_07.clear();
            } else if (LOB_IS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
                inTMsg.acctTrtyOrgCd_09.clear();
                inTMsg.acctTrtyPsnCd_09.clear();
                inTMsg.acctTrtyTocCd_09.clear();
            }
        // } else if (LOB_PPS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
        } else{
            if (LOB_PPS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_05, afterCode);
                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_05, psnCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_05, tocCd);
            } else if (LOB_LFS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_07, afterCode);
                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_07, psnCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_07, tocCd);
            } else if (LOB_IS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_09, afterCode);
                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_09, psnCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_09, tocCd);
            }
            // END 2023/05/23 S.Fujita [QC#61533 ,MOD]
        }

        ZYPEZDItemValueSetter.setValue(inTMsg.manEntryFlg, befManEntryFlg);
    }

    private void setAcctTrtyResrcAsgPrmForPros(PROS_TRTY_RESRC_ASGTMsg inTMsg, String afterCode, String psnCd, String tocCd, String befManEntryFlg, ResultSet rsSet) throws SQLException {
        if (ORG_NM_DEL.equals(afterCode)) {
            // START 2023/05/23 S.Fujita [QC#61533 ,MOD]
            // inTMsg.acctTrtyOrgCd_01.clear();
            // inTMsg.acctTrtyPsnCd_01.clear();
            // inTMsg.acctTrtyTocCd_01.clear();
            if (LOB_PPS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
                inTMsg.acctTrtyOrgCd_05.clear();
                inTMsg.acctTrtyPsnCd_05.clear();
                inTMsg.acctTrtyTocCd_05.clear();
            } else if (LOB_LFS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
                inTMsg.acctTrtyOrgCd_07.clear();
                inTMsg.acctTrtyPsnCd_07.clear();
                inTMsg.acctTrtyTocCd_07.clear();
            } else if (LOB_IS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
                inTMsg.acctTrtyOrgCd_09.clear();
                inTMsg.acctTrtyPsnCd_09.clear();
                inTMsg.acctTrtyTocCd_09.clear();
            }
        // } else if (LOB_PPS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
        } else{
            if (LOB_PPS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_05, afterCode);
                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_05, psnCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_05, tocCd);
            } else if (LOB_LFS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_07, afterCode);
                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_07, psnCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_07, tocCd);
            } else if (LOB_IS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_09, afterCode);
                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_09, psnCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_09, tocCd);
            }
            // END 2023/05/23 S.Fujita [QC#61533 ,MOD]
        }
        ZYPEZDItemValueSetter.setValue(inTMsg.manEntryFlg, befManEntryFlg);
    }

    private void updateHeader(boolean isError, BigDecimal hdrPk, String upldCsvId, ART10FMsg reqMsg) throws SQLException {
        String msg = null;

        ACCT_TRTY_RESRC_RQST_HDRTMsg inTMsg = new ACCT_TRTY_RESRC_RQST_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyResrcRqstHdrPk, hdrPk);
        ZYPEZDItemValueSetter.setValue(inTMsg.rqstUsrId, upldCsvId);
        ZYPEZDItemValueSetter.setValue(inTMsg.rqstCratSysTpCd, RQST_CRAT_SYS_TP.CSV_UPLOAD);
        ZYPEZDItemValueSetter.setValue(inTMsg.rqstCratTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));

        if (!isError) {
            msg = createResultMessageArg(0, this.currentUpdateCount, 0);
            this.messenger.addMessageForFile(NYXM0001I, msg);
            // START 2023/05/23 S.Fujita [QC#61533 ,ADD]
            this.messenger.uploadMessage();
            // END 2023/05/23 S.Fujita [QC#61533 ,ADD]
            ZYPEZDItemValueSetter.setValue(inTMsg.rqstRsltTpCd, RQST_RSLT_TP.COMPLETED);
            ZYPEZDItemValueSetter.setValue(inTMsg.rqstRsltCmntTxt, createResultMsg(NYXM0001I, msg));

            this.batchHelper.updateProcessStatus(reqMsg, REQUEST_STATUS.NOMAL_END);
        } else {
            ZYPEZDItemValueSetter.setValue(inTMsg.rqstRsltTpCd, RQST_RSLT_TP.COMPLETED_INCLUDING_ERROR);
            msg = createResultMessageArg(0, this.currentUpdateCount, this.currentErrorCount);
            this.messenger.addMessageForFile(NYXM0002E, msg);
            ZYPEZDItemValueSetter.setValue(inTMsg.rqstRsltCmntTxt, createResultMsg(NYXM0002E, msg));

            this.termCd = TERM_CD.WARNING_END;
            this.messenger.uploadMessage();
            this.batchHelper.updateProcessStatus(reqMsg, REQUEST_STATUS.WARING_END);
        }

        this.currentUpdateCount = 0;
        this.currentErrorCount = 0;

        EZDTBLAccessor.insert(inTMsg);
    }

    private static String createResultMsg(String msgId, String msgTxt) {
        StringBuilder sb = new StringBuilder();

        sb.append(msgId);
        sb.append(" ");
        sb.append(msgTxt);

        return sb.toString();
    }

    private ACCT_TRTY_RESRC_RQST_DTLTMsg createUpdateDetail(ResultSet rsSet, BigDecimal hdrPk, String beforeCode, String afterCode, String acctTpCd, String rsnCmntTxt, boolean isError) throws SQLException {

        String befManEntryFlg = getManualEntryFlag(rsSet);
        ACCT_TRTY_RESRC_RQST_DTLTMsg inTMsg = new ACCT_TRTY_RESRC_RQST_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        BigDecimal dtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ACCT_TRTY_RESRC_RQST_DTL_SQ);

        ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyResrcRqstDtlPk, dtlPk);
        ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyResrcRqstHdrPk, hdrPk);
        ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctNum, rsSet.getString("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctNm, rsSet.getString("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctTpCd, acctTpCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.locNum, rsSet.getString("LOC_NUM"));

        if (DS_ACCT_TP.PROSPECT.equals(acctTpCd)) {
            PROS_PTY_LOC_WRKTMsg prosPtyLocWrkKey = new PROS_PTY_LOC_WRKTMsg();
            prosPtyLocWrkKey.setSQLID("001");
            prosPtyLocWrkKey.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            prosPtyLocWrkKey.setConditionValue("locNum01", rsSet.getString("LOC_NUM"));
            prosPtyLocWrkKey.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
            PROS_PTY_LOC_WRKTMsgArray prosPtyLocWrkAry = (PROS_PTY_LOC_WRKTMsgArray) EZDTBLAccessor.findByCondition(prosPtyLocWrkKey);

            if (prosPtyLocWrkAry.getValidCount() > 0) {
                PROS_PTY_LOC_WRKTMsg prosPtyLocWrkTmsg = (PROS_PTY_LOC_WRKTMsg) prosPtyLocWrkAry.get(0);
                ZYPEZDItemValueSetter.setValue(inTMsg.firstLineAddr, prosPtyLocWrkTmsg.firstLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(inTMsg.scdLineAddr, prosPtyLocWrkTmsg.scdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(inTMsg.thirdLineAddr, prosPtyLocWrkTmsg.thirdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(inTMsg.frthLineAddr, prosPtyLocWrkTmsg.frthLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(inTMsg.ctyAddr, prosPtyLocWrkTmsg.ctyAddr.getValue());
                ZYPEZDItemValueSetter.setValue(inTMsg.postCd, prosPtyLocWrkTmsg.postCd.getValue());
                ZYPEZDItemValueSetter.setValue(inTMsg.stCd, prosPtyLocWrkTmsg.stCd.getValue());
            }
        } else {
            PTY_LOC_WRKTMsg ptyLocWrkKey = new PTY_LOC_WRKTMsg();
            ptyLocWrkKey.setSQLID("013");
            ptyLocWrkKey.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            ptyLocWrkKey.setConditionValue("locNum01", rsSet.getString("LOC_NUM"));
            ptyLocWrkKey.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
            PTY_LOC_WRKTMsgArray ptyLocWrkAry = (PTY_LOC_WRKTMsgArray) EZDTBLAccessor.findByCondition(ptyLocWrkKey);

            if (ptyLocWrkAry.getValidCount() > 0) {
                PTY_LOC_WRKTMsg ptyLocWrkTmsg = (PTY_LOC_WRKTMsg) ptyLocWrkAry.get(0);
                ZYPEZDItemValueSetter.setValue(inTMsg.firstLineAddr, ptyLocWrkTmsg.firstLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(inTMsg.scdLineAddr, ptyLocWrkTmsg.scdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(inTMsg.thirdLineAddr, ptyLocWrkTmsg.thirdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(inTMsg.frthLineAddr, ptyLocWrkTmsg.frthLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(inTMsg.ctyAddr, ptyLocWrkTmsg.ctyAddr.getValue());
                ZYPEZDItemValueSetter.setValue(inTMsg.postCd, ptyLocWrkTmsg.postCd.getValue());
                ZYPEZDItemValueSetter.setValue(inTMsg.stCd, ptyLocWrkTmsg.stCd.getValue());
            }
        }

        if (LOB_PPS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_05, beforeCode);
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_05, afterCode);
        } else if (LOB_LFS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_07, beforeCode);
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_07, afterCode);
        } else if (LOB_IS.equals(rsSet.getString("TRTY_GRP_TP_CD"))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_09, beforeCode);
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_09, afterCode);
        }

        ZYPEZDItemValueSetter.setValue(inTMsg.manEntryFlg, befManEntryFlg);
        ZYPEZDItemValueSetter.setValue(inTMsg.massUpdRsnCmntTxt, rsnCmntTxt);

        if (isError) {
            ZYPEZDItemValueSetter.setValue(inTMsg.rqstDtlRsltTpCd, RQST_DTL_RSLT_TP.ERROR);
            ZYPEZDItemValueSetter.setValue(inTMsg.rqstDtlRsltCmntTxt, this.onlineMsg.toString());
            this.totalErrCount++;
            this.currentErrorCount++;
            this.termCd = TERM_CD.WARNING_END;
        } else {
            ZYPEZDItemValueSetter.setValue(inTMsg.rqstDtlRsltTpCd, RQST_DTL_RSLT_TP.SUCCESS);
            this.totalNmlCount++;
            this.currentUpdateCount++;
            if (this.onlineInfoMsg.length() > 0) {
                ZYPEZDItemValueSetter.setValue(inTMsg.rqstDtlRsltCmntTxt, this.onlineInfoMsg.toString());
            }
        }

        EZDTBLAccessor.insert(inTMsg);

        this.onlineMsg.setLength(0);
        this.onlineInfoMsg.setLength(0);
        return inTMsg;

    }

    private String getMaxDt(String dt) {
        if (!ZYPCommonFunc.hasValue(dt)) {
            return MAX_DT;
        }
        return dt;
    }



    private static String createResultMessageArg(int insCount, int updCount, int errCount) {
        StringBuilder builder = new StringBuilder();
        if (insCount > 0) {
            builder.append(String.format(RESULT_MSG_INS, insCount));
        }
        if (updCount > 0) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(String.format(RESULT_MSG_UPD, updCount));
        }
        if (errCount > 0) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(String.format(RESULT_MSG_ERR, errCount));
        }
        return builder.toString();
    }

    private boolean existsTerritoryRule(String locNum, String orgCd, String orgStruTpCd) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("trtyRuleTpCd", TRTY_RULE_TP.LOCATION_NUMBER);
        param.put("orgStruTpCd", orgStruTpCd);
        param.put("slsDt", this.slsDt);
        param.put("trtyRuleFromValTxt", locNum);
        param.put("orgCd", orgCd);
        Integer cnt = (Integer) this.ssmBatchClient.queryObject("getCountTerritoryRule", param);
        if (cnt == null || cnt == 0) {
            return false;
        }
        return cnt > 0;
    }

    private boolean isRuleLogicAllOr(String orgCd, String orgStruTpCd) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("orgCd", orgCd);
        param.put("orgStruTpCd", orgStruTpCd);
        param.put("slsDt", this.slsDt);
        param.put("trtyRuleTpCd", TRTY_RULE_TP.LOCATION_NUMBER);
        param.put("trtyRuleLogicTpCd", TRTY_RULE_LOGIC_TP.OR);
        Integer cnt = (Integer) this.ssmBatchClient.queryObject("getCountTerritoryRuleLogicNotOR", param);
        if (cnt == null) {
            return false;
        }
        return cnt > 0;
    }

private boolean isGeoTerritory(String afterCode) {

        DS_ORG_UNITTMsg orgUnitTMsg = new DS_ORG_UNITTMsg();
        ZYPEZDItemValueSetter.setValue(orgUnitTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(orgUnitTMsg.orgCd, afterCode.toString());
        orgUnitTMsg = (DS_ORG_UNITTMsg) S21CacheTBLAccessor.findByKey(orgUnitTMsg);

        if (orgUnitTMsg != null) {

            TRTY_TPTMsg trtyTpTMsg = new TRTY_TPTMsg();
            ZYPEZDItemValueSetter.setValue(trtyTpTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(trtyTpTMsg.trtyTpCd, orgUnitTMsg.trtyTpCd);
            trtyTpTMsg = (TRTY_TPTMsg) S21CacheTBLAccessor.findByKey(trtyTpTMsg);

            if (trtyTpTMsg != null) {
                return ZYPCommonFunc.hasValue(trtyTpTMsg.geoTrtyFlg) && ZYPConstant.FLG_ON_Y.equals(trtyTpTMsg.geoTrtyFlg.getValue());
            }
        }

        return false;
    }

    private boolean isExistsPostCdInTrtyRule(String afterCode, String postCd) {

        DS_ORG_UNITTMsg orgUnitTMsg = new DS_ORG_UNITTMsg();
        ZYPEZDItemValueSetter.setValue(orgUnitTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(orgUnitTMsg.orgCd, afterCode.toString());
        orgUnitTMsg = (DS_ORG_UNITTMsg) S21CacheTBLAccessor.findByKey(orgUnitTMsg);

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("slsDt", this.slsDt);
        param.put("orgCd", orgUnitTMsg.orgCd.getValue());
        param.put("orgStruTpCd", orgUnitTMsg.orgStruTpCd.getValue());
        param.put("trtyRuleTpCd", TRTY_RULE_TP.POSTAL_CODE);
        param.put("trtyRuleLogicTpCd", TRTY_RULE_LOGIC_TP.OR);
        param.put("trtyRuleOprdTpCd_Eq", TRTY_RULE_OPRD_TP.EQUAL);
        param.put("trtyRuleOprdTpCd_Bw", TRTY_RULE_OPRD_TP.BETWEEN);
        param.put("postCd", postCd);

        Integer overlapCnt = (Integer) this.ssmBatchClient.queryObject("getOverlapPostalCode", param);

        return overlapCnt > 0;
    }

    private void insertTrtyRuleForPostalCode(String afterCode, String postCd) {

        TRTY_RULETMsg tMsg = new TRTY_RULETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRulePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TRTY_RULE_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.orgCd, afterCode.toString());
        ZYPEZDItemValueSetter.setValue(tMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleTpCd, TRTY_RULE_TP.POSTAL_CODE);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleFromValTxt, postCd);
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleOprdTpCd, TRTY_RULE_OPRD_TP.EQUAL);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleLogicTpCd, TRTY_RULE_LOGIC_TP.OR);

        S21FastTBLAccessor.insert(tMsg);
    }
}
