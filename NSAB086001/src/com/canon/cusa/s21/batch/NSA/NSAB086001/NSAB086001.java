package com.canon.cusa.s21.batch.NSA.NSAB086001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import parts.dbcommon.EZDTBLAccessor;

import business.db.MTR_READ_NTFYTMsg;

import com.canon.cusa.s21.batch.NSA.NSAB086001.constant.NSAB086001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
/**
 * <pre>
 * Meter Reading Notification for Email
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/11/10   Hitachi         Y.Nagasawa      Create          QC#61756
 */
public class NSAB086001 extends S21BatchMain {

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSAB0860";

    /** Batch ID */
    public static final String BATCH_ID = BIZ_APP_ID + "01";

    /** SsmLLClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** Time stamp */
    private String systemTimeStamp = null;

    /** Total Normal Count */
    private int totalNormCount = 0;

    /** TotalErrCount */
    private int totalErrCount = 0;
    
    /** Commit Count */
    private int commitCount = 0;

    /** TermCd */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Setup First Line Address */
    private String setupFirstLineAddr = null;

    /** Setup Second Line Address */
    private String setupScdLineAddr = null;

    /** Setup Third Line Address */
    private String setupThirdLineAddr = null;

    /** Setup Forth Line Address */
    private String setupFrthLineAddr = null;

    /** Setup City Address */
    private String setupCtyAddr = null;

    /** Setup State Code */
    private String setupStCd = null;

    /** Setup Post Code */
    private String setupPostCd = null;

    /** Setup Contact Email Address */
    private String setupCtacEmlAddr = null;

    /** Setup First Name */
    private String setupFirstNm = null;

    /** Setup Middle Name */
    private String setupMidNm = null;

    /** Setup Last Name */
    private String setupLastNm = null;

    /** Setup Telephone Number */
    private String setupTelNum = null;

