package com.canon.cusa.s21.batch.NSA.NSAB085001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.DS_WIN_DAYSTMsg;
import business.db.MTR_READ_NTFYTMsg;
import business.db.MTR_READ_NTFY_DTLTMsg;

import com.canon.cusa.s21.batch.NSA.NSAB085001.constant.NSAB085001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

/**
 * <pre>
 *  Meter Reading Notification Batch Extract
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/11/10   Hitachi         Y.Nagasawa      Create          QC#61756
 */

public class NSAB085001 extends S21BatchMain {
    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSAB0850";

    /** Batch ID */
    public static final String BATCH_ID = BIZ_APP_ID + "01";

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SsmLLClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** Total InsertCount Count */
    private int totalinsertCountCount = 0;

    /** Total update Count */
    private int totalUpdateCount = 0;

    /** TotalErrCount */
    private int totalErrCount = 0;

    /** TermCd */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Tmp Meter Reading Notification PK */
    private BigDecimal TMP_MTR_READ_NTFY_PK = null;

    @Override
    protected void initRoutine() {
        // Initialize
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSAB085001Constant.ZZM9000E, new String[] {"Global Company Code" });
        }
        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd, BATCH_ID);
        if (!hasValue(this.salesDate)) {
            throw new S21AbendException(NSAB085001Constant.ZZM9000E, new String[] {"Sales Date" });
        }
    }

    @Override
    protected void mainRoutine() {
        doProcess();
        commit();
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.totalinsertCountCount + this.totalUpdateCount, this.totalErrCount);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new NSAB085001().executeBatch(NSAB085001.class.getSimpleName());
    }

    private void doProcess(){
         createNewMtrReadNotify();
         manMailUpdate();
    }

    private void createNewMtrReadNotify() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = getMailSendingTarget();
            rs = stmt.executeQuery();
            List<MTR_READ_NTFYTMsg> mtrReadNtfyTMsgList = new ArrayList<MTR_READ_NTFYTMsg>();
            List<MTR_READ_NTFY_DTLTMsg> mtrReadNtfyDtlTMsgList = new ArrayList<MTR_READ_NTFY_DTLTMsg>();
            BigDecimal meterReadingNotificationPK = null;
            boolean firstFlg = true;
            while (rs.next()) {
                if (firstFlg) {
                    mtrReadNtfyTMsgList.add(createMtrReadNtfy(rs));
                    meterReadingNotificationPK = mtrReadNtfyTMsgList.get(0).mtrReadNtfyPk.getValue();
                    mtrReadNtfyDtlTMsgList.add(createMtrReadNtfyDtl(rs, meterReadingNotificationPK));
                    firstFlg = false;
                } else {
                    meterReadingNotificationPK = relationCheck(rs, mtrReadNtfyTMsgList);
                    if (hasValue(meterReadingNotificationPK)) {
                        mtrReadNtfyDtlTMsgList.add(createMtrReadNtfyDtl(rs, meterReadingNotificationPK));
                    } else {
                        mtrReadNtfyTMsgList.add(createMtrReadNtfy(rs));
                        mtrReadNtfyDtlTMsgList.add(createMtrReadNtfyDtl(rs, this.TMP_MTR_READ_NTFY_PK));
                    }
                }
            }
            insertMtrReadNtfy(mtrReadNtfyTMsgList, mtrReadNtfyDtlTMsgList);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void manMailUpdate() {
        List<Map<String, Object>> manMailUpdateList = getMeterReadingNotification();
        for (Map<String, Object> mtrReadNtfy : manMailUpdateList) {
            MTR_READ_NTFYTMsg updateTMsg = new MTR_READ_NTFYTMsg();
            String dsBllgSchdSts = (String) mtrReadNtfy.get("DS_BLLG_SCHD_STS_CD");
            ZYPEZDItemValueSetter.setValue(updateTMsg.mtrReadNtfyPk, (BigDecimal) mtrReadNtfy.get("MTR_READ_NTFY_PK"));
            ZYPEZDItemValueSetter.setValue(updateTMsg.glblCmpyCd, this.glblCmpyCd);
            updateTMsg =(MTR_READ_NTFYTMsg)  EZDTBLAccessor.findByKeyForUpdate(updateTMsg);
            if (DS_BLLG_SCHD_STS.OPEN.equals(dsBllgSchdSts)) {
                ZYPEZDItemValueSetter.setValue(updateTMsg.mtrNtfyStsCd, NSAB085001Constant.MTR_NTFY_STS_WAITING_FOR_SEND);
            } else {
                ZYPEZDItemValueSetter.setValue(updateTMsg.mtrNtfyStsCd, NSAB085001Constant.MTR_NTFY_STS_ERROR);
                ZYPEZDItemValueSetter.setValue(updateTMsg.mtrNtfyErrMsgTxt, S21MessageFunc.clspGetMessage(NSAB085001Constant.NSAM0783E));
                ZYPEZDItemValueSetter.setValue(updateTMsg.manSendFlg, ZYPConstant.FLG_OFF_N);
            }
            EZDTBLAccessor.update(updateTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
                rollback();
                this.totalErrCount++;
                throw new S21AbendException(NSAB085001Constant.NSAM0032E, new String[] { "MTR_READ_NTFY", updateTMsg.getReturnCode() });
            } else {
                if (NSAB085001Constant.MTR_NTFY_STS_ERROR.equals(updateTMsg.mtrNtfyStsCd.getValue())) {
                    this.totalErrCount++;
                } else {
                    this.totalUpdateCount++;
                }
            }
        }
    }

    /**
     * @return PreparedStatement
     */
    private PreparedStatement getMailSendingTarget() throws SQLException{
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dateFormat", ZYPDateUtil.TYPE_YYYYMMDD);
        paramMap.put("defBefDays", NSAB085001Constant.DEF_WINDOW_BEF_DAYS);
        BigDecimal mtrReadWinMlyDaysAot = BigDecimal.ZERO;
        BigDecimal mtrReadWinOthDaysAot = BigDecimal.ZERO;
        DS_WIN_DAYSTMsg dsWinDaysTMsg = new DS_WIN_DAYSTMsg();
        ZYPEZDItemValueSetter.setValue(dsWinDaysTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsWinDaysTMsg.dsWinDaysTrgtPerTxt, "*");
        ZYPEZDItemValueSetter.setValue(dsWinDaysTMsg.svcLineBizCd, "*");
        dsWinDaysTMsg = (DS_WIN_DAYSTMsg) S21ApiTBLAccessor.findByKey(dsWinDaysTMsg);
        if (dsWinDaysTMsg != null) {
            mtrReadWinMlyDaysAot = dsWinDaysTMsg.mtrReadWinMlyDaysAot.getValue();
            mtrReadWinOthDaysAot = dsWinDaysTMsg.mtrReadWinOthDaysAot.getValue();
        }
        paramMap.put("mtrReadWinMlyDaysAot", mtrReadWinMlyDaysAot);
        paramMap.put("mtrReadWinOthDaysAot", mtrReadWinOthDaysAot);
        paramMap.put("months", BLLG_CYCLE_UOM.MONTHS);
        paramMap.put("salesDate", this.salesDate);
        paramMap.put("mtrReadMeth", MTR_READ_METH.EMAIL);
        paramMap.put("mtreReadAvalFlg", ZYPConstant.FLG_ON_Y);
        paramMap.put("mtrEntryCptlFlg", ZYPConstant.FLG_OFF_N);
        paramMap.put("svcMachMstrStsCd", SVC_MACH_MSTR_STS.REMOVED);
        paramMap.put("skipReconvTpCd", SKIP_RECOV_TP.SKIP);
        paramMap.put("svcCtacTpCd", SVC_CTAC_TP.METER_READ);
        paramMap.put("dsCtacTpEmail", DS_CTAC_PNT_TP.EMAIL_WORK);
        paramMap.put("dsCtacPntActvFlg", ZYPConstant.FLG_ON_Y);
        paramMap.put("phone01", DS_CTAC_PNT_TP.PHONE_MOBILE);
        paramMap.put("phone02", DS_CTAC_PNT_TP.PHONE_WORK);
        paramMap.put("phone03", DS_CTAC_PNT_TP.PHONE_ASSISTANT);
        paramMap.put("fax", DS_CTAC_PNT_TP.FAX_WORK);
        
        return this.ssmLLClient.createPreparedStatement("getMailSendingTarget", paramMap, getExecPrm());
    }

    /**
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getMeterReadingNotification() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("manualSendFlag", ZYPConstant.FLG_ON_Y);
        paramMap.put("usgChrgFlg", ZYPConstant.FLG_ON_Y);
        return ssmBatchClient.queryObjectList("getMeterReadingNotification", paramMap);
    }

    /**
     * @param ResultSet rs
     * @return MTR_READ_NTFYTMsg
     */
    private MTR_READ_NTFYTMsg createMtrReadNtfy(ResultSet rs) throws SQLException{
        MTR_READ_NTFYTMsg mtrReadNtfyTMsg = new MTR_READ_NTFYTMsg();
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyTMsg.mtrReadNtfyPk, ZYPOracleSeqAccessor.getNumberBigDecimal("MTR_READ_NTFY_SQ"));
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyTMsg.ctacPsnFirstNm, rs.getString("CTAC_PSN_FIRST_NM"));
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyTMsg.ctacPsnMidNm, rs.getString("CTAC_PSN_MID_NM"));
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyTMsg.ctacPsnLastNm, rs.getString("CTAC_PSN_LAST_NM"));
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyTMsg.ctacPsnEmlAddr, rs.getString("CTAC_PSN_EML_ADDR"));
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyTMsg.ctacPsnTelNum, rs.getString("CTAC_PSN_TEL_NUM"));
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyTMsg.ctacPsnFaxNum, rs.getString("CTAC_PSN_FAX_NUM"));
        if (!hasValue(mtrReadNtfyTMsg.ctacPsnEmlAddr)) {
            ZYPEZDItemValueSetter.setValue(mtrReadNtfyTMsg.mtrNtfyStsCd, NSAB085001Constant.MTR_NTFY_STS_ERROR);
            ZYPEZDItemValueSetter.setValue(mtrReadNtfyTMsg.mtrNtfyErrMsgTxt, S21MessageFunc.clspGetMessage(NSAB085001Constant.ZZM9000E, new String[] { "Email Address" }));
        } else {
            ZYPEZDItemValueSetter.setValue(mtrReadNtfyTMsg.mtrNtfyStsCd, NSAB085001Constant.MTR_NTFY_STS_WAITING_FOR_SEND);
            ZYPEZDItemValueSetter.setValue(mtrReadNtfyTMsg.mtrNtfyErrMsgTxt, "");
        }
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyTMsg.manSendFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyTMsg.mtrReadNtfySendTs, "");
        this.TMP_MTR_READ_NTFY_PK = mtrReadNtfyTMsg.mtrReadNtfyPk.getValue();
        return mtrReadNtfyTMsg;
    }

    /**
     * @param ResultSet rs
     * @param BigDecimal meterReadingNotificationPK
     * @return MTR_READ_NTFYTMsg
     */
    private MTR_READ_NTFY_DTLTMsg createMtrReadNtfyDtl (ResultSet rs, BigDecimal meterReadingNotificationPK) throws SQLException{
        MTR_READ_NTFY_DTLTMsg mtrReadNtfyDtlTMsg = new MTR_READ_NTFY_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyDtlTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyDtlTMsg.mtrReadNtfyDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal("MTR_READ_NTFY_DTL_SQ"));
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyDtlTMsg.mtrReadNtfyPk, meterReadingNotificationPK);
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyDtlTMsg.dsContrPk, rs.getBigDecimal("DS_CONTR_PK"));
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyDtlTMsg.dsContrNum, rs.getString("DS_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyDtlTMsg.dsContrDtlPk, rs.getBigDecimal("DS_CONTR_DTL_PK"));
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyDtlTMsg.svcMachMstrPk, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyDtlTMsg.serNum, rs.getString("SER_NUM"));
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyDtlTMsg.svcConfigMstrPk, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyDtlTMsg.mdlId, rs.getBigDecimal("MDL_ID"));
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyDtlTMsg.t_MdlNm, rs.getString("T_MDL_NM"));
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyDtlTMsg.svcPhysMtrReadPk, rs.getBigDecimal("SVC_PHYS_MTR_READ_PK"));
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyDtlTMsg.mtrLbCd, rs.getString("MTR_LB_CD"));
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyDtlTMsg.mtrLbDescTxt, rs.getString("MTR_LB_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyDtlTMsg.readMtrCnt, rs.getBigDecimal("READ_MTR_CNT"));
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyDtlTMsg.dsContrBllgSchdPk, rs.getBigDecimal("DS_CONTR_BLLG_SCHD_PK"));
        ZYPEZDItemValueSetter.setValue(mtrReadNtfyDtlTMsg.bllgSchdThruDt, rs.getString("BLLG_SCHD_THRU_DT"));
        return mtrReadNtfyDtlTMsg;
    }

    /**
     * @param ResultSet rs
     * @param List<MTR_READ_NTFY_DTLTMsg> mtrReadNtfyDtlTMsgList
     * @return BigDecimal meterReadingNotificationPK
     */
    private BigDecimal relationCheck(ResultSet rs,  List<MTR_READ_NTFYTMsg> mtrReadNtfyTMsgList) throws SQLException {
        BigDecimal meterReadingNotificationPK = null;
        for (int i = 0; i < mtrReadNtfyTMsgList.size(); i++) {
            if (mtrReadNtfyTMsgList.get(i).ctacPsnEmlAddr.getValue().equals(rs.getString("CTAC_PSN_EML_ADDR"))) {
                meterReadingNotificationPK = mtrReadNtfyTMsgList.get(i).mtrReadNtfyPk.getValue();
                break;
            }
        }
        return meterReadingNotificationPK;
    }

    /**
     * @param List<MTR_READ_NTFYTMsg> mtrReadNtfyTMsgList
     * @param List<MTR_READ_NTFY_DTLTMsg> mtrReadNtfyDtlTMsgList
     */
    private void insertMtrReadNtfy(List<MTR_READ_NTFYTMsg> mtrReadNtfyTMsgList, List<MTR_READ_NTFY_DTLTMsg> mtrReadNtfyDtlTMsgList){
        // MTR_READ_NTFY
        for (MTR_READ_NTFYTMsg mtrReadNtfyTMsg : mtrReadNtfyTMsgList) {
            EZDTBLAccessor.insert(mtrReadNtfyTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mtrReadNtfyTMsg.getReturnCode())) {
                rollback();
                this.totalErrCount++;
                throw new S21AbendException(NSAB085001Constant.NSAM0032E, new String[] { "MTR_READ_NTFY", mtrReadNtfyTMsg.getReturnCode() });
            } else {
                if (NSAB085001Constant.MTR_NTFY_STS_ERROR.equals(mtrReadNtfyTMsg.mtrNtfyStsCd.getValue())) {
                    this.totalErrCount++;
                } else {
                    this.totalinsertCountCount++;
                }
            }
        }
        // MTR_READ_NTFY_DTL
        for (MTR_READ_NTFY_DTLTMsg mtrReadNtfyDtlTMsg : mtrReadNtfyDtlTMsgList) {
            EZDTBLAccessor.insert(mtrReadNtfyDtlTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mtrReadNtfyDtlTMsg.getReturnCode())) {
                rollback();
                this.totalErrCount++;
                throw new S21AbendException(NSAB085001Constant.NSAM0032E, new String[] { "MTR_READ_NTFY_DTL", mtrReadNtfyDtlTMsg.getReturnCode() });
            } else {
                this.totalinsertCountCount++;
            }
        }
    }

    private S21SsmExecutionParameter getExecPrm() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(NSAB085001Constant.MAX_FETCH_SIZE);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }
}
