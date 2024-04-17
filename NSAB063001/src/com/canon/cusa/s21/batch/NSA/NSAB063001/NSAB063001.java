/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB063001;

import static com.canon.cusa.s21.batch.NSA.NSAB063001.constant.NSAB063001Constant.*;
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
import parts.dbcommon.EZDTBLAccessor;

import business.db.ART10FMsg;
import business.db.CTAC_PSNTMsg;
import business.db.DS_CTAC_PNT_TPTMsg;
import business.db.SVC_CTAC_TPTMsg;
import business.db.SVC_MACH_CTAC_PSNTMsg;
import business.db.SVC_MACH_CTAC_UPLD_WRKTMsg;
import business.db.UPLD_CSV_RQSTTMsg;
import business.parts.NMZC002001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC002001.NMZC002001;
import com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Machine Master Contact Upload
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/13   Hitachi         A.Kohinata      Create          N/A
 * 2016/07/04   Hitachi         T.Tomita        Update          QC#11164
 * 2017/02/17   Hitachi         N.Arai          Update          QC#17301
 * 2017/09/15   Hitachi         M.Kidokoro      Update          QC#21030,21032
 * 2017/09/15   Hitachi         M.Kidokoro      Update          QC#21029
 * 2017/09/19   Hitachi         M.Kidokoro      Update          QC#21031
 * 2017/10/19   Hitachi         M.Kidokoro      Update          QC#20246
 * 2018/01/16   Hitachi         M.Kidokoro      Update          QC#22630
 * 2018/01/17   CITS            M.Naito         Update          QC#22038
 * 2018/07/05   Hitachi         K.Kitachi       Update          QC#26340
 * 2018/07/17   Hitachi         A.Kohinata      Update          QC#22583
 * 2018/09/25   Hitachi         K.Kitachi       Update          QC#27788
 * 2019/01/16   Fujitsu         S.Kosaka        Update          QC#29642
 * </pre>
 */

public class NSAB063001 extends S21BatchMain {

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

    // del start 2018/07/17 QC#22583
    ///** Normal Count */
    //private int normalCount = 0;
    // del end 2018/07/17 QC#22583

    /** ErrCount */
    private int errCount = 0;

    /** Current Location Account Number */
    private String curLocAcctNum = null;

 // START 2017/02/17 N.Arai [QC#17301, MOD]
    /** TotalErrCount */
    private int totalErrCount = 0;
 // END 2017/02/17 N.Arai [QC#17301, MOD]

    // add start 2018/07/17 QC#22583
    /** Insert Count */
    private int insertCount = 0;

    /** Update Count */
    private int updateCount = 0;

    /** Total Normal Count */
    private int totalNormalCount = 0;
    // add end 2018/07/17 QC#22583

