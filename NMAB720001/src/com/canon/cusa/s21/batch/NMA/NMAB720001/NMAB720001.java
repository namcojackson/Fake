/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB720001;

import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.ASTERISK;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.CRLF;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.CSMP_CONTR_TGT_PROC_STS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.CSMP_EML_SEND_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.CSMP_ERR_DTL_CNT;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.DEFAULT_COMMIT_UNIT;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.HYPHEN;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.MAIL_CHARSET;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.MAIL_FIELD_ERROR_TOTAL;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.MAIL_FIELD_ERR_MSG;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.MAIL_FIELD_MAX_ERROR;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.MAIL_FIELD_SCUCECC_TOTAL;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.MAIL_FIELD_TOTAL;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.MAIL_KEY_FROM;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.MAIL_TYPE_FROM;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.MAIL_TYPE_TO;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.NMAM0043E;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.NMAM0072E;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.NMAM0176E;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.NMAM0177E;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.NMAM8028E;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.NMAM8031E;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.NMAM8132E;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.NMAM8533E;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.NMAM8534E;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.NMAM8535E;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.NMAM8536E;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.NMAM8537E;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.NUM_CONST;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.STS_A;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.STS_I;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.STS_N;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.STS_U;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.VAR_CHAR_CONST;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.ZERO;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NMA.NMAB720001.constant.NMAB720001Constant.ZZZM9026E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCommonFunc;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CSMP_CONTRTMsg;
import business.db.CSMP_CONTR_INTFCTMsg;
import business.db.CSMP_PROC_STSTMsg;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CSMP_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 *<pre>
 * CSMP Contract List sync process Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/23   Fujitsu         M.Ohno          Create          N/A
 * 2016/06/09   Fujitsu         M.Nakamura      Update          QC#9730
 * 2018/06/08   Fujitsu         M.Ishii         Update          QC#18256
 *</pre>
 */
public class NMAB720001 extends S21BatchMain {
    /** Global Company Code */
    private String glblCmpyCd = null;

    /** commitUnit */
    private int commitUnit = 0;

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

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** User Param */
    private String varUser1 = null;

    /** cspmDtlErrCount */
    private BigDecimal cspmDtlErrCount = BigDecimal.valueOf(0);

    /** csmpEmlSendCd */
    private String csmpEmlSendCd = null;

    /** csmpContrTgtProcStsCd */
    private String[] csmpContrTgtProcStsCd = null;

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

        this.commitUnit = getCommitCount();
        if (this.commitUnit <= 0 || DEFAULT_COMMIT_UNIT < this.commitUnit) {
            this.commitUnit = DEFAULT_COMMIT_UNIT;
        }

        this.varUser1 = getUserVariable1();
        if (this.varUser1 == null || "".equals(this.varUser1)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Var User1" });
        }

        this.cspmDtlErrCount = ZYPCodeDataUtil.getNumConstValue(CSMP_ERR_DTL_CNT, this.glblCmpyCd);
        if (this.cspmDtlErrCount == null) {
            throw new S21AbendException(NMAM8132E, new String[] {CSMP_ERR_DTL_CNT, NUM_CONST });
        }

        this.csmpEmlSendCd = ZYPCodeDataUtil.getVarCharConstValue(CSMP_EML_SEND_CD, this.glblCmpyCd);

        String csmpContrTgtProcStsCds = ZYPCodeDataUtil.getVarCharConstValue(CSMP_CONTR_TGT_PROC_STS_CD, this.glblCmpyCd);
        if (csmpContrTgtProcStsCds == null || "".equals(csmpContrTgtProcStsCds)) {
            throw new S21AbendException(NMAM8132E, new String[] {CSMP_CONTR_TGT_PROC_STS_CD, VAR_CHAR_CONST });
        }
        this.csmpContrTgtProcStsCd = csmpContrTgtProcStsCds.split(",");

