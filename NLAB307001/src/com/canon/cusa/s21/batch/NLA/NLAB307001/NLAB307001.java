package com.canon.cusa.s21.batch.NLA.NLAB307001;

import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.BATCH_ID;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.DS_COND_CONST_CD_1;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.DS_COND_CONST_CD_2;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.DS_COND_CONST_CD_3;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.DS_COND_CONST_GRP_ID;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.DT_FMT;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.ERR_DT_FMT;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.ERR_GRP_ID;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.ERR_TMP;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.LINE_FEED_CODE;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.ML_LANG;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.ML_LANG_CD;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.MSG_KEY_BATCH_ID;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.MSG_KEY_ERR_DT;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.MSG_KEY_MESSAGE;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.MSG_KEY_STS;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.MSG_PARAM_ADDR;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.MSG_PARAM_GRP;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.MSG_PARAM_LINE;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.MSG_PARAM_RWS;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.MSG_PARAM_TMP;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.NASM0010E;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.NDMM0016E;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.NLEM0001E;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.NLEM0004E;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.NWZM0650E;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.PROGRAM_ID;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.SPACE;
import static com.canon.cusa.s21.batch.NLA.NLAB307001.NLAB307001Constant.ZZBM0009I;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.GLBL_CMPYTMsg;
import business.db.RTRN_TRK_NTFY_WRKTMsg;
import business.db.RWS_DTLTMsg;
import business.db.RWS_SCHD_DTL_TRKTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_TRK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
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
 * RMA Tracking Notification Batch
 * 
 * Update Receiving Work Sheet Tracking Status automatically, and send mail.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/05/2016   CITS            M.Ito           Create          N/A
 *</pre>
 */
public class NLAB307001 extends S21BatchMain {

    /** Commit Count Of Schedule Detail */
    private int commitCountOfSD = 0;

    /** Total Commit Count Of Schedule Detail */
    private int totalCommitCountOfSD = 0;

    /** Commit Count Of Schedule Detail Tracking */
    private int commitCountOfSDT = 0;

    /** Total Commit Count Of Schedule Detail Tracking */
    private int totalCommitCountOfSDT = 0;

    /** Commit Count Of Tracking Notification Work */
    private int commitCountOfTNW = 0;

    /** Total Commit Count Of Tracking Notification Work */
    private int totalCommitCountOfTNW = 0;

    /** Commit Count Of Tracking Notification Work */
    private int commitCountOfTNWDel = 0;

    /** Total Commit Count Of Tracking Notification Work */
    private int totalCommitCountOfTNWDel = 0;

    /** Total Error Count */
    private int totalErrorCount = 0;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** User Profile */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Commit Transaction Count */
    private int commitTrxCount = 0;

    /** Batch Date */
    private String batchDate = null;

    /** SSM */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    @Override
    protected void initRoutine() {

        // Get global company code
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();

        // Get Commit Transaction Count
        this.commitTrxCount = this.getCommitCount();

        // No global company code
        if (this.glblCmpyCd == null) {
            throw new S21AbendException(NASM0010E);
        }

        // Global company code is not exists
        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(glblCmpyTMsg);

        if (glblCmpyTMsg == null) {
            throw new S21AbendException(NWZM0650E);
        }

        // Get batch date
        this.batchDate = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);

        if (this.batchDate == null) {
            throw new S21AbendException(NDMM0016E);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> glblCmpyCd:" + this.glblCmpyCd, this);
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> commitTrxCount:" + this.commitTrxCount, this);
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> batchDate:" + this.batchDate, this);
    }

    @Override
    protected void mainRoutine() {

        try {
            mainRoutine1();
            mainRoutine2();
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }

    }

