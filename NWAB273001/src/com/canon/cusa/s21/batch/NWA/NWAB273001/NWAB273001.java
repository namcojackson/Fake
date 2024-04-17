/**
 * <Pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB273001;

import static com.canon.cusa.s21.batch.NWA.NWAB273001.constant.NWAB273001Constant.CRLF;
import static com.canon.cusa.s21.batch.NWA.NWAB273001.constant.NWAB273001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB273001.constant.NWAB273001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NWA.NWAB273001.constant.NWAB273001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NWA.NWAB273001.constant.NWAB273001Constant.MAIL_KEY_FROM;
import static com.canon.cusa.s21.batch.NWA.NWAB273001.constant.NWAB273001Constant.MAIL_KEY_TO;
import static com.canon.cusa.s21.batch.NWA.NWAB273001.constant.NWAB273001Constant.MAIL_OUT_TYPE_MM_DD_YYYY;
import static com.canon.cusa.s21.batch.NWA.NWAB273001.constant.NWAB273001Constant.MAIL_TEMPLATE_ID_M001;
import static com.canon.cusa.s21.batch.NWA.NWAB273001.constant.NWAB273001Constant.MAIL_TEMPLATE_KEY_CONTENT;
import static com.canon.cusa.s21.batch.NWA.NWAB273001.constant.NWAB273001Constant.MAIL_TEMPLATE_KEY_SLS_DT;
import static com.canon.cusa.s21.batch.NWA.NWAB273001.constant.NWAB273001Constant.PROC_RES_CD_ERROR;
import static com.canon.cusa.s21.batch.NWA.NWAB273001.constant.NWAB273001Constant.PROC_RES_CD_SUCCESS;
import static com.canon.cusa.s21.batch.NWA.NWAB273001.constant.NWAB273001Constant.NWZM2320E;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import business.parts.NWZC229001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC229001.NWZC229001;
import com.canon.cusa.s21.batch.NWA.NWAB273001.constant.NWAB273001Constant.MSG_ID;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * eCheck Capture Batch.
 * program of BusinessID NWAB273001. 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/06/2023   Hitachi         M.Hashino       Create          QC55645
 * 08/01/2023   Hitachi         S.Ikariya       Update          QC61696
 *</pre>
 */

public class NWAB273001 extends S21BatchMain {

    /** Global Company code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** Term Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Error Record Count */
    private int errRecCnt = 0;

    /** records */
    private int totalRecCnt = 0;

    /** Normal Record Count */
    private int normalRecCnt = 0;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Mail template ID */
    private String mailTemplateId = null;

    /** Mail Bean Map */
    private HashMap<String, NWAB273001MailBean> emailBodyMap = null;

    /** Collection Person Code */
    private String cltPsnCd = null;

    /** Collection Person Name */
    private String cltPsnNm = null;

    /** Masked Bank Account Number */
    private String maskedBankAcctNum = null;

    /**
     * Main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWAB273001().executeBatch(NWAB273001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Global Company Code"));
        }

        // Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Sales Date"));
        }

        // Mail Template
        mailTemplateId = MAIL_TEMPLATE_ID;
        if (!ZYPCommonFunc.hasValue(mailTemplateId)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Mail Template Id"));
        }

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.emailBodyMap = new HashMap<String, NWAB273001MailBean>();

    }

    @Override
    protected void mainRoutine() {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("fnlzInvFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("echkPmtTermCashDiscCd", PMT_TERM_CASH_DISC.CHECK_BY_PHONE);

        boolean rslt = (Boolean) ssmBatchClient.queryObject("getTargetInvoice", ssmParam, new ECheckInterface());
        if (!rslt) {
            this.termCd = TERM_CD.WARNING_END;
        }
    }


    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);
        }

    /**
     * ResultSet of SQL process.
     */
    protected class ECheckInterface extends S21SsmBooleanResultSetHandlerSupport {

        @Override
        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            Boolean rslt = Boolean.TRUE;

