/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB109001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.INVTY_DISCR_RPT_SER_WRKTMsg;
import com.canon.cusa.s21.batch.NLC.NLCB109001.constant.NLCB109001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SUB_SYS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailAttachment;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.mail.S21MailAttachment.AttachmentType;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NLCB1090:Inventory Discrepancy Report Batch For Serial# / Config ID Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/30/2016   CITS            Y.Nomura         Create          N/A
 * 09/21/2017   CITS            Y.Fukumura       Update          QC#21298
 *</pre>
 */
public class NLCB109001 extends S21BatchMain {
    /** Global Company Code */
    private String globalCompanyCode = null;

    /** Create Report Date */
    private String createReportDate = null;

    /** Create Report Time */
    private String createReportTime = null;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Sales Date */
    private String salesDate = null;

    /** Report ID */
    private String reportId = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** total count */
    private int totalCount = 0;

    /** error count */
    private int errorCount = 0;

    /** fetchSize */
    private static final int FETCH_SIZE = 1000;

    /** VarCharConstValue NLCB1090_SVC_MACH_MSTR_STS_CD */
    private String varCharConstValMachMstrStsCd;

    /** VarCharConstValue NLCB1090_SVC_LOC_STS_CD */
    private String varCharConstValLocStsCd;

    /** NumConstValue NLCB1090_PROC_ADJ_DAYS */
    private BigDecimal numConstValProcAdjDays;

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameter.
     */
    public static void main(String[] args) {
        // Initialization S21BatchMain
        new NLCB109001().executeBatch(NLCB109001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        globalCompanyCode = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(globalCompanyCode)) {
            throw new S21AbendException(NLCB109001Constant.ZZM9000E, new String[] {"Global Company Code" });
        }

        //QC#21298 Start
//        salesDate = getUserVariable1();
//        if ((!ZYPCommonFunc.hasValue(salesDate)) || (salesDate.length() != ZYPDateUtil.TYPE_YYYYMMDD.length())) {
//            salesDate = ZYPDateUtil.getSalesDate(globalCompanyCode);
//            if (!ZYPCommonFunc.hasValue(salesDate)) {
//                throw new S21AbendException(NLCB109001Constant.NPAM1480E);
//            }
//        }
        if (!ZYPCommonFunc.hasValue(getUserVariable1())) {
            // When the Discrepancy Report Preservation is not
            // obtained,
            // processing is ended
            throw new S21AbendException(NLCB109001Constant.ZZM9000E, new String[] {NLCB109001Constant.MSG_STR_DISCR_RPT_PRESER_PERIOD });
        }

        if (!ZYPCommonFunc.isHankakuSuuji(getUserVariable1())) {
            // When the Discrepancy Report Preservation is not
            // numerical value,
            // processing is ended
            throw new S21AbendException(NLCB109001Constant.ZZM9004E, new String[] {NLCB109001Constant.MSG_STR_DISCR_RPT_PRESER_PERIOD });
        }
        salesDate = ZYPDateUtil.getSalesDate(globalCompanyCode);
        salesDate = ZYPDateUtil.getSalesDate(globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            throw new S21AbendException(NLCB109001Constant.NPAM1480E);
        }
        //QC#21298 End

        reportId = getReportId();
        if (!ZYPCommonFunc.hasValue(reportId)) {
            throw new S21AbendException(NLCB109001Constant.ZZM9000E, new String[] {"Discrepancy Report ID" });
        }

        // get var char/num const
        this.varCharConstValMachMstrStsCd = ZYPCodeDataUtil.getVarCharConstValue(NLCB109001Constant.NLCB1090_SVC_MACH_MSTR_STS_CD, globalCompanyCode);
        this.varCharConstValLocStsCd = ZYPCodeDataUtil.getVarCharConstValue(NLCB109001Constant.NLCB1090_SVC_LOC_STS_CD, globalCompanyCode);
        this.numConstValProcAdjDays = repNullToZero(ZYPCodeDataUtil.getNumConstValue(NLCB109001Constant.NLCB1090_PROC_ADJ_DT, globalCompanyCode));
        // adj date
        salesDate = getAdjDate(salesDate, numConstValProcAdjDays);
    }

