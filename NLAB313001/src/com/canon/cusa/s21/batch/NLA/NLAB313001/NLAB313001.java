/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLA.NLAB313001;

import static com.canon.cusa.s21.batch.NLA.NLAB313001.constant.NLAB313001Constant.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.TECH_MDSE_LIST_WRKTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCharacterConversionUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TECH_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.common.util.S21StopWatch;
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
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSATop500Parts;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperationsResponse;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ObjectFactory;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperation;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.W6RequestedProperties;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.wrapper.ClickSoftwareOptimizationService;

/**
 * <pre>
 * NLAB313001 Send Top500 Item to Click Batch.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/19/2015   Hitachi         J.Kim           Create          N/A
 * 08/24/2016   Hitachi         A.Kohinata      Update          QC#13701
 * 10/17/2016   Hitachi         N.Arai          Update          QC#15230
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16316
 * 05/23/2017   Hitachi         K.Kitachi       Update          QC#18374
 * 06/05/2017   Hitachi         K.Kitachi       Update          QC#18374
 * 06/05/2018   Fujitsu         T.Murai         Update          QC#25752
 * 2022/02/14   Hitachi         K.Kitachi       Update          QC#59695
 * 2022/10/19   Hitachi         K.Kishimoto     Update          QC#60712
 * 2022/11/25   Hitachi         K.Kitachi       Update          QC#60855
 * </pre>
 */
public class NLAB313001 extends S21BatchMain {

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Current System Timestamp */
    private String currentSystemTs = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    // START 2017/05/23 K.Kitachi [QC#18374, DEL]
//    /** Sales Date */
//    private String salesDate = null;
//
//    /** process Date */
//    private String processDate;
    // END 2017/05/23 K.Kitachi [QC#18374, DEL]

    /** Total Normal Count */
    private int normalCount = 0;

    /** Total Error Count */
    private int errorCount = 0;

    /** Commit Number Count */
    private int commitNumber = 0;

    /** Error Messages */
    private String errorMessages = null;

    /** clicksoft proxy */
    private ClickSoftwareOptimizationService proxy = new ClickSoftwareOptimizationService();

    /** clicksoft object factory */
    private ObjectFactory objFactory = new ObjectFactory();

    // START 2022/10/19 [QC#60712, ADD]
    /** OFS Multi Byte Convert Flag */
    private  boolean ofsMultiByteConvertFlg = false;
    // END   2022/10/19 [QC#60712, ADD]

    @Override
    protected void initRoutine() {
        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NLAM1118E, new String[] {MSG_TXT_GLBL_CMPY_CD });
        }

        // Sales Date
        // START 2017/05/23 K.Kitachi [QC#18374, DEL]
//        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
//        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
//            throw new S21AbendException(NLAM1285E, new String[] {MSG_TXT_SALES_DATE });
//        }
        // END 2017/05/23 K.Kitachi [QC#18374, DEL]

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COUNT_NUMBER) {
            this.commitNumber = MAX_COUNT_NUMBER;
        }

// START 2016/10/17 N.Arai [QC#15230, MOD]
//        this.processDate = ZYPDateUtil.addDays(this.salesDate, -1);
        // START 2017/05/23 K.Kitachi [QC#18374, DEL]
//        this.processDate = this.salesDate;
        // END 2017/05/23 K.Kitachi [QC#18374, DEL]
