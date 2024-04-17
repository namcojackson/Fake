/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB271001;

import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.DS_ACCT_NM_SUBSTR_LG;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.DUP_MODE_01;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.DUP_MODE_02;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.DUP_MODE_03;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.FIRST_LINE_ADDR_SUBSTR_LG;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.MATCH_ACCT_LOC_NUM_MAX_LENGTH;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.MATCH_CRIT_TXT_DUNS;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.MATCH_CRIT_TXT_EXACT;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.MATCH_CRIT_TXT_GLN;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.MATCH_CRIT_TXT_PARTIAL;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.NMAM0176E;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.NMAM0177E;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.NMAM8132E;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.PROS_TO_TRTY_BATCH_MODE;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.TRTY_MATCH_BULK_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.TIME_MODE_01;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.TIME_MODE_02;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.VAR_CHAR_CONST;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.ZZZM9026E;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.DUP_CANDI_MATCH_WRK;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.DUP_PROS_CANDI_MATCH_WRK;
import static com.canon.cusa.s21.batch.NMA.NMAB271001.constant.NMAB271001Constant.CONST_DB_SCHEMA;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDDBCICarrier;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CANDI_TRTY_LOC_WRKTMsg;
import business.db.CTRYTMsg;
import business.db.CTRYTMsgArray;
import business.db.DS_ACCT_RVW_PROSTMsg;
import business.db.DUP_CANDI_LOC_WRKTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.STTMsg;
import business.parts.NMZC271001PMsg;
import business.parts.NMZC271001_territoryListBatchPMsg;

import com.canon.cusa.s21.api.NMZ.NMZC271001.NMZC271001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.internal.S21BatchTransactionQuery;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.common.util.S21StopWatch;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Prospect Candidate Territory and Locations
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/06   Fujitsu         M.Ohno          Create
 * 2016/08/03   Fujitsu         N.Sugiura       Update          QC#12418
 * 2016/08/19   SRAA            Y.Chen          Update          QC#13664
 * 2016/10/24   Fujitsu         N.Sugiura       Update          QC#15214
 * 2017/12/15   Fujitsu         N.Sugiura       Update          QC#22246
 * 2018/03/28   Fujitsu         H.Nagashima     Update          QC#23142,QC#23150
 * 2018/04/11   Fujitsu         Hd.Sugawara     Update          QC#23142-2
 * 2018/05/23   Fujitsu         Hd.Sugawara     Update          QC#23142-3,QC#23150-2
 * 2018/07/11   Fujitsu         Mz.takahashi    Update          QC#25871
 *</pre>
 */
public class NMAB271001 extends S21BatchMain {
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

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    // 2016/10/24 CSA-QC#15214 Add Start
    /** SSM Client Custom */
    private S21SsmBatchClientCustom ssmBatchClientCustom = null;
    // 2016/10/24 CSA-QC#15214 Add End

    /** process date time */
    private String procDt = null;

    /** User Param */
    private String varUser1 = null;

    /** User Param */
    private String varUser2 = null;

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

    // Add Start 2018/07/11 QC#25871
    private int nmSubstrLengthNumeric = 0;

    /** Schema Name */
    private String dbSchema = null;

    /** Commit Count */
    private int commitCount = DEFAULT_FETCH_SIZE;
    // Add End 2018/07/11 QC#25871

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

