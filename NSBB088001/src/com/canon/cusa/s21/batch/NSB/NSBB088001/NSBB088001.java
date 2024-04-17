/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB088001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMessageInfo;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CLICK_FLD_RQSTTMsg;
import business.db.SVC_BAT_ERR_LOGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.BaseObjectWrapper;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAFieldRequest;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAFieldRequestStatusReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CountryReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperationsResponse;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteOperation;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteOperationResponse;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ObjectFactory;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperation;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.W6RequestedProperties;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.wrapper.ClickSoftwareOptimizationService;


/**
 *<pre>
 *  Send Field Request to Click Software
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/28/2015   Fujitsu         S.Nakai         Create          N/A
 * 2016/10/25   Hitachi         K.Kojima        Update          QC#15491
 * 11/17/2016   Hitachi         K.Ochiai        Update          QC#16024
 * 12/12/2016   Hitachi         Y.Takeno        Update          QC#16316
 *</pre>
 */
public class NSBB088001 extends S21BatchMain implements NSBB088001Constant {

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** global company code */
    private String glblCmpyCd = "";

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** Current System Timestamp */
    private String currentSystemTs = null;

    /** Error Messages */
    private Map<BigDecimal, EZDMessageInfo> errorMessages = new LinkedHashMap<BigDecimal, EZDMessageInfo>();

    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    /** Error Key Map */
    List<Map<String, Object>> errKeyMap = null;

    /** clicksoft object factory */
    ObjectFactory objFactory = new ObjectFactory();

    /** clicksoft proxy */
    private ClickSoftwareOptimizationService proxy = new ClickSoftwareOptimizationService();

