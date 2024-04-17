/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB098001;

import static com.canon.cusa.s21.batch.NSB.NSBB098001.NSBB098001Constant.*;

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
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CALTMsg;
import business.db.CALTMsgArray;
import business.db.DS_COND_CONSTTMsg;
import business.db.DS_COND_CONSTTMsgArray;
import business.db.DS_SVC_CALL_TPTMsg;
import business.db.FSRTMsg;
import business.db.FSR_VISITTMsg;
import business.db.FSR_VISITTMsgArray;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_PBLM_TPTMsg;
import business.db.SVC_PBLM_TPTMsgArray;
import business.db.SVC_PRC_SHIFTTMsg;
import business.db.SVC_PRC_SHIFTTMsgArray;
import business.db.SVC_TASK_KEY_INFOTMsg;
import business.db.SVC_TASK_OTBD_MSGTMsg;
import business.db.SVC_TASK_RQST_DUPTMsg;
import business.parts.NSZC043001PMsg;
import business.parts.NSZC045001PMsg;
import business.parts.NSZC133001PMsg;
import business.parts.NSZC134001PMsg;
import business.parts.NSZC135001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC045001.NSZC045001;
import com.canon.cusa.s21.api.NSZ.NSZC133001.NSZC133001;
import com.canon.cusa.s21.api.NSZ.NSZC134001.NSZC134001;
import com.canon.cusa.s21.api.NSZ.NSZC135001.NSZC135001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SVC_CALL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.OTBD_INTFC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_RQST_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeUtil;
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
 *  Create Task From Walmart Request
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/03/15   Hitachi         M.Nakajima        Create        QC#60012
 * 2023/07/10   Hitachi         N.Takatsu         Update        QC#60012
 * 2023/07/28   Hitachi         N.Takatsu         Update        QC#60012
 * 2023/08/03   Hitachi         K.Kitachi         Update        QC#60012
 * 2023/08/08   Hitachi         N.Takatsu         Update        QC#61771
 * 2023/08/22   Hitachi         K.Kitachi         Update        QC#61771
 * 2023/09/01   Hitachi         N.Takatsu         Update        QC#61771
 * 2024/02/14   CITS            T.Aizawa          Update        QC#61771
 *</pre>
 */

public class NSBB098001 extends S21BatchMain {

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

    /** svcPblmTpCd */
    private String svcPblmTpCd = null;

    /** issueDetails */
    private String issueDetails = null;

    /** custMemoTxt */
    private String custMemoTxt = null;

    /** Register State List */
    private List<String> rqstModeRegisterList;

    /** Cancel State List */
    private List<String> rqstModeCancelList;

    /** Update State List */
    private List<String> rqstModeUpdateList;

    /** Execute Param */
    S21SsmExecutionParameter execParam = null;

    // START 2023/07/10 N.Takatsu [QC#60012, ADD]
    /** VAR_CHAR_CONST : NSBB0170_CRS_SVC_NOT_EXIST_EML */
    private String ctacPsnEmlAddr = null;
    // END   2023/07/10 N.Takatsu [QC#60012, ADD]
    // START 2023/07/28 N.Takatsu [QC#60012, ADD]
    private int recordType = 0;
    // END   2023/07/28 N.Takatsu [QC#60012, ADD]

    // START 2023/08/03 K.Kitachi [QC#60012, ADD]
    /** Out of Territory Service Branch Code */
    private String outTrtySvcBrCd = null;

    /** Click send exclusion call type list */
    private List<String> clickSendExclCallTpList;

    /** Cross Service Dummy Tech Code */
    private String essDummyTech = null;

    /** Zone Map */
    private Map<String, DS_COND_CONSTTMsg> zoneMap = null;
    // END   2023/08/03 K.Kitachi [QC#60012, ADD]

