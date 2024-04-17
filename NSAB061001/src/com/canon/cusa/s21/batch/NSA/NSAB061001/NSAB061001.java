/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB061001;

import static com.canon.cusa.s21.batch.NSA.NSAB061001.constant.NSAB061001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCommonFunc;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ART10FMsg;
import business.db.UPLD_CSV_RQSTTMsg;
import business.parts.NSZC036001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC036001.NSZC036001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Machine Master Solution Upload
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/13   Hitachi         T.Tomita        Create          QC#6999
 * 2017/02/09   Hitachi         N.Arai          Update          QC#17296
 * 2017/02/17   Hitachi         N.Arai          Update          QC#17301
 * 2018/07/17   Hitachi         A.Kohinata      Update          QC#22583
 * </pre>
 */

public class NSAB061001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** SsmLLClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** BatchHelper */
    private S21RequestBatchHelper batchHelper = null;

    /** ZYPCSVUploadMessenger */
    private ZYPCSVUploadMessenger messenger = null;

    /** TermCd */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Normal Count */
    private int normalCount = 0;

    /** ErrCount */
    private int errCount = 0;

    // START 2017/02/17 N.Arai [QC#17301, MOD]
    /** TotalErrCount */
    private int totalErrCount = 0;
    // END 2017/02/17 N.Arai [QC#17301, MOD]

    // add start 2018/07/17 QC#22583
    /** Total Normal Count */
    private int totalNormalCount = 0;
    // add end 2018/07/17 QC#22583

    protected void initRoutine() {

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_GLOBAL_COMPANY_CODE });
        }

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd, BATCH_ID);
        if (!hasValue(this.salesDate)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_SALES_DATE });
        }

        // Initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.batchHelper = new S21RequestBatchHelper();
    }

    @Override
    protected void mainRoutine() {

        if (!this.batchHelper.isRecord()) {
            this.termCd = TERM_CD.WARNING_END;
            return;
        }
        for (ART10FMsg request : this.batchHelper.getRequestList()) {
            doProcess(request);
            // START 2017/02/17 N.Arai [QC#17301, ADD]
            commit();
            // END 2017/02/17 N.Arai [QC#17301, ADD]
        }
    }

    @Override
    protected void termRoutine() {
        // START 2017/02/17 N.Arai [QC#17301, MOD]
        // setTermState(this.termCd, this.normalCount, this.errCount);
        // mod start 2018/07/17 QC#22583
        //setTermState(this.termCd, this.normalCount, this.totalErrCount);
        setTermState(this.termCd, this.totalNormalCount, this.totalErrCount);
        // mod end 2018/07/17 QC#22583
        // END 2017/02/17 N.Arai [QC#17301, MOD]
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB061001().executeBatch(NSAB061001.class.getSimpleName());
    }

    private void doProcess(ART10FMsg fMsg) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        NSZC036001PMsg apiPMsg;

        // Get Upload ID
        String upldCsvId = getUpldCsvId(fMsg);

        // Get Upload Request PK
        BigDecimal upldCsvRqstPk = getUpldCsvReqPk(fMsg);
        UPLD_CSV_RQSTTMsg upldCSVRqstTMsg = getUpldCSVRqst(upldCsvRqstPk);
        this.messenger = new ZYPCSVUploadMessenger(upldCsvId, upldCsvRqstPk);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getSvcSlnUpldWrk", setParamMap(upldCSVRqstTMsg), setExecParam());
            rs = stmt.executeQuery();
            // add start 2018/07/17 QC#22583
            this.normalCount = 0;
            // add end 2018/07/17 QC#22583
            // START 2017/02/17 N.Arai [QC#17301, ADD]
            this.errCount = 0;
            // END 2017/02/17 N.Arai [QC#17301, ADD]

            while (rs.next()) {

                if (!validation(rs)) {
                    this.errCount++;
                    continue;
                }

                // create API parameters
                apiPMsg = createSlnUpdApiParams(rs);
                // call API
                if (callSlnUpdApi(apiPMsg, rs.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"))) {
                    commit();
                    this.normalCount++;
                } else {
                    rollback();
                    this.errCount++;
                }
            }

            // add start 2018/07/17 QC#22583
            String arg = createResultMessageArg();
            this.totalNormalCount += this.normalCount;
            // add end 2018/07/17 QC#22583
            // START 2017/02/17 N.Arai [QC#17301, ADD]
            this.totalErrCount += this.errCount;
            // END 2017/02/17 N.Arai [QC#17301, ADD]

            if (this.errCount == 0) {
                this.batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.NOMAL_END);
                // add start 2018/07/17 QC#22583
                this.messenger.addMessageForFile(NYXM0001I, arg);
                this.messenger.uploadMessage();
                // add end 2018/07/17 QC#22583
            } else {
                this.termCd = TERM_CD.WARNING_END;
                this.batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.WARING_END);
                // mod start 2018/07/17 QC#22583
                //this.messenger.addMessageForFile(NSAM0510E, null);
                this.messenger.addMessageForFile(NYXM0002E, arg);
                // mod end 2018/07/17 QC#22583
                this.messenger.uploadMessage();
            }

        } catch (SQLException se) {
            sqlExceptionHandler(se);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }

    }

    private boolean validation(ResultSet rs) throws SQLException {
        BigDecimal rowNum = rs.getBigDecimal("UPLD_CSV_RQST_ROW_NUM");

        if (!hasValue(rs.getString("SVC_SLN_NM"))) {
            this.messenger.addMessageForRecord(rowNum, ZZM9000E, MSG_SVC_SLN_NM);
            return false;
        }

        if (!hasValue(rs.getBigDecimal("SVC_CONFIG_MSTR_PK_01"))) {
            this.messenger.addMessageForRecord(rowNum, ZZM9000E, MSG_SVC_CONFIG_MSTR_PK_01);
            return false;
        }

        if (!hasValue(rs.getBigDecimal("SVC_CONFIG_MSTR_PK_01_FOR_MSTR"))) {
            this.messenger.addMessageForRecord(rowNum, NSAM0011E, MSG_SVC_CONFIG_MSTR_PK_01);
            return false;
        }

        if (hasValue(rs.getBigDecimal("SVC_CONFIG_MSTR_PK_02")) && !hasValue(rs.getBigDecimal("SVC_CONFIG_MSTR_PK_02_FOR_MSTR"))) {
            this.messenger.addMessageForRecord(rowNum, NSAM0011E, MSG_SVC_CONFIG_MSTR_PK_02);
            return false;
        }

        if (hasValue(rs.getBigDecimal("SVC_CONFIG_MSTR_PK_03")) && !hasValue(rs.getBigDecimal("SVC_CONFIG_MSTR_PK_03_FOR_MSTR"))) {
            this.messenger.addMessageForRecord(rowNum, NSAM0011E, MSG_SVC_CONFIG_MSTR_PK_03);
            return false;
        }

        if (hasValue(rs.getBigDecimal("SVC_CONFIG_MSTR_PK_04")) && !hasValue(rs.getBigDecimal("SVC_CONFIG_MSTR_PK_04_FOR_MSTR"))) {
            this.messenger.addMessageForRecord(rowNum, NSAM0011E, MSG_SVC_CONFIG_MSTR_PK_04);
            return false;
        }

        if (hasValue(rs.getBigDecimal("SVC_CONFIG_MSTR_PK_05")) && !hasValue(rs.getBigDecimal("SVC_CONFIG_MSTR_PK_05_FOR_MSTR"))) {
            this.messenger.addMessageForRecord(rowNum, NSAM0011E, MSG_SVC_CONFIG_MSTR_PK_05);
            return false;
        }

        if (hasValue(rs.getBigDecimal("SVC_CONFIG_MSTR_PK_06")) && !hasValue(rs.getBigDecimal("SVC_CONFIG_MSTR_PK_06_FOR_MSTR"))) {
            this.messenger.addMessageForRecord(rowNum, NSAM0011E, MSG_SVC_CONFIG_MSTR_PK_06);
            return false;
        }

        if (hasValue(rs.getBigDecimal("SVC_CONFIG_MSTR_PK_07")) && !hasValue(rs.getBigDecimal("SVC_CONFIG_MSTR_PK_07_FOR_MSTR"))) {
            this.messenger.addMessageForRecord(rowNum, NSAM0011E, MSG_SVC_CONFIG_MSTR_PK_07);
            return false;
        }

        return true;
    }

    private NSZC036001PMsg createSlnUpdApiParams(ResultSet rs) throws SQLException {

        NSZC036001PMsg pMsg = new NSZC036001PMsg();

        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.svcSlnSq, rs.getBigDecimal("SVC_SLN_SQ"));
        setValue(pMsg.svcSlnNm, rs.getString("SVC_SLN_NM"));
        setValue(pMsg.slsDt, this.salesDate);
        setValue(pMsg.svcSlnUpdPsnCd, BATCH_ID);

        BigDecimal svcConfigMstrPk;
        int index = 0;
        for (int i = 0; i < SVC_CONFIG_LIST_NUM; i++) {
            svcConfigMstrPk = rs.getBigDecimal("SVC_CONFIG_MSTR_PK_0" + (i + 1));
            if (hasValue(svcConfigMstrPk)) {
                setValue(pMsg.xxSvcConfigList.no(index).svcConfigMstrPk, svcConfigMstrPk);
                setValue(pMsg.xxSvcConfigList.no(index).delFlg, ZYPConstant.FLG_OFF_N);
                index++;
            }
        }
        pMsg.xxSvcConfigList.setValidCount(index);

        return pMsg;
    }

    private boolean callSlnUpdApi(NSZC036001PMsg apiPMsg, BigDecimal rowNum) {
        new NSZC036001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            // START 2017/02/09 N.Arai [QC#17296, MOD]
//            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
//            for (String xxMsgId : xxMsgIdList) {
//                this.messenger.addMessageForRecord(rowNum, xxMsgId, null);
//            }
            String xxMsgId = S21ApiUtil.getXxMsgIdList(apiPMsg).get(0);
            S21ApiMessage xxMsg = S21ApiUtil.getXxMsgList(apiPMsg).get(0);
            String[] msgPrms = xxMsg.getXxMsgPrmArray();
            String msg = S21MessageFunc.clspGetMessage(xxMsgId, msgPrms);
            this.messenger.addMessageForRecord(rowNum, NSAM0624E, msg);
            // END 2017/02/09 N.Arai [QC#17296, MOD]
            return false;
        }
        return true;
    }

    private String getUpldCsvId(ART10FMsg fMsg) {
        String uploadCsvId = fMsg.EZREQUserKey.getValue();
        if (!hasValue(uploadCsvId)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_UPLD_CSV_RQST_PK });
        }
        return S21RequestBatchHelper.removeDoubleQuotation(uploadCsvId);
    }

    private BigDecimal getUpldCsvReqPk(ART10FMsg fMsg) {
        // UploadCsvRequestPK
        String upldCsvReqPk = fMsg.EZREQCondition.getValue();
        String removeDQRequestPK = S21RequestBatchHelper.removeDoubleQuotation(upldCsvReqPk);

        if (!hasValue(removeDQRequestPK)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_UPLD_CSV_RQST_PK });
        }
        if (!EZDCommonFunc.isNumeric(removeDQRequestPK)) {
            throw new S21AbendException(NSAM0040E, new String[] {MSG_UPLD_CSV_RQST_PK });
        }
        return new BigDecimal(removeDQRequestPK);
    }

    private UPLD_CSV_RQSTTMsg getUpldCSVRqst(BigDecimal upldCsvRqstPk) {
        UPLD_CSV_RQSTTMsg inTMsg = new UPLD_CSV_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.upldCsvRqstPk, upldCsvRqstPk);

        UPLD_CSV_RQSTTMsg outTMsg = (UPLD_CSV_RQSTTMsg) EZDTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            throw new S21AbendException(ZYPM0181E);
        }
        return outTMsg;
    }

    private S21SsmExecutionParameter setExecParam() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(MAX_COMMIT_NUMBER);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execParam;
    }

    private Map<String, Object> setParamMap(UPLD_CSV_RQSTTMsg upldCSVRqstTMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("upldCsvRqstPk", upldCSVRqstTMsg.upldCsvRqstPk);
        return paramMap;
    }

    // add start 2018/07/17 QC#22583
    private String createResultMessageArg() {
        StringBuilder builder = new StringBuilder();
        if (this.normalCount > 0 || this.errCount == 0) {
            builder.append(String.format(RESULT_MSG_UPD, this.normalCount));
        }
        if (this.errCount > 0) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(String.format(RESULT_MSG_ERR, this.errCount));
        }
        return builder.toString();
    }
    // add end 2018/07/17 QC#22583
}
