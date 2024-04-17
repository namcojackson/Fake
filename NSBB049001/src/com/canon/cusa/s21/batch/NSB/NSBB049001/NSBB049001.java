/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB049001;

import static com.canon.cusa.s21.batch.NSB.NSBB049001.constant.NSBB049001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.SVC_BAT_ERR_LOGTMsg;
import business.db.SVC_MBL_DICTTMsg;

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
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperationsResponse;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ObjectFactory;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperation;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.W6RequestedProperties;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSABillCode;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSACorrectionCode;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSALocationCode;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAProblemCode;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAReasonCode;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.Skill;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.TaskType;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.wrapper.ClickSoftwareOptimizationService;

/**
 * <pre>
 * Send Dictionary to Click Software Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/10/2015   Hitachi         J.Kim           Create
 * 11/01/2016   Hitachi         T.Tomita        Update          QC#15711
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16316
 * 2017/03/03   Hitachi         K.Kojima        Update          QC#17943
 * 2018/08/06   Hitachi         K.Kojima        Update          QC#27565
 * 2022/08/30   Hitachi         K.Kitachi       Update          QC#60518
 * </pre>
 */
public class NSBB049001 extends S21BatchMain {

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Current System Timestamp */
    private String currentSystemTs = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

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

    /** clicksoft proxy */
    private ClickSoftwareOptimizationService proxy = new ClickSoftwareOptimizationService();

    /** clicksoft object factory */
    private ObjectFactory objFactory = new ObjectFactory();

    /** SVC_MBL_DICT_TP_TXT */
    private String svcMblDictTpTxt = null;

    // add start 2016/11/01 CSA Defect#15711
    /** WMB_MAX_CONUNT */
    private BigDecimal wmbMaxCount = null;
    // add end 2016/11/01 CSA Defect#15711

