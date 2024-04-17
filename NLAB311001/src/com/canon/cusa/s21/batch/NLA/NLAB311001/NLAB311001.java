package com.canon.cusa.s21.batch.NLA.NLAB311001;

import static com.canon.cusa.s21.batch.NLA.NLAB311001.constant.NLAB311001Constant.*;

import java.util.List;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.DS_BIZ_PROC_LOGTMsgArray;
import business.parts.NLZC410001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC410001.NLZC410001;
import com.canon.cusa.s21.api.NLZ.NLZC410001.constant.NLZC410001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * NLAB311001 Send My Inventory to Click Batch.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 23/19/2015   Hitachi         J.Kim           Create          N/A
 * 04/28/2016   Hitachi         T.Iwamoto       Update          QC#7312
 * 07/07/2016   Hitachi         Y.Takeno        Update          QC#7632
 * 10/17/2016   Hitachi         N.Arai          Update          QC#15230
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16316
 * 2017/01/13   Hitachi         K.Kojima        Update          QC#17013
 * 2017/01/13   Hitachi         K.Kojima        Update          QC#16905
 * 2017/01/19   Hitachi         K.Kojima        Update          QC#16905
 * 2017/01/24   Hitachi         K.Kojima        Update          QC#16905
 * 2017/07/31   Hitachi         K.Kojima        Update          QC#20212
 * 2019/04/16   CITS            A.Kobayashi     Update          QC#31144
 * 2020/04/25   CITS            T.Wada          Update          QC#55791-1
 * </pre>
 */
