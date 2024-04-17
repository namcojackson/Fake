package com.canon.cusa.s21.batch.NLB.NLBB003001;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NLZC205001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC205001.NLZC205001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmIntegerResultSetHandlerSupport;
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
 * <pre>
 * SO Creation
 * Create Shipping Order to Call SO API.
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/11   Fujitsu         H.Nishiyama     Create          N/A
 * 2012/11/29   Fujitsu         Y.Taoka         Update          WDS OCE #94
 * 2013/05/09   Fujitsu         J.Yasukawa      Update          WDS OCE R-WH003 & BUG FIX
 * 2016/02/12   CSAI            K.Lee           Update          WDS_NA QC#4290
 * 05/24/2016   CSAI            Y.Imazu         Update          QC#8126
 *</pre>
 */
public class NLBB003001 extends S21BatchMain {

    /** The process abended. */
    private static final String NLBM1065E = "NLBM1065E";

    /** DB Column: RTE_NUM */
    private static final String RTE_NUM = "RTE_NUM";

    /** DB Column: SHPG_PLN_NUM */
    private static final String SHPG_PLN_NUM = "SHPG_PLN_NUM";

    /** DB Column: SCE_ORD_TP_CD */
    private static final String SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /** Mail Message Header */
    private static final String MAIL_MSG_HEADER = "Routing#  Message#  Message";

    /** Mail Group ID */
    private static final String MAIL_GROUP_ID = "NLBB0030";

    /** Mail Template ID */
    private static final String MAIL_TEMPLATE_ID = "NLBB9999M001";

    /** Mail Key: From */
    private static final String MAIL_KEY_FROM = "From";

    /** Mail Key: To */
    private static final String MAIL_KEY_TO = "To";

    /** Mail Template Key: Batch ID */
    private static final String MAIL_TEMPLATE_KEY_ID = "batchId";

    /** Mail Template Key: Error Date */
    private static final String MAIL_TEMPLATE_KEY_DATE = "errDate";

    /** Mail Template Key: Message */
    private static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";

    /** Business ID */
    private static final String BUSINESS_ID = "NLBB0030";

    /** Date Time Pattern */
    private static final String DATE_TIME_PATTERN = "MM/dd/yyyy HH:mm:ss";

    /** Mail Message Format */
    private static final String ML_MSG_FMT = "%-10s %s";

    /** Line Separator Form */
    private static final String LINE_SEPARATOR = "line.separator";

    /** Error ResultSet Key */
    private static final String ERR_KEY = "key";

    /** Error Message ID */
    private static final String ERR_MSG_ID = "msgId";

    /* 05/24/2016 CSAI Y.Imazu Mod QC#8126 START */
    /** Commit count is not reached yet. Commit was skipped. */
    private static final String NLBM1348I = "NLBM1348I";
    /* 05/24/2016 CSAI Y.Imazu Mod QC#8126 END */
    // 2012/11/29 Add #94 End

    /** Total Commit Count */
    private int totalCommitCount;

    /** Total Error Count */
    private int totalErrCount;

    /** Terminate Cord */
    private TERM_CD termCd;

    /** User Profile */
    private S21UserProfileService profile;

    /** SQL Access Parts */
    private S21SsmBatchClient ssmBatchClient = null;


