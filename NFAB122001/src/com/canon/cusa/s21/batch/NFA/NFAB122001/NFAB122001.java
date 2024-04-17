/**
 * <pre>Copyright (c) 2020 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB122001;

import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.BATCH_ID;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.BIZ_APP_ID;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.DUMMY_CODE;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.ERR_MSG_CRLF;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.ERR_MSG_SPACE_1;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.ERR_MSG_SPACE_4;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.ERR_MSG_SPACE_7;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.FIN_BR_CD;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.MAIL_GRP_ID_FROM;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.MSG_ITEM_FINANCIAL_BRANCH;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.MSG_ITEM_FINANCIAL_BRANCH_DEFAULT;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.MSG_ITEM_SALES_DATE;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.NFAB1220_DEFAULT_FIN_BR;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.NSAM0032E;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.NSAM0469E;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.NSAM0470E;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.NSZM0392E;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.NZZM0003E;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.SER_NUM;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.SET_MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.SET_PARAM_NULL;
import static com.canon.cusa.s21.batch.NFA.NFAB122001.NFAB122001Constant.SVC_MACH_MSTR_PK;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_BAT_ERR_LOGTMsg;
import business.db.SVC_BR_POST_XREF_TRKTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTR_TRKTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
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
 * Fin Branch Code Creation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/11/18   CITS            R.Kurahashi     Create          QC#57653-1
 * 2023/09/11   Hitachi         K.Watanabe      Update          QC#61310
 * </pre>
 */
