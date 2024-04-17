/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB094001;

import static com.canon.cusa.s21.batch.NSB.NSBB094001.constant.NSBB094001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.CLICK_TECH_WH_WRKTMsg;
import business.db.CLICK_TECH_WH_WRKTMsgArray;
import business.db.DS_BIZ_PROC_LOGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.BaseObjectWrapper;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAWarehouse;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperationsResponse;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ObjectFactory;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperation;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.W6RequestedProperties;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.wrapper.ClickSoftwareOptimizationService;

/**
 * <pre>
 * Send Warehouse to Click
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/06/2016   Hitachi         Y.Zhang         Create          NA
 * 2016/11/08   Hitachi         K.Kojima        Update          QC#15844
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16316
 * 09/06/2017   Hitachi         U.Kim           Update          QC#20868
 * 2021/11/17   CITS            R.Cabral        Update          QC#59387
 * 2023/06/21   Hitachi         K.Chiba         Update          QC#61262
 * </pre>
 */
public class NSBB094001 extends S21BatchMain {

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Current System Timestamp */
    private String currentSystemTs = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** Total Normal Count */
    private int normalCount = 0;

    /** Total Error Count */
    private int errorCount = 0;

    /** Commit Number Count */
    private int commitNumber = 0;

    /** Error Messages */
    private String errorMessages = null;

    /** Last Process Timestamp */
    private Map<String, Object> lastProcTsMap = null;

    /** clicksoft object factory */
    private ObjectFactory objFactory = new ObjectFactory();

