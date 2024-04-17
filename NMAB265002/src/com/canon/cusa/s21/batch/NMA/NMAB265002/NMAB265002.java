package com.canon.cusa.s21.batch.NMA.NMAB265002;

import static com.canon.cusa.s21.batch.NMA.NMAB265002.Constant.NMAB265002Constant.ORG_RANK_NUM_MAX;
import static com.canon.cusa.s21.batch.NMA.NMAB265002.Constant.NMAB265002Constant.TABLE_ACCT_TRTY_RESRC_ASG;
import static com.canon.cusa.s21.batch.NMA.NMAB265002.Constant.NMAB265002Constant.TABLE_ACCT_TRTY_RESRC_LOG;
import static com.canon.cusa.s21.batch.NMA.NMAB265002.Constant.NMAB265002Constant.TABLE_ACCT_TRTY_ROLE_ASG;
import static com.canon.cusa.s21.batch.NMA.NMAB265002.Constant.NMAB265002Constant.TABLE_ACCT_TRTY_ROLE_ASG_LOG;
import static com.canon.cusa.s21.batch.NMA.NMAB265002.Constant.NMAB265002Constant.TABLE_PROS_TRTY_RESRC_ASG;
import static com.canon.cusa.s21.batch.NMA.NMAB265002.Constant.NMAB265002Constant.TABLE_PROS_TRTY_RESRC_LOG;
import static com.canon.cusa.s21.batch.NMA.NMAB265002.Constant.NMAB265002Constant.TABLE_PROS_TRTY_ROLE_ASG;
import static com.canon.cusa.s21.batch.NMA.NMAB265002.Constant.NMAB265002Constant.TABLE_PROS_TRTY_ROLE_ASG_LOG;
import static com.canon.cusa.s21.batch.NMA.NMAB265002.Constant.NMAB265002Constant.ZZZM9026E;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDDBCICarrier;

import com.canon.cusa.s21.batch.NMA.NMAB265002.S21SsmBatchClientCustom;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_GRP_TP;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.util.S21StopWatch;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * NMAB2650 Sales Territory Visibility Defaulting Processor 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/03/09   Fujitsu         Mz.Takahashi    Create          QC#23671
 * 2018/03/20   Fujitsu         Mz.Takahashi    Update          QC#22968(Uncoment)
 * 2018/08/29   Fujitsu         M.Ohno          Update          QC#25556
 *</pre>
 */
public class NMAB265002 extends S21BatchMain {


    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Commit Transaction Count */
    private int commitTransactionCount = 0;

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

