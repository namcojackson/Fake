/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB017001;

import static com.canon.cusa.s21.batch.NSB.NSBB017001.NSBB017001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CNTYTMsg;
import business.db.CNTYTMsgArray;
import business.db.CRS_SVC_RCV_RQST_STAGETMsg;
import business.db.FSRTMsg;
import business.db.FSR_VISITTMsg;
import business.db.FSR_VISITTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_TASKTMsg;
import business.db.SVC_TASKTMsgArray;
import business.parts.NMZC001001PMsg;
import business.parts.NMZC001002PMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NSZC043001PMsg;
import business.parts.NSZC045001PMsg;
import business.parts.NSZC107001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC001001.NMZC001001;
import com.canon.cusa.s21.api.NMZ.NMZC001001.constant.NMZC001001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC045001.NSZC045001;
import com.canon.cusa.s21.api.NSZ.NSZC107001.NSZC107001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TRX_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_BILL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PRVD_PTY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 *<pre>
 *  Create Task From Cross Service Request
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/02/2016   Hitachi         Y.Takeno        Create          N/A
 * 03/25/2016   Hitachi         Y.Takeno        Update          QC#5457
 * 05/24/2016   Hitachi         T.Iwamoto       Update          QC#8809
 * 06/15/2016   Hitachi         Y.Takeno        Update          QC#9640
 * 07/04/2016   Hitachi         T.Iwamoto       Update          QC#9677
 * 08/29/2016   Fujitsu         N.Sugiura       Update          QC#13560
 * 2016/09/05   Hitachi         T.Tomita        Update          QC#1247115156
 * 2016/10/04   Hitachi         K.Yamada        Update          QC#14984
 * 10/12/2016   Fujitsu         S.Nakai         Update          QC#15156
 * 2016/10/13   Hitachi         T.Kanasaka      Update          QC#15143
 * 2016/11/14   Hitachi         N.Arai          Update          QC#15829
 * 2018/05/18   CITS            T.Wada          Update          QC#20824
 * 2019/02/19   Hitachi         K.Kim           Update          QC#30296
 * 2019/03/01   Hitachi         K.Fujimoto      Update          QC#29742
 * 2019/05/07   Hitachi         K.Kim           Update          QC#50119
 * 2019/07/08   Hitachi         K.Kitachi       Update          QC#51071/51198/51201/51208
 * 2019/07/10   Hitachi         K.Kitachi       Update          QC#51195
 * 2019/07/26   Hitachi         K.Kitachi       Update          QC#52074
 * 2019/08/08   Hitachi         K.Kitachi       Update          QC#52391
 * 2019/09/10   Hitachi         K.Fujimoto      Update          QC#51315
 * 2019/09/25   Hitachi         K.Kitachi       Update          QC#53713
 * 2019/10/07   Hitachi         A.Kohinata      Update          QC#53406
 * 2019/11/18   Hitachi         E.Kameishi      Update          QC#51561
 * 2023/10/06   Hitachi         K.Watanabe      Update          QC#54186
 *</pre>
 */
public class NSBB017001 extends S21BatchMain {

    /** Termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Global company code */
    private String glblCmpyCd = "";

    /** Sales Date */
    private String slsDt = "";

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** Current System Timestamp */
    private String currentSystemTs = null;

    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmClient = S21SsmBatchClient.getClient(this.getClass());

// START 2016/03/25 [QC#5457, MOD]
    /** VAR_CHAR_CONST : NSBB0170_DEF_COA_CH_CD */
    private String defCoaChCd = null;

    /** VAR_CHAR_CONST : NSBB0170_DEF_COA_AFFL_CD */
    private String defCoaAfflCd = null;

    /** VAR_CHAR_CONST : NSBB0170_DEF_SVC_MEMO_TP_CD */
    private String defSvcMemoTpCd = null;

    /** VAR_CHAR_CONST : NSBB0170_SVC_PBLM_TP */
    private String defSvcPblmCd = null;

    /** VAR_CHAR_CONST : NSBB0170_SVC_CALL_SRC_TP_CD*/
    private String defSvcCallTpCd = null;

    /** VAR_CHAR_CONST : NSBB0170_LINE_BIZ_TP_CD*/
    private String deflineBizTpCd = null;

    /** VAR_CHAR_CONST : NSBB0170_CTRY_CD*/
    private String defCtryCd = null;

    // END 2016/03/25 [QC#5457, MOD]

    // START 2016/09/05 T.Tomita [QC#12471, ADD]
    /** VAR_CHAR_CONST : NSBB0170_SVC_BY_LINE_BIZ_TP_CD */
    private String svcByLineBizTpCd = null;
    // END 2016/09/05 T.Tomita [QC#12471, ADD]

    // START 2016/10/13 T.Kanasaka [QC#15143, ADD]
    /** VAR_CHAR_CONST : NSBB0170_CRS_SVC_NOT_EXIST_EML */
    private String defCustEmlAddr = null;
    // END 2016/10/13 T.Kanasaka [QC#15143, ADD]

    // mod start 2016/07/04 CSA Defect#9677
    /** sendSvcTaskNum*/
    private String sendSvcTaskNum = null;
    // mod end 2016/07/04 CSA Defect#9677

    // Add Start 2019/03/01 K.Fujimoto QC#29742
    /** User Variable1 */
    private String usrVar1;

    /** Multi Count */
    private BigDecimal multiCnt;
    // Add End   2019/03/01 K.Fujimoto QC#29742

    // START 2019/07/26 K.Kitachi [QC#52074, ADD]
    /** Cross Service Task Status Code List */
    private ArrayList<String> crsSvcTaskStsCdList;

    /** Service Task Status Code List */
    private ArrayList<String> svcTaskStsCdList;
    // END 2019/07/26 K.Kitachi [QC#52074, ADD]

    /**
     * @param args parameter
     */
    public static void main(String[] args) {
        new NSBB017001().executeBatch(NSBB017001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        this.currentSystemTs = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);

        // blank check(Global Company Code)
        glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSBM0032E, new String[] {"Global Company Code" });
        }

        // blank check(Sales Date)
        slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            throw new S21AbendException(NSBM0032E, new String[] {"Sales Date" });
        }

        // Add Start 2019/03/01 K.Fujimoto QC#29742
        this.multiCnt = ZYPCodeDataUtil.getNumConstValue(NUM_CONST_MULTI_CRS_SVC_RQST_CNT, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.multiCnt)) {
            this.multiCnt = BigDecimal.ONE;
        }
        this.usrVar1 = getUserVariable1();
        if (!hasValue(usrVar1)) {
            throw new S21AbendException(NSZM0392E, new String[] {"User Variable1"});
        }
        // Add End   2019/03/01 K.Fujimoto QC#29742

        // START 2019/07/26 K.Kitachi [QC#52074, ADD]
        this.crsSvcTaskStsCdList = new ArrayList<String>();
        this.crsSvcTaskStsCdList.add(CRS_SVC_TASK_STS_COMPLETED);
        this.crsSvcTaskStsCdList.add(CRS_SVC_TASK_STS_PENDING_CHARGES);
        this.crsSvcTaskStsCdList.add(CRS_SVC_TASK_STS_CLOSED);
        this.crsSvcTaskStsCdList.add(CRS_SVC_TASK_STS_DEBRIEF_ERROR);

        this.svcTaskStsCdList = new ArrayList<String>();
        this.svcTaskStsCdList.add(SVC_TASK_STS.COMPLETED);
        this.svcTaskStsCdList.add(SVC_TASK_STS.PENDING_CHARGE);
        this.svcTaskStsCdList.add(SVC_TASK_STS.CLOSED);
        this.svcTaskStsCdList.add(SVC_TASK_STS.DEBRIEF_ERROR);
        // END 2019/07/26 K.Kitachi [QC#52074, ADD]
    }

    @Override
    protected void mainRoutine() {

        // initialize varConstMap
        initVarConstMap();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {

            // get request data
            stmt = ssmLLClient.createPreparedStatement("getCrsSvcRcvRqstStage", getCrsSvcRcvRqstStageParams(), execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {

                if (processRequestData(rs)) {
                    totalNmlCount++;

                } else {
                    totalErrCount++;
                }

                // START 2016/06/15 [QC#9640, ADD]
                commit();
                // END 2016/06/15 [QC#9640, ADD]
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);

        }

        if (totalErrCount > 0) {
            termCd = TERM_CD.WARNING_END;
        }
    }

    @Override
    protected void termRoutine() {
        setRecordCount(totalNmlCount, totalErrCount, (totalNmlCount + totalErrCount));
        setTermState(termCd);
    }

    private Map<String, Object> getCrsSvcRcvRqstStageParams() {
        Map<String, Object> prms = new HashMap<String, Object>();
        prms.put("glblCmpyCd", this.glblCmpyCd);
        prms.put("procStsCd", PROC_STS.IN_COMPLETED);
        prms.put("enblFlg", ZYPConstant.FLG_ON_Y);
        // Add Start 2019/03/01 K.Fujimoto QC#29742
        prms.put("multiCnt", multiCnt);
        prms.put("usrVar1", this.usrVar1);
        // Add End   2019/03/01 K.Fujimoto QC#29742
        // START 2019/07/08 K.Kitachi [QC#51071, ADD]
        prms.put("rqstSqOrigTxtLen", CRS_SVC_RQST_SQ_ORIG_TXT_LENGTH);
        // END 2019/07/08 K.Kitachi [QC#51071, ADD]
        // START 2019/07/10 K.Kitachi [QC#51195, ADD]
        prms.put("locNmLen", LOC_NM_LENGTH);
        // END 2019/07/10 K.Kitachi [QC#51195, ADD]

        return prms;
    }

    private boolean processRequestData(ResultSet rs) throws SQLException {

        // mod start 2016/07/04 CSA Defect#9677
        this.sendSvcTaskNum = null;
        // mod end 2016/07/04 CSA Defect#9677

        if (!inputDataCheck(rs)) {
            return false;
        }

        // START 2019/07/26 K.Kitachi [QC#52074, ADD]
        if (alreadyCancOrCloseOnS21(rs)) {
            updateCrsSvcRcvRqstStage(rs, PROC_STS.COMPLEATED, null, null);
            return true;
        }
        if (duplicateWithPrevIf(rs)) {
            updateCrsSvcRcvRqstStage(rs, PROC_STS.COMPLEATED, null, null);
            return true;
        }
        // END 2019/07/26 K.Kitachi [QC#52074, ADD]

        boolean result = false;
        int recordType = identifyRecordType(rs);
        switch (recordType) {
            case RECORD_TYPE_REGISTER:
                result = registerFsr(rs);
                break;
            case RECORD_TYPE_UPDATE:
                result = updateFsr(rs);
                break;
            case RECORD_TYPE_CANCEL:
                result = cancelFsr(rs);
                break;
            default:
        }

        // Call Send Task API
        // mod start 2016/07/04 CSA Defect#9677
        if (result && hasValue(sendSvcTaskNum)) {
            NSZC107001PMsg apiPMsg = new NSZC107001PMsg();
            setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(apiPMsg.slsDt, this.slsDt);
            setValue(apiPMsg.svcTaskNum, this.sendSvcTaskNum);
            NSZC107001 api = new NSZC107001();
            api.execute(apiPMsg, ONBATCH_TYPE.BATCH);
        }
        // mod end 2016/07/04 CSA Defect#9677

        return result;
    }

    private boolean inputDataCheck(ResultSet rs) throws SQLException {

        if (!hasValue(rs.getString("CRS_SVC_SR_NUM"))) {
            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0032E, new String[]{"SR Number" }));
            return false;
        }

        // SRNo.
        String srNo = rs.getString("CRS_SVC_SR_NUM");

        if (!isValidDateFormat(DT_FORMAT_RQST_TS, rs.getString("CRS_SVC_CRAT_TS_ORIG_TXT"))) {
            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0140E, new String[]{"SR No. " + srNo, "Creation Timestamp" }));
            return false;
        }

        if (!hasValue(rs.getString("CRS_SVC_CUST_NM"))) {
            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0141E, new String[]{"SR No. " + srNo, "Cross Service Customer Name" }));
            return false;
        }

        if (!hasValue(rs.getString("CRS_SVC_FIRST_LINE_ADDR"))) {
            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0141E, new String[]{"SR No. " + srNo, "Cross Service First Address" }));
            return false;
        }

        if (!hasValue(rs.getString("CRS_SVC_CTY_ADDR"))) {
            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0141E, new String[]{"SR No. " + srNo, "Cross Service City Address" }));
            return false;
        }

        if (!hasValue(rs.getString("CRS_SVC_ST_CD"))) {
            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0141E, new String[]{"SR No. " + srNo, "Cross Service State Code" }));
            return false;
        }

        if (!hasValue(rs.getString("CRS_SVC_POST_CD"))) {
            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0141E, new String[]{"SR No. " + srNo, "Cross Service Post Code" }));
            return false;
        }

        if (!hasValue(rs.getString("MDL_NM"))) {
            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0141E, new String[]{"SR No. " + srNo, "Model Number" }));
            return false;
        }

        // START 2019/07/08 K.Kitachi [QC#51208, DEL]