    /**
     * @param args parameter
     */
    public static void main(String[] args) {
        new NSBB098001().executeBatch(NSBB098001.class.getSimpleName());
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
        // slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        slsDt = ZYPDateUtil.getSalesDate("ADB");
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            throw new S21AbendException(NSBM0032E, new String[] {"Sales Date" });
        }
        // START 2023/07/10 N.Takatsu [QC#60012, ADD]
        this.ctacPsnEmlAddr = ZYPCodeDataUtil.getVarCharConstValue(WALMART_NOT_EXIST_EML, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.ctacPsnEmlAddr)) {
            throw new S21AbendException(NSBM0069E, new String[] {WALMART_NOT_EXIST_EML });
        }
        // END   2023/07/10 N.Takatsu [QC#60012, ADD]

        // START 2023/08/03 K.Kitachi [QC#60012, ADD]
        this.outTrtySvcBrCd = ZYPCodeDataUtil.getVarCharConstValue(OUT_TRTY_SVC_BR_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.outTrtySvcBrCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {OUT_TRTY_SVC_BR_CD });
        }

        String clickSendExclCallTp = ZYPCodeDataUtil.getVarCharConstValue(CLICK_SEND_EXCL_CALL_TP, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(clickSendExclCallTp)) {
            throw new S21AbendException(NSBM0069E, new String[] {CLICK_SEND_EXCL_CALL_TP });
        }
        String[] clickSendExclCallTpStrList = clickSendExclCallTp.split(",");
        this.clickSendExclCallTpList = new ArrayList<String>();
        this.clickSendExclCallTpList = Arrays.asList(clickSendExclCallTpStrList);

        this.essDummyTech = ZYPCodeDataUtil.getVarCharConstValue(ESS_DUMMY_TECH, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.essDummyTech)) {
            throw new S21AbendException(NSBM0069E, new String[] {ESS_DUMMY_TECH });
        }

        createZoneMap();
        // END   2023/08/03 K.Kitachi [QC#60012, ADD]

        execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
    }

    @Override
    protected void mainRoutine() {

        // create SVC_TASK_RQST_DUP table from SVC_TASK_RQST_ORIG
        // table.
        createDupFromOrig();
        commit();

        createStateListFromVarCharConst();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            // get request data
            stmt = ssmLLClient.createPreparedStatement("getSvcTaskRqst", getSvcTaskRqstParams(), execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {

                if (processRequestData(rs)) {
                    totalNmlCount++;

                } else {
                    totalErrCount++;
                }

                commit();

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

    private void createDupFromOrig() {

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) getOrig();
        if (resultList.size() > 0) {
            for (Map<String, Object> result : resultList) {
                StringBuilder sb = new StringBuilder();
                // set SVC_TASK_RQST_DUP
                SVC_TASK_RQST_DUPTMsg inTMsg = new SVC_TASK_RQST_DUPTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.svcTaskRqstDupPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TASK_RQST_DUP_SQ));
                ZYPEZDItemValueSetter.setValue(inTMsg.svcTaskRqstOrigPk, (BigDecimal) result.get("SVC_TASK_RQST_ORIG_PK"));
                ZYPEZDItemValueSetter.setValue(inTMsg.rqstModeTxt, (String) result.get("RQST_MODE_TXT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.svcCallSrcTpCd, (String) result.get("SVC_CALL_SRC_TP_CD"));
                ZYPEZDItemValueSetter.setValue(inTMsg.cratTs, (String) result.get("CRAT_TS"));
                String serNum = (String) result.get("SER_NUM_LONG_TXT");
                if (ZYPCommonFunc.hasValue(serNum)) {
                    serNum = serNum.replaceFirst("^[\\s]+", "").replaceFirst("[\\s]+$", "");
                    if (serNum.length() > SER_NUM_LENGTH) {
                        // START 2023/08/22 K.Kitachi [QC#61771, MOD]
//                        sb.append(NSBM0211E + ":" + getRtnMsg(NSBM0211E, new String[] {"u_serial_number", serNum, (String) result.get("CUST_CALL_ID") }));
                        // START 2024/02/14 t.aizawa [QC#61771, MOD]
                        // sb.append(getRtnMsg(NSBM0211E, new String[] {"u_serial_number", serNum, (String) result.get("CUST_CALL_ID") }));
                        sb.append(getRtnMsg(NSBM0211E, new String[] {"Serial Number", serNum, (String) result.get("CUST_CALL_ID") }));
                        // END   2024/02/14 t.aizawa [QC#61771, MOD]
                        // END   2023/08/22 K.Kitachi [QC#61771, MOD]
                        serNum = serNum.substring(0, SER_NUM_LENGTH);
                    }
                }
                ZYPEZDItemValueSetter.setValue(inTMsg.serNum, serNum);
                ZYPEZDItemValueSetter.setValue(inTMsg.rqstModeDescTxt, (String) result.get("RQST_MODE_DESC_TXT"));
                String fsrNum = (String) result.get("FSR_LONG_TXT");
                if (ZYPCommonFunc.hasValue(fsrNum)) {
                    fsrNum = fsrNum.replaceFirst("^[\\s]+", "").replaceFirst("[\\s]+$", "");
                    if (fsrNum.length() > FSR_NUM_LENGTH) {
                        if (sb.length() > 0) {
                            sb.append(" | ");
                        }
                        // START 2023/08/22 K.Kitachi [QC#61771, MOD]
//                        sb.append(NSBM0211E + ":" + getRtnMsg(NSBM0211E, new String[] {"correlation_id", fsrNum, (String) result.get("CUST_CALL_ID") }));
                        sb.append(getRtnMsg(NSBM0211E, new String[] {"correlation_id", fsrNum, (String) result.get("CUST_CALL_ID") }));
                        // END   2023/08/22 K.Kitachi [QC#61771, MOD]
                        fsrNum = fsrNum.substring(0, FSR_NUM_LENGTH);
                    }
                }
                ZYPEZDItemValueSetter.setValue(inTMsg.fsrNum, fsrNum);
                ZYPEZDItemValueSetter.setValue(inTMsg.ctacPsnEmlAddr, (String) result.get("CTAC_PSN_EML_ADDR"));
                String ctacPsnCellPhoNum = (String) result.get("CTAC_PSN_CELL_PHO_TXT");
                if (ZYPCommonFunc.hasValue(ctacPsnCellPhoNum)) {
                    ctacPsnCellPhoNum = ctacPsnCellPhoNum.trim().replaceAll("[^-0-9]+", "");
                    if (ctacPsnCellPhoNum.length() > CTAC_PSN_CELL_PHO_NUM_LENGTH) {
                        if (sb.length() > 0) {
                            sb.append(" | ");
                        }
                        // START 2023/08/22 K.Kitachi [QC#61771, MOD]
//                        sb.append(NSBM0211E + ":" + getRtnMsg(NSBM0211E, new String[] {"u_callback_info", ctacPsnCellPhoNum, (String) result.get("CUST_CALL_ID") }));
                        // START 2024/02/14 t.aizawa [QC#61771, MOD]
                        // sb.append(getRtnMsg(NSBM0211E, new String[] {"u_callback_info", ctacPsnCellPhoNum, (String) result.get("CUST_CALL_ID") }));
                        sb.append(getRtnMsg(NSBM0211E, new String[] {"Callback Number", ctacPsnCellPhoNum, (String) result.get("CUST_CALL_ID") }));
                        // END   2024/02/14 t.aizawa [QC#61771, MOD]
                        // END   2023/08/22 K.Kitachi [QC#61771, MOD]
                        ctacPsnCellPhoNum = ctacPsnCellPhoNum.substring(0, CTAC_PSN_CELL_PHO_NUM_LENGTH);
                    }
                }
                ZYPEZDItemValueSetter.setValue(inTMsg.ctacPsnCellPhoNum, ctacPsnCellPhoNum);
                ZYPEZDItemValueSetter.setValue(inTMsg.svcCustAttnTxt, (String) result.get("SVC_CUST_ATTN_TXT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.svcCmntTxt, (String) result.get("SVC_CMNT_TXT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.custCloseCatgTxt, (String) result.get("CUST_CLOSE_CATG_TXT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.custCloseDescTxt, (String) result.get("CUST_CLOSE_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.custMemoTxt, (String) result.get("CUST_MEMO_TXT"));
                Map<String, Object> svcMachMstrMap = getSvcMachMstr(serNum);
                String dtTmUTC = (String) result.get("RCV_TM_DESC_TXT");
                if (ZYPCommonFunc.hasValue(dtTmUTC)) {

                    String timeUTC = dtTmUTC.replaceAll("[^0-9]", "") + FILLER_MSEC_STRING;
                    // convert UTC to sys_time
                    String dtTmSYS = ZYPLocalTimeUtil.convertTimeUTC2Sys(timeUTC);
                    if (svcMachMstrMap == null) {
                        ZYPEZDItemValueSetter.setValue(inTMsg.rcvTmDescTxt, dtTmSYS.substring(0, LENGTH_TS));
                        ZYPEZDItemValueSetter.setValue(inTMsg.svcTaskRcvDt, dtTmSYS.substring(0, LENGTH_DT));
                        ZYPEZDItemValueSetter.setValue(inTMsg.svcTaskRcvTm, dtTmSYS.substring(LENGTH_DT, LENGTH_TS));
                    } else {
                        // convert sys_time to local time
                        SvcTimeZoneInfo convSysToLoc = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, dtTmSYS, (String) svcMachMstrMap.get("CTRY_CD"), (String) svcMachMstrMap.get("POST_CD"));
                        if (convSysToLoc != null) {
                            ZYPEZDItemValueSetter.setValue(inTMsg.rcvTmDescTxt, convSysToLoc.getDateTime().substring(0, LENGTH_TS));
                            ZYPEZDItemValueSetter.setValue(inTMsg.svcTaskRcvDt, convSysToLoc.getDateTime().substring(0, LENGTH_DT));
                            ZYPEZDItemValueSetter.setValue(inTMsg.svcTaskRcvTm, convSysToLoc.getDateTime().substring(LENGTH_DT, LENGTH_TS));
                        } else {
                            ZYPEZDItemValueSetter.setValue(inTMsg.rcvTmDescTxt, dtTmSYS.substring(0, LENGTH_TS));
                            ZYPEZDItemValueSetter.setValue(inTMsg.svcTaskRcvDt, dtTmSYS.substring(0, LENGTH_DT));
                            ZYPEZDItemValueSetter.setValue(inTMsg.svcTaskRcvTm, dtTmSYS.substring(LENGTH_DT, LENGTH_TS));
                        }
                    }
                }
                ZYPEZDItemValueSetter.setValue(inTMsg.custSysId, (String) result.get("CUST_SYS_ID"));
                ZYPEZDItemValueSetter.setValue(inTMsg.custCallId, (String) result.get("CUST_CALL_ID"));
                ZYPEZDItemValueSetter.setValue(inTMsg.svcRqstProcStsCd, SVC_RQST_PROC_STS.REQUESTED);
                ZYPEZDItemValueSetter.setValue(inTMsg.errInfoTxt, sb.toString());

                EZDTBLAccessor.create(inTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                    throw new S21AbendException(NSBM0120E, new String[] {"SVC_TASK_RQST_DUP" });
                }
            }
        }
    }

    private List<Map<String, Object>> getOrig() {
        Map<String, Object> origPrms = new HashMap<String, Object>();
        origPrms.put("glblCmpyCd", this.glblCmpyCd);

        return (List<Map<String, Object>>) this.ssmClient.queryObjectList("getOrig", origPrms);
    }

    private void createStateListFromVarCharConst() {

        // Mode Register
        String walmartStateRegister = ZYPCodeDataUtil.getVarCharConstValue(WALMART_STATE_REGISTER, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(walmartStateRegister)) {
            throw new S21AbendException(NSBM0069E, new String[] {WALMART_STATE_REGISTER });
        }
        String[] regisertStrList = walmartStateRegister.split(",");
        this.rqstModeRegisterList = new ArrayList<String>();
        this.rqstModeRegisterList = Arrays.asList(regisertStrList);

        // Mode Cancel
        String walmartStateCancel = ZYPCodeDataUtil.getVarCharConstValue(WALMART_STATE_CANCEL, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(walmartStateCancel)) {
            throw new S21AbendException(NSBM0069E, new String[] {WALMART_STATE_CANCEL });
        }
        String[] cancelStrList = walmartStateCancel.split(",");
        this.rqstModeCancelList = new ArrayList<String>();
        this.rqstModeCancelList = Arrays.asList(cancelStrList);

        // Mode Update
        String walmartStatUpdate = ZYPCodeDataUtil.getVarCharConstValue(WALMART_STATE_UPDATE, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(walmartStatUpdate)) {
            throw new S21AbendException(NSBM0069E, new String[] {WALMART_STATE_UPDATE });
        }
        String[] updateStrList = walmartStatUpdate.split(",");
        this.rqstModeUpdateList = new ArrayList<String>();
        this.rqstModeUpdateList = Arrays.asList(updateStrList);
    }

    private Map<String, Object> getSvcTaskRqstParams() {
        List<String> svcRqstProcStsCdList = new ArrayList<String>();
        svcRqstProcStsCdList.add(SVC_RQST_PROC_STS.REQUESTED);
        svcRqstProcStsCdList.add(SVC_RQST_PROC_STS.UNPROCESSED);
        svcRqstProcStsCdList.add(SVC_RQST_PROC_STS.OUTBOUNDERROR);

        Map<String, Object> prms = new HashMap<String, Object>();
        prms.put("glblCmpyCd", this.glblCmpyCd);
        prms.put("svcRqstProcStsCdList", svcRqstProcStsCdList);
        return prms;
    }

    private boolean processRequestData(ResultSet rs) throws SQLException {

        boolean result = false;
        // START 2023/07/28 N.Takatsu [QC#60012, MOD]
//        int recordType = identifyRecordType(rs);
        this.recordType = identifyRecordType(rs);
        // END   2023/07/28 N.Takatsu [QC#60012, MOD]
        switch (recordType) {
            case RECORD_TYPE_REGISTER:
                result = registerFsr(rs);
                break;
            case RECORD_TYPE_CANCEL:
                result = cancelTask(rs);
                break;
            case RECORD_TYPE_UPDATE:
                result = updateNote(rs);
                break;
            default:
                String errMsg = getRtnMsg(NSBM0216E, new String[] {"state" });
                updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, errMsg, null);
                sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0216E, new String[] {"state" }, null, null));
                // START 2023/07/28 N.Takatsu [QC#60012, DEL]
//                executeOtbdApiIfRegisterFail(rs, errMsg);
                // END   2023/07/28 N.Takatsu [QC#60012, DEL]
                result = false;
                break;
        }
        return result;
    }

    private int identifyRecordType(ResultSet rs) throws SQLException {

        if (rqstModeRegisterList.contains(rs.getString("RQST_MODE_TXT"))) {
            // START 2023/07/28 N.Takatsu [QC#60012, ADD]
            if (!hasValue(rs.getString("FSR_NUM"))) {
                return RECORD_TYPE_REGISTER;
            }
            // END   2023/07/28 N.Takatsu [QC#60012, ADD]
        }

        if (rqstModeCancelList.contains(rs.getString("RQST_MODE_TXT"))) {
            return RECORD_TYPE_CANCEL;
        } else if (rqstModeUpdateList.contains(rs.getString("RQST_MODE_TXT"))) {
            // START 2023/07/28 N.Takatsu [QC#60012, ADD]
            if (hasValue(rs.getString("FSR_NUM"))) {
                return RECORD_TYPE_UPDATE;
            }
            // END   2023/07/28 N.Takatsu [QC#60012, ADD]
        } else {
            return -1;
        }
        // START 2023/07/28 N.Takatsu [QC#60012, ADD]
        return -1;
        // END   2023/07/28 N.Takatsu [QC#60012, ADD]
    }

    private boolean registerFsr(ResultSet rs) throws SQLException {

        String errMsg = null;

        if (SVC_RQST_PROC_STS.REQUESTED.equals(rs.getString("SVC_RQST_PROC_STS_CD")) && ZYPCommonFunc.hasValue(rs.getString("ERR_INFO_TXT"))) {
            errMsg = rs.getString("ERR_INFO_TXT");
            updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, errMsg, null);
            executeOtbdApiIfRegisterFail(rs, errMsg);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, errMsg));
            return false;
        }

        this.issueDetails = extractStr(rs.getString("SVC_CMNT_TXT"), Pattern.compile(ZYPCodeDataUtil.getVarCharConstValue(WALMART_ISSUE_DTL_PTRN, this.glblCmpyCd)), 2);
        this.svcPblmTpCd = extractStr(rs.getString("SVC_CMNT_TXT"), Pattern.compile(ZYPCodeDataUtil.getVarCharConstValue(WALMART_SVC_PBLM_TP_PTRN, this.glblCmpyCd)), 2);
        if (ZYPCommonFunc.hasValue(this.svcPblmTpCd)) {
            this.svcPblmTpCd = this.svcPblmTpCd.substring(0, SVC_PBLM_TP_CD_LENGTH);
        }
        this.custMemoTxt = null;
        if (ZYPCommonFunc.hasValue(rs.getString("CUST_MEMO_TXT"))) {
            this.custMemoTxt = rs.getString("CUST_MEMO_TXT");
        }

        boolean errFlg = false;

        errMsg = inputDataCheckRegisterFsr(rs);
        if (ZYPCommonFunc.hasValue(errMsg)) {
            errFlg = true;
        }

        String custCallId = rs.getString("CUST_CALL_ID");

        if (duplicateWithPrevIf(rs)) {
            String errInfoTxt = getRtnMsg(NSBM0214E, new String[] {"number ", custCallId });
            errMsg = errInfoTxt;
            updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.EXCLUDED, null, errInfoTxt, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0214E, new String[] {"number", custCallId }, null, null));
            errFlg = true;
        }

        if (!SVC_RQST_PROC_STS.OUTBOUNDERROR.equals(rs.getString("SVC_RQST_PROC_STS_CD"))) {

            if (errFlg) {
                // execute outbound API.
                executeOtbdApiIfRegisterFail(rs, errMsg);
                return false;
            }

            String serNum = rs.getString("SER_NUM");
            Map<String, Object> svcMachMstr = getSvcMachMstr(serNum);

            // call FSR update API(NSZC043001)
            // START 2023/07/10 N.Takatsu [QC#60012, MOD]
            String getCtacPsnEmlAddr = rs.getString("CTAC_PSN_EML_ADDR");
            if (!hasValue(getCtacPsnEmlAddr)) {
                getCtacPsnEmlAddr = this.ctacPsnEmlAddr;
            }
//            NSZC043001PMsg pMsg = createNSZC043001PMsgRegisterFsr((BigDecimal) svcMachMstr.get("SVC_MACH_MSTR_PK"), rs.getString("SVC_TASK_RCV_DT"), rs.getString("SVC_TASK_RCV_TM"), rs.getString("CTAC_PSN_CELL_PHO_NUM"), rs
//                    .getString("SVC_CUST_ATTN_TXT"), custCallId, rs.getString("SVC_CALL_SRC_TP_CD"));
            NSZC043001PMsg pMsg = createNSZC043001PMsgRegisterFsr((BigDecimal) svcMachMstr.get("SVC_MACH_MSTR_PK"), rs.getString("SVC_TASK_RCV_DT"), rs.getString("SVC_TASK_RCV_TM"), rs.getString("CTAC_PSN_CELL_PHO_NUM"), rs
                    .getString("SVC_CUST_ATTN_TXT"), custCallId, rs.getString("SVC_CALL_SRC_TP_CD"), getCtacPsnEmlAddr);
            // End   2023/07/10 N.Takatsu [QC#60012, MOD]

            // call creation
            if (!callApiNSZC043001(pMsg)) {
                rollback();

                // update SVC_TASK_RQST_DUP
                StringBuilder sb = new StringBuilder();
                if (S21ApiUtil.getXxMsgList(pMsg).size() > 0) {
                    for (S21ApiMessage msg : S21ApiUtil.getXxMsgList(pMsg)) {
                        if (sb.length() > 0) {
                            // START 2023/08/22 K.Kitachi [QC#61771, MOD]
//                            sb.append(" : ");
                            sb.append(" | ");
                            // END   2023/08/22 K.Kitachi [QC#61771, MOD]
                        }
                        String msgId = msg.getXxMsgid();
                        String[] msgPrms = msg.getXxMsgPrmArray();
                        String errInfoTxt = getRtnMsg(msgId, msgPrms);
                        sb.append(errInfoTxt);
                    }
                    errMsg = sb.toString();
                }
                updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, errMsg, null);

                // set mail param
                S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                sendRequestDataErrorMail(createMailTemplateParamMap(rs, msgId, msgPrms, null, null));

                errFlg = true;
            }

            if (errFlg) {
                // execute outbound API.
                executeOtbdApiIfRegisterFail(rs, errMsg);
                return false;
            }

            if (!errFlg) {
                commit();

                List<String> svcTaskNumList = new ArrayList<String>();
                for (int i = 0; i < pMsg.taskList.length(); i++) {
                    svcTaskNumList.add(pMsg.taskList.no(i).svcTaskNum.getValue());
                }
                String fsrNum = pMsg.fsrNum.getValue();

                // set Incident to FSR
                FSRTMsg tMsg = new FSRTMsg();
                setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                setValue(tMsg.fsrNum, fsrNum);
                tMsg = (FSRTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                setValue(tMsg.xtrnlSysIncdtNum, custCallId);

                EZDTBLAccessor.update(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    throw new S21AbendException(NSBM0120E, new String[] {"FSR" });
                }

                // create SVC_TASK_KEY_INFO
                SVC_TASK_KEY_INFOTMsg svcTaskKeyInfoTMsg = new SVC_TASK_KEY_INFOTMsg();
                ZYPEZDItemValueSetter.setValue(svcTaskKeyInfoTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(svcTaskKeyInfoTMsg.svcTaskKeyInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TASK_KEY_INFO_SQ));
                ZYPEZDItemValueSetter.setValue(svcTaskKeyInfoTMsg.svcTaskNum, pMsg.taskList.no(0).svcTaskNum.getValue());
                ZYPEZDItemValueSetter.setValue(svcTaskKeyInfoTMsg.fsrNum, fsrNum);

                EZDTBLAccessor.create(svcTaskKeyInfoTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcTaskKeyInfoTMsg.getReturnCode())) {
                    throw new S21AbendException(NSBM0120E, new String[] {"SVC_TASK_KEY_INFO" });
                }

                // update SVC_TASK_RQST_DUP
                updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.PROCESSED, fsrNum, null, null);

                // execute outbound API.
                if (!executeOtbdApiIfRegisterSucceed(rs, fsrNum, svcTaskNumList)) {
                    return false;
                }
            }
            return true;

            // else : PROC_STS.OUTBOUNDERROR
        } else {

            String fsrNum = rs.getString("FSR_NUM");

            if (hasValue(fsrNum)) {

                FSR_VISITTMsg inMsg = new FSR_VISITTMsg();
                inMsg.setSQLID("005");
                inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                inMsg.setConditionValue("fsrNum01", fsrNum);
                inMsg.setConditionValue("fsrVisitNum01", "01");
                FSR_VISITTMsgArray tMsgArray = (FSR_VISITTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

                List<String> svcTaskNumList = new ArrayList<String>();
                for (int i = 0; i < tMsgArray.length(); i++) {
                    svcTaskNumList.add(tMsgArray.no(i).svcTaskNum.getValue());
                }

                // execute outbound API.
                if (executeOtbdApiIfRegisterSucceed(rs, fsrNum, svcTaskNumList)) {
                    // update SVC_TASK_RQST_DUP
                    updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.PROCESSED, null, null, null);
                    return true;
                }
                return false;

            } else {
                // execute outbound API.
                if (executeOtbdApiIfRegisterFail(rs, rs.getString("ERR_INFO_TXT"))) {
                    updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, null, null);
                    return true;
                }
                return false;
            }
        }
    }

    private boolean executeOtbdApiIfRegisterFail(ResultSet rs, String errMsg) throws SQLException {
        // START 2023/08/08 N.Takatsu [QC#61771, DEL]
//      // call NSZC1320
//        NSZC132001PMsg returnParamMsg = callNszc1320(rs);
//        if (returnParamMsg != null) {
//            String errInfo = S21MessageFunc.clspGetMessage(returnParamMsg.xxMsgIdList.no(0).xxMsgId.getValue(), new String[] {returnParamMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue() });
//            updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.OUTBOUNDERROR, null, errInfo, NSBI1320);
//
//            // set mail param
//            S21ApiMessage msg = S21ApiUtil.getXxMsgList(returnParamMsg).get(0);
//            String msgId = msg.getXxMsgid();
//            String[] msgPrms = msg.getXxMsgPrmArray();
//            sendRequestDataErrorMail(createMailTemplateParamMap(rs, msgId, msgPrms, null, null));
//
//            return false;
//        }
        // END   2023/08/08 N.Takatsu [QC#61771, DEL]

        String errTxt = "";
        if (!NSBI1350.equals(rs.getString("INTFC_ID"))) {

            // START 2023/08/08 N.Takatsu [QC#61771, MOD]
//            // call Walmart Webhook API(NSZC1340)
//            NSZC134001PMsg nszc1340pMsg = createNSZC134001PMsg(rs, SVC_RQST_PROC_STS.ERROR);
//
//            nszc1340pMsg = callApiNSZC134001(rs, nszc1340pMsg);
//            if (nszc1340pMsg != null) {
//
//                String intfcId = NSBI1340;
//
//                StringBuilder sb = new StringBuilder();
//                if (S21ApiUtil.getXxMsgList(nszc1340pMsg).size() > 0) {
//                    for (S21ApiMessage msg : S21ApiUtil.getXxMsgList(nszc1340pMsg)) {
//                        if (sb.length() > 0) {
//                            sb.append(" : ");
//                        }
//                        String msgId = msg.getXxMsgid();
//                        String[] msgPrms = msg.getXxMsgPrmArray();
//                        String errInfoTxt = getRtnMsg(msgId, msgPrms);
//                        sb.append(errInfoTxt);
//                    }
//                    errTxt = sb.toString();
//                }
//                updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.OUTBOUNDERROR, null, errTxt, intfcId);
//
//                S21ApiMessage otbdMsg = S21ApiUtil.getXxMsgList(nszc1340pMsg).get(0);
//                String otbdMsgId = otbdMsg.getXxMsgid();
//                String[] otbdMsgPrms = otbdMsg.getXxMsgPrmArray();
//                sendRequestDataErrorMail(createMailTemplateParamMap(rs, otbdMsgId, otbdMsgPrms, null, null));
//                return false;
//            }

            NSZC133001PMsg nszc1330pMsg = createNSZC133001PMsg(rs, null, null);

            nszc1330pMsg = callApiNSZC133001(rs, nszc1330pMsg);
            if (nszc1330pMsg != null) {

                String intfcId = NSBI1330;

                StringBuilder sb = new StringBuilder();
                if (S21ApiUtil.getXxMsgList(nszc1330pMsg).size() > 0) {
                    for (S21ApiMessage msg : S21ApiUtil.getXxMsgList(nszc1330pMsg)) {
                        if (sb.length() > 0) {
                            // START 2023/08/22 K.Kitachi [QC#61771, MOD]
//                            sb.append(" : ");
                            sb.append(" | ");
                            // END   2023/08/22 K.Kitachi [QC#61771, MOD]
                        }
                        String msgId = msg.getXxMsgid();
                        String[] msgPrms = msg.getXxMsgPrmArray();
                        String errInfoTxt = getRtnMsg(msgId, msgPrms);
                        sb.append(errInfoTxt);
                    }
                    errTxt = sb.toString();
                }
                updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.OUTBOUNDERROR, null, errTxt, intfcId);

                S21ApiMessage otbdMsg = S21ApiUtil.getXxMsgList(nszc1330pMsg).get(0);
                String otbdMsgId = otbdMsg.getXxMsgid();
                String[] otbdMsgPrms = otbdMsg.getXxMsgPrmArray();
                sendRequestDataErrorMail(createMailTemplateParamMap(rs, otbdMsgId, otbdMsgPrms, null, null));
                return false;
            }
            // END   2023/08/08 N.Takatsu [QC#61771, MOD]
        }

        // call Walmart Hold API(NSZC1350)
        NSZC135001PMsg nszc1350pMsg = createNSZC135001PMsg(rs, errMsg);

        nszc1350pMsg = callApiNSZC135001(rs, nszc1350pMsg);
        if (nszc1350pMsg != null) {

            String intfcId = NSBI1350;

            StringBuilder sb = new StringBuilder();
            if (S21ApiUtil.getXxMsgList(nszc1350pMsg).size() > 0) {
                for (S21ApiMessage msg : S21ApiUtil.getXxMsgList(nszc1350pMsg)) {
                    if (sb.length() > 0) {
                        // START 2023/08/22 K.Kitachi [QC#61771, MOD]
//                        sb.append(" : ");
                        sb.append(" | ");
                        // END   2023/08/22 K.Kitachi [QC#61771, MOD]
                    }
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    String errInfoTxt = getRtnMsg(msgId, msgPrms);
                    sb.append(errInfoTxt);
                }
                errTxt = sb.toString();
            }
            updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.OUTBOUNDERROR, null, errTxt, intfcId);

            S21ApiMessage otbdMsg = S21ApiUtil.getXxMsgList(nszc1350pMsg).get(0);
            String otbdMsgId = otbdMsg.getXxMsgid();
            String[] otbdMsgPrms = otbdMsg.getXxMsgPrmArray();
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, otbdMsgId, otbdMsgPrms, null, null));
            return false;
        }

        return true;
    }

    private boolean executeOtbdApiIfRegisterSucceed(ResultSet rs, String fsrNum, List<String> svcTaskNumList) throws SQLException {

        // START 2023/08/08 N.Takatsu [QC#61771, DEL]
//      // call NSZC1320
//        NSZC132001PMsg returnParamMsg = callNszc1320(rs);
//        if (returnParamMsg != null) {
//            String errInfo = S21MessageFunc.clspGetMessage(returnParamMsg.xxMsgIdList.no(0).xxMsgId.getValue(), new String[] {returnParamMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue() });
//            updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.OUTBOUNDERROR, null, errInfo, NSBI1320);
//
//            // set mail param
//            S21ApiMessage msg = S21ApiUtil.getXxMsgList(returnParamMsg).get(0);
//            String msgId = msg.getXxMsgid();
//            String[] msgPrms = msg.getXxMsgPrmArray();
//            sendRequestDataErrorMail(createMailTemplateParamMap(rs, msgId, msgPrms, fsrNum, svcTaskNumList));
//
//            return false;
//        }
        // END   2023/08/08 N.Takatsu [QC#61771, DEL]

        // call Walmart Update Call Info (NSZC1330)
        if (!NSBI1340.equals(rs.getString("INTFC_ID"))) {

            NSZC133001PMsg nszc1330pMsg = createNSZC133001PMsg(rs, fsrNum, DS_SVC_CALL_TP.SERVICE_CALL);

            nszc1330pMsg = callApiNSZC133001(rs, nszc1330pMsg);
            if (nszc1330pMsg != null) {

                String intfcId = NSBI1330;

                String errMsg = "";
                StringBuilder sb = new StringBuilder();
                if (S21ApiUtil.getXxMsgList(nszc1330pMsg).size() > 0) {
                    for (S21ApiMessage msg : S21ApiUtil.getXxMsgList(nszc1330pMsg)) {
                        if (sb.length() > 0) {
                            // START 2023/08/22 K.Kitachi [QC#61771, MOD]
//                            sb.append(" : ");
                            sb.append(" | ");
                            // END   2023/08/22 K.Kitachi [QC#61771, MOD]
                        }
                        String msgId = msg.getXxMsgid();
                        String[] msgPrms = msg.getXxMsgPrmArray();
                        String errInfoTxt = getRtnMsg(msgId, msgPrms);
                        sb.append(errInfoTxt);
                    }
                    errMsg = sb.toString();
                }
                updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.OUTBOUNDERROR, null, errMsg, intfcId);

                S21ApiMessage otbdMsg = S21ApiUtil.getXxMsgList(nszc1330pMsg).get(0);
                String otbdMsgId = otbdMsg.getXxMsgid();
                String[] otbdMsgPrms = otbdMsg.getXxMsgPrmArray();
                sendRequestDataErrorMail(createMailTemplateParamMap(rs, otbdMsgId, otbdMsgPrms, fsrNum, svcTaskNumList));
                return false;
            }
        }

        // call Walmart Webhook API(NSZC1340)
        NSZC134001PMsg nszc1340pMsg = createNSZC134001PMsg(rs, SVC_RQST_PROC_STS.PROCESSED);

        nszc1340pMsg = callApiNSZC134001(rs, nszc1340pMsg);
        if (nszc1340pMsg != null) {

            String intfcId = NSBI1340;

            String errMsg = "";
            StringBuilder sb = new StringBuilder();
            if (S21ApiUtil.getXxMsgList(nszc1340pMsg).size() > 0) {
                for (S21ApiMessage msg : S21ApiUtil.getXxMsgList(nszc1340pMsg)) {
                    if (sb.length() > 0) {
                        // START 2023/08/22 K.Kitachi [QC#61771, MOD]
//                        sb.append(" : ");
                        sb.append(" | ");
                        // END   2023/08/22 K.Kitachi [QC#61771, MOD]
                    }
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    String errInfoTxt = getRtnMsg(msgId, msgPrms);
                    sb.append(errInfoTxt);
                }
                errMsg = sb.toString();
            }
            updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.OUTBOUNDERROR, null, errMsg, intfcId);

            S21ApiMessage otbdMsg = S21ApiUtil.getXxMsgList(nszc1340pMsg).get(0);
            String otbdMsgId = otbdMsg.getXxMsgid();
            String[] otbdMsgPrms = otbdMsg.getXxMsgPrmArray();
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, otbdMsgId, otbdMsgPrms, fsrNum, svcTaskNumList));
            return false;
        }
        return true;
    }

    private boolean cancelTask(ResultSet rs) throws SQLException {

        String errMsg = null;

        if (SVC_RQST_PROC_STS.REQUESTED.equals(rs.getString("SVC_RQST_PROC_STS_CD")) && ZYPCommonFunc.hasValue(rs.getString("ERR_INFO_TXT"))) {
            errMsg = rs.getString("ERR_INFO_TXT");
            updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, errMsg, null);
            // START 2023/09/01 N.Takatsu [QC#61771, DEL]
//            executeOtbdApi(rs, null, null);
            // END  2023/09/01 N.Takatsu [QC#61771, DEL]
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, errMsg));
            return false;
        }

        boolean errFlg = false;

        String fsrNum = rs.getString("FSR_NUM");

        // START 2023/08/08 N.Takatsu [QC#61771, ADD]
        if (hasValue(fsrNum) && !isFsrNum(fsrNum)) {
            updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.PROCESSED, null, null, null);
            commit();
            return true;
        }
        // END   2023/08/08 N.Takatsu [QC#61771, ADD]

        if (!SVC_RQST_PROC_STS.OUTBOUNDERROR.equals(rs.getString("SVC_RQST_PROC_STS_CD"))) {

            if (!inputDataCheck(rs)) {
                errFlg = true;
            }

            if (errFlg) {
                // START 2023/09/01 N.Takatsu [QC#61771, DEL]
//                // execute outbound API.
//                executeOtbdApi(rs, fsrNum, null);
                // END   2023/09/01 N.Takatsu [QC#61771, DEL]
                return false;
            }

            List<Map<String, Object>> svcTaskNumList = getFsrVisit(fsrNum);
            List<String> svcTaskNumStrList = new ArrayList<String>();
            // START 2023/07/28 N.Takatsu [QC#60012, MOD]
//          if (svcTaskNumList != null) {
            if (svcTaskNumList != null && svcTaskNumList.size() > 0) {
            // END   2023/07/28 N.Takatsu [QC#60012, MOD]
                for (Map<String, Object> svcTaskNum : svcTaskNumList) {
                    // START 2023/08/03 K.Kitachi [QC#60012, ADD]
                    if (SVC_TASK_STS.VENDOR_ACKNOWLEDGED.equals((String) svcTaskNum.get("FSR_VISIT_STS_CD"))) {
                        // START 2023/09/01 N.Takatsu [QC#61771, MOD]
//                        sendCancelMail(rs, svcTaskNum);
                        List<String> vndAckTaskList = new ArrayList<String>();
                        vndAckTaskList.add((String) svcTaskNum.get("SVC_TASK_NUM"));
                        updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, getRtnMsg("NSZM0814E", null), null);
                        sendRequestDataErrorMail(createMailTemplateParamMap(rs, "NSZM0814E", null, fsrNum, vndAckTaskList));
                        sendCancelMail(rs, svcTaskNum);
                        errFlg = true;
                        commit();
                        // END   2023/09/01 N.Takatsu [QC#61771, MOD]
                        continue;
                    }
                    // END   2023/08/03 K.Kitachi [QC#60012, ADD]
                    svcTaskNumStrList.add((String) svcTaskNum.get("SVC_TASK_NUM"));
                }
            }

            // START 2023/07/28 N.Takatsu [QC#60012, MOD]
