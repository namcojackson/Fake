/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB100001;

import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.BATCH_ID;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.FETCH_SIZE_MAX;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.NSBM0197E;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.NSBM0198E;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.NSBM0199E;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.NSBM0200E;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.NSBM0201E;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.NSBM0202E;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.NSBM0203E;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.NSBM0205E;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.NSBM0206E;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.NSBM0207E;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.NSBM0219E;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.NSBM0220E;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.NSBM0221E;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.NSBM0222E;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.NYXM0001I;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.NYXM0002E;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.PROC_MODE_CD_CREATE;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.PROC_MODE_CD_UPDATE;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.RESULT_MSG_ERR;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.RESULT_MSG_INS;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.RESULT_MSG_INS_ERR;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.RESULT_MSG_INS_MARNING;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.VLD_MTR_FLG_X;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.ZYPM0181E;
import static com.canon.cusa.s21.batch.NSB.NSBB100001.constant.NSBB100001Constant.ZZM9000E;
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
import parts.dbcommon.EZDTBLAccessor;
import business.db.ART10FMsg;
import business.db.DS_MTR_READ_TPTMsg;
import business.db.DS_TEST_COPY_CLSTMsg;
import business.db.FSR_VISITTMsg;
import business.db.FSR_VISITTMsgArray;
import business.db.MTR_LBTMsg;
import business.db.MTR_LBTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_PHYS_MTRTMsg;
import business.db.SVC_PHYS_MTRTMsgArray;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.db.SVC_PHYS_MTR_READTMsgArray;
import business.db.SVC_PHYS_MTR_UPD_WRKTMsg;
import business.db.UPLD_CSV_RQSTTMsg;
import business.parts.NSZC010001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC010001.NSZC010001;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CNTR_RESET_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TEST_COPY_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Service Physical Meter Update Batch
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/01/26   Hitachi         H.Watanabe      Create          QC#60925
 * 2023/07/07   Hitachi         H.Watanabe      Update          QC#60925
 * 2023/09/17   Hitachi         Y.Nagasawa      Update          QC#60925
 * 2023/10/20   Hitachi         N.Takatsu       Update          QC#60925
 * 2024/03/13   CITS            Aizawa          Update          QC#60925
 *</pre>
 */
public class NSBB100001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    // -- Other Internal Variable ---------------
    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    // START 2024/03/13 t.aizawa [QC#61771, MOD]
    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;
    // END 2024/03/13 t.aizawa [QC#61771, MOD]

    /** Execute Param */
    S21SsmExecutionParameter excParam = null;

    /** Term code */
    private TERM_CD termCd = null;

    /** total search count */
    private int searchCnt = 0;

    /** Info success count */
    private int infoSccessCnt = 0;

    /** ZYPCSVUploadMessenger */
    private ZYPCSVUploadMessenger messenger = null;

    /** BatchHelper */
    private S21RequestBatchHelper batchHelper = null;

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSBB100001().executeBatch(NSBB100001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Initialization
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());
        // START 2024/03/13 t.aizawa [QC#61771, ADD]
        this.ssmBatchClient = S21SsmBatchClient.getClient(getClass());
        // END 2024/03/13 t.aizawa [QC#61771, ADD]
        this.termCd = TERM_CD.NORMAL_END;

        // Get the Global Company Code.
        // If an error occurs, throw Exception.
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Global Company Code" });
        }

        // Get the Sales Date.
        // If an error occurs, throw Exception.
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, BATCH_ID);
        if (!hasValue(slsDt)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Sales Date" });
        }

        this.batchHelper = new S21RequestBatchHelper();

        excParam = new S21SsmExecutionParameter();
        excParam.setFetchSize(FETCH_SIZE_MAX);
        excParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        excParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        excParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
    }

    @Override
    protected void mainRoutine() {
        if (!this.batchHelper.isRecord()) {
            this.termCd = TERM_CD.WARNING_END;
            return;
        }
        for (ART10FMsg request : this.batchHelper.getRequestList()) {
            doProcess(request);
            commit();
        }
    }

    @Override
    protected void termRoutine() {
        // Set term code and total count.
        setTermState(this.termCd, infoSccessCnt, searchCnt - infoSccessCnt, searchCnt);
    }

    /**
     * doProcess
     */
    private void doProcess(ART10FMsg fMsg) {

        PreparedStatement Stmt = null;
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

            Stmt = ssmLLClient.createPreparedStatement("getSvcPhysMtrUpdInfoWrk", setParamForTargetList(upldCSVRqstTMsg), excParam);
            rs = Stmt.executeQuery();

            List<SVC_PHYS_MTR_UPD_WRKTMsg> svcPhysMtrUpdWrkTMsgArray = new ArrayList<SVC_PHYS_MTR_UPD_WRKTMsg>();

            // 2023/07/07 QC#60925 DEL Start
//            int groupCount = 0;
            // 2023/07/07 QC#60925 DEL End
            // START 2023/10/20 N.Takatsu [QC#60925, ADD]
            List<SVC_PHYS_MTR_UPD_WRKTMsg> svcPhysMtrUpdWrkTMsgArrayForMach = new ArrayList<SVC_PHYS_MTR_UPD_WRKTMsg>();
            BigDecimal preSvcMachMstrPk = null;
            // END   2023/10/20 N.Takatsu [QC#60925, ADD]
            while (rs.next()) {

                this.searchCnt++;
                if (isSameGroup(rs, svcPhysMtrUpdWrkTMsgArray)) {
                    svcPhysMtrUpdWrkTMsgArray.add(setTagetList(rs));
                    // 2023/07/07 QC#60925 DEL Start
//                    groupCount++;
                    // 2023/07/07 QC#60925 DEL End
                    // START 2023/10/20 N.Takatsu [QC#60925, ADD]
                    preSvcMachMstrPk = rs.getBigDecimal("SVC_MACH_MSTR_PK");
                    // END   2023/10/20 N.Takatsu [QC#60925, ADD]
                    continue;
                }
                checkAndUpdate(svcPhysMtrUpdWrkTMsgArray);
                // 2023/07/07 QC#60925 DEL Start
//                groupCount = 0;
                // 2023/07/07 QC#60925 DEL End
                // START 2023/10/20 N.Takatsu [QC#60925, ADD]
                for (int i = 0; i < svcPhysMtrUpdWrkTMsgArray.size(); i++) {
                    svcPhysMtrUpdWrkTMsgArrayForMach.add(svcPhysMtrUpdWrkTMsgArray.get(i));
                }
                if (hasValue(preSvcMachMstrPk) && preSvcMachMstrPk.compareTo(rs.getBigDecimal("SVC_MACH_MSTR_PK")) != 0) {
                    boolean isErrMach = false;
                    for (int i = 0; i < svcPhysMtrUpdWrkTMsgArrayForMach.size(); i++) {
                        if (hasValue(svcPhysMtrUpdWrkTMsgArrayForMach.get(i).upldCsvRqstCmntTxt)) {
                            isErrMach = true;
                            break;
                        }
                    }
                    if (isErrMach) {
                        rollback();
                        for (int i = 0; i < svcPhysMtrUpdWrkTMsgArrayForMach.size(); i++) {
                            if (!hasValue(svcPhysMtrUpdWrkTMsgArrayForMach.get(i).upldCsvRqstCmntTxt)) {
                                String arg = RESULT_MSG_INS_ERR;
                                this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArrayForMach.get(i).upldCsvRqstRowNum.getValue(), NYXM0002E, arg);
                                updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArrayForMach.get(i), S21MessageFunc.clspGetMessage(NYXM0002E, new String[] {arg }));
                                this.infoSccessCnt--;
                            }
                            updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArrayForMach.get(i));
                        }
                    } else {
                        updateCompleteProcess(svcPhysMtrUpdWrkTMsgArrayForMach);
                    }
                    commit();
                    svcPhysMtrUpdWrkTMsgArrayForMach.clear();
                }
                // END   2023/10/20 N.Takatsu [QC#60925, ADD]
                svcPhysMtrUpdWrkTMsgArray.clear();
                svcPhysMtrUpdWrkTMsgArray.add(setTagetList(rs));
                // 2023/07/07 QC#60925 DEL Start
