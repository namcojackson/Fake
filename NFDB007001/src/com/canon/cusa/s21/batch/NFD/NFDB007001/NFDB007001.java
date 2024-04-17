/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFD.NFDB007001;

import static com.canon.cusa.s21.batch.NFD.NFDB007001.NFDB007001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NFD.NFDB007001.NFDB007001Constant.FETCH_SIZE_MAX;
import static com.canon.cusa.s21.batch.NFD.NFDB007001.NFDB007001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NFD.NFDB007001.NFDB007001Constant.MAIL_TMPL_ID;
import static com.canon.cusa.s21.batch.NFD.NFDB007001.NFDB007001Constant.MAIL_TMPL_KEY_ACCT_NUM;
import static com.canon.cusa.s21.batch.NFD.NFDB007001.NFDB007001Constant.MAIL_TMPL_KEY_BILL_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NFD.NFDB007001.NFDB007001Constant.MAIL_TMPL_KEY_CLT_PSN_NM;
import static com.canon.cusa.s21.batch.NFD.NFDB007001.NFDB007001Constant.MAIL_TMPL_KEY_CLT_WRK_ITEM_RWED_DT;
import static com.canon.cusa.s21.batch.NFD.NFDB007001.NFDB007001Constant.MAIL_TMPL_KEY_CLT_WRK_ITEM_RWSD_DT;
import static com.canon.cusa.s21.batch.NFD.NFDB007001.NFDB007001Constant.MAIL_TMPL_KEY_CLT_WRK_ITEM_WSRD_DT;
import static com.canon.cusa.s21.batch.NFD.NFDB007001.NFDB007001Constant.MAIL_TMPL_KEY_WRK_ITEM_NM;
import static com.canon.cusa.s21.batch.NFD.NFDB007001.NFDB007001Constant.NFBM0184E;
import static com.canon.cusa.s21.batch.NFD.NFDB007001.NFDB007001Constant.ZZZM9025E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_STRGY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_ITEM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 04/20/2015   Fujitsu         Y.Kamide        Create          N/A
 * 01/14/2016   CSAI            K.Uramori       Update          Apply finalized specification
 * 03/21/2016   CSAI            K.Uramori       Update          QC#5525
 * 2017/07/04   Hitachi         E.Kameishi      Update          QC#18754
 * 2021/07/01   CITS            G.Delgado       Update          QC#58909
 *</pre>
 */
public class NFDB007001 extends S21BatchMain {

    /** Normal Counter */
    private int normCnt = 0;

    /** Error Counter */
    private int errCnt = 0;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Batch Process Date */
    private String batProcDt = null;

    /** Term Code */
    private TERM_CD termCd = null;

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Default email address */
    private String defEmlAdd = "";

    /** Default email address list */
    private List<String> defEmlAddList = new ArrayList<String>();

    @Override
    protected void initRoutine() {

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.termCd = TERM_CD.NORMAL_END;

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        this.batProcDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd, BUSINESS_ID);

