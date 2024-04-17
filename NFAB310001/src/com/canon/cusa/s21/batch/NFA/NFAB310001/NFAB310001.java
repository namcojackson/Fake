/**
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB310001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import parts.dbcommon.EZDTBLAccessor;

import business.db.ACCT_DLY_ACTL_EXCH_RATESTMsg;
import business.db.AJE_ACRL_INTFCTMsg;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;



/**
 * <pre>
 * AJE Link File Creation - AR Receipt
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 6/6/2023     Hitachi         G.Quan          Creat           QC#61088
 *</pre>
 */
public class NFAB310001 extends S21BatchMain {
    /** SQL Access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** yyyyMMdd */
    static final String YYYYMMDD = "yyyyMMdd";

    /** Termination Code */
    private TERM_CD termCd;

    /** Processing Count */
    private int totalRecordCnt = 0;

    /** normal Count */
    private int normalRecordCnt = 0;

    /** err Count */
    private int errorRecordCnt = 0;

    /** GLOBAL_COMPANY_CODE */
    private String glblCmpyCd = null;

    /** Currency Code */
    private String ccyCode = null;

    /**Exchange Rate */
    private BigDecimal exchRate = BigDecimal.ONE;

    /** last month */
    private String glDt = null;

    /** interfaceId */
    private String interfaceId = null;

    /** Sales Date */
    private String slsDt = null;

    /** month start index */
    static final int MONTH_START_INDEX = 4;

    /** month end index */
    static final int MONTH_END_INDEX = 6;

    /**
     * Debug Entry Point
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFAB310001().executeBatch(NFAB310001.class.getSimpleName());
    }

    /**
     * Init Routine
     */
    @Override
    protected void initRoutine() {

        this.glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            this.errorRecordCnt++;
            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
            throw new S21AbendException("ZZXM0020E", new String[] {"Global Company Code"});
        }