    @Override
    protected void initRoutine() {

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NASM0010E);
        }
        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd, BATCH_ID);
        if (!hasValue(this.salesDate)) {
            throw new S21AbendException(NASM0011E);
        }

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
            // 2017/02/17 N.Arai [QC#17301, ADD]
            commit();
        }
    }

    @Override
    protected void termRoutine() {
        // mod start 2018/07/17 QC#22583
        //setTermState(this.termCd, this.normalCount, this.errCount);
        setTermState(this.termCd, this.totalNormalCount, this.totalErrCount);
        // mod end 2018/07/17 QC#22583
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB063001().executeBatch(NSAB063001.class.getSimpleName());
    }

    private void doProcess(ART10FMsg fMsg) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        // START 2017/09/19 M.Kidokoro [QC#21031, ADD]
        PreparedStatement wrkStmt = null;
        ResultSet wrkRs = null;
        // END 2017/09/19 M.Kidokoro [QC#21031, ADD]

        // Get Upload ID
        String upldCsvId = getUpldCsvId(fMsg);

        // Get Upload Request PK
        BigDecimal upldCsvRqstPk = getUpldCsvReqPk(fMsg);
        UPLD_CSV_RQSTTMsg upldCSVRqstTMsg = getUpldCSVRqst(upldCsvRqstPk);
        this.messenger = new ZYPCSVUploadMessenger(upldCsvId, upldCsvRqstPk);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getSvcMachCtacUpldWrk", setParamMap(upldCSVRqstTMsg), setExecParam());
            rs = stmt.executeQuery();
            // START 2017/09/19 M.Kidokoro [QC#21031, ADD]
            wrkStmt = this.ssmLLClient.createPreparedStatement("getSvcMachCtacUpldWrk", setParamMap(upldCSVRqstTMsg), setExecParamForWrk());
            wrkRs = wrkStmt.executeQuery();
            // END 2017/09/19 M.Kidokoro [QC#21031, ADD]

            SVC_MACH_CTAC_UPLD_WRKTMsg wrkTMsg = null;
            boolean errFlg = false;
            // add start 2018/07/17 QC#22583
            this.insertCount = 0;
            this.updateCount = 0;
            // add end 2018/07/17 QC#22583
            // 2017/02/17 N.Arai [QC#17301, ADD]
            this.errCount = 0;
            // START 2017/09/19 M.Kidokoro [QC#21031, ADD]
            int wrkIndex = 0;
            // END 2017/09/19 M.Kidokoro [QC#21031, ADD]

            while (rs.next()) {
                wrkTMsg = setWrkTMsg(rs);
                errFlg = false;

                // START 2017/09/19 M.Kidokoro [QC#21031, ADD]
                // START 2018/01/16 M.Kidokoro [QC#22630, DEL]
//                if (!validateOverlap(wrkTMsg, wrkRs, wrkIndex)) {
//                    this.messenger.uploadMessage();
//                    this.errCount++;
//                    break;
//                }
                // END 2018/01/16 M.Kidokoro [QC#22630, DEL]
                // END 2017/09/19 M.Kidokoro [QC#21031, ADD]

                if (NMZC002001Constant.PROCESS_MODE_CTAC_CRAT.equals(wrkTMsg.procModeCd.getValue())) {
                    // START 2018/01/16 M.Kidokoro [QC#22630, MOD]
//                    errFlg = doProcessCreateMode(wrkTMsg);
                    errFlg = doProcessCreateMode(wrkTMsg, wrkRs, wrkIndex);
                    // END 2018/01/16 M.Kidokoro [QC#22630, MOD]
                } else if (NMZC002001Constant.PROCESS_MODE_CTAC_UPD.equals(wrkTMsg.procModeCd.getValue())) {
                    // START 2018/01/16 M.Kidokoro [QC#22630, MOD]
//                    errFlg = doProcessUpdateMode(wrkTMsg);
                    errFlg = doProcessUpdateMode(wrkTMsg, wrkRs, wrkIndex);
                    // END 2018/01/16 M.Kidokoro [QC#22630, MOD]
                } else {
                    this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), ZZM9000E, MSG_MODE);
                    errFlg = false;
                }

                if (errFlg) {
                    // START 2017/09/15 M.Kidokoro [QC#21029, DEL]
//                    commit();
                    // END 2017/09/15 M.Kidokoro [QC#21029, DEL]
                    // mod start 2018/07/17 QC#22583
                    //this.normalCount++;
                    if (NMZC002001Constant.PROCESS_MODE_CTAC_CRAT.equals(wrkTMsg.procModeCd.getValue())) {
                        this.insertCount++;
                    } else {
                        this.updateCount++;
                    }
                    // mod end 2018/07/17 QC#22583
                } else {
                    // START 2017/09/15 M.Kidokoro [QC#21029, DEL]
//                    rollback();
                    // END 2017/09/15 M.Kidokoro [QC#21029, DEL]
                    this.messenger.uploadMessage();
                    this.errCount++;
                }
                // START 2017/09/19 M.Kidokoro [QC#21031, ADD]
                wrkIndex++;
                // END 2017/09/19 M.Kidokoro [QC#21031, ADD]
            }

            // START 2018/09/25 K.Kitachi [QC#27788, ADD]
            List<Map<String, Object>> primErrUpldWrkInfoList = getPrimErrUpldWrkInfoList(upldCsvRqstPk);
            for (Map<String, Object> primErrUpldWrkInfo : primErrUpldWrkInfoList) {
                this.messenger.addMessageForRecord((BigDecimal) primErrUpldWrkInfo.get("UPLD_CSV_RQST_ROW_NUM"), NSAM0738E, null);
                this.messenger.uploadMessage();
                this.errCount++;
                if (NMZC002001Constant.PROCESS_MODE_CTAC_CRAT.equals((String) primErrUpldWrkInfo.get("PROC_MODE_CD"))) {
                    this.insertCount--;
                } else {
                    this.updateCount--;
                }
            }
            // END 2018/09/25 K.Kitachi [QC#27788, ADD]

            // 2017/02/17 N.Arai [QC#17301, ADD]
            this.totalErrCount += this.errCount;

            if (this.errCount == 0) {
                // START 2017/09/15 M.Kidokoro [QC#21029, ADD]
                commit();
                // END 2017/09/15 M.Kidokoro [QC#21029, ADD]
                this.batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.NOMAL_END);
                // add start 2018/07/17 QC#22583
                this.totalNormalCount = this.totalNormalCount + this.insertCount + this.updateCount;
                String arg = createResultMessageArg();
                this.messenger.addMessageForFile(NYXM0001I, arg);
                this.messenger.uploadMessage();
                // add end 2018/07/17 QC#22583
            } else {
                // START 2017/09/15 M.Kidokoro [QC#21029, ADD]
                rollback();
                // END 2017/09/15 M.Kidokoro [QC#21029, ADD]
                this.termCd = TERM_CD.WARNING_END;
                this.batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.WARING_END);
                // mod start 2018/07/17 QC#22583
                //this.messenger.addMessageForFile(NSAM0510E, null);
                this.errCount = this.errCount + this.insertCount + this.updateCount;
                this.totalErrCount = this.totalErrCount + this.insertCount + this.updateCount;
                this.insertCount = 0;
                this.updateCount = 0;
                String arg = createResultMessageArg();
                this.messenger.addMessageForFile(NYXM0002E, arg);
                // mod end 2018/07/17 QC#22583
                this.messenger.uploadMessage();
            }
        } catch (SQLException se) {
            sqlExceptionHandler(se);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            // START 2017/09/19 M.Kidokoro [QC#21031, ADD]
            S21SsmLowLevelCodingClient.closeResource(wrkStmt, wrkRs);
            // END 2017/09/19 M.Kidokoro [QC#21031, ADD]
        }
    }

    // START 2018/01/16 M.Kidokoro [QC#22630, MOD]
//    private boolean doProcessCreateMode(SVC_MACH_CTAC_UPLD_WRKTMsg wrkTMsg) throws SQLException {
    private boolean doProcessCreateMode(SVC_MACH_CTAC_UPLD_WRKTMsg wrkTMsg, ResultSet wrkRs, int wrkIndex) throws SQLException {
    // END 2018/01/16 M.Kidokoro [QC#22630, MOD]

        // START 2018/01/16 M.Kidokoro [QC#22630, MOD]
//        if (!validateCreateMode(wrkTMsg)) {
        if (!validateCreateMode(wrkTMsg, wrkRs, wrkIndex)) {
        // END 2018/01/16 M.Kidokoro [QC#22630, MOD]
            return false;
        }
        NMZC002001PMsg apiPMsg = new NMZC002001PMsg();
        // START 2018/01/17 M.Naito [QC#22038, MOD]
//        CTAC_PSNTMsg ctacPsnTMsg = getCtacPsn(wrkTMsg.ctacPsnFirstNm.getValue(), wrkTMsg.ctacPsnLastNm.getValue());
        CTAC_PSNTMsg ctacPsnTMsg = getCtacPsnForDelyIstl(wrkTMsg.ctacPsnFirstNm.getValue(), wrkTMsg.ctacPsnLastNm.getValue());

        if (ctacPsnTMsg == null) {
            if (!callContactUpdateApiCreateMode(wrkTMsg, apiPMsg)) {
                return false;
            }
        } else {
            if (!callContactUpdateApiUpdateMode(wrkTMsg, ctacPsnTMsg, apiPMsg)) {
                return false;
            }
        }
        // check SVC_MACH_CTAC_PSN
        BigDecimal svcMachCtacPsnPk = checkSvcMachCtacPsn(wrkTMsg, apiPMsg);
        if (svcMachCtacPsnPk == null) {
            if (!insertSvcMachCtacPsn(wrkTMsg, apiPMsg)) {
                return false;
            }
        } else {
            if (!updateSvcMachCtacPsn(wrkTMsg, svcMachCtacPsnPk)) {
                return false;
            }
        }
        // END 2018/01/17 M.Naito [QC#22038, MOD]
        return true;
    }

    // START 2018/01/16 M.Kidokoro [QC#22630, MOD]