//        if (hasValue(rs.getString("MDL_NM")) && !hasValue(rs.getString("CRS_SVC_MDSE_CD"))) {
//            String mdlNm = rs.getString("MDL_NM");
//            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
//            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0142E, new String[]{"SR No. " + srNo, "Model Number", mdlNm }));
//            return false;
//        }
        // END 2019/07/08 K.Kitachi [QC#51208, DEL]

        if (!hasValue(rs.getString("SER_NUM"))) {
            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0141E, new String[]{"SR No. " + srNo, "Serial Number" }));
            return false;
        }

        // START 2019/07/08 K.Kitachi [QC#51208, ADD]
        if (hasValue(rs.getString("MDL_NM")) && !hasValue(rs.getString("CRS_SVC_MDSE_CD")) && !isExistMachine(rs.getString("SER_NUM"))) {
            String mdlNm = rs.getString("MDL_NM");
            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0142E, new String[] {"SR No. " + srNo, "Model Number", mdlNm }));
            return false;
        }
        // END 2019/07/08 K.Kitachi [QC#51208, ADD]

        if (!hasValue(rs.getString("CRS_SVC_TASK_NUM"))) {
            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0141E, new String[]{"SR No. " + srNo, "Cross Service Task Number" }));
            return false;
        }

        if (!hasValue(rs.getString("CRS_SVC_TASK_STS_CD"))) {
            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0141E, new String[]{"SR No. " + srNo, "Cross Service Task Status Code" }));
            return false;
        }

        if (!hasValue(rs.getString("SVC_TASK_STS_CD"))) {
            String crsSvcTaskStsCd = rs.getString("CRS_SVC_TASK_STS_CD");
            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0142E, new String[]{"SR No. " + srNo, "Cross Service Task Status Code", crsSvcTaskStsCd }));
            return false;
        }

        if (!hasValue(rs.getString("CRS_SVC_TASK_TP_CD"))) {
            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0141E, new String[]{"SR No. " + srNo, "Cross Service Task Type Code" }));
            return false;
        }

        if (!hasValue(rs.getString("DS_SVC_CALL_TP_CD"))) {
            String crsSvcTaskTpCd = rs.getString("CRS_SVC_TASK_TP_CD");
            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0142E, new String[]{"SR No. " + srNo, "Cross Service Task Type Code", crsSvcTaskTpCd }));
            return false;
        }

        if (!CRS_SVC_ACT_CD_NEW.equals(rs.getString("CRS_SVC_ACT_CD"))
                && !CRS_SVC_ACT_CD_UPDATE.equals(rs.getString("CRS_SVC_ACT_CD"))) {
            String actionCode = rs.getString("CRS_SVC_ACT_CD");
            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0140E, new String[]{"SR No. " + srNo, "Action Code " + actionCode }));
            return false;
        }

        return true;
    }

    private boolean isValidDateFormat(String pattern, String source) {

        if (source == null) {
            return true;
        }

        try {
            DateFormat format = new SimpleDateFormat(pattern);
            format.parse(source);

        } catch (ParseException ex) {
            return false;
        }

        return true;
    }

    private int identifyRecordType(ResultSet rs) throws SQLException {

        if (CRS_SVC_ACT_CD_NEW.equals(rs.getString("CRS_SVC_ACT_CD"))) {
            return RECORD_TYPE_REGISTER;
        } else if (CRS_SVC_ACT_CD_UPDATE.equals(rs.getString("CRS_SVC_ACT_CD"))) {
            if (SVC_TASK_STS.CANCELLED.equals(rs.getString("SVC_TASK_STS_CD"))) {
                return RECORD_TYPE_CANCEL;
            } else {
                return RECORD_TYPE_UPDATE;
            }
        } else  {
            return -1;
        }
    }

    private boolean registerFsr(ResultSet rs) throws SQLException {

        // find SVC_TASK by SR No.
        SVC_TASKTMsg tMsg = new SVC_TASKTMsg();
        tMsg.setSQLID("010");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("crsSvcSrNum01", rs.getString("CRS_SVC_SR_NUM"));

        if (EZDTBLAccessor.count(tMsg) > 0) {
            // ERROR : duplicate SR No
            String srNo = rs.getString("CRS_SVC_SR_NUM");
            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0145E, new String[]{"SR No " + srNo }));
            return false;
        }

        Map<String, Object> shipTo = null;

        String serNum = rs.getString("SER_NUM");
        Map<String, Object> svcMachMstr = getSvcMachMstr(serNum);
        if (svcMachMstr != null) {
            String curLocAcctNum = (String) svcMachMstr.get("CUR_LOC_ACCT_NUM");
            String curLocNum = (String) svcMachMstr.get("CUR_LOC_NUM");

            shipTo = findShipToCustByCurLocAcctNum(curLocAcctNum, curLocNum);
            // START 2019/08/08 K.Kitachi [QC#52391, MOD]
//            if (shipTo != null) {
//                if (!compareAddress(rs, shipTo)) {
//                    // START 2019/07/08 K.Kitachi [QC#51198, DEL]
////                    // call Customer Update API(NMZC001001)
////                    NMZC001001PMsg pMsg = createNMZC001001PMsg(rs);
////                    // START 2016/06/15 [QC#9640, MOD]
////                    if (!callApiNMZC001001(pMsg)) {
////                        rollback();
////
////                        updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
////
////                        S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
////                        String msgId = msg.getXxMsgid();
////                        String[] msgPrms = msg.getXxMsgPrmArray();
////                        sendRequestDataErrorMail(createMailTemplateParamMap(rs, msgId, msgPrms));
////                        return false;
////                    }
////                    // END 2016/06/15 [QC#9640, MOD]
//                    // END 2019/07/08 K.Kitachi [QC#51198, DEL]
//
//                    // START 2019/07/08 K.Kitachi [QC#51198, ADD]
//                    BigDecimal svcMachMstrPk = machineLocationProcess(rs, svcMachMstr);
//                    if (!hasValue(svcMachMstrPk)) {
//                        return false;
//                    }
//                    // END 2019/07/08 K.Kitachi [QC#51198, ADD]
//                }
//
//            } else {
            if (shipTo == null) {
            // END 2019/08/08 K.Kitachi [QC#52391, MOD]
                // ERROR : not found customer
                updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
                sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0140E, new String[]{"Machine Master " + serNum, "Customer Information" }));
                return false;
            }

        } else {
            // START 2019/07/08 K.Kitachi [QC#51198, DEL]
//            String acctNum = null;
//            // START 2016/09/05 T.Tomita [QC#12471, DEL]
////            String ownrLocNum = null;
//            // END 2016/09/05 T.Tomita [QC#12471, DEL]
//            String billToLocNum = null;
//            String shipToLocNum = null;
//            String billToCustCd = null;
//            String shipToCustCd = null;
//
//            shipTo = findShipToCustByAddress(rs);
//            if (shipTo == null) {
//                // call Customer Update API(NMZC001001)
//                NMZC001001PMsg pMsg = createNMZC001001PMsg(rs);
//                // START 2016/06/15 [QC#9640, MOD]
//                if (!callApiNMZC001001(pMsg)) {
//                    rollback();
//
//                    updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
//
//                    S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
//                    String msgId = msg.getXxMsgid();
//                    String[] msgPrms = msg.getXxMsgPrmArray();
//                    sendRequestDataErrorMail(createMailTemplateParamMap(rs, msgId, msgPrms));
//                    return false;
//                }
//                // END 2016/06/15 [QC#9640, MOD]
//
//                acctNum = pMsg.dsAcctNum.getValue();
//                for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
//                    NMZC001002PMsg pMsg2 = pMsg.NMZC001002PMsg.no(i);
//                    // START 2016/09/05 T.Tomita [QC#12471, DEL]
////                    // mod start 2016/08/29 CSA Defect#13560
////                    if (ZYPConstant.FLG_ON_Y.equals(pMsg2.xxPrinFlg.getValue())) {
////                    // mod End 2016/08/29 CSA Defect#13560
////                        ownrLocNum = pMsg2.locNum.getValue();
////                    }
//                    // END 2016/09/05 T.Tomita [QC#12471, DEL]
//                    if (ZYPConstant.FLG_ON_Y.equals(pMsg2.billToCustFlg.getValue())) {
//                        billToLocNum = pMsg2.locNum.getValue();
//                    }
//                    if (ZYPConstant.FLG_ON_Y.equals(pMsg2.shipToCustFlg.getValue())) {
//                        shipToLocNum = pMsg2.locNum.getValue();
//                    }
//                }
//
//                billToCustCd = getBillToCustCdFromLocNum(billToLocNum);
//                shipToCustCd = getShipToCustCdFromLocNum(shipToLocNum);
//
//            } else {
//                // START 2016/03/25 [QC#5457, MOD]
//                acctNum = (String) shipTo.get("DS_ACCT_NUM");
//                // START 2016/09/05 T.Tomita [QC#12471, DEL]
////                ownrLocNum = (String) shipTo.get("LOC_NM");
////                billToCustCd = (String) shipTo.get("LOC_NM");
//                // END 2016/09/05 T.Tomita [QC#12471, DEL]
//                shipToCustCd = (String) shipTo.get("SHIP_TO_CUST_CD");
//                // END  2016/03/25 [QC#5457, MOD]
//
//                // START 2016/09/05 T.Tomita [QC#12471, ADD]
//                NMZC610001PMsg apiMsg = new NMZC610001PMsg();
//                setValue(apiMsg.glblCmpyCd, this.glblCmpyCd);
//                setValue(apiMsg.xxModeCd, NMZC610001Constant.PROCESS_DEFAULT_BILL_SHIP);
//                setValue(apiMsg.dsAcctNum_I1, acctNum);
//                setValue(apiMsg.shipToCustCd, shipToCustCd);
//                setValue(apiMsg.dsTrxRuleTpCd, DS_TRX_RULE_TP.SERVICE);
//
//                NMZC610001 api = new NMZC610001();
//                api.execute(apiMsg, ONBATCH_TYPE.BATCH);
//                if (S21ApiUtil.isXxMsgId(apiMsg)) {
//                    S21ApiMessage msg = S21ApiUtil.getXxMsgList(apiMsg).get(0);
//                    String msgId = msg.getXxMsgid();
//                    String[] msgPrms = msg.getXxMsgPrmArray();
//                    sendRequestDataErrorMail(createMailTemplateParamMap(rs, msgId, msgPrms));
//                    return false;
//                }
//                for (int i = 0; i < apiMsg.DefaultBillShipList.getValidCount(); i++) {
//                    if (hasValue(apiMsg.DefaultBillShipList.no(i).billToCustCd)) {
//                        billToCustCd = apiMsg.DefaultBillShipList.no(i).billToCustCd.getValue();
//                        break;
//                    }
//                }
//                // END 2016/09/05 T.Tomita [QC#12471, ADD]
//            }
//
//            // call Machine Master Update API(NSZC001001)
//            NSZC001001PMsg pMsg = createNSZC001001PMsg(rs, acctNum, billToCustCd, shipToCustCd);
//            // START 2016/06/15 [QC#9640, MOD]
//            if (!callApiNSZC001001(pMsg)) {
//                rollback();
//
//                updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
//
//                S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
//                String msgId = msg.getXxMsgid();
//                String[] msgPrms = msg.getXxMsgPrmArray();
//                sendRequestDataErrorMail(createMailTemplateParamMap(rs, msgId, msgPrms));
//                return false;
//            }
//            // END 2016/06/15 [QC#9640, MOD]
//
//            svcMachMstr = new HashMap<String, Object>();
//            svcMachMstr.put("SVC_MACH_MSTR_PK", pMsg.svcMachMstrPk.getValue());
            // END 2019/07/08 K.Kitachi [QC#51198, DEL]

            // START 2019/07/08 K.Kitachi [QC#51198, ADD]
            BigDecimal svcMachMstrPk = machineLocationProcess(rs, svcMachMstr);
            if (!hasValue(svcMachMstrPk)) {
                return false;
            }
            svcMachMstr = new HashMap<String, Object>();
            svcMachMstr.put("SVC_MACH_MSTR_PK", svcMachMstrPk);
            // END 2019/07/08 K.Kitachi [QC#51198, ADD]
        }

        // call FSR update API(NSZC043001)
        BigDecimal svcMachMstrPk = (BigDecimal) svcMachMstr.get("SVC_MACH_MSTR_PK");
        String dsSvcCallTpCd = rs.getString("DS_SVC_CALL_TP_CD");
        // START 2019/09/10 K.Fujimoto [QC#51315, MOD]
        // String cratTs = rs.getString("CRS_SVC_CRAT_TS_ORIG_TXT");
        String cratTs = rs.getString("CRS_SVC_TASK_TS_ORIG_TXT");
        String futSvcTs = rs.getString("CRS_SVC_CRAT_TS_ORIG_TXT");
        String pblmCd = rs.getString("CRS_SVC_PBLM_CD");
        String srCmntTxt = rs.getString("CRS_SVC_SR_CMNT_TXT");
        // NSZC043001PMsg pMsg = createNSZC043001PMsgRegisterFsr(svcMachMstrPk, dsSvcCallTpCd, cratTs, pblmCd, srCmntTxt);
        NSZC043001PMsg pMsg = createNSZC043001PMsgRegisterFsr(svcMachMstrPk, dsSvcCallTpCd, cratTs, pblmCd, srCmntTxt, futSvcTs);
        // END   2019/09/10 K.Fujimoto [QC#51315, MOD]
        // START 2016/06/15 [QC#9640, MOD]
        if (!callApiNSZC043001(pMsg)) {
            rollback();

            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);

            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, msgId, msgPrms));
            return false;
        }
        // END 2016/06/15 [QC#9640, MOD]

        // update SVC_TASK
        SVC_TASKTMsg svcTaskTMsg = new SVC_TASKTMsg();
        setValue(svcTaskTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(svcTaskTMsg.svcTaskNum, pMsg.taskList.no(0).svcTaskNum);
        svcTaskTMsg = (SVC_TASKTMsg) EZDTBLAccessor.findByKeyForUpdate(svcTaskTMsg);
        setValue(svcTaskTMsg.crsSvcSrNum, rs.getString("CRS_SVC_SR_NUM"));
        EZDTBLAccessor.update(svcTaskTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcTaskTMsg.getReturnCode())) {
            throw new S21AbendException(NSBM0120E, new String[] {"SVC_TASK"});
        }

        // update CRS_SVC_RCV_RQST_STAGE
        String fsrNum = pMsg.fsrNum.getValue();
        String svcTaskNum = pMsg.taskList.no(0).svcTaskNum.getValue();
        updateCrsSvcRcvRqstStage(rs, PROC_STS.COMPLEATED, fsrNum, svcTaskNum);

        // set Task# For Send Task API
        // mod start 2016/07/04 CSA Defect#9677
        this.sendSvcTaskNum = svcTaskNum;
        // mod end 2016/07/04 CSA Defect#9677
        return true;
    }

    private void updateCrsSvcRcvRqstStage(ResultSet rs, String crsSvcProcStsCd, String procFsrNum, String procSvcTaskNum) throws SQLException {
        CRS_SVC_RCV_RQST_STAGETMsg tMsg = new CRS_SVC_RCV_RQST_STAGETMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.crsSvcRcvRqstStagePk, rs.getBigDecimal("CRS_SVC_RCV_RQST_STAGE_PK"));
        tMsg = (CRS_SVC_RCV_RQST_STAGETMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
        if (hasValue(crsSvcProcStsCd)) {
            setValue(tMsg.crsSvcProcStsCd, crsSvcProcStsCd);
        }
        if (hasValue(procFsrNum)) {
            setValue(tMsg.procFsrNum, procFsrNum);
        }
        if (hasValue(procSvcTaskNum)) {
            setValue(tMsg.procSvcTaskNum, procSvcTaskNum);
        }
        setValue(tMsg.crsSvcProcTs, this.currentSystemTs);

        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(NSBM0120E, new String[] {"CRS_SVC_RCV_RQST_STAGE"});
        }
    }

    private boolean updateFsr(ResultSet rs) throws SQLException {

        List<String> svcTaskNumList = new ArrayList<String>();

        // find SVC_TASK by SR No.
        SVC_TASKTMsg svcTaskTMsg = findSvcTaskBySRNo(rs.getString("CRS_SVC_SR_NUM"));
        if (svcTaskTMsg == null) {
            // ERROR : SR No. is not found.
            String srNo = rs.getString("CRS_SVC_SR_NUM");
            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0146E, new String[]{"SR No " + srNo }));
            return false;
        }
        svcTaskNumList.add(svcTaskTMsg.svcTaskNum.getValue());

        // find FSR_VISIT
        FSR_VISITTMsg inMsg = new FSR_VISITTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("fsrNum01", svcTaskTMsg.fsrNum.getValue());
        inMsg.setConditionValue("svcTaskNum01", svcTaskTMsg.svcTaskNum.getValue());
        FSR_VISITTMsgArray tMsgArray = (FSR_VISITTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {

            FSR_VISITTMsg fsrVisitTMsg = tMsgArray.no(0);

            do {
                if (!hasValue(fsrVisitTMsg.nextFsrVisitNum) || !SVC_TASK_STS.COMPLETED.equals(fsrVisitTMsg.fsrVisitStsCd.getValue())) {
                    break;
                }

                // find follow-up record
                inMsg = new FSR_VISITTMsg();
                inMsg.setSQLID("005");
                inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                inMsg.setConditionValue("fsrNum01", fsrVisitTMsg.fsrNum.getValue());
                inMsg.setConditionValue("fsrVisitNum01", fsrVisitTMsg.nextFsrVisitNum.getValue());

                tMsgArray = (FSR_VISITTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
                if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
                    fsrVisitTMsg = tMsgArray.no(0);
                    svcTaskNumList.add(fsrVisitTMsg.svcTaskNum.getValue());
                }

            } while(tMsgArray != null && tMsgArray.getValidCount() > 0);

            if (!isFsrVisitCanUpdate(fsrVisitTMsg)) {
                // ERROR : FSR_VISIT cannot update.
                String fsrNo = fsrVisitTMsg.fsrNum.getValue();
                updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
                sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0144E, new String[]{"FSR No " + fsrNo, "update" }));
                return false;
            }

            String serNum = rs.getString("SER_NUM");
            Map<String, Object> svcMachMstr = getSvcMachMstr(serNum);
            if (svcMachMstr == null) {
                // ERROR : FSR_VISIT cannot update.
                String srNo = rs.getString("CRS_SVC_SR_NUM");
                updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
                sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0142E, new String[]{"SR No. " + srNo, "Machine Master", serNum }));
                return false;
            }

            Map<String, Object> shipTo = findShipToCustByCurLocAcctNum((String) svcMachMstr.get("CUR_LOC_ACCT_NUM"), (String) svcMachMstr.get("CUR_LOC_NUM"));
            // START 2019/08/08 K.Kitachi [QC#52391, MOD]
