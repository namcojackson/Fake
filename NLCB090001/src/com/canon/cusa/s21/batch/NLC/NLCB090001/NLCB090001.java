/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB090001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.NLCI0050_01TMsg;

import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>    
 * Business ID : NLCB0900 Inventory Reconciliation from MNX
 *  
 * Date         Company         Name            Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 10/30/2020   CITS            R.MERCADO       Create          QC#57659
 * 
 *</pre>
 */
public class NLCB090001 extends S21BatchMain {

    // *********************************************************
    // Instance Variables: Basic
    // *********************************************************
    /** Termination Code of Execution Process */
    private TERM_CD termCd = null;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Interface ID */
    private String interfaceId = null;

    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList = null;

    /** Counter of Records: Successful */
    private int successCount = 0;

    /** Counter of Records: Error */
    private int errorCount = 0;

    /** Target Warehouse Code */
    String[] trgtWhCdAry = null;

    /** **/
    BigDecimal sqlId = null;

    // *********************************************************
    // Methods
    // *********************************************************
    /**
     * Startups.
     * @param args arguments
     */
    public static void main(String[] args) {

        new NLCB090001().executeBatch(NLCB090001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Initialize Variables
        termCd = TERM_CD.NORMAL_END;
        errMsgList = new ArrayList<Map<String, String>>();

        // Getting Global Company Code
        S21UserProfileServiceFactory profFactory = S21UserProfileServiceFactory.getInstance();
        S21UserProfileService prof = profFactory.getService();
        glblCmpyCd = prof.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NLCB090001Constant.NLGM0049E, new String[] {NLCB090001Constant.PRAM_NM_GLBL_CMPY_CD });
        }