public class NLAB311001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Current System Timestamp */
    private String currentSystemTs = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** process Date */
    private String processDate;

    // START 2017/01/13 K.Kojima [QC#16905,ADD]
    /** process Mode */
    private String processMode;

    /** DS_BIZ_PROC_LOG */
    private DS_BIZ_PROC_LOGTMsg dsBizProcLogTMsg = null;

    /** Current System Timestamp */
    private String currentSystemTimestamp = null;

    // END 2017/01/13 K.Kojima [QC#16905,ADD]

    // START 2017/07/31 K.Kojima [QC#20212,ADD]
    /** Split variable */
    private String splitVariable = null;
    // END 2017/07/31 K.Kojima [QC#20212,ADD]

    /** Total Normal Count */
    private int normalCount = 0;

    /** Total Error Count */
    private int errorCount = 0;

    /** Commit Number Count */
    private int commitNumber = 0;

    /** Error Messages */
    private String errorMessages = null;

    @Override
    protected void initRoutine() {

        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NLAM1118E, new String[] {MSG_TXT_GLBL_CMPY_CD });
        }

        // Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            throw new S21AbendException(NLAM1285E, new String[] {MSG_TXT_SALES_DATE });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COUNT_NUMBER) {
            this.commitNumber = MAX_COUNT_NUMBER;
        }

        // START 2016/10/17 N.Arai [QC#15230, MOD]
        // this.processDate = ZYPDateUtil.addDays(this.salesDate, -1);
        this.processDate = this.salesDate;
        // END 2016/10/17 N.Arai [QC#15230, MOD]

        // START 2017/01/13 K.Kojima [QC#16905,ADD]
        this.processMode = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(this.processMode) || !this.processMode.equals(MODE_DAILY)) {
            this.processMode = MODE_NIGHTLY;
        }
        // END 2017/01/13 K.Kojima [QC#16905,ADD]

        // START 2017/07/31 K.Kojima [QC#20212,ADD]
        this.splitVariable = getUserVariable2();
        // END 2017/07/31 K.Kojima [QC#20212,ADD]

        // START 2017/01/24 K.Kojima [QC#16905,DEL]
        // this.ssmLLClient =
        // S21SsmLowLevelCodingClient.getClient(this.getClass());
        // END 2017/01/24 K.Kojima [QC#16905,DEL]

    }

    @Override
    protected void mainRoutine() {

        this.currentSystemTs = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);

        // START 2017/01/13 K.Kojima [QC#16905,ADD]
        this.currentSystemTimestamp = ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);

        // START 2017/07/31 K.Kojima [QC#20212,MOD]
        // getDsBizProcLog();
        if (this.processMode.equals(MODE_DAILY) || !ZYPCommonFunc.hasValue(this.splitVariable)) {
            getDsBizProcLog();

            // QC#31144 inform the time for processing data from last task (this message can be changed)
            S21InfoLogOutput.println("TARGET DATA : " + this.dsBizProcLogTMsg.dsBizLastUpdTs.getValue());

        // QC#55791-1 Add Start
        } else if (this.processMode.equals(MODE_NIGHTLY)){
            getDsBizProcLog4Nightly();
        }
        // QC#55791-1 Add End
        // END 2017/07/31 K.Kojima [QC#20212,MOD]
        // END 2017/01/13 K.Kojima [QC#16905,ADD]

        // START 2017/01/13 K.Kojima [QC#16905,MOD]
        // callWebService();
        if (this.processMode.equals(MODE_DAILY)) {
            callAPIForDaily();
        } else {
            // START 2017/01/24 K.Kojima [QC#16905,MOD]
            // callWebService();
            callAPIForNightly();
            // END 2017/01/24 K.Kojima [QC#16905,MOD]
        }
        // END 2017/01/13 K.Kojima [QC#16905,MOD]

        // START 2017/07/31 K.Kojima [QC#20212,ADD]
        if (this.processMode.equals(MODE_DAILY) || !ZYPCommonFunc.hasValue(this.splitVariable)) {
            // END 2017/07/31 K.Kojima [QC#20212,ADD]
            // START 2017/01/19 K.Kojima [QC#16905,ADD]
            if (ZYPCommonFunc.hasValue(dsBizProcLogTMsg.dsBizProcLogPk)) {
                updateDsBizProcLog();
            } else {
                insertDsBizProcLog();
            }
            // END 2017/01/19 K.Kojima [QC#16905,ADD]
            // START 2017/07/31 K.Kojima [QC#20212,ADD]

            // QC#31144 inform the time to finish and next time processing (this message can be changed)
            S21InfoLogOutput.println("NEXT TIME TARGET DATA : " + this.currentSystemTimestamp);

            // END 2017/07/31 K.Kojima [QC#20212,ADD]
        // QC#55791-1 Add Start
        } else if (this.processMode.equals(MODE_NIGHTLY)){
            if (ZYPCommonFunc.hasValue(dsBizProcLogTMsg.dsBizProcLogPk)) {
                updateDsBizProcLog4Nightly();
            } else {
                insertDsBizProcLog4Nightly();
            }
        }
        // QC#55791-1 Add End

        if (errorMessages == null) {
            // START 2017/01/13 K.Kojima [QC#16905,MOD]
            // updateTechInvtyAgingWrk();
            // START 2017/01/24 K.Kojima [QC#16905,DEL]
            // if (this.processMode.equals(MODE_NIGHTLY)) {
            // updateTechInvtyAgingWrk();
            // }
            // END 2017/01/24 K.Kojima [QC#16905,DEL]
            // END 2017/01/13 K.Kojima [QC#16905,MOD]
        } else {
            // Send mail if error.
            sendErrorMail();
        }
    }

    private void sendErrorMail() {

        // 1. Get From Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);

        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"FROM mail-address.", (MAIL_GROUP_ID_FROM) });
        }

        // 2. Get To Address
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // 3. Get Mail Template.
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_01);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NSAM0069E, new String[] {"Mailtemplate", MAIL_TEMPLATE_ID_01 });
        }

        // 4. Create message for Body
        S21MailAddress fromAddress = addrFromList.get(0);
        S21Mail mail = new S21Mail(glblCmpyCd);

        // 5. Create Subject and Body
        mail.setFromAddress(fromAddress);
        mail.setToAddressList(addrToList);
        template.setTemplateParameter(MAIL_ITEM_BATCH_ID, BATCH_PROGRAM_ID);
        template.setTemplateParameter(MAIL_ITEM_ERR_DATE, this.currentSystemTs);
        template.setTemplateParameter(MAIL_ITEM_ERR_MSG, this.errorMessages.toString());

        // 6. Call Mail API
        mail.setMailTemplate(template);
        mail.setSubject(template.getSubject(), MAIL_CHARSET);
        mail.postMail();
    }

    /**
     * set error info
     * @param msgId
     * @param apiBizId
     */
    private String setErrorInfo(String msgId, String[] params) {
        S21InfoLogOutput.println(msgId, params);
        return S21MessageFunc.clspGetMessage(msgId, params);
    }

    @Override
    protected void termRoutine() {
        setRecordCount(normalCount, errorCount, (normalCount + errorCount));
        setTermState(termCd);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new NLAB311001().executeBatch(NLAB311001.class.getSimpleName());
    }

    // START 2017/01/13 K.Kojima [QC#16905,ADD]
    /**
     * getDsBizProcLog
     */
    private void getDsBizProcLog() {
        DS_BIZ_PROC_LOGTMsg inMsg = new DS_BIZ_PROC_LOGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("procPgmId01", PROC_PGM_ID);
        DS_BIZ_PROC_LOGTMsgArray tMsgAry = (DS_BIZ_PROC_LOGTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgAry == null || tMsgAry.getValidCount() == 0) {
            this.dsBizProcLogTMsg = new DS_BIZ_PROC_LOGTMsg();
            ZYPEZDItemValueSetter.setValue(this.dsBizProcLogTMsg.dsBizLastUpdTs, DS_BIZ_LAST_UPD_TS_DEFAULT);
        } else {
            this.dsBizProcLogTMsg = (DS_BIZ_PROC_LOGTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsgAry.no(0));
        }
    }

    /**
     * insertDsBizProcLog
     */
    private void insertDsBizProcLog() {
        ZYPEZDItemValueSetter.setValue(dsBizProcLogTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsBizProcLogTMsg.dsBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BIZ_PROC_LOG_SQ));
        ZYPEZDItemValueSetter.setValue(dsBizProcLogTMsg.procPgmId, PROC_PGM_ID);
        ZYPEZDItemValueSetter.setValue(dsBizProcLogTMsg.dsBizProcDt, this.processDate);
        ZYPEZDItemValueSetter.setValue(dsBizProcLogTMsg.dsBizProcFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(dsBizProcLogTMsg.dsBizLastProcTs, this.currentSystemTimestamp);
        ZYPEZDItemValueSetter.setValue(dsBizProcLogTMsg.dsBizLastUpdTs, this.currentSystemTimestamp);
        S21FastTBLAccessor.insert(dsBizProcLogTMsg);
    }

    /**
     * updateDsBizProcLog
     */
    private void updateDsBizProcLog() {
        ZYPEZDItemValueSetter.setValue(dsBizProcLogTMsg.dsBizProcDt, this.processDate);
        ZYPEZDItemValueSetter.setValue(dsBizProcLogTMsg.dsBizLastProcTs, this.currentSystemTimestamp);
        ZYPEZDItemValueSetter.setValue(dsBizProcLogTMsg.dsBizLastUpdTs, this.currentSystemTimestamp);
        S21FastTBLAccessor.update(dsBizProcLogTMsg);
    }

    /**
     * callAPIForDaily
     */
    private void callAPIForDaily() {
        NLZC410001PMsg param = new NLZC410001PMsg();
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.procDt, this.processDate);
        ZYPEZDItemValueSetter.setValue(param.xxModeCd, NLZC410001Constant.MODE_DAILIY);
        ZYPEZDItemValueSetter.setValue(param.xxUpdTs, this.dsBizProcLogTMsg.dsBizLastUpdTs);
        new NLZC410001().execute(param, ONBATCH_TYPE.BATCH);
        this.normalCount = param.xxTotCnt.getValueInt();
        this.errorCount = param.xxTotCnt_E.getValueInt();
        if (S21ApiUtil.isXxMsgId(param)) {
            String newLine = System.getProperty("line.separator");
            StringBuilder msgBuf = new StringBuilder();
            for (int i = 0; i < param.xxMsgIdList.getValidCount(); i++) {
                if (i != 0) {
                    msgBuf.append(newLine);
                }
                msgBuf.append(setErrorInfo(param.xxMsgIdList.no(i).xxMsgId.getValue(), null));
            }
            this.errorMessages = msgBuf.toString();
        }
    }

    // END 2017/01/13 K.Kojima [QC#16905,ADD]

    // START 2017/01/24 K.Kojima [QC#16905,ADD]

    /**
     * callAPIForNightly
     */
    private void callAPIForNightly() {
        NLZC410001PMsg param = new NLZC410001PMsg();
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.procDt, this.processDate);
        ZYPEZDItemValueSetter.setValue(param.xxModeCd, NLZC410001Constant.MODE_NIGHTLY);
        // START 2017/07/31 K.Kojima [QC#20212,ADD]
        ZYPEZDItemValueSetter.setValue(param.xxModeCd_NT, getNightySplitMode());
        // END 2017/07/31 K.Kojima [QC#20212,ADD]
        // QC#55791 Add Start
        ZYPEZDItemValueSetter.setValue(param.xxUpdTs, this.dsBizProcLogTMsg.dsBizLastUpdTs);
        // QC#55791 Add End
        new NLZC410001().execute(param, ONBATCH_TYPE.BATCH);
        this.normalCount = param.xxTotCnt.getValueInt();
        this.errorCount = param.xxTotCnt_E.getValueInt();
        if (S21ApiUtil.isXxMsgId(param)) {
            String newLine = System.getProperty("line.separator");
            StringBuilder msgBuf = new StringBuilder();
            for (int i = 0; i < param.xxMsgIdList.getValidCount(); i++) {
                if (i != 0) {
                    msgBuf.append(newLine);
                }
                msgBuf.append(setErrorInfo(param.xxMsgIdList.no(i).xxMsgId.getValue(), null));
            }
            this.errorMessages = msgBuf.toString();
        }
    } // END 2017/01/24 K.Kojima [QC#16905,ADD]

    // START 2017/07/31 K.Kojima [QC#20212,ADD]
    private String getNightySplitMode() {
        if (ZYPCommonFunc.hasValue(this.splitVariable)) {
            return this.splitVariable;
        } else {
            return NLZC410001Constant.NIGHTY_SPLIT_MODE_OTHER;
        }
    }
    // END 2017/07/31 K.Kojima [QC#20212,ADD]

    // QC#55791-1 Add Start
    /**
     * getDsBizProcLog (ForNightly)
     */
    private void getDsBizProcLog4Nightly() {
        DS_BIZ_PROC_LOGTMsg inMsg = new DS_BIZ_PROC_LOGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("procPgmId01", PROC_PGM_ID + MODE_NIGHTLY + this.splitVariable);
        DS_BIZ_PROC_LOGTMsgArray tMsgAry = (DS_BIZ_PROC_LOGTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgAry == null || tMsgAry.getValidCount() == 0) {
            this.dsBizProcLogTMsg = new DS_BIZ_PROC_LOGTMsg();
            String dsBizLastUpdTs = ZYPDateUtil.addDays(ZYPDateUtil.getCurrentSystemTime("yyyyMMdd"), -1) + DEF_TMSTMP ;
            ZYPEZDItemValueSetter.setValue(this.dsBizProcLogTMsg.dsBizLastUpdTs, dsBizLastUpdTs);
        } else {
            this.dsBizProcLogTMsg = (DS_BIZ_PROC_LOGTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsgAry.no(0));
        }
    }
    /**
     * insertDsBizProcLog(ForNightly)
     */
    private void insertDsBizProcLog4Nightly() {
        ZYPEZDItemValueSetter.setValue(dsBizProcLogTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsBizProcLogTMsg.dsBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BIZ_PROC_LOG_SQ));
        ZYPEZDItemValueSetter.setValue(dsBizProcLogTMsg.procPgmId, PROC_PGM_ID + MODE_NIGHTLY + this.splitVariable);
        ZYPEZDItemValueSetter.setValue(dsBizProcLogTMsg.dsBizProcDt, this.processDate);
        ZYPEZDItemValueSetter.setValue(dsBizProcLogTMsg.dsBizProcFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(dsBizProcLogTMsg.dsBizLastProcTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));
        ZYPEZDItemValueSetter.setValue(dsBizProcLogTMsg.dsBizLastUpdTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));
        S21FastTBLAccessor.insert(dsBizProcLogTMsg);
    }

    /**
     * updateDsBizProcLog(ForNightly)
     */
    private void updateDsBizProcLog4Nightly() {
        ZYPEZDItemValueSetter.setValue(dsBizProcLogTMsg.dsBizProcDt, this.processDate);
        ZYPEZDItemValueSetter.setValue(dsBizProcLogTMsg.dsBizLastProcTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));
        ZYPEZDItemValueSetter.setValue(dsBizProcLogTMsg.dsBizLastUpdTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));
        S21FastTBLAccessor.update(dsBizProcLogTMsg);
    }
    // QC#55791-1 Add End

}
