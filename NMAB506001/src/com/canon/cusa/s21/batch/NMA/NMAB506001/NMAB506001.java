/* <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.batch.NMA.NMAB506001;

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
import parts.common.EZDTStringItem;
import business.db.DEF_DPLY_COA_INFOTMsg;
import business.db.DS_ACCT_RVW_PROSTMsg;
import business.db.DS_ORG_UNITTMsg;
import business.db.INTFC_TS_MGTTMsg;
import business.db.S21_PSNTMsg;
import business.db.TRTY_RULETMsg;
import business.db.TRTY_TPTMsg;
import business.db.TRTY_TRK_PROS_RVW_RSLTTMsg;
import business.parts.NMZC001001PMsg;
import business.parts.NMZC001002PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC001001.NMZC001001;
import com.canon.cusa.s21.api.NMZ.NMZC001001.constant.NMZC001001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROS_RVW_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_LOGIC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_OPRD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_TRK_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
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
 * TT Prospect Approval Process
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/06   Fujitsu         M.Yamada        Create          N/A
 * 2016/07/01   Fujitsu         M.Ohno          Update          QC#11147
 * 2016/07/08   Fujitsu         M.Yamada        Update          QC#11523
 * 2016/07/14   Fujitsu         M.Yamada        Update          QC#11915
 * 2016/08/04   Fujitsu         N.Sugiura       Update          QC#12418
 * 2016/08/22   Fujitsu         N.Sugiura       Update          QC#12366
 * 2016/10/04   Hitachi         T.Mizuki        Update          QC#11320
 * 2017/03/15   Fujitsu         R.Nakamura      Update          QC#15878
 * 2017/08/09   Fujitsu         W.Honda         Update          QC#20392
 * 2017/08/14   Fujitsu         A.Kosai         Update          QC#20561
 * 2017/08/15   Fujitsu         A.Kosai         Update          QC#20392-1
 * 2017/09/14   Fujitsu         A.Kosai         Update          QC#20360
 * 2017/10/10   Fujitsu         T.Aoi           Update          QC#20631
 * 2017/10/11   Fujitsu         T.Aoi           Update          QC#18598
 * 2017/11/29   Fujitsu         N.Sugiura       Update          QC#20629
 * 2018/03/23   Fujitsu         A.Kosai         Update          QC#23157
 * 2018/06/01   Fujitsu         Hd.Sugawara     Update          QC#24293
 *</pre>
 */
public class NMAB506001 extends S21BatchMain {
    /** MAX_NOTIFY_LINE_CNT */
    private static final int MAX_NOTIFY_LINE_CNT = 200;

    /** DEF_NOTIFY_MESSAGE_LEN */
    private static final int DEF_NOTIFY_MESSAGE_LEN = 115;

    /** DEF_ERROR_MESSAGE_LEN */
    private static final int DEF_ERROR_MESSAGE_LEN = 140;

    // TRTY_TRK_PROS_RVW_STS
    /** STS_NORMAL */
    private static final String STS_NORMAL = "N";

    /** STS_ERROR */
    private static final String STS_ERROR = "E";

    // TRTY_TRK_RVW_STS
    /** STS_U */
    private static final String STS_U = "U";

    /** STS_D */
    private static final String STS_D = "D";

    /** NMAL6700001 */
    private static final String NMAL6700001 = "NMAL6700001";

    /** NMAL2800_DEF_DS_ACCT_CLS_CD */
    private static final String NMAL2800_DEF_DS_ACCT_CLS_CD = "NMAL2800_DEF_DS_ACCT_CLS_CD";

    /** PROS_ACCT_INAC_RSN_CD */
    private static final String PROS_ACCT_INAC_RSN_CD = "PROS_ACCT_INAC_RSN_CD";

    //---- Message IDs
    /** An parameter "Interface ID" has not been set. */
    private static final String USEM0099E = "USEM0099E";

    /** Input Parameter Global Company Code is mandatory field. */
    private static final String NMZM0009E = "NMZM0009E";

    /** Input Parameter Sales Date is mandatory field. */
    private static final String NMZM0011E = "NMZM0011E";

    /** The entered [@] does not exist. */
    private static final String NMAM0009E = "NMAM0009E";

    /** <pre>Valid CONSTANT data &lt;Type: [@], Table [VAR_CHAR_CONST] Key: [@]&gt; is not registered.</pre> */
    private static final String NMAM8027E = "NMAM8027E";

    /** [@] field is mandatory. */
    private static final String NMAM0836E = "NMAM0836E";

    /** <pre>The data you searched does not exist. <Table: [@], Condition: [@]></pre> */
    private static final String NMAM8007E = "NMAM8007E";

    /** @ exceed the maximum length. */
    private static final String NMAM8579E = "NMAM8579E";

    /** S21's review status @ is invalid. */
    private static final String NMAM8580E = "NMAM8580E";

    /** Account# or LOC# has already registered in S21. */
    private static final String NMAM8581E = "NMAM8581E";

    /** Territory[@] is not Active Territory on S21. */
    private static final String NMAM8582E = "NMAM8582E";

    /** It should be set @ or @. */
    private static final String NMAM8583E = "NMAM8583E";

    /** Common fields are not the same value for a grouping data. */
    private static final String NMAM8584E = "NMAM8584E";

    /** County is not found. */
    private static final String NMAM8585E = "NMAM8585E";

    /** Sales Rep is not assigned to Territory[@]. */
    private static final String NMAM8586I = "NMAM8586I";

    /** <pre>Territory Rule cannot be registered because Location Number already exists in Territory Rule.</pre> */
    private static final String NMAM8587I = "NMAM8587I";

    /** <pre>Territory Rule cannot be registered because Territory Rule Logic Type in target rule is not "OR".</pre> */
    private static final String NMAM8588I = "NMAM8588I";

    /** LOC# is not active in S21. */
    private static final String NMAM8589E = "NMAM8589E";

    /** [@] cannot be merged. */
    private static final String NMAM8592E = "NMAM8592E";

    /** [@] It failed to register @ Table,[@] */
    private static final String NMAM8571E = "NMAM8571E";

    // mod start 2016/10/04 CSA QC#11320
    /** Account#  ,location# does not exist on S21 Csutomer nor Prospect . */
    private static final String NMAM8654E = "NMAM8654E";

    /** Account# ,location# is registered as Customer . Please check Location status.  */
    private static final String NMAM8655E = "NMAM8655E";
    // mod end 2016/10/04 CSA QC#11320

    /** mail group id(from) : FROM0005 */
    private static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id(to) for Error : NMAB5060 */
    private static final String MAIL_GROUP_ID_TO = "NMAB5060";

    /** mail template id : NMAB5060M001 */
    private static final String MAIL_TEMPLATE_ID_FOR_ISSUE = "NMAB5060M001";

    /** mail template id : NMAB5060M002 */
    private static final String MAIL_TEMPLATE_ID_FOR_NOTIFY = "NMAB5060M002";

    /** batch id : NMAB5060 */
    private static final String BATCH_ID = "NMAB5060";

    /** batchNm : NMAB5060 TT Prospect Approval Process */
    private static final String BATCH_NM = "NMAB5060 TT Prospect Approval Process";

    /** line separator */
    private static final String NEW_LINE = String.format("%n");

    /** DEFAULT_FETCH_SIZE */
    private static final int DEFAULT_FETCH_SIZE = 1000;

    /** number of records for commit */
    private static final int COMMIT_CYCLE = 1000;

    /** mail template key(batchId) : batchId */
    private static final String MAIL_TEMPLATE_KEY_BATCH_ID = "batchId";

    /** MAIL_TEMPLATE_KEY_MSG_INFO */
    private static final String MAIL_TEMPLATE_KEY_MSG_INFO = "message";

    /** MAIL_TEMPLATE_KEY_BATCH_NM */
    private static final String MAIL_TEMPLATE_KEY_BATCH_NM = "batchNm";

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

    /** s21 transaction accessor */
    S21TransactionTableAccessor s21TrxAsr = new S21TransactionTableAccessor();

    /** NMAL2800_DEF_DS_ACCT_CLS_CD */
    private String dsAcctClsCd;

    /** DEF_DPLY_COA_INFO.COA_CH_CD */
    private String coaChCd;

    /** DEF_DPLY_COA_INFO.COA_AFFL_CD */
    private String coaAfflCd;

    /** errInfoList */
    private List<Map<String, String[]>> errInfoList = new ArrayList<Map<String, String[]>>();

    /** errorMessage */
    private StringBuilder errorMessage = new StringBuilder();

    /** notifyMessageList */
    private Map<String, StringBuilder> notifyMessageMap = new HashMap<String, StringBuilder>();

    /** notifyCountList */
    private Map<String, Integer> notifyCountList = new HashMap<String, Integer>();

    //    private String updateTimestamp;

    /** maxRqstUpdTs */
    private String maxRqstUpdTs;

    /** dsAcctInacRsnCd */
    private String dsAcctInacRsnCd;

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

        getConstValues();

