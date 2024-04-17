/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB274001;

import static com.canon.cusa.s21.batch.NFC.NFCB274001.constant.NFCB274001Constant.BLANK;
import static com.canon.cusa.s21.batch.NFC.NFCB274001.constant.NFCB274001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NFC.NFCB274001.constant.NFCB274001Constant.NFCM0531E;
import static com.canon.cusa.s21.batch.NFC.NFCB274001.constant.NFCB274001Constant.NFCM0783E;
import static com.canon.cusa.s21.batch.NFC.NFCB274001.constant.NFCB274001Constant.NFCM0782E;
import static com.canon.cusa.s21.batch.NFC.NFCB274001.constant.NFCB274001Constant.MAX_END_DATE;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_BILL_TO_CHNG_SELL_TOTMsg;
import business.db.AR_TRX_BALTMsg;
import business.parts.NFZC202001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC202001.NFZC202001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
/**
 * <pre>
 * NFCB2740:Bill To Change
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/29   Fujitsu         S.Fujita        Create          N/A
 * 2017/12/15   Hitachi         E.Kameishi      Update          QC#22096
 *</pre>
 */
public class NFCB274001 extends S21BatchMain {

    /** Termination Code of Execution Process */
    private TERM_CD termCd = null;

    /** SSM-Client*/
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Cursor Parameter */
    private S21SsmExecutionParameter execParam;

    /** Global Company Code */
    private String glblCmpyCd = null;
    /** Batch Process Date */
    private String batProcDate = null;
    /** Counter of Records: Error */
    private int procCnt = 0;
    /** Counter of Records: Successful */
    private int normalCnt = 0;
    /** Error count */
    private int errCnt = 0;

    /**
     * @param args  String[]
     */
    public static void main(String[] args) {
        new NFCB274001().executeBatch(NFCB274001.class.getSimpleName());
    }