        this.varUser1 = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(this.varUser1)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Var User1" });
        }

        this.varUser2 = getUserVariable2();
        if (!ZYPCommonFunc.hasValue(this.varUser2)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Var User2" });
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

        this.nmSubstrLengthNumeric = Integer.parseInt(this.nmSubstrLength);
        this.procDt = ZYPDateUtil.getBatProcDate();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // 2016/10/24 CSA-QC#15214 Add Start
        // Initialization of SSM Custom
        ssmBatchClientCustom = new S21SsmBatchClientCustom(this.getClass());
        // 2016/10/24 CSA-QC#15214 Add End
        
        // Add Start 2018/07/11 QC#25871
        this.dbSchema = ZYPCodeDataUtil.getVarCharConstValue(CONST_DB_SCHEMA, glblCmpyCd);

        int count =  this.getCommitCount();

        if (count > this.commitCount) {
            this.commitCount = count; 
        }
        // Add End 2018/07/11 QC#25871
    }

    @Override
    protected void mainRoutine() {
        if (DUP_MODE_01.equals(this.varUser1)) {
            doProcessMode1();
        } else if (DUP_MODE_02.equals(this.varUser1)) {
            doProcessMode2();
        } else if (DUP_MODE_03.equals(this.varUser1)) {
            doProcessMode3();
        }
    }

    private void doProcessMode1() {
        if (TIME_MODE_02.equals(this.varUser2)) {
// 2016/10/24 CSA-QC#15214 Mod Start
//            List<BigDecimal> locWrkPkList = getDupCandiLocWrk();
//            List<DUP_CANDI_LOC_WRKTMsg> delList = new ArrayList<DUP_CANDI_LOC_WRKTMsg>(locWrkPkList.size());
//            for (BigDecimal locWrkPk : locWrkPkList) {
//                // delete DUP_CANDI_LOC_WRK
//                DUP_CANDI_LOC_WRKTMsg inTMsg = new DUP_CANDI_LOC_WRKTMsg();
//                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(inTMsg.dupCandiLocWrkPk, locWrkPk);
//
//                inTMsg = (DUP_CANDI_LOC_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);
//
//                delList.add(inTMsg);
//            }
//
//            if (delList.size() > 0) {
//                int resultCount = S21FastTBLAccessor.removeLogical(delList.toArray(new DUP_CANDI_LOC_WRKTMsg[0]));
//
//                if (resultCount != delList.size()) {
//                    throw new S21AbendException(NMAM0177E, new String[] {delList.get(0).getTableName() });
//                }
//            }
            int totalDeleteCount = deleteDupCandiLocWrk();
            S21InfoLogOutput.println("DUP_CANDI_LOC_WRK DELETE_COUNT:" + totalDeleteCount);
            commit();
// 2016/10/24 CSA-QC#15214 Mod End
        }

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        if (TIME_MODE_01.equals(this.varUser2)) {
            ssmParam.put("mode01Flg", ZYPConstant.FLG_ON_Y);
        }

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getDsAcctRvwPros", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            while (rsSet.next()) {
                // MasterCheck
                // 2017/12/15 CSA-QC#22246 Mod Start
                // if (ZYPCommonFunc.hasValue(rsSet.getString("BEF_BILL_TO_ST_CD")) //
                //         && !ZYPCommonFunc.hasValue(getStCd(rsSet.getString("BEF_BILL_TO_ST_CD")))) {
                //     S21InfoLogOutput.println(NMAM8132E, new String[] {"BEF_BILL_TO_ST_CD", "ST" });
                if (ZYPCommonFunc.hasValue(rsSet.getString("BEF_SHIP_TO_ST_NM")) //
                        && !ZYPCommonFunc.hasValue(getStCd(rsSet.getString("BEF_SHIP_TO_ST_NM")))) {
                    S21InfoLogOutput.println(NMAM8132E, new String[] {"BEF_SHIP_TO_ST_NM", "ST" });
                // 2017/12/15 CSA-QC#22246 Mod End
                    this.termCd = TERM_CD.WARNING_END;
                    this.totalErrCount++;
                    continue;
                }

                if (ZYPCommonFunc.hasValue(rsSet.getString("BEF_CTRY_NM")) //
                        && !ZYPCommonFunc.hasValue(getCtryCd(rsSet.getString("BEF_CTRY_NM")))) {
                    S21InfoLogOutput.println(NMAM8132E, new String[] {"BEF_CTRY_NM", "CTRY" });
                    this.termCd = TERM_CD.WARNING_END;
                    this.totalErrCount++;
                    continue;
                }

                List<Map<String, String>> dupAddrExactList = dupCheckForAddrExactMatch(rsSet);

                List<Map<String, String>> dupAddrPartialList = null;

                if (dupAddrExactList == null || dupAddrExactList.size() <= 0) {
                    dupAddrPartialList = dupCheckForAddrPartialMatch(rsSet);
                }

                List<Map<String, String>> dupDunsExactList = dupCheckForDuns(rsSet.getString("BEF_DUNS_NUM"), rsSet.getBigDecimal("DS_ACCT_RVW_PROS_PK"));

                List<Map<String, String>> dupGlnExactList = dupCheckForGln(rsSet.getString("AFT_GLN_NUM"), rsSet.getBigDecimal("DS_ACCT_RVW_PROS_PK"));

                List<String> dupLocNmList = new ArrayList<String>();
                List<DUP_CANDI_LOC_WRKTMsg> insertList = new ArrayList<DUP_CANDI_LOC_WRKTMsg>();

                if (dupAddrExactList != null) {
                    for (Map<String, String> dupAddrExact : dupAddrExactList) {
                        DUP_CANDI_LOC_WRKTMsg inTMsg = createDupCandiLocWrkTMsg(rsSet.getString("RVW_PROS_NUM"), dupAddrExact);

                        ZYPEZDItemValueSetter.setValue(inTMsg.exactMatchFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(inTMsg.prtlMatchFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(inTMsg.dunsMatchFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(inTMsg.glnMatchFlg, ZYPConstant.FLG_OFF_N);
                        insertList.add(inTMsg);

                        //QC#23142 add Start
                        if (DS_LOC_TP.UNDER_REVIEW.equals(dupAddrExact.get("TBL_NM"))) {
                            if (!dupLocNmList.contains(dupAddrExact.get("RVW_PROS_NUM"))) {
                                dupLocNmList.add(dupAddrExact.get("RVW_PROS_NUM"));
                            }
                        } else {
                        //QC#23142 add End
                            if (!dupLocNmList.contains(dupAddrExact.get("LOC_NUM"))) {
                                dupLocNmList.add(dupAddrExact.get("LOC_NUM"));
                            }
                        }
                    }
                }

                if (dupAddrPartialList != null) {
                    for (Map<String, String> dupAddrPartial : dupAddrPartialList) {
                        DUP_CANDI_LOC_WRKTMsg inTMsg = createDupCandiLocWrkTMsg(rsSet.getString("RVW_PROS_NUM"), dupAddrPartial);

                        ZYPEZDItemValueSetter.setValue(inTMsg.exactMatchFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(inTMsg.prtlMatchFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(inTMsg.dunsMatchFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(inTMsg.glnMatchFlg, ZYPConstant.FLG_OFF_N);
                        insertList.add(inTMsg);

                        //QC#23142 add Start
                        if (DS_LOC_TP.UNDER_REVIEW.equals(dupAddrPartial.get("TBL_NM"))) {
                            if (!dupLocNmList.contains(dupAddrPartial.get("RVW_PROS_NUM"))) {
                                dupLocNmList.add(dupAddrPartial.get("RVW_PROS_NUM"));
                            }
                        } else {
                        //QC#23142 add End
                            if (!dupLocNmList.contains(dupAddrPartial.get("LOC_NUM"))) {
                                dupLocNmList.add(dupAddrPartial.get("LOC_NUM"));
                            }
                        }
                    }
                }

                if (dupDunsExactList != null) {
                    for (Map<String, String> dupDunsExact : dupDunsExactList) {
                        DUP_CANDI_LOC_WRKTMsg inTMsg = createDupCandiLocWrkTMsg(rsSet.getString("RVW_PROS_NUM"), dupDunsExact);

                        ZYPEZDItemValueSetter.setValue(inTMsg.exactMatchFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(inTMsg.prtlMatchFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(inTMsg.dunsMatchFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(inTMsg.glnMatchFlg, ZYPConstant.FLG_OFF_N);
                        insertList.add(inTMsg);

                        //QC#23142 add Start
                        if (DS_LOC_TP.UNDER_REVIEW.equals(dupDunsExact.get("TBL_NM"))) {
                            if (!dupLocNmList.contains(dupDunsExact.get("RVW_PROS_NUM"))) {
                                dupLocNmList.add(dupDunsExact.get("RVW_PROS_NUM"));
                            }
                        } else {
                        //QC#23142 add End
                            if (!dupLocNmList.contains(dupDunsExact.get("LOC_NUM"))) {
                                dupLocNmList.add(dupDunsExact.get("LOC_NUM"));
                            }
                        }
                    }
                }

                if (dupGlnExactList != null) {
                    for (Map<String, String> dupGlnExact : dupGlnExactList) {
                        DUP_CANDI_LOC_WRKTMsg inTMsg = createDupCandiLocWrkTMsg(rsSet.getString("RVW_PROS_NUM"), dupGlnExact);

                        ZYPEZDItemValueSetter.setValue(inTMsg.exactMatchFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(inTMsg.prtlMatchFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(inTMsg.dunsMatchFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(inTMsg.glnMatchFlg, ZYPConstant.FLG_ON_Y);
                        insertList.add(inTMsg);

                        //QC#23142 add Start
                        if (DS_LOC_TP.UNDER_REVIEW.equals(dupGlnExact.get("TBL_NM"))) {
                            if (!dupLocNmList.contains(dupGlnExact.get("RVW_PROS_NUM"))) {
                                dupLocNmList.add(dupGlnExact.get("RVW_PROS_NUM"));
                            }
                        } else {
                        //QC#23142 add End
                            if (!dupLocNmList.contains(dupGlnExact.get("LOC_NUM"))) {
                                dupLocNmList.add(dupGlnExact.get("LOC_NUM"));
                            }
                        }
                    }
                }

                excecuteInsertForDup(insertList);

                StringBuilder matchCritTxt = new StringBuilder();
                if (dupAddrExactList != null && dupAddrExactList.size() > 0) {
                    matchCritTxt.append(this.exact);
                }

                if (dupAddrPartialList != null && dupAddrPartialList.size() > 0) {
                    if (matchCritTxt.length() > 0) {
                        matchCritTxt.append(",");
                    }

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

                // update DS_ACCT_RVW_PROS
                if (dupLocNmList.size() > 0) {
                    StringBuilder locNm = new StringBuilder();
                    for (String dupLocNm : dupLocNmList) {
                        if (locNm.length() + dupLocNm.length() + 1 > MATCH_ACCT_LOC_NUM_MAX_LENGTH) {
                            break;
                        }

                        if (locNm.length() > 0) {
                            locNm.append(",");
                        }
                        locNm.append(dupLocNm);
                    }

                    DS_ACCT_RVW_PROSTMsg inTMsg = new DS_ACCT_RVW_PROSTMsg();
                    ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctRvwProsPk, rsSet.getBigDecimal("DS_ACCT_RVW_PROS_PK"));

                    inTMsg = (DS_ACCT_RVW_PROSTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

                    ZYPEZDItemValueSetter.setValue(inTMsg.dupAcctLocFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(inTMsg.matchCritTxt, matchCritTxt.toString());
                    ZYPEZDItemValueSetter.setValue(inTMsg.matchAcctLocNum, locNm.toString());

                    EZDTBLAccessor.update(inTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                        this.totalErrCount++;
                        throw new S21AbendException(NMAM0177E, new String[] {inTMsg.getTableName() });
                    }

                    commit();
                    this.totalNmlCount++;
                } else {
                    this.totalNmlCount++;
                }
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }

    }

    private void doProcessMode2() {
        if (TIME_MODE_02.equals(this.varUser2)) {

// 2016/10/24 CSA-QC#15214 Mod Start        
//            List<BigDecimal> locWrkPkList = getCandiTrtyLocWrk();
//            List<CANDI_TRTY_LOC_WRKTMsg> delList = new ArrayList<CANDI_TRTY_LOC_WRKTMsg>(locWrkPkList.size());
//
//            for (BigDecimal locWrkPk : locWrkPkList) {
//                // delete CANDI_TRTY_LOC_WRK
//                CANDI_TRTY_LOC_WRKTMsg inTMsg = new CANDI_TRTY_LOC_WRKTMsg();
//                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(inTMsg.candiTrtyLocWrkPk, locWrkPk);
//
//                inTMsg = (CANDI_TRTY_LOC_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);
//
//                delList.add(inTMsg);
//            }
//
//            if (delList.size() > 0) {
//                int resultCount = S21FastTBLAccessor.removeLogical(delList.toArray(new CANDI_TRTY_LOC_WRKTMsg[0]));
//
//                if (resultCount != delList.size()) {
//                    throw new S21AbendException(NMAM0177E, new String[] {delList.get(0).getTableName() });
//                }
//            }
            int totalDeleteCount = deleteCandiTrtyLocWrk();
            S21InfoLogOutput.println("CANDI_TRTY_LOC_WRK DELETE_COUNT:" + totalDeleteCount);
            commit();
// 2016/10/24 CSA-QC#15214 Mod End
        }

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        if (TIME_MODE_01.equals(this.varUser2)) {
            ssmParam.put("mode01Flg", ZYPConstant.FLG_ON_Y);
        }

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getDsAcctRvwPros", ssmParam, execParam);
            rsSet = stmt.executeQuery();

// 2016/10/24 CSA-QC#15214 Mod Start
            NMZC271001PMsg pMsg = new NMZC271001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.procDt);
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeInd, PROS_TO_TRTY_BATCH_MODE);

            List<Map<String, String>> mmwTrtyList = new ArrayList<Map<String, String>>();
            int bufCnt = 0;

            int trtyApiBatchCnt = TRTY_MATCH_BULK_SIZE;
            if (trtyApiBatchCnt > pMsg.searchListBatch.length()) {
                trtyApiBatchCnt = pMsg.searchListBatch.length();
            }
            while (rsSet.next()) {
                // MasterCheck
                // 2017/12/15 CSA-QC#22246 Mod Start
                // if (ZYPCommonFunc.hasValue(rsSet.getString("BEF_BILL_TO_ST_CD")) //
                //         && !ZYPCommonFunc.hasValue(getStCd(rsSet.getString("BEF_BILL_TO_ST_CD")))) {
                //     S21InfoLogOutput.println(NMAM8132E, new String[] {"BEF_BILL_TO_ST_CD", "ST" });
                if (ZYPCommonFunc.hasValue(rsSet.getString("BEF_SHIP_TO_ST_NM")) //
                        && !ZYPCommonFunc.hasValue(getStCd(rsSet.getString("BEF_SHIP_TO_ST_NM")))) {
                    S21InfoLogOutput.println(NMAM8132E, new String[] {"BEF_SHIP_TO_ST_CD", "ST" });
                // 2017/12/15 CSA-QC#22246 Mod End
                    this.termCd = TERM_CD.WARNING_END;
                    this.totalErrCount++;
                    continue;
                }
                if (bufCnt == trtyApiBatchCnt - 1) {
                    bufCnt = 0;
                    callAcctTrtyRuleRelnApi(mmwTrtyList, pMsg);

                    mmwTrtyList.clear();
                    pMsg.searchListBatch.clear();
                } else {
                    // 2017/12/15 CSA-QC#22246 Mod Start
                    // String stCd = getStCd(rsSet.getString("BEF_BILL_TO_ST_CD"));
                    // String postCd = rsSet.getString("BEF_BILL_TO_POST_CD");
                    String stCd = getStCd(rsSet.getString("BEF_SHIP_TO_ST_NM"));
                    String postCd = rsSet.getString("BEF_SHIP_TO_POST_CD");
                    // 2017/12/15 CSA-QC#22246 Mod End
                    String dsAcctNm = rsSet.getString("BEF_DS_ACCT_NM");
                    String dsCustSicCd = rsSet.getString("BEF_DS_CUST_SIC_CD");

                    ZYPEZDItemValueSetter.setValue(pMsg.searchListBatch.no(bufCnt).stCd, stCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.searchListBatch.no(bufCnt).postCd, postCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.searchListBatch.no(bufCnt).dsAcctNm, dsAcctNm);
                    ZYPEZDItemValueSetter.setValue(pMsg.searchListBatch.no(bufCnt).dsCustSicCd, dsCustSicCd);
                    pMsg.searchListBatch.setValidCount(bufCnt + 1);
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("RVW_PROS_NUM", rsSet.getString("RVW_PROS_NUM"));
                    map.put("DS_ACCT_RVW_PROS_PK_STR", rsSet.getString("DS_ACCT_RVW_PROS_PK_STR"));
                    mmwTrtyList.add(map);
                    bufCnt++;
                }

//                // execute NMZC271001
//                api.execute(pMsg, ONBATCH_TYPE.BATCH);
//
//                List<String> errList = S21ApiUtil.getXxMsgIdList(pMsg);
//
//                if (errList != null && errList.size() > 0) {
//                    for (String err : errList) {
//                        S21InfoLogOutput.println(err);
//                    }
//
//                    continue;
//                }
//
//                // insert CANDI_TRTY_LOC_WRK
//                List<String> dupOrgNmList = new ArrayList<String>();
//                List<CANDI_TRTY_LOC_WRKTMsg> insertList = new ArrayList<CANDI_TRTY_LOC_WRKTMsg>();
//                for (int i = 0; i < pMsg.territoryList.getValidCount(); i++) {
//                    NMZC271001_territoryListPMsg pMsgDtl = pMsg.territoryList.no(i);
//
//                    if (!dupOrgNmList.contains(pMsgDtl.orgNm.getValue())) {
//                        dupOrgNmList.add(pMsgDtl.orgNm.getValue());
//                    }
//
//                    CANDI_TRTY_LOC_WRKTMsg inTMsg = new CANDI_TRTY_LOC_WRKTMsg();
//                    ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
//                    BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CANDI_TRTY_LOC_WRK_SQ);
//                    ZYPEZDItemValueSetter.setValue(inTMsg.candiTrtyLocWrkPk, pk);
//                    ZYPEZDItemValueSetter.setValue(inTMsg.dsLocTpCd, DS_LOC_TP.UNDER_REVIEW);
//                    ZYPEZDItemValueSetter.setValue(inTMsg.rvwProsNum, rsSet.getString("RVW_PROS_NUM"));
//                    ZYPEZDItemValueSetter.setValue(inTMsg.candiTrtyOrgCd, pMsgDtl.orgCd.getValue());
//                    ZYPEZDItemValueSetter.setValue(inTMsg.candiTrtyOrgNm, pMsgDtl.orgNm.getValue());
//                    insertList.add(inTMsg);
//                }
//
//                excecuteInsertForTrty(insertList);
//
//                // update DS_ACCT_RVW_PROS
//                if (dupOrgNmList.size() > 0) {
//                    StringBuilder orgNm = new StringBuilder();
//                    for (String dupOrgNm : dupOrgNmList) {
//
//                        if (orgNm.length() + dupOrgNm.length() + 1 > MATCH_ACCT_LOC_NUM_MAX_LENGTH) {
//                            break;
//                        }
//
//                        if (orgNm.length() > 0) {
//                            orgNm.append(",");
//                        }
//                        orgNm.append(dupOrgNm);
//                    }
//                    DS_ACCT_RVW_PROSTMsg inTMsg = new DS_ACCT_RVW_PROSTMsg();
//                    ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
//                    ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctRvwProsPk, rsSet.getBigDecimal("DS_ACCT_RVW_PROS_PK"));
//
//                    inTMsg = (DS_ACCT_RVW_PROSTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);
//
//                    ZYPEZDItemValueSetter.setValue(inTMsg.candiTrtyNm, orgNm.toString());
//
//                    EZDTBLAccessor.update(inTMsg);
//
//                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
//                        this.totalErrCount++;
//                        throw new S21AbendException(NMAM0177E, new String[] {inTMsg.getTableName() });
//                    }
//
//                    commit();
//                    this.totalNmlCount++;
//                } else {
//                    this.totalNmlCount++;
//                }

            }
            if (mmwTrtyList.size() > 0) {
                callAcctTrtyRuleRelnApi(mmwTrtyList, pMsg);
            }
// 2016/10/24 CSA-QC#15214 Mod End
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }

    }

    // Add Start 2018/07/11 QC#25871
    private void doProcessMode3() {
        S21StopWatch sw = new S21StopWatch();
        int count = 0;
        
        if (TIME_MODE_02.equals(this.varUser2)) {
            sw.start();
            int totalDeleteCount = deleteDupCandiLocWrk();
            sw.stop();
            S21InfoLogOutput.println(String.format("DELETE DUP_CANDI_LOC_WRK Finish count:%d, [%d msec]", totalDeleteCount, sw.getElapsedMilliSec()));
            commit();
        }

        //
        // Truncate
        //

        // DUP_CANDI_MATCH_WRK
        sw.start();
        truncateCandiMatchWrk();
        sw.stop();
        S21InfoLogOutput.println(String.format("TRUNCATE DUP_CANDI_MATCH_WRK Finish [%d msec]", sw.getElapsedMilliSec()));
        
        // DUP_PROS_CANDI_MATCH_WRK
        sw.start();
        truncateProsCandiMatchWrk();
        sw.stop();
        S21InfoLogOutput.println(String.format("TRUNCATE DUP_PROS_CANDI_MATCH_WRK Finish [%d msec]", sw.getElapsedMilliSec()));

        //
        // Customer -> DUP_CANDI_MATCH_WRK
        //
        sw.start();
        count = insertCandiMatchWrk();
        commit();
        sw.stop();
        S21InfoLogOutput.println(String.format("INSERT DUP_CANDI_MATCH_WRK Finish count:%d [%d msec]", count, sw.getElapsedMilliSec()));

        //
        // Prospect -> DUP_PROS_CANDI_MATCH_WRK
        //
        sw.start();
        count = insertProsCandiMatchWrk();
        commit();
        sw.stop();
        S21InfoLogOutput.println(String.format("INSERT DUP_PROS_CANDI_MATCH_WRK Finish count:%d [%d msec]", count, sw.getElapsedMilliSec()));

        //
        // Update statistics
        //

        // DUP_CANDI_MATCH_WRK
        sw.start();
        updateStatistics(DUP_CANDI_MATCH_WRK);
        sw.stop();
        S21InfoLogOutput.println(String.format("Update statistics Finish: DUP_CANDI_MATCH_WRK [%d msec]",sw.getElapsedMilliSec()));

        // DUP_PROS_CANDI_MATCH_WRK
        sw.start();
        updateStatistics(DUP_PROS_CANDI_MATCH_WRK);
        sw.stop();
        S21InfoLogOutput.println(String.format("Update statistics Finish: DUP_PROS_CANDI_MATCH_WRK [%d msec]",sw.getElapsedMilliSec()));

        //
        // Duplication Check AcctNm, Location
        //

        List<DUP_CANDI_LOC_WRKTMsg>  registList = new ArrayList<DUP_CANDI_LOC_WRKTMsg>();

        // Pros <=> Pros
        sw.start();
        List<Map<String, String>> list = dupChkAcctLocPros2Pros();
        sw.stop();
        S21InfoLogOutput.println(String.format("Duplication Check AcctNm, Location Finish(Pros <=> Pros) count:%d [%d msec]", list.size(), sw.getElapsedMilliSec()));

        sw.start();
        count = 0;
        for (Map<String, String> dat : list){
            List<DUP_CANDI_LOC_WRKTMsg> tMsgs = toDupCandiLocWrkForAccNmLoc(dat, DS_LOC_TP.PROSPECT);
            count += tMsgs.size();
            registList.addAll(tMsgs);
            insertDupCandiLocWrk(registList);
        }
        sw.stop();
        S21InfoLogOutput.println(String.format("ToDupCandiLocWrkForAccNmLoc Finish(Pros <=> Pros) count:%d [%d msec]", count, sw.getElapsedMilliSec()));

        // Pros <=> Cust
        sw.start();
        list = dupChkAcctLocPros2Cust();
        sw.stop();
        S21InfoLogOutput.println(String.format("Duplication Check AcctNm, Location Finish(Pros <=> Cust) count:%d [%d msec]", list.size(), sw.getElapsedMilliSec()));

        sw.start();
        count = 0;
        for (Map<String, String> dat : list){
            List<DUP_CANDI_LOC_WRKTMsg> tMsgs = toDupCandiLocWrkForAccNmLoc(dat, DS_LOC_TP.CUSTOMER);
            count += tMsgs.size();
            registList.addAll(tMsgs);
            insertDupCandiLocWrk(registList);
        }
        sw.stop();
        S21InfoLogOutput.println(String.format("ToDupCandiLocWrkForAccNmLoc Finish(Pros <=> Cust) count:%d [%d msec]", count, sw.getElapsedMilliSec()));

        //
        // Duplication Check Duns Num
        //

        // Pros <=> Pros
        sw.start();
        list = dupChkDunsNumPros2Pros();
        sw.stop();
        S21InfoLogOutput.println(String.format("Duplication Check Duns Num Finish(Pros <=> Pros) count:%d [%d msec]", list.size(), sw.getElapsedMilliSec()));

        sw.start();
        count = 0;
        for (Map<String, String> dat : list){
            List<DUP_CANDI_LOC_WRKTMsg> tMsgs = toDupCandiLocWrkForDunsNum(dat, DS_LOC_TP.PROSPECT);
            count += tMsgs.size();
            registList.addAll(tMsgs);
            insertDupCandiLocWrk(registList);
        }
        sw.stop();
        S21InfoLogOutput.println(String.format("ToDupCandiLocWrkForDunsNum Finish(Pros <=> Pros) count:%d [%d msec]", count, sw.getElapsedMilliSec()));

        // Pros <=> Cust
        sw.start();
        list = dupChkDunsNumPros2Cust();
        sw.stop();
        S21InfoLogOutput.println(String.format("Duplication Check Duns Num Finish(Pros <=> Cust) count:%d [%d msec]", list.size(), sw.getElapsedMilliSec()));

        sw.start();
        count = 0;
        for (Map<String, String> dat : list){
            List<DUP_CANDI_LOC_WRKTMsg> tMsgs = toDupCandiLocWrkForDunsNum(dat, DS_LOC_TP.CUSTOMER);
            count += tMsgs.size();
            registList.addAll(tMsgs);
            insertDupCandiLocWrk(registList);
        }
        sw.stop();
        S21InfoLogOutput.println(String.format("ToDupCandiLocWrkForDunsNum Finish(Pros <=> Cust) count:%d [%d msec]", count, sw.getElapsedMilliSec()));

        //
        // Duplication Check Gln Num
        //

        // Pros <=> Pros
        sw.start();
        list = dupChkGlnNumPros2Pros();
        sw.stop();
        S21InfoLogOutput.println(String.format("Duplication Check Gln Num Finish(Pros <=> Pros) count:%d [%d msec]", list.size(), sw.getElapsedMilliSec()));

        sw.start();
        count = 0;
        for (Map<String, String> dat : list){
            List<DUP_CANDI_LOC_WRKTMsg> tMsgs = toDupCandiLocWrkForGlnNum(dat, DS_LOC_TP.PROSPECT);
            count += tMsgs.size();
            registList.addAll(tMsgs);
            insertDupCandiLocWrk(registList);
        }
        sw.stop();
        S21InfoLogOutput.println(String.format("ToDupCandiLocWrkForGlnNum Finish(Pros <=> Pros) count:%d [%d msec]", count, sw.getElapsedMilliSec()));

         // Pros <=> Cust
        sw.start();
        list = dupChkGlnNumPros2Cust();
        sw.stop();
        S21InfoLogOutput.println(String.format("Duplication Check Gln Num Finish(Pros <=> Cust) count:%d [%d msec]", list.size(), sw.getElapsedMilliSec()));

        sw.start();
        count = 0;
        for (Map<String, String> dat : list){
            List<DUP_CANDI_LOC_WRKTMsg> tMsgs = toDupCandiLocWrkForGlnNum(dat, DS_LOC_TP.CUSTOMER);
            count += tMsgs.size();
            registList.addAll(tMsgs);
            insertDupCandiLocWrk(registList);
        }
        sw.stop();
        S21InfoLogOutput.println(String.format("ToDupCandiLocWrkForGlnNum Finish(Pros <=> Cust) count:%d [%d msec]", count, sw.getElapsedMilliSec()));

        insertDupCandiLocWrk(registList, false);
    }
    // Add End 2018/07/11 QC#25871

    // Add Start 2018/07/11 QC#25871
    private void insertDupCandiLocWrk(List<DUP_CANDI_LOC_WRKTMsg> registList){
        insertDupCandiLocWrk(registList, true);
    }

    private void insertDupCandiLocWrk(List<DUP_CANDI_LOC_WRKTMsg> registList, boolean chkCount){

        if (registList.size() <= 0 ){
            return;
        }
        
        if (chkCount){
            if (registList.size() < this.commitCount){
                return;
            }
        }

        int insertCount = S21FastTBLAccessor.insert(registList.toArray(new DUP_CANDI_LOC_WRKTMsg[registList.size()]));
        int registListSize = registList.size();

        if (insertCount != registListSize) {
            rollback();
            this.totalErrCount += registListSize;
            throw new S21AbendException(NMAM0176E, new String[] {"DUP_CANDI_LOC_WRK" });
        }

        this.totalNmlCount += registListSize;
        registList.clear();
        commit();

    }
    // Add End 2018/07/11 QC#25871

//  private void doProcessMode3() {
//      if (TIME_MODE_02.equals(this.varUser2)) {
////2016/10/24 CSA-QC#15214 Mod Start
////          List<BigDecimal> locWrkPkList = getDupCandiLocWrk();
////          List<DUP_CANDI_LOC_WRKTMsg> delList = new ArrayList<DUP_CANDI_LOC_WRKTMsg>(locWrkPkList.size());
////          for (BigDecimal locWrkPk : locWrkPkList) {
////              // delete DUP_CANDI_LOC_WRK
////              DUP_CANDI_LOC_WRKTMsg inTMsg = new DUP_CANDI_LOC_WRKTMsg();
////              ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
////              ZYPEZDItemValueSetter.setValue(inTMsg.dupCandiLocWrkPk, locWrkPk);
////
////              inTMsg = (DUP_CANDI_LOC_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);
////
////              delList.add(inTMsg);
////          }
////
////          if (delList.size() > 0) {
////              int resultCount = S21FastTBLAccessor.removeLogical(delList.toArray(new DUP_CANDI_LOC_WRKTMsg[0]));
////
////              if (resultCount != delList.size()) {
////                  throw new S21AbendException(NMAM0177E, new String[] {delList.get(0).getTableName() });
////              }
////          }
//          int totalDeleteCount = deleteDupCandiLocWrk();
//          S21InfoLogOutput.println("DUP_CANDI_LOC_WRK DELETE_COUNT:" + totalDeleteCount);
//          commit();
////2016/10/24 CSA-QC#15214 Mod End
//      }
//
//      S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
//
//      PreparedStatement stmt = null;
//      ResultSet rsSet = null;
//
//      execParam.setFetchSize(DEFAULT_FETCH_SIZE);
//      execParam.setMaxRows(0);
//      execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
//      execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
//      execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
//
//      Map<String, Object> ssmParam = new HashMap<String, Object>();
//      ssmParam.put("glblCmpyCd", this.glblCmpyCd);
//      ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
//      // Add Start 2018/05/23 QC#23142-3,QC#23150-2
//      ssmParam.put("rgtnStsCd_PC", RGTN_STS.PENDING_COMPLETION);
//      // Add End 2018/05/23 QC#23142-3,QC#23150-2
//
//      try {
//          stmt = this.ssmLLClient.createPreparedStatement("getPros", ssmParam, execParam);
//          rsSet = stmt.executeQuery();
//
//          while (rsSet.next()) {
//              // Mod Start 2018/04/11 QC#23142-2
//              //List<Map<String, String>> dupAddrExactList = dupCheckForProsAddrExactMatch(rsSet);
//              List<Map<String, String>> dupAddrExactList = null;
//              // Mod End 2018/04/11 QC#23142-2
//
//              List<Map<String, String>> dupAddrPartialList = null;
//
//              // Del Start 2018/04/11 QC#23142-2
//              //if (dupAddrExactList == null || dupAddrExactList.size() <= 0) {
//                  // Del End 2018/04/11 QC#23142-2
//                  dupAddrPartialList = dupCheckForProsAddrPartialMatch(rsSet);
//                  // Del Start 2018/04/11 QC#23142-2
//              //}
//              // Del End 2018/04/11 QC#23142-2
//
//              // Add Start 2018/04/11 QC#23142-2
//              dupAddrExactList = selectProsAddrExactMatch(rsSet, dupAddrPartialList);
//
//              if (dupAddrExactList != null && dupAddrExactList.size() > 0) {
//                  dupAddrPartialList = null;
//              }
//              // Add End 2018/04/11 QC#23142-2
//
//              List<Map<String, String>> dupDunsExactList = dupCheckForDuns(rsSet.getString("DUNS_NUM"), rsSet.getBigDecimal("DS_ACCT_PROS_PK"));
//
//              List<Map<String, String>> dupGlnExactList = dupCheckForGln(rsSet.getString("GLN_NUM"), rsSet.getBigDecimal("DS_ACCT_PROS_PK"));
//
//              List<DUP_CANDI_LOC_WRKTMsg> insertList = new ArrayList<DUP_CANDI_LOC_WRKTMsg>();
//
//              if (dupAddrExactList != null) {
//                  for (Map<String, String> dupAddrExact : dupAddrExactList) {
//                      DUP_CANDI_LOC_WRKTMsg inTMsg = createDupCandiLocWrkTMsgForPros(rsSet.getString("DS_ACCT_NUM"), rsSet.getString("LOC_NUM"), dupAddrExact);
//
//                      ZYPEZDItemValueSetter.setValue(inTMsg.exactMatchFlg, ZYPConstant.FLG_ON_Y);
//                      ZYPEZDItemValueSetter.setValue(inTMsg.prtlMatchFlg, ZYPConstant.FLG_OFF_N);
//                      ZYPEZDItemValueSetter.setValue(inTMsg.dunsMatchFlg, ZYPConstant.FLG_OFF_N);
//                      ZYPEZDItemValueSetter.setValue(inTMsg.glnMatchFlg, ZYPConstant.FLG_OFF_N);
//                      insertList.add(inTMsg);
//                  }
//              }
//
//              if (dupAddrPartialList != null) {
//                  for (Map<String, String> dupAddrPartial : dupAddrPartialList) {
//                      DUP_CANDI_LOC_WRKTMsg inTMsg = createDupCandiLocWrkTMsgForPros(rsSet.getString("DS_ACCT_NUM"), rsSet.getString("LOC_NUM"), dupAddrPartial);
//
//                      ZYPEZDItemValueSetter.setValue(inTMsg.exactMatchFlg, ZYPConstant.FLG_OFF_N);
//                      ZYPEZDItemValueSetter.setValue(inTMsg.prtlMatchFlg, ZYPConstant.FLG_ON_Y);
//                      ZYPEZDItemValueSetter.setValue(inTMsg.dunsMatchFlg, ZYPConstant.FLG_OFF_N);
//                      ZYPEZDItemValueSetter.setValue(inTMsg.glnMatchFlg, ZYPConstant.FLG_OFF_N);
//                      insertList.add(inTMsg);
//                  }
//              }
//
//              if (dupDunsExactList != null) {
//                  for (Map<String, String> dupDunsExact : dupDunsExactList) {
//                      DUP_CANDI_LOC_WRKTMsg inTMsg = createDupCandiLocWrkTMsgForPros(rsSet.getString("DS_ACCT_NUM"), rsSet.getString("LOC_NUM"), dupDunsExact);
//
//                      ZYPEZDItemValueSetter.setValue(inTMsg.exactMatchFlg, ZYPConstant.FLG_OFF_N);
//                      ZYPEZDItemValueSetter.setValue(inTMsg.prtlMatchFlg, ZYPConstant.FLG_OFF_N);
//                      ZYPEZDItemValueSetter.setValue(inTMsg.dunsMatchFlg, ZYPConstant.FLG_ON_Y);
//                      ZYPEZDItemValueSetter.setValue(inTMsg.glnMatchFlg, ZYPConstant.FLG_OFF_N);
//                      insertList.add(inTMsg);
//                  }
//              }
//
//              if (dupGlnExactList != null) {
//                  for (Map<String, String> dupGlnExact : dupGlnExactList) {
//                      DUP_CANDI_LOC_WRKTMsg inTMsg = createDupCandiLocWrkTMsgForPros(rsSet.getString("DS_ACCT_NUM"), rsSet.getString("LOC_NUM"), dupGlnExact);
//
//                      ZYPEZDItemValueSetter.setValue(inTMsg.exactMatchFlg, ZYPConstant.FLG_OFF_N);
//                      ZYPEZDItemValueSetter.setValue(inTMsg.prtlMatchFlg, ZYPConstant.FLG_OFF_N);
//                      ZYPEZDItemValueSetter.setValue(inTMsg.dunsMatchFlg, ZYPConstant.FLG_OFF_N);
//                      ZYPEZDItemValueSetter.setValue(inTMsg.glnMatchFlg, ZYPConstant.FLG_ON_Y);
//                      insertList.add(inTMsg);
//                  }
//              }
//
//              excecuteInsertForDup(insertList);
//
//              // Add Start 2018/04/11 QC#23142-2
//              int remainder = (this.totalNmlCount + 1) % DEFAULT_FETCH_SIZE;
//              if (remainder == 0) {
//                  // Add End 2018/04/11 QC#23142-2
//              commit();
//              // Add Start 2018/04/11 QC#23142-2
//              }
//              // Add End 2018/04/11 QC#23142-2
//
//              this.totalNmlCount++;
//
//          }
//
//          // Add Start 2018/04/11 QC#23142-2
//          commit();
//          // Add End 2018/04/11 QC#23142-2
//      } catch (SQLException e) {
//          super.sqlExceptionHandler(e);
//      } finally {
//          S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
//      }
//
//  }

    // Add Start 2018/04/11 QC#23142-2
    /**
     * @param rsSet ResultSet
     * @param dupAddrPartial List<Map<String, String>>
     * @return List<Map<String, String>>
     * @throws SQLException
     */
    private List<Map<String, String>> selectProsAddrExactMatch(ResultSet rsSet, //
            List<Map<String, String>> dupAddrPartialList) throws SQLException {
        List<Map<String, String>> dupAddrExactList = null;

        if (dupAddrPartialList == null) {
            return dupAddrExactList;
        }

        // Select exact match record from partial list
        for (Map<String, String> dupAddrPartial : dupAddrPartialList) {
            if (isProsAddrExactMatch(rsSet, dupAddrPartial)) {
                if (dupAddrExactList == null) {
                    dupAddrExactList = new ArrayList<Map<String, String>>();
                }
                dupAddrExactList.add(dupAddrPartial);
            }
        }

        return dupAddrExactList;
    }

    /**
     * @param rsSet ResultSet
     * @param dupAddrPartial Map<String, String>
     * @return boolean
     * @throws SQLException
     */
    private boolean isProsAddrExactMatch(ResultSet rsSet, Map<String, String> dupAddrPartial) throws SQLException {
        String conditionFirstLineAddr = rsSet.getString("FIRST_LINE_ADDR");
        String conditionPostCd = rsSet.getString("POST_CD");
        String conditionDsAcctNm = rsSet.getString("DS_ACCT_NM");

        String dupAddrFirstLineAddr = dupAddrPartial.get("FIRST_LINE_ADDR");
        String dupAddrPostCd = dupAddrPartial.get("POST_CD");
        String dupAddrDsAcctNm = dupAddrPartial.get("DS_ACCT_NM");

        if (!conditionFirstLineAddr.equals(dupAddrFirstLineAddr)) {
            return false;
        }

        if (!conditionPostCd.equals(dupAddrPostCd)) {
            return false;
        }

        if (!conditionDsAcctNm.equals(dupAddrDsAcctNm)) {
            return false;
        }

        return true;
    }
    // Add End 2018/04/11 QC#23142-2

    private List<Map<String, String>> dupCheckForAddrExactMatch(ResultSet rsSet) throws SQLException {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // Add Start 2018/05/23 QC#23142-3,QC#23150-2
        ssmParam.put("rgtnStsCd_PC", RGTN_STS.PENDING_COMPLETION);
        // Add End 2018/05/23 QC#23142-3,QC#23150-2
        // 2017/12/15 CSA-QC#22246 Mod Start
        // ssmParam.put("firstLineAddr", rsSet.getString("BEF_BILL_TO_FIRST_LINE_ADDR"));
        ssmParam.put("firstLineAddr", rsSet.getString("BEF_LOC_FIRST_LINE_ADDR"));
        // 2017/12/15 CSA-QC#22246 Mod End
        ssmParam.put("exactFlg", ZYPConstant.FLG_ON_Y);
        // 2017/12/15 CSA-QC#22246 Mod Start
        // ssmParam.put("ctyAddr", rsSet.getString("BEF_BILL_TO_CTY_ADDR"));
        // ssmParam.put("stCd", getStCd(rsSet.getString("BEF_BILL_TO_ST_CD")));
        // ssmParam.put("postCd", rsSet.getString("BEF_BILL_TO_POST_CD"));
        ssmParam.put("ctyAddr", rsSet.getString("BEF_SHIP_TO_CTY_ADDR"));
        ssmParam.put("stCd", getStCd(rsSet.getString("BEF_SHIP_TO_ST_NM")));
        ssmParam.put("postCd", rsSet.getString("BEF_SHIP_TO_POST_CD"));
        // 2017/12/15 CSA-QC#22246 Mod End
        ssmParam.put("ctryCd", getCtryCd(rsSet.getString("BEF_CTRY_NM")));
        ssmParam.put("dsAcctNm", rsSet.getString("BEF_DS_ACCT_NM"));
        ssmParam.put("tableNmDac", DS_LOC_TP.CUSTOMER);
        ssmParam.put("tableNmDap", DS_LOC_TP.PROSPECT);
        ssmParam.put("tableNmDarp", DS_LOC_TP.UNDER_REVIEW);
        ssmParam.put("isMode01", ZYPConstant.FLG_ON_Y);
        ssmParam.put("dsAcctRvwProsPk", rsSet.getBigDecimal("DS_ACCT_RVW_PROS_PK"));

        // List<Map<String, String>> dupList = null;
        // 2017/12/15 CSA-QC#22246 Mod Start
        // if (!ZYPCommonFunc.hasValue(rsSet.getString("BEF_BILL_TO_FIRST_LINE_ADDR")) //
        //         || !ZYPCommonFunc.hasValue(rsSet.getString("BEF_BILL_TO_CTY_ADDR")) //
        //         || !ZYPCommonFunc.hasValue(rsSet.getString("BEF_BILL_TO_POST_CD")) //
        if (!ZYPCommonFunc.hasValue(rsSet.getString("BEF_LOC_FIRST_LINE_ADDR")) //
                || !ZYPCommonFunc.hasValue(rsSet.getString("BEF_SHIP_TO_CTY_ADDR")) //
                || !ZYPCommonFunc.hasValue(rsSet.getString("BEF_SHIP_TO_POST_CD")) //
        // 2017/12/15 CSA-QC#22246 Mod End
                || !ZYPCommonFunc.hasValue(rsSet.getString("BEF_CTRY_NM")) //
                || !ZYPCommonFunc.hasValue(rsSet.getString("BEF_DS_ACCT_NM"))) {

            return (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getDupLocOnlyRvw", ssmParam);

        }
        return (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getDupLoc", ssmParam);
    }

    private List<Map<String, String>> dupCheckForAddrPartialMatch(ResultSet rsSet) throws SQLException {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // Add Start 2018/05/23 QC#23142-3,QC#23150-2
        ssmParam.put("rgtnStsCd_PC", RGTN_STS.PENDING_COMPLETION);
        // Add End 2018/05/23 QC#23142-3,QC#23150-2
        // 2017/12/15 CSA-QC#22246 Mod Start
        // ssmParam.put("firstLineAddr", rsSet.getString("BEF_BILL_TO_FIRST_LINE_ADDR"));
        ssmParam.put("firstLineAddr", rsSet.getString("BEF_LOC_FIRST_LINE_ADDR"));
        // 2017/12/15 CSA-QC#22246 Mod End
        ssmParam.put("exactFlg", ZYPConstant.FLG_OFF_N);
        // 2017/12/15 CSA-QC#22246 Mod Start
        // ssmParam.put("ctyAddr", rsSet.getString("BEF_BILL_TO_CTY_ADDR"));
        // ssmParam.put("stCd", getStCd(rsSet.getString("BEF_BILL_TO_ST_CD")));
        // ssmParam.put("postCd", rsSet.getString("BEF_BILL_TO_POST_CD"));
        ssmParam.put("ctyAddr", rsSet.getString("BEF_SHIP_TO_CTY_ADDR"));
        ssmParam.put("stCd", getStCd(rsSet.getString("BEF_SHIP_TO_ST_NM")));
        ssmParam.put("postCd", rsSet.getString("BEF_SHIP_TO_POST_CD"));
        // 2017/12/15 CSA-QC#22246 Mod End
        ssmParam.put("ctryCd", getCtryCd(rsSet.getString("BEF_CTRY_NM")));
        ssmParam.put("dsAcctNm", rsSet.getString("BEF_DS_ACCT_NM"));
        ssmParam.put("tableNmDac", DS_LOC_TP.CUSTOMER);
        ssmParam.put("tableNmDap", DS_LOC_TP.PROSPECT);
        ssmParam.put("tableNmDarp", DS_LOC_TP.UNDER_REVIEW);
        ssmParam.put("lineSubstrLength", this.lineSubstrLength);
        ssmParam.put("nmSubstrLength", this.nmSubstrLength);
        ssmParam.put("isMode01", ZYPConstant.FLG_ON_Y);
        ssmParam.put("dsAcctRvwProsPk", rsSet.getBigDecimal("DS_ACCT_RVW_PROS_PK"));

        // List<Map<String, String>> dupList = null;
        // 2017/12/15 CSA-QC#22246 Mod Start
        // if (!ZYPCommonFunc.hasValue(rsSet.getString("BEF_BILL_TO_FIRST_LINE_ADDR")) //
        //         || !ZYPCommonFunc.hasValue(rsSet.getString("BEF_BILL_TO_CTY_ADDR")) //
        //         || !ZYPCommonFunc.hasValue(rsSet.getString("BEF_BILL_TO_POST_CD")) //
        if (!ZYPCommonFunc.hasValue(rsSet.getString("BEF_LOC_FIRST_LINE_ADDR")) //
                || !ZYPCommonFunc.hasValue(rsSet.getString("BEF_SHIP_TO_CTY_ADDR")) //
                || !ZYPCommonFunc.hasValue(rsSet.getString("BEF_SHIP_TO_POST_CD")) //
        // 2017/12/15 CSA-QC#22246 Mod End
                || !ZYPCommonFunc.hasValue(rsSet.getString("BEF_CTRY_NM")) //
                || !ZYPCommonFunc.hasValue(rsSet.getString("BEF_DS_ACCT_NM"))) {

            return (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getDupLocOnlyRvw", ssmParam);

        }
        return (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getDupLoc", ssmParam);
    }

    // Del Start 2018/04/11 QC#23142-2
//    private List<Map<String, String>> dupCheckForProsAddrExactMatch(ResultSet rsSet) throws SQLException {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
//        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
//        ssmParam.put("firstLineAddr", rsSet.getString("FIRST_LINE_ADDR"));
//        ssmParam.put("exactFlg", ZYPConstant.FLG_ON_Y);
//        ssmParam.put("ctyAddr", rsSet.getString("CTY_ADDR"));
//        ssmParam.put("stCd", rsSet.getString("ST_CD"));
//        ssmParam.put("postCd", rsSet.getString("POST_CD"));
//        ssmParam.put("ctryCd", rsSet.getString("CTRY_CD"));
//        ssmParam.put("dsAcctNm", rsSet.getString("DS_ACCT_NM"));
//        ssmParam.put("dsAcctProsPk", rsSet.getBigDecimal("DS_ACCT_PROS_PK"));
//        ssmParam.put("tableNmDac", DS_LOC_TP.CUSTOMER);
//        ssmParam.put("tableNmDap", DS_LOC_TP.PROSPECT);
//        ssmParam.put("tableNmDarp", DS_LOC_TP.UNDER_REVIEW);
//
//        return (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getDupLoc", ssmParam);
//    }
    // Del End 2018/04/11 QC#23142-2

    private List<Map<String, String>> dupCheckForProsAddrPartialMatch(ResultSet rsSet) throws SQLException {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // Add Start 2018/05/23 QC#23142-3,QC#23150-2
        ssmParam.put("rgtnStsCd_PC", RGTN_STS.PENDING_COMPLETION);
        // Add End 2018/05/23 QC#23142-3,QC#23150-2
        ssmParam.put("firstLineAddr", rsSet.getString("FIRST_LINE_ADDR"));
        ssmParam.put("exactFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("ctyAddr", rsSet.getString("CTY_ADDR"));
        ssmParam.put("stCd", rsSet.getString("ST_CD"));
        ssmParam.put("postCd", rsSet.getString("POST_CD"));
        ssmParam.put("ctryCd", rsSet.getString("CTRY_CD"));
        ssmParam.put("dsAcctNm", rsSet.getString("DS_ACCT_NM"));
        ssmParam.put("dsAcctProsPk", rsSet.getBigDecimal("DS_ACCT_PROS_PK"));
        ssmParam.put("tableNmDac", DS_LOC_TP.CUSTOMER);
        ssmParam.put("tableNmDap", DS_LOC_TP.PROSPECT);
        ssmParam.put("tableNmDarp", DS_LOC_TP.UNDER_REVIEW);
        ssmParam.put("lineSubstrLength", this.lineSubstrLength);
        ssmParam.put("nmSubstrLength", this.nmSubstrLength);

        return (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getDupLoc", ssmParam);
    }

    private List<Map<String, String>> dupCheckForDuns(String dunsNum, BigDecimal pk) throws SQLException {
        if (!ZYPCommonFunc.hasValue(dunsNum)) {
            return null;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // Add Start 2018/05/23 QC#23142-3,QC#23150-2
        ssmParam.put("rgtnStsCd_PC", RGTN_STS.PENDING_COMPLETION);
        // Add End 2018/05/23 QC#23142-3,QC#23150-2
        ssmParam.put("dunsNum", dunsNum);
        ssmParam.put("tableNmDac", DS_LOC_TP.CUSTOMER);
        ssmParam.put("tableNmDap", DS_LOC_TP.PROSPECT);
        ssmParam.put("tableNmDarp", DS_LOC_TP.UNDER_REVIEW);

        if (DUP_MODE_01.equals(this.varUser1)) {
            ssmParam.put("isMode01", ZYPConstant.FLG_ON_Y);
            ssmParam.put("dsAcctRvwProsPk", pk);
        } else if (DUP_MODE_03.equals(this.varUser1)) {
            ssmParam.put("dsAcctProsPk", pk);
        }

        return (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getDupLoc", ssmParam);
    }

    private List<Map<String, String>> dupCheckForGln(String glnNum, BigDecimal pk) throws SQLException {
        if (!ZYPCommonFunc.hasValue(glnNum)) {
            return null;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // Add Start 2018/05/23 QC#23142-3,QC#23150-2
        ssmParam.put("rgtnStsCd_PC", RGTN_STS.PENDING_COMPLETION);
        // Add End 2018/05/23 QC#23142-3,QC#23150-2
        ssmParam.put("glnNum", glnNum);
        ssmParam.put("tableNmDac", DS_LOC_TP.CUSTOMER);
        ssmParam.put("tableNmDap", DS_LOC_TP.PROSPECT);
        ssmParam.put("tableNmDarp", DS_LOC_TP.UNDER_REVIEW);

        if (DUP_MODE_01.equals(this.varUser1)) {
            ssmParam.put("isMode01", ZYPConstant.FLG_ON_Y);
            ssmParam.put("dsAcctRvwProsPk", pk);
        } else if (DUP_MODE_03.equals(this.varUser1)) {
            ssmParam.put("dsAcctProsPk", pk);
        }

        return (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getDupLoc", ssmParam);
    }
// 2016/10/24 CSA-QC#15214 Del Start
//    private List<BigDecimal> getDupCandiLocWrk() {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
//        ssmParam.put("dsLocTpCd", DS_LOC_TP.UNDER_REVIEW);
//        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getDupCandiLocWrk", ssmParam);
//    }

//    private List<BigDecimal> getCandiTrtyLocWrk() {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
//        ssmParam.put("dsLocTpCd", DS_LOC_TP.UNDER_REVIEW);
//        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getCandiTrtyLocWrk", ssmParam);
//    }
// 2016/10/24 CSA-QC#15214 Del End
    // 2016/10/24 CSA-QC#15214 Add Start
    private int deleteDupCandiLocWrk() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dsLocTpCd", DS_LOC_TP.UNDER_REVIEW);
        return ssmBatchClientCustom.delete("deleteDupCandiLocWrk", ssmParam);
    }

    private int deleteCandiTrtyLocWrk() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dsLocTpCd", DS_LOC_TP.UNDER_REVIEW);
        return ssmBatchClientCustom.delete("deleteCandiTrtyLocWrk", ssmParam);
    }
    // 2016/10/24 CSA-QC#15214 Add End

    private String getStCd(String stCd) {
        if (!ZYPCommonFunc.hasValue(stCd)) {
            return "";
        }

        STTMsg inTMsg = new STTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.stCd, stCd);

        inTMsg = (STTMsg) S21CodeTableAccessor.findByKey(inTMsg);

        if (inTMsg == null) {
            return null;
        }

        return inTMsg.stCd.getValue();
    }

    private String getCtryCd(String ctryNm) {
        if (!ZYPCommonFunc.hasValue(ctryNm)) {
            return "";
        }

        CTRYTMsg inTMsg = new CTRYTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.ctryDescTxt, ctryNm);

        CTRYTMsgArray inTMsgArray = (CTRYTMsgArray) S21CodeTableAccessor.findByCondition(inTMsg);

        if (inTMsgArray.length() <= 0) {
            return null;
        }

        return inTMsgArray.no(0).ctryCd.getValue();
    }

    private DUP_CANDI_LOC_WRKTMsg createDupCandiLocWrkTMsg(String rvwProsNum, Map<String, String> rtrnMap) {
        DUP_CANDI_LOC_WRKTMsg inTMsg = new DUP_CANDI_LOC_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DUP_CANDI_LOC_WRK_SQ);
        ZYPEZDItemValueSetter.setValue(inTMsg.dupCandiLocWrkPk, pk);
        ZYPEZDItemValueSetter.setValue(inTMsg.dsLocTpCd, DS_LOC_TP.UNDER_REVIEW);
        ZYPEZDItemValueSetter.setValue(inTMsg.rvwProsNum, rvwProsNum);
        ZYPEZDItemValueSetter.setValue(inTMsg.dupCandiLocTpCd, rtrnMap.get("TBL_NM"));

        if (DS_LOC_TP.UNDER_REVIEW.equals(rtrnMap.get("TBL_NM"))) {
            //QC#23150 mod Start
//            ZYPEZDItemValueSetter.setValue(inTMsg.dupCandiRvwProsNum, rtrnMap.get("LOC_NM"));
            ZYPEZDItemValueSetter.setValue(inTMsg.dupCandiRvwProsNum, rtrnMap.get("RVW_PROS_NUM"));
            //QC#23150 mod End
        } else {
            ZYPEZDItemValueSetter.setValue(inTMsg.dupCandiDsAcctNum, rtrnMap.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(inTMsg.dupCandiLocNum, rtrnMap.get("LOC_NUM"));
        }

        return inTMsg;
    }

    private DUP_CANDI_LOC_WRKTMsg createDupCandiLocWrkTMsgForPros(String dsAcctNum, String locNum, Map<String, String> rtrnMap) {
        DUP_CANDI_LOC_WRKTMsg inTMsg = new DUP_CANDI_LOC_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DUP_CANDI_LOC_WRK_SQ);
        ZYPEZDItemValueSetter.setValue(inTMsg.dupCandiLocWrkPk, pk);
        ZYPEZDItemValueSetter.setValue(inTMsg.dsLocTpCd, DS_LOC_TP.PROSPECT);
        ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctNum, dsAcctNum);
        ZYPEZDItemValueSetter.setValue(inTMsg.locNum, locNum);
        ZYPEZDItemValueSetter.setValue(inTMsg.dupCandiLocTpCd, rtrnMap.get("TBL_NM"));
        ZYPEZDItemValueSetter.setValue(inTMsg.dupCandiDsAcctNum, rtrnMap.get("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(inTMsg.dupCandiLocNum, rtrnMap.get("LOC_NUM"));

        return inTMsg;
    }

    /**
     * excecuteInsert
     * @param insertList List<DUP_CANDI_LOC_WRKTMsg>
     */
    private void excecuteInsertForDup(List<DUP_CANDI_LOC_WRKTMsg> insertList) {

        if (insertList == null || insertList.size() <= 0) {
            return;
        }

        int insertCount = S21FastTBLAccessor.insert(insertList.toArray(new DUP_CANDI_LOC_WRKTMsg[insertList.size()]));
        int insertListSize = insertList.size();

        if (insertCount != insertListSize) {
            rollback();
            this.totalErrCount++;
            throw new S21AbendException(NMAM0176E, new String[] {"DUP_CANDI_LOC_WRK" });
        }
    }

    /**
     * excecuteInsert
     * @param insertList List<CANDI_TRTY_LOC_WRKTMsg>
     */
    private void excecuteInsertForTrty(List<CANDI_TRTY_LOC_WRKTMsg> insertList) {

        if (insertList == null || insertList.size() <= 0) {
            return;
        }

        int insertCount = S21FastTBLAccessor.insert(insertList.toArray(new CANDI_TRTY_LOC_WRKTMsg[insertList.size()]));
        int insertListSize = insertList.size();

        if (insertCount != insertListSize) {
            rollback();
            this.totalErrCount++;
            throw new S21AbendException(NMAM0176E, new String[] {"CANDI_TRTY_LOC_WRK" });
        }
    }
    // 2016/10/24 CSA-QC#15214 Add Start
    /**
     * Call Account Territory Rule Relation API
     * @param mmwTrtyList List<Map<String, String>>
     * @param nmzc2710Api nmzc2710Api
     */
    private void callAcctTrtyRuleRelnApi(List<Map<String, String>> mmwTrtyList, NMZC271001PMsg nmzc2710Pmsg) {

        NMZC271001 nmzc2710Api = new NMZC271001();
        nmzc2710Api.execute(nmzc2710Pmsg, ONBATCH_TYPE.BATCH);

        List<String> errList = S21ApiUtil.getXxMsgIdList(nmzc2710Pmsg);
        if (errList != null && errList.size() > 0) {
            for (String err : errList) {
                S21InfoLogOutput.println(err);
            }
        } else {
            for (int i = 0; i < mmwTrtyList.size(); i++) {
                String rvwProsNum = mmwTrtyList.get(i).get("RVW_PROS_NUM");
                BigDecimal dsAcctRvwProsPk = new BigDecimal(mmwTrtyList.get(i).get("DS_ACCT_RVW_PROS_PK_STR"));
                List<String> dupOrgNmList = new ArrayList<String>();
                List<CANDI_TRTY_LOC_WRKTMsg> insertList = new ArrayList<CANDI_TRTY_LOC_WRKTMsg>();
                for (int j = 0; j < nmzc2710Pmsg.territoryListBatch.getValidCount(); j++) {
                    NMZC271001_territoryListBatchPMsg subPMsg = nmzc2710Pmsg.territoryListBatch.no(j);
                    int rowNumInPMsg = subPMsg.xxRowNum.getValueInt();
                    if (i == rowNumInPMsg) {
                        // To set the CANDI_TRTY_LOC_WRK for the insert.
                        CANDI_TRTY_LOC_WRKTMsg inTMsg = new CANDI_TRTY_LOC_WRKTMsg();
                        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                        BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CANDI_TRTY_LOC_WRK_SQ);
                        ZYPEZDItemValueSetter.setValue(inTMsg.candiTrtyLocWrkPk, pk);
                        ZYPEZDItemValueSetter.setValue(inTMsg.dsLocTpCd, DS_LOC_TP.UNDER_REVIEW);
                        ZYPEZDItemValueSetter.setValue(inTMsg.rvwProsNum, rvwProsNum);
                        ZYPEZDItemValueSetter.setValue(inTMsg.candiTrtyOrgCd, subPMsg.orgCd.getValue());
                        ZYPEZDItemValueSetter.setValue(inTMsg.candiTrtyOrgNm, subPMsg.orgNm.getValue());
                        insertList.add(inTMsg);
                        if (!dupOrgNmList.contains(subPMsg.orgNm.getValue())) {
                            dupOrgNmList.add(subPMsg.orgNm.getValue());
                        }
                    }
                }
                // insert CANDI_TRTY_LOC_WRK
                excecuteInsertForTrty(insertList);
                // update DS_ACCT_RVW_PROS
                if (dupOrgNmList.size() > 0) {
                    StringBuilder orgNm = new StringBuilder();
                    for (String dupOrgNm : dupOrgNmList) {

                        if (orgNm.length() + dupOrgNm.length() + 1 > MATCH_ACCT_LOC_NUM_MAX_LENGTH) {
                            break;
                        }

                        if (orgNm.length() > 0) {
                            orgNm.append(",");
                        }
                        orgNm.append(dupOrgNm);
                    }
                    dupOrgNmList.clear();
                    DS_ACCT_RVW_PROSTMsg inTMsg = new DS_ACCT_RVW_PROSTMsg();
                    ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctRvwProsPk, dsAcctRvwProsPk);

                    inTMsg = (DS_ACCT_RVW_PROSTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

                    ZYPEZDItemValueSetter.setValue(inTMsg.candiTrtyNm, orgNm.toString());

                    EZDTBLAccessor.update(inTMsg);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                        this.totalErrCount++;
                        throw new S21AbendException(NMAM0177E, new String[] {inTMsg.getTableName() });
                    }

                    commit();
                    this.totalNmlCount++;
                } else {
                    this.totalNmlCount++;
                }
            }

        }
    }
    // 2016/10/24 CSA-QC#15214 Add End

    // Add Start 2018/07/11 QC#25871
    private void truncateCandiMatchWrk() {
        this.ssmBatchClientCustom.delete("truncateCandiMatchWrk", null);
    }
    // Add End 2018/07/11 QC#25871

    // Add Start 2018/07/11 QC#25871
    private void truncateProsCandiMatchWrk() {
        this.ssmBatchClientCustom.delete("truncateProsCandiMatchWrk", null);
    }
    // Add End 2018/07/11 QC#25871

    // Add Start 2018/07/11 QC#25871
    private int insertCandiMatchWrk() {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("nowTime", EZDDBCICarrier.getStartDateTime());
        params.put("timeZone", EZDDBCICarrier.getUpTimeZone());
        params.put("upCmpyCd", EZDDBCICarrier.getUpCmpyCd());
        params.put("userId", EZDDBCICarrier.getUserID());
        params.put("aplId", EZDDBCICarrier.getUppgID());
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("lineSubstrLength", this.lineSubstrLength); 
        params.put("nmSubstrLength", this.nmSubstrLengthNumeric);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
                
        return  this.ssmBatchClientCustom.insert("insertCandiMatchWrk", params);
    }
    // Add End 2018/07/11 QC#25871

    // Add Start 2018/07/11 QC#25871
    private int insertProsCandiMatchWrk() {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("nowTime", EZDDBCICarrier.getStartDateTime());
        params.put("timeZone", EZDDBCICarrier.getUpTimeZone());
        params.put("upCmpyCd", EZDDBCICarrier.getUpCmpyCd());
        params.put("userId", EZDDBCICarrier.getUserID());
        params.put("aplId", EZDDBCICarrier.getUppgID());
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("lineSubstrLength", this.lineSubstrLength); 
        params.put("nmSubstrLength", this.nmSubstrLengthNumeric);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("rgtnStsCd_PC", RGTN_STS.PENDING_COMPLETION);
                
        return  this.ssmBatchClientCustom.insert("insertProsCandiMatchWrk", params);
    }
    // Add End 2018/07/11 QC#25871

    /**
     * updateStatistics
     * @param tableName
     * @return
     */
    private Boolean updateStatistics(String tableName) {
        S21BatchTransactionQuery queryComponet = new S21BatchTransactionQuery();
        String sql = String.format("DBMS_STATS.GATHER_TABLE_STATS ('%s','%s', CASCADE => TRUE); ", this.dbSchema, tableName);

        StringBuilder cmd = new StringBuilder();
        cmd.append("DECLARE ");
        cmd.append("BEGIN ");
        cmd.append(sql);
        cmd.append("END;");

        int num = queryComponet.executeUpdate(cmd.toString());

        if (num < 0) {
            S21InfoLogOutput.println(String.format("Error happened \"%s\"", sql));
            return false;
        } else {
            S21InfoLogOutput.println(String.format("Statistics update finished \"%s\"", sql));
        }
        return true;
    }
    
    
    // Add Start 2018/07/11 QC#25871
    private List<Map<String, String>>  dupChkAcctLocPros2Pros(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);

        return (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("dupChkAcctLocPros2Pros", params);
    }
    // Add End 2018/07/11 QC#25871

    // Add Start 2018/07/11 QC#25871
    private List<Map<String, String>>  dupChkAcctLocPros2Cust(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);

        return (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("dupChkAcctLocPros2Cust", params);
    }
    // Add End 2018/07/11 QC#25871

    // Add Start 2018/07/11 QC#25871
    private List<DUP_CANDI_LOC_WRKTMsg> toDupCandiLocWrkForAccNmLoc(Map<String, String> param, String locTp){
        List<DUP_CANDI_LOC_WRKTMsg> resultList = new ArrayList<DUP_CANDI_LOC_WRKTMsg>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("patlFirstLineAddr", param.get("PRTL_FIRST_LINE_ADDR"));
        params.put("ctyAddr", param.get("CTY_ADDR"));
        params.put("stCd", param.get("ST_CD"));
        params.put("prtlPostCd", param.get("PRTL_POST_CD"));
        params.put("ctryCd", param.get("CTRY_CD"));
        params.put("prtlDsAcctNm", param.get("PRTL_DS_ACCT_NM"));
        params.put("tableNmDac", DS_LOC_TP.CUSTOMER);
        params.put("tableNmDap", DS_LOC_TP.PROSPECT);

        if (DS_LOC_TP.CUSTOMER.equals(locTp)){
            params.put("chkCustomer", ZYPConstant.FLG_ON_Y);
        }

        List<Map<String, String>> list =  (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("toDupCandiLocWrkForAccNmLoc", params);

        for (Map<String, String> dat : list){
            DUP_CANDI_LOC_WRKTMsg tmsg = new DUP_CANDI_LOC_WRKTMsg();

            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
            BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DUP_CANDI_LOC_WRK_SQ);
            ZYPEZDItemValueSetter.setValue(tmsg.dupCandiLocWrkPk, pk);
            ZYPEZDItemValueSetter.setValue(tmsg.dsAcctNum, dat.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(tmsg.locNum, dat.get("LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(tmsg.dsLocTpCd, dat.get("DS_LOC_TP_CD"));
            ZYPEZDItemValueSetter.setValue(tmsg.dupCandiDsAcctNum, dat.get("DUP_CANDI_DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(tmsg.dupCandiLocNum, dat.get("DUP_CANDI_LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(tmsg.dupCandiLocTpCd, dat.get("DUP_CANDI_LOC_TP_CD"));

            if (ZYPConstant.FLG_ON_Y.equals(dat.get("NM_ADDR_EXACT_COND_FLG"))){
                ZYPEZDItemValueSetter.setValue(tmsg.exactMatchFlg,ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(tmsg.prtlMatchFlg,ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(tmsg.exactMatchFlg,ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tmsg.prtlMatchFlg,ZYPConstant.FLG_ON_Y);
            }

            ZYPEZDItemValueSetter.setValue(tmsg.dunsMatchFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tmsg.glnMatchFlg, ZYPConstant.FLG_OFF_N);

            resultList.add(tmsg);
        }

        return resultList;
    }
    // Add End 2018/07/11 QC#25871

    // Add Start 2018/07/11 QC#25871
    private List<Map<String, String>>  dupChkDunsNumPros2Pros(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);

        return (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("dupChkDunsNumPros2Pros", params);
    }
    // Add End 2018/07/11 QC#25871

    // Add Start 2018/07/11 QC#25871
    private List<Map<String, String>>  dupChkDunsNumPros2Cust(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);

        return (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("dupChkDunsNumPros2Cust", params);
    }
    // Add End 2018/07/11 QC#25871

    // Add Start 2018/07/11 QC#25871
    private List<DUP_CANDI_LOC_WRKTMsg> toDupCandiLocWrkForDunsNum(Map<String, String> param, String locTp){
        List<DUP_CANDI_LOC_WRKTMsg> resultList = new ArrayList<DUP_CANDI_LOC_WRKTMsg>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dunsNum", param.get("DUNS_NUM"));
        params.put("tableNmDac", DS_LOC_TP.CUSTOMER);
        params.put("tableNmDap", DS_LOC_TP.PROSPECT);

        if (DS_LOC_TP.CUSTOMER.equals(locTp)){
            params.put("chkCustomer", ZYPConstant.FLG_ON_Y);
        }

        List<Map<String, String>> list =  (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("toDupCandiLocWrkForDunsNum", params);

        for (Map<String, String> dat : list){
            DUP_CANDI_LOC_WRKTMsg tmsg = new DUP_CANDI_LOC_WRKTMsg();

            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
            BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DUP_CANDI_LOC_WRK_SQ);
            ZYPEZDItemValueSetter.setValue(tmsg.dupCandiLocWrkPk, pk);
            ZYPEZDItemValueSetter.setValue(tmsg.dsAcctNum, dat.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(tmsg.locNum, dat.get("LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(tmsg.dsLocTpCd, dat.get("DS_LOC_TP_CD"));
            ZYPEZDItemValueSetter.setValue(tmsg.dupCandiDsAcctNum, dat.get("DUP_CANDI_DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(tmsg.dupCandiLocNum, dat.get("DUP_CANDI_LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(tmsg.dupCandiLocTpCd, dat.get("DUP_CANDI_LOC_TP_CD"));
            ZYPEZDItemValueSetter.setValue(tmsg.exactMatchFlg,ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tmsg.prtlMatchFlg,ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tmsg.dunsMatchFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(tmsg.glnMatchFlg, ZYPConstant.FLG_OFF_N);

            resultList.add(tmsg);
        }

        return resultList;
    }
    // Add End 2018/07/11 QC#25871

    // Add Start 2018/07/11 QC#25871
    private List<Map<String, String>>  dupChkGlnNumPros2Pros(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);

        return (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("dupChkGlnNumPros2Pros", params);
    }
    // Add End 2018/07/11 QC#25871

    // Add Start 2018/07/11 QC#25871
    private List<Map<String, String>>  dupChkGlnNumPros2Cust(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);

        return (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("dupChkGlnNumPros2Cust", params);
    }
    // Add End 2018/07/11 QC#25871

    // Add Start 2018/07/11 QC#25871
    private List<DUP_CANDI_LOC_WRKTMsg> toDupCandiLocWrkForGlnNum(Map<String, String> param, String locTp){
        List<DUP_CANDI_LOC_WRKTMsg> resultList = new ArrayList<DUP_CANDI_LOC_WRKTMsg>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("glnNum", param.get("GLN_NUM"));
        params.put("tableNmDac", DS_LOC_TP.CUSTOMER);
        params.put("tableNmDap", DS_LOC_TP.PROSPECT);

        if (DS_LOC_TP.CUSTOMER.equals(locTp)){
            params.put("chkCustomer", ZYPConstant.FLG_ON_Y);
        }

        List<Map<String, String>> list =  (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("toDupCandiLocWrkForGlnNum", params);

        for (Map<String, String> dat : list){
            DUP_CANDI_LOC_WRKTMsg tmsg = new DUP_CANDI_LOC_WRKTMsg();

            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
            BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DUP_CANDI_LOC_WRK_SQ);
            ZYPEZDItemValueSetter.setValue(tmsg.dupCandiLocWrkPk, pk);
            ZYPEZDItemValueSetter.setValue(tmsg.dsAcctNum, dat.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(tmsg.locNum, dat.get("LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(tmsg.dsLocTpCd, dat.get("DS_LOC_TP_CD"));
            ZYPEZDItemValueSetter.setValue(tmsg.dupCandiDsAcctNum, dat.get("DUP_CANDI_DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(tmsg.dupCandiLocNum, dat.get("DUP_CANDI_LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(tmsg.dupCandiLocTpCd, dat.get("DUP_CANDI_LOC_TP_CD"));
            ZYPEZDItemValueSetter.setValue(tmsg.exactMatchFlg,ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tmsg.prtlMatchFlg,ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tmsg.dunsMatchFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tmsg.glnMatchFlg, ZYPConstant.FLG_ON_Y);

            resultList.add(tmsg);
        }

        return resultList;
    }
    // Add End 2018/07/11 QC#25871
    
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
        /* Initialize S21BatchMain */
        new NMAB271001().executeBatch(NMAB271001.class.getSimpleName());

    }

}