        // ----- start add 2016/01/14 get default email address from
        // VAR_CHAR_CONST
        defEmlAdd = ZYPCodeDataUtil.getVarCharConstValue(NFDB007001Constant.AR_CLT_DEF_EML_ADDR, this.glblCmpyCd);
        if (!hasValue(defEmlAdd)) {
            // error
            throw new S21AbendException(ZZZM9025E, new String[] {"Default Collector's Email Address" });
        }
        // split the email addresses
        divDefEmlAdd();
        // ----- end 2016/01/14
    }

    private void divDefEmlAdd() {
        StringTokenizer st = new StringTokenizer(defEmlAdd, ",");

        while (st.hasMoreTokens()) {
            defEmlAddList.add(st.nextToken());
        }

    }

    @Override
    protected void mainRoutine() {
        sendEscalationMail();
    }

    @Override
    protected void termRoutine() {
        setTermState(termCd, normCnt, errCnt, normCnt + errCnt);
    }

    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
        new NFDB007001().executeBatch(NFDB007001.class.getSimpleName());
    }

    /**
     * sendEscalationMail
     */
    private void sendEscalationMail() {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = getWorkItemTrx();
            rs = ps.executeQuery();
            while (rs.next()) {
                sendMail(rs);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * Send Mail
     * @param rs ResultSet
     * @throws SQLException
     */
    public void sendMail(ResultSet rs) throws SQLException {

        // 1. Get From Address
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1("NFD");
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        S21MailAddress from = null;
        if (!addrFromList.isEmpty()) {
            from = addrFromList.get(0);
        }

        // 2. Get To Address
        String cltPsnNmBill = rs.getString("CLT_PSN_NM_BILL");
        String cltPsnNmAcct = rs.getString("CLT_PSN_NM_ACCT");

        boolean useDefaultAddrFlg = false;
        List<String> mailAddressList = new ArrayList<String>();
        String cltPsnNm = cltPsnNmBill;

        S21UserInfo userInfo = getUserProfileService().getUserInfoFor(rs.getString("CLT_PSN_CD_BILL"));
        if (userInfo != null) {
            S21UserInfo superVisorInfo = getUserProfileService().getUserInfoFor(userInfo.getUserDetails().getManagerId());
            if (superVisorInfo != null) {
                String strAddrTo = superVisorInfo.getEmailAddress();
                if (hasValue(strAddrTo)) {
                    mailAddressList.add(strAddrTo);
                }
                setManagerMailAddress(mailAddressList, superVisorInfo);
            } else {
                useDefaultAddrFlg = true;
            }
        }

        if (!useDefaultAddrFlg && mailAddressList.size() < 1) {
            userInfo = getUserProfileService().getUserInfoFor(rs.getString("CLT_PSN_CD_ACCT"));
            if (userInfo != null) {
                S21UserInfo superVisorInfo = getUserProfileService().getUserInfoFor(userInfo.getUserDetails().getManagerId());
                if (superVisorInfo != null) {
                    cltPsnNm = cltPsnNmAcct;
                    String strAddrTo = superVisorInfo.getEmailAddress();
                    if (hasValue(strAddrTo)) {
                        mailAddressList.add(strAddrTo);
                    }
                    setManagerMailAddress(mailAddressList, superVisorInfo);
                }
            }
        }

        if (mailAddressList.size() < 1) {
            // --- start mod 2016/01/14 set default collector's email
            // address
            mailAddressList.addAll(defEmlAddList);
            cltPsnNm = NFDB007001Constant.AR_CLT_DEF_EML_ADDR_NM;
            // ---- end 2016/01/14
        }

        List<S21MailAddress> s21MailAddressList = new ArrayList<S21MailAddress>();
        for (String toAddr : mailAddressList) {
            s21MailAddressList.add(new S21MailAddress(this.glblCmpyCd, toAddr));
        }

        // 4. Create Subject and Body
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TMPL_ID);
        if (template == null) {
            throw new S21AbendException(NFBM0184E, new String[] {MAIL_TMPL_ID });
        }

        template.setTemplateParameter(MAIL_TMPL_KEY_WRK_ITEM_NM, nullToEmpty(rs.getString("CLT_WRK_ITEM_NM")));
        template.setTemplateParameter(MAIL_TMPL_KEY_ACCT_NUM, nullToEmpty(rs.getString("CLT_ACCT_CD")));
        // START 2021/07/01 G.Delgado [QC#58909,MOD]
        // template.setTemplateParameter(MAIL_TMPL_KEY_LOC_NUM, nullToEmpty(rs.getString("LOC_NUM")));
        template.setTemplateParameter(MAIL_TMPL_KEY_BILL_TO_CUST_CD, nullToEmpty(rs.getString("BILL_TO_CUST_CD")));
        // END 2021/07/01 G.Delgado [QC#58909,MOD]
        template.setTemplateParameter(MAIL_TMPL_KEY_CLT_PSN_NM, nullToEmpty(cltPsnNm));
        template.setTemplateParameter(MAIL_TMPL_KEY_CLT_WRK_ITEM_RWSD_DT, nullToEmpty(ZYPDateUtil.formatEzd8ToDisp(rs.getString("CLT_WRK_ITEM_RWSD_DT"))));
        template.setTemplateParameter(MAIL_TMPL_KEY_CLT_WRK_ITEM_WSRD_DT, nullToEmpty(ZYPDateUtil.formatEzd8ToDisp(rs.getString("CLT_WRK_ITEM_WSRD_DT"))));
        template.setTemplateParameter(MAIL_TMPL_KEY_CLT_WRK_ITEM_RWED_DT, nullToEmpty(ZYPDateUtil.formatEzd8ToDisp(rs.getString("CLT_WRK_ITEM_RWED_DT"))));

        // 5. Call Mail API
        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(s21MailAddressList);
        mail.setMailTemplate(template);
        mail.postMail();

        this.normCnt++;
    }

    /**
     * setManagerMailAddress
     * @param mailAddressList List<String>
     * @param superVisorInfo S21UserInfo
     */
    private void setManagerMailAddress(List<String> mailAddressList, S21UserInfo superVisorInfo) {
        S21UserInfo managerInfo = getUserProfileService().getUserInfoFor(superVisorInfo.getUserDetails().getManagerId());
        if (managerInfo != null) {
            String strAddrTo = managerInfo.getEmailAddress();
            if (hasValue(strAddrTo)) {
                mailAddressList.add(strAddrTo);
            }
        }
    }

    /**
     * getWorkItemTrx
     * @return
     * @throws SQLException
     */
    private PreparedStatement getWorkItemTrx() throws SQLException {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("cltStrgyStsCd", CLT_STRGY_STS.OPEN);
        queryParam.put("batProcDt", batProcDt);
        queryParam.put("cltWrkTpCd", CLT_WRK_TP.MANUAL);
        //---- start mod 2016/03/21 change to OPEN
        queryParam.put("cltWrkItemStsCd", CLT_WRK_ITEM_STS.OPEN);
        //---- end 2016/03/21
        // START 2017/06/26 E.Kameishi [QC#18754, ADD]
        queryParam.put("wrkItmStsPending", CLT_WRK_ITEM_STS.PENDING);
        // END 2017/06/26 E.Kameishi [QC#18754, ADD]
        queryParam.put("cltWrkEsclFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("flgY", ZYPConstant.FLG_ON_Y);
        // START 2017/08/03 J.Kim [QC#18754, ADD]
        queryParam.put("wrkTpCd", CLT_WRK_TP.MANUAL);
        // END 2017/08/03 J.Kim [QC#18754, ADD]

        return this.ssmLLClient.createPreparedStatement("getWorkItemTrx", queryParam, getExecPrm());
    }

    /**
     * get S21SsmExecutionParameter
     * @return S21SsmExecutionParameter
     */
    private S21SsmExecutionParameter getExecPrm() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    private static String nullToEmpty(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }
}
