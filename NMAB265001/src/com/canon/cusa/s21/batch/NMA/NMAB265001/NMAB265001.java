/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB265001;

import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.CHAR_COMMA;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.COND_EXP_AND;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.COND_EXP_REPLACE_STR;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.COND_EXP_TRTY_RULE_OPRD_TP_BETWEEN;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.COND_EXP_TRTY_RULE_OPRD_TP_EQUAL;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.COND_EXP_TRTY_RULE_TP_ACCOUNT_CLASSIFICATION;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.COND_EXP_TRTY_RULE_TP_ACCOUNT_NUMBER_CUSTOMER;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.COND_EXP_TRTY_RULE_TP_ACCOUNT_NUMBER_PROSPECT;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.COND_EXP_TRTY_RULE_TP_ACCOUNT_OR_SITE_NUMBER;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.COND_EXP_TRTY_RULE_TP_ACCOUNT_OR_SITE_NUMBER_CUSTOMER;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.COND_EXP_TRTY_RULE_TP_CUSTOMERGROUP;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.COND_EXP_TRTY_RULE_TP_POSTAL_CODE;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.COND_EXP_TRTY_RULE_TP_SIC_CODE;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.COND_EXP_TRTY_RULE_TP_STATE;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.CONST_CUST_GATHER_TABLE_BEFORE_INS_LOC;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.CONST_CUST_GATHER_TABLE_INIT;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.CONST_DB_SCHEMA;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.CONST_PROS_GATHER_TABLE_BEFORE_INS_LOC;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.CONST_PROS_GATHER_TABLE_INIT;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.CONST_TRTY_RULE_OPRD_TP;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.CONST_TRTY_RULE_TP;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.TABLE_TRTY_RULE_LOC_RELN;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.TABLE_TRTY_RULE_LOC_RELN_LOG;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.TABLE_TRTY_RULE_PROS_RELN;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.TABLE_TRTY_RULE_PROS_RELN_LOG;
import static com.canon.cusa.s21.batch.NMA.NMAB265001.Constant.NMAB265001Constant.ZZZM9026E;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDDBCICarrier;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_LOGIC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_OPRD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.internal.S21BatchTransactionQuery;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.util.S21StopWatch;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * NMAB2650 Sales Territory Visibility Defaulting Processor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         K.Minamide      Create          N/A
 * 2016/03/16   Fujitsu         H.Ito           Update          QC#4443
 * 2016/03/29   Fujitsu         N.Sugiura       Update          QC#5909
 * 2016/07/05   Fujitsu         R.Nakamura      Update          QC#5909
 * 2016/07/28   SRAA            Y.Chen          Update          QC#12571
 * 2017/08/24   Fujitsu         W.Honda         Update          QC#20535,20538
 * 2017/10/25   Fujitsu         M.Ohno          Update          QC#21425
 * 2017/11/21   Fujitsu         Hd.Sugawara     Update          QC#22193
 * 2018/02/22   Fujitsu         S.Ohki          Update          QC#22968
 * 2018/03/09   Fujitsu         Mz.Takahashi    Update          QC#23671
 * 2018/03/20   Fujitsu         Mz.Takahashi    Update          QC#23671(Second Fix)
 * 2018/03/20   Fujitsu         S.Ohki          Update          QC#22968(Uncoment)
 * 2018/06/01   Fujitsu         Hd.Sugawara     Update          QC#24293
 * 2018/08/22   Fujitsu         M.Ohno          Update          QC#26772
 * 2019/05/23   Fujitsu         M.Ohno          Update          QC#50095
 *</pre>
 */
public class NMAB265001 extends S21BatchMain {

    /**
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NMAB265001().executeBatch(NMAB265001.class.getSimpleName());
    }

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Commit Transaction Count */
    private int commitTransactionCount = 0;

    // DEL START 2016/03/29 QC#5909
//    /** Process Multiplicity Number */
//    private int processMultiplicityNumber = 0;
//
//    /** Process Control Number */
//    private int processControlNumber = 0;
//
//    /** Limited Process Count */
//    private int limitedProcessCount = 0;
    // DEL END   2016/03/29 QC#5909
    /** Direct Sales Account Type */
    private String directSalesAccountType = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Total Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    // Add Start 2016/07/05 QC#5909
    /** SQL access parts */
    private S21SsmBatchClientCustom ssmBatchClientCustom = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;
    // Add End 2016/07/05 QC#5909

    // Add Start 2017/08/24 QC#20535,20538
    /** Process Multiplicity Number */
    private int processMultiplicityNumber = 0;

    /** Process Control Number */
    private int processControlNumber = 0;

    /** sales date time */
    private String slsDt = null;
    // Mod End 2017/08/24 QC#20535,20538

    // Add Start 2018/03/20 QC#23671(Second Fix)
    /** DataBase Scheme Name */
    private String dbSchema = null;
    // Add End 2018/03/20 QC#23671(Second Fix)

    @Override
    protected void initRoutine() {

        // Mod Start 2017/08/24 QC#20535,20538

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Global Company Code" });
        }

        this.commitTransactionCount = getCommitCount();
        if (this.commitTransactionCount < 0) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Commit Transaction Count" });
        }

        try {
            this.processMultiplicityNumber = Integer.valueOf(getUserVariable1());
        } catch (NumberFormatException e) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Multiplicity Number" });
        }

        try {
            this.processControlNumber = Integer.valueOf(getUserVariable2());
        } catch (NumberFormatException e) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Process Control Number" });
        }

        // 2016/03/16 QC#4443 Mod Start
        try {
            directSalesAccountType = getUserVariable3();
            if (!DS_ACCT_TP.PROSPECT.equals(directSalesAccountType) && !DS_ACCT_TP.CUSTOMER.equals(directSalesAccountType)) {
                throw new S21AbendException(ZZZM9026E, new String[] {"Direct Sales Account Type" });
            }
        } catch (NumberFormatException e) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Direct Sales Account Type" });
        }
        // 2016/03/16 QC#4443 Mod End
        this.slsDt = ZYPDateUtil.getSalesDate();

        // Initialization of SSM Custom
        this.ssmBatchClientCustom = new S21SsmBatchClientCustom(this.getClass());

        // Add Start 2016/07/06 QC#5909
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // Add End 2016/07/06 QC#5909

        // Add Start 2018/03/20 QC#23671(Second Fix)
        this.dbSchema = ZYPCodeDataUtil.getVarCharConstValue(CONST_DB_SCHEMA, glblCmpyCd);

        updateStatistics(true);
        // Add End 2018/03/20 QC#23671(Second Fix)

//        this.glblCmpyCd = super.getGlobalCompanyCode();
//        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
//            throw new S21AbendException(ZZZM9026E, new String[] {"Global Company Code" });
//        }
//
//        this.commitTransactionCount = super.getCommitCount();
//        if (this.commitTransactionCount < 0) {
//            throw new S21AbendException(ZZZM9026E, new String[] {"Commit Transaction Count" });
//        }
//
//        // DEL START 2016/03/29 QC#5909
////        try {
////            this.processMultiplicityNumber = Integer.valueOf(super.getUserVariable1());
////        } catch (NumberFormatException e) {
////            throw new S21AbendException(ZZZM9026E, new String[] {"Multiplicity Number" });
////        }
////
////        try {
////            this.processControlNumber = Integer.valueOf(super.getUserVariable2());
////        } catch (NumberFormatException e) {
////            throw new S21AbendException(ZZZM9026E, new String[] {"Process Control Number" });
////        }
//        // DEL END   2016/03/29 QC#5909
//
//// MOD START 2016/03/16 QC#4443
////        try {
////            this.limitedProcessCount = Integer.valueOf(super.getUserVariable3());
////        } catch (NumberFormatException e) {
////            throw new S21AbendException(ZZZM9026E, new String[] {"Limited Process Count" });
////        }
//
//        // Add Start 2016/07/05 QC#5909
//        this.ssmBatchClientCustom = new S21SsmBatchClientCustom(this.getClass());
//        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
//        // Add End 2016/07/05 QC#5909
//
//        try {
//            this.directSalesAccountType = super.getUserVariable3();
//            if (!ZYPCommonFunc.hasValue(this.directSalesAccountType)) {
//                throw new S21AbendException(ZZZM9026E, new String[] {"Direct Sales Account Type" });
//            }
//        } catch (NumberFormatException e) {
//            throw new S21AbendException(ZZZM9026E, new String[] {"Direct Sales Account Type" });
//        }
//// MOD END   2016/03/16 QC#4443
        // Mod End 2017/08/24 QC#20535,20538
    }

// MOD START 2016/03/16 QC#4443
    @Override
    protected void mainRoutine() {
        // Mod Start 2017/08/24 QC#20535,20538
        // From NMAB265001 mainRoutine.
        mainProcessForDelTempData();

        // From NMAB265002 mainRoutine.
        mainProcessForCratTempData();

        // From NMAB265003 mainRoutine.
        // DEL START 2018/03/09 QC#23671
        //mainProcessForDelAsgData();
        // DEL END 2018/03/09 QC#23671

        // From NMAB265004 mainRoutine.
        // DEL START 2018/03/09 QC#23671
        //mainProcessForCratAsgData();
        // DEL END 2018/03/09 QC#23671

//        if (DS_ACCT_TP.CUSTOMER.equals(this.directSalesAccountType)) {
//            procCustomer();
//        } else if (DS_ACCT_TP.PROSPECT.equals(this.directSalesAccountType)) {
//            procProspect();
//        } else {
//            return;
//        }
//
//        // DEL START 2016/03/29 QC#5909
//        // super.commit();
//        // DEL END   2016/03/29 QC#5909
        // Mod End 2017/08/24 QC#20535,20538
    }

    // Add Start 2017/08/24 QC#20535,20538
    @Override
    protected void termRoutine() {
        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);
    }
    // Add End 2017/08/24 QC#20535,20538

    // Add Start 2017/08/24 QC#20535,20538
    /**
     * main Process : 01(Delete Temporary data)
     */
    private void mainProcessForDelTempData() {
        if (DS_ACCT_TP.CUSTOMER.equals(this.directSalesAccountType)) {
            procCustomerForDelTempData();
        } else if (DS_ACCT_TP.PROSPECT.equals(this.directSalesAccountType)) {
            procProspectForDelTempData();
        } else {
            return;
        }
    }
    // Add End 2017/08/24 QC#20535,20538

    /**
     * Process for Direct Sales Account Type : Customer.
     */
    // Mod Start 2017/08/24 QC#20535,20538
//    private void procCustomer() {
    private void procCustomerForDelTempData() {
    // Mod End 2017/08/24 QC#20535,20538
        // Mod Start 2016/07/05 QC#5909
//        TRTY_RULE_LOC_RELN_LOGTMsg deleteLogTMsg = new TRTY_RULE_LOC_RELN_LOGTMsg();
//        ZYPEZDItemValueSetter.setValue(deleteLogTMsg.glblCmpyCd, this.glblCmpyCd);
//        S21FastTBLAccessor.removeByPartialValue(deleteLogTMsg, new String[] {"glblCmpyCd" });
        
        S21StopWatch sw = new S21StopWatch();
        sw.start();

        Map<String, String> paramsTRLRL = new HashMap<String, String>();
        paramsTRLRL.put("tableNameTRLRL", TABLE_TRTY_RULE_LOC_RELN_LOG);
        ssmBatchClientCustom.delete("truncateTableForDelTempData", paramsTRLRL);
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:truncateTableForDelTempData]End [%s]", sw.getElapsedMilliSec()));
        // Mod End 2016/07/05 QC#5909

        // Mod Start 2016/07/06 QC#5909
//        TRTY_RULE_LOC_RELNTMsg searchCond = new TRTY_RULE_LOC_RELNTMsg();
//        searchCond.setSQLID("001");
//        searchCond.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
//        EZDTMsgArray selectTMsgs = EZDTBLAccessor.findByCondition(searchCond);
//
//        TRTY_RULE_LOC_RELN_LOGTMsg insertTMsg = new TRTY_RULE_LOC_RELN_LOGTMsg();
//        int commitCount = 0;
//        for (int i = 0; i < selectTMsgs.getValidCount(); i++) {
//            totalCount++;
//            TRTY_RULE_LOC_RELNTMsg selectedTMsg = (TRTY_RULE_LOC_RELNTMsg) selectTMsgs.get(i);
//            insertTMsg.clear();
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezTableID, selectedTMsg.ezTableID);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezCancelFlag, selectedTMsg.ezCancelFlag);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezInTime, selectedTMsg.ezInTime);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezInCompanyCode, selectedTMsg.ezInCompanyCode);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezInUserID, selectedTMsg.ezInUserID);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID, selectedTMsg.ezUpAplID);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTime, selectedTMsg.ezUpTime);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTimeZone, selectedTMsg.ezUpTimeZone);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpCompanyCode, selectedTMsg.ezUpCompanyCode);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpUserID, selectedTMsg.ezUpUserID);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID, selectedTMsg.ezUpAplID);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, selectedTMsg.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.trtyRulePk, selectedTMsg.trtyRulePk);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNum, selectedTMsg.dsAcctNum);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.locNum, selectedTMsg.locNum);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNm, selectedTMsg.dsAcctNm);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctTpCd, selectedTMsg.dsAcctTpCd);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.srcLineBizTpCd, selectedTMsg.srcLineBizTpCd);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.trtyTpCd, selectedTMsg.trtyTpCd);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.trtyGrpTpCd, selectedTMsg.trtyGrpTpCd);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.orgRankNum, selectedTMsg.orgRankNum);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.orgCd, selectedTMsg.orgCd);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ptyLocPk, selectedTMsg.ptyLocPk);
//
//            S21FastTBLAccessor.insert(insertTMsg);
//            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
//                throw new S21AbendException("MMAM0003E", new String[] {"TRTY_RULE_LOC_RELN_LOG"});
//            }
//            if (commitCount >= this.commitTransactionCount) {
//                commit();
//                totalNmlCount = totalNmlCount + commitCount;
//                commitCount = 0;
//
//            }
//            commitCount++;
//
//        }
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        Map<String, String> insertParamsTRLRL = new HashMap<String, String>();
        insertParamsTRLRL.put("glblCmpyCd", this.glblCmpyCd);
        insertParamsTRLRL.put("tableNameTRLRL", TABLE_TRTY_RULE_LOC_RELN);
