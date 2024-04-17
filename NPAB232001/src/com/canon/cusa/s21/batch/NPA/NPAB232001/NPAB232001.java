/**
 * <pre>Copyright (c) 2020 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB232001;

import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.ASL_DTL_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.ASL_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.DB_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.DB_PARAM_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.DB_PARAM_SALES_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.DB_PARAM_TRANSACTION_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.DUE_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.EMAIL_PARAM_BATCH_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.EMAIL_PARAM_MESSAGE;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.IF_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.ML_GRP_CD_FROM;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.ML_GRP_CD_TO;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.ML_LANG;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.ML_LANG_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.ML_TMPL_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.NPAB2310_ORDERSBPS_CRAT_USER;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.NPAM1653E;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.NPZM0161E;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.PARAM_NM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.PARAM_NM_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.PO_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.PRIM_SPLY_SITE_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.PRNT_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.RTL_WH_CATG_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.SRC_APVL_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.SRC_APVL_SQ;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.UNIT_PRC_AMT;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.WH_OWNR_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.WH_RG_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.ZZBM0009I;
import static com.canon.cusa.s21.batch.NPA.NPAB232001.constant.NPAB232001Constant.ZZM9000E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NPZC103001PMsg;
import business.parts.NPZC103001_prchReqInfoPMsg;

import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>    
 * Baxter IF for ORDERSBPS
 *  
 * Date         Company         Name            Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 11/01/2021   CITS            K.Ogino         Create          QC#58990
 * 12/01/2021   CITS            K.Ogino         Create          QC#58990-1
 * 12/07/2021   CITS            K.Ogino         Create          QC#58990-2
 * 07/19/2022   CITS            F.Fadriquela    Update          QC#60274
 *</pre>
 */
public class NPAB232001 extends S21BatchMain {

    // *********************************************************
    // Instance Variables: Basic
    // *********************************************************

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    /** Transaction table accessor */
    private S21TransactionTableAccessor trxAccess = new S21TransactionTableAccessor();

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Interface ID */
    private String interfaceId = null;

    /** Error Massage List */
    private ArrayList<String> errMsgList = new ArrayList<String>();

    /** Termination Code of Execution Process */
    private TERM_CD termCd = null;

    /** Counter of Records: Successful */
    private int successCount = 0;

    /** Counter of Records: Error */
    private int errorCount = 0;

    /** Sales Date */
    private String salesDate = null;

    // *********************************************************
    // Instance Variables: Constant values on load
    // *********************************************************
    /** var_char_const_val */
    private String varVal = "";

