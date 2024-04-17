/* <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.batch.NMA.NMAB509001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import parts.common.EZDMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ACCT_TRTY_RESRC_ASGTMsg;
import business.db.ACCT_TRTY_ROLE_ASGTMsg;
import business.db.DS_ACCT_PROSTMsg;
import business.db.INTFC_TS_MGTTMsg;
import business.db.LINE_BIZ_ROLE_RANK_DECNTMsg;
import business.db.PROS_PTY_LOC_WRKTMsg;
import business.db.PROS_PTY_LOC_WRKTMsgArray;
import business.db.PROS_TRTY_RESRC_ASGTMsg;
import business.db.PROS_TRTY_ROLE_ASGTMsg;
import business.db.PTY_LOC_WRKTMsg;
import business.db.PTY_LOC_WRKTMsgArray;
import business.db.TRTY_RULETMsg;
import business.db.TRTY_TPTMsg;
import business.db.TRTY_TRK_TRTY_UPDTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASG_TRTY_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_LOGIC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_OPRD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_TRK_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Territory Force Update batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/20   Fujitsu         M.Yamada        Create          N/A
 * 2016/07/08   Fujitsu         M.Yamada        Update          QC#11557
 * 2016/07/11   Fujitsu         M.Yamada        Update          QC#11556
 * 2016/07/12   Fujitsu         M.Yamada        Update          QC#11664
 * 2016/07/12   Fujitsu         M.Yamada        Update          QC#11751
 * 2016/07/13   Fujitsu         M.Yamada        Update          QC#11823
 * 2016/07/28   Fujitsu         M.Yamada        Update          QC#11070
 * 2017/03/15   Fujitsu         R.Nakamura      Update          QC#15878
 * 2017/08/29   Fujitsu         A.Kosai         Update          QC#20384
 * 2017/08/30   Fujitsu         A.Kosai         Update          QC#20601
 * 2017/09/11   Fujitsu         A.Kosai         Update          QC#21008
 * 2017/09/12   Fujitsu         A.Kosai         Update          QC#20528
 * 2017/11/24   Fujitsu         N.Sugiura       Update          QC#22299
 * 2018/03/26   Fujitsu         A.Kosai         Update          QC#23157
 * 2018/06/01   Fujitsu         Hd.Sugawara     Update          QC#24293
 * 2018/07/24   Fujitsu         A.Kosai         Update          QC#26753
 * 2018/08/14   Fujitsu         H.Kumagai       Update          QC#27597
 * 2018/08/22   Fujitsu         M.Ohno          Update          QC#25361
 *</pre>
 */
public class NMAB509001 extends S21BatchMain {

    //---- Message IDs
    /** An parameter "Interface ID" has not been set. */
    private static final String USEM0099E = "USEM0099E";

    /** Input Parameter Global Company Code is mandatory field. */
    private static final String NMZM0009E = "NMZM0009E";

    /** Input Parameter Sales Date is mandatory field. */
    private static final String NMZM0011E = "NMZM0011E";

    /** The entered [@] does not exist. */
    private static final String NMAM0009E = "NMAM0009E";

    /** The entered [@] has multiple records. */
    private static final String NMAM8611E = "NMAM8611E";

    /** [@] field is mandatory. */
    private static final String NMAM0836E = "NMAM0836E";

    /** [@] It failed to register @ Table,[@] */
    private static final String NMAM8571E = "NMAM8571E";

    /** It failed to update [@]. */
    private static final String NMAM0177E = "NMAM0177E";

    /** It failed to delete [@]. */
    private static final String NMAM0208E = "NMAM0208E";

    /** Data insert failure.@ */
    private static final String NMAM0282E = "NMAM0282E";

    /** Data select failure. [ TableName = @ , key = @, value = @ ] */
    private static final String ZZMM0014E = "ZZMM0014E";

    /** mail group id(from) : FROM0005 */
    private static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id(to) for Error : NMAB5090 */
    private static final String MAIL_GROUP_ID_TO = "NMAB5090";

    /** mail template id : NMAB5090M001 */
    private static final String MAIL_TEMPLATE_ID_FOR_BIZ_ERROR = "NMAB5090M001";

    /** batchNm : NMAB5090 Territory Force Update batch */
    private static final String BATCH_NM = "NMAB5090 Territory Force Update batch";

    /** mail template key(batchId) : batchId */
    private static final String MAIL_TEMPLATE_KEY_BATCH_NM = "batchNm";

    /** MAIL_TEMPLATE_KEY_MSG_INFO */
    private static final String MAIL_TEMPLATE_KEY_MSG_INFO = "message";

    /** line separator */
    private static final String NEW_LINE = String.format("%n");

    /** DEFAULT_FETCH_SIZE */
    private static final int DEFAULT_FETCH_SIZE = 1000;

    /** COMMIT_CYCLE */
    private static final int COMMIT_CYCLE = 1000;

    /** VALIDATION_NORMAL */
    private static final String VALIDATION_NORMAL = "N";

    /** VALIDATION_ERROR_LINE */
    private static final String VALIDATION_ERROR_LINE = "EL";

    /** VALIDATION_ERROR_ACCT_LOC */
    private static final String VALIDATION_ERROR_ACCT_LOC = "EA";

    /** PROC_TP_REMOVE_TRTY */
    private static final String PROC_TP_REMOVE_TRTY = "REMOVE_TRTY";

    /** PROC_TP_REASGN_ACCT */
    private static final String PROC_TP_REASGN_ACCT = "REASGN_ACCT";

    /** PROC_TP_OUT_OF_BIZ */
    private static final String PROC_TP_MOVE_TO_GEO = "MOVE_TO_GEO";

    /** SKIP */
    private static final String PROC_TP_SKIP = "SKIP";

    /** PROC_TP_OUT_OF_BIZ */
    private static final String PROC_TP_OUT_OF_BIZ = "OUT_OF_BIZ";

    // 2017/08/29 QC#20384 Add Start
    /** PROC_TP_ASGN_TRTY */
    private static final String PROC_TP_ASGN_TRTY = "ASGN_TRTY";
    // 2017/08/29 QC#20384 Add End

    /** DEF_ERROR_MESSAGE_LEN */
    private static final int DEF_ERROR_MESSAGE_LEN = 140;

    /** SSM Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** commit count */
    private int commitCount;

    /** error count */
    private int errorCount;

    /** term code */
    private TERM_CD termCd;

    /** global company code */
    private String glblCmpyCd;

    /** sales date */
    private String slsDt;

    /** interface id */
    private String interfaceId;

    /** transaction id array */
    private BigDecimal[] trxIds = null;

    /** maxRqstUpdTs */
    private String maxRqstUpdTs;

    /** s21 transaction accessor */
    S21TransactionTableAccessor s21TrxAsr = new S21TransactionTableAccessor();

    /** errInfoList */
    private List<Map<String, String[]>> errInfoList = new ArrayList<Map<String, String[]>>();

    /** errorMessage */
    private StringBuilder errorMessage = new StringBuilder();

