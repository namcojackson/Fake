/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB060001;

import static com.canon.cusa.s21.batch.NSA.NSAB060001.constant.NSAB060001Constant.*;
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

import parts.common.EZDCommonFunc;
import parts.common.EZDDebugOutput;
import parts.common.EZDMessageEnv;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ART10FMsg;
import business.db.DS_MSGTMsg;
import business.db.DS_MTR_INTFCTMsg;
import business.db.DS_WIN_DAYSTMsg;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.db.SVC_PHYS_MTR_READTMsgArray;
import business.db.UPLD_CSV_RQSTTMsg;
import business.parts.NSZC051001PMsg;
import business.parts.NSZC051001_rsltPrmListPMsg;

import com.canon.cusa.s21.api.NSZ.NSZC051001.NSZC051001;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001SvcPhysMtrRead;
import com.canon.cusa.s21.common.NSX.NSXC003001.SvcPhysMtrReadInfoBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CNTR_RESET_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Meter Read Upload
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/27   Hitachi         A.Kohinata      Create          N/A
 * 2017/02/07   Hitachi         N.Arai          Update          QC#17296
 * 2017/02/17   Hitachi         N.Arai          Update          QC#17301
 * 2017/05/22   Hitachi         K.Kitachi       Update          QC#18342
 * 2017/09/19   Hitachi         T.Tomita        Update          QC#21185
 * 2017/09/26   Hitachi         T.Tomita        Update          QC#21185
 * 2017/12/20   Hitachi         M.Kidokoro      Update          QC#21127
 * 2018/02/08   Hitachi         M.Kidokoro      Update          QC#18145
 * 2018/07/17   Hitachi         A.Kohinata      Update          QC#22583
 * 2019/01/11   Hitachi         Y.Takeno        Update          QC#29791
 * 2019/01/22   Hitachi         Y.Takeno        Update          QC#29719
 * 2019/01/30   Hitachi         K.Kitachi       Update          QC#29719
 * 2019/08/20   Hitachi         K.Kitachi       Update          QC#52860
 * 2020/03/03   Hitachi         K.Kishimoto     Update          QC#56123
 * 2024/03/26   Hitachi         K.Watanabe      Update          QC#63549
 * </pre>
 */