//    private boolean doProcessUpdateMode(SVC_MACH_CTAC_UPLD_WRKTMsg wrkTMsg) throws SQLException {
    private boolean doProcessUpdateMode(SVC_MACH_CTAC_UPLD_WRKTMsg wrkTMsg, ResultSet wrkRs, int wrkIndex) throws SQLException {
    // END 2018/01/16 M.Kidokoro [QC#22630, MOD]

        // START 2018/01/16 M.Kidokoro [QC#22630, MOD]
//        if (!validateUpdateMode(wrkTMsg)) {
        if (!validateUpdateMode(wrkTMsg, wrkRs, wrkIndex)) {
        // END 2018/01/16 M.Kidokoro [QC#22630, MOD]
            return false;
        }
        NMZC002001PMsg apiPMsg = new NMZC002001PMsg();
        // START 2018/01/17 M.Naito [QC#22038, MOD]
//        CTAC_PSNTMsg ctacPsnTMsg = getCtacPsn(wrkTMsg.ctacPsnPk.getValue());
        CTAC_PSNTMsg ctacPsnTMsg = getCtacPsnForDelyIstl(wrkTMsg.ctacPsnFirstNm.getValue(), wrkTMsg.ctacPsnLastNm.getValue());

        if (ctacPsnTMsg == null) {
            // START 2018/07/05 K.Kitachi [QC#26340, MOD]
//            this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NSZM0617E, null);
            this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NSZM0610E, "Contact");
            // END 2018/07/05 K.Kitachi [QC#26340, MOD]
            return false;
        }
        if (this.salesDate.compareTo(wrkTMsg.effFromDt.getValue()) > 0) {
            this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NSAM0373E, MSG_SALES_DATE);
            return false;
        }
        if (!callContactUpdateApiUpdateMode(wrkTMsg, ctacPsnTMsg, apiPMsg)) {
            return false;
        }
        // check SVC_MACH_CTAC_PSN
        BigDecimal svcMachCtacPsnPk = checkSvcMachCtacPsn(wrkTMsg, apiPMsg);
        if (svcMachCtacPsnPk == null) {
            if (!insertSvcMachCtacPsn(wrkTMsg, apiPMsg)) {
                return false;
            }
        } else {
            if (!updateSvcMachCtacPsn(wrkTMsg, svcMachCtacPsnPk)) {
                return false;
            }
        }
        // END 2018/01/17 M.Naito [QC#22038, MOD]
        return true;
    }

    // START 2018/01/16 M.Kidokoro [QC#22630, MOD]
//    private boolean validateCreateMode(SVC_MACH_CTAC_UPLD_WRKTMsg wrkTMsg) throws SQLException {
    private boolean validateCreateMode(SVC_MACH_CTAC_UPLD_WRKTMsg wrkTMsg, ResultSet wrkRs, int wrkIndex) throws SQLException {
    // END 2018/01/16 M.Kidokoro [QC#22630, MOD]

        BigDecimal upldCsvRqstRowNum = wrkTMsg.upldCsvRqstRowNum.getValue();

        // Mandatory check
        if (!hasValue(wrkTMsg.svcMachMstrPk) && !hasValue(wrkTMsg.serNum)) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, ZZM9000E, MSG_BASE_ID_OR_SERIAL_NUM);
            return false;
        }
        if (!hasValue(wrkTMsg.ctacPsnLastNm)) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, ZZM9000E, MSG_LAST_NAME);
            return false;
        }
        if (!hasValue(wrkTMsg.ctacPsnFirstNm)) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, ZZM9000E, MSG_FIRST_NAME);
            return false;
        }
        if (!hasValue(wrkTMsg.dsCtacPntTpCd)) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, ZZM9000E, MSG_CTAC_PNT);
            return false;
        }
        if (!hasValue(wrkTMsg.dsCtacPntValTxt)) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, ZZM9000E, MSG_CTAC_PNT_VAL);
            return false;
        }
        if (!hasValue(wrkTMsg.svcCtacTpCd)) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, ZZM9000E, MSG_CTAC_TP_CD);
            return false;
        }
        if (!hasValue(wrkTMsg.effFromDt)) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, ZZM9000E, MSG_EFF_FROM_DT);
            return false;
        }

        // Master check
        if (!existsSvcMachMstr(wrkTMsg)) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NSAM0037E, MSG_BASE_ID_OR_SERIAL_NUM);
            return false;
        }
        if (!existsDsCtacPntTp(wrkTMsg.dsCtacPntTpCd.getValue())) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NSAM0037E, MSG_CTAC_PNT);
            return false;
        }
        if (!existsSvcCtacTp(wrkTMsg.svcCtacTpCd.getValue())) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NSAM0037E, MSG_CTAC_TP_CD);
            return false;
        }

        // Relation check
        if (!hasValue(this.curLocAcctNum)) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NSAM0535E, MSG_CUR_LOC_ACCT_NUM);
            return false;
        }
        // START 2017/09/15 M.Kidokoro [QC#21030,21032, DEL]
//        if (ZYPConstant.FLG_ON_Y.equals(wrkTMsg.dsCtacPntActvFlg.getValue()) && existsActvCtacPnt(wrkTMsg)) {
//            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NSAM0445E, null);
//            return false;
//        }
        // END 2017/09/15 M.Kidokoro [QC#21030,21032, DEL]
        // START 2016/07/04 T.Tomita [QC#11164, ADD]
        if (ZYPCommonFunc.hasValue(wrkTMsg.effFromDt) && ZYPCommonFunc.hasValue(wrkTMsg.effThruDt) && ZYPDateUtil.compare(wrkTMsg.effFromDt.getValue(), wrkTMsg.effThruDt.getValue()) > 0) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NSAM0374E, "start date");
            return false;
        }
        // START 2018/01/17 M.Naito [QC#22038, DEL]
        // END 2016/07/04 T.Tomita [QC#11164, ADD]
        // START 2018/01/16 M.Kidokoro [QC#22630, ADD]