//                groupCount++;
                // 2023/07/07 QC#60925 DEL End
            }
            if (svcPhysMtrUpdWrkTMsgArray.size() > 0) {
                checkAndUpdate(svcPhysMtrUpdWrkTMsgArray);
                // START 2023/10/20 N.Takatsu [QC#60925, ADD]
                for (int i = 0; i < svcPhysMtrUpdWrkTMsgArray.size(); i++) {
                    svcPhysMtrUpdWrkTMsgArrayForMach.add(svcPhysMtrUpdWrkTMsgArray.get(i));
                }
                boolean isErrMach = false;
                for (int i = 0; i < svcPhysMtrUpdWrkTMsgArrayForMach.size(); i++) {
                    if (hasValue(svcPhysMtrUpdWrkTMsgArrayForMach.get(i).upldCsvRqstCmntTxt)) {
                        isErrMach = true;
                        break;
                    }
                }
                if (isErrMach) {
                    rollback();
                    for (int i = 0; i < svcPhysMtrUpdWrkTMsgArrayForMach.size(); i++) {
                        if (!hasValue(svcPhysMtrUpdWrkTMsgArrayForMach.get(i).upldCsvRqstCmntTxt)) {
                            String arg = RESULT_MSG_INS_ERR;
                            this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArrayForMach.get(i).upldCsvRqstRowNum.getValue(), NYXM0002E, arg);
                            updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArrayForMach.get(i), S21MessageFunc.clspGetMessage(NYXM0002E, new String[] {arg }));
                            this.infoSccessCnt--;
                        }
                        updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArrayForMach.get(i));
                    }
                } else {
                    updateCompleteProcess(svcPhysMtrUpdWrkTMsgArrayForMach);
                }
                commit();
                // END   2023/10/20 N.Takatsu [QC#60925, ADD]
            }
            closeCsvFile(fMsg);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(Stmt, rs);
        }
    }

    private UPLD_CSV_RQSTTMsg getUpldCSVRqst(BigDecimal upldCsvRqstPk) {
        UPLD_CSV_RQSTTMsg inTMsg = new UPLD_CSV_RQSTTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.upldCsvRqstPk, upldCsvRqstPk);

        UPLD_CSV_RQSTTMsg outTMsg = (UPLD_CSV_RQSTTMsg) EZDTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            throw new S21AbendException(ZYPM0181E);
        }
        return outTMsg;
    }

    private boolean isSameGroup(ResultSet rs, List<SVC_PHYS_MTR_UPD_WRKTMsg> svcPhysMtrUpdWrkTMsgArray) throws SQLException {
        // 2023/09/17 QC#60925 Mod Start
        // if (this.searchCnt == 1) {
        if (svcPhysMtrUpdWrkTMsgArray == null || svcPhysMtrUpdWrkTMsgArray.size() == 0) {
        // 2023/09/17 QC#60925 Mod End
            return true;
        }
        if (!ZYPCommonFunc.hasValue(rs.getString("PROC_MODE_CD"))) {
            return false;
        }
        if (!svcPhysMtrUpdWrkTMsgArray.get(0).procModeCd.getValue().equals(rs.getString("PROC_MODE_CD"))) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(rs.getBigDecimal("SVC_MACH_MSTR_PK"))) {
            return false;
        }
        if (!svcPhysMtrUpdWrkTMsgArray.get(0).svcMachMstrPk.getValue().equals(rs.getBigDecimal("SVC_MACH_MSTR_PK"))) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(rs.getString("MTR_READ_DT"))) {
            return false;
        }
        if (!svcPhysMtrUpdWrkTMsgArray.get(0).mtrReadDt.getValue().equals(rs.getString("MTR_READ_DT"))) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(0).svcPhysMtrReadGrpSq)) {
            if (!svcPhysMtrUpdWrkTMsgArray.get(0).svcPhysMtrReadGrpSq.getValue().equals(rs.getBigDecimal("SVC_PHYS_MTR_READ_GRP_SQ"))) {
                return false;
            }
        } else {
            // START 2023/10/20 N.Takatsu [QC#60925, MOD]
//            if (!svcPhysMtrUpdWrkTMsgArray.get(0).dsTestCopyClsDescTxt.getValue().equals(rs.getString("DS_TEST_COPY_CLS_DESC_TXT"))) {
            if (!isEqualsForString(svcPhysMtrUpdWrkTMsgArray.get(0).dsTestCopyClsDescTxt.getValue(), rs.getString("DS_TEST_COPY_CLS_DESC_TXT"))) {
            // END   2023/10/20 N.Takatsu [QC#60925, MOD]
                return false;
            }
            // START 2023/10/20 N.Takatsu [QC#60925, MOD]
//            if (!svcPhysMtrUpdWrkTMsgArray.get(0).svcTaskNum.getValue().equals(rs.getString("SVC_TASK_NUM"))) {
            if (!isEqualsForString(svcPhysMtrUpdWrkTMsgArray.get(0).svcTaskNum.getValue(), rs.getString("SVC_TASK_NUM"))) {
            // END   2023/10/20 N.Takatsu [QC#60925, MOD]
                return false;
            }
            if (svcPhysMtrUpdWrkTMsgArray.get(0).mtrLbDescTxt.getValue().equals(rs.getString("MTR_LB_DESC_TXT"))) {
                return false;
            }
        }
        return true;
    }

    private SVC_PHYS_MTR_UPD_WRKTMsg setTagetList(ResultSet rs) throws SQLException {
        SVC_PHYS_MTR_UPD_WRKTMsg svcPhysMtrUpdWrkTMsg = new SVC_PHYS_MTR_UPD_WRKTMsg();
        setValue(svcPhysMtrUpdWrkTMsg.ezInUserID, rs.getString("EZINUSERID"));
        setValue(svcPhysMtrUpdWrkTMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
        setValue(svcPhysMtrUpdWrkTMsg.upldCsvRqstPk, rs.getBigDecimal("UPLD_CSV_RQST_PK"));
        setValue(svcPhysMtrUpdWrkTMsg.upldCsvRqstRowNum, rs.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"));
        setValue(svcPhysMtrUpdWrkTMsg.procModeCd, rs.getString("PROC_MODE_CD"));
        setValue(svcPhysMtrUpdWrkTMsg.serNum, rs.getString("SER_NUM"));
        setValue(svcPhysMtrUpdWrkTMsg.svcMachMstrPk, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        setValue(svcPhysMtrUpdWrkTMsg.svcPhysMtrReadGrpSq, rs.getBigDecimal("SVC_PHYS_MTR_READ_GRP_SQ"));
        setValue(svcPhysMtrUpdWrkTMsg.mtrLbDescTxt, rs.getString("MTR_LB_DESC_TXT"));
        setValue(svcPhysMtrUpdWrkTMsg.dsMtrReadTpGrpCd, rs.getString("DS_MTR_READ_TP_GRP_CD"));
        setValue(svcPhysMtrUpdWrkTMsg.dsMtrReadTpDescTxt, rs.getString("DS_MTR_READ_TP_DESC_TXT"));
        // 2023/09/17 QC#60925 Mod Start
        // setValue(svcPhysMtrUpdWrkTMsg.vldMtrFlg, rs.getString("VLD_MTR_FLG"));
        setValue(svcPhysMtrUpdWrkTMsg.vldMtrTxt, rs.getString("VLD_MTR_TXT"));
        // 2023/09/17 QC#60925 Mod End
        setValue(svcPhysMtrUpdWrkTMsg.mtrReadDt, getAndCheckDateFormat(rs.getString("MTR_READ_DT")));
        setValue(svcPhysMtrUpdWrkTMsg.svcTaskNum, rs.getString("SVC_TASK_NUM"));
        setValue(svcPhysMtrUpdWrkTMsg.dsTestCopyClsDescTxt, rs.getString("DS_TEST_COPY_CLS_DESC_TXT"));
        setValue(svcPhysMtrUpdWrkTMsg.readMtrCnt, rs.getBigDecimal("READ_MTR_CNT"));
        setValue(svcPhysMtrUpdWrkTMsg.mtrEntryCmntTxt, rs.getString("MTR_ENTRY_CMNT_TXT"));
        setValue(svcPhysMtrUpdWrkTMsg.upldCsvRqstCmntTxt, rs.getString("UPLD_CSV_RQST_CMNT_TXT"));
        return svcPhysMtrUpdWrkTMsg;
    }

    private void checkAndUpdate(List<SVC_PHYS_MTR_UPD_WRKTMsg> svcPhysMtrUpdWrkTMsgArray) throws SQLException {
        boolean errFlg = false;
        int errIndex = 0;
        for (int i = 0; i < svcPhysMtrUpdWrkTMsgArray.size(); i++) {
            if (hasInputError(svcPhysMtrUpdWrkTMsgArray, i)) {
                errFlg = true;
                errIndex = i;
                break;
            }
            if (hasMasterCheckError(svcPhysMtrUpdWrkTMsgArray, i)) {
                errFlg = true;
                errIndex = i;
                break;
            }
            if (hasRelationalError(svcPhysMtrUpdWrkTMsgArray, i)) {
                errFlg = true;
                errIndex = i;
                break;
            }
        }
        // 2023/07/07 QC#60925 Add Start
        if (!errFlg) {
            if (isCarrOverMtrError(svcPhysMtrUpdWrkTMsgArray)) {
                errFlg = true;
            }
        }
        // 2023/07/07 QC#60925 Add End
        if (errFlg) {
            // START 2023/10/20 N.Takatsu [QC#60925, DEL]
//            setValue(svcPhysMtrUpdWrkTMsgArray.get(errIndex).upldCsvRqstCmntTxt, "e");
            // END   2023/10/20 N.Takatsu [QC#60925, DEL]
            updateErrorProcess(svcPhysMtrUpdWrkTMsgArray);
            this.termCd = TERM_CD.WARNING_END;
        } else {
            if (PROC_MODE_CD_CREATE.equals(svcPhysMtrUpdWrkTMsgArray.get(0).procModeCd.getValue())) {
                if (createSvcPhysMtrRerd(svcPhysMtrUpdWrkTMsgArray)) {
                    // START 2023/10/20 N.Takatsu [QC#60925, DEL]
//                    updateCompleteProcess(svcPhysMtrUpdWrkTMsgArray);
                    // END   2023/10/20 N.Takatsu [QC#60925, DEL]
                }
            } else {
                if (updateSvcPhysMtrRerd(svcPhysMtrUpdWrkTMsgArray)) {
                    // START 2023/10/20 N.Takatsu [QC#60925, DEL]
//                    updateCompleteProcess(svcPhysMtrUpdWrkTMsgArray);
                    // END   2023/10/20 N.Takatsu [QC#60925, DEL]
                }
            }
        }

    }

    private boolean hasInputError(List<SVC_PHYS_MTR_UPD_WRKTMsg> svcPhysMtrUpdWrkTMsgArray, int index) throws SQLException {
        if (!ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).procModeCd.getValue())) {
            this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), ZZM9000E, "Mode");
            updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(ZZM9000E, new String[] {"Mode" }));
            return true;
        }
        if (!ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).svcMachMstrPk.getValue())) {
            this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), ZZM9000E, "Machine Master ID");
            updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(ZZM9000E, new String[] {"Machine Master ID" }));
            return true;
        }
        if (!ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).mtrLbDescTxt.getValue())) {
            this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), ZZM9000E, "Counter Type");
            updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(ZZM9000E, new String[] {"Counter Type" }));
            return true;
        }
        if (!ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).mtrReadDt.getValue())) {
            this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), ZZM9000E, "Meter Read Date");
            updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(ZZM9000E, new String[] {"Meter Read Date" }));
            return true;
        }
        if (!ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).readMtrCnt.getValue())) {
            this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), ZZM9000E, "Meter Reading");
            updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(ZZM9000E, new String[] {"Meter Reading" }));
            return true;
        }
        if (PROC_MODE_CD_CREATE.equals(svcPhysMtrUpdWrkTMsgArray.get(index).procModeCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpGrpCd.getValue())) {
                this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), ZZM9000E, "Reading Type");
                updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(ZZM9000E, new String[] {"Reading Type" }));
                return true;
            }
            if (!ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpDescTxt.getValue())) {
                this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), ZZM9000E, "Meter Read Type");
                updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(ZZM9000E, new String[] {"Meter Read Type" }));
                return true;
            }
        // 2023/07/07 QC#60925 Mod Start
//        } else {
        }
        if (PROC_MODE_CD_UPDATE.equals(svcPhysMtrUpdWrkTMsgArray.get(index).procModeCd.getValue())) {
        // 2023/07/07 QC#60925 Mod End
            if (!ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).svcPhysMtrReadGrpSq.getValue())) {
                this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), ZZM9000E, "Meter Read Group Seq");
                updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(ZZM9000E, new String[] {"Meter Read Group Seq" }));
                return true;
            }
        }
        return false;
    }

    private boolean hasMasterCheckError(List<SVC_PHYS_MTR_UPD_WRKTMsg> svcPhysMtrUpdWrkTMsgArray, int index) throws SQLException {
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        setValue(svcMachMstrTMsg.glblCmpyCd, svcPhysMtrUpdWrkTMsgArray.get(index).glblCmpyCd.getValue());
        setValue(svcMachMstrTMsg.svcMachMstrPk, svcPhysMtrUpdWrkTMsgArray.get(index).svcMachMstrPk.getValue());
        svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(svcMachMstrTMsg);
        if (svcMachMstrTMsg == null) {
            this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0197E, "Machine Master ID");
            updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0197E, new String[] {"Machine Master ID" }));
            return true;
        }

        if (PROC_MODE_CD_UPDATE.equals(svcPhysMtrUpdWrkTMsgArray.get(index).procModeCd.getValue())) {
            SVC_PHYS_MTR_READTMsg svcPhysMtrReadTMsg = new SVC_PHYS_MTR_READTMsg();
            SVC_PHYS_MTR_READTMsgArray svcPhysMtrReadTMsgArray = new SVC_PHYS_MTR_READTMsgArray();
            svcPhysMtrReadTMsg.setSQLID("007");
            svcPhysMtrReadTMsg.setConditionValue("glblCmpyCd01", svcPhysMtrUpdWrkTMsgArray.get(index).glblCmpyCd.getValue());
            svcPhysMtrReadTMsg.setConditionValue("svcPhysMtrReadGrpSq01", svcPhysMtrUpdWrkTMsgArray.get(index).svcPhysMtrReadGrpSq.getValue());
            svcPhysMtrReadTMsgArray = (SVC_PHYS_MTR_READTMsgArray) EZDTBLAccessor.findByCondition(svcPhysMtrReadTMsg);
            // 2023/07/07 QC#60925 Mod Start
//            if (svcPhysMtrReadTMsgArray.getValidCount()() <= 0) {
            if (svcPhysMtrReadTMsgArray.length() <= 0) {
            // 2023/07/07 QC#60925 Mod End
                this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0197E, "Meter Read Group Seq");
                updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0197E, new String[] {"Meter Read Group Seq" }));
                return true;
            }
        }

        MTR_LBTMsg mtrLbTMsg = new MTR_LBTMsg();
        MTR_LBTMsgArray mtrLbTMsgArray = new MTR_LBTMsgArray();
        mtrLbTMsg.setSQLID("004");
        mtrLbTMsg.setConditionValue("glblCmpyCd01", svcPhysMtrUpdWrkTMsgArray.get(index).glblCmpyCd.getValue());
        mtrLbTMsg.setConditionValue("mtrLbDescTxt01", svcPhysMtrUpdWrkTMsgArray.get(index).mtrLbDescTxt.getValue());
        mtrLbTMsgArray = (MTR_LBTMsgArray) EZDTBLAccessor.findByCondition(mtrLbTMsg);
        if ((mtrLbTMsgArray.getValidCount() <= 0)) {
            this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0197E, "Counter Type");
            updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0197E, new String[] {"Counter Type" }));
            return true;
        }

        // 2023/07/07 QC#60925 Mod Start
        if (PROC_MODE_CD_CREATE.equals(svcPhysMtrUpdWrkTMsgArray.get(index).procModeCd.getValue())) {
            if (ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpGrpCd.getValue())) {
                // START 2024/03/13 t.aizawa [QC#61771, MOD]
                // PreparedStatement Stmt = ssmLLClient.createPreparedStatement("getDsMtrReadTpGrp", setParamgetDsMtrReadTpGrp(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpGrpCd.getValue()), excParam);
                // ResultSet getDsMtrReadTpGrp = Stmt.executeQuery();
                // if (!getDsMtrReadTpGrp.next()) {
                String dsMtrReadTpGrpCd = (String) ssmBatchClient.queryObject("getDsMtrReadTpGrp", setParamgetDsMtrReadTpGrp(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpGrpCd.getValue()), excParam);
                if (!ZYPCommonFunc.hasValue(dsMtrReadTpGrpCd)) {
                // END 2024/03/13 t.aizawa [QC#61771, MOD]
                    this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0197E, "Reading Type");
                    updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0197E, new String[] {"Reading Type" }));
                    return true;
                }
            }
        }
//         if (ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpGrpCd.getValue())) {
//            PreparedStatement Stmt = ssmLLClient.createPreparedStatement("getDsMtrReadTpGrp", setParamgetDsMtrReadTpGrp(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpGrpCd.getValue()), excParam);
//            ResultSet getDsMtrReadTpGrp = Stmt.executeQuery();
//            if (!getDsMtrReadTpGrp.next()) {
//                this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0197E, "Reading Type");
//                updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0197E, new String[] {"Reading Type" }));
//                return true;
//            }
//        }
        // 2023/07/07 QC#60925 Mod End

        // 2023/07/07 QC#60925 Mod Start
        if (PROC_MODE_CD_CREATE.equals(svcPhysMtrUpdWrkTMsgArray.get(index).procModeCd.getValue())) {
            if (ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpDescTxt.getValue())) {
                // START 2024/03/13 t.aizawa [QC#61771, MOD]
                // PreparedStatement Stmt = ssmLLClient.createPreparedStatement("getDsMtrReadTpCd", setParamDsMtrReadTpCd(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpDescTxt.getValue()), excParam);
                // ResultSet dsMtrReadTpCd = Stmt.executeQuery();
                // if (!dsMtrReadTpCd.next()) {
                String dsMtrReadTpCd = (String) ssmBatchClient.queryObject("getDsMtrReadTpCd", setParamDsMtrReadTpCd(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpDescTxt.getValue()), excParam);
                if (!ZYPCommonFunc.hasValue(dsMtrReadTpCd)) {
                // END 2024/03/13 t.aizawa [QC#61771, MOD]
                    this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0197E, "Meter Read Type");
                    updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0197E, new String[] {"Meter Read Type" }));
                    return true;
                }
            }
        }
