/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB320001;

import static com.canon.cusa.s21.batch.NMA.NMAB320001.constant.NMAB320001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB320001.constant.NMAB320001Constant.DS_ACCT_NM_SUBSTR_LG;
import static com.canon.cusa.s21.batch.NMA.NMAB320001.constant.NMAB320001Constant.FIRST_LINE_ADDR_SUBSTR_LG;
import static com.canon.cusa.s21.batch.NMA.NMAB320001.constant.NMAB320001Constant.MATCH_CRIT_TXT_DUNS;
import static com.canon.cusa.s21.batch.NMA.NMAB320001.constant.NMAB320001Constant.MATCH_CRIT_TXT_EXACT;
import static com.canon.cusa.s21.batch.NMA.NMAB320001.constant.NMAB320001Constant.MATCH_CRIT_TXT_GLN;
import static com.canon.cusa.s21.batch.NMA.NMAB320001.constant.NMAB320001Constant.MATCH_CRIT_TXT_PARTIAL;
import static com.canon.cusa.s21.batch.NMA.NMAB320001.constant.NMAB320001Constant.MAX_MATCH_LG;
import static com.canon.cusa.s21.batch.NMA.NMAB320001.constant.NMAB320001Constant.NMAM0176E;
import static com.canon.cusa.s21.batch.NMA.NMAB320001.constant.NMAB320001Constant.NMAM0177E;
import static com.canon.cusa.s21.batch.NMA.NMAB320001.constant.NMAB320001Constant.NMAM8132E;
import static com.canon.cusa.s21.batch.NMA.NMAB320001.constant.NMAB320001Constant.PROCEDURE_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB320001.constant.NMAB320001Constant.PROS_TO_TRTY_BATCH_MODE;
import static com.canon.cusa.s21.batch.NMA.NMAB320001.constant.NMAB320001Constant.QUOTE;
import static com.canon.cusa.s21.batch.NMA.NMAB320001.constant.NMAB320001Constant.VAR_CHAR_CONST;
import static com.canon.cusa.s21.batch.NMA.NMAB320001.constant.NMAB320001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NMA.NMAB320001.constant.NMAB320001Constant.ZZZM9026E;

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
import business.db.MKTG_MAPTMsg;
import business.db.MKTG_MAP_RQSTTMsg;
import business.parts.NMZC271001PMsg;
import business.parts.NMZC271001_territoryListBatchPMsg;

import com.canon.cusa.s21.api.NMZ.NMZC271001.NMZC271001;
import com.canon.cusa.s21.batch.NMA.NMAB320001.constant.NMAB320001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.internal.S21BatchTransactionQuery;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Marketing Data Mass Update Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/20   Fujitsu         M.Ohno          Create          N/A
 * 2016/07/22   Fujitsu         N.Sugiura       Update          QC#12258
 * 2016/09/07   SRAA            Y.Chen          Update          QC#12258
 *</pre>
 */
public class NMAB320001 extends S21BatchMain {
    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Total Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** process date time */
    private String procDt = null;

    /** Vat Char */
    private String exact = null;

    /** Vat Char */
    private String partial = null;

    /** Vat Char */
    private String duns = null;

    /** Vat Char */
    private String gln = null;

    /** Vat Char */
    private String lineSubstrLength = null;

    /** Vat Char */
    private String nmSubstrLength = null;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    // QC#12258
    /** Cache - Address Exact Match */
    private Map<String, List<Map<String, String>>> cacheAddrExactMatch = new HashMap<String, List<Map<String, String>>>();

    /** Cache - Address Partial Match */
    private Map<String, List<Map<String, String>>> cacheAddrPrtlMatch = new HashMap<String, List<Map<String, String>>>();

    /** Cache - DUNS# Match */
    private Map<String, List<Map<String, String>>> cacheDunsMatch = new HashMap<String, List<Map<String, String>>>();

    /** Cache - GLN# Match */
    private Map<String, List<Map<String, String>>> cacheGlnMatch = new HashMap<String, List<Map<String, String>>>();

    /** Cache - MKTG_MAP_PK */
    private Map<String, BigDecimal> cacheMktgMapPk = new HashMap<String, BigDecimal>();

    /** Cache - Territory Match */
    private Map<String, List<String>> cacheTrtyMatch = new HashMap<String, List<String>>();