//            if (shipTo != null) {
//                if (!compareAddress(rs, shipTo)) {
//                    // START 2019/07/08 K.Kitachi [QC#51198, DEL]
////                    // call Customer Update API(NMZC001001)
////                    NMZC001001PMsg pMsg = createNMZC001001PMsg(rs);
////                    // START 2016/06/15 [QC#9640, MOD]
////                    if (!callApiNMZC001001(pMsg)) {
////                        rollback();
////
////                        updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
////
////                        S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
////                        String msgId = msg.getXxMsgid();
////                        String[] msgPrms = msg.getXxMsgPrmArray();
////                        sendRequestDataErrorMail(createMailTemplateParamMap(rs, msgId, msgPrms));
////                        return false;
////                    }
////                    // END 2016/06/15 [QC#9640, MOD]
////
////                    SVC_MACH_MSTRTMsg updTMsg = new SVC_MACH_MSTRTMsg();
////                    setValue(updTMsg.glblCmpyCd, this.glblCmpyCd);
////                    setValue(updTMsg.svcMachMstrPk, (BigDecimal) svcMachMstr.get("SVC_MACH_MSTR_PK"));
////                    updTMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdate(updTMsg);
////
////                    String ownrAcctNum = pMsg.dsAcctNum.getValue();
////                    String ownrLocNum = null;
////                    String billToAcctNum = pMsg.dsAcctNum.getValue();
////                    String billToCustCd = null;
////                    String billToLocNum = null;
////                    String curLocAcctNum = pMsg.dsAcctNum.getValue();
////                    String shipToCustCd = null;
////                    String shipToLocNum = null;
////                    for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
////                        NMZC001002PMsg pMsg2 = pMsg.NMZC001002PMsg.no(i);
////                     // mod start 2016/08/29 CSA Defect#13560
////                        if (ZYPConstant.FLG_ON_Y.equals(pMsg2.xxPrinFlg.getValue())) {
////                     // mod End 2016/08/29 CSA Defect#13560
////                            ownrLocNum = pMsg2.locNum.getValue();
////                        }
////                        if (ZYPConstant.FLG_ON_Y.equals(pMsg2.billToCustFlg.getValue())) {
////                            billToLocNum = pMsg2.locNum.getValue();
////                            billToCustCd = getBillToCustCdFromLocNum(billToLocNum);
////                        }
////                        if (ZYPConstant.FLG_ON_Y.equals(pMsg2.shipToCustFlg.getValue())) {
////                            shipToLocNum = pMsg2.locNum.getValue();
////                            shipToCustCd = getShipToCustCdFromLocNum(shipToLocNum);
////                        }
////                    }
////
////// START 2016/11/14 N.Arai [QC#15829, MOD]
////                    Map<String, String> beforParamMap = new HashMap<String, String>();
////                    beforParamMap.put(OWNR_ACCT_NUM, updTMsg.ownrAcctNum.getValue());
////                    beforParamMap.put(OWNR_LOC_NUM, updTMsg.ownrLocNum.getValue());
////                    beforParamMap.put(BILL_TO_ACCT_NUM, updTMsg.billToAcctNum.getValue());
////                    beforParamMap.put(BILL_TO_LOC_NUM, updTMsg.billToLocNum.getValue());
////                    beforParamMap.put(CUR_LOC_ACCT_NUM, updTMsg.curLocAcctNum.getValue());
////                    beforParamMap.put(CUR_LOC_NUM, updTMsg.curLocNum.getValue());
////                    beforParamMap.put(IND_BILL_TO_LOC_NUM, updTMsg.indBillToLocNum.getValue());
////                    beforParamMap.put(IND_CUR_LOC_NUM, updTMsg.indCurLocNum.getValue());
////// END 2016/11/14 N.Arai [QC#15829, MOD]
////
////                    setValue(updTMsg.ownrAcctNum, ownrAcctNum);
////                    setValue(updTMsg.ownrLocNum, ownrAcctNum);
////                    setValue(updTMsg.billToAcctNum, billToAcctNum);
////                    setValue(updTMsg.billToLocNum, billToCustCd);
////                    setValue(updTMsg.curLocAcctNum, curLocAcctNum);
////                    setValue(updTMsg.curLocNum, shipToCustCd);
////                    setValue(updTMsg.indBillToLocNum, billToLocNum);
////                    setValue(updTMsg.indCurLocNum, shipToLocNum);
////
////                    EZDTBLAccessor.update(updTMsg);
////                    // mod start 2016/10/04 CSA Defect#14984
////                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
////                    // mod end 2016/10/04 CSA Defect#14984
////                        throw new S21AbendException(NSBM0120E, new String[] {"SVC_MACH_MSTR"});
////                    }
////
////// START 2016/11/14 N.Arai [QC#15829, MOD]
////                    if (!insertSvcMachMstrTrk(beforParamMap, updTMsg)) {
////                        throw new S21AbendException(NSBM0120E, new String[] {"SVC_MACH_MSTR_TRK"});
////                    }
////// END 2016/11/14 N.Arai [QC#15829, MOD]
//                    // END 2019/07/08 K.Kitachi [QC#51198, DEL]
//
//                    // START 2019/07/08 K.Kitachi [QC#51198, ADD]
//                    BigDecimal svcMachMstrPk = machineLocationProcess(rs, svcMachMstr);
//                    if (!hasValue(svcMachMstrPk)) {
//                        return false;
//                    }
//                    // END 2019/07/08 K.Kitachi [QC#51198, ADD]
//                }
//            } else {
            if (shipTo == null) {
            // END 2019/08/08 K.Kitachi [QC#52391, MOD]
                // ERROR : not found customer
                updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
                sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0140E, new String[]{"Machine Master " + serNum, "Customer Information" }));
                return false;
            }

            // find FSR
            FSRTMsg fsrTMsg = new FSRTMsg();
            setValue(fsrTMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(fsrTMsg.fsrNum, svcTaskTMsg.fsrNum);
            fsrTMsg = (FSRTMsg) EZDTBLAccessor.findByKey(fsrTMsg);

            // call FSR update API(NSZC043001)
            String fsrNum = fsrTMsg.fsrNum.getValue();
            String svcPblmTpCd = fsrTMsg.svcPblmTpCd.getValue();
            String svcCallSrcTpCd = fsrTMsg.svcCallSrcTpCd.getValue();
            BigDecimal svcMachMstrPk = (BigDecimal) svcMachMstr.get("SVC_MACH_MSTR_PK");
            String pblmCd = rs.getString("CRS_SVC_PBLM_CD");
            String srCmntTxt = rs.getString("CRS_SVC_SR_CMNT_TXT");
            // START 2019/09/10 K.Fujimoto [QC#51315, MOD]
            String futSvcTs = rs.getString("CRS_SVC_CRAT_TS_ORIG_TXT");
            // NSZC043001PMsg pMsg = createNSZC043001PMsgUpdateFsr(fsrNum, svcPblmTpCd, svcCallSrcTpCd, svcMachMstrPk, svcTaskNumList, pblmCd, srCmntTxt);
            NSZC043001PMsg pMsg = createNSZC043001PMsgUpdateFsr(fsrNum, svcPblmTpCd, svcCallSrcTpCd, svcMachMstrPk, svcTaskNumList, pblmCd, srCmntTxt, futSvcTs);
            // END   2019/09/10 K.Fujimoto [QC#51315, MOD]
            // START 2016/06/15 [QC#9640, MOD]
            if (!callApiNSZC043001(pMsg)) {
                rollback();

                updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);

                S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                sendRequestDataErrorMail(createMailTemplateParamMap(rs, msgId, msgPrms));
                return false;
            }
            // END 2016/06/15 [QC#9640, MOD]

            // set Task# For Send Task API
            // mod start 2016/07/04 CSA Defect#9677
            this.sendSvcTaskNum = pMsg.taskList.no(0).svcTaskNum.getValue();
            // mod end 2016/07/04 CSA Defect#9677
        }

        // update CRS_SVC_RCV_RQST_STAGE
        updateCrsSvcRcvRqstStage(rs, PROC_STS.COMPLEATED, null, null);

        return true;
    }

    private boolean isFsrVisitCanUpdate(FSR_VISITTMsg tMsg) {

        if (SVC_TASK_STS.COMPLETED.equals(tMsg.fsrVisitStsCd.getValue())) {
            return false;
        }

        if (SVC_TASK_STS.CLOSED.equals(tMsg.fsrVisitStsCd.getValue())) {
            return false;
        }

        if (SVC_TASK_STS.CANCELLED.equals(tMsg.fsrVisitStsCd.getValue())) {
            return false;
        }

        return true;
    }

    private boolean cancelFsr(ResultSet rs) throws SQLException {

        // find SVC_TASK by SR No.
        SVC_TASKTMsg svcTaskTMsg = findSvcTaskBySRNo(rs.getString("CRS_SVC_SR_NUM"));
        if (svcTaskTMsg == null) {
            // ERROR : SR No. is not found.
            String srNo = rs.getString("CRS_SVC_SR_NUM");
            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0146E, new String[]{"SR No " + srNo }));
            return false;
        }

        // find FSR_VISIT
        List<FSR_VISITTMsg> fsrVisitTMsgList = new ArrayList<FSR_VISITTMsg>();

        FSR_VISITTMsg inMsg = new FSR_VISITTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("fsrNum01", svcTaskTMsg.fsrNum.getValue());
        inMsg.setConditionValue("svcTaskNum01", svcTaskTMsg.svcTaskNum.getValue());
        FSR_VISITTMsgArray tMsgArray = (FSR_VISITTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {

            FSR_VISITTMsg fsrVisitTMsg = tMsgArray.no(0);
            fsrVisitTMsgList.add(fsrVisitTMsg);

            do {
                if (!hasValue(fsrVisitTMsg.nextFsrVisitNum) || !SVC_TASK_STS.COMPLETED.equals(fsrVisitTMsg.fsrVisitStsCd.getValue())) {
                    break;
                }

                // find follow-up record
                inMsg = new FSR_VISITTMsg();
                inMsg.setSQLID("005");
                inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                inMsg.setConditionValue("fsrNum01", fsrVisitTMsg.fsrNum.getValue());
                inMsg.setConditionValue("fsrVisitNum01", fsrVisitTMsg.nextFsrVisitNum.getValue());

                tMsgArray = (FSR_VISITTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
                if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
                    fsrVisitTMsg = tMsgArray.no(0);
                    fsrVisitTMsgList.add(fsrVisitTMsg);
                }

            } while(tMsgArray != null && tMsgArray.getValidCount() > 0);

            if (!isFsrVisitCanUpdate(fsrVisitTMsg)) {
                // ERROR : FSR_VISIT cannot update.
                String fsrNo = fsrVisitTMsg.fsrNum.getValue();
                updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);
                sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0144E, new String[]{"FSR No " + fsrNo, "update" }));
                return false;
            }

            String svcTaskNum = svcTaskTMsg.svcTaskNum.getValue();

            boolean isCancelFsr = true;
            String fsrNum = svcTaskTMsg.fsrNum.getValue();
            SVC_TASKTMsg inSvcTaskMsg = new SVC_TASKTMsg();
            inSvcTaskMsg.setSQLID("005");
            inSvcTaskMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            inSvcTaskMsg.setConditionValue("fsrNum01", fsrNum);
            SVC_TASKTMsgArray svcTaskMsgArray = (SVC_TASKTMsgArray) EZDTBLAccessor.findByCondition(inSvcTaskMsg);
            for (int i = 0; i < svcTaskMsgArray.getValidCount(); i++) {
                SVC_TASKTMsg svcTaskMsg = svcTaskMsgArray.no(i);
                if (svcTaskMsgArray.getValidCount() > 1 && !SVC_TASK_STS.CANCELLED.equals(svcTaskMsg.svcTaskStsCd.getValue())) {
                    isCancelFsr = false;
                }
            }

            if (isCancelFsr) {
                // call FSR update API(NSZC043001)
                NSZC043001PMsg pMsg = createNSZC043001PMsgCancelFsr(fsrNum, svcTaskNum);
                // START 2016/06/15 [QC#9640, MOD]
                if (!callApiNSZC043001(pMsg)) {
                    rollback();

                    updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);

                    S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    sendRequestDataErrorMail(createMailTemplateParamMap(rs, msgId, msgPrms));
                    return false;
                }
                // END 2016/06/15 [QC#9640, MOD]

            } else {
                // call Add Task API(NSZC045001)
                String pblmCd = rs.getString("CRS_SVC_PBLM_CD");
                String srCmntTxt = rs.getString("CRS_SVC_SR_CMNT_TXT");
                NSZC045001PMsg pMsg = createNSZC045001PMsg(fsrNum, svcTaskNum, pblmCd, srCmntTxt);
                // START 2016/06/15 [QC#9640, MOD]
                if (!callApiNSZC045001(pMsg)) {
                    rollback();

                    updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);

                    S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    sendRequestDataErrorMail(createMailTemplateParamMap(rs, msgId, msgPrms));
                    return false;
                }
                // END 2016/06/15 [QC#9640, MOD]
            }
            // set Task# For Send Task API
            // mod start 2016/07/04 CSA Defect#9677
            this.sendSvcTaskNum = svcTaskNum;
            // mod end 2016/07/04 CSA Defect#9677
        }

        // update CRS_SVC_RCV_RQST_STAGE
        updateCrsSvcRcvRqstStage(rs, PROC_STS.COMPLEATED, null, null);

        return true;
    }

    private SVC_TASKTMsg findSvcTaskBySRNo(String crsSvcSrNum) {
        // find SVC_TASK by SR No.
        SVC_TASKTMsg svcTaskTMsg = new SVC_TASKTMsg();
        svcTaskTMsg.setSQLID("010");
        svcTaskTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        svcTaskTMsg.setConditionValue("crsSvcSrNum01", crsSvcSrNum);
        SVC_TASKTMsgArray svcTaskTMsgArray = (SVC_TASKTMsgArray) EZDTBLAccessor.findByCondition(svcTaskTMsg);
        if (svcTaskTMsgArray != null && svcTaskTMsgArray.getValidCount() > 0) {
            return svcTaskTMsgArray.no(0);
        }
        return null;
    }

    // START 2019/05/07 [QC#50119, ADD]
    private String getSvcIstlReqFlg(String crsSvcTaskTpCd) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", this.glblCmpyCd);
        prmMap.put("crsSvcTaskTp", crsSvcTaskTpCd);

        return (String) ssmClient.queryObject("getSvcIstlReqFlg", prmMap);
    }
    // END 2019/05/07 [QC#50119, ADD]

    private Map<String, Object> getSvcMachMstr(String serNum) {
        List<String> svcMachMstrStsCdList = new ArrayList<String>();
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.INSTALLED);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);

        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", this.glblCmpyCd);
        prmMap.put("serNum", serNum);
        prmMap.put("svcMachMstrStsCdList", svcMachMstrStsCdList);

        return (Map<String, Object>) ssmClient.queryObject("findSvcMachMstr", prmMap);
    }

    private Map<String, Object> findShipToCustByCurLocAcctNum(String curLocAcctNum, String curLocNum) throws SQLException {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", this.glblCmpyCd);
        prmMap.put("sellToCustCd", curLocAcctNum);
        prmMap.put("shipToCustCd", curLocNum);
        prmMap.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (Map<String, Object>) ssmClient.queryObject("findShipToCustByCurLocAcctNum", prmMap);
    }

    private Map<String, Object> findShipToCustByAddress(ResultSet rs) throws SQLException {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", this.glblCmpyCd);
        prmMap.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        prmMap.put("locNm", rs.getString("CRS_SVC_CUST_NM"));
        prmMap.put("firstLineAddr", rs.getString("CRS_SVC_FIRST_LINE_ADDR"));
        prmMap.put("scdLineAddr", rs.getString("CRS_SVC_SCD_LINE_ADDR"));
        prmMap.put("thirdLineAddr", rs.getString("CRS_SVC_THIRD_LINE_ADDR"));
        prmMap.put("frthLineAddr", rs.getString("CRS_SVC_FRTH_LINE_ADDR"));
        prmMap.put("ctyLineAddr", rs.getString("CRS_SVC_CTY_ADDR"));
        prmMap.put("stCd", rs.getString("CRS_SVC_ST_CD"));
        prmMap.put("postCd", rs.getString("CRS_SVC_POST_CD"));

        return (Map<String, Object>) ssmClient.queryObject("findShipToCustByAddress", prmMap);
    }

    // START 2019/08/08 K.Kitachi [QC#52391, DEL]