//         if (ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpDescTxt.getValue())) {
//            PreparedStatement Stmt = ssmLLClient.createPreparedStatement("getDsMtrReadTpCd", setParamDsMtrReadTpCd(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpDescTxt.getValue()), excParam);
//            ResultSet dsMtrReadTpCd = Stmt.executeQuery();
//            if (!dsMtrReadTpCd.next()) {
//                this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0197E, "Meter Read Type");
//                updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0197E, new String[] {"Meter Read Type" }));
//                return true;
//            }
//        }
        // 2023/07/07 QC#60925 Mod End

        // 2023/07/07 QC#60925 Mod Start
        if (PROC_MODE_CD_CREATE.equals(svcPhysMtrUpdWrkTMsgArray.get(index).procModeCd.getValue())) {
            if (ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).dsTestCopyClsDescTxt.getValue())) {
                // START 2024/03/13 t.aizawa [QC#61771, MOD]
                // PreparedStatement Stmt = ssmLLClient.createPreparedStatement("getDsTestCopyCls", setParamDsTestCopyCls(svcPhysMtrUpdWrkTMsgArray.get(index).dsTestCopyClsDescTxt.getValue()), excParam);
                // ResultSet dsTestCopyClsCd = Stmt.executeQuery();
                // if (!dsTestCopyClsCd.next()) {
                String dsTestCopyClsCd = (String) ssmBatchClient.queryObject("getDsTestCopyCls", setParamDsTestCopyCls(svcPhysMtrUpdWrkTMsgArray.get(index).dsTestCopyClsDescTxt.getValue()), excParam);
                if (!ZYPCommonFunc.hasValue(dsTestCopyClsCd)) {
                // END 2024/03/13 t.aizawa [QC#61771, MOD]
                    this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0197E, "In/Out reading ind.");
                    updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0197E, new String[] {"In/Out reading ind." }));
                    return true;
                }
            }
        }
//         if (ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).dsTestCopyClsDescTxt.getValue())) {
//            PreparedStatement Stmt = ssmLLClient.createPreparedStatement("getDsTestCopyCls", setParamDsTestCopyCls(svcPhysMtrUpdWrkTMsgArray.get(index).dsTestCopyClsDescTxt.getValue()), excParam);
//            ResultSet dsTestCopyClsCd = Stmt.executeQuery();
//            if (!dsTestCopyClsCd.next()) {
//                this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0197E, "In/Out reading ind.");
//                updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0197E, new String[] {"In/Out reading ind." }));
//                return true;
//            }
//        }
        // 2023/07/07 QC#60925 Mod End

        return false;
    }

    private boolean hasRelationalError(List<SVC_PHYS_MTR_UPD_WRKTMsg> svcPhysMtrUpdWrkTMsgArray, int index) throws SQLException {
        if ((PROC_MODE_CD_CREATE.equals(svcPhysMtrUpdWrkTMsgArray.get(index).procModeCd.getValue()) || PROC_MODE_CD_UPDATE.equals(svcPhysMtrUpdWrkTMsgArray.get(index).procModeCd.getValue())) == false) {
            this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0198E, null);
            updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0198E, new String[] {}));
            return true;
        }

        // 2023/07/07 QC#60925 Mod Start
//        SVC_TASKTMsg svcTaskTMsg = new SVC_TASKTMsg();
//        SVC_TASKTMsgArray svcTaskTMsgArray = new SVC_TASKTMsgArray();
//        svcTaskTMsg.setSQLID("004");
//        svcTaskTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
//        svcTaskTMsg.setConditionValue("svcTaskNum01", svcPhysMtrUpdWrkTMsgArray.get(index).svcTaskNum.getValue());
//        svcTaskTMsgArray = (SVC_TASKTMsgArray) EZDTBLAccessor.findByCondition(svcTaskTMsg);
//        if (svcTaskTMsgArray.getValidCount() <= 0) {
//            this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0199E, "Task Number");
//            updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0199E, new String[] {"Task Number" }));
//            return true;
//        }
        if (ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).svcTaskNum.getValue())) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", this.glblCmpyCd);
            paramMap.put("svcTaskNum", svcPhysMtrUpdWrkTMsgArray.get(index).svcTaskNum.getValue());
            paramMap.put("svcMachMstrPk", svcPhysMtrUpdWrkTMsgArray.get(index).svcMachMstrPk.getValue());
            // START 2024/03/13 t.aizawa [QC#61771, MOD]
            // PreparedStatement StmtTrue = ssmLLClient.createPreparedStatement("getSvcTask", paramMap, excParam);
            // ResultSet rsTrue = StmtTrue.executeQuery();

            // if (!rsTrue.next()) {
            Map<String, Object> rsltMap = NWXC412001.autoCast(ssmBatchClient.queryObject("getSvcTask", paramMap, excParam));
            if (rsltMap == null) {
            // END 2024/03/13 t.aizawa [QC#61771, MOD]
                this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0199E, "Task Number");
                updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0199E, new String[] {"Task Number" }));
                return true;
            }
        }
        // 2023/07/07 QC#60925 Mod End

        // 2023/07/07 QC#60925 Del Start
//        String mtrLbCd = getMtrLbCd(svcPhysMtrUpdWrkTMsgArray.get(index).mtrLbDescTxt.getValue());
//        if (!ZYPCommonFunc.hasValue(mtrLbCd)) {
//            this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0199E, "Machine Master ID");
//            updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0199E, new String[] {"Machine Master ID" }));
//            return true;
//        }
        // 2023/07/07 QC#60925 Del End

        if (!ZYPDateUtil.checkDate((svcPhysMtrUpdWrkTMsgArray.get(index).mtrReadDt.getValue()))) {
            this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0200E, "Meter Read Date");
            updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0200E, new String[] {}));
            return true;
        }

        if (ZYPDateUtil.compare(svcPhysMtrUpdWrkTMsgArray.get(index).mtrReadDt.getValue(), slsDt) == 1) {
            this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0201E, null);
            updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0201E, new String[] {}));
            return true;
        }

        // 2023/07/07 QC#60925 Mod Start
//        if (ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).dsTestCopyClsDescTxt.getValue())) {
//            if ((DS_TEST_COPY_CLS_DESC_TXT_IN.equals(svcPhysMtrUpdWrkTMsgArray.get(index).dsTestCopyClsDescTxt.getValue()) || DS_TEST_COPY_CLS_DESC_TXT_OUT.equals(svcPhysMtrUpdWrkTMsgArray.get(index).dsTestCopyClsDescTxt.getValue())) == false) {
//                this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0202E, "In/Out Flag");
//                updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0202E, new String[] {"In", "Out", "In/Out Flag" }));
//                return true;
//            }
//        }
        if (PROC_MODE_CD_CREATE.equals(svcPhysMtrUpdWrkTMsgArray.get(index).procModeCd.getValue())) {
            if (ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).dsTestCopyClsDescTxt.getValue())) {
                if (isInOutFlagError(svcPhysMtrUpdWrkTMsgArray.get(index).dsTestCopyClsDescTxt.getValue())) {
                    this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0202E, "In/Out Flag");
                    updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0202E, new String[] {"In", "Out", "In/Out Flag" }));
                    return true;
                }
            }
        }
        // 2023/07/07 QC#60925 Mod End

        // 2023/07/07 QC#60925 Mod Start
//        if ((DS_MTR_READ_TP_DESC_TXT_ACTUAL.equals((svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpDescTxt.getValue())) || DS_MTR_READ_TP_DESC_TXT_ADJUSTMENT.equals(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpDescTxt.getValue())) == false) {
//            this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0202E, "Meter Read Type");
//            updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0202E, new String[] {"ACTUAL", "ADJUSTMENT", "Meter Read Type" }));
//            return true;
//        }

        if (PROC_MODE_CD_CREATE.equals(svcPhysMtrUpdWrkTMsgArray.get(index).procModeCd.getValue())) {
            if (ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpDescTxt.getValue())) {
                if (isDsMtrReadTpDescTxtError(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpDescTxt.getValue())) {
                    this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0222E, null);
                    updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0222E, new String[] {}));
                    return true;
                }
            }
        }
        // 2023/07/07 QC#60925 Mod End

        // 2023/07/07 QC#60925 Mod Start
//        if ((DS_MTR_READ_TP_GRP.SERVICE_READS.equals(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpGrpCd.getValue()) || DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ.equals(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpGrpCd.getValue())) == false) {
//            this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0202E, "Meter Category");
//            updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0202E, new String[] {"S", "SF", "Meter Category" }));
//            return true;
//        }

        if (PROC_MODE_CD_CREATE.equals(svcPhysMtrUpdWrkTMsgArray.get(index).procModeCd.getValue())) {
            if (ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpGrpCd.getValue())) {
                if ((DS_MTR_READ_TP_GRP.SERVICE_READS.equals(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpGrpCd.getValue()) || DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ.equals(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpGrpCd
                        .getValue())) == false) {
                    this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0202E, "Meter Category");
                    updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0202E, new String[] {"S", "SF", "Meter Category" }));
                    return true;
                }
            }
        }
        // 2023/07/07 QC#60925 Mod End

        // 2023/09/17 QC#60925 Mod Start
        // if (ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).vldMtrFlg.getValue())) {
        //    if (!VLD_MTR_FLG_X.equals(svcPhysMtrUpdWrkTMsgArray.get(index).vldMtrFlg.getValue())) {
        if (ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).vldMtrTxt.getValue())) {
            if (!VLD_MTR_FLG_X.equals(svcPhysMtrUpdWrkTMsgArray.get(index).vldMtrTxt.getValue())) {
        // 2023/09/17 QC#60925 Mod End
                this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0203E, null);
                updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0203E, new String[] {}));
                return true;
            }
        }

        // 2023/07/07 QC#60925 Del Start
//        if (hasMtrCompositionError(svcPhysMtrUpdWrkTMsgArray, index)) {
//            this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0204E, null);
//            updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0204E, new String[] {}));
//            return true;
//        }
        // 2023/07/07 QC#60925 Del End

        if (PROC_MODE_CD_CREATE.equals(svcPhysMtrUpdWrkTMsgArray.get(index).procModeCd.getValue())) {
            // 2023/07/07 QC#60925 Mod Start
//            if (DS_TEST_COPY_CLS_DESC_TXT_OUT.equals(svcPhysMtrUpdWrkTMsgArray.get(index).dsTestCopyClsDescTxt.getValue())) {
            if (getDsTestCopyClsDescTxt(DS_TEST_COPY_CLS.TEST_COPY_OUT).equals(svcPhysMtrUpdWrkTMsgArray.get(index).dsTestCopyClsDescTxt.getValue())) {
            // 2023/07/07 QC#60925 Mod End
                // START 2024/03/13 t.aizawa [QC#61771, MOD]
                // PreparedStatement Stmt = ssmLLClient.createPreparedStatement("getInRead", setParamForInRead(svcPhysMtrUpdWrkTMsgArray, index), excParam);
                // ResultSet rs = Stmt.executeQuery();

                // if (!rs.next()) {
                Map<String, Object> rsltMap = NWXC412001.autoCast(ssmBatchClient.queryObject("getInRead", setParamForInRead(svcPhysMtrUpdWrkTMsgArray, index), excParam));
                if (rsltMap == null) {
                // END 2024/03/13 t.aizawa [QC#61771, MOD]
                    this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0205E, null);
                    updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0205E, new String[] {}));
                    return true;
                }
            }

        }

        // 2023/07/07 QC#60925 Mod Start
        if (PROC_MODE_CD_CREATE.equals(svcPhysMtrUpdWrkTMsgArray.get(index).procModeCd.getValue())) {
            if ((DS_MTR_READ_TP_GRP.SERVICE_READS).equals(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpGrpCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).svcTaskNum.getValue())) {
                    this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), ZZM9000E, "Task Number");
                    updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(ZZM9000E, new String[] {"Task Number" }));
                    return true;
                }
                if (!ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).dsTestCopyClsDescTxt.getValue())) {
                    this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), ZZM9000E, "In/Out reading ind");
                    updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(ZZM9000E, new String[] {"In/Out reading ind" }));
                    return true;
                }
            }
        }

//         if ((DS_MTR_READ_TP_GRP.SERVICE_READS).equals(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpGrpCd.getValue())) {
//            if (!ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).svcTaskNum.getValue())) {
//                this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), ZZM9000E, "Task Number");
//                updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(ZZM9000E, new String[] {"Task Number" }));
//                return true;
//            }
//            if (!ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).dsTestCopyClsDescTxt.getValue())) {
//                this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), ZZM9000E, "In/Out reading ind");
//                updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(ZZM9000E, new String[] {"In/Out reading ind" }));
//                return true;
//            }
//        }
        // 2023/07/07 QC#60925 Mod End

        // 2023/07/07 QC#60925 Mod Start