    @Override
    protected void initRoutine() {
        // Initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSAB086001Constant.ZZM9000E, new String[] {"Global Company Code" });
        }
        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd, BATCH_ID);
        if (!hasValue(this.salesDate)) {
            throw new S21AbendException(NSAB086001Constant.ZZM9000E, new String[] {"Sales Date" });
        }
        // Get Commit Number
        this.commitCount = getCommitCount();
        if (this.commitCount <= 0 || this.commitCount >= NSAB086001Constant.MAX_COMMIT_COUNT) {
            this.commitCount = NSAB086001Constant.MAX_COMMIT_COUNT;
        }
        // Get Time Stamp
        this.systemTimeStamp = ZYPDateUtil.getCurrentSystemTime(NSAB086001Constant.TIME_STAMP_FORMAT);

    }

    @Override
    protected void mainRoutine() {
        doProcess();
        commit();
    }

    private void doProcess(){
        getSetupValue();
        createNotificationMail();
   }

    private void getSetupValue(){
        this.setupFirstLineAddr = ZYPCodeDataUtil.getVarCharConstValue("NSAB0860_FIRST_LINE_ADDR", this.glblCmpyCd);
        this.setupScdLineAddr = ZYPCodeDataUtil.getVarCharConstValue("NSAB0860_SCD_LINE_ADDR", this.glblCmpyCd);
        this.setupThirdLineAddr = ZYPCodeDataUtil.getVarCharConstValue("NSAB0860_THIRD_LINE_ADDR", this.glblCmpyCd);
        this.setupFrthLineAddr = ZYPCodeDataUtil.getVarCharConstValue("NSAB0860_FRTH_LINE_ADDR", this.glblCmpyCd);
        this.setupCtyAddr = ZYPCodeDataUtil.getVarCharConstValue("NSAB0860_CTY_ADDR", this.glblCmpyCd);
        this.setupStCd = ZYPCodeDataUtil.getVarCharConstValue("NSAB0860_ST_CD", this.glblCmpyCd);
        this.setupPostCd = ZYPCodeDataUtil.getVarCharConstValue("NSAB0860_POST_CD", this.glblCmpyCd);
        this.setupCtacEmlAddr = ZYPCodeDataUtil.getVarCharConstValue("NSAB0860_CTAC_EML_ADDR", this.glblCmpyCd);
        this.setupFirstNm = ZYPCodeDataUtil.getVarCharConstValue("NSAB0860_FIRST_NM", this.glblCmpyCd);
        this.setupMidNm = ZYPCodeDataUtil.getVarCharConstValue("NSAB0860_MID_NM", this.glblCmpyCd);
        this.setupLastNm = ZYPCodeDataUtil.getVarCharConstValue("NSAB0860_LAST_NM", this.glblCmpyCd);
        this.setupTelNum = ZYPCodeDataUtil.getVarCharConstValue("NSAB0860_TEL_NUM", this.glblCmpyCd);
    }

    private void createNotificationMail() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        NotificationMailHeaderData notificationMailHeaderData = null;
        List<NotificationMailDetailData> notificationMailDetailList = new ArrayList<NotificationMailDetailData>();
        boolean firstFlg = true;
        try {
            stmt = getMtrReadNtfy();
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (firstFlg) {
                    notificationMailHeaderData = createNotificationMailHeaderData(rs);
                    notificationMailDetailList.add(createNotificationMailDetailData(rs));
                    notificationMailHeaderData.setDetailList(notificationMailDetailList);
                    firstFlg = false;
                    continue;
                }
                if (relationCheck(notificationMailHeaderData, rs)) {
                    notificationMailDetailList.add(createNotificationMailDetailData(rs));
                    notificationMailHeaderData.setDetailList(notificationMailDetailList);
                } else {
                    mailExecution(notificationMailHeaderData);
                    notificationMailHeaderData = null;
                    notificationMailDetailList.clear();
                    notificationMailHeaderData = createNotificationMailHeaderData(rs);
                    notificationMailDetailList.add(createNotificationMailDetailData(rs));
                    notificationMailHeaderData.setDetailList(notificationMailDetailList);
                }
            }
            if (notificationMailHeaderData != null) {
                mailExecution(notificationMailHeaderData);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private PreparedStatement getMtrReadNtfy() throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("mtrNtfyStsCd", NSAB086001Constant.MTR_NTFY_STS_WAITING_FOR_SEND);
        paramMap.put("manSendFlg", ZYPConstant.FLG_ON_Y);
        paramMap.put("dateFormat", NSAB086001Constant.SQL_DATE_FORMAT);
        paramMap.put("salesDate", this.systemTimeStamp.substring(0, 14));
        return this.ssmLLClient.createPreparedStatement("getTargetData", paramMap, getExecParam());
    }

    /**
     * @param ResultSet rs
     * @return NotificationMailHeaderData
     */
    private NotificationMailHeaderData createNotificationMailHeaderData(ResultSet rs) throws SQLException {
        NotificationMailHeaderData header = new NotificationMailHeaderData();
        header.setMtrReadNtfyPk(rs.getBigDecimal("MTR_READ_NTFY_PK"));
        header.setSlsDt(ZYPDateUtil.formatEzd8ToSysDisp(this.salesDate));
        header.setSysDt(ZYPDateUtil.formatEzd8ToSysDisp(this.salesDate));
        header.setDsAcctNm(rs.getString("DS_ACCT_NM"));
        header.setCtacPsnFirstNm(rs.getString("CTAC_PSN_FIRST_NM"));
        header.setCtacPsnMidNm(rs.getString("CTAC_PSN_MID_NM"));
        header.setCtacPsnLastNm(rs.getString("CTAC_PSN_LAST_NM"));
        header.setCtacPsnEmlAddr(rs.getString("CTAC_PSN_EML_ADDR"));
        header.setCtacPsnTelNum(rs.getString("CTAC_PSN_TEL_NUM"));
        header.setCtacPsnFaxNum(rs.getString("CTAC_PSN_FAX_NUM"));
        header.setSetupFirstLineAddr(this.setupFirstLineAddr);
        header.setSetupScdLineAddr(this.setupScdLineAddr);
        header.setSetupThirdLineAddr(this.setupThirdLineAddr);
        header.setSetupFrthLineAddr(this.setupFrthLineAddr);
        header.setSetupCtyAddr(this.setupCtyAddr);
        header.setSetupStCd(this.setupStCd);
        header.setSetupPostCd(this.setupPostCd);
        header.setSetupCtacEmlAddr(this.setupCtacEmlAddr);
        header.setSetupFirstNm(this.setupFirstNm);
        header.setSetupMidNm(this.setupMidNm);
        header.setSetupLastNm(this.setupLastNm);
        header.setSetupTelNum(this.setupTelNum);
        header.setDetailList(new ArrayList<NotificationMailDetailData>());
        return header;
    }

    /**
     * @param ResultSet rs
     * @return NotificationMailDetailData
     */
    private NotificationMailDetailData createNotificationMailDetailData(ResultSet rs) throws SQLException {
        NotificationMailDetailData detail = new NotificationMailDetailData();
        detail.setDsContrNum(rs.getString("DS_CONTR_NUM"));
        detail.setSerNum(rs.getString("SER_NUM"));
        detail.setT_MdlNm(rs.getString("T_MDL_NM"));
        detail.setMtrLbDescTxt(rs.getString("MTR_LB_DESC_TXT"));
        detail.setReadMtrCnt(rs.getBigDecimal("READ_MTR_CNT"));
        detail.setBllgSchdThruDt(ZYPDateUtil.formatEzd8ToSysDisp(rs.getString("BLLG_SCHD_THRU_DT")));
        return detail;
    }

    /**
     * @param NotificationMailHeaderData notificationMailHeaderData
     */
    private void mailExecution(NotificationMailHeaderData notificationMailHeaderData) {
        Object[] errorInfo = postNotificationMail(notificationMailHeaderData);
        if (errorInfo != null) {
            updateMtrReadNtfy(notificationMailHeaderData, errorInfo);
        } else {
            updateMtrReadNtfy(notificationMailHeaderData, null);
        }
    }

    /**
     * @param NotificationMailHeaderData notificationMailHeaderData
     * @return Object[]
     */
    private Object[] postNotificationMail(NotificationMailHeaderData notificationMailHeaderData) {
        // From Address
        if (!ZYPCommonFunc.hasValue(notificationMailHeaderData.getSetupCtacEmlAddr())) {
            return new Object[] { NSAB086001Constant.ZZM9000E, new String[] { "Setup Email Address"} };
        }
        if (!checkEmailFormat(notificationMailHeaderData.getSetupCtacEmlAddr())) {
            return new Object[] { NSAB086001Constant.ZZZM9026E, new String[] { "Setup Email Address"} };
        }
        S21MailAddress fromAddr = new S21MailAddress(this.glblCmpyCd, notificationMailHeaderData.getSetupCtacEmlAddr());

        // To Address
        if (!ZYPCommonFunc.hasValue(notificationMailHeaderData.getCtacPsnEmlAddr())) {
            return new Object[] {  NSAB086001Constant.ZZM9000E, new String[] { "Email Address"} };
        }
        if (!checkEmailFormat(notificationMailHeaderData.getCtacPsnEmlAddr())) {
            return new Object[] { NSAB086001Constant.ZZZM9026E, new String[] { "Email Address"} };
        }
        S21MailAddress toAddr = new S21MailAddress(this.glblCmpyCd, notificationMailHeaderData.getCtacPsnEmlAddr());

        // Get Mail Template
        S21MailTemplate mailTemplate;
        mailTemplate = new S21MailTemplate(this.glblCmpyCd, NSAB086001Constant.MAIL_TEMPLATE_ID);
        if (mailTemplate.getBody().equals("")) {
            return new Object[] { NSAB086001Constant.NSAM0069E, new String[] { "Mail Template", NSAB086001Constant.MAIL_TEMPLATE_ID } };
        }
        mailTemplate.setTemplateParameter(notificationMailHeaderData);

        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(fromAddr);
        mail.setToAddress(toAddr);
        mail.setMailTemplate(mailTemplate);
        mail.postMail();

        return null;
    }

    /**
     * @param NotificationMailHeaderData notificationMailHeaderData
     * @param ResultSet rs
     * @return boolean
     */
    private boolean relationCheck(NotificationMailHeaderData notificationMailHeaderData, ResultSet rs) throws SQLException {
        if (notificationMailHeaderData == null) {
            return false;
        }
        if (notificationMailHeaderData.getMtrReadNtfyPk().equals(rs.getBigDecimal("MTR_READ_NTFY_PK"))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param String emlAddr
     * @return boolean
     */
    private static boolean checkEmailFormat(String emlAddr) {
        boolean ret = true;
        try {
            InternetAddress addr = new InternetAddress(emlAddr);
            addr.validate();
        } catch (AddressException e) {
            ret = false;
        }
        return ret;
    }

    /**
     * @param NotificationMailHeaderData notificationMailHeaderData
     * @param Object[] errorInfo
     */
    private void updateMtrReadNtfy(NotificationMailHeaderData notificationMailHeaderData, Object[] errorInfo) {
        MTR_READ_NTFYTMsg updateTMsg = new MTR_READ_NTFYTMsg();
        ZYPEZDItemValueSetter.setValue(updateTMsg.mtrReadNtfyPk, notificationMailHeaderData.getMtrReadNtfyPk());
        ZYPEZDItemValueSetter.setValue(updateTMsg.glblCmpyCd, this.glblCmpyCd);
        updateTMsg =(MTR_READ_NTFYTMsg)  EZDTBLAccessor.findByKeyForUpdate(updateTMsg);
        if (errorInfo != null) {
            ZYPEZDItemValueSetter.setValue(updateTMsg.mtrNtfyStsCd, NSAB086001Constant.MTR_NTFY_STS_ERROR);
            ZYPEZDItemValueSetter.setValue(updateTMsg.mtrNtfyErrMsgTxt, getErrorMsg(errorInfo));
        } else {
            ZYPEZDItemValueSetter.setValue(updateTMsg.mtrNtfyStsCd, NSAB086001Constant.MTR_NTFY_STS_COMPLETED);
            ZYPEZDItemValueSetter.setValue(updateTMsg.mtrReadNtfySendTs, this.systemTimeStamp);
        }
        ZYPEZDItemValueSetter.setValue(updateTMsg.manSendFlg, ZYPConstant.FLG_OFF_N);
        EZDTBLAccessor.update(updateTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
            rollback();
            this.totalErrCount++;
            throw new S21AbendException(NSAB086001Constant.NSAM0032E, new String[] { "MTR_READ_NTFY", updateTMsg.getReturnCode() });
        } else {
            if (NSAB086001Constant.MTR_NTFY_STS_ERROR.equals(updateTMsg.mtrNtfyStsCd.getValue())) {
                this.totalErrCount++;
            } else {
                this.totalNormCount++;
            }
        }
    }

    /**
     * @param Object[] errorInfo
     * @return String
     */
    private String getErrorMsg(Object[] errorInfo) {
        String msgId = (String) errorInfo[0];
        String[] msgPrm  = (String[]) errorInfo[1];
        String msgTxt = S21MessageFunc.clspGetMessage(msgId, msgPrm);
        if (msgTxt.length() > 400) {
            msgTxt = msgTxt.substring(0, 400);
        }
        return msgTxt;
    }

    private S21SsmExecutionParameter getExecParam() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(NSAB086001Constant.FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execParam;
    }

    @Override
    protected void termRoutine() {
        int totalCnt = totalNormCount + totalErrCount;
        setTermState(termCd, totalNormCount, totalErrCount, totalCnt);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new NSAB086001().executeBatch(NSAB086001.class.getSimpleName());
    }

}