    @Override
    protected void initRoutine() {
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.commitCount = 0;
        this.errorCount = 0;
        this.termCd = TERM_CD.NORMAL_END;

        this.maxRqstUpdTs = "";

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NMZM0009E);
        }
        this.slsDt = ZYPDateUtil.getSalesDate();
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            throw new S21AbendException(NMZM0011E);
        }
        this.interfaceId = getInterfaceID();
        if (this.interfaceId == null || "".equals(this.interfaceId)) {
            throw new S21AbendException(USEM0099E);
        }

        this.trxIds = this.s21TrxAsr.getIntegrationRecordDesc(this.interfaceId);

    }

    @Override
    protected void mainRoutine() {
        boolean isCopyToWork = false;
        for (BigDecimal trxId : this.trxIds) {
            if (!isCopyToWork) {
                copyToTrtyTrkTrtyUpd(trxId);
                isCopyToWork = true;
            }
            this.s21TrxAsr.endIntegrationProcess(this.interfaceId, trxId);
            commit();
        }
        if (ZYPCommonFunc.hasValue(this.maxRqstUpdTs)) {
            updateIntfcTsMgt();
            commit();
        }

        trtyForceUpdate();

        if (ZYPCommonFunc.hasValue(errorMessage.toString())) {
            rollback();
            sendErrorMail();
            commit();
        }
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.commitCount, this.errorCount, this.commitCount + this.errorCount);
    }

    private void copyToTrtyTrkTrtyUpd(BigDecimal trxId) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("interfaceId", this.interfaceId);
        ssmParam.put("transactionId", trxId);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getInterfaceData", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            List<TRTY_TRK_TRTY_UPDTMsg> tMsgList = new ArrayList<TRTY_TRK_TRTY_UPDTMsg>();

            while (rsSet.next()) {
                if (this.maxRqstUpdTs.compareTo(rsSet.getString("LAST_UPD_TS")) < 0) {
                    this.maxRqstUpdTs = rsSet.getString("LAST_UPD_TS");
                }
                TRTY_TRK_TRTY_UPDTMsg tMsg = editTrtyTrkTrtyUpdTMsg(rsSet);
                tMsgList.add(tMsg);
                if (tMsgList.size() >= COMMIT_CYCLE) {
                    int cnt = S21FastTBLAccessor.insert(tMsgList.toArray(new TRTY_TRK_TRTY_UPDTMsg[tMsgList.size()]));
                    if (cnt != tMsgList.size()) {
                        rollback();
                        tMsgList.clear();
                        throw new S21AbendException(NMAM8571E, new String[] {"copyToTrtyTrkTrtyUpd(1)", "TRTY_TRK_TRTY_UPD", String.valueOf(cnt) });
                    }
                    commit();
                    tMsgList.clear();
                }
            }
            if (tMsgList.size() > 0) {
                int cnt = S21FastTBLAccessor.insert(tMsgList.toArray(new TRTY_TRK_TRTY_UPDTMsg[tMsgList.size()]));
                if (cnt != tMsgList.size()) {
                    rollback();
                    throw new S21AbendException(NMAM8571E, new String[] {"copyToTrtyTrkTrtyUpd(2)", "TRTY_TRK_TRTY_UPD", String.valueOf(cnt) });
                }
                commit();
            }

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }

    }

    private TRTY_TRK_TRTY_UPDTMsg editTrtyTrkTrtyUpdTMsg(ResultSet rsSet) throws SQLException {

        TRTY_TRK_TRTY_UPDTMsg tMsg = new TRTY_TRK_TRTY_UPDTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyTrkTrtyUpdPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TRTY_TRK_TRTY_UPD_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, rsSet.getString("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.locNum, rsSet.getString("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.trtyOrgCd, rsSet.getString("TRTY_ORG_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.lineBizRoleTpCd, rsSet.getString("LINE_BIZ_ROLE_TP_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.trtyTrkProcStsCd, TRTY_TRK_PROC_STS.NEW);
        ZYPEZDItemValueSetter.setValue(tMsg.rqstUpdTs, rsSet.getString("LAST_UPD_TS"));

        return tMsg;
    }

    private void updateIntfcTsMgt() {
        INTFC_TS_MGTTMsg keyTMsg = new INTFC_TS_MGTTMsg();
        ZYPEZDItemValueSetter.setValue(keyTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(keyTMsg.mngIntfcId, this.interfaceId);

        INTFC_TS_MGTTMsg tMsg = (INTFC_TS_MGTTMsg) S21FastTBLAccessor.findByKeyForUpdate(keyTMsg);

        if (tMsg == null) {
            ZYPEZDItemValueSetter.setValue(keyTMsg.mngIntfcTs, this.maxRqstUpdTs);
            S21FastTBLAccessor.insert(keyTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(keyTMsg.getReturnCode())) {
                rollback();
                throw new S21AbendException(NMAM0282E, new String[] {"INTFC_TS_MGT : " + keyTMsg.getReturnCode() //
                        + "/" + keyTMsg.toString() });
            }
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.mngIntfcTs, this.maxRqstUpdTs);
            S21FastTBLAccessor.update(tMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                rollback();
                throw new S21AbendException(NMAM0177E, new String[] {"INTFC_TS_MGT : " + tMsg.getReturnCode() //
                        + "/" + tMsg.toString() });
            }
        }
    }

    private void trtyForceUpdate() {

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
        ssmParam.put("new", TRTY_TRK_PROC_STS.NEW);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getUpdateTerritory", ssmParam, execParam);
            rsSet = stmt.executeQuery();
            Map<BigDecimal, TRTY_TRK_TRTY_UPDTMsg> wkUpdTMsgMap = new HashMap<BigDecimal, TRTY_TRK_TRTY_UPDTMsg>();
            ACCT_TRTY_RESRC_ASGTMsg acctResrcAsgTMsg = new ACCT_TRTY_RESRC_ASGTMsg();
            PROS_TRTY_RESRC_ASGTMsg prosResrcAsgTMsg = new PROS_TRTY_RESRC_ASGTMsg();
            List<ACCT_TRTY_ROLE_ASGTMsg> roleAsgTMsgList = new ArrayList<ACCT_TRTY_ROLE_ASGTMsg>();
            String validationSts = null;
            String procTp = null;
            NMAB509001ProcInfoBean procInfoBean = null;
            // 2017/09/12 QC#20528 Add Start
            boolean isOutBiz = false;
            // 2017/09/12 QC#20528 Add End
            while (rsSet.next()) {
                if (procInfoBean == null) {
                    validationSts = VALIDATION_NORMAL;
                    procInfoBean = new NMAB509001ProcInfoBean();
                    procInfoBean.setDsAcctNum(rsSet.getString("DS_ACCT_NUM"));
                    procInfoBean.setLocNum(rsSet.getString("LOC_NUM"));
                    acctResrcAsgTMsg = getAcctResrcAsgTMsg(rsSet);
                    prosResrcAsgTMsg = getProsResrcAsgTMsg(rsSet);
                }
                TRTY_TRK_TRTY_UPDTMsg wkUpdTMsg = getWkUpdTMsg(rsSet);
                if (isBreakAcctLoc(wkUpdTMsg, procInfoBean)) {
                    if (isStsNormal(validationSts)) {
                        if (ZYPCommonFunc.hasValue(acctResrcAsgTMsg.dsAcctNm)) {
                            resistAcctTrtyResrcAsg(acctResrcAsgTMsg, procInfoBean);
                        }
                        if (ZYPCommonFunc.hasValue(prosResrcAsgTMsg.dsAcctNm)) {
                            resistProsTrtyResrcAsg(prosResrcAsgTMsg, procInfoBean);
                        }
                    } else {
                        rollback();
                    }

                    updateTrtyTrkTrtyUpdProcSts(wkUpdTMsgMap, validationSts);
                    commit();

                    acctResrcAsgTMsg = getAcctResrcAsgTMsg(rsSet);
                    prosResrcAsgTMsg = getProsResrcAsgTMsg(rsSet);
                    wkUpdTMsgMap.clear();
                    // 2017/09/12 QC#20528 Add Start
                    isOutBiz = false;
                    // 2017/09/12 QC#20528 Add End
                }

                DS_ACCT_PROSTMsg ttAcctTMsg = new DS_ACCT_PROSTMsg();
                PTY_LOC_WRKTMsg dplwTMsg = new PTY_LOC_WRKTMsg();
                procTp = getProcTp(rsSet);
                if (PROC_TP_SKIP.equals(procTp)) {
                    setTrtyTrkProcStsCd(rsSet, wkUpdTMsg);
                    wkUpdTMsgMap.put(wkUpdTMsg.trtyTrkTrtyUpdPk.getValue(), wkUpdTMsg);
                    continue;
                }
                // 2017/09/12 QC#20528 Add Start
                if (isProcTpOutOfBiz(procTp)) {
                    isOutBiz = true;
                }
                if (!isProcTpOutOfBiz(procTp) && isOutBiz) {
                    setTrtyTrkProcStsCd(rsSet, wkUpdTMsg);
                    wkUpdTMsgMap.put(wkUpdTMsg.trtyTrkTrtyUpdPk.getValue(), wkUpdTMsg);
                    continue;
                }
                // 2017/09/12 QC#20528 Add End
                setProcInfoBeanFromRsSet(procInfoBean, rsSet);

                // 2018/08/14 QC#27597 Mod Start
                validationSts = validation(procTp, rsSet, ttAcctTMsg, dplwTMsg, wkUpdTMsg);
                // 2018/08/14 QC#27597 Mod End
                if (isStsNormal(validationSts)) {
                    procInfoBean.setDsAcctTpCd(ttAcctTMsg.dsAcctTpCd.getValue());
                    procInfoBean.setLineBizRoleTpCd(rsSet.getString("LINE_BIZ_ROLE_TP_CD"));
                    String trtyRoleAsgnTblNm = null;
                    String dsAcctTblNm = null;
                    String dsAcctTpCd = procInfoBean.getDsAcctTpCd();
                    if (DS_ACCT_TP.CUSTOMER.equals(dsAcctTpCd)) {
                        trtyRoleAsgnTblNm = "ACCT_TRTY_ROLE_ASG";
                        dsAcctTblNm = "SELL_TO_CUST";
                    }
                    if (DS_ACCT_TP.PROSPECT.equals(dsAcctTpCd)) {
                        trtyRoleAsgnTblNm = "PROS_TRTY_ROLE_ASG";
                        dsAcctTblNm = "DS_ACCT_PROS";
                    }

                    roleAsgTMsgList //
                    = getTrtyRoleAsg(procTp, procInfoBean, trtyRoleAsgnTblNm, dsAcctTblNm);
                    if (roleAsgTMsgList == null) {
                        errInfoList.add(getMap(NMAM0009E, new String[] {trtyRoleAsgnTblNm + ".DS_ACCT_NUM:" + procInfoBean.getDsAcctNum() }));
                        setTrtyTrkProcStsCd(rsSet, wkUpdTMsg);
                        wkUpdTMsgMap.put(wkUpdTMsg.trtyTrkTrtyUpdPk.getValue(), wkUpdTMsg);
                        validationSts = VALIDATION_ERROR_LINE;
                        continue;
                    }
                    boolean isResrcAsgExists = false;
                    if (DS_ACCT_TP.CUSTOMER.equals(dsAcctTpCd)) {
                        if (ZYPCommonFunc.hasValue(acctResrcAsgTMsg.dsAcctNm)) {
                            isResrcAsgExists = true;
                        }
                        for (ACCT_TRTY_ROLE_ASGTMsg roleAsgTMsg : roleAsgTMsgList) {
                            if (!isResrcAsgExists) {
                                ZYPEZDItemValueSetter.setValue(acctResrcAsgTMsg.dsAcctNm, roleAsgTMsg.dsAcctNm.getValue());
                                ZYPEZDItemValueSetter.setValue(acctResrcAsgTMsg.dsAcctTpCd, roleAsgTMsg.dsAcctTpCd.getValue());
                            }
                            procInfoBean.setDsAcctNm(roleAsgTMsg.dsAcctNm.getValue());
                            validationSts //
                            = setValidationSts(validationSts //
                                    , updateTerritory(//
                                            procTp //
                                            , rsSet //
                                            , dplwTMsg.lineBizTpCd.getValue() //
                                            , roleAsgTMsg //
                                            , procInfoBean //
                                            , trtyRoleAsgnTblNm //
                                            , dsAcctTblNm));
                            if (VALIDATION_NORMAL.equals(validationSts)) {
                                acctResrcAsgTMsg //
                                = editAcctTrtyResrcAsgTMsgByAttrb(acctResrcAsgTMsg, procInfoBean, procTp, !isResrcAsgExists);
                            }
                        }
                    }
                    if (DS_ACCT_TP.PROSPECT.equals(dsAcctTpCd)) {
                        if (ZYPCommonFunc.hasValue(prosResrcAsgTMsg.dsAcctNm)) {
                            isResrcAsgExists = true;
                        }
                        for (ACCT_TRTY_ROLE_ASGTMsg roleAsgTMsg : roleAsgTMsgList) {
                            if (!isResrcAsgExists) {
                                ZYPEZDItemValueSetter.setValue(prosResrcAsgTMsg.dsAcctNm, roleAsgTMsg.dsAcctNm.getValue());
                                ZYPEZDItemValueSetter.setValue(prosResrcAsgTMsg.dsAcctTpCd, roleAsgTMsg.dsAcctTpCd.getValue());
                            }
                            procInfoBean.setDsAcctNm(roleAsgTMsg.dsAcctNm.getValue());
                            validationSts //
                            = setValidationSts(validationSts //
                                    , updateTerritory(//
                                            procTp //
                                            , rsSet //
                                            , dplwTMsg.lineBizTpCd.getValue() //
                                            , roleAsgTMsg //
                                            , procInfoBean //
                                            , trtyRoleAsgnTblNm //
                                            , dsAcctTblNm));
                            if (VALIDATION_NORMAL.equals(validationSts)) {
                                prosResrcAsgTMsg //
                                = editProsTrtyResrcAsgTMsgByAttrb(prosResrcAsgTMsg, procInfoBean, procTp, !isResrcAsgExists);
                            }
                        }
                    }
                }
                setTrtyTrkProcStsCd(rsSet, wkUpdTMsg);
                wkUpdTMsgMap.put(wkUpdTMsg.trtyTrkTrtyUpdPk.getValue(), wkUpdTMsg);

            } // end while
            if (!wkUpdTMsgMap.isEmpty()) {
                if (isStsNormal(validationSts)) {
                    if (ZYPCommonFunc.hasValue(acctResrcAsgTMsg.dsAcctNm)) {
                        resistAcctTrtyResrcAsg(acctResrcAsgTMsg, procInfoBean);
                    }
                    if (ZYPCommonFunc.hasValue(prosResrcAsgTMsg.dsAcctNm)) {
                        resistProsTrtyResrcAsg(prosResrcAsgTMsg, procInfoBean);
                    }
                } else {
                    rollback();
                }
                updateTrtyTrkTrtyUpdProcSts(wkUpdTMsgMap, validationSts);

                commit();
            }

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    private ACCT_TRTY_RESRC_ASGTMsg getAcctResrcAsgTMsg(ResultSet rsSet) throws SQLException {

        String dsAcctNum = rsSet.getString("DS_ACCT_NUM");
        String locNum = rsSet.getString("LOC_NUM");
        ACCT_TRTY_RESRC_ASGTMsg atraTMsg //
        = getAcctTrtyResrcAsgTMsg(dsAcctNum, locNum);
        if (atraTMsg == null) {
            atraTMsg = new ACCT_TRTY_RESRC_ASGTMsg();
            ZYPEZDItemValueSetter.setValue(atraTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.dsAcctNum, dsAcctNum);
            ZYPEZDItemValueSetter.setValue(atraTMsg.locNum, locNum);
            ZYPEZDItemValueSetter.setValue(atraTMsg.manEntryFlg, ZYPConstant.FLG_ON_Y);

        }
        return atraTMsg;
    }

    private PROS_TRTY_RESRC_ASGTMsg getProsResrcAsgTMsg(ResultSet rsSet) throws SQLException {

        String dsAcctNum = rsSet.getString("DS_ACCT_NUM");
        String locNum = rsSet.getString("LOC_NUM");
        PROS_TRTY_RESRC_ASGTMsg ptraTMsg //
        = getProsTrtyResrcAsgTMsg(dsAcctNum, locNum);
        if (ptraTMsg == null) {
            ptraTMsg = new PROS_TRTY_RESRC_ASGTMsg();
            ZYPEZDItemValueSetter.setValue(ptraTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.dsAcctNum, dsAcctNum);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.locNum, locNum);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.manEntryFlg, ZYPConstant.FLG_ON_Y);

        }
        return ptraTMsg;
    }

    /**
     * @param rsSet
     * @param wkUpdTMsg
     * @throws SQLException
     */
    private void setTrtyTrkProcStsCd(ResultSet rsSet, TRTY_TRK_TRTY_UPDTMsg wkUpdTMsg) throws SQLException {
        if (errInfoList.size() == 0) {
            commitCount++;
            ZYPEZDItemValueSetter.setValue(wkUpdTMsg.trtyTrkProcStsCd, TRTY_TRK_PROC_STS.COMPLETE);
        } else {
            editErrorMessage(rsSet);
            errorCount++;
            errInfoList.clear();
            ZYPEZDItemValueSetter.setValue(wkUpdTMsg.trtyTrkProcStsCd, TRTY_TRK_PROC_STS.ERROR);
        }
    }

    private void setProcInfoBeanFromRsSet(NMAB509001ProcInfoBean procInfoBean, ResultSet rsSet) throws SQLException {

        procInfoBean.setTrtyTrkTrtyUpdPk(rsSet.getBigDecimal("TRTY_TRK_TRTY_UPD_PK"));
        procInfoBean.setDsAcctNum(rsSet.getString("DS_ACCT_NUM"));
        procInfoBean.setLocNum(rsSet.getString("LOC_NUM"));
        procInfoBean.setTrtyOrgCd(rsSet.getString("TRTY_ORG_CD"));
        procInfoBean.setLineBizRoleTpCd(rsSet.getString("LINE_BIZ_ROLE_TP_CD"));
        procInfoBean.setTrtyCdValTpCd(rsSet.getString("TRTY_CD_VAL_TP_CD"));
        procInfoBean.setOutBizTrtyFlg(rsSet.getString("OUT_BIZ_TRTY_FLG"));
        procInfoBean.setGeoTrtyFlg(rsSet.getString("GEO_TRTY_FLG"));
        procInfoBean.setOrigLineBizRoleTpCd(rsSet.getString("ORIG_LINE_BIZ_ROLE_TP_CD"));
    }

    private String getProcTp(ResultSet rsSet) throws SQLException {
        if (isOutOfBiz(rsSet.getString("OUT_BIZ_TRTY_FLG"))) {
            return PROC_TP_OUT_OF_BIZ;
        }
        if (isCsaGeo(rsSet.getString("GEO_TRTY_FLG"))) {
            return PROC_TP_SKIP;
        }
        String lineBizRoleTpCd = rsSet.getString("LINE_BIZ_ROLE_TP_CD");
        if (isMoveToGeo(lineBizRoleTpCd)) {
            return PROC_TP_MOVE_TO_GEO;
        }
        if (ZYPCommonFunc.hasValue(lineBizRoleTpCd)) {
            return PROC_TP_REASGN_ACCT;
        }
        // 2017/08/29 QC#20384 Add Start
        if (!ZYPCommonFunc.hasValue(lineBizRoleTpCd)) {
            return PROC_TP_ASGN_TRTY;
        }
        // 2017/08/29 QC#20384 Add End
        return PROC_TP_REMOVE_TRTY;
    }

    /**
     * @param validationSts
     * @return
     */
    private boolean isStsNormal(String validationSts) {
        return !VALIDATION_ERROR_ACCT_LOC.equals(validationSts) //
                && !VALIDATION_ERROR_LINE.equals(validationSts);
    }

    /**
     * @param wkUpdTMsgMap
     * @param validationSts String
     */
    private void updateTrtyTrkTrtyUpdProcSts(Map<BigDecimal, TRTY_TRK_TRTY_UPDTMsg> wkUpdTMsgMap, String validationSts) {
        List<TRTY_TRK_TRTY_UPDTMsg> wkUpdTMsgList = new ArrayList<TRTY_TRK_TRTY_UPDTMsg>(wkUpdTMsgMap.size());

        for (Entry<BigDecimal, TRTY_TRK_TRTY_UPDTMsg> wkUpdTMsgEntry : wkUpdTMsgMap.entrySet()) {
            TRTY_TRK_TRTY_UPDTMsg tMsg = wkUpdTMsgEntry.getValue();
            String trtyTrkProcStsCd = tMsg.trtyTrkProcStsCd.getValue();
            tMsg = (TRTY_TRK_TRTY_UPDTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsg);
            if (tMsg == null) {
                continue;
            }
            if (VALIDATION_ERROR_ACCT_LOC.equals(validationSts)) {
                ZYPEZDItemValueSetter.setValue(tMsg.trtyTrkProcStsCd, TRTY_TRK_PROC_STS.ERROR);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.trtyTrkProcStsCd, trtyTrkProcStsCd);
            }
            wkUpdTMsgList.add(tMsg);
        }
        int cnt = S21FastTBLAccessor.update(//
                wkUpdTMsgList.toArray(new TRTY_TRK_TRTY_UPDTMsg[wkUpdTMsgList.size()]));
        if (cnt != wkUpdTMsgList.size()) {
            rollback();
            throw new S21AbendException(NMAM8571E, new String[] {"updateTrtyTrkTrtyUpdProcSts", "TRTY_TRK_TRTY_UPD", String.valueOf(cnt) });
        }
    }

    private boolean isBreakAcctLoc(TRTY_TRK_TRTY_UPDTMsg wkUpdTMsg, NMAB509001ProcInfoBean procInfoBean) {
        if (!isSameValue(wkUpdTMsg.dsAcctNum.getValue(), procInfoBean.getDsAcctNum())) {
            return true;
        }
        if (!isSameValue(wkUpdTMsg.locNum.getValue(), procInfoBean.getLocNum())) {
            return true;
        }
        return false;
    }

    private TRTY_TRK_TRTY_UPDTMsg getWkUpdTMsg(ResultSet rsSet) throws SQLException {
        TRTY_TRK_TRTY_UPDTMsg tMsg = new TRTY_TRK_TRTY_UPDTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyTrkTrtyUpdPk, rsSet.getBigDecimal("TRTY_TRK_TRTY_UPD_PK"));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, rsSet.getString("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.locNum, rsSet.getString("LOC_NUM"));

        return tMsg;
    }

    private boolean isSameValue(String currVal, String prevVal) {
        if (!ZYPCommonFunc.hasValue(currVal) && !ZYPCommonFunc.hasValue(prevVal)) {
            return true;
        }
        if (!ZYPCommonFunc.hasValue(currVal) || !ZYPCommonFunc.hasValue(prevVal)) {
            return false;
        }

        return currVal.equals(prevVal);
    }

    private String updateTerritory(//
            String procTp //
            , ResultSet rsSet //
            , String srcLineBizTpCd //
            , ACCT_TRTY_ROLE_ASGTMsg roleAsgTMsg //
            , NMAB509001ProcInfoBean procInfoBean //
            , String trtyRoleAsgnTblNm //
            , String dsAcctTblNm) throws SQLException {


        String locNum = procInfoBean.getLocNum();

        String lineBizRoleTpCd = roleAsgTMsg.lineBizRoleTpCd.getValue();

        // 2017/08/29 QC#20384 Mod Start
        //LINE_BIZ_ROLE_RANK_DECNTMsg rrdTMsg = getLineBizRoleRankDecnInfo(procInfoBean, srcLineBizTpCd, lineBizRoleTpCd);
        LINE_BIZ_ROLE_RANK_DECNTMsg rrdTMsg = getLineBizRoleRankDecnInfo(procInfoBean, srcLineBizTpCd, lineBizRoleTpCd, procTp);
        // 2017/08/29 QC#20384 Mod End
        if (rrdTMsg == null) {
            errInfoList.add(//
                    getMap(NMAM0009E, new String[] {"getLineBizRoleRankDecnInfo.LINE_BIZ_ROLE_TP_CD:" + lineBizRoleTpCd }));
            return VALIDATION_ERROR_ACCT_LOC;
        }

        // 2017/09/11 QC#21008 Mod Start
        //String orgCd = roleAsgTMsg.orgCd.getValue();
        //updateTerritoryRuleToExpired(orgCd, locNum);
        String roleAsgOrgCd = roleAsgTMsg.orgCd.getValue();
        String origOrgCd = rsSet.getString("TRTY_ORG_CD");
        // 2017/11/24 QC#22299 Mod Start
        List<BigDecimal> trtyRulePkList = getTerritoryRule(roleAsgOrgCd, locNum);
        if (trtyRulePkList.size() == 0 || (trtyRulePkList.size() > 0 && !origOrgCd.equals(roleAsgOrgCd))) {
            updateTerritoryRuleToExpired(trtyRulePkList);
            deleteTrtyRoleAsg(roleAsgTMsg, trtyRoleAsgnTblNm);
        }
        // 2017/11/24 QC#22299 Mod End
        // 2017/09/11 QC#21008 Mod End

        // 2017/08/29 QC#20384 Mod Start
        //if (!isProcTpReassignAcct(procTp) && !isProcTpOutOfBiz(procTp)) {
        if (!isProcTpReassignAcct(procTp) && !isProcTpOutOfBiz(procTp) && !isProcTpAssignTrty(procTp)) {
        // 2017/08/29 QC#20384 Mod End
            return VALIDATION_NORMAL;
        }

        // 2017/09/11 QC#21008 Mod Start
        //orgCd = rsSet.getString("TRTY_ORG_CD");
        //List<Map<String, String>> resrcList = getResourceData(rrdTMsg.lineBizRankNum.getValue(), orgCd, procTp);
        //if (resrcList == null || resrcList.isEmpty()) {
        //    errInfoList.add(//
        //            getMap(NMAM0009E, new String[] {"getResourceData.ORG_CD:" + orgCd }));
        //    return VALIDATION_ERROR_ACCT_LOC;
        //}
        List<Map<String, String>> resrcList = getResourceData(rrdTMsg.lineBizRankNum.getValue(), origOrgCd, procTp);
        if (resrcList == null || resrcList.isEmpty()) {
            errInfoList.add(//
                    getMap(NMAM0009E, new String[] {"getResourceData.ORG_CD:" + origOrgCd }));
            return VALIDATION_ERROR_ACCT_LOC;
        }
        // 2017/09/11 QC#21008 Mod End

        // 2017/09/11 QC#21008 Mod Start
        //String sts = resistTrtyRule(rsSet, orgCd, locNum, procTp);
        // 2017/09/12 QC#20528 Mod Start
        //String sts = resistTrtyRule(rsSet, roleAsgOrgCd, locNum, procTp);
        String sts = null;
        // 2017/11/24 QC#22299 Mod Start
        // if (isProcTpOutOfBiz(procTp)) {
        //     sts = resistTrtyRule(rsSet, origOrgCd, locNum, procTp);
        // } else {
        //     sts = resistTrtyRule(rsSet, roleAsgOrgCd, locNum, procTp);
        // }
        sts = resistTrtyRule(rsSet, origOrgCd, locNum, procTp);
        // 2017/11/24 QC#22299 Mod End
        // 2017/09/12 QC#20528 Mod End
        // 2017/09/11 QC#21008 Mod End
        if (!VALIDATION_NORMAL.equals(sts)) {
            return VALIDATION_ERROR_ACCT_LOC;
        }

        for (Map<String, String> resrcMap : resrcList) {
            // 2017/08/29 QC#20384 Mod Start
            //procInfoBean.setTocCd(resrcMap.get("TOC_CD"));
            //procInfoBean.setPsnCd(resrcMap.get("PSN_CD"));
            if (S21StringUtil.isEquals(resrcMap.get("RESRC_NON_SLS_REP_FLG"), ZYPConstant.FLG_OFF_N)) {
                procInfoBean.setTocCd(resrcMap.get("TOC_CD"));
                procInfoBean.setPsnCd(resrcMap.get("PSN_CD"));
            }
            // 2017/08/29 QC#20384 Mod End
            if (trtyRulePkList.size() == 0 || (trtyRulePkList.size() > 0 && !origOrgCd.equals(roleAsgOrgCd))) {
                registTrtyRoleAsg(trtyRoleAsgnTblNm, rsSet, roleAsgTMsg, resrcMap, rrdTMsg, procTp);
            }
        }

        return VALIDATION_NORMAL;
    }

    private void resistAcctTrtyResrcAsg(//
            ACCT_TRTY_RESRC_ASGTMsg resrcAsgTMsg, NMAB509001ProcInfoBean procInfoBean) {

        ACCT_TRTY_RESRC_ASGTMsg atraTMsg //
        = getAcctTrtyResrcAsgTMsg(procInfoBean.getDsAcctNum(), procInfoBean.getLocNum());
        if (atraTMsg == null) {
            S21FastTBLAccessor.insert(resrcAsgTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(resrcAsgTMsg.getReturnCode())) {
                rollback();
                throw new S21AbendException(NMAM0282E //
                        , new String[] {"ACCT_TRTY_RESRC_ASG : " + resrcAsgTMsg.getReturnCode() + "/" + resrcAsgTMsg.toString() });
            }
            return;
        }

        ZYPEZDItemValueSetter.setValue(atraTMsg.glblCmpyCd, this.getGlobalCompanyCode());
        atraTMsg = (ACCT_TRTY_RESRC_ASGTMsg) S21FastTBLAccessor.findByKeyForUpdate(atraTMsg);
        if (atraTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(atraTMsg.getReturnCode())) {
            rollback();
            throw new S21AbendException(ZZMM0014E //
                    , new String[] {"ACCT_TRTY_RESRC_ASG" //
                            , "DS_ACCT_NUM/LOC_NUM" //
                            , procInfoBean.getDsAcctNum() + "/" + procInfoBean.getLocNum() });
        }
        EZDMsg.copy(resrcAsgTMsg, null, atraTMsg, null);

        S21FastTBLAccessor.update(atraTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(atraTMsg.getReturnCode())) {
            rollback();
            throw new S21AbendException(NMAM0177E //
                    , new String[] {"ACCT_TRTY_RESRC_ASG : " + atraTMsg.getReturnCode() + "/" + atraTMsg.toString() });
        }
    }

    private void resistProsTrtyResrcAsg(//
            PROS_TRTY_RESRC_ASGTMsg resrcAsgTMsg, NMAB509001ProcInfoBean procInfoBean) {

        PROS_TRTY_RESRC_ASGTMsg ptraTMsg //
        = getProsTrtyResrcAsgTMsg(procInfoBean.getDsAcctNum(), procInfoBean.getLocNum());
        if (ptraTMsg == null) {
            S21FastTBLAccessor.insert(resrcAsgTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(resrcAsgTMsg.getReturnCode())) {
                rollback();
                throw new S21AbendException(NMAM0282E //
                        , new String[] {"PROS_TRTY_RESRC_ASG : " + resrcAsgTMsg.getReturnCode() + "/" + resrcAsgTMsg.toString() });
            }
            return;
        }

        ZYPEZDItemValueSetter.setValue(ptraTMsg.glblCmpyCd, this.getGlobalCompanyCode());
        ptraTMsg = (PROS_TRTY_RESRC_ASGTMsg) S21FastTBLAccessor.findByKeyForUpdate(ptraTMsg);
        if (ptraTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(ptraTMsg.getReturnCode())) {
            rollback();
            throw new S21AbendException(ZZMM0014E //
                    , new String[] {"PROS_TRTY_RESRC_ASG" //
                            , "DS_ACCT_NUM/LOC_NUM" //
                            , procInfoBean.getDsAcctNum() + "/" + procInfoBean.getLocNum() });
        }
        EZDMsg.copy(resrcAsgTMsg, null, ptraTMsg, null);

        S21FastTBLAccessor.update(ptraTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(ptraTMsg.getReturnCode())) {
            rollback();
            throw new S21AbendException(NMAM0177E //
                    , new String[] {"PROS_TRTY_RESRC_ASG : " + ptraTMsg.getReturnCode() + "/" + ptraTMsg.toString() });
        }
    }

    /**
     * @param atraTMsg ACCT_TRTY_RESRC_ASGTMsg
     * @param procInfoBean
     * @param isInsert
     * @param procTp
     * @param orgCd
     * @param psnCd
     * @param tocCd
     * @param lineBizRoleTpCd
     * @return
     */
    private ACCT_TRTY_RESRC_ASGTMsg editAcctTrtyResrcAsgTMsgByAttrb(//
            ACCT_TRTY_RESRC_ASGTMsg atraTMsg, NMAB509001ProcInfoBean procInfoBean, String procTp, boolean isInsert) {

        String orgCd = null;
        String psnCd = null;
        String tocCd = null;
        String lineBizRoleTpCd = null;
        String manEntryFlg = ZYPConstant.FLG_OFF_N;
        if (isProcTpReassignAcct(procTp)) {
            orgCd = procInfoBean.getTrtyOrgCd();
            lineBizRoleTpCd = procInfoBean.getLineBizRoleTpCd();
            manEntryFlg = ZYPConstant.FLG_ON_Y;
            if (ZYPCommonFunc.hasValue(procInfoBean.getLineBizRankNum())) { // non Specialist
                psnCd = procInfoBean.getPsnCd();
                tocCd = procInfoBean.getTocCd();
            }
        }
        String asgTrtyAttrbCd = procInfoBean.getAsgTrtyAttrbCd();

        if (ASG_TRTY_ATTRB.ATTRIBUTE1_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_01, orgCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_01, psnCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_01, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_01, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE2_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_02, orgCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_02, psnCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_02, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_02, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE3_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_03, orgCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_03, psnCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_03, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_03, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE4_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_04, orgCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_04, psnCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_04, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_04, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE5_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_05, orgCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_05, psnCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_05, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_05, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE6_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_06, orgCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_06, psnCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_06, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_06, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE7_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_07, orgCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_07, psnCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_07, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_07, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE8_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_08, orgCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_08, psnCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_08, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_08, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE9_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_09, orgCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_09, psnCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_09, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_09, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE10_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_10, orgCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_10, psnCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_10, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_10, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE11_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_11, orgCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_11, psnCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_11, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_11, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE12_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_12, orgCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_12, psnCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_12, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_12, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE13_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_13, orgCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_13, psnCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_13, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_13, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE14_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_14, orgCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_14, psnCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_14, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_14, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE15_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_15, orgCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_15, psnCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_15, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_15, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE16_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_16, orgCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_16, psnCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_16, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_16, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE17_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_17, orgCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_17, psnCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_17, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_17, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE18_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_18, orgCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_18, psnCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_18, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_18, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE19_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_19, orgCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_19, psnCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_19, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_19, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE20_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_20, orgCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_20, psnCd);
            ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_20, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_20, lineBizRoleTpCd);
            }
        }

        ZYPEZDItemValueSetter.setValue(atraTMsg.manEntryFlg, manEntryFlg);

        return atraTMsg;
    }

    /**
     * @param atraTMsg ACCT_TRTY_RESRC_ASGTMsg
     * @param procInfoBean
     * @param isInsert
     * @param procTp
     * @param orgCd
     * @param psnCd
     * @param tocCd
     * @param lineBizRoleTpCd
     * @return
     */
    private PROS_TRTY_RESRC_ASGTMsg editProsTrtyResrcAsgTMsgByAttrb(//
            PROS_TRTY_RESRC_ASGTMsg ptraTMsg, NMAB509001ProcInfoBean procInfoBean, String procTp, boolean isInsert) {

        String orgCd = null;
        String psnCd = null;
        String tocCd = null;
        String lineBizRoleTpCd = null;
        String manEntryFlg = ZYPConstant.FLG_OFF_N;
        if (isProcTpReassignAcct(procTp)) {
            orgCd = procInfoBean.getTrtyOrgCd();
            lineBizRoleTpCd = procInfoBean.getLineBizRoleTpCd();
            manEntryFlg = ZYPConstant.FLG_ON_Y;
            if (ZYPCommonFunc.hasValue(procInfoBean.getLineBizRankNum())) { // non Specialist
                psnCd = procInfoBean.getPsnCd();
                tocCd = procInfoBean.getTocCd();
            }
        }
        String asgTrtyAttrbCd = procInfoBean.getAsgTrtyAttrbCd();

        if (ASG_TRTY_ATTRB.ATTRIBUTE1_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_01, orgCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_01, psnCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_01, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_01, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE2_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_02, orgCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_02, psnCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_02, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_02, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE3_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_03, orgCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_03, psnCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_03, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_03, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE4_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_04, orgCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_04, psnCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_04, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_04, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE5_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_05, orgCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_05, psnCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_05, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_05, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE6_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_06, orgCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_06, psnCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_06, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_06, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE7_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_07, orgCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_07, psnCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_07, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_07, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE8_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_08, orgCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_08, psnCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_08, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_08, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE9_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_09, orgCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_09, psnCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_09, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_09, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE10_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_10, orgCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_10, psnCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_10, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_10, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE11_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_11, orgCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_11, psnCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_11, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_11, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE12_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_12, orgCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_12, psnCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_12, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_12, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE13_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_13, orgCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_13, psnCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_13, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_13, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE14_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_14, orgCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_14, psnCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_14, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_14, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE15_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_15, orgCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_15, psnCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_15, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_15, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE16_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_16, orgCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_16, psnCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_16, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_16, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE17_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_17, orgCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_17, psnCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_17, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_17, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE18_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_18, orgCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_18, psnCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_18, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_18, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE19_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_19, orgCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_19, psnCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_19, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_19, lineBizRoleTpCd);
            }

        } else if (ASG_TRTY_ATTRB.ATTRIBUTE20_ID.equals(asgTrtyAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_20, orgCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_20, psnCd);
            ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_20, tocCd);
            if (isInsert) {
                ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_20, lineBizRoleTpCd);
            }
        }

        ZYPEZDItemValueSetter.setValue(ptraTMsg.manEntryFlg, manEntryFlg);

        return ptraTMsg;
    }

    private void registTrtyRoleAsg(//
            String trtyRoleAsgnTblNm //
            , ResultSet rsSet //
            , ACCT_TRTY_ROLE_ASGTMsg ttTraTMsg //
            , Map<String, String> resrcMap //
            , LINE_BIZ_ROLE_RANK_DECNTMsg rrdTMsg //
            , String procTp) throws SQLException {
        // 2017/08/29 QC#20384 Mod Start
        //if (!isProcTpReassignAcct(procTp)) {
        if (!isProcTpReassignAcct(procTp) && !isProcTpAssignTrty(procTp)) {
        // 2017/08/29 QC#20384 Mod End
            return;
        }

        ACCT_TRTY_ROLE_ASGTMsg traTMsg = getResistTrtyRoleAsgTMsg(rsSet, ttTraTMsg, resrcMap, rrdTMsg);

        if ("PROS_TRTY_ROLE_ASG".equals(trtyRoleAsgnTblNm)) {
            PROS_TRTY_ROLE_ASGTMsg prosTMsg = new PROS_TRTY_ROLE_ASGTMsg();
            EZDMsg.copy(traTMsg, null, prosTMsg, null);
            EZDTBLAccessor.insert(prosTMsg);
            return;
        }
        EZDTBLAccessor.insert(traTMsg);
        return;
    }

    private ACCT_TRTY_ROLE_ASGTMsg getResistTrtyRoleAsgTMsg(//
            ResultSet rsSet, ACCT_TRTY_ROLE_ASGTMsg ttTraTMsg, Map<String, String> resrcMap, LINE_BIZ_ROLE_RANK_DECNTMsg rrdTMsg) throws SQLException {
        ACCT_TRTY_ROLE_ASGTMsg tMsg = new ACCT_TRTY_ROLE_ASGTMsg();

        EZDMsg.copy(ttTraTMsg, null, tMsg, null);

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(//
                tMsg.acctTrtyRoleAsgPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ACCT_TRTY_ROLE_ASG_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, rsSet.getString("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.locNum, rsSet.getString("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.orgCd, rsSet.getString("TRTY_ORG_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.lineBizRoleTpCd, rsSet.getString("LINE_BIZ_ROLE_TP_CD"));

        ZYPEZDItemValueSetter.setValue(tMsg.orgNm, resrcMap.get("ORG_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.trtyGrpTpCd, resrcMap.get("TRTY_GRP_TP_CD"));
        tMsg.orgRankNum.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.tocCd, resrcMap.get("TOC_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.psnCd, resrcMap.get("PSN_CD"));

        ZYPEZDItemValueSetter.setValue(tMsg.manEntryFlg, ZYPConstant.FLG_ON_Y);

        ZYPEZDItemValueSetter.setValue(tMsg.trtyTpCd, resrcMap.get("TRTY_TP_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.asgTrtyAttrbCd, rrdTMsg.asgTrtyAttrbCd);
        // 2018/08/22 QC#25361 mod start
//        ZYPEZDItemValueSetter.setValue(tMsg.nonSlsRepFlg, resrcMap.get("NON_SLS_REP_FLG"));
        ZYPEZDItemValueSetter.setValue(tMsg.nonSlsRepFlg, resrcMap.get("RESRC_NON_SLS_REP_FLG"));
        // 2018/08/22 QC#25361 mod end
        if (!ZYPCommonFunc.hasValue(tMsg.nonSlsRepFlg)) {
            ZYPEZDItemValueSetter.setValue(tMsg.nonSlsRepFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.lineBizTpCd, rrdTMsg.srcLineBizTpCd);

        return tMsg;
    }

    private String resistTrtyRule(//
            ResultSet rsSet, String orgCd, String locNum, String procTp) throws SQLException {

        // 2017/08/29 QC#20384 Mod Start
        //if (!(isProcTpReassignAcct(procTp) || isOutOfBiz(rsSet.getString("OUT_BIZ_TRTY_FLG")))) {
        if (!(isProcTpReassignAcct(procTp) || isOutOfBiz(rsSet.getString("OUT_BIZ_TRTY_FLG")) || isProcTpAssignTrty(procTp))) {
        // 2017/08/29 QC#20384 Mod End
            return VALIDATION_NORMAL;
        }

        Map<String, String> trtyMap = getTrtyForResistTrtyRule(orgCd);
        if (trtyMap == null) {
            errInfoList.add(//
                    getMap(NMAM0009E, new String[] {"getTrtyForResistTrtyRule.ORG_CD:" + orgCd }));
            return VALIDATION_ERROR_ACCT_LOC;
        }
        String orgStruTpCd = ORG_STRU_TP.TERRITORY_STRUCTURE;
        //        String orgTierCd = "";
        orgStruTpCd = trtyMap.get("ORG_STRU_TP_CD");
        //        orgTierCd = trtyMap.get("ORG_TIER_CD");

        // 2018/03/26 QC#23157 Add Start
        if (isProcTpReassignAcct(procTp) || isProcTpAssignTrty(procTp)) {
            if (isGeoTerritory(trtyMap)) {
                String postCd = getLocPostCd(locNum);
                // 2018/07/24 QC#26753 Mod Start
//                if (!isExistsPostCdInTrtyRule(trtyMap, postCd)) {
                // postal rule only & not exists postal code
                if (isPostalRuleOnly(trtyMap) &&
                        !isExistsPostCdInTrtyRule(trtyMap, postCd)) {
                // 2018/07/24 QC#26753 Mod End
                    insertTrtyRuleForPostalCode(trtyMap, postCd);
                }
                return VALIDATION_NORMAL;
            }
        }
        // 2018/03/26 QC#23157 Add End

        if (existsLocNumInTrtyRule(locNum, orgCd, orgStruTpCd)) {
            return VALIDATION_NORMAL;
        }

        if (existsOrLogicInTrtyRule(locNum, orgCd, orgStruTpCd)) {
            return VALIDATION_NORMAL;
        }

        TRTY_RULETMsg tMsg = getResistTrtyRuleTMsg(orgStruTpCd, orgCd, locNum);
        S21FastTBLAccessor.insert(tMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            rollback();
            throw new S21AbendException(NMAM0282E, new String[] {"TRTY_RULE : " + tMsg.getReturnCode() //
                    + "/" + tMsg.toString() });
        }
        return VALIDATION_NORMAL;
    }

    private TRTY_RULETMsg getResistTrtyRuleTMsg(String orgStruTpCd, String orgCd, String locNum) {
        TRTY_RULETMsg tMsg = new TRTY_RULETMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(//
                tMsg.trtyRulePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TRTY_RULE_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.orgStruTpCd, orgStruTpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.orgCd, orgCd);
        // Mod Start 2017/03/15 QC#15878
//        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleTpCd, TRTY_RULE_TP.ACCOUNT_OR_SITE_NUMBER);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleTpCd, TRTY_RULE_TP.LOCATION_NUMBER);
        // Mod End 2017/03/15 QC#15878
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleFromValTxt, locNum);
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, this.slsDt);
        //        ZYPEZDItemValueSetter.setValue(tMsg.orgTierCd, orgTierCd);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleOprdTpCd, TRTY_RULE_OPRD_TP.EQUAL);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleLogicTpCd, TRTY_RULE_LOGIC_TP.OR);

        return tMsg;
    }

    private boolean existsOrLogicInTrtyRule(String locNum, String orgCd, String orgStruTpCd) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("orgCd", orgCd);
        param.put("orgStruTpCd", orgStruTpCd);
        param.put("slsDt", this.slsDt);

        // Mod Start 2017/03/15 QC#15878
//        param.put("tpLocNum", TRTY_RULE_TP.ACCOUNT_OR_SITE_NUMBER); // 4:Location#
        param.put("tpLocNum", TRTY_RULE_TP.LOCATION_NUMBER); // 4:Location#
        // Mod End 2017/03/15 QC#15878
        param.put("tpOr", TRTY_RULE_LOGIC_TP.OR);

        Integer cnt //
        = (Integer) this.ssmBatchClient.queryObject("existsOrLogicInTrtyRule", param);

        return cnt > 0;
    }

    private boolean existsLocNumInTrtyRule(String locNum, String orgCd, String orgStruTpCd) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        // Mod Start 2017/03/15 QC#15878
//        param.put("tpLocNum", TRTY_RULE_TP.ACCOUNT_OR_SITE_NUMBER); // 4:Location#
        param.put("tpLocNum", TRTY_RULE_TP.LOCATION_NUMBER); // 4:Location#
        // Mod End 2017/03/15 QC#15878
        param.put("orgStruTpCd", orgStruTpCd);
        param.put("slsDt", this.slsDt);
        param.put("locNum", locNum);
        param.put("orgCd", orgCd);

        Integer cnt //
        = (Integer) this.ssmBatchClient.queryObject("getCountLocNumInTrtyRule", param);

        return cnt > 0;
    }

    private Map<String, String> getTrtyForResistTrtyRule(String orgCd) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("orgCd", orgCd);
        param.put("current", GNRN_TP.CURRENT);
        param.put("future", GNRN_TP.FUTURE);
        param.put("slsDt", this.slsDt);

        List<Map<String, String>> rsList //
        = (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getTrtyForResistTrtyRule", param);
        if (rsList == null || rsList.size() == 0) {
            return null;
        }

        return rsList.get(0);
    }

    private List<Map<String, String>> getResourceData(String lineBizRankNum, String orgCd, String procTp) {

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("orgCd", orgCd);
        param.put("current", GNRN_TP.CURRENT);
        param.put("future", GNRN_TP.FUTURE);
        param.put("slsDt", this.slsDt);
        param.put("firstOrgCd", "1"); // Sales
        // 2017/08/30 QC#20601 Add Start
        param.put("lineBizRankNum", lineBizRankNum);
        // 2017/08/30 QC#20601 Add End

        List<Map<String, String>> rsList //
        = (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getResourceData", param);

        return rsList;
    }

    private boolean isMoveToGeo(String lineBizRoleTpCd) {
        return LINE_BIZ_ROLE_TP.REMOVED_FROM_TT.equals(lineBizRoleTpCd);
    }

    private boolean isOutOfBiz(String outBizTrtyFlg) {
        return ZYPConstant.FLG_ON_Y.equals(outBizTrtyFlg);
    }

    private boolean isCsaGeo(String geoTrtyFlg) {
        return ZYPConstant.FLG_ON_Y.equals(geoTrtyFlg);
    }

    private boolean isProcTpOutOfBiz(String procTp) {
        return PROC_TP_OUT_OF_BIZ.equals(procTp);
    }

    private boolean isProcTpMoveToGeo(String procTp) {
        return PROC_TP_MOVE_TO_GEO.equals(procTp);
    }

    private boolean isProcTpReassignAcct(String procTp) {
        return PROC_TP_REASGN_ACCT.equals(procTp);
    }

    private boolean isProcTpRemoveTrty(String procTp) {
        return PROC_TP_REMOVE_TRTY.equals(procTp);
    }

    // 2017/08/29 QC#20384 Add Start
    private boolean isProcTpAssignTrty(String procTp) {
        return PROC_TP_ASGN_TRTY.equals(procTp);
    }
    // 2017/08/29 QC#20384 Add End

    private void deleteTrtyRoleAsg(ACCT_TRTY_ROLE_ASGTMsg ttTraTMsg, String trtyRoleAsgnTblNm) {
        if (!ZYPCommonFunc.hasValue(ttTraTMsg.acctTrtyRoleAsgPk)) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(ttTraTMsg.glblCmpyCd, this.glblCmpyCd);
        if ("PROS_TRTY_ROLE_ASG".equals(trtyRoleAsgnTblNm)) {
            PROS_TRTY_ROLE_ASGTMsg prosTMsg = new PROS_TRTY_ROLE_ASGTMsg();
            EZDMsg.copy(ttTraTMsg, null, prosTMsg, null);
            prosTMsg = (PROS_TRTY_ROLE_ASGTMsg) S21FastTBLAccessor.findByKeyForUpdate(prosTMsg);
            if (prosTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(prosTMsg.getReturnCode())) {
                rollback();
                throw new S21AbendException(ZZMM0014E //
                        , new String[] {"PROS_TRTY_ROLE_ASG", "PROS_TRTY_ROLE_ASG_PK", ttTraTMsg.acctTrtyRoleAsgPk.getValue().toString() });
            }
// 2017/11/24 QC#22299 Mod Start
//            EZDTBLAccessor.logicalRemove(prosTMsg);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prosTMsg.getReturnCode())) {
//                rollback();
//                throw new S21AbendException(NMAM0208E //
//                        , new String[] {"PROS_TRTY_ROLE_ASG : " + prosTMsg.getReturnCode() + "/" + prosTMsg.toString() });
//            }
            int resRecordCnt = S21FastTBLAccessor.removePhysical(new EZDTMsg[] {prosTMsg});
            if (resRecordCnt != 1) {
// 2017/11/24 QC#22299 Mod End
                rollback();
                throw new S21AbendException(NMAM0208E //
                        , new String[] {"PROS_TRTY_ROLE_ASG : " + prosTMsg.getReturnCode() + "/" + prosTMsg.toString() });
            }
            return;
        }

        ttTraTMsg = (ACCT_TRTY_ROLE_ASGTMsg) S21FastTBLAccessor.findByKeyForUpdate(ttTraTMsg);
        if (ttTraTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(ttTraTMsg.getReturnCode())) {
            rollback();
            throw new S21AbendException(ZZMM0014E //
                    , new String[] {"ACCT_TRTY_ROLE_ASG", "ACCT_TRTY_ROLE_ASG_PK", ttTraTMsg.acctTrtyRoleAsgPk.getValue().toString() });
        }
// 2017/11/24 QC#22299 Mod Start
//        EZDTBLAccessor.logicalRemove(ttTraTMsg);
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ttTraTMsg.getReturnCode())) {
//            rollback();
//            throw new S21AbendException(NMAM0208E //
//                    , new String[] {"ACCT_TRTY_ROLE_ASG : " + ttTraTMsg.getReturnCode() + "/" + ttTraTMsg.toString() });
//        }
        int resRecordCnt = S21FastTBLAccessor.removePhysical(new EZDTMsg[] {ttTraTMsg});
        if (resRecordCnt != 1) {
// 2017/11/24 QC#22299 Mod End
            rollback();
            throw new S21AbendException(NMAM0208E //
                    , new String[] {"ACCT_TRTY_ROLE_ASG : " + ttTraTMsg.getReturnCode() + "/" + ttTraTMsg.toString() });
        }
        return;
    }

    private void updateTerritoryRuleToExpired(List<BigDecimal> trtyRulePkList) {
        // List<BigDecimal> trtyRulePkList = getTerritoryRule(orgCd, locNum);

        for (BigDecimal trtyRulePk : trtyRulePkList) {
            TRTY_RULETMsg tMsg = new TRTY_RULETMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.trtyRulePk, trtyRulePk);

            tMsg = (TRTY_RULETMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsg);
            if (tMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                rollback();
                throw new S21AbendException(ZZMM0014E //
                        , new String[] {"TRTY_RULE", "TRTY_RULE_PK", tMsg.trtyRulePk.getValue().toString() });
            }
            ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, ZYPDateUtil.addDays(this.slsDt, -1));

            S21FastTBLAccessor.update(tMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                rollback();
                throw new S21AbendException(NMAM0177E, new String[] {"TRTY_RULE : " + tMsg.getReturnCode() //
                        + "/" + tMsg.toString() });
            }
        }
    }

    private List<BigDecimal> getTerritoryRule(String orgCd, String locNum) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("orgCd", orgCd);
        param.put("current", GNRN_TP.CURRENT);
        param.put("future", GNRN_TP.FUTURE);
        param.put("slsDt", this.slsDt);
        // Mod Start 2017/03/15 QC#15878
//        param.put("tpLocNum", TRTY_RULE_TP.ACCOUNT_OR_SITE_NUMBER); // 4:Location#
        param.put("tpLocNum", TRTY_RULE_TP.LOCATION_NUMBER); // 4:Location#
        // Mod End 2017/03/15 QC#15878
        param.put("locNum", locNum);
        param.put("tpEqual", TRTY_RULE_OPRD_TP.EQUAL);
        param.put("tpOr", TRTY_RULE_LOGIC_TP.OR);

        List<BigDecimal> rsList //
        = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getTerritoryRule", param);
        if (rsList == null || rsList.size() == 0) {
            return new ArrayList<BigDecimal>(0);
        }

        return rsList;
    }

    private LINE_BIZ_ROLE_RANK_DECNTMsg getLineBizRoleRankDecnInfo(//
        // 2017/08/29 QC#20384 Mod Start
        //    NMAB509001ProcInfoBean procInfoBean, String srcLineBizTpCd, String lineBizRoleTpCd) {
            NMAB509001ProcInfoBean procInfoBean, String srcLineBizTpCd, String lineBizRoleTpCd, String procTp) {
        // 2017/08/29 QC#20384 Mod Start
        LINE_BIZ_ROLE_RANK_DECNTMsg rrdTMsg = new LINE_BIZ_ROLE_RANK_DECNTMsg();

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsAcctTpCd", procInfoBean.getDsAcctTpCd());
        param.put("srcLineBizTpCd", srcLineBizTpCd);
        if (ZYPCommonFunc.hasValue(procInfoBean.getLineBizRoleTpCd()) //
                && !"R".equals(procInfoBean.getLineBizRoleTpCd())) {
            param.put("lineBizRoleTpCd", procInfoBean.getLineBizRoleTpCd());
        } else {
            param.put("lineBizRoleTpCd", lineBizRoleTpCd);
        }

        List<Map<String, String>> rsList //
        = (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getLineBizRoleRankDecnInfo", param);
        if (rsList == null || rsList.size() == 0) {
            // 2017/08/29 QC#20384 Mod Start
            //errInfoList.add(getMap(NMAM0009E, new String[] {"LINE_BIZ_ROLE_TP_CD:" + procInfoBean.getLineBizRoleTpCd() }));
            //return null;
            if (isProcTpAssignTrty(procTp)) {
                return rrdTMsg;
            } else {
                errInfoList.add(getMap(NMAM0009E, new String[] {"LINE_BIZ_ROLE_TP_CD:" + procInfoBean.getLineBizRoleTpCd() }));
                return null;
            }
            // 2017/08/29 QC#20384 Mod End
        }

        Map<String, String> rsMap = rsList.get(0);

        ZYPEZDItemValueSetter.setValue(rrdTMsg.asgTrtyAttrbCd, rsMap.get("ASG_TRTY_ATTRB_CD"));
        ZYPEZDItemValueSetter.setValue(rrdTMsg.srcLineBizTpCd, rsMap.get("SRC_LINE_BIZ_TP_CD"));
        ZYPEZDItemValueSetter.setValue(rrdTMsg.lineBizRankNum, rsMap.get("LINE_BIZ_RANK_NUM"));

        procInfoBean.setAsgTrtyAttrbCd(rsMap.get("ASG_TRTY_ATTRB_CD"));
        procInfoBean.setLineBizRankNum(rsMap.get("LINE_BIZ_RANK_NUM"));

        return rrdTMsg;
    }

    private List<ACCT_TRTY_ROLE_ASGTMsg> getTrtyRoleAsg(//
            String procTp, NMAB509001ProcInfoBean procInfoBean, String trtyRoleAsgTbl, String dsAcctTbl) {
        //        ACCT_TRTY_ROLE_ASGTMsg ttTraTMsg = new ACCT_TRTY_ROLE_ASGTMsg();

        Map<String, String> param = new HashMap<String, String>();
        param.put("trtyRoleAsgTbl", trtyRoleAsgTbl);
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsAcctNum", procInfoBean.getDsAcctNum());
        param.put("locNum", procInfoBean.getLocNum());
        // 2017/08/29 QC#20384 Mod Start
        //if (isProcTpMoveToGeo(procTp) || isProcTpRemoveTrty(procTp)) {
        //    param.put("isMoveToGeoFlg", ZYPConstant.FLG_ON_Y);
        //    param.put("orgCd", procInfoBean.getTrtyOrgCd());
        //} else {
        //    param.put("isMoveToGeoFlg", ZYPConstant.FLG_OFF_N);
        //    param.put("orgCd", "");
        //}
        if (isProcTpMoveToGeo(procTp) || isProcTpRemoveTrty(procTp) || isProcTpAssignTrty(procTp)) {
            param.put("isMoveToGeoFlg", ZYPConstant.FLG_ON_Y);
            param.put("orgCd", procInfoBean.getTrtyOrgCd());
        } else {
            param.put("isMoveToGeoFlg", ZYPConstant.FLG_OFF_N);
            param.put("orgCd", "");
        }
        // 2017/08/29 QC#20384 Mod End
        if (isProcTpReassignAcct(procTp)) {
            param.put("isReassignAccountFlg", ZYPConstant.FLG_ON_Y);
            param.put("lineBizRoleTpCd", procInfoBean.getLineBizRoleTpCd());
        } else {
            param.put("isReassignAccountFlg", ZYPConstant.FLG_OFF_N);
            param.put("lineBizRoleTpCd", "");
        }
        List<Map<String, Object>> rsList //
        = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTrtyRoleAssign", param);
        if (rsList == null || rsList.size() == 0) {
            // 2017/08/29 QC#20384 Mod Start
            //if (PROC_TP_REASGN_ACCT.equals(procTp)) {
            //    return getTrtyRoleAsgFromDsAcct(dsAcctTbl, procInfoBean);
            if (isProcTpReassignAcct(procTp) || isProcTpAssignTrty(procTp)) {
                return getTrtyRoleAsgFromDsAcct(dsAcctTbl, procInfoBean, procTp);
            // 2017/08/29 QC#20384 Mod End
            } else {
                return null;
            }
        }

        //        Map<String, Object> rsMap = rsList.get(0);
        List<ACCT_TRTY_ROLE_ASGTMsg> ttTraTMsgList = new ArrayList<ACCT_TRTY_ROLE_ASGTMsg>(rsList.size());

        for (Map<String, Object> rsMap : rsList) {
            ACCT_TRTY_ROLE_ASGTMsg ttTraTMsg = new ACCT_TRTY_ROLE_ASGTMsg();
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.acctTrtyRoleAsgPk, (BigDecimal) rsMap.get("ACCT_TRTY_ROLE_ASG_PK"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.dsAcctNum, (String) rsMap.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.locNum, (String) rsMap.get("LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.orgCd, (String) rsMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.lineBizRoleTpCd, (String) rsMap.get("LINE_BIZ_ROLE_TP_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.dsAcctNm, (String) rsMap.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.billToCustCd, (String) rsMap.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.shipToCustCd, (String) rsMap.get("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.dsAcctTpCd, (String) rsMap.get("DS_ACCT_TP_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.dsAcctClsCd, (String) rsMap.get("DS_ACCT_CLS_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.firstDsAcctGrpTpCd, (String) rsMap.get("FIRST_DS_ACCT_GRP_TP_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.scdDsAcctGrpTpCd, (String) rsMap.get("SCD_DS_ACCT_GRP_TP_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.thirdDsAcctGrpTpCd, (String) rsMap.get("THIRD_DS_ACCT_GRP_TP_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.frthDsAcctGrpTpCd, (String) rsMap.get("FRTH_DS_ACCT_GRP_TP_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.fifthDsAcctGrpTpCd, (String) rsMap.get("FIFTH_DS_ACCT_GRP_TP_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.indyTpCd, (String) rsMap.get("INDY_TP_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.firstLineAddr, (String) rsMap.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.scdLineAddr, (String) rsMap.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.thirdLineAddr, (String) rsMap.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.frthLineAddr, (String) rsMap.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.ctyAddr, (String) rsMap.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.stCd, (String) rsMap.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.provNm, (String) rsMap.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.cntyNm, (String) rsMap.get("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.postCd, (String) rsMap.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.dsCustSicCd, (String) rsMap.get("DS_CUST_SIC_CD"));

            ttTraTMsgList.add(ttTraTMsg);
        }

        return ttTraTMsgList;
    }

    private List<ACCT_TRTY_ROLE_ASGTMsg> getTrtyRoleAsgFromDsAcct(//
        // 2017/08/29 QC#20384 Mod Start
        //    String dsAcctTbl, NMAB509001ProcInfoBean procInfoBean) {
            String dsAcctTbl, NMAB509001ProcInfoBean procInfoBean, String procTp) {
        // 2017/08/29 QC#20384 Mod End
        ACCT_TRTY_ROLE_ASGTMsg ttTraTMsg = new ACCT_TRTY_ROLE_ASGTMsg();

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("lineBizRoleTpCd", procInfoBean.getLineBizRoleTpCd());
        param.put("slsDt", this.slsDt);
        param.put("dsAcctNum", procInfoBean.getDsAcctNum());
        param.put("locNum", procInfoBean.getLocNum());
        param.put("terminated", RGTN_STS.TERMINATED);
        param.put("readyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);
        // 2017/08/29 QC#20384 Add Start
        param.put("orgCd", procInfoBean.getTrtyOrgCd());
        // 2017/08/29 QC#20384 Add End
        
        List<Map<String, Object>> rsList = null;
        if ("SELL_TO_CUST".equals(dsAcctTbl)) {
            rsList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTrtyRoleAsgFromDsAcct", param);

        } else if ("DS_ACCT_PROS".equals(dsAcctTbl)) {
            rsList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTrtyRoleAsgFromPros", param);
        }

        if (rsList == null || rsList.size() == 0) {
            return new ArrayList<ACCT_TRTY_ROLE_ASGTMsg>(0);
        }

        //        Map<String, Object> rsMap = rsList.get(0);
        List<ACCT_TRTY_ROLE_ASGTMsg> ttTraTMsgList = new ArrayList<ACCT_TRTY_ROLE_ASGTMsg>(rsList.size());

        for (Map<String, Object> rsMap : rsList) {
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.acctTrtyRoleAsgPk, (BigDecimal) rsMap.get("ACCT_TRTY_ROLE_ASG_PK"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.dsAcctNum, (String) rsMap.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.locNum, (String) rsMap.get("LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.orgCd, (String) rsMap.get("ORG_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.lineBizRoleTpCd, (String) rsMap.get("LINE_BIZ_ROLE_TP_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.dsAcctNm, (String) rsMap.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.billToCustCd, (String) rsMap.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.shipToCustCd, (String) rsMap.get("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.dsAcctTpCd, (String) rsMap.get("DS_ACCT_TP_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.dsAcctClsCd, (String) rsMap.get("DS_ACCT_CLS_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.firstDsAcctGrpTpCd, (String) rsMap.get("FIRST_DS_ACCT_GRP_TP_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.scdDsAcctGrpTpCd, (String) rsMap.get("SCD_DS_ACCT_GRP_TP_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.thirdDsAcctGrpTpCd, (String) rsMap.get("THIRD_DS_ACCT_GRP_TP_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.frthDsAcctGrpTpCd, (String) rsMap.get("FRTH_DS_ACCT_GRP_TP_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.fifthDsAcctGrpTpCd, (String) rsMap.get("FIFTH_DS_ACCT_GRP_TP_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.indyTpCd, (String) rsMap.get("INDY_TP_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.firstLineAddr, (String) rsMap.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.scdLineAddr, (String) rsMap.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.thirdLineAddr, (String) rsMap.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.frthLineAddr, (String) rsMap.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.ctyAddr, (String) rsMap.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.stCd, (String) rsMap.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.provNm, (String) rsMap.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.cntyNm, (String) rsMap.get("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.postCd, (String) rsMap.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(ttTraTMsg.dsCustSicCd, (String) rsMap.get("DS_CUST_SIC_CD"));

            ttTraTMsgList.add(ttTraTMsg);
        }
        return ttTraTMsgList;
    }

    private ACCT_TRTY_RESRC_ASGTMsg getAcctTrtyResrcAsgTMsg(String dsAcctNum, String locNum) {
        ACCT_TRTY_RESRC_ASGTMsg atraTMsg = new ACCT_TRTY_RESRC_ASGTMsg();

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsAcctNum", dsAcctNum);
        param.put("locNum", locNum);
        List<Map<String, String>> rsList //
        = (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getAcctTrtyResrcAsg", param);
        if (rsList == null || rsList.size() == 0) {
            return null;
        }
        Map<String, String> rsMap = rsList.get(0);

        ZYPEZDItemValueSetter.setValue(atraTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(atraTMsg.dsAcctNum, rsMap.get("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.dsAcctNm, rsMap.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.locNum, rsMap.get("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.dsAcctTpCd, rsMap.get("DS_ACCT_TP_CD"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_01, rsMap.get("ACCT_TRTY_ORG_CD_01"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_01, rsMap.get("ACCT_TRTY_PSN_CD_01"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_01, rsMap.get("ACCT_TRTY_TOC_CD_01"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_02, rsMap.get("ACCT_TRTY_ORG_CD_02"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_02, rsMap.get("ACCT_TRTY_PSN_CD_02"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_02, rsMap.get("ACCT_TRTY_TOC_CD_02"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_03, rsMap.get("ACCT_TRTY_ORG_CD_03"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_03, rsMap.get("ACCT_TRTY_PSN_CD_03"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_03, rsMap.get("ACCT_TRTY_TOC_CD_03"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_04, rsMap.get("ACCT_TRTY_ORG_CD_04"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_04, rsMap.get("ACCT_TRTY_PSN_CD_04"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_04, rsMap.get("ACCT_TRTY_TOC_CD_04"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_05, rsMap.get("ACCT_TRTY_ORG_CD_05"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_05, rsMap.get("ACCT_TRTY_PSN_CD_05"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_05, rsMap.get("ACCT_TRTY_TOC_CD_05"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_06, rsMap.get("ACCT_TRTY_ORG_CD_06"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_06, rsMap.get("ACCT_TRTY_PSN_CD_06"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_06, rsMap.get("ACCT_TRTY_TOC_CD_06"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_07, rsMap.get("ACCT_TRTY_ORG_CD_07"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_07, rsMap.get("ACCT_TRTY_PSN_CD_07"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_07, rsMap.get("ACCT_TRTY_TOC_CD_07"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_08, rsMap.get("ACCT_TRTY_ORG_CD_08"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_08, rsMap.get("ACCT_TRTY_PSN_CD_08"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_08, rsMap.get("ACCT_TRTY_TOC_CD_08"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_09, rsMap.get("ACCT_TRTY_ORG_CD_09"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_09, rsMap.get("ACCT_TRTY_PSN_CD_09"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_09, rsMap.get("ACCT_TRTY_TOC_CD_09"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_10, rsMap.get("ACCT_TRTY_ORG_CD_10"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_10, rsMap.get("ACCT_TRTY_PSN_CD_10"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_10, rsMap.get("ACCT_TRTY_TOC_CD_10"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_11, rsMap.get("ACCT_TRTY_ORG_CD_11"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_11, rsMap.get("ACCT_TRTY_PSN_CD_11"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_11, rsMap.get("ACCT_TRTY_TOC_CD_11"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_12, rsMap.get("ACCT_TRTY_ORG_CD_12"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_12, rsMap.get("ACCT_TRTY_PSN_CD_12"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_12, rsMap.get("ACCT_TRTY_TOC_CD_12"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_13, rsMap.get("ACCT_TRTY_ORG_CD_13"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_13, rsMap.get("ACCT_TRTY_PSN_CD_13"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_13, rsMap.get("ACCT_TRTY_TOC_CD_13"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_14, rsMap.get("ACCT_TRTY_ORG_CD_14"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_14, rsMap.get("ACCT_TRTY_PSN_CD_14"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_14, rsMap.get("ACCT_TRTY_TOC_CD_14"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_15, rsMap.get("ACCT_TRTY_ORG_CD_15"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_15, rsMap.get("ACCT_TRTY_PSN_CD_15"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_15, rsMap.get("ACCT_TRTY_TOC_CD_15"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_16, rsMap.get("ACCT_TRTY_ORG_CD_16"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_16, rsMap.get("ACCT_TRTY_PSN_CD_16"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_16, rsMap.get("ACCT_TRTY_TOC_CD_16"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_17, rsMap.get("ACCT_TRTY_ORG_CD_17"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_17, rsMap.get("ACCT_TRTY_PSN_CD_17"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_17, rsMap.get("ACCT_TRTY_TOC_CD_17"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_18, rsMap.get("ACCT_TRTY_ORG_CD_18"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_18, rsMap.get("ACCT_TRTY_PSN_CD_18"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_18, rsMap.get("ACCT_TRTY_TOC_CD_18"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_19, rsMap.get("ACCT_TRTY_ORG_CD_19"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_19, rsMap.get("ACCT_TRTY_PSN_CD_19"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_19, rsMap.get("ACCT_TRTY_TOC_CD_19"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyOrgCd_20, rsMap.get("ACCT_TRTY_ORG_CD_20"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyPsnCd_20, rsMap.get("ACCT_TRTY_PSN_CD_20"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.acctTrtyTocCd_20, rsMap.get("ACCT_TRTY_TOC_CD_20"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_01, rsMap.get("LINE_BIZ_ROLE_TP_CD_01"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_02, rsMap.get("LINE_BIZ_ROLE_TP_CD_02"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_03, rsMap.get("LINE_BIZ_ROLE_TP_CD_03"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_04, rsMap.get("LINE_BIZ_ROLE_TP_CD_04"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_05, rsMap.get("LINE_BIZ_ROLE_TP_CD_05"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_06, rsMap.get("LINE_BIZ_ROLE_TP_CD_06"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_07, rsMap.get("LINE_BIZ_ROLE_TP_CD_07"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_08, rsMap.get("LINE_BIZ_ROLE_TP_CD_08"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_09, rsMap.get("LINE_BIZ_ROLE_TP_CD_09"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_10, rsMap.get("LINE_BIZ_ROLE_TP_CD_10"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_11, rsMap.get("LINE_BIZ_ROLE_TP_CD_11"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_12, rsMap.get("LINE_BIZ_ROLE_TP_CD_12"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_13, rsMap.get("LINE_BIZ_ROLE_TP_CD_13"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_14, rsMap.get("LINE_BIZ_ROLE_TP_CD_14"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_15, rsMap.get("LINE_BIZ_ROLE_TP_CD_15"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_16, rsMap.get("LINE_BIZ_ROLE_TP_CD_16"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_17, rsMap.get("LINE_BIZ_ROLE_TP_CD_17"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_18, rsMap.get("LINE_BIZ_ROLE_TP_CD_18"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_19, rsMap.get("LINE_BIZ_ROLE_TP_CD_19"));
        ZYPEZDItemValueSetter.setValue(atraTMsg.lineBizRoleTpCd_20, rsMap.get("LINE_BIZ_ROLE_TP_CD_20"));

        ZYPEZDItemValueSetter.setValue(atraTMsg.manEntryFlg, rsMap.get("MAN_ENTRY_FLG"));

        return atraTMsg;
    }

    private PROS_TRTY_RESRC_ASGTMsg getProsTrtyResrcAsgTMsg(String dsAcctNum, String locNum) {
        PROS_TRTY_RESRC_ASGTMsg ptraTMsg = new PROS_TRTY_RESRC_ASGTMsg();

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsAcctNum", dsAcctNum);
        param.put("locNum", locNum);
        List<Map<String, String>> rsList //
        = (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getProsTrtyResrcAsg", param);
        if (rsList == null || rsList.size() == 0) {
            return null;
        }
        Map<String, String> rsMap = rsList.get(0);

        ZYPEZDItemValueSetter.setValue(ptraTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ptraTMsg.dsAcctNum, rsMap.get("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.dsAcctNm, rsMap.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.locNum, rsMap.get("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.dsAcctTpCd, rsMap.get("DS_ACCT_TP_CD"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_01, rsMap.get("ACCT_TRTY_ORG_CD_01"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_01, rsMap.get("ACCT_TRTY_PSN_CD_01"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_01, rsMap.get("ACCT_TRTY_TOC_CD_01"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_02, rsMap.get("ACCT_TRTY_ORG_CD_02"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_02, rsMap.get("ACCT_TRTY_PSN_CD_02"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_02, rsMap.get("ACCT_TRTY_TOC_CD_02"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_03, rsMap.get("ACCT_TRTY_ORG_CD_03"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_03, rsMap.get("ACCT_TRTY_PSN_CD_03"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_03, rsMap.get("ACCT_TRTY_TOC_CD_03"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_04, rsMap.get("ACCT_TRTY_ORG_CD_04"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_04, rsMap.get("ACCT_TRTY_PSN_CD_04"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_04, rsMap.get("ACCT_TRTY_TOC_CD_04"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_05, rsMap.get("ACCT_TRTY_ORG_CD_05"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_05, rsMap.get("ACCT_TRTY_PSN_CD_05"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_05, rsMap.get("ACCT_TRTY_TOC_CD_05"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_06, rsMap.get("ACCT_TRTY_ORG_CD_06"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_06, rsMap.get("ACCT_TRTY_PSN_CD_06"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_06, rsMap.get("ACCT_TRTY_TOC_CD_06"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_07, rsMap.get("ACCT_TRTY_ORG_CD_07"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_07, rsMap.get("ACCT_TRTY_PSN_CD_07"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_07, rsMap.get("ACCT_TRTY_TOC_CD_07"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_08, rsMap.get("ACCT_TRTY_ORG_CD_08"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_08, rsMap.get("ACCT_TRTY_PSN_CD_08"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_08, rsMap.get("ACCT_TRTY_TOC_CD_08"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_09, rsMap.get("ACCT_TRTY_ORG_CD_09"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_09, rsMap.get("ACCT_TRTY_PSN_CD_09"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_09, rsMap.get("ACCT_TRTY_TOC_CD_09"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_10, rsMap.get("ACCT_TRTY_ORG_CD_10"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_10, rsMap.get("ACCT_TRTY_PSN_CD_10"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_10, rsMap.get("ACCT_TRTY_TOC_CD_10"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_11, rsMap.get("ACCT_TRTY_ORG_CD_11"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_11, rsMap.get("ACCT_TRTY_PSN_CD_11"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_11, rsMap.get("ACCT_TRTY_TOC_CD_11"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_12, rsMap.get("ACCT_TRTY_ORG_CD_12"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_12, rsMap.get("ACCT_TRTY_PSN_CD_12"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_12, rsMap.get("ACCT_TRTY_TOC_CD_12"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_13, rsMap.get("ACCT_TRTY_ORG_CD_13"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_13, rsMap.get("ACCT_TRTY_PSN_CD_13"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_13, rsMap.get("ACCT_TRTY_TOC_CD_13"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_14, rsMap.get("ACCT_TRTY_ORG_CD_14"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_14, rsMap.get("ACCT_TRTY_PSN_CD_14"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_14, rsMap.get("ACCT_TRTY_TOC_CD_14"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_15, rsMap.get("ACCT_TRTY_ORG_CD_15"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_15, rsMap.get("ACCT_TRTY_PSN_CD_15"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_15, rsMap.get("ACCT_TRTY_TOC_CD_15"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_16, rsMap.get("ACCT_TRTY_ORG_CD_16"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_16, rsMap.get("ACCT_TRTY_PSN_CD_16"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_16, rsMap.get("ACCT_TRTY_TOC_CD_16"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_17, rsMap.get("ACCT_TRTY_ORG_CD_17"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_17, rsMap.get("ACCT_TRTY_PSN_CD_17"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_17, rsMap.get("ACCT_TRTY_TOC_CD_17"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_18, rsMap.get("ACCT_TRTY_ORG_CD_18"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_18, rsMap.get("ACCT_TRTY_PSN_CD_18"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_18, rsMap.get("ACCT_TRTY_TOC_CD_18"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_19, rsMap.get("ACCT_TRTY_ORG_CD_19"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_19, rsMap.get("ACCT_TRTY_PSN_CD_19"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_19, rsMap.get("ACCT_TRTY_TOC_CD_19"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyOrgCd_20, rsMap.get("ACCT_TRTY_ORG_CD_20"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyPsnCd_20, rsMap.get("ACCT_TRTY_PSN_CD_20"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.acctTrtyTocCd_20, rsMap.get("ACCT_TRTY_TOC_CD_20"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_01, rsMap.get("LINE_BIZ_ROLE_TP_CD_01"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_02, rsMap.get("LINE_BIZ_ROLE_TP_CD_02"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_03, rsMap.get("LINE_BIZ_ROLE_TP_CD_03"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_04, rsMap.get("LINE_BIZ_ROLE_TP_CD_04"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_05, rsMap.get("LINE_BIZ_ROLE_TP_CD_05"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_06, rsMap.get("LINE_BIZ_ROLE_TP_CD_06"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_07, rsMap.get("LINE_BIZ_ROLE_TP_CD_07"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_08, rsMap.get("LINE_BIZ_ROLE_TP_CD_08"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_09, rsMap.get("LINE_BIZ_ROLE_TP_CD_09"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_10, rsMap.get("LINE_BIZ_ROLE_TP_CD_10"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_11, rsMap.get("LINE_BIZ_ROLE_TP_CD_11"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_12, rsMap.get("LINE_BIZ_ROLE_TP_CD_12"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_13, rsMap.get("LINE_BIZ_ROLE_TP_CD_13"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_14, rsMap.get("LINE_BIZ_ROLE_TP_CD_14"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_15, rsMap.get("LINE_BIZ_ROLE_TP_CD_15"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_16, rsMap.get("LINE_BIZ_ROLE_TP_CD_16"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_17, rsMap.get("LINE_BIZ_ROLE_TP_CD_17"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_18, rsMap.get("LINE_BIZ_ROLE_TP_CD_18"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_19, rsMap.get("LINE_BIZ_ROLE_TP_CD_19"));
        ZYPEZDItemValueSetter.setValue(ptraTMsg.lineBizRoleTpCd_20, rsMap.get("LINE_BIZ_ROLE_TP_CD_20"));

        ZYPEZDItemValueSetter.setValue(ptraTMsg.manEntryFlg, rsMap.get("MAN_ENTRY_FLG"));

        return ptraTMsg;
    }

    // 2018/08/14 QC#27597 Mod Start
    private String validation(String procTp, ResultSet rsSet, DS_ACCT_PROSTMsg ttAcctTMsg, PTY_LOC_WRKTMsg dplwTMsg, TRTY_TRK_TRTY_UPDTMsg trtyTrkTrtyUpdTMsg) throws SQLException {
    // 2018/08/14 QC#27597 Mod End
        String validationSts = VALIDATION_NORMAL;

        String trtyOrgCd = rsSet.getString("TRTY_ORG_CD");
        if (!ZYPCommonFunc.hasValue(trtyOrgCd)) {
            errInfoList.add(getMap(NMAM0836E, new String[] {"TRTY_ORG_CD" }));
            validationSts = setValidationSts(validationSts, VALIDATION_ERROR_LINE);
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("LINE_BIZ_ROLE_TP_CD")) //
                && !isMoveToGeo(rsSet.getString("LINE_BIZ_ROLE_TP_CD")) //
                && !ZYPCommonFunc.hasValue(rsSet.getString("ORIG_LINE_BIZ_ROLE_TP_CD"))) {
            errInfoList.add(getMap(NMAM0009E, new String[] {"TRTY_TRK_TRTY_UPD.LINE_BIZ_ROLE_TP_CD:" + rsSet.getString("LINE_BIZ_ROLE_TP_CD") }));
            validationSts = setValidationSts(validationSts, VALIDATION_ERROR_LINE);
        }

        List<Map<String, String>> actvAcctList = getActiveAcct(rsSet);
        if (actvAcctList == null || actvAcctList.size() == 0) {
            errInfoList.add(getMap(NMAM0009E, new String[] {"getActiveAcct.DS_ACCT_NUM:" + rsSet.getString("DS_ACCT_NUM") }));
            validationSts = setValidationSts(validationSts, VALIDATION_ERROR_ACCT_LOC);

        } else if (actvAcctList.size() > 1) {
            errInfoList.add(getMap(NMAM8611E, new String[] {"getActiveAcct.DS_ACCT_NUM:" + rsSet.getString("DS_ACCT_NUM") }));
            validationSts = setValidationSts(validationSts, VALIDATION_ERROR_ACCT_LOC);

        } else {
            ZYPEZDItemValueSetter.setValue(ttAcctTMsg.dsAcctTpCd, actvAcctList.get(0).get("DS_ACCT_TP_CD"));
        }

        List<Map<String, String>> actvLocList = getActiveLoc(rsSet);
        if (actvLocList == null || actvLocList.size() == 0) {
            errInfoList.add(getMap(NMAM0009E, new String[] {"getActiveLoc.LOC_NUM:" + rsSet.getString("LOC_NUM") }));
            validationSts = setValidationSts(validationSts, VALIDATION_ERROR_ACCT_LOC);

        } else if (actvLocList.size() > 1) {
            errInfoList.add(getMap(NMAM8611E, new String[] {"getActiveLoc.LOC_NUM:" + rsSet.getString("LOC_NUM") }));
            validationSts = setValidationSts(validationSts, VALIDATION_ERROR_ACCT_LOC);
        } else {
            ZYPEZDItemValueSetter.setValue(dplwTMsg.lineBizTpCd, actvLocList.get(0).get("LINE_BIZ_TP_CD"));
        }

        if (ZYPCommonFunc.hasValue(trtyOrgCd)) {
            if (!existsActiveTrty(trtyOrgCd)) {
                errInfoList.add(getMap(NMAM0009E, new String[] {"existsActiveTrty.TRTY_ORG_CD:" + trtyOrgCd }));
                validationSts = setValidationSts(validationSts, VALIDATION_ERROR_LINE);
            }
        }

        if (!VALIDATION_NORMAL.equals(validationSts)) {
            return validationSts;
        }
        if (!PROC_TP_REASGN_ACCT.equals(procTp)) {
            return validationSts;
        }

        String lineBizRoleTpCd = rsSet.getString("LINE_BIZ_ROLE_TP_CD");
        //        if (!ZYPCommonFunc.hasValue(lineBizRoleTpCd)) {
        //            if ("CSA GEO".equals(trtyOrgCd)) {
        //                return VALIDATION_NORMAL_SKIP;
        //            } else if ("OUTOFBUS".equals(trtyOrgCd)) {
        //                return VALIDATION_NORMAL_OUT_OF_BIZ;
        //            }
        //            return VALIDATION_NORMAL_REMOVE_TRTY;
        //        }
        //        if (LINE_BIZ_ROLE_TP.REMOVED_FROM_TT.equals(lineBizRoleTpCd)) {
        //            return VALIDATION_NORMAL_MOVE_TO_GEO;
        //        }

        // 2018/08/14 QC#27597 Mod Start
        String trtyGrpTpCd = getTrtyGrpTpCd(trtyTrkTrtyUpdTMsg, rsSet.getString("TRTY_ORG_CD"));
        
        List<String> lineBizRankNumList //
        = getLineBizRankNum(//
                rsSet //
                , actvAcctList.get(0).get("DS_ACCT_TP_CD") //
                , actvLocList.get(0).get("LINE_BIZ_TP_CD") //
                , lineBizRoleTpCd
                , trtyGrpTpCd);
        // 2018/08/14 QC#27597 Mod End
        if (lineBizRankNumList == null || lineBizRankNumList.size() == 0) {
            errInfoList.add(getMap(NMAM0009E, new String[] {"getLineBizRankNum.LINE_BIZ_ROLE_TP_CD:" + lineBizRoleTpCd }));
            return setValidationSts(validationSts, VALIDATION_ERROR_LINE);

        } else if (lineBizRankNumList.size() > 1) {
            errInfoList.add(getMap(NMAM8611E, new String[] {"getLineBizRankNum.LINE_BIZ_ROLE_TP_CD:" + lineBizRoleTpCd }));
            return setValidationSts(validationSts, VALIDATION_ERROR_LINE);
        }

        if (!existsResource(trtyOrgCd, lineBizRankNumList.get(0))) {
            errInfoList.add(getMap(NMAM0009E, new String[] {"existsResource.TRTY_ORG_CD:" + trtyOrgCd }));
            return setValidationSts(validationSts, VALIDATION_ERROR_LINE);
        }

        return validationSts;
    }

    private boolean existsResource(String orgCd, String lineBizRankNum) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("orgCd", orgCd);
        param.put("lineBizRankNum", lineBizRankNum);
        param.put("current", GNRN_TP.CURRENT);
        param.put("future", GNRN_TP.FUTURE);
        param.put("slsDt", this.slsDt);
        param.put("firstOrgCd", "1"); // Sales    // QC#11553
        Integer cnt //
        = (Integer) this.ssmBatchClient.queryObject("getCountResource", param);
        if (cnt == null) {
            return false;
        }

        return cnt > 0;
    }

    // 2018/08/14 QC#27597 Mod Start
    private List<String> getLineBizRankNum(ResultSet rsSet, String dsAcctTpCd, String srcLineBizTpCd, String lineBizRoleTpCd, String trtyGrpTpCd) throws SQLException {
    // 2018/08/14 QC#27597 Mod End
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsAcctTpCd", dsAcctTpCd);
        param.put("srcLineBizTpCd", srcLineBizTpCd);
        param.put("lineBizRoleTpCd", lineBizRoleTpCd);
        // 2018/08/14 QC#27597 Add Start
        param.put("trtyGrpTpCd", trtyGrpTpCd);
        // 2018/08/14 QC#27597 Add End
        List<String> rsList //
        = (List<String>) this.ssmBatchClient.queryObjectList("getLineBizRankNum", param);

        return rsList;
    }

    private boolean existsActiveTrty(String orgCd) throws SQLException {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("orgCd", orgCd);
        param.put("current", GNRN_TP.CURRENT);
        param.put("future", GNRN_TP.FUTURE);
        param.put("slsDt", this.slsDt);
        Integer cnt //
        = (Integer) this.ssmBatchClient.queryObject("getCountActiveTrty", param);
        if (cnt == null) {
            return false;
        }

        return cnt > 0;
    }

    private List<Map<String, String>> getActiveLoc(ResultSet rsSet) throws SQLException {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
        param.put("locNum", rsSet.getString("LOC_NUM"));
        param.put("rgtnStsTerminated", RGTN_STS.TERMINATED);
        param.put("rgtnStsOrdTake", RGTN_STS.READY_FOR_ORDER_TAKING);
        List<Map<String, String>> rsList //
        = (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getActiveLoc", param);

        return rsList;
    }

    private List<Map<String, String>> getActiveAcct(ResultSet rsSet) throws SQLException {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
        param.put("slsDt", this.slsDt);
        param.put("terminated", RGTN_STS.TERMINATED);
        List<Map<String, String>> rsList //
        = (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getActiveAcct", param);

        return rsList;
    }

    /**
     * @param currSts current status.
     * @param validationSts error status.
     * @return validation status
     */
    private String setValidationSts(String currSts, String validationSts) {
        if (VALIDATION_ERROR_ACCT_LOC.equals(currSts)) {
            return currSts;
        }
        return validationSts;
    }

    private Map<String, String[]> getMap(String msgId, String[] msgPrm) {
        Map<String, String[]> m = new HashMap<String, String[]>();
        m.put(msgId, msgPrm);
        return m;
    }

    private void editErrorMessage(ResultSet rsSet) throws SQLException {
        if (errInfoList.size() == 0) {
            return;
        }
        String dsAcctNum = "";
        if (ZYPCommonFunc.hasValue(rsSet.getString("DS_ACCT_NUM"))) {
            dsAcctNum = rsSet.getString("DS_ACCT_NUM");
        }

        String locNum = "";
        if (ZYPCommonFunc.hasValue(rsSet.getString("LOC_NUM"))) {
            locNum = rsSet.getString("LOC_NUM");
        }

        String trtyOrgCd = "";
        if (ZYPCommonFunc.hasValue(rsSet.getString("TRTY_ORG_CD"))) {
            trtyOrgCd = rsSet.getString("TRTY_ORG_CD");
        }

        String lineBizRoleTpCd = "";
        if (ZYPCommonFunc.hasValue(rsSet.getString("LINE_BIZ_ROLE_TP_CD"))) {
            lineBizRoleTpCd = rsSet.getString("LINE_BIZ_ROLE_TP_CD");
        }

        StringBuilder sb = new StringBuilder(DEF_ERROR_MESSAGE_LEN);
        sb.append(String.format("%-20s", dsAcctNum));
        sb.append(" ");
        sb.append(String.format("%-30s", locNum));
        sb.append(" ");
        sb.append(String.format("%-12s", trtyOrgCd));
        sb.append(" ");
        sb.append(String.format("%-3s", lineBizRoleTpCd));
        for (Map<String, String[]> errInfo : errInfoList) {
            for (Entry<String, String[]> msgEntry : errInfo.entrySet()) {
                sb.append(NEW_LINE);
                sb.append("          ");
                sb.append(S21MessageFunc.clspGetMessage(msgEntry.getKey(), errInfo.get(msgEntry.getKey())));
            }
        }
        sb.append(NEW_LINE);
        errorMessage.append(sb);
        errInfoList.clear();
    }

    private void sendErrorMail() {
        S21MailGroup mailGroupFrom = null;
        S21MailGroup mailGroupTo = null;
        S21MailAddress fromAddress = null;
        List<S21MailAddress> toAddressList = null;
        S21MailTemplate mailTemplate = null;
        S21Mail mail = null;

        mailGroupFrom = new S21MailGroup(getGlobalCompanyCode(), MAIL_GROUP_ID_FROM);
        fromAddress = mailGroupFrom.getMailAddress().get(0);

        mailGroupTo = new S21MailGroup(getGlobalCompanyCode(), MAIL_GROUP_ID_TO);
        toAddressList = mailGroupTo.getMailAddress();

        mailTemplate = new S21MailTemplate(getGlobalCompanyCode(), MAIL_TEMPLATE_ID_FOR_BIZ_ERROR);
        mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_BATCH_NM, BATCH_NM);
        mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_MSG_INFO, errorMessage.toString());

        mail = new S21Mail(getGlobalCompanyCode());
        mail.setFromAddress(fromAddress);
        mail.setToAddressList(toAddressList);
        mail.setMailTemplate(mailTemplate);
        mail.postMail();
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        // initialize S21BatchMain
        new NMAB509001().executeBatch(NMAB509001.class.getSimpleName());
    }

    // 2018/03/26 QC#23157 Add Start
    private boolean isGeoTerritory(Map<String, String> trtyMap) {

        TRTY_TPTMsg trtyTpTMsg = new TRTY_TPTMsg();
        ZYPEZDItemValueSetter.setValue(trtyTpTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(trtyTpTMsg.trtyTpCd, trtyMap.get("TRTY_TP_CD"));
        trtyTpTMsg = (TRTY_TPTMsg) S21CacheTBLAccessor.findByKey(trtyTpTMsg);

        if (trtyTpTMsg != null) {
            return ZYPCommonFunc.hasValue(trtyTpTMsg.geoTrtyFlg) && ZYPConstant.FLG_ON_Y.equals(trtyTpTMsg.geoTrtyFlg.getValue());
        }

        return false;
    }

    private String getLocPostCd(String locNum) {

        PROS_PTY_LOC_WRKTMsg prosPtyLocWrkTMsg = new PROS_PTY_LOC_WRKTMsg();
        prosPtyLocWrkTMsg.setSQLID("001");
        prosPtyLocWrkTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        prosPtyLocWrkTMsg.setConditionValue("locNum01", locNum);
        prosPtyLocWrkTMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        PROS_PTY_LOC_WRKTMsgArray prosPtyLocWrkTMsgArray = 
            (PROS_PTY_LOC_WRKTMsgArray) EZDTBLAccessor.findByCondition(prosPtyLocWrkTMsg);

        if (prosPtyLocWrkTMsgArray != null && prosPtyLocWrkTMsgArray.getValidCount() > 0) {
            return prosPtyLocWrkTMsgArray.no(0).postCd.getValue();
        }

        PTY_LOC_WRKTMsg acctPtyLocWrkTMsg = new PTY_LOC_WRKTMsg();
        acctPtyLocWrkTMsg.setSQLID("013");
        acctPtyLocWrkTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        acctPtyLocWrkTMsg.setConditionValue("locNum01", locNum);
        acctPtyLocWrkTMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        PTY_LOC_WRKTMsgArray acctPtyLocWrkTMsgArray = 
            (PTY_LOC_WRKTMsgArray) EZDTBLAccessor.findByCondition(acctPtyLocWrkTMsg);

        if (acctPtyLocWrkTMsgArray != null && acctPtyLocWrkTMsgArray.getValidCount() > 0) {
            return acctPtyLocWrkTMsgArray.no(0).postCd.getValue();
        }

        return null;
    }

    private boolean isExistsPostCdInTrtyRule(Map<String, String> trtyMap, String postCd) {

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("slsDt", this.slsDt);
        param.put("orgCd", trtyMap.get("ORG_CD"));
        param.put("orgStruTpCd", trtyMap.get("ORG_STRU_TP_CD"));
        param.put("trtyRuleTpCd", TRTY_RULE_TP.POSTAL_CODE);
        param.put("trtyRuleLogicTpCd", TRTY_RULE_LOGIC_TP.OR);
        param.put("trtyRuleOprdTpCd_Eq", TRTY_RULE_OPRD_TP.EQUAL);
        // Del Start 2018/05/16 QC#24293
        //param.put("trtyRuleOprdTpCd_Lk", TRTY_RULE_OPRD_TP.LIKE);
        // Del End 2018/05/16 QC#24293
        param.put("trtyRuleOprdTpCd_Bw", TRTY_RULE_OPRD_TP.BETWEEN);
        param.put("postCd", postCd);

        Integer overlapCnt = (Integer) this.ssmBatchClient.queryObject("getOverlapPostalCode", param);

        return overlapCnt > 0;
    }

    private void insertTrtyRuleForPostalCode(Map<String, String> trtyMap, String postCd) {

        TRTY_RULETMsg tMsg = new TRTY_RULETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRulePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TRTY_RULE_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.orgCd, trtyMap.get("ORG_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.orgStruTpCd, trtyMap.get("ORG_STRU_TP_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleTpCd, TRTY_RULE_TP.POSTAL_CODE);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleFromValTxt, postCd);
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleOprdTpCd, TRTY_RULE_OPRD_TP.EQUAL);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleLogicTpCd, TRTY_RULE_LOGIC_TP.OR);

        S21FastTBLAccessor.insert(tMsg);
    }
    // 2018/03/26 QC#23157 Add End

    // 2018/07/24 QC#26753 Add Start
    private boolean isPostalRuleOnly(Map<String, String> trtyMap) {

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("slsDt", this.slsDt);
        param.put("orgCd", trtyMap.get("ORG_CD"));
        param.put("orgStruTpCd", trtyMap.get("ORG_STRU_TP_CD"));
        param.put("trtyRuleTpCd", TRTY_RULE_TP.POSTAL_CODE);
        param.put("trtyRuleLogicTpCd", TRTY_RULE_LOGIC_TP.OR);

        Integer otherRuleCnt = (Integer) this.ssmBatchClient.queryObject("getOtherRuleThanPostalCode", param);

        return otherRuleCnt == 0;
    }
    // 2018/07/24 QC#26753 Add End

    // 2018/08/14 QC#27597 Add Start
    private String getTrtyGrpTpCd(TRTY_TRK_TRTY_UPDTMsg trtyTrkTrtyUpdTMsg, String trtyOrgCd) {

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("orgCd", trtyOrgCd);
        param.put("current", GNRN_TP.CURRENT);
        param.put("future", GNRN_TP.FUTURE);
        param.put("slsDt", this.slsDt);

        List<String> rsList = (List<String>) this.ssmBatchClient.queryObjectList("getTrtyGrpTpCd", param);

        if (rsList != null && rsList.size() != 0) {
            return rsList.get(0);
        }

        return null;
    }
    // 2018/08/14 QC#27597 Add End
}
