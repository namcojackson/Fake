package com.canon.cusa.s21.batch.NWA.NWAB248001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.dbcommon.EZDTBLAccessor;

import business.db.DS_CONTRTMsg;
import business.db.DS_CONTRTMsgArray;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.PRC_CATGTMsg;
import business.db.PRC_COND_TPTMsg;
import business.db.SCHD_AGMTTMsg;
import business.db.SCHD_AGMT_ADJ_CONTRTMsg;
import business.db.SCHD_AGMT_ADJ_CONTR_RSLTTMsg;
import business.db.SCHD_AGMT_CTAC_PSNTMsg;
import business.db.SCHD_AGMT_CTAC_PSNTMsgArray;
import business.db.SCHD_AGMT_LINETMsg;
import business.db.SCHD_AGMT_LINETMsgArray;
import business.db.SCHD_AGMT_PLNTMsg;
import business.db.SCHD_AGMT_PRC_CALC_BASETMsg;
import business.db.SCHD_AGMT_PRC_CALC_BASETMsgArray;
import business.db.SCHD_AGMT_SLS_CRTMsg;
import business.db.SCHD_AGMT_SLS_CRTMsgArray;
import business.db.SHPG_INTVLTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NMZC002001PMsg;
import business.parts.NMZC002001_ContactPointInfoListPMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC157004PMsg;
import business.parts.NWZC171001PMsg;
import business.parts.NWZC171001_CPMsg;
import business.parts.NWZC171001_DPMsg;
import business.parts.NWZC171001_EPMsg;
import business.parts.NWZC171002PMsg;
import business.parts.NWZC171003PMsg;
import business.parts.NWZC171003PMsgArray;

import com.canon.cusa.s21.api.NMZ.NMZC002001.NMZC002001;
import com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.api.NWZ.NWZC171001.NWZC171001;
import com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import static com.canon.cusa.s21.batch.NWA.NWAB248001.constant.NWAB248001Constant.*;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_CUST_REF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_AGMT_ADJ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_AGMT_ADJ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_AGMT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Scheduling Agreement Adjustment Contract Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/01/2022   Hitachi         D.Yoshida       Create          QC#59973
 * </pre>
 */
public class NWAB248001 extends S21BatchMain {

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Client for prepared statement */
    private S21SsmLowLevelCodingClient ssmLlcClnt = null;

    /** Execute Param */
    private S21SsmExecutionParameter excParam = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** total search count */
    private int searchCnt = 0;

    /** Task success count */
    private int infoSccessCnt = 0;