public class NFAB122001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** System TimeStamp */
    private String sysTime = null;

    /** Error Message list */
    private List<String> errMsgList = new ArrayList<String>();

    /** SVC MACH MSTR PK list */
    private List<String> svcMachMstrPkList = new ArrayList<String>();

    /** Serial Number list */
    private List<String> serNumList = new ArrayList<String>();

    /** Error Massage */
    private String errMsg = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Total Count */
    private int totalCount;

    /** Normal Count */
    private int normalCount;

    /** Error Count */
    private int errorCount;

    /** Mail Template ID */
    private String mailTemplateId = null;

    /** Termination Code */
    private TERM_CD termCd = null;

    @Override
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSZM0392E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }
        // Sales date
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BATCH_ID);
        if (!hasValue(slsDt)) {
            throw new S21AbendException(NSZM0392E, new String[] {MSG_ITEM_SALES_DATE });
        }

        // Mail Template
        this.mailTemplateId = SET_MAIL_TEMPLATE_ID;

        // initialize
        this.sysTime = ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT);

        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    }

    @Override
    protected void mainRoutine() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String defFinBrCd = ZYPCodeDataUtil.getVarCharConstValue(NFAB1220_DEFAULT_FIN_BR, this.glblCmpyCd);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("none", SET_PARAM_NULL);
        // START 2023/09/11 K.Watanabe [QC#61310, ADD]
        List<String> svcMachMstrStsCdList = new ArrayList<String>();
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.CREATED);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.REMOVED);
        paramMap.put("svcMachMstrStsCdList", svcMachMstrStsCdList);
        paramMap.put("rtlWhCatgCd", RTL_WH_CATG.SHOWROOM);
        // END 2023/09/11 K.Watanabe [QC#61310, ADD]
        paramMap.put("slsDt", this.slsDt);
        paramMap.put("maxDt", "99991231");
        paramMap.put("defFinBrCd", defFinBrCd);
        paramMap.put("dummyZ", DUMMY_CODE);

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getDiffMachineData", paramMap, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                boolean updateFlg = false;
                boolean updateFinFlg = true;
                boolean insertFlg = false;
                String brCdTpDescTxt = null;

                if (!ZYPCommonFunc.hasValue(rs.getString("COA_BR_CD"))) {
                    updateFinFlg = false;
                }

                // update SVC_MACH_MSTR
                if (updateFinFlg) {
                    updateFlg = updateMachineInBranch(rs.getBigDecimal("SVC_MACH_MSTR_PK"), rs.getString("SER_NUM"), rs.getString("FIN_BR_CD"));
                }

                // set brCdTpDescTxt
                StringBuffer sb = new StringBuffer();
                sb.append(FIN_BR_CD);
                brCdTpDescTxt = sb.toString();

                // insert SVC_BR_POST_XREF_TRK
                if (updateFlg) {
                    insertFlg = updateResultRegistration(rs, ZYPConstant.FLG_ON_Y, brCdTpDescTxt);
                    if (insertFlg) {
                        commit();
                        normalCount++;
                    } else {
                        rollback();
                        errorProcess(rs.getBigDecimal("SVC_MACH_MSTR_PK").toString(), rs.getString("SER_NUM"));
                        commit();
                        errorCount++;
                    }
                } else {
                    addErrMsg(rs.getBigDecimal("SVC_MACH_MSTR_PK").toString(), rs.getString("SER_NUM"), MSG_ITEM_FINANCIAL_BRANCH, null, new String[] {});
                    updateResultRegistration(rs, ZYPConstant.FLG_OFF_N, brCdTpDescTxt);
                    // insert into SVC_BAT_ERR_LOG with error message
                    errorProcess(rs.getBigDecimal("SVC_MACH_MSTR_PK").toString(), rs.getString("SER_NUM"));
                    commit();
                    errorCount++;
                }
                totalCount++;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private boolean updateMachineInBranch(BigDecimal pk, String serNumber, String finBrCd) {
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();

        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.svcMachMstrPk, pk);

        inMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);
        if (inMsg == null) {
            addErrMsg(pk.toString(), serNumber, null, NZZM0003E, new String[] {"SVC_MACH_MSTR" });
            return false;
        }

        String oldFinBrCd = inMsg.finBrCd.getValue();
        setValue(inMsg.finBrCd, finBrCd);

        // update into SVC_MACH_MSTR
        S21FastTBLAccessor.update(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            addErrMsg(pk.toString(), serNumber, null, NSAM0470E, new String[] {"SVC_MACH_MSTR", SVC_MACH_MSTR_PK + "=" + pk });
            return false;
        }

        // insert into SVC_MACH_MSTR_TRK
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(createSvcMachMstrTrk(pk, FIN_BR_CD, oldFinBrCd, inMsg.finBrCd.getValue()))) {
            addErrMsg(pk.toString(), serNumber, null, NSAM0032E, new String[] {"SVC_MACH_MSTR_TRK" });
            return false;
        }

        return true;
    }

    private String createSvcMachMstrTrk(BigDecimal svcMachMstrPk, String updFld, String oldVal, String newVal) {
        SVC_MACH_MSTR_TRKTMsg tMsg = new SVC_MACH_MSTR_TRKTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.svcMachMstrTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MACH_MSTR_TRK_SQ));
        setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
        setValue(tMsg.trxRgtnDt, this.slsDt);
        setValue(tMsg.updFldTxt, updFld);
        setValue(tMsg.oldValTxt, oldVal);
        setValue(tMsg.newValTxt, newVal);
        setValue(tMsg.updUsrId, BATCH_ID);
        setValue(tMsg.noteExistFlg, ZYPConstant.FLG_OFF_N);
        EZDTBLAccessor.create(tMsg);
        return tMsg.getReturnCode();
    }

    private boolean updateResultRegistration(ResultSet rs, String updtRsltFlg, String brCdTpDescTxt) throws SQLException {
        SVC_BR_POST_XREF_TRKTMsg inMsg = new SVC_BR_POST_XREF_TRKTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.svcBrPostXrefTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_BR_POST_XREF_TRK_SQ));
        setValue(inMsg.svcBrPostXrefProcTs, this.sysTime);
        setValue(inMsg.serNum, rs.getString("SER_NUM"));
        setValue(inMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
        setValue(inMsg.ctyAddr, rs.getString("CTY_ADDR"));
        setValue(inMsg.postCd, rs.getString("POST_CD"));

        setValue(inMsg.brCdTpDescTxt, brCdTpDescTxt);

        String finBrCd = rs.getString("FIN_BR_CD");

        if (updtRsltFlg.equals(ZYPConstant.FLG_OFF_N)) {
            // in case of failed, set erro message for fin branch
            setValue(inMsg.procRsltFlg, updtRsltFlg);
            setValue(inMsg.batErrMsgTxt, MSG_ITEM_FINANCIAL_BRANCH);
        } else {
            setValue(inMsg.procRsltFlg, ZYPConstant.FLG_ON_Y);

            if (finBrCd.equals(ZYPCodeDataUtil.getVarCharConstValue(NFAB1220_DEFAULT_FIN_BR, this.glblCmpyCd))) {
                setValue(inMsg.batErrMsgTxt, MSG_ITEM_FINANCIAL_BRANCH_DEFAULT);
            }
        }
        S21FastTBLAccessor.insert(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            addErrMsg(rs.getBigDecimal("SVC_MACH_MSTR_PK").toString(), rs.getString("SER_NUM"), null, NSAM0469E, new String[] {"SVC_BR_POST_XREF_TRK", SER_NUM + "=" + rs.getString("SER_NUM") });
            return false;
        }
        return true;
    }

    private void errorProcess(String updateSvcMachMstrPk, String updateSerNum) {
        this.termCd = TERM_CD.WARNING_END;

        SVC_BAT_ERR_LOGTMsg inMsg = new SVC_BAT_ERR_LOGTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.svcBatErrLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_BAT_ERR_LOG_SQ));
        setValue(inMsg.bizAppId, BIZ_APP_ID);
        setValue(inMsg.svcBatErrLogTs, this.sysTime);
        setValue(inMsg.svcBatErrKeyNum_01, updateSvcMachMstrPk);
        setValue(inMsg.svcBatErrKeyNum_02, updateSerNum);
        setValue(inMsg.svcBatErrKeyNm_01, updateSvcMachMstrPk);
        setValue(inMsg.svcBatErrKeyNm_02, updateSerNum);
        setValue(inMsg.svcBatErrMsgTxt, this.errMsg);
        S21FastTBLAccessor.insert(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            addErrMsg(updateSvcMachMstrPk, updateSerNum, null, NSAM0469E, new String[] {"SVC_BAT_ERR_LOG", SVC_MACH_MSTR_PK + "=" + updateSvcMachMstrPk });
        }
    }

    private void addErrMsg(String updateSvcMachMstrPk, String updateSerNum, String errorMsg, String msgId, String... param) {
        if (hasValue(msgId)) {
            this.errMsg = S21MessageFunc.clspGetMessage(msgId, param);
        } else {
            this.errMsg = errorMsg;
        }
        this.errMsgList.add(this.errMsg);
        this.svcMachMstrPkList.add(updateSvcMachMstrPk);
        this.serNumList.add(updateSerNum);
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
        for (int i = 0; i < this.errMsgList.size(); i++) {
            msgBuf.append(this.errMsgList.get(i));
            msgBuf.append(ERR_MSG_CRLF);
            msgBuf.append(ERR_MSG_SPACE_7);
        }
        String errorMessage = msgBuf.toString();
        template.setTemplateParameter("message", errorMessage);

        msgBuf = new StringBuilder();
        for (int i = 0; i < this.svcMachMstrPkList.size(); i++) {
            msgBuf.append(ERR_MSG_SPACE_1);
            msgBuf.append(svcMachMstrPkList.get(i));
            msgBuf.append(ERR_MSG_SPACE_4);
            msgBuf.append(serNumList.get(i));
            msgBuf.append(ERR_MSG_CRLF);
        }
        errorMessage = msgBuf.toString();

        template.setTemplateParameter("ErrInfo", errorMessage);

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

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            // Send error mail
            postErrorMail();

            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFAB122001().executeBatch(NFAB122001.class.getSimpleName());
    }
}