public class NSAB060001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** SsmLLClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

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

    /** Meter Lavel Code */
    private String mtrLbCd = null;

    // START 2017/02/17 N.Arai [QC#17301, MOD]
    /** TotalErrCount */
    private int totalErrCount = 0;
    // END 2017/02/17 N.Arai [QC#17301, MOD]

    // START 2017/05/22 K.Kitachi [QC#18342, ADD]
    /** Process Time Stamp */
    private String procTs = null;
    // END 2017/05/22 K.Kitachi [QC#18342, ADD]

    // Add Start 2017/09/19 QC#21185
    private boolean wrnFlg = false;
    // Add End 2017/09/19 QC#21185

    // add start 2018/07/17 QC#22583
    /** Total Normal Count */
    private int totalNormalCount = 0;
    // add end 2018/07/17 QC#22583

    @Override
    protected void initRoutine() {

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_GLOBAL_COMPANY_CODE });
        }
        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd, BATCH_ID);

        // START 2017/05/22 K.Kitachi [QC#18342, ADD]
        this.procTs = ZYPDateUtil.getCurrentSystemTime(FORMAT_SYS_TS);
        // END 2017/05/22 K.Kitachi [QC#18342, ADD]

        // Initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
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
        new NSAB060001().executeBatch(NSAB060001.class.getSimpleName());
    }

    private void doProcess(ART10FMsg fMsg) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        // Get Upload ID
        String upldCsvId = getUpldCsvId(fMsg);
        EZDDebugOutput.println(0, "UPLD_CSV_ID" + upldCsvId, this);

        // Get Upload Request PK
        BigDecimal upldCsvRqstPk = getUpldCsvReqPk(fMsg);
        EZDDebugOutput.println(0, "UPLD_CSV_RQST_PK" + upldCsvRqstPk, this);
        UPLD_CSV_RQSTTMsg upldCSVRqstTMsg = getUpldCSVRqst(upldCsvRqstPk);
        this.messenger = new ZYPCSVUploadMessenger(upldCsvId, upldCsvRqstPk);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getSvcMtrUpldWrk", setParamMap(upldCSVRqstTMsg), setExecParam());
            rs = stmt.executeQuery();

            String prevSerNum = null;
            String prevMtrReadDt = null;
            int targetCount = 0;
            List<String> mtrLbList = new ArrayList<String>();
            List<BigDecimal> rowNumList = new ArrayList<BigDecimal>();
            boolean errFlg = false;
            NSZC051001PMsg apiPMsg = new NSZC051001PMsg();
            // add start 2018/07/17 QC#22583
            this.normalCount = 0;
            // add end 2018/07/17 QC#22583
            // START 2017/02/17 N.Arai [QC#17301, ADD]
            this.errCount = 0;
            // END 2017/02/17 N.Arai [QC#17301, ADD]
            // Add Start 2017/09/19 QC#21185
            this.wrnFlg = false;
            // Add End 2017/09/19 QC#21185

            while (rs.next()) {

                if (changeTarget(prevSerNum, prevMtrReadDt, rs)) {
                    if (!errFlg) {
                        // call API
                        // START 2017/05/22 K.Kitachi [QC#18342, MOD]
//                        if (callMeterBulkUploadAPI(apiPMsg, rowNumList)) {
//                            commit();
//                            this.normalCount += targetCount;
//                        } else {
//                            setErrMsg(fMsg);
//                            this.errCount += targetCount;
//                        }
                        meterBulkUploadProcess(apiPMsg, rowNumList, targetCount, fMsg);
                        // END 2017/05/22 K.Kitachi [QC#18342, MOD]
                    } else {
                        addMsgNoErrRec(rowNumList);
                        setErrMsg(fMsg);
                        this.errCount += targetCount;
                    }
                    targetCount = 0;
                    mtrLbList = new ArrayList<String>();
                    rowNumList = new ArrayList<BigDecimal>();
                    errFlg = false;
                    apiPMsg = new NSZC051001PMsg();
                }

                prevSerNum = rs.getString("SER_NUM");
                prevMtrReadDt = rs.getString("MTR_READ_DT");
                targetCount++;

                if (errFlg) {
                    rowNumList.add(rs.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"));
                    continue;
                }

                if (!validation(rs, mtrLbList)) {
                    errFlg = true;
                    continue;
                }

                mtrLbList.add(this.mtrLbCd);
                rowNumList.add(rs.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"));
                // set API params
                setFsrUpdateApiParams(apiPMsg, rs, targetCount);
            }

            if (!errFlg) {
                // call API
                // START 2017/05/22 K.Kitachi [QC#18342, MOD]
//                if (callMeterBulkUploadAPI(apiPMsg, rowNumList)) {
//                    commit();
//                    this.normalCount += targetCount;
//                } else {
//                    setErrMsg(fMsg);
//                    this.errCount += targetCount;
//                }
                meterBulkUploadProcess(apiPMsg, rowNumList, targetCount, fMsg);
                // END 2017/05/22 K.Kitachi [QC#18342, MOD]
            } else {
                addMsgNoErrRec(rowNumList);
                setErrMsg(fMsg);
                this.errCount += targetCount;
            }

            // add start 2018/07/17 QC#22583
            String arg = createResultMessageArg();
            this.totalNormalCount += this.normalCount;
            // add end 2018/07/17 QC#22583
            // START 2017/02/17 N.Arai [QC#17301, ADD]
            this.totalErrCount += this.errCount;
            // END 2017/02/17 N.Arai [QC#17301, ADD]

            if (this.errCount == 0) {
                // Mod Start 2017/09/26 QC#21185
                this.batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.NOMAL_END);
                // mod start 2018/07/17 QC#22583
                //if (this.wrnFlg) {
                //    this.messenger.addMessageForFile(NSAM0705W, null);
                //    this.messenger.uploadMessage();
                //}
                this.messenger.addMessageForFile(NYXM0001I, arg);
                this.messenger.uploadMessage();
                // mod end 2018/07/17 QC#22583
                // Mod End 2017/09/26 QC#21185
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

    private boolean validation(ResultSet rs, List<String> mtrLbList) throws SQLException {
        BigDecimal rowNum = rs.getBigDecimal("UPLD_CSV_RQST_ROW_NUM");

        if (!hasValue(rs.getString("SER_NUM"))) {
            this.messenger.addMessageForRecord(rowNum, ZZM9000E, MSG_SERIAL_NUM);
            return false;
        }
        if (!hasValue(rs.getString("MTR_READ_DT"))) {
            this.messenger.addMessageForRecord(rowNum, ZZM9000E, MSG_MTR_READ_DT);
            return false;
        }
        if (!hasValue(rs.getString("MTR_LB_CD")) && !hasValue(rs.getString("MTR_LB_DESC_TXT"))) {
            this.messenger.addMessageForRecord(rowNum, ZZM9000E, MSG_MTR_LB_CD_AND_DESC);
            return false;
        }
        // START 2018/01/22 [QC#29719, MOD]
        // if (!hasValue(rs.getBigDecimal("SVC_MACH_MSTR_PK"))) {
        //     this.messenger.addMessageForRecord(rowNum, NSAM0511E, null);
        //    return false;
        //}
        // START 2019/01/30 K.Kitachi [QC#29719, MOD]
//        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
//        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        if (hasValue(rs.getString("MDSE_CD"))) {
//            inMsg.setSQLID("001");
//            inMsg.setConditionValue("serNum01", rs.getString("SER_NUM"));
//            inMsg.setConditionValue("mdseCd01", rs.getString("MDSE_CD"));
//        } else {
//            inMsg.setSQLID("002");
//            inMsg.setConditionValue("serNum01", rs.getString("SER_NUM"));
//        }
//        SVC_MACH_MSTRTMsgArray svcMachMstrArray = (SVC_MACH_MSTRTMsgArray)EZDTBLAccessor.findByCondition(inMsg);
//        if (svcMachMstrArray.length() == 0) {
//            this.messenger.addMessageForRecord(rowNum, NSAM0511E, null);
//            return false;
//        } else if (svcMachMstrArray.length() > 1) {
//            this.messenger.addMessageForRecord(rowNum, NSAM0018E, null);
//            return false;
//        }
//        BigDecimal svcMachMstrPk = svcMachMstrArray.no(0).svcMachMstrPk.getValue();
        if (!hasValue(rs.getBigDecimal("SVC_MACH_MSTR_PK"))) {
            this.messenger.addMessageForRecord(rowNum, NSAM0511E, null);
            return false;
        }
        // END 2019/01/30 K.Kitachi [QC#29719, MOD]
        // END   2018/01/22 [QC#29719, MOD]

        this.mtrLbCd = getMtrLbCd(rs);
        if (!hasValue(this.mtrLbCd)) {
            this.messenger.addMessageForRecord(rowNum, NSAM0512E, null);
            return false;
        }
        if (rs.getString("MTR_READ_DT").compareTo(this.salesDate) > 0) {
            this.messenger.addMessageForRecord(rowNum, NSAM0513E, null);
            return false;
        }
        if (mtrLbList.contains(this.mtrLbCd)) {
            this.messenger.addMessageForRecord(rowNum, NSAM0514E, null);
            return false;
        }

        // START 2018/01/22 [QC#29719, MOD]
        // BigDecimal svcPhysMtrPk = getSvcPhysMtrPk(rs);
        // START 2019/01/30 K.Kitachi [QC#29719, MOD]
//        BigDecimal svcPhysMtrPk = getSvcPhysMtrPk(svcMachMstrPk);
        BigDecimal svcPhysMtrPk = getSvcPhysMtrPk(rs);
        // END 2019/01/30 K.Kitachi [QC#29719, MOD]
        // END   2018/01/22 [QC#29719, MOD]
        if (!hasValue(svcPhysMtrPk)) {
            this.messenger.addMessageForRecord(rowNum, NSAM0515E, null);
            return false;
        }

        // START 2020/03/03 [QC#56123, MOD]
        BigDecimal prevMtrCnt = getPrevMtrCnt(svcPhysMtrPk, rs.getString("MTR_READ_DT"));
        // END   2020/03/03 [QC#56123, MOD]
        if (hasValue(prevMtrCnt) && hasValue(rs.getBigDecimal("READ_MTR_CNT"))) {
            if (prevMtrCnt.compareTo(rs.getBigDecimal("READ_MTR_CNT")) > 0) {
                this.messenger.addMessageForRecord(rowNum, NSAM0516E, null);
                return false;
            }
        }
        // START 2020/03/03 [QC#56123, ADD]
        BigDecimal nextMtrCnt = getNextMtrCnt(svcPhysMtrPk, rs.getString("MTR_READ_DT"));
        if (hasValue(nextMtrCnt) && hasValue(rs.getBigDecimal("READ_MTR_CNT"))) {
            if (nextMtrCnt.compareTo(rs.getBigDecimal("READ_MTR_CNT")) < 0) {
                this.messenger.addMessageForRecord(rowNum, NSZM1312E, null);
                return false;
            }
        }
        // END   2020/03/03 [QC#56123, ADD]

        // START 2020/03/03 [QC#56123, DEL]
//        // START 2017/12/20 M.Kidokoro [QC#21127, ADD]
//        Map<String, Object> svcPhysMtrReadMap = null;
//        // START 2018/01/22 [QC#29719, MOD]
//        // List<Map<String, Object>> svcPhysMtrReadList = getSvcPhysMtrRead(rs.getBigDecimal("SVC_MACH_MSTR_PK"));
//        // START 2019/01/30 K.Kitachi [QC#29719, MOD]
////        List<Map<String, Object>> svcPhysMtrReadList = getSvcPhysMtrRead(svcMachMstrPk);
//        List<Map<String, Object>> svcPhysMtrReadList = getSvcPhysMtrRead(rs.getBigDecimal("SVC_MACH_MSTR_PK"));
//        // END 2019/01/30 K.Kitachi [QC#29719, MOD]
//        // END   2018/01/22 [QC#29719, MOD]
//        for (int cnt = 0; cnt < svcPhysMtrReadList.size(); cnt++) {
//            svcPhysMtrReadMap = svcPhysMtrReadList.get(cnt);
//            if (((String) svcPhysMtrReadMap.get("MTR_LB_CD")).equals(rs.getString("MTR_LB_CD")) || ((String) svcPhysMtrReadMap.get("MTR_LB_DESC_TXT")).equals(rs.getString("MTR_LB_DESC_TXT"))) {
//                if ((rs.getString("MTR_READ_DT").compareTo((String) svcPhysMtrReadMap.get("MTR_READ_DT")) < 0) && (rs.getBigDecimal("READ_MTR_CNT").compareTo((BigDecimal) svcPhysMtrReadMap.get("READ_MTR_CNT")) > 0)) {
//                    this.messenger.addMessageForRecord(rowNum, NSZM1312E, null);
//                    return false;
//                }
//            }
//        }
//        // END 2017/12/20 M.Kidokoro [QC#21127, ADD]
        // END   2020/03/03 [QC#56123, DEL]

        // START 2019/08/20 K.Kitachi [QC#52860, ADD]
        if (isInvoicedPeriod(rs.getBigDecimal("SVC_MACH_MSTR_PK"), rs.getString("MTR_READ_DT"))) {
            this.messenger.addMessageForRecord(rowNum, NSAM0709E, null);
            return false;
        }
        // END 2019/08/20 K.Kitachi [QC#52860, ADD]

        return true;
    }

    private void setFsrUpdateApiParams(NSZC051001PMsg apiPMsg, ResultSet rs, int targetCount) throws SQLException {
        if (targetCount == 1) {
            setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(apiPMsg.mtrReadSrcTpCd, MTR_READ_SRC_TP.UPLD);
            setValue(apiPMsg.dsMtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
            setValue(apiPMsg.slsDt, this.salesDate);
            // START 2019/01/11 [QC#29791, MOD]
            //setValue(apiPMsg.rgtnUsrId, BATCH_ID);
            setValue(apiPMsg.rgtnUsrId, rs.getString("EZINUSERID"));
            // END   2019/01/11 [QC#29791, MOD]
            setValue(apiPMsg.serNum, rs.getString("SER_NUM"));
            setValue(apiPMsg.mtrReadDt, rs.getString("MTR_READ_DT"));
            setValue(apiPMsg.rgtnMtrDt, this.salesDate);
            setValue(apiPMsg.dsMtrReadTpGrpCd, DS_MTR_READ_TP_GRP.BILLABLE_READS);
            // Add Start 2017/09/19 QC#21185
            setValue(apiPMsg.xxRqstFlg_WR, ZYPConstant.FLG_ON_Y);
            // Add End 2017/09/19 QC#21185
            // START 2019/01/10 [QC#29719, ADD]
            // START 2019/01/30 K.Kitachi [QC#29719, DEL]
//            setValue(apiPMsg.mdseCd, rs.getString("MDSE_CD"));
            // END 2019/01/30 K.Kitachi [QC#29719, DEL]
            // END   2019/01/10 [QC#29719, ADD]
        }

        int index = targetCount - 1;
        setValue(apiPMsg.meterList.no(index).mdlMtrLbCd, this.mtrLbCd);
        setValue(apiPMsg.meterList.no(index).readMtrCnt, rs.getBigDecimal("READ_MTR_CNT"));
        setValue(apiPMsg.meterList.no(index).testMtrCnt, rs.getBigDecimal("TEST_MTR_CNT"));
        setValue(apiPMsg.meterList.no(index).estFlg, ZYPConstant.FLG_OFF_N);
        // START 2018/02/08 M.Kidokoro [QC#18145,ADD]
        setValue(apiPMsg.meterList.no(index).mtrEntryCmntTxt, rs.getString("MTR_ENTRY_CMNT_TXT"));
        // END 2018/02/08 M.Kidokoro [QC#18145,ADD]
        apiPMsg.meterList.setValidCount(targetCount);
    }

    // START 2017/05/22 K.Kitachi [QC#18342, MOD]
//    private boolean callMeterBulkUploadAPI(NSZC051001PMsg apiPMsg, List<BigDecimal> rowNumList) {
    private boolean callMeterBulkUploadAPI(NSZC051001PMsg apiPMsg, List<BigDecimal> rowNumList, List<DS_MTR_INTFCTMsg> dsMtrIntfcTMsgList, List<DS_MSGTMsg> dsMsgTMsgList) {
        new NSZC051001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        // START 2019/08/20 K.Kitachi [QC#52860, ADD]
        if (isRollOver(apiPMsg)) {
            setRollOverError(apiPMsg);
        }
        // END 2019/08/20 K.Kitachi [QC#52860, ADD]
        BigDecimal dsMtrIntfcPk;
        BigDecimal errGrpSq = null;
        DS_MTR_INTFCTMsg dsMtrIntfcTMsg;
        for (int i = 0; i < apiPMsg.rsltPrmList.getValidCount(); i++) {
            dsMtrIntfcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_MTR_INTFC_SQ);
            if (DS_MTR_PROC_STS.ERROR.equals(apiPMsg.dsMtrProcStsCd.getValue()) && !hasValue(errGrpSq)) {
                errGrpSq = dsMtrIntfcPk.negate();
            }
            dsMtrIntfcTMsg = createDsMtrIntfcTMsg(apiPMsg, dsMtrIntfcPk, errGrpSq, i);
            dsMtrIntfcTMsgList.add(dsMtrIntfcTMsg);
            if (hasValue(apiPMsg.dsMsgTxt)) {
                dsMsgTMsgList.add(createDsMsgTMsg(apiPMsg, dsMtrIntfcPk));
            }
        }
        // Mod Start 2017/09/19 QC#21185
        String dsMtrProcStsCd = apiPMsg.dsMtrProcStsCd.getValue();
        if (!hasValue(dsMtrProcStsCd) || DS_MTR_PROC_STS.ERROR.equals(dsMtrProcStsCd)) {
//            // START 2017/02/07 N.Arai [QC#17296, MOD]
////            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
////            for (String xxMsgId : xxMsgIdList) {
////                for (BigDecimal targetRowNum : rowNumList) {
////                    this.messenger.addMessageForRecord(targetRowNum, xxMsgId, null);
////                }
////            }
//            String xxMsgId = S21ApiUtil.getXxMsgIdList(apiPMsg).get(0);
//            S21ApiMessage xxMsg = S21ApiUtil.getXxMsgList(apiPMsg).get(0);
//            String[] msgPrms = xxMsg.getXxMsgPrmArray();
//            String msg = S21MessageFunc.clspGetMessage(xxMsgId, msgPrms);
//            for (BigDecimal rowNum : rowNumList) {
//                this.messenger.addMessageForRecord(rowNum, NSAM0624E, msg);
//            }
//            // END 2017/02/07 N.Arai [QC#17296, MOD]
            for (BigDecimal rowNum : rowNumList) {
                this.messenger.addMessageForRecord(rowNum, NSAM0624E, apiPMsg.dsMsgTxt.getValue());
            }
            return false;
        }
        if (DS_MTR_PROC_STS.WARNING.equals(dsMtrProcStsCd)) {
            for (BigDecimal rowNum : rowNumList) {
                this.messenger.addMessageForRecord(rowNum, NSAM0700W, apiPMsg.dsMsgTxt.getValue());
            }
        }
        // Mod End 2017/09/19 QC#21185
        // START 2024/03/26 K.Watanabe [QC#63549, ADD]
        int finalreadPeriod = getFinalReadPeriod(apiPMsg.svcMachMstrPk.getValue(), apiPMsg.mtrReadDt.getValue());
        if (finalreadPeriod != 0) {
            setValue(apiPMsg.dsMtrProcStsCd, DS_MTR_PROC_STS.WARNING);
            for (BigDecimal rowNum : rowNumList) {
                this.messenger.addMessageForRecord(rowNum, NSAM0787W, apiPMsg.dsMsgTxt.getValue());
            }
        }
        // END   2024/03/26 K.Watanabe [QC#63549, ADD]
        return true;
    }
    // END 2017/05/22 K.Kitachi [QC#18342, MOD]

    // START 2024/03/26 K.Watanabe [QC#63549, ADD]
    /**
     * getFinalReadPeriod
     * @param svcMachMstrPk BigDecimal
     * @param mtrReadDt String
     * @return Integer
     */
    private Integer getFinalReadPeriod(BigDecimal svcMachMstrPk, String mtrReadDt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dateFormat", ZYPDateUtil.TYPE_YYYYMMDD);
        paramMap.put("defBefDays", DEF_WINDOW_BEF_DAYS);
        BigDecimal mtrReadWinMlyDaysAot = BigDecimal.ZERO;
        BigDecimal mtrReadWinOthDaysAot = BigDecimal.ZERO;
        DS_WIN_DAYSTMsg dsWinDaysTMsg = new DS_WIN_DAYSTMsg();
        ZYPEZDItemValueSetter.setValue(dsWinDaysTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsWinDaysTMsg.dsWinDaysTrgtPerTxt, DS_WIN_DAYS_TRGT_PER_TXT);
        ZYPEZDItemValueSetter.setValue(dsWinDaysTMsg.svcLineBizCd, DS_WIN_DAYS_SVC_LINE_BIZ_CD_ALL);
        dsWinDaysTMsg = (DS_WIN_DAYSTMsg) S21ApiTBLAccessor.findByKey(dsWinDaysTMsg);
        if (dsWinDaysTMsg != null) {
            mtrReadWinMlyDaysAot = dsWinDaysTMsg.mtrReadWinMlyDaysAot.getValue();
            mtrReadWinOthDaysAot = dsWinDaysTMsg.mtrReadWinOthDaysAot.getValue();
        }
        paramMap.put("mtrReadWinMlyDaysAot", mtrReadWinMlyDaysAot);
        paramMap.put("mtrReadWinOthDaysAot", mtrReadWinOthDaysAot);
        paramMap.put("months", BLLG_CYCLE_UOM.MONTHS);
        paramMap.put("usgChrgFlg", ZYPConstant.FLG_ON_Y);
        paramMap.put("invFlg", ZYPConstant.FLG_ON_Y);
        paramMap.put("dsBllgSchdStsCd", DS_BLLG_SCHD_STS.CLOSE);
        paramMap.put("skipRecovTp", SKIP_RECOV_TP.SKIP);
        paramMap.put("svcMachMstrPk", svcMachMstrPk);
        paramMap.put("svcLineBizCd", DS_WIN_DAYS_SVC_LINE_BIZ_CD_ALL);
        paramMap.put("mtrReadDt", mtrReadDt);
        return (Integer) ssmBatchClient.queryObject("getFinalReadPeriod", paramMap);
    }
    // END   2024/03/26 K.Watanabe [QC#63549, ADD]

    private boolean changeTarget(String prevSerNum, String prevMtrReadDt, ResultSet rs) throws SQLException {
        if (!hasValue(prevSerNum)) {
            return false;
        }
        if (!hasValue(prevMtrReadDt)) {
            return false;
        }
        if (!prevSerNum.equals(rs.getString("SER_NUM"))) {
            return true;
        }
        if (!prevMtrReadDt.equals(rs.getString("MTR_READ_DT"))) {
            return true;
        }
        return false;
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
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.upldCsvRqstPk, upldCsvRqstPk);

        UPLD_CSV_RQSTTMsg outTMsg = (UPLD_CSV_RQSTTMsg) EZDTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            throw new S21AbendException(ZYPM0181E);
        }
        return outTMsg;
    }

    private String getMtrLbCd(ResultSet rs) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("mtrLbCd", rs.getString("MTR_LB_CD"));
        paramMap.put("mtrLbDescTxt", rs.getString("MTR_LB_DESC_TXT"));
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("minEffFromDt", MIN_EFF_FROM_DT);
        paramMap.put("maxEffThruDt", MAX_EFF_THRU_DT);
        return (String) ssmBatchClient.queryObject("getMtrLbCd", paramMap);
    }

    // START 2018/01/22 [QC#29719, MOD]
    // private BigDecimal getSvcPhysMtrPk(ResultSet rs) throws SQLException {
    // START 2019/01/30 K.Kitachi [QC#29719, MOD]
