/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB039001;

import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.ASSET_END_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.ASSET_LAST_MOD_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.ASSET_START_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.ASSET_TRMN_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.BASE_BILL_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.BASE_BLLG_CYCLE_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.BASE_PER_CYCLE_DEAL_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.BATCH_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.CFS_AGGR_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.CFS_EQUIP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.CFS_EQUIP_CD_10;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.CFS_FLEET_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.CFS_FTR_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.CFS_LEASE_CONTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.CFS_LEASE_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.CFS_LEGAL_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.CSA_CONTR_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.EMAIL_PARAM_BATCH_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.EMAIL_PARAM_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.EMAIL_PARAM_MSG;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.ENBL_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.END_CUST_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.FIRST_XS_MTR_COPY_DPLY_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.FIRST_XS_MTR_RATE;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.FRTH_XS_MTR_COPY_DPLY_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.FRTH_XS_MTR_RATE;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.HDR_BKNG_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.HDR_END_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.HDR_START_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.HDR_TRMN_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.INIT_CPLT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.INTERFACE_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.LAST_READ_MTR_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.LAST_TEST_MTR_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.LEASE_DLR_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.MAIL_FROM_GROUP_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.MAIL_TO_GROUP_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.MSG_ITEM_COLON;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.MSG_ITEM_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.MSG_ITEM_SALES_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.MSG_ITEM_TRX_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.NSAM0031E;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.NSAM0032E;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.NSAM0033E;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.NSAM0045E;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.SCD_XS_MTR_COPY_DPLY_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.SCD_XS_MTR_RATE;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.SER_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.THIRD_XS_MTR_COPY_DPLY_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.THIRD_XS_MTR_RATE;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.TIME_FORMAT_MM_DD_YYYY_HH_MM;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.TRANSACTION_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.USG_BILL_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.USG_BLLG_CYCLE_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.USG_TP_BILL_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.USG_TRMN_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB039001.constant.NSAB039001Constant.ZZM9000E;
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
import business.db.CFS_LEASE_CONTRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 *<pre>
 *  CFS Contract Import
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/23/2016   CITS            T.Kikuhara      Create          N/A
 * 03/28/2017   Hitachi         T.Tomita        Update          QC#18073
 * 04/07/2017   Hitachi         T.Tomita        Update          QC#18247
 * 04/17/2017   Hitachi         T.Tomita        Update          QC#18247
 * 08/16/2017   Hitachi         T.Tomita        Update          QC#18761
 *</pre>
 */
public class NSAB039001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** Interface Id */
    private String intfcId = null;

    /** Transaction Id List */
    private BigDecimal[] trxIdList = null;

    /** fetchSize */
    private static final int FETCH_SIZE = 1000;

    /** Insert Count */
    private int insertCount = 0;

    /** Delete Count */
    private int deleteCount = 0;

    /** Total Commit Count */
    private int totalCommitCount = 0;

    // mod start 2017/03/28 CSA Defect#18073
    /** Error Count */
    private int errCount = 0;