    /** SQL access parts */
    private S21SsmBatchClientCustom ssmBatchClientCustom = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** sales date time */
    private String slsDt = null;
    /**
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NMAB265002().executeBatch(NMAB265002.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Global Company Code" });
        }

        this.commitTransactionCount = getCommitCount();
        if (this.commitTransactionCount < 0) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Commit Transaction Count" });
        }

        try {
            directSalesAccountType = getUserVariable3();
            if (!DS_ACCT_TP.PROSPECT.equals(directSalesAccountType) && !DS_ACCT_TP.CUSTOMER.equals(directSalesAccountType)) {
                throw new S21AbendException(ZZZM9026E, new String[] {"Direct Sales Account Type" });
            }
        } catch (NumberFormatException e) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Direct Sales Account Type" });
        }

        this.slsDt = ZYPDateUtil.getSalesDate();

        // Initialization of SSM Custom
        this.ssmBatchClientCustom = new S21SsmBatchClientCustom(this.getClass());

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    }

    @Override
    protected void mainRoutine() {

        // From NMAB265002 mainRoutine.
        mainProcessForCratTempData();

        // From NMAB265003 mainRoutine.
        mainProcessForDelAsgData();

        // From NMAB265004 mainRoutine.
        mainProcessForCratAsgData();

    }

    @Override
    protected void termRoutine() {
        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);
    }

    /**
     * main Process : 02(Create Temporary data)
     */
    private void mainProcessForCratTempData() {
        Map<String, Object> updateCancelFlgParam = new HashMap<String, Object>();
        updateCancelFlgParam.put("glblCmpyCd", glblCmpyCd);
        updateCancelFlgParam.put("trtyGrpTpCdEss", TRTY_GRP_TP.ESS);
        if (DS_ACCT_TP.CUSTOMER.equals(this.directSalesAccountType)) {
            updateCancelFlgParam.put("customer", ZYPConstant.FLG_ON_Y);
        } else if (DS_ACCT_TP.PROSPECT.equals(this.directSalesAccountType)) {
            updateCancelFlgParam.put("prospect", ZYPConstant.FLG_ON_Y);
        }

        S21StopWatch sw = new S21StopWatch();
        sw.start();
        this.ssmBatchClientCustom.update("setCancelFlagTrtyRuleReln", updateCancelFlgParam);
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:setCancelFlagTrtyRuleReln_%s]End [%s]", directSalesAccountType, sw.getElapsedMilliSec()));
        commit();
    }

    /**
     * main Process : 03(Delete Assign data)
     */
    private void mainProcessForDelAsgData() {

        if (DS_ACCT_TP.CUSTOMER.equals(this.directSalesAccountType)) {
            procCustomerForDelAsgData();
        } else if (DS_ACCT_TP.PROSPECT.equals(this.directSalesAccountType)) {
            procProspectForDelAsgData();
        } else {
            return;
        }
    }

    /**
     * Process for Direct Sales Account Type : Customer.
     */
    private void procCustomerForDelAsgData() {
        S21StopWatch sw = new S21StopWatch();
        Map<String, String> paramsATRAL = new HashMap<String, String>();
        paramsATRAL.put("glblCmpyCd", this.glblCmpyCd);
        paramsATRAL.put("tableNameATRAL", TABLE_ACCT_TRTY_ROLE_ASG_LOG);
        
        sw.start();
        ssmBatchClientCustom.delete("truncateTableForDelAsgData", paramsATRAL);
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:truncateTableForDelAsgData_ATRAL]End [%s]", sw.getElapsedMilliSec()));

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        Map<String, String> insertParamsATRA = new HashMap<String, String>();
        insertParamsATRA.put("glblCmpyCd", this.glblCmpyCd);
        insertParamsATRA.put("tableNameATRA", TABLE_ACCT_TRTY_ROLE_ASG);

        try {

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
            sw.start();
            stmtSelect.executeUpdate();
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_SQL:searchInsertTBLForDelAsgData_ATRA]End [%s]", sw.getElapsedMilliSec()));

            commit();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

        rs = null;
        stmtSelect = null;
        Map<String, String> paramsATRA = new HashMap<String, String>();
        paramsATRA.put("glblCmpyCd", this.glblCmpyCd);
        paramsATRA.put("manEntryFlg", ZYPConstant.FLG_OFF_N);
        paramsATRA.put("tableNameATRA", TABLE_ACCT_TRTY_ROLE_ASG);

        try {
            stmtSelect = this.ssmLLClient.createPreparedStatement("deleteTableData", paramsATRA);
            sw.start();
            int deleteCount = stmtSelect.executeUpdate();
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_SQL:deleteTableData_ATRA]End [%s]", sw.getElapsedMilliSec()));
            S21InfoLogOutput.println("ACCT_TRTY_ROLE_ASG delete count: [" + deleteCount + "]");
            commit();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

        Map<String, String> paramsATSAL = new HashMap<String, String>();
        paramsATSAL.put("glblCmpyCd", this.glblCmpyCd);
        paramsATSAL.put("tableNameATSAL", TABLE_ACCT_TRTY_RESRC_LOG);
        sw.start();
        ssmBatchClientCustom.delete("truncateTableForDelAsgData", paramsATSAL);
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:truncateTableForDelAsgData_ATSAL]End [%s]", sw.getElapsedMilliSec()));

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
            sw.start();
            stmtSelect.executeUpdate();
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_SQL:searchInsertRsc_ATRA]End [%s]", sw.getElapsedMilliSec()));

            commit();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

        Map<String, String> paramsATRSA = new HashMap<String, String>();
        paramsATRSA.put("glblCmpyCd", this.glblCmpyCd);
        paramsATRSA.put("tableNameATSA", TABLE_ACCT_TRTY_RESRC_ASG);
        sw.start();
        ssmBatchClientCustom.delete("truncateTableForDelAsgData", paramsATRSA);
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:truncateTableForDelAsgData_ATSA]End [%s]", sw.getElapsedMilliSec()));

        commit();

    }

    /**
     * Process for Direct Sales Account Type : Prospect.
     */
    private void procProspectForDelAsgData() {
        S21StopWatch sw = new S21StopWatch();
        Map<String, String> paramsPTRAL = new HashMap<String, String>();
        paramsPTRAL.put("glblCmpyCd", this.glblCmpyCd);
        paramsPTRAL.put("tableNamePTRAL", TABLE_PROS_TRTY_ROLE_ASG_LOG);
        sw.start();
        ssmBatchClientCustom.delete("truncateTableForDelAsgData", paramsPTRAL);
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:truncateTableForDelAsgData_PTRAL]End [%s]", sw.getElapsedMilliSec()));

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        Map<String, String> insertParamsPTRA = new HashMap<String, String>();
        insertParamsPTRA.put("glblCmpyCd", this.glblCmpyCd);
        insertParamsPTRA.put("tableNamePTRA", TABLE_PROS_TRTY_ROLE_ASG);

        try {
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
            sw.start();
            stmtSelect.executeUpdate();
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_SQL:searchInsertTBLForDelAsgData_PTRA]End [%s]", sw.getElapsedMilliSec()));

            commit();
            
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

        rs = null;
        stmtSelect = null;
        Map<String, String> paramsPTRA = new HashMap<String, String>();
        paramsPTRA.put("glblCmpyCd", this.glblCmpyCd);
        paramsPTRA.put("manEntryFlg", ZYPConstant.FLG_OFF_N);
        paramsPTRA.put("tableNamePTRA", TABLE_PROS_TRTY_ROLE_ASG);
        try {

            stmtSelect = this.ssmLLClient.createPreparedStatement("deleteTableData", paramsPTRA);
            sw.start();
            int deleteCount = stmtSelect.executeUpdate();
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_SQL:deleteTableData_PTRA]End [%s]", sw.getElapsedMilliSec()));
            S21InfoLogOutput.println("PROS_TRTY_ROLE_ASG delete count: [" + deleteCount + "]");
            commit();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

        Map<String, String> paramsPTSAL = new HashMap<String, String>();
        paramsPTSAL.put("glblCmpyCd", this.glblCmpyCd);
        paramsPTSAL.put("tableNamePTSAL", TABLE_PROS_TRTY_RESRC_LOG);
        sw.start();
        ssmBatchClientCustom.delete("truncateTableForDelAsgData", paramsPTSAL);
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:truncateTableForDelAsgData_PTSAL]End [%s]", sw.getElapsedMilliSec()));

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
            sw.start();
            stmtSelect.executeUpdate();
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_SQL:searchInsertRsc_PTRA]End [%s]", sw.getElapsedMilliSec()));

            commit();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

        Map<String, String> paramsPTRSA = new HashMap<String, String>();
        paramsPTRSA.put("glblCmpyCd", this.glblCmpyCd);
        paramsPTRSA.put("tableNamePTSA", TABLE_PROS_TRTY_RESRC_ASG);
        sw.start();
        ssmBatchClientCustom.delete("truncateTableForDelAsgData", paramsPTRSA);
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:truncateTableForDelAsgData_PTSA]End [%s]", sw.getElapsedMilliSec()));
        commit();

    }

    /**
     * main Process : 04(Create Assign data)
     */
    private void mainProcessForCratAsgData() {
        // 2018/08/29 QC#25556 add start
        this.deletePhysicalManual();
        // 2018/08/29 QC#25556 add end

        this.setCancelFlagManual(true);
        this.setCancelFlagManual(false);

        this.makeTempSalesRepRoleAsg();
        this.makeTempSpecialistRoleAsg();
        // 2018/08/29 QC#25556 add start
        this.setZeroCancelFlagManual();
        // 2018/08/29 QC#25556 add end
        this.insertAcctTrtyResrcAsg();

        deleteDupRuleRoleAsg();

        // 2018/08/29 QC#25556 del start
//        deletePhysicalManual();
        // 2018/08/29 QC#25556 del end
    }


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
        
        S21StopWatch sw = new S21StopWatch();
        sw.start();
        int insertCount = this.ssmBatchClientCustom.insert("insertTrtyRoleAsgList", ssmGetLocationParam);
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:insertTrtyRoleAsgList_SalesRep]End [%s]", sw.getElapsedMilliSec()));

        this.totalCount += insertCount;
        this.totalNmlCount += insertCount;
        commit();
    }

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

        S21StopWatch sw = new S21StopWatch();
        sw.start();
        int insertCount = this.ssmBatchClientCustom.insert("insertTrtyRoleAsgList", ssmGetLocationParam);
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:insertTrtyRoleAsgList_Specialist]End [%s]", sw.getElapsedMilliSec()));

        
        this.totalCount += insertCount;
        this.totalNmlCount += insertCount;
        commit();
    }

    private void insertAcctTrtyResrcAsg() {

        Map<String, Object> ssmGetLocationParam = makeTrtyResrcAsgBaseSsmParam();
        ssmGetLocationParam.put("rgtnSts_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmGetLocationParam.put("rgtnSts_P99", RGTN_STS.TERMINATED);
        ssmGetLocationParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
        ssmGetLocationParam.put("dsAcctTp", this.directSalesAccountType);

        S21StopWatch sw = new S21StopWatch();
        sw.start();
        this.ssmBatchClientCustom.insert("insertAcctTrtyResrcAsg", ssmGetLocationParam);
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:insertAcctTrtyResrcAsg]End [%s]", sw.getElapsedMilliSec()));

        commit();
    }

    private void setCancelFlagManual(boolean isSalesRep) {

        // Set Cancel Flag Manual every dsAcctTp salesRep/Specialist Customer/Prospect
        Map<String, Object> ssmSetCancelFlagManualRoleAsignListParam = makeTrtyRAsgSsmParam();
        ssmSetCancelFlagManualRoleAsignListParam.put("dsAcctTp", this.directSalesAccountType);
        if (isSalesRep) {
              ssmSetCancelFlagManualRoleAsignListParam.put("salesRepFlg", ZYPConstant.FLG_ON_Y);
        } else {
              ssmSetCancelFlagManualRoleAsignListParam.put("salesRepFlg", ZYPConstant.FLG_OFF_N);
        }

        S21StopWatch sw = new S21StopWatch();
        sw.start();
        this.ssmBatchClientCustom.update("setCancelFlagManualRoleAsg", ssmSetCancelFlagManualRoleAsignListParam);
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:setCancelFlagManualRoleAsg_%s]End [%s]", isSalesRep, sw.getElapsedMilliSec()));
    }

    private void deletePhysicalManual() {
        // Delete Manual
        Map<String, Object> ssmDeleteCancelFlagManualRoleAsignListParam = makeTrtyRAsgSsmParam();
        S21StopWatch sw = new S21StopWatch();
        sw.start();
        this.ssmBatchClientCustom.delete("deleteCancelFlagManualRoleAsg", ssmDeleteCancelFlagManualRoleAsignListParam);
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:deleteCancelFlagManualRoleAsg]End [%s]", sw.getElapsedMilliSec()));
        commit();
    }

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
        S21StopWatch sw = new S21StopWatch();
        sw.start();
        this.ssmBatchClientCustom.delete("deleteDupRuleRoleAsg", params);
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:deleteDupRuleRoleAsg_%s]End [%s]", directSalesAccountType, sw.getElapsedMilliSec()));
        commit();
    }
    
    private void setZeroCancelFlagManual() {

        // Set Cancel Flag Manual every dsAcctTp salesRep/Specialist Customer/Prospect
        Map<String, Object> ssmSetCancelFlagManualRoleAsignListParam = makeTrtyRAsgSsmParam();

        S21StopWatch sw = new S21StopWatch();
        sw.start();
        this.ssmBatchClientCustom.update("setZeroCancelFlagManualRoleAsg", ssmSetCancelFlagManualRoleAsignListParam);
        sw.stop();
    }
}
