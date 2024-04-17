/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB261001;

import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.MAX_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.NMAM0071E;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.NMAM8121E;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.NMAM8228E;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.NMAM8404E;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.NMAM8407E;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.NMAM8460E;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.NMAM8545E;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.NMAM8546E;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.NMAM8556E;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.NMAM8557E;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.NMAM8562E;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.NMAM8587I;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.NMAM8588I;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.NMAM8647E;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.NMAM8672E;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.NMAM8683E;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.NYXM0001I;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.NYXM0002E;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.ORG_NM_DEL;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.RESULT_MSG_ERR;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.RESULT_MSG_INS;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.RESULT_MSG_UPD;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.SLS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.TIME_STAMP_FORMAT;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.TRTY_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.UPLD_CSV_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NMA.NMAB261001.constant.NMAB261001Constant.ZZZM9026E;

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
import business.db.ACCT_TRTY_RESRC_ASG_WRKTMsg;
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
 * Account Owner Look Up Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/30   Fujitsu         M.Ohno          Create
 * 2016/06/24   Hitachi         Y.Takeno        Update          QC#8156
 * 2016/07/01   Hitachi         Y.Takeno        Update          QC#8156
 * 2016/09/06   SRAA            Y.Chen          Update          QC#11727
 * 2016/09/07   Fujitsu         C.Yokoi         Update          QC#12229
 * 2016/09/12   Fujitsu         C.Yokoi         Update          QC#10091
 * 2016/09/23   Fujitsu         C.Yokoi         Update          QC#8156
 * 2016/10/05   Fujitsu         C.Yokoi         Update          QC#14526
 * 2016/10/19   Hitachi         T.Mizuki        Update          QC#8156
 * 2017/03/15   Fujitsu         R.Nakamura      Update          QC#15878
 * 2017/10/13   Fujitsu         M.Ohno          Update          QC#20662
 * 2017/10/13   Hitachi         J.Kim           Update          QC#21299
 * 2017/12/20   Fujitsu         N.Sugiura       Update          QC#22261
 * 2018/03/23   Fujitsu         R.Nakamura      Update          QC#24442
 * 2018/03/26   Fujitsu         A.Kosai         Update          QC#23157
 * 2018/06/01   Fujitsu         Hd.Sugawara     Update          QC#24293
 * 2018/06/13   Fujitsu         T.Noguchi       Update          QC#25490
 * 2018/08/28   Fujitsu         W.Honda         Update          QC#25558
 * 2018/09/11   Fujitsu         Hd.Sugawara     Update          QC#28073
 * 2019/07/19   Fujitsu         R.Nakamura      Update          QC#51785
 * 2019/07/30   Fujitsu         Hd.Sugawara     Update          QC#52200
 * 2019/11/14   Fujitsu         C.Hara          Update          QC#54207
 * 2023/06/30   CITS            F.Komaki        Update          QC#61615
 * 2023/10/30   CITS            K.Ikeda         Update          QC#62019
 *</pre>
 */
public class NMAB261001 extends S21BatchMain {

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

    /** userConstantVariable */
    private String userConstantVariable = null;

    /** process date time */
    private String procDt = null;

    /** sales date time */
    private String slsDt = null;

    /** TermCd */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** ONLINE errorMsg */
    private StringBuilder onlineMsg = new StringBuilder();

    // QC#11727
    /** ONLINE Information Message */
    private StringBuilder onlineInfoMsg = new StringBuilder();

    /** Total Process Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    // START 2016/07/01 [QC#8156,MOD]
    /** Update Count (current File) */
    private int currentUpdateCount = 0;

    /** Error Count (current File) */
    private int currentErrorCount = 0;
    // END 2016/07/01 [QC#8156,MOD]

    // 2018/08/28 CSA-QC#25558 Add Start
    /** Upload CSV Request ID List */
    private BigDecimal[] upldCsvRqstPkArray = null;
    // 2018/08/28 CSA-QC#25558 Add Start

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