//        if (!validateOverlap(wrkTMsg, wrkRs, wrkIndex)) {
//            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NSAM0213E, null);
//            return false;
//        }
        // END 2018/01/16 M.Kidokoro [QC#22630, ADD]
        // END 2018/01/17 M.Naito [QC#22038, DEL]

        return true;
    }

    // START 2018/01/16 M.Kidokoro [QC#22630, MOD]
//    private boolean validateUpdateMode(SVC_MACH_CTAC_UPLD_WRKTMsg wrkTMsg) throws SQLException {
    private boolean validateUpdateMode(SVC_MACH_CTAC_UPLD_WRKTMsg wrkTMsg, ResultSet wrkRs, int wrkIndex) throws SQLException {
    // END 2018/01/16 M.Kidokoro [QC#22630, MOD]

        BigDecimal upldCsvRqstRowNum = wrkTMsg.upldCsvRqstRowNum.getValue();

        // Mandatory check
        if (!hasValue(wrkTMsg.svcMachMstrPk) && !hasValue(wrkTMsg.serNum)) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, ZZM9000E, MSG_BASE_ID_OR_SERIAL_NUM);
            return false;
        }
        if (!hasValue(wrkTMsg.ctacPsnPk)) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, ZZM9000E, MSG_CONTACT_ID);
            return false;
        }
        if (!hasValue(wrkTMsg.dsCtacPntTpCd)) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, ZZM9000E, MSG_CTAC_PNT);
            return false;
        }
        if (!hasValue(wrkTMsg.dsCtacPntValTxt)) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, ZZM9000E, MSG_CTAC_PNT_VAL);
            return false;
        }
        if (!hasValue(wrkTMsg.svcCtacTpCd)) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, ZZM9000E, MSG_CTAC_TP_CD);
            return false;
        }
        if (!hasValue(wrkTMsg.effFromDt)) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, ZZM9000E, MSG_EFF_FROM_DT);
            return false;
        }

        // Master check
        if (!existsSvcMachMstr(wrkTMsg)) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NSAM0037E, MSG_BASE_ID_OR_SERIAL_NUM);
            return false;
        }
        if (!existsCtacPsn(wrkTMsg.ctacPsnPk.getValue())) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NSAM0037E, MSG_CONTACT_ID);
            return false;
        }
        if (!existsDsCtacPntTp(wrkTMsg.dsCtacPntTpCd.getValue())) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NSAM0037E, MSG_CTAC_PNT);
            return false;
        }
        if (hasValue(wrkTMsg.svcCtacTpCd) && !existsSvcCtacTp(wrkTMsg.svcCtacTpCd.getValue())) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NSAM0037E, MSG_CTAC_TP_CD);
            return false;
        }

        // Relation check
        if (!hasValue(this.curLocAcctNum)) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NSAM0535E, MSG_CUR_LOC_ACCT_NUM);
            return false;
        }
        // START 2017/09/15 M.Kidokoro [QC#21030,21032, DEL]
//        if (ZYPConstant.FLG_ON_Y.equals(wrkTMsg.dsCtacPntActvFlg.getValue()) && existsActvCtacPnt(wrkTMsg)) {
//            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NSAM0445E, null);
//            return false;
//        }
        // END 2017/09/15 M.Kidokoro [QC#21030,21032, DEL]
        // START 2016/07/04 T.Tomita [QC#11164, ADD]
        if (ZYPCommonFunc.hasValue(wrkTMsg.effFromDt) && ZYPCommonFunc.hasValue(wrkTMsg.effThruDt) && ZYPDateUtil.compare(wrkTMsg.effFromDt.getValue(), wrkTMsg.effThruDt.getValue()) > 0) {
            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NSAM0374E, "start date");
            return false;
        }
        // START 2018/01/17 M.Naito [QC#22038, DEL]
        // END 2016/07/04 T.Tomita [QC#11164, ADD]
        // START 2018/01/16 M.Kidokoro [QC#22630, ADD]