//        if (DS_MTR_READ_TP_DESC_TXT_ACTUAL.equals(svcPhysMtrUpdWrkTMsgArray.get(index).dsMtrReadTpDescTxt.getValue())) {
//            PreparedStatement StmtFrom = ssmLLClient.createPreparedStatement("getBeforeRead", setParamForBeforeRead(svcPhysMtrUpdWrkTMsgArray.get(index).svcMachMstrPk.getValue(), getMtrLbCd(svcPhysMtrUpdWrkTMsgArray.get(index).mtrLbDescTxt
//                    .getValue()), svcPhysMtrUpdWrkTMsgArray.get(index).mtrReadDt.getValue(), null), excParam);
//            ResultSet rsFrom = StmtFrom.executeQuery();
//            if (rsFrom.next()) {
//                if (rsFrom.getBigDecimal("READ_MTR_CNT").compareTo(svcPhysMtrUpdWrkTMsgArray.get(index).readMtrCnt.getValue()) > 0) {
//                    this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0206E, null);
//                    updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0206E, new String[] {}));
//                    return true;
//                }
//            }
//            PreparedStatement StmtTrue = ssmLLClient.createPreparedStatement("getBeforeRead", setParamForBeforeRead(svcPhysMtrUpdWrkTMsgArray.get(index).svcMachMstrPk.getValue(), getMtrLbCd(svcPhysMtrUpdWrkTMsgArray.get(index).mtrLbDescTxt
//                    .getValue()), null, svcPhysMtrUpdWrkTMsgArray.get(index).mtrReadDt.getValue()), excParam);
//            ResultSet rsTrue = StmtTrue.executeQuery();
//            if (rsTrue.next()) {
//                if (rsTrue.getBigDecimal("READ_MTR_CNT").compareTo(svcPhysMtrUpdWrkTMsgArray.get(index).readMtrCnt.getValue()) < 0) {
//                    this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0207E, null);
//                    updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0207E, new String[] {}));
//                    return true;
//                }
//            }
//        }

        if (isBeForeReadCntOverError(svcPhysMtrUpdWrkTMsgArray.get(index))) {
            this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0206E, null);
            updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0206E, new String[] {}));
            return true;
        }
        if (isAfterReadCntUnderError(svcPhysMtrUpdWrkTMsgArray.get(index))) {
            this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0207E, null);
            updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0207E, new String[] {}));
            return true;
        }
        // 2023/07/07 QC#60925 Mod End

        // 2023/07/07 QC#60925 Add Start
        if (PROC_MODE_CD_UPDATE.equals(svcPhysMtrUpdWrkTMsgArray.get(index).procModeCd.getValue())) {
            if (ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(index).svcPhysMtrReadGrpSq.getValue())) {
                if (isSvcPhysMtrReadGrpSqError(svcPhysMtrUpdWrkTMsgArray.get(index).svcPhysMtrReadGrpSq.getValue())) {
                    this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0219E, null);
                    updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0219E, new String[] {}));
                    return true;
                }

            }
        }

        if (isMtrLbCDuplicateError(svcPhysMtrUpdWrkTMsgArray)) {
            this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(index).upldCsvRqstRowNum.getValue(), NSBM0220E, null);
            updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(index), S21MessageFunc.clspGetMessage(NSBM0220E, new String[] {}));
            return true;
        }
        // 2023/07/07 QC#60925 Add End
        return false;
    }

    private String getAndCheckDateFormat(String mtrReadDt) {
        if (!ZYPCommonFunc.hasValue(mtrReadDt)) {
            return null;
        }
        if (mtrReadDt.length() != ZYPDateUtil.TYPE_YYYYMMDD.length()) {
            return null;
        }
        if (!ZYPDateUtil.checkDate(mtrReadDt)) {
            return null;
        }
        return mtrReadDt;
    }

    // 2023/07/07 QC#60925 Del Start
//    private boolean hasMtrCompositionError(List<SVC_PHYS_MTR_UPD_WRKTMsg> svcPhysMtrUpdWrkTMsgArray, int index) throws SQLException {
//        PreparedStatement Stmt = null;
//        ResultSet rs = null;
//
//        Stmt = ssmLLClient.createPreparedStatement("getSvcPhysMtr", setParamForSvcPhysMtr(svcPhysMtrUpdWrkTMsgArray, index), excParam);
//        rs = Stmt.executeQuery();
//
//        while (rs.next()) {
//            return false;
//        }
//
//        return true;
//    }
    // 2023/07/07 QC#60925 Del End

    private void updateErrorProcess(List<SVC_PHYS_MTR_UPD_WRKTMsg> svcPhysMtrUpdWrkTMsgArray) throws SQLException {
        for (int i = 0; i < svcPhysMtrUpdWrkTMsgArray.size(); i++) {
            if (!ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(i).upldCsvRqstCmntTxt)) {
                this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(i).upldCsvRqstRowNum.getValue(), NYXM0002E, createErrorWrkTableMessageArg(svcPhysMtrUpdWrkTMsgArray.size()));
                String arg = createErrorWrkTableMessageArg(svcPhysMtrUpdWrkTMsgArray.size());
                updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(i), S21MessageFunc.clspGetMessage(NYXM0002E, new String[] {arg }));
            }
        }
    }

    private void updateCompleteProcess(List<SVC_PHYS_MTR_UPD_WRKTMsg> svcPhysMtrUpdWrkTMsgArray) throws SQLException {
        for (int i = 0; i < svcPhysMtrUpdWrkTMsgArray.size(); i++) {
            if (!ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(i).upldCsvRqstCmntTxt)) {
                this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(i).upldCsvRqstRowNum.getValue(), NYXM0001I, createWrkTableMessageArg(svcPhysMtrUpdWrkTMsgArray.size()));
                String arg = createWrkTableMessageArg(svcPhysMtrUpdWrkTMsgArray.size());
                updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(i), S21MessageFunc.clspGetMessage(NYXM0001I, new String[] {arg }));
                // START 2023/10/20 N.Takatsu [QC#60925, ADD]
                updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(i));
                // END   2023/10/20 N.Takatsu [QC#60925, ADD]
            }
        }
    }

    private boolean createSvcPhysMtrRerd(List<SVC_PHYS_MTR_UPD_WRKTMsg> svcPhysMtrUpdWrkTMsgArray) throws SQLException {
        NSZC010001PMsg pMsg = new NSZC010001PMsg();

        // 2023/07/07 QC#60925 Mod Start
//         FSR_VISITTMsg fsrVisitTMsg = new FSR_VISITTMsg();
//         FSR_VISITTMsgArray fsrVisitTMsgArray = new
//         FSR_VISITTMsgArray();
//         fsrVisitTMsg.setSQLID("002");
//         fsrVisitTMsg.setConditionValue("svcTaskNum01",
//         svcPhysMtrUpdWrkTMsgArray.get(0).svcTaskNum.getValue());
//         fsrVisitTMsg.setConditionValue("glblCmpyCd01",
//         this.glblCmpyCd);
//         fsrVisitTMsgArray = (FSR_VISITTMsgArray)
//         EZDTBLAccessor.findByCondition(fsrVisitTMsg);
        FSR_VISITTMsg fsrVisitTMsg = new FSR_VISITTMsg();
        FSR_VISITTMsgArray fsrVisitTMsgArray = new FSR_VISITTMsgArray();
        if (ZYPCommonFunc.hasValue(svcPhysMtrUpdWrkTMsgArray.get(0).svcTaskNum.getValue())) {
            fsrVisitTMsg.setSQLID("002");
            fsrVisitTMsg.setConditionValue("svcTaskNum01", svcPhysMtrUpdWrkTMsgArray.get(0).svcTaskNum.getValue());
            fsrVisitTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            fsrVisitTMsgArray = (FSR_VISITTMsgArray) EZDTBLAccessor.findByCondition(fsrVisitTMsg);
        }
        // 2023/07/07 QC#60925 Mod End

        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.slsDt, this.slsDt);
        setValue(pMsg.svcMachMstrPk, svcPhysMtrUpdWrkTMsgArray.get(0).svcMachMstrPk.getValue());
        setValue(pMsg.mtrReadSrcTpCd, MTR_READ_SRC_TP.UPLD);
        // 2023/07/07 QC#60925 Mod Start