//        int commitCount = 0;

        try {
            
// QC#12571
//            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
//            execParam.setFetchSize(FETCH_SIZE);
//            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
//            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
//            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
//
//            stmtSelect = this.ssmLLClient.createPreparedStatement("searchInsertTBL", insertParamsTRLRL, execParam);
//            rs = stmtSelect.executeQuery();
//
//            List<TRTY_RULE_LOC_RELN_LOGTMsg> insertTMsgList = new ArrayList<TRTY_RULE_LOC_RELN_LOGTMsg>();
//
//            while (rs.next()) {
//                totalCount++;
//                TRTY_RULE_LOC_RELN_LOGTMsg insertTMsg = new TRTY_RULE_LOC_RELN_LOGTMsg();
//
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezTableID, rs.getString("EZTABLEID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezCancelFlag, rs.getString("EZCANCELFLAG"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInTime, rs.getString("EZINTIME"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInCompanyCode, rs.getString("EZINCOMPANYCODE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInUserID, rs.getString("EZINUSERID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID, rs.getString("EZINAPLID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTime, rs.getString("EZUPTIME"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTimeZone, rs.getString("EZUPTIMEZONE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpCompanyCode, rs.getString("EZUPCOMPANYCODE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpUserID, rs.getString("EZUPUSERID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID, rs.getString("EZUPAPLID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.trtyRulePk, rs.getBigDecimal("TRTY_RULE_PK"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.locNum, rs.getString("LOC_NUM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctTpCd, rs.getString("DS_ACCT_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.srcLineBizTpCd, rs.getString("SRC_LINE_BIZ_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.trtyTpCd, rs.getString("TRTY_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.trtyGrpTpCd, rs.getString("TRTY_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.orgRankNum, rs.getString("ORG_RANK_NUM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.orgCd, rs.getString("ORG_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ptyLocPk, rs.getBigDecimal("PTY_LOC_PK"));
//
//                insertTMsgList.add(insertTMsg);
//
//                if (insertTMsgList.size() >= this.commitTransactionCount) {
//                    int resCnt = S21FastTBLAccessor.insert(insertTMsgList.toArray(new TRTY_RULE_LOC_RELN_LOGTMsg[0]));
//                    if (resCnt != insertTMsgList.size()) {
//                        rollback();
//                        throw new S21AbendException("MMAM0003E", new String[] {"TRTY_RULE_LOC_RELN_LOG" });
//                    } else {
//                        commit();
//                        this.totalNmlCount += insertTMsgList.size();
//                    }
//                    insertTMsgList.clear();
//                }
//            }            
            String dateTime = EZDDBCICarrier.getStartDateTime();
            String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
            String upPgId = EZDDBCICarrier.getUppgID();
            String upTimeZone = EZDDBCICarrier.getUpTimeZone();
            String userId = EZDDBCICarrier.getUserID();

            insertParamsTRLRL.put("ezintime", dateTime);
            insertParamsTRLRL.put("ezintimezone", upTimeZone);
            insertParamsTRLRL.put("ezincompanycode", upCmpyCd);
            insertParamsTRLRL.put("ezinuserid", userId);
            insertParamsTRLRL.put("ezinaplid", upPgId);

            insertParamsTRLRL.put("ezuptime", dateTime);
            insertParamsTRLRL.put("ezuptimezone", upTimeZone);
            insertParamsTRLRL.put("ezupcompanycode", upCmpyCd);
            insertParamsTRLRL.put("ezupuserid", userId);
            insertParamsTRLRL.put("ezupaplid", upPgId);

            stmtSelect = this.ssmLLClient.createPreparedStatement("searchInsertTBLForDelTempData", insertParamsTRLRL);
            // No Need DelTempData count
            sw.start();
            stmtSelect.executeUpdate();
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_SQL:searchInsertTBLForDelTempData]End [%s]", sw.getElapsedMilliSec()));

//            this.totalNmlCount += count;
//            this.totalCount += count;
            commit();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
        // Mod End 2016/07/06 QC#5909

        // Mod Start 2016/07/05 QC#5909
//        TRTY_RULE_LOC_RELNTMsg deleteTMsg = new TRTY_RULE_LOC_RELNTMsg();
//        ZYPEZDItemValueSetter.setValue(deleteTMsg.glblCmpyCd, this.glblCmpyCd);
//        S21FastTBLAccessor.removeByPartialValue(deleteTMsg, new String[] {"glblCmpyCd" });
        Map<String, String> paramsTRLR = new HashMap<String, String>();
        paramsTRLR.put("tableNameTRLR", TABLE_TRTY_RULE_LOC_RELN);
        sw.start();
        ssmBatchClientCustom.delete("truncateTableForDelTempData", paramsTRLR);
        // Mod End 2016/07/05 QC#5909
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:searchInsertTBLForDelTempData]End [%s]", sw.getElapsedMilliSec()));
        commit();
        // No Need DelTempData count
//        totalNmlCount = totalNmlCount + commitCount;
    }

    /**
     * Process for Direct Sales Account Type : Prospect.
     */
//    private void procProspect() {
    private void procProspectForDelTempData() {
        // Mod Start 2016/07/05 QC#5909
//        TRTY_RULE_PROS_RELN_LOGTMsg deleteLogTMsg = new TRTY_RULE_PROS_RELN_LOGTMsg();
//        ZYPEZDItemValueSetter.setValue(deleteLogTMsg.glblCmpyCd, this.glblCmpyCd);
//        S21FastTBLAccessor.removeByPartialValue(deleteLogTMsg, new String[] {"glblCmpyCd" });
        Map<String, String> paramsTRPRL = new HashMap<String, String>();
        paramsTRPRL.put("tableNameTRPRL", TABLE_TRTY_RULE_PROS_RELN);
        S21StopWatch sw = new S21StopWatch();
        sw.start();
        ssmBatchClientCustom.delete("truncateTableForDelTempData", paramsTRPRL);
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:truncateTableForDelTempData_TRPRL]End [%s]", sw.getElapsedMilliSec()));
        
        // Mod End 2016/07/05 QC#5909

        // Mod Start 2016/07/06 QC#5909
//        TRTY_RULE_PROS_RELNTMsg searchCond = new TRTY_RULE_PROS_RELNTMsg();
//        searchCond.setSQLID("001");
//        searchCond.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
//        EZDTMsgArray selectTMsgs = EZDTBLAccessor.findByCondition(searchCond);
//        // MOD START 2016/03/29 QC#5909
//        // TRTY_RULE_PROS_RELN_LOGTMsg[] insertTMsgs = new TRTY_RULE_PROS_RELN_LOGTMsg[selectTMsgs.getValidCount()];
////        TRTY_RULE_LOC_RELN_LOGTMsg insertTMsg = new TRTY_RULE_LOC_RELN_LOGTMsg();
//        TRTY_RULE_PROS_RELN_LOGTMsg insertTMsg = new TRTY_RULE_PROS_RELN_LOGTMsg();
//        int commitCount = 0;
//        // MOD END   2016/03/29 QC#5909
//        for (int i = 0; i < selectTMsgs.getValidCount(); i++) {
//            // ADD START 2016/03/29 QC#5909
//            totalCount++;
//            // ADD END   2016/03/29 QC#5909
//            TRTY_RULE_PROS_RELNTMsg selectedTMsg = (TRTY_RULE_PROS_RELNTMsg) selectTMsgs.get(i);
//            // MOD START 2016/03/29 QC#5909
//            // TRTY_RULE_PROS_RELN_LOGTMsg insertTMsg = new TRTY_RULE_PROS_RELN_LOGTMsg();
//            insertTMsg.clear();
//            // MOD END   2016/03/29 QC#5909
//
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezTableID, selectedTMsg.ezTableID);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezCancelFlag, selectedTMsg.ezCancelFlag);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezInTime, selectedTMsg.ezInTime);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezInCompanyCode, selectedTMsg.ezInCompanyCode);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezInUserID, selectedTMsg.ezInUserID);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID, selectedTMsg.ezUpAplID);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTime, selectedTMsg.ezUpTime);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTimeZone, selectedTMsg.ezUpTimeZone);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpCompanyCode, selectedTMsg.ezUpCompanyCode);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpUserID, selectedTMsg.ezUpUserID);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID, selectedTMsg.ezUpAplID);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, selectedTMsg.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.trtyRulePk, selectedTMsg.trtyRulePk);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNum, selectedTMsg.dsAcctNum);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.locNum, selectedTMsg.locNum);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNm, selectedTMsg.dsAcctNm);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctTpCd, selectedTMsg.dsAcctTpCd);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.srcLineBizTpCd, selectedTMsg.srcLineBizTpCd);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.trtyTpCd, selectedTMsg.trtyTpCd);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.trtyGrpTpCd, selectedTMsg.trtyGrpTpCd);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.orgRankNum, selectedTMsg.orgRankNum);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.orgCd, selectedTMsg.orgCd);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.ptyLocPk, selectedTMsg.ptyLocPk);
//            // MOD START 2016/03/29 QC#5909
//            // insertTMsgs[i] = insertTMsg;
//            S21FastTBLAccessor.insert(insertTMsg);
//            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
//                throw new S21AbendException("MMAM0003E", new String[] {"TRTY_RULE_PROS_RELN_LOG"});
//            }
//            if (commitCount >= this.commitTransactionCount) {
//                commit();
//                totalNmlCount = totalNmlCount + commitCount;
//                commitCount = 0;
//
//            }
//            commitCount++;
//            // MOD END 2016/03/29 QC#5909
//        }
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        Map<String, String> insertParamsTRLRL = new HashMap<String, String>();
        insertParamsTRLRL.put("glblCmpyCd", this.glblCmpyCd);
        insertParamsTRLRL.put("tableNameTRPRL", TABLE_TRTY_RULE_PROS_RELN_LOG);