    /**
     * @param args parameter
     */
    public static void main(String[] args) {

        new NSBB088001().executeBatch(NSBB088001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // blank check(Global Company Code)
        glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NSBM0032E, new String[] {"Global Company Code" });
        }
        // Initialize
        this.errKeyMap = new ArrayList<Map<String, Object>>();
    }

    @Override
    protected void mainRoutine() {

        this.currentSystemTs = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);

        // Field Request Information.
        sendFldRqst();

        // Send Mail if error or warning occurred.
        if (!errorMessages.isEmpty()) {
            termCd = TERM_CD.WARNING_END;
            insertErrorData();
            sendErrorMail();
        }
    }


    @Override
    protected void termRoutine() {

        setRecordCount(totalNmlCount, totalErrCount, (totalNmlCount + totalErrCount));
        setTermState(termCd);
    }

    /**
     * Send Task Information.
     */
    private void sendFldRqst() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            // get Task Information.
            stmt = ssmLLClient.createPreparedStatement("getFieldRqst", getFldRqstParam());
            rs = stmt.executeQuery();

            while (rs.next()) {

                if (sendFldRqstApi(rs)) {
                    // START 2016/10/25 K.Kojima [QC#15491,DEL]
                    // totalNmlCount++;
                    // END 2016/10/25 K.Kojima [QC#15491,DEL]
                    commit();
                } else {
                    totalErrCount++;
                    rollback();
                }
            }

        } catch (SQLException e) {
            rollback();
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    private boolean sendFldRqstApi(ResultSet rs) throws SQLException {

        BigDecimal clickFldRqstPk = rs.getBigDecimal(CLICK_FLD_RQST_PK);

        StandardOperation standardOperation = objFactory.createStandardOperation();
        standardOperation.setAction("UpdateOrCreate");
        W6RequestedProperties props = objFactory.createW6RequestedProperties();
        standardOperation.setRequestedProperties(props);

        CSAFieldRequest fldRqst = objFactory.createCSAFieldRequest();

        //set Field Request Fields
        fldRqst.setSerialNo(rs.getString(SER_NUM));
        fldRqst.setNotes(rs.getString(SVC_CMNT_TXT));
        fldRqst.setCustomerName(rs.getString(LOC_NM));
        fldRqst.setCSAStreet1(cratAddressTxt(rs.getString(FIRST_LINE_ADDR), rs.getString(SCD_LINE_ADDR), rs.getString(THIRD_LINE_ADDR), rs.getString(FRTH_LINE_ADDR)));
        fldRqst.setCity(rs.getString(CTY_ADDR));
        fldRqst.setState(rs.getString(ST_CD));
        fldRqst.setPostalCode(rs.getString(POST_CD));

        CountryReference cntyRef = new CountryReference();
        cntyRef.getContent().add(objFactory.createCountryReferenceName(rs.getString(CTRY_CD)));
        fldRqst.setCountryID(cntyRef);

        fldRqst.setTaskNumber(rs.getString(SVC_TASK_NUM));
        
        String clickKeyTxt = rs.getString(CLICK_KEY_TXT);
        if (isNumber(clickKeyTxt)) {
            fldRqst.setKey(Integer.valueOf(clickKeyTxt));
        }

        CSAFieldRequestStatusReference fldRqstStsRef = new CSAFieldRequestStatusReference();
        fldRqstStsRef.getContent().add(rs.getString(CLICK_FLD_RQST_STS_NM));

        fldRqst.setStatus(fldRqstStsRef);

        // START 2016/11/17 K.Ochiai [QC#16024, ADD]
        fldRqst.setEngineerID(rs.getString(TECH_CD));
        // END 2016/11/17 K.Ochiai [QC#16024, ADD]

        BaseObjectWrapper bow = objFactory.createBaseObjectWrapper();
        bow.setObject(fldRqst);
        standardOperation.setObject(bow);

        //call WMB API
        boolean errFlg = callWmbApi(standardOperation);

        //update Click Field Request
        CLICK_FLD_RQSTTMsg prmClickFldRqstTMsg = new CLICK_FLD_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(prmClickFldRqstTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmClickFldRqstTMsg.clickFldRqstPk, rs.getBigDecimal(CLICK_FLD_RQST_PK));
        CLICK_FLD_RQSTTMsg clickFldRqstTMsg = (CLICK_FLD_RQSTTMsg) EZDTBLAccessor.findByKeyForUpdateWait(prmClickFldRqstTMsg);
        if (clickFldRqstTMsg == null) {
            addMessage(clickFldRqstPk, NSBM0120E, new String[] {CLICK_FLD_RQST, cratFailedMsg(clickFldRqstPk)});
            return false;
        }
        if (errFlg) {
            ZYPEZDItemValueSetter.setValue(clickFldRqstTMsg.procStsCd, PROC_STS.COMPLEATED);
            ZYPEZDItemValueSetter.setValue(clickFldRqstTMsg.fldRqstSendTs, currentSystemTs);
        } else {
            ZYPEZDItemValueSetter.setValue(clickFldRqstTMsg.procStsCd, PROC_STS.ERROR);
        }
        S21FastTBLAccessor.update(clickFldRqstTMsg);
        String returnCode = clickFldRqstTMsg.getReturnCode();

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(returnCode)) {
            addMessage(clickFldRqstPk, NSBM0120E, new String[] {CLICK_FLD_RQST, cratFailedMsg(clickFldRqstPk)});
            return false;
        }

        // START 2016/10/25 K.Kojima [QC#15491,ADD]
        if (errFlg) {
            this.totalNmlCount++;
        } else {
            this.totalErrCount++;
        }
        // END 2016/10/25 K.Kojima [QC#15491,ADD]

        return true;
    }
    /**
     * Add Error Message.
     * @param svcTaskNum svcTaskNum
     * @param fsrNum fsrNum
     * @param code Message Code
     * @param param Message Parameter
     */
    private void addMessage(BigDecimal clickFlgRqstPk, String code, String... param) {
        termCd = TERM_CD.WARNING_END;
        errorMessages.put(clickFlgRqstPk, new EZDMessageInfo(code, param));
        setErrKeyMap(clickFlgRqstPk);

        S21InfoLogOutput.println(code, param);
    }
    /**
     * Send Error Mail.
     */
    private void sendErrorMail() {
        String errDate = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);

        // Get From Mail Address.
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_GROUP_KEY_FROM);
        S21MailAddress fromAddress;
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(NSBM0135E, new String[] {"FROM mail-address.", (MAIL_GROUP_ID_FROM + "/" + MAIL_GROUP_KEY_FROM) });
        }

        // Get To Mail Address.
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NSBM0135E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get Mail Template.
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_01);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NSBM0135E, new String[] {"Mailtemplate", MAIL_TEMPLATE_ID_01 });
        }

        fromAddress = addrFromList.get(0);

        S21Mail mail = new S21Mail(glblCmpyCd);

        // Set From Mail Address.
        mail.setFromAddress(fromAddress);
        // Set To Mail Address.
        mail.setToAddressList(addrToList);

        //create message
        StringBuffer msg = new StringBuffer();
        String newLine = System.getProperty("line.separator");
        for (Map<String, Object> entry : errKeyMap) {
            msg.append(entry.get(ERROR_MESSAGE).toString());
            msg.append(newLine);
        }

        // Set Parameter
        template.setTemplateParameter(MAIL_ITEM_BATCH_ID, BATCH_PROGRAM_ID);
        template.setTemplateParameter(MAIL_ITEM_ERR_DATE, errDate);
        template.setTemplateParameter(MAIL_ITEM_ERR_MSG, msg.toString());
        
        mail.setMailTemplate(template);
        mail.setSubject(template.getSubject());
        mail.postMail();
    }
    private void setErrKeyMap(BigDecimal clickFlgRqstPk) {

        Map<String, Object> errKey = new HashMap<String, Object>();
        errKey.put(CLICK_FLD_RQST_PK, clickFlgRqstPk);
        errKey.put(ERROR_MESSAGE, errorMessages.get(clickFlgRqstPk).getMessage());
        this.errKeyMap.add(errKey);
    }
    private void insertErrorData() {
        List<SVC_BAT_ERR_LOGTMsg> inTMsgList = new ArrayList<SVC_BAT_ERR_LOGTMsg>();

        for (Map<String, Object> entry : errKeyMap) {
            inTMsgList.add(setCreateErrorValue(entry));
        }

        SVC_BAT_ERR_LOGTMsg[] inMsgArray;
        inMsgArray = new SVC_BAT_ERR_LOGTMsg[inTMsgList.size()];
        S21FastTBLAccessor.insert(inTMsgList.toArray(inMsgArray));
    }
    private SVC_BAT_ERR_LOGTMsg setCreateErrorValue(Map<String, Object> entry) {
        SVC_BAT_ERR_LOGTMsg inParam = new SVC_BAT_ERR_LOGTMsg();

        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrLogPk, getSvcBatErrLogPk());
        ZYPEZDItemValueSetter.setValue(inParam.bizAppId, BATCH_PROGRAM_ID);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrLogTs, this.currentSystemTs);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNum_01, entry.get(CLICK_FLD_RQST_PK).toString());
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNm_01, CLICK_FLD_RQST_PK);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrMsgTxt, entry.get(ERROR_MESSAGE).toString());

        return inParam;
    }
    /**
     * This method will return SVC_BAT_ERR_LOG_SQ for
     * SVC_BAT_ERR_LOG_PK.
     * @return BigDecimal
     */
    private BigDecimal getSvcBatErrLogPk() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_BAT_ERR_LOG_SQ");
    }
    /**
     * Get Parameter to Query Unsent Document.
     * @return Query Parameter
     */
    private Map<String, Object> getFldRqstParam() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("inCompleted", PROC_STS.IN_COMPLETED);
        params.put("error", PROC_STS.ERROR);

        return params;
    }
    /**
     * Call WMB API.
     * @return Query Parameter
     */
    private boolean callWmbApi(StandardOperation standardOperation) throws SQLException {

        // START 2016/10/25 K.Kojima [QC#15491,MOD]
        // ExecuteOperation eo = objFactory.createExecuteOperation();
        // eo.setOperation(standardOperation);
        StandardOperations standardOperations = objFactory.createStandardOperations();
        standardOperations.getOperation().add(standardOperation);

        ExecuteMultipleOperations emo = objFactory.createExecuteMultipleOperations();
        emo.setOneTransaction(true);
        emo.setContinueOnError(true);
        emo.setOperations(standardOperations);
        // END 2016/10/25 K.Kojima [QC#15491,MOD]

        try {
            // START 2016/10/25 K.Kojima [QC#15491,MOD]
            // ExecuteOperationResponse response = proxy.executeOperation(eo);
// START 2016/12/12 [QC#16316, MOD]
//            ExecuteMultipleOperationsResponse response = proxy.executeMultipleOperations(emo);
            ExecuteMultipleOperationsResponse response = proxy.executeMultipleOperations(INTERFACE_ID, emo);
// END   2016/12/12 [QC#16316, MOD]
            // END 2016/10/25 K.Kojima [QC#15491,MOD]
        } catch (Exception e) {
            this.termCd = TERM_CD.WARNING_END;
            e.printStackTrace();
            // START 2016/10/25 K.Kojima [QC#15491,DEL]
            // this.totalErrCount++;
            // END 2016/10/25 K.Kojima [QC#15491,DEL]
            return false;
        } catch (Throwable t) {
            this.termCd = TERM_CD.WARNING_END;
            t.printStackTrace();
            // START 2016/10/25 K.Kojima [QC#15491,DEL]
            // this.totalErrCount++;
            // END 2016/10/25 K.Kojima [QC#15491,DEL]
            return false;
        }
        return true;
    }
    /**
     * Create Failed Message
     * @return Message
     */
    private String cratFailedMsg(BigDecimal clickFldRqstPk) {

        StringBuffer sb = new StringBuffer(CLICK_FLD_RQST_PK);
        sb.append(COLON);
        sb.append(clickFldRqstPk.toString());

        return sb.toString();
    }
    
    /**
     * Create Address Text
     * @return Address
     */
    private String cratAddressTxt(String addr1, String addr2, String addr3, String addr4) {

        StringBuffer sb = new StringBuffer("");
        if (ZYPCommonFunc.hasValue(addr1)) {
            sb.append(addr1);
        }
        if (ZYPCommonFunc.hasValue(addr2)) {
            if (ZYPCommonFunc.hasValue(sb.toString())) {
                sb.append(COMMA);
                sb.append(SPACE);
            }
            sb.append(addr2);
        }
        if (ZYPCommonFunc.hasValue(addr3)) {
            if (ZYPCommonFunc.hasValue(sb.toString())) {
                sb.append(COMMA);
                sb.append(SPACE);
            }
            sb.append(addr3);
        }
        if (ZYPCommonFunc.hasValue(addr4)) {
            if (ZYPCommonFunc.hasValue(sb.toString())) {
                sb.append(COMMA);
                sb.append(SPACE);
            }
            sb.append(addr4);
        }
        return sb.toString();
    }
    public boolean isNumber(String num) {
        try {
            Integer.parseInt(num);
            return true;
            } catch (NumberFormatException e) {
            return false;
        }
    }
}