        this.userConstantVariable = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(userConstantVariable)) {
            throw new S21AbendException(ZZZM9026E, new String[] {"User Constant Variable" });
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
        new NMAB261001().executeBatch(NMAB261001.class.getSimpleName());

    }

    @Override
    protected void mainRoutine() {
        if (this.userConstantVariable.equals(RQST_CRAT_SYS_TP.ONLINE)) {
            ART10FMsg fMsg = new ART10FMsg();
            this.doProcess(fMsg);
        } else {
            batchHelper = new S21RequestBatchHelper();
            if (!this.batchHelper.isRecord()) {
                this.termCd = TERM_CD.WARNING_END;
                return;
            }

            // 2018/08/28 CSA-QC#25558 Add Start
            List<BigDecimal> upldCsvRqstPkList = new ArrayList<BigDecimal>();
            for (ART10FMsg request : this.batchHelper.getRequestList()) {
                upldCsvRqstPkList.add(getUpldCsvReqPk(request));
            }
            this.upldCsvRqstPkArray = upldCsvRqstPkList.toArray(new BigDecimal[upldCsvRqstPkList.size()]);
            // 2018/08/28 CSA-QC#25558 Add Start

            for (ART10FMsg request : this.batchHelper.getRequestList()) {
                this.doProcess(request);
            }
        }
    }

    /**
     * @param fMsg ART10FMsg
     */
    private void doProcess(ART10FMsg fMsg) {
        PreparedStatement psWrk = null;
        ResultSet rsSet = null;
        String upldCsvId = null;
        BigDecimal upldCsvRqstPk = null;

        if (this.userConstantVariable.equals(RQST_CRAT_SYS_TP.CSV_UPLOAD)) {
            // Upload ID
            upldCsvId = getUpldCsvId(fMsg);

            // Upload Request PK
            upldCsvRqstPk = getUpldCsvReqPk(fMsg);
            this.messenger = new ZYPCSVUploadMessenger(upldCsvId, upldCsvRqstPk);
        }

        try {
            // 1.1 Get active request data
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            Map<String, Object> inWrkMap = new HashMap<String, Object>();
            execParam.setFetchSize(DEFAULT_FETCH_SIZE);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            BigDecimal hdrPk = null;
            if (RQST_CRAT_SYS_TP.ONLINE.equals(this.userConstantVariable)) {
                inWrkMap.put("glblCmpyCd", glblCmpyCd);
                inWrkMap.put("rqstCratSysTpCd", RQST_CRAT_SYS_TP.ONLINE);
                inWrkMap.put("rqstRstyTpCd", RQST_RSLT_TP.SUBMITTED);

                psWrk = ssmLLClient.createPreparedStatement("getRequestInfo", inWrkMap, execParam);
                rsSet = psWrk.executeQuery();

            } else if (RQST_CRAT_SYS_TP.CSV_UPLOAD.equals(this.userConstantVariable)) {
                inWrkMap.put("glblCmpyCd", glblCmpyCd);
                inWrkMap.put("upldCsvRqstPk", upldCsvRqstPk);
                // 2018/08/28 CSA-QC#25558 Add Start
                inWrkMap.put("upldCsvRqstPkArray", this.upldCsvRqstPkArray);
                // 2018/08/28 CSA-QC#25558 Add Start

                psWrk = ssmLLClient.createPreparedStatement("getWrkInfo", inWrkMap, execParam);
                rsSet = psWrk.executeQuery();

                hdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ACCT_TRTY_RESRC_RQST_HDR_SQ);
            }

            BigDecimal beforeHdrPk = null;
            String rsnCmntTxt = "";
            boolean isError = false;
            boolean isHeaderError = false;
            boolean hasUpdate = false;
            // Del Start 2019/07/30 QC#52200
            //List<ACCT_TRTY_RESRC_RQST_DTLTMsg> updateList = new ArrayList<ACCT_TRTY_RESRC_RQST_DTLTMsg>();
            // Del End 2019/07/30 QC#52200

            while (rsSet.next()) {
                isError = false;
                // HeaderCheck
                if (RQST_CRAT_SYS_TP.ONLINE.equals(this.userConstantVariable)) {
                    hdrPk = rsSet.getBigDecimal("ACCT_TRTY_RESRC_RQST_HDR_PK");
                }

                if (ZYPCommonFunc.hasValue(beforeHdrPk) && !hdrPk.equals(beforeHdrPk)) {
                    // commit or roll back organization master
                    if (!isError) {
                        // 2016/09/23 CSA-QC#8156 Mod Start
                        updateHeader(isHeaderError, beforeHdrPk, upldCsvId, fMsg);
                        // updateHeader(isError, beforeHdrPk, upldCsvId, rsnCmntTxt, fMsg);
                        // 2016/09/23 CSA-QC#8156 Mod End
                    } else {
                        // roll back organization master
                        rollback();
                        // 2016/09/23 CSA-QC#8156 Mod Start
                        updateHeader(isHeaderError, beforeHdrPk, upldCsvId, fMsg);
                        // updateHeader(isError, beforeHdrPk, upldCsvId, rsnCmntTxt, fMsg);
                        // 2016/09/23 CSA-QC#8156 Mod End
                    }

                    // Del Start 2019/07/30 QC#52200
                    //// update or insert DTL
                    //if (RQST_CRAT_SYS_TP.CSV_UPLOAD.equals(this.userConstantVariable)) {
                    //    S21FastTBLAccessor.insert(updateList.toArray(new ACCT_TRTY_RESRC_RQST_DTLTMsg[0]));
                    //    updateList.clear();
                    //} else if (RQST_CRAT_SYS_TP.ONLINE.equals(this.userConstantVariable)) {
                    //    S21FastTBLAccessor.update(updateList.toArray(new ACCT_TRTY_RESRC_RQST_DTLTMsg[0]));
                    //    updateList.clear();
                    //}
                    // Del End 2019/07/30 QC#52200

                    isError = false;
                    commit();
                }

                beforeHdrPk = hdrPk;
                if (RQST_CRAT_SYS_TP.CSV_UPLOAD.equals(this.userConstantVariable)) {
                    rsnCmntTxt = rsSet.getString("UPLD_CSV_RSN_CMNT_TXT");

                }

                // 2.0 get User Data
                List<String> beforeAttrList = new ArrayList<String>(TRTY_SIZE);
                List<String> afterAttrList = new ArrayList<String>(TRTY_SIZE);
                List<String> afterGrpList = new ArrayList<String>(TRTY_SIZE);
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                boolean hasAfter = false;
                // 2019/11/14 QC#54207 Add Start
                // 2.1 Mandatory Check
                if (!ZYPCommonFunc.hasValue(rsSet.getString("LOC_NUM"))) {
                    setErrorMsg(rsSet, ZZZM9025E, "Location");
                    beforeAttrList.add("");
                    afterAttrList.add("");
                    afterGrpList.add("");
                    isError = true;
                }

                if (!ZYPCommonFunc.hasValue(rsSet.getString("DS_ACCT_NUM"))) {
                    setErrorMsg(rsSet, ZZZM9025E, "Account Number");
                    beforeAttrList.add("");
                    afterAttrList.add("");
                    afterGrpList.add("");
                    isError = true;
                }
                // 2019/11/14 QC#54207 Add End
                if (RQST_CRAT_SYS_TP.CSV_UPLOAD.equals(this.userConstantVariable)) {
                    // Add Start 2018/09/11 QC#28073
                    String acctTpCd = null;
                    Map<String, Object> acctChkParam = new HashMap<String, Object>();

                    acctChkParam.put("glblCmpyCd", this.glblCmpyCd);
                    acctChkParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
                    acctChkParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
                    acctChkParam.put("rgtnStsCdP01", RGTN_STS.PENDING_COMPLETION);

                    // 2019/11/14 QC#54207 Mod Start
                    // Map<String, String> acctMap = (Map<String, String>) this.ssmBatchClient.queryObject("getAcct", acctChkParam);
                    Map<String, String> acctMap = null;
                    if(ZYPCommonFunc.hasValue(rsSet.getString("DS_ACCT_NUM"))){
                        acctMap = (Map<String, String>) this.ssmBatchClient.queryObject("getAcct", acctChkParam);
                    }
                    // 2019/11/14 QC#54207 Mod End

                    if (acctMap != null && !acctMap.isEmpty()) {
                        acctTpCd = acctMap.get("DS_ACCT_TP_CD");
                    }
                    // Add End 2018/09/11 QC#28073

                    for (int i = 0; i < TRTY_SIZE; i++) {
                        String no = String.format("%1$02d", i + 1);

                        // START 2017/10/13 J.Kim [QC#21299,ADD]
                        String bfOrgCd = rsSet.getString("ACCT_TRTY_ORG_CD_" + no);
                        // Mod Start 2018/09/11 QC#28073
                        //if (ZYPCommonFunc.hasValue(bfOrgCd) && !checkOrgCd(rsSet, bfOrgCd)) {
                        if (ZYPCommonFunc.hasValue(bfOrgCd) && !checkOrgCd(rsSet, bfOrgCd, acctTpCd)) {
                            // Mod End 2018/09/11 QC#28073
                            setErrorMsg(rsSet, NMAM8672E);
                            beforeAttrList.add("");
                            afterAttrList.add("");
                            afterGrpList.add("");
                            isError = true;
                            continue;
                        }
                        // END 2017/10/13 J.Kim [QC#23922,ADD]

                        // START 2017/10/13 J.Kim [QC#21299,MOD]
                        // if (ZYPCommonFunc.hasValue(rsSet.getString("BEF_ACCT_TRTY_ORG_NM_" + no))) {
                        //    ssmParam.put("glblCmpyCd", this.glblCmpyCd);
                        //    ssmParam.put("orgNm", rsSet.getString("BEF_ACCT_TRTY_ORG_NM_" + no));
                        if (ZYPCommonFunc.hasValue(bfOrgCd)) {
                            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
                            ssmParam.put("orgCd", bfOrgCd);
                            ssmParam.put("orgNm", "");
                        // END 2017/10/13 J.Kim [QC#21299,MOD]
                            ssmParam.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
                            ssmParam.put("gnrnTpCd2", GNRN_TP.CURRENT);
                            ssmParam.put("gnrnTpCd3", GNRN_TP.FUTURE);
                            Map<String, String> orgCdMap = (Map<String, String>) this.ssmBatchClient.queryObject("getOrgCd", ssmParam);

                            if (orgCdMap == null || orgCdMap.isEmpty()) {
                                setErrorMsg(rsSet, NMAM8121E, "Territory");
                                beforeAttrList.add("");
                                afterAttrList.add("");
                                afterGrpList.add("");
                                isError = true;
                                continue;
                            }

                            beforeAttrList.add(orgCdMap.get("ORG_CD"));

                        } else {
                            beforeAttrList.add("");
                        }

                        // START 2017/10/13 J.Kim [QC#21299,MOD]
                        // if (ZYPCommonFunc.hasValue(rsSet.getString("AFT_ACCT_TRTY_ORG_NM_" + no))) {
                        if (ZYPCommonFunc.hasValue(rsSet.getString("ACCT_TRTY_ORG_NM_" + no))) { 
                        // END 2017/10/12 J.Kim [QC#21299,MOD]

                            hasAfter = true;
                            // START 2017/10/13 J.Kim [QC#21299,MOD]
                            // if (ORG_NM_DEL.equals(rsSet.getString("AFT_ACCT_TRTY_ORG_NM_" + no))) {
                            if (ORG_NM_DEL.equals(rsSet.getString("ACCT_TRTY_ORG_NM_" + no))) {
                            // END 2017/10/13 J.Kim [QC#21299,MOD]
                                afterAttrList.add(ORG_NM_DEL);
                                continue;
                            }

                            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
                            // START 2017/10/13 J.Kim [QC#21299,MOD]
                            // ssmParam.put("orgNm", rsSet.getString("AFT_ACCT_TRTY_ORG_NM_" + no));
                            ssmParam.put("orgNm", rsSet.getString("ACCT_TRTY_ORG_NM_" + no));
                            ssmParam.put("orgCd", "");
                            // END 2017/10/13 J.Kim [QC#21299,MOD]
                            ssmParam.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
                            ssmParam.put("gnrnTpCd2", GNRN_TP.CURRENT);
                            ssmParam.put("gnrnTpCd3", GNRN_TP.FUTURE);
                            Map<String, String> orgCdMap = (Map<String, String>) this.ssmBatchClient.queryObject("getOrgCd", ssmParam);

                            if (orgCdMap == null || orgCdMap.isEmpty()) {
                                setErrorMsg(rsSet, NMAM8121E, "Territory");
                                afterAttrList.add("");
                                afterGrpList.add("");
                                isError = true;
                                continue;
                            }

                            afterAttrList.add(orgCdMap.get("ORG_CD"));
                            afterGrpList.add(orgCdMap.get("TRTY_GRP_TP_CD"));
                        } else {
                            afterAttrList.add("");
                            afterGrpList.add("");
                        }
                    }

                } else if (RQST_CRAT_SYS_TP.ONLINE.equals(this.userConstantVariable)) {
                    for (int i = 0; i < TRTY_SIZE; i++) {
                        String no = String.format("%1$02d", i + 1);

                        if (ZYPCommonFunc.hasValue(rsSet.getString("BEF_ACCT_TRTY_ORG_CD_" + no))) {
                            beforeAttrList.add(rsSet.getString("BEF_ACCT_TRTY_ORG_CD_" + no));
                        } else {
                            beforeAttrList.add("");
                        }

                        if (ZYPCommonFunc.hasValue(rsSet.getString("AFT_ACCT_TRTY_ORG_CD_" + no))) {
                            hasAfter = true;

                            if (ORG_NM_DEL.equals(rsSet.getString("AFT_ACCT_TRTY_ORG_CD_" + no))) {
                                afterAttrList.add(ORG_NM_DEL);
                                continue;
                            }

                            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
                            ssmParam.put("orgCd", rsSet.getString("AFT_ACCT_TRTY_ORG_CD_" + no));
                            ssmParam.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
                            ssmParam.put("gnrnTpCd2", GNRN_TP.CURRENT);
                            ssmParam.put("gnrnTpCd3", GNRN_TP.FUTURE);
                            Map<String, String> orgCdMap = (Map<String, String>) this.ssmBatchClient.queryObject("getOrgCd", ssmParam);

                            if (orgCdMap == null || orgCdMap.isEmpty()) {
                                setErrorMsg(rsSet, NMAM8121E, "Territory");
                                isError = true;
                                continue;
                            }

                            afterGrpList.add(orgCdMap.get("TRTY_GRP_TP_CD"));
                            afterAttrList.add(orgCdMap.get("ORG_CD"));

                        } else {
                            afterAttrList.add("");
                            afterGrpList.add("");
                        }
                    }
                }

                if (isError) {
                    Map<String, Object> acctChkParam = new HashMap<String, Object>();
                    acctChkParam.put("glblCmpyCd", this.glblCmpyCd);
                    acctChkParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
                    acctChkParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
                    acctChkParam.put("rgtnStsCdP01", RGTN_STS.PENDING_COMPLETION);
                    // 2019/11/14 QC#54207 Mod Start
                    //Map<String, String> acctMap = (Map<String, String>) this.ssmBatchClient.queryObject("getAcct", acctChkParam);
                    Map<String, String> acctMap = null;
                    if(ZYPCommonFunc.hasValue(rsSet.getString("DS_ACCT_NUM"))){
                        acctMap = (Map<String, String>) this.ssmBatchClient.queryObject("getAcct", acctChkParam);
                    }
                    // 2019/11/14 QC#54207 Mod End
                    String acctTpCd = null;
                    if (acctMap != null && !acctMap.isEmpty()) {
                        acctTpCd = acctMap.get("DS_ACCT_TP_CD");
                    }

                    // 2016/09/23 CSA-QC#8156 Mod Start
                    // Mod Start 2019/07/30 QC#52200
                    //updateList.add(createUpdateDetail(rsSet, hdrPk, beforeAttrList, afterAttrList, acctTpCd, rsnCmntTxt, isError));
                    endDetailProcess(rsSet, hdrPk, beforeAttrList, afterAttrList, acctTpCd, rsnCmntTxt, isError);
                    // Mod End 2019/07/30 QC#52200
                    // updateList.add(createUpdateDetail(rsSet, hdrPk, beforeAttrList, afterAttrList, acctTpCd, isError));
                        // 2016/09/23 CSA-QC#8156 Mod End
                    isHeaderError = true;
                    continue;
                }

                // Add Start 2018/09/11 QC#28073
                String befManEntryFlg = getManualEntryFlag(rsSet);
                String aftManEntryFlg = rsSet.getString("MAN_ENTRY_FLG");
                // Add End 2018/09/11 QC#28073

                // 2. Validation
                // Mod Start 2018/09/11 QC#28073
                //boolean isChngManFlg = isChangeManualEntryFlag(afterAttrList, rsSet);
                boolean isChngManFlg = isChangeManualEntryFlag(afterAttrList, rsSet, befManEntryFlg);
                // Mod End 2018/09/11 QC#28073
                boolean returnIsError = validationCheck(rsSet, this.userConstantVariable, beforeAttrList, afterAttrList, hasAfter, isChngManFlg);

                if (!isError) {
                    isError = returnIsError;
                }

                // 2018/08/28 CSA-QC#25558 Add Start
                boolean procFlg = ZYPConstant.FLG_ON_Y.equals(rsSet.getString("PROC_FLG"));

                if (!procFlg) {
                    Map<String, Object> acctChkParam = new HashMap<String, Object>();
                    acctChkParam.put("glblCmpyCd", this.glblCmpyCd);
                    acctChkParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
                    acctChkParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
                    acctChkParam.put("rgtnStsCdP01", RGTN_STS.PENDING_COMPLETION);
                    Map<String, String> acctMap = (Map<String, String>) this.ssmBatchClient.queryObject("getAcct", acctChkParam);
                    String acctTpCd = null;
                    if (acctMap != null && !acctMap.isEmpty()) {
                        acctTpCd = acctMap.get("DS_ACCT_TP_CD");
                    }

                    // Mod Start 2019/07/30 QC#52200
                    //updateList.add(createUpdateDetail(rsSet, hdrPk, beforeAttrList, afterAttrList, acctTpCd, rsnCmntTxt, isError));
                    endDetailProcess(rsSet, hdrPk, beforeAttrList, afterAttrList, acctTpCd, rsnCmntTxt, isError);
                    // Mod End 2019/07/30 QC#52200
                    // updateList.add(createUpdateDetail(rsSet, hdrPk, beforeAttrList, afterAttrList, acctTpCd, isError));
                    // 2016/09/23 CSA-QC#8156 Mod End
                    hasUpdate = true;
                    continue;
                }
                // 2018/08/28 CSA-QC#25558 Add Start

                String acctTpCd = null;
                if (!isError) {
                    // get Acct Type
                    Map<String, Object> acctChkParam = new HashMap<String, Object>();
                    acctChkParam.put("glblCmpyCd", this.glblCmpyCd);
                    acctChkParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
                    acctChkParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
                    acctChkParam.put("rgtnStsCdP01", RGTN_STS.PENDING_COMPLETION);
                    Map<String, String> acctMap = (Map<String, String>) this.ssmBatchClient.queryObject("getAcct", acctChkParam);
                    acctTpCd = acctMap.get("DS_ACCT_TP_CD");

                    // Update for change only Manual Entry Flag
                    if (isChngManFlg) {
                        updateManualEntryFlag(afterAttrList, rsSet, acctTpCd);
                        // 2016/09/23 CSA-QC#8156 Mod Start
                        // Mod Start 2019/07/30 QC#52200
                        //updateList.add(createUpdateDetail(rsSet, hdrPk, beforeAttrList, afterAttrList, acctTpCd, rsnCmntTxt, isError));
                        endDetailProcess(rsSet, hdrPk, beforeAttrList, afterAttrList, acctTpCd, rsnCmntTxt, isError);
                        // Mod End 2019/07/30 QC#52200
                        //updateList.add(createUpdateDetail(rsSet, hdrPk, beforeAttrList, afterAttrList, acctTpCd, isError));
                        // 2016/09/23 CSA-QC#8156 Mod End
                        hasUpdate = true;
                        continue;
                    }

                    // 3.1.1 Update Territory Rule
                    // 2017/12/20 QC#22261 Mod Start 
                    // for (String beforeAttr : beforeAttrList) {
                    //    if (!ZYPCommonFunc.hasValue(beforeAttr)) {
                    //        continue;
                    //    }
                    for (int i = 0; i < beforeAttrList.size(); i++) {
                        if (!ZYPCommonFunc.hasValue(beforeAttrList.get(i)) || ZYPCommonFunc.hasValue(beforeAttrList.get(i)) && !ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
                            continue;
                        }
                    // 2017/12/20 QC#22261 Mod End 
                        Map<String, Object> getRuleParam = new HashMap<String, Object>();
                        getRuleParam.put("glblCmpyCd", this.glblCmpyCd);
                        getRuleParam.put("orgCd", beforeAttrList.get(i));
                        getRuleParam.put("gnrnTpCd", GNRN_TP.CURRENT);
                        // Mod Start 2017/03/15 QC#15878
//                        getRuleParam.put("ruleTpCd", TRTY_RULE_TP.ACCOUNT_OR_SITE_NUMBER);
                        getRuleParam.put("ruleTpCd", TRTY_RULE_TP.LOCATION_NUMBER);
                        // Mod End 2017/03/15 QC#15878
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
                    for (String afterAttr : afterAttrList) {
                        // DEL and Null Check
                        if (!ZYPCommonFunc.hasValue(afterAttr) || ORG_NM_DEL.equals(afterAttr)) {
                            continue;
                        }

                        // 2018/03/26 QC#23157 Add Start
                        if (isGeoTerritory(afterAttr)) {
                            if (!isExistsPostCdInTrtyRule(afterAttr, rsSet.getString("POST_CD"))) {
                                insertTrtyRuleForPostalCode(afterAttr, rsSet.getString("POST_CD"));
                            }
                            continue;
                        }
                        // 2018/03/26 QC#23157 Add End

                        // QC#11727
                        String orgStruTpCd = ORG_STRU_TP.TERRITORY_STRUCTURE;
                        String locNum = rsSet.getString("LOC_NUM");
                        String orgCd = afterAttr;
                        if (existsTerritoryRule(locNum, orgCd, orgStruTpCd)) {
                            setInfoMsg(rsSet, NMAM8587I);
                            continue;
                        }
                        if (isRuleLogicAllOr(orgCd, orgStruTpCd)) {
                            setInfoMsg(rsSet, NMAM8588I);
                            continue;
                        }

                        Map<String, Object> getRuleParam = new HashMap<String, Object>();
                        getRuleParam.put("glblCmpyCd", this.glblCmpyCd);
                        getRuleParam.put("orgCd", afterAttr);
                        getRuleParam.put("gnrnTpCd", GNRN_TP.CURRENT);
                        // Mod Start 2017/03/15 QC#15878
//                        getRuleParam.put("ruleTpCd", TRTY_RULE_TP.ACCOUNT_OR_SITE_NUMBER);
                        getRuleParam.put("ruleTpCd", TRTY_RULE_TP.LOCATION_NUMBER);
                        // Mod End 2017/03/15 QC#15878
                        getRuleParam.put("fromValTxt", rsSet.getString("LOC_NUM"));
                        getRuleParam.put("oprdTpCd", TRTY_RULE_OPRD_TP.EQUAL);
                        getRuleParam.put("logicTpCd", TRTY_RULE_LOGIC_TP.OR);
                        getRuleParam.put("procDt", this.procDt);
                        getRuleParam.put("maxDt", MAX_DT);

                        BigDecimal rulePk = (BigDecimal) this.ssmBatchClient.queryObject("getTrtyRule", getRuleParam);

                        if (!ZYPCommonFunc.hasValue(rulePk)) {
                            // 2023/10/30 QC#62019 K.Ikeda START
                            Map<String, Object> getActiveRuleParam = new HashMap<String, Object>();
                            
                            getActiveRuleParam.put("glblCmpyCd", this.glblCmpyCd);
                            getActiveRuleParam.put("fromValTxt", rsSet.getString("LOC_NUM"));
                            getActiveRuleParam.put("procDt", this.procDt);
                            getActiveRuleParam.put("maxDt", MAX_DT);
                            BigDecimal activeRulePk = (BigDecimal) this.ssmBatchClient.queryObject("getActiveTrtyRule", getActiveRuleParam);
                            
                            if (activeRulePk != null) {
                                TRTY_RULETMsg upTMsg = new TRTY_RULETMsg();
                                ZYPEZDItemValueSetter.setValue(upTMsg.glblCmpyCd, this.glblCmpyCd);
                                ZYPEZDItemValueSetter.setValue(upTMsg.trtyRulePk, activeRulePk);
                                
                                upTMsg = (TRTY_RULETMsg) EZDTBLAccessor.findByKeyForUpdateWait(upTMsg);
                                ZYPEZDItemValueSetter.setValue(upTMsg.effThruDt, ZYPDateUtil.addDays(this.procDt, -1));

                                EZDTBLAccessor.update(upTMsg);
                            }
                            // 2023/10/30 QC#62019 K.Ikeda END
                            TRTY_RULETMsg inTMsg = new TRTY_RULETMsg();

                            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                            BigDecimal trtyRulePk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TRTY_RULE_SQ);
                            ZYPEZDItemValueSetter.setValue(inTMsg.trtyRulePk, trtyRulePk);
                            ZYPEZDItemValueSetter.setValue(inTMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
                            ZYPEZDItemValueSetter.setValue(inTMsg.orgCd, afterAttr);
                            // Mod Start 2017/03/15 QC#15878
//                            ZYPEZDItemValueSetter.setValue(inTMsg.trtyRuleTpCd, TRTY_RULE_TP.ACCOUNT_OR_SITE_NUMBER);
                            ZYPEZDItemValueSetter.setValue(inTMsg.trtyRuleTpCd, TRTY_RULE_TP.LOCATION_NUMBER);
                            // Mod End 2017/03/15 QC#15878
                            ZYPEZDItemValueSetter.setValue(inTMsg.trtyRuleFromValTxt, rsSet.getString("LOC_NUM"));
                            ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, this.procDt);
                            ZYPEZDItemValueSetter.setValue(inTMsg.trtyRuleOprdTpCd, TRTY_RULE_OPRD_TP.EQUAL);
                            ZYPEZDItemValueSetter.setValue(inTMsg.trtyRuleLogicTpCd, TRTY_RULE_LOGIC_TP.OR);

                            EZDTBLAccessor.insert(inTMsg);

                            // 2023/06/30 QC#61615 START
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
                            // 2023/06/30 QC#61615 END
                        }
                    }

                    List<String> bizLineList = new ArrayList<String>();
                    for (int i = 0; i < TRTY_SIZE; i++) {

                        if (!ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
                            bizLineList.add("");
                            continue;
                        }

                        // get Territory Attribute
                        Map<String, Object> getLineParam = new HashMap<String, Object>();

                        int no = i + 1;

                        getLineParam.put("glblCmpyCd", this.glblCmpyCd);
                        getLineParam.put("dsAcctTp", acctTpCd);
                        getLineParam.put("asgTrtyAttrb", no);
                        getLineParam.put("trtyGrpTp", afterGrpList.get(i));
                        String lineRank = (String) this.ssmBatchClient.queryObject("getLineBizRankNum", getLineParam);

                        bizLineList.add(lineRank);

                        // New Mode
                        if (!ZYPCommonFunc.hasValue(beforeAttrList.get(i)) && ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
                            Map<String, Object> getAcctForNewModeParam = new HashMap<String, Object>();
                            getAcctForNewModeParam.put("glblCmpyCd", this.glblCmpyCd);
                            if (DS_ACCT_TP.CUSTOMER.equals(acctTpCd)) {
                                getAcctForNewModeParam.put("acctFlag", ZYPConstant.FLG_ON_Y);
                            } else {
                                getAcctForNewModeParam.put("acctFlag", ZYPConstant.FLG_OFF_N);
                                // Add Start 2018/09/11 QC#28073
                                getAcctForNewModeParam.put("rgtnStsCd_P01", RGTN_STS.PENDING_COMPLETION);
                                // Add End 2018/09/11 QC#28073
                            }
                            getAcctForNewModeParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
                            getAcctForNewModeParam.put("locNum", rsSet.getString("LOC_NUM"));
                            getAcctForNewModeParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
                            getAcctForNewModeParam.put("attrbCd", String.valueOf(no));
                            getAcctForNewModeParam.put("trtyGrpTpCd", afterGrpList.get(i));
                            getAcctForNewModeParam.put("rgtnStsCd_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
                            getAcctForNewModeParam.put("slsDt", this.slsDt);
                            getAcctForNewModeParam.put("maxDt", "99991231");
                            getAcctForNewModeParam.put("rowNum_1", 1);
                            getAcctForNewModeParam.put("rowNum_2", 2);
                            getAcctForNewModeParam.put("rowNum_3", 3);
                            getAcctForNewModeParam.put("rowNum_4", 4);
                            getAcctForNewModeParam.put("rowNum_5", 5);
                            Map<String, Object> acctInfoMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getAcctForNewMode", getAcctForNewModeParam);

                            // 2018/06/13 QC#25490 Add Start
                            if (acctInfoMap == null) {
                                setErrorMsg(rsSet, NMAM8683E);
                                isError = true;
                                continue;
                            }
                            // 2018/06/13 QC#25490 Add End

                            // 2016/09/12 CSA-QC#10091 Add Start
                            if (!ZYPCommonFunc.hasValue((String) acctInfoMap.get("LINE_BIZ_ROLE_TP_CD"))) {
                                setErrorMsg(rsSet, NMAM8647E);
                                isError = true;
                                continue;
                            }
                            // 2016/09/12 CSA-QC#10091 Add End

                            // Add Start 2018/09/11 QC#28073
                            if (DS_ACCT_TP.CUSTOMER.equals(acctTpCd)) {
                            // Add End 2018/09/11 QC#28073

                            List<Map<String, Object>> insertRoleMapList = null;
                            if (ZYPCommonFunc.hasValue(lineRank)) {
                                insertRoleMapList = getTrtyInfo(afterAttrList.get(i), true);
                                if (insertRoleMapList.size() > 0) {
                                    ACCT_TRTY_ROLE_ASGTMsg insertTMsg = setInsertMsgForNewMode(acctInfoMap, insertRoleMapList.get(0), rsSet.getString("MAN_ENTRY_FLG"), String.valueOf(no), afterAttrList.get(i));
                                    EZDTBLAccessor.insert(insertTMsg);
                                }
                            } else {
                                insertRoleMapList = getTrtyInfo(afterAttrList.get(i), false);

                                for (Map<String, Object> insertRoleMap : insertRoleMapList) {
                                    ACCT_TRTY_ROLE_ASGTMsg insertTMsg = setInsertMsgForNewMode(acctInfoMap, insertRoleMap, rsSet.getString("MAN_ENTRY_FLG"), String.valueOf(no), afterAttrList.get(i));
                                    EZDTBLAccessor.insert(insertTMsg);
                                }
                            }

                            // Add Start 2018/09/11 QC#28073
                            } else if (DS_ACCT_TP.PROSPECT.equals(acctTpCd)) {
                                List<Map<String, Object>> insertRoleMapList = null;
                                if (ZYPCommonFunc.hasValue(lineRank)) {
                                    insertRoleMapList = getTrtyInfo(afterAttrList.get(i), true);
                                    if (insertRoleMapList.size() > 0) {
                                        PROS_TRTY_ROLE_ASGTMsg insertTMsg = setInsertProsMsgForNewMode(acctInfoMap, insertRoleMapList.get(0), rsSet.getString("MAN_ENTRY_FLG"), String.valueOf(no), afterAttrList.get(i));
                                        EZDTBLAccessor.insert(insertTMsg);
                                    }
                                } else {
                                    insertRoleMapList = getTrtyInfo(afterAttrList.get(i), false);

                                    for (Map<String, Object> insertRoleMap : insertRoleMapList) {
                                        PROS_TRTY_ROLE_ASGTMsg insertTMsg = setInsertProsMsgForNewMode(acctInfoMap, insertRoleMap, rsSet.getString("MAN_ENTRY_FLG"), String.valueOf(no), afterAttrList.get(i));
                                        EZDTBLAccessor.insert(insertTMsg);
                                    }
                                }
                            }
                            // Add End 2018/09/11 QC#28073

                        // Update Mode
                        } else {
                            if (DS_ACCT_TP.CUSTOMER.equals(acctTpCd)) {
                                if (ZYPCommonFunc.hasValue(lineRank)) {
                                    // 3.1.3 Update ACCT_TRTY_ROLE_ASG
                                    // (Cust & SlsRep)
                                        Map<String, Object> getAcctTrtyParam = new HashMap<String, Object>();
                                        getAcctTrtyParam.put("glblCmpyCd", this.glblCmpyCd);
                                        getAcctTrtyParam.put("attrbCd", String.valueOf((i + 1)));
                                        getAcctTrtyParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
                                        getAcctTrtyParam.put("locNum", rsSet.getString("LOC_NUM"));
                                        getAcctTrtyParam.put("orgCd", beforeAttrList.get(i));

                                        Map<String, Object> acctRoleMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getAcctTrtyRoleAgs", getAcctTrtyParam);

                                        if (acctRoleMap == null || acctRoleMap.isEmpty()) {
                                            setErrorMsg(rsSet, NMAM8121E, "ACCT_TRTY_ROLE_ASG");
                                            isError = true;
                                            continue;
                                        }

                                        ACCT_TRTY_ROLE_ASGTMsg inTMsg = new ACCT_TRTY_ROLE_ASGTMsg();
                                        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                                        ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyRoleAsgPk, (BigDecimal) acctRoleMap.get("ACCT_TRTY_ROLE_ASG_PK"));

                                        inTMsg = (ACCT_TRTY_ROLE_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

                                        S21FastTBLAccessor.removeLogical(new ACCT_TRTY_ROLE_ASGTMsg[] {inTMsg });


                                    // DEL and Null Check
                                    if (!ZYPCommonFunc.hasValue(afterAttrList.get(i)) || ORG_NM_DEL.equals(afterAttrList.get(i))) {
                                        continue;
                                    }

                                    // 3.1.4 Insert ACCT_TRTY_ROLE_ASG
                                    // (Cust & SlsRep)
                                    Map<String, Object> getInsertCustParam = new HashMap<String, Object>();
                                    getInsertCustParam.put("glblCmpyCd", this.glblCmpyCd);
                                    getInsertCustParam.put("orgCd", afterAttrList.get(i));
                                    getInsertCustParam.put("isSlsRepFlg", ZYPConstant.FLG_ON_Y);
                                    getInsertCustParam.put("procDt", this.procDt);
                                    getInsertCustParam.put("maxDt", MAX_DT);
                                    getInsertCustParam.put("gnrnTpCd2", GNRN_TP.CURRENT);

                                    Map<String, Object> insertRoleMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getInsertData", getInsertCustParam);

                                    if (insertRoleMap == null || insertRoleMap.isEmpty()) {
                                        setErrorMsg(rsSet, NMAM8121E, "Salesrep");
                                        isError = true;
                                        continue;
                                    }

                                    ACCT_TRTY_ROLE_ASGTMsg insertTMsg = setInsertMsgForCust(acctRoleMap, insertRoleMap, afterAttrList.get(i), rsSet.getString("MAN_ENTRY_FLG"));
                                    EZDTBLAccessor.insert(insertTMsg);

                                } else {
                                    // 3.1.5 Update ACCT_TRTY_ROLE_ASG
                                    // (Cust & Specialist)

                                    Map<String, Object> getAcctTrtyParam = new HashMap<String, Object>();
                                    getAcctTrtyParam.put("glblCmpyCd", this.glblCmpyCd);
                                    getAcctTrtyParam.put("attrbCd", no);
                                    getAcctTrtyParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
                                    getAcctTrtyParam.put("locNum", rsSet.getString("LOC_NUM"));
                                    getAcctTrtyParam.put("orgCd", beforeAttrList.get(i));

                                    Map<String, Object> acctRoleMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getAcctTrtyRoleAgs", getAcctTrtyParam);

                                    if (acctRoleMap == null || acctRoleMap.isEmpty()) {
                                        setErrorMsg(rsSet, NMAM8121E, "ACCT_TRTY_ROLE_ASG");
                                        isError = true;
                                        continue;
                                    }

                                    ACCT_TRTY_ROLE_ASGTMsg inTMsg = new ACCT_TRTY_ROLE_ASGTMsg();
                                    ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                                    ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyRoleAsgPk, (BigDecimal) acctRoleMap.get("ACCT_TRTY_ROLE_ASG_PK"));

                                    inTMsg = (ACCT_TRTY_ROLE_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

                                    if (inTMsg == null) {
                                        setErrorMsg(rsSet, NMAM8121E, "ACCT_TRTY_ROLE_ASG");
                                        isError = true;
                                        continue;
                                    }

                                    S21FastTBLAccessor.removeLogical(new ACCT_TRTY_ROLE_ASGTMsg[] {inTMsg });

                                    // DEL and Null Check
                                    if (!ZYPCommonFunc.hasValue(afterAttrList.get(i)) || ORG_NM_DEL.equals(afterAttrList.get(i))) {
                                        continue;
                                    }

                                    // 3.1.6 Insert ACCT_TRTY_ROLE_ASG
                                    // (Cust & Specialist)
                                    Map<String, Object> getInsertCustParam = new HashMap<String, Object>();
                                    getInsertCustParam.put("glblCmpyCd", this.glblCmpyCd);
                                    getInsertCustParam.put("orgCd", afterAttrList.get(i));
                                    getInsertCustParam.put("isSlsRepFlg", ZYPConstant.FLG_OFF_N);
                                    getInsertCustParam.put("procDt", this.procDt);
                                    getInsertCustParam.put("maxDt", MAX_DT);
                                    getInsertCustParam.put("gnrnTpCd2", GNRN_TP.CURRENT);
                                    getInsertCustParam.put("firstOrgCd", SLS_CD);

                                    Map<String, Object> insertRoleMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getInsertData", getInsertCustParam);

                                    if (insertRoleMap == null || insertRoleMap.isEmpty()) {
                                        setErrorMsg(rsSet, NMAM8121E, "Specialist");
                                        isError = true;
                                        continue;
                                    }

                                    ACCT_TRTY_ROLE_ASGTMsg insertTMsg = setInsertMsgForCust(acctRoleMap, insertRoleMap, afterAttrList.get(i), rsSet.getString("MAN_ENTRY_FLG"));
                                    EZDTBLAccessor.insert(insertTMsg);
                                }
                            } else if (DS_ACCT_TP.PROSPECT.equals(acctTpCd)) {
                                if (ZYPCommonFunc.hasValue(lineRank)) {
                                    // 3.1.7 Update PROS_TRTY_ROLE_ASG
                                    // (Pros & SlsRep)
                                        Map<String, Object> getProcTrtyParam = new HashMap<String, Object>();
                                        getProcTrtyParam.put("glblCmpyCd", this.glblCmpyCd);
                                        getProcTrtyParam.put("attrbCd", no);
                                        getProcTrtyParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
                                        getProcTrtyParam.put("locNum", rsSet.getString("LOC_NUM"));
                                        getProcTrtyParam.put("orgCd", beforeAttrList.get(i));

                                        Map<String, Object> procRoleMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getProcTrtyRoleAgs", getProcTrtyParam);

                                        if (procRoleMap == null || procRoleMap.isEmpty()) {
                                            setErrorMsg(rsSet, NMAM8121E, "PROS_TRTY_ROLE_ASG");
                                            isError = true;
                                            continue;
                                        }

                                        PROS_TRTY_ROLE_ASGTMsg inTMsg = new PROS_TRTY_ROLE_ASGTMsg();
                                        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                                        ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyRoleAsgPk, (BigDecimal) procRoleMap.get("ACCT_TRTY_ROLE_ASG_PK"));

                                        inTMsg = (PROS_TRTY_ROLE_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

                                        S21FastTBLAccessor.removeLogical(new PROS_TRTY_ROLE_ASGTMsg[] {inTMsg });

                                    // DEL and Null Check
                                    if (!ZYPCommonFunc.hasValue(afterAttrList.get(i)) || ORG_NM_DEL.equals(afterAttrList.get(i))) {
                                        continue;
                                    }

                                    // 3.1.8 Insert PROS_TRTY_ROLE_ASG
                                    // (Pros & SlsRep)
                                    Map<String, Object> getInsertCustParam = new HashMap<String, Object>();
                                    getInsertCustParam.put("glblCmpyCd", this.glblCmpyCd);
                                    getInsertCustParam.put("orgCd", afterAttrList.get(i));
                                    getInsertCustParam.put("isSlsRepFlg", ZYPConstant.FLG_ON_Y);
                                    getInsertCustParam.put("procDt", this.procDt);
                                    getInsertCustParam.put("maxDt", MAX_DT);
                                    getInsertCustParam.put("gnrnTpCd2", GNRN_TP.CURRENT);

                                    Map<String, Object> insertRoleMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getInsertData", getInsertCustParam);

                                    if (insertRoleMap == null || insertRoleMap.isEmpty()) {
                                        setErrorMsg(rsSet, NMAM8121E, "Salesrep");
                                        isError = true;
                                        continue;
                                    }

                                    PROS_TRTY_ROLE_ASGTMsg insertTMsg = setInsertMsgForProc(procRoleMap, insertRoleMap, afterAttrList.get(i), rsSet.getString("MAN_ENTRY_FLG"));
                                    EZDTBLAccessor.insert(insertTMsg);

                                } else {
                                    // 3.1.9 Update PROS_TRTY_ROLE_ASG
                                    // (Pros & Specialist)
                                        Map<String, Object> getProcTrtyParam = new HashMap<String, Object>();
                                        getProcTrtyParam.put("glblCmpyCd", this.glblCmpyCd);
                                        getProcTrtyParam.put("attrbCd", no);
                                        getProcTrtyParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
                                        getProcTrtyParam.put("locNum", rsSet.getString("LOC_NUM"));
                                        getProcTrtyParam.put("orgCd", beforeAttrList.get(i));

                                        Map<String, Object> procRoleMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getProcTrtyRoleAgs", getProcTrtyParam);

                                        if (procRoleMap == null || procRoleMap.isEmpty()) {
                                            setErrorMsg(rsSet, NMAM8121E, "PROS_TRTY_ROLE_ASG");
                                            isError = true;
                                            continue;
                                        }

                                        PROS_TRTY_ROLE_ASGTMsg inTMsg = new PROS_TRTY_ROLE_ASGTMsg();
                                        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                                        ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyRoleAsgPk, (BigDecimal) procRoleMap.get("ACCT_TRTY_ROLE_ASG_PK"));

                                        inTMsg = (PROS_TRTY_ROLE_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

                                        S21FastTBLAccessor.removeLogical(new PROS_TRTY_ROLE_ASGTMsg[] {inTMsg });

                                    // DEL and Null Check
                                    if (!ZYPCommonFunc.hasValue(afterAttrList.get(i)) || ORG_NM_DEL.equals(afterAttrList.get(i))) {
                                        continue;
                                    }

                                    // 3.1.10 Insert PROS_TRTY_ROLE_ASG
                                    // (Pros & Specialist)
                                    Map<String, Object> getInsertCustParam = new HashMap<String, Object>();
                                    getInsertCustParam.put("glblCmpyCd", this.glblCmpyCd);
                                    getInsertCustParam.put("orgCd", afterAttrList.get(i));
                                    getInsertCustParam.put("isSlsRepFlg", ZYPConstant.FLG_OFF_N);
                                    getInsertCustParam.put("procDt", this.procDt);
                                    getInsertCustParam.put("maxDt", MAX_DT);
                                    getInsertCustParam.put("gnrnTpCd2", GNRN_TP.CURRENT);
                                    getInsertCustParam.put("firstOrgCd", SLS_CD);

                                    Map<String, Object> insertRoleMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getInsertData", getInsertCustParam);

                                    if (insertRoleMap == null || insertRoleMap.isEmpty()) {
                                        setErrorMsg(rsSet, NMAM8121E, "Specialist");
                                        continue;
                                    }

                                    PROS_TRTY_ROLE_ASGTMsg insertTMsg = setInsertMsgForProc(procRoleMap, insertRoleMap, afterAttrList.get(i), rsSet.getString("MAN_ENTRY_FLG"));
                                    EZDTBLAccessor.insert(insertTMsg);
                                }
                            }
                        }
                    }

                    if (isError) {
                        // update DTL
                        // 2016/09/23 CSA-QC#8156 Mod Start
                        // Mod Start 2019/07/30 QC#52200
                        //updateList.add(createUpdateDetail(rsSet, hdrPk, beforeAttrList, afterAttrList, acctTpCd, rsnCmntTxt, isError));
                        endDetailProcess(rsSet, hdrPk, beforeAttrList, afterAttrList, acctTpCd, rsnCmntTxt, isError);
                        // Mod End 2019/07/30 QC#52200
                        // updateList.add(createUpdateDetail(rsSet, hdrPk, beforeAttrList, afterAttrList, acctTpCd, isError));
                        // 2016/09/23 CSA-QC#8156 Mod End
                        isHeaderError = true;
                        // 2016/10/04 CSA-QC#14526 Add
                        continue;
                    }

                    // 3.1.11 Update ACCT_TRTY_RESRC_ASG
                    //TODO
                    List<String> psnCdList = new ArrayList<String>(TRTY_SIZE);
                    List<String> tocCdList = new ArrayList<String>(TRTY_SIZE);
                    for (int i = 0; i < TRTY_SIZE; i++) {

                        if (ZYPCommonFunc.hasValue(bizLineList.get(i))) {
                            if (ZYPCommonFunc.hasValue(afterAttrList.get(i)) && !ORG_NM_DEL.equals(afterAttrList.get(i))) {
                                Map<String, Object> getInsertCustParam = new HashMap<String, Object>();
                                getInsertCustParam.put("glblCmpyCd", this.glblCmpyCd);
                                getInsertCustParam.put("isSlsRepFlg", ZYPConstant.FLG_ON_Y);
                                getInsertCustParam.put("procDt", this.procDt);
                                getInsertCustParam.put("maxDt", MAX_DT);
                                getInsertCustParam.put("gnrnTpCd2", GNRN_TP.CURRENT);
                                getInsertCustParam.put("orgCd", afterAttrList.get(i));

                                Map<String, Object> insertRoleMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getInsertData", getInsertCustParam);

                                if (insertRoleMap != null && !insertRoleMap.isEmpty()) {
                                    psnCdList.add((String) insertRoleMap.get("PSN_CD"));
                                    tocCdList.add((String) insertRoleMap.get("TOC_CD"));
                                    continue;
                                }
                            }
                        }

                        psnCdList.add("");
                        tocCdList.add("");
                    }

                    // Add Start 2018/09/11 QC#28073
                    // Mod Start 2019/07/30 QC#52200
                    //if (!befManEntryFlg.equals(aftManEntryFlg)) {
                    if (!ZYPCommonFunc.hasValue(befManEntryFlg) || !befManEntryFlg.equals(aftManEntryFlg)) {
                        // Mod End 2019/07/30 QC#52200
                        updateRoleAsgManEntryFlg(rsSet, acctTpCd);
                    }
                    // Add End 2018/09/11 QC#28073

                    if (DS_ACCT_TP.CUSTOMER.equals(acctTpCd)) {
                        ACCT_TRTY_RESRC_ASGTMsg inTMsg = new ACCT_TRTY_RESRC_ASGTMsg();
                        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                        // Del Start 2018/03/23 QC#24442
//                        ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctNm, rsSet.getString("DS_ACCT_NM"));
                        // Del End 2018/03/23 QC#24442
                        ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctNum, rsSet.getString("DS_ACCT_NUM"));
                        ZYPEZDItemValueSetter.setValue(inTMsg.locNum, rsSet.getString("LOC_NUM"));
                        ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctTpCd, acctTpCd);
                        inTMsg = (ACCT_TRTY_RESRC_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

                        if (inTMsg == null) {
                            setErrorMsg(rsSet, NMAM8121E, "ACCT_TRTY_RESRC_ASG");
                            isError = true;
                        } else {
                            setAcctTrtyResrcAsgPrmForCust(inTMsg, afterAttrList, psnCdList, tocCdList, rsSet.getString("MAN_ENTRY_FLG"));
                            EZDTBLAccessor.update(inTMsg);
                        }
                    } else if (DS_ACCT_TP.PROSPECT.equals(acctTpCd)) {
                        PROS_TRTY_RESRC_ASGTMsg inTMsg = new PROS_TRTY_RESRC_ASGTMsg();
                        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                        // Del Start 2018/03/23 QC#24442
//                        ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctNm, rsSet.getString("DS_ACCT_NM"));
                        // Del End 2018/03/23 QC#24442
                        ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctNum, rsSet.getString("DS_ACCT_NUM"));
                        ZYPEZDItemValueSetter.setValue(inTMsg.locNum, rsSet.getString("LOC_NUM"));
                        ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctTpCd, acctTpCd);
                        inTMsg = (PROS_TRTY_RESRC_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

                        if (inTMsg == null) {
                            // Mod Start 2019/07/30 QC#52200
                            //setErrorMsg(rsSet, NMAM8121E, "ACCT_TRTY_RESRC_ASG");
                            setErrorMsg(rsSet, NMAM8121E, "PROS_TRTY_RESRC_ASG");
                            // Mod End 2019/07/30 QC#52200
                            isError = true;
                        } else {
                            setAcctTrtyResrcAsgPrmForPros(inTMsg, afterAttrList, psnCdList, tocCdList, rsSet.getString("MAN_ENTRY_FLG"));
                            EZDTBLAccessor.update(inTMsg);
                        }
                    }
                }

                // update DTL
                // 2016/09/23 CSA-QC#8156 Mod Start
                // Mod Start 2019/07/30 QC#52200
                //updateList.add(createUpdateDetail(rsSet, hdrPk, beforeAttrList, afterAttrList, acctTpCd, rsnCmntTxt, isError));
                endDetailProcess(rsSet, hdrPk, beforeAttrList, afterAttrList, acctTpCd, rsnCmntTxt, isError);
                // Mod End 2019/07/30 QC#52200
                // updateList.add(createUpdateDetail(rsSet, hdrPk, beforeAttrList, afterAttrList, acctTpCd, isError));
                // 2016/09/23 CSA-QC#8156 Mod End
                hasUpdate = true;
                if (isError) {
                    isHeaderError = true;
                }
            }

            if (hasUpdate || isHeaderError) {
                // Mod Start 2019/07/30 QC#52200
                //if (hasUpdate) {
                if (!isHeaderError) {
                    // Mod End 2019/07/30 QC#52200
                    // 2016/09/23 CSA-QC#8156 Mod Start
                    updateHeader(isHeaderError, beforeHdrPk, upldCsvId, fMsg);
                    // updateHeader(isHeaderError, beforeHdrPk, upldCsvId, rsnCmntTxt, fMsg);
                    // 2016/09/23 CSA-QC#8156 Mod End
                } else {
                    // roll back organization master
                    rollback();
                    // 2016/09/23 CSA-QC#8156 Mod Start
                    updateHeader(isHeaderError, beforeHdrPk, upldCsvId, fMsg);
                    // updateHeader(isHeaderError, beforeHdrPk, upldCsvId, rsnCmntTxt, fMsg);
                    // 2016/09/23 CSA-QC#8156 Mod End
                }

                // Del Start 2019/07/30 QC#52200
                //if (RQST_CRAT_SYS_TP.CSV_UPLOAD.equals(this.userConstantVariable)) {
                //    S21FastTBLAccessor.insert(updateList.toArray(new ACCT_TRTY_RESRC_RQST_DTLTMsg[0]));
                //    updateList.clear();
                //} else if (RQST_CRAT_SYS_TP.ONLINE.equals(this.userConstantVariable)) {
                //    S21FastTBLAccessor.update(updateList.toArray(new ACCT_TRTY_RESRC_RQST_DTLTMsg[0]));
                //    updateList.clear();
                //}
                // Del End 2019/07/30 QC#52200
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

    // Add Start 2019/07/30 QC#52200
    /**
     * endDetailProcess
     * @param rsSet ResultSet
     * @param hdrPk BigDecimal
     * @param beforeAttrList List<String>
     * @param afterAttrList List<String>
     * @param acctTpCd String
     * @param rsnCmntTxt String
     * @param isError boolean
     * @throws SQLException
     */
    private void endDetailProcess(ResultSet rsSet, BigDecimal hdrPk, List<String> beforeAttrList, List<String> afterAttrList, //
            String acctTpCd, String rsnCmntTxt, boolean isError) throws SQLException {

        if (isError) {
            rollback();
        }

        createUpdateDetail(rsSet, hdrPk, beforeAttrList, afterAttrList, acctTpCd, rsnCmntTxt, isError);
        commit();
    }
    // Add End 2019/07/30 QC#52200

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

        // UploadCsvRequestPK
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

    // Mod Start 2018/09/11 QC#28073
    ///**
    // * @param afterAttrList List<String>
    // * @param rsSet ResultSet
    // * @return boolean
    // */
    //private boolean isChangeManualEntryFlag(List<String> afterAttrList, ResultSet rsSet) throws SQLException {
    /**
     * @param afterAttrList List<String>
     * @param rsSet ResultSet
     * @return boolean
     */
    private boolean isChangeManualEntryFlag(List<String> afterAttrList, ResultSet rsSet, String beforeManFlg) throws SQLException {
        // Mod End 2018/09/11 QC#28073
        if (afterAttrList != null && !afterAttrList.isEmpty()) {
            for (int i = 0; i < afterAttrList.size(); i++) {
                if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
                    return false;
                }
            }
        }
//        String a= rsSet.getString("MAN_ENTRY_FLG");
        // Del Start 2018/09/11 QC#28073
        //Map<String, Object> ssmParam = new HashMap<String, Object>();
        //ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        //ssmParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
        //ssmParam.put("locNum", rsSet.getString("LOC_NUM"));
        //
        //String beforeManFlg = (String) this.ssmBatchClient.queryObject("getManualFlag", ssmParam);
        // Del End 2018/09/11 QC#28073

        // 2017/10/16 QC#20662 Mod Start
        //if (!beforeManFlg.equals(rsSet.getString("MAN_ENTRY_FLG"))) {
        if (!ZYPCommonFunc.hasValue(beforeManFlg) || !beforeManFlg.equals(rsSet.getString("MAN_ENTRY_FLG"))) {
        // 2017/10/16 QC#20662 Mod End
            return true;
        }
        return false;
    }

    // Add Start 2018/09/11 QC#28073
    /**
     * @param rsSet ResultSet
     * @return String
     */
    private String getManualEntryFlag(ResultSet rsSet) throws SQLException {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
        ssmParam.put("locNum", rsSet.getString("LOC_NUM"));

        String beforeManFlg = (String) this.ssmBatchClient.queryObject("getManualFlag", ssmParam);

        return beforeManFlg;
    }
    // Add End 2018/09/11 QC#28073

    /**
     * @param afterAttrList List<String>
     * @param rsSet ResultSet
     * @param acctTpCd String
     */
    private void updateManualEntryFlag(List<String> afterAttrList, ResultSet rsSet, String acctTpCd) throws SQLException {
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
                inRoleTMsgAry.no(i).manEntryFlg.setValue(rsSet.getString("MAN_ENTRY_FLG"));
                acctTrtyRoleAsgList.add(inRoleTMsgAry.no(i));
            }

            // for DataBase Update
            int updCnt = acctTrtyRoleAsgList.size();
            int rsltCnt = S21FastTBLAccessor.update(acctTrtyRoleAsgList.toArray(new ACCT_TRTY_ROLE_ASGTMsg[updCnt]));
            if (rsltCnt != updCnt) {
                // abnormal end
                //setErrorMsg(rsSet, NMAM8020E, "ACCT_TRTY_ROLE_ASG");
                return;
            }

            // Update ACCT_TRTY_RESRC_ASG
            ACCT_TRTY_RESRC_ASGTMsg inResrcTMsg = new ACCT_TRTY_RESRC_ASGTMsg();
            ZYPEZDItemValueSetter.setValue(inResrcTMsg.glblCmpyCd, this.glblCmpyCd);
            // Del Start 2018/03/23 QC#24442
