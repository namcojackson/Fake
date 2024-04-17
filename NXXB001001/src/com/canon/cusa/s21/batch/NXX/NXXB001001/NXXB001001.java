/**
 * <Pre>Copyright (c) 2019 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NXX.NXXB001001;

import static com.canon.cusa.s21.batch.NXX.NXXB001001.constant.NXXB001001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NXX.NXXB001001.constant.NXXB001001Constant.MSG_ITEM_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NXX.NXXB001001.constant.NXXB001001Constant.TIMESTAMP_PATTERN;
import static com.canon.cusa.s21.batch.NXX.NXXB001001.constant.NXXB001001Constant.ZZSM4101E;
import static com.canon.cusa.s21.batch.NXX.NXXB001001.constant.NXXB001001Constant.ZZZM9025E;

import java.math.BigDecimal;

import parts.dbcommon.EZDTBLAccessor;
import business.db.NXXI0120_01TMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * S21 IVP Outbound Batch
 *
 * Date       Company  Name          Create/Update   Defect No
 * -----------------------------------------------------------------------
 * 19/04/2019 CITS     Nitai      Create         QC#31014
 *</pre>
 */
public class NXXB001001 extends S21BatchMain {

    /** global company code */
    private String glblCmpyCd = "";

    /** interface ID */
    private String interfaceId = "";

    /** transaction ID */
    private BigDecimal transactionId;

    /** total correct count */
    private int totalSuccessCount = 0;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /**
     * @param args
     */
    public static void main(String[] args) {

        new NXXB001001().executeBatch(NXXB001001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Interface ID
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(ZZZM9025E, new String[] {MSG_ITEM_INTERFACE_ID });
        }
    }

    @Override
    protected void mainRoutine() {

        // create transaction ID.
        S21TransactionTableAccessor s21TrxTblAccessor = new S21TransactionTableAccessor();
        transactionId = s21TrxTblAccessor.getNextTransactionId();

        // TMsg of IF Tables
        NXXI0120_01TMsg outBoundTMsg = new NXXI0120_01TMsg();
        ZYPEZDItemValueSetter.setValue(outBoundTMsg.interfaceId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(outBoundTMsg.transactionId, this.transactionId);
        ZYPEZDItemValueSetter.setValue(outBoundTMsg.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(outBoundTMsg.unitId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(outBoundTMsg.seqNumber, BigDecimal.ONE);

        String rcvTs = ZYPDateUtil.getCurrentSystemTime(TIMESTAMP_PATTERN);
        ZYPEZDItemValueSetter.setValue(outBoundTMsg.intfcTestTxt, rcvTs);
        S21FastTBLAccessor.insert(outBoundTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outBoundTMsg.getReturnCode())) {
            throw new S21AbendException(ZZSM4101E, new String[] {"It failed to insert NXXI0120_01.", "","", ""});
        }
        totalSuccessCount++;

        //Create Interface Transaction record.
        s21TrxTblAccessor.createIntegrationRecordForBatch(interfaceId, transactionId);
    }

    /**
     * Termination process
     */
    @Override
    protected void termRoutine() {

        setTermState(this.termCd, this.totalSuccessCount, 0);
    }
}
