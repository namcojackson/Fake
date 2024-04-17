/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB032001;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** <pre>
 * Delivery Schedule Non-Assign Notification
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/22/2015   CSAI            Y.Imazu         Create          N/A
 * </pre>
 */
public class NLBB032001 extends S21BatchMain {

    /** Counter : Normal finished Records */
    private int counterNomalRec = 0;

    /** Counter : Error finished Records */
    private int counterErrorRec = 0;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Batch Process Date */
    private String batchProcDt = null;

    /** Term Code */
    private TERM_CD termCd;

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Mail Message Information */
    private StringBuilder mailMsgInfo = null;

    /** Fatch size for SSM */
    private static final int FETCH_SIZE_MAX = 1000;

    /** Business ID */
    private static final String BUSINESS_ID = "NLBB032001";

    /** Mail Group ID */
    private static final String DEF_MAIL_GROUP_ID = "NLBB0320";

    /** Mail group id for From */
    private static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** Mail Template ID */
    private static final String MAIL_TEMPLATE_ID = "NLBB0320M001";

    /** Mail Template Key: Batch ID */
    private static final String MAIL_TEMPLATE_KEY_ID = "batchId";

    /** Mail Template Key: Message */
    private static final String MAIL_TEMPLATE_KEY_DATA = "message";

    /** Error Message Text : space */
    private static final String MSG_TXT_SPACE = " ";

    /** Mail line feed code */
    private static final String LINE_FEED_CODE = "\r\n";

    /** Global Company Code is mandatory. */
    private static final String NASM0010E = "NASM0010E";

    /** "Data Global Company Code" does not exist in the Master. */
    private static final String NWZM0650E = "NWZM0650E";

    /** Batch Operation Date cannot be obtained. */
    private static final String NDMM0016E = "NDMM0016E";

    /** It failed to register an  email. */
    private static final String NLEM0004E = "NLEM0004E";

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {

        new NLBB032001().executeBatch(NLBB032001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.termCd = TERM_CD.NORMAL_END;

        S21UserProfileService prof = S21UserProfileServiceFactory.getInstance().getService();
        glblCmpyCd = prof.getGlobalCompanyCode();

        if (S21StringUtil.isEmpty(glblCmpyCd)) {

            throw new S21AbendException(NASM0010E);
        }

        // Value check(Global Company Code)
        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);

        if (null == glblCmpyTMsg) {

            throw new S21AbendException(NWZM0650E);
        }

        batchProcDt = ZYPDateUtil.getBatProcDate(glblCmpyCd, BUSINESS_ID);

        if (S21StringUtil.isEmpty(batchProcDt)) {

            throw new S21AbendException(NDMM0016E);
        }
    }

    @Override
    protected void mainRoutine() {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            /*************************************************************
             * 1. Get Schedule Non-Assign Data
             ************************************************************/
            S21SsmExecutionParameter excParam = new S21SsmExecutionParameter();
            excParam.setFetchSize(FETCH_SIZE_MAX);
            excParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            excParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            excParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", glblCmpyCd);
            paramMap.put("sceOrdTpRS", SCE_ORD_TP.DIRECT_SALES);

            stmt = this.ssmLLClient.createPreparedStatement("getNonAsgSchd", paramMap, excParam);
            rs = stmt.executeQuery();

            /*************************************************************
             * 2. Create Message
             ************************************************************/
            while (rs.next()) {

                if (this.mailMsgInfo == null) {

                    this.mailMsgInfo = new StringBuilder();
                }

                this.mailMsgInfo.append(ZYPCommonFunc.rightPad(rs.getString("TRX_HDR_NUM"), 15, MSG_TXT_SPACE));
                this.mailMsgInfo.append(ZYPCommonFunc.rightPad(rs.getString("SO_NUM"), 21, MSG_TXT_SPACE));

                if (ZYPCommonFunc.hasValue(rs.getString("RDD_DT"))) {

                    this.mailMsgInfo.append(ZYPCommonFunc.rightPad(ZYPDateUtil.convertFormat(rs.getString("RDD_DT"), "yyyyMMdd", "MMddyyyy", '/'), 18, MSG_TXT_SPACE));

                } else {

                    this.mailMsgInfo.append(ZYPCommonFunc.rightPad(rs.getString("RDD_DT"), 18, MSG_TXT_SPACE));
                }

                this.mailMsgInfo.append(rs.getString("RTL_WH_NM"));
                this.mailMsgInfo.append(LINE_FEED_CODE);

                counterNomalRec++;
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }

        /*************************************************************
         * 3. Send eMail
         ************************************************************/
         if (this.mailMsgInfo != null) {

            callPostMail();
        }
    }

    @Override
    protected void termRoutine() {

        // Print result.
        if (counterErrorRec == 0) {

            S21InfoLogOutput.println("Delivery Schedule Non-Assign Notification Batch is normally end.");

        } else {

            S21InfoLogOutput.println("Delivery Schedule Non-Assign Notification Batch is abnormally end.");
        }

        // Set term code and total count.
        setTermState(this.termCd, counterNomalRec, counterErrorRec, (counterNomalRec + counterErrorRec));
    }

    /**
     * Send error Mail
     */
    private void callPostMail() {

        S21Mail mail = new S21Mail(glblCmpyCd);
        S21MailGroup group = new S21MailGroup(glblCmpyCd, DEF_MAIL_GROUP_ID);

        // Get From Address
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        if (!addrFromList.isEmpty()) {

            mail.setFromAddress(addrFromList.get(0));
        }

        // Get To Address
        List<S21MailAddress> toAddrList = group.getMailAddress();

        if (toAddrList.isEmpty()) {

            throw new S21AbendException(NLEM0004E);
        }

        mail.setToAddressList(toAddrList);

        // Get date time
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);

        // mail template parameter
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_ID, BUSINESS_ID);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_DATA, this.mailMsgInfo.toString());

        // Set mail template
        mail.setMailTemplate(template);

        // set mail database
        mail.postMail();
    }
}