//            ZYPEZDItemValueSetter.setValue(inResrcTMsg.dsAcctNm, rsSet.getString("DS_ACCT_NM"));
            // Del End 2018/03/23 QC#24442
            ZYPEZDItemValueSetter.setValue(inResrcTMsg.dsAcctNum, rsSet.getString("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(inResrcTMsg.locNum, rsSet.getString("LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(inResrcTMsg.dsAcctTpCd, acctTpCd);
            inResrcTMsg = (ACCT_TRTY_RESRC_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inResrcTMsg);

            ZYPEZDItemValueSetter.setValue(inResrcTMsg.manEntryFlg, rsSet.getString("MAN_ENTRY_FLG"));

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
                inRoleTMsgAry.no(i).manEntryFlg.setValue(rsSet.getString("MAN_ENTRY_FLG"));
                acctTrtyRoleAsgList.add(inRoleTMsgAry.no(i));
            }

            // for DataBase Update
            int updCnt = acctTrtyRoleAsgList.size();
            // Mod Start 2018/03/23 QC#24442
//            int rsltCnt = S21FastTBLAccessor.update(acctTrtyRoleAsgList.toArray(new ACCT_TRTY_ROLE_ASGTMsg[updCnt]));
            int rsltCnt = S21FastTBLAccessor.update(acctTrtyRoleAsgList.toArray(new PROS_TRTY_ROLE_ASGTMsg[updCnt]));
            // Mod End 2018/03/23 QC#24442
            if (rsltCnt != updCnt) {
                // abnormal end
                //setErrorMsg(rsSet, NMAM8020E, "ACCT_TRTY_ROLE_ASG");
                return;
            }

            // Update PROS_TRTY_RESRC_ASG
            PROS_TRTY_RESRC_ASGTMsg inResrcTMsg = new PROS_TRTY_RESRC_ASGTMsg();
            ZYPEZDItemValueSetter.setValue(inResrcTMsg.glblCmpyCd, this.glblCmpyCd);
            // Del Start 2018/03/23 QC#24442
//            ZYPEZDItemValueSetter.setValue(inResrcTMsg.dsAcctNm, rsSet.getString("DS_ACCT_NM"));
            // Del End 2018/03/23 QC#24442
            ZYPEZDItemValueSetter.setValue(inResrcTMsg.dsAcctNum, rsSet.getString("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(inResrcTMsg.locNum, rsSet.getString("LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(inResrcTMsg.dsAcctTpCd, acctTpCd);
            inResrcTMsg = (PROS_TRTY_RESRC_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inResrcTMsg);

            ZYPEZDItemValueSetter.setValue(inResrcTMsg.manEntryFlg, rsSet.getString("MAN_ENTRY_FLG"));

            EZDTBLAccessor.update(inResrcTMsg);
        }
    }

    // Add Start 2018/09/11 QC#28073
    /**
     * @param rsSet ResultSet
     * @param acctTpCd String
     */
    private void updateRoleAsgManEntryFlg(ResultSet rsSet, String acctTpCd) throws SQLException {
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
                inRoleTMsgAry.no(i).manEntryFlg.setValue(rsSet.getString("MAN_ENTRY_FLG"));
                acctTrtyRoleAsgList.add(inRoleTMsgAry.no(i));
            }

            // for DataBase Update
            int updCnt = acctTrtyRoleAsgList.size();
            S21FastTBLAccessor.update(acctTrtyRoleAsgList.toArray(new ACCT_TRTY_ROLE_ASGTMsg[updCnt]));
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
                inRoleTMsgAry.no(i).manEntryFlg.setValue(rsSet.getString("MAN_ENTRY_FLG"));
                acctTrtyRoleAsgList.add(inRoleTMsgAry.no(i));
            }

            // for DataBase Update
            int updCnt = acctTrtyRoleAsgList.size();
            S21FastTBLAccessor.update(acctTrtyRoleAsgList.toArray(new PROS_TRTY_ROLE_ASGTMsg[updCnt]));
        }
    }
    // Add End 2018/09/11 QC#28073

    private boolean validationCheck(ResultSet rsSet, String type, List<String> beforeAttrList, List<String> afterAttrList, boolean hasAfter, boolean isChngManFlg) throws SQLException {
        boolean isError = false;
        // 2019/11/14 QC#54207 Del Start
        // boolean isMandatoryError = false;

        // 2.1 Mandatory Check
        // if (!ZYPCommonFunc.hasValue(rsSet.getString("LOC_NUM"))) {
        //     setErrorMsg(rsSet, ZZZM9025E, "Location");
        //     isMandatoryError = true;
        // }

        // if (!ZYPCommonFunc.hasValue(rsSet.getString("DS_ACCT_NUM"))) {
        //     setErrorMsg(rsSet, ZZZM9025E, "Account Number");
        //     isMandatoryError = true;
        // }

        // if (isMandatoryError) {
        //     return isMandatoryError;
        // }
        // 2019/11/14 QC#54207 Del End

        // 2.2 After Territory Check
        if (!hasAfter && !isChngManFlg) {
            setErrorMsg(rsSet, NMAM8556E);
            isError = true;
        }

        // 2016/06/13 CSA-QC#9800 Delete Start
        // for (int i = 0; i < TRTY_SIZE; i++) {
            // DEL and Null check
            // if (!ZYPCommonFunc.hasValue(afterAttrList.get(i)) || ORG_NM_DEL.equals(afterAttrList.get(i)) || !isChngManFlg) {
            //    continue;
            // }

            // 2016/06/13 CSA-QC#9800 Delete Start
            // if (ZYPCommonFunc.hasValue(afterAttrList.get(i)) && !ZYPCommonFunc.hasValue(beforeAttrList.get(i))) {
            //     setErrorMsg(rsSet, NMAM8561E);
            //     isError = true;
            // }
        // }
        // 2016/06/13 CSA-QC#9800 Delete End

        // 2.3 Duplicate Check
        Map<String, Object> dupChkParam = new HashMap<String, Object>();
        List<BigDecimal> dtlPk = null;
        if (RQST_CRAT_SYS_TP.ONLINE.equals(type)) {
            dupChkParam.put("glblCmpyCd", this.glblCmpyCd);
            dupChkParam.put("rhPk", rsSet.getBigDecimal("ACCT_TRTY_RESRC_RQST_HDR_PK"));
            dupChkParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
            dupChkParam.put("locNum", rsSet.getString("LOC_NUM"));
            dtlPk = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("reqDupChk", dupChkParam);

        } else if (RQST_CRAT_SYS_TP.CSV_UPLOAD.equals(type)) {
            dupChkParam.put("glblCmpyCd", this.glblCmpyCd);
            dupChkParam.put("upldCsvRqstPk", rsSet.getBigDecimal("UPLD_CSV_RQST_PK"));
            dupChkParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
            dupChkParam.put("locNum", rsSet.getString("LOC_NUM"));
            dtlPk = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("csvDupChk", dupChkParam);
        }

        if (dtlPk.size() > 1) {
            setErrorMsg(rsSet, NMAM8404E);
            isError = true;
        }

        // 2.4 Master Check
        // 2.4.1 Acct Check
        Map<String, Object> acctChkParam = new HashMap<String, Object>();
        acctChkParam.put("glblCmpyCd", this.glblCmpyCd);
        acctChkParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
        acctChkParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
        acctChkParam.put("rgtnStsCdP01", RGTN_STS.PENDING_COMPLETION);
        Map<String, String> acctMap = (Map<String, String>) this.ssmBatchClient.queryObject("getAcct", acctChkParam);

        if (acctMap == null) {
            setErrorMsg(rsSet, NMAM8121E, "Account");
            isError = true;
        }

        // 2.4.2 Loc Check
        Map<String, Object> locChkParam = new HashMap<String, Object>();
        locChkParam.put("glblCmpyCd", this.glblCmpyCd);
        locChkParam.put("locNum", rsSet.getString("LOC_NUM"));
        locChkParam.put("procDt", this.procDt);
        locChkParam.put("maxDt", MAX_DT);
        locChkParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
        BigDecimal lokPk = (BigDecimal) this.ssmBatchClient.queryObject("getLoc", locChkParam);

        if (lokPk == null) {
            setErrorMsg(rsSet, NMAM8121E, "Location");
            isError = true;
        }

        // 2.4.3 Acct and Loc Check
        Map<String, Object> acctLocChkParam = new HashMap<String, Object>();
        acctLocChkParam.put("glblCmpyCd", this.glblCmpyCd);
        acctLocChkParam.put("locNum", rsSet.getString("LOC_NUM"));
        acctLocChkParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
        acctLocChkParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
        acctLocChkParam.put("rgtnStsCdP01", RGTN_STS.PENDING_COMPLETION);
        acctLocChkParam.put("procDt", this.procDt);
        acctLocChkParam.put("maxDt", MAX_DT);

        BigDecimal acctLokPk = (BigDecimal) this.ssmBatchClient.queryObject("getAcctLoc", acctLocChkParam);

        if (acctLokPk == null) {
            setErrorMsg(rsSet, NMAM8557E);
            isError = true;
        }

        if (isChngManFlg) {
            return isError;
        }

        // 2.4.4 DS_ORG_UNIT Date Check
        for (int i = 0; i < beforeAttrList.size(); i++) {
            if (!ZYPCommonFunc.hasValue(beforeAttrList.get(i))) {
                continue;
            }

            Map<String, Object> dtChkParam = new HashMap<String, Object>();
            dtChkParam.put("glblCmpyCd", this.glblCmpyCd);
            dtChkParam.put("orgCd", beforeAttrList.get(i));
            dtChkParam.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
            dtChkParam.put("gnrnTpCd2", GNRN_TP.CURRENT);
            dtChkParam.put("gnrnTpCd3", GNRN_TP.FUTURE);
            Map<String, String> trtyDtMap = (Map<String, String>) this.ssmBatchClient.queryObject("getOrgUnitDt", dtChkParam);

            if (trtyDtMap == null) {
                setErrorMsg(rsSet, NMAM8121E, "Territory");
                isError = true;
            }

            if (ZYPDateUtil.compare(trtyDtMap.get("EFF_FROM_DT"), this.procDt) > 0 //
                    || ZYPDateUtil.compare(getMaxDt(trtyDtMap.get("EFF_THRU_DT")), this.procDt) < 0) {
                setErrorMsg(rsSet, NMAM8460E, "Territory");
                isError = true;
            }
        }

        // use check2.7
        ArrayList<String> afterGrpList = new ArrayList<String>(TRTY_SIZE);
        for (String afterAttr : afterAttrList) {

            // DEL and Null check
            if (!ZYPCommonFunc.hasValue(afterAttr) || ORG_NM_DEL.equals(afterAttr)) {
                afterGrpList.add("");
                continue;
            }

            Map<String, Object> dtChkParam = new HashMap<String, Object>();
            dtChkParam.put("glblCmpyCd", this.glblCmpyCd);
            dtChkParam.put("orgCd", afterAttr);
            dtChkParam.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
            dtChkParam.put("gnrnTpCd2", GNRN_TP.CURRENT);
            dtChkParam.put("gnrnTpCd3", GNRN_TP.FUTURE);
            Map<String, String> trtyMap = (Map<String, String>) this.ssmBatchClient.queryObject("getOrgUnitDt", dtChkParam);

            if (trtyMap == null) {
                setErrorMsg(rsSet, NMAM8121E, "Territory");
                isError = true;
            }

            if (ZYPDateUtil.compare(trtyMap.get("EFF_FROM_DT"), this.procDt) > 0 // 
                    || ZYPDateUtil.compare(getMaxDt(trtyMap.get("EFF_THRU_DT")), this.procDt) < 0) {
                setErrorMsg(rsSet, NMAM8460E, "Territory");
                isError = true;
            }

            afterGrpList.add(trtyMap.get("TRTY_GRP_TP_CD"));
        }

        // 2.5 Before and After Check
        boolean hasBeforeAttr = false;
        List<Integer> changeList = new ArrayList<Integer>(TRTY_SIZE);
        for (int i = 0; i < afterAttrList.size(); i++) {
            if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
                if (!afterAttrList.get(i).equals(beforeAttrList.get(i))) {
                    changeList.add(i);
                }

                // 2016/06/22 CSA-QC#10638 Mod Start
                if (ZYPCommonFunc.hasValue(beforeAttrList.get(i))) {
                    hasBeforeAttr = true;

                    // Add Start 2019/07/19 QC#51785
                    if (null == acctMap) {
                        continue;
                    }
                    // Add End 2019/07/19 QC#51785

                    // 2.4.6 ACCT_TRTY_ROLE_ASG/PROS_TRTY_ROLE_ASG Check
                    Map<String, Object> trtyRoleParam = new HashMap<String, Object>();
                    BigDecimal trtyRolePk = null;
                    trtyRoleParam.put("glblCmpyCd", this.glblCmpyCd);
                    trtyRoleParam.put("locNum", rsSet.getString("LOC_NUM"));
                    trtyRoleParam.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
                    trtyRoleParam.put("orgCd", beforeAttrList.get(i));

                    if (DS_ACCT_TP.CUSTOMER.equals(acctMap.get("DS_ACCT_TP_CD"))) {
                        trtyRolePk = (BigDecimal) this.ssmBatchClient.queryObject("getAcctTrtyRole", trtyRoleParam);
                    } else if (DS_ACCT_TP.PROSPECT.equals(acctMap.get("DS_ACCT_TP_CD"))) {
                        trtyRolePk = (BigDecimal) this.ssmBatchClient.queryObject("getProsTrtyRole", trtyRoleParam);
                    }

                    if (trtyRolePk == null) {
                        setErrorMsg(rsSet, NMAM8121E, "Territory visibility information");
                        isError = true;
                    }
                }
            }
        }

        if (hasBeforeAttr && !isError) {
            // 2.4.5 ACCT_TRTY_RESRC_ASG/PROS_TRTY_RESRC_ASG Check
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

            if (trty == null) {
                setErrorMsg(rsSet, NMAM8121E, "Territory visibility information");
                isError = true;
            }
        }
        // 2016/06/22 CSA-QC#10638 Mod End

        if (changeList.size() <= 0) {
            setErrorMsg(rsSet, NMAM8407E);
            isError = true;
        }

        // 2.6 EZUPTIME Check
        if (RQST_CRAT_SYS_TP.ONLINE.equals(type)) {
            ACCT_TRTY_RESRC_RQST_HDRTMsg inTMsg = new ACCT_TRTY_RESRC_RQST_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyResrcRqstHdrPk, rsSet.getBigDecimal("ACCT_TRTY_RESRC_RQST_HDR_PK"));
            inTMsg = (ACCT_TRTY_RESRC_RQST_HDRTMsg) EZDTBLAccessor.findByKey(inTMsg);
            if (inTMsg == null || !inTMsg.ezUpTime.getValue().equals(rsSet.getString("EZUPTIME"))) {
                setErrorMsg(rsSet, NMAM8562E);
                isError = true;
            }
        } else if (RQST_CRAT_SYS_TP.CSV_UPLOAD.equals(type)) {
            ACCT_TRTY_RESRC_ASG_WRKTMsg inTMsg = new ACCT_TRTY_RESRC_ASG_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.upldCsvRqstPk, rsSet.getBigDecimal("UPLD_CSV_RQST_PK"));
            ZYPEZDItemValueSetter.setValue(inTMsg.upldCsvRqstRowNum, rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"));
            inTMsg = (ACCT_TRTY_RESRC_ASG_WRKTMsg) EZDTBLAccessor.findByKey(inTMsg);
            if (inTMsg == null || !inTMsg.ezUpTime.getValue().equals(rsSet.getString("EZUPTIME"))) {
                setErrorMsg(rsSet, NMAM8562E);
                isError = true;
            }

        }

        // 2.7 Territory and Resource Check
        for (int changeNo : changeList) {
            if (ORG_NM_DEL.equals(afterAttrList.get(changeNo))) {
                continue;
            }
            // Add Start 2019/07/19 QC#51785
            if (null == acctMap) {
                continue;
            }
            // Add End 2019/07/19 QC#51785
            Map<String, Object> getLineParam = new HashMap<String, Object>();
            int no = changeNo + 1;
            getLineParam.put("glblCmpyCd", this.glblCmpyCd);
            getLineParam.put("dsAcctTp", acctMap.get("DS_ACCT_TP_CD"));
            getLineParam.put("asgTrtyAttrb", no);
            getLineParam.put("trtyGrpTp", afterGrpList.get(changeNo));
            String lineRank = (String) this.ssmBatchClient.queryObject("getLineBizRankNum", getLineParam);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("glblCmpyCd", getGlobalCompanyCode());
            params.put("trtyCd", afterAttrList.get(changeNo));
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
                } else {
                    setErrorMsg(rsSet, NMAM8546E);
                    isError = true;
                }
            }
        }

        return isError;
    }

    private void setErrorMsg(ResultSet rsSet, String msgId, String msgStr) throws SQLException {

        // if (RQST_CRAT_SYS_TP.ONLINE.equals(this.userConstantVariable)) {
            String msgTxt = S21MessageFunc.clspGetMessage(msgId, new String[] {msgStr });
            if (this.onlineMsg.length() > 0) {
                this.onlineMsg.append(",");
            }
            this.onlineMsg.append(msgTxt);

        if (RQST_CRAT_SYS_TP.CSV_UPLOAD.equals(this.userConstantVariable)) {
            messenger.addMessageForRecord(rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"), msgId, msgStr);
        }
    }

    private void setErrorMsg(ResultSet rsSet, String msgId) throws SQLException {

        // if (RQST_CRAT_SYS_TP.ONLINE.equals(this.userConstantVariable)) {
            String msgTxt = S21MessageFunc.clspGetMessage(msgId);
            if (this.onlineMsg.length() > 0) {
                this.onlineMsg.append(",");
            }
            this.onlineMsg.append(msgTxt);

        if (RQST_CRAT_SYS_TP.CSV_UPLOAD.equals(this.userConstantVariable)) {
            messenger.addMessageForRecord(rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"), msgId, null);
        }
    }

    // QC#11727
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

    private ACCT_TRTY_ROLE_ASGTMsg setInsertMsgForNewMode(Map<String, Object> acctMap, Map<String, Object> insertRoleMap, String manEntryFlg, String attrbCd, String orgCd) {
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
        ZYPEZDItemValueSetter.setValue(insertTMsg.manEntryFlg, manEntryFlg);
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

    // Add Start 2018/09/11 QC#28073
    private PROS_TRTY_ROLE_ASGTMsg setInsertProsMsgForNewMode(Map<String, Object> acctMap, Map<String, Object> insertRoleMap, String manEntryFlg, String attrbCd, String orgCd) {
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
        ZYPEZDItemValueSetter.setValue(insertTMsg.manEntryFlg, manEntryFlg);
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
    // Add End 2018/09/11 QC#28073

    private ACCT_TRTY_ROLE_ASGTMsg setInsertMsgForCust(Map<String, Object> acctRoleMap, Map<String, Object> insertRoleMap, String orgCd, String manEntryFlg) {
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
        ZYPEZDItemValueSetter.setValue(insertTMsg.manEntryFlg, manEntryFlg);
        ZYPEZDItemValueSetter.setValue(insertTMsg.trtyTpCd, (String) insertRoleMap.get("TRTY_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.nonSlsRepFlg, (String) insertRoleMap.get("NON_SLS_REP_FLG"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.asgTrtyAttrbCd, (String) acctRoleMap.get("ASG_TRTY_ATTRB_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizTpCd, (String) acctRoleMap.get("LINE_BIZ_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsCustSicCd, (String) acctRoleMap.get("DS_CUST_SIC_CD"));

        return insertTMsg;
    }

    private PROS_TRTY_ROLE_ASGTMsg setInsertMsgForProc(Map<String, Object> procRoleMap, Map<String, Object> insertRoleMap, String orgCd, String manEntryFlg) {
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
        ZYPEZDItemValueSetter.setValue(insertTMsg.manEntryFlg, manEntryFlg);
        ZYPEZDItemValueSetter.setValue(insertTMsg.trtyTpCd, (String) insertRoleMap.get("TRTY_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.nonSlsRepFlg, (String) insertRoleMap.get("NON_SLS_REP_FLG"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.asgTrtyAttrbCd, (String) procRoleMap.get("ASG_TRTY_ATTRB_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizTpCd, (String) procRoleMap.get("LINE_BIZ_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsCustSicCd, (String) procRoleMap.get("DS_CUST_SIC_CD"));

        return insertTMsg;
    }

    private void setAcctTrtyResrcAsgPrmForCust(ACCT_TRTY_RESRC_ASGTMsg inTMsg, List<String> afterAttrList, List<String> psnCdList, List<String> tocCdList, String manEntryFlg) {
        int i = 0;
        // 2016/09/07 CSA-QC#12229 Mod Start
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_01.clear();
            inTMsg.acctTrtyPsnCd_01.clear();
            inTMsg.acctTrtyTocCd_01.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_01, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_01, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_01, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_02.clear();
            inTMsg.acctTrtyPsnCd_02.clear();
            inTMsg.acctTrtyTocCd_02.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_02, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_02, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_02, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_03.clear();
            inTMsg.acctTrtyPsnCd_03.clear();
            inTMsg.acctTrtyTocCd_03.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_03, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_03, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_03, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_04.clear();
            inTMsg.acctTrtyPsnCd_04.clear();
            inTMsg.acctTrtyTocCd_04.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_04, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_04, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_04, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_05.clear();
            inTMsg.acctTrtyPsnCd_05.clear();
            inTMsg.acctTrtyTocCd_05.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_05, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_05, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_05, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_06.clear();
            inTMsg.acctTrtyPsnCd_06.clear();
            inTMsg.acctTrtyTocCd_06.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_06, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_06, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_06, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_07.clear();
            inTMsg.acctTrtyPsnCd_07.clear();
            inTMsg.acctTrtyTocCd_07.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_07, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_07, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_07, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_08.clear();
            inTMsg.acctTrtyPsnCd_08.clear();
            inTMsg.acctTrtyTocCd_08.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_08, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_08, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_08, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_09.clear();
            inTMsg.acctTrtyPsnCd_09.clear();
            inTMsg.acctTrtyTocCd_09.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_09, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_09, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_09, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_10.clear();
            inTMsg.acctTrtyPsnCd_10.clear();
            inTMsg.acctTrtyTocCd_10.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_10, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_10, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_10, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_11.clear();
            inTMsg.acctTrtyPsnCd_11.clear();
            inTMsg.acctTrtyTocCd_11.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_11, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_11, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_11, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_12.clear();
            inTMsg.acctTrtyPsnCd_12.clear();
            inTMsg.acctTrtyTocCd_12.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_12, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_12, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_12, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_13.clear();
            inTMsg.acctTrtyPsnCd_13.clear();
            inTMsg.acctTrtyTocCd_13.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_13, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_13, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_13, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_14.clear();
            inTMsg.acctTrtyPsnCd_14.clear();
            inTMsg.acctTrtyTocCd_14.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_14, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_14, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_14, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_15.clear();
            inTMsg.acctTrtyPsnCd_15.clear();
            inTMsg.acctTrtyTocCd_15.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_15, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_15, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_15, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_16.clear();
            inTMsg.acctTrtyPsnCd_16.clear();
            inTMsg.acctTrtyTocCd_16.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_16, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_16, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_16, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_17.clear();
            inTMsg.acctTrtyPsnCd_17.clear();
            inTMsg.acctTrtyTocCd_17.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_17, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_17, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_17, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_18.clear();
            inTMsg.acctTrtyPsnCd_18.clear();
            inTMsg.acctTrtyTocCd_18.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_18, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_18, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_18, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_19.clear();
            inTMsg.acctTrtyPsnCd_19.clear();
            inTMsg.acctTrtyTocCd_19.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_19, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_19, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_19, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_20.clear();
            inTMsg.acctTrtyPsnCd_20.clear();
            inTMsg.acctTrtyTocCd_20.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_20, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_20, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_20, tocCdList.get(i));
        }
        // 2016/09/07 CSA-QC#12229 Mod End

        ZYPEZDItemValueSetter.setValue(inTMsg.manEntryFlg, manEntryFlg);
    }

    private void setAcctTrtyResrcAsgPrmForPros(PROS_TRTY_RESRC_ASGTMsg inTMsg, List<String> afterAttrList, List<String> psnCdList, List<String> tocCdList, String manEntryFlg) {
        int i = 0;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_01.clear();
            inTMsg.acctTrtyPsnCd_01.clear();
            inTMsg.acctTrtyTocCd_01.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_01, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_01, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_01, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_02.clear();
            inTMsg.acctTrtyPsnCd_02.clear();
            inTMsg.acctTrtyTocCd_02.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_02, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_02, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_02, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_03.clear();
            inTMsg.acctTrtyPsnCd_03.clear();
            inTMsg.acctTrtyTocCd_03.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_03, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_03, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_03, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_04.clear();
            inTMsg.acctTrtyPsnCd_04.clear();
            inTMsg.acctTrtyTocCd_04.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_04, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_04, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_04, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_05.clear();
            inTMsg.acctTrtyPsnCd_05.clear();
            inTMsg.acctTrtyTocCd_05.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_05, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_05, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_05, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_06.clear();
            inTMsg.acctTrtyPsnCd_06.clear();
            inTMsg.acctTrtyTocCd_06.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_06, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_06, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_06, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_07.clear();
            inTMsg.acctTrtyPsnCd_07.clear();
            inTMsg.acctTrtyTocCd_07.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_07, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_07, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_07, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_08.clear();
            inTMsg.acctTrtyPsnCd_08.clear();
            inTMsg.acctTrtyTocCd_08.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_08, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_08, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_08, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_09.clear();
            inTMsg.acctTrtyPsnCd_09.clear();
            inTMsg.acctTrtyTocCd_09.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_09, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_09, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_09, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_10.clear();
            inTMsg.acctTrtyPsnCd_10.clear();
            inTMsg.acctTrtyTocCd_10.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_10, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_10, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_10, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_11.clear();
            inTMsg.acctTrtyPsnCd_11.clear();
            inTMsg.acctTrtyTocCd_11.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_11, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_11, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_11, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_12.clear();
            inTMsg.acctTrtyPsnCd_12.clear();
            inTMsg.acctTrtyTocCd_12.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_12, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_12, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_12, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_13.clear();
            inTMsg.acctTrtyPsnCd_13.clear();
            inTMsg.acctTrtyTocCd_13.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_13, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_13, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_13, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_14.clear();
            inTMsg.acctTrtyPsnCd_14.clear();
            inTMsg.acctTrtyTocCd_14.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_14, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_14, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_14, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_15.clear();
            inTMsg.acctTrtyPsnCd_15.clear();
            inTMsg.acctTrtyTocCd_15.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_15, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_15, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_15, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_16.clear();
            inTMsg.acctTrtyPsnCd_16.clear();
            inTMsg.acctTrtyTocCd_16.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_16, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_16, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_16, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_17.clear();
            inTMsg.acctTrtyPsnCd_17.clear();
            inTMsg.acctTrtyTocCd_17.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_17, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_17, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_17, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_18.clear();
            inTMsg.acctTrtyPsnCd_18.clear();
            inTMsg.acctTrtyTocCd_18.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_18, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_18, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_18, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_19.clear();
            inTMsg.acctTrtyPsnCd_19.clear();
            inTMsg.acctTrtyTocCd_19.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_19, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_19, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_19, tocCdList.get(i));
        }
        i++;
        if (ORG_NM_DEL.equals(afterAttrList.get(i))) {
            inTMsg.acctTrtyOrgCd_20.clear();
            inTMsg.acctTrtyPsnCd_20.clear();
            inTMsg.acctTrtyTocCd_20.clear();
        } else if (ZYPCommonFunc.hasValue(afterAttrList.get(i))) {
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyOrgCd_20, afterAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyPsnCd_20, psnCdList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyTocCd_20, tocCdList.get(i));
        }

        ZYPEZDItemValueSetter.setValue(inTMsg.manEntryFlg, manEntryFlg);
    }

    // 2016/09/23 CSA-QC#8156 Mod Start
    private void updateHeader(boolean isError, BigDecimal hdrPk, String upldCsvId, ART10FMsg reqMsg) throws SQLException {
    // private void updateHeader(boolean isError, BigDecimal hdrPk, String upldCsvId, String rsnCmntTxt, ART10FMsg reqMsg) throws SQLException {
    // 2016/09/23 CSA-QC#8156 Mod End
        String msg = null;

        if (RQST_CRAT_SYS_TP.ONLINE.equals(this.userConstantVariable)) {
            ACCT_TRTY_RESRC_RQST_HDRTMsg inTMsg = new ACCT_TRTY_RESRC_RQST_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyResrcRqstHdrPk, hdrPk);

            inTMsg = (ACCT_TRTY_RESRC_RQST_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

            if (!isError) {
                ZYPEZDItemValueSetter.setValue(inTMsg.rqstRsltTpCd, RQST_RSLT_TP.COMPLETED);
                msg = createResultMessageArg(0, 1, 0);
                ZYPEZDItemValueSetter.setValue(inTMsg.rqstRsltCmntTxt, createResultMsg(NYXM0001I, msg));
            } else {
                ZYPEZDItemValueSetter.setValue(inTMsg.rqstRsltTpCd, RQST_RSLT_TP.COMPLETED_INCLUDING_ERROR);
                msg = createResultMessageArg(0, 0, 1);
                ZYPEZDItemValueSetter.setValue(inTMsg.rqstRsltCmntTxt, createResultMsg(NYXM0002E, msg));
            }

            EZDTBLAccessor.update(inTMsg);

        } else if (RQST_CRAT_SYS_TP.CSV_UPLOAD.equals(this.userConstantVariable)) {
            ACCT_TRTY_RESRC_RQST_HDRTMsg inTMsg = new ACCT_TRTY_RESRC_RQST_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyResrcRqstHdrPk, hdrPk);
            ZYPEZDItemValueSetter.setValue(inTMsg.rqstUsrId, upldCsvId);
            ZYPEZDItemValueSetter.setValue(inTMsg.rqstCratSysTpCd, RQST_CRAT_SYS_TP.CSV_UPLOAD);
            ZYPEZDItemValueSetter.setValue(inTMsg.rqstCratTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));

            if (!isError) {
                // START 2016/06/24 [QC#8156,ADD]
                msg = createResultMessageArg(0, this.currentUpdateCount, 0);
                this.messenger.addMessageForFile(NYXM0001I, msg);
                this.messenger.uploadMessage();
                // END 2016/06/24 [QC#8156,ADD]
                ZYPEZDItemValueSetter.setValue(inTMsg.rqstRsltTpCd, RQST_RSLT_TP.COMPLETED);
                ZYPEZDItemValueSetter.setValue(inTMsg.rqstRsltCmntTxt, createResultMsg(NYXM0001I, msg));

                this.batchHelper.updateProcessStatus(reqMsg, REQUEST_STATUS.NOMAL_END);
            } else {
                ZYPEZDItemValueSetter.setValue(inTMsg.rqstRsltTpCd, RQST_RSLT_TP.COMPLETED_INCLUDING_ERROR);
                // START 2016/06/24 [QC#8156,MOD]
                // this.messenger.addMessageForFile(NMAM8457E, null);
                msg = createResultMessageArg(0, this.currentUpdateCount, this.currentErrorCount);
                this.messenger.addMessageForFile(NYXM0002E, msg);
                ZYPEZDItemValueSetter.setValue(inTMsg.rqstRsltCmntTxt, createResultMsg(NYXM0002E, msg));
                // END 2016/06/24 [QC#8156,MOD]

                this.termCd = TERM_CD.WARNING_END;
                this.messenger.uploadMessage();
                this.batchHelper.updateProcessStatus(reqMsg, REQUEST_STATUS.WARING_END);
            }

            // START 2016/07/01 [QC#8156,ADD]
            this.currentUpdateCount = 0;
            this.currentErrorCount = 0;
            // END 2016/07/01 [QC#8156,ADD]

            // 2016/09/16 CSA-QC#8156 Delete Start
            // ZYPEZDItemValueSetter.setValue(inTMsg.rqstRsltCmntTxt, rsnCmntTxt);
            EZDTBLAccessor.insert(inTMsg);
        }
    }

    private static String createResultMsg(String msgId, String msgTxt) {
        StringBuilder sb = new StringBuilder();

        sb.append(msgId);
        // mod start 2016/10/19 CSA QC#8156
        sb.append(" ");
        // mod end 2016/10/19 CSA QC#8156
        sb.append(msgTxt);

        return sb.toString();
    }

    // 2016/09/23 CSA-QC#8156 Mod Start
    private ACCT_TRTY_RESRC_RQST_DTLTMsg createUpdateDetail(ResultSet rsSet, BigDecimal hdrPk, List<String> beforeAttrList, List<String> afterAttrList, String acctTpCd, String rsnCmntTxt, boolean isError) throws SQLException {
    // private ACCT_TRTY_RESRC_RQST_DTLTMsg createUpdateDetail(ResultSet rsSet, BigDecimal hdrPk, List<String> beforeAttrList, List<String> afterAttrList, String acctTpCd, boolean isError) throws SQLException {
    // 2016/09/23 CSA-QC#8156 Mod End
        if (RQST_CRAT_SYS_TP.ONLINE.equals(this.userConstantVariable)) {
            ACCT_TRTY_RESRC_RQST_DTLTMsg inTMsg = new ACCT_TRTY_RESRC_RQST_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyResrcRqstHdrPk, hdrPk);
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyResrcRqstDtlPk, rsSet.getBigDecimal("ACCT_TRTY_RESRC_RQST_DTL_PK"));

            inTMsg = (ACCT_TRTY_RESRC_RQST_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

            if (this.onlineMsg.length() <= 0) {
                ZYPEZDItemValueSetter.setValue(inTMsg.rqstDtlRsltTpCd, RQST_DTL_RSLT_TP.SUCCESS);
                this.totalNmlCount++;

                // QC#11727
                if (this.onlineInfoMsg.length() > 0) {
                    ZYPEZDItemValueSetter.setValue(inTMsg.rqstDtlRsltCmntTxt, this.onlineInfoMsg.toString());
                }
            } else {
                ZYPEZDItemValueSetter.setValue(inTMsg.rqstDtlRsltTpCd, RQST_DTL_RSLT_TP.ERROR);
                ZYPEZDItemValueSetter.setValue(inTMsg.rqstDtlRsltCmntTxt, this.onlineMsg.toString());
                this.totalErrCount++;
                this.termCd = TERM_CD.WARNING_END;
            }

            EZDTBLAccessor.update(inTMsg);

            this.onlineMsg.setLength(0);
            // QC#11727
            this.onlineInfoMsg.setLength(0);
            return inTMsg;

        } else if (RQST_CRAT_SYS_TP.CSV_UPLOAD.equals(this.userConstantVariable)) {
            ACCT_TRTY_RESRC_RQST_DTLTMsg inTMsg = new ACCT_TRTY_RESRC_RQST_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
            BigDecimal dtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ACCT_TRTY_RESRC_RQST_DTL_SQ);

            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyResrcRqstDtlPk, dtlPk);
            ZYPEZDItemValueSetter.setValue(inTMsg.acctTrtyResrcRqstHdrPk, hdrPk);
            ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctNum, rsSet.getString("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctNm, rsSet.getString("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctTpCd, acctTpCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.locNum, rsSet.getString("LOC_NUM"));

            // 2016/06/22 CSA-QC#10624 Add Start
            // 2017/10/13 CSA-QC#20662 Add Start
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
            // 2017/10/13 CSA-QC#20662 Add End
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
            // 2016/06/22 CSA-QC#10624 Add End
            // 2016/06/22 CSA-QC#10624 Delete Start
            // ZYPEZDItemValueSetter.setValue(inTMsg.firstLineAddr, rsSet.getString("ACCT_TRTY_RESRC_ADDR"));
            // ZYPEZDItemValueSetter.setValue(inTMsg.ctyAddr, rsSet.getString("CTY_ADDR"));
            // ZYPEZDItemValueSetter.setValue(inTMsg.postCd, rsSet.getString("POST_CD"));
            // ZYPEZDItemValueSetter.setValue(inTMsg.stCd, rsSet.getString("ST_CD"));
            // 2016/06/22 CSA-QC#10624 Delete End

            int i = 0;
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_01, beforeAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_01, afterAttrList.get(i));
            i++;
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_02, beforeAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_02, afterAttrList.get(i));
            i++;
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_03, beforeAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_03, afterAttrList.get(i));
            i++;
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_04, beforeAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_04, afterAttrList.get(i));
            i++;
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_05, beforeAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_05, afterAttrList.get(i));
            i++;
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_06, beforeAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_06, afterAttrList.get(i));
            i++;
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_07, beforeAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_07, afterAttrList.get(i));
            i++;
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_08, beforeAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_08, afterAttrList.get(i));
            i++;
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_09, beforeAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_09, afterAttrList.get(i));
            i++;
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_10, beforeAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_10, afterAttrList.get(i));
            i++;
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_11, beforeAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_11, afterAttrList.get(i));
            i++;
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_12, beforeAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_12, afterAttrList.get(i));
            i++;
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_13, beforeAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_13, afterAttrList.get(i));
            i++;
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_14, beforeAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_14, afterAttrList.get(i));
            i++;
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_15, beforeAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_15, afterAttrList.get(i));
            i++;
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_16, beforeAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_16, afterAttrList.get(i));
            i++;
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_17, beforeAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_17, afterAttrList.get(i));
            i++;
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_18, beforeAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_18, afterAttrList.get(i));
            i++;
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_19, beforeAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_19, afterAttrList.get(i));
            i++;
            ZYPEZDItemValueSetter.setValue(inTMsg.befAcctTrtyOrgCd_20, beforeAttrList.get(i));
            ZYPEZDItemValueSetter.setValue(inTMsg.aftAcctTrtyOrgCd_20, afterAttrList.get(i));

            ZYPEZDItemValueSetter.setValue(inTMsg.manEntryFlg, rsSet.getString("MAN_ENTRY_FLG"));
            // 2016/09/23 CSA-QC#8156 Add Start
            ZYPEZDItemValueSetter.setValue(inTMsg.massUpdRsnCmntTxt, rsnCmntTxt);

            if (isError) {
                ZYPEZDItemValueSetter.setValue(inTMsg.rqstDtlRsltTpCd, RQST_DTL_RSLT_TP.ERROR);
                ZYPEZDItemValueSetter.setValue(inTMsg.rqstDtlRsltCmntTxt, this.onlineMsg.toString());
                this.totalErrCount++;
                // START 2016/07/01 [QC#8156,ADD]
                this.currentErrorCount++;
                // END 2016/07/01 [QC#8156,ADD]
                this.termCd = TERM_CD.WARNING_END;
            } else {
                ZYPEZDItemValueSetter.setValue(inTMsg.rqstDtlRsltTpCd, RQST_DTL_RSLT_TP.SUCCESS);
                this.totalNmlCount++;
                // START 2016/07/01 [QC#8156,ADD]
                this.currentUpdateCount++;
                // END 2016/07/01 [QC#8156,ADD]
                // QC#11727
                if (this.onlineInfoMsg.length() > 0) {
                    ZYPEZDItemValueSetter.setValue(inTMsg.rqstDtlRsltCmntTxt, this.onlineInfoMsg.toString());
                }
            }

            EZDTBLAccessor.insert(inTMsg);

            this.onlineMsg.setLength(0);
            // QC#11727
            this.onlineInfoMsg.setLength(0);
            return inTMsg;
        }
        return null;
    }

    private String getMaxDt(String dt) {
        if (!ZYPCommonFunc.hasValue(dt)) {
            return MAX_DT;
        }
        return dt;
    }
    // START 2016/06/24 [QC#8156,ADD]
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
    // END 2016/06/24 [QC#8156,ADD]

    // QC#11727
    private boolean existsTerritoryRule(String locNum, String orgCd, String orgStruTpCd) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        // Mod Start 2017/03/15 QC#15878