//    /** Error Message */
//    private String errMsg = null;
    /** Error Message List */
    private List<String> errMsgList = null;
    // mod end 2017/03/28 CSA Defect#18073

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21TransactionTableAccessor */
    private S21TransactionTableAccessor trxAccess = null;

    /**
     * @param args parameter
     */
    public static void main(String[] args) {
        new NSAB039001().executeBatch(NSAB039001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Sales date
        salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!hasValue(this.salesDate)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_SALES_DATE });
        }

        // Get Interface Id
        this.intfcId = getInterfaceID();
        if (!hasValue(this.intfcId)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_INTERFACE_ID });
        }

        // Initialize
        this.trxAccess = new S21TransactionTableAccessor();
        this.termCd = TERM_CD.NORMAL_END;
        this.totalCommitCount = 0;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // add start 2017/03/28 CSA Defect#18073
        this.errMsgList = new ArrayList<String>();
        // add end 2017/03/28 CSA Defect#18073
    }

    @Override
    protected void mainRoutine() {
        // mod start 2017/03/28 CSA Defect#18073
        // Get Transaction ID
        this.trxIdList = this.trxAccess.getIntegrationRecord(this.intfcId);
        if (this.trxIdList == null || this.trxIdList.length == 0) {
            return;
        }

        // del start 2017/04/07 CSA Defect#18247
//        // Purge Transaction data
//        if (!purgeCfsLeaseContr()) {
//            rollback();
//            this.errCount++;
//            return;
//        }
        // del end 2017/04/07 CSA Defect#18247

        // Loop Transaction ID
        for (BigDecimal trxId : this.trxIdList) {
            this.insertCount = 0;
            this.deleteCount = 0;

            // mod start 2017/04/07 CSA Defect#18247
            // Purge Transaction data
            if (!purgeCfsLeaseContr()) {
                rollback();
                this.errCount++;
                continue;
            }
            // mod end 2017/04/07 CSA Defect#18247

            // Insert Transaction data
            if (!insertCfsLeaseContr(trxId)) {
                rollback();
                this.errCount++;
                continue;
            }

            // Update Transaction data
            if (!updateCfsLeaseContr(trxId)) {
                rollback();
                this.errCount++;
                continue;
            }

            // Delete Transaction data
            if (!deleteCfsLeaseContr(trxId)) {
                rollback();
                this.errCount++;
                continue;
            }

            // Update Process Flag
            this.trxAccess.endIntegrationProcess(this.intfcId, trxId);

            // commit Transaction data
            commit();

            this.totalCommitCount = this.totalCommitCount + this.insertCount - this.deleteCount;
        }

        sendErrorMail();
        // mod end 2017/03/28 CSA Defect#18073

    }

    @Override
    protected void termRoutine() {
        // mod start 2017/03/28 CSA Defect#18073
//        this.totalCommitCount = this.insertCount - this.deleteCount;
//        setTermState(this.termCd, this.totalCommitCount, 0);
        if (this.errCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.totalCommitCount, this.errCount);
        // mod end 2017/03/28 CSA Defect#18073
    }

    // mod start 2017/03/28 CSA Defect#18073
    /**
     * Purge Transaction data
     */
    private boolean purgeCfsLeaseContr() {

//        // Purge Process is only once execute
//        if (this.insertCount > 0) {
//            return;
//        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(GLBL_CMPY_CD, glblCmpyCd);
        List<BigDecimal> purgeList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getCfsLeaseContrPkForPurge", queryParam);
        for (BigDecimal purgePk : purgeList) {
            CFS_LEASE_CONTRTMsg tMsg = new CFS_LEASE_CONTRTMsg();
            setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(tMsg.cfsLeaseContrPk, purgePk);
            EZDTBLAccessor.remove(tMsg);
            if (tMsg.getReturnCode() != EZDTBLAccessor.RTNCD_NORMAL) {
//                errMsg = S21MessageFunc.clspGetMessage(NSAM0033E, new String[] {tMsg.getTableName() });
//                sendErrorMail();
                String xxErrMsg = S21MessageFunc.clspGetMessage(NSAM0033E, new String[] {tMsg.getTableName() });
                this.errMsgList.add(xxErrMsg);
                return false;
            }
        }
        return true;
    }
    // mod end 2017/03/28 CSA Defect#18073

    // mod start 2017/03/28 CSA Defect#18073
    /**
     * Insert Transaction data
     */
    private boolean insertCfsLeaseContr(BigDecimal trxId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(GLBL_CMPY_CD, this.glblCmpyCd);
        param.put(INTERFACE_ID, this.intfcId);
        param.put(TRANSACTION_ID, trxId);
        param.put(ENBL_FLG, ZYPConstant.FLG_ON_Y);

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getNSAI0020", param, execParam);
            rs = stmt.executeQuery();

            int cnt = 0;
            // Insert CFS_LEASE_CONTR
            while (rs.next()) {
                // Set Result Set To CFS_LEASE_CONTRTMsg
                CFS_LEASE_CONTRTMsg tMsg = createCfsLeaseContrTmsg(rs);
                EZDTBLAccessor.insert(tMsg);
                if (tMsg.getReturnCode() != EZDTBLAccessor.RTNCD_NORMAL) {
//                    errMsg = S21MessageFunc.clspGetMessage(NSAM0032E, new String[] {tMsg.getTableName() });
//                    sendErrorMail();
                    String xxErrMsg = createMsgTrxId(trxId) + S21MessageFunc.clspGetMessage(NSAM0032E, new String[] {tMsg.getTableName() });
                    this.errMsgList.add(xxErrMsg);
                    return false;
                }
                cnt++;
            }
            this.insertCount = this.insertCount + cnt;
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        return true;
    }
    // mod end 2017/03/28 CSA Defect#18073

    /**
     * Set Result Set To CFS_LEASE_CONTRTMsg
     */
    private CFS_LEASE_CONTRTMsg createCfsLeaseContrTmsg(ResultSet rs) throws SQLException {
        CFS_LEASE_CONTRTMsg tMsg = new CFS_LEASE_CONTRTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.cfsLeaseContrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CFS_LEASE_CONTR_SQ));
        setValue(tMsg.cfsLeaseNum, rs.getString(CFS_LEASE_NUM));
        setValue(tMsg.csaContrNum, rs.getString(CSA_CONTR_NUM));
        setValue(tMsg.serNum, rs.getString(SER_NUM));
        setValue(tMsg.cfsFleetFlg, rs.getString(CFS_FLEET_FLG));
        setValue(tMsg.endCustNm, rs.getString(END_CUST_NM));
        setValue(tMsg.leaseDlrCd, rs.getString(LEASE_DLR_CD));
        setValue(tMsg.baseBillFlg, rs.getString(BASE_BILL_FLG));
        setValue(tMsg.usgBillFlg, rs.getString(USG_BILL_FLG));
        setValue(tMsg.usgTpBillCd, rs.getString(USG_TP_BILL_CD));
        setValue(tMsg.baseBllgCycleCd, rs.getString(BASE_BLLG_CYCLE_CD));
        setValue(tMsg.usgBllgCycleCd, rs.getString(USG_BLLG_CYCLE_CD));
        setValue(tMsg.usgTrmnDt, rs.getString(USG_TRMN_DT));
        setValue(tMsg.basePerCycleDealAmt, rs.getBigDecimal(BASE_PER_CYCLE_DEAL_AMT));
        // Mod Start 2017/08/16 QC#187611
        setValue(tMsg.firstXsMtrCopyDplyQty, rs.getBigDecimal(FIRST_XS_MTR_COPY_DPLY_QTY));
        // Mod End 2017/08/16 QC#18761
        setValue(tMsg.firstXsMtrRate, rs.getBigDecimal(FIRST_XS_MTR_RATE));
        // Mod Start 2017/08/16 QC#187611
        setValue(tMsg.scdXsMtrCopyDplyQty, rs.getBigDecimal(SCD_XS_MTR_COPY_DPLY_QTY));
        // Mod End 2017/08/16 QC#18761
        setValue(tMsg.scdXsMtrRate, rs.getBigDecimal(SCD_XS_MTR_RATE));
        // Mod Start 2017/08/16 QC#187611
        setValue(tMsg.thirdXsMtrCopyDplyQty, rs.getBigDecimal(THIRD_XS_MTR_COPY_DPLY_QTY));
        // Mod End 2017/08/16 QC#18761
        setValue(tMsg.thirdXsMtrRate, rs.getBigDecimal(THIRD_XS_MTR_RATE));
        // Mod Start 2017/08/16 QC#187611
        setValue(tMsg.frthXsMtrCopyDplyQty, rs.getBigDecimal(FRTH_XS_MTR_COPY_DPLY_QTY));
        // Mod End 2017/08/16 QC#18761
        setValue(tMsg.frthXsMtrRate, rs.getBigDecimal(FRTH_XS_MTR_RATE));
        setValue(tMsg.assetLastModDt, rs.getString(ASSET_LAST_MOD_DT));
        setValue(tMsg.assetStartDt, rs.getString(ASSET_START_DT));
        setValue(tMsg.assetEndDt, rs.getString(ASSET_END_DT));
        setValue(tMsg.assetTrmnDt, rs.getString(ASSET_TRMN_DT));
        setValue(tMsg.hdrStartDt, rs.getString(HDR_START_DT));
        setValue(tMsg.hdrEndDt, rs.getString(HDR_END_DT));
        setValue(tMsg.hdrTrmnDt, rs.getString(HDR_TRMN_DT));
        setValue(tMsg.cratDt, salesDate);
        setValue(tMsg.cfsAggrCd, rs.getString(CFS_AGGR_CD));
        setValue(tMsg.cfsLegalFlg, rs.getString(CFS_LEGAL_FLG));
        setValue(tMsg.lastReadMtrCnt, rs.getBigDecimal(LAST_READ_MTR_CNT));
        setValue(tMsg.lastTestMtrCnt, rs.getBigDecimal(LAST_TEST_MTR_CNT));
        setValue(tMsg.cfsEquipCd, rs.getString(CFS_EQUIP_CD));
        setValue(tMsg.hdrBkngDt, rs.getString(HDR_BKNG_DT));
        setValue(tMsg.cfsFtrFlg, rs.getString(CFS_FTR_FLG));
        setValue(tMsg.initCpltDt, rs.getString(INIT_CPLT_DT));
        return tMsg;
    }

    // mod start 2017/03/28 CSA Defect#18073
    /**
     * Update Transaction data
     */
    private boolean updateCfsLeaseContr(BigDecimal trxId) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(CFS_EQUIP_CD, CFS_EQUIP_CD_10);
        List<Map<String, Object>> purgeList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getCfsLeaseContrPkForUpd", queryParam);
        if (purgeList == null || purgeList.isEmpty()) {
            return true;
        }

        BigDecimal preCfsLeaseContrPk = null;
        BigDecimal cfsLeaseContrPk = null;
        for (int i = 0; i < purgeList.size(); i++) {
            cfsLeaseContrPk = (BigDecimal) purgeList.get(i).get(CFS_LEASE_CONTR_PK);
            if (hasValue(preCfsLeaseContrPk) && preCfsLeaseContrPk.compareTo(cfsLeaseContrPk) == 0) {
                // mod start 2017/04/17 CSA Defect#18247
//                String msgItem = MSG_ITEM_SER_NUM + MSG_ITEM_COLON + (String) purgeList.get(i).get(SER_NUM);
//                String xxErrMsg = createMsgTrxId(trxId) + S21MessageFunc.clspGetMessage(NSAM0035E, new String[] {msgItem });
//                this.errMsgList.add(xxErrMsg);
//                return false;
                continue;
                // mod end 2017/04/17 CSA Defect#18247
            }

            CFS_LEASE_CONTRTMsg inMsg = new CFS_LEASE_CONTRTMsg();
            setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(inMsg.cfsLeaseContrPk, cfsLeaseContrPk);
            CFS_LEASE_CONTRTMsg tMsg = (CFS_LEASE_CONTRTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
            if (tMsg == null) {
                continue;
            }
            setValue(tMsg.cfsEquipCd, (String) purgeList.get(i).get(CFS_EQUIP_CD));
            EZDTBLAccessor.update(tMsg);
            if (tMsg.getReturnCode() != EZDTBLAccessor.RTNCD_NORMAL) {
//                errMsg = S21MessageFunc.clspGetMessage(NSAM0031E, new String[] {tMsg.getTableName() });
//                sendErrorMail();
                String xxErrMsg = createMsgTrxId(trxId) + S21MessageFunc.clspGetMessage(NSAM0031E, new String[] {tMsg.getTableName() });
                this.errMsgList.add(xxErrMsg);
                return false;
            }
            preCfsLeaseContrPk = cfsLeaseContrPk;
        }
        return true;
    }
    // mod end 2017/03/28 CSA Defect#18073

    // mod start 2017/03/28 CSA Defect#18073
    /**
     * Delete Transaction data
     */
    private boolean deleteCfsLeaseContr(BigDecimal trxId) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(CFS_EQUIP_CD, CFS_EQUIP_CD_10);
        List<BigDecimal> purgeList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getCfsLeaseContrPkForDel", queryParam);
        int cnt = 0;
        for (BigDecimal purgePk : purgeList) {
            CFS_LEASE_CONTRTMsg tMsg = new CFS_LEASE_CONTRTMsg();
            setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(tMsg.cfsLeaseContrPk, purgePk);
            EZDTBLAccessor.remove(tMsg);
            if (tMsg.getReturnCode() != EZDTBLAccessor.RTNCD_NORMAL) {
//                errMsg = S21MessageFunc.clspGetMessage(NSAM0033E, new String[] {tMsg.getTableName() });
//                sendErrorMail();
                String xxErrMsg = createMsgTrxId(trxId) + S21MessageFunc.clspGetMessage(NSAM0033E, new String[] {tMsg.getTableName() });
                this.errMsgList.add(xxErrMsg);
                return false;
            }
            cnt++;
        }
        this.deleteCount = this.deleteCount + cnt;
        return true;
    }
    // mod end 2017/03/28 CSA Defect#18073

    /**
     * Error Process
     */
    private void sendErrorMail() {

        // mod start 2017/03/28 CSA Defect#18073
        // Get Error Info
//        StringBuilder message = new StringBuilder();
//        message.append(errMsg);
//        message.append(LINE_FEED_CODE);
        if (this.errMsgList.size() == 0) {
            return;
        }
        String message = createEmailParamMsg();
        // mod end 2017/03/28 CSA Defect#18073

        // Get Mail Address From
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_FROM_GROUP_ID);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        S21MailAddress addrFrom = null;
        if (!addrFromList.isEmpty()) {
            addrFrom = addrFromList.get(0);
        }

        // Get Mail Address To
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_TO_GROUP_ID);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList.isEmpty()) {
            throw new S21AbendException(NSAM0045E, new String[] {MAIL_TO_GROUP_ID });
        }

        // Get Mail Subject and Body
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!hasValue(template.getBody())) {
            throw new S21AbendException(NSAM0045E, new String[] {MAIL_TEMPLATE_ID });
        }
        template.setTemplateParameter(EMAIL_PARAM_BATCH_ID, BATCH_ID);
        String currentTime = ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT_MM_DD_YYYY_HH_MM);
        template.setTemplateParameter(EMAIL_PARAM_DATE, currentTime);
        // mod start 2017/03/28 CSA Defect#18073
        template.setTemplateParameter(EMAIL_PARAM_MSG, message);
        // mod end 2017/03/28 CSA Defect#18073

        // Call Mail API
        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setFromAddress(addrFrom);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();

        return;

    }

    // add start 2017/03/28 CSA Defect#18073
    private String createMsgTrxId(BigDecimal trxId) {
        String rtnVal = "";
        if (!hasValue(trxId)) {
            return rtnVal;
        }
        return MSG_ITEM_TRX_ID + MSG_ITEM_COLON + trxId.toPlainString() + MSG_ITEM_COLON;
    }

    private String createEmailParamMsg() {
        StringBuilder rtnMsg = new StringBuilder();
        for (String msg : this.errMsgList) {
            if (!hasValue(msg)) {
                continue;
            }

            if (hasValue(rtnMsg.toString())) {
                rtnMsg.append(S21Mail.LINE_SEPARATOR);
            }
            rtnMsg.append(msg);
        }
        return rtnMsg.toString();
    }
    // add end 2017/03/28 CSA Defect#18073
}
