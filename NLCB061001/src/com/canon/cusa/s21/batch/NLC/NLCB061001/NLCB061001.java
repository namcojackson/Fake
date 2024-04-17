/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.batch.NLC.NLCB061001;

import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.BIND_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.BIND_LOC_STS_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.BIND_LOC_TP_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.BIND_RTL_WH_CATG_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.BIND_STK_STS_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.BIND_WH_OWNR_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.CST_DEBUG_MSG_LVL;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.INVTY_AVAL_QTY;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.MSG_STR_COMP_CODE;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.MSG_STR_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.MSG_STR_PARAM_01;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.NPAM1172E;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.TPL_ACCT_TXT;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.TPL_PTNR_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.TPL_LOC_TXT_ZCOL;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.VAR_CHAR_CONST_TARGET_RTL_WH_CATG_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.VAR_CHAR_CONST_TARGET_WH_OWNR_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.ZZBM0009I;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NLC.NLCB061001constant.NLCB061001Constant.ZZM9004E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.NLCI1060_01TMsg;

import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NLCB061001
 * Function Name : Columbus On-hand Inventory snapshot to Choice.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/04/2016   CITS            T.Hakodate      Create          N/A
 * 07/14/2017   CITS            Y.IWASAKI       Update          QC#19942
 *</pre>
 */