//            if (svcTaskNumList != null) {
            // START 2023/08/03 K.Kitachi [QC#60012, MOD]
//            if (svcTaskNumList != null && svcTaskNumList.size() > 0) {
            if (svcTaskNumStrList != null && svcTaskNumStrList.size() > 0) {
            // END   2023/08/03 K.Kitachi [QC#60012, MOD]
            // END   2023/07/28 N.Takatsu [QC#60012, MOD]
                // call Add Task API(NSZC045001)
                NSZC045001PMsg pMsg = createNSZC045001PMsg(fsrNum, svcTaskNumStrList, rs.getString("CUST_CLOSE_CATG_TXT"), rs.getString("CUST_CLOSE_DESC_TXT"));
                if (!callApiNSZC045001(pMsg)) {
                    rollback();

                    // update SVC_TASK_RQST_DUP
                    StringBuilder sb = new StringBuilder();
                    if (S21ApiUtil.getXxMsgList(pMsg).size() > 0) {
                        for (S21ApiMessage msg : S21ApiUtil.getXxMsgList(pMsg)) {
                            if (sb.length() > 0) {
                                // START 2023/08/22 K.Kitachi [QC#61771, MOD]
//                                sb.append(" : ");
                                sb.append(" | ");
                                // END   2023/08/22 K.Kitachi [QC#61771, MOD]
                            }
                            String msgId = msg.getXxMsgid();
                            String[] msgPrms = msg.getXxMsgPrmArray();
                            String errInfoTxt = getRtnMsg(msgId, msgPrms);
                            sb.append(errInfoTxt);
                        }
                        errMsg = sb.toString();
                    }
                    updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, errMsg, null);

                    // set mail parameter
                    S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    sendRequestDataErrorMail(createMailTemplateParamMap(rs, msgId, msgPrms, fsrNum, svcTaskNumStrList));
                    // START 2023/08/03 K.Kitachi [QC#60012, ADD]
                    for (Map<String, Object> callInfo : svcTaskNumList) {
                        if (!SVC_TASK_STS.VENDOR_ACKNOWLEDGED.equals((String) callInfo.get("FSR_VISIT_STS_CD"))) {
                            sendCancelMail(rs, callInfo);
                        }
                    }
                    // END   2023/08/03 K.Kitachi [QC#60012, ADD]

                    errFlg = true;
                }

            } else {
                // START 2023/07/28 N.Takatsu [QC#60012, DEL]
//                // ERROR : FSR_VISIT cannot cancel.
//                String errInfoTxt = getRtnMsg(NSBM0215E, new String[] {"number", rs.getString("CUST_CALL_ID"), "correlation_id", fsrNum });
//                updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, errInfoTxt, null);
//
//                sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0215E, new String[] {"number", rs.getString("CUST_CALL_ID"), "correlation_id", fsrNum }, fsrNum, null));
//                errFlg = true;
                // END   2023/07/28 N.Takatsu [QC#60012, DEL]
            }

            if (errFlg) {
                // START 2023/09/01 N.Takatsu [QC#61771, DEL]
                // execute outbound API.
                //executeOtbdApi(rs, fsrNum, svcTaskNumStrList);
                // END   2023/09/01 N.Takatsu [QC#61771, DEL]
                return false;
            } else {
                // update CRS_SVC_RCV_RQST_STAGE
                updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.PROCESSED, null, null, null);
                commit();
                return true;
            }

            // PROC_STS.OUTBOUNDERROR
        } else {
            // START 2023/09/01 N.Takatsu [QC#61771, DEL]
//            FSR_VISITTMsg inMsg = new FSR_VISITTMsg();
//            inMsg.setSQLID("004");
//            inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
//            inMsg.setConditionValue("fsrNum01", fsrNum);
//            FSR_VISITTMsgArray tMsgArray = (FSR_VISITTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
//
//            List<String> svcTaskNumList = new ArrayList<String>();
//            for (int i = 0; i < tMsgArray.length(); i++) {
//                svcTaskNumList.add(tMsgArray.no(i).svcTaskNum.getValue());
//            }

//            // execute outbound API.
//            if (executeOtbdApi(rs, fsrNum, svcTaskNumList)) {
//                updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, null, null);
//                return true;
//            }
            // END   2023/09/01 N.Takatsu [QC#61771, DEL]
            return false;
        }
    }

    private boolean updateNote(ResultSet rs) throws SQLException {

        String errMsg = null;

        if (SVC_RQST_PROC_STS.REQUESTED.equals(rs.getString("SVC_RQST_PROC_STS_CD")) && ZYPCommonFunc.hasValue(rs.getString("ERR_INFO_TXT"))) {
            errMsg = rs.getString("ERR_INFO_TXT");
            updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, errMsg, null);
            executeOtbdApi(rs, null, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, errMsg));
            return false;
        }

        boolean errFlg = false;

        String fsrNum = rs.getString("FSR_NUM");

        // START 2023/08/08 N.Takatsu [QC#61771, ADD]
        if (hasValue(fsrNum) && !isFsrNum(fsrNum)) {
            updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.PROCESSED, null, null, null);
            commit();
            return true;
        }
        // END   2023/08/08 N.Takatsu [QC#61771, ADD]

        if (!SVC_RQST_PROC_STS.OUTBOUNDERROR.equals(rs.getString("SVC_RQST_PROC_STS_CD"))) {

            if (!inputDataCheck(rs)) {
                errFlg = true;
            }

            if (errFlg) {
                // execute outbound API.
                executeOtbdApi(rs, fsrNum, null);
                return false;
            }

            List<Map<String, Object>> svcTaskNumList = getFsrVisit(fsrNum);
            List<String> svcTaskNumStrList = new ArrayList<String>();
            if (svcTaskNumList.size() > 0) {
                for (Map<String, Object> svcTaskNum : svcTaskNumList) {
                    svcTaskNumStrList.add((String) svcTaskNum.get("SVC_TASK_NUM"));
                }
            }

            if (svcTaskNumList.size() > 0) {
                createSvcMemo(fsrNum, svcTaskNumStrList, rs.getString("CUST_MEMO_TXT"), rs.getString("SVC_CALL_SRC_TP_CD"));
                // START 2023/07/28 N.Takatsu [QC#60012, DEL]
//              } else {
//                // ERROR : FSR_VISIT cannot update.
//                String errInfoTxt = getRtnMsg(NSBM0217E, new String[] {"number", rs.getString("CUST_CALL_ID"), "correlation_id", fsrNum });
//                updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, errInfoTxt, null);
//                sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0217E, new String[] {"number", rs.getString("CUST_CALL_ID"), "correlation_id", fsrNum }, fsrNum, null));
//                errFlg = true;
                // END   2023/07/28 N.Takatsu [QC#60012, DEL]
            }

            if (errFlg) {
                // execute outbound API.
                executeOtbdApi(rs, fsrNum, svcTaskNumStrList);
                return false;
            } else {
                // update CRS_SVC_RCV_RQST_STAGE
                updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.PROCESSED, null, null, null);
                commit();
                return true;
            }

            // PROC_STS.OUTBOUNDERROR
        } else {
            FSR_VISITTMsg inMsg = new FSR_VISITTMsg();
            inMsg.setSQLID("004");
            inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            inMsg.setConditionValue("fsrNum01", fsrNum);
            FSR_VISITTMsgArray tMsgArray = (FSR_VISITTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

            List<String> svcTaskNumList = new ArrayList<String>();
            for (int i = 0; i < tMsgArray.length(); i++) {
                svcTaskNumList.add(tMsgArray.no(i).svcTaskNum.getValue());
            }

            // execute outbound API.
            if (executeOtbdApi(rs, fsrNum, svcTaskNumList)) {
                updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, null, null);
                return true;
            }
            return false;
        }
    }

    private boolean executeOtbdApi(ResultSet rs, String fsrNum, List<String> svcTaskNumList) throws SQLException {

        // call Walmart Update Task Info API(NSZC1330)
        NSZC133001PMsg nszc1330pMsg = createNSZC133001PMsg(rs, fsrNum, DS_SVC_CALL_TP.SERVICE_CALL);

        nszc1330pMsg = callApiNSZC133001(rs, nszc1330pMsg);
        if (nszc1330pMsg != null) {

            String intfcId = NSBI1330;

            String errMsg = "";
            StringBuilder sb = new StringBuilder();
            if (S21ApiUtil.getXxMsgList(nszc1330pMsg).size() > 0) {
                for (S21ApiMessage msg : S21ApiUtil.getXxMsgList(nszc1330pMsg)) {
                    if (sb.length() > 0) {
                        // START 2023/08/22 K.Kitachi [QC#61771, MOD]
//                        sb.append(" : ");
                        sb.append(" | ");
                        // END   2023/08/22 K.Kitachi [QC#61771, MOD]
                    }
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    String errInfoTxt = getRtnMsg(msgId, msgPrms);
                    sb.append(errInfoTxt);
                }
                errMsg = sb.toString();
            }
            updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.OUTBOUNDERROR, null, errMsg, intfcId);

            S21ApiMessage otbdMsg = S21ApiUtil.getXxMsgList(nszc1330pMsg).get(0);
            String otbdMsgId = otbdMsg.getXxMsgid();
            String[] otbdMsgPrms = otbdMsg.getXxMsgPrmArray();
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, otbdMsgId, otbdMsgPrms, fsrNum, svcTaskNumList));
            return false;
        }
        return true;
    }

    private String extractStr(String longText, Pattern pattern, int index) {
        String extractText = null;
        if (!ZYPCommonFunc.hasValue(longText)) {
            return null;
        }
        try {
            Matcher match = pattern.matcher(longText);
            while (match.find()) {
                extractText = match.group(index);
            }

        } catch (IllegalStateException e) {
            return null;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

        return extractText;
    }

    private String inputDataCheckRegisterFsr(ResultSet rs) throws SQLException {

        String errInfoTxt = null;
        // custCallId
        String custCallId = rs.getString("CUST_CALL_ID");
        if (!hasValue(custCallId)) {
            errInfoTxt = getRtnMsg(NSBM0032E, new String[] {"number" });
            updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, errInfoTxt, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0032E, new String[] {"number" }, null, null));
            return errInfoTxt;
        }

        // custSysId
        if (!hasValue(rs.getString("CUST_SYS_ID"))) {
            errInfoTxt = getRtnMsg(NSBM0216E, new String[] {"sys_id" });
            updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, errInfoTxt, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0216E, new String[] {"sys_id" }, null, null));
            return errInfoTxt;
        }

        // serNum
        String serNum = rs.getString("SER_NUM");
        if (!hasValue(serNum)) {
            // START 2024/02/14 t.aizawa [QC#61771, MOD]
            // errInfoTxt = getRtnMsg(NSBM0212E, new String[] {"u_serial_number", custCallId });
            errInfoTxt = getRtnMsg(NSBM0212E, new String[] {"Serial Number", custCallId });
            // END   2024/02/14 t.aizawa [QC#61771, MOD]
            updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, errInfoTxt, null);
            // START 2024/02/14 t.aizawa [QC#61771, MOD]
            // sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0212E, new String[] {"u_serial_number", custCallId }, null, null));
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0212E, new String[] {"Serial Number", custCallId }, null, null));
            // END   2024/02/14 t.aizawa [QC#61771, MOD]
            return errInfoTxt;
        }

        Map<String, Object> svcMachMstrInfo = getSvcMachMstr(serNum);
        if (svcMachMstrInfo == null) {
            // START 2024/02/14 t.aizawa [QC#61771, MOD]
            // errInfoTxt = getRtnMsg(NSBM0211E, new String[] {"u_serial_number", serNum, custCallId });
            errInfoTxt = getRtnMsg(NSBM0211E, new String[] {"Serial Number", serNum, custCallId });
            // END   2024/02/14 t.aizawa [QC#61771, MOD]
            updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, errInfoTxt, null);
            // START 2024/02/14 t.aizawa [QC#61771, MOD]
            // sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0211E, new String[] {"u_serial_number", serNum, custCallId }, null, null));
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0211E, new String[] {"Serial Number", serNum, custCallId }, null, null));
            // END   2024/02/14 t.aizawa [QC#61771, MOD]
            return errInfoTxt;
        }

        // svcTaskRcvDt
        if (!isValidDateFormat(DT_FORMAT_DT, rs.getString("SVC_TASK_RCV_DT"))) {
            errInfoTxt = getRtnMsg(NSBM0213E, new String[] {"opened_at" + rs.getString("RCV_TM_DESC_TXT"), custCallId });
            updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, errInfoTxt, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0213E, new String[] {"opened_at", rs.getString("RCV_TM_DESC_TXT"), custCallId }, null, null));
            return errInfoTxt;
        }

        // svcTaskRcvTm
        if (!isValidDateFormat(DT_FORMAT_TM, rs.getString("SVC_TASK_RCV_TM"))) {
            errInfoTxt = getRtnMsg(NSBM0213E, new String[] {"opened_at" + rs.getString("RCV_TM_DESC_TXT"), custCallId });
            updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, errInfoTxt, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0213E, new String[] {"opened_at", rs.getString("RCV_TM_DESC_TXT"), custCallId }, null, null));
            return errInfoTxt;
        }

        // svcPblmTpCd
        if (!hasValue(this.svcPblmTpCd)) {
            this.svcPblmTpCd = ZYPCodeDataUtil.getVarCharConstValue(NSBB0980_DEF_SVC_PBLM_TP, glblCmpyCd);
        }

        if (!getSvcPblmTp(this.svcPblmTpCd)) {
            this.svcPblmTpCd = ZYPCodeDataUtil.getVarCharConstValue(NSBB0980_DEF_SVC_PBLM_TP, glblCmpyCd);
        }

        // START 2023/07/10 N.Takatsu [QC#60012, DEL]