//    private boolean compareAddress(ResultSet rs, Map<String, Object> shipTo) throws SQLException {
//
//        if (!isMatchObject(rs.getString("CRS_SVC_CUST_NM"), (String) shipTo.get("LOC_NM"))) {
//            return false;
//        }
//
//        if (!isMatchObject(rs.getString("CRS_SVC_FIRST_LINE_ADDR"), (String) shipTo.get("FIRST_LINE_ADDR"))) {
//            return false;
//        }
//
//        if (!isMatchObject(rs.getString("CRS_SVC_SCD_LINE_ADDR"), (String) shipTo.get("SCD_LINE_ADDR"))) {
//            return false;
//        }
//
//        if (!isMatchObject(rs.getString("CRS_SVC_THIRD_LINE_ADDR"), (String) shipTo.get("THIRD_LINE_ADDR"))) {
//            return false;
//        }
//
//        if (!isMatchObject(rs.getString("CRS_SVC_FRTH_LINE_ADDR"), (String) shipTo.get("FRTH_LINE_ADDR"))) {
//            return false;
//        }
//
//        if (!isMatchObject(rs.getString("CRS_SVC_CTY_ADDR"), (String) shipTo.get("CTY_ADDR"))) {
//            return false;
//        }
//
//        if (!isMatchObject(rs.getString("CRS_SVC_ST_CD"), (String) shipTo.get("ST_CD"))) {
//            return false;
//        }
//
//        String rsPostCd = rs.getString("CRS_SVC_POST_CD");
//        if (rsPostCd != null && rsPostCd.length() > POST_CD_LENGTH) {
//            rsPostCd = rsPostCd.substring(0, POST_CD_LENGTH);
//        }
//        String shipToPostCd = (String) shipTo.get("POST_CD");
//        if (shipToPostCd != null && shipToPostCd.length() > POST_CD_LENGTH) {
//            shipToPostCd = shipToPostCd.substring(0, POST_CD_LENGTH);
//        }
//        if (!isMatchObject(rsPostCd, shipToPostCd)) {
//            return false;
//        }
//
//        return true;
//    }
//
//    private static boolean isMatchObject(String str1, String str2) {
//        if (hasValue(str1) && !hasValue(str2)) {
//            return false;
//        }
//        if (!hasValue(str1) && hasValue(str2)) {
//            return false;
//        }
//        if (hasValue(str1) && hasValue(str2)) {
//            if (!str1.equals(str2)) {
//                return false;
//            }
//        }
//        return true;
//    }
    // END 2019/08/08 K.Kitachi [QC#52391, DEL]

    private String getShipToCustCdFromLocNum(String locNum) {
        SHIP_TO_CUSTTMsg inMsg = new SHIP_TO_CUSTTMsg();
        inMsg.setSQLID("048");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("locNum01", locNum);
        SHIP_TO_CUSTTMsgArray tMsgAry = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgAry.getValidCount() > 0) {
            return tMsgAry.no(0).shipToCustCd.getValue();
        }
        return null;
    }

    private String getBillToCustCdFromLocNum(String locNum) {
        BILL_TO_CUSTTMsg inMsg = new BILL_TO_CUSTTMsg();
        inMsg.setSQLID("060");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("locNum01", locNum);
        BILL_TO_CUSTTMsgArray tMsgAry = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgAry.getValidCount() > 0) {
            return tMsgAry.no(0).billToCustCd.getValue();
        }
        return null;
    }

    // START 2016/06/15 [QC#9640, MOD]
    private boolean callApiNMZC001001(NMZC001001PMsg pMsg) {
        NMZC001001 api = new NMZC001001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return false;
        }

        // START 2019/07/08 K.Kitachi [QC#51201, ADD]
        for (int idx = 0; idx < pMsg.NMZC001002PMsg.getValidCount(); idx++) {
            if (S21ApiUtil.isXxMsgId(pMsg.NMZC001002PMsg.no(idx))) {
                return false;
            }
        }
        // END 2019/07/08 K.Kitachi [QC#51201, ADD]

        return true;
    }
    // END 2016/06/15 [QC#9640, MOD]

    private NMZC001001PMsg createNMZC001001PMsg(ResultSet rs) throws SQLException {

        NMZC001001PMsg pMsg = new NMZC001001PMsg();
        setValue(pMsg.xxModeCd, NMZC001001Constant.PROCESS_MODE_CUST_CRAT_ACTV);
        setValue(pMsg.slsDt, this.slsDt);
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.dsAcctNm, rs.getString("CRS_SVC_CUST_NM"));
        setValue(pMsg.dsAcctItrlFlg, ZYPConstant.FLG_OFF_N);