//        param.put("trtyRuleTpCd", TRTY_RULE_TP.ACCOUNT_OR_SITE_NUMBER);
        param.put("trtyRuleTpCd", TRTY_RULE_TP.LOCATION_NUMBER);
        // Mod End 2017/03/15 QC#15878
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
        // Mod Start 2017/03/15 QC#15878
//        param.put("trtyRuleTpCd", TRTY_RULE_TP.ACCOUNT_OR_SITE_NUMBER);
        param.put("trtyRuleTpCd", TRTY_RULE_TP.LOCATION_NUMBER);
        // Mod End 2017/03/15 QC#15878
        param.put("trtyRuleLogicTpCd", TRTY_RULE_LOGIC_TP.OR);
        Integer cnt = (Integer) this.ssmBatchClient.queryObject("getCountTerritoryRuleLogicNotOR", param);
        if (cnt == null) {
            return false;
        }
        return cnt > 0;
    }

    // START 2017/10/13 J.Kim [QC#21299,ADD]
    /**
     * check Before Organization Code
     * @throws SQLException
     */
    // Mod Start 2018/09/11 QC#28073
    //private boolean checkOrgCd(ResultSet rsSet, String orgCd) throws SQLException {
    private boolean checkOrgCd(ResultSet rsSet, String orgCd, String acctTpCd) throws SQLException {
        // Mod End 2018/09/11 QC#28073

        // Add Start 2018/09/11 QC#28073
        if (DS_ACCT_TP.CUSTOMER.equals(acctTpCd)) {
        // Add End 2018/09/11 QC#28073

        ACCT_TRTY_ROLE_ASGTMsg acctTrtyRoleAsgTMsg = new ACCT_TRTY_ROLE_ASGTMsg();
        acctTrtyRoleAsgTMsg.setSQLID("005");
        acctTrtyRoleAsgTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        acctTrtyRoleAsgTMsg.setConditionValue("dsAcctNum01", rsSet.getString("DS_ACCT_NUM"));
        acctTrtyRoleAsgTMsg.setConditionValue("locNum01", rsSet.getString("LOC_NUM"));
        acctTrtyRoleAsgTMsg.setConditionValue("orgCd01", orgCd);
        acctTrtyRoleAsgTMsg.setConditionValue("dsAcctNm01", rsSet.getString("DS_ACCT_NM"));
        ACCT_TRTY_ROLE_ASGTMsgArray acctTrtyRoleAsgTmsg = (ACCT_TRTY_ROLE_ASGTMsgArray) EZDTBLAccessor.findByCondition(acctTrtyRoleAsgTMsg);
        if (acctTrtyRoleAsgTmsg == null || acctTrtyRoleAsgTmsg.getValidCount() == 0) {
            return false;
        }

        // Add Start 2018/09/11 QC#28073
        } else if (DS_ACCT_TP.PROSPECT.equals(acctTpCd)) {
            Map<String, String> param = new HashMap<String, String>();

            param.put("glblCmpyCd", this.glblCmpyCd);
            param.put("dsAcctNum", rsSet.getString("DS_ACCT_NUM"));
            param.put("locNum", rsSet.getString("LOC_NUM"));
            param.put("orgCd", orgCd);
            param.put("dsAcctNm", rsSet.getString("DS_ACCT_NM"));

            Integer count = (Integer) this.ssmBatchClient.queryObject("countProsTrtyRoleAsg", param);

            if (count.intValue() == 0) {
                return false;
            }
        }
        // Add End 2018/09/11 QC#28073

        return true;
    }
    // END 2017/10/13 J.Kim [QC#21299,ADD]
    // 2018/03/26 QC#23157 Add Start
    private boolean isGeoTerritory(String afterAttr) {

        DS_ORG_UNITTMsg orgUnitTMsg = new DS_ORG_UNITTMsg();
        ZYPEZDItemValueSetter.setValue(orgUnitTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(orgUnitTMsg.orgCd, afterAttr);
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

    private boolean isExistsPostCdInTrtyRule(String afterAttr, String postCd) {

        DS_ORG_UNITTMsg orgUnitTMsg = new DS_ORG_UNITTMsg();
        ZYPEZDItemValueSetter.setValue(orgUnitTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(orgUnitTMsg.orgCd, afterAttr);
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
        param.put("postCd", postCd);

        Integer overlapCnt = (Integer) this.ssmBatchClient.queryObject("getOverlapPostalCode", param);

        return overlapCnt > 0;
    }

    private void insertTrtyRuleForPostalCode(String afterAttr, String postCd) {

        TRTY_RULETMsg tMsg = new TRTY_RULETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRulePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TRTY_RULE_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.orgCd, afterAttr);
        ZYPEZDItemValueSetter.setValue(tMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleTpCd, TRTY_RULE_TP.POSTAL_CODE);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleFromValTxt, postCd);
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleOprdTpCd, TRTY_RULE_OPRD_TP.EQUAL);
        ZYPEZDItemValueSetter.setValue(tMsg.trtyRuleLogicTpCd, TRTY_RULE_LOGIC_TP.OR);

        S21FastTBLAccessor.insert(tMsg);
    }
    // 2018/03/26 QC#23157 Add End
}