//        int commitCount = 0;

        try {
// QC#12571
//            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
//            execParam.setFetchSize(FETCH_SIZE);
//            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
//            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
//            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
//
//            stmtSelect = this.ssmLLClient.createPreparedStatement("searchInsertTBL", insertParamsTRLRL, execParam);
//            rs = stmtSelect.executeQuery();
//
//            List<TRTY_RULE_PROS_RELN_LOGTMsg> insertTMsgList = new ArrayList<TRTY_RULE_PROS_RELN_LOGTMsg>(this.commitTransactionCount);
//
//            while (rs.next()) {
//                totalCount++;
//                TRTY_RULE_PROS_RELN_LOGTMsg insertTMsg = new TRTY_RULE_PROS_RELN_LOGTMsg();
//
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezTableID, rs.getString("EZTABLEID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezCancelFlag, rs.getString("EZCANCELFLAG"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInTime, rs.getString("EZINTIME"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInCompanyCode, rs.getString("EZINCOMPANYCODE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInUserID, rs.getString("EZINUSERID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID, rs.getString("EZINAPLID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTime, rs.getString("EZUPTIME"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTimeZone, rs.getString("EZUPTIMEZONE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpCompanyCode, rs.getString("EZUPCOMPANYCODE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpUserID, rs.getString("EZUPUSERID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID, rs.getString("EZUPAPLID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.trtyRulePk, rs.getBigDecimal("TRTY_RULE_PK"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.locNum, rs.getString("LOC_NUM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctTpCd, rs.getString("DS_ACCT_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.srcLineBizTpCd, rs.getString("SRC_LINE_BIZ_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.trtyTpCd, rs.getString("TRTY_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.trtyGrpTpCd, rs.getString("TRTY_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.orgRankNum, rs.getString("ORG_RANK_NUM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.orgCd, rs.getString("ORG_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ptyLocPk, rs.getBigDecimal("PTY_LOC_PK"));
//
//                insertTMsgList.add(insertTMsg);
//
//                if (insertTMsgList.size() >= this.commitTransactionCount) {
//                    int resCnt = S21FastTBLAccessor.insert(insertTMsgList.toArray(new TRTY_RULE_PROS_RELN_LOGTMsg[0]));
//                    if (resCnt != insertTMsgList.size()) {
//                        rollback();
//                        throw new S21AbendException("MMAM0003E", new String[] {"TRTY_RULE_PROS_RELN_LOG" });
//                    } else {
//                        commit();
//                        this.totalNmlCount += insertTMsgList.size();
//                    }
//                    insertTMsgList.clear();
//                }
//            }
            String dateTime = EZDDBCICarrier.getStartDateTime();
            String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
            String upPgId = EZDDBCICarrier.getUppgID();
            String upTimeZone = EZDDBCICarrier.getUpTimeZone();
            String userId = EZDDBCICarrier.getUserID();

            insertParamsTRLRL.put("ezintime", dateTime);
            insertParamsTRLRL.put("ezintimezone", upTimeZone);
            insertParamsTRLRL.put("ezincompanycode", upCmpyCd);
            insertParamsTRLRL.put("ezinuserid", userId);
            insertParamsTRLRL.put("ezinaplid", upPgId);

            insertParamsTRLRL.put("ezuptime", dateTime);
            insertParamsTRLRL.put("ezuptimezone", upTimeZone);
            insertParamsTRLRL.put("ezupcompanycode", upCmpyCd);
            insertParamsTRLRL.put("ezupuserid", userId);
            insertParamsTRLRL.put("ezupaplid", upPgId);

            stmtSelect = this.ssmLLClient.createPreparedStatement("searchInsertTBLForDelTempData", insertParamsTRLRL);
            // No Need DelTempData count
            sw.start();
            stmtSelect.executeUpdate();
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_SQL:searchInsertTBLForDelTempData_TRPRL]End [%s]", sw.getElapsedMilliSec()));
//            this.totalNmlCount += count;
//            this.totalCount += count;
            commit();
            
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
        // Mod Start 2016/07/06 QC#5909

        // DEL START 2016/03/29 QC#5909
        // if (insertTMsgs.length > 0) {
        //     int insCnt = S21FastTBLAccessor.insert(insertTMsgs);
        //     this.totalCount += insCnt;
        //     this.totalNmlCount += insCnt;
        // }
        // DEL END 2016/03/29 QC#5909

        // Mod Start 2016/07/05 QC#5909
//        TRTY_RULE_PROS_RELNTMsg deleteTMsg = new TRTY_RULE_PROS_RELNTMsg();
//        ZYPEZDItemValueSetter.setValue(deleteTMsg.glblCmpyCd, this.glblCmpyCd);
//        S21FastTBLAccessor.removeByPartialValue(deleteTMsg, new String[] {"glblCmpyCd" });
        Map<String, String> paramsTRPR = new HashMap<String, String>();
        paramsTRPR.put("tableNameTRPR", TABLE_TRTY_RULE_PROS_RELN);
        sw.start();
        ssmBatchClientCustom.delete("truncateTableForDelTempData", paramsTRPR);
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:truncateTableForDelTempData_TRPR]End [%s]", sw.getElapsedMilliSec()));
        // Mod End 2016/07/05 QC#5909

        // ADD START 2016/03/29 QC#5909
        commit();
        // No Need DelTempData count
//        totalNmlCount = totalNmlCount + commitCount;
        // ADD END   2016/03/29 QC#5909
    }
// MOD END   2016/03/16 QC#4443

    /**
     * main Process : 02(Create Temporary data)
     */
    private void mainProcessForCratTempData() {
        S21StopWatch sw = new S21StopWatch();
        String constTrtyRuleTp = ZYPCodeDataUtil.getVarCharConstValue(CONST_TRTY_RULE_TP, glblCmpyCd);
        List<String> trtyRuleTpList = Arrays.asList(constTrtyRuleTp.split(","));

        // Add Start 2018/06/01 QC#24293
        String constTrtyRuleOprdTp = ZYPCodeDataUtil.getVarCharConstValue(CONST_TRTY_RULE_OPRD_TP, glblCmpyCd);
        List<String> trtyRuleOprdTpList = Arrays.asList(constTrtyRuleOprdTp.split(","));
        // Add End 2018/06/01 QC#24293

        String dateTime = EZDDBCICarrier.getStartDateTime();
        String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
        String upPgId = EZDDBCICarrier.getUppgID();
        String upTimeZone = EZDDBCICarrier.getUpTimeZone();
        String userId = EZDDBCICarrier.getUserID();

        //--- TRTY_RULE_LOGIC_TP_CD [OR]
        // doing trtyRuleTpCd and trtyRuleOprdTpCd Matrix pattern
        for (String trtyRuleTpCdStr : trtyRuleTpList) {
            // Mod Start 2018/06/01 QC#24293
            //for (int trtyRuleOprdTpCd = 2; trtyRuleOprdTpCd <= TRTY_RULE_LOGIC_TP_PTN; trtyRuleOprdTpCd++) {
            for (String trtyRuleOprdTpCdStr : trtyRuleOprdTpList) {
                // Mod End 2018/06/01 QC#24293

                // Del Start 2018/06/01 QC#24293
                //String trtyRuleOprdTpCdStr = String.valueOf(trtyRuleOprdTpCd);
                // Del End 2018/06/01 QC#24293

                // Operand Rule In is Skip
                if(TRTY_RULE_OPRD_TP.IN.equals(trtyRuleOprdTpCdStr)
                   // Mod Start 2018/06/01 QC#24293
                   //&& (TRTY_RULE_TP.POSTAL_CODE.equals(trtyRuleTpCdStr) 
                   //    || TRTY_RULE_TP.ACCOUNT_NAME.equals(trtyRuleTpCdStr)
                   //    || TRTY_RULE_TP.LOCATION_NUMBER.equals(trtyRuleTpCdStr)
                   //)
                   // Mod End 2018/06/01 QC#24293
                ) {
                    continue;
                }

                // Add Start 2018/06/01 QC#24293
                if (TRTY_RULE_OPRD_TP.BETWEEN.equals(trtyRuleOprdTpCdStr) //
                        && !TRTY_RULE_TP.POSTAL_CODE.equals(trtyRuleTpCdStr) //
                ) {
                    continue;
                }
                // Add End 2018/06/01 QC#24293

                //-------truncate Work Table
                Map<String, String> paramsATRAL = new HashMap<String, String>();
                paramsATRAL.put("glblCmpyCd", this.glblCmpyCd);
                // Add Start 2017/08/21 QC#20593
                if (DS_ACCT_TP.PROSPECT.equals(directSalesAccountType)) {
                    paramsATRAL.put("prospect", ZYPConstant.FLG_ON_Y);
                } else if (DS_ACCT_TP.CUSTOMER.equals(directSalesAccountType)) {
                    paramsATRAL.put("customer", ZYPConstant.FLG_ON_Y);
                }
                // Add End 2017/08/21 QC#20593
                sw.start();
                ssmBatchClientCustom.delete("truncateTableForCratTempData", paramsATRAL);
                sw.stop();
                S21InfoLogOutput.println(String.format("[PFM_SQL:truncateTableForCratTempData_%s]End [%s]", directSalesAccountType, sw.getElapsedMilliSec()));

                //-------insert Work Table
                Map<String, Object> ssmGetTrtyWrkParam = new HashMap<String, Object>();

                // Add Start 2017/08/21 QC#20593
//                ssmGetTrtyWrkParam.put("tableId", "TRTY_RULE_WRK");
                if (DS_ACCT_TP.PROSPECT.equals(directSalesAccountType)) {
                    ssmGetTrtyWrkParam.put("tableId", "TRTY_RULE_PROS_WRK");
                    ssmGetTrtyWrkParam.put("prospect", ZYPConstant.FLG_ON_Y);
                } else if (DS_ACCT_TP.CUSTOMER.equals(directSalesAccountType)) {
                    ssmGetTrtyWrkParam.put("tableId", "TRTY_RULE_WRK");
                    ssmGetTrtyWrkParam.put("customer", ZYPConstant.FLG_ON_Y);
                }
                // Add End 2017/08/21 QC#20593
                ssmGetTrtyWrkParam.put("ezintime", dateTime);
                ssmGetTrtyWrkParam.put("ezintimezone", upTimeZone);
                ssmGetTrtyWrkParam.put("ezincompanycode", upCmpyCd);
                ssmGetTrtyWrkParam.put("ezinuserid", userId);
                ssmGetTrtyWrkParam.put("ezinaplid", upPgId);
                ssmGetTrtyWrkParam.put("ezuptime", dateTime);
                ssmGetTrtyWrkParam.put("ezuptimezone", upTimeZone);
                ssmGetTrtyWrkParam.put("ezupcompanycode", upCmpyCd);
                ssmGetTrtyWrkParam.put("ezupuserid", userId);
                ssmGetTrtyWrkParam.put("ezupaplid", upPgId);

                ssmGetTrtyWrkParam.put("glblCmpyCd", glblCmpyCd);
                ssmGetTrtyWrkParam.put("slsDt", slsDt);

                //----TRTY RULE
                ssmGetTrtyWrkParam.put("trtyRuleTpCd", trtyRuleTpCdStr);
                if (TRTY_RULE_TP.STATE.equals(trtyRuleTpCdStr)) {
                    ssmGetTrtyWrkParam.put("trtyRuleTpState", ZYPConstant.FLG_ON_Y);
                }
                else if (TRTY_RULE_TP.POSTAL_CODE.equals(trtyRuleTpCdStr)) {
                    ssmGetTrtyWrkParam.put("trtyRuleTpPostalCode", ZYPConstant.FLG_ON_Y);
                }
                // Del Start 2018/06/01 QC#24293
                //else if (TRTY_RULE_TP.ACCOUNT_NAME.equals(trtyRuleTpCdStr)) {
                //    ssmGetTrtyWrkParam.put("trtyRuleTpAccountName", ZYPConstant.FLG_ON_Y);
                //}
                // Del End 2018/06/01 QC#24293
                else if (TRTY_RULE_TP.LOCATION_NUMBER.equals(trtyRuleTpCdStr)) {
                    ssmGetTrtyWrkParam.put("trtyRuleTpAccountOrSiteNumber", ZYPConstant.FLG_ON_Y);
                }
                else if (TRTY_RULE_TP.ACCOUNT_CLASSIFICATION.equals(trtyRuleTpCdStr)) {
                    ssmGetTrtyWrkParam.put("trtyRuleTpAccountClassification", ZYPConstant.FLG_ON_Y);
                }
                else if (TRTY_RULE_TP.CUSTOMERGROUP.equals(trtyRuleTpCdStr)) {
                    ssmGetTrtyWrkParam.put("trtyRuleTpCustomerGroup", ZYPConstant.FLG_ON_Y);
                }
                else if (TRTY_RULE_TP.SIC_CODE.equals(trtyRuleTpCdStr)) {
                    ssmGetTrtyWrkParam.put("trtyRuleTpSicCode", ZYPConstant.FLG_ON_Y);
                }

                //----TRTY_RULE OPRD
                ssmGetTrtyWrkParam.put("trtyRuleOprdTpCd", trtyRuleOprdTpCdStr);
                if (TRTY_RULE_OPRD_TP.EQUAL.equals(trtyRuleOprdTpCdStr)) {
                    ssmGetTrtyWrkParam.put("trtyRuleOprdTpCdEqual", ZYPConstant.FLG_ON_Y);
                }
                else if (TRTY_RULE_OPRD_TP.BETWEEN.equals(trtyRuleOprdTpCdStr)) {
                    ssmGetTrtyWrkParam.put("trtyRuleOprdTpCdBetween", ZYPConstant.FLG_ON_Y);
                }
                // Del Start 2018/06/01 QC#24293
                //else if (TRTY_RULE_OPRD_TP.LIKE.equals(trtyRuleOprdTpCdStr)) {
                //    ssmGetTrtyWrkParam.put("trtyRuleOprdTpCdLike", ZYPConstant.FLG_ON_Y);
                //}
                //else if (TRTY_RULE_OPRD_TP.IN.equals(trtyRuleOprdTpCdStr)) {
                //    ssmGetTrtyWrkParam.put("trtyRuleOprdTpCdIn", ZYPConstant.FLG_ON_Y);
                //}
                // Del End 2018/06/01 QC#24293
                //Rule Table
                ssmGetTrtyWrkParam.put("defSlsRepBatFlg", ZYPConstant.FLG_ON_Y);
                ssmGetTrtyWrkParam.put("gnrnTpCurrent", GNRN_TP.CURRENT);
                ssmGetTrtyWrkParam.put("gnrnTpFuture", GNRN_TP.FUTURE);
                ssmGetTrtyWrkParam.put("trtyRuleLogicTpCdOr", TRTY_RULE_LOGIC_TP.OR);

                sw.start();
                this.ssmBatchClientCustom.insert("insertTrtyWrk", ssmGetTrtyWrkParam);
                sw.stop();
                S21InfoLogOutput.println(String.format("[PFM_SQL:insertTrtyWrk_%s]End [%s]", directSalesAccountType, sw.getElapsedMilliSec()));
                commit();

                // Add Start 2018/03/20 QC#23671(Second Fix)
                updateStatistics(false);
                // Add Start 2018/03/20 QC#23671(Second Fix)

                //-------insert Table
                Map<String, Object> ssmGetLocationParam = new HashMap<String, Object>();
//                ssmGetLocationParam.put("processMultiplicityNumber", String.valueOf(processMultiplicityNumber));
//                ssmGetLocationParam.put("processControlNumber", String.valueOf(processControlNumber));
                ssmGetLocationParam.put("glblCmpyCd", glblCmpyCd);
                ssmGetLocationParam.put("slsDt", slsDt);
                //Rule Table
                ssmGetLocationParam.put("defSlsRepBatFlg", ZYPConstant.FLG_ON_Y);
                ssmGetLocationParam.put("gnrnTpCurrent", GNRN_TP.CURRENT);
                ssmGetLocationParam.put("gnrnTpFuture", GNRN_TP.FUTURE);
                ssmGetLocationParam.put("trtyRuleLogicTpCdOr", TRTY_RULE_LOGIC_TP.OR);
                //Location Table
                ssmGetLocationParam.put("rgtnStsTerminated", RGTN_STS.TERMINATED);
                // 2019/05/23 QC#50095 Add Start
                ssmGetLocationParam.put("rgtnStsActive", RGTN_STS.READY_FOR_ORDER_TAKING);
                // 2019/05/23 QC#50095 Add End
                ssmGetLocationParam.put("activeFlag", ZYPConstant.FLG_ON_Y);
                ssmGetLocationParam.put("getLocationsForOr", ZYPConstant.FLG_ON_Y);
                ssmGetLocationParam.put("locGrpCd_Customer", LOC_GRP.CUSTOMER);
                if (DS_ACCT_TP.PROSPECT.equals(directSalesAccountType)) {
                    ssmGetLocationParam.put("prospect", ZYPConstant.FLG_ON_Y);
                } else if (DS_ACCT_TP.CUSTOMER.equals(directSalesAccountType)) {
                    ssmGetLocationParam.put("customer", ZYPConstant.FLG_ON_Y);
                }

                if (DS_ACCT_TP.PROSPECT.equals(directSalesAccountType)) {
                    ssmGetLocationParam.put("tableId", "TRTY_RULE_PROS_RELN");
                } else if (DS_ACCT_TP.CUSTOMER.equals(directSalesAccountType)) {
                    ssmGetLocationParam.put("tableId", "TRTY_RULE_LOC_RELN");
                }

                if(TRTY_RULE_OPRD_TP.BETWEEN.equals(trtyRuleOprdTpCdStr) 
                        && TRTY_RULE_TP.POSTAL_CODE.equals(trtyRuleTpCdStr)) {
                    ssmGetLocationParam.put("isPostBetween", ZYPConstant.FLG_ON_Y);
                }

                ssmGetLocationParam.put("ezintime", dateTime);
                ssmGetLocationParam.put("ezintimezone", upTimeZone);
                ssmGetLocationParam.put("ezincompanycode", upCmpyCd);
                ssmGetLocationParam.put("ezinuserid", userId);
                ssmGetLocationParam.put("ezinaplid", upPgId);

                ssmGetLocationParam.put("ezuptime", dateTime);
                ssmGetLocationParam.put("ezuptimezone", upTimeZone);
                ssmGetLocationParam.put("ezupcompanycode", upCmpyCd);
                ssmGetLocationParam.put("ezupuserid", userId);
                ssmGetLocationParam.put("ezupaplid", upPgId);

                //----TRTY RULE
                ssmGetLocationParam.put("trtyRuleTpCd", trtyRuleTpCdStr);
                if (TRTY_RULE_TP.STATE.equals(trtyRuleTpCdStr)) {
                    ssmGetLocationParam.put("trtyRuleTpState", ZYPConstant.FLG_ON_Y);
                }
                else if (TRTY_RULE_TP.POSTAL_CODE.equals(trtyRuleTpCdStr)) {
                    ssmGetLocationParam.put("trtyRuleTpPostalCode", ZYPConstant.FLG_ON_Y);
                }
                // Mod Start 2018/06/01 QC#24293
                //else if (TRTY_RULE_TP.ACCOUNT_NAME.equals(trtyRuleTpCdStr)) {
                //    ssmGetLocationParam.put("trtyRuleTpAccountName", ZYPConstant.FLG_ON_Y);
                //}
                else if (TRTY_RULE_TP.ACCOUNT_NUMBER.equals(trtyRuleTpCdStr)) {
                    ssmGetLocationParam.put("trtyRuleTpAccountNumber", ZYPConstant.FLG_ON_Y);
                }
                // Mod End 2018/06/01 QC#24293
                else if (TRTY_RULE_TP.LOCATION_NUMBER.equals(trtyRuleTpCdStr)) {
                    ssmGetLocationParam.put("trtyRuleTpAccountOrSiteNumber", ZYPConstant.FLG_ON_Y);
                }
                else if (TRTY_RULE_TP.ACCOUNT_CLASSIFICATION.equals(trtyRuleTpCdStr)) {
                    ssmGetLocationParam.put("trtyRuleTpAccountClassification", ZYPConstant.FLG_ON_Y);
                }
                else if (TRTY_RULE_TP.CUSTOMERGROUP.equals(trtyRuleTpCdStr)) {
                    ssmGetLocationParam.put("trtyRuleTpCustomerGroup", ZYPConstant.FLG_ON_Y);
                }
                else if (TRTY_RULE_TP.SIC_CODE.equals(trtyRuleTpCdStr)) {
                    ssmGetLocationParam.put("trtyRuleTpSicCode", ZYPConstant.FLG_ON_Y);
                }

                //----TRTY_RULE OPRD
                ssmGetLocationParam.put("trtyRuleOprdTpCd", trtyRuleOprdTpCdStr);
                if (TRTY_RULE_OPRD_TP.EQUAL.equals(trtyRuleOprdTpCdStr)) {
                    ssmGetLocationParam.put("trtyRuleOprdTpCdEqual", ZYPConstant.FLG_ON_Y);
                }
                else if (TRTY_RULE_OPRD_TP.BETWEEN.equals(trtyRuleOprdTpCdStr)) {
                    ssmGetLocationParam.put("trtyRuleOprdTpCdBetween", ZYPConstant.FLG_ON_Y);
                }
                // Del Start 2018/06/01 QC#24293
                //else if (TRTY_RULE_OPRD_TP.LIKE.equals(trtyRuleOprdTpCdStr)) {
                //    ssmGetLocationParam.put("trtyRuleOprdTpCdLike", ZYPConstant.FLG_ON_Y);
                //}
                //else if (TRTY_RULE_OPRD_TP.IN.equals(trtyRuleOprdTpCdStr)) {
                //    ssmGetLocationParam.put("trtyRuleOprdTpCdIn", ZYPConstant.FLG_ON_Y);
                //}
                // Del End 2018/06/01 QC#24293

                // 2018/02/22 QC#22968 Add Start
                ssmGetLocationParam.put("gnrnTpCd", GNRN_TP.CURRENT);
                ssmGetLocationParam.put("trtyGrpTpCdEss", TRTY_GRP_TP.ESS);
                ssmGetLocationParam.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
                // 2018/02/22 QC#22968 Add End

                //Get And Insert Loc Info
                // No Need CratTempData count
                sw.start();
                this.ssmBatchClientCustom.insert("insertLocations", ssmGetLocationParam);
                sw.stop();
                S21InfoLogOutput.println(String.format("[PFM_SQL:insertLocations_%s]End [%s]", directSalesAccountType, sw.getElapsedMilliSec()));
//                totalNmlCount += insertCount;
                commit();
            }
        }

        //--- TRTY_RULE_LOGIC_TP_CD [AND]
        // doing trtyRuleTpCd trtyRuleOprdTpCd by Org Code
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        try {

            //Get Rule By Org Code List
            Map<String, Object> ssmGetOrgCdListParam = new HashMap<String, Object>();
            ssmGetOrgCdListParam.put("processMultiplicityNumber", String.valueOf(processMultiplicityNumber));
            ssmGetOrgCdListParam.put("processControlNumber", String.valueOf(processControlNumber));
            ssmGetOrgCdListParam.put("glblCmpyCd", glblCmpyCd);
            ssmGetOrgCdListParam.put("slsDt", slsDt);
            ssmGetOrgCdListParam.put("defSlsRepBatFlg", ZYPConstant.FLG_ON_Y);
            ssmGetOrgCdListParam.put("gnrnTpCurrent", GNRN_TP.CURRENT);
            ssmGetOrgCdListParam.put("gnrnTpFuture", GNRN_TP.FUTURE);
            ssmGetOrgCdListParam.put("trtyRuleTpList", trtyRuleTpList);
            ssmGetOrgCdListParam.put("trtyRuleLogicTpCdAnd", TRTY_RULE_LOGIC_TP.AND);
            
            ssmGetOrgCdListParam.put("state", TRTY_RULE_TP.STATE);
            ssmGetOrgCdListParam.put("postalCode", TRTY_RULE_TP.POSTAL_CODE);
            // Mod Start 2018/06/01 QC#24293
            //ssmGetOrgCdListParam.put("accountName", TRTY_RULE_TP.ACCOUNT_NAME );
            ssmGetOrgCdListParam.put("accountNumber", TRTY_RULE_TP.ACCOUNT_NUMBER);
            // Mod End 2018/06/01 QC#24293
            ssmGetOrgCdListParam.put("locationNumber", TRTY_RULE_TP.LOCATION_NUMBER);
            ssmGetOrgCdListParam.put("accountClassification", TRTY_RULE_TP.ACCOUNT_CLASSIFICATION);
            ssmGetOrgCdListParam.put("customerGroup", TRTY_RULE_TP.CUSTOMERGROUP);
            ssmGetOrgCdListParam.put("sicCode", TRTY_RULE_TP.SIC_CODE);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            stmtSelect = this.ssmLLClient.createPreparedStatement("getOrgAndRuleList", ssmGetOrgCdListParam, execParam);
            sw.start();
            rs = stmtSelect.executeQuery();
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_SQL:getOrgAndRuleList]End [%s]", sw.getElapsedMilliSec()));
            
            Map<String, Object> ssmGetLocationParam = new HashMap<String, Object>();
            while (rs.next()) {

                ssmGetLocationParam.clear();
                ssmGetLocationParam.put("glblCmpyCd", glblCmpyCd);
                ssmGetLocationParam.put("slsDt", slsDt);
                ssmGetLocationParam.put("rgtnStsTerminated", RGTN_STS.TERMINATED);
                ssmGetLocationParam.put("activeFlag", ZYPConstant.FLG_ON_Y);
                ssmGetLocationParam.put("locGrpCd_Customer", LOC_GRP.CUSTOMER);
                ssmGetLocationParam.put("trtyRuleTpCd", "");      // Dummy Value
                ssmGetLocationParam.put("trtyRuleOprdTpCd", "");  // Dummy Value
                ssmGetLocationParam.put("gnrnTpCurrent", GNRN_TP.CURRENT);  // Dummy Value
                ssmGetLocationParam.put("gnrnTpFuture", GNRN_TP.FUTURE);    // Dummy Value
                ssmGetLocationParam.put("defSlsRepBatFlg", ZYPConstant.FLG_ON_Y);  // Dummy Value
                if (DS_ACCT_TP.PROSPECT.equals(directSalesAccountType)) {
                    ssmGetLocationParam.put("prospect", ZYPConstant.FLG_ON_Y);
                } else if (DS_ACCT_TP.CUSTOMER.equals(directSalesAccountType)) {
                    ssmGetLocationParam.put("customer", ZYPConstant.FLG_ON_Y);
                }

                if (DS_ACCT_TP.PROSPECT.equals(directSalesAccountType)) {
                    ssmGetLocationParam.put("tableId", "TRTY_RULE_PROS_RELN");
                } else if (DS_ACCT_TP.CUSTOMER.equals(directSalesAccountType)) {
                    ssmGetLocationParam.put("tableId", "TRTY_RULE_LOC_RELN");
                }

                ssmGetLocationParam.put("orgCd"      , rs.getString("ORG_CD"));
                ssmGetLocationParam.put("trtyRulePk" , rs.getString("TRTY_RULE_PK"));
                ssmGetLocationParam.put("trtyGrpTpCd", rs.getString("TRTY_GRP_TP_CD"));
                ssmGetLocationParam.put("trtyTpCd"   , rs.getString("TRTY_TP_CD"));

                ssmGetLocationParam.put("ezintime", dateTime);
                ssmGetLocationParam.put("ezintimezone", upTimeZone);
                ssmGetLocationParam.put("ezincompanycode", upCmpyCd);
                ssmGetLocationParam.put("ezinuserid", userId);
                ssmGetLocationParam.put("ezinaplid", upPgId);

                ssmGetLocationParam.put("ezuptime", dateTime);
                ssmGetLocationParam.put("ezuptimezone", upTimeZone);
                ssmGetLocationParam.put("ezupcompanycode", upCmpyCd);
                ssmGetLocationParam.put("ezupuserid", userId);
                ssmGetLocationParam.put("ezupaplid", upPgId);

                String conditionalExpression = "";

                //----set trtyRuleTpCd
                if (ZYPCommonFunc.hasValue(rs.getString("TR_OPRD_TP_CD_STATE"))) {
                    conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, COND_EXP_AND);
                    conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, COND_EXP_TRTY_RULE_TP_STATE);
                    conditionalExpression = setOprdCondition(rs.getString("TR_OPRD_TP_CD_STATE")
                            , rs.getString("TR_FROM_VAL_TXT_STATE")
                            , rs.getString("TR_THRU_VAL_TXT_STATE"), conditionalExpression);
                }
                if (ZYPCommonFunc.hasValue(rs.getString("TR_OPRD_TP_CD_POSTAL"))) {
                    conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, COND_EXP_AND);
                    conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, COND_EXP_TRTY_RULE_TP_POSTAL_CODE);
                    conditionalExpression = setOprdCondition(rs.getString("TR_OPRD_TP_CD_POSTAL")
                            , rs.getString("TR_FROM_VAL_TXT_POSTAL")
                            , rs.getString("TR_THRU_VAL_TXT_POSTAL"), conditionalExpression);
                }
                // Mod Start 2018/06/01 QC#24293
                //if (ZYPCommonFunc.hasValue(rs.getString("TR_OPRD_TP_CD_ACT_NM"))) {
                //    conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, COND_EXP_AND);
                //    conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, COND_EXP_TRTY_RULE_TP_ACCOUNT_NAME);
                //    conditionalExpression = setOprdCondition(rs.getString("TR_OPRD_TP_CD_ACT_NM")
                //            , rs.getString("TR_FROM_VAL_TXT_ACT_NM")
                //            , rs.getString("TR_THRU_VAL_TXT_ACT_NM"), conditionalExpression);
                //}
                if (ZYPCommonFunc.hasValue(rs.getString("TR_OPRD_TP_CD_ACT_NUM"))) {
                    conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, COND_EXP_AND);
                    if (DS_ACCT_TP.PROSPECT.equals(directSalesAccountType)) {
                        conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, //
                                COND_EXP_TRTY_RULE_TP_ACCOUNT_NUMBER_PROSPECT);
                    } else if (DS_ACCT_TP.CUSTOMER.equals(directSalesAccountType)) {
                        conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, //
                                COND_EXP_TRTY_RULE_TP_ACCOUNT_NUMBER_CUSTOMER);
                    }
                    conditionalExpression = setOprdCondition(rs.getString("TR_OPRD_TP_CD_ACT_NUM")
                            , rs.getString("TR_FROM_VAL_TXT_ACT_NUM")
                            , rs.getString("TR_THRU_VAL_TXT_ACT_NUM"), conditionalExpression);
                }
                // Mod End 2018/06/01 QC#24293
                if (ZYPCommonFunc.hasValue(rs.getString("TR_OPRD_TP_CD_LOC_NUM"))) {
                    conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, COND_EXP_AND);
                    // 2018/08/23 QC#26772 mod start
                    if (DS_ACCT_TP.PROSPECT.equals(directSalesAccountType)) {
                        conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, COND_EXP_TRTY_RULE_TP_ACCOUNT_OR_SITE_NUMBER);
                    } else if (DS_ACCT_TP.CUSTOMER.equals(directSalesAccountType)) {
                        conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, COND_EXP_TRTY_RULE_TP_ACCOUNT_OR_SITE_NUMBER_CUSTOMER);
                    }
//                    conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, COND_EXP_TRTY_RULE_TP_ACCOUNT_OR_SITE_NUMBER);
                    // 2018/08/23 QC#26772 mod end
                    conditionalExpression = setOprdCondition(rs.getString("TR_OPRD_TP_CD_LOC_NUM")
                            , rs.getString("TR_FROM_VAL_TXT_LOC_NUM")
                            , rs.getString("TR_THRU_VAL_TXT_LOC_NUM"), conditionalExpression);
                }
                if (ZYPCommonFunc.hasValue(rs.getString("TR_OPRD_TP_CD_ACT_CLS"))) {
                    conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, COND_EXP_AND);
                    conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, COND_EXP_TRTY_RULE_TP_ACCOUNT_CLASSIFICATION);
                    conditionalExpression = setOprdCondition(rs.getString("TR_OPRD_TP_CD_ACT_CLS")
                            , rs.getString("TR_FROM_VAL_TXT_ACT_CLS")
                            , rs.getString("TR_THRU_VAL_TXT_ACT_CLS"), conditionalExpression);
                }
                if (ZYPCommonFunc.hasValue(rs.getString("TR_OPRD_TP_CD_CUSTGRP"))) {
                    conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, COND_EXP_AND);
                    // 2018/08/23 QC#26772 mod start
                    String acctString = null;
                    if (DS_ACCT_TP.PROSPECT.equals(directSalesAccountType)) {
                        acctString = "AND ACCT.DS_ACCT_NUM = DAGA.DS_ACCT_NUM ";
                    } else if (DS_ACCT_TP.CUSTOMER.equals(directSalesAccountType)) {
                        acctString = "AND ACCT.SELL_TO_CUST_CD = DAGA.DS_ACCT_NUM ";
                    }
                    conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, //
                            "EXISTS (" //
                            , "SELECT " //
                            , "DAGA.DS_ACCT_GRP_ASG_PK " //
                            , "FROM " //
                            , "DS_ACCT_GRP_ASG DAGA " //
                            , "WHERE " //
                            , "DAGA.GLBL_CMPY_CD = '", glblCmpyCd //
                            , "' AND DAGA.EFF_FROM_DT <= TO_CHAR(TO_DATE('", slsDt, "', 'YYYYMMDD') + 1, 'YYYYMMDD') " //
                            , "AND ( DAGA.EFF_THRU_DT    IS NULL " //
                            , "OR DAGA.EFF_THRU_DT >= TO_CHAR(TO_DATE('", slsDt, "', 'YYYYMMDD') + 1, 'YYYYMMDD')) " //
                            , "AND DAGA.EZCANCELFLAG = '0' " //
                            , acctString //
                            , "AND ", COND_EXP_TRTY_RULE_TP_CUSTOMERGROUP);
