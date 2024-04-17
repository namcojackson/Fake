/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB024001;

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
import business.db.DS_MSGTMsg;
import business.db.DS_MTR_INTFCTMsg;
import business.db.IWR_MLY_MTR_READ_TRKTMsg;
import business.db.IWR_MTR_READ_DTL_WRKTMsg;
import business.parts.NSZC051001PMsg;
import business.parts.NSZC051001_rsltPrmListPMsg;

import com.canon.cusa.s21.api.NSZ.NSZC051001.NSZC051001;
import com.canon.cusa.s21.common.NSX.NSXC001001.MtrWinInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetMtrWinFromThruDt;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IWR_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Create Meter Reading From IWR
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/24   Hitachi         T.Aoyagi        Create          N/A
 * 2016/07/07   Hitachi         T.Aoyagi        Update          QC#11183
 * 2017/06/06   Hitachi         K.Kitachi       Update          QC#18342
 * 2017/08/08   Hitachi         A.Kohinata      Update          QC#18799
 * 2017/11/28   Hitachi         T.Tomita        Update          QC#22771
 * 2018/03/13   Hitachi         K.Kojima        Update          QC#23112
 * </pre>
 */
public class NSAB024001 extends S21BatchMain {

    /** [@] is not registered.(@) */
    private static final String NSAM0069E = "NSAM0069E";

    /** Failed to update "@". */
    private static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    private static final String NSAM0032E = "NSAM0032E";

    /** [@] field is mandatory. */
    private static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    private static final String ZZZM9006E = "ZZZM9006E";

    /** message Item: GlobalCompanyCode */
    private static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** Business Application ID */
    private static final String BIZ_APP_ID = "NSAB0240";

    /** mail group id for From */
    private static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id for To */
    private static final String MAIL_GROUP_ID_TO = "NSAB0240";

    /** template ID */
    private static final String MAIL_TEMPLATE_ID = BIZ_APP_ID + "M001";

    /** template parameter key : batch id */
    private static final String MAIL_TEMPLATE_KEY_ID = "batchId";

    /** template parameter key : err date */
    private static final String MAIL_TEMPLATE_KEY_DATE = "errDate";

    /** template parameter key : message */
    private static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";

    /** Date Time Pattern For Mail */
    private static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";

    // START 2018/03/13 K.Kojima [QC#23112,DEL]
    // /** Date 28 */
    // private static final int DATE_28 = 28;
    // END 2018/03/13 K.Kojima [QC#23112,DEL]

    /** Message Kind : Error */
    private static final String MSG_KIND_ERR = "E";

    /** Message Kind : Warning */
    private static final String MSG_KIND_WARN = "W";

    // START 2017/06/06 K.Kitachi [QC#18342, ADD]
    /** Format System Time stamp **/
    private static final String FORMAT_SYS_TS = "yyyyMMddHHmmssSSS";

    /** Column name : DS_MTR_INTFC_PK */
    private static final String DS_MTR_INTFC_PK = "DS_MTR_INTFC_PK";
    // END 2017/06/06 K.Kitachi [QC#18342, ADD]

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Sales Date */
    private String salesDate;

    /** Meter Before Thru Days */
    private BigDecimal mtrBefThruDays;

    // START 2018/03/13 K.Kojima [QC#23112,DEL]
    // /** No Contract Meter Read Interval Months */
    // private BigDecimal noContrMtrReadIntvlMths;
    // END 2018/03/13 K.Kojima [QC#23112,DEL]

    /** IWR Meter Read Commit Count */
    private BigDecimal iwrMtrReadComitCnt;

    /** Warning Mail Send Flag */
    private String warnMlSendFlg;

    /** Total Count */
    private int totalCount;

    /** error Count */
    private int errorCount;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatClient = null;

    /** From Address List */
    List<S21MailAddress> fromAddrList = null;

    /** To Address List */
    List<S21MailAddress> addrToList = null;

    /** Mail Template */
    S21MailTemplate template = null;

    /** Error Message List */
    private List<String> errMsgList = new ArrayList<String>();

    // START 2017/06/06 K.Kitachi [QC#18342, ADD]
    /** Process Time Stamp */
    private String procTs = null;
    // END 2017/06/06 K.Kitachi [QC#18342, ADD]