//        // ctacPsnEmlAddr
//        if (!hasValue(rs.getString("CTAC_PSN_EML_ADDR"))) {
//            errInfoTxt = getRtnMsg(NSBM0212E, new String[] {"site_contact_email ", custCallId });
//            updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, errInfoTxt, null);
//            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0212E, new String[] {"site_contact_email ", custCallId }, null, null));
//            return errInfoTxt;
//        }
        // END   2023/07/10 N.Takatsu [QC#60012, DEL]
        return errInfoTxt;
    }

    private boolean inputDataCheck(ResultSet rs) throws SQLException {

        // custCallId
        String custCallId = rs.getString("CUST_CALL_ID");
        if (!hasValue(custCallId)) {
            String errInfoTxt = getRtnMsg(NSBM0032E, new String[] {"number" });
            updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, errInfoTxt, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0032E, new String[] {"number" }, null, null));
            return false;
        }

        // custSysId
        if (!hasValue(rs.getString("CUST_SYS_ID"))) {
            String errInfoTxt = getRtnMsg(NSBM0216E, new String[] {"sys_id" });
            updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, errInfoTxt, null);
            sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0216E, new String[] {"sys_id" }, null, null));
            return false;
        }

        // fsrNum
        if (!hasValue(rs.getString("FSR_NUM"))) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) getDupWithRegisterError(rs.getString("CUST_CALL_ID"));
            // START 2023/07/28 N.Takatsu [QC#60012, MOD]