//        if (getDsMtrReadTpDescTxt(DS_MTR_READ_TP.ACTUAL).equals(svcPhysMtrUpdWrkTMsgArray.get(0).dsMtrReadTpDescTxt.getValue())) {
//            setValue(pMsg.dsMtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
//        } else {
//            setValue(pMsg.dsMtrReadTpCd, DS_MTR_READ_TP.ADJUSTMENT);
//        }
        if (getDsMtrReadTpDescTxt(DS_MTR_READ_TP.ACTUAL).equals(svcPhysMtrUpdWrkTMsgArray.get(0).dsMtrReadTpDescTxt.getValue())) {
            setValue(pMsg.dsMtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
        } else if (getDsMtrReadTpDescTxt(DS_MTR_READ_TP.ADJUSTMENT).equals(svcPhysMtrUpdWrkTMsgArray.get(0).dsMtrReadTpDescTxt.getValue())) {
            setValue(pMsg.dsMtrReadTpCd, DS_MTR_READ_TP.ADJUSTMENT);
        } else if (getDsMtrReadTpDescTxt(DS_MTR_READ_TP.ROLLOVER).equals(svcPhysMtrUpdWrkTMsgArray.get(0).dsMtrReadTpDescTxt.getValue())) {
            setValue(pMsg.dsMtrReadTpCd, DS_MTR_READ_TP.ROLLOVER);
        } else if (getDsMtrReadTpDescTxt(DS_MTR_READ_TP.EXCHANGE_METER_TO).equals(svcPhysMtrUpdWrkTMsgArray.get(0).dsMtrReadTpDescTxt.getValue())) {
            setValue(pMsg.dsMtrReadTpCd, DS_MTR_READ_TP.EXCHANGE_METER_TO);
        }
        // 2023/07/07 QC#60925 Mod End
        setValue(pMsg.dsMtrReadTpGrpCd, svcPhysMtrUpdWrkTMsgArray.get(0).dsMtrReadTpGrpCd.getValue());
        setValue(pMsg.svcTaskNum, svcPhysMtrUpdWrkTMsgArray.get(0).svcTaskNum.getValue());
        // 2023/07/07 QC#60925 Mod Start
//        if (DS_TEST_COPY_CLS_DESC_TXT_IN.equals(svcPhysMtrUpdWrkTMsgArray.get(0).dsTestCopyClsDescTxt.getValue())) {
        if (getDsTestCopyClsDescTxt(DS_TEST_COPY_CLS.TEST_COPY_IN).equals(svcPhysMtrUpdWrkTMsgArray.get(0).dsTestCopyClsDescTxt.getValue())) {
        // 2023/07/07 QC#60925 Mod End
            setValue(pMsg.dsTestCopyClsCd, DS_TEST_COPY_CLS.TEST_COPY_IN);
            // 2023/07/07 QC#60925 Mod Start
//             } else {
        } else if (getDsTestCopyClsDescTxt(DS_TEST_COPY_CLS.TEST_COPY_OUT).equals(svcPhysMtrUpdWrkTMsgArray.get(0).dsTestCopyClsDescTxt.getValue())) {
            // 2023/07/07 QC#60925 Mod End
            setValue(pMsg.dsTestCopyClsCd, DS_TEST_COPY_CLS.TEST_COPY_OUT);
        }
        // 2023/07/07 QC#60925 Mod Start
//         setValue(pMsg.fsrNum,
//         fsrVisitTMsgArray.no(0).fsrNum.getValue());
//         setValue(pMsg.fsrVisitNum,
//         fsrVisitTMsgArray.no(0).fsrVisitNum.getValue());
        if (fsrVisitTMsgArray.getValidCount() > 0) {
            setValue(pMsg.fsrNum, fsrVisitTMsgArray.no(0).fsrNum.getValue());
        }
        if (fsrVisitTMsgArray.getValidCount() > 0) {
            setValue(pMsg.fsrVisitNum, fsrVisitTMsgArray.no(0).fsrVisitNum.getValue());
        }
        // 2023/07/07 QC#60925 Mod End
        setValue(pMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
        for (int i = 0; i < svcPhysMtrUpdWrkTMsgArray.size(); i++) {
            setValue(pMsg.A.no(i).mdlMtrLbCd, getMtrLbCd(svcPhysMtrUpdWrkTMsgArray.get(i).mtrLbDescTxt.getValue()));
            setValue(pMsg.A.no(i).mtrLbCd, getMtrLbCd(svcPhysMtrUpdWrkTMsgArray.get(i).mtrLbDescTxt.getValue()));
            setValue(pMsg.A.no(i).mtrReadDt, svcPhysMtrUpdWrkTMsgArray.get(i).mtrReadDt.getValue());
            setValue(pMsg.A.no(i).rgtnMtrDt, this.slsDt);
            setValue(pMsg.A.no(i).readMtrCnt, svcPhysMtrUpdWrkTMsgArray.get(i).readMtrCnt.getValue());
            setValue(pMsg.A.no(i).rgtnUsrId, svcPhysMtrUpdWrkTMsgArray.get(i).ezInUserID.getValue());
            setValue(pMsg.A.no(i).estFlg, ZYPConstant.FLG_OFF_N);
            setValue(pMsg.A.no(i).invProcFlg, ZYPConstant.FLG_OFF_N);
            setValue(pMsg.A.no(i).mtrEntryCmntTxt, svcPhysMtrUpdWrkTMsgArray.get(i).mtrEntryCmntTxt.getValue());
            // 2023/09/17 QC#60925 Mod Start
            // if (svcPhysMtrUpdWrkTMsgArray.get(i).vldMtrFlg.getValue().equals(VLD_MTR_FLG_X)) {
            if (svcPhysMtrUpdWrkTMsgArray.get(i).vldMtrTxt.getValue().equals(VLD_MTR_FLG_X)) {
            // 2023/09/17 QC#60925 Mod End
                setValue(pMsg.A.no(i).vldMtrFlg, ZYPConstant.FLG_ON_Y);
            } else {
                setValue(pMsg.A.no(i).vldMtrFlg, ZYPConstant.FLG_OFF_N);
            }
            // 2023/07/07 QC#60925 Add Start
            setValue(pMsg.A.no(i).dsMtrReadTpCd, getDsMtrReadTpCd(svcPhysMtrUpdWrkTMsgArray.get(i).dsMtrReadTpDescTxt.getValue()));
            // 2023/07/07 QC#60925 Add End
            pMsg.A.setValidCount(i + 1);
        }
        NSZC010001 nszc010001 = new NSZC010001();
        nszc010001.execute(pMsg, ONBATCH_TYPE.BATCH);
        if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            for (int i = 0; i < svcPhysMtrUpdWrkTMsgArray.size(); i++) {
                this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(i).upldCsvRqstRowNum.getValue(), pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), null);
                updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(i), S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), new String[] {}));
            }
            return false;
        }
        this.infoSccessCnt += svcPhysMtrUpdWrkTMsgArray.size();
        return true;
    }

    private boolean updateSvcPhysMtrRerd(List<SVC_PHYS_MTR_UPD_WRKTMsg> svcPhysMtrUpdWrkTMsgArray) throws SQLException {
        NSZC010001PMsg pMsg = new NSZC010001PMsg();

        SVC_PHYS_MTR_READTMsg svcPhysMtrReadTMsg = new SVC_PHYS_MTR_READTMsg();
        SVC_PHYS_MTR_READTMsgArray svcPhysMtrReadTMsgArray = new SVC_PHYS_MTR_READTMsgArray();
        svcPhysMtrReadTMsg.setSQLID("007");
        svcPhysMtrReadTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        svcPhysMtrReadTMsg.setConditionValue("svcPhysMtrReadGrpSq01", svcPhysMtrUpdWrkTMsgArray.get(0).svcPhysMtrReadGrpSq.getValue());
        svcPhysMtrReadTMsgArray = (SVC_PHYS_MTR_READTMsgArray) EZDTBLAccessor.findByCondition(svcPhysMtrReadTMsg);

        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.slsDt, this.slsDt);
        setValue(pMsg.svcMachMstrPk, svcPhysMtrReadTMsgArray.no(0).svcMachMstrPk.getValue());
        setValue(pMsg.mtrReadSrcTpCd, svcPhysMtrReadTMsgArray.no(0).mtrReadSrcTpCd.getValue());
        setValue(pMsg.dsMtrReadTpCd, svcPhysMtrReadTMsgArray.no(0).dsMtrReadTpCd.getValue());
        setValue(pMsg.dsMtrReadTpGrpCd, svcPhysMtrReadTMsgArray.no(0).dsMtrReadTpGrpCd.getValue());
        setValue(pMsg.svcTaskNum, svcPhysMtrReadTMsgArray.no(0).svcTaskNum.getValue());
        setValue(pMsg.dsTestCopyClsCd, svcPhysMtrReadTMsgArray.no(0).dsTestCopyClsCd.getValue());
        setValue(pMsg.fsrNum, svcPhysMtrReadTMsgArray.no(0).fsrNum.getValue());
        setValue(pMsg.fsrVisitNum, svcPhysMtrReadTMsgArray.no(0).fsrVisitNum.getValue());
        setValue(pMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
        // 2023/07/07 QC#60925 Mod Start
//        for (int i = 0; i < svcPhysMtrUpdWrkTMsgArray.size(); i++) {
//            setValue(pMsg.A.no(i).svcPhysMtrReadPk, svcPhysMtrReadTMsgArray.no(i).svcPhysMtrReadPk.getValue());
//            setValue(pMsg.A.no(i).mdlMtrLbCd, svcPhysMtrReadTMsgArray.no(i).mtrLbCd.getValue());
//            setValue(pMsg.A.no(i).mtrLbCd, svcPhysMtrReadTMsgArray.no(i).mtrLbCd.getValue());
//            setValue(pMsg.A.no(i).mtrReadDt, svcPhysMtrReadTMsgArray.no(i).mtrReadDt.getValue());
//            setValue(pMsg.A.no(i).rgtnMtrDt, svcPhysMtrReadTMsgArray.no(i).rgtnMtrDt.getValue());
//            setValue(pMsg.A.no(i).readMtrCnt, svcPhysMtrUpdWrkTMsgArray.get(i).readMtrCnt.getValue());
//            BigDecimal testMtrCnt = calcTestMtrCnt(svcPhysMtrUpdWrkTMsgArray.get(i), svcPhysMtrReadTMsgArray.no(i));
//            setValue(pMsg.A.no(i).testMtrCnt, testMtrCnt);
//            setValue(pMsg.A.no(i).rgtnUsrId, svcPhysMtrReadTMsgArray.no(i).rgtnUsrId.getValue());
//            setValue(pMsg.A.no(i).estFlg, svcPhysMtrReadTMsgArray.no(i).estFlg.getValue());
//            setValue(pMsg.A.no(i).invProcFlg, svcPhysMtrReadTMsgArray.no(i).invProcFlg.getValue());
//            setValue(pMsg.A.no(i).mtrEntryCmntTxt, svcPhysMtrUpdWrkTMsgArray.get(i).mtrEntryCmntTxt.getValue());
//            if (svcPhysMtrUpdWrkTMsgArray.get(i).vldMtrFlg.getValue().equals(VLD_MTR_FLG_X)) {
//                setValue(pMsg.A.no(i).vldMtrFlg, ZYPConstant.FLG_ON_Y);
//            } else {
//                setValue(pMsg.A.no(i).vldMtrFlg, ZYPConstant.FLG_OFF_N);
//            }
//            // 2023/07/07 QC#60925 Add Start
//            setValue(pMsg.A.no(i).dsMtrReadTpCd, svcPhysMtrUpdWrkTMsgArray.get(i).dsMtrReadTpDescTxt.getValue());
//            // 2023/07/07 QC#60925 Add End
//            pMsg.A.setValidCount(i + 1);
//        }
        for (int i = 0; i < svcPhysMtrReadTMsgArray.length(); i++) {
            setValue(pMsg.A.no(i).svcPhysMtrReadPk, svcPhysMtrReadTMsgArray.no(i).svcPhysMtrReadPk.getValue());
            setValue(pMsg.A.no(i).mdlMtrLbCd, svcPhysMtrReadTMsgArray.no(i).mtrLbCd.getValue());
            setValue(pMsg.A.no(i).mtrLbCd, svcPhysMtrReadTMsgArray.no(i).mtrLbCd.getValue());
            setValue(pMsg.A.no(i).mtrReadDt, svcPhysMtrReadTMsgArray.no(i).mtrReadDt.getValue());
            setValue(pMsg.A.no(i).rgtnMtrDt, svcPhysMtrReadTMsgArray.no(i).rgtnMtrDt.getValue());
            setValue(pMsg.A.no(i).readMtrCnt, setSvcPhysMtrReadCount(svcPhysMtrUpdWrkTMsgArray, svcPhysMtrReadTMsgArray, i));
            BigDecimal testMtrCnt = calcTestMtrCnt(svcPhysMtrUpdWrkTMsgArray, svcPhysMtrReadTMsgArray.no(i));
            setValue(pMsg.A.no(i).testMtrCnt, testMtrCnt);
            setValue(pMsg.A.no(i).rgtnUsrId, svcPhysMtrReadTMsgArray.no(i).rgtnUsrId.getValue());
            setValue(pMsg.A.no(i).estFlg, svcPhysMtrReadTMsgArray.no(i).estFlg.getValue());
            setValue(pMsg.A.no(i).invProcFlg, svcPhysMtrReadTMsgArray.no(i).invProcFlg.getValue());
            setValue(pMsg.A.no(i).mtrEntryCmntTxt, setMtrEntryCmntTxt(svcPhysMtrUpdWrkTMsgArray, svcPhysMtrReadTMsgArray, i));
            // 2023/09/17 QC#60925 Mod Start
            // if (svcPhysMtrUpdWrkTMsgArray.get(0).vldMtrFlg.getValue().equals(VLD_MTR_FLG_X)) {
            if (svcPhysMtrUpdWrkTMsgArray.get(0).vldMtrTxt.getValue().equals(VLD_MTR_FLG_X)) {
            // 2023/09/17 QC#60925 Mod End
                setValue(pMsg.A.no(i).vldMtrFlg, ZYPConstant.FLG_ON_Y);
            } else {
                setValue(pMsg.A.no(i).vldMtrFlg, ZYPConstant.FLG_OFF_N);
            }
            setValue(pMsg.A.no(i).dsMtrReadTpCd, setDsMtrReadTpCd(svcPhysMtrReadTMsgArray, i));
            pMsg.A.setValidCount(i + 1);
        }
        // 2023/07/07 QC#60925 Mod End
        NSZC010001 nszc010001 = new NSZC010001();
        nszc010001.execute(pMsg, ONBATCH_TYPE.BATCH);
        if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            for (int i = 0; i < svcPhysMtrUpdWrkTMsgArray.size(); i++) {
                this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(i).upldCsvRqstRowNum.getValue(), pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), null);
                updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(i), S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), new String[] {}));
            }
            return false;
        }
        this.infoSccessCnt += svcPhysMtrUpdWrkTMsgArray.size();
        return true;
    }

    // 2023/07/07 QC#60925 Mod Start
