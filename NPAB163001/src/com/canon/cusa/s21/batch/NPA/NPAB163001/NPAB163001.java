/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB163001;

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
import business.db.CLICK_TECH_ORD_PRTTMsg;

import com.canon.cusa.s21.batch.NPA.NPAB163001.constant.NPAB163001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCharacterConversionUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.BaseObjectWrapper;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartOrder;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsLookupReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsOrderStatusReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperationsResponse;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ObjectFactory;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperation;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.W6RequestedProperties;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartOrder.PartsLookup;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.wrapper.ClickSoftwareOptimizationService;


/**
 *<pre>
 *  Send Tech Order Parts to Click Software
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/05/2015   Fujitsu         S.Nakai         Create          N/A
 * 01/13/2015   Fujitsu         S.Nakai         Update          QC#2862
 * 10/05/2016   Hitachi         K.Yamada        Update          QC#14803
 * 10/19/2016   Hitachi         T.Iwamoto       Update          QC#14445
 * 2016/10/25   Hitachi         K.Kojima        Update          QC#15491
 * 2016/11/09   Hitachi         T.Iwamoto       Update          QC#15739
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16316
 * 2022/10/19   Hitachi         K.Kishimoto     Update          QC#60712
 *</pre>
 */
public class NPAB163001 extends S21BatchMain implements NPAB163001Constant {

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

    /** varConstmap */
    private Map<String, String> varConstMap = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmClient = S21SsmBatchClient.getClient(this.getClass());

    // START 2022/10/19 [QC#60712, ADD]
    /** OFS Multi Byte Convert Flag */
    private  boolean ofsMultiByteConvertFlg = false;
    // END   2022/10/19 [QC#60712, ADD]