//            if (resultList != null && rqstModeCancelList.contains(rs.getString("RQST_MODE_TXT"))) {
            if (resultList.size() > 0 && RECORD_TYPE_CANCEL == this.recordType) {
            // END   2023/07/28 N.Takatsu [QC#60012, MOD]
                for (Map<String, Object> result : resultList) {
                    SVC_TASK_RQST_DUPTMsg tMsg = new SVC_TASK_RQST_DUPTMsg();
                    setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                    setValue(tMsg.svcTaskRqstDupPk, (BigDecimal) result.get("SVC_TASK_RQST_DUP_PK"));

                    EZDTBLAccessor.logicalRemove(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        throw new S21AbendException(NSBM0120E, new String[] {"SVC_TASK_RQST_DUP" });
                    }
                }
            } else {
                String errInfoTxt = getRtnMsg(NSBM0212E, new String[] {"correlation_id", custCallId });
                updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, errInfoTxt, null);
                sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0212E, new String[] {"correlation_id", custCallId }, null, null));
                return false;
            }
        }

        // close_code, close_notes
        // START 2023/07/28 N.Takatsu [QC#60012, MOD]
//        if (rqstModeCancelList.contains(rs.getString("RQST_MODE_TXT"))) {
        if (RECORD_TYPE_CANCEL == this.recordType) {
        // END   2023/07/28 N.Takatsu [QC#60012, MOD]
            StringBuilder sb = new StringBuilder();
            if (!hasValue(rs.getString("CUST_CLOSE_CATG_TXT"))) {
                sb.append("close_code");
            }
            if (!hasValue(rs.getString("CUST_CLOSE_DESC_TXT"))) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append("close_notes");
            }
            if (sb.length() > 0) {
                String errInfoTxt = getRtnMsg(NSBM0216E, new String[] {sb.toString() });
                updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, errInfoTxt, null);
                sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0216E, new String[] {sb.toString() }, null, null));
                return false;
            }
        }

        // START 2023/07/28 N.Takatsu [QC#60012, MOD]
//        if (rqstModeUpdateList.contains(rs.getString("RQST_MODE_TXT"))) {
        if (RECORD_TYPE_UPDATE == this.recordType) {
        // END   2023/07/28 N.Takatsu [QC#60012, MOD]
            // comments
            if (!hasValue(rs.getString("CUST_MEMO_TXT"))) {
                String errInfoTxt = getRtnMsg(NSBM0216E, new String[] {"comments" });
                updateSvcTaskRqstDup(rs, SVC_RQST_PROC_STS.ERROR, null, errInfoTxt, null);
                sendRequestDataErrorMail(createMailTemplateParamMap(rs, NSBM0216E, new String[] {"comments" }, null, null));
                return false;
            }
        }

        return true;
    }

    private boolean duplicateWithPrevIf(ResultSet rs) throws SQLException {
        List<String> svcRqstProcStsCdList = new ArrayList<String>();
        svcRqstProcStsCdList.add(SVC_RQST_PROC_STS.REQUESTED);
        svcRqstProcStsCdList.add(SVC_RQST_PROC_STS.EXCLUDED);
        svcRqstProcStsCdList.add(SVC_RQST_PROC_STS.ERROR);

        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", this.glblCmpyCd);
        prmMap.put("svcRqstProcStsCd", svcRqstProcStsCdList);
        prmMap.put("custCallId", rs.getString("CUST_CALL_ID"));
        prmMap.put("rqstModeTxt", this.rqstModeRegisterList);

        BigDecimal count = BigDecimal.ZERO;
        if (rs.getString("CUST_CALL_ID") != null) {
            count = (BigDecimal) this.ssmClient.queryObject("getDuplicateWithPrevIfCount", prmMap);
        }
        if (count.compareTo(BigDecimal.ZERO) != 0) {
            return true;
        }
        return false;
    }

    // START 2023/07/10 N.Takatsu [QC#60012, MOD]
//    private NSZC043001PMsg createNSZC043001PMsgRegisterFsr(BigDecimal svcMachMstrPk, String svcTaskRcvDt, String svcTaskRcvTm, String custTelNum, String svcCustAttnTxt, String custCallId, String svcCallSrcTpCd) {
    private NSZC043001PMsg createNSZC043001PMsgRegisterFsr(BigDecimal svcMachMstrPk, String svcTaskRcvDt, String svcTaskRcvTm, String custTelNum, String svcCustAttnTxt, String custCallId, String svcCallSrcTpCd, String getCtacPsnEmlAddr) {
    // END   2023/07/10 N.Takatsu [QC#60012, MOD]

        NSZC043001PMsg pMsg = new NSZC043001PMsg();

        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.xxModeCd, NSZC043001Constant.MODE_CREATE_FSR);
        setValue(pMsg.slsDt, this.slsDt);
        setValue(pMsg.userId, BATCH_PROGRAM_ID);
        setValue(pMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        setValue(pMsg.svcCallSrcTpCd, svcCallSrcTpCd);
        setValue(pMsg.svcMachMstrPk, svcMachMstrPk);
        // START 2023/07/10 N.Takatsu [QC#60012, ADD]
        setValue(pMsg.custEmlAddr, getCtacPsnEmlAddr);
        // END   2023/07/10 N.Takatsu [QC#60012, ADD]
        setValue(pMsg.dsSvcCallTpCd, DS_SVC_CALL_TP.SERV_CALL);
        setValue(pMsg.svcTaskRcvDt, svcTaskRcvDt);
        setValue(pMsg.svcTaskRcvTm, svcTaskRcvTm);
        setValue(pMsg.svcPblmTpCd, this.svcPblmTpCd);
        setValue(pMsg.custTelNum, custTelNum);
        setValue(pMsg.svcCustAttnTxt, svcCustAttnTxt);
        int i = 0;
        if (ZYPCommonFunc.hasValue(this.issueDetails)) {
            setValue(pMsg.svcMemoList.no(i).svcMemoTpCd, SVC_MEMO_TP.PROBLEM);
            setValue(pMsg.svcMemoList.no(i).svcCmntTxt, this.issueDetails);
            i++;
        }
        setValue(pMsg.svcMemoList.no(i).svcMemoTpCd, SVC_MEMO_TP.GENERAL);
        setValue(pMsg.svcMemoList.no(i).svcCmntTxt, ZYPCodeDataUtil.getVarCharConstValue(WALMART_REGISTER_CMNT, this.glblCmpyCd) + custCallId);
        i++;
        pMsg.svcMemoList.setValidCount(i);
        if (this.custMemoTxt != null) {
            setValue(pMsg.svcMemoList.no(i).svcMemoTpCd, SVC_MEMO_TP.GENERAL);
            setValue(pMsg.svcMemoList.no(i).svcCmntTxt, this.custMemoTxt);
            i++;
            pMsg.svcMemoList.setValidCount(i);
        }

        return pMsg;
    }

    private NSZC045001PMsg createNSZC045001PMsg(String fsrNum, List<String> svcTaskNumList, String custCloseCdTxt, String custCloseDescTxt) {

        NSZC045001PMsg pMsg = new NSZC045001PMsg();

        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.xxModeCd, NSZC045001.PROCESS_MODE_CANCEL_TASK);
        setValue(pMsg.userId, BATCH_PROGRAM_ID);
        setValue(pMsg.fsrNum, fsrNum);
        // START 2023/09/01 N.Takatsu [QC#61771, ADD]
        setValue(pMsg.slsDt , this.slsDt);
        // END   2023/09/01 N.Takatsu [QC#61771, ADD]
        setValue(pMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        for (int i = 0; i < svcTaskNumList.size(); i++) {
            setValue(pMsg.xxSvcTaskList.no(i).svcTaskNum, svcTaskNumList.get(i));
            setValue(pMsg.xxSvcTaskList.no(i).schdDisptEmlFlg, ZYPConstant.FLG_ON_Y);
        }
        pMsg.xxSvcTaskList.setValidCount(svcTaskNumList.size());
        setValue(pMsg.xxSvcMemoList.no(0).svcMemoTpCd, SVC_MEMO_TP.GENERAL);
        setValue(pMsg.xxSvcMemoList.no(0).svcCmntTxt, custCloseCdTxt + ":" + custCloseDescTxt);
        pMsg.xxSvcMemoList.setValidCount(1);

        return pMsg;
    }

    private NSZC133001PMsg createNSZC133001PMsg(ResultSet rs, String fsrNum, String dsSvcCallTpCd) throws SQLException {
        NSZC133001PMsg pMsg = new NSZC133001PMsg();

        String custSysId = "";
        if (ZYPCommonFunc.hasValue(rs.getString("CUST_SYS_ID"))) {
            custSysId = rs.getString("CUST_SYS_ID");
        }
        setValue(pMsg.custSysId, custSysId);

        if (!ZYPCommonFunc.hasValue(fsrNum)) {
            fsrNum = "";
        }
        setValue(pMsg.fsrNum, fsrNum);

        String custCallId = "";
        if (ZYPCommonFunc.hasValue(rs.getString("CUST_CALL_ID"))) {
            custCallId = rs.getString("CUST_CALL_ID");
        }
        setValue(pMsg.custCallId, custCallId);

        String errMsg = "";
        // START 2023/07/28 N.Takatsu [QC#60012, MOD]
//        if (this.rqstModeRegisterList.contains(rs.getString("RQST_MODE_TXT"))) {
        if (RECORD_TYPE_REGISTER == this.recordType) {
        // END   2023/07/28 N.Takatsu [QC#60012, MOD]
            // START 2023/08/08 N.Takatsu [QC#61771, MOD]
//            DS_SVC_CALL_TPTMsg tMsg = new DS_SVC_CALL_TPTMsg();
//            setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
//            setValue(tMsg.dsSvcCallTpCd, dsSvcCallTpCd);
//            tMsg = (DS_SVC_CALL_TPTMsg) EZDTBLAccessor.findByKey(tMsg);
//            errMsg = getRtnMsg(NSBM0208I, new String[] {fsrNum, dsSvcCallTpCd, tMsg.dsSvcCallTpDescTxt.getValue(), custCallId });
//            errMsg = NSBM0208I + " : " + errMsg;

            if (!ZYPCommonFunc.hasValue(dsSvcCallTpCd)) {
                errMsg = getRtnMsg(NSBM0223E, null);
                // START 2023/08/22 K.Kitachi [QC#61771, DEL]
//                errMsg = NSBM0223E + " : " + errMsg;
                // END   2023/08/22 K.Kitachi [QC#61771, DEL]
            } else {
                DS_SVC_CALL_TPTMsg tMsg = new DS_SVC_CALL_TPTMsg();
                setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                setValue(tMsg.dsSvcCallTpCd, dsSvcCallTpCd);
                tMsg = (DS_SVC_CALL_TPTMsg) EZDTBLAccessor.findByKey(tMsg);
                errMsg = getRtnMsg(NSBM0208I, new String[] {fsrNum, dsSvcCallTpCd, tMsg.dsSvcCallTpDescTxt.getValue(), custCallId });
                // START 2023/08/22 K.Kitachi [QC#61771, DEL]
//                errMsg = NSBM0208I + " : " + errMsg;
                // END   2023/08/22 K.Kitachi [QC#61771, DEL]
            }
            // END   2023/08/08 N.Takatsu [QC#61771, MOD]
        }
        setValue(pMsg.svcCmntTxt, errMsg);

        String workNote = "";
        // START 2023/07/28 N.Takatsu [QC#60012, MOD]
//        if (this.rqstModeUpdateList.contains(rs.getString("RQST_MODE_TXT"))) {
//            workNote = getRtnMsg(NSBM0209E, new String[] {custCallId });
//            workNote = NSBM0209E + " : " + workNote;
//        } else if (this.rqstModeCancelList.contains(rs.getString("RQST_MODE_TXT"))) {
//            workNote = getRtnMsg(NSBM0210E, new String[] {custCallId });
//            workNote = NSBM0210E + " : " + workNote;
//        }
        if (RECORD_TYPE_UPDATE == this.recordType) {
            workNote = getRtnMsg(NSBM0209E, new String[] {custCallId });
            // START 2023/08/22 K.Kitachi [QC#61771, DEL]
//            workNote = NSBM0209E + " : " + workNote;
            // END   2023/08/22 K.Kitachi [QC#61771, DEL]
        } else if (RECORD_TYPE_CANCEL == this.recordType) {
            workNote = getRtnMsg(NSBM0210E, new String[] {custCallId });
            // START 2023/08/22 K.Kitachi [QC#61771, DEL]
//            workNote = NSBM0210E + " : " + workNote;
            // END   2023/08/22 K.Kitachi [QC#61771, DEL]
        }
        // END   2023/07/28 N.Takatsu [QC#60012, MOD]
        setValue(pMsg.svcWrkTxt, workNote);
        return pMsg;
    }

    private NSZC134001PMsg createNSZC134001PMsg(ResultSet rs, String svcRqstProcStsCd) throws SQLException {
        NSZC134001PMsg pMsg = new NSZC134001PMsg();
        setValue(pMsg.custWbhkId, ZYPCodeDataUtil.getVarCharConstValue(WALMART_CUST_WBHK_ID, this.glblCmpyCd));
        setValue(pMsg.custTblNm, ZYPCodeDataUtil.getVarCharConstValue(WALMART_INC_TBL_NM, this.glblCmpyCd));

        String custSysId = "";
        if (ZYPCommonFunc.hasValue(rs.getString("CUST_SYS_ID"))) {
            custSysId = rs.getString("CUST_SYS_ID");
        }
        setValue(pMsg.custSysId, custSysId);

        Map<String, Object> wbhkRgtnFld = getWbhkRgtnFld(rs.getString("SVC_CALL_SRC_TP_CD"), svcRqstProcStsCd);
        setValue(pMsg.svcTaskWatchFldTxt, (String) wbhkRgtnFld.get("WBHK_WATCH_FLD_TXT"));
        setValue(pMsg.svcTaskRtrnFldTxt, (String) wbhkRgtnFld.get("WBHK_RTRN_FLD_TXT"));

        return pMsg;
    }

    private NSZC135001PMsg createNSZC135001PMsg(ResultSet rs, String errMsg) throws SQLException {
        NSZC135001PMsg pMsg = new NSZC135001PMsg();

        String custSysId = "";
        if (ZYPCommonFunc.hasValue(rs.getString("CUST_SYS_ID"))) {
            custSysId = rs.getString("CUST_SYS_ID");
        }
        setValue(pMsg.custSysId, custSysId);

        String custCallId = "";
        if (ZYPCommonFunc.hasValue(rs.getString("CUST_CALL_ID"))) {
            custCallId = rs.getString("CUST_CALL_ID");
        }
        setValue(pMsg.custCallId, custCallId);

        setValue(pMsg.custRsnCd, ZYPCodeDataUtil.getVarCharConstValue(WALMART_CUST_RSN_CD, this.glblCmpyCd));
        // START 2023/08/08 N.Takatsu [QC#61771, MOD]
//        String defComment = ZYPCodeDataUtil.getVarCharConstValue(WALMART_SVC_CMNT_BGN_TXT, this.glblCmpyCd);
//        setValue(pMsg.svcCmntTxt, defComment + errMsg);
//        setValue(pMsg.svcStsTxt, ZYPCodeDataUtil.getVarCharConstValue(WALMART_SVC_STS_TXT, this.glblCmpyCd));
        String svcStsTxt = ZYPCodeDataUtil.getVarCharConstValue(WALMART_SVC_STS_TXT, this.glblCmpyCd);
        setValue(pMsg.svcStsTxt, svcStsTxt + SPACE + errMsg);
        // END   2023/08/08 N.Takatsu [QC#61771, MOD]

        return pMsg;
    }

    private boolean callApiNSZC043001(NSZC043001PMsg pMsg) throws SQLException {

        NSZC043001 api = new NSZC043001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return false;
        }
        return true;
    }

    private boolean callApiNSZC045001(NSZC045001PMsg pMsg) throws SQLException {

        NSZC045001 api = new NSZC045001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return false;
        }
        return true;
    }

    // START 2023/08/08 N.Takatsu [QC#61771, DEL]