    /**
     * Main Method
     * @param args ArgmentList
     */
    public static void main(String[] args) {

        // S21BatchMain format
        new NLBB003001().executeBatch(NLBB003001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.totalCommitCount = 0;
        this.totalErrCount = 0;
        this.termCd = TERM_CD.NORMAL_END;
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
    }

    @Override
    protected void mainRoutine() {

        String glblCmpyCd = this.profile.getGlobalCompanyCode();

        // SQL bind parameter
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        // START MOD R-WH003
        // queryParam.put("shpgStsCd", SHPG_STS.S_OR_O_CREATING); // 2012/11/29 Modify #94
        queryParam.put("shpgStsCd", SHPG_STS.S_OR_O_PRINTED);
        // END MOD R-WH003
        queryParam.put("rteStsCd", RTE_STS.SO_CREATING);

        Integer result = (Integer) this.ssmBatchClient.queryObject(
                "getRteStsSoCreating",
                queryParam,
                new SoCreate(this.profile));

        this.totalCommitCount = result.intValue();
    }

    @Override
    protected void termRoutine() {

        int totalCount = this.totalCommitCount + this.totalErrCount;
        setTermState(this.termCd, this.totalCommitCount, this.totalErrCount, totalCount);
    }

    /**
     * SO Create
     */
    private class SoCreate extends S21SsmIntegerResultSetHandlerSupport {

        /** User Profile */
        private S21UserProfileService profile;

        public SoCreate(S21UserProfileService profile) {

            this.profile = profile;
        }

        public Integer doProcessQueryResult(ResultSet rs) throws SQLException {

            // Total Commit Count
            int rtrnCommitCount = 0;

            if (rs.next()) {

                String glblCmpyCd = this.profile.getGlobalCompanyCode();

                // Commit Count
                int commitCount = 0;

                // Operation Error Key List
                List<Map<String, String>> errInfos = new ArrayList<Map<String, String>>();

                // API Parameter List
                List<NLZC205001PMsg> params = new ArrayList<NLZC205001PMsg>();

                // Commit Waiting List
                List<Map<String, String>> commitList = new ArrayList<Map<String, String>>();

                boolean roopFlg = false;
                String unitRteNum = "";

                do {

                    String rteNum = nullToEmpty(rs.getString(RTE_NUM));

                    // First Record
                    if (!roopFlg) {

                        roopFlg = true;
                        unitRteNum = rteNum;

                    // First Record of RteNum Unit
                    } else if (!unitRteNum.equals(rteNum)) {

                        // Call SO API
                        boolean operationErrFlg = callSoApi(params);

                        if (operationErrFlg) {

                            // Set Error Message
                            List<Map<String, String>> apiErrInfos = setErrInfos(unitRteNum, params);
                            errInfos.addAll(apiErrInfos);
                        }

                        // 2012/11/29 Mod #94 Start
                        if (operationErrFlg) {

                            // rollback
                            rollback();
                            commitCount = 0;
                            errInfos.addAll(commitList);
                            /* 05/24/2016 CSAI Y.Imazu Add QC#8126 START */
                            commitList.clear();

                        } else {

                            commitCount++;
                            /* 05/24/2016 CSAI Y.Imazu Add QC#8126 END */

                            /* 05/24/2016 CSAI Y.Imazu Mod QC#8126 START */
                            // } else if (commitCount >= getCommitCount()) {
                            if (commitCount >= getCommitCount()) {
                                /* 05/24/2016 CSAI Y.Imazu Mod QC#8126 END */

                                // commit
                                /* 05/24/2016 CSAI Y.Imazu Del QC#8126 START */
                                // commitCount++;
                                /* 05/24/2016 CSAI Y.Imazu Del QC#8126 END */
                                commit();
                                rtrnCommitCount += commitCount;
                                commitCount = 0;
                                commitList.clear();

                            } else {

                                // wait commit
                                /* 05/24/2016 CSAI Y.Imazu Mod QC#8126 START */
                                // commitList.add(setErrInfo(unitRteNum, NLAM1262E));
                                // commitCount++;
                                commitList.add(setErrInfo(unitRteNum, NLBM1348I));
                                /* 05/24/2016 CSAI Y.Imazu Mod QC#8126 END */
                            }
                        /* 05/24/2016 CSAI Y.Imazu Add QC#8126 START */
                        }
                        /* 05/24/2016 CSAI Y.Imazu Add QC#8126 END */
                        // 2012/11/29 Mod #94 End

                        // Clear API Parameter List
                        params.clear();
                        unitRteNum = rteNum;
                    }

                    // Create API Parameter
                    NLZC205001PMsg pmsg = createPmsg(glblCmpyCd, rs);

                    // Add API Parameter List
                    params.add(pmsg);

                } while (rs.next());

                // Call SO API
                boolean operationErrFlg = callSoApi(params);

                if (operationErrFlg) {

                    // Set Error Message
                    List<Map<String, String>> apiErrInfos = setErrInfos(unitRteNum, params);
                    errInfos.addAll(apiErrInfos);
                }

                // 2012/11/29 Mod #94 Start
                if (operationErrFlg) {

                    // rollback
                    rollback();
                    commitCount = 0;
                    // START DEL 05/09/2013 BUG FIX
                    // commitList.add(setErrInfo(unitRteNum, NLAM1262E));
                    // END DEL 05/09/2013 BUG FIX
                    errInfos.addAll(commitList);

                } else {

                    // Commit
                    commit();
                    commitCount++;
                    commitList.clear();
                }
                // 2012/11/29 Mod #94 End

                // Is Operation Error?
                if (!errInfos.isEmpty()) {

                    String msgInfo = createMsgInfo(errInfos);

                    // Send Mail
                    postMail(glblCmpyCd, msgInfo);
                    totalErrCount = errInfos.size();
                    termCd = TERM_CD.WARNING_END;
                }

                rtrnCommitCount += commitCount;
            }

            return Integer.valueOf(rtrnCommitCount);
        }

        /**
         * Create API Parameter
         * @param glblCmpyCd GlobalCompanyCode
         * @param rs ResultSet
         * @return APIParameter
         * @exception SQLException
         */
        private NLZC205001PMsg createPmsg(final String glblCmpyCd, final ResultSet rs) throws SQLException {

            String shpgPlnNum = nullToEmpty(rs.getString(SHPG_PLN_NUM));
            String sceOrdTpCd = nullToEmpty(rs.getString(SCE_ORD_TP_CD));

            NLZC205001PMsg pmsg = new NLZC205001PMsg();

            pmsg.glblCmpyCd.setValue(glblCmpyCd);
            pmsg.sceOrdTpCd.setValue(sceOrdTpCd);
            pmsg.shpgPlnNum.setValue(shpgPlnNum);
            pmsg.shpgFrceFlg.setValue(NLZC205001.SHPG_FRCE_FLG_OFF);
            pmsg.xxModeCd.setValue(NLZC205001.MODE_NEW);
            return pmsg;
        }

        /**
         * Call SO API
         * @param params APIParameterList
         * @return OperationErrorFlag (true: OperationError)
         */
        private boolean callSoApi(List<NLZC205001PMsg> params) {

            boolean operationErrFlg = false;

            NLZC205001 api = new NLZC205001();
            api.execute(params, S21ApiInterface.ONBATCH_TYPE.BATCH);

            for (NLZC205001PMsg pmsg : params) {

                List<String> msgIds = S21ApiUtil.getXxMsgIdList(pmsg);

                for (String msgId : msgIds) {

                    if (msgId.endsWith("W")) {

                        S21InfoLogOutput.println(msgId);
                        continue;
                    } else if (msgId.endsWith("E")) {
                        operationErrFlg = true;
                        break;
                    }

                }
            }

            return operationErrFlg;
        }

        /**
         * Set Operation Error Key List
         * @param key ErrorKeyValue
         * @param params APIParameterList
         * @return OperationErrorKeyList
         */
        private List<Map<String, String>> setErrInfos(final String key, final List<NLZC205001PMsg> params) {

            List<Map<String, String>> errInfos = new ArrayList<Map<String, String>>();

            for (NLZC205001PMsg pmsg : params) {

                List<String> msgIds = S21ApiUtil.getXxMsgIdList(pmsg);

                for (String msgId : msgIds) {

                    Map<String, String> errInfo = setErrInfo(key, msgId);
                    errInfos.add(errInfo);
                }
            }

            return errInfos;
        }

        /**
         * Set Operation Error Key
         * @param key ErrorKeyValue
         * @param msgId ErrorMessageID
         * @return OperationErrorKey
         */
        private Map<String, String> setErrInfo(final String key, final String msgId) {

            S21InfoLogOutput.println(msgId);

            Map<String, String> errInfo = new HashMap<String, String>();
            errInfo.put(ERR_KEY, key);
            errInfo.put(ERR_MSG_ID, msgId);

            return errInfo;
        }

        /**
         * Create mail message
         * @param errLists ErrorList
         * @return MailMessage
         */
        private String createMsgInfo(final List<Map<String, String>> errLists) {

            StringBuilder msgInfo = new StringBuilder();

            // Header
            msgInfo.append(MAIL_MSG_HEADER);
            msgInfo.append(System.getProperty(LINE_SEPARATOR));

            // Detail
            for (Map<String, String> errList : errLists) {

                String key = errList.get(ERR_KEY);
                String msgId = errList.get(ERR_MSG_ID);
                String msg = S21MessageFunc.clspGetMessage(msgId);

                msgInfo.append(String.format(ML_MSG_FMT, key, msg));
                msgInfo.append(System.getProperty(LINE_SEPARATOR));
            }

            return msgInfo.toString();
        }

        /**
         * @param glblCmpyCd GlobalCompanyCode
         * @param messageInfo MailMessage
         */
        private void postMail(String glblCmpyCd, String messageInfo) {

            S21Mail mail = new S21Mail(glblCmpyCd);
            S21MailGroup group = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID);

            // Get sender user
            group.setMailKey1(MAIL_KEY_FROM);
            List<S21MailAddress> fromAddrList = group.getMailAddress();

            if (!fromAddrList.isEmpty()) {

                // Set sender
                mail.setFromAddress(fromAddrList.get(0));
            }

            // Get send to user
            group.setMailKey1(MAIL_KEY_TO);
            List<S21MailAddress> toAddrList = group.getMailAddress();

            if (toAddrList.isEmpty()) {

                throw new S21AbendException(NLBM1065E);
            }

            // Set send to
            mail.setToAddressList(toAddrList);

            // Get date time
            String currentTime = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN);

            S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);

            // mail template parameter
            template.setTemplateParameter(MAIL_TEMPLATE_KEY_ID, BUSINESS_ID);
            template.setTemplateParameter(MAIL_TEMPLATE_KEY_DATE, currentTime);
            template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, messageInfo);

            // Set mail template
            mail.setMailTemplate(template);

            // set mail database
            mail.postMail();
        }

        /**
         * @param str String
         * @return String
         */
        private String nullToEmpty(String str) {

            if (str == null) {

                return "";

            } else {

                return str;
            }
        }
    }
}