public class NLCB061001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface ID */
    private String interfaceId;

    /** The number of cases : Select */
    private int selectCount;

    /** Commit Count */
    private int commitCount = 0;

    /** Transaction table accessor */
    private S21TransactionTableAccessor trxAccess;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Termination code */
    private TERM_CD termCd;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList = new ArrayList<Map<String, String>>();

    /** Target WH_OWNR_CD */
    private String targetWhOwnrCd;

    /** Target RTL_WH_CATG_CD */
    private String targetRtlWhCatgCd;

    protected void initRoutine() {

        // Initialization of ssmLLClient
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Initialization of variable
        selectCount = 0;
        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        // The Transaction ID is obtained
        trxAccess = new S21TransactionTableAccessor();

        // Check on input parameter
        checkParameter();

    }

    protected void mainRoutine() {

        // Get operation date.
        // batProcDate = ZYPDateUtil.getBatProcDate(glblCmpyCd);

        // Create SO Interface data and Transaction table.
        createSoToChoIF();

    }

    protected void termRoutine() {

        String[] msg = null;

        // The number of cases : Insert is output
        msg = new String[] {interfaceId, "Insert", Integer.toString(selectCount) };
        S21InfoLogOutput.println(ZZBM0009I, msg);

        // Setting of termination code
        setTermState(termCd, selectCount, 0, selectCount);

    }

    /**
     * checkParameter
     */
    private void checkParameter() {

        // Global Company Code
        glblCmpyCd = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            outputErr(ZZM9000E, new String[] {MSG_STR_COMP_CODE });
            throw new S21AbendException(ZZM9000E, new String[] {MSG_STR_COMP_CODE });
        }

        // Interface ID
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            outputErr(ZZM9000E, new String[] {MSG_STR_INTERFACE_ID });
            throw new S21AbendException(ZZM9000E, new String[] {MSG_STR_INTERFACE_ID });
        }

        // Commit Count
        String str = getUserVariable1();

        if (!ZYPCommonFunc.hasValue(str)) {
            outputErr(ZZM9000E, new String[] {MSG_STR_PARAM_01 });
            throw new S21AbendException(ZZM9000E, new String[] {MSG_STR_PARAM_01 });
        }

        try {
            commitCount = Integer.parseInt(str);

        } catch (NumberFormatException e) {
            outputErr(ZZM9004E, new String[] {"Commit Count(" + str + ")" });
            NLXMailSend mail = new NLXMailSend(this.glblCmpyCd);
            mail.send(BUSINESS_ID, errMsgList);
            throw new S21AbendException(ZZM9004E, new String[] {"Commit Count(" + str + ")" });
        }

        targetWhOwnrCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_TARGET_WH_OWNR_CD, glblCmpyCd); /* DBS */

        targetRtlWhCatgCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_TARGET_RTL_WH_CATG_CD, glblCmpyCd); /* 03 */
    }

    /**
     * createSoToChoIF
     */
    private void createSoToChoIF() {

        S21SsmExecutionParameter execParam = null;

        // Set the fetch size.
        execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        execParam.setFetchSize(FETCH_SIZE);

        // Get Transaction ID.(RWS)
        BigDecimal rwsTrxId = trxAccess.getNextTransactionId();
        // Create RWS Interface data.
        createInvtyInterface(rwsTrxId, execParam);

        // Create Transaction table.
        trxAccess.createIntegrationRecordForBatch(interfaceId, rwsTrxId);
        commit();

    }

    /**
     * createInvtyInterface
     * @param trxId
     * @param execParam
     */
    private void createInvtyInterface(BigDecimal trxId, S21SsmExecutionParameter execParam) {

        PreparedStatement prdStmtSo = null;
        ResultSet rsSo = null;

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();

            ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
            ssmParam.put(BIND_RTL_WH_CATG_CD, this.targetRtlWhCatgCd);
            ssmParam.put(BIND_WH_OWNR_CD, this.targetWhOwnrCd);
            ssmParam.put(BIND_LOC_TP_CD, LOC_TP.WAREHOUSE);
            ssmParam.put(BIND_LOC_STS_CD, LOC_STS.DC_STOCK);
            ssmParam.put(BIND_STK_STS_CD, STK_STS.GOOD);

            prdStmtSo = ssmLLClient.createPreparedStatement("findInvty", ssmParam, execParam);
            rsSo = prdStmtSo.executeQuery();

            int cnt = 0;
            int unitId = 0;

            while (rsSo.next()) {

                selectCount++;
                cnt++;
                unitId++;

                String mdseCd = rsSo.getString(MDSE_CD);
                BigDecimal invtyAvalQty = rsSo.getBigDecimal(INVTY_AVAL_QTY);

                // Insert NLAI2020_01
                createNLCI1060_01(mdseCd, invtyAvalQty, unitId, trxId);

                if (cnt >= commitCount) {
                    commit();
                    cnt = 0;
                }

            }

        } catch (SQLException e) {

            rollback();
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        } finally {
            if (errMsgList.size() > 0) {
                NLXMailSend mail = new NLXMailSend(this.glblCmpyCd);
                mail.send(BUSINESS_ID, errMsgList);
                commit();
            }

            S21SsmLowLevelCodingClient.closeResource(prdStmtSo, rsSo);

        }

    }

    /**
     * createNLAI2020_01RWS
     * @param rwsHeader
     * @param unitId
     * @param trxId
     */
    private void createNLCI1060_01(String mdseCd, BigDecimal invtyAvalQty, int unitId, BigDecimal trxId) {

        NLCI1060_01TMsg inMsg = new NLCI1060_01TMsg();

        ZYPEZDItemValueSetter.setValue(inMsg.interfaceId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(inMsg.transactionId, trxId);
        ZYPEZDItemValueSetter.setValue(inMsg.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(inMsg.unitId, BigDecimal.valueOf(unitId));
        ZYPEZDItemValueSetter.setValue(inMsg.seqNumber, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(inMsg.tplPtnrId, TPL_PTNR_ID);
        ZYPEZDItemValueSetter.setValue(inMsg.tplAcctTxt, TPL_ACCT_TXT);
        ZYPEZDItemValueSetter.setValue(inMsg.tplLocTxt, TPL_LOC_TXT_ZCOL);
        ZYPEZDItemValueSetter.setValue(inMsg.itemCdTxt, mdseCd);
        ZYPEZDItemValueSetter.setValue(inMsg.qtyOrdTxt, invtyAvalQty.toPlainString());

        EZDTBLAccessor.insert(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {

            StringBuilder sb = new StringBuilder();

            sb.append(inMsg.getTableName());

            rollback();

            outputErr(NPAM1172E, new String[] {sb.toString() });

            throw new S21AbendException(S21MessageFunc.clspGetMessage(NPAM1172E, new String[] {sb.toString() }));

        }

    }

    /**
     * @param msgId
     * @param msgParam
     */
    private void outputErr(String msgId, String[] msgParam) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
        errMsgList.add(mailParam);

        S21InfoLogOutput.println(msgId, msgParam);
    }

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {

        new NLCB061001().executeBatch(NLCB061001.class.getSimpleName());

    }

}