    @Override
    protected void mainRoutine() {
        
        //QC#21298 Start
        // Get operation date
        createReportDate = ZYPDateUtil.getBatProcDate(globalCompanyCode);
        // Get current system time
        createReportTime = ZYPDateUtil.getCurrentSystemTime(NLCB109001Constant.TM_PTRN_HHMMSS);
        //QC#21298 End

        // get WH
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            //QC#21298 Start
            purgeDiscrRptSerWrk(ssmLlcClient,execParam);
            //QC#21298 End
            
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NLCB109001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            
            preparedStatement = ssmLlcClient.createPreparedStatement("getWmsWarehouse", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                mainLoop(resultSet.getString(NLCB109001Constant.WMS_WH_CD));
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
    }

    //QC#21298 Start
    /**
     * INVTY_DISCR_RPT_SER_WORK table is deleted
     */
    private void purgeDiscrRptSerWrk(S21SsmLowLevelCodingClient ssmClient, S21SsmExecutionParameter ssmExecParam) {

        PreparedStatement stmtPurge = null;
        ResultSet rsPurge = null;

        try {
            // set a SQL parameter
            Map<String, String> queryParam = new HashMap<String, String>();
            // Global Company Code
            queryParam.put("glblCmpyCd", globalCompanyCode);
            // processDate - Discrepancy Report Preservation period
            queryParam.put("dateCondition", ZYPDateUtil.addDays(createReportDate, -Integer.parseInt(getUserVariable1())));

            stmtPurge = ssmClient.createPreparedStatement("getPurgeWorkDiscrepancySerReport", queryParam, ssmExecParam);

            rsPurge = stmtPurge.executeQuery();

            int commitCount = 0;

            while (rsPurge.next()) {

                final INVTY_DISCR_RPT_SER_WRKTMsg outRecord = new INVTY_DISCR_RPT_SER_WRKTMsg();

                outRecord.glblCmpyCd.setValue(rsPurge.getString("GLBL_CMPY_CD"));
                outRecord.invtyDiscrRptSerWrkPk.setValue(rsPurge.getBigDecimal("INVTY_DISCR_RPT_SER_WRK_PK"));

                // The physical deletion processing is done
                EZDTBLAccessor.remove(outRecord);
                commitCount++;

                if (commitCount >= getCommitCount()) {
                    commit();
                    commitCount = 0;
                }
            }
            commit();
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtPurge, rsPurge);
        }
    }
    /**
     * INVTY_DISCR_RPT_WRK_SQ getting
     * @return sequence number
     */
    private BigDecimal getSequenceNumber() {
        BigDecimal seqNo = ZYPOracleSeqAccessor.getNumberBigDecimal(NLCB109001Constant.SEQ_NM_INVTY_DISCR_RPT_SER_WRK_SQ);
        if (seqNo == null) {
            // When the sequence is not obtained,
            // processing is ended
            String[] tmp = {"INVTY_DISCR_RPT_SER_WRK_SQ", "ZYPOracleSeqAccessor.getNumberBigDecimal", "INVTY_DISCR_RPT_SER_WRK_SQ" };
            throw new S21AbendException(NLCB109001Constant.NLCM0050E, tmp);
        }
        return seqNo;
    }
    //QC#21298 End