    /**
     * @param args parameter
     */
    public static void main(String[] args) {

        new NPAB163001().executeBatch(NPAB163001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // blank check(Global Company Code)
        glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NPAM1001E, new String[] {"Global Company Code" });
        }
        this.varConstMap = getVarConstMap();
        // Initialize
        this.errKeyMap = new ArrayList<Map<String, Object>>();
        // START 2022/10/19 [QC#60712, ADD]
        String strOFSMultiByteConvertFlg = ZYPCodeDataUtil.getVarCharConstValue("OFS_MULTI_BYTE_CONVERT_FLG", this.glblCmpyCd);
        if (ZYPConstant.FLG_ON_Y.equals(strOFSMultiByteConvertFlg)) {
            ofsMultiByteConvertFlg = true;
        }
        // END   2022/10/19 [QC#60712, ADD]
    }

    @Override
    protected void mainRoutine() {

        this.currentSystemTs = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);

        // Field Request Information.
        sendTechOrdPrt();

        // Send Mail if error or warning occurred.
        if (!errorMessages.isEmpty()) {
            termCd = TERM_CD.WARNING_END;
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
    private void sendTechOrdPrt() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            // get Task Information.
            stmt = ssmLLClient.createPreparedStatement("getTechOrdPrtSq", getFldRqstParam());
            rs = stmt.executeQuery();

            while (rs.next()) {

                if (sendTechOrdPrtApi(rs)) {
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
    private boolean sendTechOrdPrtApi(ResultSet rs) throws SQLException {

        Map<String, Object> params = new HashMap<String, Object>();
        BigDecimal techOrdPrtSq = rs.getBigDecimal(TECH_ORD_PRT_SQ);
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("techOrdPrtSq", techOrdPrtSq);
        params.put("inCompleted", PROC_STS.IN_COMPLETED);
        params.put("error", PROC_STS.ERROR);

        List<Map<String, Object>> techOrdPrtList = ssmClient.queryObjectList("getTechOrdPrt", params);

        if (techOrdPrtList == null || techOrdPrtList.size() == 0) {
            addMessage(techOrdPrtSq, NPAM1166E, new String[] {CLICK_TECH_ORD_PRT, TECH_ORD_PRT_SQ, techOrdPrtSq.toString()});
            return false;
        }
        Map<String, Object> headerInfo = techOrdPrtList.get(0);

        StandardOperation standardOperation = objFactory.createStandardOperation();
        standardOperation.setAction("UpdateOrCreate");
        W6RequestedProperties props = objFactory.createW6RequestedProperties();
        standardOperation.setRequestedProperties(props);

        CSAPartOrder prtOrd = objFactory.createCSAPartOrder();

        //set Tech Order Parts Fields
        String clickKeyTxt = getString(headerInfo, CLICK_KEY_TXT);
        if (isNumber(clickKeyTxt)) {
            prtOrd.setKey(Integer.valueOf(clickKeyTxt));
        }
        prtOrd.setAttention(getString(headerInfo, FIRST_INVTY_ORD_CMNT_TXT));
        prtOrd.setTaskNumber(getString(headerInfo, SVC_TASK_NUM));
        prtOrd.setTechnicianID(getString(headerInfo, RQST_TECH_TOC_CD));
        prtOrd.setPartOrderNumber(getString(headerInfo, PRCH_REQ_NUM));

        CSAPartsOrderStatusReference ordStsRef = new CSAPartsOrderStatusReference();
        ordStsRef.getContent().add(getString(headerInfo, PRCH_REQ_STS_NM));
        prtOrd.setStatus(ordStsRef);

        boolean itemVld = true;
        if (ZYPConstant.CHKBOX_ON_Y.equals(getString(headerInfo, VLD_ACT_CD))) {
            itemVld = true;
        } else {
            itemVld = false;
        }
        prtOrd.setItemValidation(itemVld);
        prtOrd.setName(getString(headerInfo, DEST_RTL_WH_CD));
        prtOrd.setOrderType(getString(headerInfo, PRCH_REQ_TP_NM));
        prtOrd.setDeliveryOption(getString(headerInfo, LGSC_ORD_RMK_TXT));
        prtOrd.setDeliveryTo(getString(headerInfo, SHIP_TO_CUST_NM));

        if (itemVld) {
            PartsLookup prtLkup = new PartsLookup();
            for (Map<String, Object> techPrdPrtMap : techOrdPrtList) {
                CSAPartsLookupReference csaPrtLkupRef = new CSAPartsLookupReference();
                csaPrtLkupRef.getContent().add(getString(techPrdPrtMap, MDSE_CD));
                // START 2022/10/19 [QC#60712, MOD]
//                csaPrtLkupRef.getContent().add(getString(techPrdPrtMap, MDSE_DESC_SHORT_TXT));
                csaPrtLkupRef.getContent().add(ofsMultiByteConvert(getString(techPrdPrtMap, MDSE_DESC_SHORT_TXT)));
                // END   2022/10/19 [QC#60712, MOD]
                prtLkup.getCSAPartsLookup().add(csaPrtLkupRef);
            }
            prtOrd.setPartsLookup(prtLkup);
        }

        BaseObjectWrapper bow = objFactory.createBaseObjectWrapper();
        bow.setObject(prtOrd);
        standardOperation.setObject(bow);

        //call WMB API
        boolean errFlg = callWmbApi(standardOperation);

        //update Click Tech Parts Order Request
        for (Map<String, Object> techPrdPrtMap : techOrdPrtList) {
            CLICK_TECH_ORD_PRTTMsg prmClickTechOrdPrtTMsg = new CLICK_TECH_ORD_PRTTMsg();
            BigDecimal clickTechOrdPrtPk = (BigDecimal) techPrdPrtMap.get(CLICK_TECH_ORD_PRT_PK);

            ZYPEZDItemValueSetter.setValue(prmClickTechOrdPrtTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prmClickTechOrdPrtTMsg.clickTechOrdPrtPk, clickTechOrdPrtPk);
            CLICK_TECH_ORD_PRTTMsg clickOrdPrtTMsg = (CLICK_TECH_ORD_PRTTMsg) EZDTBLAccessor.findByKeyForUpdateWait(prmClickTechOrdPrtTMsg);
            if (clickOrdPrtTMsg == null) {
                addMessage(clickTechOrdPrtPk, NPAM1003E, new String[] {CLICK_TECH_ORD_PRT, CLICK_TECH_ORD_PRT_PK, clickTechOrdPrtPk.toString()});
                return false;
            }
            if (errFlg) {
                ZYPEZDItemValueSetter.setValue(clickOrdPrtTMsg.procStsCd, PROC_STS.COMPLEATED);
                ZYPEZDItemValueSetter.setValue(clickOrdPrtTMsg.techOrdPrtSendTs, currentSystemTs);
            } else {
                ZYPEZDItemValueSetter.setValue(clickOrdPrtTMsg.procStsCd, PROC_STS.ERROR);
            }
            S21FastTBLAccessor.update(clickOrdPrtTMsg);
            String returnCode = clickOrdPrtTMsg.getReturnCode();

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(returnCode)) {
                addMessage(clickTechOrdPrtPk, NPAM1003E, new String[] {CLICK_TECH_ORD_PRT, CLICK_TECH_ORD_PRT_PK, clickTechOrdPrtPk.toString()});
                return false;
            }
        }
        // START 2016/10/25 K.Kojima [QC#15491,ADD]
        if (errFlg) {
            totalNmlCount++;
        } else {
            totalErrCount++;
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
    private void addMessage(BigDecimal clickPrtsOrdSqOrPk, String code, String... param) {
        termCd = TERM_CD.WARNING_END;
        errorMessages.put(clickPrtsOrdSqOrPk, new EZDMessageInfo(code, param));
        setErrKeyMap(clickPrtsOrdSqOrPk);

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
            throw new S21AbendException(NPAM1478E, new String[] {"FROM mail-address.", (MAIL_GROUP_ID_FROM + "/" + MAIL_GROUP_KEY_FROM) });
        }

        // Get To Mail Address.
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NPAM1478E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get Mail Template.
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_01);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NPAM1331E, new String[] {MAIL_TEMPLATE_ID_01 });
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
    private void setErrKeyMap(BigDecimal clickOrdPrtsPk) {

        Map<String, Object> errKey = new HashMap<String, Object>();
        errKey.put(CLICK_TECH_ORD_PRT_PK, clickOrdPrtsPk);
        errKey.put(ERROR_MESSAGE, errorMessages.get(clickOrdPrtsPk).getMessage());
        this.errKeyMap.add(errKey);
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
            // START 2017/01/04 K.Kitachi [QC#16316, MOD]
//            ExecuteMultipleOperationsResponse response = proxy.executeMultipleOperations(emo);
            ExecuteMultipleOperationsResponse response = proxy.executeMultipleOperations(INTERFACE_ID, emo);
            // END 2017/01/04 K.Kitachi [QC#16316, MOD]
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
     * Get String Value from Map. (With Conversion from Null to "")
     * @param map Map&lt;Object, Object&gt;
     * @param key String
     * @return String
     */
    private String getString(Map<String, Object> map, String key) {
        String ret = (String) map.get(key);
        if (ZYPCommonFunc.hasValue(ret)) {
            return ret;
        }
        return "";
    }
    private Map<String, String> getVarConstMap() {

        varConstMap = new HashMap<String, String>();
        String timeFormat = ZYPCodeDataUtil.getVarCharConstValue(NPAB1630_TIME_FORMAT, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(timeFormat)) {
            throw new S21AbendException(NPAM1010E, new String[] {NPAB1630_TIME_FORMAT });
        }
        varConstMap.put(NPAB1630_TIME_FORMAT, timeFormat);

        return varConstMap;
    }
    public boolean isNumber(String num) {
        try {
            Integer.parseInt(num);
            return true;
            } catch (NumberFormatException e) {
            return false;
        }
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