    @Override
    protected void initRoutine() {
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

        // Check Var Char
        this.exact = ZYPCodeDataUtil.getVarCharConstValue(MATCH_CRIT_TXT_EXACT, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.exact)) {
            throw new S21AbendException(NMAM8132E, new String[] {MATCH_CRIT_TXT_EXACT, VAR_CHAR_CONST });
        }

        this.partial = ZYPCodeDataUtil.getVarCharConstValue(MATCH_CRIT_TXT_PARTIAL, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.partial)) {
            throw new S21AbendException(NMAM8132E, new String[] {MATCH_CRIT_TXT_PARTIAL, VAR_CHAR_CONST });
        }

        this.duns = ZYPCodeDataUtil.getVarCharConstValue(MATCH_CRIT_TXT_DUNS, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.duns)) {
            throw new S21AbendException(NMAM8132E, new String[] {MATCH_CRIT_TXT_DUNS, VAR_CHAR_CONST });
        }

        this.gln = ZYPCodeDataUtil.getVarCharConstValue(MATCH_CRIT_TXT_GLN, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.gln)) {
            throw new S21AbendException(NMAM8132E, new String[] {MATCH_CRIT_TXT_GLN, VAR_CHAR_CONST });
        }

        this.lineSubstrLength = ZYPCodeDataUtil.getVarCharConstValue(FIRST_LINE_ADDR_SUBSTR_LG, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.lineSubstrLength)) {
            throw new S21AbendException(NMAM8132E, new String[] {FIRST_LINE_ADDR_SUBSTR_LG, VAR_CHAR_CONST });
        }

        this.nmSubstrLength = ZYPCodeDataUtil.getVarCharConstValue(DS_ACCT_NM_SUBSTR_LG, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.nmSubstrLength)) {
            throw new S21AbendException(NMAM8132E, new String[] {DS_ACCT_NM_SUBSTR_LG, VAR_CHAR_CONST });
        }

        this.procDt = ZYPDateUtil.getBatProcDate();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    }

    @Override
    protected void mainRoutine() {
        doProcess();

    }

    private void doProcess() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

        int performanceCounter = 0;
        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);

        try {
            List<MKTG_MAPTMsg> insertList = new ArrayList<MKTG_MAPTMsg>();
            BigDecimal beforePk = null;
            String updtExactFlg = ZYPConstant.FLG_OFF_N;
            String updtPrtlFlg = ZYPConstant.FLG_OFF_N;
            String updtDunsFlg = ZYPConstant.FLG_OFF_N;
            String updtGlnFlg = ZYPConstant.FLG_OFF_N;
            String ovrdVldFlg = ZYPConstant.FLG_OFF_N;
            String trgtTpCd = null;
            List<String> locNumList = new ArrayList<String>();

            stmt = this.ssmLLClient.createPreparedStatement("getMktgMap", ssmParam, execParam);
            rsSet = stmt.executeQuery();
            while (rsSet.next()) {

                // QC#12258
                String cacheKey = rsSet.getBigDecimal("MKTG_MAP_WRK_PK").toPlainString();
                BigDecimal currentPk = rsSet.getBigDecimal("MKTG_MAP_RQST_PK");
                if (beforePk == null || currentPk.compareTo(beforePk) != 0) {
                    resetCache(currentPk, rsSet);
                }

                if (beforePk != null && beforePk.compareTo(rsSet.getBigDecimal("MKTG_MAP_RQST_PK")) != 0) {
                    // update MKTG_MAP_RQST
                    excecuteInsertForMktgMap(insertList);
                    insertList.clear();

                    MKTG_MAP_RQSTTMsg updtTMsg = new MKTG_MAP_RQSTTMsg();
                    ZYPEZDItemValueSetter.setValue(updtTMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(updtTMsg.mktgMapRqstPk, beforePk);
                    updtTMsg = (MKTG_MAP_RQSTTMsg) EZDTBLAccessor.findByKeyForUpdateWait(updtTMsg);

                    ZYPEZDItemValueSetter.setValue(updtTMsg.mktgMapRqstProcFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(updtTMsg.exactMatchFlg, updtExactFlg);
                    ZYPEZDItemValueSetter.setValue(updtTMsg.prtlMatchFlg, updtPrtlFlg);
                    ZYPEZDItemValueSetter.setValue(updtTMsg.dunsMatchFlg, updtDunsFlg);
                    ZYPEZDItemValueSetter.setValue(updtTMsg.glnMatchFlg, updtGlnFlg);

                    excecuteUpdateForMktgMapRqst(updtTMsg);

                    boolean isSuccess = true;

                    if (ZYPConstant.FLG_ON_Y.equals(ovrdVldFlg) //
                            || ZYPConstant.FLG_OFF_N.equals(updtPrtlFlg)) {
                        // update EXTN schema
                        isSuccess = callProcedure(locNumList, trgtTpCd);
                    }
                    updtExactFlg = ZYPConstant.FLG_OFF_N;
                    updtPrtlFlg = ZYPConstant.FLG_OFF_N;
                    updtDunsFlg = ZYPConstant.FLG_OFF_N;
                    updtGlnFlg = ZYPConstant.FLG_OFF_N;
                    ovrdVldFlg = ZYPConstant.FLG_OFF_N;
                    locNumList.clear();

                    if (isSuccess) {
                        commit();
                        this.totalNmlCount++;
                    }
                }

                List<Map<String, String>> dupAddrExactList = null;

                if (ZYPConstant.FLG_ON_Y.equals(rsSet.getString("EXACT_COND_FLG"))) {
                    // QC#12258
                    // dupAddrExactList = dupCheckForAddrExactMatch(rsSet);
                    dupAddrExactList = this.cacheAddrExactMatch.get(cacheKey);
                }

                List<Map<String, String>> dupAddrPartialList = null;

                if (dupAddrExactList == null || dupAddrExactList.size() <= 0) {

                    if (ZYPConstant.FLG_ON_Y.equals(rsSet.getString("PRTL_COND_FLG"))) {
                        // QC#12258
                        // dupAddrPartialList = dupCheckForAddrPartialMatch(rsSet);
                        dupAddrExactList = this.cacheAddrPrtlMatch.get(cacheKey);
                    }
                }

                List<Map<String, String>> dupDunsExactList = null;

                if (ZYPConstant.FLG_ON_Y.equals(rsSet.getString("DUNS_COND_FLG"))) {
                    // QC#12258
                    // dupDunsExactList = dupCheckForDuns(rsSet.getString("DUNS_NUM"));
                    dupAddrExactList = this.cacheDunsMatch.get(cacheKey);
                }

                List<Map<String, String>> dupGlnExactList = null;

                if (ZYPConstant.FLG_ON_Y.equals(rsSet.getString("GLN_COND_FLG"))) {
                    // QC#12258
                    // dupGlnExactList = dupCheckForGln(rsSet.getString("GLN_NUM"));
                    dupAddrExactList = this.cacheGlnMatch.get(cacheKey);
                }

                // create MATCH_CRIT_TXT
                StringBuilder matchCritTxt = new StringBuilder();
                if (dupAddrExactList != null && dupAddrExactList.size() > 0) {
                    matchCritTxt.append(this.exact);
                } else if (dupAddrPartialList != null && dupAddrPartialList.size() > 0) {
                    matchCritTxt.append(this.partial);
                }

                if (dupDunsExactList != null && dupDunsExactList.size() > 0) {
                    if (matchCritTxt.length() > 0) {
                        matchCritTxt.append(",");
                    }

                    matchCritTxt.append(this.duns);
                }

                if (dupGlnExactList != null && dupGlnExactList.size() > 0) {
                    if (matchCritTxt.length() > 0) {
                        matchCritTxt.append(",");
                    }

                    matchCritTxt.append(this.gln);
                }

                StringBuilder exactLocNum = new StringBuilder();
                StringBuilder exactAcctId = new StringBuilder();
                String exactFlg = setMatchList(dupAddrExactList, exactLocNum, exactAcctId, locNumList);

                StringBuilder prtlLocNum = new StringBuilder();
                StringBuilder prtlAcctId = new StringBuilder();
                String prtlFlg = setMatchList(dupAddrPartialList, prtlLocNum, prtlAcctId, null);

                StringBuilder dunsLocNum = new StringBuilder();
                StringBuilder dunsAcctId = new StringBuilder();
                String dunsFlg = setMatchList(dupDunsExactList, dunsLocNum, dunsAcctId, locNumList);

                StringBuilder glnLocNum = new StringBuilder();
                StringBuilder glnAcctId = new StringBuilder();
                String glnFlg = setMatchList(dupGlnExactList, glnLocNum, glnAcctId, locNumList);

                StringBuilder trtyNm = new StringBuilder();
                if (exactLocNum.length() <= 0 //
                        && prtlLocNum.length() <= 0 //
                        && dunsLocNum.length() <= 0 //
                        && glnLocNum.length() <= 0) {
                    List<String> listTrtyNm = this.cacheTrtyMatch.get(cacheKey);
                    if (listTrtyNm != null && listTrtyNm.size() > 0) {
                        for (int i = 0; i < listTrtyNm.size(); i++) {
                            String orgNm = listTrtyNm.get(i);
                            if (i != 0) {
                                trtyNm.append(",");
                            }
                            if (orgNm.length() + trtyNm.length() + 1 > MAX_MATCH_LG) {
                                break;
                            }
                            trtyNm.append(orgNm);
                        }
                    }
                }

                MKTG_MAPTMsg inTMsg = new MKTG_MAPTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                // QC#12258
                // BigDecimal mktgMapPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MKTG_MAP_SQ);
                BigDecimal mktgMapPk = this.cacheMktgMapPk.get(cacheKey);
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapPk, mktgMapPk);
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapRqstPk, rsSet.getBigDecimal("MKTG_MAP_RQST_PK"));
                ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctNm, rsSet.getString("DS_ACCT_NM"));
                ZYPEZDItemValueSetter.setValue(inTMsg.firstLineAddr, rsSet.getString("FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(inTMsg.scdLineAddr, rsSet.getString("SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(inTMsg.thirdLineAddr, rsSet.getString("THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(inTMsg.frthLineAddr, rsSet.getString("FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(inTMsg.ctyAddr, rsSet.getString("CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(inTMsg.cntyPk, rsSet.getBigDecimal("CNTY_PK"));
                ZYPEZDItemValueSetter.setValue(inTMsg.stCd, rsSet.getString("ST_CD"));
                ZYPEZDItemValueSetter.setValue(inTMsg.postCd, rsSet.getString("POST_CD"));
                ZYPEZDItemValueSetter.setValue(inTMsg.ctryCd, rsSet.getString("CTRY_CD"));
                ZYPEZDItemValueSetter.setValue(inTMsg.dunsNum, rsSet.getString("DUNS_NUM"));
                ZYPEZDItemValueSetter.setValue(inTMsg.glnNum, rsSet.getBigDecimal("GLN_NUM"));
                ZYPEZDItemValueSetter.setValue(inTMsg.hinNum, rsSet.getString("HIN_NUM"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapAttrbTxt_01, rsSet.getString("MKTG_MAP_ATTRB_TXT_01"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapAttrbTxt_02, rsSet.getString("MKTG_MAP_ATTRB_TXT_02"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapAttrbTxt_03, rsSet.getString("MKTG_MAP_ATTRB_TXT_03"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapAttrbTxt_04, rsSet.getString("MKTG_MAP_ATTRB_TXT_04"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapAttrbTxt_05, rsSet.getString("MKTG_MAP_ATTRB_TXT_05"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapAttrbTxt_06, rsSet.getString("MKTG_MAP_ATTRB_TXT_06"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapAttrbTxt_07, rsSet.getString("MKTG_MAP_ATTRB_TXT_07"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapAttrbTxt_08, rsSet.getString("MKTG_MAP_ATTRB_TXT_08"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapAttrbTxt_09, rsSet.getString("MKTG_MAP_ATTRB_TXT_09"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapAttrbTxt_10, rsSet.getString("MKTG_MAP_ATTRB_TXT_10"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapAttrbTxt_11, rsSet.getString("MKTG_MAP_ATTRB_TXT_11"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapAttrbTxt_12, rsSet.getString("MKTG_MAP_ATTRB_TXT_12"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapAttrbTxt_13, rsSet.getString("MKTG_MAP_ATTRB_TXT_13"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapAttrbTxt_14, rsSet.getString("MKTG_MAP_ATTRB_TXT_14"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapAttrbTxt_15, rsSet.getString("MKTG_MAP_ATTRB_TXT_15"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapAttrbTxt_16, rsSet.getString("MKTG_MAP_ATTRB_TXT_16"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapAttrbTxt_17, rsSet.getString("MKTG_MAP_ATTRB_TXT_17"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapAttrbTxt_18, rsSet.getString("MKTG_MAP_ATTRB_TXT_18"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapAttrbTxt_19, rsSet.getString("MKTG_MAP_ATTRB_TXT_19"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mktgMapAttrbTxt_20, rsSet.getString("MKTG_MAP_ATTRB_TXT_20"));
                ZYPEZDItemValueSetter.setValue(inTMsg.matchCritTpTxt, matchCritTxt.toString());
                ZYPEZDItemValueSetter.setValue(inTMsg.exactMatchFlg, exactFlg);
                ZYPEZDItemValueSetter.setValue(inTMsg.exactMatchLocNum, exactLocNum.toString());
                ZYPEZDItemValueSetter.setValue(inTMsg.exactMatchSfAcctId, exactAcctId.toString());
                ZYPEZDItemValueSetter.setValue(inTMsg.prtlMatchFlg, prtlFlg);
                ZYPEZDItemValueSetter.setValue(inTMsg.prtlMatchLocNum, prtlLocNum.toString());
                ZYPEZDItemValueSetter.setValue(inTMsg.prtlMatchSfAcctId, prtlAcctId.toString());
                ZYPEZDItemValueSetter.setValue(inTMsg.dunsMatchFlg, dunsFlg);
                ZYPEZDItemValueSetter.setValue(inTMsg.dunsMatchLocNum, dunsLocNum.toString());
                ZYPEZDItemValueSetter.setValue(inTMsg.dunsMatchSfAcctId, dunsAcctId.toString());
                ZYPEZDItemValueSetter.setValue(inTMsg.glnMatchFlg, glnFlg);
                ZYPEZDItemValueSetter.setValue(inTMsg.glnMatchLocNum, glnLocNum.toString());
                ZYPEZDItemValueSetter.setValue(inTMsg.glnMatchSfAcctId, glnAcctId.toString());
                ZYPEZDItemValueSetter.setValue(inTMsg.candiTrtyNm, trtyNm.toString());

                insertList.add(inTMsg);
                if (ZYPConstant.FLG_OFF_N.equals(updtExactFlg)) {
                    updtExactFlg = exactFlg;
                }

                if (ZYPConstant.FLG_OFF_N.equals(updtPrtlFlg)) {
                    updtPrtlFlg = prtlFlg;
                }

                if (ZYPConstant.FLG_OFF_N.equals(updtDunsFlg)) {
                    updtDunsFlg = dunsFlg;
                }

                if (ZYPConstant.FLG_OFF_N.equals(updtGlnFlg)) {
                    updtGlnFlg = glnFlg;
                }

                ovrdVldFlg = rsSet.getString("OVRD_VLD_FLG");
                trgtTpCd = rsSet.getString("CONTR_ASSN_TRGT_TP_CD");
                beforePk = rsSet.getBigDecimal("MKTG_MAP_RQST_PK");

                performanceCounter++;
            }

            // 2016/07/22 CSA-QC#12258 Mod Start
            if (ZYPCommonFunc.hasValue(beforePk)) {
                // update MKTG_MAP_RQST
                excecuteInsertForMktgMap(insertList);
                insertList.clear();

                MKTG_MAP_RQSTTMsg updtTMsg = new MKTG_MAP_RQSTTMsg();
                ZYPEZDItemValueSetter.setValue(updtTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updtTMsg.mktgMapRqstPk, beforePk);
                updtTMsg = (MKTG_MAP_RQSTTMsg) EZDTBLAccessor.findByKeyForUpdateWait(updtTMsg);

                ZYPEZDItemValueSetter.setValue(updtTMsg.mktgMapRqstProcFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(updtTMsg.exactMatchFlg, updtExactFlg);
                ZYPEZDItemValueSetter.setValue(updtTMsg.prtlMatchFlg, updtPrtlFlg);
                ZYPEZDItemValueSetter.setValue(updtTMsg.dunsMatchFlg, updtDunsFlg);
                ZYPEZDItemValueSetter.setValue(updtTMsg.glnMatchFlg, updtGlnFlg);

                excecuteUpdateForMktgMapRqst(updtTMsg);

                boolean isSuccess = true;

                if (ZYPConstant.FLG_ON_Y.equals(ovrdVldFlg) //
                        || ZYPConstant.FLG_OFF_N.equals(updtPrtlFlg)) {
                    // update EXTN schema
                    isSuccess = callProcedure(locNumList, trgtTpCd);
                }

                if (isSuccess) {
                    this.totalNmlCount++;
                }
            }
            // 2016/07/22 CSA-QC#12258 Mod End

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }


    private String getSfAcctId(String locNum) throws SQLException {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("partySiteNumber", locNum);

        String sfAcctId = (String) this.ssmBatchClient.queryObject("getSfAcctId", ssmParam);
        if (!ZYPCommonFunc.hasValue(sfAcctId)) {
            sfAcctId = "";
        }

        return sfAcctId;
    }

    private String setMatchList(List<Map<String, String>> dupList, StringBuilder locNumList, StringBuilder sfAcctList, List<String> updtLocNumList) throws SQLException {
        if (dupList != null && dupList.size() > 0) {
            for (Map<String, String> dup : dupList) {
                String sfAcctId = getSfAcctId(dup.get("LOC_NUM"));

                if (dup.get("LOC_NUM").length() + locNumList.length() + 1 > MAX_MATCH_LG //
                        || sfAcctId.length() + sfAcctList.length() + 1 > MAX_MATCH_LG) {
                    break;
                }

                if (locNumList.length() > 0) {
                    locNumList.append(",");
                    sfAcctList.append(",");
                }
                locNumList.append(dup.get("LOC_NUM"));
                sfAcctList.append(sfAcctId);

                if (updtLocNumList != null) {
                    if (!updtLocNumList.contains(dup.get("LOC_NUM")) //
                            && ZYPCommonFunc.hasValue(sfAcctId)) {
                        updtLocNumList.add(dup.get("LOC_NUM"));
                    }
                }

            }
            return ZYPConstant.FLG_ON_Y;
        }
        return ZYPConstant.FLG_OFF_N;
    }

    private void excecuteInsertForMktgMap(List<MKTG_MAPTMsg> insertList) {

        if (insertList == null || insertList.size() <= 0) {
            return;
        }

        S21InfoLogOutput.println("Performanace Test: excecuteInsertForMktgMap - BEG - Insert Count:[" + insertList.size() + "]");

        int insertCount = S21FastTBLAccessor.insert(insertList.toArray(new MKTG_MAPTMsg[insertList.size()]));
        int insertListSize = insertList.size();

        if (insertCount != insertListSize) {
            rollback();
            this.totalErrCount++;
            throw new S21AbendException(NMAM0176E, new String[] {"MKTG_MAP" });
        }

        S21InfoLogOutput.println("Performanace Test: excecuteInsertForMktgMap - END -");
    }

    private void excecuteUpdateForMktgMapRqst(MKTG_MAP_RQSTTMsg updtMsg) {

        S21FastTBLAccessor.update(updtMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updtMsg.getReturnCode())) {
            rollback();
            this.totalErrCount++;
            throw new S21AbendException(NMAM0177E, new String[] {"MKTG_MAP_RQST" });
        }
    }

    private boolean callProcedure(List<String> locNumList, String trgtTpCd) {
        S21BatchTransactionQuery queryComponet = new S21BatchTransactionQuery();
        for (String locNum : locNumList) {
            StringBuilder cmd = new StringBuilder();
            cmd.append("DECLARE x_return boolean; ");
            cmd.append("BEGIN ");
            cmd.append(PROCEDURE_NM);
            cmd.append("(");
            cmd.append(QUOTE).append(locNum).append(QUOTE);
            cmd.append(", ");
            cmd.append(QUOTE).append(trgtTpCd).append(QUOTE);
            cmd.append(", ");
            cmd.append("x_return");
            cmd.append("); ");
            cmd.append("END;");

            int num = queryComponet.executeUpdate(cmd.toString());

            if (num < 0) {
                rollback();
                S21InfoLogOutput.println(NMAM0177E, new String[] {"CANON_E404_SF_ACCT_MAPPING_TBL" });
                this.totalErrCount++;
                this.termCd = TERM_CD.WARNING_END;
                return false;
            }
        }
        return true;
    }

    @Override
    protected void termRoutine() {
        this.totalCount = this.totalNmlCount + this.totalErrCount;
        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);

    }

    /**
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {
        new NMAB320001().executeBatch(NMAB320001.class.getSimpleName());

    }

    // QC#12258
    private void resetCache(BigDecimal mktgMapRqstPk, ResultSet rsSet) throws SQLException {
        clearCache();

        loadCacheLocMatch(mktgMapRqstPk, rsSet);

        List<Map<String, String>> mmwList = getMktgMapWrkByRqstPkDirect(mktgMapRqstPk);

        loadCacheTrtyMatch(mktgMapRqstPk, rsSet, mmwList);

        loadCacheMktgMapPk(mktgMapRqstPk, rsSet, mmwList);
    }

    private void clearCache() {
        this.cacheAddrExactMatch.clear();
        this.cacheAddrPrtlMatch.clear();
        this.cacheDunsMatch.clear();
        this.cacheGlnMatch.clear();
        this.cacheTrtyMatch.clear();
        this.cacheMktgMapPk.clear();
    }

    private void loadCacheLocMatch(BigDecimal mktgMapRqstPk, ResultSet rsSet) throws SQLException {
        S21InfoLogOutput.println("Performanace Test: resetMatchCache 0");

        if (ZYPConstant.FLG_ON_Y.equals(rsSet.getString("EXACT_COND_FLG"))) {
            List<Map<String, String>> listMap = loadCacheAddrExact(mktgMapRqstPk);
            this.cacheAddrExactMatch = convertListMapToCache(listMap);
        }

        S21InfoLogOutput.println("Performanace Test: resetMatchCache 1");

        if (ZYPConstant.FLG_ON_Y.equals(rsSet.getString("PRTL_COND_FLG"))) {
            List<Map<String, String>> listMap = loadCacheAddrPrtl(mktgMapRqstPk);
            this.cacheAddrPrtlMatch = convertListMapToCache(listMap);
            for (String key : this.cacheAddrExactMatch.keySet()) {
                if (this.cacheAddrPrtlMatch.containsKey(key)) {
                    this.cacheAddrPrtlMatch.remove(key);
                }
            }
        }

        S21InfoLogOutput.println("Performanace Test: resetMatchCache 2");

        if (ZYPConstant.FLG_ON_Y.equals(rsSet.getString("DUNS_COND_FLG"))) {
            List<Map<String, String>> listMap = loadCacheDuns(mktgMapRqstPk);
            this.cacheDunsMatch = convertListMapToCache(listMap);
        }

        S21InfoLogOutput.println("Performanace Test: resetMatchCache 3");

        if (ZYPConstant.FLG_ON_Y.equals(rsSet.getString("GLN_COND_FLG"))) {
            List<Map<String, String>> listMap = loadCacheGln(mktgMapRqstPk);
            this.cacheGlnMatch = convertListMapToCache(listMap);
        }

        S21InfoLogOutput.println("Performanace Test: resetMatchCache 4");

    }

    private void loadCacheTrtyMatch(BigDecimal mktgMapRqstPk, ResultSet rsSet, List<Map<String, String>> mmwList) throws SQLException {
        List<Map<String, String>> mmwTrtyList = new ArrayList<Map<String, String>>();
        for (Map<String, String> mmw : mmwList) {
            String mmwPk = (String) mmw.get("MKTG_MAP_WRK_PK_STR");
            if (!this.cacheAddrExactMatch.containsKey(mmwPk)
                    && !this.cacheAddrPrtlMatch.containsKey(mmwPk)
                    && !this.cacheDunsMatch.containsKey(mmwPk)
                    && !this.cacheGlnMatch.containsKey(mmwPk)) {
                mmwTrtyList.add(mmw);
            }
        }

        NMZC271001PMsg pMsg = new NMZC271001PMsg();
        int trtyApiBatchCnt = NMAB320001Constant.TRTY_MATCH_BULK_SIZE;
        if (trtyApiBatchCnt > pMsg.searchListBatch.length()) {
            trtyApiBatchCnt = pMsg.searchListBatch.length();
        }
        int bufCnt = 0;
        for (int i = 0; i < mmwTrtyList.size(); i++) {
            Map<String, String> mmw = mmwTrtyList.get(i);
            String stCd = (String) mmw.get("ST_CD");
            String postCd = (String) mmw.get("POST_CD");
            String dsAcctNm = (String) mmw.get("DS_ACCT_NM");

            pMsg.searchListBatch.no(bufCnt).stCd.setValue(stCd);
            pMsg.searchListBatch.no(bufCnt).postCd.setValue(postCd);
            pMsg.searchListBatch.no(bufCnt).dsAcctNm.setValue(dsAcctNm);

            if (bufCnt == trtyApiBatchCnt - 1 || i == mmwTrtyList.size() - 1) {
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.procDt);
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeInd, PROS_TO_TRTY_BATCH_MODE);
                pMsg.searchListBatch.setValidCount(bufCnt + 1);

                NMZC271001 api = new NMZC271001();
                api.execute(pMsg, ONBATCH_TYPE.BATCH);

                List<String> errList = S21ApiUtil.getXxMsgIdList(pMsg);
                if (errList != null && errList.size() > 0) {
                    for (String err : errList) {
                        S21InfoLogOutput.println(err);
                    }
                } else {
                    for (int j = 0; j < pMsg.territoryListBatch.getValidCount(); j++) {
                        NMZC271001_territoryListBatchPMsg subPMsg = pMsg.territoryListBatch.no(j);
                        String orgNm = subPMsg.orgNm.getValue();
                        int rowNumInPMsg = subPMsg.xxRowNum.getValueInt();
                        int rowNumInTrtyList = (i - bufCnt) + rowNumInPMsg;
                        String key = mmwTrtyList.get(rowNumInTrtyList).get("MKTG_MAP_WRK_PK_STR");
                        List<String> valueList = null;
                        if (this.cacheTrtyMatch.containsKey(key)) {
                            valueList = this.cacheTrtyMatch.get(key);
                        } else {
                            valueList = new ArrayList<String>();
                            this.cacheTrtyMatch.put(key, valueList);
                        }
                        valueList.add(orgNm);
                    }
                }

                bufCnt = 0;
            } else {
                bufCnt++;
            }
        }
    }

    private void loadCacheMktgMapPk(BigDecimal mktgMapRqstPk, ResultSet rsSet, List<Map<String, String>> mmwList) throws SQLException {
        for (Map<String, String> mmw : mmwList) {
            String mmwPk = (String) mmw.get("MKTG_MAP_WRK_PK_STR");
            String mmPk = (String) mmw.get("MKTG_MAP_PK_STR");
            this.cacheMktgMapPk.put(mmwPk, new BigDecimal(mmPk));
        }
    }

    private Map<String, List<Map<String, String>>> convertListMapToCache(List<Map<String, String>> listMap) {
        String keyName = "MKTG_MAP_WRK_PK_STR";
        Map<String, List<Map<String, String>>> cache = new HashMap<String, List<Map<String, String>>>();
        for (Map<String, String> map : listMap) {
            List<Map<String, String>> listValue = null;
            String key = (String) map.get(keyName);
            if (cache.containsKey(key)) {
                listValue = cache.get(key);
            } else {
                listValue = new ArrayList<Map<String, String>>();
                cache.put(key, listValue);
            }
            map.remove(keyName);
            listValue.add(map);
        }
        return cache;
    }

    /**
     * 'executeQuery' is faster than 'queryObjectList' 
     */
    private List<Map<String, String>> getMktgMapWrkByRqstPkDirect(BigDecimal mktgMapRqstPk) throws SQLException {
        S21InfoLogOutput.println("Performanace Test: getMktgMapWrkByRqstPk BEG");
        PreparedStatement stmt = null;
        ResultSet rsSet = null;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(DEFAULT_FETCH_SIZE);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("mktgMapRqstPk", mktgMapRqstPk);
            ssmParam.put("glblCmpyCd", this.glblCmpyCd);

            stmt = this.ssmLLClient.createPreparedStatement("getMktgMapWrkByRqstPk", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            String[] colNmArr = new String[] {"MKTG_MAP_WRK_PK_STR", "ST_CD", "POST_CD", "DS_ACCT_NM", "MKTG_MAP_PK_STR" };
            while (rsSet.next()) {
                Map<String, String> map = new HashMap<String, String>();
                for (String colNm : colNmArr) {
                    map.put(colNm, rsSet.getString(colNm));
                }
                list.add(map);
            }
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
        S21InfoLogOutput.println("Performanace Test: getMktgMapWrkByRqstPk END");
        return list;
    }

    private List<Map<String, String>> loadCacheAddrExact(BigDecimal mktgMapRqstPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("mktgMapRqstPk", mktgMapRqstPk);
        ssmParam.put("matchAddrFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("exactFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        List<Map<String, String>> dupList = (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getDupLoc", ssmParam);
        return dupList;
    }

    private List<Map<String, String>> loadCacheAddrPrtl(BigDecimal mktgMapRqstPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("mktgMapRqstPk", mktgMapRqstPk);
        ssmParam.put("matchAddrFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("exactFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("lineSubstrLength", this.lineSubstrLength);
        ssmParam.put("nmSubstrLength", this.nmSubstrLength);
        List<Map<String, String>> dupList = (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getDupLoc", ssmParam);
        return dupList;
    }

    private List<Map<String, String>> loadCacheDuns(BigDecimal mktgMapRqstPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("mktgMapRqstPk", mktgMapRqstPk);
        ssmParam.put("matchDunsFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        List<Map<String, String>> dupList = (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getDupLoc", ssmParam);
        return dupList;
    }

    private List<Map<String, String>> loadCacheGln(BigDecimal mktgMapRqstPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("mktgMapRqstPk", mktgMapRqstPk);
        ssmParam.put("matchGlnFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        List<Map<String, String>> dupList = (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getDupLoc", ssmParam);
        return dupList;
    }
}