// START 2018/05/18 [QC#20824, MOD]
        //setValue(pMsg.dsAcctClsCd, DS_ACCT_CLS.CANON);
        setValue(pMsg.dsAcctClsCd, DS_ACCT_CLS.RETAIL_ACCT);
// END   2018/05/18 [QC#20824, MOD]

// START 2016/03/25 [QC#5457, MOD]
        setValue(pMsg.coaChCd, this.defCoaChCd);
        setValue(pMsg.coaAfflCd, this.defCoaAfflCd);
// END   2016/03/25 [QC#5457, MOD]
        setValue(pMsg.xxAcctCrFlg, ZYPConstant.FLG_OFF_N);
        setValue(pMsg.xxAcctCltFlg, ZYPConstant.FLG_OFF_N);

        pMsg.NMZC001002PMsg.setValidCount(NMZC001002PMSG_ARRAY_LENGTH);
        // mod start 2016/08/29 CSA Defect#13560
        setValue(pMsg.NMZC001002PMsg.no(0).xxPrinFlg, ZYPConstant.FLG_ON_Y);
        // mod End 2016/08/29 CSA Defect#13560
        setValue(pMsg.NMZC001002PMsg.no(0).xxLocXrefInfoFlg, ZYPConstant.FLG_OFF_N);
        setValue(pMsg.NMZC001002PMsg.no(0).xxLocDunsFlg, ZYPConstant.FLG_OFF_N);
        setValue(pMsg.NMZC001002PMsg.no(0).xxLocBillToCrInfoFlg, ZYPConstant.FLG_OFF_N);
        setValue(pMsg.NMZC001002PMsg.no(0).xxLocBillToCltInfoFlg, ZYPConstant.FLG_OFF_N);
        setValue(pMsg.NMZC001002PMsg.no(0).xxLocBillToTaxFlg_B, ZYPConstant.FLG_OFF_N);
        setValue(pMsg.NMZC001002PMsg.no(0).xxLocShipToTaxFlg_S, ZYPConstant.FLG_OFF_N);
        setValue(pMsg.NMZC001002PMsg.no(0).effFromDt, this.slsDt);
        setValue(pMsg.NMZC001002PMsg.no(0).actvFlg, ZYPConstant.FLG_ON_Y);
        setValue(pMsg.NMZC001002PMsg.no(0).firstLineAddr, rs.getString("CRS_SVC_FIRST_LINE_ADDR"));
        setValue(pMsg.NMZC001002PMsg.no(0).scdLineAddr, rs.getString("CRS_SVC_SCD_LINE_ADDR"));
        setValue(pMsg.NMZC001002PMsg.no(0).thirdLineAddr, rs.getString("CRS_SVC_THIRD_LINE_ADDR"));
        setValue(pMsg.NMZC001002PMsg.no(0).frthLineAddr, rs.getString("CRS_SVC_FRTH_LINE_ADDR"));
        setValue(pMsg.NMZC001002PMsg.no(0).ctyAddr, rs.getString("CRS_SVC_CTY_ADDR"));
        // START 2019/11/18 E.Kameishi [QC#51561, MOD]
        // START 2019/07/08 K.Kitachi [QC#51201, ADD]
        //setValue(pMsg.NMZC001002PMsg.no(0).cntyNm, rs.getString("CRS_SVC_CNTY_NM"));
        setValue(pMsg.NMZC001002PMsg.no(0).cntyNm, getCntyNm(rs));
        // END 2019/07/08 K.Kitachi [QC#51201, ADD]
        // END 2019/11/18 E.Kameishi [QC#51561, MOD]
        setValue(pMsg.NMZC001002PMsg.no(0).stCd, rs.getString("CRS_SVC_ST_CD"));
        setValue(pMsg.NMZC001002PMsg.no(0).postCd, rs.getString("CRS_SVC_POST_CD"));
        setValue(pMsg.NMZC001002PMsg.no(0).locNm, rs.getString("CRS_SVC_CUST_NM"));
        setValue(pMsg.NMZC001002PMsg.no(0).ctryCd, this.defCtryCd);

        // START 2019/09/25 K.Kitachi [QC#53713, ADD]
        // Bill to
        setValue(pMsg.NMZC001002PMsg.no(0).billToCustFlg, ZYPConstant.FLG_ON_Y);
        setValue(pMsg.NMZC001002PMsg.no(0).effFromDt_B, this.slsDt);
        setValue(pMsg.NMZC001002PMsg.no(0).primUsgFlg_B, ZYPConstant.FLG_ON_Y);
        setValue(pMsg.NMZC001002PMsg.no(0).coaChCd_B, this.defCoaChCd);
        setValue(pMsg.NMZC001002PMsg.no(0).coaAfflCd_B, this.defCoaAfflCd);
        setValue(pMsg.NMZC001002PMsg.no(0).lineBizTpCd_B, this.deflineBizTpCd);

        // Ship to
        setValue(pMsg.NMZC001002PMsg.no(0).shipToCustFlg, ZYPConstant.FLG_ON_Y);
        setValue(pMsg.NMZC001002PMsg.no(0).effFromDt_S, this.slsDt);
        setValue(pMsg.NMZC001002PMsg.no(0).primUsgFlg_S, ZYPConstant.FLG_ON_Y);
        setValue(pMsg.NMZC001002PMsg.no(0).coaChCd_S, this.defCoaChCd);
        setValue(pMsg.NMZC001002PMsg.no(0).coaAfflCd_S, this.defCoaAfflCd);
        setValue(pMsg.NMZC001002PMsg.no(0).lineBizTpCd_S, this.deflineBizTpCd);
        // END 2019/09/25 K.Kitachi [QC#53713, ADD]

        // START 2019/09/25 K.Kitachi [QC#53713, DEL]
