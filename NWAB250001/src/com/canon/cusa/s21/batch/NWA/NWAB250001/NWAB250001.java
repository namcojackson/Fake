/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB250001;

import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.AUTO_LAK_UPDATE_ELIG;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.BAT_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.BIZ_APP_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.COL_CPO_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.COL_CPO_DTL_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.COL_CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.COL_LIC_ACCS_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.DB_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.DB_PARAM_GLBL_CMPY_CD_CUSA;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.DB_PARAM_ORD_CATG_CTX_TP_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.DB_PARAM_ORD_HDR_STS_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.MAIL_ADDR_TO_GRP;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.MAIL_FROM_ADDR_GRP_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.MAIL_TEMPLATE_BATCH_ID_KEY;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.MAIL_TEMPLATE_BATCH_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.MAIL_TEMPLATE_ERROR_INFO_KEY;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.MAIL_TEMP_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.NWAM0311E;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.NWAM0729E;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.NWAM0865E;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.SER_NUM_DIGIT;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.VAR_CHAR_CONST_KEY_CUSA_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB250001.constant.NWAB250001Constant.ZZZM9025E;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 *<pre>
 * NWAB2500:LAK IF Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/01/2016   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NWAB250001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd;

    /** Global Company Code for CUSA*/
    private String glblCmpyCdCusa;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** normal count */
    private int normalCount = 0;

    /** error count */
    private int errorCount = 0;

    /** error message List */
    private List<String> errorInfoList = new ArrayList<String>();

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameter.
     */
    public static void main(String[] args) {

        // Initialization S21BatchMain
        new NWAB250001().executeBatch(NWAB250001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code"});
        }

        // Get CUSA_GLBL_CMPY_CD
        this.glblCmpyCdCusa = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_CUSA_GLBL_CMPY_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCdCusa)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"CUSA Global Company Code"});
        }
    }

    @Override
    protected void termRoutine() {

        if (hasValueList(this.errorInfoList)) {
            // send error mail
            postErrorMail();
            termCd = TERM_CD.WARNING_END;
        }

        setTermState(termCd, normalCount, errorCount);
    }

    @Override
    protected void mainRoutine() {

        // S21SsmLowLevelCodintClient Setup
        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        execParam.setMaxRows(0);

        PreparedStatement ps = null;
        ResultSet rs = null;

        // Search Target LAK Order
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(DB_PARAM_ORD_HDR_STS_CD, ORD_HDR_STS.VALIDATED);
        ssmParam.put(DB_PARAM_ORD_CATG_CTX_TP_CD, AUTO_LAK_UPDATE_ELIG);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD_CUSA, this.glblCmpyCdCusa);

        try {
            ps = ssmLlcClient.createPreparedStatement("searchCsaLakRpst", ssmParam, execParam);
            rs = ps.executeQuery();

            while (rs.next()) {

                // Main Process
                if (!mainProcess(rs)) {
                    rollback();
                    errorCount++;
                } else {
                  commit();
                    normalCount++;
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    // ****************************************************************
    // Main Process
    // ****************************************************************
    private boolean mainProcess(ResultSet rs) throws SQLException {

        String cpoOrdNum = rs.getString(COL_CPO_ORD_NUM);
        String cpoDtlLineNum = rs.getString(COL_CPO_DTL_LINE_NUM);
        String cpoDtlLineSubNum = rs.getString(COL_CPO_DTL_LINE_SUB_NUM);

        String licAccsNum = rs.getString(COL_LIC_ACCS_NUM);

        // LAK length check
        if (licAccsNum.length() > SER_NUM_DIGIT) {
            String errMsgText = S21MessageFunc.clspGetMessage(NWAM0865E, new String[] {licAccsNum});
            writeLogLn(errMsgText);
            //Add Error Mail Message Text
            this.errorInfoList.add(errMsgText);
            return false;
        }

        // Get CPO
        CPOTMsg cpoTMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, cpoOrdNum);

        cpoTMsg = (CPOTMsg) EZDTBLAccessor.findByKeyForUpdate(cpoTMsg);
        if (cpoTMsg == null) {
            String[] paramArray = new String[] {"CPO", String.format("CPO_ORD_NUM = %s", cpoOrdNum)};
            String errMsgText = S21MessageFunc.clspGetMessage(NWAM0311E, paramArray);
            writeLogLn(errMsgText);
            return false;
        }

        // Detail
        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum, cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);
        cpoDtlTMsg = (CPO_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(cpoDtlTMsg);
        if (cpoDtlTMsg == null) {
            String[] paramArray = new String[] {"CPO_DTL", String.format("CPO_ORD_NUM = %s, CPO_DTL_LINE_NUM = %s, CPO_DTL_LINE_SUB_NUM = %s", cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum)};
            String errMsgText = S21MessageFunc.clspGetMessage(NWAM0311E, paramArray);
            writeLogLn(errMsgText);
            return false;
        }

        // Update LAK
        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.serNum, licAccsNum);

        EZDTBLAccessor.update(cpoDtlTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cpoDtlTMsg.getReturnCode())) {
            String[] paramArray = new String[] {"CPO_DTL", String.format("CPO_ORD_NUM = %s, CPO_DTL_LINE_NUM = %s, CPO_DTL_LINE_SUB_NUM = %s", cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum)};
            String errMsgText = S21MessageFunc.clspGetMessage(NWAM0729E, paramArray);
            writeLogLn(errMsgText);
            return false;
        }

        return true;
    }

    // ****************************************************************
    // Post Error Mail Method
    // ****************************************************************
    private void postErrorMail() {
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_FROM_ADDR_GRP_ID);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        if (!hasValueList(addrFromList)) {
            return;
        }

        S21MailAddress from = addrFromList.get(0);

        // *****************************************************************
        // Deriving To Mail Address
        // *****************************************************************
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_ADDR_TO_GRP);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();

        if (!hasValueList(addrToList)) {
            return;
        }

        // *****************************************************************
        // Create Mail Body
        // *****************************************************************
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMP_ID);
        if (template == null) {
            return;
        }

        template.setTemplateParameter(MAIL_TEMPLATE_BATCH_ID_KEY, BIZ_APP_ID);
        template.setTemplateParameter(MAIL_TEMPLATE_BATCH_NM, BAT_NM);
        String errMsg = getMailBodyErrMsg();
        template.setTemplateParameter(MAIL_TEMPLATE_ERROR_INFO_KEY, errMsg);

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
    }

    private String getMailBodyErrMsg() {
        StringBuilder sb = new StringBuilder();

        for (String message : this.errorInfoList) {
            sb.append(message);
            sb.append("\n");
            sb.append("     ");
        }

        return sb.toString();
    }

    // ****************************************************************
    // Data Check Method
    // ****************************************************************
    private static <T extends List< ? >> boolean hasValueList(T list) {
        return (list != null && list.size() > 0);
    }

    // ****************************************************************
    // Output Log Method
    // ****************************************************************
    private static void writeLogLn(String format, Object... param) {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[%s]", BIZ_APP_ID));

        if (param.length > 0) {
            sb.append(String.format(format, param));
        } else {
            sb.append(format);
        }

        S21InfoLogOutput.println(sb.toString());
    }
}