    /** Term Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Error Message */
    private Map<String, Map<BigDecimal, String>> errMap;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWAB248001().executeBatch();
    }

    @Override
    protected void initRoutine() {

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLlcClnt = S21SsmLowLevelCodingClient.getClient(getClass());

        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NWZM0011E);
        }

        // Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BATCH_ID);
        if (!hasValue(this.slsDt)) {
            throw new S21AbendException(NWZM0482E);
        }

        excParam = new S21SsmExecutionParameter();
        excParam.setFetchSize(1000);
        excParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        excParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        excParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        errMap = new HashMap<String, Map<BigDecimal, String>>();
    }

    @Override
    protected void mainRoutine() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean successFlg = false;
        BigDecimal curSchdAgmtAdjContrPk = BigDecimal.ZERO;
        BigDecimal preSchdAgmtAdjContrPk = BigDecimal.ZERO;

        try {
            // Search Target Data
            ps = getSchdAgmtAdjTgt();
            rs = ps.executeQuery();
            SCHD_AGMT_ADJ_CONTRTMsg schdAgmtAdjContr = null;

            while (rs.next()) {
                searchCnt++;
                // Get PK
                curSchdAgmtAdjContrPk = rs.getBigDecimal("SCHD_AGMT_ADJ_CONTR_PK");
                if (!rs.isFirst() && curSchdAgmtAdjContrPk.compareTo(preSchdAgmtAdjContrPk) != 0) {
                    if (!successFlg) {
                        rollback();
                        processResultRegist(schdAgmtAdjContr);
                        commit();
                    } else {
                        processResultRegist(schdAgmtAdjContr);
                        commit();
                    }
                    successFlg = false;
                } else {
                    if (successFlg) {
                        preSchdAgmtAdjContrPk = curSchdAgmtAdjContrPk;
                        continue;
                    }
                }
                // Lock Table
                schdAgmtAdjContr = getSchdAgmtAdjContrForUpdate(curSchdAgmtAdjContrPk);
                if (schdAgmtAdjContr == null) {
                    continue;
                }
                // Main Process
                successFlg = doProcess(schdAgmtAdjContr);
            }
            if (!successFlg) {
                rollback();
                processResultRegist(schdAgmtAdjContr);
                commit();
            } else {
                processResultRegist(schdAgmtAdjContr);
                commit();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.infoSccessCnt, searchCnt - infoSccessCnt, searchCnt);
    }

    private PreparedStatement getSchdAgmtAdjTgt() throws SQLException {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("stsUnprocessed", SCHD_AGMT_ADJ_STS.UNPROCESSED);
        return ssmLlcClnt.createPreparedStatement("getSchdAgmtAdjTgt", ssmParam);
    }

    private SCHD_AGMT_ADJ_CONTRTMsg getSchdAgmtAdjContrForUpdate(BigDecimal schdAgmtAdjContrPk) {
        // Lock Table
        SCHD_AGMT_ADJ_CONTRTMsg schdAgmtAdjContr = new SCHD_AGMT_ADJ_CONTRTMsg();
        setValue(schdAgmtAdjContr.glblCmpyCd, this.glblCmpyCd);
        setValue(schdAgmtAdjContr.schdAgmtAdjContrPk, schdAgmtAdjContrPk);
        return (SCHD_AGMT_ADJ_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(schdAgmtAdjContr);
    }

    private boolean doProcess(SCHD_AGMT_ADJ_CONTRTMsg schdAgmtAdjContr) {
        // process by mode
        if (S21StringUtil.isEquals(SCHD_AGMT_ADJ_TP.RENEWAL, schdAgmtAdjContr.schdAgmtAdjTpCd.getValue())) {
            return doProcessRenewal(schdAgmtAdjContr);
        } else if (S21StringUtil.isEquals(SCHD_AGMT_ADJ_TP.TERMINATE, schdAgmtAdjContr.schdAgmtAdjTpCd.getValue())) {
            return doProcessTerminate(schdAgmtAdjContr);
        } else if (S21StringUtil.isEquals(SCHD_AGMT_ADJ_TP.EXCHANGE, schdAgmtAdjContr.schdAgmtAdjTpCd.getValue())) {
            return doProcessServiceExchange(schdAgmtAdjContr);
        }
        return false;
    }

    private boolean doProcessRenewal(SCHD_AGMT_ADJ_CONTRTMsg schdAgmtAdjContr) {
        // Search Schedule Agreement Info
        String schdAgmtNum = schdAgmtAdjContr.schdAgmtNum.getValue();
        NWZC171001PMsg schdAgmtApiPMsg = getScheduleUpdateApiMsg(schdAgmtNum);
        if (schdAgmtApiPMsg == null) {
            if (!this.errMap.containsKey(schdAgmtNum)) {
                setErrMsg(schdAgmtNum, NWAM0722E, new String[] {"Schedule Agreement#" + schdAgmtNum });
            }
            return false;
        }
        // Create New Schedule
        if (!createNewScheduleForRenewal(schdAgmtApiPMsg, schdAgmtAdjContr)) {
            return false;
        }
        // Change Valid Through Date to Contract Through Date
        if (schdAgmtAdjContr.origVldThruDt.getValue().compareTo(schdAgmtAdjContr.chngVldThruDt.getValue()) < 0) {
            setValue(schdAgmtApiPMsg.schdAgmtVldThruDt, schdAgmtAdjContr.chngVldThruDt);
        }
        // Pricing
        if (!calcPriceAmt(schdAgmtApiPMsg)) {
            return false;
        }
        // Call Contact Update API
        if (!callContactUpdateApi(schdAgmtApiPMsg)) {
            return false;
        }
        // Call Supply Quote Update API
        if (!callSchdAgmtUpdateApi(schdAgmtApiPMsg)) {
            return false;
        }
        return true;
    }

    private boolean doProcessTerminate(SCHD_AGMT_ADJ_CONTRTMsg schdAgmtAdjContr) {
        // Search Schedule Agreement Info
        String schdAgmtNum = schdAgmtAdjContr.schdAgmtNum.getValue();
        NWZC171001PMsg schdAgmtApiPMsg = getScheduleUpdateApiMsg(schdAgmtNum);
        if (schdAgmtApiPMsg == null) {
            if (!this.errMap.containsKey(schdAgmtNum)) {
                setErrMsg(schdAgmtNum, NWAM0722E, new String[] {"Schedule Agreement#" + schdAgmtNum });
            }
            return false;
        }
        // Change Valid To Date
        setValue(schdAgmtApiPMsg.schdAgmtVldThruDt, schdAgmtAdjContr.chngVldThruDt);
        // Cancel Future Schedule Line
        scheduleCancel(schdAgmtApiPMsg);
        // Pricing
        if (!calcPriceAmt(schdAgmtApiPMsg)) {
            return false;
        }
        // Call Contact Update API
        if (!callContactUpdateApi(schdAgmtApiPMsg)) {
            return false;
        }
        // Call Supply Quote Update API
        if (!callSchdAgmtUpdateApi(schdAgmtApiPMsg)) {
            return false;
        }
        return true;
    }

    private boolean doProcessServiceExchange(SCHD_AGMT_ADJ_CONTRTMsg schdAgmtAdjContr) {
        // Search Schedule Agreement Info
        String schdAgmtNum = schdAgmtAdjContr.schdAgmtNum.getValue();
        NWZC171001PMsg schdAgmtApiPMsg = getScheduleUpdateApiMsg(schdAgmtNum);
        if (schdAgmtApiPMsg == null) {
            if (!this.errMap.containsKey(schdAgmtNum)) {
                setErrMsg(schdAgmtNum, NWAM0722E, new String[] {"Schedule Agreement#" + schdAgmtNum });
            }
            return false;
        }
        // Change Serial#
        BigDecimal exchSvcMachMstrPk = schdAgmtAdjContr.exchSvcMachMstrPk.getValue();
        if (!hasValue(exchSvcMachMstrPk)) {
            return false;
        }
        SVC_MACH_MSTRTMsg svcMachMstr = getSvcMachMstr(exchSvcMachMstrPk);
        if (svcMachMstr == null) {
            return false;
        }
        setValue(schdAgmtApiPMsg.serNum, svcMachMstr.serNum.getValue());
        // Change Customer Info
        if (!setCustInfo(schdAgmtApiPMsg, schdAgmtAdjContr)) {
            return false;
        }
        // Pricing
        if (!calcPriceAmt(schdAgmtApiPMsg)) {
            return false;
        }
        // Call Contact Update API
        if (!callContactUpdateApi(schdAgmtApiPMsg)) {
            return false;
        }
        // Call Supply Quote Update API
        if (!callSchdAgmtUpdateApi(schdAgmtApiPMsg)) {
            return false;
        }
        return true;
    }

    private boolean callContactUpdateApi(NWZC171001PMsg schdAgmtApiPMsg) {
        for (int i = 0; i < schdAgmtApiPMsg.D.getValidCount(); i++) {
            NWZC171001_DPMsg ctacMsg = schdAgmtApiPMsg.D.no(i);
            NMZC002001PMsg nmzc002001pMsg = createContactUpdateApiPMsg(schdAgmtApiPMsg, ctacMsg);
            new NMZC002001().execute(nmzc002001pMsg, ONBATCH_TYPE.BATCH);

            List<String> errMsgList = S21ApiUtil.getXxMsgIdList(nmzc002001pMsg);

            if (!errMsgList.isEmpty()) {
                for (String xxMsgId : errMsgList) {
                    if (xxMsgId.endsWith("E")) {
                        setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), xxMsgId, null);
                        return false;
                    }
                }
            }
            setValue(schdAgmtApiPMsg.D.no(i).ctacPsnPk, nmzc002001pMsg.ctacPsnPk);
        }
        return true;
    }

    private NMZC002001PMsg createContactUpdateApiPMsg(NWZC171001PMsg schdAgmtApiPMsg, NWZC171001_DPMsg ctacMsg) {

        NMZC002001PMsg nmzc002001pMsg = new NMZC002001PMsg();

        String locNum = getLocationNumber(schdAgmtApiPMsg, ctacMsg);

        setValue(nmzc002001pMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
        setValue(nmzc002001pMsg.glblCmpyCd, this.glblCmpyCd);

        setValue(nmzc002001pMsg.locNum, locNum);
        setValue(nmzc002001pMsg.slsDt, this.slsDt);
        setValue(nmzc002001pMsg.effFromDt, this.slsDt);
        setValue(nmzc002001pMsg.ctacPsnFirstNm, ctacMsg.ctacPsnFirstNm);
        setValue(nmzc002001pMsg.ctacPsnLastNm, ctacMsg.ctacPsnLastNm);
        setValue(nmzc002001pMsg.ctacTpCd, ctacMsg.ctacPsnTpCd);
        setValue(nmzc002001pMsg.ctacPsnCmntTxt, ctacMsg.ctacPsnCmntTxt);
        setValue(nmzc002001pMsg.ctacPsnActvFlg, FLG_ON_Y);

        NMZC002001_ContactPointInfoListPMsg contactPntPMsg = null;
        int contactVldCnt = 0;

        if (hasValue(ctacMsg.ctacPsnTelNum)) {
            contactPntPMsg = nmzc002001pMsg.ContactPointInfoList.no(contactVldCnt);
            setValue(contactPntPMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
            setValue(contactPntPMsg.dsCtacPntTpCd, DS_CTAC_PNT_TP.PHONE_WORK);
            setValue(contactPntPMsg.dsCtacPntValTxt, ctacMsg.ctacPsnTelNum);
            setValue(contactPntPMsg.dsCtacPsnExtnNum, ctacMsg.ctacPsnExtnNum);
            setValue(contactPntPMsg.dsOpsOutFlg, FLG_OFF_N);
            setValue(contactPntPMsg.dsCtacPntActvFlg, FLG_ON_Y);
            setValue(contactPntPMsg.updCtrlFlg, FLG_ON_Y);
            contactVldCnt++;
        }

        if (hasValue(ctacMsg.ctacPsnEmlAddr)) {
            contactPntPMsg = nmzc002001pMsg.ContactPointInfoList.no(contactVldCnt);
            setValue(contactPntPMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
            setValue(contactPntPMsg.dsCtacPntTpCd, DS_CTAC_PNT_TP.EMAIL_WORK);
            setValue(contactPntPMsg.dsCtacPntValTxt, ctacMsg.ctacPsnEmlAddr);
            setValue(contactPntPMsg.dsOpsOutFlg, FLG_OFF_N);
            setValue(contactPntPMsg.dsCtacPntActvFlg, FLG_ON_Y);
            contactVldCnt++;
        }

        if (hasValue(ctacMsg.ctacPsnFaxNum)) {
            contactPntPMsg = nmzc002001pMsg.ContactPointInfoList.no(contactVldCnt);
            setValue(contactPntPMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
            setValue(contactPntPMsg.dsCtacPntTpCd, DS_CTAC_PNT_TP.FAX_WORK);
            setValue(contactPntPMsg.dsCtacPntValTxt, ctacMsg.ctacPsnFaxNum);
            setValue(contactPntPMsg.dsOpsOutFlg, FLG_OFF_N);
            setValue(contactPntPMsg.dsCtacPntActvFlg, FLG_ON_Y);
            contactVldCnt++;
        }

        nmzc002001pMsg.ContactPointInfoList.setValidCount(contactVldCnt);

        return nmzc002001pMsg;
    }

    private boolean callSchdAgmtUpdateApi(NWZC171001PMsg schdAgmtApiPMsg) {
        // Call Schedule Agreement Update API
        new NWZC171001().execute(schdAgmtApiPMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(schdAgmtApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(schdAgmtApiPMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), msgId, msgPrms);
                return false;
            }
        }

        for (int i = 0; i < schdAgmtApiPMsg.NWZC171002PMsg.getValidCount(); i++) {
            NWZC171002PMsg pMsg = schdAgmtApiPMsg.NWZC171002PMsg.no(i);
            if (S21ApiUtil.isXxMsgId(pMsg)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                for (S21ApiMessage msg : msgList) {
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), msgId, msgPrms);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * updateSchdAgmtAdjContr
     * @param schdAgmtAdjContr
     * @param schdAgmtAdjStsCd ERROR or SUCCESS
     */
    private void updateSchdAgmtAdjContr(SCHD_AGMT_ADJ_CONTRTMsg schdAgmtAdjContr, String schdAgmtAdjStsCd) {
        String schdAgmtNum = schdAgmtAdjContr.schdAgmtNum.getValue();
        setValue(schdAgmtAdjContr.schdAgmtAdjStsCd, schdAgmtAdjStsCd);
        if (this.errMap.containsKey(schdAgmtNum)) {
            if (this.errMap.get(schdAgmtNum).containsKey(HDR_LINE)) {
                setValue(schdAgmtAdjContr.errMsgTxt, this.errMap.get(schdAgmtNum).get(HDR_LINE));
            } else {
                for (Map.Entry<BigDecimal, String> entry : this.errMap.get(schdAgmtNum).entrySet()) {
                    setValue(schdAgmtAdjContr.errMsgTxt, entry.getValue());
                }
            }
        }
        S21ApiTBLAccessor.update(schdAgmtAdjContr);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdAgmtAdjContr.getReturnCode())) {
            throw new S21AbendException(NWAM0729E, new String[] {"SCHD_AGMT_ADJ_CONTR" });
        }
    }

    private SCHD_AGMTTMsg getSchdAgmt(String schdAgmtNum) {

        SCHD_AGMTTMsg inTMsg = new SCHD_AGMTTMsg();
        inTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        inTMsg.schdAgmtNum.setValue(schdAgmtNum);
        SCHD_AGMTTMsg outTMsg = (SCHD_AGMTTMsg) S21ApiTBLAccessor.findByKey(inTMsg);

        if (outTMsg == null) {
            return null;
        }

        return outTMsg;
    }

    private SCHD_AGMT_LINETMsgArray getSchdAgmtLine(String schdAgmtNum) {

        SCHD_AGMT_LINETMsg inTMsg = new SCHD_AGMT_LINETMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inTMsg.setConditionValue("schdAgmtNum01", schdAgmtNum);
        SCHD_AGMT_LINETMsgArray outTMsg = (SCHD_AGMT_LINETMsgArray) S21ApiTBLAccessor.findByCondition(inTMsg);
        if (outTMsg == null) {
            return null;
        }

        return outTMsg;
    }

    @SuppressWarnings("unchecked")
    private List<SCHD_AGMT_PLNTMsg> getSchdAgmtPln(String schdAgmtNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("schdAgmtNum", schdAgmtNum);
        List<SCHD_AGMT_PLNTMsg> outTMsg = (List<SCHD_AGMT_PLNTMsg>) ssmBatchClient.queryObjectList("getSchdAgmtPln", ssmParam);

        if (outTMsg == null) {
            return null;
        }

        return outTMsg;
    }

    private SCHD_AGMT_SLS_CRTMsgArray getSchdAgmtSlsCrArray(String schdAgmtNum) {

        SCHD_AGMT_SLS_CRTMsg inTMsg = new SCHD_AGMT_SLS_CRTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inTMsg.setConditionValue("schdAgmtNum01", schdAgmtNum);
        SCHD_AGMT_SLS_CRTMsgArray array = (SCHD_AGMT_SLS_CRTMsgArray) S21ApiTBLAccessor.findByCondition(inTMsg);

        return array;
    }

    private SCHD_AGMT_PRC_CALC_BASETMsgArray getSchdAgmtPrcCalcBaseArray(String schdAgmtNum) {

        SCHD_AGMT_PRC_CALC_BASETMsg inTMsg = new SCHD_AGMT_PRC_CALC_BASETMsg();
        inTMsg.setSQLID("002");
        inTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inTMsg.setConditionValue("schdAgmtNum01", schdAgmtNum);
        SCHD_AGMT_PRC_CALC_BASETMsgArray array = (SCHD_AGMT_PRC_CALC_BASETMsgArray) S21ApiTBLAccessor.findByCondition(inTMsg);

        return array;
    }

    /**
     * Get SchdAgmtCtacPsns
     * @param schdAgmt SCHD_AGMTTMsg
     * @return SCHD_AGMT_CTAC_PSNTMsgArray
     */
    private SCHD_AGMT_CTAC_PSNTMsgArray getSchdAgmtCtacPsn(String schdAgmtNum) {
        SCHD_AGMT_CTAC_PSNTMsg schdAgmtCtacPsn = new SCHD_AGMT_CTAC_PSNTMsg();
        schdAgmtCtacPsn.setSQLID("001");
        schdAgmtCtacPsn.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        schdAgmtCtacPsn.setConditionValue("schdAgmtNum01", schdAgmtNum);

        SCHD_AGMT_CTAC_PSNTMsgArray schdAgmtCtacPsnList = (SCHD_AGMT_CTAC_PSNTMsgArray) EZDTBLAccessor.findByCondition(schdAgmtCtacPsn);
        if (schdAgmtCtacPsnList == null || schdAgmtCtacPsnList.getValidCount() == 0) {
            return null;
        }
        return schdAgmtCtacPsnList;
    }

    private SVC_MACH_MSTRTMsg getSvcMachMstr(BigDecimal svcMachMstrPk) {

        SVC_MACH_MSTRTMsg inTMsg = new SVC_MACH_MSTRTMsg();
        setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inTMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(inTMsg);
    }

    private String getLocationNumber(NWZC171001PMsg schdAgmtApiPMsg, NWZC171001_DPMsg ctacMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);

        String query = null;
        String ctacCustRefTpCd = ctacMsg.ctacCustRefTpCd.getValue();
        if (CTAC_CUST_REF_TP.BILL_TO.equals(ctacCustRefTpCd)) {
            ssmParam.put("custCd", schdAgmtApiPMsg.billToCustLocCd.getValue());
            query = "getBillToLocationNumber";

        } else if (CTAC_CUST_REF_TP.SHIP_TO.equals(ctacCustRefTpCd)) {
            ssmParam.put("custCd", schdAgmtApiPMsg.shipToCustLocCd.getValue());
            query = "getShipToLocationNumber";

        } else if (CTAC_CUST_REF_TP.SOLD_TO.equals(ctacCustRefTpCd)) {
            ssmParam.put("custCd", schdAgmtApiPMsg.soldToCustLocCd.getValue());
            query = "getBillToLocationNumber";
        } else {
            ssmParam.put("custCd", schdAgmtApiPMsg.shipToCustLocCd.getValue());
            query = "getShipToLocationNumber";
        }
        ssmParam.put("rowNum", 1);

        return (String) ssmBatchClient.queryObject(query, ssmParam);
    }

    private boolean checkSchdForRenewal(NWZC171001PMsg schdAgmtApiPMsg, BigDecimal selectLineNum, SCHD_AGMT_ADJ_CONTRTMsg schdAgmtAdjContr) {
        String shpgIntvlCd = null;
        BigDecimal otmShipQty = null;
        // get line schedule settings
        for (int i = 0; i < schdAgmtApiPMsg.NWZC171002PMsg.getValidCount(); i++) {
            NWZC171002PMsg nwzc171002PMsg = schdAgmtApiPMsg.NWZC171002PMsg.no(i);
            // Process Schedule Line Number
            if (nwzc171002PMsg.schdAgmtLineNum.getValue().compareTo(selectLineNum) == 0) {
                shpgIntvlCd = nwzc171002PMsg.shpgIntvlCd.getValue();
                otmShipQty = nwzc171002PMsg.otmShipQty.getValue();
                break;
            }
        }
        if (!hasValue(shpgIntvlCd) || !hasValue(otmShipQty) || BigDecimal.ZERO.compareTo(otmShipQty) == 0) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), selectLineNum, NWAM8461E, null);
            return false;
        }
        BigDecimal shpgIntvl = getShpgIntvlMthNum(shpgIntvlCd);
        if (shpgIntvl == null || BigDecimal.ZERO.compareTo(shpgIntvl) == 0) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), selectLineNum, NWAM8461E, null);
            return false;
        }
        // Check schedule Line
        int schdCnt = 0;
        String lastPlnDt = null;
        for (int i = schdAgmtApiPMsg.NWZC171003PMsg.getValidCount() - 1; i >= 0; i--) {
            NWZC171003PMsg nwzc171003PMsg = schdAgmtApiPMsg.NWZC171003PMsg.no(i);
            // Process Schedule Line Number
            if (nwzc171003PMsg.schdAgmtLineNum.getValue().compareTo(selectLineNum) != 0) {
                continue;
            }
            if (isCanceledSchedule(nwzc171003PMsg)) {
                continue;
            }
            if (!hasValue(lastPlnDt)) {
                lastPlnDt = nwzc171003PMsg.rddDt.getValue();
            }
            schdCnt++;
        }
        // check Schedule
        if (schdCnt < 1) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), selectLineNum, NWAM8463E, null);
            return false;
        }
        // Check Last Day
        if (!hasValue(lastPlnDt) || toCalendar(lastPlnDt) == null) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), selectLineNum, NWAM8463E, null);
            return false;
        }
        // Check after renewal contract through date
        if (schdAgmtAdjContr.chngVldThruDt.getValue().compareTo(lastPlnDt) < 0) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), selectLineNum, NWAM8464E, null);
            return false;
        }
        return true;
    }

    private boolean createNewScheduleForRenewal(NWZC171001PMsg schdAgmtApiPMsg, SCHD_AGMT_ADJ_CONTRTMsg schdAgmtAdjContr) {
        boolean hasSucceeded = false;
        for (int j = 0; j < schdAgmtApiPMsg.NWZC171002PMsg.getValidCount(); j++) {

            NWZC171002PMsg schdAgmtLine = schdAgmtApiPMsg.NWZC171002PMsg.no(j);
            // Target Line Number
            BigDecimal selectLineNum = schdAgmtLine.schdAgmtLineNum.getValue();
            // Validation
            if (!checkSchdForRenewal(schdAgmtApiPMsg, selectLineNum, schdAgmtAdjContr)) {
                continue;
            }
            String shpgIntvlCd = null;
            BigDecimal otmShipQty = null;
            for (int i = 0; i < schdAgmtApiPMsg.NWZC171002PMsg.getValidCount(); i++) {
                if (schdAgmtApiPMsg.NWZC171002PMsg.no(i).schdAgmtLineNum.getValue().compareTo(selectLineNum) == 0) {
                    shpgIntvlCd = schdAgmtLine.shpgIntvlCd.getValue();
                    otmShipQty = schdAgmtLine.otmShipQty.getValue();
                }
            }

            String lastPlnDt = null;
            List<String> deliveryDateList = new ArrayList<String>();
            for (int i = schdAgmtApiPMsg.NWZC171003PMsg.getValidCount() - 1; i >= 0; i--) {
                NWZC171003PMsg nwzc171003PMsg = schdAgmtApiPMsg.NWZC171003PMsg.no(i);
                if (nwzc171003PMsg.schdAgmtLineNum.getValue().compareTo(selectLineNum) == 0 && !isCanceledSchedule(nwzc171003PMsg)) {
                    lastPlnDt = nwzc171003PMsg.rddDt.getValue();
                    BigDecimal shpgIntvlMthNum = getShpgIntvlMthNum(shpgIntvlCd);
                    // Create New Schedule Date List
                    deliveryDateList = createDeliveryDateList(schdAgmtApiPMsg, schdAgmtAdjContr, shpgIntvlMthNum, lastPlnDt);
                    break;
                }
            }

            BigDecimal addQty = BigDecimal.ZERO;
            for (int i = 0; i < deliveryDateList.size(); i++) {

                int maxSchdNum = getMaxLineNum(schdAgmtApiPMsg.NWZC171003PMsg, selectLineNum);
                int newShcdRowLineNum = getAddLineRow(schdAgmtApiPMsg.NWZC171003PMsg, selectLineNum, maxSchdNum);

                NWZC171003PMsg newSchdLine = (NWZC171003PMsg) insertNewLine(schdAgmtApiPMsg.NWZC171003PMsg, newShcdRowLineNum);

                setValue(newSchdLine.schdAgmtNum, schdAgmtApiPMsg.schdAgmtNum);
                setValue(newSchdLine.schdAgmtLineNum, selectLineNum);
                setValue(newSchdLine.schdAgmtSchdLineNum, new BigDecimal(maxSchdNum + 1));
                setValue(newSchdLine.rddDt, deliveryDateList.get(i));
                setValue(newSchdLine.ordQty, otmShipQty);
                setValue(newSchdLine.schdAgmtPlnCancFlg, FLG_OFF_N);

                addQty = addQty.add(otmShipQty);
            }
            // Adjust Allowed Qty
            setValue(schdAgmtLine.schdAllwQty, schdAgmtLine.schdAllwQty.getValue().add(addQty));
            hasSucceeded = true;
        }
        return hasSucceeded;
    }

    private BigDecimal getShpgIntvlMthNum(String shpgIntvlCd) {
        // search Shipping Interval
        SHPG_INTVLTMsg tMsg = new SHPG_INTVLTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.shpgIntvlCd, shpgIntvlCd);
        tMsg = (SHPG_INTVLTMsg) S21CodeTableAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return null;
        }
        return tMsg.shpgIntvlMthNum.getValue();
    }

    private int getDiffMonth(String before, String after) {

        Calendar calBf = toCalendar(before);
        calBf.set(Calendar.DATE, 1);
        Calendar calAft = toCalendar(after);
        calAft.set(Calendar.DATE, 1);

        int count = 0;

        while (calBf.before(calAft)) {
            calBf.add(Calendar.MONTH, 1);
            count++;
        }

        return count;
    }

    private Calendar toCalendar(String strDate) {

        if (!hasValue(strDate)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(S21CalendarUtilConstants.TYPE_YYYYMMDD);
        Date dt = null;
        try {
            dt = format.parse(strDate);
        } catch (ParseException e) {
            EZDDebugOutput.println(1, "ParseException occured.", e);
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        return cal;
    }

    /**
     * createDeliveryDateList
     * @param scrnMsg NWAL1860BMsg
     * @return List<String>
     */
    private List<String> createDeliveryDateList(NWZC171001PMsg schdAgmtApiPMsg, SCHD_AGMT_ADJ_CONTRTMsg schdAgmtAdjContr, BigDecimal shpgIntvlMthNum, String schdAgmtPlnDt) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        String startDt = schdAgmtAdjContr.origVldThruDt.getValue();
        if (schdAgmtAdjContr.chngVldThruDt.getValue().compareTo(schdAgmtAdjContr.origVldThruDt.getValue()) < 0) {
            startDt = schdAgmtAdjContr.chngVldThruDt.getValue();
        }
        String endDt = schdAgmtAdjContr.chngVldThruDt.getValue();
        String schdAgmtPlnDay = schdAgmtPlnDt.substring(6, 8);

        Calendar estimateStartDate = toCalendar(schdAgmtPlnDt);
        Calendar origVldThruDt = toCalendar(startDt);
        while (estimateStartDate.before(origVldThruDt)) {
            estimateStartDate = getDelyDate(estimateStartDate, Integer.parseInt(schdAgmtPlnDay), shpgIntvlMthNum.intValue());
        }
        startDt = format.format(estimateStartDate.getTime());
        List<String> delyDateList = new ArrayList<String>();
        try {
            int diffMonth = getDiffMonth(startDt, endDt);
            if (diffMonth == 0) {
                return delyDateList;
            } else {
                diffMonth = diffMonth + 1;
            }
            int lineCount = getLineCount(diffMonth, shpgIntvlMthNum);

            String starDt01 = startDt.substring(0, 6) + "01";
            Date startDtDate = format.parse(starDt01);
            Calendar startDtCal = Calendar.getInstance();
            startDtCal.setTime(startDtDate);

            int endOfDate = startDtCal.getActualMaximum(Calendar.DATE);
            if (endOfDate < Integer.parseInt(schdAgmtPlnDay)) {
                startDtCal.set(Calendar.DATE, endOfDate);
            }

            int cnt = 0;
            int intvl = shpgIntvlMthNum.intValue();
            int addMonth = intvl;
            Calendar slsDate = toCalendar(this.slsDt);
            // Set First Date
            Calendar delyDate = getDelyDate(startDtCal, Integer.parseInt(schdAgmtPlnDay), 0);
            delyDateList.add(format.format(delyDate.getTime()));
            cnt++;

            // After first date
            while (cnt < lineCount) {
                if (slsDate.after(delyDate)) {
                    addMonth = addMonth + intvl;
                    cnt++;
                    continue;
                }
                delyDate = getDelyDate(startDtCal, Integer.parseInt(schdAgmtPlnDay), addMonth);
                delyDateList.add(format.format(delyDate.getTime()));
                addMonth = addMonth + intvl;
                cnt++;
            }

        } catch (ParseException e) {
            throw new S21AbendException(NWAM0713E);
        }
        return delyDateList;
    }

    /**
     * getLineCount
     * @param diffMonth int
     * @param shpgIntvlMthNum BigDecimal
     * @return int
     */
    private int getLineCount(int diffMonth, BigDecimal shpgIntvlMthNum) {

        if (diffMonth == 0) {
            return 0;
        } else {
            BigDecimal diffMonthBd = new BigDecimal(diffMonth);
            BigDecimal lineCount = diffMonthBd.divide(shpgIntvlMthNum, 0, BigDecimal.ROUND_UP);
            return lineCount.intValue();
        }
    }

    /**
     * Get Schedule Line Number in the Line
     * @param schdLineList NWAL1840_BCMsgArray
     * @param lineNum int
     * @return int
     */
    private int getMaxLineNum(NWZC171003PMsgArray schdLineList, BigDecimal lineNum) {

        int maxSchdNum = 0;
        for (int i = 0; i < schdLineList.getValidCount(); i++) {

            NWZC171003PMsg schdLine = schdLineList.no(i);

            if (schdLine.schdAgmtLineNum.getValueInt() == lineNum.intValue()) {
                maxSchdNum = schdLine.schdAgmtSchdLineNum.getValueInt();
            }
        }
        return maxSchdNum;
    }

    /**
     * Get Schedule LineNumber to Insert in the Schedule Order
     * @param schdLineList NWAL1840_BCMsgArray
     * @param lineNum BigDecimal
     * @param maxSchdNum int
     * @return int
     */
    private int getAddLineRow(NWZC171003PMsgArray schdLineList, BigDecimal lineNum, int maxSchdNum) {

        int insertRowNum = schdLineList.getValidCount();
        for (int i = 0; i < schdLineList.getValidCount(); i++) {

            NWZC171003PMsg schdLine = schdLineList.no(i);

            if (lineNum.intValue() > schdLine.schdAgmtLineNum.getValueInt()) {
                insertRowNum = i + 1;
            }
            if (lineNum.intValue() == schdLine.schdAgmtLineNum.getValueInt()) {
                if (schdLine.schdAgmtSchdLineNum.getValueInt() == maxSchdNum) {
                    return i + 1;
                }
            }
            if (lineNum.intValue() < schdLine.schdAgmtLineNum.getValueInt()) {
                return i;
            }
        }

        return insertRowNum;
    }

    /**
     * insert new row
     * @param list EZDMsgArray
     * @param insertRow int
     * @return new line
     */
    private EZDMsg insertNewLine(EZDMsgArray list, int insertRow) {

        if (list.getValidCount() >= list.length()) {
            return null;
        }

        if (list.getValidCount() == 0) {

            list.setValidCount(1);
            return list.get(insertRow);
        }

        for (int i = list.getValidCount() - 1; insertRow <= i; i--) {
            EZDMsg.copy(list.get(i), null, list.get(i + 1), null);
        }
        list.get(insertRow).clear();
        list.setValidCount(list.getValidCount() + 1);
        return list.get(insertRow);
    }

    /**
     * getDelyDate
     * @param startDate Calendar
     * @param inputDay int
     * @param addMonth int
     * @return Calendar
     */
    private Calendar getDelyDate(Calendar startDate, int inputDay, int addMonth) {
        Calendar delyDate = (Calendar) startDate.clone();
        delyDate.add(Calendar.MONTH, addMonth);
        int date = delyDate.get(Calendar.DATE);
        if (date != inputDay) {
            int endOfDate = delyDate.getActualMaximum(Calendar.DATE);
            if (inputDay <= endOfDate) {
                delyDate.set(Calendar.DATE, inputDay);
            } else {
                delyDate.set(Calendar.DATE, endOfDate);
            }
        }
        return delyDate;
    }

    private NWZC171001PMsg getScheduleUpdateApiMsg(String schdAgmtNum) {
        NWZC171001PMsg schdAgmtApiPMsg = new NWZC171001PMsg();
        SCHD_AGMTTMsg schdAgmTMsg = getSchdAgmt(schdAgmtNum);
        if (schdAgmTMsg == null) {
            return null;
        }
        // Check Status
        if (!checkSchdAgmtSts(schdAgmTMsg.schdAgmtStsCd.getValue())) {
            setErrMsg(schdAgmtNum, NWAM8462E, new String[] {schdAgmtNum });
            return null;
        }
        // Get Line
        SCHD_AGMT_LINETMsgArray schdAgmtLineTMsgList = getSchdAgmtLine(schdAgmtNum);
        if (schdAgmtLineTMsgList == null) {
            return null;
        }
        // Get Plan
        List<SCHD_AGMT_PLNTMsg> schdAgmtPlnTMsgList = getSchdAgmtPln(schdAgmtNum);
        if (schdAgmtPlnTMsgList == null) {
            return null;
        }
        // Get Calculation Base
        SCHD_AGMT_PRC_CALC_BASETMsgArray schdAgmtPrcCalcBaseTMsgList = getSchdAgmtPrcCalcBaseArray(schdAgmtNum);
        if (schdAgmtPrcCalcBaseTMsgList == null) {
            return null;
        }
        // Get Sales Credit
        SCHD_AGMT_SLS_CRTMsgArray schdAgmtSlsCrTMsgList = getSchdAgmtSlsCrArray(schdAgmtNum);
        if (schdAgmtSlsCrTMsgList == null) {
            return null;
        }
        // Get Contact Person
        SCHD_AGMT_CTAC_PSNTMsgArray schdAgmtCtacPsnList = getSchdAgmtCtacPsn(schdAgmtNum);
        // set Parameter
        createHdrParam(schdAgmtApiPMsg, schdAgmTMsg);
        createDtlParam(schdAgmtApiPMsg, schdAgmtLineTMsgList, schdAgmtPrcCalcBaseTMsgList);
        createPlnParam(schdAgmtApiPMsg, schdAgmtPlnTMsgList);
        createSlsCreditParam(schdAgmtApiPMsg, schdAgmtSlsCrTMsgList);
        createCtacPsnParam(schdAgmtApiPMsg, schdAgmtCtacPsnList);
        return schdAgmtApiPMsg;
    }

    /**
     * Create Header Parameter
     * @param bizMsg NWAL1840CMsg
     * @param schdAgmtApiPMsg NWZC171001PMsg
     * @param isSave Save Flag
     */
    private void createHdrParam(NWZC171001PMsg schdAgmtApiPMsg, SCHD_AGMTTMsg schdAgmt) {

        setValue(schdAgmtApiPMsg.glblCmpyCd, schdAgmt.glblCmpyCd);
        setValue(schdAgmtApiPMsg.slsDt, this.slsDt);
        setValue(schdAgmtApiPMsg.xxModeCd, NWZC171001Constant.MODE_SUBMIT);
        setValue(schdAgmtApiPMsg.schdAgmtNum, schdAgmt.schdAgmtNum);
        setValue(schdAgmtApiPMsg.refCpoOrdNum, schdAgmt.refCpoOrdNum);
        setValue(schdAgmtApiPMsg.schdCratTmplPk, schdAgmt.schdCratTmplPk);
        setValue(schdAgmtApiPMsg.schdAgmtCatgCd, schdAgmt.dsOrdCatgCd);
        setValue(schdAgmtApiPMsg.dsOrdCatgCd, schdAgmt.dsOrdCatgCd);
        setValue(schdAgmtApiPMsg.dsOrdTpCd, schdAgmt.dsOrdTpCd);
        setValue(schdAgmtApiPMsg.dsOrdRsnCd, schdAgmt.dsOrdRsnCd);
        setValue(schdAgmtApiPMsg.cpoOrdTpCd, schdAgmt.cpoOrdTpCd);
        setValue(schdAgmtApiPMsg.schdAgmtCratDt, schdAgmt.schdAgmtCratDt);
        setValue(schdAgmtApiPMsg.prcCatgCd, schdAgmt.prcCatgCd);
        setValue(schdAgmtApiPMsg.flPrcListCd, schdAgmt.flPrcListCd);
        setValue(schdAgmtApiPMsg.mdlId, schdAgmt.mdlId);
        setValue(schdAgmtApiPMsg.serNum, schdAgmt.serNum);
        setValue(schdAgmtApiPMsg.svcConfigMstrPk, schdAgmt.svcConfigMstrPk);
        setValue(schdAgmtApiPMsg.dsContrNum, schdAgmt.dsContrNum);
        setValue(schdAgmtApiPMsg.dsContrSqNum, getDsContrSqNum(schdAgmt.dsContrNum.getValue()));
        setValue(schdAgmtApiPMsg.cpoSrcTpCd, schdAgmt.cpoSrcTpCd);
        setValue(schdAgmtApiPMsg.schdAgmtVldFromDt, schdAgmt.schdAgmtVldFromDt);
        setValue(schdAgmtApiPMsg.schdAgmtVldThruDt, schdAgmt.schdAgmtVldThruDt);
        setValue(schdAgmtApiPMsg.schdAgmtDelyHldCd, schdAgmt.schdAgmtDelyHldCd);
        setValue(schdAgmtApiPMsg.custIssPoNum, schdAgmt.custIssPoNum);
        setValue(schdAgmtApiPMsg.custIssPoDt, schdAgmt.custIssPoDt);
        setValue(schdAgmtApiPMsg.slsRepTocCd, schdAgmt.slsRepTocCd);

        setValue(schdAgmtApiPMsg.carrCd, schdAgmt.carrCd);
        setValue(schdAgmtApiPMsg.spclHdlgTpCd, schdAgmt.spclHdlgTpCd);
        setValue(schdAgmtApiPMsg.frtCondCd, schdAgmt.frtCondCd);
        setValue(schdAgmtApiPMsg.carrSvcLvlCd, schdAgmt.carrSvcLvlCd);
        setValue(schdAgmtApiPMsg.shpgSvcLvlCd, schdAgmt.shpgSvcLvlCd);
        setValue(schdAgmtApiPMsg.frtChrgToCd, schdAgmt.frtChrgToCd);
        setValue(schdAgmtApiPMsg.frtChrgMethCd, schdAgmt.frtChrgMethCd);

        setValue(schdAgmtApiPMsg.sellToCustCd, schdAgmt.sellToCustCd);
        setValue(schdAgmtApiPMsg.soldToCustLocCd, schdAgmt.soldToCustLocCd);
        setValue(schdAgmtApiPMsg.billToCustAcctCd, schdAgmt.billToCustAcctCd);
        setValue(schdAgmtApiPMsg.billToCustLocCd, schdAgmt.billToCustLocCd);
        setValue(schdAgmtApiPMsg.shipToCustAcctCd, schdAgmt.shipToCustAcctCd);
        setValue(schdAgmtApiPMsg.shipToCustLocCd, schdAgmt.shipToCustLocCd);
        setValue(schdAgmtApiPMsg.sellToFirstRefCmntTxt, schdAgmt.sellToFirstRefCmntTxt);
        setValue(schdAgmtApiPMsg.adminPsnCd, schdAgmt.adminPsnCd);
        setValue(schdAgmtApiPMsg.dropShipFlg, schdAgmt.dropShipFlg);
        setValue(schdAgmtApiPMsg.shipToLocNm, schdAgmt.shipToLocNm);
        setValue(schdAgmtApiPMsg.shipToAddlLocNm, schdAgmt.shipToAddlLocNm);
        setValue(schdAgmtApiPMsg.shipToFirstLineAddr, schdAgmt.shipToFirstLineAddr);
        setValue(schdAgmtApiPMsg.shipToScdLineAddr, schdAgmt.shipToScdLineAddr);
        setValue(schdAgmtApiPMsg.shipToThirdLineAddr, schdAgmt.shipToThirdLineAddr);
        setValue(schdAgmtApiPMsg.shipToFrthLineAddr, schdAgmt.shipToFrthLineAddr);
        setValue(schdAgmtApiPMsg.shipToCtyAddr, schdAgmt.shipToCtyAddr);
        setValue(schdAgmtApiPMsg.shipToStCd, schdAgmt.shipToStCd);
        setValue(schdAgmtApiPMsg.shipToProvNm, schdAgmt.shipToProvNm);
        setValue(schdAgmtApiPMsg.shipToPostCd, schdAgmt.shipToPostCd);
        setValue(schdAgmtApiPMsg.shipToCntyNm, schdAgmt.shipToCntyNm);
        setValue(schdAgmtApiPMsg.shipToCtryCd, schdAgmt.shipToCtryCd);
        setValue(schdAgmtApiPMsg.shipTo01RefCmntTxt, schdAgmt.shipTo01RefCmntTxt);
        setValue(schdAgmtApiPMsg.shipTo02RefCmntTxt, schdAgmt.shipTo02RefCmntTxt);
        setValue(schdAgmtApiPMsg.pmtTermCashDiscCd, schdAgmt.pmtTermCashDiscCd);
        setValue(schdAgmtApiPMsg.carrAcctNum, schdAgmt.carrAcctNum);
        setValue(schdAgmtApiPMsg.prcBaseDt, schdAgmt.prcBaseDt);
        setValue(schdAgmtApiPMsg.prcCalcDt, this.slsDt);
        setValue(schdAgmtApiPMsg.prcContrNum, schdAgmt.prcContrNum);
        setValue(schdAgmtApiPMsg.shpgCmntTxt, schdAgmt.shpgCmntTxt);
        setValue(schdAgmtApiPMsg.itrlOrdCmntTxt, schdAgmt.itrlOrdCmntTxt);
        setValue(schdAgmtApiPMsg.invCmntTxt, schdAgmt.invCmntTxt);
    }

    /**
     * Create Detail Parameter
     * @param schdAgmtApiPMsg NWZC171001PMsg
     */
    private void createDtlParam(NWZC171001PMsg schdAgmtApiPMsg, SCHD_AGMT_LINETMsgArray getSchdAgmtLineList, SCHD_AGMT_PRC_CALC_BASETMsgArray schdAgmtPrcCalcBaseList) {

        int lineMsgValidCnt = 0;

        for (; lineMsgValidCnt < getSchdAgmtLineList.getValidCount(); lineMsgValidCnt++) {
            SCHD_AGMT_LINETMsg schdAgmtLine = getSchdAgmtLineList.no(lineMsgValidCnt);
            BigDecimal schdAgmtDtlLineNum = schdAgmtLine.schdAgmtLineNum.getValue();

            NWZC171002PMsg schdAgmtLinePMsg = schdAgmtApiPMsg.NWZC171002PMsg.no(lineMsgValidCnt);
            setValue(schdAgmtLinePMsg.schdAgmtNum, schdAgmtLine.schdAgmtNum);
            setValue(schdAgmtLinePMsg.schdAgmtLineNum, schdAgmtLine.schdAgmtLineNum);
            setValue(schdAgmtLinePMsg.dsContrDtlPk, schdAgmtLine.dsContrDtlPk);
            setValue(schdAgmtLinePMsg.mdseCd, schdAgmtLine.mdseCd);
            setValue(schdAgmtLinePMsg.mdseNm, schdAgmtLine.mdseNm);
            setValue(schdAgmtLinePMsg.sbstMdseCd, schdAgmtLine.sbstMdseCd);
            setValue(schdAgmtLinePMsg.stkStsCd, STK_STS.GOOD);
            setValue(schdAgmtLinePMsg.pkgUomCd, schdAgmtLine.pkgUomCd);
            setValue(schdAgmtLinePMsg.schdAllwQty, schdAgmtLine.schdAllwQty);
            setValue(schdAgmtLinePMsg.shpgIntvlCd, schdAgmtLine.shpgIntvlCd);
            setValue(schdAgmtLinePMsg.otmShipQty, schdAgmtLine.otmShipQty);
            setValue(schdAgmtLinePMsg.ccyCd, schdAgmtLine.ccyCd);
            setValue(schdAgmtLinePMsg.manPrcFlg, schdAgmtLine.manPrcFlg);
            setValue(schdAgmtLinePMsg.supdLockFlg, schdAgmtLine.supdLockFlg);
            setValue(schdAgmtLinePMsg.schdAgmtLineCancFlg, schdAgmtLine.schdAgmtLineCancFlg);
            setValue(schdAgmtLinePMsg.supdLockFlg, schdAgmtLine.supdLockFlg);

            // Set Calculate Base Parameter
            int calcBaseValidCnt = 0;
            for (int i = 0; i < schdAgmtPrcCalcBaseList.getValidCount(); i++) {
                SCHD_AGMT_PRC_CALC_BASETMsg schdAgmtPrcCalcBase = schdAgmtPrcCalcBaseList.no(i);
                BigDecimal lineNum = schdAgmtPrcCalcBase.schdAgmtLineNum.getValue();

                if (lineNum.compareTo(schdAgmtDtlLineNum) == 0) {
                    NWZC171001_EPMsg calcBaseApiMsg = schdAgmtApiPMsg.E.no(schdAgmtApiPMsg.E.getValidCount() + calcBaseValidCnt);
                    EZDMsg.copy(schdAgmtPrcCalcBase, "", calcBaseApiMsg, "");
                    setValue(calcBaseApiMsg.schdAgmtLineNum, lineNum);
                    calcBaseValidCnt++;
                }
            }
            schdAgmtApiPMsg.E.setValidCount(schdAgmtApiPMsg.E.getValidCount() + calcBaseValidCnt);
        }
        schdAgmtApiPMsg.NWZC171002PMsg.setValidCount(lineMsgValidCnt);
    }

    /**
     * Create Detail Parameter
     * @param bizMsg NWAL1840CMsg
     * @param schdAgmtApiPMsg NWZC171001PMsg
     * @param isSave Save Flag
     */
    private void createPlnParam(NWZC171001PMsg schdAgmtApiPMsg, List<SCHD_AGMT_PLNTMsg> schdAgmtPlnList) {

        int lineMsgValidCnt = 0;

        for (SCHD_AGMT_PLNTMsg schdAgmtPln : schdAgmtPlnList) {
            NWZC171003PMsg schdAgmtLinePMsg = schdAgmtApiPMsg.NWZC171003PMsg.no(lineMsgValidCnt);
            setValue(schdAgmtLinePMsg.schdAgmtNum, schdAgmtPln.schdAgmtNum);
            setValue(schdAgmtLinePMsg.schdAgmtLineNum, schdAgmtPln.schdAgmtLineNum);
            setValue(schdAgmtLinePMsg.schdAgmtSchdLineNum, schdAgmtPln.schdAgmtSchdLineNum);
            setValue(schdAgmtLinePMsg.rddDt, schdAgmtPln.rddDt);
            setValue(schdAgmtLinePMsg.ordQty, schdAgmtPln.ordQty);
            setValue(schdAgmtLinePMsg.schdAgmtPlnCancFlg, schdAgmtPln.schdAgmtPlnCancFlg);
            lineMsgValidCnt++;
        }
        schdAgmtApiPMsg.NWZC171003PMsg.setValidCount(lineMsgValidCnt);
    }

    /**
     * Create Sales Credit Parameter
     * @param bizMsg NWAL1840CMsg
     * @param schdAgmtApiPMsg NWZC171001PMsg
     * @param isSave Save Flag
     */
    private void createSlsCreditParam(NWZC171001PMsg schdAgmtApiPMsg, SCHD_AGMT_SLS_CRTMsgArray schdAgmtSlsCrList) {

        int validCnt = 0;

        for (int i = 0; i < schdAgmtSlsCrList.getValidCount(); i++) {
            SCHD_AGMT_SLS_CRTMsg slsCreditMsg = schdAgmtSlsCrList.no(i);
            NWZC171001_CPMsg slsCreditApiMsg = schdAgmtApiPMsg.C.no(validCnt);
            EZDMsg.copy(slsCreditMsg, "", slsCreditApiMsg, "");

            // Request Type
            setValue(slsCreditApiMsg.xxRqstTpCd, NWZC171001Constant.REQUEST_TYPE_MOD);

            validCnt++;
        }

        schdAgmtApiPMsg.C.setValidCount(validCnt);
    }

    /**
     * Create Contact Person Parameter
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     * @param schdAgmtApiPMsg NWZC171001PMsg
     */
    private void createCtacPsnParam(NWZC171001PMsg schdAgmtApiPMsg, SCHD_AGMT_CTAC_PSNTMsgArray schdAgmtCtacPsnList) {

        if (schdAgmtCtacPsnList == null || schdAgmtCtacPsnList.getValidCount() == 0) {
            return;
        }

        int validCnt = 0;

        // Set Insert Or Update Param
        for (int i = 0; i < schdAgmtCtacPsnList.getValidCount(); i++) {
            SCHD_AGMT_CTAC_PSNTMsg schdAgmtCtacPsn = schdAgmtCtacPsnList.no(i);
            NWZC171001_DPMsg ctacLineApiMsg = schdAgmtApiPMsg.D.no(validCnt);

            EZDMsg.copy(schdAgmtCtacPsn, "", ctacLineApiMsg, "");
            // Request Type
            setValue(ctacLineApiMsg.xxRqstTpCd, NWZC171001Constant.REQUEST_TYPE_MOD);
            setValue(ctacLineApiMsg.ctacPsnOvrdFlg, FLG_OFF_N);
            validCnt++;
        }

        schdAgmtApiPMsg.D.setValidCount(validCnt);
    }

    /**
     * get Contract Sequence number
     * @param glblCmpyCd String
     * @param dsContrNum String
     * @return String
     */
    private String getDsContrSqNum(String dsContrNum) {

        if (hasValue(dsContrNum)) {
            DS_CONTRTMsg dsContrTmsg = new DS_CONTRTMsg();
            dsContrTmsg.setSQLID("003");
            dsContrTmsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            dsContrTmsg.setConditionValue("dsContrNum01", dsContrNum);

            DS_CONTRTMsgArray dsContrTmsgArray = (DS_CONTRTMsgArray) EZDTBLAccessor.findByCondition(dsContrTmsg);

            if (dsContrTmsgArray.getValidCount() == 0) {
                return null;
            }

            String dsContrSqNum = "";
            for (int i = 0; i < dsContrTmsgArray.length(); i++) {
                if (!hasValue(dsContrSqNum)) {
                    dsContrSqNum = dsContrTmsgArray.no(i).dsContrSqNum.getValue();

                } else if (dsContrSqNum.compareTo(dsContrTmsgArray.no(i).dsContrSqNum.getValue()) < 0) {
                    dsContrSqNum = dsContrTmsgArray.no(i).dsContrSqNum.getValue();
                }
            }
            return dsContrSqNum;
        } else {
            return null;
        }
    }

    private boolean setCustInfo(NWZC171001PMsg schdAgmtApiPMsg, SCHD_AGMT_ADJ_CONTRTMsg schdAgmtAdjContr) {
        // Get exchange location
        String billToCustLocCd = schdAgmtAdjContr.exchBillToCustLocCd.getValue();
        String soldToCustLocCd = schdAgmtAdjContr.exchSldToCustLocCd.getValue();
        String shipToCustLocCd = schdAgmtAdjContr.exchShipToCustLocCd.getValue();
        // Get location info
        Map<String, String> billToInfo = getBillToInfo(billToCustLocCd);
        Map<String, String> soldToInfo = getBillToInfo(soldToCustLocCd);
        Map<String, String> shipToInfo = getShipToInfo(shipToCustLocCd);
        if (billToInfo == null || soldToInfo == null || shipToInfo == null) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), NWAM0722E, new String[] {"Exchange location Infomation" });
            return false;
        }
        // Set exchange location
        setValue(schdAgmtApiPMsg.billToCustLocCd, billToCustLocCd);
        setValue(schdAgmtApiPMsg.soldToCustLocCd, soldToCustLocCd);
        setValue(schdAgmtApiPMsg.shipToCustLocCd, shipToCustLocCd);
        // BillTo
        setValue(schdAgmtApiPMsg.billToCustAcctCd, billToInfo.get("SELL_TO_CUST_CD"));
        // SoldTo
        setValue(schdAgmtApiPMsg.sellToCustCd, soldToInfo.get("SELL_TO_CUST_CD"));
        setValue(schdAgmtApiPMsg.soldToCustLocCd, soldToInfo.get("BILL_TO_CUST_CD"));
        // ShipTo
        setValue(schdAgmtApiPMsg.shipToCustAcctCd, shipToInfo.get("SELL_TO_CUST_CD"));
        setValue(schdAgmtApiPMsg.shipToCustLocCd, shipToInfo.get("SHIP_TO_CUST_CD"));
        setValue(schdAgmtApiPMsg.shipToLocNm, shipToInfo.get("LOC_NM"));
        setValue(schdAgmtApiPMsg.shipToAddlLocNm, shipToInfo.get("ADDL_LOC_NM"));
        setValue(schdAgmtApiPMsg.shipToFirstLineAddr, shipToInfo.get("FIRST_LINE_ADDR"));
        setValue(schdAgmtApiPMsg.shipToScdLineAddr, shipToInfo.get("SCD_LINE_ADDR"));
        setValue(schdAgmtApiPMsg.shipToThirdLineAddr, shipToInfo.get("THIRD_LINE_ADDR"));
        setValue(schdAgmtApiPMsg.shipToFrthLineAddr, shipToInfo.get("FRTH_LINE_ADDR"));
        setValue(schdAgmtApiPMsg.shipTo01RefCmntTxt, shipToInfo.get("FIRST_REF_CMNT_TXT"));
        setValue(schdAgmtApiPMsg.shipTo02RefCmntTxt, shipToInfo.get("SCD_REF_CMNT_TXT"));
        setValue(schdAgmtApiPMsg.shipToCtyAddr, shipToInfo.get("CTY_ADDR"));
        setValue(schdAgmtApiPMsg.shipToStCd, shipToInfo.get("ST_CD"));
        setValue(schdAgmtApiPMsg.shipToPostCd, shipToInfo.get("POST_CD"));
        setValue(schdAgmtApiPMsg.shipToProvNm, shipToInfo.get("PROV_NM"));
        setValue(schdAgmtApiPMsg.shipToCtryCd, shipToInfo.get("CTRY_CD"));
        setValue(schdAgmtApiPMsg.shipToCntyNm, shipToInfo.get("CNTY_NM"));
        setValue(schdAgmtApiPMsg.dropShipFlg, FLG_OFF_N);
        return true;
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> getBillToInfo(String custLocNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("custCd", custLocNum);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        return (Map<String, String>) ssmBatchClient.queryObject("getBillToCustInfoList", params);
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> getShipToInfo(String shipToCustCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("shipToCustCd", shipToCustCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        return (Map<String, String>) ssmBatchClient.queryObject("getShipToCustInfoList", params);
    }

    private boolean calcPriceAmt(NWZC171001PMsg schdAgmtApiPMsg) {
        // Check Mandatory
        if (!checkMandatoryField(schdAgmtApiPMsg)) {
            return false;
        }

        // Set Manual Price
        for (int i = 0; i < schdAgmtApiPMsg.NWZC171002PMsg.getValidCount(); i++) {
            NWZC171002PMsg itemLineMsg = schdAgmtApiPMsg.NWZC171002PMsg.no(i);

            if (FLG_ON_Y.equals(itemLineMsg.schdAgmtLineCancFlg.getValue())) {
                continue;
            }

            // Skip Set Component
            BigDecimal schdAgmtLineNum = itemLineMsg.schdAgmtLineNum.getValue();
            if (BigDecimal.ZERO.compareTo(schdAgmtLineNum) != 0) {
                continue;
            }

        }

        // Call Pricing API
        NWZC157001PMsg prcApiPMsg = callPricingApiGetOrderAllMode(schdAgmtApiPMsg);
        if (prcApiPMsg == null) {
            return false;
        }

        // Setup Price Elements
        setPriceElement(schdAgmtApiPMsg, prcApiPMsg);
        return true;
    }

    private boolean checkMandatoryField(NWZC171001PMsg schdAgmtApiPMsg) {
        if (!checkMandatoryHeader(schdAgmtApiPMsg)) {
            return false;
        }

        if (!checkMandatoryCustomerTab(schdAgmtApiPMsg)) {
            return false;
        }

        if (!checkMandatoryHeaderTab(schdAgmtApiPMsg)) {
            return false;
        }

        if (!checkMandatorySchdLineTab(schdAgmtApiPMsg)) {
            return false;
        }

        return true;
    }

    /**
     * Check Mandatory Field For Header
     * @param schdAgmtApiPMsg NWAL1840CMsg
     * @return No Error : true
     */
    private boolean checkMandatoryHeader(NWZC171001PMsg schdAgmtApiPMsg) {

        boolean isNormal = true;

        if (!hasValue(schdAgmtApiPMsg.dsOrdCatgCd)) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_CATG });
            isNormal = false;
        }

        if (!hasValue(schdAgmtApiPMsg.dsOrdTpCd)) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_RSN_CD });
            isNormal = false;
        }

        if (!hasValue(schdAgmtApiPMsg.cpoSrcTpCd)) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_SRC_NM });
            isNormal = false;
        }

        if (!hasValue(schdAgmtApiPMsg.schdAgmtVldFromDt)) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_VALID_FROM });
            isNormal = false;
        }

        if (!hasValue(schdAgmtApiPMsg.schdAgmtVldThruDt)) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_VALID_TO });
            isNormal = false;
        }

        if (!hasValue(schdAgmtApiPMsg.prcCatgCd)) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_PRC_LIST });
            isNormal = false;
        }
        return isNormal;
    }

    /**
     * Check Mandatory Field For Customer
     * @param schdAgmtApiPMsg NWAL1840CMsg
     * @return No Error : true
     */
    private boolean checkMandatoryCustomerTab(NWZC171001PMsg schdAgmtApiPMsg) {

        boolean isNormal = true;

        if (!hasValue(schdAgmtApiPMsg.billToCustAcctCd)) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_BILL_TO_NUM });
            isNormal = false;
        }

        if (!hasValue(schdAgmtApiPMsg.billToCustLocCd)) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_BILL_TO_LOC });
            isNormal = false;
        }

        if (!hasValue(schdAgmtApiPMsg.billToCustAcctCd)) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_BILL_TO_NM });
            isNormal = false;
        }

        if (!hasValue(schdAgmtApiPMsg.shipToCustAcctCd)) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_SHIP_TO_NUM });
            isNormal = false;
        }

        if (!hasValue(schdAgmtApiPMsg.shipToCustLocCd)) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_SHIP_TO_LOC });
            isNormal = false;
        }

        if (!hasValue(schdAgmtApiPMsg.sellToCustCd)) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_SOLD_TO_NUM });
            isNormal = false;
        }

        if (!hasValue(schdAgmtApiPMsg.soldToCustLocCd)) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_SOLD_TO_LOC });
            isNormal = false;
        }

        return isNormal;
    }

    /**
     * Check Mandatory Field For Header Tab
     * @param schdAgmtApiPMsg NWAL1840CMsg
     * @return No Error : true
     */
    private boolean checkMandatoryHeaderTab(NWZC171001PMsg schdAgmtApiPMsg) {

        boolean isNormal = true;

        if (!hasValue(schdAgmtApiPMsg.frtCondCd)) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_FRT_TERMS });
            isNormal = false;
        }

        if (!hasValue(schdAgmtApiPMsg.shpgSvcLvlCd)) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_SHPG_SVC_LVL });
            isNormal = false;
        }

        if (!hasValue(schdAgmtApiPMsg.slsRepTocCd)) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_SLS_REP_NM });
            isNormal = false;
        }

        if (!hasValue(schdAgmtApiPMsg.slsRepTocCd)) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_SLS_REP_CD });
            isNormal = false;
        }

        if (checkPoNum(schdAgmtApiPMsg)) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_CUST_PO_NUM });
            isNormal = false;
        }
        return isNormal;
    }

    /**
     * Check Mandatory Field For Schedule Line Tab
     * @param schdAgmtApiPMsg NWAL1840CMsg
     * @return No Error : true
     */
    private boolean checkMandatorySchdLineTab(NWZC171001PMsg schdAgmtApiPMsg) {

        boolean isNormal = true;

        for (int i = 0; i < schdAgmtApiPMsg.NWZC171002PMsg.getValidCount(); i++) {
            NWZC171002PMsg itemLineMsg = schdAgmtApiPMsg.NWZC171002PMsg.no(i);

            if (FLG_ON_Y.equals(itemLineMsg.schdAgmtLineCancFlg.getValue())) {
                continue;
            }
            if (!hasValue(itemLineMsg.mdseCd)) {
                setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_ITEM });
                isNormal = false;
            }

            if (!hasValue(itemLineMsg.pkgUomCd)) {
                setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_UOM });
                isNormal = false;
            }

            if (!hasValue(itemLineMsg.schdAllwQty)) {
                setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_QTY_ALLW });
                isNormal = false;
            }

        }

        for (int i = 0; i < schdAgmtApiPMsg.NWZC171003PMsg.getValidCount(); i++) {
            NWZC171003PMsg schdPlnMsg = schdAgmtApiPMsg.NWZC171003PMsg.no(i);

            if (isCanceledSchedule(schdPlnMsg)) {
                continue;
            }

            if (!hasValue(schdPlnMsg.rddDt)) {
                setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_RDD_DT });
                isNormal = false;
            }

            if (!hasValue(schdPlnMsg.ordQty)) {
                setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), ZZM9000E, new String[] {MSG_PARAM_ORD_QTY });
                isNormal = false;
            }
        }
        return isNormal;
    }

    /**
     * scheduleCancel
     * @param schdAgmtApiPMsg
     * @return
     */
    private void scheduleCancel(NWZC171001PMsg schdAgmtApiPMsg) {

        String vaildTo = schdAgmtApiPMsg.schdAgmtVldThruDt.getValue();

        for (int i = 0; i < schdAgmtApiPMsg.NWZC171003PMsg.getValidCount(); i++) {
            NWZC171003PMsg schdPlnMsg = schdAgmtApiPMsg.NWZC171003PMsg.no(i);
            if (isCanceledSchedule(schdPlnMsg)) {
                continue;
            }
            if (schdPlnMsg.rddDt.getValue().compareTo(vaildTo) > 0) {
                setValue(schdPlnMsg.schdAgmtPlnCancFlg, FLG_ON_Y);
            }
        }
    }

    /**
     * Call NWZC1570 Pricing API (03:Get Line Price Mode)
     * @param bizMsg NWAL1840CMsg
     * @return NWZC157001PMsg
     */
    private NWZC157001PMsg callPricingApiGetOrderAllMode(NWZC171001PMsg schdAgmtApiPMsg) {

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        Map<String, Object> custInfo = getCustAddlInfo(schdAgmtApiPMsg.dsOrdTpCd.getValue());
        if (custInfo == null) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), NWAM0722E, new String[] {"DS_ORD_LINE_CATG_CD" });
            return null;
        }

        setHdrParam(schdAgmtApiPMsg, prcApiPMsg, NWZC157001.GET_ORDER_ALL, (String) custInfo.get("LINE_BIZ_TP_CD"));
        if (!setLineParamForGetOrderAllMode(schdAgmtApiPMsg, prcApiPMsg)) {
            return null;
        }

        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), msgId, msgPrms);
                if (msgId.endsWith("E")) {
                    return null;
                }
            }
        }

        for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            if (S21ApiUtil.isXxMsgId(prcApiPMsg.NWZC157002PMsg.no(i))) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg.NWZC157002PMsg.no(i));
                for (S21ApiMessage msg : msgList) {
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), msgId, msgPrms);
                    if (msgId.endsWith("E")) {
                        return null;
                    }
                }
            }
        }
        return prcApiPMsg;
    }

    /**
     * Set Price API Header Parameter
     * @param schdAgmtApiPMsg NWAL1840CMsg
     * @param prcApiPMsg NWZC157001PMsg
     * @param modeCd Mode Code
     */
    private void setHdrParam(NWZC171001PMsg schdAgmtApiPMsg, NWZC157001PMsg prcApiPMsg, String modeCd, String lineBizTpCd) {

        setValue(prcApiPMsg.glblCmpyCd, schdAgmtApiPMsg.glblCmpyCd);
        setValue(prcApiPMsg.xxModeCd, modeCd);
        setValue(prcApiPMsg.prcBaseDt, schdAgmtApiPMsg.slsDt);
        setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        setValue(prcApiPMsg.orgRqstDelyDt, schdAgmtApiPMsg.prcBaseDt);
        setValue(prcApiPMsg.dsOrdCatgCd, schdAgmtApiPMsg.dsOrdCatgCd);
        setValue(prcApiPMsg.dsOrdTpCd, schdAgmtApiPMsg.dsOrdTpCd);
        setValue(prcApiPMsg.lineBizTpCd, lineBizTpCd);
        setValue(prcApiPMsg.dsAcctNum, schdAgmtApiPMsg.sellToCustCd);
        setValue(prcApiPMsg.cpoSrcTpCd, schdAgmtApiPMsg.cpoSrcTpCd);
        setValue(prcApiPMsg.prcContrNum, schdAgmtApiPMsg.prcContrNum);
        setValue(prcApiPMsg.custIssPoNum, schdAgmtApiPMsg.custIssPoNum);
        if (NWZC157001.GET_BASE_PRICE.equals(modeCd)) {
            setValue(prcApiPMsg.taxCalcFlg, FLG_OFF_N);
        } else {
            setValue(prcApiPMsg.taxCalcFlg, FLG_ON_Y);
        }
        setValue(prcApiPMsg.spclHdlgTpCd, schdAgmtApiPMsg.spclHdlgTpCd);
        setValue(prcApiPMsg.xxSmryLineFlg, FLG_ON_Y);
    }

    /**
     * Set Price API Line Parameter For Get Base Price Mode
     * @param schdAgmtApiPMsg NWAL1840CMsg
     * @param prcApiPMsg NWZC157001PMsg
     * @param modeCd Mode Code
     */
    private boolean setLineParamForGetOrderAllMode(NWZC171001PMsg schdAgmtApiPMsg, NWZC157001PMsg prcApiPMsg) {

        int prcLineValidCnt = prcApiPMsg.NWZC157002PMsg.getValidCount();
        String dsOrdLineCatgCd = getDsLineCatgCd(schdAgmtApiPMsg.dsOrdTpCd.getValue());
        if (!hasValue(dsOrdLineCatgCd)) {
            setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), NWAM0722E, new String[] {"DS_ORD_LINE_CATG_CD" });
            return false;
        }

        for (int itemLineCnt = 0; itemLineCnt < schdAgmtApiPMsg.NWZC171002PMsg.getValidCount() && prcLineValidCnt < prcApiPMsg.NWZC157002PMsg.length(); itemLineCnt++) {
            NWZC171002PMsg itemLineMsg = schdAgmtApiPMsg.NWZC171002PMsg.no(itemLineCnt);

            if (FLG_ON_Y.equals(itemLineMsg.schdAgmtLineCancFlg.getValue())) {
                continue;
            }

            Map<String, Object> slsRepInfo = getSlsRepInfo(schdAgmtApiPMsg.slsRepTocCd.getValue());
            if (slsRepInfo == null) {
                setErrMsg(schdAgmtApiPMsg.schdAgmtNum.getValue(), NWAM0722E, new String[] {"TOC_CD:" + schdAgmtApiPMsg.slsRepTocCd.getValue() });
                return false;
            }
            NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(prcLineValidCnt);
            setValue(linePrcApiPMsg.trxLineNum, itemLineMsg.schdAgmtLineNum.getValue().toPlainString());
            setValue(linePrcApiPMsg.trxLineSubNum, SCHD_SUB_LINE_NUM_001);
            setValue(linePrcApiPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
            setValue(linePrcApiPMsg.prcBaseDt, schdAgmtApiPMsg.prcBaseDt);
            setValue(linePrcApiPMsg.billToCustCd, schdAgmtApiPMsg.billToCustLocCd);
            setValue(linePrcApiPMsg.shipToCustCd, schdAgmtApiPMsg.shipToCustLocCd);
            setValue(linePrcApiPMsg.dsAcctNum_SH, schdAgmtApiPMsg.shipToCustAcctCd);
            setValue(linePrcApiPMsg.dsAcctNum_BL, schdAgmtApiPMsg.billToCustAcctCd);
            setValue(linePrcApiPMsg.prcCatgCd, schdAgmtApiPMsg.prcCatgCd);
            setValue(linePrcApiPMsg.prcContrNum, schdAgmtApiPMsg.prcContrNum);
            setValue(linePrcApiPMsg.coaBrCd, (String) slsRepInfo.get("COA_BR_CD"));
            setValue(linePrcApiPMsg.ccyCd, getCcyCd(schdAgmtApiPMsg));
            setValue(linePrcApiPMsg.mdseCd, itemLineMsg.mdseCd);
            setValue(linePrcApiPMsg.pkgUomCd, itemLineMsg.pkgUomCd);
            setValue(linePrcApiPMsg.dsOrdLineCatgCd, dsOrdLineCatgCd);
            setValue(linePrcApiPMsg.ordQty, itemLineMsg.schdAllwQty);
            setValue(linePrcApiPMsg.ordCustUomQty, itemLineMsg.schdAllwQty);
            setValue(linePrcApiPMsg.invQty, BigDecimal.ZERO);
            setValue(linePrcApiPMsg.mdlId, schdAgmtApiPMsg.mdlId);
            setValue(linePrcApiPMsg.ctyAddr_SH, schdAgmtApiPMsg.shipToCtyAddr);
            setValue(linePrcApiPMsg.stCd_SH, schdAgmtApiPMsg.shipToStCd);
            setValue(linePrcApiPMsg.postCd_SH, schdAgmtApiPMsg.shipToPostCd);
            setValue(linePrcApiPMsg.ctryCd_SH, schdAgmtApiPMsg.shipToCtryCd);
            setValue(linePrcApiPMsg.slsRepOrSlsTeamTocCd, schdAgmtApiPMsg.slsRepTocCd);
            setValue(linePrcApiPMsg.frtCondCd, schdAgmtApiPMsg.frtCondCd);
            setValue(linePrcApiPMsg.frtChrgToCd, schdAgmtApiPMsg.frtChrgToCd);
            setValue(linePrcApiPMsg.frtChrgMethCd, schdAgmtApiPMsg.frtChrgMethCd);
            setValue(linePrcApiPMsg.shpgSvcLvlCd, schdAgmtApiPMsg.shpgSvcLvlCd);
            setValue(linePrcApiPMsg.xxPrcCloFlg, FLG_OFF_N);
            setValue(linePrcApiPMsg.coaExtnCd, (String) slsRepInfo.get("COA_EXTN_CD"));
            setValue(linePrcApiPMsg.xxUnitPrc, BigDecimal.ZERO);

            // Pricing Element
            BigDecimal schdAgmtLineNum = itemLineMsg.schdAgmtLineNum.getValue();
            int prcElmntCnt = 0;
            Map<String, String> prcCondTpMap = new HashMap<String, String>();

            for (int j = 0; j < schdAgmtApiPMsg.E.getValidCount(); j++) {
                NWZC171001_EPMsg prcCalcMsg = schdAgmtApiPMsg.E.no(j);

                if (schdAgmtLineNum.equals(prcCalcMsg.schdAgmtLineNum.getValue()) || (schdAgmtLineNum.equals(prcCalcMsg.schdAgmtLineNum.getValue().toString()))) {
                    NWZC157003PMsg prcElmntApiPMsg = linePrcApiPMsg.NWZC157003PMsg.no(prcElmntCnt);
                    setValue(prcElmntApiPMsg.trxLineNum, prcCalcMsg.schdAgmtLineNum.getValue().toString());
                    setValue(prcElmntApiPMsg.trxLineSubNum, SCHD_SUB_LINE_NUM_001);
                    setValue(prcElmntApiPMsg.prcCondTpCd, prcCalcMsg.prcCondTpCd);

                    String prcCondTpDescTxt = null;

                    if (hasValue(prcCalcMsg.prcCondTpCd)) {
                        prcCondTpDescTxt = prcCondTpMap.get(prcCalcMsg.prcCondTpCd.getValue());
                        if (!hasValue(prcCondTpDescTxt)) {
                            prcCondTpDescTxt = getPrcCondTpDescTxt(prcCalcMsg.prcCondTpCd.getValue());
                            if (null != prcCondTpDescTxt) {
                                prcCondTpMap.put(prcCalcMsg.prcCondTpCd.getValue(), prcCondTpDescTxt);
                            }
                        }
                    }

                    setValue(prcElmntApiPMsg.prcCondTpDescTxt, prcCondTpDescTxt);
                    setValue(prcElmntApiPMsg.prcDtlGrpCd, prcCalcMsg.prcDtlGrpCd);
                    setValue(prcElmntApiPMsg.prcJrnlGrpCd, prcCalcMsg.prcJrnlGrpCd);
                    setValue(prcElmntApiPMsg.prcCatgCd, schdAgmtApiPMsg.prcCatgCd);
                    setValue(prcElmntApiPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
                    setValue(prcElmntApiPMsg.prcCondManEntryFlg, prcCalcMsg.prcCondManEntryFlg);
                    setValue(prcElmntApiPMsg.prcCondManAddFlg, prcCalcMsg.prcCondManAddFlg);
                    setValue(prcElmntApiPMsg.prcCondManDelFlg, prcCalcMsg.prcCondManDelFlg);
                    setValue(prcElmntApiPMsg.pkgUomCd, prcCalcMsg.pkgUomCd);
                    setValue(prcElmntApiPMsg.prcCondUnitCd, prcCalcMsg.prcCondUnitCd);
                    setValue(prcElmntApiPMsg.prcCalcMethCd, prcCalcMsg.prcCalcMethCd);
                    setValue(prcElmntApiPMsg.autoPrcCcyCd, prcCalcMsg.autoPrcCcyCd);
                    setValue(prcElmntApiPMsg.autoPrcAmtRate, prcCalcMsg.autoPrcAmtRate);
                    setValue(prcElmntApiPMsg.maxPrcAmtRate, prcCalcMsg.maxPrcAmtRate);
                    setValue(prcElmntApiPMsg.minPrcAmtRate, prcCalcMsg.minPrcAmtRate);
                    setValue(prcElmntApiPMsg.manPrcAmtRate, prcCalcMsg.manPrcAmtRate);
                    setValue(prcElmntApiPMsg.calcPrcAmtRate, prcCalcMsg.calcPrcAmtRate);
                    setValue(prcElmntApiPMsg.unitPrcAmt, prcCalcMsg.unitPrcAmt);
                    setValue(prcElmntApiPMsg.dsMdsePrcPk, prcCalcMsg.dsMdsePrcPk);
                    setValue(prcElmntApiPMsg.specCondPrcPk, prcCalcMsg.specCondPrcPk);
                    setValue(prcElmntApiPMsg.frtPerWtPk, prcCalcMsg.frtPerWtPk);
                    setValue(prcElmntApiPMsg.ordPrcCalcBasePk, prcCalcMsg.schdAgmtPrcCalcBasePk);
                    setValue(prcElmntApiPMsg.prcRuleApplyFlg, prcCalcMsg.prcRuleApplyFlg);
                    setValue(prcElmntApiPMsg.prcRulePrcdPk, prcCalcMsg.schdAgmtPrcCalcBasePk);
                    prcElmntCnt++;
                }
            }
            linePrcApiPMsg.NWZC157003PMsg.setValidCount(prcElmntCnt);
            setValue(linePrcApiPMsg.prcCondManEntryFlg, FLG_OFF_N);
            prcLineValidCnt++;
        }
        prcApiPMsg.NWZC157002PMsg.setValidCount(prcLineValidCnt);
        return true;
    }

    /**
     * Get Price Condition Type Desc Text
     * @param glblCmpyCd String
     * @param prcCondTpCd Price Condition Type Code
     * @return Price Condition Type Desc Text
     */
    private String getPrcCondTpDescTxt(String prcCondTpCd) {

        PRC_COND_TPTMsg prcCondTpTMsg = getPrcCondTp(prcCondTpCd);
        if (null != prcCondTpTMsg) {
            return prcCondTpTMsg.prcCondTpDescTxt.getValue();
        }

        return null;
    }

    /**
     * Get Price Condition Type
     * @param bizMsg NWAL1840CMsg
     * @param prcCondTpCd Price Condition Type Code
     * @return Price Condition Type
     */
    private PRC_COND_TPTMsg getPrcCondTp(String prcCondTpCd) {

        PRC_COND_TPTMsg prcCondTpTMsg = new PRC_COND_TPTMsg();
        setValue(prcCondTpTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(prcCondTpTMsg.prcCondTpCd, prcCondTpCd);
        return (PRC_COND_TPTMsg) S21CodeTableAccessor.findByKey(prcCondTpTMsg);
    }

    /**
     * Get CCY Code
     * @param bizMsg NWAL1840CMsg
     * @return CCY Code
     */
    private String getCcyCd(NWZC171001PMsg schdAgmtApiPMsg) {

        if (hasValue(schdAgmtApiPMsg.prcCatgCd)) {
            PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
            setValue(prcCatgTMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(prcCatgTMsg.prcCatgCd, schdAgmtApiPMsg.prcCatgCd);
            prcCatgTMsg = (PRC_CATGTMsg) S21FastTBLAccessor.findByKey(prcCatgTMsg);

            if (null != prcCatgTMsg) {
                return prcCatgTMsg.ccyCd.getValue();
            }
        }

        return null;
    }

    /**
     * Set Price Elements
     * @param bizMsg NWAL1840CMsg
     * @param prcApiPMsg NWZC157001PMsg
     */
    private void setPriceElement(NWZC171001PMsg schdAgmtApiPMsg, NWZC157001PMsg prcApiPMsg) {

        ZYPTableUtil.clear(schdAgmtApiPMsg.E);

        setAmt(schdAgmtApiPMsg, prcApiPMsg);
        setCalBaseData(schdAgmtApiPMsg, prcApiPMsg);
    }

    /**
     * Set Amount
     * @param bizMsg NWAL1840CMsg
     * @param prcApiPMsg NWZC157001PMsg
     * @param itemLineMsgMap Item Line Map
     */
    private void setAmt(NWZC171001PMsg schdAgmtApiPMsg, NWZC157001PMsg prcApiPMsg) {

        BigDecimal totNetPrcAmt = BigDecimal.ZERO;
        BigDecimal totTaxAmt = BigDecimal.ZERO;
        BigDecimal totFrtChrgAmt = BigDecimal.ZERO;
        BigDecimal totAmt = BigDecimal.ZERO;
        StringBuffer itemLineMsgKey = new StringBuffer();

        for (int i = 0; i < prcApiPMsg.NWZC157004PMsg.getValidCount(); i++) {
            NWZC157004PMsg prcTotalPMsg = prcApiPMsg.NWZC157004PMsg.no(i);

            if (CONFIG_CATG.OUTBOUND.equals(prcTotalPMsg.configCatgCd.getValue())) {
                totNetPrcAmt = totNetPrcAmt.add(prcTotalPMsg.xxTotNetPrcAmt.getValue());
                totTaxAmt = totTaxAmt.add(prcTotalPMsg.xxTotTaxAmt.getValue());
                totFrtChrgAmt = totFrtChrgAmt.add(prcTotalPMsg.xxTotFrtAmt.getValue());
                totAmt = totAmt.add(prcTotalPMsg.xxTotAmt.getValue());

                String trxLineNum = prcTotalPMsg.trxLineNum.getValue();
                String trxLineSubNum = prcTotalPMsg.trxLineSubNum.getValue();

                itemLineMsgKey.setLength(0);
                itemLineMsgKey = itemLineMsgKey.append(trxLineNum);
                itemLineMsgKey = itemLineMsgKey.append(".");
                itemLineMsgKey = itemLineMsgKey.append(trxLineSubNum);

            }
        }
    }

    /**
     * Set Calc Base Data
     * @param bizMsg NWAL1840CMsg
     * @param prcApiPMsg NWZC157001PMsg
     * @param itemLineMsgMap Item Line Map
     */
    private void setCalBaseData(NWZC171001PMsg schdAgmtApiPMsg, NWZC157001PMsg prcApiPMsg) {

        int calcBaseValidCnt = schdAgmtApiPMsg.E.getValidCount();
        int i = 0;

        for (i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg prcLinePMsg = prcApiPMsg.NWZC157002PMsg.no(i);
            if (!CONFIG_CATG.OUTBOUND.equals(prcLinePMsg.configCatgCd.getValue())) {
                continue;
            }

            for (int j = 0; j < prcLinePMsg.NWZC157003PMsg.getValidCount(); j++) {
                NWZC157003PMsg prcElementPMsg = prcLinePMsg.NWZC157003PMsg.no(j);
                String configCatgCd = prcElementPMsg.configCatgCd.getValue();
                if (!CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
                    continue;
                }

                NWZC171001_EPMsg calcBaseCMsg = schdAgmtApiPMsg.E.no(calcBaseValidCnt);
                setValue(calcBaseCMsg.schdAgmtPrcCalcBasePk, prcElementPMsg.ordPrcCalcBasePk);
                setValue(calcBaseCMsg.schdAgmtLineNum, new BigDecimal(prcElementPMsg.trxLineNum.getValue()));
                setValue(calcBaseCMsg.prcCondTpCd, prcElementPMsg.prcCondTpCd);
                setValue(calcBaseCMsg.prcDtlGrpCd, prcElementPMsg.prcDtlGrpCd);
                setValue(calcBaseCMsg.prcJrnlGrpCd, prcElementPMsg.prcJrnlGrpCd);
                setValue(calcBaseCMsg.pkgUomCd, prcElementPMsg.pkgUomCd);
                setValue(calcBaseCMsg.prcCondUnitCd, prcElementPMsg.prcCondUnitCd);
                setValue(calcBaseCMsg.prcCalcMethCd, prcElementPMsg.prcCalcMethCd);
                setValue(calcBaseCMsg.dsMdsePrcPk, prcElementPMsg.dsMdsePrcPk);
                setValue(calcBaseCMsg.specCondPrcPk, prcElementPMsg.specCondPrcPk);
                setValue(calcBaseCMsg.frtPerWtPk, prcElementPMsg.frtPerWtPk);
                setValue(calcBaseCMsg.prcCondManEntryFlg, prcElementPMsg.prcCondManEntryFlg);
                setValue(calcBaseCMsg.prcCondManAddFlg, prcElementPMsg.prcCondManAddFlg);
                setValue(calcBaseCMsg.prcCondManDelFlg, prcElementPMsg.prcCondManDelFlg);
                setValue(calcBaseCMsg.autoPrcAmtRate, prcElementPMsg.autoPrcAmtRate);
                setValue(calcBaseCMsg.maxPrcAmtRate, prcElementPMsg.maxPrcAmtRate);
                setValue(calcBaseCMsg.minPrcAmtRate, prcElementPMsg.minPrcAmtRate);
                setValue(calcBaseCMsg.manPrcAmtRate, prcElementPMsg.manPrcAmtRate);
                setValue(calcBaseCMsg.calcPrcAmtRate, prcElementPMsg.calcPrcAmtRate);
                setValue(calcBaseCMsg.unitPrcAmt, prcElementPMsg.unitPrcAmt);
                setValue(calcBaseCMsg.autoPrcCcyCd, prcElementPMsg.autoPrcCcyCd);
                setValue(calcBaseCMsg.prcRuleApplyFlg, prcElementPMsg.prcRuleApplyFlg);
                setValue(calcBaseCMsg.prcRulePrcdPk, prcElementPMsg.prcRulePrcdPk);

                calcBaseValidCnt++;
            }
        }
        schdAgmtApiPMsg.E.setValidCount(calcBaseValidCnt);
    }

    /**
     * get Sales Rep Info
     * @param slsRepTocCd Sales Rep TOC Code
     * @return Sales Rep Info
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getSlsRepInfo(String slsRepTocCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("slsRepTocCd", slsRepTocCd);

        return (Map<String, Object>) ssmBatchClient.queryObject("getSlsRepInfo", params);
    }

    private String getSvcLineBizCd(BigDecimal svcConfigMstrPk) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("svcConfigMstrPk", svcConfigMstrPk);
        params.put("machTpCd", SVC_MACH_TP.MACHINE);

        return (String) ssmBatchClient.queryObject("getSvcLineBizCd", params);
    }

    private boolean checkPoNum(NWZC171001PMsg schdAgmtApiPMsg) {
        boolean errFlg = false;

        String trxRuleTp = getTrxRuleTp(schdAgmtApiPMsg.dsOrdCatgCd.getValue(), schdAgmtApiPMsg.dsOrdTpCd.getValue());

        NMZC610001PMsg pMsg = new NMZC610001PMsg();
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_TRANSACTION);
        setValue(pMsg.dsTrxRuleTpCd, trxRuleTp);
        setValue(pMsg.dsAcctNum_I1, schdAgmtApiPMsg.sellToCustCd);
        setValue(pMsg.billToCustCd, schdAgmtApiPMsg.soldToCustLocCd);
        new NMZC610001().execute(pMsg, ONBATCH_TYPE.BATCH);

        if (pMsg.TransactionRuleList.getValidCount() > 0) {
            for (int i = 0; i < pMsg.TransactionRuleList.getValidCount(); i++) {
                String dsPoReqFlg = pMsg.TransactionRuleList.no(i).dsPoReqFlg.getValue();
                if (FLG_ON_Y.equals(dsPoReqFlg)) {
                    if (!hasValue(schdAgmtApiPMsg.custIssPoNum)) {
                        errFlg = true;
                        break;
                    }
                }
            }
        }

        return errFlg;
    }

    private String getTrxRuleTp(String dsOrdCatgCd, String dsOrdTpCd) {

        ORD_CATG_BIZ_CTXTMsg tMsg = getOrdCatgBizCtx(dsOrdCatgCd, dsOrdTpCd);

        if (tMsg == null) {
            return null;
        }
        return tMsg.firstBizCtxAttrbTxt.getValue();
    }

    private ORD_CATG_BIZ_CTXTMsg getOrdCatgBizCtx(String dsOrdCatgCd, String dsOrdTpCd) {

        ORD_CATG_BIZ_CTXTMsg inTMsg = new ORD_CATG_BIZ_CTXTMsg();
        inTMsg.setSQLID("005");
        inTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inTMsg.setConditionValue("ordCatgCtxTpCd01A", EQUIPMENT_ORDER_VALUE_SET);
        inTMsg.setConditionValue("ordCatgCtxTpCd01B", SUPPLIES_ORDER_VALUE_SET);
        inTMsg.setConditionValue("dsOrdCatgCd01", dsOrdCatgCd);
        inTMsg.setConditionValue("dsOrdTpCd01", dsOrdTpCd);
        ORD_CATG_BIZ_CTXTMsgArray array = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);

        if (array == null || array.length() == 0) {
            return null;
        }
        return array.no(0);
    }

    /**
     * Get Customer And Additional Data
     * @param bizMsg NWAL1840CMsg
     * @return Customer And Additional Data
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getCustAddlInfo(String dsOrdTpCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("dsOrdTpCd", dsOrdTpCd);
        params.put("effDt", this.slsDt);
        params.put("rgtnSts", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (Map<String, Object>) ssmBatchClient.queryObject("getCustAddlInfo", params);
    }

    /**
     * @param bizMsg
     * @return
     */
    private String getDsLineCatgCd(String dsOrdTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        ssmParam.put("dsOrdLineDrctnCd", DS_ORD_LINE_DRCTN.OUTBOUND);
        ssmParam.put("slsDt", this.slsDt);

        return (String) ssmBatchClient.queryObject("getDsLineCatgCd", ssmParam);
    }

    private boolean isCanceledSchedule(NWZC171003PMsg schdLine) {
        if (FLG_ON_Y.equals(schdLine.schdAgmtPlnCancFlg.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * @param schdAgmtStsCd
     * @return
     */
    private boolean checkSchdAgmtSts(String schdAgmtStsCd) {
        if (!SCHD_AGMT_STS.ACTIVE.equals(schdAgmtStsCd)) {
            return false;
        }
        return true;
    }

    private void setErrMsg(String schdAgmtNum, String msgId, String[] msgPrms) {
        setErrMsg(schdAgmtNum, HDR_LINE, msgId, msgPrms);
    }

    private void setErrMsg(String schdAgmtNum, BigDecimal schdAgmtLineNum, String msgId, String[] msgPrms) {
        Map<BigDecimal, String> lineMsgMap;
        if (this.errMap.containsKey(schdAgmtNum)) {
            lineMsgMap = this.errMap.get(schdAgmtNum);
        } else {
            lineMsgMap = new HashMap<BigDecimal, String>();
        }
        String tmpMsg = S21MessageFunc.clspGetMessage(msgId, msgPrms);
        StringBuffer msgBuilder = new StringBuffer();
        msgBuilder.append(tmpMsg);
        lineMsgMap.put(schdAgmtLineNum, msgBuilder.toString());
        this.errMap.put(schdAgmtNum, lineMsgMap);
    }

    private void processResultRegist(SCHD_AGMT_ADJ_CONTRTMsg schdAgmtAdjContr) {
        if (schdAgmtAdjContr == null) {
            return;
        }
        Boolean hasSuccessRecord = false;
        String schdAgmtNum = schdAgmtAdjContr.schdAgmtNum.getValue();
        SCHD_AGMTTMsg schdAgmt = getSchdAgmt(schdAgmtNum);
        SCHD_AGMT_LINETMsgArray schdAgmtLineList = getSchdAgmtLine(schdAgmtNum);
        for (int i = 0; i < schdAgmtLineList.getValidCount(); i++) {
            SCHD_AGMT_LINETMsg schdAgmtLine = schdAgmtLineList.no(i);
            BigDecimal schdAgmtLineNum = schdAgmtLine.schdAgmtLineNum.getValue();
            SCHD_AGMT_ADJ_CONTR_RSLTTMsg inMsg = new SCHD_AGMT_ADJ_CONTR_RSLTTMsg();
            setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            BigDecimal schdAgmtAdjContrRsltPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SCHD_AGMT_ADJ_CONTR_RSLT_SQ);
            setValue(inMsg.schdAgmtAdjContrRsltPk, schdAgmtAdjContrRsltPk);
            setValue(inMsg.schdAgmtAdjContrPk, schdAgmtAdjContr.schdAgmtAdjContrPk);
            setValue(inMsg.schdAgmtLineNum, schdAgmtLine.schdAgmtLineNum);
            setValue(inMsg.mdseCd, schdAgmtLine.mdseCd);
            setValue(inMsg.dsOrdCatgCd, schdAgmt.dsOrdCatgCd);
            setValue(inMsg.dsOrdTpCd, schdAgmt.dsOrdTpCd);
            setValue(inMsg.svcLineBizCd, getSvcLineBizCd(schdAgmtAdjContr.svcConfigMstrPk.getValue()));
            if (this.errMap.containsKey(schdAgmtNum)) {
                setValue(inMsg.schdAgmtAdjStsCd, SCHD_AGMT_ADJ_STS.PROCESSED);
                if (this.errMap.get(schdAgmtNum).containsKey(schdAgmtLineNum)) {
                    setValue(inMsg.errMsgTxt, this.errMap.get(schdAgmtNum).get(schdAgmtLineNum));
                    setValue(inMsg.schdAgmtAdjStsCd, SCHD_AGMT_ADJ_STS.ERROR);
                } else if (this.errMap.get(schdAgmtNum).containsKey(HDR_LINE)) {
                    setValue(inMsg.errMsgTxt, this.errMap.get(schdAgmtNum).get(HDR_LINE));
                    setValue(inMsg.schdAgmtAdjStsCd, SCHD_AGMT_ADJ_STS.ERROR);
                } else {
                    hasSuccessRecord = true;
                }
            } else {
                setValue(inMsg.schdAgmtAdjStsCd, SCHD_AGMT_ADJ_STS.PROCESSED);
                hasSuccessRecord = true;
            }
            S21ApiTBLAccessor.insert(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdAgmtAdjContr.getReturnCode())) {
                throw new S21AbendException(NWAM0729E, new String[] {"SCHD_AGMT_ADJ_CONTR_RSLT" });
            }
        }
        if (hasSuccessRecord) {
            updateSchdAgmtAdjContr(schdAgmtAdjContr, SCHD_AGMT_ADJ_STS.PROCESSED);
            this.infoSccessCnt++;
        } else {
            updateSchdAgmtAdjContr(schdAgmtAdjContr, SCHD_AGMT_ADJ_STS.ERROR);
        }
        // Clear Error Message
        this.errMap = new HashMap<String, Map<BigDecimal, String>>();
    }
}