    @Override
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd, NSAB024001.class.getSimpleName());

        // START 2017/06/06 K.Kitachi [QC#18342, ADD]
        this.procTs = ZYPDateUtil.getCurrentSystemTime(FORMAT_SYS_TS);
        // END 2017/06/06 K.Kitachi [QC#18342, ADD]

        // Get Meter Before Thru Days
        this.mtrBefThruDays = ZYPCodeDataUtil.getNumConstValue("MTR_BEF_THRU_DAYS", this.glblCmpyCd);
        if (!hasValue(this.mtrBefThruDays)) {
            throw new S21AbendException(ZZZM9006E, new String[]{"NUM_CONST:MTR_BEF_THRU_DAYS"});
        }

        // START 2018/03/13 K.Kojima [QC#23112,DEL]
        // // Get No Contract Meter Read Interval Months
        // this.noContrMtrReadIntvlMths = ZYPCodeDataUtil.getNumConstValue("NO_CONTR_MTR_READ_INTVL_MTHS", this.glblCmpyCd);
        // if (!hasValue(this.noContrMtrReadIntvlMths)) {
        //     throw new S21AbendException(ZZZM9006E, new String[]{"NUM_CONST:NO_CONTR_MTR_READ_INTVL_MTHS"});
        // }
        // END 2018/03/13 K.Kojima [QC#23112,DEL]

        // Get IWR Meter Read Commit Count
        this.iwrMtrReadComitCnt = ZYPCodeDataUtil.getNumConstValue("IWR_MTR_READ_COMIT_CNT", this.glblCmpyCd);
        if (!hasValue(this.iwrMtrReadComitCnt)) {
            throw new S21AbendException(ZZZM9006E, new String[]{"NUM_CONST:IWR_MTR_READ_COMIT_CNT"});
        }

        // Get Warning Mail Send Flag
        this.warnMlSendFlg = ZYPCodeDataUtil.getVarCharConstValue("NSAB0240_WARN_ML_SEND_FLG", this.glblCmpyCd);
        if (!hasValue(this.warnMlSendFlg)) {
            throw new S21AbendException(ZZZM9006E, new String[]{"VAR_CHAR_CONST:NSAB0240_WARN_ML_SEND_FLG"});
        }

        // Get From Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        this.fromAddrList = groupFrom.getMailAddress();
        if (fromAddrList == null || fromAddrList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"From mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get To Address
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        this.addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get Mail Template
        this.template = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!hasValue(template.getBody())) {
            throw new S21AbendException(NSAM0069E, new String[] {"Mailtemplate", MAIL_TEMPLATE_ID });
        }

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.totalCount = 0;
        this.errorCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatClient = S21SsmBatchClient.getClient(getClass());
    }

    @Override
    protected void mainRoutine() {

        createMeterReading();
        sendMail();
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.totalCount - this.errorCount, this.errorCount, this.totalCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB024001().executeBatch(NSAB024001.class.getSimpleName());
    }

    private void createMeterReading() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.iwrMtrReadComitCnt.intValue());
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> ssmParam = getSsmParam();
        // START 2017/06/06 K.Kitachi [QC#18342, MOD]
