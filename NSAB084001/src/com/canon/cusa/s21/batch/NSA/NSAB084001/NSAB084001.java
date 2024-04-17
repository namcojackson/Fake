/*
 * <pre>Copyright (c) 2021 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB084001;

import static com.canon.cusa.s21.batch.NSA.NSAB084001.constant.NSAB084001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsgArray;
import parts.dbcommon.EZDTBLAccessor;

import business.db.DS_CONTRTMsg;
import business.db.VAR_CHAR_CONSTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;
import com.sun.xml.internal.ws.util.StringUtils;

/**
 * <pre>
 * Contract Branch Rep Update Batch
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/04/21   Hitachi         T.Nagae         Create          CCI-QC#61248
 *</pre>
 */
public class NSAB084001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** System Time */
    private String sysTs = null;

    // -- Other Internal Variable ---------------
    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Execute Param */
    S21SsmExecutionParameter excParam = null;

    /** Term code */
    private TERM_CD termCd = null;

    /** total search count */
    private int searchCnt = 0;

    /** Task success count */
    private int infoSccessCnt = 0;

    /** Toc Code Map */
    private Map<String, String> tocCodeMap = null;

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB084001().executeBatch(NSAB084001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Initialization
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());
        ssmBatchClient = S21SsmBatchClient.getClient(getClass());
        termCd = TERM_CD.NORMAL_END;

        // Get the Global Company Code.
        // If an error occurs, throw Exception.
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Global Company Code" });
        }

        // Get the Sales Date.
        // If an error occurs, throw Exception.
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, BATCH_ID);
        if (!hasValue(slsDt)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Sales Date" });
        }

        this.sysTs = ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT);

    }

    @Override
    protected void mainRoutine() {
        doProcess();
    }

    @Override
    protected void termRoutine() {
        // Set term code and total count.
        setTermState(this.termCd, infoSccessCnt, searchCnt - infoSccessCnt, searchCnt);
    }

    /**
     * doProcess
     */
    private void doProcess() {

        PreparedStatement prcIncrStmt = null;
        ResultSet rsUpdInfo = null;

        List<String> errMsgList = new ArrayList<String>();

        try {

            this.tocCodeMap = getTocCodeMap();

            prcIncrStmt = ssmLLClient.createPreparedStatement("getEndSlsRepContrInfo", setParamForTargetList());
            rsUpdInfo = prcIncrStmt.executeQuery();

            int counter = 0;

            rsUpdInfo.last();
            this.searchCnt = rsUpdInfo.getRow();
            boolean nextFlg = rsUpdInfo.first();

            while (nextFlg) {
                counter++;
                String resultUpdSts = "";

                try {
                    resultUpdSts = updateDsContr(rsUpdInfo);
                } catch (Exception e) {
                    errMsgList.add(e.getMessage());
                    this.termCd = TERM_CD.ABNORMAL_END;
                    break;
                }
                if (resultUpdSts.equals(UPD_STS_COMPLETE)) {
                    this.infoSccessCnt++;
                } else if (resultUpdSts.equals(UPD_STS_WARNING)) {
                    this.termCd = TERM_CD.WARNING_END;
                } else if (resultUpdSts.equals(UPD_STS_ERROR_BOL_IS_NOT_REGISTED)) {
                    counter--;
                    this.searchCnt--;
                } else if (resultUpdSts.equals(UPD_STS_ERROR)) {
                    errMsgList.add(S21MessageFunc.clspGetMessage(NSAM0470E, new String[] {"DS_CONTR", "Contract=" + getStringFromRs(rsUpdInfo, "DS_CONTR_NUM") }));
                    this.termCd = TERM_CD.ABNORMAL_END;
                    break;
                }
                nextFlg = rsUpdInfo.next();
            }

            if (counter != 0) {
                if (!this.termCd.equals(TERM_CD.ABNORMAL_END)) {
                    commit();
                    if (TERM_CD.WARNING_END.equals(this.termCd)) {
                        sendMail(errMsgList);
                    }
                } else {
                    sendMail(errMsgList);
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prcIncrStmt, rsUpdInfo);
        }

    }

    private Map<String, Object> setParamForTargetList() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.slsDt);
        paramMap.put("ostBos", ORG_STRU_TP_CD_BOS);
        paramMap.put("dcsExpired", DS_CONTR_STS_CD_EXPIRED);
        paramMap.put("dcsTerminated", DS_CONTR_STS_CD_TERMINATED);
        return paramMap;
    }

    private String updateDsContr(ResultSet rsUpdInfo) throws SQLException {

        DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
        dsContrTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        dsContrTMsg.ezCancelFlag.setValue("0");
        dsContrTMsg.dsContrPk.setValue(getBigDecimalFromRs(rsUpdInfo, "DS_CONTR_PK"));

        dsContrTMsg = (DS_CONTRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(dsContrTMsg);

        if (dsContrTMsg == null) {
            return UPD_STS_WARNING;
        }

        String lob = rsUpdInfo.getString("SVC_LINE_BIZ_CD");
        String tocCd = rsUpdInfo.getString("TOC_CD");

        if (this.tocCodeMap.containsKey(lob)) {
            tocCd = this.tocCodeMap.get(lob);
        } else {
            return UPD_STS_ERROR_BOL_IS_NOT_REGISTED;
        }

        // data update
        dsContrTMsg.tocCd.setValue(tocCd);

        // db update
        S21FastTBLAccessor.update(dsContrTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrTMsg.getReturnCode())) {
            return UPD_STS_ERROR;
        }

        return UPD_STS_COMPLETE;
    }

    private BigDecimal getBigDecimalFromRs(ResultSet rs, String keyStr) throws SQLException {
        BigDecimal ret = null;
        ret = rs.getBigDecimal(keyStr);
        return ret;
    }

    private String getStringFromRs(ResultSet rs, String keyStr) throws SQLException {
        String ret = null;
        ret = rs.getString(keyStr);
        return ret;
    }

    private Map<String, String> getTocCodeMap() {

        Map<String, String> tocCodeMap = new HashMap<String, String>();

        String repDefaultPps = ZYPCodeDataUtil.getVarCharConstValue(CONTR_REP_KEY_PPS, this.glblCmpyCd);
        if (repDefaultPps != null && !repDefaultPps.isEmpty()) {
            tocCodeMap.put("PPS", repDefaultPps);
        }

        String repDefaultLfs = ZYPCodeDataUtil.getVarCharConstValue(CONTR_REP_KEY_LFS, this.glblCmpyCd);
        if (repDefaultLfs != null && !repDefaultLfs.isEmpty()) {
            tocCodeMap.put("LFS", repDefaultLfs);
        }

        String repDefaultEss = ZYPCodeDataUtil.getVarCharConstValue(CONTR_REP_KEY_ESS, this.glblCmpyCd);
        if (repDefaultEss != null && !repDefaultEss.isEmpty()) {
            tocCodeMap.put("ESS", repDefaultEss);
        }

        return tocCodeMap;
    }

    private void sendMail(List<String> messages) {
        // Get From Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();
        if (fromAddrList == null || fromAddrList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"From mail-address.", MAIL_GROUP_ID_FROM });
        }

        // Get To Address
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> toAddrList = groupTo.getMailAddress();
        if (toAddrList == null || toAddrList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get Error Mail Template
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!hasValue(template.getBody())) {
            throw new S21AbendException(NSAM0069E, new String[] {"Mail Template", MAIL_TEMPLATE_ID });
        }

        // Set Template Parameter
        template.setTemplateParameter(MAIL_TEMPLATE_SET_KEY_BATCH_ID, BATCH_ID);
        template.setTemplateParameter(MAIL_TEMPLATE_SET_KEY_ERROR_DATE, slsDt);

        StringBuilder msgBuilder = new StringBuilder();
        for (int i = 0; i < messages.size(); i++) {
            msgBuilder.append(messages.get(i));
            msgBuilder.append("\n");
            msgBuilder.length();
        }

        template.setTemplateParameter(MAIL_TEMPLATE_SET_KEY_MESSAGE, msgBuilder.toString());

        // Send Mail
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(fromAddrList.get(0));
        mail.setToAddressList(toAddrList);
        mail.setMailTemplate(template);
        mail.postMail();
    }

}
