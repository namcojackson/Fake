/*
 * <pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB271001;

import static com.canon.cusa.s21.batch.NWA.NWAB271001.constant.NWAB271001Constant.BEFORE_1HOUR;
import static com.canon.cusa.s21.batch.NWA.NWAB271001.constant.NWAB271001Constant.DATE_FORMAT;
import static com.canon.cusa.s21.batch.NWA.NWAB271001.constant.NWAB271001Constant.FETCH_SIZE_MAX;
import static com.canon.cusa.s21.batch.NWA.NWAB271001.constant.NWAB271001Constant.BATCH_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB271001.constant.NWAB271001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NWA.NWAB271001.constant.NWAB271001Constant.NWAM8468E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.DS_BIZ_PROC_LOGTMsgArray;
import business.db.SO_TO_VND_NTFY_CTRLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;

/**
 * <pre>
 * Shipping Order Notification to Vendor Extract Batch
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/09/07   Hitachi         H.Watanabe      Create          QC#60256
 *</pre>
 */
public class NWAB271001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** System Time */
    private String sysTs = null;

    // -- Other Internal Variable ---------------
    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Execute Param */
    S21SsmExecutionParameter excParam = null;

    /** Term code */
    private TERM_CD termCd = null;

    /** total search count */
    private int searchCnt = 0;

    /** Task success count */
    private int infoSccessCnt = 0;

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWAB271001().executeBatch(NWAB271001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Initialization
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());
        termCd = TERM_CD.NORMAL_END;

        // Get the Global Company Code.
        // If an error occurs, throw Exception.
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Global Company Code" });
        }

        this.sysTs = ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT);

        excParam = new S21SsmExecutionParameter();
        excParam.setFetchSize(FETCH_SIZE_MAX);
        excParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        excParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        excParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
    }

    @Override
    protected void mainRoutine() {
        doProcess();
    }

    @Override
    protected void termRoutine() {
        // Set term code and total count.
        setTermState(this.termCd, infoSccessCnt, searchCnt - infoSccessCnt, searchCnt);
    }

    /**
     * doProcess
     */
    private void doProcess() {
        PreparedStatement sippingOrderPs = null;
        ResultSet sippingOrderRs = null;

        try {
            DS_BIZ_PROC_LOGTMsg dsBizProcLogTMsg = getParamForDsBizProcLog();

            sippingOrderPs = ssmLLClient.createPreparedStatement("getTargetSippingOrder", setParamForSippingOrder(dsBizProcLogTMsg), excParam);
            sippingOrderRs = sippingOrderPs.executeQuery();

            while (sippingOrderRs.next()) {
                this.searchCnt++;
                SO_TO_VND_NTFY_CTRLTMsg soToVndNtfyCtrlTMsg = new SO_TO_VND_NTFY_CTRLTMsg();
                setValue(soToVndNtfyCtrlTMsg.glblCmpyCd, this.glblCmpyCd);
                setValue(soToVndNtfyCtrlTMsg.soNum, sippingOrderRs.getString("SO_NUM"));
                setValue(soToVndNtfyCtrlTMsg.whCd, sippingOrderRs.getString("WH_CD"));
                setValue(soToVndNtfyCtrlTMsg.ntfyProcStsCd, PROC_STS.IN_COMPLETED);
                setValue(soToVndNtfyCtrlTMsg.ntfyCratTs, this.sysTs);
                S21FastTBLAccessor.insert(soToVndNtfyCtrlTMsg);
                this.infoSccessCnt++;
            }

            setValue(dsBizProcLogTMsg.dsBizLastProcTs, this.sysTs);
            S21FastTBLAccessor.update(dsBizProcLogTMsg);
            commit();
        } catch (SQLException e) {
            this.termCd = TERM_CD.ABNORMAL_END;
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(sippingOrderPs, sippingOrderRs);
        }

    }

    private DS_BIZ_PROC_LOGTMsg getParamForDsBizProcLog() {
        DS_BIZ_PROC_LOGTMsg dsBizProcLogTMsg = new DS_BIZ_PROC_LOGTMsg();
        DS_BIZ_PROC_LOGTMsg insDsBizProcLogTMsg = new DS_BIZ_PROC_LOGTMsg();
        dsBizProcLogTMsg.setSQLID("001");
        dsBizProcLogTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        dsBizProcLogTMsg.setConditionValue("procPgmId01", BATCH_ID);
        DS_BIZ_PROC_LOGTMsgArray dsBizProcLogTMsgArray = (DS_BIZ_PROC_LOGTMsgArray) EZDTBLAccessor.findByCondition(dsBizProcLogTMsg);
        if (dsBizProcLogTMsgArray == null || dsBizProcLogTMsgArray.getValidCount() == 0) {
            setValue(insDsBizProcLogTMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(insDsBizProcLogTMsg.dsBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BIZ_PROC_LOG_SQ));
            setValue(insDsBizProcLogTMsg.procPgmId, BATCH_ID);
            setValue(insDsBizProcLogTMsg.dsBizProcFlg, "Y");
            String tmpTs = getBeforeTime();
            setValue(insDsBizProcLogTMsg.dsBizLastProcTs, tmpTs);
            setValue(insDsBizProcLogTMsg.dsBizLastUpdTs, tmpTs);
            EZDTBLAccessor.insert(insDsBizProcLogTMsg);
            return insDsBizProcLogTMsg;
        } else {
            insDsBizProcLogTMsg = dsBizProcLogTMsgArray.no(0);
            return insDsBizProcLogTMsg;
        }
    }

    private Map<String, Object> setParamForSippingOrder(DS_BIZ_PROC_LOGTMsg dsBizProcLogTMsg) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsBizLastProcTs", dsBizProcLogTMsg.dsBizLastProcTs.getValue());
        paramMap.put("nowTime", this.sysTs);
        return paramMap;
    }

    private String getBeforeTime() {
        SimpleDateFormat parser = new SimpleDateFormat(DATE_FORMAT);
        parser.setLenient(false);
        Date inParamDate = null;
        try {
            inParamDate = parser.parse(this.sysTs);
        } catch (ParseException e) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NWAM8468E);
        }
        Calendar tmpCalendar = Calendar.getInstance();
        tmpCalendar.setTime(inParamDate);
        tmpCalendar.add(Calendar.HOUR, BEFORE_1HOUR);
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Calendar cal = Calendar.getInstance();
        cal.setTime(tmpCalendar.getTime());
        return format.format(cal.getTime());
    }
}
