/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB562001;

import static com.canon.cusa.s21.batch.NMA.NMAB562001.constant.NMAB562001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.GLBL_CMPYTMsg;
import business.parts.NMZC001001PMsg;
import business.parts.NMZC001002PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC001001.NMZC001001;
import com.canon.cusa.s21.api.NMZ.NMZC001001.constant.NMZC001001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_XREF_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;

/**
 * <pre>
 * CUSA Retail Location Cross Reference Sync Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 06/27/2016   Hitachi         Y.Osawa          CREATE          NEW
 *</pre>
 */
public class NMAB562001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** process date time */
    private String procDt = null;

    /** System TimeStamp */
    private String sysTime = null;

    /** success count */
    private int successCount = 0;

    /** error count */
    private int errorCount = 0;

    /** total count */
    private int totalCount = 0;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Error Massage */
    private String errMsg = null;

    /** Error Message list */
    private List<String> errMsgList = new ArrayList<String>();

    /** Mail Template ID */
    private String mailTemplateId = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NMAB562001().executeBatch(NMAB562001.class.getSimpleName());

    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
            // Send error mail
            postErrorMail();
        }
        setTermState(this.termCd, this.successCount, this.errorCount, this.totalCount);

    }

    @Override
    protected void initRoutine() {
        // blank check(Global Company Code)
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {GLBL_CMPY_CD });
        }

        // value check(Global Company Code)
        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);
        if (glblCmpyTMsg == null) {
            throw new S21AbendException(ZZZM9026E, new String[] {GLBL_CMPY_CD });
        }

        this.procDt = ZYPDateUtil.getBatProcDate();
        this.salesDate = ZYPDateUtil.getSalesDate();

        // Mail Template
        this.mailTemplateId = SET_MAIL_TEMPLATE_ID;

        // initialize
        this.sysTime = ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT);

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    }

    @Override
    protected void mainRoutine() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        try {
            execParam.setFetchSize(DEFAULT_FETCH_SIZE);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            // Search Target Data
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("ordHdrStsCanc", ORD_HDR_STS.CANCELLED);
            ssmParam.put("procDt", procDt);
            ssmParam.put("befDay", BEF_DAY);

            stmt = this.ssmLLClient.createPreparedStatement("getTergetCustomerInfo", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            while (rsSet.next()) {

                String aquNum = rsSet.getString("AQU_NUM");
                String[] itemArray = aquNum.split(HYPHEN);
                String rtlInstlCd = null;
                if (itemArray.length == CHK_LIST_LENGTH) {
                    if (itemArray[1].length() > CHK_LENGTH || itemArray[2].length() > CHK_LENGTH) {
                        // error skip
                        addErrMsg(NMAM8612E, new String[] {AQU_NUM, aquNum });
                        this.errorCount++;
                        continue;
                    }
                    rtlInstlCd = S21StringUtil.concatStrings(itemArray[1], itemArray[2]);
                } else {
                    // error skip
                    addErrMsg(NMAM8612E, new String[] {AQU_NUM, aquNum });
                    this.errorCount++;
                    continue;
                }

                // x-reference exist check
                List<Map<String, Object>> xrefAcctCdList = getXrefAcctCdList(rtlInstlCd);
                if (!xrefAcctCdList.isEmpty()) {
                    // skip
                    continue;
                }

                String shipToCustCd = rsSet.getString("ADD_SHIP_TO_CUST_CD");
                // get Customer Update API Parameter
                List<Map<String, Object>> customerUpdateInfoList = getCustomerUpdateInfoList(shipToCustCd);
                if (customerUpdateInfoList.size() != 1) {
                    // error skip
                    addErrMsg(NMAM8613E, new String[] {shipToCustCd });
                    this.errorCount++;
                    continue;
                }

                // Call Customer Update API
                if (!callCustomerUpdateApi(rtlInstlCd, customerUpdateInfoList.get(0))) {
                    this.errorCount++;
                    rollback();
                } else {
                    this.successCount++;
                    commit();
                }
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
            this.totalCount = this.successCount + this.errorCount;
        }

    }

    private List<Map<String, Object>> getXrefAcctCdList(String rtlInstlCd) {

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsXrefAcctTpCusa", DS_XREF_ACCT_TP.CUSA);
        param.put("rtlInstlCd", rtlInstlCd);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getXrefAcctCd", param);
    }

    private List<Map<String, Object>> getCustomerUpdateInfoList(String shipToCustCd) {

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("procDt", this.procDt);
        param.put("rgtnStsP20", RGTN_STS.READY_FOR_ORDER_TAKING);
        param.put("ctryCdUS", CTRY.UNITED_STATES_OF_AMERICA);
        param.put("shipToCustCd", shipToCustCd);
        List<Map<String, Object>> customerUpdateInfoList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCustomerUpdateInfo", param);

        return customerUpdateInfoList;
    }

    private boolean callCustomerUpdateApi(String rtlInstlCd, Map<String, Object> customerUpdateInfoList) {

        NMZC001001PMsg nmzc001001Pmsg = setupCustUpdateApiMsg(rtlInstlCd, customerUpdateInfoList);

        if (!callApiNMZC001001(nmzc001001Pmsg, rtlInstlCd)) {
            return false;
        }

        return true;
    }

    private NMZC001001PMsg setupCustUpdateApiMsg(String rtlInstlCd, Map<String, Object> dataMap) {

        NMZC001001PMsg nmzc001001Pmsg = new NMZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.xxModeCd, NMZC001001Constant.PROCESS_MODE_CUST_UPD);
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.slsDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.dsAcctNum, (String) dataMap.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.dsAcctNm, (String) dataMap.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.dsAcctItrlFlg, (String) dataMap.get("DS_ACCT_ITRL_FLG"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.dsAcctClsCd, (String) dataMap.get("DS_ACCT_CLS_CD"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.coaChCd, (String) dataMap.get("COA_CH_CD"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.coaAfflCd, (String) dataMap.get("COA_AFFL_CD"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.dsAcctDlrCd, (String) dataMap.get("DS_ACCT_DLR_CD"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.dsAcctLegalNm, (String) dataMap.get("DS_ACCT_LEGAL_NM"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.dbaNm, (String) dataMap.get("DBA_NM"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.firstRefCmntTxt, (String) dataMap.get("FIRST_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.scdRefCmntTxt, (String) dataMap.get("SCD_REF_CMNT_TXT"));
        if (DS_ACCT_TP.CUSTOMER.equals((String) dataMap.get("DS_ACCT_TP_CD"))) {
            ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.rgtnStsCd, (String) dataMap.get("RGTN_STS_CD"));
        }
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.dsAcctUrl, (String) dataMap.get("DS_ACCT_URL"));

        nmzc001001Pmsg.NMZC001002PMsg.setValidCount(1);
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).locNum, (String) dataMap.get("LOC_NUM"));
        // Location Address information
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).firstLineAddr, (String) dataMap.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).scdLineAddr, (String) dataMap.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).thirdLineAddr, (String) dataMap.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).frthLineAddr, (String) dataMap.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).ctyAddr, (String) dataMap.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).cntyNm, (String) dataMap.get("CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).stCd, (String) dataMap.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).postCd, (String) dataMap.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).ctryCd, (String) dataMap.get("CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).locNm, (String) dataMap.get("DS_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).addlLocNm, (String) dataMap.get("ADDL_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).glnNum, (BigDecimal) dataMap.get("GLN_NUM"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).telNum, (String) dataMap.get("TEL_NUM"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).faxNum, (String) dataMap.get("FAX_NUM"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).provNm, (String) dataMap.get("PROV_NM"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).lineBizTpCd, (String) dataMap.get("LINE_BIZ_TP_CD"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).effFromDt, (String) dataMap.get("EFF_FROM_DT"));
        // Location TAX Area
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).geoCd, (String) dataMap.get("GEO_CD"));
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).dsInsdCtyLimitFlg, (String) dataMap.get("DS_INSD_CTY_LIMIT_FLG"));
        // Location Cross Reference Information
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).xxLocXrefInfoFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).dsXrefAcctTpCd, DS_XREF_ACCT_TP.CUSA);
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).dsXrefAcctCd, rtlInstlCd);
        ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).effThruDt, (String) dataMap.get("EFF_THRU_DT"));
        return nmzc001001Pmsg;
    }

    private boolean callApiNMZC001001(NMZC001001PMsg pMsg, String dunsTrxHdrPk) {
        NMZC001001 api = new NMZC001001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        List<S21ApiMessage> errList = S21ApiUtil.getXxMsgList(pMsg);

        boolean errExist = true;
        if (!errList.isEmpty()) {
            for (S21ApiMessage msg : errList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrm = msg.getXxMsgPrmArray();

                if (msgId.endsWith("E")) {
                    errExist = false;
                }
            }
        } else {
            for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
                NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
                errList = S21ApiUtil.getXxMsgList(linePrm);
                for (S21ApiMessage msg : errList) {
                    String msgId = msg.getXxMsgid();
                    String[] msgPrm = msg.getXxMsgPrmArray();

                    if (msgId.endsWith("E")) {
                        errExist = false;
                    }
                }
            }
        }

        return errExist;
    }

    private void postErrorMail() {

        // *****************************************************************
        // Deriving From Mail Address
        // *****************************************************************
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GRP_ID_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        if (addrFromList.size() <= 0) {
            return;
        }

        S21MailAddress from = addrFromList.get(0);

        // *****************************************************************
        // Deriving To Mail Address
        // *****************************************************************
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, BIZ_APP_ID);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();

        if (addrToList.size() <= 0) {
            return;
        }

        // *****************************************************************
        // Create Mail Body
        // *****************************************************************
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, this.mailTemplateId);
        if (template == null) {
            return;
        }
        template.setTemplateParameter("batchId", this.getClass().getSimpleName());
        template.setTemplateParameter("errDate", ZYPDateUtil.formatEzd17ToDisp(this.sysTime));

        StringBuilder msgBuf = new StringBuilder();
        for (String tmpErrMsg : this.errMsgList) {
            msgBuf.append(tmpErrMsg);
            msgBuf.append(ERR_MSG_CRLF);
            msgBuf.append(ERR_MSG_SPACE);
        }
        String errorMessage = msgBuf.toString();

        template.setTemplateParameter("message", errorMessage);

        // *****************************************************************
        // Post mail
        // *****************************************************************
        S21Mail mail;
        for (S21MailAddress addr : addrToList) {
            mail = new S21Mail(this.glblCmpyCd);
            mail.setFromAddress(from);
            mail.setToAddress(addr);
            mail.setMailTemplate(template);
            mail.postMail();
        }
        return;
    }

    private void addErrMsg(String msgId, String... param) {
        this.errMsg = S21MessageFunc.clspGetMessage(msgId, param);
        if (!this.errMsgList.contains(this.errMsg)) {
            this.errMsgList.add(errMsg);
        }
    }
}