        this.trxIds = this.s21TrxAsr.getIntegrationRecordDesc(this.interfaceId);

    }

    private void getConstValues() {
        this.dsAcctClsCd = ZYPCodeDataUtil.getVarCharConstValue(NMAL2800_DEF_DS_ACCT_CLS_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.dsAcctClsCd)) {
            throw new S21AbendException(NMAM8027E, new String[] {"String", NMAL2800_DEF_DS_ACCT_CLS_CD });
        }

        DEF_DPLY_COA_INFOTMsg tMsg = new DEF_DPLY_COA_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.appFuncId, NMAL6700001);
        tMsg = (DEF_DPLY_COA_INFOTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            throw new S21AbendException(//
                    NMAM8007E, new String[] {"DEF_DPLY_COA_INFO", "APP_FUNC_ID:" + NMAL6700001 });
        }
        if (!ZYPCommonFunc.hasValue(tMsg.coaChCd)) {
            throw new S21AbendException(NMAM0836E, new String[] {"COA_CH_CD" });
        }
        if (!ZYPCommonFunc.hasValue(tMsg.coaAfflCd)) {
            throw new S21AbendException(NMAM0836E, new String[] {"COA_AFFL_CD" });
        }
        this.coaChCd = tMsg.coaChCd.getValue();
        this.coaAfflCd = tMsg.coaAfflCd.getValue();

        this.dsAcctInacRsnCd = ZYPCodeDataUtil.getVarCharConstValue(PROS_ACCT_INAC_RSN_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.dsAcctInacRsnCd)) {
            throw new S21AbendException(NMAM8027E, new String[] {"String", PROS_ACCT_INAC_RSN_CD });
        }
    }

    @Override
    protected void mainRoutine() {
        int i = 0;
        for (BigDecimal trxId : this.trxIds) {
            if (i == 0) {
                copyToTrtyTrkProsRvwRslt(trxId);
            }
            this.s21TrxAsr.endIntegrationProcess(this.interfaceId, trxId);
            commit();
            i++;
        }
        if (ZYPCommonFunc.hasValue(this.maxRqstUpdTs)) {
            updateIntfcTsMgt();
        }

        procProspect();

        if (ZYPCommonFunc.hasValue(errorMessage.toString())) {
            rollback();
            sendErrorMail();
            commit();
        }
    }

    private void updateIntfcTsMgt() {
        INTFC_TS_MGTTMsg keyTMsg = new INTFC_TS_MGTTMsg();
        ZYPEZDItemValueSetter.setValue(keyTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(keyTMsg.mngIntfcId, this.interfaceId);

        INTFC_TS_MGTTMsg tMsg = (INTFC_TS_MGTTMsg) S21FastTBLAccessor.findByKeyForUpdate(keyTMsg);

        if (tMsg == null) {
            ZYPEZDItemValueSetter.setValue(keyTMsg.mngIntfcTs, this.maxRqstUpdTs);
            S21FastTBLAccessor.insert(keyTMsg);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.mngIntfcTs, this.maxRqstUpdTs);
            S21FastTBLAccessor.update(tMsg);
        }
        commit();
    }

    private void copyToTrtyTrkProsRvwRslt(BigDecimal trxId) {
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
        ssmParam.put("interfaceId", this.interfaceId);
        ssmParam.put("transactionId", trxId);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getInterfaceData", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            List<TRTY_TRK_PROS_RVW_RSLTTMsg> tMsgList = new ArrayList<TRTY_TRK_PROS_RVW_RSLTTMsg>();

            while (rsSet.next()) {
                if (this.maxRqstUpdTs.compareTo(rsSet.getString("RQST_UPD_TS")) < 0) {
                    this.maxRqstUpdTs = rsSet.getString("RQST_UPD_TS");
                }

                TRTY_TRK_PROS_RVW_RSLTTMsg tMsg = editTrtyTrkProsRvwRsltTMsg(rsSet);
                tMsgList.add(tMsg);
                if (tMsgList.size() > COMMIT_CYCLE) {
                    int cnt = S21FastTBLAccessor.insert(tMsgList.toArray(new TRTY_TRK_PROS_RVW_RSLTTMsg[0]));
                    if (cnt != tMsgList.size()) {
                        rollback();
                        tMsgList.clear();
                        throw new S21AbendException(NMAM8571E, new String[] {"copyToTrtyTrkProsRvwRslt(1)", "TRTY_TRK_PROS_RVW_RSLT", String.valueOf(cnt) });
                    }
                    commit();
                    tMsgList.clear();
                }

            }
            if (tMsgList.size() > 0) {
                int cnt = S21FastTBLAccessor.insert(tMsgList.toArray(new TRTY_TRK_PROS_RVW_RSLTTMsg[0]));
                if (cnt != tMsgList.size()) {
                    rollback();
                    throw new S21AbendException(NMAM8571E, new String[] {"copyToTrtyTrkProsRvwRslt(2)", "TRTY_TRK_PROS_RVW_RSLT", String.valueOf(cnt) });
                }
                commit();
                tMsgList.clear();
            }

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    private TRTY_TRK_PROS_RVW_RSLTTMsg editTrtyTrkProsRvwRsltTMsg(ResultSet rsSet) //
            throws SQLException {
        errInfoList.clear();

        TRTY_TRK_PROS_RVW_RSLTTMsg tMsg = new TRTY_TRK_PROS_RVW_RSLTTMsg();
        String trtyTrkProsRvwStsCd = STS_NORMAL;

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, rsSet.getString("GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(//
                tMsg.trtyTrkProsRvwRsltPk //
                , ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TRTY_TRK_PROS_RVW_RSLT_SQ));

        setValueByLength(//
                tMsg.dsAcctNum, rsSet, "DS_ACCT_NUM", tMsg.getAttr("dsAcctNum").getDigit());
        ZYPEZDItemValueSetter.setValue(tMsg.locNum, rsSet.getString("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, rsSet.getString("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, rsSet.getString("SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNm, rsSet.getString("DS_ACCT_NM"));

        setValueByLength(//
                tMsg.firstLineAddr, rsSet, "FIRST_LINE_ADDR", tMsg.getAttr("firstLineAddr").getDigit());
        setValueByLength(//
                tMsg.scdLineAddr, rsSet, "SCD_LINE_ADDR", tMsg.getAttr("scdLineAddr").getDigit());
        setValueByLength(//
                tMsg.ctyAddr, rsSet, "CTY_ADDR", tMsg.getAttr("ctyAddr").getDigit());
        setValueByLength(//
                tMsg.stCd, rsSet, "ST_CD", tMsg.getAttr("stCd").getDigit());

        ZYPEZDItemValueSetter.setValue(tMsg.firstPostCd, rsSet.getString("FIRST_POST_CD"));

        setValueByLength(//
                tMsg.extnPostCd, rsSet, "EXTN_POST_CD", tMsg.getAttr("extnPostCd").getDigit());
        setValueByLength(//
                tMsg.telNum, rsSet, "TEL_NUM", tMsg.getAttr("telNum").getDigit());
        setValueByLength(//
                tMsg.faxNum, rsSet, "FAX_NUM", tMsg.getAttr("faxNum").getDigit());

        ZYPEZDItemValueSetter.setValue(tMsg.dunsNum, rsSet.getString("DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.extnDunsNum, rsSet.getString("EXTN_DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.dsCustSicCd, rsSet.getString("DS_CUST_SIC_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctUrl, rsSet.getString("DS_ACCT_URL"));
        ZYPEZDItemValueSetter.setValue(tMsg.trtyOrgCd, rsSet.getString("TRTY_ORG_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.lineBizRoleTpCd, rsSet.getString("LINE_BIZ_ROLE_TP_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.orgIdDescTxt, rsSet.getString("ORG_ID_DESC_TXT"));

        setValueByLength(//
                tMsg.trtyOrgNm, rsSet, "TRTY_ORG_NM", tMsg.getAttr("trtyOrgNm").getDigit());
        setValueByLength(//
                tMsg.rqstUpdByPsnCd, rsSet, "RQST_UPD_BY_PSN_CD", tMsg.getAttr("rqstUpdByPsnCd").getDigit());

        ZYPEZDItemValueSetter.setValue(tMsg.rqstUpdTs, rsSet.getString("RQST_UPD_TS"));

        setValueByLength(//
                tMsg.ctryCd, rsSet, "CTRY_CD", tMsg.getAttr("ctryCd").getDigit());
        setValueByLength(//
                tMsg.addlPostCd, rsSet, "ADDL_POST_CD", tMsg.getAttr("addlPostCd").getDigit());

        ZYPEZDItemValueSetter.setValue(tMsg.dsLocRevAmt, rsSet.getBigDecimal("DS_LOC_REV_AMT"));

        setValueByLength(//
                tMsg.dsCustSicDescTxt, rsSet, "DS_CUST_SIC_DESC_TXT", tMsg.getAttr("dsCustSicDescTxt").getDigit());

        ZYPEZDItemValueSetter.setValue(tMsg.dsLocEmpNum, rsSet.getBigDecimal("DS_LOC_EMP_NUM"));

        tMsg.dsXtrnlRefTxt.clear();

        setValueByLength(//
                tMsg.dsDataSrcTxt, rsSet, "DS_DATA_SRC_TXT", tMsg.getAttr("dsDataSrcTxt").getDigit());

        ZYPEZDItemValueSetter.setValue(tMsg.trtyTrkRvwStsCd, rsSet.getString("TRTY_TRK_RVW_STS_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.toBeDsXrefPsnCd, rsSet.getString("TO_BE_DS_XREF_PSN_CD"));

        setValueByLength(//
                tMsg.toBeDsXrefAcctCd, rsSet, "TO_BE_DS_XREF_ACCT_CD", tMsg.getAttr("toBeDsXrefAcctCd").getDigit());

        if (errInfoList.size() > 0) {
            trtyTrkProsRvwStsCd = STS_ERROR;
            editErrorMessage(tMsg, NMAM8579E);
            errInfoList.clear();
        }
        ZYPEZDItemValueSetter.setValue(tMsg.trtyTrkProcStsCd, trtyTrkProsRvwStsCd);
        return tMsg;
    }

    //    private void setValueByLength(//
    //            EZDTDateItem toItem, ResultSet rsSet, String columnLabel, int len, String trtyTrkProsRvwStsCd) //
    //            throws SQLException {
    //        String value = rsSet.getString(columnLabel);
    //        if (value.length() > len) {
    //            errInfoList.add(getMap(NMAM8xx1E, new String[] {columnLabel }));
    //            trtyTrkProsRvwStsCd = STS_ERROR;
    //        }
    //        ZYPEZDItemValueSetter.setValue(toItem, S21StringUtil.subStringByLength(value, 0, len));
    //    }

    private void setValueByLength(//
            EZDTStringItem toItem, ResultSet rsSet, String columnLabel, int len) //
            throws SQLException {
        String value = rsSet.getString(columnLabel);
        if (ZYPCommonFunc.hasValue(value) && value.length() > len) {
            errInfoList.add(getMap(NMAM8579E, new String[] {columnLabel }));
        }
        ZYPEZDItemValueSetter.setValue(toItem, S21StringUtil.subStringByLength(value, 0, len));
    }

    private Map<String, String[]> getMap(String msgId, String[] msgPrm) {
        Map<String, String[]> m = new HashMap<String, String[]>();
        m.put(msgId, msgPrm);
        return m;
    }

    private void editErrorMessage(TRTY_TRK_PROS_RVW_RSLTTMsg tMsg, String msgId) {
        if (errInfoList.size() == 0) {
            return;
        }

        StringBuilder sb = new StringBuilder(DEF_ERROR_MESSAGE_LEN);
        sb.append(String.format("%-20s", nullToBlank(tMsg.dsAcctNum.getValue())));
        sb.append(" ");
        sb.append(String.format("%-30s", nullToBlank(tMsg.locNum.getValue())));
        sb.append(" ");
        sb.append(String.format("%-20s", nullToBlank(tMsg.dsDataSrcTxt.getValue())));
        sb.append(" ");
        sb.append(String.format("%-3s", nullToBlank(tMsg.trtyTrkRvwStsCd.getValue())));
        for (Map<String, String[]> errInfo : errInfoList) {
            sb.append(NEW_LINE);
            sb.append("          ");
            sb.append(S21MessageFunc.clspGetMessage(msgId, errInfo.get(msgId)));
        }
        sb.append(NEW_LINE);
        errorMessage.append(sb);
        errInfoList.clear();
    }

    private void editErrorMessage(NMAB506001NewProsBean newProsBean) {
        if (errInfoList.size() == 0) {
            return;
        }

        StringBuilder sb = new StringBuilder(DEF_ERROR_MESSAGE_LEN);
        sb.append(String.format("%-20s", nullToBlank(newProsBean.getDsAcctNum())));
        sb.append(" ");
        sb.append(String.format("%-30s", nullToBlank(newProsBean.getLocNum())));
        sb.append(" ");
        sb.append(String.format("%-20s", nullToBlank(newProsBean.getDsDataSrcTxt())));
        sb.append(" ");
        sb.append(String.format("%-3s", nullToBlank(newProsBean.getTrtyTrkRvwStsCd())));
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

    /**
     * @return if error then return true.
     */
    private boolean procProspect() {
        boolean isError = false;

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
        ssmParam.put("update", STS_U); // TRTY_TRK_RVW_STS
        ssmParam.put("delete", STS_D);
        ssmParam.put("new", TRTY_TRK_PROC_STS.NEW);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getNewProspect", ssmParam, execParam);
            rsSet = stmt.executeQuery();
            TRTY_TRK_PROS_RVW_RSLTTMsg prevTMsg = null;
            boolean isErrorSameGroup = false;
            String trtyTrkProcStsCd = TRTY_TRK_PROC_STS.COMPLETE;
            String trtyTrkRvwStsCd = "";
            List<TRTY_TRK_PROS_RVW_RSLTTMsg> rsltTMsgList = new ArrayList<TRTY_TRK_PROS_RVW_RSLTTMsg>();

            TRTY_RULETMsg trtyRuleTMsg = new TRTY_RULETMsg();
            NMAB506001NewProsBean currNewProsBean = new NMAB506001NewProsBean();
            NMAB506001NewProsBean newProsBean = new NMAB506001NewProsBean();
            int rsCount = 0;
            while (rsSet.next()) {
                rsCount++;

                setNewProsBean(currNewProsBean, rsSet);
                TRTY_TRK_PROS_RVW_RSLTTMsg currTMsg = getCurrTMsg(currNewProsBean);
                if (prevTMsg == null) {
                    setNewProsBean(newProsBean, rsSet);
                    errInfoList.clear();
                    prevTMsg = new TRTY_TRK_PROS_RVW_RSLTTMsg();
                    EZDMsg.copy(currTMsg, null, prevTMsg, null);
                    trtyTrkRvwStsCd = prevTMsg.trtyTrkRvwStsCd.getValue();
                }

                if (isSameGroup(currTMsg, prevTMsg)) {
                    if (!isErrorSameGroup) {
                        isErrorSameGroup = isErrorSameGroup(currTMsg, prevTMsg);
                    }
                } else {
                    if (isErrorSameGroup) {
                        errInfoList.add(getMap(NMAM8584E, new String[] {}));
                        editErrorMessage(prevTMsg, NMAM8584E);
                        isErrorSameGroup = false;
                        errInfoList.clear();
                        trtyTrkProcStsCd = TRTY_TRK_PROC_STS.ERROR;
                    }
                    if (TRTY_TRK_PROC_STS.COMPLETE.equals(trtyTrkProcStsCd)) {
                        boolean isNormal = true;
                        if (STS_U.equals(newProsBean.getTrtyTrkRvwStsCd())) {
                            isNormal = registProspect(newProsBean, trtyRuleTMsg);

                        } else if (STS_D.equals(newProsBean.getTrtyTrkRvwStsCd())) {
                            isNormal = inactiveProspect(newProsBean, trtyRuleTMsg);
                        }
                        if (isNormal) {
                            this.commitCount++;
                        } else {
                            trtyTrkProcStsCd = TRTY_TRK_PROC_STS.ERROR;
                            rollback();
                            isError = true;
                            editErrorMessage(newProsBean);
                            this.errorCount++;
                        }
                    } else {
                        trtyTrkProcStsCd = TRTY_TRK_PROC_STS.ERROR;
                        rollback();
                        isError = true;
                        editErrorMessage(newProsBean);
                        this.errorCount++;
                    }
                    setProcStsToRsltTMsgList(rsltTMsgList, trtyTrkProcStsCd);
                    updateStatus(rsltTMsgList);
                    commit();
                    trtyTrkProcStsCd = TRTY_TRK_PROC_STS.COMPLETE;

                    rsltTMsgList.clear();
                    setNewProsBean(newProsBean, rsSet);
                }

                if (validation(rsSet, trtyRuleTMsg)) {
                    trtyTrkProcStsCd = TRTY_TRK_PROC_STS.ERROR;
                    //                } else {
                    //                    if (STS_U.equals(rsSet.getString("TRTY_TRK_RVW_STS_CD"))) {
                    //                        if (!registProspect(rsSet, trtyRuleTMsg)) {
                    //                            rollback();
                    //                        }
                    //                    } else if (STS_D.equals(rsSet.getString("TRTY_TRK_RVW_STS_CD"))) {
                    //                        if (!inactiveProspect(rsSet, trtyRuleTMsg)) {
                    //                            rollback();
                    //                        }
                    //                    }
                }
                if (errInfoList.size() > 0) {
                    //                    trtyTrkProcStsCd = TRTY_TRK_PROC_STS.ERROR;
                    editErrorMessage(newProsBean);
                    errInfoList.clear();
                }

                EZDMsg.copy(currTMsg, null, prevTMsg, null);
                trtyTrkRvwStsCd = prevTMsg.trtyTrkRvwStsCd.getValue();
                rsltTMsgList.add(currTMsg);
            } // end while
            //
            if (isErrorSameGroup) {
                trtyTrkProcStsCd = TRTY_TRK_PROC_STS.ERROR;
                errInfoList.add(getMap(NMAM8584E, new String[] {}));
                editErrorMessage(prevTMsg, NMAM8584E);
                errInfoList.clear();
            }
            if (TRTY_TRK_PROC_STS.COMPLETE.equals(trtyTrkProcStsCd)) {
                boolean isNormal = true;
                if (STS_U.equals(trtyTrkRvwStsCd)) {
                    isNormal = registProspect(currNewProsBean, trtyRuleTMsg);

                } else if (STS_D.equals(trtyTrkRvwStsCd)) {
                    isNormal = inactiveProspect(currNewProsBean, trtyRuleTMsg);

                }
                if (isNormal) {
                    if (rsCount > 0) {
                        this.commitCount++;
                    }
                } else {
                    rollback();
                    isError = true;
                    this.errorCount++;
                }
            } else {
                rollback();
                isError = true;
                this.errorCount++;
            }
            if (isError) {
                trtyTrkProcStsCd = TRTY_TRK_PROC_STS.ERROR;
            } else {
                for (Map<String, String[]> errInfoMap : errInfoList) {
                    for (String msgId : errInfoMap.keySet()) {
                        if (msgId.endsWith("E")) {
                            trtyTrkProcStsCd = TRTY_TRK_PROC_STS.ERROR;
                            isError = true;
                            break;
                        }
                    }
                    if (isError) {
                        break;
                    }
                }
            }
            if (isError || errInfoList.size() > 0) {
                editErrorMessage(currNewProsBean);
                errInfoList.clear();
            }

            setProcStsToRsltTMsgList(rsltTMsgList, trtyTrkProcStsCd);
            updateStatus(rsltTMsgList);
            commit();
            //
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }

        return isError;
    }

    private void setNewProsBean(NMAB506001NewProsBean newProsBean, ResultSet rsSet) throws SQLException {
        newProsBean.setTrtyTrkProsRvwRsltPk(rsSet.getBigDecimal("TRTY_TRK_PROS_RVW_RSLT_PK"));
        newProsBean.setDsAcctNum(rsSet.getString("DS_ACCT_NUM"));
        newProsBean.setLocNum(rsSet.getString("LOC_NUM"));
        newProsBean.setBillToCustCd(rsSet.getString("BILL_TO_CUST_CD"));
        newProsBean.setShipToCustCd(rsSet.getString("SHIP_TO_CUST_CD"));
        newProsBean.setDsAcctNm(rsSet.getString("DS_ACCT_NM"));
        newProsBean.setFirstLineAddr(rsSet.getString("FIRST_LINE_ADDR"));
        newProsBean.setScdLineAddr(rsSet.getString("SCD_LINE_ADDR"));
        newProsBean.setCtyAddr(rsSet.getString("CTY_ADDR"));
        newProsBean.setStCd(rsSet.getString("ST_CD"));
        newProsBean.setFirstPostCd(rsSet.getString("FIRST_POST_CD"));
        newProsBean.setExtnPostCd(rsSet.getString("EXTN_POST_CD"));
        newProsBean.setTelNum(rsSet.getString("TEL_NUM"));
        newProsBean.setFaxNum(rsSet.getString("FAX_NUM"));
        newProsBean.setDunsNum(rsSet.getString("DUNS_NUM"));
        newProsBean.setExtnDunsNum(rsSet.getString("EXTN_DUNS_NUM"));
        newProsBean.setDsCustSicCd(rsSet.getString("DS_CUST_SIC_CD"));
        newProsBean.setDsAcctUrl(rsSet.getString("DS_ACCT_URL"));
        newProsBean.setTrtyOrgCd(rsSet.getString("TRTY_ORG_CD"));
        newProsBean.setLineBizRoleTpCd(rsSet.getString("LINE_BIZ_ROLE_TP_CD"));
        newProsBean.setOrgIdDescTxt(rsSet.getString("ORG_ID_DESC_TXT"));
        newProsBean.setTrtyOrgNm(rsSet.getString("TRTY_ORG_NM"));
        newProsBean.setRqstUpdByPsnCd(rsSet.getString("RQST_UPD_BY_PSN_CD"));
        newProsBean.setRqstUpdTs(rsSet.getString("RQST_UPD_TS"));
        newProsBean.setCtryCd(rsSet.getString("CTRY_CD"));
        newProsBean.setAddlPostCd(rsSet.getString("ADDL_POST_CD"));
        newProsBean.setDsLocRevAmt(rsSet.getBigDecimal("DS_LOC_REV_AMT"));
        newProsBean.setDsCustSicDescTxt(rsSet.getString("DS_CUST_SIC_DESC_TXT"));
        newProsBean.setDsLocEmpNum(rsSet.getBigDecimal("DS_LOC_EMP_NUM"));
        newProsBean.setDsXtrnlRefTxt(rsSet.getString("DS_XTRNL_REF_TXT"));
        newProsBean.setDsDataSrcTxt(rsSet.getString("DS_DATA_SRC_TXT"));
        newProsBean.setTrtyTrkRvwStsCd(rsSet.getString("TRTY_TRK_RVW_STS_CD"));
        newProsBean.setToBeDsXrefPsnCd(rsSet.getString("TO_BE_DS_XREF_PSN_CD"));
        newProsBean.setToBeDsXrefAcctCd(rsSet.getString("TO_BE_DS_XREF_ACCT_CD"));
        newProsBean.setTrtyTrkProcStsCd(rsSet.getString("TRTY_TRK_PROC_STS_CD"));

        newProsBean.setOrigRvwProsNum(rsSet.getString("ORIG_RVW_PROS_NUM"));
        newProsBean.setOrigDsAcctNum(rsSet.getString("ORIG_DS_ACCT_NUM"));
        newProsBean.setOrigLocNum(rsSet.getString("ORIG_LOC_NUM"));
        newProsBean.setOrigDsXrefAcctTpCd(rsSet.getString("ORIG_DS_XREF_ACCT_TP_CD"));
        newProsBean.setOrigProsRvwStsCd(rsSet.getString("ORIG_PROS_RVW_STS_CD"));
        newProsBean.setOrigBefPsnNum(rsSet.getString("ORIG_BEF_PSN_NUM"));
        newProsBean.setOrigBefDsAcctNm(rsSet.getString("ORIG_BEF_DS_ACCT_NM"));

        newProsBean.setOrigStCd(rsSet.getString("ORIG_ST_CD"));
        newProsBean.setOrigLineBizRoleTpCd(rsSet.getString("ORIG_LINE_BIZ_ROLE_TP_CD"));
        newProsBean.setOrigCtryCd(rsSet.getString("ORIG_CTRY_CD"));
    }

    private boolean inactiveProspect(NMAB506001NewProsBean newProsBean, TRTY_RULETMsg trtyRuleTMsg) throws SQLException {
        boolean isNormal = true;
        if (ZYPCommonFunc.hasValue(newProsBean.getLocNum())) {
            List<Map<String, Object>> rsList = getCustInfoForInactive(newProsBean);
            // mod start 2016/10/04 CSA QC#11320
            if (rsList == null || rsList.size() == 0) {
                boolean rsCust = getCustomer(newProsBean);
                if (!rsCust) {
                    errInfoList.add(getMap(NMAM8654E, new String[] {}));
                    return false;
                } else {
                    errInfoList.add(getMap(NMAM8655E, new String[] {}));
                    return false;
                }
            }
            // mod end 2016/10/04 CSA QC#11320
            String rgtnStsCd = null;
            if (rsList.size() == 1) {
                rgtnStsCd = RGTN_STS.TERMINATED;
            }
            List<NMZC001001PMsg> pMsgList = new ArrayList<NMZC001001PMsg>();
            for (Map<String, Object> rsMap : rsList) {
                if (DS_ACCT_TP.CUSTOMER.equals(rsMap.get("DS_ACCT_TP_CD"))) {
                    errInfoList.add(getMap(NMAM8592E, new String[] {"Customer Account:" + newProsBean.getDsAcctNm() }));
                    isNormal = false;
                    break;
                }
                pMsgList.add(getPMsgForUpdateLocationToInavtive(rsMap, rgtnStsCd));
            }
            if (isNormal) {
                NMZC001001 apiCustomer = new NMZC001001();
                apiCustomer.execute(pMsgList, ONBATCH_TYPE.BATCH);
                isNormal = checkCustUpdApiResult(isNormal, pMsgList);
            }
        }

        if (isNormal) {
            registDsAcctRvwPros(newProsBean);
        } else {
            editErrorMessage(newProsBean);
        }
        return isNormal;

    }

    /**
     * @param isNormal isNormal
     * @param pMsgList List<NMZC001001PMsg>
     * @return isNormal
     */
    private boolean checkCustUpdApiResult(boolean isNormal, List<NMZC001001PMsg> pMsgList) {
        for (NMZC001001PMsg p1Msg : pMsgList) {
            if (S21ApiUtil.isXxMsgId(p1Msg)) {
                for (S21ApiMessage apiMsg : S21ApiUtil.getXxMsgList(p1Msg)) {
                    errInfoList.add(getMap(apiMsg.getXxMsgid(), apiMsg.getXxMsgPrmArray()));
                }
                isNormal = false;
            }
            for (int i = 0; i < p1Msg.NMZC001002PMsg.getValidCount(); i++) {
                NMZC001002PMsg p2Msg = p1Msg.NMZC001002PMsg.no(i);
                if (S21ApiUtil.isXxMsgId(p2Msg)) {
                    for (S21ApiMessage apiMsg : S21ApiUtil.getXxMsgList(p2Msg)) {
                        errInfoList.add(getMap(apiMsg.getXxMsgid(), apiMsg.getXxMsgPrmArray()));
                    }
                    isNormal = false;
                }
            }

        }
        return isNormal;
    }

    private void registDsAcctRvwPros(NMAB506001NewProsBean newProsBean) {

        List<BigDecimal> dsAcctRvwProsPkList = getDsAcctRvwProsPkList(newProsBean, PROS_RVW_STS.D);
        if (dsAcctRvwProsPkList == null || dsAcctRvwProsPkList.size() == 0) {
            insertDsAcctRvwPros(newProsBean);
            return;
        }

        for (BigDecimal dsAcctRvwProsPk : dsAcctRvwProsPkList) {
            DS_ACCT_RVW_PROSTMsg tMsg = getDsAcctRvwProsTMsgForUpdate(dsAcctRvwProsPk);
            if (tMsg == null) {
                continue;
            }
            updateDsAcctRvwPros(tMsg, newProsBean);
        }

    }

    private void insertDsAcctRvwPros(NMAB506001NewProsBean newProsBean) {
        DS_ACCT_RVW_PROSTMsg tMsg = new DS_ACCT_RVW_PROSTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(//
                tMsg.dsAcctRvwProsPk //
                , ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ACCT_RVW_PROS_SQ));

        String locNum = newProsBean.getLocNum();
        if (ZYPCommonFunc.hasValue(locNum)) {
            Map<String, String> e404Map = getE404Info(newProsBean);
            if (e404Map != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.prosRvwId, (String) e404Map.get("SF_ACCOUNT_ID"));
                ZYPEZDItemValueSetter.setValue(tMsg.rvwProsNum, (String) e404Map.get("SF_PROSPECT_NUMBER"));
            }

            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, newProsBean.getDsAcctNum());
            ZYPEZDItemValueSetter.setValue(tMsg.locNum, locNum);
            ZYPEZDItemValueSetter.setValue(tMsg.befDsAcctNm, newProsBean.getDsAcctNm());
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.prosRvwId, getProsRvwId(newProsBean));
            ZYPEZDItemValueSetter.setValue(tMsg.rvwProsNum, newProsBean.getDsDataSrcTxt());
            ZYPEZDItemValueSetter.setValue(tMsg.befDsAcctNm, newProsBean.getDsAcctNm());
        }
        ZYPEZDItemValueSetter.setValue(tMsg.aftLocNum, newProsBean.getToBeDsXrefPsnCd());
        ZYPEZDItemValueSetter.setValue(tMsg.prosRvwStsCd, PROS_RVW_STS.D);
        ZYPEZDItemValueSetter.setValue(tMsg.xtrnlProsRvwCmntTxt, "I79D Record created as part of E59C Process");
        ZYPEZDItemValueSetter.setValue(tMsg.aftDsXrefAcctCd, newProsBean.getToBeDsXrefAcctCd());

        ZYPEZDItemValueSetter.setValue(tMsg.rqstUpdByPsnCd, newProsBean.getRqstUpdByPsnCd());
        ZYPEZDItemValueSetter.setValue(//
                tMsg.rqstUpdDt //
                , S21StringUtil.subStringByLength(newProsBean.getRqstUpdTs(), 0, "YYYYMMDD".length()));

        ZYPEZDItemValueSetter.setValue(tMsg.acctIdAddrRvwProsFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyTrkHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.dupAcctLocFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.prosSendFlg, ZYPConstant.FLG_OFF_N);

        S21FastTBLAccessor.create(tMsg);
    }

    private Map<String, String> getE404Info(NMAB506001NewProsBean newProsBean) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsDataSrcTxt", newProsBean.getDsDataSrcTxt());
        param.put("locNum", newProsBean.getLocNum());

        Map<String, String> e404Map //
        = (Map<String, String>) this.ssmBatchClient.queryObject("getE404Info", param);

        return e404Map;
    }

    private void updateDsAcctRvwPros(//
            DS_ACCT_RVW_PROSTMsg tMsg, NMAB506001NewProsBean newProsBean) {
        //
        //ZYPEZDItemValueSetter.setValue(tMsg.prosRvwId, getProsRvwId(newProsBean)); // 2017/10/10 QC#20631 Del
        // 2017/10/11 QC#18598 Add Start
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, newProsBean.getDsAcctNum());
        ZYPEZDItemValueSetter.setValue(tMsg.locNum, newProsBean.getLocNum());
        // 2017/10/11 QC#18598 Add End
        ZYPEZDItemValueSetter.setValue(tMsg.aftLocNum, newProsBean.getToBeDsXrefPsnCd());
        ZYPEZDItemValueSetter.setValue(tMsg.prosRvwStsCd, PROS_RVW_STS.D);
        ZYPEZDItemValueSetter.setValue(tMsg.aftDsXrefAcctCd, newProsBean.getToBeDsXrefAcctCd());

        ZYPEZDItemValueSetter.setValue(tMsg.rqstUpdByPsnCd, newProsBean.getRqstUpdByPsnCd());
        ZYPEZDItemValueSetter.setValue(//
                tMsg.rqstUpdDt //
                , S21StringUtil.subStringByLength(newProsBean.getRqstUpdTs(), 0, "YYYYMMDD".length()));

        S21FastTBLAccessor.update(tMsg);
    }

    private String getProsRvwId(NMAB506001NewProsBean newProsBean) {
        // prosRvwId is CANON_E404_SF_ACCT_MAPPING_TBL.SF_ACCOUNT_ID
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("sfProspectNumber", newProsBean.getDsDataSrcTxt());

        String prosRvwId //
        = (String) this.ssmBatchClient.queryObject("getProsRvwId", param);

        return prosRvwId;
    }

    private DS_ACCT_RVW_PROSTMsg getDsAcctRvwProsTMsgForUpdate(BigDecimal dsAcctRvwProsPk) {
        DS_ACCT_RVW_PROSTMsg tMsg = new DS_ACCT_RVW_PROSTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctRvwProsPk, dsAcctRvwProsPk);

        tMsg = (DS_ACCT_RVW_PROSTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsg);

        return tMsg;
    }

    private NMZC001001PMsg getPMsgForUpdateLocationToInavtive(Map<String, Object> rsMap, String rgtnStsCd) {
        NMZC001001PMsg p1Msg = new NMZC001001PMsg();

        ZYPEZDItemValueSetter.setValue(p1Msg.xxModeCd, NMZC001001Constant.PROCESS_MODE_PROS_UPD);
        ZYPEZDItemValueSetter.setValue(p1Msg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(p1Msg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(p1Msg.dsAcctNum, (String) rsMap.get("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(p1Msg.dsAcctNm, (String) rsMap.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(p1Msg.dsAcctItrlFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(p1Msg.dsAcctClsCd, (String) rsMap.get("DS_ACCT_CLS_CD"));
        ZYPEZDItemValueSetter.setValue(p1Msg.coaChCd, (String) rsMap.get("COA_CH_CD"));
        ZYPEZDItemValueSetter.setValue(p1Msg.coaAfflCd, (String) rsMap.get("COA_AFFL_CD"));
        ZYPEZDItemValueSetter.setValue(p1Msg.dsAcctDlrCd, (String) rsMap.get("DS_ACCT_DLR_CD"));
        ZYPEZDItemValueSetter.setValue(p1Msg.dsAcctLegalNm, (String) rsMap.get("DS_ACCT_LEGAL_NM"));
        ZYPEZDItemValueSetter.setValue(p1Msg.dbaNm, (String) rsMap.get("DBA_NM"));
        if (ZYPCommonFunc.hasValue(rgtnStsCd)) {
            ZYPEZDItemValueSetter.setValue(p1Msg.rgtnStsCd, rgtnStsCd);
        } else {
            ZYPEZDItemValueSetter.setValue(p1Msg.rgtnStsCd, (String) rsMap.get("RGTN_STS_CD"));
        }
        ZYPEZDItemValueSetter.setValue(p1Msg.dsAcctUrl, (String) rsMap.get("DS_ACCT_URL"));

        if (ZYPCommonFunc.hasValue(rgtnStsCd)) {
            ZYPEZDItemValueSetter.setValue(p1Msg.xxAcctInacRsnFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(p1Msg.dsAcctInacRsnCd, this.dsAcctInacRsnCd);
            ZYPEZDItemValueSetter.setValue(p1Msg.effThruDt, ZYPDateUtil.addDays(this.slsDt, -1));
        } else {
            ZYPEZDItemValueSetter.setValue(p1Msg.xxAcctInacRsnFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(p1Msg.xxAcctDunsInfoFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(p1Msg.xxAcctCrFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(p1Msg.xxAcctCltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(p1Msg.xxAcctTaxFlg, ZYPConstant.FLG_OFF_N);

        NMZC001002PMsg p2Msg = p1Msg.NMZC001002PMsg.no(0);
        ZYPEZDItemValueSetter.setValue(p2Msg.xxPrinFlg, ZYPConstant.FLG_OFF_N);
        //        ZYPEZDItemValueSetter.setValue(p2Msg.billToCustFlg, ZYPConstant.FLG_OFF_N);
        //        ZYPEZDItemValueSetter.setValue(p2Msg.shipToCustFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(p2Msg.locNum, (String) rsMap.get("LOC_NUM"));

        ZYPEZDItemValueSetter.setValue(p2Msg.effFromDt, (String) rsMap.get("EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(p2Msg.effThruDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(p2Msg.actvFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(p2Msg.firstLineAddr, (String) rsMap.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(p2Msg.scdLineAddr, (String) rsMap.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(p2Msg.thirdLineAddr, (String) rsMap.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(p2Msg.frthLineAddr, (String) rsMap.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(p2Msg.ctyAddr, (String) rsMap.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(p2Msg.cntyNm, (String) rsMap.get("CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(p2Msg.stCd, (String) rsMap.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(p2Msg.postCd, (String) rsMap.get("POST_CD"));
        //        String postCd = (String) rsMap.get("FIRST_POST_CD");
        //        String extnPostCd = (String) rsMap.get("EXTN_POST_CD");
        //        if (ZYPCommonFunc.hasValue(extnPostCd)) {
        //            postCd = S21StringUtil.concatStrings(postCd, "-", extnPostCd);
        //        }
        //        ZYPEZDItemValueSetter.setValue(p2Msg.postCd, postCd);
        ZYPEZDItemValueSetter.setValue(p2Msg.ctryCd, (String) rsMap.get("CTRY_CD"));
        // Mod Start 2016/07/01 M.Ohno QC#11147
        ZYPEZDItemValueSetter.setValue(p2Msg.locNm, (String) rsMap.get("DS_LOC_NM"));
        // Mod End 2016/07/01 M.Ohno QC#11147
        ZYPEZDItemValueSetter.setValue(p2Msg.addlLocNm, (String) rsMap.get("ADDL_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(p2Msg.glnNum, (BigDecimal) rsMap.get("GLN_NUM"));
        ZYPEZDItemValueSetter.setValue(p2Msg.telNum, (String) rsMap.get("TEL_NUM"));
        ZYPEZDItemValueSetter.setValue(p2Msg.faxNum, (String) rsMap.get("FAX_NUM"));
        ZYPEZDItemValueSetter.setValue(p2Msg.provNm, (String) rsMap.get("PROV_NM"));
        ZYPEZDItemValueSetter.setValue(p2Msg.lineBizTpCd, (String) rsMap.get("LINE_BIZ_TP_CD"));

        ZYPEZDItemValueSetter.setValue(p2Msg.geoCd, (String) rsMap.get("GEO_CD"));
        ZYPEZDItemValueSetter.setValue(p2Msg.dsInsdCtyLimitFlg, (String) rsMap.get("DS_INSD_CTY_LIMIT_FLG"));

        ZYPEZDItemValueSetter.setValue(p2Msg.xxLocXrefInfoFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(p2Msg.xxLocDunsFlg, ZYPConstant.FLG_OFF_N);

        //        ZYPEZDItemValueSetter.setValue(p2Msg.xxLocBillToCrInfoFlg, ZYPConstant.FLG_OFF_N);
        //        ZYPEZDItemValueSetter.setValue(p2Msg.xxLocBillToCltInfoFlg, ZYPConstant.FLG_OFF_N);
        //        ZYPEZDItemValueSetter.setValue(p2Msg.xxLocBillToTaxFlg_B, ZYPConstant.FLG_OFF_N);
        //        ZYPEZDItemValueSetter.setValue(p2Msg.xxLocShipToTaxFlg_S, ZYPConstant.FLG_OFF_N);
        p1Msg.NMZC001002PMsg.setValidCount(1);

        return p1Msg;
    }

    private List<Map<String, Object>> getCustInfoForInactive(NMAB506001NewProsBean newProsBean) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        // QC#20561 2017/08/14 Mod Start
//        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        param.put("rgtnStsCd_P01", RGTN_STS.PENDING_COMPLETION);
        param.put("rgtnStsCd_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
        // QC#20561 2017/08/14 Mod End
        param.put("locNum", newProsBean.getLocNum());
        // 2017/10/11 QC#18598 Add Start
        param.put("dsAcctNum", newProsBean.getDsAcctNum());
        // 2017/10/11 QC#18598 Add End
        List<Map<String, Object>> rsList //
        = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCustInfoForInactive", param);

        return (List<Map<String, Object>>) rsList;
    }

    private boolean registProspect(NMAB506001NewProsBean newProsBean, TRTY_RULETMsg trtyRuleTMsg) {
        NMZC001001PMsg p1Msg = new NMZC001001PMsg();

        ZYPEZDItemValueSetter.setValue(p1Msg.xxModeCd, NMZC001001Constant.PROCESS_MODE_PROS_CRAT);
        ZYPEZDItemValueSetter.setValue(p1Msg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(p1Msg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(p1Msg.dsAcctNm, newProsBean.getDsAcctNm());
        ZYPEZDItemValueSetter.setValue(p1Msg.dsAcctItrlFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(p1Msg.dsAcctClsCd, this.dsAcctClsCd);
        ZYPEZDItemValueSetter.setValue(p1Msg.coaChCd, this.coaChCd);
        ZYPEZDItemValueSetter.setValue(p1Msg.coaAfflCd, this.coaAfflCd);
        ZYPEZDItemValueSetter.setValue(p1Msg.dsAcctUrl, newProsBean.getDsAcctUrl());
        ZYPEZDItemValueSetter.setValue(p1Msg.xxAcctInacRsnFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(p1Msg.xxAcctDunsInfoFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(p1Msg.xxAcctCrFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(p1Msg.xxAcctCltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(p1Msg.xxAcctTaxFlg, ZYPConstant.FLG_OFF_N);

        NMZC001002PMsg p2Msg = p1Msg.NMZC001002PMsg.no(0);
        ZYPEZDItemValueSetter.setValue(p2Msg.xxPrinFlg, ZYPConstant.FLG_OFF_N);
        //        ZYPEZDItemValueSetter.setValue(p2Msg.billToCustFlg, ZYPConstant.FLG_OFF_N);
        //        ZYPEZDItemValueSetter.setValue(p2Msg.shipToCustFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(p2Msg.effFromDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(p2Msg.actvFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(p2Msg.firstLineAddr, newProsBean.getFirstLineAddr());
        ZYPEZDItemValueSetter.setValue(p2Msg.scdLineAddr, newProsBean.getScdLineAddr());
        ZYPEZDItemValueSetter.setValue(p2Msg.ctyAddr, newProsBean.getCtyAddr());
        ZYPEZDItemValueSetter.setValue(p2Msg.stCd, newProsBean.getStCd());
        String postCd = newProsBean.getFirstPostCd();
        String extnPostCd = newProsBean.getExtnPostCd();
        if (ZYPCommonFunc.hasValue(extnPostCd)) {
            postCd = S21StringUtil.concatStrings(postCd, "-", extnPostCd);
        }
        ZYPEZDItemValueSetter.setValue(p2Msg.postCd, postCd);
        ZYPEZDItemValueSetter.setValue(p2Msg.ctryCd, newProsBean.getCtryCd());
        // Mod Start 2016/07/01 M.Ohno QC#11147
        // ZYPEZDItemValueSetter.setValue(p2Msg.locNm, newProsBean.getDsAcctNm());
        // Mod End 2016/07/01 M.Ohno QC#11147
        ZYPEZDItemValueSetter.setValue(p2Msg.telNum, newProsBean.getTelNum());
        ZYPEZDItemValueSetter.setValue(p2Msg.faxNum, newProsBean.getFaxNum());
        // 2017/09/14 QC#20360 Mod Start
        //ZYPEZDItemValueSetter.setValue(p2Msg.lineBizTpCd, LINE_BIZ_TP.ALL);
        ZYPEZDItemValueSetter.setValue(p2Msg.lineBizTpCd, getLineBizTpCd(newProsBean, trtyRuleTMsg));
        // 2017/09/14 QC#20360 Mod End
        ZYPEZDItemValueSetter.setValue(p2Msg.xxLocXrefInfoFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(p2Msg.dsXrefAcctTpCd, newProsBean.getOrigDsXrefAcctTpCd());
        ZYPEZDItemValueSetter.setValue(p2Msg.dsXrefAcctCd, newProsBean.getDsDataSrcTxt());
        ZYPEZDItemValueSetter.setValue(p2Msg.xxLocDunsFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(p2Msg.dunsNum, newProsBean.getDunsNum());
        ZYPEZDItemValueSetter.setValue(p2Msg.dsLocEmpNum, newProsBean.getDsLocEmpNum());
        ZYPEZDItemValueSetter.setValue(p2Msg.dsLocRevAmt, newProsBean.getDsLocRevAmt());
        ZYPEZDItemValueSetter.setValue(p2Msg.dsLastUpdDunsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(p2Msg.dsCustSicCd, newProsBean.getDsCustSicCd());
        ZYPEZDItemValueSetter.setValue(p2Msg.dsCustSicDescTxt, newProsBean.getDsCustSicDescTxt());
        //        ZYPEZDItemValueSetter.setValue(p2Msg.xxLocBillToCrInfoFlg, ZYPConstant.FLG_OFF_N);
        //        ZYPEZDItemValueSetter.setValue(p2Msg.xxLocBillToCltInfoFlg, ZYPConstant.FLG_OFF_N);
        //        ZYPEZDItemValueSetter.setValue(p2Msg.xxLocBillToTaxFlg_B, ZYPConstant.FLG_OFF_N);
        //        ZYPEZDItemValueSetter.setValue(p2Msg.xxLocShipToTaxFlg_S, ZYPConstant.FLG_OFF_N);
        p1Msg.NMZC001002PMsg.setValidCount(1);

        NMZC001001 apiCustomer = new NMZC001001();
        apiCustomer.execute(p1Msg, ONBATCH_TYPE.BATCH);
        boolean isNormal = true;
        if (S21ApiUtil.isXxMsgId(p1Msg)) {
            for (S21ApiMessage apiMsg : S21ApiUtil.getXxMsgList(p1Msg)) {
                errInfoList.add(getMap(apiMsg.getXxMsgid(), apiMsg.getXxMsgPrmArray()));
            }
            isNormal = false;
        }
        for (int i = 0; i < p1Msg.NMZC001002PMsg.getValidCount(); i++) {
            p2Msg = p1Msg.NMZC001002PMsg.no(i);
            if (S21ApiUtil.isXxMsgId(p2Msg)) {
                for (S21ApiMessage apiMsg : S21ApiUtil.getXxMsgList(p2Msg)) {
                    errInfoList.add(getMap(apiMsg.getXxMsgid(), apiMsg.getXxMsgPrmArray()));
                }
                isNormal = false;
            }
        }

        if (!isNormal) {
            editErrorMessage(newProsBean);
            return isNormal;
        }

        BigDecimal cntyPk = p1Msg.NMZC001002PMsg.no(0).cntyPk.getValue();
        //        BigDecimal cntyPk = getCntyPk(p1Msg);
        if (!ZYPCommonFunc.hasValue(cntyPk)) {
            errInfoList.add(getMap(NMAM8585E, new String[] {}));
            return false;
        }

        updateReviewProspect(p1Msg, newProsBean, cntyPk);

        if (ZYPCommonFunc.hasValue(newProsBean.getTrtyOrgCd())) {
            registTerritoryRule(p1Msg, newProsBean, trtyRuleTMsg);
        }
        return isNormal;
    }

    private void registTerritoryRule(NMZC001001PMsg p1Msg, NMAB506001NewProsBean newProsBean, TRTY_RULETMsg tMsg) {
        // 2018/03/23 QC#23157 Add Start
        if (isGeoTerritory(newProsBean)) {
            if (!isExistsPostCdInTrtyRule(p1Msg, newProsBean)) {
                insertTrtyRuleForPostalCode(p1Msg, tMsg);
            }
            // Del Start 2018/06/01 QC#24293
            //return;
            // Del End 2018/06/01 QC#24293
        }
        // Add Start 2018/06/01 QC#24293
        else {
        // Add End 2018/06/01 QC#24293
        // 2018/03/23 QC#23157 Add End

        boolean isSkipTrtyRule = false;
        String orgCd = tMsg.orgCd.getValue();
        String orgStruTpCd = tMsg.orgStruTpCd.getValue();

        if (!existsSalesRepRelation(orgCd, orgStruTpCd)) {
            errInfoList.add(getMap(NMAM8586I, new String[] {newProsBean.getTrtyOrgCd() }));
            isSkipTrtyRule = true;
        }
        if (existsTerritoryRule(p1Msg, orgCd, orgStruTpCd)) {
            errInfoList.add(getMap(NMAM8587I, new String[] {}));
            isSkipTrtyRule = true;
        }
        if (isRuleLogicAllOr(p1Msg, orgCd, orgStruTpCd)) {
            errInfoList.add(getMap(NMAM8588I, new String[] {}));
            isSkipTrtyRule = true;
        }
        if (!isSkipTrtyRule) {
            insertTrtyRule(tMsg, p1Msg);
        }

        // Add Start 2018/06/01 QC#24293
        }
        // Add End 2018/06/01 QC#24293

        editNotifyMailMessage(newProsBean, tMsg);

        return;
    }

    private void editNotifyMailMessage(//
            NMAB506001NewProsBean newProsBean, TRTY_RULETMsg trtyRuleTMsg) {

        // 2017/09/14 QC#20360 Mod Start
        //String rgstPsnNum = getRegisterdSalesRep(trtyRuleTMsg);
        String rgstPsnNum = null;
        Map<String, Object> rgstSalesRepInfo = getRegisterdSalesRep(trtyRuleTMsg);
        if (rgstSalesRepInfo != null) {
            rgstPsnNum = (String) rgstSalesRepInfo.get("PSN_NUM");
        }
        // 2017/09/14 QC#20360 Mod End
        String origPsnNum = newProsBean.getOrigBefPsnNum();
        if (!ZYPCommonFunc.hasValue(origPsnNum) //
                || origPsnNum.equals(rgstPsnNum)) {
            return;
        }
        StringBuilder msgSb = notifyMessageMap.get(origPsnNum);
        Integer msgCnt = notifyCountList.get(origPsnNum);
        if (msgSb == null) {
            msgSb = new StringBuilder();
            msgCnt = 0;
        } else {
            if (msgCnt >= MAX_NOTIFY_LINE_CNT) {
                return;
            }
        }
        msgSb.append(editNotifyMessage(newProsBean));
        msgCnt++;
        notifyMessageMap.put(origPsnNum, msgSb);
        notifyCountList.put(origPsnNum, msgCnt);
    }

    private StringBuilder editNotifyMessage(NMAB506001NewProsBean newProsBean) {
        StringBuilder sb = new StringBuilder(DEF_NOTIFY_MESSAGE_LEN);
        sb.append(String.format("%-8s", nullToBlank(newProsBean.getOrigBefPsnNum())));
        sb.append("           ");
        sb.append(String.format("%-20s", nullToBlank(newProsBean.getDsAcctNum())));
        sb.append(" ");
        sb.append(String.format("%-30s", nullToBlank(newProsBean.getLocNum())));
        sb.append(" ");
        sb.append(String.format("%-20s", nullToBlank(newProsBean.getDsDataSrcTxt())));
        sb.append(" ");
        sb.append(String.format("%-20s", nullToBlank(newProsBean.getOrigBefDsAcctNm())));
        sb.append(NEW_LINE);

        return sb;
    }

    // 2017/09/14 QC#20360 Mod Start
    //private String getRegisterdSalesRep(TRTY_RULETMsg tMsg) {
    private Map<String, Object> getRegisterdSalesRep(TRTY_RULETMsg tMsg) {
    // 2017/09/14 QC#20360 Mod End
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("orgCd", tMsg.orgCd.getValue());
        param.put("orgStruTpCd", tMsg.orgStruTpCd.getValue());
        param.put("current", GNRN_TP.CURRENT);
        param.put("future", GNRN_TP.FUTURE);
        param.put("slsDt", this.slsDt);

        // 2017/09/14 QC#20360 Mod Start
        //List<String> rgstPsnNumList //
        //= (List<String>) this.ssmBatchClient.queryObjectList("getRegisterdSalesRep", param);

        //if (rgstPsnNumList == null || rgstPsnNumList.size() == 0) {
        //    return null;
        //}
        //return rgstPsnNumList.get(0);
        Map<String, Object> rgstSalesRepInfo //
        = (Map<String, Object>) this.ssmBatchClient.queryObject("getRegisterdSalesRep", param);

        if (rgstSalesRepInfo == null || rgstSalesRepInfo.size() == 0) {
            return null;
        }
        return rgstSalesRepInfo;
        // 2017/09/14 QC#20360 Mod End
    }

    private void insertTrtyRule(TRTY_RULETMsg tMsg, NMZC001001PMsg p1Msg) {
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(//
                tMsg.trtyRulePk //
                , ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TRTY_RULE_SQ));
        // Mod Start 2017/03/15 QC#15878
//        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleTpCd, TRTY_RULE_TP.ACCOUNT_OR_SITE_NUMBER); // 4:Location Number
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleTpCd, TRTY_RULE_TP.LOCATION_NUMBER); // 4:Location Number
        // Mod End 2017/03/15 QC#15878
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleFromValTxt, p1Msg.NMZC001002PMsg.no(0).locNum);
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleOprdTpCd, TRTY_RULE_OPRD_TP.EQUAL);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleLogicTpCd, TRTY_RULE_LOGIC_TP.OR);

        S21FastTBLAccessor.insert(tMsg);
    }

    private boolean isRuleLogicAllOr(NMZC001001PMsg p1Msg, String orgCd, String orgStruTpCd) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("orgCd", orgCd);
        param.put("orgStruTpCd", orgStruTpCd);
        param.put("slsDt", this.slsDt);
        // Mod Start 2017/03/15 QC#15878
//        param.put("trtyRuleTpCd", TRTY_RULE_TP.ACCOUNT_OR_SITE_NUMBER); // 4:Location Number
        param.put("trtyRuleTpCd", TRTY_RULE_TP.LOCATION_NUMBER); // 4:Location Number
        // Mod End 2017/03/15 QC#15878
        param.put("trtyRuleLogicTpCd", TRTY_RULE_LOGIC_TP.OR);

        Integer cnt //
        = (Integer) this.ssmBatchClient.queryObject("getCountTerritoryRuleLogicNotOR", param);
        if (cnt == null) {
            return false;
        }

        return cnt > 0;
    }

    private boolean existsLocNum(String locNum) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("locNum", locNum);
        param.put("rgtnStsCd", RGTN_STS.TERMINATED);
        param.put("slsDt", this.slsDt);

        Integer cnt //
        // Mod Start 2017/08/08 QC#20392
//        = (Integer) this.ssmBatchClient.queryObject("getCountPtyLocWrk", param);
        // Mod Start 2017/08/15 QC#20392-1
//        = (Integer) this.ssmBatchClient.queryObject("getCountProsPtyLocWrk", param);
        = (Integer) this.ssmBatchClient.queryObject("getCountPtyLocWrk", param);
        // Mod End 2017/08/15 QC#20392-1
        // Mod End 2017/08/08 QC#20392
        if (cnt == null) {
            return false;
        }

        return cnt > 0;
    }

    private boolean existsTerritoryRule(NMZC001001PMsg p1Msg, String orgCd, String orgStruTpCd) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        // Mod Start 2017/03/15 QC#15878
//        param.put("trtyRuleTpCd", TRTY_RULE_TP.ACCOUNT_OR_SITE_NUMBER); // 4:Location Number
        param.put("trtyRuleTpCd", TRTY_RULE_TP.LOCATION_NUMBER); // 4:Location Number
        // Mod End 2017/03/15 QC#15878
        param.put("orgStruTpCd", orgStruTpCd);
        param.put("slsDt", this.slsDt);
        param.put("trtyRuleFromValTxt", p1Msg.NMZC001002PMsg.no(0).locNum.getValue());
        param.put("orgCd", orgCd);

        Integer cnt //
        = (Integer) this.ssmBatchClient.queryObject("getCountTerritoryRule", param);
        if (cnt == null) {
            return false;
        }

        return cnt > 0;
    }

    private boolean existsSalesRepRelation(String orgCd, String orgStruTpCd) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("orgCd", orgCd);
        param.put("orgStruTpCd", orgStruTpCd);
        param.put("current", GNRN_TP.CURRENT);
        param.put("future", GNRN_TP.FUTURE);
        param.put("slsDt", this.slsDt);

        Integer cnt //
        = (Integer) this.ssmBatchClient.queryObject("getCountSalesRepRelation", param);
        if (cnt == null) {
            return false;
        }

        return cnt > 0;
    }

    private void updateReviewProspect(NMZC001001PMsg p1Msg, NMAB506001NewProsBean newProsBean, BigDecimal cntyPk) {
        List<BigDecimal> pkList = getDsAcctRvwProsPkList(newProsBean, PROS_RVW_STS.U);

        for (BigDecimal dsAcctRvwProsPk : pkList) {
            DS_ACCT_RVW_PROSTMsg tMsg = new DS_ACCT_RVW_PROSTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctRvwProsPk, dsAcctRvwProsPk);

            DS_ACCT_RVW_PROSTMsg updTMsg = (DS_ACCT_RVW_PROSTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsg);
            editDsAcctRvwProsTMsg(updTMsg, p1Msg, newProsBean, cntyPk);
            S21FastTBLAccessor.update(updTMsg);
        }

    }

    private void editDsAcctRvwProsTMsg(//
            DS_ACCT_RVW_PROSTMsg updTMsg, NMZC001001PMsg p1Msg, NMAB506001NewProsBean newProsBean, BigDecimal cntyPk) {
        NMZC001002PMsg p2Msg = p1Msg.NMZC001002PMsg.no(0);

        ZYPEZDItemValueSetter.setValue(updTMsg.dsAcctNum, p1Msg.dsAcctNum);
        ZYPEZDItemValueSetter.setValue(updTMsg.locNum, p2Msg.locNum);
        ZYPEZDItemValueSetter.setValue(updTMsg.dsAcctUrl, newProsBean.getDsAcctUrl());
        // 2016/08/22 QC#12366 Del Start
        // ZYPEZDItemValueSetter.setValue(updTMsg.aftLocNum, p2Msg.locNum);
        // 2016/08/22 QC#12366 Del End
        ZYPEZDItemValueSetter.setValue(updTMsg.prosRvwStsCd, PROS_RVW_STS.U);

        // 2017/11/29 QC#20629 Mod Start
        // ZYPEZDItemValueSetter.setValue(updTMsg.aftDsAcctNm, p1Msg.dsAcctNum);
        ZYPEZDItemValueSetter.setValue(updTMsg.aftDsAcctNm, p1Msg.dsAcctNm);
        // 2017/11/29 QC#20629 Mod End
        ZYPEZDItemValueSetter.setValue(updTMsg.aftLocFirstLineAddr, p2Msg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(updTMsg.aftLocScdLineAddr, p2Msg.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(updTMsg.aftLocThirdLineAddr, p2Msg.thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(updTMsg.aftLocFrthLineAddr, p2Msg.frthLineAddr);
        ZYPEZDItemValueSetter.setValue(updTMsg.aftLocCtyAddr, p2Msg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(updTMsg.aftLocStCd, p2Msg.stCd);
        ZYPEZDItemValueSetter.setValue(updTMsg.aftLocPostCd, p2Msg.postCd);
        ZYPEZDItemValueSetter.setValue(updTMsg.aftLocCntyPk, cntyPk);
        // 2016/08/04 QC#12418 Del Start
        // ZYPEZDItemValueSetter.setValue(updTMsg.aftCntyPk, cntyPk);
        // 2016/08/04 QC#12418 Del End

        ZYPEZDItemValueSetter.setValue(updTMsg.aftTrtyOrgCd, newProsBean.getTrtyOrgCd());
        ZYPEZDItemValueSetter.setValue(updTMsg.aftDsAcctDunsNm, p1Msg.dsAcctDunsNm);
        ZYPEZDItemValueSetter.setValue(updTMsg.aftDsLocRevAmt, p1Msg.dsLocRevAmt);
        ZYPEZDItemValueSetter.setValue(updTMsg.aftDunsNum, p1Msg.dsAcctDunsNum);
        ZYPEZDItemValueSetter.setValue(updTMsg.aftDsCustSicDescTxt, p1Msg.dsCustSicDescTxt);
        ZYPEZDItemValueSetter.setValue(updTMsg.aftDsLocEmpNum, p1Msg.dsLocEmpNum);
        ZYPEZDItemValueSetter.setValue(updTMsg.aftDsCustSicCd, p1Msg.dsCustSicCd);
        ZYPEZDItemValueSetter.setValue(updTMsg.aftDsUltDunsNum, p1Msg.dsUltDunsNum);

        ZYPEZDItemValueSetter.setValue(updTMsg.aftTelNum, p2Msg.telNum);
        // 2016/08/04 QC#12418 Add Start
        ZYPEZDItemValueSetter.setValue(updTMsg.aftFaxNum, p2Msg.faxNum);
        // 2016/08/04 QC#12418 Add End
        ZYPEZDItemValueSetter.setValue(updTMsg.rqstUpdByPsnCd, newProsBean.getRqstUpdByPsnCd());
        ZYPEZDItemValueSetter.setValue(//
                updTMsg.rqstUpdDt //
                , S21StringUtil.subStringByLength(newProsBean.getRqstUpdTs(), 0, "YYYYMMDD".length()));

        ZYPEZDItemValueSetter.setValue(updTMsg.aftGlnNum, p2Msg.glnNum);
        ZYPEZDItemValueSetter.setValue(updTMsg.aftDsPrntDunsNum, p2Msg.dsPrntDunsNum);
        ZYPEZDItemValueSetter.setValue(updTMsg.aftHqDunsNum, p2Msg.hqDunsNum);
        ZYPEZDItemValueSetter.setValue(updTMsg.aftDsCmpySicCd, p1Msg.dsCustSicCd);
        ZYPEZDItemValueSetter.setValue(updTMsg.aftDsCmpySicDescTxt, p1Msg.dsCustSicDescTxt);
        ZYPEZDItemValueSetter.setValue(updTMsg.aftCtryCd, p2Msg.ctryCd);

    }

    private List<BigDecimal> getDsAcctRvwProsPkList(NMAB506001NewProsBean newProsBean, String prosRvwStsCd) {
        if (!ZYPCommonFunc.hasValue(newProsBean.getOrigRvwProsNum())) {
            return new ArrayList<BigDecimal>(0);
        }
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("rvwProsNum", newProsBean.getOrigRvwProsNum());
        // 2017/10/11 QC#18598 Del Start
        //if (PROS_RVW_STS.D.equals(prosRvwStsCd)) {
        //    param.put("locNum", newProsBean.getLocNum());
        //} else {
        //    param.put("locNum", "");
        //}
        // 2017/10/11 QC#18598 Del End
        List<BigDecimal> pkList //
        = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getDsAcctRvwProsPkList", param);
        return (List<BigDecimal>) pkList;
    }

    //    private BigDecimal getCntyPk(NMZC001001PMsg p1Msg) {
    //
    //        Map<String, String> param = new HashMap<String, String>();
    //        param.put("glblCmpyCd", this.glblCmpyCd);
    //        param.put("cntyNm", p1Msg.NMZC001002PMsg.no(0).cntyNm.getValue());
    //
    //        param.put("stCd", p1Msg.NMZC001002PMsg.no(0).stCd.getValue());
    //        BigDecimal cntyPk //
    //        = (BigDecimal) this.ssmBatchClient.queryObject("getCountyPk", param);
    //        if (!ZYPCommonFunc.hasValue(cntyPk)) {
    //            return null;
    //        }
    //        return cntyPk;
    //    }

    private void updateStatus(List<TRTY_TRK_PROS_RVW_RSLTTMsg> rsltTMsgList) {
        for (TRTY_TRK_PROS_RVW_RSLTTMsg tMsg : rsltTMsgList) {
            String trtyTrkProcStsCd = tMsg.trtyTrkProcStsCd.getValue();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            TRTY_TRK_PROS_RVW_RSLTTMsg updTMsg = (TRTY_TRK_PROS_RVW_RSLTTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsg);

            ZYPEZDItemValueSetter.setValue(updTMsg.trtyTrkProcStsCd, trtyTrkProcStsCd);
            S21FastTBLAccessor.update(updTMsg);
        }

    }

    private void setProcStsToRsltTMsgList(List<TRTY_TRK_PROS_RVW_RSLTTMsg> rsltTMsgList, String stsCd) {
        for (int ix = 0; ix < rsltTMsgList.size(); ix++) {
            TRTY_TRK_PROS_RVW_RSLTTMsg tMsg = rsltTMsgList.get(ix);
            ZYPEZDItemValueSetter.setValue(tMsg.trtyTrkProcStsCd, stsCd);
            rsltTMsgList.set(ix, tMsg);
        }

    }

    private boolean isSameGroup(TRTY_TRK_PROS_RVW_RSLTTMsg currTMsg, TRTY_TRK_PROS_RVW_RSLTTMsg prevTMsg) {
        String prevDsDataSrcTxt = prevTMsg.dsDataSrcTxt.getValue();
        if (ZYPCommonFunc.hasValue(prevDsDataSrcTxt)) {
            return prevDsDataSrcTxt.equals(currTMsg.dsDataSrcTxt.getValue());
        }
        return currTMsg.locNum.getValue().equals(prevTMsg.locNum.getValue());
    }

    private boolean isErrorSameGroup(TRTY_TRK_PROS_RVW_RSLTTMsg currTMsg, TRTY_TRK_PROS_RVW_RSLTTMsg prevTMsg) {
        if (!currTMsg.dsAcctNum.getValue().equals(prevTMsg.dsAcctNum.getValue())) {
            return true;
        }
        if (!currTMsg.locNum.getValue().equals(prevTMsg.locNum.getValue())) {
            return true;
        }
        if (!currTMsg.firstLineAddr.getValue().equals(prevTMsg.firstLineAddr.getValue())) {
            return true;
        }
        if (!currTMsg.scdLineAddr.getValue().equals(prevTMsg.scdLineAddr.getValue())) {
            return true;
        }
        if (!currTMsg.ctyAddr.getValue().equals(prevTMsg.ctyAddr.getValue())) {
            return true;
        }
        if (!currTMsg.stCd.getValue().equals(prevTMsg.stCd.getValue())) {
            return true;
        }
        if (!currTMsg.firstPostCd.getValue().equals(prevTMsg.firstPostCd.getValue())) {
            return true;
        }
        if (!currTMsg.extnPostCd.getValue().equals(prevTMsg.extnPostCd.getValue())) {
            return true;
        }
        if (!currTMsg.telNum.getValue().equals(prevTMsg.telNum.getValue())) {
            return true;
        }
        if (!currTMsg.faxNum.getValue().equals(prevTMsg.faxNum.getValue())) {
            return true;
        }
        if (!currTMsg.dunsNum.getValue().equals(prevTMsg.dunsNum.getValue())) {
            return true;
        }
        if (!currTMsg.extnDunsNum.getValue().equals(prevTMsg.extnDunsNum.getValue())) {
            return true;
        }
        if (!currTMsg.dsCustSicCd.getValue().equals(prevTMsg.dsCustSicCd.getValue())) {
            return true;
        }
        if (!currTMsg.dsAcctUrl.getValue().equals(prevTMsg.dsAcctUrl.getValue())) {
            return true;
        }
        if (!currTMsg.orgIdDescTxt.getValue().equals(prevTMsg.orgIdDescTxt.getValue())) {
            return true;
        }
        if (!currTMsg.ctryCd.getValue().equals(prevTMsg.ctryCd.getValue())) {
            return true;
        }
        if (!currTMsg.addlPostCd.getValue().equals(prevTMsg.addlPostCd.getValue())) {
            return true;
        }
        if (!isSameBigDecimalValue(currTMsg.dsLocRevAmt.getValue(), prevTMsg.dsLocRevAmt.getValue())) {
            return true;
        }
        if (!currTMsg.dsCustSicDescTxt.getValue().equals(prevTMsg.dsCustSicDescTxt.getValue())) {
            return true;
        }
        if (!isSameBigDecimalValue(currTMsg.dsLocEmpNum.getValue(), prevTMsg.dsLocEmpNum.getValue())) {
            return true;
        }
        if (!currTMsg.dsXtrnlRefTxt.getValue().equals(prevTMsg.dsXtrnlRefTxt.getValue())) {
            return true;
        }
        if (!currTMsg.dsDataSrcTxt.getValue().equals(prevTMsg.dsDataSrcTxt.getValue())) {
            return true;
        }
        if (!currTMsg.trtyTrkRvwStsCd.getValue().equals(prevTMsg.trtyTrkRvwStsCd.getValue())) {
            return true;
        }
        if (!currTMsg.toBeDsXrefPsnCd.getValue().equals(prevTMsg.toBeDsXrefPsnCd.getValue())) {
            return true;
        }
        if (!currTMsg.toBeDsXrefAcctCd.getValue().equals(prevTMsg.toBeDsXrefAcctCd.getValue())) {
            return true;
        }

        return false;
    }

    /**
     * @param currTMsg
     * @param prevTMsg
     * @return
     */
    private boolean isSameBigDecimalValue(BigDecimal currVal, BigDecimal prevVal) {
        if (!ZYPCommonFunc.hasValue(currVal) && !ZYPCommonFunc.hasValue(prevVal)) {
            return true;
        }
        if (!ZYPCommonFunc.hasValue(currVal) || !ZYPCommonFunc.hasValue(prevVal)) {
            return false;
        }
        return currVal.compareTo(prevVal) == 0;
    }

    private TRTY_TRK_PROS_RVW_RSLTTMsg getCurrTMsg(NMAB506001NewProsBean newProsBean) {
        TRTY_TRK_PROS_RVW_RSLTTMsg tMsg = new TRTY_TRK_PROS_RVW_RSLTTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.trtyTrkProsRvwRsltPk, newProsBean.getTrtyTrkProsRvwRsltPk());

        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, newProsBean.getDsAcctNum());
        ZYPEZDItemValueSetter.setValue(tMsg.locNum, newProsBean.getLocNum());
        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, newProsBean.getFirstLineAddr());
        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, newProsBean.getScdLineAddr());
        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, newProsBean.getCtyAddr());
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, newProsBean.getStCd());
        ZYPEZDItemValueSetter.setValue(tMsg.firstPostCd, newProsBean.getFirstPostCd());
        ZYPEZDItemValueSetter.setValue(tMsg.extnPostCd, newProsBean.getExtnPostCd());
        ZYPEZDItemValueSetter.setValue(tMsg.telNum, newProsBean.getTelNum());
        ZYPEZDItemValueSetter.setValue(tMsg.faxNum, newProsBean.getFaxNum());
        ZYPEZDItemValueSetter.setValue(tMsg.dunsNum, newProsBean.getDunsNum());
        ZYPEZDItemValueSetter.setValue(tMsg.extnDunsNum, newProsBean.getExtnDunsNum());
        ZYPEZDItemValueSetter.setValue(tMsg.dsCustSicCd, newProsBean.getDsCustSicCd());
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctUrl, newProsBean.getDsAcctUrl());
        ZYPEZDItemValueSetter.setValue(tMsg.orgIdDescTxt, newProsBean.getOrgIdDescTxt());
        ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, newProsBean.getCtryCd());
        ZYPEZDItemValueSetter.setValue(tMsg.addlPostCd, newProsBean.getAddlPostCd());
        ZYPEZDItemValueSetter.setValue(tMsg.dsLocRevAmt, newProsBean.getDsLocRevAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.dsCustSicDescTxt, newProsBean.getDsCustSicDescTxt());
        ZYPEZDItemValueSetter.setValue(tMsg.dsLocEmpNum, newProsBean.getDsLocEmpNum());
        ZYPEZDItemValueSetter.setValue(tMsg.dsXtrnlRefTxt, newProsBean.getDsXtrnlRefTxt());
        ZYPEZDItemValueSetter.setValue(tMsg.dsDataSrcTxt, newProsBean.getDsDataSrcTxt());
        ZYPEZDItemValueSetter.setValue(tMsg.trtyTrkRvwStsCd, newProsBean.getTrtyTrkRvwStsCd());
        ZYPEZDItemValueSetter.setValue(tMsg.toBeDsXrefPsnCd, newProsBean.getToBeDsXrefPsnCd());
        ZYPEZDItemValueSetter.setValue(tMsg.toBeDsXrefAcctCd, newProsBean.getToBeDsXrefAcctCd());

        return tMsg;
    }

    /**
     * if error then return true.
     */
    private boolean validation(ResultSet rsSet, TRTY_RULETMsg trtyRuleTMsg) throws SQLException {
        boolean isBizErr = false;

        if (ZYPConstant.FLG_OFF_N.equals(rsSet.getString("EXISTS_ST"))) {
            errInfoList.add(getMap(NMAM0009E, new String[] {"ST_CD:" + rsSet.getString("ST_CD") }));
            isBizErr = true;
        }
        if (ZYPConstant.FLG_OFF_N.equals(rsSet.getString("EXISTS_LINE_BIZ_ROLE_TP"))) {
            errInfoList.add(getMap(NMAM0009E, new String[] {"LINE_BIZ_ROLE_TP_CD:" + rsSet.getString("LINE_BIZ_ROLE_TP_CD") }));
            isBizErr = true;
        }
        if (ZYPConstant.FLG_OFF_N.equals(rsSet.getString("EXISTS_CTRY"))) {
            errInfoList.add(getMap(NMAM0009E, new String[] {"CTRY_CD:" + rsSet.getString("CTRY_CD") }));
            isBizErr = true;
        }

        if (!STS_U.equals(rsSet.getString("TRTY_TRK_RVW_STS_CD")) //
                && !STS_D.equals(rsSet.getString("TRTY_TRK_RVW_STS_CD"))) {
            errInfoList.add(getMap(NMAM0009E, new String[] {"TRTY_TRK_RVW_STS_CD:" + rsSet.getString("TRTY_TRK_RVW_STS_CD") }));
            isBizErr = true;
        }

        String trtyTrkRvwStsCd = rsSet.getString("TRTY_TRK_RVW_STS_CD");
        if (STS_U.equals(trtyTrkRvwStsCd)) {
            if (validationForUpdate(rsSet, trtyRuleTMsg)) {
                isBizErr = true;
            }
        } else if (STS_D.equals(trtyTrkRvwStsCd)) {
            if (validationForDelete(rsSet)) {
                isBizErr = true;
            }
        }

        return isBizErr;
    }

    private boolean validationForDelete(ResultSet rsSet) throws SQLException {
        boolean isBizErr = false;
        //        if (!ZYPCommonFunc.hasValue(rsSet.getString("GLBL_CMPY_CD"))) {
        //            errInfoList.add(getMap(NMAM0836E, new String[] {"GLBL_CMPY_CD" }));
        //        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("TO_BE_DS_XREF_PSN_CD")) //
                && !ZYPCommonFunc.hasValue(rsSet.getString("TO_BE_DS_XREF_ACCT_CD"))) {
            errInfoList.add(getMap(NMAM8583E, new String[] {"TO_BE_DS_XREF_PSN_CD", "TO_BE_DS_XREF_ACCT_CD" }));
            isBizErr = true;
        }

        // 2016/08/22 QC#12366 Add Start
        String toBeLocNum = rsSet.getString("TO_BE_DS_XREF_PSN_CD");
        if (ZYPCommonFunc.hasValue(toBeLocNum)) {
            if (!existsLocNum(toBeLocNum)) {
                errInfoList.add(getMap(NMAM0009E, new String[] {"Location#:" + toBeLocNum }));
                isBizErr = true;
            }
        }
        // 2016/08/22 QC#12366 Add Start

        String locNum = rsSet.getString("LOC_NUM");
        if (!ZYPCommonFunc.hasValue(locNum) //
                && !ZYPCommonFunc.hasValue(rsSet.getString("DS_DATA_SRC_TXT"))) {
            errInfoList.add(getMap(NMAM8583E, new String[] {"LOC_NUM", "DS_DATA_SRC_TXT" }));
            isBizErr = true;
        }

        if (!ZYPCommonFunc.hasValue(locNum) //
                && !PROS_RVW_STS.A.equals(rsSet.getString("ORIG_PROS_RVW_STS_CD"))) {
            errInfoList.add(getMap(NMAM8580E, new String[] {"validationForDelete:" + rsSet.getString("ORIG_PROS_RVW_STS_CD") }));
            isBizErr = true;
        }

        return isBizErr;
    }

    private boolean validationForUpdate(ResultSet rsSet, TRTY_RULETMsg trtyRuleTMsg) throws SQLException {
        boolean isBizErr = false;
        //        if (!ZYPCommonFunc.hasValue(rsSet.getString("GLBL_CMPY_CD"))) {
        //            errInfoList.add(getMap(NMAM0836E, new String[] {"GLBL_CMPY_CD" }));
        //        }
        if (!ZYPCommonFunc.hasValue(rsSet.getString("DS_ACCT_NM"))) {
            errInfoList.add(getMap(NMAM0836E, new String[] {"DS_ACCT_NM" }));
            isBizErr = true;
        }
        if (!ZYPCommonFunc.hasValue(rsSet.getString("FIRST_LINE_ADDR"))) {
            errInfoList.add(getMap(NMAM0836E, new String[] {"FIRST_LINE_ADDR" }));
            isBizErr = true;
        }
        if (!ZYPCommonFunc.hasValue(rsSet.getString("CTY_ADDR"))) {
            errInfoList.add(getMap(NMAM0836E, new String[] {"CTY_ADDR" }));
            isBizErr = true;
        }
        if (!ZYPCommonFunc.hasValue(rsSet.getString("ST_CD"))) {
            errInfoList.add(getMap(NMAM0836E, new String[] {"ST_CD" }));
            isBizErr = true;
        }
        if (!ZYPCommonFunc.hasValue(rsSet.getString("FIRST_POST_CD"))) {
            errInfoList.add(getMap(NMAM0836E, new String[] {"FIRST_POST_CD" }));
            isBizErr = true;
        }
        if (!ZYPCommonFunc.hasValue(rsSet.getString("DS_DATA_SRC_TXT"))) {
            errInfoList.add(getMap(NMAM0836E, new String[] {"DS_DATA_SRC_TXT" }));
            isBizErr = true;
        }
        if (!ZYPCommonFunc.hasValue(rsSet.getString("TRTY_TRK_RVW_STS_CD"))) {
            errInfoList.add(getMap(NMAM0836E, new String[] {"TRTY_TRK_RVW_STS_CD" }));
            isBizErr = true;
        }

        String locNum = rsSet.getString("LOC_NUM");
        if (ZYPCommonFunc.hasValue(locNum)) {
            if (!existsLocNum(locNum)) {
                errInfoList.add(getMap(NMAM0009E, new String[] {"Location#:" + locNum }));
                isBizErr = true;
            }
        }

        String origProsRvwStsCd = rsSet.getString("ORIG_PROS_RVW_STS_CD");
        if (!PROS_RVW_STS.A.equals(origProsRvwStsCd)) {
            errInfoList.add(getMap(NMAM8580E, new String[] {"validationForUpdate:" + origProsRvwStsCd }));
            isBizErr = true;
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("ORIG_DS_ACCT_NUM")) //
                || ZYPCommonFunc.hasValue(rsSet.getString("ORIG_LOC_NUM"))) {
            errInfoList.add(getMap(NMAM8581E, new String[] {}));
            isBizErr = true;
        }

        String trtyOrgCd = rsSet.getString("TRTY_ORG_CD");
        if (ZYPCommonFunc.hasValue(trtyOrgCd)) {
            TRTY_RULETMsg tMsg = getActiveTerritory(trtyOrgCd);
            if (tMsg == null) {
                errInfoList.add(getMap(NMAM8582E, new String[] {trtyOrgCd }));
                isBizErr = true;
            } else {
                EZDMsg.copy(tMsg, null, trtyRuleTMsg, null);
            }
        }

        return isBizErr;
    }

    private TRTY_RULETMsg getActiveTerritory(String trtyOrgCd) {
        TRTY_RULETMsg tMsg = new TRTY_RULETMsg();
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("orgCd", trtyOrgCd);
        param.put("current", GNRN_TP.CURRENT);
        param.put("future", GNRN_TP.FUTURE);
        param.put("slsDt", this.slsDt);
        Map<String, String> map //
        = (Map<String, String>) this.ssmBatchClient.queryObject("getActiveTerritory", param);
        if (map == null) {
            return null;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.orgCd, (String) map.get("ORG_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.orgStruTpCd, (String) map.get("ORG_STRU_TP_CD"));
        return tMsg;
    }

    @Override
    protected void termRoutine() {
        if (notifyMessageMap.size() > 0) {
            sendNotifyMail();
        }
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.commitCount, this.errorCount, this.commitCount + this.errorCount);

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

        mailTemplate = new S21MailTemplate(getGlobalCompanyCode(), MAIL_TEMPLATE_ID_FOR_ISSUE);
        mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_BATCH_NM, BATCH_NM);
        mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_MSG_INFO, errorMessage.toString());

        mail = new S21Mail(getGlobalCompanyCode());
        mail.setFromAddress(fromAddress);
        mail.setToAddressList(toAddressList);
        mail.setMailTemplate(mailTemplate);
        mail.postMail();
    }

    private void sendNotifyMail() {

        S21MailGroup mailGroupFrom = null;
        S21MailAddress fromAddress = null;
        S21MailAddress toAddress = null;
        S21MailTemplate mailTemplate = null;
        S21Mail mail = null;

        mailGroupFrom = new S21MailGroup(getGlobalCompanyCode(), MAIL_GROUP_ID_FROM);
        fromAddress = mailGroupFrom.getMailAddress().get(0);

        mailTemplate = new S21MailTemplate(getGlobalCompanyCode(), MAIL_TEMPLATE_ID_FOR_NOTIFY);
        mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_BATCH_ID, BATCH_ID);

        for (String psnCd : notifyMessageMap.keySet()) {
            String emlAddr = getEmlAddr(psnCd);
            if (ZYPCommonFunc.hasValue(emlAddr)) {
                toAddress = new S21MailAddress(this.glblCmpyCd, emlAddr);
            } else {
                S21MailGroup mailGroupTo = null;
                mailGroupTo = new S21MailGroup(getGlobalCompanyCode(), MAIL_GROUP_ID_TO);
                toAddress = mailGroupTo.getMailAddress().get(0);
            }
            mailTemplate.setTemplateParameter(//
                    MAIL_TEMPLATE_KEY_MSG_INFO, notifyMessageMap.get(psnCd).toString());

            mail = new S21Mail(getGlobalCompanyCode());
            mail.setFromAddress(fromAddress);
            mail.setToAddress(toAddress);
            mail.setMailTemplate(mailTemplate);
            mail.postMail();
        }
    }

    private String getEmlAddr(String psnCd) {
        S21_PSNTMsg tMsg = new S21_PSNTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.psnCd, psnCd);

        tMsg = (S21_PSNTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return null;
        }
        return tMsg.emlAddr.getValue();
    }
    // 2016/08/22 QC#12366 Add Start
    /**
     * Convert Null to Blank
     * @param val String
     * @return String
     */
    private String nullToBlank(String val) {
        if (!ZYPCommonFunc.hasValue(val)) {
            return "";
        }
        return val;
    }
    // 2016/08/22 QC#12366 Add End
    // mod start 2016/10/04 CSA QC#11320
    private boolean getCustomer(NMAB506001NewProsBean newProsBean) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("locNum", newProsBean.getLocNum());
        Integer cnt //
        = (Integer) this.ssmBatchClient.queryObject("getCustomer", param);
        if (cnt == null) {
            return false;
        }

        return cnt > 0;
    }
    // mod end 2016/10/04 CSA QC#11320
    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        // initialize S21BatchMain
        new NMAB506001().executeBatch(NMAB506001.class.getSimpleName());
    }

    // 2017/09/14 QC#20360 Add Start
    private String getLineBizTpCd(NMAB506001NewProsBean newProsBean, TRTY_RULETMsg tMsg) {

        String lineBizTpCd = null;

        if (ZYPCommonFunc.hasValue(newProsBean.getTrtyOrgCd())) {
            Map<String, Object> rgstSalesRepInfo = getRegisterdSalesRep(tMsg);
            if (rgstSalesRepInfo != null) {
                lineBizTpCd = (String) rgstSalesRepInfo.get("LINE_BIZ_TP_CD");
            }
        }

        if (!ZYPCommonFunc.hasValue(lineBizTpCd)) {
            if (ZYPCommonFunc.hasValue(newProsBean.getOrigBefPsnNum())) {
                Map<String, Object> currentOwnerInfo = getCurrentOwnerInfo(newProsBean.getOrigBefPsnNum());
                if (currentOwnerInfo != null) {
                    lineBizTpCd = (String) currentOwnerInfo.get("LINE_BIZ_TP_CD");
                }
            }
        }

        return lineBizTpCd;
    }

    private Map<String, Object> getCurrentOwnerInfo(String psnNum) {

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("psnNum", psnNum);
        
        Map<String, Object> currentOwnerInfo
        = (Map<String, Object>) this.ssmBatchClient.queryObject("getCurrentOwnerInfo", param);

        if (currentOwnerInfo == null || currentOwnerInfo.size() == 0) {
            return null;
        }

        return currentOwnerInfo;
    }
    // 2017/09/14 QC#20360 Add End

    // 2018/03/23 QC#23157 Add Start
    private boolean isGeoTerritory(NMAB506001NewProsBean newProsBean) {

        DS_ORG_UNITTMsg orgUnitTMsg = new DS_ORG_UNITTMsg();
        ZYPEZDItemValueSetter.setValue(orgUnitTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(orgUnitTMsg.orgCd, newProsBean.getTrtyOrgCd());
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

    private boolean isExistsPostCdInTrtyRule(NMZC001001PMsg p1Msg, NMAB506001NewProsBean newProsBean) {

        DS_ORG_UNITTMsg orgUnitTMsg = new DS_ORG_UNITTMsg();
        ZYPEZDItemValueSetter.setValue(orgUnitTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(orgUnitTMsg.orgCd, newProsBean.getTrtyOrgCd());
        orgUnitTMsg = (DS_ORG_UNITTMsg) S21CacheTBLAccessor.findByKey(orgUnitTMsg);

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("slsDt", this.slsDt);
        param.put("orgCd", orgUnitTMsg.orgCd.getValue());
        param.put("orgStruTpCd", orgUnitTMsg.orgStruTpCd.getValue());
        param.put("trtyRuleTpCd", TRTY_RULE_TP.POSTAL_CODE);
        param.put("trtyRuleLogicTpCd", TRTY_RULE_LOGIC_TP.OR);
        param.put("trtyRuleOprdTpCd_Eq", TRTY_RULE_OPRD_TP.EQUAL);
        // Del Start 2018/06/01 QC#24293
        //param.put("trtyRuleOprdTpCd_Lk", TRTY_RULE_OPRD_TP.LIKE);
        // Del End 2018/06/01 QC#24293
        param.put("trtyRuleOprdTpCd_Bw", TRTY_RULE_OPRD_TP.BETWEEN);
        param.put("postCd", p1Msg.NMZC001002PMsg.no(0).postCd.getValue());

        Integer overlapCnt = (Integer) this.ssmBatchClient.queryObject("getOverlapPostalCode", param);

        return overlapCnt > 0;
    }

    private void insertTrtyRuleForPostalCode(NMZC001001PMsg p1Msg, TRTY_RULETMsg tMsg) {

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRulePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TRTY_RULE_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleTpCd, TRTY_RULE_TP.POSTAL_CODE);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleFromValTxt, p1Msg.NMZC001002PMsg.no(0).postCd);
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleOprdTpCd, TRTY_RULE_OPRD_TP.EQUAL);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleLogicTpCd, TRTY_RULE_LOGIC_TP.OR);

        S21FastTBLAccessor.insert(tMsg);
    }
    // 2018/03/23 QC#23157 Add End

}
