/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB135001;

import static com.canon.cusa.s21.batch.NFB.NFBB135001.constant.NFBB135001Constant.FETCH_SIZE_MAX;
import static com.canon.cusa.s21.batch.NFB.NFBB135001.constant.NFBB135001Constant.NFBM0199E;
import static com.canon.cusa.s21.batch.NFB.NFBB135001.constant.NFBB135001Constant.NFBM0207E;
import static com.canon.cusa.s21.batch.NFB.NFBB135001.constant.NFBB135001Constant.NFBM0297E;
import static com.canon.cusa.s21.batch.NFB.NFBB135001.constant.NFBB135001Constant.NFBM0298E;
import static com.canon.cusa.s21.batch.NFB.NFBB135001.constant.NFBB135001Constant.NFBM0299E;
import static com.canon.cusa.s21.batch.NFB.NFBB135001.constant.NFBB135001Constant.NZZM0012E;
import static com.canon.cusa.s21.batch.NFB.NFBB135001.constant.NFBB135001Constant.CUSA_PRNT_VND_CD;
import static com.canon.cusa.s21.batch.NFB.NFBB135001.constant.NFBB135001Constant.DEFAULT_CUSA_PRNT_VND_CD;
import static com.canon.cusa.s21.batch.NFB.NFBB135001.constant.NFBB135001Constant.AP_VND_INV_NUM;
import static com.canon.cusa.s21.batch.NFB.NFBB135001.constant.NFBB135001Constant.AC_INV_TOT_COST_AMT;
import static com.canon.cusa.s21.batch.NFB.NFBB135001.constant.NFBB135001Constant.AP_PMT_CHK_NUM;
import static com.canon.cusa.s21.batch.NFB.NFBB135001.constant.NFBB135001Constant.ARCS_PMT_AMT;
import static com.canon.cusa.s21.batch.NFB.NFBB135001.constant.NFBB135001Constant.NFCA0010_01_CUST_CD;
import static com.canon.cusa.s21.batch.NFB.NFBB135001.constant.NFBB135001Constant.ARCS_PMT_DT;
import static com.canon.cusa.s21.batch.NFB.NFBB135001.constant.NFBB135001Constant.AP_VND_INV_SQ_NUM;
import static com.canon.cusa.s21.batch.NFB.NFBB135001.constant.NFBB135001Constant.AP_VND_CD;
import static com.canon.cusa.s21.batch.NFB.NFBB135001.constant.NFBB135001Constant.ITRL_ID_CSA_CUSA_WS;
import static com.canon.cusa.s21.batch.NFB.NFBB135001.constant.NFBB135001Constant.NFCA0010;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.CM_AP_INV_HDRTMsg;
import business.db.NFCA0010_01TMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21IntTransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * CSA Payment to CUSA IF
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/04/05   Hitachi         R.Takau         Create          QC#61036
 * </pre>
 */
public class NFBB135001 extends S21BatchMain {

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales date */
    private String slsDt = null;

    /** Error Count */
    private int errorCount = 0;

    /** Normal Count */
    private int normalCount = 0;

    /** Parent Vendor Code : Parent Vendor Code */
    private String cusaVendorCode;

    /** VAR_CHAR_CONST : SELL_TO_CUST_CD */
    private String sellToCustCd;

    /** Transaction Accesor */
    private S21IntTransactionTableAccessor intTrxaccessor = null;

    /** Transaction ID */
    private BigDecimal transactionId = null;