//    private BigDecimal getSvcPhysMtrPk(BigDecimal svcMachMstrPk) throws SQLException {
    private BigDecimal getSvcPhysMtrPk(ResultSet rs) throws SQLException {
    // END 2019/01/30 K.Kitachi [QC#29719, MOD]
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        // paramMap.put("svcMachMstrPk", rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        // START 2019/01/30 K.Kitachi [QC#29719, MOD]
//        paramMap.put("svcMachMstrPk", svcMachMstrPk);
        paramMap.put("svcMachMstrPk", rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        // END 2019/01/30 K.Kitachi [QC#29719, MOD]
        paramMap.put("mtrLbCd", this.mtrLbCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("minEffFromDt", MIN_EFF_FROM_DT);
        paramMap.put("maxEffThruDt", MAX_EFF_THRU_DT);
        return (BigDecimal) ssmBatchClient.queryObject("getSvcPhysMtrPk", paramMap);
    }
    // END   2018/01/22 [QC#29719, MOD]

    // START 2020/03/03 [QC#56123, MOD]
    private BigDecimal getPrevMtrCnt(BigDecimal svcPhysMtrPk, String mtrReadDt) throws SQLException {
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("glblCmpyCd", this.glblCmpyCd);
//        paramMap.put("svcPhysMtrPk", svcPhysMtrPk);
//        paramMap.put("dsMtrReadTpGrpB", DS_MTR_READ_TP_GRP.BILLABLE_READS);
//        return (BigDecimal) ssmBatchClient.queryObject("getPrevMtrCnt", paramMap);
        List<String> dsMtrReadTpGrpCdList = new ArrayList<String>();
        dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.BILLABLE_READS);
        SvcPhysMtrReadInfoBean bean = NSXC003001SvcPhysMtrRead.getPrevMtrReadInfoByMtrReadTpGrp(this.glblCmpyCd, mtrReadDt, svcPhysMtrPk, dsMtrReadTpGrpCdList);
        if (bean == null) {
            return null;
        }
        return bean.getReadMtrCnt();
    }
    // END   2020/03/03 [QC#56123, MOD]

    // START 2020/03/03 [QC#56123, ADD]
    private BigDecimal getNextMtrCnt(BigDecimal svcPhysMtrPk, String mtrReadDt) throws SQLException {
      List<String> dsMtrReadTpGrpCdList = new ArrayList<String>();
      dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.BILLABLE_READS);
      SvcPhysMtrReadInfoBean bean = NSXC003001SvcPhysMtrRead.getNextMtrReadInfoByMtrReadTpGrp(this.glblCmpyCd, mtrReadDt, svcPhysMtrPk, dsMtrReadTpGrpCdList);
      if (bean == null) {
          return null;
      }
      return bean.getReadMtrCnt();
  }
    // END   2020/03/03 [QC#56123, ADD]

    // START 2020/03/03 [QC#56123, DEL]
    // START 2017/12/20 M.Kidokoro [QC#21127, ADD]