//                    conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, COND_EXP_TRTY_RULE_TP_CUSTOMERGROUP);
                    conditionalExpression = setOprdCondition(rs.getString("TR_OPRD_TP_CD_CUSTGRP")
                            , rs.getString("TR_FROM_VAL_TXT_CUSTGRP")
                            , rs.getString("TR_THRU_VAL_TXT_CUSTGRP"), conditionalExpression);
                    conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, ")");
                    // 2018/08/23 QC#26772 mod end
                }
                if (ZYPCommonFunc.hasValue(rs.getString("TR_OPRD_TP_CD_SIC"))) {
                    conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, COND_EXP_AND);
                    conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, COND_EXP_TRTY_RULE_TP_SIC_CODE);
                    conditionalExpression = setOprdCondition(rs.getString("TR_OPRD_TP_CD_SIC")
                            , rs.getString("TR_FROM_VAL_TXT_SIC")
                            , rs.getString("TR_THRU_VAL_TXT_SIC"), conditionalExpression);
                }

                ssmGetLocationParam.put("conditionalExpression", conditionalExpression);
                ssmGetLocationParam.put("getLocationsForAdd", ZYPConstant.FLG_ON_Y);

                // 2018/02/22 QC#22968 Add Start
                ssmGetLocationParam.put("gnrnTpCd", GNRN_TP.CURRENT);
                ssmGetLocationParam.put("trtyGrpTpCdEss", TRTY_GRP_TP.ESS);
                ssmGetLocationParam.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
                // 2018/02/22 QC#22968 Add End

                //Get And Insert Loc Info
                // No Need CratTempData count
                sw.start();
                this.ssmBatchClientCustom.insert("insertLocations", ssmGetLocationParam);
                sw.stop();
                S21InfoLogOutput.println(String.format("[PFM_SQL:insertLocations_%s]End [%s]", directSalesAccountType, sw.getElapsedMilliSec()));
//                totalNmlCount += insertCount;
                commit();
            }

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
        
        ///////////////////////////////////////////////////////////////////
        // Move to 'NMAB265002' after that
        ///////////////////////////////////////////////////////////////////

        // DEL START 2018/03/09 QC#23671
        // 2018/02/22 QC#22968 Add Start
        /*
        Map<String, Object> updateCancelFlgParam = new HashMap<String, Object>();
        updateCancelFlgParam.put("glblCmpyCd", glblCmpyCd);
        updateCancelFlgParam.put("trtyGrpTpCdEss", TRTY_GRP_TP.ESS);
        if (DS_ACCT_TP.CUSTOMER.equals(this.directSalesAccountType)) {
        	updateCancelFlgParam.put("customer", ZYPConstant.FLG_ON_Y);
        } else if (DS_ACCT_TP.PROSPECT.equals(this.directSalesAccountType)) {
        	updateCancelFlgParam.put("prospect", ZYPConstant.FLG_ON_Y);
        }
        this.ssmBatchClientCustom.update("setCancelFlagTrtyRuleReln", updateCancelFlgParam);
        commit();
        */
        // 2018/02/22 QC#22968 Add End
        // DEL END 2018/03/09 QC#23671
    }

    private String setOprdCondition(String trtyRuleOprdTpCd, String trtyRuleFromValTxt, String trtyRuleThruValTxt, String conditionalExpression) {
        //----set trtyRuleOprdTpCd
        if (TRTY_RULE_OPRD_TP.EQUAL.equals(trtyRuleOprdTpCd)) {
            conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, COND_EXP_TRTY_RULE_OPRD_TP_EQUAL);
        }
        if (TRTY_RULE_OPRD_TP.BETWEEN.equals(trtyRuleOprdTpCd)) {
            conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, COND_EXP_TRTY_RULE_OPRD_TP_BETWEEN);
        }
        // Del Start 2018/06/01 QC#24293
        //if (TRTY_RULE_OPRD_TP.LIKE.equals(trtyRuleOprdTpCd)) {
        //    conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, COND_EXP_TRTY_RULE_OPRD_TP_LIKE);
        //}
        // Del End 2018/06/01 QC#24293
        if (TRTY_RULE_OPRD_TP.IN.equals(trtyRuleOprdTpCd)) {
            String[] valueList = createInConditionValueList(trtyRuleFromValTxt);
            conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, "IN (");
            for (int i = 0; i < valueList.length; i++) {
                String value = valueList[i];
                if (i > 0) {
                    conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, ",");
                }
                value = quoteSqlParameterValue(value);
                conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, "'" + value + "'");
            }
            conditionalExpression = S21StringUtil.concatStrings(conditionalExpression, ")");
        } else {
            if (ZYPCommonFunc.hasValue(trtyRuleFromValTxt)) {
                conditionalExpression = conditionalExpression.replaceFirst(COND_EXP_REPLACE_STR, trtyRuleFromValTxt);
            }
            if (ZYPCommonFunc.hasValue(trtyRuleThruValTxt)) {
                conditionalExpression = conditionalExpression.replaceFirst(COND_EXP_REPLACE_STR, trtyRuleThruValTxt);
            }
        }
        return conditionalExpression;
    }

    // QC#5786
    private String[] createInConditionValueList(String valueText) {
        if (!ZYPCommonFunc.hasValue(valueText)) {
            return new String[] {"" };
        }
        return valueText.split(CHAR_COMMA);
    }
    
    private String quoteSqlParameterValue(String value) {
        if (!ZYPCommonFunc.hasValue(value)) {
            return value;
        }
        return value.replaceAll("'", "''");
    }

    /**
     * main Process : 03(Delete Assign data)
     */