    @Override
    protected void initRoutine() {

        // "Global Company Code" Mandatory
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NFBM0199E);
        }

        // "Batch Process Date"
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NFBM0207E, new String[] {"Batch Process Date" });
        }

        // Get Parent Vendor Code : Parent Vendor Code CUSA_PRNT_VND_CD
        this.cusaVendorCode = ZYPCodeDataUtil.getVarCharConstValue(CUSA_PRNT_VND_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.cusaVendorCode)) {
            this.cusaVendorCode = DEFAULT_CUSA_PRNT_VND_CD;
        }

        // Get SELL_TO_CUST_CD : D114
        this.sellToCustCd = ZYPCodeDataUtil.getVarCharConstValue(NFCA0010_01_CUST_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.sellToCustCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NZZM0012E, new String[] {"SELL_TO_CUST_CD"});
        }

        // initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    }

    @Override
    protected void mainRoutine() {
        doProcess();
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFBB135001().executeBatch(NFBB135001.class.getSimpleName());
    }

    private void doProcess() {

        PreparedStatement ps = null;
        ResultSet rs = null;

        Map<String, Object> stesParam = new HashMap<String, Object>();
        stesParam.put("glblCmpyCd", this.glblCmpyCd);
        stesParam.put("prntVndCd", cusaVendorCode);
        stesParam.put("acctInvSts", ACCT_INV_STS.PAID);
        stesParam.put("invNumMaxLength", "12");
        stesParam.put("pmtAmtMaxLength", "16");
        stesParam.put("pmtChkNumMaxLength", "10");

        try {
            ps = this.ssmLLClient.createPreparedStatement("getCusaInv", stesParam, execParam());
            rs = ps.executeQuery();

            this.intTrxaccessor = new S21IntTransactionTableAccessor(ITRL_ID_CSA_CUSA_WS);

            NFCA0010_01TMsg interfaceTbl = new NFCA0010_01TMsg();
            CM_AP_INV_HDRTMsg inMsg = new CM_AP_INV_HDRTMsg();
            CM_AP_INV_HDRTMsg tmsg = new CM_AP_INV_HDRTMsg();

            while (rs.next()) {

                transactionId = intTrxaccessor.getNextTransactionId();
                intTrxaccessor.createIntegrationRecord(NFCA0010, transactionId);

                setValue(interfaceTbl.itrlIntfcId, NFCA0010);
                setValue(interfaceTbl.itrlTrxSq, transactionId);
                setValue(interfaceTbl.apVndInvNum, rs.getString(AP_VND_INV_NUM));
                setValue(interfaceTbl.acInvTotCostAmt, rs.getBigDecimal(AC_INV_TOT_COST_AMT));
                setValue(interfaceTbl.apPmtChkNum, rs.getString(AP_PMT_CHK_NUM));
                setValue(interfaceTbl.arcsPmtAmt, rs.getBigDecimal(ARCS_PMT_AMT));
                setValue(interfaceTbl.sellToCustCd, sellToCustCd);
                setValue(interfaceTbl.arcsPmtDt, rs.getString(ARCS_PMT_DT));

                EZDTBLAccessor.insert(interfaceTbl);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(interfaceTbl.getReturnCode())) {
                    errorCount++;
                    S21InfoLogOutput.println(NFBM0297E, new String[] {rs.getString(AP_VND_INV_NUM)});
                    rollback();
                } else {

                    setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
                    setValue(inMsg.apVndCd, rs.getString(AP_VND_CD));
                    setValue(inMsg.apVndInvNum, rs.getString(AP_VND_INV_NUM));
                    setValue(inMsg.apVndInvSqNum, rs.getString(AP_VND_INV_SQ_NUM));

                    tmsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);
                    if (tmsg == null) {
                       S21InfoLogOutput.println(NFBM0299E, new String[] {rs.getString(AP_VND_INV_NUM)});
                    }
                    setValue(tmsg.cusaIntfcDt, this.slsDt);

                    EZDTBLAccessor.update(tmsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                        errorCount++;
                        S21InfoLogOutput.println(NFBM0298E, new String[] {"CM_AP_INV_HDR", rs.getString(AP_VND_INV_NUM)});
                        rollback();
                    } else {
                        normalCount++;
                        commit();
                    }
                }
            }
        } catch (SQLException e) {
            rollback();
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private S21SsmExecutionParameter execParam() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }
}