//        if (!validateOverlap(wrkTMsg, wrkRs, wrkIndex)) {
//            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NSAM0213E, null);
//            return false;
//        }
        // END 2018/01/16 M.Kidokoro [QC#22630, ADD]
        // END 2018/01/17 M.Naito [QC#22038, DEL]

        return true;
    }

    // START 2017/09/19 M.Kidokoro [QC#21031, ADD]
    private boolean validateOverlap(SVC_MACH_CTAC_UPLD_WRKTMsg wrkTMsg, ResultSet wrkRs, int wrkIndex) throws SQLException {
        // START 2018/01/16 M.Kidokoro [QC#22630, MOD]
//        BigDecimal upldCsvRqstRowNum = wrkTMsg.upldCsvRqstRowNum.getValue();
//        List<Integer> dupIdxList = getDuplicateIdx(wrkTMsg, wrkRs, wrkIndex);
//        if (!dupIdxList.isEmpty()) {
//            // set error info
//            this.messenger.addMessageForRecord(upldCsvRqstRowNum, NSAM0213E, null);
//            return false;
//        }
        List<Integer> dupIdxList = getDuplicateIdx(wrkTMsg, wrkRs, wrkIndex);
        if (!dupIdxList.isEmpty()) {
            return false;
        }
        // END 2018/01/16 M.Kidokoro [QC#22630, MOD]
        return true;
    }

    private List<Integer> getDuplicateIdx(SVC_MACH_CTAC_UPLD_WRKTMsg wrkTMsg, ResultSet wrkRs, int wrkIndex) throws SQLException {
        List<Integer> dupIdxList = new ArrayList<Integer>();
        int index = 0;
        // START 2018/01/16 M.Kidokoro [QC#22630, ADD]
        BigDecimal wrkSvcMachMstrPk;
        String wrkSerNum;
        // END 2018/01/16 M.Kidokoro [QC#22630, ADD]

        BigDecimal svcMachMstrPk = wrkTMsg.svcMachMstrPk.getValue();
        if (svcMachMstrPk != null) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", this.glblCmpyCd);
            paramMap.put("svcMachMstrPk", wrkTMsg.svcMachMstrPk.getValue());

            PreparedStatement stmt = null;
            ResultSet rs = null;
            stmt = this.ssmLLClient.createPreparedStatement("getCtacInfo", paramMap, setExecParam());
            rs = stmt.executeQuery();

            while (rs.next()) {
                // Last Name
                if (!isEqualsForString((String) rs.getString("CTAC_PSN_LAST_NM"), wrkTMsg.ctacPsnLastNm.getValue())) {
                    index++;
                    continue;
                }
                // First Name
                if (!isEqualsForString((String) rs.getString("CTAC_PSN_FIRST_NM"), wrkTMsg.ctacPsnFirstNm.getValue())) {
                    index++;
                    continue;
                }
                // Contact Point
                if (!isEqualsForString((String) rs.getString("DS_CTAC_PNT_TP_CD"), wrkTMsg.dsCtacPntTpCd.getValue())) {
                    index++;
                    continue;
                }
                // Contact Value
                if (!isEqualsForString((String) rs.getString("DS_CTAC_PNT_VAL_TXT"), wrkTMsg.dsCtacPntValTxt.getValue())) {
                    index++;
                    continue;
                }
                // Ext
                if (!isEqualsForString((String) rs.getString("DS_CTAC_PSN_EXTN_NUM"), wrkTMsg.dsCtacPsnExtnNum.getValue())) {
                    index++;
                    continue;
                }
                // IB Contact Type
                if (!isEqualsForString((String) rs.getString("SVC_CTAC_TP_CD"), wrkTMsg.svcCtacTpCd.getValue())) {
                    index++;
                    continue;
                }
                // Start Date, End Date
                if (!isDuplicateDate((String) rs.getString("EFF_FROM_DT"), (String) rs.getString("EFF_THRU_DT"), wrkTMsg.effFromDt.getValue(), wrkTMsg.effThruDt.getValue())) {
                    index++;
                    continue;
                }
                dupIdxList.add(index);
                break;
            }
        }

        index = 0;
        wrkRs.beforeFirst();
        while (wrkRs.next()) {
            if (wrkIndex == index) {
                index++;
                continue;
            }
            // START 2018/01/16 M.Kidokoro [QC#22630, ADD]
            wrkSvcMachMstrPk = wrkRs.getBigDecimal("SVC_MACH_MSTR_PK");
            wrkSerNum = wrkRs.getString("SER_NUM");
            if (wrkSvcMachMstrPk != null) {
                if (wrkTMsg.svcMachMstrPk.getValue().compareTo(wrkSvcMachMstrPk) != 0) {
                    index++;
                    continue;
                }
            }
            if (wrkSerNum != null) {
                if (!wrkTMsg.serNum.getValue().equals(wrkSerNum)) {
                    index++;
                    continue;
                }
            }
            // END 2018/01/16 M.Kidokoro [QC#22630, ADD]
            // Last Name
            if (!isEqualsForString((String) wrkRs.getString("CTAC_PSN_LAST_NM"), wrkTMsg.ctacPsnLastNm.getValue())) {
                index++;
                continue;
            }
            // First Name
            if (!isEqualsForString((String) wrkRs.getString("CTAC_PSN_FIRST_NM"), wrkTMsg.ctacPsnFirstNm.getValue())) {
                index++;
                continue;
            }
            // Contact Point
            if (!isEqualsForString((String) wrkRs.getString("DS_CTAC_PNT_TP_CD"), wrkTMsg.dsCtacPntTpCd.getValue())) {
                index++;
                continue;
            }
            // Contact Value
            if (!isEqualsForString((String) wrkRs.getString("DS_CTAC_PNT_VAL_TXT"), wrkTMsg.dsCtacPntValTxt.getValue())) {
                index++;
                continue;
            }
            // Ext
            if (!isEqualsForString((String) wrkRs.getString("DS_CTAC_PSN_EXTN_NUM"), wrkTMsg.dsCtacPsnExtnNum.getValue())) {
                index++;
                continue;
            }
            // IB Contact Type
            if (!isEqualsForString((String) wrkRs.getString("SVC_CTAC_TP_CD"), wrkTMsg.svcCtacTpCd.getValue())) {
                index++;
                continue;
            }
            // Start Date, End Date
            if (!isDuplicateDate((String) wrkRs.getString("EFF_FROM_DT"), (String) wrkRs.getString("EFF_THRU_DT"), wrkTMsg.effFromDt.getValue(), wrkTMsg.effThruDt.getValue())) {
                index++;
                continue;
            }
            if (!dupIdxList.contains(index)) {
                dupIdxList.add(index);
            }
            index++;
        }

        return dupIdxList;
    }

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

    private boolean isDuplicateDate(String fromDt1, String thruDt1, String fromDt2, String thruDt2) {
        if (!hasValue(thruDt1)) {
            thruDt1 = MAX_DT_VAL;
        }
        if (!hasValue(thruDt2)) {
            thruDt2 = MAX_DT_VAL;
        }
        if (ZYPDateUtil.compare(fromDt1, fromDt2) <= 0 && ZYPDateUtil.compare(fromDt2, thruDt1) <= 0) {
            return true;
        }
        if (ZYPDateUtil.compare(fromDt1, thruDt2) <= 0 && ZYPDateUtil.compare(thruDt2, thruDt1) <= 0) {
            return true;
        }
        if (ZYPDateUtil.compare(fromDt1, fromDt2) > 0 && ZYPDateUtil.compare(thruDt1, thruDt2) < 0) {
            return true;
        }
        return false;
    }
    // END 2017/09/19 M.Kidokoro [QC#21031, ADD]

    
    private boolean callContactUpdateApiCreateMode(SVC_MACH_CTAC_UPLD_WRKTMsg wrkTMsg, NMZC002001PMsg apiPMsg) throws SQLException {

        setValue(apiPMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
        setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(apiPMsg.slsDt, this.salesDate);
        setValue(apiPMsg.dsAcctNum, this.curLocAcctNum);
        setValue(apiPMsg.ctacPsnFirstNm, wrkTMsg.ctacPsnFirstNm);
        setValue(apiPMsg.ctacPsnLastNm, wrkTMsg.ctacPsnLastNm);
        // START 2017/10/19 M.Kidokoro [QC#20246, MOD]
//        setValue(apiPMsg.ctacTpCd, CTAC_TP.IB_CONTACT);
        setValue(apiPMsg.ctacTpCd, CTAC_TP.DELIVERY_INSTALL);
        // END 2017/10/19 M.Kidokoro [QC#20246, MOD]

        setValue(apiPMsg.ContactPointInfoList.no(0).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
        setValue(apiPMsg.ContactPointInfoList.no(0).dsCtacPntTpCd, wrkTMsg.dsCtacPntTpCd);
        setValue(apiPMsg.ContactPointInfoList.no(0).dsCtacPntValTxt, wrkTMsg.dsCtacPntValTxt);
        setValue(apiPMsg.ContactPointInfoList.no(0).dsCtacPsnExtnNum, wrkTMsg.dsCtacPsnExtnNum);
        // 2019/01/16 QC#29642 Add Start
        setValue(apiPMsg.ContactPointInfoList.no(0).updCtrlFlg, ZYPConstant.FLG_ON_Y);
        // 2019/01/16 QC#29642 Add End
        // START 2017/09/15 M.Kidokoro [QC#21030,21032, DEL]
//        setValue(apiPMsg.ContactPointInfoList.no(0).dsCtacPntActvFlg, selectValue(wrkTMsg.dsCtacPntActvFlg.getValue(), ZYPConstant.FLG_OFF_N));
        // END 2017/09/15 M.Kidokoro [QC#21030,21032, DEL]
        apiPMsg.ContactPointInfoList.setValidCount(1);

        new NMZC002001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
            for (S21ApiMessage msg : msgList) {
                String messageArg = null;
                if (msg.getXxMsgPrmArray().length > 0) {
                    messageArg = msg.getXxMsgPrmArray()[0];
                }
                this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), msg.getXxMsgid(), messageArg);
            }
            return false;
        }
        return true;
    }

    private boolean callContactUpdateApiUpdateMode(SVC_MACH_CTAC_UPLD_WRKTMsg wrkTMsg, CTAC_PSNTMsg ctacPsnTMsg, NMZC002001PMsg apiPMsg) throws SQLException {

        setValue(apiPMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_UPD);
        setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(apiPMsg.slsDt, this.salesDate);
        setValue(apiPMsg.ctacPsnPk, ctacPsnTMsg.ctacPsnPk);
        setValue(apiPMsg.dsAcctNum, this.curLocAcctNum);
        setValue(apiPMsg.ctacPsnFirstNm, selectValue(wrkTMsg.ctacPsnFirstNm.getValue(), ctacPsnTMsg.ctacPsnFirstNm.getValue()));
        setValue(apiPMsg.ctacPsnLastNm, selectValue(wrkTMsg.ctacPsnLastNm.getValue(), ctacPsnTMsg.ctacPsnLastNm.getValue()));
        // setValue(apiPMsg.ctacTpCd, ctacPsnTMsg.ctacTpCd);

        setValue(apiPMsg.ContactPointInfoList.no(0).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
        setValue(apiPMsg.ContactPointInfoList.no(0).dsCtacPntTpCd, wrkTMsg.dsCtacPntTpCd);
        setValue(apiPMsg.ContactPointInfoList.no(0).dsCtacPntValTxt, wrkTMsg.dsCtacPntValTxt);
        setValue(apiPMsg.ContactPointInfoList.no(0).dsCtacPsnExtnNum, wrkTMsg.dsCtacPsnExtnNum);
        // 2019/01/16 QC#29642 Add Start
        setValue(apiPMsg.ContactPointInfoList.no(0).updCtrlFlg, ZYPConstant.FLG_ON_Y);
        // 2019/01/16 QC#29642 Add End
        // START 2017/09/15 M.Kidokoro [QC#21030,21032, DEL]
//        setValue(apiPMsg.ContactPointInfoList.no(0).dsCtacPntActvFlg, selectValue(wrkTMsg.dsCtacPntActvFlg.getValue(), ZYPConstant.FLG_OFF_N));
        // END 2017/09/15 M.Kidokoro [QC#21030,21032, DEL]
        apiPMsg.ContactPointInfoList.setValidCount(1);

        new NMZC002001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
            for (S21ApiMessage msg : msgList) {
                String messageArg = null;
                if (msg.getXxMsgPrmArray().length > 0) {
                    messageArg = msg.getXxMsgPrmArray()[0];
                }
                this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), msg.getXxMsgid(), messageArg);
            }
            return false;
        }
        return true;
    }

    private boolean insertSvcMachCtacPsn(SVC_MACH_CTAC_UPLD_WRKTMsg wrkTMsg, NMZC002001PMsg apiPMsg) {
        return insertSvcMachCtacPsn(wrkTMsg, null, apiPMsg);
    }
    
    private boolean insertSvcMachCtacPsn(SVC_MACH_CTAC_UPLD_WRKTMsg wrkTMsg, CTAC_PSNTMsg ctacPsnTMsg, NMZC002001PMsg apiPMsg) {

        BigDecimal dsCtacPntPk = apiPMsg.ContactPointInfoList.no(0).dsCtacPntPk.getValue();

        SVC_MACH_CTAC_PSNTMsg svcMachCtacPsnTMsg = new SVC_MACH_CTAC_PSNTMsg();
        setValue(svcMachCtacPsnTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(svcMachCtacPsnTMsg.svcMachCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MACH_CTAC_PSN_SQ));
        setValue(svcMachCtacPsnTMsg.svcMachMstrPk, wrkTMsg.svcMachMstrPk);
        if (ctacPsnTMsg == null) {
            setValue(svcMachCtacPsnTMsg.ctacPsnFirstNm, wrkTMsg.ctacPsnFirstNm);
            setValue(svcMachCtacPsnTMsg.ctacPsnLastNm, wrkTMsg.ctacPsnLastNm);
        } else {
            setValue(svcMachCtacPsnTMsg.ctacPsnFirstNm, selectValue(wrkTMsg.ctacPsnFirstNm.getValue(), ctacPsnTMsg.ctacPsnFirstNm.getValue()));
            setValue(svcMachCtacPsnTMsg.ctacPsnLastNm, selectValue(wrkTMsg.ctacPsnLastNm.getValue(), ctacPsnTMsg.ctacPsnLastNm.getValue()));
        }
        setValue(svcMachCtacPsnTMsg.dsCtacPntPk, dsCtacPntPk);
        setValue(svcMachCtacPsnTMsg.svcCtacTpCd, wrkTMsg.svcCtacTpCd);
        setValue(svcMachCtacPsnTMsg.effFromDt, wrkTMsg.effFromDt);
        setValue(svcMachCtacPsnTMsg.effThruDt, wrkTMsg.effThruDt);
        // START 2018/09/25 K.Kitachi [QC#27788, ADD]
        setValue(svcMachCtacPsnTMsg.dsPrimLocFlg, wrkTMsg.dsCtacPntActvFlg);
        // END 2018/09/25 K.Kitachi [QC#27788, ADD]

        S21ApiTBLAccessor.create(svcMachCtacPsnTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcMachCtacPsnTMsg.getReturnCode())) {
            this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NSAM0032E, svcMachCtacPsnTMsg.getTableName());
            return false;
        }
        return true;
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

    // START 2017/09/19 M.Kidokoro [QC#21031, ADD]
    private S21SsmExecutionParameter setExecParamForWrk() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(MAX_COMMIT_NUMBER);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execParam;
    }
    // END 2017/09/19 M.Kidokoro [QC#21031, ADD]

    private Map<String, Object> setParamMap(UPLD_CSV_RQSTTMsg upldCSVRqstTMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("upldCsvRqstPk", upldCSVRqstTMsg.upldCsvRqstPk);
        return paramMap;
    }

    private SVC_MACH_CTAC_UPLD_WRKTMsg setWrkTMsg(ResultSet rs) throws SQLException {
        SVC_MACH_CTAC_UPLD_WRKTMsg wrkTMsg = new SVC_MACH_CTAC_UPLD_WRKTMsg();
        setValue(wrkTMsg.upldCsvRqstPk, rs.getBigDecimal("UPLD_CSV_RQST_PK"));
        setValue(wrkTMsg.upldCsvRqstRowNum, rs.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"));
        setValue(wrkTMsg.procModeCd, rs.getString("PROC_MODE_CD"));
        setValue(wrkTMsg.svcMachMstrPk, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        setValue(wrkTMsg.serNum, rs.getString("SER_NUM"));
        setValue(wrkTMsg.ctacPsnPk, rs.getBigDecimal("CTAC_PSN_PK"));
        setValue(wrkTMsg.ctacPsnLastNm, rs.getString("CTAC_PSN_LAST_NM"));
        setValue(wrkTMsg.ctacPsnFirstNm, rs.getString("CTAC_PSN_FIRST_NM"));
        setValue(wrkTMsg.dsCtacPntTpCd, rs.getString("DS_CTAC_PNT_TP_CD"));
        setValue(wrkTMsg.dsCtacPntValTxt, rs.getString("DS_CTAC_PNT_VAL_TXT"));
        setValue(wrkTMsg.dsCtacPsnExtnNum, rs.getString("DS_CTAC_PSN_EXTN_NUM"));
        setValue(wrkTMsg.svcCtacTpCd, rs.getString("SVC_CTAC_TP_CD"));
        // START 2017/09/15 M.Kidokoro [QC#21030,21032, DEL]
//        setValue(wrkTMsg.dsCtacPntActvFlg, rs.getString("DS_CTAC_PNT_ACTV_FLG"));
        // END 2017/09/15 M.Kidokoro [QC#21030,21032, DEL]
        // START 2018/09/25 K.Kitachi [QC#27788, ADD]
        setValue(wrkTMsg.dsCtacPntActvFlg, rs.getString("DS_CTAC_PNT_ACTV_FLG"));
        // END 2018/09/25 K.Kitachi [QC#27788, ADD]
        setValue(wrkTMsg.effFromDt, rs.getString("EFF_FROM_DT"));
        setValue(wrkTMsg.effThruDt, rs.getString("EFF_THRU_DT"));
        return wrkTMsg;
    }

    private boolean existsSvcMachMstr(SVC_MACH_CTAC_UPLD_WRKTMsg wrkTMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcMachMstrPk", wrkTMsg.svcMachMstrPk.getValue());
        paramMap.put("serNum", wrkTMsg.serNum.getValue());
        Map<String, Object> svcMachMstrMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getSvcMachMstr", paramMap);
        if (svcMachMstrMap == null || svcMachMstrMap.isEmpty()) {
            return false;
        }
        setValue(wrkTMsg.svcMachMstrPk, (BigDecimal) svcMachMstrMap.get("SVC_MACH_MSTR_PK"));
        setValue(wrkTMsg.serNum, (String) svcMachMstrMap.get("SER_NUM"));
        this.curLocAcctNum = (String) svcMachMstrMap.get("CUR_LOC_ACCT_NUM");
        return true;
    }

    private boolean existsCtacPsn(BigDecimal ctacPsnPk) {
        CTAC_PSNTMsg tMsg = new CTAC_PSNTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.ctacPsnPk, ctacPsnPk);
        tMsg = (CTAC_PSNTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    private boolean existsDsCtacPntTp(String dsCtacPntTpCd) {
        DS_CTAC_PNT_TPTMsg tMsg = new DS_CTAC_PNT_TPTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.dsCtacPntTpCd, dsCtacPntTpCd);
        tMsg = (DS_CTAC_PNT_TPTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    private boolean existsSvcCtacTp(String svcCtacTpCd) {
        SVC_CTAC_TPTMsg tMsg = new SVC_CTAC_TPTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.svcCtacTpCd, svcCtacTpCd);
        tMsg = (SVC_CTAC_TPTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    // START 2017/09/15 M.Kidokoro [QC#21030,21032, DEL]
//    private boolean existsActvCtacPnt(SVC_MACH_CTAC_UPLD_WRKTMsg wrkTMsg) {
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("glblCmpyCd", this.glblCmpyCd);
//        paramMap.put("svcMachMstrPk", wrkTMsg.svcMachMstrPk.getValue());
//        paramMap.put("dsCtacPntTpCd", wrkTMsg.dsCtacPntTpCd.getValue());
//        BigDecimal cnt = (BigDecimal) this.ssmBatchClient.queryObject("getActvCtacPntCnt", paramMap);
//        if (cnt.compareTo(BigDecimal.ZERO) == 0) {
//            return false;
//        }
//        return true;
//    }
    // END 2017/09/15 M.Kidokoro [QC#21030,21032, DEL]

    private CTAC_PSNTMsg getCtacPsn(BigDecimal ctacPsnPk) {
        CTAC_PSNTMsg tMsg = new CTAC_PSNTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.ctacPsnPk, ctacPsnPk);
        return (CTAC_PSNTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    // START 2018/01/17 M.Naito [QC#22038, DEL]
//    private CTAC_PSNTMsg getCtacPsn(String ctacPsnFirstNm, String ctacPsnLastNm) {
//        CTAC_PSNTMsg tMsg = new CTAC_PSNTMsg();
//        tMsg.setSQLID("004");
//        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
//        tMsg.setConditionValue("ctacPsnFirstNm01", ctacPsnFirstNm);
//        tMsg.setConditionValue("ctacPsnLastNm01", ctacPsnLastNm);
//        CTAC_PSNTMsgArray tMsgArray = (CTAC_PSNTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
//        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
//            return null;
//        }
//        return tMsgArray.no(0);
//    }
    // END 2018/01/17 M.Naito [QC#22038, DEL]

    // START 2018/01/17 M.Naito [QC#22038, ADD]
    private CTAC_PSNTMsg getCtacPsnForDelyIstl(String ctacPsnFirstNm, String ctacPsnLastNm) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("ctacPsnFirstNm", ctacPsnFirstNm);
        paramMap.put("ctacPsnLastNm", ctacPsnLastNm);
        paramMap.put("ctacTpCd", CTAC_TP.DELIVERY_INSTALL);
        paramMap.put("dsAcctNum", this.curLocAcctNum);
        BigDecimal ctacPsnPk = (BigDecimal) this.ssmBatchClient.queryObject("getCtacPsnForDelyIstl", paramMap);
        if (ctacPsnPk == null) {
            return null;
        }
        return getCtacPsn(ctacPsnPk);
    }

    private BigDecimal checkSvcMachCtacPsn(SVC_MACH_CTAC_UPLD_WRKTMsg wrkTMsg, NMZC002001PMsg apiPMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcMachMstrPk", wrkTMsg.svcMachMstrPk.getValue());
        paramMap.put("dsCtacPntPk", apiPMsg.ContactPointInfoList.no(0).dsCtacPntPk.getValue());
        paramMap.put("svcCtacTpCd", wrkTMsg.svcCtacTpCd.getValue());
        paramMap.put("effFromDt", wrkTMsg.effFromDt.getValue());
        String effThruDt = wrkTMsg.effThruDt.getValue();
        if (!hasValue(effThruDt)) {
            effThruDt = MAX_DT_VAL;
        }
        paramMap.put("effThruDt", effThruDt);
        paramMap.put("maxDt", MAX_DT_VAL);
        BigDecimal svcMachCtacPsnPk = (BigDecimal) this.ssmBatchClient.queryObject("getSvcMachCtacPsn", paramMap);
        if (svcMachCtacPsnPk == null) {
            return null;
        }
        return svcMachCtacPsnPk;
    }

    private SVC_MACH_CTAC_PSNTMsg getSvcMachCtacPsn(BigDecimal svcMachCtacPsnPk) {
        SVC_MACH_CTAC_PSNTMsg tMsg = new SVC_MACH_CTAC_PSNTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.svcMachCtacPsnPk, svcMachCtacPsnPk);
        return (SVC_MACH_CTAC_PSNTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    private boolean updateSvcMachCtacPsn(SVC_MACH_CTAC_UPLD_WRKTMsg wrkTMsg, BigDecimal svcMachCtacPsnPk) {

        if (!hasValue(svcMachCtacPsnPk)) {
            return false;
        }

        SVC_MACH_CTAC_PSNTMsg svcMachCtacPsnTMsg = getSvcMachCtacPsn(svcMachCtacPsnPk);
        setValue(svcMachCtacPsnTMsg.effFromDt, wrkTMsg.effFromDt);
        setValue(svcMachCtacPsnTMsg.effThruDt, wrkTMsg.effThruDt);
        // START 2018/09/25 K.Kitachi [QC#27788, ADD]
        setValue(svcMachCtacPsnTMsg.dsPrimLocFlg, wrkTMsg.dsCtacPntActvFlg);
        // END 2018/09/25 K.Kitachi [QC#27788, ADD]

        S21ApiTBLAccessor.update(svcMachCtacPsnTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcMachCtacPsnTMsg.getReturnCode())) {
            this.messenger.addMessageForRecord(wrkTMsg.upldCsvRqstRowNum.getValue(), NSAM0031E, svcMachCtacPsnTMsg.getTableName());
            return false;
        }
        return true;
    }
    // END 2018/01/17 M.Naito [QC#22038, ADD]

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

    private String selectValue(String item1, String item2) {
        if (hasValue(item1)) {
            return item1;
        }
        return item2;
    }

    // add start 2018/07/17 QC#22583
    private String createResultMessageArg() {
        StringBuilder builder = new StringBuilder();
        if (this.insertCount > 0 || (this.updateCount == 0 && this.errCount == 0)) {
            builder.append(String.format(RESULT_MSG_INS, this.insertCount));
        }
        if (this.updateCount > 0) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(String.format(RESULT_MSG_UPD, this.updateCount));
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

    // START 2018/09/25 K.Kitachi [QC#27788, ADD]
    private List<Map<String, Object>> getPrimErrUpldWrkInfoList(BigDecimal upldCsvRqstPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("upldCsvRqstPk", upldCsvRqstPk);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getPrimErrUpldWrkInfoList", paramMap);
    }
    // END 2018/09/25 K.Kitachi [QC#27788, ADD]
}