    @Override
    protected void initRoutine() {

        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSAM0219E, new String[] {MSG_TXT_GLBL_CMPY_CD });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COUNT_NUMBER) {
            this.commitNumber = MAX_COUNT_NUMBER;
        }

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {

        this.lastProcTsMap = getLastProcTs();
        this.currentSystemTs = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);
        // add start 2016/11/01 CSA Defect#15711
        this.wmbMaxCount = ZYPCodeDataUtil.getNumConstValue("NSBB049001_WMB_MAX_LENGTH", this.glblCmpyCd);
        if (this.wmbMaxCount == null || BigDecimal.ZERO.compareTo(this.wmbMaxCount) > 0) {
            this.wmbMaxCount = BigDecimal.ZERO;
        }
        // add end 2016/11/01 CSA Defect#15711

        callWebService();

        insOrUpdDsBizProcLog();
        super.commit();

        if (errorMessages != null) {
            // Insert Service Batch Error Log
            insertServiceBatchErrorLog(this.svcMblDictTpTxt);
            super.commit();
            // Send mail if error.
            sendErrorMail();
        }
    }

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

    private void callWebService() {

        ResultSet rs = null;
        PreparedStatement stmt = null;

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getSvcMblDictInfo", param, setExecParam());
            rs = stmt.executeQuery();

            getSendDictionaryInfo(rs);

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    // mod start 2016/11/01 CSA Defect#15711
    private boolean sendDictionaryInfo(List<SVC_MBL_DICTTMsg> smdList, String dictTbl) {

        StandardOperations standardOperations = objFactory.createStandardOperations();
        int cnt = 0;
        // Bill Code
        if (SVC_BILL_TP.equals(dictTbl)) {
            for (SVC_MBL_DICTTMsg smdMsg : smdList) {
                StandardOperation standardOperation = objFactory.createStandardOperation();
                standardOperation.setAction("UpdateOrCreate");
                W6RequestedProperties props = objFactory.createW6RequestedProperties();
                standardOperation.setRequestedProperties(props);

                CSABillCode billcode = objFactory.createCSABillCode();
                // Dictionary Type
                // Name
                billcode.setName(smdMsg.svcMblDictNm.getValue());
                // Description
                billcode.setDescription(smdMsg.svcMblDictDescTxt.getValue());
                // Status
                billcode.setStatus(setStatus(smdMsg.ezCancelFlag.getValue()));
                // Attribute1
                billcode.setAttribute1(smdMsg.svcMblDictAttrbTxt_01.getValue());
                // Attribute2
                billcode.setAttribute2(smdMsg.svcMblDictAttrbTxt_02.getValue());

                BaseObjectWrapper bow = objFactory.createBaseObjectWrapper();
                bow.setObject(billcode);
                standardOperation.setObject(bow);
                standardOperations.getOperation().add(standardOperation);
                cnt++;
            }
        }

        // Correction Code
        if (SVC_PBLM_CRCT_TP.equals(dictTbl)) {
            for (SVC_MBL_DICTTMsg smdMsg : smdList) {
                StandardOperation standardOperation = objFactory.createStandardOperation();
                standardOperation.setAction("UpdateOrCreate");
                W6RequestedProperties props = objFactory.createW6RequestedProperties();
                standardOperation.setRequestedProperties(props);

                CSACorrectionCode correctionCode = objFactory.createCSACorrectionCode();
                // Dictionary Type
                // Name
                correctionCode.setName(smdMsg.svcMblDictNm.getValue());
                // Description
                correctionCode.setDescription(smdMsg.svcMblDictDescTxt.getValue());
                // Status
                correctionCode.setStatus(setStatus(smdMsg.ezCancelFlag.getValue()));
                // Attribute1
                correctionCode.setAttribute1(smdMsg.svcMblDictAttrbTxt_01.getValue());
                // Attribute2
                correctionCode.setAttribute2(smdMsg.svcMblDictAttrbTxt_02.getValue());

                BaseObjectWrapper bow = objFactory.createBaseObjectWrapper();
                bow.setObject(correctionCode);
                standardOperation.setObject(bow);
                standardOperations.getOperation().add(standardOperation);
                cnt++;
            }
        }

        // Location Code
        if (SVC_PBLM_LOC_TP.equals(dictTbl)) {
            for (SVC_MBL_DICTTMsg smdMsg : smdList) {
                StandardOperation standardOperation = objFactory.createStandardOperation();
                standardOperation.setAction("UpdateOrCreate");
                W6RequestedProperties props = objFactory.createW6RequestedProperties();
                standardOperation.setRequestedProperties(props);

                CSALocationCode locationCode = objFactory.createCSALocationCode();
                // Dictionary Type
                // Name
                locationCode.setName(smdMsg.svcMblDictNm.getValue());
                // Description
                locationCode.setDescription(smdMsg.svcMblDictDescTxt.getValue());
                // Status
                locationCode.setStatus(setStatus(smdMsg.ezCancelFlag.getValue()));
                // Attribute1
                locationCode.setAttribute1(smdMsg.svcMblDictAttrbTxt_01.getValue());
                // Attribute2
                locationCode.setAttribute2(smdMsg.svcMblDictAttrbTxt_02.getValue());

                BaseObjectWrapper bow = objFactory.createBaseObjectWrapper();
                bow.setObject(locationCode);
                standardOperation.setObject(bow);
                standardOperations.getOperation().add(standardOperation);
                cnt++;
            }
        }

        // Problem Code
        if (SVC_PBLM_TP.equals(dictTbl)) {
            for (SVC_MBL_DICTTMsg smdMsg : smdList) {
                StandardOperation standardOperation = objFactory.createStandardOperation();
                standardOperation.setAction("UpdateOrCreate");
                W6RequestedProperties props = objFactory.createW6RequestedProperties();
                standardOperation.setRequestedProperties(props);

                CSAProblemCode problemCode = objFactory.createCSAProblemCode();
                // Dictionary Type
                // Name
                problemCode.setName(smdMsg.svcMblDictNm.getValue());
                // Description
                problemCode.setDescription(smdMsg.svcMblDictDescTxt.getValue());
                // Status
                problemCode.setStatus(setStatus(smdMsg.ezCancelFlag.getValue()));
                // Attribute1
                problemCode.setAttribute1(smdMsg.svcMblDictAttrbTxt_01.getValue());
                // Attribute2
                problemCode.setAttribute2(setAttribute(smdMsg.svcMblDictAttrbTxt_02.getValue()));

                BaseObjectWrapper bow = objFactory.createBaseObjectWrapper();
                bow.setObject(problemCode);
                standardOperation.setObject(bow);
                standardOperations.getOperation().add(standardOperation);
                cnt++;
            }
        }

        // Reason Code
        if (SVC_PBLM_RSN_TP.equals(dictTbl)) {
            for (SVC_MBL_DICTTMsg smdMsg : smdList) {
                StandardOperation standardOperation = objFactory.createStandardOperation();
                standardOperation.setAction("UpdateOrCreate");
                W6RequestedProperties props = objFactory.createW6RequestedProperties();
                standardOperation.setRequestedProperties(props);

                CSAReasonCode reasonCode = objFactory.createCSAReasonCode();
                // Dictionary Type
                // Name
                reasonCode.setName(smdMsg.svcMblDictNm.getValue());
                // Description
                reasonCode.setDescription(smdMsg.svcMblDictDescTxt.getValue());
                // Status
                reasonCode.setStatus(setStatus(smdMsg.ezCancelFlag.getValue()));
                // Attribute1
                reasonCode.setAttribute1(smdMsg.svcMblDictAttrbTxt_01.getValue());
                // Attribute2
                reasonCode.setAttribute2(smdMsg.svcMblDictAttrbTxt_02.getValue());

                BaseObjectWrapper bow = objFactory.createBaseObjectWrapper();
                bow.setObject(reasonCode);
                standardOperation.setObject(bow);
                standardOperations.getOperation().add(standardOperation);
                cnt++;
            }
        }

        // Skills
        if (SVC_SKILL.equals(dictTbl)) {
            for (SVC_MBL_DICTTMsg smdMsg : smdList) {
                StandardOperation standardOperation = objFactory.createStandardOperation();
                standardOperation.setAction("UpdateOrCreate");
                W6RequestedProperties props = objFactory.createW6RequestedProperties();
                standardOperation.setRequestedProperties(props);

                Skill skill = objFactory.createSkill();
                // Dictionary Type
                // Name
                skill.setName(smdMsg.svcMblDictNm.getValue());
                // Description
                skill.setDescription(smdMsg.svcMblDictDescTxt.getValue());
                // Status
                skill.setStatus(setStatus(smdMsg.ezCancelFlag.getValue()));

                BaseObjectWrapper bow = objFactory.createBaseObjectWrapper();
                bow.setObject(skill);
                standardOperation.setObject(bow);
                standardOperations.getOperation().add(standardOperation);
                cnt++;
            }
        }

        // Task Types
        if (DS_SVC_CALL_TP.equals(dictTbl)) {
            for (SVC_MBL_DICTTMsg smdMsg : smdList) {
                StandardOperation standardOperation = objFactory.createStandardOperation();
                standardOperation.setAction("UpdateOrCreate");
                W6RequestedProperties props = objFactory.createW6RequestedProperties();
                standardOperation.setRequestedProperties(props);

                TaskType taskType = objFactory.createTaskType();
                // Dictionary Type
                // Name
                taskType.setName(smdMsg.svcMblDictNm.getValue());
                // Description
                taskType.setDescription(smdMsg.svcMblDictDescTxt.getValue());

                BaseObjectWrapper bow = objFactory.createBaseObjectWrapper();
                bow.setObject(taskType);
                standardOperation.setObject(bow);
                standardOperations.getOperation().add(standardOperation);
                cnt++;
            }
        }

        if (cnt == 0) {
            return true;
        }
        // Call WMB API
        return callWmbApi(standardOperations);
    }
    // mod end 2016/11/01 CSA Defect#15711

    private boolean setStatus(String statusFlg) {

        boolean rtrnFlg = false;
        if (ZYPConstant.FLG_OFF_0.equals(statusFlg)) {
            rtrnFlg = true;
        }
        return rtrnFlg;
    }

    private Integer setAttribute(String str) {

        int attribute = 0;
        if (!str.isEmpty()) {
            attribute = Integer.valueOf(str);
        }
        return attribute;
    }

    private void getSendDictionaryInfo(ResultSet rsSmd) {

        ResultSet rsSdi = null;
        PreparedStatement stmtSdi = null;

        try {

            while (rsSmd.next()) {

                List<SVC_MBL_DICTTMsg> smdList = new ArrayList<SVC_MBL_DICTTMsg>();

                StringBuffer sb = new StringBuffer();
                sb.append("SELECT ");
                sb.append(rsSmd.getString("SVC_MBL_DICT_NM"));
                sb.append(STR_CNM);
                sb.append(rsSmd.getString("SVC_MBL_DICT_DESC_TXT"));
                sb.append(STR_CNM);
                sb.append(rsSmd.getString("SVC_MBL_DICT_ATTRB_TXT_01"));
                sb.append(STR_CNM);
                sb.append(rsSmd.getString("SVC_MBL_DICT_ATTRB_TXT_02"));
                sb.append(STR_CNM);
                sb.append("EZCANCELFLAG ");
                sb.append("FROM ");
                sb.append(rsSmd.getString("SVC_MBL_DICT_TBL_TXT"));
                // START 2022/08/30 K.Kitachi [QC#60518, ADD]
                sb.append(" A ");
                // END 2022/08/30 K.Kitachi [QC#60518, ADD]
                sb.append(" WHERE ");
                sb.append(" GLBL_CMPY_CD = '");
                sb.append(this.glblCmpyCd);
                sb.append("'");
                if (this.lastProcTsMap != null) {
                    // mod start 2016/11/01 CSA Defect#15711
//                    sb.append(" AND ( EZINTIME > '");
//                    sb.append(this.lastProcTsMap.get(DS_BIZ_LAST_PROC_TS));
//                    sb.append("' OR EZUPTIME > '");
//                    sb.append(this.lastProcTsMap.get(DS_BIZ_LAST_PROC_TS));
//                    sb.append("') ");
                    // START 2022/08/30 K.Kitachi [QC#60518, MOD]
//                    sb.append(" AND EZUPTIME > '");
//                    sb.append(this.lastProcTsMap.get(DS_BIZ_LAST_PROC_TS));
//                    sb.append("' AND EZUPTIME <= '");
//                    sb.append(this.currentSystemTs);
//                    sb.append("'");
                    if (SVC_SKILL.equals(rsSmd.getString("SVC_MBL_DICT_TBL_TXT")) || DS_SVC_CALL_TP.equals(rsSmd.getString("SVC_MBL_DICT_TBL_TXT"))) {
                        sb.append(" AND EZUPTIME > '");
                        sb.append(this.lastProcTsMap.get(DS_BIZ_LAST_PROC_TS));
                        sb.append("' AND EZUPTIME <= '");
                        sb.append(this.currentSystemTs);
                        sb.append("'");
                    } else {
                        sb.append(" AND EXISTS (SELECT 1 FROM ");
                        sb.append(rsSmd.getString("SVC_MBL_DICT_TBL_TXT"));
                        sb.append(" B");
                        sb.append(" WHERE A.GLBL_CMPY_CD = B.GLBL_CMPY_CD");
                        sb.append(" AND B.EZUPTIME > '");
                        sb.append(this.lastProcTsMap.get(DS_BIZ_LAST_PROC_TS));
                        sb.append("')");
                    }
                    // END 2022/08/30 K.Kitachi [QC#60518, MOD]
                    // mod end 2016/11/01 CSA Defect#15711
                }

                Map<String, Object> param = new HashMap<String, Object>();
                param.put("gqlString", sb.toString());

                stmtSdi = this.ssmLLClient.createPreparedStatement("getSendDictionaryInfo", param, setExecParam());
                rsSdi = stmtSdi.executeQuery();

                // mod start 2016/11/01 CSA Defect#15711
                while (rsSdi.next()) {
                    SVC_MBL_DICTTMsg tMsg = new SVC_MBL_DICTTMsg();
                    setValue(tMsg.svcMblDictTpTxt, rsSmd.getString("SVC_MBL_DICT_TP_TXT"));
                    setValue(tMsg.svcMblDictNm, rsSdi.getString(1));
                    setValue(tMsg.svcMblDictDescTxt, rsSdi.getString(2));
                    setValue(tMsg.ezCancelFlag, rsSdi.getString(5));
                    setValue(tMsg.svcMblDictAttrbTxt_01, rsSdi.getString(3));
                    setValue(tMsg.svcMblDictAttrbTxt_02, rsSdi.getString(4));
                    smdList.add(tMsg);

                    if (smdList.size() >= this.wmbMaxCount.intValue()) {
                        if (!sendDictionaryInfo(smdList, rsSmd.getString("SVC_MBL_DICT_TBL_TXT"))) {
                            // START 2018/08/06 K.Kojima [QC#27565,ADD]
                            smdList = new ArrayList<SVC_MBL_DICTTMsg>();
                            // END 2018/08/06 K.Kojima [QC#27565,ADD]
                            // START 2017/03/03 K.Kojima [QC#17943,MOD]
                            // return;
                            continue;
                            // END 2017/03/03 K.Kojima [QC#17943,MOD]
                        }
                        this.normalCount = this.normalCount + smdList.size();
                        smdList = new ArrayList<SVC_MBL_DICTTMsg>();
                    }
                }

                if (smdList.size() == 0) {
                    // START 2017/03/03 K.Kojima [QC#17943,MOD]
                    // return;
                    continue;
                    // END 2017/03/03 K.Kojima [QC#17943,MOD]
                }
                // Send Dictionary
                if (!sendDictionaryInfo(smdList, rsSmd.getString("SVC_MBL_DICT_TBL_TXT"))) {
                    // START 2017/03/03 K.Kojima [QC#17943,MOD]
                    // return;
                    continue;
                    // END 2017/03/03 K.Kojima [QC#17943,MOD]
                }
                this.normalCount = this.normalCount + smdList.size();
                // mod end 2016/11/01 CSA Defect#15711
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSdi, rsSdi);
        }
    }

    // mod start 2016/11/01 CSA Defect#15711
    private boolean callWmbApi(StandardOperations standardOperations) {

        ExecuteMultipleOperations emo = objFactory.createExecuteMultipleOperations();
        emo.setOneTransaction(true);
        emo.setContinueOnError(true);
        emo.setOperations(standardOperations);

        try {

            // START 2017/01/04 K.Kitachi [QC#16316, MOD]
//            ExecuteMultipleOperationsResponse response = proxy.executeMultipleOperations(emo);
            ExecuteMultipleOperationsResponse response = proxy.executeMultipleOperations(INTERFACE_ID, emo);
            // END 2017/01/04 K.Kitachi [QC#16316, MOD]

        } catch (Exception e) {
            this.termCd = TERM_CD.WARNING_END;
            this.errorMessages = setErrorInfo(NLAM1315E, null);
            this.errorCount++;

            e.printStackTrace();
            return false;
        } catch (Throwable t) {
            this.termCd = TERM_CD.WARNING_END;
            this.errorMessages = setErrorInfo(NLAM1315E, null);
            this.errorCount++;

            t.printStackTrace();
            return false;
        }
        return true;
    }
    // mod end 2016/11/01 CSA Defect#15711

    private S21SsmExecutionParameter setExecParam() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        return execParam;
    }

    private void insertServiceBatchErrorLog(String svcTssEsclPk) {

        this.termCd = TERM_CD.WARNING_END;

        SVC_BAT_ERR_LOGTMsg inParam = new SVC_BAT_ERR_LOGTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_BAT_ERR_LOG_SQ"));
        ZYPEZDItemValueSetter.setValue(inParam.bizAppId, BATCH_PROGRAM_ID);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrLogTs, this.currentSystemTs);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNum_01, svcTssEsclPk);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNm_01, SVC_MBL_DICT_TP_TXT);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrMsgTxt, this.errorMessages);
        S21FastTBLAccessor.insert(inParam);
    }

    private void sendErrorMail() {

        // 1. Get From Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_GROUP_KEY_FROM);

        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"FROM mail-address.", (MAIL_GROUP_ID_FROM + "/" + MAIL_GROUP_KEY_FROM) });
        }

        // 2. Get To Address
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // 3. Get Mail Template.
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_01);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NSAM0069E, new String[] {"Mailtemplate", MAIL_TEMPLATE_ID_01 });
        }

        // 4. Create message for Body
        S21MailAddress fromAddress = addrFromList.get(0);
        S21Mail mail = new S21Mail(glblCmpyCd);

        // 5. Create Subject and Body
        mail.setFromAddress(fromAddress);
        mail.setToAddressList(addrToList);
        template.setTemplateParameter(MAIL_ITEM_BATCH_ID, BATCH_PROGRAM_ID);
        template.setTemplateParameter(MAIL_ITEM_ERR_DATE, ZYPDateUtil.formatEzd17ToDisp(this.currentSystemTs));
        template.setTemplateParameter(MAIL_ITEM_ERR_MSG, this.errorMessages.toString());

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
     * @param args
     */
    public static void main(String[] args) {
        new NSBB049001().executeBatch(NSBB049001.class.getSimpleName());
    }

}