/*    
    private void mainProcessForDelAsgData() {

        // DEL START 2018/03/09 QC#23671
        //if (DS_ACCT_TP.CUSTOMER.equals(this.directSalesAccountType)) {
        //    procCustomerForDelAsgData();
        //} else if (DS_ACCT_TP.PROSPECT.equals(this.directSalesAccountType)) {
        //    procProspectForDelAsgData();
        //} else {
        //    return;
        //}
        // DEL END 2018/03/09 QC#23671
        
        // Mod Start 2016/07/05 QC#5909
        // ACCT_TRTY_RESRC_LOGTMsg deleteResrcLogTMsg = new
        // ACCT_TRTY_RESRC_LOGTMsg();
        // ZYPEZDItemValueSetter.setValue(deleteResrcLogTMsg.glblCmpyCd,
        // this.glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(deleteResrcLogTMsg.dsAcctTpCd,
        // this.directSalesAccountType);
        // S21FastTBLAccessor.removeByPartialValue(deleteResrcLogTMsg,
        // new String[] {"glblCmpyCd", "dsAcctTpCd" });

//        ResultSet rs = null;
//        PreparedStatement stmtSelect = null;
//        Map<String, String> paramsATRL = new HashMap<String, String>();
//        paramsATRL.put("glblCmpyCd", this.glblCmpyCd);
//        paramsATRL.put("dsAcctTpCd", this.directSalesAccountType);
//
//        try {
// QC#12571
//            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
//            execParam.setFetchSize(FETCH_SIZE);
//            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
//            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
//            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
//
//            stmtSelect = this.ssmLLClient.createPreparedStatement("getDeleteATRLData", paramsATRL, execParam);
//            rs = stmtSelect.executeQuery();
//
//            int deleteCount = 0;
//            List<ACCT_TRTY_RESRC_LOGTMsg> deleteTMsgArray = new ArrayList<ACCT_TRTY_RESRC_LOGTMsg>();
//
//            while (rs.next()) {
//                ACCT_TRTY_RESRC_LOGTMsg deleteTMsg = new ACCT_TRTY_RESRC_LOGTMsg();
//                ZYPEZDItemValueSetter.setValue(deleteTMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
//                ZYPEZDItemValueSetter.setValue(deleteTMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
//                ZYPEZDItemValueSetter.setValue(deleteTMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
//                ZYPEZDItemValueSetter.setValue(deleteTMsg.locNum, rs.getString("LOC_NUM"));
//                deleteTMsgArray.add(deleteTMsg);
//                deleteCount++;
//
//                if (deleteCount >= FETCH_SIZE) {
//                    S21FastTBLAccessor.removePhysical(deleteTMsgArray.toArray(new ACCT_TRTY_RESRC_LOGTMsg[deleteCount]));
//                    // Add Start 2016/07/20 QC#12113
//                    commit();
//                    deleteTMsgArray.clear();
//                    // Add End 2016/07/20 QC#12113
//                    deleteCount = 0;
//                }
//            }
//
//            if (deleteCount > 0) {
//                S21FastTBLAccessor.removePhysical(deleteTMsgArray.toArray(new ACCT_TRTY_RESRC_LOGTMsg[deleteCount]));
//                // Add Start 2016/07/20 QC#12113
//                commit();
//                deleteTMsgArray.clear();
//                // Add End 2016/07/20 QC#12113
//            }
//
//            stmtSelect = this.ssmLLClient.createPreparedStatement("deleteATRLData", paramsATRL);
//            int deleteCount = stmtSelect.executeUpdate();
//            S21InfoLogOutput.println("ACCT_TRTY_RESRC_LOG delete count: [" + deleteCount + "], dsAcctTpCd = [" + this.directSalesAccountType + "]" );
//            commit();
//
//        } catch (SQLException e) {
//            throw new S21AbendException(e);
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
//        }
        // Mod End 2016/07/05 QC#5909

        // Mod Start 2016/07/06 QC#5909
        // ACCT_TRTY_RESRC_ASGTMsg searchCondResrc = new
        // ACCT_TRTY_RESRC_ASGTMsg();
        // searchCondResrc.setSQLID("002");
        // searchCondResrc.setConditionValue("glblCmpyCd01",
        // this.glblCmpyCd);
        // searchCondResrc.setConditionValue("dsAcctTpCd01",
        // this.directSalesAccountType);
        // ACCT_TRTY_RESRC_ASGTMsgArray selectResrcTMsgs =
        // (ACCT_TRTY_RESRC_ASGTMsgArray)
        // EZDTBLAccessor.findByCondition(searchCondResrc);

        // MOD START 2016/03/29 QC#5909
        // ACCT_TRTY_RESRC_LOGTMsg[] insertResrcTMsgs = new
        // ACCT_TRTY_RESRC_LOGTMsg[selectResrcTMsgs.getValidCount()];
        // ACCT_TRTY_RESRC_LOGTMsg insertResrcTMsg = new
        // ACCT_TRTY_RESRC_LOGTMsg();
        // int commitCount = 0;
        // // MOD END 2016/03/29 QC#5909
        // for (int i = 0; i < selectResrcTMsgs.getValidCount(); i++)
        // {
        // // ADD START 2016/03/29 QC#5909
        // totalCount++;
        // // ADD END 2016/03/29 QC#5909
        // ACCT_TRTY_RESRC_ASGTMsg selectedResrcTMsg =
        // (ACCT_TRTY_RESRC_ASGTMsg) selectResrcTMsgs.get(i);
        // // MOD START 2016/03/29 QC#5909
        // // ACCT_TRTY_RESRC_LOGTMsg insertResrcTMsg = new
        // ACCT_TRTY_RESRC_LOGTMsg();
        // insertResrcTMsg.clear();
        // // MOD END 2016/03/29 QC#5909
        //
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezTableID,
        // selectedResrcTMsg.ezTableID);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezCancelFlag,
        // selectedResrcTMsg.ezCancelFlag);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezInTime,
        // selectedResrcTMsg.ezInTime);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezInTimeZone,
        // selectedResrcTMsg.ezInTimeZone);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezInCompanyCode,
        // selectedResrcTMsg.ezInCompanyCode);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezInUserID,
        // selectedResrcTMsg.ezInUserID);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpAplID,
        // selectedResrcTMsg.ezUpAplID);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpTime,
        // selectedResrcTMsg.ezUpTime);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpTimeZone,
        // selectedResrcTMsg.ezUpTimeZone);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpCompanyCode,
        // selectedResrcTMsg.ezUpCompanyCode);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpUserID,
        // selectedResrcTMsg.ezUpUserID);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpAplID,
        // selectedResrcTMsg.ezUpAplID);
        //
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.glblCmpyCd,
        // selectedResrcTMsg.glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.dsAcctNum,
        // selectedResrcTMsg.dsAcctNum);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.dsAcctNm,
        // selectedResrcTMsg.dsAcctNm);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.locNum,
        // selectedResrcTMsg.locNum);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.dsAcctTpCd,
        // selectedResrcTMsg.dsAcctTpCd);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.glblCmpyCd,
        // selectedResrcTMsg.glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.dsAcctNum,
        // selectedResrcTMsg.dsAcctNum);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.dsAcctNm,
        // selectedResrcTMsg.dsAcctNm);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.locNum,
        // selectedResrcTMsg.locNum);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.dsAcctTpCd,
        // selectedResrcTMsg.dsAcctTpCd);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_01,
        // selectedResrcTMsg.acctTrtyOrgCd_01);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_01,
        // selectedResrcTMsg.acctTrtyPsnCd_01);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_01,
        // selectedResrcTMsg.acctTrtyTocCd_01);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_02,
        // selectedResrcTMsg.acctTrtyOrgCd_02);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_02,
        // selectedResrcTMsg.acctTrtyPsnCd_02);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_02,
        // selectedResrcTMsg.acctTrtyTocCd_02);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_03,
        // selectedResrcTMsg.acctTrtyOrgCd_03);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_03,
        // selectedResrcTMsg.acctTrtyPsnCd_03);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_03,
        // selectedResrcTMsg.acctTrtyTocCd_03);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_04,
        // selectedResrcTMsg.acctTrtyOrgCd_04);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_04,
        // selectedResrcTMsg.acctTrtyPsnCd_04);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_04,
        // selectedResrcTMsg.acctTrtyTocCd_04);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_05,
        // selectedResrcTMsg.acctTrtyOrgCd_05);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_05,
        // selectedResrcTMsg.acctTrtyPsnCd_05);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_05,
        // selectedResrcTMsg.acctTrtyTocCd_05);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_06,
        // selectedResrcTMsg.acctTrtyOrgCd_06);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_06,
        // selectedResrcTMsg.acctTrtyPsnCd_06);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_06,
        // selectedResrcTMsg.acctTrtyTocCd_06);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_07,
        // selectedResrcTMsg.acctTrtyOrgCd_07);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_07,
        // selectedResrcTMsg.acctTrtyPsnCd_07);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_07,
        // selectedResrcTMsg.acctTrtyTocCd_07);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_08,
        // selectedResrcTMsg.acctTrtyOrgCd_08);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_08,
        // selectedResrcTMsg.acctTrtyPsnCd_08);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_08,
        // selectedResrcTMsg.acctTrtyTocCd_08);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_09,
        // selectedResrcTMsg.acctTrtyOrgCd_09);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_09,
        // selectedResrcTMsg.acctTrtyPsnCd_09);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_09,
        // selectedResrcTMsg.acctTrtyTocCd_09);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_10,
        // selectedResrcTMsg.acctTrtyOrgCd_10);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_10,
        // selectedResrcTMsg.acctTrtyPsnCd_10);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_10,
        // selectedResrcTMsg.acctTrtyTocCd_10);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_11,
        // selectedResrcTMsg.acctTrtyOrgCd_11);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_11,
        // selectedResrcTMsg.acctTrtyPsnCd_11);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_11,
        // selectedResrcTMsg.acctTrtyTocCd_11);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_12,
        // selectedResrcTMsg.acctTrtyOrgCd_12);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_12,
        // selectedResrcTMsg.acctTrtyPsnCd_12);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_12,
        // selectedResrcTMsg.acctTrtyTocCd_12);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_13,
        // selectedResrcTMsg.acctTrtyOrgCd_13);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_13,
        // selectedResrcTMsg.acctTrtyPsnCd_13);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_13,
        // selectedResrcTMsg.acctTrtyTocCd_13);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_14,
        // selectedResrcTMsg.acctTrtyOrgCd_14);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_14,
        // selectedResrcTMsg.acctTrtyPsnCd_14);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_14,
        // selectedResrcTMsg.acctTrtyTocCd_14);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_15,
        // selectedResrcTMsg.acctTrtyOrgCd_15);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_15,
        // selectedResrcTMsg.acctTrtyPsnCd_15);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_15,
        // selectedResrcTMsg.acctTrtyTocCd_15);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_16,
        // selectedResrcTMsg.acctTrtyOrgCd_16);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_16,
        // selectedResrcTMsg.acctTrtyPsnCd_16);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_16,
        // selectedResrcTMsg.acctTrtyTocCd_16);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_17,
        // selectedResrcTMsg.acctTrtyOrgCd_17);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_17,
        // selectedResrcTMsg.acctTrtyPsnCd_17);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_17,
        // selectedResrcTMsg.acctTrtyTocCd_17);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_18,
        // selectedResrcTMsg.acctTrtyOrgCd_18);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_18,
        // selectedResrcTMsg.acctTrtyPsnCd_18);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_18,
        // selectedResrcTMsg.acctTrtyTocCd_18);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_19,
        // selectedResrcTMsg.acctTrtyOrgCd_19);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_19,
        // selectedResrcTMsg.acctTrtyPsnCd_19);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_19,
        // selectedResrcTMsg.acctTrtyTocCd_19);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_20,
        // selectedResrcTMsg.acctTrtyOrgCd_20);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_20,
        // selectedResrcTMsg.acctTrtyPsnCd_20);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_20,
        // selectedResrcTMsg.acctTrtyTocCd_20);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_01,
        // selectedResrcTMsg.lineBizRoleTpCd_01);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_02,
        // selectedResrcTMsg.lineBizRoleTpCd_02);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_03,
        // selectedResrcTMsg.lineBizRoleTpCd_03);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_04,
        // selectedResrcTMsg.lineBizRoleTpCd_04);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_05,
        // selectedResrcTMsg.lineBizRoleTpCd_05);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_06,
        // selectedResrcTMsg.lineBizRoleTpCd_06);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_07,
        // selectedResrcTMsg.lineBizRoleTpCd_07);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_08,
        // selectedResrcTMsg.lineBizRoleTpCd_08);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_09,
        // selectedResrcTMsg.lineBizRoleTpCd_09);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_10,
        // selectedResrcTMsg.lineBizRoleTpCd_10);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_11,
        // selectedResrcTMsg.lineBizRoleTpCd_11);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_12,
        // selectedResrcTMsg.lineBizRoleTpCd_12);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_13,
        // selectedResrcTMsg.lineBizRoleTpCd_13);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_14,
        // selectedResrcTMsg.lineBizRoleTpCd_14);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_15,
        // selectedResrcTMsg.lineBizRoleTpCd_15);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_16,
        // selectedResrcTMsg.lineBizRoleTpCd_16);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_17,
        // selectedResrcTMsg.lineBizRoleTpCd_17);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_18,
        // selectedResrcTMsg.lineBizRoleTpCd_18);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_19,
        // selectedResrcTMsg.lineBizRoleTpCd_19);
        // ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_20,
        // selectedResrcTMsg.lineBizRoleTpCd_20);
        //
        // // MOD START 2016/03/29 QC#5909
        // // insertResrcTMsgs[i] = insertResrcTMsg;
        // S21FastTBLAccessor.insert(insertResrcTMsg);
        // if
        // (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertResrcTMsg.getReturnCode()))
        // {
        // throw new S21AbendException("MMAM0003E", new String[]
        // {"ACCT_TRTY_RESRC_LOG"});
        // }
        // if (commitCount >= this.commitTransactionCount) {
        // commit();
        // totalNmlCount = totalNmlCount + commitCount;
        // commitCount = 0;
        //
        // }
        // commitCount++;
        // // MOD END 2016/03/29 QC#5909
        // }

// Del Start 2017/05/09 RS#8275
//        rs = null;
//        stmtSelect = null;
//        Map<String, String> insertParamsATRA = new HashMap<String, String>();
//        insertParamsATRA.put("glblCmpyCd", this.glblCmpyCd);
//        insertParamsATRA.put("dsAcctTpCd", this.directSalesAccountType);
//
//        try {
//// QC#12571
////            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
////            execParam.setFetchSize(FETCH_SIZE);
////            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
////            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
////            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
////
////            stmtSelect = this.ssmLLClient.createPreparedStatement("searchInsertATRA", insertParamsATRA, execParam);
////            rs = stmtSelect.executeQuery();
////
////            List<ACCT_TRTY_RESRC_LOGTMsg> insertResrcTMsgList = new ArrayList<ACCT_TRTY_RESRC_LOGTMsg>();
////
////            while (rs.next()) {
////                totalCount++;
////                ACCT_TRTY_RESRC_LOGTMsg insertResrcTMsg = new ACCT_TRTY_RESRC_LOGTMsg();
////
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezTableID, rs.getString("EZTABLEID"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezCancelFlag, rs.getString("EZCANCELFLAG"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezInTime, rs.getString("EZINTIME"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezInTimeZone, rs.getString("EZINTIMEZONE"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezInCompanyCode, rs.getString("EZINCOMPANYCODE"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezInUserID, rs.getString("EZINUSERID"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpAplID, rs.getString("EZINAPLID"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpTime, rs.getString("EZUPTIME"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpTimeZone, rs.getString("EZUPTIMEZONE"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpCompanyCode, rs.getString("EZUPCOMPANYCODE"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpUserID, rs.getString("EZUPUSERID"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.ezUpAplID, rs.getString("EZUPAPLID"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.locNum, rs.getString("LOC_NUM"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.dsAcctTpCd, rs.getString("DS_ACCT_TP_CD"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_01, rs.getString("ACCT_TRTY_ORG_CD_01"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_01, rs.getString("ACCT_TRTY_PSN_CD_01"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_01, rs.getString("ACCT_TRTY_TOC_CD_01"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_02, rs.getString("ACCT_TRTY_ORG_CD_02"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_02, rs.getString("ACCT_TRTY_PSN_CD_02"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_02, rs.getString("ACCT_TRTY_TOC_CD_02"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_03, rs.getString("ACCT_TRTY_ORG_CD_03"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_03, rs.getString("ACCT_TRTY_PSN_CD_03"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_03, rs.getString("ACCT_TRTY_TOC_CD_03"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_04, rs.getString("ACCT_TRTY_ORG_CD_04"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_04, rs.getString("ACCT_TRTY_PSN_CD_04"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_04, rs.getString("ACCT_TRTY_TOC_CD_04"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_05, rs.getString("ACCT_TRTY_ORG_CD_05"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_05, rs.getString("ACCT_TRTY_PSN_CD_05"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_05, rs.getString("ACCT_TRTY_TOC_CD_05"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_06, rs.getString("ACCT_TRTY_ORG_CD_06"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_06, rs.getString("ACCT_TRTY_PSN_CD_06"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_06, rs.getString("ACCT_TRTY_TOC_CD_06"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_07, rs.getString("ACCT_TRTY_ORG_CD_07"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_07, rs.getString("ACCT_TRTY_PSN_CD_07"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_07, rs.getString("ACCT_TRTY_TOC_CD_07"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_08, rs.getString("ACCT_TRTY_ORG_CD_08"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_08, rs.getString("ACCT_TRTY_PSN_CD_08"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_08, rs.getString("ACCT_TRTY_TOC_CD_08"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_09, rs.getString("ACCT_TRTY_ORG_CD_09"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_09, rs.getString("ACCT_TRTY_PSN_CD_09"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_09, rs.getString("ACCT_TRTY_TOC_CD_09"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_10, rs.getString("ACCT_TRTY_ORG_CD_10"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_10, rs.getString("ACCT_TRTY_PSN_CD_10"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_10, rs.getString("ACCT_TRTY_TOC_CD_10"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_11, rs.getString("ACCT_TRTY_ORG_CD_11"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_11, rs.getString("ACCT_TRTY_PSN_CD_11"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_11, rs.getString("ACCT_TRTY_TOC_CD_11"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_12, rs.getString("ACCT_TRTY_ORG_CD_12"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_12, rs.getString("ACCT_TRTY_PSN_CD_12"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_12, rs.getString("ACCT_TRTY_TOC_CD_12"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_13, rs.getString("ACCT_TRTY_ORG_CD_13"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_13, rs.getString("ACCT_TRTY_PSN_CD_13"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_13, rs.getString("ACCT_TRTY_TOC_CD_13"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_14, rs.getString("ACCT_TRTY_ORG_CD_14"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_14, rs.getString("ACCT_TRTY_PSN_CD_14"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_14, rs.getString("ACCT_TRTY_TOC_CD_14"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_15, rs.getString("ACCT_TRTY_ORG_CD_15"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_15, rs.getString("ACCT_TRTY_PSN_CD_15"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_15, rs.getString("ACCT_TRTY_TOC_CD_15"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_16, rs.getString("ACCT_TRTY_ORG_CD_16"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_16, rs.getString("ACCT_TRTY_PSN_CD_16"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_16, rs.getString("ACCT_TRTY_TOC_CD_16"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_17, rs.getString("ACCT_TRTY_ORG_CD_17"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_17, rs.getString("ACCT_TRTY_PSN_CD_17"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_17, rs.getString("ACCT_TRTY_TOC_CD_17"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_18, rs.getString("ACCT_TRTY_ORG_CD_18"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_18, rs.getString("ACCT_TRTY_PSN_CD_18"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_18, rs.getString("ACCT_TRTY_TOC_CD_18"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_19, rs.getString("ACCT_TRTY_ORG_CD_19"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_19, rs.getString("ACCT_TRTY_PSN_CD_19"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_19, rs.getString("ACCT_TRTY_TOC_CD_19"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyOrgCd_20, rs.getString("ACCT_TRTY_ORG_CD_20"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyPsnCd_20, rs.getString("ACCT_TRTY_PSN_CD_20"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.acctTrtyTocCd_20, rs.getString("ACCT_TRTY_TOC_CD_20"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_01, rs.getString("LINE_BIZ_ROLE_TP_CD_01"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_02, rs.getString("LINE_BIZ_ROLE_TP_CD_02"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_03, rs.getString("LINE_BIZ_ROLE_TP_CD_03"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_04, rs.getString("LINE_BIZ_ROLE_TP_CD_04"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_05, rs.getString("LINE_BIZ_ROLE_TP_CD_05"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_06, rs.getString("LINE_BIZ_ROLE_TP_CD_06"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_07, rs.getString("LINE_BIZ_ROLE_TP_CD_07"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_08, rs.getString("LINE_BIZ_ROLE_TP_CD_08"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_09, rs.getString("LINE_BIZ_ROLE_TP_CD_09"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_10, rs.getString("LINE_BIZ_ROLE_TP_CD_10"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_11, rs.getString("LINE_BIZ_ROLE_TP_CD_11"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_12, rs.getString("LINE_BIZ_ROLE_TP_CD_12"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_13, rs.getString("LINE_BIZ_ROLE_TP_CD_13"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_14, rs.getString("LINE_BIZ_ROLE_TP_CD_14"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_15, rs.getString("LINE_BIZ_ROLE_TP_CD_15"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_16, rs.getString("LINE_BIZ_ROLE_TP_CD_16"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_17, rs.getString("LINE_BIZ_ROLE_TP_CD_17"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_18, rs.getString("LINE_BIZ_ROLE_TP_CD_18"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_19, rs.getString("LINE_BIZ_ROLE_TP_CD_19"));
////                ZYPEZDItemValueSetter.setValue(insertResrcTMsg.lineBizRoleTpCd_20, rs.getString("LINE_BIZ_ROLE_TP_CD_20"));
////
////                insertResrcTMsgList.add(insertResrcTMsg);
////
////                if (insertResrcTMsgList.size() >= this.commitTransactionCount) {
////                    int resCnt = S21FastTBLAccessor.insert(insertResrcTMsgList.toArray(new ACCT_TRTY_RESRC_LOGTMsg[0]));
////                    if (resCnt != insertResrcTMsgList.size()) {
////                        rollback();
////                        throw new S21AbendException("MMAM0003E", new String[] {"ACCT_TRTY_RESRC_LOG" });
////                    } else {
////                        commit();
////                        this.totalNmlCount += insertResrcTMsgList.size();
////                    }
////                    insertResrcTMsgList.clear();
////                }
////            }
//
//            String dateTime = EZDDBCICarrier.getStartDateTime();
//            String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
//            String upPgId = EZDDBCICarrier.getUppgID();
//            String upTimeZone = EZDDBCICarrier.getUpTimeZone();
//            String userId = EZDDBCICarrier.getUserID();
//
//            insertParamsATRA.put("ezintime", dateTime);
//            insertParamsATRA.put("ezintimezone", upTimeZone);
//            insertParamsATRA.put("ezincompanycode", upCmpyCd);
//            insertParamsATRA.put("ezinuserid", userId);
//            insertParamsATRA.put("ezinaplid", upPgId);
//
//            insertParamsATRA.put("ezuptime", dateTime);
//            insertParamsATRA.put("ezuptimezone", upTimeZone);
//            insertParamsATRA.put("ezupcompanycode", upCmpyCd);
//            insertParamsATRA.put("ezupuserid", userId);
//            insertParamsATRA.put("ezupaplid", upPgId);
//
//            stmtSelect = this.ssmLLClient.createPreparedStatement("searchInsertATRA", insertParamsATRA);
//            int count = stmtSelect.executeUpdate();
//            this.totalNmlCount += count;
//            this.totalCount += count;
//            commit();
//
//        } catch (SQLException e) {
//            throw new S21AbendException(e);
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
//        }
//        // Mod End 2016/07/06 QC#5909
//        // MOD START 2016/03/29 QC#5909
//        // if (insertResrcTMsgs.length > 0) {
//        // int insCnt = S21FastTBLAccessor.insert(insertResrcTMsgs);
//        // this.totalCount += insCnt;
//        // this.totalNmlCount += insCnt;
//        // }
//        commit();
//        // totalNmlCount = totalNmlCount + commitCount;
//        // MOD END 2016/03/29 QC#5909

//        // Mod Start 2016/07/20 QC#12113
//        rs = null;
//        stmtSelect = null;
//
//        try {
//            Map<String, String> ssmGetRoleNonExistsParam = new HashMap<String, String>();
//            ssmGetRoleNonExistsParam.put("glblCmpyCd", this.glblCmpyCd);
//            ssmGetRoleNonExistsParam.put("dsAcctTpCd", this.directSalesAccountType);
//// QC#12571
//////            List<Map<String, String>> deleteResrcList = this.ssmBatchClient.queryObjectList("getRoleNonExists", ssmGetRoleNonExistsParam);
////            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
////            execParam.setFetchSize(FETCH_SIZE);
////            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
////            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
////            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
////
////            stmtSelect = this.ssmLLClient.createPreparedStatement("getRoleNonExists", ssmGetRoleNonExistsParam, execParam);
////            rs = stmtSelect.executeQuery();
////
//////            if (deleteResrcList.size() > 0) {
//////                ACCT_TRTY_RESRC_ASGTMsg[] deleteResrcArray = new ACCT_TRTY_RESRC_ASGTMsg[deleteResrcList.size()];
//////                for (int i = 0, n = deleteResrcList.size(); i < n; i++) {
//////                    Map<String, String> deleteResrcMap = deleteResrcList.get(i);
//////                    ACCT_TRTY_RESRC_ASGTMsg deleteResrcTmsg = new ACCT_TRTY_RESRC_ASGTMsg();
//////                    ZYPEZDItemValueSetter.setValue(deleteResrcTmsg.glblCmpyCd, deleteResrcMap.get("GLBL_CMPY_CD"));
//////                    ZYPEZDItemValueSetter.setValue(deleteResrcTmsg.dsAcctNum, deleteResrcMap.get("DS_ACCT_NUM"));
//////                    ZYPEZDItemValueSetter.setValue(deleteResrcTmsg.dsAcctNm, deleteResrcMap.get("DS_ACCT_NM"));
//////                    ZYPEZDItemValueSetter.setValue(deleteResrcTmsg.locNum, deleteResrcMap.get("LOC_NUM"));
//////                    deleteResrcArray[i] = deleteResrcTmsg;
//////                }
//////                S21FastTBLAccessor.removePhysical(deleteResrcArray);
//////            }
////
////            List<ACCT_TRTY_RESRC_ASGTMsg> deleteResrcList = new ArrayList<ACCT_TRTY_RESRC_ASGTMsg>();
////            int deleteCount = 0;
////            while(rs.next()) {
////                ACCT_TRTY_RESRC_ASGTMsg deleteResrcTmsg = new ACCT_TRTY_RESRC_ASGTMsg();
////
////                ZYPEZDItemValueSetter.setValue(deleteResrcTmsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
////                ZYPEZDItemValueSetter.setValue(deleteResrcTmsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
////                ZYPEZDItemValueSetter.setValue(deleteResrcTmsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
////                ZYPEZDItemValueSetter.setValue(deleteResrcTmsg.locNum, rs.getString("LOC_NUM"));
////
////                deleteResrcList.add(deleteResrcTmsg);
////                deleteCount++;
////
////                if (deleteCount >= FETCH_SIZE) {
////                    S21FastTBLAccessor.removePhysical(deleteResrcList.toArray(new ACCT_TRTY_RESRC_ASGTMsg[deleteCount]));
////                    commit();
////                    deleteResrcList.clear();
////                    deleteCount = 0;
////                }
////            }
////
////            if (deleteCount > 0) {
////                S21FastTBLAccessor.removePhysical(deleteResrcList.toArray(new ACCT_TRTY_RESRC_ASGTMsg[deleteCount]));
////                commit();
////                deleteResrcList.clear();
////                deleteCount = 0;
////            }
//
//            // 2016/08/24 CSA-QC#12063, 13277 Mod Start
////            stmtSelect = this.ssmLLClient.createPreparedStatement("deleteRoleNonExists", ssmGetRoleNonExistsParam);
//            stmtSelect = this.ssmLLClient.createPreparedStatement("deleteAcctTrtyResrcAsg", ssmGetRoleNonExistsParam);
//            // 2016/08/24 CSA-QC#12063, 13277 Mod End
//            int deleteCount = stmtSelect.executeUpdate();
//            S21InfoLogOutput.println("ACCT_TRTY_RESRC_ASG delete count: [" + deleteCount + "]");
//            commit();
//
//        } catch (SQLException e) {
//            throw new S21AbendException(e);
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
//        }
        // Del End 2017/05/09 RS#8275
    }
*/
    /**
     * Process for Direct Sales Account Type : Customer.
     */