//    private BigDecimal calcTestMtrCnt(SVC_PHYS_MTR_UPD_WRKTMsg svcPhysMtrUpdWrkTMsg, SVC_PHYS_MTR_READTMsg svcPhysMtrReadTMsg) throws SQLException {
//        BigDecimal testMtrCnt = svcPhysMtrReadTMsg.testMtrCnt.getValue();
//        if (!VLD_MTR_FLG_X.equals(svcPhysMtrUpdWrkTMsg.vldMtrFlg.getValue())) {
//            return testMtrCnt;
//        }
//        if (!DS_MTR_READ_TP_GRP.SERVICE_READS.equals(svcPhysMtrUpdWrkTMsg.dsMtrReadTpGrpCd.getValue())) {
//            return testMtrCnt;
//        }
//        if (!DS_TEST_COPY_CLS_DESC_TXT_OUT.equals(svcPhysMtrUpdWrkTMsg.dsTestCopyClsDescTxt.getValue())) {
//            return testMtrCnt;
//        }
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("glblCmpyCd", this.glblCmpyCd);
//        paramMap.put("svcPhysMtrPk", svcPhysMtrReadTMsg.svcPhysMtrPk.getValue());
//        paramMap.put("svcTaskNum", svcPhysMtrReadTMsg.svcTaskNum.getValue());
//        paramMap.put("mtrReadDt", svcPhysMtrReadTMsg.mtrReadDt.getValue());
//        paramMap.put("dsTestCopyClsCd", DS_TEST_COPY_CLS.TEST_COPY_IN);
//        paramMap.put("dsMtrReadTpGrpCd_S", DS_MTR_READ_TP_GRP.SERVICE_READS);
//        paramMap.put("dsMtrReadTpGrpCd_SF", DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ);
//        paramMap.put("vldMtrFlg", ZYPConstant.FLG_ON_Y);
//        PreparedStatement StmtTrue = ssmLLClient.createPreparedStatement("getInMeterCount", paramMap, excParam);
//        ResultSet rsTrue = StmtTrue.executeQuery();
//        if (rsTrue.next()) {
//            BigDecimal inMtrCnt = rsTrue.getBigDecimal("READ_MTR_CNT");
//            if (!hasValue(inMtrCnt)) {
//                return testMtrCnt;
//            }
//            BigDecimal outMtrCnt = svcPhysMtrUpdWrkTMsg.readMtrCnt.getValue();
//            testMtrCnt = outMtrCnt.subtract(inMtrCnt);
//        }
//        return testMtrCnt;
//    }
    private BigDecimal calcTestMtrCnt(List<SVC_PHYS_MTR_UPD_WRKTMsg> svcPhysMtrUpdWrkTMsgArray, SVC_PHYS_MTR_READTMsg svcPhysMtrReadTMsg) throws SQLException {
        BigDecimal originTestMtrCnt = svcPhysMtrReadTMsg.testMtrCnt.getValue();
        BigDecimal newMtrReadCnt = BigDecimal.ZERO;

        for (int i = 0; i < svcPhysMtrUpdWrkTMsgArray.size(); i++) {
            if (svcPhysMtrReadTMsg.mtrLbCd.getValue().equals(getMtrLbCd(svcPhysMtrUpdWrkTMsgArray.get(i).mtrLbDescTxt.getValue()))) {
                newMtrReadCnt = svcPhysMtrUpdWrkTMsgArray.get(i).readMtrCnt.getValue();
                break;
            }
        }
        if (DS_MTR_READ_TP_GRP.SERVICE_READS.equals(svcPhysMtrReadTMsg.dsMtrReadTpGrpCd.getValue()) && DS_TEST_COPY_CLS.TEST_COPY_OUT.equals((svcPhysMtrReadTMsg.dsTestCopyClsCd.getValue()))) {
            if (DS_MTR_READ_TP.ADJUSTMENT.equals(svcPhysMtrReadTMsg.dsMtrReadTpCd.getValue())) {
                return BigDecimal.ZERO;
            }
            if (CNTR_RESET_TP.METER_EXCHANGE.equals(svcPhysMtrReadTMsg.cntrResetTpCd.getValue())) {
                return BigDecimal.ZERO;
            }

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", this.glblCmpyCd);
            paramMap.put("svcPhysMtrPk", svcPhysMtrReadTMsg.svcPhysMtrPk.getValue());
            paramMap.put("svcTaskNum", svcPhysMtrReadTMsg.svcTaskNum.getValue());
            paramMap.put("mtrReadDt", svcPhysMtrReadTMsg.mtrReadDt.getValue());
            paramMap.put("dsTestCopyClsCd", DS_TEST_COPY_CLS.TEST_COPY_IN);
            paramMap.put("dsMtrReadTpGrpCd_S", DS_MTR_READ_TP_GRP.SERVICE_READS);
            paramMap.put("dsMtrReadTpGrpCd_SF", DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ);
            paramMap.put("vldMtrFlg", ZYPConstant.FLG_ON_Y);
            // START 2024/03/13 t.aizawa [QC#61771, MOD]
            // PreparedStatement StmtTrue = ssmLLClient.createPreparedStatement("getInMeterCount", paramMap, excParam);
            // ResultSet rsTrue = StmtTrue.executeQuery();
            // if (rsTrue.next()) {
            //     BigDecimal inMtrCnt = rsTrue.getBigDecimal("READ_MTR_CNT");
            Map<String, Object> rsltMap = NWXC412001.autoCast(ssmBatchClient.queryObject("getInMeterCount", paramMap, excParam));
            if (rsltMap != null) {
                BigDecimal inMtrCnt = (BigDecimal) rsltMap.get("READ_MTR_CNT");
            // END 2024/03/13 t.aizawa [QC#61771, MOD]
                if (!hasValue(inMtrCnt)) {
                    return originTestMtrCnt;
                }
                if (CNTR_RESET_TP.METER_ROLLOVER.equals(svcPhysMtrReadTMsg.cntrResetTpCd.getValue())) {
                    return getRollOverTestMtrCnt(newMtrReadCnt, inMtrCnt, svcPhysMtrReadTMsg);
                } else {
                    return newMtrReadCnt.subtract(inMtrCnt);
                }
            } else {
                return originTestMtrCnt;
            }
        } else {
            return originTestMtrCnt;
        }
    }

    // 2023/07/07 QC#60925 Mod End

    private String getUpldCsvId(ART10FMsg fMsg) {
        String uploadCsvId = fMsg.EZREQUserKey.getValue();
        if (!hasValue(uploadCsvId)) {
            throw new S21AbendException(ZZM9000E, new String[] {"UPLD_CSV_RQST_PK" });
        }
        return S21RequestBatchHelper.removeDoubleQuotation(uploadCsvId);
    }

    private BigDecimal getUpldCsvReqPk(ART10FMsg fMsg) {
        String upldCsvReqPk = fMsg.EZREQCondition.getValue();
        String removeDQRequestPK = S21RequestBatchHelper.removeDoubleQuotation(upldCsvReqPk);

        if (!hasValue(removeDQRequestPK)) {
            throw new S21AbendException(ZZM9000E, new String[] {"UPLD_CSV_RQST_PK" });
        }
        if (!EZDCommonFunc.isNumeric(removeDQRequestPK)) {
            throw new S21AbendException(ZZM9000E, new String[] {"UPLD_CSV_RQST_PK" });
        }
        return new BigDecimal(removeDQRequestPK);
    }

    private void closeCsvFile(ART10FMsg fMsg) {
        String arg = createResultMessageArg();
        if (this.infoSccessCnt > 0) {
            this.batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.NOMAL_END);
            this.messenger.addMessageForFile(NYXM0001I, arg);
            this.messenger.uploadMessage();
        } else {
            this.termCd = TERM_CD.WARNING_END;
            this.batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.WARING_END);
            this.messenger.addMessageForFile(NYXM0002E, arg);
            this.messenger.uploadMessage();
        }
    }

    private String createResultMessageArg() {
        StringBuilder builder = new StringBuilder();
        if (this.searchCnt == this.infoSccessCnt) {
            builder.append(String.format(RESULT_MSG_INS, this.infoSccessCnt));
        } else if (this.infoSccessCnt > 0) {
            builder.append(String.format(RESULT_MSG_INS_MARNING, this.infoSccessCnt));
        } else {
            builder.append(String.format(RESULT_MSG_ERR, this.searchCnt - this.infoSccessCnt));
        }
        return builder.toString();
    }

    private String createWrkTableMessageArg(int count) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(RESULT_MSG_INS, count));
        return builder.toString();
    }

    private String createErrorWrkTableMessageArg(int count) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(RESULT_MSG_ERR, count));
        return builder.toString();
    }

    private String getMtrLbCd(String mtrLbDescTxt) {
        MTR_LBTMsg mtrLbTMsg = new MTR_LBTMsg();
        MTR_LBTMsgArray mtrLbTMsgArray = new MTR_LBTMsgArray();
        mtrLbTMsg.setSQLID("004");
        mtrLbTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        mtrLbTMsg.setConditionValue("mtrLbDescTxt01", mtrLbDescTxt);
        mtrLbTMsgArray = (MTR_LBTMsgArray) EZDTBLAccessor.findByCondition(mtrLbTMsg);
        if (mtrLbTMsgArray.getValidCount() > 0) {
            return mtrLbTMsgArray.no(0).mtrLbCd.getValue();
        }
        return null;
    }

    private Map<String, Object> setParamForTargetList(UPLD_CSV_RQSTTMsg upldCSVRqstTMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("upldCsvRqstPk", upldCSVRqstTMsg.upldCsvRqstPk.getValue());
        return paramMap;
    }

    private Map<String, Object> setParamDsMtrReadTpCd(String dsMtrReadTpDescTxt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsMtrReadTpDescTxt", dsMtrReadTpDescTxt);
        return paramMap;
    }

    private Map<String, Object> setParamDsTestCopyCls(String dsTestCopyClsDescTxt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsTestCopyClsDescTxt", dsTestCopyClsDescTxt);
        return paramMap;
    }

    private Map<String, Object> setParamForInRead(List<SVC_PHYS_MTR_UPD_WRKTMsg> svcPhysMtrUpdWrkTMsgArray, int index) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcMachMstrPk", svcPhysMtrUpdWrkTMsgArray.get(index).svcMachMstrPk.getValue());
        paramMap.put("svcTaskNum", svcPhysMtrUpdWrkTMsgArray.get(index).svcTaskNum.getValue());
        paramMap.put("mtrReadDt", svcPhysMtrUpdWrkTMsgArray.get(index).mtrReadDt.getValue());
        paramMap.put("dsTestCopyClsCd", DS_TEST_COPY_CLS.TEST_COPY_IN);
        paramMap.put("vldMtrFlg", ZYPConstant.FLG_ON_Y);
        return paramMap;
    }

    // START 2023/10/20 N.Takatsu [QC#60925, MOD]
    // 2023/07/07 QC#60925 Mod Start
//    private Map<String, Object> setParamForBeforeRead(BigDecimal svcMachMstrPk, String mtrLbCd, String fromMtrReadDt, String trueMtrReadDt, BigDecimal svcPhysMtrReadGrpSq) {
//    private Map<String, Object> setParamForBeforeRead(BigDecimal svcMachMstrPk, String mtrLbCd, String fromMtrReadDt, String thruMtrReadDt, BigDecimal svcPhysMtrReadGrpSq, String procModeCd) {
    private Map<String, Object> setParamForBeforeRead(BigDecimal svcMachMstrPk, String mtrLbCd, String fromMtrReadDt, String thruMtrReadDt, BigDecimal svcPhysMtrReadGrpSq, String procModeCd, BigDecimal upldCsvRqstPk) {
        // 2023/07/07 QC#60925 Mod End
        // END 2023/10/20 N.Takatsu [QC#60925, MOD]
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcMachMstrPk", svcMachMstrPk);
        // 2023/07/07 QC#60925 Del Start
        // paramMap.put("dsMtrReadTpCd", DS_MTR_READ_TP.ACTUAL);
        // 2023/07/07 QC#60925 Del End
        // 2023/07/07 QC#60925 Add Start
        if (PROC_MODE_CD_UPDATE.equals(procModeCd)) {
            paramMap.put("svcPhysMtrReadGrpSq", svcPhysMtrReadGrpSq);
        } else if (PROC_MODE_CD_CREATE.equals(procModeCd)) {
            paramMap.put("svcPhysMtrReadGrpSq", null);
        }

        // 2023/07/07 QC#60925 Add End
        paramMap.put("mtrLbCd", mtrLbCd);
        // START 2023/10/20 N.Takatsu [QC#60925, DEL]
//        paramMap.put("vldMtrFlg", ZYPConstant.FLG_ON_Y);
        // END   2023/10/20 N.Takatsu [QC#60925, DEL]
        // START 2023/10/20 N.Takatsu [QC#60925, ADD]
        paramMap.put("dsMtrReadTpGrpCd_S", DS_MTR_READ_TP_GRP.SERVICE_READS);
        paramMap.put("dsMtrReadTpGrpCd_SF", DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ);
        paramMap.put("upldCsvRqstPk", upldCsvRqstPk);
        paramMap.put("procModeCd", PROC_MODE_CD_UPDATE);
        paramMap.put("vldMtrFlgY", ZYPConstant.FLG_ON_Y);
        paramMap.put("vldMtrFlgX", VLD_MTR_FLG_X);
        // END   2023/10/20 N.Takatsu [QC#60925, ADD]
        paramMap.put("fromMtrReadDt", fromMtrReadDt);
        // 2023/07/07 QC#60925 Mod Start
        paramMap.put("thruMtrReadDt", thruMtrReadDt);
        // 2023/07/07 QC#60925 Mod End
        return paramMap;
    }

    private Map<String, Object> setParamgetDsMtrReadTpGrp(String dsMtrReadTpGrpCd) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsMtrReadTpGrpCd", dsMtrReadTpGrpCd);
        return paramMap;
    }

    // 2023/07/07 QC#60925 Del Start
//    private Map<String, Object> setParamForSvcPhysMtr(List<SVC_PHYS_MTR_UPD_WRKTMsg> svcPhysMtrUpdWrkTMsgArray, int index) {
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("glblCmpyCd", this.glblCmpyCd);
//        paramMap.put("svcMachMstrPk", svcPhysMtrUpdWrkTMsgArray.get(index).svcMachMstrPk.getValue());
//        paramMap.put("mtrLbDescTxt", svcPhysMtrUpdWrkTMsgArray.get(index).mtrLbDescTxt.getValue());
//        return paramMap;
//    }
    // 2023/07/07 QC#60925 Del End

    // START 2023/10/20 N.Takatsu [QC#60925, DEL]