//        setValue(pMsg.NMZC001002PMsg.no(1).billToCustFlg, ZYPConstant.FLG_ON_Y);
//        setValue(pMsg.NMZC001002PMsg.no(1).xxLocXrefInfoFlg, ZYPConstant.FLG_OFF_N);
//        setValue(pMsg.NMZC001002PMsg.no(1).xxLocDunsFlg, ZYPConstant.FLG_OFF_N);
//        setValue(pMsg.NMZC001002PMsg.no(1).xxLocBillToCrInfoFlg, ZYPConstant.FLG_OFF_N);
//        setValue(pMsg.NMZC001002PMsg.no(1).xxLocBillToCltInfoFlg, ZYPConstant.FLG_OFF_N);
//        setValue(pMsg.NMZC001002PMsg.no(1).xxLocBillToTaxFlg_B, ZYPConstant.FLG_OFF_N);
//        setValue(pMsg.NMZC001002PMsg.no(1).xxLocShipToTaxFlg_S, ZYPConstant.FLG_OFF_N);
//        setValue(pMsg.NMZC001002PMsg.no(1).effFromDt, this.slsDt);
//        setValue(pMsg.NMZC001002PMsg.no(1).actvFlg, ZYPConstant.FLG_ON_Y);
//        setValue(pMsg.NMZC001002PMsg.no(1).firstLineAddr, rs.getString("CRS_SVC_FIRST_LINE_ADDR"));
//        setValue(pMsg.NMZC001002PMsg.no(1).scdLineAddr, rs.getString("CRS_SVC_SCD_LINE_ADDR"));
//        setValue(pMsg.NMZC001002PMsg.no(1).thirdLineAddr, rs.getString("CRS_SVC_THIRD_LINE_ADDR"));
//        setValue(pMsg.NMZC001002PMsg.no(1).frthLineAddr, rs.getString("CRS_SVC_FRTH_LINE_ADDR"));
//        setValue(pMsg.NMZC001002PMsg.no(1).ctyAddr, rs.getString("CRS_SVC_CTY_ADDR"));
//        // START 2019/07/08 K.Kitachi [QC#51201, ADD]
//        setValue(pMsg.NMZC001002PMsg.no(1).cntyNm, rs.getString("CRS_SVC_CNTY_NM"));
//        // END 2019/07/08 K.Kitachi [QC#51201, ADD]
//        setValue(pMsg.NMZC001002PMsg.no(1).stCd, rs.getString("CRS_SVC_ST_CD"));
//        setValue(pMsg.NMZC001002PMsg.no(1).postCd, rs.getString("CRS_SVC_POST_CD"));
//        setValue(pMsg.NMZC001002PMsg.no(1).locNm, rs.getString("CRS_SVC_CUST_NM"));
//        setValue(pMsg.NMZC001002PMsg.no(1).effFromDt_B, this.slsDt);
//        setValue(pMsg.NMZC001002PMsg.no(1).effThruDt_B, "");
//        setValue(pMsg.NMZC001002PMsg.no(1).primUsgFlg_B, ZYPConstant.FLG_ON_Y);
//// START 2016/03/25 [QC#5457, MOD]
//        setValue(pMsg.NMZC001002PMsg.no(1).coaChCd_B, this.defCoaChCd);
//        setValue(pMsg.NMZC001002PMsg.no(1).coaAfflCd_B, this.defCoaAfflCd);
//// END   2016/03/25 [QC#5457, MOD]
//        setValue(pMsg.NMZC001002PMsg.no(1).lineBizTpCd_B, this.deflineBizTpCd);
//        setValue(pMsg.NMZC001002PMsg.no(1).ctryCd, this.defCtryCd);
//
//        setValue(pMsg.NMZC001002PMsg.no(2).shipToCustFlg, ZYPConstant.FLG_ON_Y);
//        setValue(pMsg.NMZC001002PMsg.no(2).xxLocXrefInfoFlg, ZYPConstant.FLG_OFF_N);
//        setValue(pMsg.NMZC001002PMsg.no(2).xxLocDunsFlg, ZYPConstant.FLG_OFF_N);
//        setValue(pMsg.NMZC001002PMsg.no(2).xxLocBillToCrInfoFlg, ZYPConstant.FLG_OFF_N);
//        setValue(pMsg.NMZC001002PMsg.no(2).xxLocBillToCltInfoFlg, ZYPConstant.FLG_OFF_N);
//        setValue(pMsg.NMZC001002PMsg.no(2).xxLocBillToTaxFlg_B, ZYPConstant.FLG_OFF_N);
//        setValue(pMsg.NMZC001002PMsg.no(2).xxLocShipToTaxFlg_S, ZYPConstant.FLG_OFF_N);
//        setValue(pMsg.NMZC001002PMsg.no(2).effFromDt, this.slsDt);
//        setValue(pMsg.NMZC001002PMsg.no(2).actvFlg, ZYPConstant.FLG_ON_Y);
//        setValue(pMsg.NMZC001002PMsg.no(2).firstLineAddr, rs.getString("CRS_SVC_FIRST_LINE_ADDR"));
//        setValue(pMsg.NMZC001002PMsg.no(2).scdLineAddr, rs.getString("CRS_SVC_SCD_LINE_ADDR"));
//        setValue(pMsg.NMZC001002PMsg.no(2).thirdLineAddr, rs.getString("CRS_SVC_THIRD_LINE_ADDR"));
//        setValue(pMsg.NMZC001002PMsg.no(2).frthLineAddr, rs.getString("CRS_SVC_FRTH_LINE_ADDR"));
//        setValue(pMsg.NMZC001002PMsg.no(2).ctyAddr, rs.getString("CRS_SVC_CTY_ADDR"));
//        // START 2019/07/08 K.Kitachi [QC#51201, ADD]
//        setValue(pMsg.NMZC001002PMsg.no(2).cntyNm, rs.getString("CRS_SVC_CNTY_NM"));
//        // END 2019/07/08 K.Kitachi [QC#51201, ADD]
//        setValue(pMsg.NMZC001002PMsg.no(2).stCd, rs.getString("CRS_SVC_ST_CD"));
//        setValue(pMsg.NMZC001002PMsg.no(2).postCd, rs.getString("CRS_SVC_POST_CD"));
//        setValue(pMsg.NMZC001002PMsg.no(2).locNm, rs.getString("CRS_SVC_CUST_NM"));
//        setValue(pMsg.NMZC001002PMsg.no(2).effFromDt_S, this.slsDt);
//        setValue(pMsg.NMZC001002PMsg.no(2).primUsgFlg_S, ZYPConstant.FLG_OFF_N);
//// START 2016/03/25 [QC#5457, MOD]
//        setValue(pMsg.NMZC001002PMsg.no(2).coaChCd_B, this.defCoaChCd);
//        setValue(pMsg.NMZC001002PMsg.no(2).coaAfflCd_B, this.defCoaAfflCd);
//// END   2016/03/25 [QC#5457, MOD]
//        setValue(pMsg.NMZC001002PMsg.no(2).lineBizTpCd_S, this.deflineBizTpCd);
//        setValue(pMsg.NMZC001002PMsg.no(2).ctryCd, this.defCtryCd);
        // END 2019/09/25 K.Kitachi [QC#53713, DEL]

        setValue(pMsg.cltPtfoPk, BigDecimal.ZERO);

        return pMsg;
    }
    // START 2019/11/18 E.Kameishi [QC#51561, ADD]
    @SuppressWarnings("unchecked")
    private String getCntyNm(ResultSet rs) throws SQLException {

        CNTYTMsg inMsg = new CNTYTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("cntyNm01", rs.getString("CRS_SVC_CNTY_NM"));
        inMsg.setConditionValue("stCd01", rs.getString("CRS_SVC_ST_CD"));
        
        CNTYTMsgArray tMsgAry = (CNTYTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgAry.getValidCount() != 1) {
            Map<String, Object> prmMap = new HashMap<String, Object>();
            prmMap.put("glblCmpyCd", this.glblCmpyCd);
            prmMap.put("crsSvcPostCd", rs.getString("CRS_SVC_POST_CD"));
            prmMap.put("crsSvcCtyAddr", rs.getString("CRS_SVC_CTY_ADDR"));
            prmMap.put("crsSvcStCd", rs.getString("CRS_SVC_ST_CD"));
            
            List<String> cntyNm9digList = (List<String>) ssmClient.queryObjectList("getCntyNm9dig", prmMap);
            
            if (cntyNm9digList != null && cntyNm9digList.size() == 1) {
                return (String) cntyNm9digList.get(0);
            } else if (cntyNm9digList != null && cntyNm9digList.size() == 0){
                List<String> cntyNm5digList = (List<String>) ssmClient.queryObjectList("getCntyNm5dig", prmMap);
                
                if (cntyNm5digList != null && cntyNm5digList.size() == 1) {
                    return (String) cntyNm5digList.get(0);
                }
            }
        }
        return rs.getString("CRS_SVC_CNTY_NM");
        
    }
    // END 2019/11/18 E.Kameishi [QC#51561, ADD]
    // START 2016/06/15 [QC#9640, MOD]
    private boolean callApiNSZC001001(NSZC001001PMsg pMsg) {

        NSZC001001 api = new NSZC001001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return false;
        }
        return true;
    }
    // END 2016/06/15 [QC#9640, MOD]

    private NSZC001001PMsg createNSZC001001PMsg(ResultSet rs, String acctNum, String billToCustCd, String shipToCustCd) throws SQLException {
        NSZC001001PMsg pMsg = new NSZC001001PMsg();
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.slsDt, this.slsDt);
        setValue(pMsg.xxModeCd, ProcessMode.INSERT_MACHINE_IN_FIELD.code);
        setValue(pMsg.serNum, rs.getString("SER_NUM"));
        setValue(pMsg.mdseCd, rs.getString("CRS_SVC_MDSE_CD"));
        setValue(pMsg.custMachCtrlNum, rs.getString("SER_NUM"));
        setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.INSTALLED);
        setValue(pMsg.stkStsCd, STK_STS.GOOD);
        setValue(pMsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
        setValue(pMsg.effFromDt, this.slsDt);
        setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.AT_CUSTOMER);
        setValue(pMsg.prntSerNum, rs.getString("SER_NUM"));
        setValue(pMsg.svcMachTrxTpCd, SVC_MACH_TRX_TP.PURCHASE_CSA);
        setValue(pMsg.svcMachQty, BigDecimal.ONE);
        setValue(pMsg.svcMachTpCd, SVC_MACH_TP.MACHINE);

        pMsg.xxCmptMachList.setValidCount(1);
        setValue(pMsg.xxCmptMachList.no(0).mdseCd, rs.getString("CRS_SVC_MDSE_CD"));
        setValue(pMsg.xxCmptMachList.no(0).svcMachTpCd, SVC_MACH_TP.MACHINE);

        setValue(pMsg.ownrAcctNum, acctNum);
        setValue(pMsg.ownrLocNum, billToCustCd);
        setValue(pMsg.billToAcctNum, acctNum);
        setValue(pMsg.billToLocNum, billToCustCd);
        setValue(pMsg.curLocAcctNum, acctNum);
        setValue(pMsg.curLocNum, shipToCustCd);

        // START 2016/09/05 T.Tomita [QC#12471, ADD]
        setValue(pMsg.svcByLineBizTpCd, this.svcByLineBizTpCd);
        // END 2016/09/05 T.Tomita [QC#12471, ADD]

        // START 2023/10/06 K.Watanabe [QC#54186, ADD]
        if (LINE_BIZ_TP.ESS.equals(this.svcByLineBizTpCd)) {
            setValue(pMsg.istlBySvcPrvdPtyCd, SVC_PRVD_PTY.ESS_DIRECT);
            setValue(pMsg.svcBySvcPrvdPtyCd, SVC_PRVD_PTY.ESS_DIRECT);
        } else if (LINE_BIZ_TP.LFS.equals(this.svcByLineBizTpCd)) {
            setValue(pMsg.istlBySvcPrvdPtyCd, SVC_PRVD_PTY.LFS_DIRECT);
            setValue(pMsg.svcBySvcPrvdPtyCd, SVC_PRVD_PTY.LFS_DIRECT);
        } else if (LINE_BIZ_TP.PPS.equals(this.svcByLineBizTpCd)) {
            setValue(pMsg.istlBySvcPrvdPtyCd, SVC_PRVD_PTY.PPS_DIRECT);
            setValue(pMsg.svcBySvcPrvdPtyCd, SVC_PRVD_PTY.PPS_DIRECT);
        }
        // END 2023/10/06 K.Watanabe [QC#54186, ADD]

        // START 2019/05/07 [QC#50119, ADD]
        setValue(pMsg.sldByLineBizTpCd, LINE_BIZ_TP.ESS);
        setValue(pMsg.istlDt, ZYPDateUtil.getCurrentSystemTime(ZYPDateUtil.TYPE_YYYYMMDD));

        // Install Call
        String svcIstlReqFlg = getSvcIstlReqFlg(rs.getString("CRS_SVC_TASK_TP_CD"));
        if (ZYPConstant.FLG_ON_Y.equals(svcIstlReqFlg)) {
            setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
            pMsg.istlDt.clear();
        }
        // END 2019/05/07 [QC#50119, ADD]

        return pMsg;
    }

    // START 2016/06/15 [QC#9640, MOD]
    private boolean callApiNSZC043001(NSZC043001PMsg pMsg) throws SQLException {

        NSZC043001 api = new NSZC043001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return false;
        }
        return true;
    }
    // END 2016/06/15 [QC#9640, MOD]

    // START 2019/09/10 K.Fujimoto [QC#51315, MOD]
    // private NSZC043001PMsg createNSZC043001PMsgRegisterFsr(
    //        BigDecimal svcMachMstrPk, String dsSvcCallTpCd, String cratTsDescTxt, String pblmCdDescTxt, String srCmntTxt) {
     private NSZC043001PMsg createNSZC043001PMsgRegisterFsr(
            BigDecimal svcMachMstrPk, String dsSvcCallTpCd, String cratTsDescTxt, String pblmCdDescTxt, String srCmntTxt, String futSvcTsDescTxt) {
    // END   2019/09/10 K.Fujimoto [QC#51315, MOD]
        NSZC043001PMsg pMsg = new NSZC043001PMsg();

        String svcTaskRcvTs = ZYPDateUtil.DateFormatter(cratTsDescTxt, DT_FORMAT_RQST_TS, DT_FORMAT_DT);
        String svcTaskRcvDt = svcTaskRcvTs.substring(0, DT_FORMAT_DT_POS_DATE);
        String svcTaskRcvTm = svcTaskRcvTs.substring(DT_FORMAT_DT_POS_DATE);
        // START 2019/09/10 K.Fujimoto [QC#51315, ADD]
        if (hasValue(futSvcTsDescTxt)) {
            String futSvcTs = ZYPDateUtil.DateFormatter(futSvcTsDescTxt, DT_FORMAT_RQST_TS, DT_FORMAT_DT);
            String futSvcDt = futSvcTs.substring(0, DT_FORMAT_DT_POS_DATE);
            String futSvcTm = futSvcTs.substring(DT_FORMAT_DT_POS_DATE);
            setValue(pMsg.taskList.no(0).futSvcDt, futSvcDt);
            setValue(pMsg.taskList.no(0).futSvcTm, futSvcTm);
            setValue(pMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
            pMsg.taskList.setValidCount(1);
        }
        // END   2019/09/10 K.Fujimoto [QC#51315, ADD]
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.xxModeCd, NSZC043001Constant.MODE_CREATE_FSR);
        // mod start 2016/05/24 CSA Defect#8809
        setValue(pMsg.slsDt, this.slsDt);
        // mod end 2016/05/24 CSA Defect#8809
        setValue(pMsg.userId, BATCH_PROGRAM_ID);
        setValue(pMsg.bypsPrfTechFlg, ZYPConstant.FLG_OFF_N);
        setValue(pMsg.svcMachMstrPk, svcMachMstrPk);
        setValue(pMsg.dsSvcCallTpCd, dsSvcCallTpCd);
        setValue(pMsg.svcBillTpCd, SVC_BILL_TP._8_NO_CHARGE);
        setValue(pMsg.svcTaskRcvDt, svcTaskRcvDt);
        setValue(pMsg.svcTaskRcvTm, svcTaskRcvTm);
        setValue(pMsg.prtChrgFlg, ZYPConstant.FLG_OFF_N);
        setValue(pMsg.svcLborChrgFlg, ZYPConstant.FLG_OFF_N);
// START 2016/03/25 [QC#5457, MOD]
        setValue(pMsg.svcPblmTpCd, this.defSvcPblmCd);
        setValue(pMsg.svcCallSrcTpCd, this.defSvcCallTpCd);
// END   2016/03/25 [QC#5457, MOD]
        setValue(pMsg.billToCustUpdFlg, ZYPConstant.FLG_OFF_N);
        setValue(pMsg.shipToCustUpdFlg, ZYPConstant.FLG_OFF_N);
        // mod start 2016/07/04 CSA Defect#9677
        setValue(pMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // mod end 2016/07/04 CSA Defect#9677
        // START 2016/10/13 T.Kanasaka [QC#15143, ADD]
        setValue(pMsg.custEmlAddr, this.defCustEmlAddr);
        // END 2016/10/13 T.Kanasaka [QC#15143, ADD]

        // mod start 2019/10/07 QC#53406
        if (hasValue(pblmCdDescTxt) || hasValue(srCmntTxt)) {
            pMsg.svcMemoList.setValidCount(1);
            // START 2016/03/25 [QC#5457, MOD]
            setValue(pMsg.svcMemoList.no(0).svcMemoTpCd, this.defSvcMemoTpCd);
            // END 2016/03/25 [QC#5457, MOD]
            StringBuilder sbSvcCmntTxt = new StringBuilder();
            if (hasValue(pblmCdDescTxt)) {
                sbSvcCmntTxt.append(pblmCdDescTxt);
            }
            if (hasValue(srCmntTxt)) {
                sbSvcCmntTxt.append(srCmntTxt);
            }
            if (sbSvcCmntTxt.length() > 0) {
                setValue(pMsg.svcMemoList.no(0).svcCmntTxt, sbSvcCmntTxt.toString());
            }
        }
        // mod end 2019/10/07 QC#53406

        return pMsg;
    }

     // START 2019/09/10 K.Fujimoto [QC#51315, MOD]
    //private NSZC043001PMsg createNSZC043001PMsgUpdateFsr(String fsrNum, String svcPblmTpCd, String svcCallSrcTpCd, BigDecimal svcMachMstrPk, List<String> svcTaskNumList, String pblmCdDescTxt, String srCmntTxt) {
    private NSZC043001PMsg createNSZC043001PMsgUpdateFsr(String fsrNum, String svcPblmTpCd, String svcCallSrcTpCd, BigDecimal svcMachMstrPk, List<String> svcTaskNumList, String pblmCdDescTxt, String srCmntTxt, String futSvcTsDescTxt) {
     // END   2019/09/10 K.Fujimoto [QC#51315, MOD]

        NSZC043001PMsg pMsg = new NSZC043001PMsg();

        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.xxModeCd, NSZC043001Constant.MODE_UPDATE_FSR);
        // mod start 2016/05/24 CSA Defect#8809
        setValue(pMsg.slsDt, this.slsDt);
        // mod end 2016/05/24 CSA Defect#8809
        setValue(pMsg.fsrNum, fsrNum);
        setValue(pMsg.svcPblmTpCd, svcPblmTpCd);
        setValue(pMsg.svcCallSrcTpCd, svcCallSrcTpCd);
        setValue(pMsg.userId, BATCH_PROGRAM_ID);
        setValue(pMsg.svcMachMstrPk, svcMachMstrPk);
        // mod start 2016/07/04 CSA Defect#9677
        setValue(pMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // mod end 2016/07/04 CSA Defect#9677

        int lastIndex = svcTaskNumList.size() - 1;
        setValue(pMsg.taskList.no(0).svcTaskNum, svcTaskNumList.get(lastIndex));
        setValue(pMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        // START 2019/09/10 K.Fujimoto [QC#51315, ADD]
        if (hasValue(futSvcTsDescTxt)) {
            String futSvcTs = ZYPDateUtil.DateFormatter(futSvcTsDescTxt, DT_FORMAT_RQST_TS, DT_FORMAT_DT);
            String futSvcDt = futSvcTs.substring(0, DT_FORMAT_DT_POS_DATE);
            String futSvcTm = futSvcTs.substring(DT_FORMAT_DT_POS_DATE);
            setValue(pMsg.taskList.no(0).futSvcDt, futSvcDt);
            setValue(pMsg.taskList.no(0).futSvcTm, futSvcTm);
            pMsg.taskList.setValidCount(1);
        }
        // END   2019/09/10 K.Fujimoto [QC#51315, ADD]
        pMsg.taskList.setValidCount(1);

        // mod start 2019/10/07 QC#53406
        if (hasValue(pblmCdDescTxt) || hasValue(srCmntTxt)) {
            pMsg.svcMemoList.setValidCount(1);
            // START 2016/03/25 [QC#5457, MOD]
            setValue(pMsg.svcMemoList.no(0).svcMemoTpCd, this.defSvcMemoTpCd);
            // END 2016/03/25 [QC#5457, MOD]
            StringBuilder sbSvcCmntTxt = new StringBuilder();
            if (hasValue(pblmCdDescTxt)) {
                sbSvcCmntTxt.append(pblmCdDescTxt);
            }
            if (hasValue(srCmntTxt)) {
                sbSvcCmntTxt.append(srCmntTxt);
            }
            if (sbSvcCmntTxt.length() > 0) {
                setValue(pMsg.svcMemoList.no(0).svcCmntTxt, sbSvcCmntTxt.toString());
            }
        }
        // mod end 2019/10/07 QC#53406

        return pMsg;
    }

    private NSZC043001PMsg createNSZC043001PMsgCancelFsr(String fsrNum, String svcTaskNum) {
        NSZC043001PMsg pMsg = new NSZC043001PMsg();
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.xxModeCd, NSZC043001Constant.MODE_CANCEL_FSR);
        // mod start 2016/05/24 CSA Defect#8809
        setValue(pMsg.slsDt, this.slsDt);
        // mod end 2016/05/24 CSA Defect#8809
        setValue(pMsg.userId, BATCH_PROGRAM_ID);
        setValue(pMsg.fsrNum, fsrNum);
        // mod start 2016/07/04 CSA Defect#9677
        setValue(pMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // mod end 2016/07/04 CSA Defect#9677

        setValue(pMsg.taskList.no(0).svcTaskNum, svcTaskNum);
        setValue(pMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        setValue(pMsg.taskList.no(0).schdDisptEmlFlg, ZYPConstant.FLG_ON_Y);
        pMsg.taskList.setValidCount(1);

        return pMsg;
    }

    // START 2016/06/15 [QC#9640, MOD]
    private boolean callApiNSZC045001(NSZC045001PMsg pMsg) throws SQLException {

        NSZC045001 api = new NSZC045001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return false;
        }
        return true;
    }
    // END 2016/06/15 [QC#9640, MOD]

    private NSZC045001PMsg createNSZC045001PMsg(String fsrNum, String svcTaskNum, String pblmCdDescTxt, String srCmntTxt) {
        NSZC045001PMsg pMsg = new NSZC045001PMsg();

        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.xxModeCd, NSZC045001.PROCESS_MODE_CANCEL_TASK);
        setValue(pMsg.userId, BATCH_PROGRAM_ID);
        setValue(pMsg.fsrNum, fsrNum);
        // mod start 2016/07/04 CSA Defect#9677
        setValue(pMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // mod end 2016/07/04 CSA Defect#9677

        setValue(pMsg.xxSvcTaskList.no(0).svcTaskNum, svcTaskNum);
        setValue(pMsg.xxSvcTaskList.no(0).schdDisptEmlFlg, ZYPConstant.FLG_ON_Y);
        pMsg.xxSvcTaskList.setValidCount(1);

        // mod start 2019/10/07 QC#53406
        if (hasValue(pblmCdDescTxt) || hasValue(srCmntTxt)) {
            pMsg.xxSvcMemoList.setValidCount(1);
            // START 2016/03/25 [QC#5457, MOD]
            setValue(pMsg.xxSvcMemoList.no(0).svcMemoTpCd, this.defSvcMemoTpCd);
            // END 2016/03/25 [QC#5457, MOD]
            StringBuilder sbSvcCmntTxt = new StringBuilder();
            if (hasValue(pblmCdDescTxt)) {
                sbSvcCmntTxt.append(pblmCdDescTxt);
            }
            if (hasValue(srCmntTxt)) {
                sbSvcCmntTxt.append(srCmntTxt);
            }
            if (sbSvcCmntTxt.length() > 0) {
                setValue(pMsg.xxSvcMemoList.no(0).svcCmntTxt, sbSvcCmntTxt.toString());
            }
        }
        // mod end 2019/10/07 QC#53406

        return pMsg;
    }

    private void sendRequestDataErrorMail(Map<String, String> paramMap) {

        // Get From Mail Address.
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_GROUP_KEY_FROM);
        S21MailAddress fromAddress;
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(NSBM0135E, new String[] {"FROM mail-address.", (MAIL_GROUP_ID_FROM + "/" + MAIL_GROUP_KEY_FROM) });
        }

        // Get To Mail Address.
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NSBM0135E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get Mail Template.
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_01);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NSBM0077E, new String[] {MAIL_TEMPLATE_ID_01 });
        }

        fromAddress = addrFromList.get(0);

        S21Mail mail = new S21Mail(glblCmpyCd);

        // Set From Mail Address.
        mail.setFromAddress(fromAddress);
        // Set To Mail Address.
        mail.setToAddressList(addrToList);

        // Set Parameter
        Iterator<Map.Entry<String, String>> iterator = paramMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            template.setTemplateParameter(entry.getKey(), entry.getValue());
        }

        // START 2019/02/19 [QC#30296, ADD]
        template.setTemplateParameter(MAIL_ITEM_BATCH_ID, BATCH_PROGRAM_ID);
        // END 2019/02/19 [QC#30296, ADD]

        mail.setMailTemplate(template);
        mail.setSubject(template.getSubject());
        mail.postMail();
    }

    private Map<String, String> createMailTemplateParamMap(ResultSet rs, String msgId, String[] msgPrms) throws SQLException {
        Map<String, String> prmMap = new HashMap<String, String>();
        String errDate = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);

        // ${errDate}
        prmMap.put(MAIL_ITEM_ERR_DATE, errDate);

        // ${message}
        if (msgId != null && msgPrms != null) {
            String msg = S21MessageFunc.clspGetMessage(msgId, msgPrms);
            prmMap.put(MAIL_ITEM_MESSAGE, msg);

        } else if (msgId != null && msgPrms == null) {
            String msg = S21MessageFunc.clspGetMessage(msgId);
            prmMap.put(MAIL_ITEM_MESSAGE, msg);
        }

        // ${srnum}
        prmMap.put(MAIL_ITEM_SRNUM, rs.getString("CRS_SVC_SR_NUM"));

        // ${cratts}
        prmMap.put(MAIL_ITEM_CRATTS, rs.getString("CRS_SVC_CRAT_TS_ORIG_TXT"));

        // ${custnm}
        prmMap.put(MAIL_ITEM_CUST_NM, rs.getString("CRS_SVC_CUST_NM"));

        // ${firstaddr}
        prmMap.put(MAIL_ITEM_FIRST_ADDR, rs.getString("CRS_SVC_FIRST_LINE_ADDR"));

        // ${scdaddr}
        prmMap.put(MAIL_ITEM_SCD_ADDR, rs.getString("CRS_SVC_SCD_LINE_ADDR"));

        // ${thirdaddr}
        prmMap.put(MAIL_ITEM_THIRD_ADDR, rs.getString("CRS_SVC_THIRD_LINE_ADDR"));

        // ${frthaddr}
        prmMap.put(MAIL_ITEM_FRTH_ADDR, rs.getString("CRS_SVC_FRTH_LINE_ADDR"));

        // ${ctyaddr}
        prmMap.put(MAIL_ITEM_CTY_ADDR, rs.getString("CRS_SVC_CTY_ADDR"));

        // ${stcd}
        prmMap.put(MAIL_ITEM_ST_CD, rs.getString("CRS_SVC_ST_CD"));

        // ${postcd}
        prmMap.put(MAIL_ITEM_POST_CD, rs.getString("CRS_SVC_POST_CD"));

        // ${ctrynm}
        prmMap.put(MAIL_ITEM_CTRY_NM, rs.getString("CRS_SVC_CNTY_NM"));

        // ${mdlnum}
        prmMap.put(MAIL_ITEM_MDL_NUM, rs.getString("MDL_NM"));

        // ${sernum}
        prmMap.put(MAIL_ITEM_SER_NUM, rs.getString("SER_NUM"));

        // ${pblmcmnt}
        prmMap.put(MAIL_ITEM_PBLMCMNT, rs.getString("CRS_SVC_PBLM_CMNT_TXT"));

        // ${srcmnt}
        prmMap.put(MAIL_ITEM_SRCMNT, rs.getString("CRS_SVC_SR_CMNT_TXT"));

        // ${tasknum}
        prmMap.put(MAIL_ITEM_TASK_NUM, rs.getString("CRS_SVC_TASK_NUM"));

        // ${tasksts}
        prmMap.put(MAIL_ITEM_TASK_STS, rs.getString("CRS_SVC_TASK_STS_CD"));

        // ${tasktp}
        prmMap.put(MAIL_ITEM_TASK_TP, rs.getString("CRS_SVC_TASK_TP_CD"));

        // ${fsrnum}
        prmMap.put(MAIL_ITEM_FSR_NUM, rs.getString("FSR_NUM"));

        // ${acctcd}
        prmMap.put(MAIL_ITEM_ACCT_CD, rs.getString("CRS_SVC_ACT_CD"));

        // ${rqstsq}
        prmMap.put(MAIL_ITEM_RQST_SQ, rs.getString("CRS_SVC_RQST_SQ_ORIG_TXT"));

        return prmMap;
    }

