/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLA.NLAB317001;

import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.BATCH_ID;
import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.DATE_TIME_PATTERN_FOR_MAIL;
import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.DB_PARAM_CARR_CD;
import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.DB_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.DB_PARAM_ORD_BOOK_REQ_TS;
import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.DB_PARAM_ORD_HDR_STS_CD;
import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.DB_PARAM_ORD_LINE_STS_CD;
import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.DB_PARAM_RPT_ID;
import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.DB_PARAM_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.ERROR;
import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.MSG_ERROR;
import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.MSG_WARNING;
import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.NLAF0020_DIRECT_PRINT;
import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.REPORT_ID;
import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.RPT_PRINT_CTRL;
import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.RWS_NUM;
import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.RWS_REF_NUM;
import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.SUCCESS;
import static com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant.WARNING;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDPMsg;
import business.parts.NLZC408001PMsg;
import business.parts.NLZC409001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC408001.NLZC408001;
import com.canon.cusa.s21.api.NLZ.NLZC409001.NLZC409001;
import com.canon.cusa.s21.batch.NLA.NLAB317001.constant.NLAB317001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRSMT_METH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NLAB317001:Standalone RMA Printing Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/08/2016   CITS            M.Naito         Create          N/A
 * 10/20/2017   CITS            T.Kikuhara      Update          QC#21195
 *</pre>
 */
public class NLAB317001 extends S21BatchMain {
    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Term code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** total count */
    private int totalCount = 0;

    /** Error count */
    private int errorCount = 0;

    /** Process CD */
    private String procCd = null;

    /** fetchSize */
    private static final int FETCH_SIZE = 1000;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** message Set for rptBrNum */
    private Set<String> setRptBrNum = new HashSet<String>();