        // Getting Interface ID
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NLCB090001Constant.NLGM0049E, new String[] {NLCB090001Constant.PRAM_NM_INTERFACE_ID });
        }

        // Getting SQL ID
        sqlId = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ);
    }

    @Override
    protected void mainRoutine() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        // Get Target TRANSACTION_ID
        S21TransactionTableAccessor trxAccessor = new S21TransactionTableAccessor();
        BigDecimal[] tranIds = trxAccessor.getIntegrationRecord(this.interfaceId);

        if (tranIds.length <= 0) {
            return;
        }

        PreparedStatement prdStmt = null;
        ResultSet rs = null;

        try {

            // Loop in Target TRANSACTION_ID unit
            for (BigDecimal trxId : tranIds) {

                boolean success = true;

                // Numbering the transaction ID for the I/F Data
                BigDecimal regTrxId = trxAccessor.getNextTransactionId();

                // UnitId For Registration
                int regUnitId = 0;

                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put(NLCB090001Constant.COL_INTERFACE_ID, this.interfaceId);
                queryParam.put(NLCB090001Constant.COL_TRANSACTION_ID, trxId);
                queryParam.put(NLCB090001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
                queryParam.put(NLCB090001Constant.COL_WH_OWNR_CD, WH_OWNR.MNX);

                prdStmt = ssmLLClient.createPreparedStatement("getWmsUploadInfo", queryParam, execParam);
                rs = prdStmt.executeQuery();

                // Loop n UnitID
                while (rs.next()) {
                    // Get Current UnitId
                    BigDecimal unitId = rs.getBigDecimal(NLCB090001Constant.COL_UNIT_ID);
                    String rtlWhCd = rs.getString(NLCB090001Constant.COL_TPL_LOC_CD);

                    if (!ZYPCommonFunc.hasValue(rtlWhCd)) {
                        EZDDebugOutput.println(1, "Specified TPL_LOC_TXT is unknown. Skip record.", this);
                        continue;
                    }

                    String rtlSwhCd = getRtlSwhCd(rs.getString(NLCB090001Constant.COL_TPL_LOC_CD));

                    Map<String, Object> wmsUplInfo = new HashMap<String, Object>();
                    wmsUplInfo.put(NLCB090001Constant.COL_SEQ_NUMBER, rs.getBigDecimal(NLCB090001Constant.COL_SEQ_NUMBER));
                    wmsUplInfo.put(NLCB090001Constant.COL_REQ_DT_TM_TS_TXT, rs.getString(NLCB090001Constant.COL_REQ_DT_TM_TS_TXT));
                    wmsUplInfo.put(NLCB090001Constant.COL_ITEM_CD_TXT, rs.getString(NLCB090001Constant.COL_ITEM_CD_TXT));
                    wmsUplInfo.put(NLCB090001Constant.COL_QTY_ORD_TXT, rs.getBigDecimal(NLCB090001Constant.COL_QTY_ORD_TXT));
                    wmsUplInfo.put(NLCB090001Constant.COL_RTL_WH_CD, rtlWhCd);
                    wmsUplInfo.put(NLCB090001Constant.COL_RTL_SWH_CD, rtlSwhCd);

                    // Validation check
                    if (!validateIFData(wmsUplInfo, trxId, unitId)) {
                        EZDDebugOutput.println(1, "validateIFData Error. Skip record.", this);
                        continue;
                    }

                    // Numbered the UnitId for registration
                    ++regUnitId;
                    // Create IF Data
                    createNLCI0050(wmsUplInfo, regTrxId, regUnitId);
                }

                // Settle transaction with current TRANSACTION_ID
                // Rollback trxId on error
                if (success) {
                    if (regUnitId > 0) {
                        trxAccessor.createIntegrationRecordForBatch(NLCB090001Constant.VAL_NLCI0050, regTrxId);
                        ++this.successCount;
                    }
                } else {
                    ++this.errorCount;
                    rollback();
                }

                // Update PROCESSED_FLAG with current TRANSACTION_ID
                // Update to processed in any case - success/fail -
                trxAccessor.endIntegrationProcess(this.interfaceId, trxId);
                commit();
            }

        } catch (SQLException e) {
            rollback();
            sqlExceptionHandler(e);
        } finally {
            if (errMsgList.size() > 0) {
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(NLCB090001Constant.BUSINESS_ID, errMsgList);
                commit();
            }
        }
    }

    @Override
    protected void termRoutine() {
        // Setting Process Termination Code
        setTermState(termCd, this.successCount, this.errorCount, this.successCount + this.errorCount);
    }

    private String getRtlSwhCd(String rtlWhCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put(NLCB090001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLCB090001Constant.COL_RTL_WH_CD, rtlWhCd);
        queryParam.put("SALES_DATE", ZYPDateUtil.getSalesDate(glblCmpyCd));
        queryParam.put(NLCB090001Constant.COL_PRTY_LOC_FLG, ZYPConstant.FLG_ON_Y);

        String rtlSwCd = (String) this.ssmBatchClient.queryObject("getRtlSwhCd", queryParam);
        return rtlSwCd;
    }

    private boolean validateIFData(Map<String, Object> wmsUplInfo, BigDecimal tranId, BigDecimal curUnitId) {

        // MANDATORY CHECK
        boolean flg = true;
        String errorLocation = interfaceId + "," + tranId.toPlainString() + "," + curUnitId.toPlainString();

        // REQ_DT_TM_TS_TXT
        if (wmsUplInfo.get(NLCB090001Constant.COL_REQ_DT_TM_TS_TXT) == null) {
            outputErr(NLCB090001Constant.NLGM0041E, new String[] {NLCB090001Constant.COL_REQ_DT_TM_TS_TXT, NLCB090001Constant.TBL_NLCI1070_01, errorLocation });
            flg = false;
        }
        // ITEM_CD_TXT
        if (wmsUplInfo.get(NLCB090001Constant.COL_ITEM_CD_TXT) == null) {
            outputErr(NLCB090001Constant.NLGM0041E, new String[] {NLCB090001Constant.COL_ITEM_CD_TXT, NLCB090001Constant.TBL_NLCI1070_02, interfaceId + "," + tranId.toString() + "," + curUnitId });
            flg = false;
        }
        // QTY_ORD_TXT
        if (wmsUplInfo.get(NLCB090001Constant.COL_QTY_ORD_TXT) == null) {
            outputErr(NLCB090001Constant.NLGM0041E, new String[] {NLCB090001Constant.COL_QTY_ORD_TXT, NLCB090001Constant.TBL_NLCI1070_02, interfaceId + "," + tranId.toString() + "," + curUnitId });
            flg = false;
        }
        // RTL_SWH_CD
        if (wmsUplInfo.get(NLCB090001Constant.COL_RTL_SWH_CD) == null) {
            outputErr(NLCB090001Constant.NLGM0041E, new String[] {NLCB090001Constant.COL_RTL_SWH_CD, NLCB090001Constant.TBL_NLCI1070_01, errorLocation });
            flg = false;
        }

        return flg;
    }

    private void createNLCI0050(Map<String, Object> wmsUplInfo, BigDecimal regTrxId, int regUnitId) {
        NLCI0050_01TMsg tMsg = new NLCI0050_01TMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, NLCB090001Constant.VAL_NLCI0050);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, regTrxId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, new BigDecimal(regUnitId));
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tMsg.warehouseCodeIf, (String) wmsUplInfo.get(NLCB090001Constant.COL_RTL_WH_CD));

        // Transaction sequence IF
        BigDecimal seqNumber = (BigDecimal) wmsUplInfo.get(NLCB090001Constant.COL_SEQ_NUMBER);
        String seqNum = seqNumber.toPlainString();
        seqNum = ZYPCommonFunc.leftPad(seqNum, NLCB090001Constant.LG_SEQ_NUMBER, NLCB090001Constant.VAL_ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionSequenceIf, seqNum);

        ZYPEZDItemValueSetter.setValue(tMsg.recordTypeIf, "13");
        ZYPEZDItemValueSetter.setValue(tMsg.detailTypeIf, "1");
        ZYPEZDItemValueSetter.setValue(tMsg.dataActionCodeIf, "");
        ZYPEZDItemValueSetter.setValue(tMsg.companyCodeIf, "01");
        ZYPEZDItemValueSetter.setValue(tMsg.warehouseCode2If, (String) wmsUplInfo.get(NLCB090001Constant.COL_RTL_SWH_CD));

        String reqDt = (String) wmsUplInfo.get(NLCB090001Constant.COL_REQ_DT_TM_TS_TXT);
        reqDt = ZYPDateUtil.DateFormatter(reqDt, NLCB090001Constant.VAL_DATETIME_FORMAT_INPUT, NLCB090001Constant.VAL_DATETIME_FORMAT_OUTPUT);
        ZYPEZDItemValueSetter.setValue(tMsg.closedDateIf, reqDt);

        ZYPEZDItemValueSetter.setValue(tMsg.itemCodeIf, (String) wmsUplInfo.get(NLCB090001Constant.COL_ITEM_CD_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.stockStatusIf, "S1");
        ZYPEZDItemValueSetter.setValue(tMsg.onHandNoHoldIf, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.onHandHoldIf, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.receivingOnStageIf, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.shippingOnStageIf, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.allOfNoOnHandIf, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.allOfOnHandIf, new BigDecimal(((BigDecimal) wmsUplInfo.get(NLCB090001Constant.COL_QTY_ORD_TXT)).intValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.orderNotAllocatedIf, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsg.suspendIf, BigDecimal.ZERO);

        EZDTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            outputErr(NLCB090001Constant.NLGM0045E, new String[] {NLCB090001Constant.TBL_NLCI0050_01, NLCB090001Constant.TBL_NLCI1070_01, NLXCMsgHelper.toListedString("INTERFACE_ID", "TRANSACTION_ID", "UNIT_ID"),
                    NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
            throw new S21AbendException(NLCB090001Constant.NLGM0045E, new String[] {NLCB090001Constant.TBL_NLCI0050_01, NLCB090001Constant.TBL_NLCI1070_01, NLXCMsgHelper.toListedString("INTERFACE_ID", "TRANSACTION_ID", "UNIT_ID"),
                    NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
        }
    }

    /**
     * Output Error Message.
     * @param msgId MessageId
     * @param msgParam MessageParameters
     */
    private void outputErr(String msgId, String[] msgParam) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
        errMsgList.add(mailParam);

        S21InfoLogOutput.println(msgId, msgParam);
    }
}