// END 2016/10/17 N.Arai [QC#15230, MOD]

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // START 2022/10/19 [QC#60712, ADD]
        String strOFSMultiByteConvertFlg = ZYPCodeDataUtil.getVarCharConstValue("OFS_MULTI_BYTE_CONVERT_FLG", this.glblCmpyCd);
        if (ZYPConstant.FLG_ON_Y.equals(strOFSMultiByteConvertFlg)) {
            ofsMultiByteConvertFlg = true;
        }
        // END   2022/10/19 [QC#60712, ADD]
    }

    @Override
    protected void mainRoutine() {

        // TODO
        // Add Start 2018/06/05 QC#25752 performance Check
        S21StopWatch sw = new S21StopWatch();
        sw.start();
        // Add End 2018/06/05 QC#25752 performance Check
        this.currentSystemTs = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);

        callWebService();

        // TODO
        // Add Start 2018/06/05 QC#25752 performance Check
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:callWebService]End [%s]", sw.getElapsedMilliSec()));
        
        sw = new S21StopWatch();
        sw.start();
        // Add End 2018/06/05 QC#25752 performance Check
        
        if (errorMessages == null) {
            updateTechMdseListWrk();
        } else {
            // Send mail if error.
            sendErrorMail();
        }
        // TODO
        // Add Start 2018/06/05 QC#25752 performance Check
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:updateTechMdseListWrk]End [%s]", sw.getElapsedMilliSec()));
        // Add End 2018/06/05 QC#25752 performance Check
        
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

    private void callWebService() {

        ResultSet rs = null;
        PreparedStatement stmt = null;

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        // START 2017/05/23 K.Kitachi [QC#18374, DEL]
//        param.put("techMdseListDt", this.processDate);
        // END 2017/05/23 K.Kitachi [QC#18374, DEL]
        param.put("techProcStsCd", TECH_PROC_STS.WAITING_FOR_PROCESS);

        try {

            // TODO
            // Add Start 2018/06/05 QC#25752 performance Check
            S21StopWatch sw = new S21StopWatch();
            sw.start();
            // Add End 2018/06/05 QC#25752 performance Check

            stmt = this.ssmLLClient.createPreparedStatement("getTechMdseListWrkInfo", param, setExecParam());
            rs = stmt.executeQuery();

            // TODO
            // Add Start 2018/06/05 QC#25752 performance Check
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_SQL:getTechMdseListWrkInfo]End [%s]", sw.getElapsedMilliSec()));
            // Add End 2018/06/05 QC#25752 performance Check

            sendTop500PartsInfo(rs);

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    // mod start 2016/08/24 CSA Defect#13701
    private void sendTop500PartsInfo(ResultSet rs) {

        StandardOperations standardOperations = objFactory.createStandardOperations();
        List<CSATop500Parts> top500PartsList = new ArrayList<CSATop500Parts>();
        // START 2022/02/14 K.Kitachi [QC#59695, DEL]
//        BigDecimal wmbMaxCount = ZYPCodeDataUtil.getNumConstValue("NLAB313001_WMB_MAX_LENGTH", glblCmpyCd);
//        if (wmbMaxCount == null) {
//            wmbMaxCount = BigDecimal.ZERO;
//        }
//        int sendTargetCnt = 0;
        // END 2022/02/14 K.Kitachi [QC#59695, DEL]

        // START 2022/02/14 K.Kitachi [QC#59695, ADD]
        String delFlg = null;
        String prevDelFlg = null;
        // END 2022/02/14 K.Kitachi [QC#59695, ADD]

        try {

            String techId = null;
            String prevTechId = null;

            while (rs.next()) {

                techId = rs.getString("TECH_TOC_CD");
                // START 2022/02/14 K.Kitachi [QC#59695, ADD]
                delFlg = rs.getString("DEL_FLG");
                // END 2022/02/14 K.Kitachi [QC#59695, ADD]

                // START 2022/02/14 K.Kitachi [QC#59695, DEL]
//                if (BigDecimal.ZERO.compareTo(wmbMaxCount) < 0) {
//                    if (sendTargetCnt > 0 && sendTargetCnt + top500PartsList.size() >= wmbMaxCount.intValue()) {
//                        // Call WMB API
//                        callWmbApi(standardOperations);
//                        standardOperations = objFactory.createStandardOperations();
//                        sendTargetCnt = 0;
//                    }
//                }
                // END 2022/02/14 K.Kitachi [QC#59695, DEL]

                // START 2022/02/14 K.Kitachi [QC#59695, MOD]
//                if (ZYPCommonFunc.hasValue(prevTechId) && !techId.equals(prevTechId)) {
                if (isCallWmbApi(techId, delFlg, prevTechId, prevDelFlg)) {
                // END 2022/02/14 K.Kitachi [QC#59695, MOD]

                    for (CSATop500Parts top500Parts : top500PartsList) {
                        StandardOperation standardOperation = objFactory.createStandardOperation();
                        // START 2022/02/14 K.Kitachi [QC#59695, MOD]
//                        standardOperation.setAction("UpdateOrCreate");
                        if (ZYPConstant.FLG_ON_Y.equals(prevDelFlg)) {
                            standardOperation.setAction("Delete");
                        } else {
                            standardOperation.setAction("UpdateOrCreate");
                        }
                        // END 2022/02/14 K.Kitachi [QC#59695, MOD]
                        W6RequestedProperties props = objFactory.createW6RequestedProperties();
                        standardOperation.setRequestedProperties(props);

                        BaseObjectWrapper bow = objFactory.createBaseObjectWrapper();
                        bow.setObject(top500Parts);
                        standardOperation.setObject(bow);
                        standardOperations.getOperation().add(standardOperation);
                        // START 2022/02/14 K.Kitachi [QC#59695, DEL]
//                        sendTargetCnt++;
                        // END 2022/02/14 K.Kitachi [QC#59695, DEL]
                    }
                    // START 2022/02/14 K.Kitachi [QC#59695, ADD]
                    // Call WMB API
                    callWmbApi(standardOperations);
                    standardOperations = objFactory.createStandardOperations();
                    // END 2022/02/14 K.Kitachi [QC#59695, ADD]
                    top500PartsList = new ArrayList<CSATop500Parts>();
                }

                CSATop500Parts top500Parts = objFactory.createCSATop500Parts();
                top500Parts.setEmployeeNumber(rs.getString("TECH_TOC_CD"));
                top500Parts.setPartNumber(rs.getString("MDSE_CD"));
                // START 2022/10/19 [QC#60712, MOD]
//                top500Parts.setDescripton(rs.getString("MDSE_NM"));
                top500Parts.setDescripton(ofsMultiByteConvert(rs.getString("MDSE_NM")));
                // END   2022/10/19 [QC#60712, MOD]
                top500PartsList.add(top500Parts);

                prevTechId = techId;
                // START 2022/02/14 K.Kitachi [QC#59695, ADD]
                prevDelFlg = delFlg;
                // END 2022/02/14 K.Kitachi [QC#59695, ADD]
                this.normalCount++;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }

        // START 2022/02/14 K.Kitachi [QC#59695, MOD]
//        if (sendTargetCnt + top500PartsList.size() > 0) {
        if (top500PartsList.size() > 0) {
        // END 2022/02/14 K.Kitachi [QC#59695, MOD]
            for (CSATop500Parts top500Parts : top500PartsList) {
                StandardOperation standardOperation = objFactory.createStandardOperation();
                // START 2022/02/14 K.Kitachi [QC#59695, MOD]
//                standardOperation.setAction("UpdateOrCreate");
                if (ZYPConstant.FLG_ON_Y.equals(prevDelFlg)) {
                    standardOperation.setAction("Delete");
                } else {
                    standardOperation.setAction("UpdateOrCreate");
                }
                // END 2022/02/14 K.Kitachi [QC#59695, MOD]
                W6RequestedProperties props = objFactory.createW6RequestedProperties();
                standardOperation.setRequestedProperties(props);

                BaseObjectWrapper bow = objFactory.createBaseObjectWrapper();
                bow.setObject(top500Parts);
                standardOperation.setObject(bow);
                standardOperations.getOperation().add(standardOperation);
            }
            // Call WMB API
            callWmbApi(standardOperations);
        }
    }
    // mod end 2016/08/24 CSA Defect#13701

    // START 2022/02/14 K.Kitachi [QC#59695, ADD]
    private boolean isCallWmbApi(String techId, String delFlg, String prevTechId, String prevDelFlg) {
        if (!ZYPCommonFunc.hasValue(prevTechId) && !ZYPCommonFunc.hasValue(prevDelFlg)) {
            return false;
        }
        // START 2022/11/25 K.Kitachi [QC#60855, DEL]
//        if (ZYPConstant.FLG_ON_Y.equals(prevDelFlg)) {
//            return true;
//        }
        // END 2022/11/25 K.Kitachi [QC#60855, DEL]
        if (prevTechId.equals(techId) && prevDelFlg.equals(delFlg)) {
            return false;
        }
        return true;
    }
    // END 2022/02/14 K.Kitachi [QC#59695, ADD]

    private void callWmbApi(StandardOperations standardOperations) {

        ExecuteMultipleOperations emo = objFactory.createExecuteMultipleOperations();
        emo.setOneTransaction(true);
        emo.setContinueOnError(true);
        emo.setOperations(standardOperations);

        try {

            // START 2017/01/04 K.Kitachi [QC#16316, MOD]
//            ExecuteMultipleOperationsResponse response = this.proxy.executeMultipleOperations(emo);
            ExecuteMultipleOperationsResponse response = this.proxy.executeMultipleOperations(INTERFACE_ID, emo);
            // END 2017/01/04 K.Kitachi [QC#16316, MOD]

        } catch (Exception e) {
            // START 2017/06/05 K.Kitachi [QC#18374, DEL]
//            this.termCd = TERM_CD.WARNING_END;
//            this.errorMessages = setErrorInfo(NLAM1315E, null);
//            this.errorCount++;
            // END 2017/06/05 K.Kitachi [QC#18374, DEL]

            e.printStackTrace();
            return;
        } catch (Throwable t) {
            // START 2017/06/05 K.Kitachi [QC#18374, DEL]
//            this.termCd = TERM_CD.WARNING_END;
//            this.errorMessages = setErrorInfo(NLAM1315E, null);
//            this.errorCount++;
            // END 2017/06/05 K.Kitachi [QC#18374, DEL]

            t.printStackTrace();
            return;
        }
    }

    private void updateTechMdseListWrk() {

        ResultSet rs = null;
        PreparedStatement stmt = null;

        List<TECH_MDSE_LIST_WRKTMsg> inTMsgList = new ArrayList<TECH_MDSE_LIST_WRKTMsg>();
        Map<String, String> inParam = new HashMap<String, String>();
        inParam.put("glblCmpyCd", this.glblCmpyCd);
        // START 2017/05/23 K.Kitachi [QC#18374, DEL]
//        inParam.put("techMdseListDt", this.processDate);
        // END 2017/05/23 K.Kitachi [QC#18374, DEL]
        inParam.put("techProcStsCd", TECH_PROC_STS.WAITING_FOR_PROCESS);

        try {

            // TODO
            // Add Start 2018/06/05 QC#25752 performance Check
            S21StopWatch sw = new S21StopWatch();
            sw.start();
            // Add End 2018/06/05 QC#25752 performance Check
            stmt = this.ssmLLClient.createPreparedStatement("getTechMdseListWrkInfo", inParam, setExecParam());
            rs = stmt.executeQuery();

            // TODO
            // Add Start 2018/06/05 QC#25752 performance Check
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_SQL:getTechMdseListWrkInfo]End [%s]", sw.getElapsedMilliSec()));
            // Add End 2018/06/05 QC#25752 performance Check

            while (rs.next()) {
                TECH_MDSE_LIST_WRKTMsg inTMsg = new TECH_MDSE_LIST_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.techMdseListWrkPk, rs.getBigDecimal(TECH_MDSE_LIST_WRK_PK));

                TECH_MDSE_LIST_WRKTMsg updateTiaw = (TECH_MDSE_LIST_WRKTMsg) S21FastTBLAccessor.findByKeyForUpdate(inTMsg);
                ZYPEZDItemValueSetter.setValue(updateTiaw.techProcStsCd, TECH_PROC_STS.PROCESSED);
                inTMsgList.add(updateTiaw);

                if (this.commitNumber == inTMsgList.size()) {
                    executionUpdate(inTMsgList);
                    inTMsgList = new ArrayList<TECH_MDSE_LIST_WRKTMsg>();
                }
            }

            if (inTMsgList.size() > 0) {
                executionUpdate(inTMsgList);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void executionUpdate(List<TECH_MDSE_LIST_WRKTMsg> inTMsgList) {

        TECH_MDSE_LIST_WRKTMsg[] inTMsgArray = new TECH_MDSE_LIST_WRKTMsg[inTMsgList.size()];
        S21FastTBLAccessor.update(inTMsgList.toArray(inTMsgArray));
        return;
    }

    private void sendErrorMail() {

        // 1. Get From Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);

        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"FROM mail-address.", (MAIL_GROUP_ID_FROM) });
        }

        // 2. Get To Address
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // 3. Get Mail Template.
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID_01);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NSAM0069E, new String[] {"Mailtemplate", MAIL_TEMPLATE_ID_01 });
        }

        // 4. Create message for Body
        S21MailAddress fromAddress = addrFromList.get(0);
        S21Mail mail = new S21Mail(this.glblCmpyCd);

        // 5. Create Subject and Body
        mail.setFromAddress(fromAddress);
        mail.setToAddressList(addrToList);
        template.setTemplateParameter(MAIL_ITEM_BATCH_ID, BATCH_PROGRAM_ID);
        template.setTemplateParameter(MAIL_ITEM_ERR_DATE, this.currentSystemTs);
        template.setTemplateParameter(MAIL_ITEM_ERR_MSG, this.errorMessages.toString());

        // 6. Call Mail API
        mail.setMailTemplate(template);
        mail.setSubject(template.getSubject(), MAIL_CHARSET);
        mail.postMail();
    }

    @Override
    protected void termRoutine() {
        setRecordCount(normalCount, errorCount, (normalCount + errorCount));
        setTermState(termCd);
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

    /**
     * @param args
     */
    public static void main(String[] args) {
        new NLAB313001().executeBatch(NLAB313001.class.getSimpleName());

    }
    // START 2022/10/19 [QC#60712, ADD]
    /** OFS Multi Byte Convert Flag */
    private String ofsMultiByteConvert(String inStr) {
        if (this.ofsMultiByteConvertFlg == false) {
            return inStr;
        }
        String outStr = ZYPCharacterConversionUtil.convertCharacter(inStr);
        return outStr;
    }
    // END   2022/10/19 [QC#60712, ADD]

}