/*
    private void procCustomerForDelAsgData() {
        // Mod Start 2016/07/05 QC#5909
        // ACCT_TRTY_ROLE_ASG_LOGTMsg deleteLogTMsg = new
        // ACCT_TRTY_ROLE_ASG_LOGTMsg();
        // ZYPEZDItemValueSetter.setValue(deleteLogTMsg.glblCmpyCd,
        // this.glblCmpyCd);
        // S21FastTBLAccessor.removeByPartialValue(deleteLogTMsg, new
        // String[] {"glblCmpyCd" });

        Map<String, String> paramsATRAL = new HashMap<String, String>();
        paramsATRAL.put("glblCmpyCd", this.glblCmpyCd);
        paramsATRAL.put("tableNameATRAL", TABLE_ACCT_TRTY_ROLE_ASG_LOG);
        ssmBatchClientCustom.delete("truncateTableForDelAsgData", paramsATRAL);
        // Mod End 2016/07/05 QC#5909

        // Mod Start 2016/07/06 QC#5909
        // ACCT_TRTY_ROLE_ASGTMsg searchCond = new
        // ACCT_TRTY_ROLE_ASGTMsg();
        // searchCond.setSQLID("001");
        // searchCond.setConditionValue("glblCmpyCd01",
        // this.glblCmpyCd);
        // ACCT_TRTY_ROLE_ASGTMsgArray selectTMsgs =
        // (ACCT_TRTY_ROLE_ASGTMsgArray)
        // EZDTBLAccessor.findByCondition(searchCond);
        // // MOD START 2016/03/29 QC#5909
        // // ACCT_TRTY_ROLE_ASG_LOGTMsg[] insertTMsgs = new
        // ACCT_TRTY_ROLE_ASG_LOGTMsg[selectTMsgs.getValidCount()];
        // ACCT_TRTY_ROLE_ASG_LOGTMsg insertTMsg = new
        // ACCT_TRTY_ROLE_ASG_LOGTMsg();
        // int commitCount = 0;
        // // MOD END 2016/03/29 QC#5909
        // for (int i = 0; i < selectTMsgs.getValidCount(); i++) {
        // // ADD START 2016/03/29 QC#5909
        // totalCount++;
        // // ADD END 2016/03/29 QC#5909
        // ACCT_TRTY_ROLE_ASGTMsg selectedTMsg =
        // (ACCT_TRTY_ROLE_ASGTMsg) selectTMsgs.get(i);
        // // MOD START 2016/03/29 QC#5909
        // // ACCT_TRTY_ROLE_ASG_LOGTMsg insertTMsg = new
        // ACCT_TRTY_ROLE_ASG_LOGTMsg();
        // insertTMsg.clear();
        // // MOD END 2016/03/29 QC#5909
        //
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezTableID,
        // selectedTMsg.ezTableID);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezCancelFlag,
        // selectedTMsg.ezCancelFlag);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezInTime,
        // selectedTMsg.ezInTime);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezInTimeZone,
        // selectedTMsg.ezInTimeZone);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezInCompanyCode,
        // selectedTMsg.ezInCompanyCode);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezInUserID,
        // selectedTMsg.ezInUserID);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID,
        // selectedTMsg.ezUpAplID);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTime,
        // selectedTMsg.ezUpTime);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTimeZone,
        // selectedTMsg.ezUpTimeZone);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpCompanyCode,
        // selectedTMsg.ezUpCompanyCode);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpUserID,
        // selectedTMsg.ezUpUserID);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID,
        // selectedTMsg.ezUpAplID);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd,
        // selectedTMsg.glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.acctTrtyRoleAsgLogPk,
        // selectedTMsg.acctTrtyRoleAsgPk);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNum,
        // selectedTMsg.dsAcctNum);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.locNum,
        // selectedTMsg.locNum);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.orgCd,
        // selectedTMsg.orgCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizRoleTpCd,
        // selectedTMsg.lineBizRoleTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNm,
        // selectedTMsg.dsAcctNm);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.billToCustCd,
        // selectedTMsg.billToCustCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.shipToCustCd,
        // selectedTMsg.shipToCustCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctTpCd,
        // selectedTMsg.dsAcctTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctClsCd,
        // selectedTMsg.dsAcctClsCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.firstDsAcctGrpTpCd,
        // selectedTMsg.firstDsAcctGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.scdDsAcctGrpTpCd,
        // selectedTMsg.scdDsAcctGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.thirdDsAcctGrpTpCd,
        // selectedTMsg.thirdDsAcctGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.frthDsAcctGrpTpCd,
        // selectedTMsg.frthDsAcctGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.fifthDsAcctGrpTpCd,
        // selectedTMsg.fifthDsAcctGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.indyTpCd,
        // selectedTMsg.indyTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dsCustSicCd,
        // selectedTMsg.dsCustSicCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.firstLineAddr,
        // selectedTMsg.firstLineAddr);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.scdLineAddr,
        // selectedTMsg.scdLineAddr);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.thirdLineAddr,
        // selectedTMsg.thirdLineAddr);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.frthLineAddr,
        // selectedTMsg.frthLineAddr);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ctyAddr,
        // selectedTMsg.ctyAddr);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.stCd,
        // selectedTMsg.stCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.provNm,
        // selectedTMsg.provNm);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.cntyNm,
        // selectedTMsg.cntyNm);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.postCd,
        // selectedTMsg.postCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.orgNm,
        // selectedTMsg.orgNm);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.trtyGrpTpCd,
        // selectedTMsg.trtyGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.orgRankNum,
        // selectedTMsg.orgRankNum);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.tocCd,
        // selectedTMsg.tocCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.psnCd,
        // selectedTMsg.psnCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.manEntryFlg,
        // selectedTMsg.manEntryFlg);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.trtyTpCd,
        // selectedTMsg.trtyTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.asgTrtyAttrbCd,
        // selectedTMsg.asgTrtyAttrbCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.nonSlsRepFlg,
        // selectedTMsg.nonSlsRepFlg);
        // // MOD START 2016/03/29 QC#5909
        // // insertTMsgs[i] = insertTMsg;
        // S21FastTBLAccessor.insert(insertTMsg);
        // if
        // (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode()))
        // {
        // throw new S21AbendException("MMAM0003E", new String[]
        // {"ACCT_TRTY_ROLE_ASG_LOG"});
        // }
        // if (commitCount >= this.commitTransactionCount) {
        // commit();
        // totalNmlCount = totalNmlCount + commitCount;
        // commitCount = 0;
        //
        // }
        // commitCount++;
        // // MOD END 2016/03/29 QC#5909
        // }

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        Map<String, String> insertParamsATRA = new HashMap<String, String>();
        insertParamsATRA.put("glblCmpyCd", this.glblCmpyCd);
        insertParamsATRA.put("tableNameATRA", TABLE_ACCT_TRTY_ROLE_ASG);

        try {
// QC#12571
//            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
//            execParam.setFetchSize(FETCH_SIZE);
//            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
//            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
//            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
//
//            stmtSelect = this.ssmLLClient.createPreparedStatement("searchInsertTBL", insertParamsATRA, execParam);
//            rs = stmtSelect.executeQuery();
//
//            List<ACCT_TRTY_ROLE_ASG_LOGTMsg> insertTMsgList = new ArrayList<ACCT_TRTY_ROLE_ASG_LOGTMsg>();
//
//            while (rs.next()) {
//                totalCount++;
//                ACCT_TRTY_ROLE_ASG_LOGTMsg insertTMsg = new ACCT_TRTY_ROLE_ASG_LOGTMsg();
//
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezTableID, rs.getString("EZTABLEID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezCancelFlag, rs.getString("EZCANCELFLAG"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInTime, rs.getString("EZINTIME"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInTimeZone, rs.getString("EZINTIMEZONE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInCompanyCode, rs.getString("EZINCOMPANYCODE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInUserID, rs.getString("EZINUSERID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID, rs.getString("EZINAPLID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTime, rs.getString("EZUPTIME"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTimeZone, rs.getString("EZUPTIMEZONE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpCompanyCode, rs.getString("EZUPCOMPANYCODE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpUserID, rs.getString("EZUPUSERID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID, rs.getString("EZUPAPLID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.acctTrtyRoleAsgLogPk, rs.getBigDecimal("ACCT_TRTY_ROLE_ASG_PK"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.locNum, rs.getString("LOC_NUM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.orgCd, rs.getString("ORG_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizRoleTpCd, rs.getString("LINE_BIZ_ROLE_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.billToCustCd, rs.getString("BILL_TO_CUST_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.shipToCustCd, rs.getString("SHIP_TO_CUST_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctTpCd, rs.getString("DS_ACCT_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctClsCd, rs.getString("DS_ACCT_CLS_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.firstDsAcctGrpTpCd, rs.getString("FIRST_DS_ACCT_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.scdDsAcctGrpTpCd, rs.getString("SCD_DS_ACCT_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.thirdDsAcctGrpTpCd, rs.getString("THIRD_DS_ACCT_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.frthDsAcctGrpTpCd, rs.getString("FRTH_DS_ACCT_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.fifthDsAcctGrpTpCd, rs.getString("FIFTH_DS_ACCT_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.indyTpCd, rs.getString("INDY_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.firstLineAddr, rs.getString("FIRST_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.scdLineAddr, rs.getString("SCD_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.thirdLineAddr, rs.getString("THIRD_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.frthLineAddr, rs.getString("FRTH_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ctyAddr, rs.getString("CTY_ADDR"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.stCd, rs.getString("ST_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.provNm, rs.getString("PROV_NM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.cntyNm, rs.getString("CNTY_NM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.postCd, rs.getString("POST_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.orgNm, rs.getString("ORG_NM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.trtyGrpTpCd, rs.getString("TRTY_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.orgRankNum, rs.getString("ORG_RANK_NUM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.tocCd, rs.getString("TOC_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.psnCd, rs.getString("PSN_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.manEntryFlg, rs.getString("MAN_ENTRY_FLG"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.trtyTpCd, rs.getString("TRTY_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.asgTrtyAttrbCd, rs.getString("ASG_TRTY_ATTRB_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.nonSlsRepFlg, rs.getString("NON_SLS_REP_FLG"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizTpCd, rs.getString("LINE_BIZ_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsCustSicCd, rs.getString("DS_CUST_SIC_CD"));
//
//                insertTMsgList.add(insertTMsg);
//
//                if (insertTMsgList.size() >= this.commitTransactionCount) {
//                    int resCnt = S21FastTBLAccessor.insert(insertTMsgList.toArray(new ACCT_TRTY_ROLE_ASG_LOGTMsg[0]));
//                    if (resCnt != insertTMsgList.size()) {
//                        rollback();
//                        throw new S21AbendException("MMAM0003E", new String[] {"ACCT_TRTY_ROLE_ASG_LOG" });
//                    } else {
//                        commit();
//                        this.totalNmlCount += insertTMsgList.size();
//                    }
//                    insertTMsgList.clear();
//                }
//            }

            String dateTime = EZDDBCICarrier.getStartDateTime();
            String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
            String upPgId = EZDDBCICarrier.getUppgID();
            String upTimeZone = EZDDBCICarrier.getUpTimeZone();
            String userId = EZDDBCICarrier.getUserID();

            insertParamsATRA.put("ezintime", dateTime);
            insertParamsATRA.put("ezintimezone", upTimeZone);
            insertParamsATRA.put("ezincompanycode", upCmpyCd);
            insertParamsATRA.put("ezinuserid", userId);
            insertParamsATRA.put("ezinaplid", upPgId);

            insertParamsATRA.put("ezuptime", dateTime);
            insertParamsATRA.put("ezuptimezone", upTimeZone);
            insertParamsATRA.put("ezupcompanycode", upCmpyCd);
            insertParamsATRA.put("ezupuserid", userId);
            insertParamsATRA.put("ezupaplid", upPgId);

            stmtSelect = this.ssmLLClient.createPreparedStatement("searchInsertTBLForDelAsgData", insertParamsATRA);
            // No Need DelAsgData count
            stmtSelect.executeUpdate();
//            this.totalNmlCount += count;
//            this.totalCount += count;
            commit();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
        // Mod End 2016/07/06 QC#5909

        // MOD START 2016/03/29 QC#5909
        // if (insertTMsgs.length > 0) {
        // int insCnt = S21FastTBLAccessor.insert(insertTMsgs);
        // this.totalCount += insCnt;
        // this.totalNmlCount += insCnt;
        // }
        // MOD END 2016/03/29 QC#5909

        // Mod Start 2016/07/05 QC#5909
        // ACCT_TRTY_ROLE_ASGTMsg deleteTMsg = new
        // ACCT_TRTY_ROLE_ASGTMsg();
        // ZYPEZDItemValueSetter.setValue(deleteTMsg.glblCmpyCd,
        // this.glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(deleteTMsg.manEntryFlg,
        // ZYPConstant.FLG_OFF_N);
        // S21FastTBLAccessor.removeByPartialValue(deleteTMsg, new
        // String[] {"glblCmpyCd", "manEntryFlg" });

        rs = null;
        stmtSelect = null;
        Map<String, String> paramsATRA = new HashMap<String, String>();
        paramsATRA.put("glblCmpyCd", this.glblCmpyCd);
        paramsATRA.put("manEntryFlg", ZYPConstant.FLG_OFF_N);
        paramsATRA.put("tableNameATRA", TABLE_ACCT_TRTY_ROLE_ASG);

        try {
// QC#12571
//            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
//            execParam.setFetchSize(FETCH_SIZE);
//            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
//            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
//            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
//
//            stmtSelect = this.ssmLLClient.createPreparedStatement("getDeleteTableData", paramsATRA, execParam);
//            rs = stmtSelect.executeQuery();
//
//            int deleteCount = 0;
//            List<ACCT_TRTY_ROLE_ASGTMsg> deleteTMsgArray = new ArrayList<ACCT_TRTY_ROLE_ASGTMsg>();
//
//            while (rs.next()) {
//                ACCT_TRTY_ROLE_ASGTMsg deleteTMsg = new ACCT_TRTY_ROLE_ASGTMsg();
//                ZYPEZDItemValueSetter.setValue(deleteTMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
//                ZYPEZDItemValueSetter.setValue(deleteTMsg.acctTrtyRoleAsgPk, rs.getBigDecimal("ACCT_TRTY_ROLE_ASG_PK"));
//                deleteTMsgArray.add(deleteTMsg);
//                deleteCount++;
//
//                if (deleteCount >= FETCH_SIZE) {
//                    S21FastTBLAccessor.removePhysical(deleteTMsgArray.toArray(new ACCT_TRTY_ROLE_ASGTMsg[deleteCount]));
//                    // Add Start 2016/07/20 QC#12113
//                    commit();
//                    deleteTMsgArray.clear();
//                    // Add End 2016/07/20 QC#12113
//                    deleteCount = 0;
//                }
//            }
//
//            if (deleteCount > 0) {
//                S21FastTBLAccessor.removePhysical(deleteTMsgArray.toArray(new ACCT_TRTY_ROLE_ASGTMsg[deleteCount]));
//                // Add Start 2016/07/20 QC#12113
//                commit();
//                deleteTMsgArray.clear();
//                // Add End 2016/07/20 QC#12113
//            }
            stmtSelect = this.ssmLLClient.createPreparedStatement("deleteTableData", paramsATRA);
            int deleteCount = stmtSelect.executeUpdate();
            S21InfoLogOutput.println("ACCT_TRTY_ROLE_ASG delete count: [" + deleteCount + "]");
            commit();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
        // Mod End 2016/07/05 QC#5909


        // Add Start 2017/05/09 RS#8275
        Map<String, String> paramsATSAL = new HashMap<String, String>();
        paramsATSAL.put("glblCmpyCd", this.glblCmpyCd);
        paramsATSAL.put("tableNameATSAL", TABLE_ACCT_TRTY_RESRC_LOG);
        ssmBatchClientCustom.delete("truncateTableForDelAsgData", paramsATSAL);

        rs = null;
        stmtSelect = null;
        Map<String, String> insertParamsATRAL = new HashMap<String, String>();
        insertParamsATRAL.put("glblCmpyCd", this.glblCmpyCd);

        try {
            String dateTime = EZDDBCICarrier.getStartDateTime();
            String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
            String upPgId = EZDDBCICarrier.getUppgID();
            String upTimeZone = EZDDBCICarrier.getUpTimeZone();
            String userId = EZDDBCICarrier.getUserID();

            insertParamsATRAL.put("ezintime", dateTime);
            insertParamsATRAL.put("ezintimezone", upTimeZone);
            insertParamsATRAL.put("ezincompanycode", upCmpyCd);
            insertParamsATRAL.put("ezinuserid", userId);
            insertParamsATRAL.put("ezinaplid", upPgId);

            insertParamsATRAL.put("ezuptime", dateTime);
            insertParamsATRAL.put("ezuptimezone", upTimeZone);
            insertParamsATRAL.put("ezupcompanycode", upCmpyCd);
            insertParamsATRAL.put("ezupuserid", userId);
            insertParamsATRAL.put("ezupaplid", upPgId);

            insertParamsATRAL.put("tableNameATRA", TABLE_ACCT_TRTY_RESRC_ASG);

            stmtSelect = this.ssmLLClient.createPreparedStatement("searchInsertRsc", insertParamsATRAL);
            // No Need DelAsgData count
            stmtSelect.executeUpdate();
//            this.totalNmlCount += count;
//            this.totalCount += count;
            commit();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

        Map<String, String> paramsATRSA = new HashMap<String, String>();
        paramsATRSA.put("glblCmpyCd", this.glblCmpyCd);
        paramsATRSA.put("tableNameATSA", TABLE_ACCT_TRTY_RESRC_ASG);
        ssmBatchClientCustom.delete("truncateTableForDelAsgData", paramsATRSA);
        // Add End 2017/05/09 RS#8275

        // ADD START 2016/03/29 QC#5909
        commit();
        // No Need DelAsgData count
        // totalNmlCount = totalNmlCount + commitCount;
        // ADD END 2016/03/29 QC#5909
    }
*/
    /**
     * Process for Direct Sales Account Type : Prospect.
     */