        this.procDt = ZYPDateUtil.getBatProcDate();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    }

    @Override
    protected void mainRoutine() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> ssmParamGetMdse = new HashMap<String, Object>();
        ssmParamGetMdse.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamGetMdse.put("csmpProcStsCd", this.csmpContrTgtProcStsCd);
        try {
            stmt = this.ssmLLClient.createPreparedStatement("getCsmpContrIntfc", ssmParamGetMdse, execParam);
            rsSet = stmt.executeQuery();
            boolean isError = false;

            while (rsSet.next()) {
                // validate check
                String msg = validateCheck(rsSet);
                if (ZYPCommonFunc.hasValue(msg)) {
                    errorUpdate(msg, rsSet.getBigDecimal("CSMP_CONTR_INTFC_PK"));
                    continue;
                }


                List<Map<String, Object>> csmpContrList = null;
                String csmpNum = rsSet.getString("CSMP_NUM") + "-" + rsSet.getString("CUSA_END_USR_NUM");

                if (STS_I.equals(rsSet.getString("CSMP_TRX_STS_CD"))) {
                    csmpContrList = getCsmpContrForCSA(rsSet);
                    if (csmpContrList == null || csmpContrList.size() == 0) {
                     // 2018/06/08 QC#18256 mod Start
//                        msg = S21MessageFunc.clspGetMessage(NMAM8536E, new String[] {"Dealer Ref #" });
                        msg = S21MessageFunc.clspGetMessage(NMAM8536E, new String[] {"CSA Number" });
                     // 2018/06/08 QC#18256 mod End
                        errorUpdate(msg, rsSet.getBigDecimal("CSMP_CONTR_INTFC_PK"));
                        continue;
                    }

                    // 2016/06/09 QC#9730 Add Start
                    isError = false;
                    for (Map<String, Object> csmpContrMap : csmpContrList) {
                        if (csmpNum.equals((String) csmpContrMap.get("CSMP_NUM"))) {
                            msg = S21MessageFunc.clspGetMessage(NMAM0072E, new String[] {"CSMP#=" + csmpNum});
                            errorUpdate(msg, rsSet.getBigDecimal("CSMP_CONTR_INTFC_PK"));
                            isError = true;
                            break;
                        }
                    }
                    if (isError) {
                        continue;
                    }
                    // 2016/06/09 QC#9730 Add End
                } else if (STS_U.equals(rsSet.getString("CSMP_TRX_STS_CD"))) {
                    csmpContrList = getCsmpContrForCSMP(rsSet);
                    if (csmpContrList == null || csmpContrList.size() == 0) {
                        msg = S21MessageFunc.clspGetMessage(NMAM8536E, new String[] {"CSMP# (update)" });
                        errorUpdate(msg, rsSet.getBigDecimal("CSMP_CONTR_INTFC_PK"));
                        continue;
                    }
                }

                // sync process
                String errMsg = "";
                String prcCatgCd = getCsmpLevelName(rsSet);
                // Pattern#9
                if (STS_I.equals(rsSet.getString("CSMP_TRX_STS_CD"))) {
                    for (Map<String, Object> csmpContrMap : csmpContrList) {
                        CSMP_CONTRTMsg inTMsg = new CSMP_CONTRTMsg();
                        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(inTMsg.csmpContrPk, (BigDecimal) csmpContrMap.get("CSMP_CONTR_PK"));
                        inTMsg = (CSMP_CONTRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

                        ZYPEZDItemValueSetter.setValue(inTMsg.csmpNum, csmpNum);
                        ZYPEZDItemValueSetter.setValue(inTMsg.prcCatgCd, prcCatgCd);
                        ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, rsSet.getString("EFF_FROM_DT"));
                        ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, rsSet.getString("EFF_THRU_DT"));
                        inTMsg.rnwCsmpNum.clear();
                        errMsg = executeTMsg(inTMsg, "CSMP_CONTR", false);
                        if (ZYPCommonFunc.hasValue(errMsg)) {
                            errorUpdate(msg, rsSet.getBigDecimal("CSMP_CONTR_INTFC_PK"));
                            break;
                        }
                    }
                } else if (STS_U.equals(rsSet.getString("CSMP_TRX_STS_CD"))) {
                    for (Map<String, Object> csmpContrMap : csmpContrList) {
                        CSMP_CONTRTMsg inTMsg = new CSMP_CONTRTMsg();
                        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(inTMsg.csmpContrPk, (BigDecimal) csmpContrMap.get("CSMP_CONTR_PK"));
                        inTMsg = (CSMP_CONTRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);
                        if (STS_N.equals(rsSet.getString("CSMP_CONTR_STS_CD"))) {
                            // Pattern#8
                            ZYPEZDItemValueSetter.setValue(inTMsg.prcCatgCd, prcCatgCd);
                            ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, rsSet.getString("EFF_FROM_DT"));
                            ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, rsSet.getString("EFF_THRU_DT"));
                            ZYPEZDItemValueSetter.setValue(inTMsg.cusaRejDt, rsSet.getString("LAST_UPD_TS").substring(0, 8));
                            errMsg = executeTMsg(inTMsg, "CSMP_CONTR", false);
                            continue;
                        }

                        if (ZYPDateUtil.compare(rsSet.getString("EFF_THRU_DT"), inTMsg.effThruDt.getValue()) == 0) {
                            // Pattern#1
                            if (prcCatgCd.equals(inTMsg.prcCatgCd.getValue())) {
                                continue;
                            } else if (checkDt(rsSet) //
                                    || ZYPConstant.FLG_OFF_N.equals(inTMsg.csmpContrActvFlg.getValue())) {
                                // Pattern#2.1
                                // Pattern#6
                                ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, rsSet.getString("EFF_FROM_DT"));
                                ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, rsSet.getString("EFF_THRU_DT"));
                            } else {
                                // Pattern#2
                                // Pattern#2.2
                                // Pattern#2.2.1
                                String m1Date = getM1Date(rsSet);
                                ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, rsSet.getString("EFF_FROM_DT"));
                                ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, ZYPDateUtil.addDays(m1Date, -1));

                                // Pattern#2.2.2
                                CSMP_CONTRTMsg newTMsg = setInsertTmsgParam(inTMsg, m1Date, prcCatgCd, rsSet);
                                errMsg = executeTMsg(newTMsg, "CSMP_CONTR", true);
                            }

                        } else if (ZYPDateUtil.compare(rsSet.getString("EFF_THRU_DT"), inTMsg.effThruDt.getValue()) < 0) {
                            if (checkDt(rsSet) //
                                    || prcCatgCd.equals(inTMsg.prcCatgCd.getValue()) //
                                    || ZYPConstant.FLG_OFF_N.equals(inTMsg.csmpContrActvFlg.getValue())) {
                                // Pattern#3
                                // Pattern#4.2
                                // Pattern#7
                                ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, rsSet.getString("EFF_FROM_DT"));
                                ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, rsSet.getString("EFF_THRU_DT"));
                            } else {
                                // Pattern#4.3
                                // Pattern#4.3.1
                                String m1Date = getM1Date(rsSet);
                                ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, rsSet.getString("EFF_FROM_DT"));
                                ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, ZYPDateUtil.addDays(m1Date, -1));

                                // Pattern#4.3.2
                                CSMP_CONTRTMsg newTMsg = setInsertTmsgParam(inTMsg, m1Date, prcCatgCd, rsSet);
                                errMsg = executeTMsg(newTMsg, "CSMP_CONTR", true);
                            }
                        } else {
                            ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, rsSet.getString("EFF_FROM_DT"));
                            ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, rsSet.getString("EFF_THRU_DT"));
                        }
                        errMsg = executeTMsg(inTMsg, "CSMP_CONTR", false);
                        if (ZYPCommonFunc.hasValue(errMsg)) {
                            errorUpdate(msg, rsSet.getBigDecimal("CSMP_CONTR_INTFC_PK"));
                            break;
                        }
                    }
                }
                if (!ZYPCommonFunc.hasValue(errMsg)) {
                    completeUpdate(rsSet.getBigDecimal("CSMP_CONTR_INTFC_PK"));
                }

            }
            if (this.csmpEmlSendCd != null
                    && this.csmpEmlSendCd.contains(this.varUser1)) {
                sendMail();
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }

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
        /* Initialize S21BatchMain */
        new NMAB720001().executeBatch(NMAB720001.class.getSimpleName());

    }

    private String validateCheck(ResultSet rsSet) throws SQLException {
        // Mandatory
        if (!mandatoryCheck(rsSet, "DLR_REF_NUM")) {
            return S21MessageFunc.clspGetMessage(NMAM8533E, new String[] {"CSA Number" });
        }

        if (!mandatoryCheck(rsSet, "DS_ACCT_NM")) {
            return S21MessageFunc.clspGetMessage(NMAM8533E, new String[] {"Account Name" });
        }

        if (!mandatoryCheck(rsSet, "CUSA_END_USR_NUM")) {
            return S21MessageFunc.clspGetMessage(NMAM8533E, new String[] {"CUSA End User Name" });
        }

        if (!mandatoryCheck(rsSet, "CSMP_NUM")) {
            return S21MessageFunc.clspGetMessage(NMAM8533E, new String[] {"CSMP Contract Number" });
        }

        if (!mandatoryCheck(rsSet, "CSMP_CONTR_STS_CD")) {
            return S21MessageFunc.clspGetMessage(NMAM8533E, new String[] {"CSMP Contract Status Code" });
        }

        if (!mandatoryCheck(rsSet, "CSMP_RG_CD")) {
            return S21MessageFunc.clspGetMessage(NMAM8533E, new String[] {"Region Code" });
        }

        if (!mandatoryCheck(rsSet, "RTL_DLR_CD")) {
            return S21MessageFunc.clspGetMessage(NMAM8533E, new String[] {"Origin Dealer Code" });
        }

        if (!mandatoryCheck(rsSet, "EFF_FROM_DT")) {
            return S21MessageFunc.clspGetMessage(NMAM8533E, new String[] {"Effective Date" });
        }

        if (!mandatoryCheck(rsSet, "EFF_THRU_DT")) {
            return S21MessageFunc.clspGetMessage(NMAM8533E, new String[] {"Expiration Date" });
        }

        if (!mandatoryCheck(rsSet, "CSMP_CATG_CD")) {
            return S21MessageFunc.clspGetMessage(NMAM8533E, new String[] {"CSMP Level" });
        }

        if (!mandatoryCheck(rsSet, "CR_LIST_TXT")) {
            return S21MessageFunc.clspGetMessage(NMAM8533E, new String[] {"Credit List" });
        }

        if (!mandatoryCheck(rsSet, "CR_LIST_GNRN_NUM")) {
            return S21MessageFunc.clspGetMessage(NMAM8533E, new String[] {"Credit List Generation Number" });
        }

        if (!mandatoryCheck(rsSet, "VLD_FROM_DT")) {
            return S21MessageFunc.clspGetMessage(NMAM8533E, new String[] {"Valid From Date" });
        }

        if (!mandatoryCheck(rsSet, "CSMP_TRX_STS_CD")) {
            return S21MessageFunc.clspGetMessage(NMAM8533E, new String[] {"CSMP Transaction Status Code" });
        }

        if (!mandatoryCheck(rsSet, "LAST_UPD_TS")) {
            return S21MessageFunc.clspGetMessage(NMAM8533E, new String[] {"Operation Date" });
        }

        if (!mandatoryCheck(rsSet, "CSMP_PROC_STS_CD")) {
            return S21MessageFunc.clspGetMessage(NMAM8533E, new String[] {"Process Status" });
        }

        // Invalid check
        if (rsSet.getString("CUSA_END_USR_NUM").length() != 5) {
            return S21MessageFunc.clspGetMessage(NMAM8537E, new String[] {"CUSA End User Name" });
        }

        if (!EZDCommonFunc.isNumeric(rsSet.getString("CSMP_NUM"))) {
            return S21MessageFunc.clspGetMessage(NMAM8535E, new String[] {"CSMP Contract Number" });
        }

        if (!STS_A.equals(rsSet.getString("CSMP_CONTR_STS_CD")) //
                && !STS_N.equals(rsSet.getString("CSMP_CONTR_STS_CD"))) {
            return S21MessageFunc.clspGetMessage(NMAM8534E, new String[] {"CSMP Contract Status Code" });
        }

        if (!ZYPDateUtil.checkDate(rsSet.getString("EFF_FROM_DT"))) {
            return S21MessageFunc.clspGetMessage(NMAM8534E, new String[] {"Effective Date" });
        }

        if (!ZYPDateUtil.checkDate(rsSet.getString("EFF_THRU_DT"))) {
            return S21MessageFunc.clspGetMessage(NMAM8534E, new String[] {"Expiration Date" });
        }

        if (!EZDCommonFunc.isNumeric(rsSet.getString("CR_LIST_GNRN_NUM"))) {
            return S21MessageFunc.clspGetMessage(NMAM8535E, new String[] {"Credit List Generation Number" });
        }

        if (!ZYPDateUtil.checkDate(rsSet.getString("VLD_FROM_DT"))) {
            return S21MessageFunc.clspGetMessage(NMAM8534E, new String[] {"Valid From Date" });
        }

        if (!STS_I.equals(rsSet.getString("CSMP_TRX_STS_CD")) //
                && !STS_U.equals(rsSet.getString("CSMP_TRX_STS_CD"))) {
            return S21MessageFunc.clspGetMessage(NMAM8534E, new String[] {"CSMP Transaction Status Code" });
        }

        if (!isYYYYMMDDHHmmssDate(rsSet.getString("LAST_UPD_TS"))) {
            return S21MessageFunc.clspGetMessage(NMAM8534E, new String[] {"Operation Date" });
        }

        if (!isCsmpProcSts(rsSet.getString("CSMP_PROC_STS_CD"))) {
            return S21MessageFunc.clspGetMessage(NMAM8534E, new String[] {"Process Status" });
        }

        if (getCsmpLevelName(rsSet) == null) {
            return S21MessageFunc.clspGetMessage(NMAM8536E, new String[] {"Credit List" });
        }

        if (ZYPDateUtil.compare(rsSet.getString("EFF_FROM_DT"), rsSet.getString("EFF_THRU_DT")) > 0) {
            return S21MessageFunc.clspGetMessage(NMAM0043E, new String[] {"Effective Date", "Expiration Date" });
        }

        return "";
    }

    private boolean mandatoryCheck(ResultSet rsSet, String colum) throws SQLException {
        if (!ZYPCommonFunc.hasValue(rsSet.getString(colum)) //
                || ASTERISK.equals(rsSet.getString(colum)) //
                || ZERO.equals(rsSet.getString(colum))) {

            return false;
        }
        return true;
    }

    private boolean isYYYYMMDDHHmmssDate(String date) {
        if (date == null || date.length() != 14) {
            return false;
        }
        String datePart = date.substring(0, 8);
        String timePart = date.substring(8);

        if (!EZDCommonFunc.isNumeric(datePart) //
                || !EZDCommonFunc.isNumeric(datePart)) {
            return false;
        }

        if (!ZYPDateUtil.checkDate(datePart)) {
            return false;
        }

        int hourPart = Integer.valueOf(timePart.substring(0, 2));
        int minPart = Integer.valueOf(timePart.substring(2, 4));
        int secPart = Integer.valueOf(timePart.substring(4));

        if (hourPart < 24 && minPart < 60 && secPart < 60) {
            return true;
        }

        return false;
    }

    private boolean isCsmpProcSts(String stsCd) {
        CSMP_PROC_STSTMsg inTMsg = new CSMP_PROC_STSTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.csmpProcStsCd, stsCd);
        inTMsg = (CSMP_PROC_STSTMsg) S21CodeTableAccessor.findByKey(inTMsg);
        if (inTMsg == null) {
            return false;
        }
        return true;
    }

    private String getCsmpLevelName(ResultSet rsSet) throws SQLException {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("crListTxt", rsSet.getString("CR_LIST_TXT"));
        ssmParam.put("crListGnrnNum", rsSet.getString("CR_LIST_GNRN_NUM"));
        ssmParam.put("procDt", this.procDt);
        String prcCatgCd = (String) this.ssmBatchClient.queryObject("getCsmpPrcXref", ssmParam);
        return prcCatgCd;
    }

    private List<Map<String, Object>> getCsmpContrForCSA(ResultSet rsSet) throws SQLException {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dlrRefNum", rsSet.getString("DLR_REF_NUM"));
        List<Map<String, Object>> csmpContrList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCsmpContrForCSA", ssmParam);
        return csmpContrList;
    }

    private List<Map<String, Object>> getCsmpContrForCSMP(ResultSet rsSet) throws SQLException {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        String csmpNum = rsSet.getString("CSMP_NUM") + "-" + rsSet.getString("CUSA_END_USR_NUM");
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("csmpNum", csmpNum);
        List<Map<String, Object>> csmpContrList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCsmpContrForCSMP", ssmParam);
        return csmpContrList;
    }

    private void errorUpdate(String msg, BigDecimal csmpContrIntfcPk) {
        CSMP_CONTR_INTFCTMsg inTMsg = new CSMP_CONTR_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.csmpContrIntfcPk, csmpContrIntfcPk);
        inTMsg = (CSMP_CONTR_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

        ZYPEZDItemValueSetter.setValue(inTMsg.csmpErrMsgTxt, msg);
        ZYPEZDItemValueSetter.setValue(inTMsg.csmpProcStsCd, CSMP_PROC_STS.ERROR);
        executeTMsg(inTMsg, "CSMP_CONTR_INTFC", false);
        this.totalErrCount++;
        this.termCd = TERM_CD.WARNING_END;
        commit();
    }

    private void completeUpdate(BigDecimal csmpContrIntfcPk) {
        CSMP_CONTR_INTFCTMsg inTMsg = new CSMP_CONTR_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.csmpContrIntfcPk, csmpContrIntfcPk);
        inTMsg = (CSMP_CONTR_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

        inTMsg.csmpErrMsgTxt.clear();
        ZYPEZDItemValueSetter.setValue(inTMsg.csmpProcStsCd, CSMP_PROC_STS.COMPLETED);
        executeTMsg(inTMsg, "CSMP_CONTR_INTFC", false);
        this.totalNmlCount++;
        commit();
    }

    /**
     * excecuteUpdate
     * @param array arrayEZDTMsg[]
     */
    private String executeTMsg(EZDTMsg array, String msg, boolean isInsert) {
        if (isInsert) {
            S21FastTBLAccessor.insert(array);
        } else {
            S21FastTBLAccessor.update(array);
        }

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(array.getReturnCode())) {
            super.rollback();
            if (isInsert) {
                return S21MessageFunc.clspGetMessage(NMAM0176E, new String[] {msg });
            } else {
                return S21MessageFunc.clspGetMessage(NMAM0177E, new String[] {msg });
            }
        }
        return "";
    }

    private String getM1Date(ResultSet rsSet) throws SQLException {
        if (ZYPDateUtil.compare(rsSet.getString("VLD_FROM_DT"), rsSet.getString("LAST_UPD_TS").substring(0, 8)) >= 0) {
            return rsSet.getString("VLD_FROM_DT");
        }

        return rsSet.getString("LAST_UPD_TS").substring(0, 8);
    }

    private CSMP_CONTRTMsg setInsertTmsgParam(CSMP_CONTRTMsg oldTMsg, String m1Date, String prcCatgCd, ResultSet rsSet) throws SQLException {
        CSMP_CONTRTMsg newTMsg = new CSMP_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(newTMsg.glblCmpyCd, this.glblCmpyCd);
        BigDecimal csmpContrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CSMP_CONTR_SQ);
        ZYPEZDItemValueSetter.setValue(newTMsg.csmpContrPk, csmpContrPk);
        ZYPEZDItemValueSetter.setValue(newTMsg.dsAcctNum, oldTMsg.dsAcctNum);
        ZYPEZDItemValueSetter.setValue(newTMsg.dsAcctNm, oldTMsg.dsAcctNm);
        ZYPEZDItemValueSetter.setValue(newTMsg.csmpNum, oldTMsg.csmpNum);
        ZYPEZDItemValueSetter.setValue(newTMsg.dlrRefNum, oldTMsg.dlrRefNum);
        ZYPEZDItemValueSetter.setValue(newTMsg.prcCatgCd, prcCatgCd);
        ZYPEZDItemValueSetter.setValue(newTMsg.csmpContrActvFlg, oldTMsg.csmpContrActvFlg);
        ZYPEZDItemValueSetter.setValue(newTMsg.effFromDt, m1Date);
        ZYPEZDItemValueSetter.setValue(newTMsg.effThruDt, rsSet.getString("EFF_THRU_DT"));
        ZYPEZDItemValueSetter.setValue(newTMsg.rtlDlrCd, oldTMsg.rtlDlrCd);
        ZYPEZDItemValueSetter.setValue(newTMsg.rnwCsmpNum, oldTMsg.rnwCsmpNum);
        ZYPEZDItemValueSetter.setValue(newTMsg.prcContrNum, oldTMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(newTMsg.origCoaBrCd, oldTMsg.origCoaBrCd);

        return newTMsg;
    }

    private void sendMail() {
        // Get From Mail Address.
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_KEY_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(NMAM8028E, new String[] {MAIL_TYPE_FROM, MAIL_GROUP_ID_FROM, MAIL_KEY_FROM });
        }
        S21MailAddress mailAddrFrom = addrFromList.get(0);

        // Get To Mail Address.
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> mailAddrToList = groupTo.getMailAddress();
        if (mailAddrToList == null || mailAddrToList.isEmpty()) {
            throw new S21AbendException(NMAM8028E, new String[] {MAIL_TYPE_TO, MAIL_GROUP_ID_TO, HYPHEN });
        }

        // Get Template
        S21MailTemplate maiTemplate = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!ZYPCommonFunc.hasValue(maiTemplate.getBody())) {
            throw new S21AbendException(NMAM8031E, new String[] {MAIL_TEMPLATE_ID });
        }

        String errMsg = addErrInf();

        maiTemplate.setTemplateParameter(MAIL_FIELD_TOTAL, this.totalErrCount + this.totalNmlCount);
        maiTemplate.setTemplateParameter(MAIL_FIELD_SCUCECC_TOTAL, this.totalNmlCount);
        maiTemplate.setTemplateParameter(MAIL_FIELD_ERROR_TOTAL, this.totalErrCount);
        maiTemplate.setTemplateParameter(MAIL_FIELD_MAX_ERROR, this.cspmDtlErrCount);
        maiTemplate.setTemplateParameter(MAIL_FIELD_ERR_MSG, errMsg);

        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(mailAddrFrom);
        mail.setToAddressList(mailAddrToList);
        mail.setMailTemplate(maiTemplate);
        mail.setSubject(maiTemplate.getSubject(), MAIL_CHARSET);
        mail.postMail();
    }

    private String addErrInf() {
        StringBuilder errMsg = new StringBuilder();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("csmpProcStsCd", CSMP_PROC_STS.ERROR);
        ssmParam.put("rowNum", this.cspmDtlErrCount);
        List<Map< ? , ? >> resultParamList = (List<Map< ? , ? >>) this.ssmBatchClient.queryObjectList("getCsmpContrIntfcForMail", ssmParam);
        for (Map< ? , ? > resultParam : resultParamList) {
            errMsg.append("CSMP#:           ");
            errMsg.append((String) resultParam.get("CSMP_NUM"));
            errMsg.append(CRLF);
            errMsg.append("End User Number: ");
            errMsg.append((String) resultParam.get("CUSA_END_USR_NUM"));
            errMsg.append(CRLF);
            errMsg.append("Credit List:     ");
            errMsg.append((String) resultParam.get("CR_LIST_TXT"));
            errMsg.append(CRLF);
            errMsg.append("Generation:      ");
            errMsg.append((String) resultParam.get("CR_LIST_GNRN_NUM"));
            errMsg.append(CRLF);
            errMsg.append("Error:           ");
            errMsg.append((String) resultParam.get("CSMP_ERR_MSG_TXT"));
            errMsg.append(CRLF);
            errMsg.append(CRLF);
        }
        return errMsg.toString();
    }

    private boolean checkDt(ResultSet rsSet) throws SQLException {
        if (ZYPDateUtil.compare(rsSet.getString("EFF_THRU_DT"), rsSet.getString("VLD_FROM_DT")) < 0 //
                || ZYPDateUtil.compare(rsSet.getString("EFF_THRU_DT"), rsSet.getString("LAST_UPD_TS")) < 0 //
                || ZYPDateUtil.compare(rsSet.getString("VLD_FROM_DT"), rsSet.getString("EFF_FROM_DT")) < 0 //
                || ZYPDateUtil.compare(rsSet.getString("LAST_UPD_TS"), rsSet.getString("EFF_FROM_DT")) < 0 //
        ) {
            return true;
        }
        return false;
    }
}