        this.interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            this.errorRecordCnt++;
            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
            throw new S21AbendException("ZZXM0020E", new String[] {"interface Id"});
        }

        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(slsDt)) {
            this.errorRecordCnt++;
            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
            throw new S21AbendException("ZZXM0020E", new String[] {"Sales Date" });
        }

        this.termCd = TERM_CD.NORMAL_END;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    }

    /**
     * Main Routine
     */
    @Override
    protected void mainRoutine() {
        execute();
    }

    /**
     */
    private void execute() {
        // Sales Date of last month
        this.glDt = getGlDt();

        // Currency Code
        this.ccyCode = getCcyCd();

        // Exchange Rate
        ACCT_DLY_ACTL_EXCH_RATESTMsg acctDlyActlExchRatesTMsg = new ACCT_DLY_ACTL_EXCH_RATESTMsg();
        ZYPEZDItemValueSetter.setValue(acctDlyActlExchRatesTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(acctDlyActlExchRatesTMsg.ccyCd, this.ccyCode);
        ZYPEZDItemValueSetter.setValue(acctDlyActlExchRatesTMsg.actlExchRateEntDt,ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
        acctDlyActlExchRatesTMsg = (ACCT_DLY_ACTL_EXCH_RATESTMsg) S21FastTBLAccessor.findByKey(acctDlyActlExchRatesTMsg);

        if (acctDlyActlExchRatesTMsg != null) {
            this.exchRate = acctDlyActlExchRatesTMsg.actlExchRate.getValue();
        }

        // S21TransactionTableAccessor
        S21TransactionTableAccessor trxAccess = new S21TransactionTableAccessor();

        // Get Transaction Id List
        BigDecimal[] trxIdList = trxAccess.getIntegrationRecord(this.interfaceId);
        if (trxIdList == null) {
            return;
        }

        for (BigDecimal trxId : trxIdList) {
            PreparedStatement stmt = null;
            ResultSet rs = null;

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("transactionId", trxId);
            paramMap.put("interfaceId", this.interfaceId);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            int commitGoCount = 0;
            try {
                stmt = this.ssmLLClient.createPreparedStatement("getInputData", paramMap, execParam);
                rs = stmt.executeQuery();

                AJE_ACRL_INTFCTMsg insertTMsg = new AJE_ACRL_INTFCTMsg();

                while (rs.next()) {
                    insertTMsg.clear();
                    // insert
                    setValues(rs, insertTMsg);
                    insertTbl(insertTMsg);
                    commitGoCount++;
                }

            } catch (SQLException e) {
                sqlExceptionHandler(e);
                // if any error occurs, rollback
                rollback();
                return;
            } finally {
                S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            }

            if (commitGoCount > 0) {
                trxAccess.endIntegrationProcess(this.interfaceId, trxId);
                commit();
            }
        }
    }
    /**
     * Term Routine
     */
    @Override
    protected void termRoutine() {
        this.totalRecordCnt += this.normalRecordCnt;
        setTermState(this.termCd, this.normalRecordCnt, 0, this.totalRecordCnt);
    }

    private void setValues(ResultSet rs, AJE_ACRL_INTFCTMsg insertTMsg) throws SQLException {

        ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.ajeAcrlIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal("AJE_ACRL_INTFC_SQ"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.glDt, this.glDt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.sysSrcCd,  SYS_SRC.S21_ACCOUNTING_AJE);
        ZYPEZDItemValueSetter.setValue(insertTMsg.trxCd, rs.getString("TRX_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.trxRsnCd, rs.getString("TRX_RSN_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dealCcyCd, this.ccyCode);
        ZYPEZDItemValueSetter.setValue(insertTMsg.funcCcyCd, this.ccyCode);
        ZYPEZDItemValueSetter.setValue(insertTMsg.exchRate, this.exchRate);
        ZYPEZDItemValueSetter.setValue(insertTMsg.jrnlDealAmt, rs.getBigDecimal("JRNL_DEAL_AMT"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.jrnlFuncAmt, rs.getBigDecimal("JRNL_DEAL_AMT"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.coaCmpyCd, rs.getString("COA_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.coaBrCd, rs.getString("COA_BR_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.coaCcCd, rs.getString("COA_CC_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.coaAcctCd, rs.getString("COA_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.coaProdCd, rs.getString("COA_PROD_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.coaChCd, rs.getString("COA_CH_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.coaAfflCd, rs.getString("COA_AFFL_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.coaProjCd, rs.getString("COA_PROJ_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.coaExtnCd, rs.getString("COA_EXTN_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.slsRepCrPct, rs.getBigDecimal("SLS_REP_CR_PCT"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.serNum, rs.getString("SER_NUM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.svcMachMstrPk, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.svcConfigMstrPk, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsContrPk, rs.getBigDecimal("DS_CONTR_PK"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsContrNum, rs.getString("DS_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsContrDtlPk, rs.getBigDecimal("DS_CONTR_DTL_PK"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.tocCd, rs.getString("TOC_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.ajeItemCd, rs.getString("AJE_ITEM_CD"));
        ZYPEZDItemValueSetter.setValue(insertTMsg.ajeItemDescTxt, rs.getString("AJE_ITEM_DESC_TXT"));
    }

    /**
     * insertTbl
     * @param rs Result
     */
    private void insertTbl(AJE_ACRL_INTFCTMsg insertTMsg) {
        EZDTBLAccessor.insert(insertTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
            this.errorRecordCnt++;
            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
            throw new S21AbendException("NFAM1300E");
        }
        this.normalRecordCnt++;

    }

    private String getGlDt() {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);

        String slsDtMonth = this.slsDt.substring(MONTH_START_INDEX, MONTH_END_INDEX);
        Date slsDtDate = null;
        try {
            slsDtDate = sdf.parse(this.slsDt);
        } catch (ParseException e) {
            e.printStackTrace();
            this.errorRecordCnt++;
            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
            throw new S21AbendException("NFAM0212E");
        }

        Calendar calc = Calendar.getInstance();
        calc.setTime(slsDtDate);

        String calcString = sdf.format(calc.getTime());
        String month = calcString.substring(MONTH_START_INDEX, MONTH_END_INDEX);
        while (month.equals(slsDtMonth)) {
            calc.add(Calendar.DATE, -1);
            calcString = sdf.format(calc.getTime());
            month = calcString.substring(MONTH_START_INDEX, MONTH_END_INDEX);
        }
        String date = sdf.format(calc.getTime());

        return date;
    }

    /**
     * getCcyCd
     * @return ccyCd String
     */
    private String getCcyCd() {
        // Currency Code
        String ccyCd = "";
        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21FastTBLAccessor.findByKey(glblCmpyTMsg);
        if (glblCmpyTMsg != null) {
            ccyCd = glblCmpyTMsg.stdCcyCd.getValue();
        } else {
            ccyCd = CCY.US_DOLLAR;
        }
        return ccyCd;
    }
}