            // Has No Result Data
            if (!rs.next()) {
                return true;
            }

            // PMsg
            NWZC229001PMsg pMsg = new NWZC229001PMsg();
            // START 2023/08/01 S.Ikariya [QC#61696,MOD]
//            int i = 0;
//            String prevRecBankRteNum = null;
//            String prevRecBankAcctNum = null;
//            BigDecimal echkPmtAmt = BigDecimal.ZERO;
            pMsg.InvList.setValidCount(1);
            // END 2023/08/01 S.Ikariya [QC#61696,MOD]
            String procResCd = null;

            do {
                  // START 2023/08/01 S.Ikariya [QC#61696,DEL]
//                if (ZYPCommonFunc.hasValue(prevRecBankRteNum) && ZYPCommonFunc.hasValue(prevRecBankAcctNum)
//                        && prevRecBankRteNum.equals(rs.getString("BANK_RTE_NUM")) && prevRecBankAcctNum.equals(rs.getString("DS_BANK_ACCT_NUM"))) {
//                    i++;
//                } else {
//                    if (ZYPCommonFunc.hasValue(prevRecBankRteNum) && ZYPCommonFunc.hasValue(prevRecBankRteNum)) {
//                        pMsg.InvList.setValidCount(i + 1);
//                        procResCd = callECheckInterfaceAPI(pMsg);
//                       if (PROC_RES_CD_SUCCESS.equals(procResCd)) {
//                            normalRecCnt++;
//                        } else if (PROC_RES_CD_ERROR.equals(procResCd)) {
//                            errRecCnt++;
//                        }
//                        totalRecCnt++;
//                    }
//                // Clear prevAcct Parameters
//                    i = 0;
//                    echkPmtAmt = BigDecimal.ZERO;
                  // END 2023/08/01 S.Ikariya [QC#61696,DEL]
                    pMsg.clear();
                    // Set Parameters to PMsg
                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
                    ZYPEZDItemValueSetter.setValue(pMsg.bankRteNum, rs.getString("BANK_RTE_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.dsBankAcctNum, rs.getString("DS_BANK_ACCT_NUM"));
                    ZYPEZDItemValueSetter.setValue(pMsg.billToCustAcctCd, rs.getString("SELL_TO_CUST_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.dsCustBankAcctRelnPk, rs.getBigDecimal("DS_CUST_BANK_ACCT_RELN_PK"));
                    // START 2023/08/01 S.Ikariya [QC#61696,ADD]
                    ZYPEZDItemValueSetter.setValue(pMsg.echkPmtAmt, rs.getBigDecimal("INV_TOT_DEAL_NET_AMT"));
                    // END 2023/08/01 S.Ikariya [QC#61696,ADD]
                    // Set other info
                    maskedBankAcctNum = rs.getString("MASK_BANK_ACCT_NUM");
                    cltPsnCd = rs.getString("CLT_PSN_CD");
                    cltPsnNm = rs.getString("CLT_PSN_NM");
                    // START 2023/08/01 S.Ikariya [QC#61696,MOD]
//                  // Save Bank Account Info
//                  prevRecBankRteNum = rs.getString("BANK_RTE_NUM");
//                  prevRecBankAcctNum = rs.getString("DS_BANK_ACCT_NUM");
//              }
//              // Calculate and Set echkPmtAmt
//              echkPmtAmt = echkPmtAmt.add(rs.getBigDecimal("INV_TOT_DEAL_NET_AMT"));
//              ZYPEZDItemValueSetter.setValue(pMsg.echkPmtAmt, echkPmtAmt);

                // Set invoice detail info to PMsg InvList
//              ZYPEZDItemValueSetter.setValue(pMsg.InvList.no(i).invNum, rs.getString("INV_NUM"));
//              ZYPEZDItemValueSetter.setValue(pMsg.InvList.no(i).invDt, rs.getString("INV_DT"));
//              ZYPEZDItemValueSetter.setValue(pMsg.InvList.no(i).invTotDealNetAmt, rs.getBigDecimal("INV_TOT_DEAL_NET_AMT"));
                ZYPEZDItemValueSetter.setValue(pMsg.InvList.no(0).invNum, rs.getString("INV_NUM"));
                ZYPEZDItemValueSetter.setValue(pMsg.InvList.no(0).invDt, rs.getString("INV_DT"));
                ZYPEZDItemValueSetter.setValue(pMsg.InvList.no(0).invTotDealNetAmt, rs.getBigDecimal("INV_TOT_DEAL_NET_AMT"));
                // END 2023/08/01 S.Ikariya [QC#61696,MOD]
              // START 2023/08/01 S.Ikariya [QC#61696,DEL]
//            } while(rs.next());
//             // call API for Last Account
//            pMsg.InvList.setValidCount(i + 1);
              // END 2023/08/01 S.Ikariya [QC#61696,DEL]
                procResCd = callECheckInterfaceAPI(pMsg);
                if (PROC_RES_CD_SUCCESS.equals(procResCd)) {
                    normalRecCnt++;
                } else if (PROC_RES_CD_ERROR.equals(procResCd)) {
                    errRecCnt++;
                }
                totalRecCnt++;
            // START 2023/08/01 S.Ikariya [QC#61696,ADD]
            } while(rs.next());
            // END 2023/08/01 S.Ikariya [QC#61696,ADD]

            if (emailBodyMap.size() > 0) {
                sendMail();
            }

            if (errRecCnt > 0) {
                rslt = Boolean.FALSE;
            }
            rs.close();
            return rslt;
        }
    }

    /**
     * Call eCheck Interface API.
     * @param pMsg NWZC229001PMsg.
     * @return String.
     */
    private String callECheckInterfaceAPI(NWZC229001PMsg pMsg) {
        // eCheckInterfaceAPI
         new NWZC229001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        // commit TRX table
        commit();

        String isApproved = pMsg.echkHostRspStsCd.getValue();

        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            String errMsg = "";
            for (int n = 0; n < pMsg.xxMsgIdList.getValidCount(); n++) {
                String errId = pMsg.xxMsgIdList.no(n).xxMsgId.getValue();
                if (ZYPCommonFunc.hasValue(errId)){
                    S21InfoLogOutput.println(errId);
                }
            }

            // Input parameter error, CPGM Gateway error
            if (!ZYPCommonFunc.hasValue(isApproved)) {
                errMsg = pMsg.xxMsgIdList.no(0).xxMsgTxt.getValue();

            } else {

                // Payment process error
                if (!ZYPCodeDataUtil.getVarCharConstValue("ECHK_APVL_STS_APPROVED", pMsg.glblCmpyCd.getValue()).equals(isApproved)) {
                    S21InfoLogOutput.println(pMsg.echkHostRspStsDescTxt.getValue());
                    errMsg = pMsg.echkHostRspStsDescTxt.getValue();

                // Payment process: Success, Insert DB process: Error
                } else {
                    errMsg = S21MessageFunc.clspGetMessage(NWZM2320E);
                }
            }
            // Error Message
            setDeclineInfoEmail(pMsg, errMsg);
            return PROC_RES_CD_ERROR;
        }
        return PROC_RES_CD_SUCCESS;
    }

    /**
     * <pre>
     * Set Decline Information Email
     * </pre>
     * @param pMsg NWZC229001PMsg
     * @param errMsg String
     */
    private void setDeclineInfoEmail(NWZC229001PMsg pMsg, String errMsg) {
        NWAB273001MailBean mailBean = new NWAB273001MailBean();
        mailBean.setSellToCustCd(pMsg.billToCustAcctCd.getValue());
        mailBean.setSellToLocNm(pMsg.locNm.getValue());
        mailBean.setErrMsgTxt(errMsg);
        mailBean.setBankRteNum(pMsg.bankRteNum.getValue());
        mailBean.setBankAcctNum(this.maskedBankAcctNum);
        mailBean.setCltPsnCd(this.cltPsnCd);
        mailBean.setCltPsnNm(this.cltPsnNm);
        mailBean.setSlsDt(this.slsDt);
        for (int n = 0; n < pMsg.InvList.getValidCount(); n++) {
            NWAB273001MailContentsLineBean lineBean = new NWAB273001MailContentsLineBean();
            lineBean.setInvNum(pMsg.InvList.no(n).invNum.getValue());
            lineBean.setInvDt(pMsg.InvList.no(n).invDt.getValue());
            lineBean.setInvTotDealNetAmt(pMsg.InvList.no(n).invTotDealNetAmt.getValue());
            mailBean.setContentsLine(lineBean);
        }
        mailBean.setEchkPmtAmt(pMsg.echkPmtAmt.getValue());
        this.emailBodyMap.put(pMsg.echkPmtReqId.getValue(), mailBean);
    }

    /**
     * <pre>
     * Send Mail
     * </pre>
     */

    private void sendMail() {

        StringBuilder contents = new StringBuilder();

        // Mail Contents
        for (Iterator<Entry<String, NWAB273001MailBean>> it = emailBodyMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, NWAB273001MailBean> entry = (Map.Entry<String, NWAB273001MailBean>) it.next();
            contents.append(((NWAB273001MailBean) entry.getValue()).getMailTxt());
            contents.append(CRLF).append(CRLF);
        }

        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_KEY_FROM);

        S21MailAddress fromAddr = null;
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        if (addrFromList != null && !addrFromList.isEmpty()) {

            fromAddr = addrFromList.get(0);
            S21Mail mail = new S21Mail(glblCmpyCd);
            mail.setFromAddress(fromAddr);

            S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
            groupTo.setMailKey1(MAIL_KEY_TO);

            List<S21MailAddress> addrToList = groupTo.getMailAddress();
            if (addrToList.isEmpty()) {
                String[] errMsg = new String[] {MAIL_GROUP_ID_TO, MAIL_KEY_TO };
                S21InfoLogOutput.println(MSG_ID.NWAM0516E.toString(), errMsg);
                throw new S21AbendException(MSG_ID.NWAM0516E.toString(), errMsg);
            }
            mail.setToAddressList(addrToList);

            S21MailTemplate mailTemp = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_M001);

            if (mailTemp != null) {
                mailTemp.setTemplateParameter(MAIL_TEMPLATE_KEY_CONTENT, contents.toString());
                mailTemp.setTemplateParameter(MAIL_TEMPLATE_KEY_SLS_DT, ZYPDateUtil.DateFormatter(slsDt, ZYPDateUtil.TYPE_YYYYMMDD, MAIL_OUT_TYPE_MM_DD_YYYY));
                mail.setMailTemplate(mailTemp);
                String resCd = mail.postMail();
                if (!ZYPCommonFunc.hasValue(resCd)) {
                    throw new S21AbendException(MSG_ID.NWAM0268E.toString());
                }
            } else {
                String[] errMsg = new String[] {MAIL_TEMPLATE_ID_M001 };
                S21InfoLogOutput.println(MSG_ID.NWAM0319E.toString(), errMsg);
                throw new S21AbendException(MSG_ID.NWAM0319E.toString(), errMsg);
            }
        } else {
            String[] errMsg = new String[] {MAIL_GROUP_ID_FROM, MAIL_KEY_FROM };
            S21InfoLogOutput.println(MSG_ID.NWAM0516E.toString(), errMsg);
            throw new S21AbendException(MSG_ID.NWAM0516E.toString(), errMsg);
        }
    }

    /**
     * <pre>
     * To Array
     * </pre>
     * @param appendMsgList String[]
     * @return String[]
     */
    private String[] toArray(String... appendMsgList) {
        String[] msgArray = new String[appendMsgList.length];
        for (int i = 0; i < msgArray.length; i++) {
            msgArray[i] = appendMsgList[i];
        }
        return msgArray;
    }
}