/*
    private void procProspectForDelAsgData() {
        // Mod Start 2016/07/05 QC#5909
        // PROS_TRTY_ROLE_ASG_LOGTMsg deleteLogTMsg = new
        // PROS_TRTY_ROLE_ASG_LOGTMsg();
        // ZYPEZDItemValueSetter.setValue(deleteLogTMsg.glblCmpyCd,
        // this.glblCmpyCd);
        // S21FastTBLAccessor.removeByPartialValue(deleteLogTMsg, new
        // String[] {"glblCmpyCd" });
        Map<String, String> paramsPTRAL = new HashMap<String, String>();
        paramsPTRAL.put("glblCmpyCd", this.glblCmpyCd);
        paramsPTRAL.put("tableNamePTRAL", TABLE_PROS_TRTY_ROLE_ASG_LOG);
        ssmBatchClientCustom.delete("truncateTableForDelAsgData", paramsPTRAL);
        // Mod End 2016/07/05 QC#5909

        // Mod Start 2016/07/06 QC#5909
        // PROS_TRTY_ROLE_ASGTMsg searchCond = new
        // PROS_TRTY_ROLE_ASGTMsg();
        // searchCond.setSQLID("001");
        // searchCond.setConditionValue("glblCmpyCd01",
        // this.glblCmpyCd);
        // PROS_TRTY_ROLE_ASGTMsgArray selectTMsgs =
        // (PROS_TRTY_ROLE_ASGTMsgArray)
        // EZDTBLAccessor.findByCondition(searchCond);
        // // MOD START 2016/03/29 QC#5909
        // // PROS_TRTY_ROLE_ASG_LOGTMsg[] insertTMsgs = new
        // PROS_TRTY_ROLE_ASG_LOGTMsg[selectTMsgs.getValidCount()];
        // PROS_TRTY_ROLE_ASG_LOGTMsg insertTMsg = new
        // PROS_TRTY_ROLE_ASG_LOGTMsg();
        // int commitCount = 0;
        // // MOD END 2016/03/29 QC#5909
        // for (int i = 0; i < selectTMsgs.getValidCount(); i++) {
        // // ADD START 2016/03/29 QC#5909
        // totalCount++;
        // // ADD END 2016/03/29 QC#5909
        // PROS_TRTY_ROLE_ASGTMsg selectedTMsg =
        // (PROS_TRTY_ROLE_ASGTMsg) selectTMsgs.get(i);
        // // MOD START 2016/03/29 QC#5909
        // // PROS_TRTY_ROLE_ASG_LOGTMsg insertTMsg = new
        // PROS_TRTY_ROLE_ASG_LOGTMsg();
        // insertTMsg.clear();
        // // MOD END 2016/03/29 QC#5909
        //
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezTableID,
        // selectedTMsg.ezTableID);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezCancelFlag,
        // selectedTMsg.ezCancelFlag);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezInTime,
        // selectedTMsg.ezInTime);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezInTimeZone,
        // selectedTMsg.ezInTimeZone);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezInCompanyCode,
        // selectedTMsg.ezInCompanyCode);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezInUserID,
        // selectedTMsg.ezInUserID);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID,
        // selectedTMsg.ezUpAplID);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTime,
        // selectedTMsg.ezUpTime);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTimeZone,
        // selectedTMsg.ezUpTimeZone);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpCompanyCode,
        // selectedTMsg.ezUpCompanyCode);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpUserID,
        // selectedTMsg.ezUpUserID);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID,
        // selectedTMsg.ezUpAplID);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd,
        // selectedTMsg.glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.acctTrtyRoleAsgLogPk,
        // selectedTMsg.acctTrtyRoleAsgPk);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNum,
        // selectedTMsg.dsAcctNum);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.locNum,
        // selectedTMsg.locNum);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.orgCd,
        // selectedTMsg.orgCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizRoleTpCd,
        // selectedTMsg.lineBizRoleTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNm,
        // selectedTMsg.dsAcctNm);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.billToCustCd,
        // selectedTMsg.billToCustCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.shipToCustCd,
        // selectedTMsg.shipToCustCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctTpCd,
        // selectedTMsg.dsAcctTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctClsCd,
        // selectedTMsg.dsAcctClsCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.firstDsAcctGrpTpCd,
        // selectedTMsg.firstDsAcctGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.scdDsAcctGrpTpCd,
        // selectedTMsg.scdDsAcctGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.thirdDsAcctGrpTpCd,
        // selectedTMsg.thirdDsAcctGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.frthDsAcctGrpTpCd,
        // selectedTMsg.frthDsAcctGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.fifthDsAcctGrpTpCd,
        // selectedTMsg.fifthDsAcctGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.indyTpCd,
        // selectedTMsg.indyTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.dsCustSicCd,
        // selectedTMsg.dsCustSicCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.firstLineAddr,
        // selectedTMsg.firstLineAddr);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.scdLineAddr,
        // selectedTMsg.scdLineAddr);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.thirdLineAddr,
        // selectedTMsg.thirdLineAddr);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.frthLineAddr,
        // selectedTMsg.frthLineAddr);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.ctyAddr,
        // selectedTMsg.ctyAddr);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.stCd,
        // selectedTMsg.stCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.provNm,
        // selectedTMsg.provNm);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.cntyNm,
        // selectedTMsg.cntyNm);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.postCd,
        // selectedTMsg.postCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.orgNm,
        // selectedTMsg.orgNm);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.trtyGrpTpCd,
        // selectedTMsg.trtyGrpTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.orgRankNum,
        // selectedTMsg.orgRankNum);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.tocCd,
        // selectedTMsg.tocCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.psnCd,
        // selectedTMsg.psnCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.manEntryFlg,
        // selectedTMsg.manEntryFlg);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.trtyTpCd,
        // selectedTMsg.trtyTpCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.asgTrtyAttrbCd,
        // selectedTMsg.asgTrtyAttrbCd);
        // ZYPEZDItemValueSetter.setValue(insertTMsg.nonSlsRepFlg,
        // selectedTMsg.nonSlsRepFlg);
        //
        // // MOD START 2016/03/29 QC#5909
        // // insertTMsgs[i] = insertTMsg;
        // S21FastTBLAccessor.insert(insertTMsg);
        // if
        // (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode()))
        // {
        // throw new S21AbendException("MMAM0003E", new String[]
        // {"TRTY_RULE_LOC_RELN_LOG"});
        // }
        // if (commitCount >= this.commitTransactionCount) {
        // commit();
        // totalNmlCount = totalNmlCount + commitCount;
        // commitCount = 0;
        //
        // }
        // commitCount++;
        // // MOD END 2016/03/29 QC#5909
        // }

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        Map<String, String> insertParamsPTRA = new HashMap<String, String>();
        insertParamsPTRA.put("glblCmpyCd", this.glblCmpyCd);
        insertParamsPTRA.put("tableNamePTRA", TABLE_PROS_TRTY_ROLE_ASG);
//        int commitCount = 0;

        try {
// QC#12571
//            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
//            execParam.setFetchSize(FETCH_SIZE);
//            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
//            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
//            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
//
//            stmtSelect = this.ssmLLClient.createPreparedStatement("searchInsertTBL", insertParamsPTRA, execParam);
//            rs = stmtSelect.executeQuery();
//
//            PROS_TRTY_ROLE_ASG_LOGTMsg insertTMsg = new PROS_TRTY_ROLE_ASG_LOGTMsg();
//
//            while (rs.next()) {
//                totalCount++;
//                insertTMsg.clear();
//
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezTableID, rs.getString("EZTABLEID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezCancelFlag, rs.getString("EZCANCELFLAG"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInTime, rs.getString("EZINTIME"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInTimeZone, rs.getString("EZINTIMEZONE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInCompanyCode, rs.getString("EZINCOMPANYCODE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezInUserID, rs.getString("EZINUSERID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID, rs.getString("EZINAPLID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTime, rs.getString("EZUPTIME"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpTimeZone, rs.getString("EZUPTIMEZONE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpCompanyCode, rs.getString("EZUPCOMPANYCODE"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpUserID, rs.getString("EZUPUSERID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ezUpAplID, rs.getString("EZUPAPLID"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.acctTrtyRoleAsgLogPk, rs.getBigDecimal("ACCT_TRTY_ROLE_ASG_PK"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.locNum, rs.getString("LOC_NUM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.orgCd, rs.getString("ORG_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizRoleTpCd, rs.getString("LINE_BIZ_ROLE_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.billToCustCd, rs.getString("BILL_TO_CUST_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.shipToCustCd, rs.getString("SHIP_TO_CUST_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctTpCd, rs.getString("DS_ACCT_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsAcctClsCd, rs.getString("DS_ACCT_CLS_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.firstDsAcctGrpTpCd, rs.getString("FIRST_DS_ACCT_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.scdDsAcctGrpTpCd, rs.getString("SCD_DS_ACCT_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.thirdDsAcctGrpTpCd, rs.getString("THIRD_DS_ACCT_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.frthDsAcctGrpTpCd, rs.getString("FRTH_DS_ACCT_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.fifthDsAcctGrpTpCd, rs.getString("FIFTH_DS_ACCT_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.indyTpCd, rs.getString("INDY_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.firstLineAddr, rs.getString("FIRST_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.scdLineAddr, rs.getString("SCD_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.thirdLineAddr, rs.getString("THIRD_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.frthLineAddr, rs.getString("FRTH_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.ctyAddr, rs.getString("CTY_ADDR"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.stCd, rs.getString("ST_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.provNm, rs.getString("PROV_NM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.cntyNm, rs.getString("CNTY_NM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.postCd, rs.getString("POST_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.orgNm, rs.getString("ORG_NM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.trtyGrpTpCd, rs.getString("TRTY_GRP_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.orgRankNum, rs.getString("ORG_RANK_NUM"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.tocCd, rs.getString("TOC_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.psnCd, rs.getString("PSN_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.manEntryFlg, rs.getString("MAN_ENTRY_FLG"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.trtyTpCd, rs.getString("TRTY_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.asgTrtyAttrbCd, rs.getString("ASG_TRTY_ATTRB_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.nonSlsRepFlg, rs.getString("NON_SLS_REP_FLG"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.lineBizTpCd, rs.getString("LINE_BIZ_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(insertTMsg.dsCustSicCd, rs.getString("DS_CUST_SIC_CD"));
//
//                S21FastTBLAccessor.insert(insertTMsg);
//                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
//                    throw new S21AbendException("MMAM0003E", new String[] {"TRTY_RULE_LOC_RELN_LOG" });
//                }
//                if (commitCount >= this.commitTransactionCount) {
//                    commit();
//                    totalNmlCount = totalNmlCount + commitCount;
//                    commitCount = 0;
//
//                }
//                commitCount++;
//            }
            String dateTime = EZDDBCICarrier.getStartDateTime();
            String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
            String upPgId = EZDDBCICarrier.getUppgID();
            String upTimeZone = EZDDBCICarrier.getUpTimeZone();
            String userId = EZDDBCICarrier.getUserID();

            insertParamsPTRA.put("ezintime", dateTime);
            insertParamsPTRA.put("ezintimezone", upTimeZone);
            insertParamsPTRA.put("ezincompanycode", upCmpyCd);
            insertParamsPTRA.put("ezinuserid", userId);
            insertParamsPTRA.put("ezinaplid", upPgId);

            insertParamsPTRA.put("ezuptime", dateTime);
            insertParamsPTRA.put("ezuptimezone", upTimeZone);
            insertParamsPTRA.put("ezupcompanycode", upCmpyCd);
            insertParamsPTRA.put("ezupuserid", userId);
            insertParamsPTRA.put("ezupaplid", upPgId);

            stmtSelect = this.ssmLLClient.createPreparedStatement("searchInsertTBLForDelAsgData", insertParamsPTRA);
            // No Need DelAsgData count
            stmtSelect.executeUpdate();
//            this.totalNmlCount += count;
//            this.totalCount += count;
            commit();
            
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
        // Mod End 2016/07/06 QC#5909

        // DEL START 2016/03/29 QC#5909
        // if (insertTMsgs.length > 0) {
        // int insCnt = S21FastTBLAccessor.insert(insertTMsgs);
        // this.totalCount += insCnt;
        // this.totalNmlCount += insCnt;
        // }
        // DEL END 2016/03/29 QC#5909

        // Mod Start 2016/07/05 QC#5909
        // PROS_TRTY_ROLE_ASGTMsg deleteTMsg = new
        // PROS_TRTY_ROLE_ASGTMsg();
        // ZYPEZDItemValueSetter.setValue(deleteTMsg.glblCmpyCd,
        // this.glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(deleteTMsg.manEntryFlg,
        // ZYPConstant.FLG_OFF_N);
        // S21FastTBLAccessor.removeByPartialValue(deleteTMsg, new
        // String[] {"glblCmpyCd", "manEntryFlg" });
        rs = null;
        stmtSelect = null;
        Map<String, String> paramsPTRA = new HashMap<String, String>();
        paramsPTRA.put("glblCmpyCd", this.glblCmpyCd);
        paramsPTRA.put("manEntryFlg", ZYPConstant.FLG_OFF_N);
        paramsPTRA.put("tableNamePTRA", TABLE_PROS_TRTY_ROLE_ASG);
        try {
// QC#12571
//            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
//            execParam.setFetchSize(FETCH_SIZE);
//            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
//            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
//            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
//
//            stmtSelect = this.ssmLLClient.createPreparedStatement("getDeleteTableData", paramsPTRA, execParam);
//            rs = stmtSelect.executeQuery();
//
//            int deleteCount = 0;
//            List<PROS_TRTY_ROLE_ASGTMsg> deleteTMsgArray = new ArrayList<PROS_TRTY_ROLE_ASGTMsg>();
//
//            while (rs.next()) {
//                PROS_TRTY_ROLE_ASGTMsg deleteTMsg = new PROS_TRTY_ROLE_ASGTMsg();
//                ZYPEZDItemValueSetter.setValue(deleteTMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
//                ZYPEZDItemValueSetter.setValue(deleteTMsg.acctTrtyRoleAsgPk, rs.getBigDecimal("ACCT_TRTY_ROLE_ASG_PK"));
//                deleteTMsgArray.add(deleteTMsg);
//                deleteCount++;
//
//                if (deleteCount >= FETCH_SIZE) {
//                    S21FastTBLAccessor.removePhysical(deleteTMsgArray.toArray(new PROS_TRTY_ROLE_ASGTMsg[deleteCount]));
//                    // Add Start 2016/07/20 QC#12113
//                    commit();
//                    deleteTMsgArray.clear();
//                    // Add End 2016/07/20 QC#12113
//                    deleteCount = 0;
//                }
//            }
//
//            if (deleteCount > 0) {
//                S21FastTBLAccessor.removePhysical(deleteTMsgArray.toArray(new PROS_TRTY_ROLE_ASGTMsg[deleteCount]));
//                // Add Start 2016/07/20 QC#12113
//                commit();
//                deleteTMsgArray.clear();
//                // Add End 2016/07/20 QC#12113
//            }
            stmtSelect = this.ssmLLClient.createPreparedStatement("deleteTableData", paramsPTRA);
            int deleteCount = stmtSelect.executeUpdate();
            S21InfoLogOutput.println("PROS_TRTY_ROLE_ASG delete count: [" + deleteCount + "]");
            commit();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
        // Mod End 2016/07/05 QC#5909

        // Add Start 2017/05/09 RS#8275
        Map<String, String> paramsPTSAL = new HashMap<String, String>();
        paramsPTSAL.put("glblCmpyCd", this.glblCmpyCd);
        paramsPTSAL.put("tableNamePTSAL", TABLE_PROS_TRTY_RESRC_LOG);
        ssmBatchClientCustom.delete("truncateTableForDelAsgData", paramsPTSAL);

        rs = null;
        stmtSelect = null;
        Map<String, String> insertParamsPTRAL = new HashMap<String, String>();
        insertParamsPTRAL.put("glblCmpyCd", this.glblCmpyCd);

        try {
            String dateTime = EZDDBCICarrier.getStartDateTime();
            String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
            String upPgId = EZDDBCICarrier.getUppgID();
            String upTimeZone = EZDDBCICarrier.getUpTimeZone();
            String userId = EZDDBCICarrier.getUserID();

            insertParamsPTRAL.put("ezintime", dateTime);
            insertParamsPTRAL.put("ezintimezone", upTimeZone);
            insertParamsPTRAL.put("ezincompanycode", upCmpyCd);
            insertParamsPTRAL.put("ezinuserid", userId);
            insertParamsPTRAL.put("ezinaplid", upPgId);

            insertParamsPTRAL.put("ezuptime", dateTime);
            insertParamsPTRAL.put("ezuptimezone", upTimeZone);
            insertParamsPTRAL.put("ezupcompanycode", upCmpyCd);
            insertParamsPTRAL.put("ezupuserid", userId);
            insertParamsPTRAL.put("ezupaplid", upPgId);

            insertParamsPTRAL.put("tableNamePTRA", TABLE_PROS_TRTY_RESRC_ASG);

            stmtSelect = this.ssmLLClient.createPreparedStatement("searchInsertRsc", insertParamsPTRAL);
            // No Need DelAsgData count
            stmtSelect.executeUpdate();
//            this.totalNmlCount += count;
//            this.totalCount += count;
            commit();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

        Map<String, String> paramsPTRSA = new HashMap<String, String>();
        paramsPTRSA.put("glblCmpyCd", this.glblCmpyCd);
        paramsPTRSA.put("tableNamePTSA", TABLE_PROS_TRTY_RESRC_ASG);
        ssmBatchClientCustom.delete("truncateTableForDelAsgData", paramsPTRSA);
        // Add End 2017/05/09 RS#8275

        // ADD START 2016/03/29 QC#5909
        commit();
        // No Need DelAsgData count
//        totalNmlCount = totalNmlCount + commitCount;
        // ADD END 2016/03/29 QC#5909
    }
*/
    // MOD END 2016/03/16 QC#4443

    /**
     * main Process : 04(Create Assign data)
     */