    @Override
    protected void initRoutine() {

        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSBM0179E);
        }

        // Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            throw new S21AbendException(NSBM0180E, new String[] {MSG_TXT_SALES_DATE });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COUNT_NUMBER) {
            this.commitNumber = MAX_COUNT_NUMBER;
        }

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        this.currentSystemTs = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);

    }

    @Override
    protected void mainRoutine() {

        // get the last data
        this.lastProcTsMap = getLastProcTs();
        this.currentSystemTs = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);

        // call the web Service
        // START 2021/11/17 R.Cabral [QC#59387, MOD]
        // callWebService();
        String lastProcDt = (String) lastProcTsMap.get(DS_BIZ_LAST_PROC_TS);
        lastProcDt = lastProcDt.substring(0, 8);
        String curSysDt = this.currentSystemTs.substring(0, 8);
        boolean expiredFlag = ZYPDateUtil.compare(curSysDt, lastProcDt) > 0;
        if (expiredFlag) {
            callWebService(expiredFlag);
        }
        callWebService(false);
        insertClickTechWhWrk();
        // END 2021/11/17 R.Cabral [QC#59387, MOD]

        // insert or update DS_BIZ_PROC_LOG
        insOrUpdDsBizProcLog();
        super.commit();

        if (errorMessages != null) {
            // Send mail if error.
            sendErrorMail();
        }
    }

    // START 2021/11/17 R.Cabral [QC#59387, ADD]
    private void insertClickTechWhWrk() {
        CLICK_TECH_WH_WRKTMsg getAllRows = new CLICK_TECH_WH_WRKTMsg();
        getAllRows.setSQLID("001");
        getAllRows.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        CLICK_TECH_WH_WRKTMsgArray allRows = (CLICK_TECH_WH_WRKTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(getAllRows);

        for (int idx = 0; idx < allRows.getValidCount(); idx++) {
            EZDTBLAccessor.remove(allRows.no(idx));
        }

        PreparedStatement stmt = null;
        ResultSet rs = null;

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("rtlSwhCd", RTL_SWH_CD_G);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getClickTechWhWrkBackup", param, setExecParam());
            rs = stmt.executeQuery();

            while (rs.next()) {
                // Replace contents with the backup results
                CLICK_TECH_WH_WRKTMsg backupItem = new CLICK_TECH_WH_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(backupItem.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(backupItem.rtlWhCd, rs.getString(RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(backupItem.rtlSwhCd, rs.getString(RTL_SWH_CD));
                ZYPEZDItemValueSetter.setValue(backupItem.techTocCd, rs.getString(TECH_TOC_CD));
                // START 2023/06/19 K.Chiba [QC#61262, MOD]
                ZYPEZDItemValueSetter.setValue(backupItem.whOwnrPsnTpCd, rs.getString(WH_OWNR_PSN_TP_CD));
                // END 2023/06/19 K.Chiba [QC#61262, MOD]
                EZDTBLAccessor.insert(backupItem);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    // END 2021/11/17 R.Cabral [QC#59387, ADD]

    private Map<String, Object> getLastProcTs() {

        ResultSet rs = null;
        PreparedStatement stmt = null;
        Map<String, Object> dsBizLastProcTsMap = null;

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("procPgmId", PROGRAM_ID);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getDsBizLastProcTs", param, setExecParam());
            rs = stmt.executeQuery();
            if (rs.next()) {
                dsBizLastProcTsMap = new HashMap<String, Object>();
                dsBizLastProcTsMap.put(DS_BIZ_LAST_PROC_TS, rs.getString(DS_BIZ_LAST_PROC_TS));
                dsBizLastProcTsMap.put(DS_BIZ_PROC_LOG_PK, rs.getBigDecimal(DS_BIZ_PROC_LOG_PK));
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        return dsBizLastProcTsMap;
    }

    // START 2021/11/17 R.Cabral [QC#59387, MOD]
    // private void callWebService() {
    private void callWebService(boolean expiredFlag) {
    // END 2021/11/17 R.Cabral [QC#59387, MOD]
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("salesDate", this.salesDate);
        //START 2017/09/06 U.Kim [QC#20868, ADD]
        param.put("rtlSwhCd", RTL_SWH_CD_G);
        //END 2017/09/06 U.Kim [QC#20868, ADD]
        if (this.lastProcTsMap != null) {
            param.put("dsBizLastProcTs", this.lastProcTsMap.get("DS_BIZ_LAST_PROC_TS"));
        }
        try {
            // START 2021/11/17 R.Cabral [QC#59387, MOD]
            // stmt = this.ssmLLClient.createPreparedStatement("getWarehouseInfo", param, setExecParam());
            // rs = stmt.executeQuery();
            // sendTechInvtyAgingWrkInfo(rs);
            if (expiredFlag) {
                param.put("effThruDtExpiredConst", EFF_THRU_DT_EXPIRED_CONST);
                param.put("defaultEffThruDt", DEFAULT_EFF_THRU_DT);
                if (this.lastProcTsMap != null) {
                    String lastProcDt = (String) lastProcTsMap.get(DS_BIZ_LAST_PROC_TS);
                    lastProcDt = lastProcDt.substring(0, 8);
                    param.put("dsBizLastProcTs", lastProcDt);
                }
                stmt = this.ssmLLClient.createPreparedStatement("getExpiredWarehouseInfo", param, setExecParam());
            } else {
                stmt = this.ssmLLClient.createPreparedStatement("getWarehouseInfo", param, setExecParam());
            }
            rs = stmt.executeQuery();
            sendTechInvtyAgingWrkInfo(rs, expiredFlag);
            // END 2021/11/17 R.Cabral [QC#59387, MOD]
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * insert or update DS_BIZ_PROC_LOG
     */
    private void insOrUpdDsBizProcLog() {
        DS_BIZ_PROC_LOGTMsg tmsg = new DS_BIZ_PROC_LOGTMsg();
        if (this.lastProcTsMap == null) {
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tmsg.dsBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BIZ_PROC_LOG_SQ));
            ZYPEZDItemValueSetter.setValue(tmsg.procPgmId, PROGRAM_ID);
            ZYPEZDItemValueSetter.setValue(tmsg.dsBizProcDt, this.currentSystemTs.substring(0, DT_LEN));
            ZYPEZDItemValueSetter.setValue(tmsg.dsBizProcFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(tmsg.dsBizLastProcTs, this.currentSystemTs);
            ZYPEZDItemValueSetter.setValue(tmsg.dsBizLastUpdTs, this.currentSystemTs);
            S21FastTBLAccessor.insert(tmsg);
        } else {
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tmsg.dsBizProcLogPk, (BigDecimal) this.lastProcTsMap.get(DS_BIZ_PROC_LOG_PK));
            DS_BIZ_PROC_LOGTMsg updTMsg = (DS_BIZ_PROC_LOGTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);
            if (updTMsg != null) {
                ZYPEZDItemValueSetter.setValue(updTMsg.dsBizProcDt, this.currentSystemTs.substring(0, DT_LEN));
                ZYPEZDItemValueSetter.setValue(updTMsg.dsBizProcFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(updTMsg.dsBizLastProcTs, this.currentSystemTs);
                ZYPEZDItemValueSetter.setValue(updTMsg.dsBizLastUpdTs, this.currentSystemTs);
                S21FastTBLAccessor.update(updTMsg);
            }
        }
    }

    // START 2021/11/17 R.Cabral [QC#59387, MOD]
    // private void sendTechInvtyAgingWrkInfo(ResultSet rs) {
    private void sendTechInvtyAgingWrkInfo(ResultSet rs, boolean expiredFlag) {
    // END 2021/11/17 R.Cabral [QC#59387, MOD]
        StandardOperations standardOperations = objFactory.createStandardOperations();
        BigDecimal wmbMaxCount = ZYPCodeDataUtil.getNumConstValue("NSBB094001_WMB_MAX_LENGTH", glblCmpyCd);
        if (wmbMaxCount == null) {
            wmbMaxCount = BigDecimal.ZERO;
        }
        try {
            while (rs.next()) {
                if (BigDecimal.ZERO.compareTo(wmbMaxCount) < 0) {
                    if (standardOperations.getOperation().size() > 0 && standardOperations.getOperation().size() >= wmbMaxCount.intValue()) {
                        // Call WMB API
                        callWmbApi(standardOperations);
                        standardOperations = objFactory.createStandardOperations();
                    }
                }
                // START 2023/06/19 K.Chiba [QC#61262, ADD]
                String techTocCd = rs.getString("TECH_TOC_CD");
                if (ZYPCommonFunc.hasValue(techTocCd)) {
                    // END 2023/06/19 K.Chiba [QC#61262, ADD]
                    CSAWarehouse csaWarehouse = objFactory.createCSAWarehouse();
                    boolean activeFlg = false;
                    // START 2021/11/17 R.Cabral [QC#59387, MOD]
                    // if
                    // (ZYPConstant.FLG_ON_Y.equals(rs.getString("ACTV_FLG")))
                    // {
                    if (!expiredFlag && ZYPConstant.FLG_ON_Y.equals(rs.getString("ACTV_FLG"))) {
                        // END 2021/11/17 R.Cabral [QC#59387, MOD]
                        activeFlg = true;
                    }
                    csaWarehouse.setActive(activeFlg);
                    // START 2023/06/19 K.Chiba [QC#61262, MOD]
                    // csaWarehouse.setEngineer(rs.getString("TECH_TOC_CD"));
                    csaWarehouse.setEngineer(techTocCd);
                    // END 2023/06/19 K.Chiba [QC#61262, MOD]
                    // START 2016/11/08 K.Kojima [QC#15844,MOD]
                    // csaWarehouse.setWarehouse(rs.getString("RTL_WH_CD"));
                    csaWarehouse.setWarehouse(rs.getString("RTL_WH_NM"));
                    // END 2016/11/08 K.Kojima [QC#15844,MOD]
                    csaWarehouse.setWHCode(rs.getString("RTL_WH_CD"));
                    W6RequestedProperties props = objFactory.createW6RequestedProperties();
                    StandardOperation standardOperation = objFactory.createStandardOperation();
                    standardOperation.setAction("UpdateOrCreate");
                    standardOperation.setRequestedProperties(props);
                    BaseObjectWrapper bow = objFactory.createBaseObjectWrapper();
                    bow.setObject(csaWarehouse);
                    standardOperation.setObject(bow);
                    standardOperations.getOperation().add(standardOperation);
                    // START 2021/10/25 R.Cabral [QC#59387, ADD]
                    // START 2023/06/19 K.Chiba [QC#61262, ADD]
                    this.normalCount++;
                    // END 2023/06/19 K.Chiba [QC#61262, ADD]
                    // START 2023/06/19 K.Chiba [QC#61262, ADD]
                }
                // END 2023/06/19 K.Chiba [QC#61262, ADD]
                // START 2023/06/19 K.Chiba [QC#61262, MOD]
                // String tecTocCdPrev = getPrevOwner(rs.getString(RTL_WH_CD),rs.getString(RTL_SWH_CD));
                String tecTocCdPrev = getPrevOwner(rs.getString(RTL_WH_CD), rs.getString(RTL_SWH_CD), rs.getString(WH_OWNR_PSN_TP_CD));
                // END 2023/06/19 K.Chiba [QC#61262, MOD]]
                if (ZYPCommonFunc.hasValue(tecTocCdPrev) && !(tecTocCdPrev.equals(rs.getString(TECH_TOC_CD)))) {
                    CSAWarehouse csaExpiredWarehouse = objFactory.createCSAWarehouse();
                    csaExpiredWarehouse.setActive(false);
                    csaExpiredWarehouse.setEngineer(tecTocCdPrev);
                    csaExpiredWarehouse.setWarehouse(rs.getString("RTL_WH_NM"));
                    csaExpiredWarehouse.setWHCode(rs.getString(RTL_WH_CD));
                    W6RequestedProperties expiredProps = objFactory.createW6RequestedProperties();
                    StandardOperation expiredStandardOperation = objFactory.createStandardOperation();
                    expiredStandardOperation.setAction("UpdateOrCreate");
                    expiredStandardOperation.setRequestedProperties(expiredProps);
                    BaseObjectWrapper expiredBow = objFactory.createBaseObjectWrapper();
                    expiredBow.setObject(csaExpiredWarehouse);
                    expiredStandardOperation.setObject(expiredBow);
                    standardOperations.getOperation().add(expiredStandardOperation);
                    // START 2023/06/19 K.Chiba [QC#61262, ADD]
                    this.normalCount++;
                    // END 2023/06/19 K.Chiba [QC#61262, ADD]
                }
                // END 2021/10/25 R.Cabral [QC#59387, ADD]
                // START 2023/06/19 K.Chiba [QC#61262, DEL]
                // this.normalCount++;
                // END 2023/06/19 K.Chiba [QC#61262, DEL]
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }

        if (standardOperations.getOperation().size() > 0) {
            // Call WMB API
            callWmbApi(standardOperations);
        }
    }

    // START 2021/11/17 R.Cabral [QC#59387, ADD]
    // START 2023/06/19 K.Chiba [QC#61262, MOD]
    // private String getPrevOwner(String rtlWhCd, String rtlSwhCd) {
    private String getPrevOwner(String rtlWhCd, String rtlSwhCd, String whOwnrPsnTpCd) {
        // END 2023/06/19 K.Chiba [QC#61262, MOD]
        CLICK_TECH_WH_WRKTMsg msg = new CLICK_TECH_WH_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(msg.rtlWhCd, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(msg.rtlSwhCd, rtlSwhCd);
        // START 2023/06/19 K.Chiba [QC#61262, ADD]
        ZYPEZDItemValueSetter.setValue(msg.whOwnrPsnTpCd, whOwnrPsnTpCd);
        // END 2023/06/19 K.Chiba [QC#61262, ADD]
        CLICK_TECH_WH_WRKTMsg resultMsg = (CLICK_TECH_WH_WRKTMsg) EZDTBLAccessor.findByKey(msg);
        String result = null;
        if (resultMsg != null) {
            result = resultMsg.techTocCd.getValue();
        }
        return result;
    }

    // END 2021/11/17 R.Cabral [QC#59387, ADD]

    private void callWmbApi(StandardOperations standardOperations) {

        try {
            ExecuteMultipleOperations emo = objFactory.createExecuteMultipleOperations();
            emo.setOneTransaction(true);
            emo.setContinueOnError(true);
            emo.setOperations(standardOperations);

            ClickSoftwareOptimizationService proxy = new ClickSoftwareOptimizationService();
            // START 2017/01/04 K.Kitachi [QC#16316, MOD]
//            ExecuteMultipleOperationsResponse response = proxy.executeMultipleOperations(emo);
            ExecuteMultipleOperationsResponse response = proxy.executeMultipleOperations(INTERFACE_ID, emo);
            // END 2017/01/04 K.Kitachi [QC#16316, MOD]

        } catch (Exception e) {
            this.termCd = TERM_CD.WARNING_END;
            this.errorMessages = setErrorInfo(NSBM0181E, null);
            this.errorCount++;
            e.printStackTrace();
            return;
        } catch (Throwable t) {
            this.termCd = TERM_CD.WARNING_END;
            this.errorMessages = setErrorInfo(NSBM0181E, null);
            t.printStackTrace();
            return;
        }
    }

    private S21SsmExecutionParameter setExecParam() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        return execParam;
    }

    private void sendErrorMail() {

        // 1. Get From Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);

        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(NSBM0182E);
        }

        // 2. Get To Address
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NSBM0182E);
        }

        // 3. Get Mail Template.
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_01);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NSBM0183E);
        }

        // 4. Create message for Body
        S21MailAddress fromAddress = addrFromList.get(0);
        S21Mail mail = new S21Mail(glblCmpyCd);

        // 5. Create Subject and Body
        mail.setFromAddress(fromAddress);
        mail.setToAddressList(addrToList);
        template.setTemplateParameter(MAIL_ITEM_BATCH_ID, PROGRAM_ID);
        template.setTemplateParameter(MAIL_ITEM_ERR_DATE, ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT));
        template.setTemplateParameter(MAIL_ITEM_ERR_MSG, this.errorMessages);

        // 6. Call Mail API
        mail.setMailTemplate(template);
        mail.setSubject(template.getSubject(), MAIL_CHARSET);
        mail.postMail();
    }

    /**
     * set error info
     * @param msgId
     * @param apiBizId
     */
    private String setErrorInfo(String msgId, String[] params) {
        S21InfoLogOutput.println(msgId, params);
        return S21MessageFunc.clspGetMessage(msgId, params);
    }

    @Override
    protected void termRoutine() {
        setRecordCount(normalCount, errorCount, (normalCount + errorCount));
        setTermState(termCd);
    }

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSBB094001().executeBatch(NSBB094001.class.getSimpleName());
    }
}