    /**
     * @param args arguments
     */
    public static void main(String[] args) {
        // Initialization of S21BatchMain
        new NPAB232001().executeBatch(NPAB232001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Initialization of variable
        successCount = 0;
        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        S21UserProfileServiceFactory profFactory = S21UserProfileServiceFactory.getInstance();
        S21UserProfileService prof = profFactory.getService();

        // Global Company Code
        this.glblCmpyCd = prof.getGlobalCompanyCode();

        // Interface ID
        this.interfaceId = getInterfaceID();

        // Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // VAR_CHAR_CONST
        this.varVal = ZYPCodeDataUtil.getVarCharConstValue(NPAB2310_ORDERSBPS_CRAT_USER, this.glblCmpyCd);
        if (varVal == null) {
            varVal = "BAXTER";
        }

        // Check on input parameter
        checkParameter();
    }

    @Override
    protected void mainRoutine() {

        // Get Target TRANSACTION_ID
        BigDecimal[] tranIds = trxAccess.getIntegrationRecord(this.interfaceId);

        if (tranIds.length <= 0) {
            return;
        }

        try {
            for (BigDecimal trxId : tranIds) {

                cratPrReq(trxId);

                // Update the flag to processed in any case -
                // success/fail -
                trxAccess.endIntegrationProcess(this.interfaceId, trxId);

                commit();
            }

        } catch (S21AbendException e) {
            rollback();
            throw e;
        } finally {
            if (errMsgList.size() > 0) {
                termCd = TERM_CD.WARNING_END;
                // ERROR MAIL
                sendErrMail();
                commit();
            }
        }
    }

    @Override
    protected void termRoutine() {

        int totalCount = successCount + errorCount;

        // The number of cases : Insert is output
        S21InfoLogOutput.println(ZZBM0009I, new String[] {interfaceId, "Insert", Integer.toString(totalCount) });

        // Setting of termination code
        setTermState(termCd, successCount, errorCount, totalCount);

    }

    /**
     * <pre>
     * Check processing of input parameters.
     * </pre>
     */
    private void checkParameter() {

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {PARAM_NM_GLBL_CMPY_CD });
        }

        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(ZZM9000E, new String[] {PARAM_NM_INTERFACE_ID });
        }
    }

    private void cratPrReq(BigDecimal trxId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        bindParam.put(DB_PARAM_INTERFACE_ID, this.interfaceId);
        bindParam.put(DB_PARAM_TRANSACTION_ID, trxId);
        bindParam.put(DB_PARAM_SALES_DATE, this.salesDate);

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {

            stmt = ssmLLClient.createPreparedStatement("findIFData", bindParam, execParam);
            rs = stmt.executeQuery();

            NPZC103001PMsg pMsg = null;
            String prevGrpKey = "";
            int apiLimitCnt = 999;
            int apiLineCnt = 0;

            while (rs.next()) {

                // IF Data Validation Check.
                boolean isErr = isValidIfData(rs);
                if (isErr) {
                    continue;
                }
                // QC#58990-1. Mod. QC#58990-2
                String grpKey = rs.getString(RTL_WH_CD) + rs.getString(RTL_SWH_CD) + rs.getString(PRNT_VND_CD) + rs.getString(DUE_DT);

                if (!prevGrpKey.equals(grpKey)) {
                    // Header
                    if (pMsg == null) {
                        pMsg = createHeaderParam(rs);
                        apiLineCnt = 0;
                        createDetailParam(pMsg, rs, apiLineCnt);
                        apiLineCnt++;
                    } else {
                        isErr = callPrUpdateAPI(pMsg, trxId);
                        pMsg = null;
                        if (isErr) {
                            rollback();
                        } else {
                            commit();
                        }
                        pMsg = createHeaderParam(rs);
                        apiLineCnt = 0;
                        createDetailParam(pMsg, rs, apiLineCnt);
                        apiLineCnt++;
                    }
                    prevGrpKey = grpKey;
                } else {
                    // Detail
                    createDetailParam(pMsg, rs, apiLineCnt);
                    apiLineCnt++;
                }

                // API Line Limit
                if (apiLimitCnt == pMsg.prchReqInfo.getValidCount()) {
                    isErr = callPrUpdateAPI(pMsg, trxId);
                    if (isErr) {
                        rollback();
                    } else {
                        commit();
                    }
                    pMsg = null;
                    prevGrpKey = "";
                    apiLineCnt = 0;
                }
            }

            if (pMsg != null) {
                boolean isErr = callPrUpdateAPI(pMsg, trxId);
                if (isErr) {
                    rollback();
                } else {
                    commit();
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private NPZC103001PMsg createHeaderParam(ResultSet rs) throws SQLException {

        String whOwnrCd = rs.getString(WH_OWNR_CD);
        NPZC103001PMsg pMsg = new NPZC103001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_CREATE);
        ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC103001Constant.EVENT_SUBMIT);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.procDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqRecTpCd, PRCH_REQ_REC_TP.PO_REQUISITION);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqTpCd, PRCH_REQ_TP.STANDARD_2);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqCratByPsnCd, varVal);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqRqstByPsnCd, varVal);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqSrcTpCd, PRCH_REQ_SRC_TP.WH_PLANNING);
        // START 2022/07/18 F.Fadriquela [QC#60274, MOD]
        //if (WH_OWNR.MNX.equals(whOwnrCd)) {
        if (WH_OWNR.MNX.equals(whOwnrCd) || RTL_WH_CATG.PARTS_DEPO.equals(rs.getString(RTL_WH_CATG_CD))) {
        // END 2022/07/18 F.Fadriquela [QC#60274, MOD]
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.PRE_APPROVED);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.ENTERED);
        }

        return pMsg;
    }

    private void createDetailParam(NPZC103001PMsg pMsg, ResultSet rs, int apiLineCnt) throws SQLException {

        NPZC103001_prchReqInfoPMsg prInfo = pMsg.prchReqInfo.no(apiLineCnt);

        ZYPEZDItemValueSetter.setValue(prInfo.prchReqLineTpCd, PRCH_REQ_LINE_TP.GOODS);
        ZYPEZDItemValueSetter.setValue(prInfo.rqstRcvDt, rs.getString(DUE_DT));
        ZYPEZDItemValueSetter.setValue(prInfo.procrTpCd, PROCR_TP.SUPPLIER);
        if (PRCH_REQ_APVL_STS.PRE_APPROVED.equals(pMsg.prchReqApvlStsCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(prInfo.poSchdRelDt, this.salesDate);
        }
        ZYPEZDItemValueSetter.setValue(prInfo.destInvtyLocCd, rs.getString(RTL_WH_CD) + rs.getString(RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(prInfo.prntVndCd, rs.getString(PRNT_VND_CD));
        ZYPEZDItemValueSetter.setValue(prInfo.vndCd, rs.getString(VND_CD));
        ZYPEZDItemValueSetter.setValue(prInfo.poMdseCmpsnTpCd, PO_MDSE_CMPSN_TP.REGULAR);
        ZYPEZDItemValueSetter.setValue(prInfo.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(prInfo.aslMdseCd, rs.getString(ASL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(prInfo.prchReqQty, rs.getBigDecimal(PO_QTY));
        ZYPEZDItemValueSetter.setValue(prInfo.prchReqDispQty, rs.getBigDecimal(PO_QTY));
        ZYPEZDItemValueSetter.setValue(prInfo.prchReqDsplUomCd, "EA");
        ZYPEZDItemValueSetter.setValue(prInfo.ropQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prInfo.minOrdQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prInfo.incrOrdQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prInfo.maxInvtyQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prInfo.curInvtyQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prInfo.curInvtyAvalQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prInfo.schdInbdQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prInfo.schdOtbdQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prInfo.aslDtlPk, rs.getBigDecimal(ASL_DTL_PK));
        ZYPEZDItemValueSetter.setValue(prInfo.aslUnitPrcAmt, rs.getBigDecimal(UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(prInfo.entDealNetUnitPrcAmt, rs.getBigDecimal(UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(prInfo.entFuncNetUnitPrcAmt, rs.getBigDecimal(UNIT_PRC_AMT));
        // QC#58990-2
        ZYPEZDItemValueSetter.setValue(prInfo.trxRefNum, this.varVal);
        ZYPEZDItemValueSetter.setValue(prInfo.prchReqLineCmntTxt, rs.getBigDecimal(SRC_APVL_ID).toPlainString() + "-" + rs.getBigDecimal(SRC_APVL_SQ).toPlainString());

        ZYPEZDItemValueSetter.setValue(prInfo.ccyCd, CCY.US_DOLLAR);
        ZYPEZDItemValueSetter.setValue(prInfo.exchRate, BigDecimal.ONE);

        pMsg.prchReqInfo.setValidCount(apiLineCnt + 1);

    }

    private boolean callPrUpdateAPI(NPZC103001PMsg pMsg, BigDecimal tranId) {

        NPZC103001 prUpdateApi = new NPZC103001();
        prUpdateApi.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);

            for (S21ApiMessage s21ApiMsg : msgList) {
                String msgId = s21ApiMsg.getXxMsgid();
                String msg = S21MessageFunc.clspGetMessage(msgId);
                setErrMsg(NPAM1653E, new String[] {msg, this.interfaceId, tranId.toPlainString() });
            }
            return true;
        }
        // QC#58990-1
        this.successCount++;

        return false;
    }

    private void setErrMsg(String errMsgId, String errMsgParam[]) {

        S21InfoLogOutput.println(errMsgId, errMsgParam);
        errMsgList.add(S21MessageFunc.clspGetMessage(errMsgId, errMsgParam));

        errorCount++;
        rollback();
    }

    private boolean isValidIfData(ResultSet rs) throws SQLException {

        boolean isErr = false;

        String mdseCd = rs.getString(MDSE_CD);
        String rtlWhCd = rs.getString(RTL_WH_CD);
        String rtlSwhCd = rs.getString(RTL_SWH_CD);
        String whRgCd = rs.getString(WH_RG_CD);
        BigDecimal poQty = rs.getBigDecimal(PO_QTY);
        String ifMdseCd = rs.getString(IF_MDSE_CD);
        String aslVndCd = rs.getString(VND_CD);
        String ifSiteCd = rs.getString(PRIM_SPLY_SITE_TXT);

        // MDSE_CD
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            isErr = true;
        }
        // RTL_WH_CD(Region Mapping)
        if (!ZYPCommonFunc.hasValue(rtlWhCd)) {
            isErr = true;
        }
        // PRCH_REQ_QTY
        if (!ZYPCommonFunc.hasValue(poQty) || BigDecimal.ZERO.compareTo(poQty) >= 0) {
            isErr = true;
        }
     // PRCH_REQ_QTY
        if (!ZYPCommonFunc.hasValue(aslVndCd)) {
            isErr = true;
        }


        if (isErr) {
            StringBuilder sb = new StringBuilder();
            if (ZYPCommonFunc.hasValue(mdseCd)) {
                sb.append("\tMDSE_CD:[" + mdseCd + "]");
            } else {
                sb.append("\tIF_MDSE_CD:[" + ifMdseCd + "] There is no S21 MDSE Master.");
            }
            if (ZYPCommonFunc.hasValue(rtlWhCd)) {
                sb.append(" / DEST_INVTY_LOC_CD:" + rtlWhCd + rtlSwhCd);
            } else {
                sb.append(" / TRD_PTNR_WH_RG_XREF:[" + whRgCd + "] There is no Region Mapping.");
            }
            if (ZYPCommonFunc.hasValue(aslVndCd)) {
                sb.append(" / ASL_VND_CD:" + aslVndCd);
            } else {
                sb.append(" / IF_SITE_CD:[" + ifSiteCd + "] There is no ASL Mapping.");
            }
            if (ZYPCommonFunc.hasValue(poQty)) {
                sb.append(" / QTY:" + poQty.toPlainString());
            } else {
                sb.append(" / QTY: Null");
            }
            // QC#58990-1
            this.errorCount++;
            errMsgList.add(sb.toString());
        }

        return isErr;
    }

    private void sendErrMail() {
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        // Get mail group
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, ML_GRP_CD_FROM);
        // Get mail group
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, ML_GRP_CD_TO);

        // Set address
        List<S21MailAddress> toAddrList = groupTo.getMailAddress();
        if (toAddrList.isEmpty()) {
            return;
        }
        mail.setToAddressList(toAddrList);
        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();
        mail.setFromAddress(fromAddrList.get(0));

        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, ML_TMPL_ID);
        if (template == null) {
            throw new S21AbendException(NPZM0161E);
        }

        String crlf = System.getProperty("line.separator");

        // Message
        StringBuffer msg = new StringBuffer();
        for (String errMsg : this.errMsgList) {
            // line end.
            msg.append(errMsg);
            msg.append(crlf);
        }

        template.setTemplateParameter(EMAIL_PARAM_BATCH_ID, BUSINESS_ID);
        template.setTemplateParameter(EMAIL_PARAM_MESSAGE, msg.toString());

        // Set mail subject
        mail.setSubject(template.getSubject(ML_LANG), ML_LANG_CD);
        mail.setMailTemplate(template);

        // Post
        mail.postMail();
    }
}