    @Override
    protected void termRoutine() {

        // main 1
        String[] strGrp = {"RWS_DTL", "updated", String.valueOf(this.totalCommitCountOfSD) };
        String[] strGrpTrk = {"RWS_SCHD_DTL_TRK", "created", String.valueOf(this.totalCommitCountOfSDT) };
        String[] strTrkNtfy = {"RTRN_TRK_NTFY_WRK", "created", String.valueOf(this.totalCommitCountOfTNW) };

        S21InfoLogOutput.println(ZZBM0009I, strGrp);
        S21InfoLogOutput.println(ZZBM0009I, strGrpTrk);
        S21InfoLogOutput.println(ZZBM0009I, strTrkNtfy);

        // main 2
        String[] strTrkNtfyDel = {"RTRN_TRK_NTFY_WRK", "deleted logicaly", String.valueOf(this.totalCommitCountOfTNWDel) };

        S21InfoLogOutput.println(ZZBM0009I, strTrkNtfyDel);

        int totalProcessCount = this.totalCommitCountOfSD + this.totalErrorCount;
        setTermState(this.termCd, this.totalCommitCountOfSD, this.totalErrorCount, totalProcessCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {

        // Initialize S21BatchMain
        new NLAB307001().executeBatch(NLAB307001.class.getSimpleName());
    }

    private void mainRoutine1() throws SQLException {

        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process start [mainRoutine1()]", this);

        List<RwsSchdDtlInfo> rwsSchdDtlInfoList = null;

        // Get target date and status from DS Condition Constant
        Map<String, String> queryParamForDsCondConst = new HashMap<String, String>();
        queryParamForDsCondConst.put("glblCmpyCd", this.glblCmpyCd);
        queryParamForDsCondConst.put("dsCondConstGrpId", DS_COND_CONST_GRP_ID);
        List<DsCondConstInfo> dsCondConstInfoList = (List<DsCondConstInfo>) this.ssmBatchClient.queryObjectList("getDsCondConstInfo", queryParamForDsCondConst);
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> (List<DsCondConstInfo>) this.ssmBatchClient.queryObjectList('getDsCondConstInfo', queryParamForDsCondConst)" + queryParamForDsCondConst, this);

        for (DsCondConstInfo dsCondConstInfo : dsCondConstInfoList) {
            // Get update target RWS Schedule Detail Info
            // Case 1 : Schedule was not updated while 10 days.(and no storage)
            if (DS_COND_CONST_CD_1.equals(dsCondConstInfo.getDsCondConstCd())) {
                Map<String, String> queryParamForRwsSchdDtl = new HashMap<String, String>();
                queryParamForRwsSchdDtl.put("glblCmpyCd", this.glblCmpyCd);
                queryParamForRwsSchdDtl.put("batchDate", this.batchDate);
                queryParamForRwsSchdDtl.put("dtFmt", DT_FMT);
                queryParamForRwsSchdDtl.put("pastDays", dsCondConstInfo.getDsCondConstValTxt01());
                queryParamForRwsSchdDtl.put("rwsStsCd", RWS_STS.PRINTED);
                queryParamForRwsSchdDtl.put("rtrnTrkStsCd", dsCondConstInfo.getDsCondConstValTxt02());
                queryParamForRwsSchdDtl.put("flgOn", ZYPConstant.FLG_ON_Y);
                queryParamForRwsSchdDtl.put("flgOff", ZYPConstant.FLG_OFF_N);
                rwsSchdDtlInfoList = (List<RwsSchdDtlInfo>) this.ssmBatchClient.queryObjectList("getRwsSchdDtlInfo1", queryParamForRwsSchdDtl);
                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> (List<RwsSchdDtlInfo>) this.ssmBatchClient.queryObjectList('getRwsSchdDtlInfo1', queryParamForRwsSchdDtl)" + queryParamForDsCondConst, this);
            }

            // Case 2 : No notification although 10 days past from pickup.(and no storage)
            if (DS_COND_CONST_CD_2.equals(dsCondConstInfo.getDsCondConstCd())) {
                Map<String, String> queryParamForRwsSchdDtl = new HashMap<String, String>();
                queryParamForRwsSchdDtl.put("glblCmpyCd", this.glblCmpyCd);
                queryParamForRwsSchdDtl.put("batchDate", this.batchDate);
                queryParamForRwsSchdDtl.put("dtFmt", DT_FMT);
                queryParamForRwsSchdDtl.put("pastDays", dsCondConstInfo.getDsCondConstValTxt01());
                queryParamForRwsSchdDtl.put("rwsStsCd", RWS_STS.PRINTED);
                queryParamForRwsSchdDtl.put("rtrnTrkStsCd", dsCondConstInfo.getDsCondConstValTxt02());
                queryParamForRwsSchdDtl.put("flgOn", ZYPConstant.FLG_ON_Y);
                queryParamForRwsSchdDtl.put("flgOff", ZYPConstant.FLG_OFF_N);
                queryParamForRwsSchdDtl.put("rtrnTrkStsCd_A", RTRN_TRK_STS._10_DAYS_NEED_UPDATE);
                queryParamForRwsSchdDtl.put("rtrnTrkStsCd_B", RTRN_TRK_STS.CUSTOMER_REFUSED_P_OR_U_ESCALATED);
                queryParamForRwsSchdDtl.put("rtrnTrkStsCd_C", RTRN_TRK_STS.CUSTOMER_REFUSED_P_OR_U_ESCALATED);
                rwsSchdDtlInfoList = (List<RwsSchdDtlInfo>) this.ssmBatchClient.queryObjectList("getRwsSchdDtlInfo2", queryParamForRwsSchdDtl);
                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> (List<RwsSchdDtlInfo>) this.ssmBatchClient.queryObjectList('getRwsSchdDtlInfo2', queryParamForRwsSchdDtl)" + queryParamForDsCondConst, this);
            }

            // Case 3 : No notification although 10 days past from retrieval. It include retrieval and return.(and no storage)
            if (DS_COND_CONST_CD_3.equals(dsCondConstInfo.getDsCondConstCd())) {
                Map<String, String> queryParamForRwsSchdDtl = new HashMap<String, String>();
                queryParamForRwsSchdDtl.put("glblCmpyCd", this.glblCmpyCd);
                queryParamForRwsSchdDtl.put("batchDate", this.batchDate);
                queryParamForRwsSchdDtl.put("dtFmt", DT_FMT);
                queryParamForRwsSchdDtl.put("pastDays", dsCondConstInfo.getDsCondConstValTxt01());
                queryParamForRwsSchdDtl.put("rwsStsCd", RWS_STS.PRINTED);
                queryParamForRwsSchdDtl.put("rtrnTrkStsCd", dsCondConstInfo.getDsCondConstValTxt02());
                queryParamForRwsSchdDtl.put("flgOn", ZYPConstant.FLG_ON_Y);
                queryParamForRwsSchdDtl.put("flgOff", ZYPConstant.FLG_OFF_N);
                queryParamForRwsSchdDtl.put("rtrnTrkStsCd_A", RTRN_TRK_STS._10_DAYS_NEED_UPDATE);
                queryParamForRwsSchdDtl.put("rtrnTrkStsCd_B", RTRN_TRK_STS.CUSTOMER_REFUSED_P_OR_U_ESCALATED);
                queryParamForRwsSchdDtl.put("rtrnTrkStsCd_C", RTRN_TRK_STS.CUSTOMER_REFUSED_P_OR_U_ESCALATED);
                rwsSchdDtlInfoList = (List<RwsSchdDtlInfo>) this.ssmBatchClient.queryObjectList("getRwsSchdDtlInfo3", queryParamForRwsSchdDtl);
                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> (List<RwsSchdDtlInfo>) this.ssmBatchClient.queryObjectList('getRwsSchdDtlInfo3', queryParamForRwsSchdDtl)" + queryParamForDsCondConst, this);
            }

            for (int i = 0; i < rwsSchdDtlInfoList.size(); i++) {

                RwsSchdDtlInfo rwsSchdDtlInfo = rwsSchdDtlInfoList.get(i);
                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> rwsSchdDtlInfo:" + rwsSchdDtlInfo, this);

                // Get update target row data
                RWS_DTLTMsg rwsSchdDtlTMsg = new RWS_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(rwsSchdDtlTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(rwsSchdDtlTMsg.rwsNum, rwsSchdDtlInfo.getRwsNum());
                ZYPEZDItemValueSetter.setValue(rwsSchdDtlTMsg.rwsDtlLineNum, rwsSchdDtlInfo.getRwsDtlLineNum());
                rwsSchdDtlTMsg = (RWS_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(rwsSchdDtlTMsg);
                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> (RWS_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(rwsSchdDtlTMsg);" + rwsSchdDtlTMsg, this);

                if (rwsSchdDtlTMsg != null) {
                    // Update RWS Schedule Detail
                    ZYPEZDItemValueSetter.setValue(rwsSchdDtlTMsg.rtrnTrkStsCd, dsCondConstInfo.getDsCondConstValTxt02());
                    if (ZYPCommonFunc.hasValue(dsCondConstInfo.getSchdCoordStsCd())) {
                        ZYPEZDItemValueSetter.setValue(rwsSchdDtlTMsg.schdCoordStsCd, dsCondConstInfo.getSchdCoordStsCd());
                    }
                    EZDTBLAccessor.update(rwsSchdDtlTMsg);
                    this.commitCountOfSD++;
                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> EZDTBLAccessor.update(rwsSchdDtlTMsg);" + rwsSchdDtlTMsg, this);

                    // Insert RWS Schedule Detail Tracking
                    RWS_SCHD_DTL_TRKTMsg rwsSchdDtlTrkTMsg = new RWS_SCHD_DTL_TRKTMsg();
                    ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.rwsSchdDtlTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.RWS_SCHD_DTL_TRK_SQ));
                    ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.updUsrId, rwsSchdDtlInfo.getEzUpUserId());
                    ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.updTs, rwsSchdDtlInfo.getEzUpTime());
                    ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.rwsNum, rwsSchdDtlTMsg.rwsNum);
                    ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.rwsDtlLineNum, rwsSchdDtlTMsg.rwsDtlLineNum);
                    ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.schdCoordStsCd, dsCondConstInfo.getSchdCoordStsCd());
                    ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.schdCoordPsnCd, rwsSchdDtlTMsg.schdCoordPsnCd);
                    ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.schdPickUpDt, rwsSchdDtlTMsg.schdPickUpDt);
                    ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.carrCd, rwsSchdDtlTMsg.carrCd);
                    ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.shpgSvcLvlCd, rwsSchdDtlTMsg.shpgSvcLvlCd);
                    ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.rtrnTrkStsCd, dsCondConstInfo.getDsCondConstValTxt02());
                    ZYPEZDItemValueSetter.setValue(rwsSchdDtlTrkTMsg.rtrnTrkNtfyGrpCd, rwsSchdDtlTMsg.rtrnTrkNtfyGrpCd);
                    EZDTBLAccessor.insert(rwsSchdDtlTrkTMsg);
                    this.commitCountOfSDT++;
                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> EZDTBLAccessor.insert(rwsSchdDtlTrkTMsg);" + rwsSchdDtlTrkTMsg, this);

                    if (ZYPConstant.FLG_ON_Y.equals(dsCondConstInfo.getNtfyMlSendFlg())) {
                        // Insert Return Tracking Notification Work
                        RTRN_TRK_NTFY_WRKTMsg rtrnTrkNtfyWrkTMsg = new RTRN_TRK_NTFY_WRKTMsg();
                        ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.glblCmpyCd, this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.rtrnTrkNtfyWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.RTRN_TRK_NTFY_WRK_SQ));
                        ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.updUsrId, rwsSchdDtlInfo.getEzUpUserId());
                        ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.updTs, rwsSchdDtlInfo.getEzUpTime());
                        ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.rwsNum, rwsSchdDtlTMsg.rwsNum);
                        ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.rwsDtlLineNum, rwsSchdDtlTMsg.rwsDtlLineNum);
                        ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.schdCoordStsCd, dsCondConstInfo.getSchdCoordStsCd());
                        ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.schdCoordPsnCd, rwsSchdDtlTMsg.schdCoordPsnCd);
                        ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.schdPickUpDt, rwsSchdDtlTMsg.schdPickUpDt);
                        ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.carrCd, rwsSchdDtlTMsg.carrCd);
                        ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.shpgSvcLvlCd, rwsSchdDtlTMsg.shpgSvcLvlCd);
                        ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.rtrnTrkStsCd, dsCondConstInfo.getDsCondConstValTxt02());
                        ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.rtrnTrkNtfyGrpCd, rwsSchdDtlTMsg.rtrnTrkNtfyGrpCd);
                        EZDTBLAccessor.insert(rtrnTrkNtfyWrkTMsg);
                        this.commitCountOfTNW++;
                        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> EZDTBLAccessor.insert(rtrnTrkNtfyWrkTMsg);" + rtrnTrkNtfyWrkTMsg, this);
                    }
                }

                // Commit if process count reaches designated commit count
                if (this.commitCountOfSD >= this.commitTrxCount) {
                    commit();
                    this.totalCommitCountOfSD += this.commitCountOfSD;
                    this.totalCommitCountOfSDT += this.commitCountOfSDT;
                    this.totalCommitCountOfTNW += this.commitCountOfTNW;
                    this.commitCountOfSD = 0;
                    this.commitCountOfSDT = 0;
                    this.commitCountOfTNW = 0;
                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> commit();", this);
                }
            }

            if (rwsSchdDtlInfoList != null) {
                // Commit remains
                commit();
                this.totalCommitCountOfSD += this.commitCountOfSD;
                this.totalCommitCountOfSDT += this.commitCountOfSDT;
                this.totalCommitCountOfTNW += this.commitCountOfTNW;
                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> commit();", this);
            }
        }
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process end [mainRoutine1()]", this);
    }

    private void mainRoutine2() throws SQLException {
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process start [mainRoutine2()]", this);

        // Get notification target Return Tracking Notification Work Info
        Map<String, String> queryParamForRtrnTrkNtfyWrk = new HashMap<String, String>();
        queryParamForRtrnTrkNtfyWrk.put("glblCmpyCd", this.glblCmpyCd);
        queryParamForRtrnTrkNtfyWrk.put("batchDate", this.batchDate);
        List<RmaTrkStsInfo> rmaTrkStsInfoList = (List<RmaTrkStsInfo>) this.ssmBatchClient.queryObjectList("getRmaTrkStsInfo", queryParamForRtrnTrkNtfyWrk);
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> this.ssmBatchClient.queryObjectList('getRmaTrkStsInfo', queryParamForRtrnTrkNtfyWrk)" + queryParamForRtrnTrkNtfyWrk, this);

        // ************************* //
        // *** Send Mail process *** //
        // ************************* //
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        S21MailGroup group = null;
        S21MailGroup groupFrom = null;
        List<S21MailAddress> toAddrList = null;
        S21MailTemplate template = null;
        S21MailTemplate errTemplate = null;
        String status = "";
        String msg = "";
        StringBuilder mailMsgInfo = new StringBuilder();
        String preMailGrp = "";
        boolean error = false;
        Map<String, String[]> errorMap = new LinkedHashMap<String, String[]>(100);
        String[] grpError = null;
        String[] tmpError = null;
        String[] addrError = null;
        BigDecimal lastGrpWrkPk = null;

        // Make mail message and send message by Notification Group
        for (int i = 0; i < rmaTrkStsInfoList.size(); i++) {
            RmaTrkStsInfo rmaTrkStsInfo = rmaTrkStsInfoList.get(i);
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> rmaTrkStsInfo:" + rmaTrkStsInfo, this);

            if (!preMailGrp.equals(rmaTrkStsInfo.getMlGrpId())) {

                if (i > 0) {
                    // *** Send mail of previous mail group, and do logic-delete for work table, then commit it *** //

                    if (!error) {
                        mailMsgInfo.append(msg);
                        msg = "";
                        // Set template parameter
                        template.setTemplateParameter(MSG_KEY_BATCH_ID, BATCH_ID);
                        template.setTemplateParameter(MSG_KEY_MESSAGE, mailMsgInfo.toString());
                        template.setTemplateParameter(MSG_KEY_STS, status);
                        mail.setMailTemplate(template);

                        // Set mail subject
                        mail.setSubject(template.getSubject(ML_LANG), ML_LANG_CD);

                        // Post
                        mail.postMail();
                        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> mail.postMail();", this);

                        // Logic delete
                        RTRN_TRK_NTFY_WRKTMsg rtrnTrkNtfyWrkTMsg = new RTRN_TRK_NTFY_WRKTMsg();
                        ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.glblCmpyCd, this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.rtrnTrkNtfyWrkPk, rmaTrkStsInfo.getRtrnTrkNtfyWrkPk());
                        rtrnTrkNtfyWrkTMsg = (RTRN_TRK_NTFY_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(rtrnTrkNtfyWrkTMsg);
                        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> (RTRN_TRK_NTFY_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(rtrnTrkNtfyWrkTMsg);" + rtrnTrkNtfyWrkTMsg, this);

                        if (rtrnTrkNtfyWrkTMsg != null) {
                            EZDTBLAccessor.logicalRemove(rtrnTrkNtfyWrkTMsg);
                            this.commitCountOfTNWDel++;
                            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> EZDTBLAccessor.update(rtrnTrkNtfyWrkTMsg);" + rtrnTrkNtfyWrkTMsg, this);
                        }

                        // Commit if process count reaches designated commit count
                        if (this.commitCountOfTNWDel >= this.commitTrxCount) {
                            commit();
                            this.totalCommitCountOfTNWDel += this.commitCountOfTNWDel;
                            this.commitCountOfTNWDel = 0;
                            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> commit();" + rtrnTrkNtfyWrkTMsg, this);
                        }
                    }
                }

                // *** Get next mail group info *** //
                // Get mail group
                if (ZYPCommonFunc.hasValue(rmaTrkStsInfo.getMlGrpId())) {
                    group = new S21MailGroup(this.glblCmpyCd, rmaTrkStsInfo.getMlGrpId());
                } else {
                    group = new S21MailGroup(this.glblCmpyCd, ERR_GRP_ID);
                    error = true;
                    grpError = new String[] {MSG_PARAM_GRP, S21StringUtil.concatStrings(MSG_PARAM_RWS, SPACE, rmaTrkStsInfo.getRwsNum(), SPACE, MSG_PARAM_LINE, SPACE, rmaTrkStsInfo.getRwsDtlLineNum())};
                    errorMap.put("groupError", grpError);
                }

                // Get from address
                groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
                List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
                if (!addrFromList.isEmpty()) {
                    mail.setFromAddress(addrFromList.get(0));
                }

                // Get to address
                toAddrList = group.getMailAddress();
                if (toAddrList.isEmpty()) {
                    throw new S21AbendException(NLEM0004E);
                }
                if (ZYPCommonFunc.hasValue(rmaTrkStsInfo.getMlGrpId())) {
                    mail.setToAddressList(toAddrList);
                } else {
                    if (ZYPCommonFunc.hasValue(rmaTrkStsInfo.getEmlAddr())) {
                        toAddrList.add(new S21MailAddress(this.glblCmpyCd, rmaTrkStsInfo.getEmlAddr()));
                    }
                    mail.setToAddressList(toAddrList);
                    error = true;
                    addrError = new String[] {MSG_PARAM_ADDR, S21StringUtil.concatStrings(MSG_PARAM_RWS, SPACE, rmaTrkStsInfo.getRwsNum(), SPACE, MSG_PARAM_LINE, SPACE, rmaTrkStsInfo.getRwsDtlLineNum())};
                    errorMap.put("addrError", addrError);
                }

                // Get mail template
                if (ZYPCommonFunc.hasValue(rmaTrkStsInfo.getMlGrpId())) {
                    template = new S21MailTemplate(this.glblCmpyCd, rmaTrkStsInfo.getMlTmplId());
                } else {
                    errTemplate = new S21MailTemplate(this.glblCmpyCd, ERR_TMP);
                    error = true;
                    tmpError = new String[] {MSG_PARAM_TMP, S21StringUtil.concatStrings(MSG_PARAM_RWS, SPACE, rmaTrkStsInfo.getRwsNum(), SPACE, MSG_PARAM_LINE, SPACE, rmaTrkStsInfo.getRwsDtlLineNum())};
                    errorMap.put("tmpError", tmpError);
                }
            }

            // Create mail message
            msg = S21StringUtil.concatStrings(rmaTrkStsInfo.getCpoOrdNum());
            msg = msg + S21StringUtil.concatStrings("    ", rmaTrkStsInfo.getRwsNum());
            msg = msg + S21StringUtil.concatStrings("    ", rmaTrkStsInfo.getRwsDtlLineNum());
            status = rmaTrkStsInfo.getRtrnTrkStsDescTxt();

            if (ZYPCommonFunc.hasValue(rmaTrkStsInfo.getMlGrpId())) {
                preMailGrp = rmaTrkStsInfo.getMlGrpId();
            } else {
                preMailGrp = "";
            }
            lastGrpWrkPk = rmaTrkStsInfo.getRtrnTrkNtfyWrkPk();
        }

        // *** last group process *** //
        if (rmaTrkStsInfoList.size() > 0) {

            if (!error) {
                mailMsgInfo.append(msg);
                msg = "";
                // Set template parameter
                template.setTemplateParameter(MSG_KEY_BATCH_ID, BATCH_ID);
                template.setTemplateParameter(MSG_KEY_STS, status);
                template.setTemplateParameter(MSG_KEY_MESSAGE, mailMsgInfo.toString());
                mail.setMailTemplate(template);

                // Set mail subject
                mail.setSubject(template.getSubject(ML_LANG), ML_LANG_CD);

                // Post
                mail.postMail();
                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> mail.postMail();", this);

                // Logic delete if the mail send
                RTRN_TRK_NTFY_WRKTMsg rtrnTrkNtfyWrkTMsg = new RTRN_TRK_NTFY_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(rtrnTrkNtfyWrkTMsg.rtrnTrkNtfyWrkPk, lastGrpWrkPk);
                rtrnTrkNtfyWrkTMsg = (RTRN_TRK_NTFY_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(rtrnTrkNtfyWrkTMsg);

                if (rtrnTrkNtfyWrkTMsg != null) {
                    EZDTBLAccessor.logicalRemove(rtrnTrkNtfyWrkTMsg);
                    this.commitCountOfTNWDel++;
                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> EZDTBLAccessor.update(rtrnTrkNtfyWrkTMsg);" + rtrnTrkNtfyWrkTMsg, this);
                }

                // commit remains
                commit();
                this.totalCommitCountOfTNWDel += this.commitCountOfTNWDel;
                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> commit();" + rtrnTrkNtfyWrkTMsg, this);
            }
        }

        // Send error mail
        if (error) {

            // Set template parameter
            errTemplate.setTemplateParameter(MSG_KEY_BATCH_ID, BATCH_ID);
            // Create mail message
            for (Map.Entry<String, String[]> entry : errorMap.entrySet()) {
                msg = S21StringUtil.concatStrings(S21MessageFunc.clspGetMessage(NLEM0001E, entry.getValue()), LINE_FEED_CODE);
                totalErrorCount++;
            }
            mailMsgInfo.append(msg);
            errTemplate.setTemplateParameter(MSG_KEY_MESSAGE, mailMsgInfo.toString());
            errTemplate.setTemplateParameter(MSG_KEY_ERR_DT, ZYPDateUtil.getCurrentSystemTime(ERR_DT_FMT));
            mail.setMailTemplate(errTemplate);

            // Set mail subject
            mail.setSubject(errTemplate.getSubject(ML_LANG), ML_LANG_CD);

            // Post
            mail.postMail();
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Error mail.postMail();", this);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> process end [mainRoutine2()]", this);
    }

}