//        int loopCnt = 0;

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getTargetKeyFromDtlWrk", ssmParam, execParam);
            rs = stmt.executeQuery();

            BigDecimal prevMachMstrPk = null;

            while (rs.next()) {
                String iwrProcSts = IWR_PROC_STS.SKIP_RECORD;

                BigDecimal svcMachMstrPk = rs.getBigDecimal("SVC_MACH_MSTR_PK");
                String mtrRcvDtTmTs = rs.getString("MTR_RCV_DT_TM_TS");

                List<Map<String, Object>> iwrMtrReadList = getIwrMtrReadDtlWrk(svcMachMstrPk, mtrRcvDtTmTs);

                if (prevMachMstrPk == null || prevMachMstrPk.compareTo(svcMachMstrPk) != 0) {
                    prevMachMstrPk = svcMachMstrPk;

                    List<DS_MTR_INTFCTMsg> dsMtrIntfcTMsgList = new ArrayList<DS_MTR_INTFCTMsg>();
                    List<DS_MSGTMsg> dsMsgTMsgList = new ArrayList<DS_MSGTMsg>();
                    if (isMtrWindowPeriod(iwrMtrReadList)) {
//                        if (callApi(iwrMtrReadList, DS_MTR_READ_TP_GRP.BILLABLE_READS)) {
                        if (callApi(iwrMtrReadList, DS_MTR_READ_TP_GRP.BILLABLE_READS, dsMtrIntfcTMsgList, dsMsgTMsgList)) {
                            insertIwrMlyMtrReadTrks(iwrMtrReadList);
                            iwrProcSts = IWR_PROC_STS.PROCESSED_RECORD;
                        } else {
                            rollback();
                        }
                    // START 2018/03/13 K.Kojima [QC#23112,DEL]
//                    } else if (isNoContrAndIntvlMtrRead(svcMachMstrPk)) {
//                        if (callApi(iwrMtrReadList, DS_MTR_READ_TP_GRP.SERVICE_READS)) {
//                        if (callApi(iwrMtrReadList, DS_MTR_READ_TP_GRP.SERVICE_READS, dsMtrIntfcTMsgList, dsMsgTMsgList)) {
//                            insertIwrMlyMtrReadTrks(iwrMtrReadList);
//                            iwrProcSts = IWR_PROC_STS.PROCESSED_RECORD;
//                        } else {
//                            rollback();
//                        }
                    // END 2018/03/13 K.Kojima [QC#23112,DEL]
                    }
                    if (dsMtrIntfcTMsgList.size() > 0) {
                        insertDsMtrIntfc(dsMtrIntfcTMsgList);
                    }
                    if (dsMsgTMsgList.size() > 0) {
                        insertDsMsg(dsMsgTMsgList);
                    }
                }

                // ------------------------------
                // Update IWR_MTR_READ_DTL_WRK
                // ------------------------------
                updateIwrMtrReadDtlWrks(iwrMtrReadList, iwrProcSts);
//                loopCnt++;

//                if (loopCnt % this.iwrMtrReadComitCnt.intValue() == 0) {
//                    commit();
//                }
                commit();
                // END 2017/06/06 K.Kitachi [QC#18342, MOD]
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private Map<String, Object> getSsmParam() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("iwrProcStsNewRecord", IWR_PROC_STS.NEW_RECORD);
        return ssmParam;
    }

    private boolean isMtrWindowPeriod(List<Map<String, Object>> iwrMtrReadList) throws SQLException {

        if (iwrMtrReadList.isEmpty()) {
            return false;
        }

        BigDecimal svcMachMstrPk = (BigDecimal) iwrMtrReadList.get(0).get("SVC_MACH_MSTR_PK");
        String mtrReadDt = (String) iwrMtrReadList.get(0).get("MTR_READ_DT");

        // mod start 2017/08/08 QC#18799
        Map<String, Object> bllgSchd = getBllgSchd(svcMachMstrPk);
        if (bllgSchd == null) {
            return false;
        }
        MtrWinInfo mtrWinInfo = new MtrWinInfo();
        mtrWinInfo.setGlblCmpyCd(this.glblCmpyCd);
        mtrWinInfo.setBllgFromDt((String) bllgSchd.get("BLLG_SCHD_FROM_DT"));
        mtrWinInfo.setBllgThruDt((String) bllgSchd.get("BLLG_SCHD_THRU_DT"));
        mtrWinInfo.setBaseDt(this.salesDate);
        mtrWinInfo.setDsContrBllgSchdPk((BigDecimal) bllgSchd.get("DS_CONTR_BLLG_SCHD_PK"));
        // mod end 2017/08/08 QC#18799
        NSXC001001GetMtrWinFromThruDt.getMtrWinByDate(mtrWinInfo);
        String winFromDt = mtrWinInfo.getMtrWinFromDt();
        String winThruDt = mtrWinInfo.getMtrWinThruDt();
        if (!hasValue(winFromDt) || !hasValue(winThruDt)) {
            return false;
        }
        if (mtrReadDt.compareTo(winFromDt) < 0 || winThruDt.compareTo(mtrReadDt) < 0) {
            return false;
        }
        return true;
    }

    // START 2018/03/13 K.Kojima [QC#23112,DEL]
    // private boolean isNoContrAndIntvlMtrRead(BigDecimal svcMachMstrPk) throws SQLException {
    // 
    //     BigDecimal targetMachMstrPk = getNoContrAndIntvlMtrReadMach(svcMachMstrPk);
    //     if (!hasValue(targetMachMstrPk)) {
    //         return false;
    //     }
    //     return true;
    // }
    // END 2018/03/13 K.Kojima [QC#23112,DEL]

    // START 2017/06/06 K.Kitachi [QC#18342, MOD]