// START 2016/03/25 [QC#5457, MOD]
    private void initVarConstMap() {

        this.defCoaChCd = ZYPCodeDataUtil.getVarCharConstValue(NSBB0170_DEF_COA_CH_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.defCoaChCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {NSBB0170_DEF_COA_CH_CD });
        }

        this.defCoaAfflCd = ZYPCodeDataUtil.getVarCharConstValue(NSBB0170_DEF_COA_AFFL_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.defCoaAfflCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {NSBB0170_DEF_COA_AFFL_CD });
        }

        this.defSvcMemoTpCd = ZYPCodeDataUtil.getVarCharConstValue(NSBB0170_DEF_SVC_MEMO_TP_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.defSvcMemoTpCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {NSBB0170_DEF_SVC_MEMO_TP_CD });
        }

        this.defSvcPblmCd = ZYPCodeDataUtil.getVarCharConstValue(NSBB0170_DEF_SVC_PBLM_TP, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.defSvcPblmCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {NSBB0170_DEF_SVC_PBLM_TP });
        }

        this.defSvcCallTpCd = ZYPCodeDataUtil.getVarCharConstValue(NSBB0170_DEF_SVC_CALL_SRC_TP_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.defSvcCallTpCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {NSBB0170_DEF_SVC_CALL_SRC_TP_CD });
        }

        this.deflineBizTpCd = ZYPCodeDataUtil.getVarCharConstValue(NSBB0170_LINE_BIZ_TP_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.deflineBizTpCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {NSBB0170_LINE_BIZ_TP_CD });
        }

        this.defCtryCd = ZYPCodeDataUtil.getVarCharConstValue(NSBB0170_CTRY_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.defCtryCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {NSBB0170_CTRY_CD });
        }

        // START 2016/09/05 T.Tomita [QC#12471, ADD]
        this.svcByLineBizTpCd = ZYPCodeDataUtil.getVarCharConstValue(NSBB0170_SVC_BY_LINE_BIZ_TP_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.svcByLineBizTpCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {NSBB0170_SVC_BY_LINE_BIZ_TP_CD });
        }
        // END 2016/09/05 T.Tomita [QC#12471, ADD]

        // START 2016/10/13 T.Kanasaka [QC#15143, ADD]
        this.defCustEmlAddr = ZYPCodeDataUtil.getVarCharConstValue(NSBB0170_CRS_SVC_NOT_EXIST_EML, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.defCustEmlAddr)) {
            throw new S21AbendException(NSBM0069E, new String[] {NSBB0170_CRS_SVC_NOT_EXIST_EML });
        }
        // END 2016/10/13 T.Kanasaka [QC#15143, ADD]
    }
// END   2016/03/25 [QC#5457, MOD]

// START 2016/11/14 N.Arai [QC#15829, MOD]
    // START 2019/08/08 K.Kitachi [QC#52391, DEL]