    /** message list */
    private List<String> msgList = new ArrayList<String>();

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameter.
     */
    public static void main(String[] args) {
        new NLAB317001().executeBatch(NLAB317001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        profileService = S21UserProfileServiceFactory.getInstance().getService();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        glblCmpyCd = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NLAB317001Constant.NLZM2259E);
        }
        salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            throw new S21AbendException(NLAB317001Constant.NLZM2079E);
        }
    }

    @Override
    protected void mainRoutine() {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            //Get RMA(RWS_REF_NUM)
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            paramMap.put(DB_PARAM_ORD_HDR_STS_CD, ORD_HDR_STS.CLOSED);
            paramMap.put(DB_PARAM_ORD_LINE_STS_CD, ORD_LINE_STS.CANCELLED);
            paramMap.put(DB_PARAM_ORD_BOOK_REQ_TS, salesDate);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("getRwsRefNum", paramMap, execParam);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                totalCount++;
                procCd = null;

                mainProcess(rs);

                if (ERROR.equals(procCd)) {
                    errorCount++;
                    rollback();
                } else if (WARNING.equals(procCd)) {
                    rollback();
                } else if (SUCCESS.equals(procCd)) {
                    commit();
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, rs);
        }
    }

    @Override
    protected void termRoutine() {
        // Set EndCode and CommitCount
        if (0 < errorCount) {
            termCd = TERM_CD.WARNING_END;
        }
        sendEmail();
        setTermState(termCd, totalCount - errorCount, errorCount, totalCount);
    }

    /**
     * Main Process PO Send Form
     * @param rs getPoList ResultSet
     */
    private void mainProcess(ResultSet rs) throws SQLException {

        String rwsRefNum = null;
        BigDecimal rmaRptSq = null;
        String carrCd = null;
        String carrEmlAddr = null;
        String rptBrNum = null;
        String trsmtMethTpCd = null;

        rwsRefNum = rs.getString(RWS_REF_NUM);
        // QC#21195 UPD START
        String rwsNum = rs.getString(RWS_NUM);
        // Call RMA Create Report Work API
        NLZC408001PMsg dLZC408001PMsg = callCreateRmaReportApi(rwsRefNum, rwsNum);
        checkApi(dLZC408001PMsg, rwsNum);
        // QC#21195 UPD END
        if (!SUCCESS.equals(procCd)) {
            return;
        }

        // Get CSA_RMA_RPT_PRINT_RQST_SQ
        rmaRptSq = dLZC408001PMsg.csaRmaRptPrintRqstSq.getValue();

        // Check Direct Print
        String prntTpFlg = ZYPCodeDataUtil.getVarCharConstValue(NLAF0020_DIRECT_PRINT, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(prntTpFlg)) {
            S21InfoLogOutput.println(NLAB317001Constant.NLZM2493E, new String[] {NLAF0020_DIRECT_PRINT});
            procCd = ERROR;
            return;
        }

        if (ZYPConstant.FLG_OFF_N.equals(prntTpFlg)) {
            // Case PDF
            trsmtMethTpCd = TRSMT_METH_TP.PDF_DOWNLOAD;
        } else {
            // Check Carrier Address from Report Carrier Information
            carrCd = dLZC408001PMsg.carrCd.getValue();
            if (ZYPCommonFunc.hasValue(carrCd)) {
                // Get Carrier Address
                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
                queryParam.put(DB_PARAM_RPT_ID, REPORT_ID);
                queryParam.put(DB_PARAM_CARR_CD, carrCd);
                carrEmlAddr = (String) ssmBatchClient.queryObject("getCarrEmlAddr", queryParam);
            }

            if (!ZYPCommonFunc.hasValue(carrEmlAddr)) {
                // Case Printer
                trsmtMethTpCd = TRSMT_METH_TP.PRINTER;
                //Get BR_NUM From RPT_PRINT_CTRL
                rptBrNum = getRptBrNum(rwsRefNum, REPORT_ID, dLZC408001PMsg.whCd.getValue());
                if (!ZYPCommonFunc.hasValue(rptBrNum)) {
                    procCd = ERROR;
                    return;
                }
            } else {
                // Case Email
                trsmtMethTpCd = TRSMT_METH_TP.EMAIL_PDF;
            }
        }
        rmaRptSq = dLZC408001PMsg.csaRmaRptPrintRqstSq.getValue();
        // Call RMA Report Send To EIP API
        NLZC409001PMsg dLZC409001PMsg = callReportSendToEipApi(rwsRefNum, rmaRptSq, trsmtMethTpCd, rptBrNum, carrEmlAddr);
        checkApi(dLZC409001PMsg, rwsRefNum);
    }

    // QC#21195 UPD START
    /**
     * Call Create PO Report API
     * @param rwsNum String
     * @return NLZC408001PMsg
     */
    private NLZC408001PMsg callCreateRmaReportApi(String rwsRefNum, String rwsNum) throws SQLException {

        NLZC408001PMsg dLZC408001PMsg = new NLZC408001PMsg();
        ZYPEZDItemValueSetter.setValue(dLZC408001PMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dLZC408001PMsg.rwsRefNum, rwsRefNum);
        ZYPEZDItemValueSetter.setValue(dLZC408001PMsg.rwsNum, rwsNum);
        ZYPEZDItemValueSetter.setValue(dLZC408001PMsg.procPgmId, BATCH_ID);

        // call API
        NLZC408001 createPoReportApi = new NLZC408001();
        createPoReportApi.execute(dLZC408001PMsg, ONBATCH_TYPE.BATCH);

        return dLZC408001PMsg;
    }
    // QC#21195 UPD END

    /**
     * RMA Report Send To EIP API
     * @param rmaRptSq BigDecimal
     * @param trsmtMethTpCd String
     * @param brId String
     * @param emlAddr String
     * @return NLZC409001PMsg
     */
    private NLZC409001PMsg callReportSendToEipApi(String rwsRefNum, BigDecimal rmaRptSq, String trsmtMethTpCd, String brId, String emlAddr) {

        // set param
        NLZC409001PMsg dLZC409001PMsg = new NLZC409001PMsg();
        ZYPEZDItemValueSetter.setValue(dLZC409001PMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dLZC409001PMsg.csaRmaRptPrintRqstSq, rmaRptSq);
        ZYPEZDItemValueSetter.setValue(dLZC409001PMsg.trsmtMethTpCd, trsmtMethTpCd);
        ZYPEZDItemValueSetter.setValue(dLZC409001PMsg.rwsRefNum, rwsRefNum);

        if (trsmtMethTpCd.equals(TRSMT_METH_TP.PRINTER)) {
            ZYPEZDItemValueSetter.setValue(dLZC409001PMsg.rptBrId, brId);
        } else if (trsmtMethTpCd.equals(TRSMT_METH_TP.EMAIL_PDF)) {
            ZYPEZDItemValueSetter.setValue(dLZC409001PMsg.carrCtacEmlAddr, emlAddr);
        }

        // call API
        NLZC409001 purchaseOrderApi = new NLZC409001();
        purchaseOrderApi.execute(dLZC409001PMsg, ONBATCH_TYPE.BATCH);

        return dLZC409001PMsg;
    }

    private String getRptBrNum(String rwsRefNum, String rptId, String rtlWhCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(DB_PARAM_RPT_ID, rptId);
        queryParam.put(DB_PARAM_RTL_WH_CD, rtlWhCd);
        String rptBrNum = (String) ssmBatchClient.queryObject("getRptBrNum", queryParam);
        if (!ZYPCommonFunc.hasValue(rptBrNum)) {
            //Set ErrorMail
            if (!setRptBrNum.contains(rwsRefNum + rtlWhCd)) {
                setRptBrNum.add(rwsRefNum + rtlWhCd);
                setErrorInfo(rwsRefNum, NLAB317001Constant.NLZM2492E, new String[] {rtlWhCd, REPORT_ID, RPT_PRINT_CTRL});
            }
        }
        return rptBrNum;
    }

    private void setErrorInfo(String rwsRefNum, String msgId, String[] params) {
        S21InfoLogOutput.println(msgId, params);
        String msg = String.format("%15s  %s",
                rwsRefNum,
                S21MessageFunc.clspGetMessage(msgId, params));
        this.msgList.add(msg);
    }

    private void sendEmail() {

        if (msgList.isEmpty()) {
            return;
        }

        S21MailGroup fromGrp = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> fromAddrList = fromGrp.getMailAddress();

        S21Mail mail = new S21Mail(this.glblCmpyCd);

        if (fromAddrList.size() > 0) {

            mail.setFromAddress(fromAddrList.get(0));

            S21MailGroup toGrp = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
            List<S21MailAddress> toAddrList = toGrp.getMailAddress();
            if (!toAddrList.isEmpty()) {

                mail.setToAddressList(toAddrList);

                S21MailTemplate tmpl = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID);

                if (ZYPCommonFunc.hasValue(tmpl.getSubject())) {

                    String newLine = System.getProperty("line.separator");

                    StringBuilder msgBuf = new StringBuilder();
                    for (String msg : msgList) {
                        msgBuf.append(msg);
                        msgBuf.append(newLine);
                    }

                    SimpleDateFormat errTmFmt = new SimpleDateFormat(DATE_TIME_PATTERN_FOR_MAIL);

                    tmpl.setTemplateParameter("batchId", BATCH_ID);
                    tmpl.setTemplateParameter("errDate", errTmFmt.format(new Date()));
                    tmpl.setTemplateParameter("message", msgBuf.toString());

                    mail.setMailTemplate(tmpl);
                    mail.postMail();

                    // Error Info Insert commit
                    commit();
                }
            }
        }
    }

    /**
     * Check API PROCCESS
     * @param pMsg EZDPMsg
     * @param rwsRefNum String
     * @return 
     * @return true:OK, false:NG
     */
    private void checkApi(EZDPMsg pMsg, String rwsRefNum) {

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            S21InfoLogOutput.println(NLAB317001Constant.NLEM0003E, new String[] {rwsRefNum});

            StringBuilder msg = new StringBuilder();
            List<String> msgLst = S21ApiUtil.getXxMsgIdList(pMsg);
            String msgSts = MSG_WARNING;
            for (String msgId : msgLst) {
                int len = msgId.length();
                if (MSG_ERROR.equals(msgId.substring(len - 1, len))) {
                    msgSts = MSG_ERROR;
                }
                msg.append(S21MessageFunc.clspGetMessage(msgId));
                S21InfoLogOutput.println(msg.toString());
            }
            if (MSG_ERROR.equals(msgSts)) {
                procCd = ERROR;
            } else {
                procCd = WARNING;
            }
        } else {
            procCd = SUCCESS;
        }
    }
}
