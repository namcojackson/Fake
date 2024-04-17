/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB169001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import parts.common.EZDMessageInfo;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CLICK_TECH_TRNSF_TOTMsg;

import com.canon.cusa.s21.batch.NPA.NPAB169001.constant.NPAB169001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeException;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeUtil;
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
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsTransferStatusReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsTransfer;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSARequestStatusReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperationsResponse;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ObjectFactory;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperation;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.W6RequestedProperties;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.wrapper.ClickSoftwareOptimizationService;


/**
 *<pre>
 *  Send Parts Transfer to Click Software
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/05/2015   Fujitsu         S.Nakai         Create          N/A
 * 01/13/2015   Fujitsu         S.Nakai         Update          QC#2862
 * 02/12/2016   Hitachi         Y.Takeno        Update          QC#3727
 * 02/23/2016   Hitachi         Y.Takeno        Update          QC#3727
 * 2016/10/25   Hitachi         K.Kojima        Update          QC#15491
 * 2016/11/10   Hitachi         T.Iwamoto       Update          QC#14444
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16316
 *</pre>
 */
public class NPAB169001 extends S21BatchMain implements NPAB169001Constant {

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

    /**
     * @param args parameter
     */
    public static void main(String[] args) {

        new NPAB169001().executeBatch(NPAB169001.class.getSimpleName());
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
    }

    @Override
    protected void mainRoutine() {

        this.currentSystemTs = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);

        // Field Request Information.
        sendPartTrnsf();

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
    private void sendPartTrnsf() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            // get Task Information.
            stmt = ssmLLClient.createPreparedStatement("getPrtTrnsfTo", getFldRqstParam());
            rs = stmt.executeQuery();