//    private boolean insertSvcMachMstrTrk(Map<String, String> beforMap, SVC_MACH_MSTRTMsg newTMsg) {
//
//        BigDecimal svcMachMstrPk = newTMsg.svcMachMstrPk.getValue();
//
//        if (!setSvcMachMstrTrk(svcMachMstrPk, OWNR_ACCT_NUM, beforMap.get(OWNR_ACCT_NUM), newTMsg.ownrAcctNum.getValue())) {
//            return false;
//        }
//        if (!setSvcMachMstrTrk(svcMachMstrPk, OWNR_LOC_NUM, beforMap.get(OWNR_LOC_NUM), newTMsg.ownrLocNum.getValue())) {
//            return false;
//        }
//        if (!setSvcMachMstrTrk(svcMachMstrPk, BILL_TO_ACCT_NUM, beforMap.get(BILL_TO_ACCT_NUM), newTMsg.billToAcctNum.getValue())) {
//            return false;
//        }
//        if (!setSvcMachMstrTrk(svcMachMstrPk, BILL_TO_LOC_NUM, beforMap.get(BILL_TO_LOC_NUM), newTMsg.billToLocNum.getValue())) {
//            return false;
//        }
//        if (!setSvcMachMstrTrk(svcMachMstrPk, CUR_LOC_ACCT_NUM, beforMap.get(CUR_LOC_ACCT_NUM), newTMsg.curLocAcctNum.getValue())) {
//            return false;
//        }
//        if (!setSvcMachMstrTrk(svcMachMstrPk, CUR_LOC_NUM, beforMap.get(CUR_LOC_NUM), newTMsg.curLocNum.getValue())) {
//            return false;
//        }
//        if (!setSvcMachMstrTrk(svcMachMstrPk, IND_BILL_TO_LOC_NUM, beforMap.get(IND_BILL_TO_LOC_NUM), newTMsg.indBillToLocNum.getValue())) {
//            return false;
//        }
//        if (!setSvcMachMstrTrk(svcMachMstrPk, IND_CUR_LOC_NUM, beforMap.get(IND_CUR_LOC_NUM), newTMsg.indCurLocNum.getValue())) {
//            return false;
//        }
//        return true;
//
//    }
//
//    private boolean setSvcMachMstrTrk(BigDecimal svcMachMstrPk, String updFld, String oldVal, String newVal) {
//
//        if (!ZYPCommonFunc.hasValue(oldVal) && !ZYPCommonFunc.hasValue(newVal)) {
//            return true;
//        }
//        if (ZYPCommonFunc.hasValue(oldVal) && newVal.equals(oldVal)) {
//            return true;
//        }
//        if (ZYPCommonFunc.hasValue(newVal) && oldVal.equals(newVal)) {
//            return true;
//        }
//
//        SVC_MACH_MSTR_TRKTMsg tMsg = new SVC_MACH_MSTR_TRKTMsg();
//
//        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
//        setValue(tMsg.svcMachMstrTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MACH_MSTR_TRK_SQ));
//        setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
//        setValue(tMsg.trxRgtnDt, this.slsDt);
//        setValue(tMsg.updFldTxt, updFld);
//        setValue(tMsg.oldValTxt, oldVal);
//        setValue(tMsg.newValTxt, newVal);
//        setValue(tMsg.updUsrId, BATCH_ID);
//        setValue(tMsg.noteExistFlg, ZYPConstant.FLG_OFF_N);
//        EZDTBLAccessor.create(tMsg);
//        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
//            return false;
//        }
//        return true;
//    }
    // END 2019/08/08 K.Kitachi [QC#52391, DEL]
// END 2016/11/14 N.Arai [QC#15829, MOD]

    // START 2019/07/08 K.Kitachi [QC#51198, ADD]
    private BigDecimal machineLocationProcess(ResultSet rs, Map<String, Object> svcMachMstr) throws SQLException {

        String acctNum = null;
        String billToLocNum = null;
        String shipToLocNum = null;
        String billToCustCd = null;
        String shipToCustCd = null;

        Map<String, Object> crsSvcShipTo = findShipToCustByAddress(rs);

        if (crsSvcShipTo == null) {
            // call Customer Update API(NMZC001001)
            NMZC001001PMsg nmzc001001PMsg = createNMZC001001PMsg(rs);
            if (!callApiNMZC001001(nmzc001001PMsg)) {
                rollback();

                updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);

                S21ApiMessage msg = getErrMsgNMZC001001(nmzc001001PMsg);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                sendRequestDataErrorMail(createMailTemplateParamMap(rs, msgId, msgPrms));
                return null;
            }

            acctNum = nmzc001001PMsg.dsAcctNum.getValue();

            for (int i = 0; i < nmzc001001PMsg.NMZC001002PMsg.getValidCount(); i++) {
                NMZC001002PMsg nmzc001002PMsg = nmzc001001PMsg.NMZC001002PMsg.no(i);
                if (ZYPConstant.FLG_ON_Y.equals(nmzc001002PMsg.billToCustFlg.getValue())) {
                    billToLocNum = nmzc001002PMsg.locNum.getValue();
                }
                if (ZYPConstant.FLG_ON_Y.equals(nmzc001002PMsg.shipToCustFlg.getValue())) {
                    shipToLocNum = nmzc001002PMsg.locNum.getValue();
                }
            }

            billToCustCd = getBillToCustCdFromLocNum(billToLocNum);
            shipToCustCd = getShipToCustCdFromLocNum(shipToLocNum);
        } else {
            acctNum = (String) crsSvcShipTo.get("DS_ACCT_NUM");
            shipToCustCd = (String) crsSvcShipTo.get("SHIP_TO_CUST_CD");

            NMZC610001PMsg nmzc610001PMsg = new NMZC610001PMsg();
            setValue(nmzc610001PMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(nmzc610001PMsg.xxModeCd, NMZC610001Constant.PROCESS_DEFAULT_BILL_SHIP);
            setValue(nmzc610001PMsg.dsAcctNum_I1, acctNum);
            setValue(nmzc610001PMsg.shipToCustCd, shipToCustCd);
            setValue(nmzc610001PMsg.dsTrxRuleTpCd, DS_TRX_RULE_TP.SERVICE);

            NMZC610001 nmzc610001 = new NMZC610001();
            nmzc610001.execute(nmzc610001PMsg, ONBATCH_TYPE.BATCH);
            if (S21ApiUtil.isXxMsgId(nmzc610001PMsg)) {
                S21ApiMessage msg = S21ApiUtil.getXxMsgList(nmzc610001PMsg).get(0);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                sendRequestDataErrorMail(createMailTemplateParamMap(rs, msgId, msgPrms));
                return null;
            }
            for (int i = 0; i < nmzc610001PMsg.DefaultBillShipList.getValidCount(); i++) {
                if (hasValue(nmzc610001PMsg.DefaultBillShipList.no(i).billToCustCd)) {
                    billToCustCd = nmzc610001PMsg.DefaultBillShipList.no(i).billToCustCd.getValue();
                    break;
                }
            }

            billToLocNum = getLocNumFromBillToCustCd(billToCustCd);
            shipToLocNum = getLocNumFromShipToCustCd(shipToCustCd);
        }

        BigDecimal svcMachMstrPk = null;
        // START 2019/08/08 K.Kitachi [QC#52391, MOD]
//        if (svcMachMstr == null) {
        // call Machine Master Update API(NSZC001001)
        NSZC001001PMsg nszc001001PMsg = createNSZC001001PMsg(rs, acctNum, billToCustCd, shipToCustCd);
        if (!callApiNSZC001001(nszc001001PMsg)) {
            rollback();

            updateCrsSvcRcvRqstStage(rs, PROC_STS.ERROR, null, null);

            S21ApiMessage msg = S21ApiUtil.getXxMsgList(nszc001001PMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, msgId, msgPrms));
            return null;
        }
        svcMachMstrPk = nszc001001PMsg.svcMachMstrPk.getValue();
//        } else {
//            svcMachMstrPk = (BigDecimal) svcMachMstr.get("SVC_MACH_MSTR_PK");
//
//            SVC_MACH_MSTRTMsg updTMsg = new SVC_MACH_MSTRTMsg();
//            setValue(updTMsg.glblCmpyCd, this.glblCmpyCd);
//            setValue(updTMsg.svcMachMstrPk, svcMachMstrPk);
//            updTMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdate(updTMsg);
//
//            Map<String, String> beforParamMap = new HashMap<String, String>();
//            beforParamMap.put(OWNR_ACCT_NUM, updTMsg.ownrAcctNum.getValue());
//            beforParamMap.put(OWNR_LOC_NUM, updTMsg.ownrLocNum.getValue());
//            beforParamMap.put(BILL_TO_ACCT_NUM, updTMsg.billToAcctNum.getValue());
//            beforParamMap.put(BILL_TO_LOC_NUM, updTMsg.billToLocNum.getValue());
//            beforParamMap.put(CUR_LOC_ACCT_NUM, updTMsg.curLocAcctNum.getValue());
//            beforParamMap.put(CUR_LOC_NUM, updTMsg.curLocNum.getValue());
//            beforParamMap.put(IND_BILL_TO_LOC_NUM, updTMsg.indBillToLocNum.getValue());
//            beforParamMap.put(IND_CUR_LOC_NUM, updTMsg.indCurLocNum.getValue());
//
//            setValue(updTMsg.ownrAcctNum, acctNum);
//            setValue(updTMsg.billToAcctNum, acctNum);
//            setValue(updTMsg.billToLocNum, billToCustCd);
//            setValue(updTMsg.curLocAcctNum, acctNum);
//            setValue(updTMsg.curLocNum, shipToCustCd);
//            setValue(updTMsg.indBillToLocNum, billToLocNum);
//            setValue(updTMsg.indCurLocNum, shipToLocNum);
//
//            EZDTBLAccessor.update(updTMsg);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
//                throw new S21AbendException(NSBM0120E, new String[] {"SVC_MACH_MSTR" });
//            }
//
//            if (!insertSvcMachMstrTrk(beforParamMap, updTMsg)) {
//                throw new S21AbendException(NSBM0120E, new String[] {"SVC_MACH_MSTR_TRK" });
//            }
//        }
        // END 2019/08/08 K.Kitachi [QC#52391, MOD]

        return svcMachMstrPk;
    }

    private String getLocNumFromShipToCustCd(String shipToCustCd) {
        SHIP_TO_CUSTTMsg inMsg = new SHIP_TO_CUSTTMsg();
        inMsg.setSQLID("011");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        SHIP_TO_CUSTTMsgArray tMsgAry = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgAry.getValidCount() > 0) {
            return tMsgAry.no(0).locNum.getValue();
        }
        return null;
    }

    private String getLocNumFromBillToCustCd(String billToCustCd) {
        BILL_TO_CUSTTMsg inMsg = new BILL_TO_CUSTTMsg();
        inMsg.setSQLID("019");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("billToCustCd01", billToCustCd);
        BILL_TO_CUSTTMsgArray tMsgAry = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgAry.getValidCount() > 0) {
            return tMsgAry.no(0).locNum.getValue();
        }
        return null;
    }
    // END 2019/07/08 K.Kitachi [QC#51198, ADD]

    // START 2019/07/08 K.Kitachi [QC#51201, ADD]
    private S21ApiMessage getErrMsgNMZC001001(NMZC001001PMsg nmzc001001PMsg) {
        if (S21ApiUtil.isXxMsgId(nmzc001001PMsg)) {
            return S21ApiUtil.getXxMsgList(nmzc001001PMsg).get(0);
        }
        for (int idx = 0; idx < nmzc001001PMsg.NMZC001002PMsg.getValidCount(); idx++) {
            if (S21ApiUtil.isXxMsgId(nmzc001001PMsg.NMZC001002PMsg.no(idx))) {
                return S21ApiUtil.getXxMsgList(nmzc001001PMsg.NMZC001002PMsg.no(idx)).get(0);
            }
        }
        return new S21ApiMessage();
    }
    // END 2019/07/08 K.Kitachi [QC#51201, ADD]

    // START 2019/07/08 K.Kitachi [QC#51208, ADD]
    private boolean isExistMachine(String serNum) {
        Map<String, Object> svcMachMstr = getSvcMachMstr(serNum);
        if (svcMachMstr == null) {
            return false;
        }
        return true;
    }
    // END 2019/07/08 K.Kitachi [QC#51208, ADD]

    // START 2019/07/26 K.Kitachi [QC#52074, ADD]
    private boolean alreadyCancOrCloseOnS21(ResultSet rs) throws SQLException {
        Map<String, Object> prmMap = getAlreadyCancOrCloseOnS21CountParams(rs);
        BigDecimal count = (BigDecimal) this.ssmClient.queryObject("getAlreadyCancOrCloseOnS21Count", prmMap);
        if (count.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        return false;
    }

    private Map<String, Object> getAlreadyCancOrCloseOnS21CountParams(ResultSet rs) throws SQLException {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", this.glblCmpyCd);
        prmMap.put("crsSvcRcvRqstStagePk", rs.getBigDecimal("CRS_SVC_RCV_RQST_STAGE_PK"));
        prmMap.put("crsSvcTaskStsCd", CRS_SVC_TASK_STS_CANCELLED);
        prmMap.put("svcTaskStsCd", SVC_TASK_STS.CANCELLED);
        prmMap.put("crsSvcTaskStsCdList", this.crsSvcTaskStsCdList);
        prmMap.put("svcTaskStsCdList", this.svcTaskStsCdList);

        return prmMap;
    }

    private boolean duplicateWithPrevIf(ResultSet rs) throws SQLException {
        Map<String, Object> prmMap = getDuplicateWithPrevIfCountParams(rs);
        BigDecimal count = (BigDecimal) this.ssmClient.queryObject("getDuplicateWithPrevIfCount", prmMap);
        if (count.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        return false;
    }

    private Map<String, Object> getDuplicateWithPrevIfCountParams(ResultSet rs) throws SQLException {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", this.glblCmpyCd);
        prmMap.put("crsSvcRcvRqstStagePk", rs.getBigDecimal("CRS_SVC_RCV_RQST_STAGE_PK"));
        prmMap.put("crsSvcSrNum", rs.getString("CRS_SVC_SR_NUM"));
        prmMap.put("crsSvcProcStsCd", PROC_STS.COMPLEATED);
        prmMap.put("rqstSqOrigTxtLen", CRS_SVC_RQST_SQ_ORIG_TXT_LENGTH);

        return prmMap;
    }
    // END 2019/07/26 K.Kitachi [QC#52074, ADD]
}