/*
    private void mainProcessForCratAsgData() {

        this.setCancelFlagManual(true);
        this.setCancelFlagManual(false);

        this.makeTempSalesRepRoleAsg();
        this.makeTempSpecialistRoleAsg();
        this.insertAcctTrtyResrcAsg();

        // 2017/10/25 QC#21425 add start
        deleteDupRuleRoleAsg();
        // 2017/10/25 QC#21425 add end

        deletePhysicalManual();
        
    }
*/
/*
    private void makeTempSalesRepRoleAsg() {

        // ---Insert
        Map<String, Object> ssmGetLocationParam = makeTrtyRAsgSsmParam();
        ssmGetLocationParam.put("salseRepFlag", ZYPConstant.FLG_ON_Y);

        // ------1.Rank
        ssmGetLocationParam.put("nonSlsRepFlgs", ZYPConstant.FLG_OFF_N); // is 'non' N
        ssmGetLocationParam.put("gnrnTpCd", GNRN_TP.CURRENT);
        ssmGetLocationParam.put("orgRankNumMax", ORG_RANK_NUM_MAX);
        // ------2.LineBizTpCd
        ssmGetLocationParam.put("rnkEligFlg", ZYPConstant.FLG_ON_Y);
        // ------4.Location
        ssmGetLocationParam.put("rgtnStsTerminated", RGTN_STS.TERMINATED);
        ssmGetLocationParam.put("activeFlag", ZYPConstant.FLG_ON_Y);
        // ------5.Resource
        ssmGetLocationParam.put("salesRepFlg", ZYPConstant.FLG_ON_Y);
        ssmGetLocationParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        ssmGetLocationParam.put("slsDt", this.slsDt);
        ssmGetLocationParam.put("maxDt", "99991231");
        ssmGetLocationParam.put("bizArea_Sales", BIZ_AREA_ORG.SALES);
        ssmGetLocationParam.put("rgtnSts_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmGetLocationParam.put("rgtnSts_P99", RGTN_STS.TERMINATED);
        ssmGetLocationParam.put("flgOffN", ZYPConstant.FLG_OFF_N);
        ssmGetLocationParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
        // ------6.isManual
        ssmGetLocationParam.put("dsAcctTp", this.directSalesAccountType);
        ssmGetLocationParam.put("gnrnTp_Current", GNRN_TP.CURRENT);
        ssmGetLocationParam.put("rgtnSts_P20", RGTN_STS.READY_FOR_ORDER_TAKING);

        int insertCount = this.ssmBatchClientCustom.insert("insertTrtyRoleAsgList", ssmGetLocationParam);
        this.totalCount += insertCount;
        this.totalNmlCount += insertCount;
        commit();
    }
*/

/*
    private void makeTempSpecialistRoleAsg() {

        // ---Insert
        Map<String, Object> ssmGetLocationParam = makeTrtyRAsgSsmParam();
        ssmGetLocationParam.put("specialistFlag", ZYPConstant.FLG_ON_Y);

        // ------1.Rank
        ssmGetLocationParam.put("nonSlsRepFlgs", ZYPConstant.FLG_ON_Y); // is 'non' Y
        ssmGetLocationParam.put("gnrnTpCd", GNRN_TP.CURRENT);
        // ------2.LineBizTpCd
        ssmGetLocationParam.put("rnkEligFlg", ZYPConstant.FLG_OFF_N);
        // ------4.Location
        ssmGetLocationParam.put("rgtnStsTerminated", RGTN_STS.TERMINATED);
        ssmGetLocationParam.put("activeFlag", ZYPConstant.FLG_ON_Y);
        // ------5.Resource
        ssmGetLocationParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        ssmGetLocationParam.put("slsDt", this.slsDt);
        ssmGetLocationParam.put("maxDt", "99991231");
        ssmGetLocationParam.put("bizArea_Sales", BIZ_AREA_ORG.SALES);
        ssmGetLocationParam.put("rgtnSts_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmGetLocationParam.put("rgtnSts_P99", RGTN_STS.TERMINATED);
        ssmGetLocationParam.put("flgOffN", ZYPConstant.FLG_OFF_N);
        ssmGetLocationParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
        // ------6.isManual
        ssmGetLocationParam.put("dsAcctTp", this.directSalesAccountType);
        ssmGetLocationParam.put("gnrnTp_Current", GNRN_TP.CURRENT);
        ssmGetLocationParam.put("rgtnSts_P20", RGTN_STS.READY_FOR_ORDER_TAKING);

        int insertCount = this.ssmBatchClientCustom.insert("insertTrtyRoleAsgList", ssmGetLocationParam);
        this.totalCount += insertCount;
        this.totalNmlCount += insertCount;
        commit();
    }
*/
    
/*
    private void insertAcctTrtyResrcAsg() {

        Map<String, Object> ssmGetLocationParam = makeTrtyResrcAsgBaseSsmParam();
        ssmGetLocationParam.put("rgtnSts_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmGetLocationParam.put("rgtnSts_P99", RGTN_STS.TERMINATED);
        // Del Start 2017/11/21 QC#22193
        //ssmGetLocationParam.put("flgOffN", ZYPConstant.FLG_OFF_N);
        // Del End 2017/11/21 QC#22193
        ssmGetLocationParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
        ssmGetLocationParam.put("dsAcctTp", this.directSalesAccountType);

        this.ssmBatchClientCustom.insert("insertAcctTrtyResrcAsg", ssmGetLocationParam);
        commit();
    }
*/
    
    /*
    private void setCancelFlagManual(boolean isSalesRep) {

        // Set Cancel Flag Manual every dsAcctTp salesRep/Specialist Customer/Prospect
        Map<String, Object> ssmSetCancelFlagManualRoleAsignListParam = makeTrtyRAsgSsmParam();
        ssmSetCancelFlagManualRoleAsignListParam.put("dsAcctTp", this.directSalesAccountType);
        if (isSalesRep) {
              ssmSetCancelFlagManualRoleAsignListParam.put("salesRepFlg", ZYPConstant.FLG_ON_Y);
        } else {
              ssmSetCancelFlagManualRoleAsignListParam.put("salesRepFlg", ZYPConstant.FLG_OFF_N);
        }
        this.ssmBatchClientCustom.update("setCancelFlagManualRoleAsg", ssmSetCancelFlagManualRoleAsignListParam);
    }
*/

/*
    private void deletePhysicalManual() {
        // Delete Manual
        Map<String, Object> ssmDeleteCancelFlagManualRoleAsignListParam = makeTrtyRAsgSsmParam();
        this.ssmBatchClientCustom.delete("deleteCancelFlagManualRoleAsg", ssmDeleteCancelFlagManualRoleAsignListParam);
        commit();
    }
*/
    
/*
    private Map<String, Object> makeTrtyRAsgSsmParam() {
        String dateTime = EZDDBCICarrier.getStartDateTime();
        String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
        String upPgId = EZDDBCICarrier.getUppgID();
        String upTimeZone = EZDDBCICarrier.getUpTimeZone();
        String userId = EZDDBCICarrier.getUserID();

        Map<String, Object> ssmGetLocationParam = new HashMap<String, Object>();

        if (DS_ACCT_TP.PROSPECT.equals(directSalesAccountType)) {
            ssmGetLocationParam.put("tableId", "PROS_TRTY_ROLE_ASG");
        } else if (DS_ACCT_TP.CUSTOMER.equals(directSalesAccountType)) {
            ssmGetLocationParam.put("tableId", "ACCT_TRTY_ROLE_ASG");
        }

        ssmGetLocationParam.put("glblCmpyCd", glblCmpyCd);
        ssmGetLocationParam.put("slsDt", slsDt);
        ssmGetLocationParam.put("ezintime", dateTime);
        ssmGetLocationParam.put("ezintimezone", upTimeZone);
        ssmGetLocationParam.put("ezincompanycode", upCmpyCd);
        ssmGetLocationParam.put("ezinuserid", userId);
        ssmGetLocationParam.put("ezinaplid", upPgId);

        ssmGetLocationParam.put("ezuptime", dateTime);
        ssmGetLocationParam.put("ezuptimezone", upTimeZone);
        ssmGetLocationParam.put("ezupcompanycode", upCmpyCd);
        ssmGetLocationParam.put("ezupuserid", userId);
        ssmGetLocationParam.put("ezupaplid", upPgId);

        if (DS_ACCT_TP.CUSTOMER.equals(this.directSalesAccountType)) {
            ssmGetLocationParam.put("customerFlag", ZYPConstant.FLG_ON_Y);
        } else if (DS_ACCT_TP.PROSPECT.equals(this.directSalesAccountType)) {
            ssmGetLocationParam.put("prospectFlag", ZYPConstant.FLG_ON_Y);
        }

        return ssmGetLocationParam;
    }
*/

/*
    private Map<String, Object> makeTrtyResrcAsgBaseSsmParam() {
        String dateTime = EZDDBCICarrier.getStartDateTime();
        String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
        String upPgId = EZDDBCICarrier.getUppgID();
        String upTimeZone = EZDDBCICarrier.getUpTimeZone();
        String userId = EZDDBCICarrier.getUserID();

        Map<String, Object> ssmGetLocationParam = new HashMap<String, Object>();

        if (DS_ACCT_TP.PROSPECT.equals(directSalesAccountType)) {
            ssmGetLocationParam.put("tableId", "PROS_TRTY_RESRC_ASG");
        } else if (DS_ACCT_TP.CUSTOMER.equals(directSalesAccountType)) {
            ssmGetLocationParam.put("tableId", "ACCT_TRTY_RESRC_ASG");
        }

        ssmGetLocationParam.put("glblCmpyCd", glblCmpyCd);
        ssmGetLocationParam.put("slsDt", slsDt);
        ssmGetLocationParam.put("ezintime", dateTime);
        ssmGetLocationParam.put("ezintimezone", upTimeZone);
        ssmGetLocationParam.put("ezincompanycode", upCmpyCd);
        ssmGetLocationParam.put("ezinuserid", userId);
        ssmGetLocationParam.put("ezinaplid", upPgId);

        ssmGetLocationParam.put("ezuptime", dateTime);
        ssmGetLocationParam.put("ezuptimezone", upTimeZone);
        ssmGetLocationParam.put("ezupcompanycode", upCmpyCd);
        ssmGetLocationParam.put("ezupuserid", userId);
        ssmGetLocationParam.put("ezupaplid", upPgId);

        if (DS_ACCT_TP.CUSTOMER.equals(this.directSalesAccountType)) {
            ssmGetLocationParam.put("customerFlag", ZYPConstant.FLG_ON_Y);
        } else if (DS_ACCT_TP.PROSPECT.equals(this.directSalesAccountType)) {
            ssmGetLocationParam.put("prospectFlag", ZYPConstant.FLG_ON_Y);
        }

        return ssmGetLocationParam;
    }
*/
    // Add End 2017/08/24 QC#20535,20538

    // Del Start 2017/08/24 QC#20535,20538
//    @Override
//    protected void termRoutine() {
//        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);
//    }
    // Del End 2017/08/24 QC#20535,20538

    // 2017/10/25 QC#21425 add start
/*
    private void deleteDupRuleRoleAsg() {
        // Delete
        Map<String, Object> params =  new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rankNumX", "X");
        
        if (DS_ACCT_TP.CUSTOMER.equals(this.directSalesAccountType)) {
            params.put("customerFlag", ZYPConstant.FLG_ON_Y);
        } else if (DS_ACCT_TP.PROSPECT.equals(this.directSalesAccountType)) {
            params.put("prospectFlag", ZYPConstant.FLG_ON_Y);
        }
        this.ssmBatchClientCustom.delete("deleteDupRuleRoleAsg", params);
        commit();
    }
*/
    // 2017/10/25 QC#21425 add end

    // 2018/03/20 QC#23671(Second Fix) Add Start
    /**
     * updateStatistics
     * @param isInitTiming
     * @return
     */
    private Boolean updateStatistics(Boolean isInitTiming) {
        String constName = "";

        if (DS_ACCT_TP.CUSTOMER.equals(this.directSalesAccountType)) {
            if (isInitTiming){
                constName = CONST_CUST_GATHER_TABLE_INIT;
            } else {
                constName = CONST_CUST_GATHER_TABLE_BEFORE_INS_LOC;
            }
        } else if (DS_ACCT_TP.PROSPECT.equals(this.directSalesAccountType)) {
            if (isInitTiming){
                constName = CONST_PROS_GATHER_TABLE_INIT;
            } else {
                constName = CONST_PROS_GATHER_TABLE_BEFORE_INS_LOC;
            }
        } else {
            S21InfoLogOutput.println(String.format("Illegal directSalesAccountType [%s]",this.directSalesAccountType));
            return false;
        }

        Boolean ret = true;

        if (S21StringUtil.isEmpty(dbSchema)){
            S21InfoLogOutput.println(String.format("Skip statistics update (var_char_const[%s] is empty).", CONST_DB_SCHEMA));
        } else {
            String tableSetting = ZYPCodeDataUtil.getVarCharConstValue(constName, glblCmpyCd);
            if (S21StringUtil.isEmpty(tableSetting)){
                S21InfoLogOutput.println(String.format("Skip statistics update (var_char_const[%s] is empty).", constName));
            } else {
                ret = updateStatistics(Arrays.asList(tableSetting.trim().split(",")));
            }
        }

        return ret;

    }

    /**
     * updateStatistics
     * @param tables
     * @return
     */
    private Boolean updateStatistics(List<String> tables) {
        Boolean result = true;

        for (String tableName : tables){
            Boolean ret = updateStatistics(tableName);

            if (ret == false){
                result = false;
            }
        }
        
        return result;
    }

    /**
     * updateStatistics
     * @param tableName
     * @return
     */
    private Boolean updateStatistics(String tableName) {
        S21BatchTransactionQuery queryComponet = new S21BatchTransactionQuery();
        String sql = String.format("DBMS_STATS.GATHER_TABLE_STATS ('%s','%s', CASCADE => TRUE); ", this.dbSchema, tableName);

        StringBuilder cmd = new StringBuilder();
        cmd.append("DECLARE ");
        cmd.append("BEGIN ");
        cmd.append(sql);
        cmd.append("END;");

        int num = queryComponet.executeUpdate(cmd.toString());

        if (num < 0) {
            S21InfoLogOutput.println(String.format("Error happened \"%s\"", sql));
            return false;
        } else {
            S21InfoLogOutput.println(String.format("Statistics update finished \"%s\"", sql));
        }
        return true;
    }
    // 2018/03/20 QC#23671(Second Fix) Add End
}