//    private void updateSvcPhysMtrUpdWrk(SVC_PHYS_MTR_UPD_WRKTMsg svcPhysMtrUpdWrkTMsg, String message) throws SQLException {
//        SVC_PHYS_MTR_UPD_WRKTMsg inMsg = new SVC_PHYS_MTR_UPD_WRKTMsg();
//        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
//        setValue(inMsg.ezCancelFlag, "0");
//        setValue(inMsg.upldCsvRqstPk, svcPhysMtrUpdWrkTMsg.upldCsvRqstPk.getValue());
//        setValue(inMsg.upldCsvRqstRowNum, svcPhysMtrUpdWrkTMsg.upldCsvRqstRowNum.getValue());
//        setValue(inMsg.upldCsvRqstCmntTxt, message);
//        EZDTBLAccessor.updateByPartialValue(inMsg, new String[] {"upldCsvRqstPk", "upldCsvRqstRowNum" }, inMsg, new String[] {"upldCsvRqstCmntTxt" });
//    }
    // END   2023/10/20 N.Takatsu [QC#60925, DEL]

    // START 2023/10/20 N.Takatsu [QC#60925, ADD]
    private void updateSvcPhysMtrUpdWrk(SVC_PHYS_MTR_UPD_WRKTMsg svcPhysMtrUpdWrkTMsg, String message) {
        setValue(svcPhysMtrUpdWrkTMsg.upldCsvRqstCmntTxt, message);
    }

    private void updateSvcPhysMtrUpdWrk(SVC_PHYS_MTR_UPD_WRKTMsg svcPhysMtrUpdWrkTMsg) {
        SVC_PHYS_MTR_UPD_WRKTMsg inMsg = new SVC_PHYS_MTR_UPD_WRKTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.ezCancelFlag, "0");
        setValue(inMsg.upldCsvRqstPk, svcPhysMtrUpdWrkTMsg.upldCsvRqstPk);
        setValue(inMsg.upldCsvRqstRowNum, svcPhysMtrUpdWrkTMsg.upldCsvRqstRowNum);
        setValue(inMsg.upldCsvRqstCmntTxt, svcPhysMtrUpdWrkTMsg.upldCsvRqstCmntTxt);
        EZDTBLAccessor.updateByPartialValue(inMsg, new String[] {"upldCsvRqstPk", "upldCsvRqstRowNum" }, inMsg, new String[] {"upldCsvRqstCmntTxt" });
    }
    // END   2023/10/20 N.Takatsu [QC#60925, ADD]

    // 2023/07/07 QC#60925 Add Start
    private BigDecimal setSvcPhysMtrReadCount(List<SVC_PHYS_MTR_UPD_WRKTMsg> svcPhysMtrUpdWrkTMsgArray, SVC_PHYS_MTR_READTMsgArray svcPhysMtrReadTMsgArray, int index) {
        for (int i = 0; i < svcPhysMtrUpdWrkTMsgArray.size(); i++) {
            String wrakMtrLbCd = getMtrLbCd(svcPhysMtrUpdWrkTMsgArray.get(i).mtrLbDescTxt.getValue());
            if (svcPhysMtrReadTMsgArray.no(index).mtrLbCd.getValue().equals(wrakMtrLbCd)) {
                return svcPhysMtrUpdWrkTMsgArray.get(i).readMtrCnt.getValue();
            }
        }
        return svcPhysMtrReadTMsgArray.no(index).readMtrCnt.getValue();
    }

    private String setMtrEntryCmntTxt(List<SVC_PHYS_MTR_UPD_WRKTMsg> svcPhysMtrUpdWrkTMsgArray, SVC_PHYS_MTR_READTMsgArray svcPhysMtrReadTMsgArray, int index) {
        for (int i = 0; i < svcPhysMtrUpdWrkTMsgArray.size(); i++) {
            String wrakMtrLbCd = getMtrLbCd(svcPhysMtrUpdWrkTMsgArray.get(i).mtrLbDescTxt.getValue());
            if (svcPhysMtrReadTMsgArray.no(index).mtrLbCd.getValue().equals(wrakMtrLbCd)) {
                return svcPhysMtrUpdWrkTMsgArray.get(i).mtrEntryCmntTxt.getValue();
            }
        }
        return svcPhysMtrReadTMsgArray.no(index).mtrEntryCmntTxt.getValue();
    }

    private String setDsMtrReadTpCd(SVC_PHYS_MTR_READTMsgArray svcPhysMtrReadTMsgArray, int index) {
        if (CNTR_RESET_TP.METER_ROLLOVER.equals(svcPhysMtrReadTMsgArray.no(index).cntrResetTpCd.getValue())) {
            return DS_MTR_READ_TP.ROLLOVER;
        }
        if (CNTR_RESET_TP.METER_EXCHANGE.equals(svcPhysMtrReadTMsgArray.no(index).cntrResetTpCd.getValue())) {
            return DS_MTR_READ_TP.EXCHANGE_METER_TO;
        }
        return svcPhysMtrReadTMsgArray.no(index).dsMtrReadTpCd.getValue();
    }

    private String getDsMtrReadTpCd(String dsMtrReadTpDescTxt) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsMtrReadTpDescTxt", dsMtrReadTpDescTxt);
        // START 2024/03/13 t.aizawa [QC#61771, MOD]
        // PreparedStatement StmtTrue = ssmLLClient.createPreparedStatement("getDsMtrReadTpCd", paramMap, excParam);
        // ResultSet rs = StmtTrue.executeQuery();
        // if (rs.next()) {
        //     return rs.getString("DS_MTR_READ_TP_CD");
        // }
        // return null;
        return (String) ssmBatchClient.queryObject("getDsMtrReadTpCd", paramMap, excParam);
        // END 2024/03/13 t.aizawa [QC#61771, MOD]
    }

    private boolean isBeForeReadCntOverError(SVC_PHYS_MTR_UPD_WRKTMsg svcPhysMtrUpdWrkTMsg) throws SQLException {
        if (PROC_MODE_CD_CREATE.equals(svcPhysMtrUpdWrkTMsg.procModeCd.getValue())) {
            if (getDsMtrReadTpDescTxt(DS_MTR_READ_TP.ACTUAL).equals(svcPhysMtrUpdWrkTMsg.dsMtrReadTpDescTxt.getValue())) {
                // START 2023/10/20 N.Takatsu [QC#60925, MOD]
//                PreparedStatement StmtFrom = ssmLLClient.createPreparedStatement("getBeforeRead", setParamForBeforeRead(svcPhysMtrUpdWrkTMsg.svcMachMstrPk.getValue(), getMtrLbCd(svcPhysMtrUpdWrkTMsg.mtrLbDescTxt.getValue()),
//                        svcPhysMtrUpdWrkTMsg.mtrReadDt.getValue(), null, svcPhysMtrUpdWrkTMsg.svcPhysMtrReadGrpSq.getValue(), svcPhysMtrUpdWrkTMsg.procModeCd.getValue()), excParam);
                // START 2024/03/13 t.aizawa [QC#61771, MOD]
                // PreparedStatement StmtFrom = ssmLLClient.createPreparedStatement("getBeforeRead", setParamForBeforeRead(svcPhysMtrUpdWrkTMsg.svcMachMstrPk.getValue(), getMtrLbCd(svcPhysMtrUpdWrkTMsg.mtrLbDescTxt.getValue()),
                //         svcPhysMtrUpdWrkTMsg.mtrReadDt.getValue(), null, svcPhysMtrUpdWrkTMsg.svcPhysMtrReadGrpSq.getValue(), svcPhysMtrUpdWrkTMsg.procModeCd.getValue(), svcPhysMtrUpdWrkTMsg.upldCsvRqstPk.getValue()), excParam);
                // // END   2023/10/20 N.Takatsu [QC#60925, MOD]
                // ResultSet rsFrom = StmtFrom.executeQuery();
                // if (rsFrom.next()) {
                //     if (rsFrom.getBigDecimal("READ_MTR_CNT").compareTo(svcPhysMtrUpdWrkTMsg.readMtrCnt.getValue()) > 0) {
                Map<String, Object> rsltMap = NWXC412001.autoCast(ssmBatchClient.queryObject("getBeforeRead", setParamForBeforeRead(svcPhysMtrUpdWrkTMsg.svcMachMstrPk.getValue(), getMtrLbCd(svcPhysMtrUpdWrkTMsg.mtrLbDescTxt.getValue()),
                        svcPhysMtrUpdWrkTMsg.mtrReadDt.getValue(), null, svcPhysMtrUpdWrkTMsg.svcPhysMtrReadGrpSq.getValue(), svcPhysMtrUpdWrkTMsg.procModeCd.getValue(), svcPhysMtrUpdWrkTMsg.upldCsvRqstPk.getValue()), excParam));
                if (rsltMap != null) {
                    BigDecimal readMtrCnt = (BigDecimal) rsltMap.get("READ_MTR_CNT");
                    if (readMtrCnt.compareTo(svcPhysMtrUpdWrkTMsg.readMtrCnt.getValue()) > 0) {
                //         this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsg.upldCsvRqstRowNum.getValue(), NSBM0206E, null);
                //         updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsg, S21MessageFunc.clspGetMessage(NSBM0206E, new String[] {}));
                // END 2024/03/13 t.aizawa [QC#61771, MOD]
                        return true;
                    }
                }
            }
        } else if (PROC_MODE_CD_UPDATE.equals(svcPhysMtrUpdWrkTMsg.procModeCd.getValue())) {
            SVC_PHYS_MTR_READTMsg svcPhysMtrReadTMsg = new SVC_PHYS_MTR_READTMsg();
            SVC_PHYS_MTR_READTMsgArray svcPhysMtrReadTMsgArray = new SVC_PHYS_MTR_READTMsgArray();
            svcPhysMtrReadTMsg.setSQLID("007");
            svcPhysMtrReadTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            svcPhysMtrReadTMsg.setConditionValue("svcPhysMtrReadGrpSq01", svcPhysMtrUpdWrkTMsg.svcPhysMtrReadGrpSq.getValue());
            svcPhysMtrReadTMsgArray = (SVC_PHYS_MTR_READTMsgArray) EZDTBLAccessor.findByCondition(svcPhysMtrReadTMsg);

            for (int i = 0; i < svcPhysMtrReadTMsgArray.length(); i++) {
                if (svcPhysMtrReadTMsgArray.no(i).mtrLbCd.getValue().equals(getMtrLbCd(svcPhysMtrUpdWrkTMsg.mtrLbDescTxt.getValue()))) {
                    if (DS_MTR_READ_TP.ACTUAL.equals(svcPhysMtrReadTMsgArray.no(i).dsMtrReadTpCd.getValue()) && !ZYPCommonFunc.hasValue(svcPhysMtrReadTMsgArray.no(i).cntrResetTpCd.getValue())) {
                        // START 2023/10/20 N.Takatsu [QC#60925, MOD]
//                        PreparedStatement StmtFrom = ssmLLClient.createPreparedStatement("getBeforeRead", setParamForBeforeRead(svcPhysMtrUpdWrkTMsg.svcMachMstrPk.getValue(), getMtrLbCd(svcPhysMtrUpdWrkTMsg.mtrLbDescTxt.getValue()),
//                                svcPhysMtrUpdWrkTMsg.mtrReadDt.getValue(), null, svcPhysMtrUpdWrkTMsg.svcPhysMtrReadGrpSq.getValue(), svcPhysMtrUpdWrkTMsg.procModeCd.getValue()), excParam);
                        // START 2024/03/13 t.aizawa [QC#61771, MOD]
                        // PreparedStatement StmtFrom = ssmLLClient.createPreparedStatement("getBeforeRead", setParamForBeforeRead(svcPhysMtrUpdWrkTMsg.svcMachMstrPk.getValue(), getMtrLbCd(svcPhysMtrUpdWrkTMsg.mtrLbDescTxt.getValue()),
                        //         svcPhysMtrUpdWrkTMsg.mtrReadDt.getValue(), null, svcPhysMtrUpdWrkTMsg.svcPhysMtrReadGrpSq.getValue(), svcPhysMtrUpdWrkTMsg.procModeCd.getValue(), svcPhysMtrUpdWrkTMsg.upldCsvRqstPk.getValue()), excParam);
                        // // END   2023/10/20 N.Takatsu [QC#60925, MOD]
                        // ResultSet rsFrom = StmtFrom.executeQuery();
                        // if (rsFrom.next()) {
                        //     if (rsFrom.getBigDecimal("READ_MTR_CNT").compareTo(svcPhysMtrUpdWrkTMsg.readMtrCnt.getValue()) > 0) {
                        Map<String, Object> rsltMap = NWXC412001.autoCast(ssmBatchClient.queryObject("getBeforeRead", setParamForBeforeRead(svcPhysMtrUpdWrkTMsg.svcMachMstrPk.getValue(), getMtrLbCd(svcPhysMtrUpdWrkTMsg.mtrLbDescTxt
                                .getValue()), svcPhysMtrUpdWrkTMsg.mtrReadDt.getValue(), null, svcPhysMtrUpdWrkTMsg.svcPhysMtrReadGrpSq.getValue(), svcPhysMtrUpdWrkTMsg.procModeCd.getValue(), svcPhysMtrUpdWrkTMsg.upldCsvRqstPk.getValue()),
                                excParam));
                        if (rsltMap != null) {
                            BigDecimal readMtrCnt = (BigDecimal) rsltMap.get("READ_MTR_CNT");
                            if (readMtrCnt.compareTo(svcPhysMtrUpdWrkTMsg.readMtrCnt.getValue()) > 0) {
                        // END 2024/03/13 t.aizawa [QC#61771, MOD]
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isAfterReadCntUnderError(SVC_PHYS_MTR_UPD_WRKTMsg svcPhysMtrUpdWrkTMsg) throws SQLException {
        // START 2023/10/20 N.Takatsu [QC#60925, MOD]
//        PreparedStatement StmtFrom = ssmLLClient.createPreparedStatement("getBeforeRead", setParamForBeforeRead(svcPhysMtrUpdWrkTMsg.svcMachMstrPk.getValue(), getMtrLbCd(svcPhysMtrUpdWrkTMsg.mtrLbDescTxt.getValue()), null,
//                svcPhysMtrUpdWrkTMsg.mtrReadDt.getValue(), svcPhysMtrUpdWrkTMsg.svcPhysMtrReadGrpSq.getValue(), svcPhysMtrUpdWrkTMsg.procModeCd.getValue()), excParam);
        // START 2024/03/13 t.aizawa [QC#61771, MOD]
        // PreparedStatement StmtFrom = ssmLLClient.createPreparedStatement("getBeforeRead", setParamForBeforeRead(svcPhysMtrUpdWrkTMsg.svcMachMstrPk.getValue(), getMtrLbCd(svcPhysMtrUpdWrkTMsg.mtrLbDescTxt.getValue()), null,
        //         svcPhysMtrUpdWrkTMsg.mtrReadDt.getValue(), svcPhysMtrUpdWrkTMsg.svcPhysMtrReadGrpSq.getValue(), svcPhysMtrUpdWrkTMsg.procModeCd.getValue(), svcPhysMtrUpdWrkTMsg.upldCsvRqstPk.getValue()), excParam);

        // // END   2023/10/20 N.Takatsu [QC#60925, MOD]
        // ResultSet rsFrom = StmtFrom.executeQuery();
        // if (rsFrom.next()) {
        //     if (CNTR_RESET_TP.METER_ROLLOVER.equals(rsFrom.getString("CNTR_RESET_TP_CD"))) {
        //         return false;
        //     }
        //     if (CNTR_RESET_TP.METER_EXCHANGE.equals(rsFrom.getString("CNTR_RESET_TP_CD"))) {
        //         return false;
        //     }
        //     if (DS_MTR_READ_TP.ADJUSTMENT.equals(rsFrom.getString("DS_MTR_READ_TP_CD"))) {
        //         return false;
        //     }
        // if (rsFrom.getBigDecimal("READ_MTR_CNT").compareTo(svcPhysMtrUpdWrkTMsg.readMtrCnt.getValue()) < 0) {
        //     return true;
        // }
        Map<String, Object> rsltMap = NWXC412001.autoCast(ssmBatchClient.queryObject("getBeforeRead", setParamForBeforeRead(svcPhysMtrUpdWrkTMsg.svcMachMstrPk.getValue(), getMtrLbCd(svcPhysMtrUpdWrkTMsg.mtrLbDescTxt.getValue()), null,
                svcPhysMtrUpdWrkTMsg.mtrReadDt.getValue(), svcPhysMtrUpdWrkTMsg.svcPhysMtrReadGrpSq.getValue(), svcPhysMtrUpdWrkTMsg.procModeCd.getValue(), svcPhysMtrUpdWrkTMsg.upldCsvRqstPk.getValue()), excParam));
        if (rsltMap != null) {
            String cntrResetTpCd = (String) rsltMap.get("CNTR_RESET_TP_CD");
            if (CNTR_RESET_TP.METER_ROLLOVER.equals(cntrResetTpCd)) {
                return false;
            }
            if (CNTR_RESET_TP.METER_EXCHANGE.equals(cntrResetTpCd)) {
                return false;
            }
            String dsMtrReadTpCd = (String) rsltMap.get("DS_MTR_READ_TP_CD");
            if (DS_MTR_READ_TP.ADJUSTMENT.equals(dsMtrReadTpCd)) {
                return false;
            }
            BigDecimal readMtrCnt = (BigDecimal) rsltMap.get("READ_MTR_CNT");
            if (readMtrCnt.compareTo(svcPhysMtrUpdWrkTMsg.readMtrCnt.getValue()) < 0) {
                return true;
            }
        // END 2024/03/13 t.aizawa [QC#61771, MOD]
        }
        return false;
    }

    private boolean isSvcPhysMtrReadGrpSqError(BigDecimal svcPhysMtrReadGrpSq) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcPhysMtrReadGrpSq", svcPhysMtrReadGrpSq);
        paramMap.put("dsMtrReadTpGrpCd_S", DS_MTR_READ_TP_GRP.SERVICE_READS);
        paramMap.put("dsMtrReadTpGrpCd_SF", DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ);
        // START 2024/03/13 t.aizawa [QC#61771, MOD]
        // PreparedStatement StmtTrue = ssmLLClient.createPreparedStatement("getSvcPhysMtrReadGrpSqError", paramMap, excParam);
        // ResultSet rs = StmtTrue.executeQuery();
        // if (rs.next()) {
        Map<String, Object> rsltMap = NWXC412001.autoCast(ssmBatchClient.queryObject("getSvcPhysMtrReadGrpSqError", paramMap, excParam));
        if (rsltMap != null) {
        // END 2024/03/13 t.aizawa [QC#61771, MOD]
            return false;
        }
        return true;
    }

    private boolean isMtrLbCDuplicateError(List<SVC_PHYS_MTR_UPD_WRKTMsg> svcPhysMtrUpdWrkTMsg) {
        boolean matchFlag = false;
        for (int i = 0; i < svcPhysMtrUpdWrkTMsg.size(); i++) {
            for (int j = 0; j < svcPhysMtrUpdWrkTMsg.size(); j++) {
                if (svcPhysMtrUpdWrkTMsg.get(i).mtrLbDescTxt.getValue().equals(svcPhysMtrUpdWrkTMsg.get(j).mtrLbDescTxt.getValue())) {
                    if (matchFlag) {
                        return true;
                    }
                    if (!matchFlag) {
                        matchFlag = true;
                    }
                }
            }
            matchFlag = false;
        }
        return false;

    }

    private boolean isCarrOverMtrError(List<SVC_PHYS_MTR_UPD_WRKTMsg> svcPhysMtrUpdWrkTMsgArray) throws SQLException {
        List<String> mtrLbCdListWrk = new ArrayList<String>();
        for (int i = 0; i < svcPhysMtrUpdWrkTMsgArray.size(); i++) {
            mtrLbCdListWrk.add(getMtrLbCd(svcPhysMtrUpdWrkTMsgArray.get(i).mtrLbDescTxt.getValue()));
        }
        if (PROC_MODE_CD_CREATE.equals(svcPhysMtrUpdWrkTMsgArray.get(0).procModeCd.getValue())) {
            SVC_PHYS_MTRTMsg svcPhysMtrTMsg = new SVC_PHYS_MTRTMsg();
            SVC_PHYS_MTRTMsgArray svcPhysMtrTMsgArray = new SVC_PHYS_MTRTMsgArray();
            svcPhysMtrTMsg.setSQLID("002");
            svcPhysMtrTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            svcPhysMtrTMsg.setConditionValue("svcMachMstrPk01", svcPhysMtrUpdWrkTMsgArray.get(0).svcMachMstrPk.getValue());
            svcPhysMtrTMsgArray = (SVC_PHYS_MTRTMsgArray) EZDTBLAccessor.findByCondition(svcPhysMtrTMsg);

            boolean matchFlag = false;
            for (int i = 0; i < mtrLbCdListWrk.size(); i++) {
                for (int j = 0; j < svcPhysMtrTMsgArray.getValidCount(); j++) {
                    if (mtrLbCdListWrk.get(i).equals(svcPhysMtrTMsgArray.no(j).mdlMtrLbCd.getValue())) {
                        matchFlag = true;
                    }
                }
                if (!matchFlag) {
                    this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(0).upldCsvRqstRowNum.getValue(), NSBM0221E, null);
                    updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(0), S21MessageFunc.clspGetMessage(NSBM0221E, new String[] {}));
                    return true;
                }
                matchFlag = false;
            }

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", glblCmpyCd);
            paramMap.put("svcMachMstrPk", svcPhysMtrUpdWrkTMsgArray.get(0).svcMachMstrPk.getValue());
            // START 2024/03/13 t.aizawa [QC#61771, MOD]
            // PreparedStatement StmtTrue = ssmLLClient.createPreparedStatement("getCarryOverMtr", paramMap, excParam);
            // ResultSet rs = StmtTrue.executeQuery();
            List<Map<String, Object>> rsltMapList = NWXC412001.autoCast(ssmBatchClient.queryObjectList("getCarryOverMtr", paramMap, excParam));
            // END 2024/03/13 t.aizawa [QC#61771, MOD]
            matchFlag = false;
            // START 2024/03/13 t.aizawa [QC#61771, MOD]
            // while (rs.next()) {
            for (Map<String, Object> rsltMap : rsltMapList) {
            // END 2024/03/13 t.aizawa [QC#61771, MOD]
                for (int i = 0; i < mtrLbCdListWrk.size(); i++) {
                    // START 2024/03/13 t.aizawa [QC#61771, MOD]
                    // if (rs.getString("MDL_MTR_LB_CD").equals(mtrLbCdListWrk.get(i))) {
                    String mdlMtrLbCd = (String) rsltMap.get("MDL_MTR_LB_CD");
                    if (mdlMtrLbCd.equals(mtrLbCdListWrk.get(i))) {
                    // END 2024/03/13 t.aizawa [QC#61771, MOD]
                        matchFlag = true;
                    }
                }
                if (!matchFlag) {
                    this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(0).upldCsvRqstRowNum.getValue(), NSBM0221E, null);
                    updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(0), S21MessageFunc.clspGetMessage(NSBM0221E, new String[] {}));
                    return true;
                }
                matchFlag = false;
            }

        } else if (PROC_MODE_CD_UPDATE.equals(svcPhysMtrUpdWrkTMsgArray.get(0).procModeCd.getValue())) {
            SVC_PHYS_MTR_READTMsg svcPhysMtrReadTMsg = new SVC_PHYS_MTR_READTMsg();
            SVC_PHYS_MTR_READTMsgArray svcPhysMtrReadTMsgArray = new SVC_PHYS_MTR_READTMsgArray();

            svcPhysMtrReadTMsg.setSQLID("007");
            svcPhysMtrReadTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            svcPhysMtrReadTMsg.setConditionValue("svcPhysMtrReadGrpSq01", svcPhysMtrUpdWrkTMsgArray.get(0).svcPhysMtrReadGrpSq.getValue());
            svcPhysMtrReadTMsgArray = (SVC_PHYS_MTR_READTMsgArray) EZDTBLAccessor.findByCondition(svcPhysMtrReadTMsg);

            boolean matchFlag = false;
            for (int i = 0; i < mtrLbCdListWrk.size(); i++) {
                for (int j = 0; j < svcPhysMtrReadTMsgArray.length(); j++) {
                    if (mtrLbCdListWrk.get(i).equals(svcPhysMtrReadTMsgArray.no(j).mtrLbCd.getValue())) {
                        matchFlag = true;
                    }
                }
                if (!matchFlag) {
                    this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(0).upldCsvRqstRowNum.getValue(), NSBM0221E, null);
                    updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(0), S21MessageFunc.clspGetMessage(NSBM0221E, new String[] {}));
                    return true;
                }
                matchFlag = false;
            }

            matchFlag = false;
            for (int i = 0; i < svcPhysMtrReadTMsgArray.length(); i++) {
                for (int j = 0; j < mtrLbCdListWrk.size(); j++) {
                    if (svcPhysMtrReadTMsgArray.no(i).carryOverTpCd.getValue().equals(ZYPConstant.FLG_ON_1)) {
                        matchFlag = true;
                    }
                    if (mtrLbCdListWrk.get(j).equals(svcPhysMtrReadTMsgArray.no(i).mtrLbCd.getValue())) {
                        matchFlag = true;
                    }
                }
                if (!matchFlag) {
                    this.messenger.addMessageForRecord(svcPhysMtrUpdWrkTMsgArray.get(0).upldCsvRqstRowNum.getValue(), NSBM0221E, null);
                    updateSvcPhysMtrUpdWrk(svcPhysMtrUpdWrkTMsgArray.get(0), S21MessageFunc.clspGetMessage(NSBM0221E, new String[] {}));
                    return true;
                }
                matchFlag = false;
            }
        }

        return false;
    }

    private boolean isInOutFlagError(String dsTestCopyClsDescTxt) {
        if (getDsTestCopyClsDescTxt(DS_TEST_COPY_CLS.TEST_COPY_IN).equals(dsTestCopyClsDescTxt)) {
            return false;
        }
        if (getDsTestCopyClsDescTxt(DS_TEST_COPY_CLS.TEST_COPY_OUT).equals(dsTestCopyClsDescTxt)) {
            return false;
        }
        return true;
    }

    private String getDsTestCopyClsDescTxt(String dsTestCopyClsCd) {
        DS_TEST_COPY_CLSTMsg dsTestCopyClsTMsg = new DS_TEST_COPY_CLSTMsg();
        setValue(dsTestCopyClsTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(dsTestCopyClsTMsg.dsTestCopyClsCd, dsTestCopyClsCd);
        dsTestCopyClsTMsg = (DS_TEST_COPY_CLSTMsg) EZDTBLAccessor.findByKey(dsTestCopyClsTMsg);
        if (dsTestCopyClsTMsg != null) {
            return dsTestCopyClsTMsg.dsTestCopyClsDescTxt.getValue();
        }
        return null;
    }

    private boolean isDsMtrReadTpDescTxtError(String dsMtrReadTpCd) {
        if (getDsMtrReadTpDescTxt(DS_MTR_READ_TP.ACTUAL).equals(dsMtrReadTpCd)) {
            return false;
        }
        if (getDsMtrReadTpDescTxt(DS_MTR_READ_TP.ADJUSTMENT).equals(dsMtrReadTpCd)) {
            return false;
        }
        if (getDsMtrReadTpDescTxt(DS_MTR_READ_TP.ROLLOVER).equals(dsMtrReadTpCd)) {
            return false;
        }
        if (getDsMtrReadTpDescTxt(DS_MTR_READ_TP.EXCHANGE_METER_TO).equals(dsMtrReadTpCd)) {
            return false;
        }
        return true;
    }

    private String getDsMtrReadTpDescTxt(String getDsMtrReadTpCd) {
        DS_MTR_READ_TPTMsg dsMtrReadTpTMsg = new DS_MTR_READ_TPTMsg();
        setValue(dsMtrReadTpTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(dsMtrReadTpTMsg.dsMtrReadTpCd, getDsMtrReadTpCd);
        dsMtrReadTpTMsg = (DS_MTR_READ_TPTMsg) EZDTBLAccessor.findByKey(dsMtrReadTpTMsg);
        if (dsMtrReadTpTMsg != null) {
            return dsMtrReadTpTMsg.dsMtrReadTpDescTxt.getValue();
        }
        return null;
    }

    private BigDecimal getRollOverTestMtrCnt(BigDecimal newMtrReadCnt, BigDecimal inMtrReadCnt, SVC_PHYS_MTR_READTMsg svcPhysMtrReadTMsg) {
        BigDecimal maxMtrReadCnt = BigDecimal.ZERO;
        SVC_PHYS_MTRTMsg svcPhysMtrTMsg = new SVC_PHYS_MTRTMsg();
        setValue(svcPhysMtrTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(svcPhysMtrTMsg.svcPhysMtrPk, svcPhysMtrReadTMsg.svcPhysMtrPk.getValue());
        svcPhysMtrTMsg = (SVC_PHYS_MTRTMsg) EZDTBLAccessor.findByKey(svcPhysMtrTMsg);

        if (svcPhysMtrTMsg != null) {
            if (svcPhysMtrTMsg.cntrDigitNum.getValueInt() > 0) {
                maxMtrReadCnt = BigDecimal.valueOf(Math.pow(10, svcPhysMtrTMsg.cntrDigitNum.getValueInt()));
                newMtrReadCnt = newMtrReadCnt.add(maxMtrReadCnt);
                return newMtrReadCnt.subtract(inMtrReadCnt);
            }
        }
        return BigDecimal.ZERO;
    }
    // 2023/07/07 QC#60925 Add End

    // START 2023/10/20 N.Takatsu [QC#60925, ADD]
    private boolean isEqualsForString(String val1, String val2) {
        if (!hasValue(val1) && !hasValue(val2)) {
            return true;
        }
        if (hasValue(val1) && !hasValue(val2)) {
            return false;
        }
        if (!hasValue(val1) && hasValue(val2)) {
            return false;
        }
        if (val1.equals(val2)) {
            return true;
        }
        return false;
    }
    // END   2023/10/20 N.Takatsu [QC#60925, ADD]
}