//    private NSZC132001PMsg callNszc1320(ResultSet rs) throws SQLException {
//
//        PreparedStatement prcIncrStmt = null;
//        ResultSet rsInfo = null;
//
//        prcIncrStmt = ssmLLClient.createPreparedStatement("getUnSubscribeTarget", setParamForResendTargetList(rs), execParam);
//        rsInfo = prcIncrStmt.executeQuery();
//
//        while (rsInfo.next()) {
//            NSZC132001PMsg paramMsg = new NSZC132001PMsg();
//
//            setValue(paramMsg.custWbhkId, getStringFromRs(rsInfo, CUST_WBHK_ID));
//            setValue(paramMsg.custTblNm, getStringFromRs(rsInfo, CUST_TBL_NM));
//            setValue(paramMsg.custSysId, getStringFromRs(rsInfo, CUST_SYS_ID));
//
//            // execute
//            new NSZC132001().execute(paramMsg, ONBATCH_TYPE.BATCH);
//            createSvcTaskOtbdMsg(rs, paramMsg, null, null, null);
//            if (S21ApiUtil.isXxMsgId(paramMsg)) {
//                return paramMsg;
//            }
//            paramMsg.clear();
//        }
//        return null;
//    }
    // END   2023/08/08 N.Takatsu [QC#61771, DEL]

    private NSZC133001PMsg callApiNSZC133001(ResultSet rs, NSZC133001PMsg pMsg) throws SQLException {

        NSZC133001 api = new NSZC133001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);
        // START 2023/08/08 N.Takatsu [QC#61771, MOD]
//         createSvcTaskOtbdMsg(rs, null, pMsg, null, null);
        createSvcTaskOtbdMsg(rs, pMsg, null, null);
        // END   2023/08/08 N.Takatsu [QC#61771, MOD]

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return pMsg;
        }
        return null;
    }

    private NSZC134001PMsg callApiNSZC134001(ResultSet rs, NSZC134001PMsg pMsg) throws SQLException {

        NSZC134001 api = new NSZC134001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);
        // START 2023/08/08 N.Takatsu [QC#61771, MOD]
//        createSvcTaskOtbdMsg(rs, null, null, pMsg, null);
        createSvcTaskOtbdMsg(rs, null, pMsg, null);
        // END   2023/08/08 N.Takatsu [QC#61771, MOD]

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return pMsg;
        }
        return null;
    }

    private NSZC135001PMsg callApiNSZC135001(ResultSet rs, NSZC135001PMsg pMsg) throws SQLException {

        NSZC135001 api = new NSZC135001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);
        // START 2023/08/08 N.Takatsu [QC#61771, MOD]
//        createSvcTaskOtbdMsg(rs, null, null, null, pMsg);
        createSvcTaskOtbdMsg(rs, null, null, pMsg);
        // END   2023/08/08 N.Takatsu [QC#61771, MOD]

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return pMsg;
        }
        return null;
    }

    // START 2023/08/08 N.Takatsu [QC#61771, MOD]
//    private boolean createSvcTaskOtbdMsg(ResultSet rs, NSZC132001PMsg nszc1320PMsg, NSZC133001PMsg nszc1330PMsg, NSZC134001PMsg nszc1340PMsg, NSZC135001PMsg nszc1350PMsg) throws SQLException {
    private boolean createSvcTaskOtbdMsg(ResultSet rs, NSZC133001PMsg nszc1330PMsg, NSZC134001PMsg nszc1340PMsg, NSZC135001PMsg nszc1350PMsg) throws SQLException { 
    // END   2023/08/08 N.Takatsu [QC#61771, MOD]
        SVC_TASK_OTBD_MSGTMsg tMsg = new SVC_TASK_OTBD_MSGTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.svcTaskRqstDupPk, rs.getBigDecimal("SVC_TASK_RQST_DUP_PK"));
        setValue(tMsg.svcTaskOtbdMsgPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TASK_OTBD_MSG_SQ));
        setValue(tMsg.cratTs, rs.getString("CRAT_TS"));
        setValue(tMsg.svcRqstProcStsCd, SVC_RQST_PROC_STS.PROCESSED);

        // START 2023/08/08 N.Takatsu [QC#61771, MOD]
//        if (nszc1320PMsg != null) {
//            setValue(tMsg.custWbhkId, nszc1320PMsg.custWbhkId);
//            setValue(tMsg.custTblNm, nszc1320PMsg.custTblNm);
//            setValue(tMsg.custSysId, nszc1320PMsg.custSysId);
//            StringBuilder sb = new StringBuilder();
//            if (S21ApiUtil.getXxMsgList(nszc1320PMsg).size() > 0) {
//                for (S21ApiMessage msg : S21ApiUtil.getXxMsgList(nszc1320PMsg)) {
//                    if (sb.length() > 0) {
//                        sb.append(" : ");
//                    }
//                    String msgId = msg.getXxMsgid();
//                    String[] msgPrms = msg.getXxMsgPrmArray();
//                    String errInfoTxt = getRtnMsg(msgId, msgPrms);
//                    sb.append(errInfoTxt);
//                }
//                setValue(tMsg.svcRqstProcStsCd, SVC_RQST_PROC_STS.OUTBOUNDERROR);
//            }
//            setValue(tMsg.errInfoTxt, sb.toString());
//            setValue(tMsg.intfcId, NSBI1320);
//            setValue(tMsg.otbdIntfcTpCd, OTBD_INTFC_TP.WALMART_INBOUND_CALL_CREATION);
//
//        } else if (nszc1330PMsg != null) {
        if (nszc1330PMsg != null) {
        // END   2023/08/08 N.Takatsu [QC#61771, MOD]
            setValue(tMsg.custSysId, nszc1330PMsg.custSysId);
            setValue(tMsg.fsrNum, nszc1330PMsg.fsrNum);
            setValue(tMsg.custCallId, nszc1330PMsg.custCallId);
            setValue(tMsg.svcCmntTxt, nszc1330PMsg.svcCmntTxt);
            setValue(tMsg.svcWrkTxt, nszc1330PMsg.svcWrkTxt);
            StringBuilder sb = new StringBuilder();
            if (S21ApiUtil.getXxMsgList(nszc1330PMsg).size() > 0) {
                for (S21ApiMessage msg : S21ApiUtil.getXxMsgList(nszc1330PMsg)) {
                    if (sb.length() > 0) {
                        // START 2023/08/22 K.Kitachi [QC#61771, MOD]
//                        sb.append(" : ");
                        sb.append(" | ");
                        // END   2023/08/22 K.Kitachi [QC#61771, MOD]
                    }
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    String errInfoTxt = getRtnMsg(msgId, msgPrms);
                    sb.append(errInfoTxt);
                }
                setValue(tMsg.svcRqstProcStsCd, SVC_RQST_PROC_STS.OUTBOUNDERROR);
            }
            setValue(tMsg.errInfoTxt, sb.toString());
            setValue(tMsg.intfcId, NSBI1330);
            // START 2023/07/28 N.Takatsu [QC#60012, MOD]
//            if (this.rqstModeRegisterList.contains(rs.getString("RQST_MODE_TXT"))) {
//                setValue(tMsg.otbdIntfcTpCd, OTBD_INTFC_TP.WALMART_INBOUND_CALL_CREATION);
//            }
//            if (this.rqstModeCancelList.contains(rs.getString("RQST_MODE_TXT"))) {
//                setValue(tMsg.otbdIntfcTpCd, OTBD_INTFC_TP.WALMART_INBOUND_CALL_CANCEL);
//            }
//            if (this.rqstModeUpdateList.contains(rs.getString("RQST_MODE_TXT"))) {
//                setValue(tMsg.otbdIntfcTpCd, OTBD_INTFC_TP.WALMART_INBOUND_UPDATE_NOTE);
//            }
            if (RECORD_TYPE_REGISTER == this.recordType) {
                setValue(tMsg.otbdIntfcTpCd, OTBD_INTFC_TP.WALMART_INBOUND_CALL_CREATION);
            }
            if (RECORD_TYPE_CANCEL == this.recordType) {
                setValue(tMsg.otbdIntfcTpCd, OTBD_INTFC_TP.WALMART_INBOUND_CALL_CANCEL);
            }
            if (RECORD_TYPE_UPDATE == this.recordType) {
                setValue(tMsg.otbdIntfcTpCd, OTBD_INTFC_TP.WALMART_INBOUND_UPDATE_NOTE);
            }
            // END   2023/07/28 N.Takatsu [QC#60012, MOD]
        } else if (nszc1340PMsg != null) {
            setValue(tMsg.custWbhkId, nszc1340PMsg.custWbhkId);
            setValue(tMsg.custTblNm, nszc1340PMsg.custTblNm);
            setValue(tMsg.custSysId, nszc1340PMsg.custSysId);
            setValue(tMsg.svcTaskWatchFldTxt, nszc1340PMsg.svcTaskWatchFldTxt);
            setValue(tMsg.svcTaskRtrnFldTxt, nszc1340PMsg.svcTaskRtrnFldTxt);
            StringBuilder sb = new StringBuilder();
            if (S21ApiUtil.getXxMsgList(nszc1340PMsg).size() > 0) {
                for (S21ApiMessage msg : S21ApiUtil.getXxMsgList(nszc1340PMsg)) {
                    if (sb.length() > 0) {
                        // START 2023/08/22 K.Kitachi [QC#61771, MOD]
//                        sb.append(" : ");
                        sb.append(" | ");
                        // END   2023/08/22 K.Kitachi [QC#61771, MOD]
                    }
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    String errInfoTxt = getRtnMsg(msgId, msgPrms);
                    sb.append(errInfoTxt);
                }
                setValue(tMsg.svcRqstProcStsCd, SVC_RQST_PROC_STS.OUTBOUNDERROR);
            }
            setValue(tMsg.errInfoTxt, sb.toString());
            setValue(tMsg.intfcId, NSBI1340);
            setValue(tMsg.otbdIntfcTpCd, OTBD_INTFC_TP.WALMART_INBOUND_CALL_CREATION);
        } else if (nszc1350PMsg != null) {
            setValue(tMsg.custSysId, nszc1350PMsg.custSysId);
            setValue(tMsg.custCallId, nszc1350PMsg.custCallId);
            setValue(tMsg.custRsnCd, nszc1350PMsg.custRsnCd);
            setValue(tMsg.svcCmntTxt, nszc1350PMsg.svcCmntTxt);
            setValue(tMsg.svcStsTxt, nszc1350PMsg.svcStsTxt);
            StringBuilder sb = new StringBuilder();
            if (S21ApiUtil.getXxMsgList(nszc1350PMsg).size() > 0) {
                for (S21ApiMessage msg : S21ApiUtil.getXxMsgList(nszc1350PMsg)) {
                    if (sb.length() > 0) {
                        // START 2023/08/22 K.Kitachi [QC#61771, MOD]
//                        sb.append(" : ");
                        sb.append(" | ");
                        // END   2023/08/22 K.Kitachi [QC#61771, MOD]
                    }
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    String errInfoTxt = getRtnMsg(msgId, msgPrms);
                    sb.append(errInfoTxt);
                }
                setValue(tMsg.svcRqstProcStsCd, SVC_RQST_PROC_STS.OUTBOUNDERROR);
            }
            setValue(tMsg.errInfoTxt, sb.toString());
            setValue(tMsg.intfcId, NSBI1350);
            setValue(tMsg.otbdIntfcTpCd, OTBD_INTFC_TP.WALMART_INBOUND_CALL_CREATION);
        }

        EZDTBLAccessor.create(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(NSBM0120E, new String[] {"SVC_TASK_OTBD_MSG" });
        }

        return true;
    }

    private void updateSvcTaskRqstDup(ResultSet rs, String svcRqstProcStsCd, String fsrNum, String errInfoTxt, String intfcId) throws SQLException {

        SVC_TASK_RQST_DUPTMsg tMsg = new SVC_TASK_RQST_DUPTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.svcTaskRqstDupPk, rs.getBigDecimal("SVC_TASK_RQST_DUP_PK"));
        tMsg = (SVC_TASK_RQST_DUPTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
        if (hasValue(svcRqstProcStsCd)) {
            setValue(tMsg.svcRqstProcStsCd, svcRqstProcStsCd);
            if (SVC_RQST_PROC_STS.PROCESSED.equals(svcRqstProcStsCd)) {
                tMsg.intfcId.clear();
                tMsg.errInfoTxt.clear();
            }
        }
        if (hasValue(fsrNum)) {
            setValue(tMsg.fsrNum, fsrNum);
        }
        if (hasValue(errInfoTxt)) {
            setValue(tMsg.errInfoTxt, errInfoTxt);
        }
        if (hasValue(intfcId)) {
            setValue(tMsg.intfcId, intfcId);
        }

        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(NSBM0120E, new String[] {"SVC_TASK_RQST_DUP" });
        }
    }

    private boolean isValidDateFormat(String pattern, String source) {

        if (source == null) {
            return false;
        }

        try {
            DateFormat format = new SimpleDateFormat(pattern);
            format.parse(source);

        } catch (ParseException ex) {
            return false;
        }

        return true;
    }

    // START 2023/08/08 N.Takatsu [QC#61771, DEL]