    /**
     * Initial processing
     */
    @Override
    protected void initRoutine() {
        // Initialize Variables
        termCd = TERM_CD.NORMAL_END;
        // Initialize ssmLLClient
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // Getting Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        // Getting Batch Process Date
        this.batProcDate = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.batProcDate)) {
            execAbendException(NFCM0531E, new String[] {"Batch Operation Date"});
        }
        // Set SSM Cursor Parameter
        this.execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
    }

    /**
     * Main processing
     */
    @Override
    protected void mainRoutine() {
        try {
            // Bill To Change
            billToChange();
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    /**
     * Termination
     */
    @Override
    protected void termRoutine() {
        setTermState(termCd, this.normalCnt, this.errCnt, this.procCnt);
    }

    /**
     * Bill To Change
     * @throws SQLException
     */
    private void billToChange() throws SQLException {
        //----------------------------------------------------
        // AR Transaction Balance List
        //----------------------------------------------------
        PreparedStatement stmtSelect = null;
        ResultSet rs = null;
        try {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("batProcDate", this.batProcDate);
            queryParam.put("maxEndDate", MAX_END_DATE);
            queryParam.put("arCashAppStsUnApp", AR_CASH_APPLY_STS.UNAPPLIED);
            queryParam.put("arCashAppStsPart", AR_CASH_APPLY_STS.PARTIAL);

            stmtSelect = this.ssmLLClient.createPreparedStatement("getArTrxBal", queryParam, this.execParam);
            rs = stmtSelect.executeQuery();

            //----------------------------------------------------
            // AR Transaction Balance Loop
            //----------------------------------------------------
            while (rs.next()) {
                //----------------------------------------------------
                // insert AR BillTo Change Info
                //----------------------------------------------------
                insertArBillToChngInfo(rs);

                this.procCnt++;
                this.normalCnt++;
            }
            commit();
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * Execute AbendException
     * @param msgId String
     * @param msgStr String[]
     */
    private void execAbendException(String msgId, String[] msgStr) {

        termCd = TERM_CD.ABNORMAL_END;
        throw new S21AbendException(msgId, msgStr);
    }

    /**
     * Insert AR BillTo Change Info
     * @param rs ResultSet
     */
    private void insertArBillToChngInfo(ResultSet rs) throws SQLException {
        // update for AR Transaction Balance
        BigDecimal arTrxBalPk = checkNull(rs.getBigDecimal("AR_TRX_BAL_PK"));
        String sellToCustCd = checkNull(rs.getString("SELL_TO_CUST_CD"));
        //----------------------------------------------------
        // insert AR BillTo Change SellTo
        //----------------------------------------------------
        insertArBillToChngSellTo(rs);
        //----------------------------------------------------
        // update AR Transaction Balance
        //----------------------------------------------------
        updateArTrxBal(arTrxBalPk, sellToCustCd);
        // START 2017/12/15 E.Kameishi [QC#22096, ADD]
        //----------------------------------------------------
        // update Credit Profile
        //----------------------------------------------------
        callCrPrflUpdtAPI(rs);
        // END 2017/12/15 E.Kameishi [QC#22096, ADD]
    }

    /**
     * Insert AR BillTo Change SellTo
     * @param rs ResultSet
     */
    private void insertArBillToChngSellTo(ResultSet rs) throws SQLException {

        AR_BILL_TO_CHNG_SELL_TOTMsg insMsg = new AR_BILL_TO_CHNG_SELL_TOTMsg();

        ZYPEZDItemValueSetter.setValue(insMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insMsg.mstrChngDt, this.batProcDate);
        ZYPEZDItemValueSetter.setValue(insMsg.arTrxTpCd, rs.getString("AR_TRX_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insMsg.arTrxNum, rs.getString("AR_TRX_NUM"));
        ZYPEZDItemValueSetter.setValue(insMsg.sellToCustCd, rs.getString("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(insMsg.oldBillToCustCd, rs.getString("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(insMsg.newBillToCustCd, rs.getString("SELL_TO_CUST_CD"));

        EZDTBLAccessor.insert(insMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insMsg.getReturnCode())) {
            this.errCnt++;
            execAbendException(NFCM0782E, new String[] {"AR_BILL_TO_CHNG_SELL_TO"});
        }
    }

    /**
     * Update AR Transaction Balance
     * @param arTrxBalPk BigDecimal
     * @param sellToCustCd String
     */
    private void updateArTrxBal(BigDecimal arTrxBalPk, String sellToCustCd) {

        AR_TRX_BALTMsg tMsg = new AR_TRX_BALTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.arTrxBalPk, arTrxBalPk);

        AR_TRX_BALTMsg balResult = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);

        if (balResult == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(balResult.getReturnCode())) {
            this.errCnt++;
            execAbendException(NFCM0783E, new String[] {"AR_TRX_BAL"});
        }

        // set sellToCustCd
        ZYPEZDItemValueSetter.setValue(balResult.billToCustAcctCd, sellToCustCd);

        EZDTBLAccessor.update(balResult);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(balResult.getReturnCode())) {
            this.errCnt++;
            execAbendException(NFCM0783E, new String[] {"AR_TRX_BAL"});
        }
    }

    /**
     * checkNull
     * @param val String
     * @return val String
     */
    private String checkNull(String val) {
        if (!ZYPCommonFunc.hasValue(val)) {
            return BLANK;
        } else {
            return val;
        }
    }

    /**
     * checkNull
     * @param val BigDecimal
     * @return val BigDecimal
     */
    private BigDecimal checkNull(BigDecimal val) {

        BigDecimal num = BigDecimal.ZERO;
        if (!ZYPCommonFunc.hasValue(val)) {
            return num;
        } else {
            return val;
        }
    }
    // START 2017/12/13 E.Kameishi [QC#22096, ADD]
    private void callCrPrflUpdtAPI(ResultSet rs) throws SQLException {
        NFZC202001 crPrflUpdApi = new NFZC202001();
        NFZC202001PMsg paramMsg = new NFZC202001PMsg();
        
        AR_TRX_BALTMsg arTrxBal = new AR_TRX_BALTMsg();
        ZYPEZDItemValueSetter.setValue(arTrxBal.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(arTrxBal.arTrxBalPk, rs.getBigDecimal("AR_TRX_BAL_PK"));
        arTrxBal = (AR_TRX_BALTMsg) S21ApiTBLAccessor.findByKeyForUpdate(arTrxBal);
        
        ZYPEZDItemValueSetter.setValue(paramMsg.xxModeCd, NFZC202001.MODE_BILL_TO_CUST);
        ZYPEZDItemValueSetter.setValue(paramMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(paramMsg.billToCustCd, arTrxBal.billToCustCd);
        ZYPEZDItemValueSetter.setValue(paramMsg.procDt, ZYPDateUtil.getSalesDate());

        crPrflUpdApi.execute(paramMsg, ONBATCH_TYPE.BATCH);
        
        List<String> msgList = S21ApiUtil.getXxMsgIdList(paramMsg);
        for (String msgId : msgList) {
            if (msgId.endsWith("E")) {
                this.errCnt++;
                this.termCd = TERM_CD.ABNORMAL_END;
                rollback();
                throw new S21AbendException(msgId);
            }
        }
    }
    // END 2017/12/13 E.Kameishi [QC#22096, ADD]
}