            while (rs.next()) {

                if (sendPartTrnsfApi(rs)) {
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
    private boolean sendPartTrnsfApi(ResultSet rs) throws SQLException {

        StandardOperation standardOperation = objFactory.createStandardOperation();
        standardOperation.setAction("UpdateOrCreate");
        W6RequestedProperties props = objFactory.createW6RequestedProperties();
        standardOperation.setRequestedProperties(props);

        CSAPartsTransfer prtTrnsf = objFactory.createCSAPartsTransfer();
// START 2016/11/10 T.Iwamoto [QC#14444,DEL]
/*
        prtTrnsf.setTransferTo(rs.getString(TO_INVTY_LOC_CD));

// START 02/12/2016 Y.Takeno [QC#3727, MOD]
        prtTrnsf.setRequestDate(changeDateFormat(convertRequestDate(rs.getString(TECH_TRNSF_RQST_TS), rs.getString(CTRY_CD), rs.getString(POST_CD)), rs.getString(CTRY_CD), rs.getString(POST_CD)));
//        prtTrnsf.setRequestDate(changeDateFormat(rs.getString(TECH_TRNSF_RQST_TS)));
// END   02/12/2016 Y.Takeno [QC#3727, MOD]

        prtTrnsf.setPartSerialNo(rs.getString(SER_NUM));
        prtTrnsf.setTransferNo(rs.getString(MDSE_CD));
        prtTrnsf.setQuantity(Integer.valueOf(rs.getString(SHIP_QTY)));
        prtTrnsf.setTransferFrom(rs.getString(FROM_INVTY_LOC_CD));
*/
// END 2016/11/10 T.Iwamoto [QC#14444,DEL]
        String clickKeyTxt = rs.getString(CLICK_KEY_TXT);
        if (isNumber(clickKeyTxt)) {
            prtTrnsf.setKey(Integer.valueOf(clickKeyTxt));
        }

        CSAPartsTransferStatusReference prtTrnsfStsRef = new CSAPartsTransferStatusReference();
        prtTrnsfStsRef.getContent().add(rs.getString(TECH_PROC_STS_DESC_TXT));
        prtTrnsf.setTransferStatus(prtTrnsfStsRef);

        CSARequestStatusReference rqstStsRef = new CSARequestStatusReference();
        rqstStsRef.getContent().add(rs.getString(TECH_TRNSF_RTRN_STS_TXT));
        prtTrnsf.setRequestStatus(rqstStsRef);

        BaseObjectWrapper bow = objFactory.createBaseObjectWrapper();
        bow.setObject(prtTrnsf);
        standardOperation.setObject(bow);

        //call WMB API
        boolean errFlg = callWmbApi(standardOperation);

        //update Click Parts Transfer
        CLICK_TECH_TRNSF_TOTMsg prmClickPrtTrnsfTMsg = new CLICK_TECH_TRNSF_TOTMsg();
        BigDecimal clickPrtTrnsfPk = rs.getBigDecimal(CLICK_TECH_TRNSF_TO_PK);

        ZYPEZDItemValueSetter.setValue(prmClickPrtTrnsfTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmClickPrtTrnsfTMsg.clickTechTrnsfToPk, clickPrtTrnsfPk);
        ZYPEZDItemValueSetter.setValue(prmClickPrtTrnsfTMsg.toInvtyLocCd, rs.getString(TO_INVTY_LOC_CD));
        CLICK_TECH_TRNSF_TOTMsg clickPrtTrnsfTMsg = (CLICK_TECH_TRNSF_TOTMsg) EZDTBLAccessor.findByKeyForUpdateWait(prmClickPrtTrnsfTMsg);
        if (clickPrtTrnsfTMsg == null) {
            addMessage(clickPrtTrnsfPk, NPAM1003E, new String[] {CLICK_TECH_TRNSF_TO, CLICK_TECH_TRNSF_TO_PK, clickPrtTrnsfPk.toString()});
            return false;
        }
        if (errFlg) {
            ZYPEZDItemValueSetter.setValue(clickPrtTrnsfTMsg.procStsCd, PROC_STS.COMPLEATED);
            ZYPEZDItemValueSetter.setValue(clickPrtTrnsfTMsg.techTrnsfToSendTs, currentSystemTs);
        } else {
            ZYPEZDItemValueSetter.setValue(clickPrtTrnsfTMsg.procStsCd, PROC_STS.ERROR);
        }
        S21FastTBLAccessor.update(clickPrtTrnsfTMsg);
        String returnCode = clickPrtTrnsfTMsg.getReturnCode();

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(returnCode)) {
            addMessage(clickPrtTrnsfPk, NPAM1003E, new String[] {CLICK_TECH_TRNSF_TO, CLICK_TECH_TRNSF_TO_PK, clickPrtTrnsfPk.toString()});
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
    private void addMessage(BigDecimal clickPrtsTrnsfSqOrPk, String code, String... param) {
        termCd = TERM_CD.WARNING_END;
        errorMessages.put(clickPrtsTrnsfSqOrPk, new EZDMessageInfo(code, param));
        setErrKeyMap(clickPrtsTrnsfSqOrPk);

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
    private void setErrKeyMap(BigDecimal clickPrtTrnsfPk) {

        Map<String, Object> errKey = new HashMap<String, Object>();
        errKey.put(CLICK_TECH_TRNSF_TO_PK, clickPrtTrnsfPk);
        errKey.put(ERROR_MESSAGE, errorMessages.get(clickPrtTrnsfPk).getMessage());
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
     * changeDateFormat
     * @param dateStr String
     * @param ctryCd String
     * @param postCd String
     * @return BigDecimal
     */
    private XMLGregorianCalendar changeDateFormat(String dateStr, String ctryCd, String postCd) {
        XMLGregorianCalendar xmlgc = null;
        try {

            if (!ZYPCommonFunc.hasValue(dateStr)) {
                return xmlgc;
            }
            // Date --> GregorianCalendar
            SimpleDateFormat format = new SimpleDateFormat(varConstMap.get(NPAB1690_TIME_FORMAT));

            ParsePosition pos = new ParsePosition(0);
            Date formatDate = format.parse(dateStr, pos);
            if (formatDate == null) {

                return xmlgc;
            } else {

                GregorianCalendar calendar = new GregorianCalendar();
// START 2016/02/23 Y.Takeno [QC#3727,ADD]
                if (ctryCd != null && postCd != null) {
                    try {
                        String tzId = ZYPLocalTimeUtil.getTZId(ctryCd, postCd);
                        if (tzId != null) {
                            calendar.setTimeZone(TimeZone.getTimeZone(tzId));
                        }

                    } catch (ZYPLocalTimeException e) {
                        return xmlgc;
                    }
                }
// END   2016/02/23 Y.Takeno [QC#3727,ADD]
                DatatypeFactory factory = DatatypeFactory.newInstance();

                Calendar parsedCalendar = Calendar.getInstance();
                parsedCalendar.setTime(formatDate);

                // set date and time from time parameter and
                calendar.set(parsedCalendar.get(Calendar.YEAR),
                                parsedCalendar.get(Calendar.MONTH),
                                parsedCalendar.get(Calendar.DATE),
                                parsedCalendar.get(Calendar.HOUR_OF_DAY),
                                parsedCalendar.get(Calendar.MINUTE),
                                parsedCalendar.get(Calendar.SECOND));
// START 2016/02/23 Y.Takeno [QC#3727,ADD]
                calendar.set(Calendar.MILLISECOND, parsedCalendar.get(Calendar.MILLISECOND));
// END   2016/02/23 Y.Takeno [QC#3727,ADD]

                xmlgc = factory.newXMLGregorianCalendar(calendar);
            }
            return xmlgc;
        } catch (DatatypeConfigurationException re) {

            return xmlgc;
        }
    }
    private Map<String, String> getVarConstMap() {

        varConstMap = new HashMap<String, String>();
        String timeFormat = ZYPCodeDataUtil.getVarCharConstValue(NPAB1690_TIME_FORMAT, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(timeFormat)) {
            throw new S21AbendException(NPAM1010E, new String[] {NPAB1690_TIME_FORMAT });
        }
        varConstMap.put(NPAB1690_TIME_FORMAT, timeFormat);

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
 // START 02/12/2016 Y.Takeno [QC#3727, MOD]
    private String convertRequestDate(String updatedDate, String ctryCd, String postCd) {
//        String tsUpdatedDate = NSXC001001SvcTimeZone.convertTime(
//                NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, updatedDate, ctryCd, postCd).getDateTime();
        SvcTimeZoneInfo tzUpdatedDate = NSXC001001SvcTimeZone.convertTime(
                NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, updatedDate, ctryCd, postCd);

        if (tzUpdatedDate != null) {
            String tsUpdatedDate = tzUpdatedDate.getDateTime();
            if (tsUpdatedDate != null) {
                SimpleDateFormat inFormat = new SimpleDateFormat(DT_FORMAT_TS);
                Date dtUpdatedDate = inFormat.parse(tsUpdatedDate, new ParsePosition(0));
                SimpleDateFormat outFormat = new SimpleDateFormat(varConstMap.get(NPAB1690_TIME_FORMAT));
                return outFormat.format(dtUpdatedDate);
            }
        }
        return null;
    }
// END   02/12/2016 Y.Takeno [QC#3727, MOD]

}