//    private Map<String, Object> setParamForResendTargetList(ResultSet rs) throws SQLException {
//        List<String> svcRqstProcStsCdList = new ArrayList<String>();
//        svcRqstProcStsCdList.add(SVC_RQST_PROC_STS.REQUESTED);
//        svcRqstProcStsCdList.add(SVC_RQST_PROC_STS.EXCLUDED);
//        svcRqstProcStsCdList.add(SVC_RQST_PROC_STS.ERROR);
//
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("glblCmpyCd", this.glblCmpyCd);
//        paramMap.put("custSysId", getStringFromRs(rs, CUST_SYS_ID));
//        paramMap.put("intfcId", NSBI1340);
//        paramMap.put("rqstModeRegisterList", svcRqstProcStsCdList);
//        return paramMap;
//    }
    // END   2023/08/08 N.Takatsu [QC#61771, DEL]

    private Map<String, Object> getSvcMachMstr(String serNum) {

        // find SVC_MACH_MSTR by serNum
        List<String> svcMachMstrStsCdList = new ArrayList<String>();
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.INSTALLED);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);

        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", this.glblCmpyCd);
        prmMap.put("serNum", serNum);
        prmMap.put("svcMachMstrStsCdList", svcMachMstrStsCdList);
        prmMap.put("svcMachTpCd", SVC_MACH_TP.MACHINE);

        if (!ZYPCommonFunc.hasValue(serNum)) {
            return null;
        }
        return (Map<String, Object>) ssmClient.queryObject("findSvcMachMstr", prmMap);
    }

    private boolean getSvcPblmTp(String pblmTpCd) {

        // find SVC_PBLM_TP by svcPblmTpCd
        SVC_PBLM_TPTMsg svcPblmTpTMsg = new SVC_PBLM_TPTMsg();
        svcPblmTpTMsg.setSQLID("004");
        svcPblmTpTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        svcPblmTpTMsg.setConditionValue("svcPblmTpCd01", pblmTpCd);
        SVC_PBLM_TPTMsgArray svcPblmTpTMsgArray = (SVC_PBLM_TPTMsgArray) EZDTBLAccessor.findByCondition(svcPblmTpTMsg);
        if (svcPblmTpTMsgArray != null && svcPblmTpTMsgArray.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    private List<Map<String, Object>> getFsrVisit(String fsrNum) {

        // find FSR_VISIT.SVC_TASK_NUM by fsrNum
        List<String> fsrVisitStsCdList = new ArrayList<String>();
        fsrVisitStsCdList.add(SVC_TASK_STS.COMPLETED);
        fsrVisitStsCdList.add(SVC_TASK_STS.CLOSED);
        fsrVisitStsCdList.add(SVC_TASK_STS.CANCELLED);
        fsrVisitStsCdList.add(SVC_TASK_STS.DEBRIEF_ERROR);

        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", this.glblCmpyCd);
        prmMap.put("fsrVisitStsCdList", fsrVisitStsCdList);
        prmMap.put("fsrNum", fsrNum);

        if (!ZYPCommonFunc.hasValue(fsrNum)) {
            return null;
        }
        return (List<Map<String, Object>>) ssmClient.queryObjectList("getFsrVisit", prmMap);
    }

    private Map<String, Object> getWbhkRgtnFld(String svcCallSrcTpCd, String svcRqstProcStsCd) {

        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", this.glblCmpyCd);
        if (!hasValue(svcCallSrcTpCd)) {
            svcCallSrcTpCd = SVC_CALL_SRC_TP.WALMART;
        }
        prmMap.put("svcCallSrcTpCd", svcCallSrcTpCd);
        prmMap.put("svcRqstProcStsCd", svcRqstProcStsCd);

        return (Map<String, Object>) ssmClient.queryObject("getWbhkRgtnFld", prmMap);
    }

    private List<Map<String, Object>> getDupWithRegisterError(String custCallId) {
        List<String> svcRqstProcStsCdList = new ArrayList<String>();
        svcRqstProcStsCdList.add(SVC_RQST_PROC_STS.ERROR);

        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", this.glblCmpyCd);
        prmMap.put("svcRqstProcStsCdList", svcRqstProcStsCdList);
        prmMap.put("rqstModeRegisterList", this.rqstModeRegisterList);
        prmMap.put("custCallId", custCallId);

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getDupWithRegisterError", prmMap);
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

        S21Mail mail = new S21Mail(this.glblCmpyCd);

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

        template.setTemplateParameter(MAIL_ITEM_BATCH_ID, BATCH_PROGRAM_ID);

        mail.setMailTemplate(template);
        mail.setSubject(template.getSubject());
        mail.postMail();
    }

    private Map<String, String> createMailTemplateParamMap(ResultSet rs, String msgId, String[] msgPrms, String fsrNum, List<String> svcTaskNumList) throws SQLException {

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

        // ${number}
        prmMap.put(MAIL_ITEM_NUM, rs.getString("CUST_CALL_ID"));

        // ${cratts}
        if (ZYPCommonFunc.hasValue(rs.getString("CRAT_TS"))) {
            prmMap.put(MAIL_ITEM_CRAT_TS, ZYPDateUtil.formatEzd17ToDisp(rs.getString("CRAT_TS")));
        }

        // ${fsrnum}
        prmMap.put(MAIL_ITEM_FSR_NUM, fsrNum);

        // ${tasknum}
        StringBuilder sb = new StringBuilder();
        // START 2023/07/28 N.Takatsu [QC#60012, MOD]
//        if (svcTaskNumList != null) {
        if (svcTaskNumList != null && svcTaskNumList.size() > 0) {
        // END   2023/07/28 N.Takatsu [QC#60012, MOD]
            for (String svcTaskNum : svcTaskNumList) {
                if (ZYPCommonFunc.hasValue(svcTaskNum)) {
                    // START 2023/08/03 K.Kitachi [QC#60012, MOD]
//                    sb.append(",");
                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    // END   2023/08/03 K.Kitachi [QC#60012, MOD]
                }
                sb.append(svcTaskNum);
            }
        }
        prmMap.put(MAIL_ITEM_TASK_NUM, sb.toString());

        // ${sernum}
        prmMap.put(MAIL_ITEM_SER_NUM, rs.getString("SER_NUM"));

        // ${pblmcmnt}
        prmMap.put(MAIL_ITEM_PBLM_CMNT, this.issueDetails);

        // START 2023/07/28 N.Takatsu [QC#60012, MOD]
        // ${rqstmode}
//        if (ZYPCommonFunc.hasValue(rs.getString("RQST_MODE_TXT"))) {
//            prmMap.put(MAIL_ITEM_RQST_MODE, rs.getString("RQST_MODE_TXT") + " " + rs.getString("RQST_MODE_DESC_TXT"));
//        }
        if (RECORD_TYPE_REGISTER == this.recordType) {
            prmMap.put(MAIL_ITEM_RQST_MODE, CREATE);
        } else if (RECORD_TYPE_UPDATE == this.recordType) {
            prmMap.put(MAIL_ITEM_RQST_MODE, UPDATE);
        } else if (RECORD_TYPE_CANCEL == this.recordType) {
            prmMap.put(MAIL_ITEM_RQST_MODE, CANCEL);
        } else {
            prmMap.put(MAIL_ITEM_RQST_MODE, UNKNOWN);
        }
        // END   2023/07/28 N.Takatsu [QC#60012, MOD]

        return prmMap;
    }

    private Map<String, String> createMailTemplateParamMap(ResultSet rs, String errMsg) throws SQLException {

        Map<String, String> prmMap = new HashMap<String, String>();
        String errDate = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);

        // ${errDate}
        prmMap.put(MAIL_ITEM_ERR_DATE, errDate);

        // ${message}
        prmMap.put(MAIL_ITEM_MESSAGE, errMsg);

        // ${number}
        prmMap.put(MAIL_ITEM_NUM, rs.getString("CUST_CALL_ID"));

        // ${cratts}
        if (ZYPCommonFunc.hasValue(rs.getString("CRAT_TS"))) {
            prmMap.put(MAIL_ITEM_CRAT_TS, ZYPDateUtil.formatEzd17ToDisp(rs.getString("CRAT_TS")));
        }

        // ${fsrnum}
        prmMap.put(MAIL_ITEM_FSR_NUM, rs.getString("FSR_NUM"));

        // ${sernum}
        prmMap.put(MAIL_ITEM_SER_NUM, rs.getString("SER_NUM"));

        // ${pblmcmnt}
        prmMap.put(MAIL_ITEM_PBLM_CMNT, this.issueDetails);

        // START 2023/07/28 N.Takatsu [QC#60012, MOD]
        // ${rqstmode}
//        if (ZYPCommonFunc.hasValue(rs.getString("RQST_MODE_TXT"))) {
//            prmMap.put(MAIL_ITEM_RQST_MODE, rs.getString("RQST_MODE_TXT") + " " + rs.getString("RQST_MODE_DESC_TXT"));
//        }
        if (RECORD_TYPE_REGISTER == this.recordType) {
            prmMap.put(MAIL_ITEM_RQST_MODE, CREATE);
        } else if (RECORD_TYPE_UPDATE == this.recordType) {
            prmMap.put(MAIL_ITEM_RQST_MODE, UPDATE);
        } else if (RECORD_TYPE_CANCEL == this.recordType) {
            prmMap.put(MAIL_ITEM_RQST_MODE, CANCEL);
        }
        // END   2023/07/28 N.Takatsu [QC#60012, MOD]

        return prmMap;
    }

    /**
     * Get Return Message
     * @param msgId String
     * @param prm String[]
     * @return String
     */
    private static String getRtnMsg(String msgId, String[] prm) {
        String rtnVal = null;
        if (ZYPCommonFunc.hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId, prm);
            // START 2023/08/22 K.Kitachi [QC#61771, DEL]
//            rtnVal = rtnVal.substring(msgId.length()).trim();
            // END   2023/08/22 K.Kitachi [QC#61771, DEL]
        }
        return rtnVal;
    }

    private void createSvcMemo(String fsrNum, List<String> svcTaskNumStrList, String memoTxt, String svcCallSrcTpCd) {
        for (String svcTaskNum : svcTaskNumStrList) {

            SVC_MEMOTMsg outMsg = new SVC_MEMOTMsg();
            setValue(outMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(outMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
            setValue(outMsg.fsrNum, fsrNum);
            setValue(outMsg.svcTaskNum, svcTaskNum);
            setValue(outMsg.svcMemoCatgCd, SVC_MEMO_CATG.DISPATCH_MEMO);
            setValue(outMsg.svcMemoTpCd, SVC_MEMO_TP.GENERAL);
            setValue(outMsg.svcCmntTxt, memoTxt);
            setValue(outMsg.lastUpdUsrId, BATCH_PROGRAM_ID);
            setValue(outMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS));
            EZDTBLAccessor.create(outMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                throw new S21AbendException(NSBM0120E, new String[] {"SVC_MEMO" });
            }
        }
    }

    // START 2023/08/08 N.Takatsu [QC#61771, DEL]
//    private String getStringFromRs(ResultSet rs, String keyStr) throws SQLException {
//        String ret = "";
//        ret = rs.getString(keyStr);
//        if (!ZYPCommonFunc.hasValue(ret)) {
//            ret = "";
//        }
//        return ret;
//    }
    // END   2023/08/08 N.Takatsu [QC#61771, DEL]

    // START 2023/08/03 K.Kitachi [QC#60012, ADD]
    private void createZoneMap() {
        this.zoneMap = new HashMap<String, DS_COND_CONSTTMsg>();
        DS_COND_CONSTTMsgArray dsCondConstTMsgArray = getDsCondConstByGrpId(DS_COND_CONST_GRP_ID_NSBB0980_NTFY_ADDR);
        for (int i = 0; i < dsCondConstTMsgArray.getValidCount(); i++) {
            this.zoneMap.put(dsCondConstTMsgArray.no(i).dsCondConstNm.getValue(), dsCondConstTMsgArray.no(i));
        }
    }

    private DS_COND_CONSTTMsgArray getDsCondConstByGrpId(String dsCondConstGrpId) {
        DS_COND_CONSTTMsg inMsg = new DS_COND_CONSTTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsCondConstGrpId01", dsCondConstGrpId);
        return (DS_COND_CONSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    private void sendCancelMail(ResultSet rs, Map<String, Object> callInfo) throws SQLException {

        // Get From Mail Address.
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_GROUP_KEY_FROM);
        S21MailAddress fromAddress;
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(NSBM0135E, new String[] {"FROM mail-address.", (MAIL_GROUP_ID_FROM + "/" + MAIL_GROUP_KEY_FROM) });
        }

        boolean isDealerCall = isDealerCall(callInfo);
        String zoneName = getZoneName(callInfo, isDealerCall);
        String zoneMapKey = getZoneMapKey(zoneName);
        boolean isAfterHours = isAfterHours(rs, callInfo);

        // Get To Mail Address.
        S21MailAddress toAddress = getToAddress(zoneMapKey, isAfterHours);
        if (toAddress == null) {
            throw new S21AbendException(NSBM0135E, new String[] {"TO mail-address.", DS_COND_CONST_NM_DEFAULT_ZONE });
        }

        // Get Mail Template.
        String templateId = getTemplateId(callInfo, isDealerCall, zoneMapKey);
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, templateId);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NSBM0077E, new String[] {templateId });
        }

        // Get Parameter.
        Map<String, String> paramMap = createCancelMailTemplateParamMap(callInfo, templateId);

        fromAddress = addrFromList.get(0);

        S21Mail mail = new S21Mail(this.glblCmpyCd);

        // Set From Mail Address.
        mail.setFromAddress(fromAddress);

        // Set To Mail Address.
        mail.setToAddress(toAddress);

        // Set Parameter.
        Iterator<Map.Entry<String, String>> iterator = paramMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            template.setTemplateParameter(entry.getKey(), entry.getValue());
        }

        mail.setMailTemplate(template);
        mail.setSubject(template.getSubject());
        mail.postMail();
    }

    private boolean isDealerCall(Map<String, Object> callInfo) {
        if (ZYPCommonFunc.hasValue((String) callInfo.get("PSN_CD")) && !PSN_TP.EMPLOYEE.equals((String) callInfo.get("PSN_TP_CD"))) {
            return true;
        }
        if (this.outTrtySvcBrCd.equals((String) callInfo.get("FLD_SVC_BR_CD"))) {
            return true;
        }
        if (this.clickSendExclCallTpList.contains((String) callInfo.get("DS_SVC_CALL_TP_CD"))) {
            return true;
        }
        return false;
    }

    private String getZoneName(Map<String, Object> callInfo, boolean isDealerCall) {
        if (!isDealerCall && ZYPCommonFunc.hasValue((String) callInfo.get("TECH_CD"))) {
            return getZoneByTech((String) callInfo.get("TECH_CD"));
        }
        return getZoneByDealer((String) callInfo.get("SVC_TASK_NUM"));
    }

    private String getZoneByTech(String techCd) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", this.glblCmpyCd);
        prmMap.put("slsDt", this.slsDt);
        prmMap.put("techCd", techCd);
        prmMap.put("service", BIZ_AREA_ORG.SERVICE);
        prmMap.put("employee", PSN_TP.EMPLOYEE);

        return (String) this.ssmClient.queryObject("getZoneByTech", prmMap);
    }

    private String getZoneByDealer(String svcTaskNum) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", this.glblCmpyCd);
        prmMap.put("svcTaskNum", svcTaskNum);
        prmMap.put("service", BIZ_AREA_ORG.SERVICE);
        prmMap.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (String) this.ssmClient.queryObject("getZoneByDealer", prmMap);
    }

    private String getZoneMapKey(String zoneName) {
        if (ZYPCommonFunc.hasValue(zoneName) && this.zoneMap.containsKey(zoneName)) {
            return zoneName;
        }
        if (this.zoneMap.containsKey(DS_COND_CONST_NM_DEFAULT_ZONE)) {
            return DS_COND_CONST_NM_DEFAULT_ZONE;
        }
        return null;
    }

    private boolean isAfterHours(ResultSet rs, Map<String, Object> callInfo) throws SQLException {
        String convCratTs = convSysToLoc(rs.getString("CRAT_TS"), (String) callInfo.get("CTRY_CD"), (String) callInfo.get("POST_CD"));
        if (!ZYPCommonFunc.hasValue(convCratTs)) {
            convCratTs = rs.getString("CRAT_TS");
        }
        String convCratDt = convCratTs.substring(0, SUB_STR_POS_8);
        String convCratTm = convCratTs.substring(SUB_STR_POS_8, SUB_STR_POS_14);
        int dayOfTheWeek = getDayOfTheWeek(convCratDt);
        SVC_PRC_SHIFTTMsg svcPrcShiftTMsg = getSvcPrcShiftInfo(convCratTm, dayOfTheWeek, (String) callInfo.get("SVC_BY_LINE_BIZ_TP_CD"));
        String ahsEnblFlg = getAhsEnblFlg(svcPrcShiftTMsg);
        if (ZYPConstant.FLG_ON_Y.equals(ahsEnblFlg)) {
            return true;
        }
        return false;
    }

    private String convSysToLoc(String dateTime, String ctryCd, String postCd) {
        int mode = NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC;
        SvcTimeZoneInfo info = NSXC001001SvcTimeZone.convertTime(mode, dateTime, ctryCd, postCd);
        if (info == null || !ZYPCommonFunc.hasValue(info.getDateTime())) {
            return null;
        }
        String convTs = info.getDateTime();
        if (!ZYPCommonFunc.hasValue(convTs) || convTs.length() < SUB_STR_POS_14) {
            return null;
        }
        return convTs;
    }

    private int getDayOfTheWeek(String date) {
        int dayOfTheWeek = DAY_OF_THE_WEEK_IS_HOLIDAY;
        CALTMsgArray calTMsgArray = getCal(CAL_TP.SVC_HOLIDAY, DT_ATTRB_VAL_CD, date);
        if (calTMsgArray.getValidCount() == 0) {
            Calendar cal = Calendar.getInstance();
            int yyyy = Integer.parseInt(date.substring(0, SUB_STR_POS_4));
            int mm = Integer.parseInt(date.substring(SUB_STR_POS_4, SUB_STR_POS_6));
            int dd = Integer.parseInt(date.substring(SUB_STR_POS_6, SUB_STR_POS_8));
            cal.set(yyyy, mm - 1, dd);
            dayOfTheWeek = cal.get(Calendar.DAY_OF_WEEK);
        }
        return dayOfTheWeek;
    }

    private CALTMsgArray getCal(String calTpCd, String dtAttrbValCd, String date) {
        CALTMsg tMsg = new CALTMsg();
        tMsg.setSQLID("903");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("calTpCd01", calTpCd);
        tMsg.setConditionValue("dtAttrbValCd01", dtAttrbValCd);
        tMsg.setConditionValue("calDt01", date);
        return (CALTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
    }

    private SVC_PRC_SHIFTTMsg getSvcPrcShiftInfo(String time, int dayOfTheWeek, String svcByLineBizTpCd) {
        SVC_PRC_SHIFTTMsgArray svcPrcShiftTMsgArray = getSvcPrcShift(time, dayOfTheWeek, svcByLineBizTpCd);
        if (svcPrcShiftTMsgArray.getValidCount() == 0) {
            return null;
        }
        return svcPrcShiftTMsgArray.no(0);
    }

    private SVC_PRC_SHIFTTMsgArray getSvcPrcShift(String time, int dayOfTheWeek, String svcByLineBizTpCd) {
        SVC_PRC_SHIFTTMsg tMsg = new SVC_PRC_SHIFTTMsg();
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("svcLineBizCd01", svcByLineBizTpCd);
        tMsg.setConditionValue("svcPrcShiftActvFlg01", ZYPConstant.FLG_ON_Y);
        if (dayOfTheWeek == Calendar.SUNDAY) {
            tMsg.setSQLID("001");
            tMsg.setConditionValue("svcPrcSunStartValTxt01", time);
            tMsg.setConditionValue("svcPrcSunEndValTxt01", time);
        } else if (dayOfTheWeek == Calendar.MONDAY) {
            tMsg.setSQLID("002");
            tMsg.setConditionValue("svcPrcMonStartValTxt01", time);
            tMsg.setConditionValue("svcPrcMonEndValTxt01", time);
        } else if (dayOfTheWeek == Calendar.TUESDAY) {
            tMsg.setSQLID("003");
            tMsg.setConditionValue("svcPrcTueStartValTxt01", time);
            tMsg.setConditionValue("svcPrcTueEndValTxt01", time);
        } else if (dayOfTheWeek == Calendar.WEDNESDAY) {
            tMsg.setSQLID("004");
            tMsg.setConditionValue("svcPrcWedStartValTxt01", time);
            tMsg.setConditionValue("svcPrcWedEndValTxt01", time);
        } else if (dayOfTheWeek == Calendar.THURSDAY) {
            tMsg.setSQLID("005");
            tMsg.setConditionValue("svcPrcThuStartValTxt01", time);
            tMsg.setConditionValue("svcPrcThuEndValTxt01", time);
        } else if (dayOfTheWeek == Calendar.FRIDAY) {
            tMsg.setSQLID("006");
            tMsg.setConditionValue("svcPrcFriStartValTxt01", time);
            tMsg.setConditionValue("svcPrcFriEndValTxt01", time);
        } else if (dayOfTheWeek == Calendar.SATURDAY) {
            tMsg.setSQLID("007");
            tMsg.setConditionValue("svcPrcSatStartValTxt01", time);
            tMsg.setConditionValue("svcPrcSatEndValTxt01", time);
        } else if (dayOfTheWeek == DAY_OF_THE_WEEK_IS_HOLIDAY) {
            tMsg.setSQLID("008");
            tMsg.setConditionValue("svcPrcHolStartValTxt01", time);
            tMsg.setConditionValue("svcPrcHolEndValTxt01", time);
        } else {
            return new SVC_PRC_SHIFTTMsgArray();
        }
        SVC_PRC_SHIFTTMsgArray svcPrcShiftMsgArray = (SVC_PRC_SHIFTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (svcPrcShiftMsgArray.getValidCount() == 0) {
            tMsg.setConditionValue("svcLineBizCd01", ALL_BIZ_LINE_TP_CD);
            svcPrcShiftMsgArray = (SVC_PRC_SHIFTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        }
        return svcPrcShiftMsgArray;
    }

    private String getAhsEnblFlg(SVC_PRC_SHIFTTMsg svcPrcShiftTMsg) {
        if (svcPrcShiftTMsg == null) {
            return ZYPConstant.FLG_ON_Y;
        }
        return svcPrcShiftTMsg.svcPrcShiftAhsFlg.getValue();
    }

    private S21MailAddress getToAddress(String zoneMapKey, boolean isAfterHours) {
        if (!ZYPCommonFunc.hasValue(zoneMapKey)) {
            return null;
        }
        DS_COND_CONSTTMsg dsCondConstTMsg = this.zoneMap.get(zoneMapKey);
        String address = dsCondConstTMsg.dsCondConstValTxt_02.getValue();
        String name = dsCondConstTMsg.dsCondConstValTxt_01.getValue();
        if (isAfterHours) {
            address = dsCondConstTMsg.dsCondConstValTxt_04.getValue();
            name = dsCondConstTMsg.dsCondConstValTxt_03.getValue();
        }
        return new S21MailAddress(this.glblCmpyCd, address, name);
    }

    private String getTemplateId(Map<String, Object> callInfo, boolean isDealerCall, String zoneMapKey) {
        String templateId = MAIL_TEMPLATE_ID_03;
        if (this.essDummyTech.equals((String) callInfo.get("TECH_CD"))) {
            templateId = MAIL_TEMPLATE_ID_04;
        } else if (DS_COND_CONST_NM_DEFAULT_ZONE.equals(zoneMapKey)) {
            templateId = MAIL_TEMPLATE_ID_05;
        } else if (!isDealerCall && ZYPCommonFunc.hasValue((String) callInfo.get("TECH_CD"))) {
            templateId = MAIL_TEMPLATE_ID_02;
        }
        return templateId;
    }

    private Map<String, String> createCancelMailTemplateParamMap(Map<String, Object> callInfo, String templateId) {

        Map<String, String> prmMap = new HashMap<String, String>();
        if (MAIL_TEMPLATE_ID_02.equals(templateId)) {
            prmMap.put("svcTaskNum", (String) callInfo.get("SVC_TASK_NUM"));
            prmMap.put("techCd", (String) callInfo.get("TECH_CD"));
            prmMap.put("firstNm", (String) callInfo.get("PSN_FIRST_NM"));
            prmMap.put("lastNm", (String) callInfo.get("PSN_LAST_NM"));
        } else if (MAIL_TEMPLATE_ID_03.equals(templateId)) {
            prmMap.put("svcTaskNum", (String) callInfo.get("SVC_TASK_NUM"));
            Map<String, Object> vendorInfo = getVendorInfo((String) callInfo.get("SVC_TASK_NUM"));
            if (vendorInfo != null) {
                prmMap.put("vndNm", (String) vendorInfo.get("VND_NM"));
                prmMap.put("vndCtacNm", (String) vendorInfo.get("VND_CTAC_NM"));
                prmMap.put("vndTelNum", (String) vendorInfo.get("VND_TEL_NUM"));
                prmMap.put("vndEmlAddr", (String) vendorInfo.get("VND_EML_ADDR"));
            }
        } else if (MAIL_TEMPLATE_ID_04.equals(templateId)) {
            prmMap.put("svcTaskNum", (String) callInfo.get("SVC_TASK_NUM"));
            prmMap.put("fsrNum", (String) callInfo.get("FSR_NUM"));
            prmMap.put("crsSvcSrNum", (String) callInfo.get("CRS_SVC_SR_NUM"));
        } else if (MAIL_TEMPLATE_ID_05.equals(templateId)) {
            prmMap.put("svcTaskNum", (String) callInfo.get("SVC_TASK_NUM"));
        }
        return prmMap;
    }

    private Map<String, Object> getVendorInfo(String svcTaskNum) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", this.glblCmpyCd);
        prmMap.put("slsDt", this.slsDt);
        prmMap.put("svcTaskNum", svcTaskNum);
        prmMap.put("maxDate", MAX_DATE);
        prmMap.put("dtFmt", ZYPDateUtil.TYPE_YYYYMMDD);
        prmMap.put("deliveryInstall", CTAC_TP.DELIVERY_INSTALL);
        prmMap.put("phoneWork", DS_CTAC_PNT_TP.PHONE_WORK);
        prmMap.put("faxWork", DS_CTAC_PNT_TP.FAX_WORK);
        prmMap.put("emailWork", DS_CTAC_PNT_TP.EMAIL_WORK);
        prmMap.put("terminated", DS_CONTR_DTL_STS.TERMINATED);
        prmMap.put("cancelled", DS_CONTR_DTL_STS.CANCELLED);
        prmMap.put("expired", DS_CONTR_DTL_STS.EXPIRED);

        return (Map<String, Object>) this.ssmClient.queryObject("getVendorInfo", prmMap);
    }
    // END 2023/08/03 K.Kitachi [QC#60012, ADD]

    // START 2023/08/08 N.Takatsu [QC#61771, ADD]
    private boolean isFsrNum(String fsrNum) {
        FSRTMsg tMsg = new FSRTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.fsrNum, fsrNum);
        FSRTMsg fsrtMsg = (FSRTMsg) EZDTBLAccessor.findByKey(tMsg);
        if (fsrtMsg == null) {
            return false;
        }
        return true;
    }
    // END   2023/08/08 N.Takatsu [QC#61771, ADD]
}