    private void mainLoop(String whCd) {
        // target WH loop
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NLCB109001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NLCB109001Constant.DB_PARAM_SVC_MACH_MSTR_STS_CD, getSqlParam(this.varCharConstValMachMstrStsCd));
            paramMap.put(NLCB109001Constant.DB_PARAM_SVC_MACH_MSTR_LOC_STS_CD, getSqlParam(this.varCharConstValLocStsCd));
            paramMap.put(NLCB109001Constant.DB_PARAM_WMS_WH_CD, whCd);
            paramMap.put(NLCB109001Constant.DB_PARAM_WMS_INVTY_DT, salesDate);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("search", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();
            StringBuilder csvLine = new StringBuilder();
            StringBuilder mailLine = new StringBuilder();
            while (resultSet.next()) {
                // *****************************************
                // Mail Create
                // *****************************************
                csvLine.append(makeCsvFileText(resultSet));
                mailLine.append(makeMailFileText(resultSet) + NLCB109001Constant.LINE_FEED_CODE);

                //QC#21298 Start
                // *****************************************
                // Print Work Table Insert
                // *****************************************
                // INSERT INVTY_DISCR_RPT_SER_WRK
                BigDecimal seqNo = getSequenceNumber();
                
                // TMsg parameter setting for
                // INVTY_DISCR_RPT_SER_WRKTMsg
                INVTY_DISCR_RPT_SER_WRKTMsg discrTMsg = new INVTY_DISCR_RPT_SER_WRKTMsg();
                discrTMsg.glblCmpyCd.setValue(globalCompanyCode);
                discrTMsg.invtyDiscrRptSerWrkPk.setValue(seqNo);
                ZYPEZDItemValueSetter.setValue(discrTMsg.invtyDiscrRptSerCratDt, createReportDate);
                ZYPEZDItemValueSetter.setValue(discrTMsg.invtyDiscrRptSerCratTm, createReportTime);
                ZYPEZDItemValueSetter.setValue(discrTMsg.invtyLocCd, resultSet.getString("INVTY_LOC_CD"));
                ZYPEZDItemValueSetter.setValue(discrTMsg.firstProdCtrlCd, resultSet.getString("FIRST_PROD_CTRL_CD"));
                ZYPEZDItemValueSetter.setValue(discrTMsg.mdseCd, resultSet.getString("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(discrTMsg.mdseNm, resultSet.getString("MDSE_NM"));
                ZYPEZDItemValueSetter.setValue(discrTMsg.stkStsCd, resultSet.getString("STK_STS_CD"));
                ZYPEZDItemValueSetter.setValue(discrTMsg.svcConfigMstrPk, resultSet.getBigDecimal("SVC_CONFIG_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(discrTMsg.serNum, resultSet.getString("SER_NUM"));
                ZYPEZDItemValueSetter.setValue(discrTMsg.relnSysInvtyQty, resultSet.getBigDecimal("WMS_QTY"));
                ZYPEZDItemValueSetter.setValue(discrTMsg.s21InvtyQty, resultSet.getBigDecimal("S21_QTY"));
                ZYPEZDItemValueSetter.setValue(discrTMsg.diffInvtyQty, resultSet.getBigDecimal("DIFF_QTY"));
                ZYPEZDItemValueSetter.setValue(discrTMsg.invtyDiscrRptPrintFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(discrTMsg.rtlWhCd, resultSet.getString("RTL_WH_CD"));
                ZYPEZDItemValueSetter.setValue(discrTMsg.rtlWhNm, resultSet.getString("RTL_WH_NM"));
                ZYPEZDItemValueSetter.setValue(discrTMsg.rtlSwhCd, resultSet.getString("RTL_SWH_CD"));
                ZYPEZDItemValueSetter.setValue(discrTMsg.rtlSwhNm, resultSet.getString("RTL_SWH_NM"));
                ZYPEZDItemValueSetter.setValue(discrTMsg.zerothProdCtrlCd, resultSet.getString("ZEROTH_PROD_CTRL_CD"));
                ZYPEZDItemValueSetter.setValue(discrTMsg.zerothProdCtrlNm, resultSet.getString("ZEROTH_PROD_CTRL_NM"));
                ZYPEZDItemValueSetter.setValue(discrTMsg.firstProdCtrlNm, resultSet.getString("FIRST_PROD_CTRL_NM"));
                ZYPEZDItemValueSetter.setValue(discrTMsg.physWhCd, resultSet.getString("WMS_WH_CD"));
                ZYPEZDItemValueSetter.setValue(discrTMsg.physWhNm, resultSet.getString("WMS_DESC_NM"));
                ZYPEZDItemValueSetter.setValue(discrTMsg.stkStsNm, resultSet.getString("STK_STS_NM"));
                discrTMsg.locStsCd.clear();
                discrTMsg.locStsNm.clear();

                S21FastTBLAccessor.insert(discrTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(discrTMsg.getReturnCode())) {
                    errorCount++;
                    // When terminating abnormally,
                    // processing is ended
                    String[] tmp = {NLCB109001Constant.NLCM0034E, "INVTY_DISCR_RPT_SER_WRK", "GLBL_CMPY_CD = " + resultSet.getString("GLBL_CMPY_CD") + ", INVTY_DISCR_RPT_SEQ_WRK_SQ = " + seqNo };
                    throw new S21AbendException(NLCB109001Constant.NLCM0034E, tmp);
                }
                
                //QC#21298 End
                totalCount++;
            }
            if (0 < csvLine.length()) {
                StringBuilder mailMsg = new StringBuilder();
                mailMsg.append(NLCB109001Constant.ML_HEAD + NLCB109001Constant.LINE_FEED_CODE);
                mailMsg.append(mailLine);

                StringBuilder csvFile = new StringBuilder();
                csvFile.append(makeCsvFileHeader());
                csvFile.append(csvLine);
                sendNoticeMail(mailMsg, csvFile, whCd);
            } else {
                StringBuilder mailMsg = new StringBuilder();
                mailMsg.append(String.format(NLCB109001Constant.ML_NO_DATA, whCd));
                sendNoticeMail(mailMsg, null, whCd);
            }
            commit();
        } catch (SQLException e) {
            rollback();
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
    }

    @Override
    protected void termRoutine() {
        // Set EndCode and CommitCount
        if (0 < errorCount) {
            termCd = TERM_CD.WARNING_END;
        }
        setTermState(termCd, totalCount - errorCount, errorCount, totalCount);
    }

    private String[] getSqlParam(String val) {
        if (val == null) {
            return null;
        }
        return val.split(NLCB109001Constant.COMMA);
    }

    private String getAdjDate(String date, BigDecimal adjDays) {
        DateFormat df = new SimpleDateFormat(ZYPDateUtil.TYPE_YYYYMMDD);
        Date dt;
        try {
            dt = df.parse(date);
        } catch (ParseException e) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.DATE, adjDays.intValue());
        return df.format(cal.getTime());
    }

    /**
     * <pre>
     * Send Notice Mail
     * </pre>
     */
    private void sendNoticeMail(StringBuilder text, StringBuilder csv, String whCd) {

        // Get mail information : address
        S21Mail mail = new S21Mail(globalCompanyCode);
        S21MailGroup group = new S21MailGroup(globalCompanyCode, NLCB109001Constant.MAIL_TO_GROUP_ID);

        S21MailGroup groupFrom = new S21MailGroup(globalCompanyCode, NLCB109001Constant.MAIL_FROM_GROUP_ID);
        groupFrom.setMailKey1(SUB_SYS.NLC);

        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();

        if (!fromAddrList.isEmpty()) {
            mail.setFromAddress(fromAddrList.get(0));
        }

        group.setMailKey1(whCd);
        List<S21MailAddress> toAddrList = group.getMailAddress();

        if (toAddrList.isEmpty()) {
            throw new S21AbendException(NLCB109001Constant.NLBM1065E);
        }

        mail.setToAddressList(toAddrList);

        // Get mail template's parameter value
        // ********************************
        String currentTime = ZYPDateUtil.getCurrentSystemTime(NLCB109001Constant.TIME_FORMAT_MM_DD_YYYY_HH_MM);

        // Replace mail template's parameter
        // ********************************
        S21MailTemplate template = new S21MailTemplate(globalCompanyCode, NLCB109001Constant.MAIL_TEMPLATE_ID);

        template.setTemplateParameter(NLCB109001Constant.EMAIL_PARAM_WMS_WH_CD, whCd);
        template.setTemplateParameter(NLCB109001Constant.EMAIL_PARAM_REPORT_ID, reportId);
        template.setTemplateParameter(NLCB109001Constant.EMAIL_PARAM_DATE, currentTime);
        template.setTemplateParameter(NLCB109001Constant.EMAIL_PARAM_MSG, text.toString());
        if (csv != null) {
            // Attach CSV file ********************************
            S21MailAttachment attachment = new S21MailAttachment(globalCompanyCode);
            attachment.setAttachment(csv.toString());
            attachment.setFileName(String.format(NLCB109001Constant.CSV_FILE_NAME, whCd));
            attachment.setContentType(AttachmentType.DEFAULT_TEXT.getType());
            mail.setAttachment(attachment);
        }

        mail.setMailTemplate(template);
        // Send mail ********************************
        mail.postMail();
    }

    /**
     * <pre>
     * CSV File Header Make process
     * @return string
     * </pre>
     */
    private String makeCsvFileHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append(makeStr("W/H") + NLCB109001Constant.COMMA);
        sb.append(makeStr("PR") + NLCB109001Constant.COMMA);
        sb.append(makeStr("Item") + NLCB109001Constant.COMMA);
        sb.append(makeStr("Description") + NLCB109001Constant.COMMA);
        sb.append(makeStr("SS") + NLCB109001Constant.COMMA);
        sb.append(makeStr("Config ID") + NLCB109001Constant.COMMA);
        sb.append(makeStr("Serial#") + NLCB109001Constant.COMMA);
        sb.append(makeStr("S21 Qty") + NLCB109001Constant.COMMA);
        sb.append(makeStr("WMS Qty") + NLCB109001Constant.COMMA);
        sb.append(makeStr("Diff Qty") + NLCB109001Constant.LINE_FEED_CODE);
        return sb.toString();
    }

    /**
     * <pre>
     * Csv file text making process
     * </pre>
     * @param ResultSet
     * @return string
     * @throws SQLException
     */
    private String makeCsvFileText(ResultSet rs) throws SQLException {
        StringBuilder sb = new StringBuilder();

        String invtyLocCd = repNullToStr(rs.getString(NLCB109001Constant.INVTY_LOC_CD));
        String firstProdCtrlCd = repNullToStr(rs.getString(NLCB109001Constant.FIRST_PROD_CTRL_CD));
        String mdseCd = repNullToStr(rs.getString(NLCB109001Constant.MDSE_CD));
        String mdseNm = repNullToStr(rs.getString(NLCB109001Constant.MDSE_NM));
        String stkStsCd = repNullToStr(rs.getString(NLCB109001Constant.STK_STS_CD));
        String svcConfigMstrPk = repNullToStr(rs.getBigDecimal(NLCB109001Constant.SVC_CONFIG_MSTR_PK));
        String serNum = repNullToStr(rs.getString(NLCB109001Constant.SER_NUM));
        BigDecimal s21Qty = repNullToZero(rs.getBigDecimal(NLCB109001Constant.S21_QTY));
        BigDecimal wmsQty = repNullToZero(rs.getBigDecimal(NLCB109001Constant.WMS_QTY));
        BigDecimal diffQty = repNullToZero(rs.getBigDecimal(NLCB109001Constant.DIFF_QTY));

        sb.append(makeStr(invtyLocCd) + NLCB109001Constant.COMMA);
        sb.append(makeStr(firstProdCtrlCd) + NLCB109001Constant.COMMA);
        sb.append(makeStr(mdseCd) + NLCB109001Constant.COMMA);
        sb.append(makeStr(mdseNm) + NLCB109001Constant.COMMA);
        sb.append(makeStr(stkStsCd) + NLCB109001Constant.COMMA);
        sb.append(makeStr(svcConfigMstrPk) + NLCB109001Constant.COMMA);
        sb.append(makeStr(serNum) + NLCB109001Constant.COMMA);
        sb.append(makeStr(s21Qty.toString()) + NLCB109001Constant.COMMA);
        sb.append(makeStr(wmsQty.toString()) + NLCB109001Constant.COMMA);
        sb.append(makeStr(diffQty.toString()) + NLCB109001Constant.LINE_FEED_CODE);
        return sb.toString();
    }

    private String makeMailFileText(ResultSet rs) throws SQLException {
        String invtyLocCd = repNullToStr(rs.getString(NLCB109001Constant.INVTY_LOC_CD));
        String firstProdCtrlCd = repNullToStr(rs.getString(NLCB109001Constant.FIRST_PROD_CTRL_CD));
        String mdseCd = repNullToStr(rs.getString(NLCB109001Constant.MDSE_CD));
        String mdseNm = repNullToStr(rs.getString(NLCB109001Constant.MDSE_NM));
        String stkStsCd = repNullToStr(rs.getString(NLCB109001Constant.STK_STS_CD));
        String svcConfigMstrPk = repNullToStr(rs.getBigDecimal(NLCB109001Constant.SVC_CONFIG_MSTR_PK));
        String serNum = repNullToStr(rs.getString(NLCB109001Constant.SER_NUM));
        BigDecimal s21Qty = repNullToZero(rs.getBigDecimal(NLCB109001Constant.S21_QTY));
        BigDecimal wmsQty = repNullToZero(rs.getBigDecimal(NLCB109001Constant.WMS_QTY));
        BigDecimal diffQty = repNullToZero(rs.getBigDecimal(NLCB109001Constant.DIFF_QTY));

        return String.format(NLCB109001Constant.ML_FMT, invtyLocCd, firstProdCtrlCd, mdseCd, mdseNm, stkStsCd, svcConfigMstrPk, serNum, s21Qty.intValue(), wmsQty.intValue(), diffQty.intValue());
    }

    /**
     * <pre>
     * CSV File Data String Item generate process
     * </pre>
     * @param strItem
     * @param String
     */
    private String makeStr(String strItem) {

        return NLCB109001Constant.DOUBLE_QUOTES + strItem.replaceAll(NLCB109001Constant.DOUBLE_QUOTES, NLCB109001Constant.DOUBLE_QUOTES + NLCB109001Constant.DOUBLE_QUOTES) + NLCB109001Constant.DOUBLE_QUOTES;
    }

    /**
     * <pre>
     * Replace Null to "" on string item process
     * </pre>
     * @param chkStr
     * @param String
     */
    private String repNullToStr(String chkStr) {
        if (chkStr == null) {
            return "";
        } else {
            return chkStr;
        }
    }

    /**
     * <pre>
     * Replace Null to "" on string item process
     * </pre>
     * @param chkStr
     * @param String
     */
    private String repNullToStr(BigDecimal chkBd) {
        if (chkBd == null) {
            return "";
        } else {
            return chkBd.toString();
        }
    }

    /**
     * <pre>
     * Replace Null to Zero on BigDecimal item process
     * </pre>
     * @param chkBd
     * @param BigDecimal
     */
    private BigDecimal repNullToZero(BigDecimal chkBd) {
        if (chkBd == null) {
            return BigDecimal.ZERO;
        } else {
            return chkBd;
        }
    }
}