//    private boolean callApi(List<Map<String, Object>> iwrMtrReadList, String dsMtrReadTpGrpCd) {
    private boolean callApi(List<Map<String, Object>> iwrMtrReadList, String dsMtrReadTpGrpCd, List<DS_MTR_INTFCTMsg> dsMtrIntfcTMsgList, List<DS_MSGTMsg> dsMsgTMsgList) {
    // END 2017/06/06 K.Kitachi [QC#18342, MOD]

        if (iwrMtrReadList.isEmpty()) {
            return false;
        }

        NSZC051001 api = new NSZC051001();
        NSZC051001PMsg pMsg = new NSZC051001PMsg();

        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.mtrReadSrcTpCd, MTR_READ_SRC_TP.IWR);
        setValue(pMsg.dsMtrReadTpCd, DS_MTR_READ_TP.IMAGEWARE);
        setValue(pMsg.slsDt, this.salesDate);
        setValue(pMsg.rgtnUsrId, NSAB024001.class.getSimpleName());
        setValue(pMsg.svcMachMstrPk, (BigDecimal) iwrMtrReadList.get(0).get("SVC_MACH_MSTR_PK"));
        setValue(pMsg.serNum, (String) iwrMtrReadList.get(0).get("SER_NUM"));
        setValue(pMsg.mtrReadDt, (String) iwrMtrReadList.get(0).get("MTR_READ_DT"));
        setValue(pMsg.rgtnMtrDt, this.salesDate);
        setValue(pMsg.dsMtrReadTpGrpCd, dsMtrReadTpGrpCd);
        // Add Start 2017/11/28 QC#22771
        setValue(pMsg.xxRqstFlg_WR, ZYPConstant.FLG_ON_Y);
        // Add End 2017/11/28 QC#22771

        for (int i = 0; i < iwrMtrReadList.size(); i++) {
            Map<String, Object> iwrMtrReadInfo = iwrMtrReadList.get(i);
            setValue(pMsg.meterList.no(i).mdlMtrLbCd, (String) iwrMtrReadInfo.get("MTR_LB_CD"));
            setValue(pMsg.meterList.no(i).readMtrCnt, (BigDecimal) iwrMtrReadInfo.get("READ_MTR_CNT"));
            setValue(pMsg.meterList.no(i).testMtrCnt, BigDecimal.ZERO);
            setValue(pMsg.meterList.no(i).estFlg, ZYPConstant.FLG_OFF_N);
        }
        pMsg.meterList.setValidCount(iwrMtrReadList.size());
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        // START 2017/06/06 K.Kitachi [QC#18342, ADD]
        BigDecimal dsMtrIntfcPk;
        BigDecimal errGrpSq = null;
        DS_MTR_INTFCTMsg dsMtrIntfcTMsg;
        for (int i = 0; i < pMsg.rsltPrmList.getValidCount(); i++) {
            dsMtrIntfcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_MTR_INTFC_SQ);
            if (DS_MTR_PROC_STS.ERROR.equals(pMsg.dsMtrProcStsCd.getValue()) && !hasValue(errGrpSq)) {
                errGrpSq = dsMtrIntfcPk.negate();
            }
            dsMtrIntfcTMsg = createDsMtrIntfcTMsg(pMsg, dsMtrIntfcPk, errGrpSq, i);
            dsMtrIntfcTMsgList.add(dsMtrIntfcTMsg);
            if (hasValue(pMsg.dsMsgTxt)) {
                dsMsgTMsgList.add(createDsMsgTMsg(pMsg, dsMtrIntfcPk));
            }
        }
        // END 2017/06/06 K.Kitachi [QC#18342, ADD]

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            String errMsg = S21MessageFunc.clspGetMessage(msgId, msgPrms);

            if (msgId.endsWith(MSG_KIND_ERR)) {
                // Error
                for (Map<String, Object> iwrMtrRead : iwrMtrReadList) {
                    addErrMsg(errMsg, (BigDecimal) iwrMtrRead.get("IWR_MTR_READ_DTL_WRK_PK"));
                    this.errorCount++;
                }
                return false;
            } else if (msgId.endsWith(MSG_KIND_WARN)) {
                // Warning
                if (ZYPConstant.FLG_ON_Y.equals(this.warnMlSendFlg)) {
                    for (Map<String, Object> iwrMtrRead : iwrMtrReadList) {
                        addErrMsg(errMsg, (BigDecimal) iwrMtrRead.get("IWR_MTR_READ_DTL_WRK_PK"));
                        // add start 2016/07/07 CSA Defect#11183
                        this.errorCount++;
                        // add end 2016/07/07 CSA Defect#11183
                    }
                }
                return true;
            }
        }
        return true;
    }

    private void insertIwrMlyMtrReadTrks(List<Map<String, Object>> iwrMtrReadList) throws SQLException {

        for (Map<String, Object> iwrMtrReadInfo : iwrMtrReadList) {
            IWR_MTR_READ_DTL_WRKTMsg tMsg = new IWR_MTR_READ_DTL_WRKTMsg();
            setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(tMsg.iwrMtrReadDtlWrkPk, (BigDecimal) iwrMtrReadInfo.get("IWR_MTR_READ_DTL_WRK_PK"));
            tMsg = (IWR_MTR_READ_DTL_WRKTMsg) EZDTBLAccessor.findByKey(tMsg);
            if (tMsg == null) {
                continue;
            }

            IWR_MLY_MTR_READ_TRKTMsg inMsg = new IWR_MLY_MTR_READ_TRKTMsg();
            setValue(inMsg.glblCmpyCd, tMsg.glblCmpyCd);
            BigDecimal iwrMlyMtrReadTrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.IWR_MLY_MTR_READ_TRK_SQ);
            setValue(inMsg.iwrMlyMtrReadTrkPk, iwrMlyMtrReadTrkPk);
            setValue(inMsg.iwrMtrReadDtlWrkPk, tMsg.iwrMtrReadDtlWrkPk);
            setValue(inMsg.localMtrRcvTs, tMsg.localMtrRcvDtTmTs);
            setValue(inMsg.gmtMtrRcvTs, tMsg.gmtMtrRcvDtTmTs);
            setValue(inMsg.svcMachMstrPk, tMsg.svcMachMstrPk);
            setValue(inMsg.svcPhysMtrPk, tMsg.svcPhysMtrPk);
            setValue(inMsg.iwrMdlNm, tMsg.iwrMdlNm);
            setValue(inMsg.serNum, tMsg.serNum);
            setValue(inMsg.childSvcMachMstrPk, tMsg.childSvcMachMstrPk);
            setValue(inMsg.childSerNum, tMsg.childSerNum);
            setValue(inMsg.mtrReadDt, tMsg.mtrReadDt);
            setValue(inMsg.readMtrCnt, tMsg.readMtrCnt);
            setValue(inMsg.mtrCntrId, tMsg.mtrCntrId);
            S21FastTBLAccessor.insert(inMsg);

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                throw new S21AbendException(NSAM0032E, new String[] {"IWR_MLY_MTR_READ_TRK"});
            }
        }
    }

    private void updateIwrMtrReadDtlWrks(List<Map<String, Object>> iwrMtrReadList, String iwrProcStsCd) throws SQLException {

        for (Map<String, Object> iwrMtrReadInfo : iwrMtrReadList) {
            IWR_MTR_READ_DTL_WRKTMsg inMsg = new IWR_MTR_READ_DTL_WRKTMsg();
            setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(inMsg.iwrMtrReadDtlWrkPk, (BigDecimal) iwrMtrReadInfo.get("IWR_MTR_READ_DTL_WRK_PK"));
            inMsg = (IWR_MTR_READ_DTL_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);
            if (inMsg == null) {
                continue;
            }
            setValue(inMsg.iwrProcStsCd, iwrProcStsCd);
            EZDTBLAccessor.update(inMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                throw new S21AbendException(NSAM0031E, new String[] {"IWR_MTR_READ_DTL_WRK"});
            }
            this.totalCount++;
        }
    }

    private void addErrMsg(String errMsg, BigDecimal iwrMtrReadDtlPk) {

        StringBuilder sb = new StringBuilder();
        if (hasValue(errMsg)) {
            sb.append(errMsg);
            sb.append("[IWR_MTR_READ_DTL_WRK_PK=");
            sb.append(iwrMtrReadDtlPk);
            sb.append("]");
            this.errMsgList.add(sb.toString());
        }
    }

    private void sendMail() {

        if (this.errMsgList.isEmpty()) {
            return;
        }

        S21MailAddress from = null;
        if (!this.fromAddrList.isEmpty()) {
            from = this.fromAddrList.get(0);
        }

        // Create Subject and Body
        String currentTime = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);

        String newLine = System.getProperty("line.separator");
        StringBuilder msgBuf = new StringBuilder();
        for (String errMsg : this.errMsgList) {
            msgBuf.append(errMsg);
            msgBuf.append(newLine);
        }

        template.setTemplateParameter(MAIL_TEMPLATE_KEY_ID, BIZ_APP_ID);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_DATE, currentTime);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, msgBuf.toString());

        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();

        StringBuilder logBuf = new StringBuilder();
        logBuf.append(newLine);
        logBuf.append("==================== Skip or Error Data ====================");
        logBuf.append(newLine);
        logBuf.append(msgBuf);
        logBuf.append("============================================================");
        S21InfoLogOutput.println(logBuf.toString());
    }

    // START 2018/03/13 K.Kojima [QC#23112,DEL]
    // private String addMonths(String strDate) {
    // 
    //     SimpleDateFormat sdf = new SimpleDateFormat(S21CalendarUtilConstants.TYPE_YYYYMMDD);
    //     Date dt = null;
    //     try {
    //         dt = sdf.parse(strDate);
    //     } catch (ParseException e) {
    //         return strDate;
    //     }
    //     Calendar cal = Calendar.getInstance();
    //     cal.setTime(dt);
    // 
    //     int curDay = cal.get(Calendar.DATE);
    // 
    //     if (curDay >= DATE_28) {
    //         // Date : 28 - 31
    //         cal.add(Calendar.MONTH, this.noContrMtrReadIntvlMths.negate().intValue());
    //         int newEndDay = cal.getActualMaximum(Calendar.DATE);
    //         cal.set(Calendar.DATE, newEndDay);
    //     } else {
    //         // Date : 1 - 27
    //         cal.add(Calendar.MONTH, this.noContrMtrReadIntvlMths.negate().intValue());
    //     }
    //     return sdf.format(cal.getTime());
    // }
    // END 2018/03/13 K.Kojima [QC#23112,DEL]

    // mod start 2017/08/08 QC#18799
    private Map<String, Object> getBllgSchd(BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("slsDt", ZYPDateUtil.addDays(this.salesDate, mtrBefThruDays.intValue()));
        return (Map<String, Object>) ssmBatClient.queryObject("getBllgSchd", ssmParam);
    }
    // mod end 2017/08/08 QC#18799

    // START 2018/03/13 K.Kojima [QC#23112,DEL]
    // private BigDecimal getNoContrAndIntvlMtrReadMach(BigDecimal svcMachMstrPk) {
    // 
    //     Map<String, Object> ssmParam = new HashMap<String, Object>();
    //     ssmParam.put("glblCmpyCd", this.glblCmpyCd);
    //     ssmParam.put("svcMachMstrPk", svcMachMstrPk);
    //     ssmParam.put("dsContrDtlStsCdCanc", DS_CONTR_DTL_STS.CANCELLED);
    //     ssmParam.put("dsContrCatgCdWty", DS_CONTR_CATG.WARRANTY);
    //     ssmParam.put("slsDt", this.salesDate);
    //     ssmParam.put("intvlDt", addMonths(this.salesDate));
    //     return (BigDecimal) ssmBatClient.queryObject("getNoContrAndIntvlMtrReadMach", ssmParam);
    // }
    // END 2018/03/13 K.Kojima [QC#23112,DEL]

    private List<Map<String, Object>> getIwrMtrReadDtlWrk(BigDecimal svcMachMstrPk, String mtrRcvDtTmTs) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("mtrRcvDtTmTs", mtrRcvDtTmTs);
        // Add Start 2017/11/28 QC#22771
        ssmParam.put("iwrProcStsNewRecord", IWR_PROC_STS.NEW_RECORD);
        // Add End 2017/11/28 QC#22771
        return (List<Map<String, Object>>) ssmBatClient.queryObjectList("getIwrMtrReadDtlWrk", ssmParam);
    }

    // START 2017/06/06 K.Kitachi [QC#18342, ADD]
    private DS_MTR_INTFCTMsg createDsMtrIntfcTMsg(NSZC051001PMsg pMsg, BigDecimal dsMtrIntfcPk, BigDecimal errGrpSq, int idx) {
        NSZC051001_rsltPrmListPMsg rsltPrm = pMsg.rsltPrmList.no(idx);
        DS_MTR_INTFCTMsg tMsg = new DS_MTR_INTFCTMsg();
        setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(tMsg.dsMtrIntfcPk, dsMtrIntfcPk);
        setValue(tMsg.mtrReadSrcTpCd, pMsg.mtrReadSrcTpCd);
        BigDecimal svcPhysMtrReadGrpSq = rsltPrm.svcPhysMtrReadGrpSq.getValue();
        if (DS_MTR_PROC_STS.ERROR.equals(pMsg.dsMtrProcStsCd.getValue())) {
            svcPhysMtrReadGrpSq = errGrpSq;
        }
        setValue(tMsg.svcPhysMtrReadGrpSq, svcPhysMtrReadGrpSq);
        setValue(tMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        setValue(tMsg.serNum, pMsg.serNum);
        setValue(tMsg.mdseCd, pMsg.mdseCd_O);
        setValue(tMsg.svcPhysMtrPk, rsltPrm.svcPhysMtrPk);
        setValue(tMsg.mdlMtrLbCd, rsltPrm.mdlMtrLbCd);
        setValue(tMsg.readMtrCnt, rsltPrm.readMtrCnt);
        setValue(tMsg.mtrReadDt, pMsg.mtrReadDt);
        setValue(tMsg.dsMtrProcStsCd, pMsg.dsMtrProcStsCd);
        setValue(tMsg.rgtnUsrId, pMsg.rgtnUsrId);
        setValue(tMsg.dsMtrProcTs, this.procTs);
        return tMsg;
    }

    private DS_MSGTMsg createDsMsgTMsg(NSZC051001PMsg pMsg, BigDecimal dsMtrIntfcPk) {
        DS_MSGTMsg tMsg = new DS_MSGTMsg();
        setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(tMsg.dsMsgPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_MSG_SQ));
        setValue(tMsg.dsMsgTrxNum, String.valueOf(dsMtrIntfcPk));
        setValue(tMsg.dsMsgTrxNm, DS_MTR_INTFC_PK);
        setValue(tMsg.dsMsgTxt, pMsg.dsMsgTxt);
        return tMsg;
    }

    private void insertDsMtrIntfc(List<DS_MTR_INTFCTMsg> createTMsg) {
        DS_MTR_INTFCTMsg[] createList = new DS_MTR_INTFCTMsg[createTMsg.size()];
        S21FastTBLAccessor.insert(createTMsg.toArray(createList));
    }

    private void insertDsMsg(List<DS_MSGTMsg> createDsMsgTMsg) {
        DS_MSGTMsg[] createList = new DS_MSGTMsg[createDsMsgTMsg.size()];
        S21FastTBLAccessor.insert(createDsMsgTMsg.toArray(createList));
    }
    // END 2017/06/06 K.Kitachi [QC#18342, ADD]
}