//    private List<Map<String, Object>> getSvcPhysMtrRead(BigDecimal svcMachMstrPk) throws SQLException {
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("glblCmpyCd", this.glblCmpyCd);
//        paramMap.put("svcMachMstrPk", svcMachMstrPk);
//        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSvcPhysMtrRead", paramMap);
//    }
    // END 2017/12/20 M.Kidokoro [QC#21127, ADD]
    // END   2020/03/03 [QC#56123, DEL]

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

    private void addMsgNoErrRec(List<BigDecimal> rowNumList) {
        for (BigDecimal targetRowNum : rowNumList) {
            this.messenger.addMessageForRecord(targetRowNum, NSAM0517E, null);
        }
    }

    private void setErrMsg(ART10FMsg fMsg) {
        rollback();
        this.messenger.uploadMessage();
    }

    // Add Start 2017/09/19 QC#21185
    private void setWrnMsg() {
        this.messenger.uploadMessage();
    }
    // Add End 2017/09/19 QC#21185

    // START 2017/05/22 K.Kitachi [QC#18342, ADD]
    private void meterBulkUploadProcess(NSZC051001PMsg apiPMsg, List<BigDecimal> rowNumList, int targetCount, ART10FMsg fMsg) {
        List<DS_MTR_INTFCTMsg> dsMtrIntfcTMsgList = new ArrayList<DS_MTR_INTFCTMsg>();
        List<DS_MSGTMsg> dsMsgTMsgList = new ArrayList<DS_MSGTMsg>();
        if (callMeterBulkUploadAPI(apiPMsg, rowNumList, dsMtrIntfcTMsgList, dsMsgTMsgList)) {
            this.normalCount += targetCount;
            // Add Start 2017/09/19 QC#21185
            String dsMtrProcStsCd = apiPMsg.dsMtrProcStsCd.getValue();
            if (DS_MTR_PROC_STS.WARNING.equals(dsMtrProcStsCd)) {
                setWrnMsg();
                this.wrnFlg = true;
            }
            // Add End 2017/09/19 QC#21185
        } else {
            setErrMsg(fMsg);
            this.errCount += targetCount;
        }
        if (dsMtrIntfcTMsgList.size() > 0) {
            insertDsMtrIntfc(dsMtrIntfcTMsgList);
        }
        if (dsMsgTMsgList.size() > 0) {
            insertDsMsg(dsMsgTMsgList);
        }
        commit();
    }

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
    // END 2017/05/22 K.Kitachi [QC#18342, ADD]

    // add start 2018/07/17 QC#22583
    private String createResultMessageArg() {
        StringBuilder builder = new StringBuilder();
        if (this.normalCount > 0 || this.errCount == 0) {
            if (this.wrnFlg) {
                builder.append(String.format(RESULT_MSG_INS_MARNING, this.normalCount));
            } else {
                builder.append(String.format(RESULT_MSG_INS, this.normalCount));
            }
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

    // START 2019/08/20 K.Kitachi [QC#52860, ADD]
    private boolean isInvoicedPeriod(BigDecimal svcMachMstrPk, String mtrReadDt) throws SQLException {
        BigDecimal count = getInvoicedPeriodCount(svcMachMstrPk, mtrReadDt);
        if (BigDecimal.ZERO.compareTo(count) < 0) {
            return true;
        }
        return false;
    }

    private BigDecimal getInvoicedPeriodCount(BigDecimal svcMachMstrPk, String mtrReadDt) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcMachMstrPk", svcMachMstrPk);
        paramMap.put("mtrReadDt", mtrReadDt);
        return (BigDecimal) this.ssmBatchClient.queryObject("getInvoicedPeriodCount", paramMap);
    }

    private boolean isRollOver(NSZC051001PMsg pMsg) {
        if (DS_MTR_PROC_STS.ERROR.equals(pMsg.dsMtrProcStsCd.getValue())) {
            return false;
        }
        List<BigDecimal> svcPhysMtrReadGrpSqList = new ArrayList<BigDecimal>();
        for (int i = 0; i < pMsg.rsltPrmList.getValidCount(); i++) {
            BigDecimal svcPhysMtrReadGrpSq = pMsg.rsltPrmList.no(i).svcPhysMtrReadGrpSq.getValue();
            if (!svcPhysMtrReadGrpSqList.contains(svcPhysMtrReadGrpSq)) {
                svcPhysMtrReadGrpSqList.add(svcPhysMtrReadGrpSq);
            }
        }
        for (BigDecimal svcPhysMtrReadGrpSq : svcPhysMtrReadGrpSqList) {
            SVC_PHYS_MTR_READTMsgArray svcPhysMtrReadList = getSvcPhysMtrReadList(svcPhysMtrReadGrpSq);
            for (int i = 0; i < svcPhysMtrReadList.getValidCount(); i++) {
                if (CNTR_RESET_TP.METER_ROLLOVER.equals(svcPhysMtrReadList.no(i).cntrResetTpCd.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    private SVC_PHYS_MTR_READTMsgArray getSvcPhysMtrReadList(BigDecimal svcPhysMtrReadGrpSq) {
        SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
        inMsg.setSQLID("007");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("svcPhysMtrReadGrpSq01", svcPhysMtrReadGrpSq);
        return (SVC_PHYS_MTR_READTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    private void setRollOverError(NSZC051001PMsg pMsg) {
        setValue(pMsg.dsMtrProcStsCd, DS_MTR_PROC_STS.ERROR);
        setValue(pMsg.dsMsgTxt, EZDMessageEnv.getMessage(NSZM1335W));
    }
    // END 2019/08/20 K.Kitachi [QC#52860, ADD]
}
