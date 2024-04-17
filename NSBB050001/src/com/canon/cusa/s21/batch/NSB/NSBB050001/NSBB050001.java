/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB050001;

import static com.canon.cusa.s21.batch.NSB.NSBB050001.constant.NSBB050001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.DS_COND_CONSTTMsg;
import business.db.DS_COND_CONSTTMsgArray;
import business.db.FSR_EVENTTMsg;
import business.db.NSBI0500_01TMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMOTMsgArray;

import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001GetDownTm;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MBL_INTFC_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_DISPT_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * MDS Cloud Staging Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/10/2016   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSBB050001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd;

    /** Sales Date */
    private String slsDt;

    /** Interface Id */
    private String intfcId;

    /** Transaction ID */
    private BigDecimal trxId;

    /** Unit ID */
    private int unitId = 1;

    /** Transaction table Access */
    private S21TransactionTableAccessor trxAccess;

    /** Total Process Count */
    private int totalProcCount = 0;

    /** Process Time Stamp */
    private String procTs;

    /** TERM_CD */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** DS_COND_CONST:FROM_DT */
    private String dsCondConstFromDt;

    /** DS_COND_CONST:THRU_DT */
    private String dsCondConstThruDt;

    /** DS_COND_CONST:NO_SEND_FLG */
    private String dsCondConstNoSendFlg;

    /** DS_COND_CONST:EXEC_TS */
    private String dsCondConstExecTs;

    /** VAR_CHAR_CONST:mdsTmZnNm */
    private String mdsTmZnNm;

    /** Line Separator Form */
    private String lineSeparator = null;

    /** format SimpleDateFormat */
    private SimpleDateFormat formatYyyymmdd = new SimpleDateFormat(DATE_FORMAT, Locale.US);

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NSBB050001().executeBatch(NSBB050001.class.getSimpleName());
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.totalProcCount, 0);
    }

    @Override
    protected void initRoutine() {
        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSBM0032E, new String[] {GLBL_CMPY_CD });
        }

        // Get Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, this.getClass().getSimpleName());
        if (!hasValue(this.slsDt)) {
            throw new S21AbendException(NSBM0032E, new String[] {SLS_DT });
        }

        // Get Interface Id
        this.intfcId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(this.intfcId)) {
            throw new S21AbendException(NSBM0032E, new String[] {INTERFACE_ID });
        }

        // Get VAR_CHAR_CONST
        this.mdsTmZnNm = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_MDS_TM_ZN_NM, this.glblCmpyCd);
        if (!hasValue(this.mdsTmZnNm)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_MDS_TM_ZN_NM });
        }

        // Get System Timestamp
        this.procTs = ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);

        this.trxAccess = new S21TransactionTableAccessor();
        this.trxId = this.trxAccess.getNextTransactionId();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.lineSeparator = System.getProperty("line.separator");
    }

    @Override
    protected void mainRoutine() {
        // get DsCondConst
        getDsCondConst();

        boolean useDsCondConst = false;
        // check whether standard date is necessary
        Map<String, Object> param = null;
        if (!hasValue(this.dsCondConstExecTs) && hasValue(this.dsCondConstFromDt)) {
            // set parameter
            param = setParam(ZYPConstant.FLG_ON_Y, this.dsCondConstFromDt, this.dsCondConstThruDt);
            useDsCondConst = true;
        } else {
            param = setParam(ZYPConstant.FLG_OFF_N, null, null);
        }
        // get target data
        List<Map<String, Object>> closeFsrInfoList = getCloseFsrInfo(param);
        if (useDsCondConst) {
            // register interface data
            registerInterfaceData(closeFsrInfoList, useDsCondConst, null);
        } else {
            // get standard date list each Account number
            List<Map<String, Object>> stdDtList = createStdDtForAcctNum();
            // register interface data
            registerInterfaceData(closeFsrInfoList, useDsCondConst, stdDtList);
        }

        //update DS_COND_CONST Info
        if (useDsCondConst) {
            updateDsCondConst();
        }

        // insert INTERFACE_TRANSACTION
        if (this.totalProcCount > 0) {
            this.trxAccess.createIntegrationRecordForBatch(this.intfcId, this.trxId);
        }
    }

    private boolean isRegisterTarget(List<Map<String, Object>> stdDtList, String dsAcctNum, String fsrCloDt) {
        // get standard date for Account
        String stdDt = null;
        for (Map<String, Object> stdDtMap : stdDtList) {
            if (dsAcctNum.equals(stdDtMap.get("DS_ACCT_NUM").toString())) {
                stdDt = (String) stdDtMap.get("STD_DT");
                break;
            }
        }
        // check standard date and FSR close date
        if (ZYPDateUtil.compare(fsrCloDt, stdDt) <= 0) {
            return true;
        }
        return false;
    }

    private List<Map<String, Object>> createStdDtForAcctNum() {

        List<Map<String, Object>> stdDtList = new ArrayList<Map<String, Object>>();
        String stdDt = null;

        // get Ds Account Info from MDS_ACCT_TNNT
        List<Map<String, Object>> dsAcctNumList = getDsAcctNum();
        for (Map<String, Object> dsAcctNumInfo : dsAcctNumList) {
            // calculate standard date
            Map<String, Object> stdDtMap = new HashMap<String, Object>();
            stdDt = calcStdDt(dsAcctNumInfo);
            stdDtMap.put("DS_ACCT_NUM", (String) dsAcctNumInfo.get("DS_ACCT_NUM"));
            stdDtMap.put("STD_DT", stdDt);
            stdDtList.add(stdDtMap);
        }
        return stdDtList;
    }

    private void getDsCondConst() {
        DS_COND_CONSTTMsg inMsg = new DS_COND_CONSTTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsCondConstGrpId01", DS_COND_CONST_GRP_ID);
        DS_COND_CONSTTMsgArray outMsgArray = (DS_COND_CONSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        for (int i = 0; i < outMsgArray.getValidCount(); i++) {
            DS_COND_CONSTTMsg outMsg = outMsgArray.no(i);
            if (DS_COND_CONST_FROM_DT.equals(outMsg.dsCondConstCd.getValue())) {
                this.dsCondConstFromDt = outMsg.dsCondConstValTxt_01.getValue();
            } else if (DS_COND_CONST_THRU_DT.equals(outMsg.dsCondConstCd.getValue())) {
                this.dsCondConstThruDt = outMsg.dsCondConstValTxt_01.getValue();
            } else if (DS_COND_CONST_NO_SEND_FLG.equals(outMsg.dsCondConstCd.getValue())) {
                this.dsCondConstNoSendFlg = outMsg.dsCondConstValTxt_01.getValue();
            } else if (DS_COND_CONST_EXEC_TS.equals(outMsg.dsCondConstCd.getValue())) {
                this.dsCondConstExecTs = outMsg.dsCondConstValTxt_01.getValue();
            }
        }
    }

    private String getCmntTxtFromSvcMemo(String svcTaskNum01) {
        String cmntTxt = null;
        SVC_MEMOTMsg inMsg = new SVC_MEMOTMsg();
        inMsg.setSQLID("012");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("svcTaskNum01", svcTaskNum01);
        SVC_MEMOTMsgArray outMsgArray = (SVC_MEMOTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        for (int i = 0; i < outMsgArray.getValidCount(); i++) {
            cmntTxt = setSvcCmntTxt(outMsgArray.no(i).svcCmntTxt.getValue(), cmntTxt);
            if (cmntTxt.length() >= SVC_MEMO_LENGTH) {
                cmntTxt = cmntTxt.substring(0, SVC_MEMO_LENGTH);
                break;
            }
        }
        return cmntTxt;
    }

    private String setSvcCmntTxt(String svcCmntTxt, String cmntTxt) {
        svcCmntTxt = svcCmntTxt.replaceAll(this.lineSeparator, SPACE);
        svcCmntTxt = svcCmntTxt.replaceAll("\n", SPACE);
        StringBuilder sb = new StringBuilder();
        if (ZYPCommonFunc.hasValue(cmntTxt)) {
            sb.append(cmntTxt);
            sb.append(SPACE);
        }
        sb.append(svcCmntTxt);

        return sb.toString();
    }

    private List<Map<String, Object>> getDsAcctNum() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("closed", SVC_TASK_STS.CLOSED);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsAcctNum", ssmParam);
    }

    private List<Map<String, Object>> getCloseFsrInfo(Map<String, Object> setParam) {
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCloseFsrInfo", setParam);
    }

    private Map<String, Object> setParam(String searchByCondConst, String fromDt, String thruDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("slsDt", this.slsDt);
        ssmParam.put("svcPsnVisit", DEF_SVC_PBLM_TP_CD);
        ssmParam.put("employee", PSN_TP.EMPLOYEE);
        ssmParam.put("callCenter", DEF_CALL_CENTER);
        ssmParam.put("onLine", DEF_ON_LINE);
        ssmParam.put("closed", SVC_TASK_STS.CLOSED);
        ssmParam.put("searchByCondConst", searchByCondConst);
        ssmParam.put("fromDt", fromDt);
        ssmParam.put("thruDt", thruDt);
        ssmParam.put("sentToMDSCloud", SVC_DISPT_EVENT.SENT_TO_MDS_CLOUD);
        ssmParam.put("noSendFlg", this.dsCondConstNoSendFlg);
        return ssmParam;
    }

    private void registerInterfaceData(List<Map<String, Object>> closeFsrInfoList, boolean useDsCondConst, List<Map<String, Object>> stdDtList) {

        for (Map<String, Object> closeFsrInfo : closeFsrInfoList) {
            // check whether register target or not
            if (!useDsCondConst) {
                if (!isRegisterTarget(stdDtList, (String) closeFsrInfo.get("DS_ACCT_NUM"), (String) closeFsrInfo.get("FSR_CLO_DT"))) {
                    continue;
                }
            }
            // insert NSBI0500
            insertInterfaceData(closeFsrInfo);

            // register FSR_EVENT
            createFsrEvent(closeFsrInfo);

            this.totalProcCount++;
            this.unitId++;
        }
    }

    private void insertInterfaceData(Map<String, Object> closeFsrInfo) {
        NSBI0500_01TMsg inTMsg = setInterfaceData(closeFsrInfo);
        S21FastTBLAccessor.insert(inTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            throw new S21AbendException(NSBM0164E, new String[] {TABLE_INTERFACE });
        }
    }

    private void createFsrEvent(Map<String, Object> closeFsrInfo) {

        BigDecimal fsrEventPk = ZYPOracleSeqAccessor.getNumberBigDecimal("FSR_EVENT_SQ");
        FSR_EVENTTMsg tMsg = new FSR_EVENTTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.fsrEventPk, fsrEventPk);
        setValue(tMsg.svcDisptEventCd, SVC_DISPT_EVENT.SENT_TO_MDS_CLOUD);
        setValue(tMsg.svcTaskNum, setValStr((String) closeFsrInfo.get("SVC_TASK_NUM")));
        setValue(tMsg.fsrNum, setValStr((String) closeFsrInfo.get("FSR_NUM")));
        setValue(tMsg.fsrVisitNum, setValStr((String) closeFsrInfo.get("FSR_VISIT_NUM")));
        setValue(tMsg.fsrEventExeUsrId, BATCH_ID);
        setValue(tMsg.fsrEventExeTs, this.procTs);
        setValue(tMsg.mblIntfcProcCd, MBL_INTFC_PROC.NO_NEED);
        setValue(tMsg.mblIntfcFlg, ZYPConstant.FLG_OFF_N);

        S21FastTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(NSBM0164E, new String[] {TABLE_FSR_EVENT });
        }
    }

    private void updateDsCondConst() {
        DS_COND_CONSTTMsg inMsg = new DS_COND_CONSTTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.dsCondConstGrpId, DS_COND_CONST_GRP_ID);
        setValue(inMsg.dsCondConstCd, DS_COND_CONST_EXEC_TS);
        DS_COND_CONSTTMsg outMsg = (DS_COND_CONSTTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
        if (outMsg != null) {
            setValue(inMsg.dsCondConstValTxt_01, this.procTs);
            S21FastTBLAccessor.update(outMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                throw new S21AbendException(NSBM0164E, new String[] {TABLE_DS_COND_CONST});
            }
        }
    }

    private String createAddrInfo(NSBI0500_01TMsg inTMsg) {
        String addrInfo = null;
        if (hasValue(inTMsg.fill256Txt_01)) {
            addrInfo = inTMsg.fill256Txt_01.getValue();
        }
        if (hasValue(addrInfo) && hasValue(inTMsg.ctyAddr)) {
            addrInfo = ZYPCommonFunc.concatString(addrInfo, SPACE, inTMsg.ctyAddr.getValue());
        } else if (!hasValue(addrInfo)) {
            addrInfo = inTMsg.ctyAddr.getValue();
        }
        if (hasValue(addrInfo) && hasValue(inTMsg.stCd)) {
            addrInfo = ZYPCommonFunc.concatString(addrInfo, SPACE, inTMsg.stCd.getValue());
        } else if (!hasValue(addrInfo)) {
            addrInfo = inTMsg.stCd.getValue();
        }
        if (hasValue(addrInfo) && hasValue(inTMsg.postCd)) {
            addrInfo = ZYPCommonFunc.concatString(addrInfo, SPACE, inTMsg.postCd.getValue());
        } else if (!hasValue(addrInfo)) {
            addrInfo = inTMsg.postCd.getValue();
        }

        return cutStr(addrInfo, ADDR_INFO_LENGTH);
    }

    private String cutStr(String str, int length) {
        if (!hasValue(str)) {
            return null;
        } else if (str.length() > length) {
            return str.substring(0, length);
        }
        return str;
    }

    private String setValStr(String str) {
        if (!hasValue(str)) {
            return null;
        }
        return str;
    }

    private String setValBd(BigDecimal bd) {
        if (!hasValue(bd)) {
            return null;
        }
        return bd.toString();
    }

    private String setValIntForMin(int intParam) {
        if (intParam == 0) {
            return String.valueOf(intParam);
        }
        return String.valueOf(intParam * MINUTES);
    }

    private NSBI0500_01TMsg setInterfaceData(Map<String, Object> closeFsrInfo) {
        NSBI0500_01TMsg inTMsg = new NSBI0500_01TMsg();
        setValue(inTMsg.interfaceId, this.intfcId);
        setValue(inTMsg.transactionId, this.trxId);
        setValue(inTMsg.segmentId, BigDecimal.ONE);
        setValue(inTMsg.unitId, BigDecimal.valueOf(this.unitId));
        setValue(inTMsg.seqNumber, BigDecimal.ONE);
        setValue(inTMsg.mdsTnntId, setValStr((String) closeFsrInfo.get("MDS_TNNT_ID")));
        setValue(inTMsg.fsrCloDt, setValStr((String) closeFsrInfo.get("FSR_CLO_DT")));
        setValue(inTMsg.fsrNum, setValStr((String) closeFsrInfo.get("FSR_NUM")));
        setValue(inTMsg.svcTaskNum, setValStr((String) closeFsrInfo.get("SVC_TASK_NUM")));
        setValue(inTMsg.serNum, setValStr((String) closeFsrInfo.get("SER_NUM")));
        setValue(inTMsg.svcCallPrtyDescTxt, setValStr((String) closeFsrInfo.get("SVC_CALL_PRTY_DESC_TXT")));
        setValue(inTMsg.svcTaskStsDescTxt, setValStr((String) closeFsrInfo.get("SVC_TASK_STS_DESC_TXT")));
        setValue(inTMsg.fill20Txt_02, this.mdsTmZnNm);
        setValue(inTMsg.dsAcctNm, setValStr((String) closeFsrInfo.get("DS_ACCT_NM")));
        setValue(inTMsg.fill256Txt_01, setValStr((String) closeFsrInfo.get("FILL_256_TXT_01")));
        setValue(inTMsg.ctyAddr, setValStr((String) closeFsrInfo.get("CTY_ADDR")));
        setValue(inTMsg.stCd, setValStr((String) closeFsrInfo.get("ST_CD")));
        setValue(inTMsg.postCd, setValStr((String) closeFsrInfo.get("POST_CD")));
        String addressInfo = createAddrInfo(inTMsg);
        setValue(inTMsg.fill256Txt_02, addressInfo);
        setValue(inTMsg.svcTaskRcvDt, setValStr((String) closeFsrInfo.get("SVC_TASK_RCV_DT")));
        setValue(inTMsg.svcTaskRcvTm, setValStr((String) closeFsrInfo.get("SVC_TASK_RCV_TM")));
        setValue(inTMsg.techAcptDt, setValStr((String) closeFsrInfo.get("TECH_ACPT_DT")));
        setValue(inTMsg.techAcptTm, setValStr((String) closeFsrInfo.get("TECH_ACPT_TM")));
        setValue(inTMsg.fsrVisitArvDt, setValStr((String) closeFsrInfo.get("FSR_VISIT_ARV_DT")));
        setValue(inTMsg.fsrVisitArvTm, setValStr((String) closeFsrInfo.get("FSR_VISIT_ARV_TM")));
        setValue(inTMsg.fsrVisitCpltDt, setValStr((String) closeFsrInfo.get("FSR_VISIT_CPLT_DT")));
        setValue(inTMsg.fsrVisitCpltTm, setValStr((String) closeFsrInfo.get("FSR_VISIT_CPLT_TM")));
        setValue(inTMsg.svcTaskCloDt, setValStr((String) closeFsrInfo.get("SVC_TASK_CLO_DT")));
        setValue(inTMsg.svcTaskCloTm, setValStr((String) closeFsrInfo.get("SVC_TASK_CLO_TM")));
        setValue(inTMsg.fill20Txt_06, setValBd((BigDecimal) closeFsrInfo.get("FILL_20_TXT_06")));
        setValue(inTMsg.fill20Txt_07, setValBd((BigDecimal) closeFsrInfo.get("FILL_20_TXT_07")));
        BigDecimal downTm = NSXC002001GetDownTm.getDownTm(this.glblCmpyCd, (String) closeFsrInfo.get("SVC_TASK_NUM"));
        setValue(inTMsg.fill20Txt_08, setValIntForMin(downTm.intValue()));
        setValue(inTMsg.svcPblmTpDescTxt, setValStr((String) closeFsrInfo.get("SVC_PBLM_TP_DESC_TXT")));
        setValue(inTMsg.svcPblmCrctTpDescTxt, setValStr((String) closeFsrInfo.get("SVC_PBLM_CRCT_TP_DESC_TXT")));
        setValue(inTMsg.svcPblmRsnTpDescTxt, setValStr((String) closeFsrInfo.get("SVC_PBLM_RSN_TP_DESC_TXT")));
        setValue(inTMsg.svcPblmLocTpDescTxt, setValStr((String) closeFsrInfo.get("SVC_PBLM_LOC_TP_DESC_TXT")));
        String svcCmntTxt = getCmntTxtFromSvcMemo((String) closeFsrInfo.get("SVC_TASK_NUM"));
        setValue(inTMsg.fill256Txt_03, svcCmntTxt);
        setValue(inTMsg.fill20Txt_09, setValStr((String) closeFsrInfo.get("FILL_20_TXT_09")));
        setValue(inTMsg.svcPblmCrctTpCd, setValStr((String) closeFsrInfo.get("SVC_PBLM_CRCT_TP_CD")));
        setValue(inTMsg.fill20Txt_11, setValStr((String) closeFsrInfo.get("FILL_20_TXT_11")));
        setValue(inTMsg.fill100Txt_01, setValStr((String) closeFsrInfo.get("FILL_100_TXT_01")));
        setValue(inTMsg.fill100Txt_02, setValStr((String) closeFsrInfo.get("FILL_100_TXT_02")));
        setValue(inTMsg.techNm_01, setValStr((String) closeFsrInfo.get("TECH_NM")));
        setValue(inTMsg.coaBrDescTxt, setValStr((String) closeFsrInfo.get("COA_BR_DESC_TXT")));
        setValue(inTMsg.techNm_02, setValStr((String) closeFsrInfo.get("TECH_NM")));

        return inTMsg;
    }

    private String calcStdDt(Map<String, Object> dsAcctNumInfo) {
        String stdDt = null;
        // calculate last day of the previous month of sales date
        Calendar prevDtCal = getPrevMonthDt();
        int lastDayOfMonth = prevDtCal.getActualMaximum(Calendar.DATE);
        String prevMthDt = this.formatYyyymmdd.format(prevDtCal.getTime());

        // compare lastDayOfMonth and CUT_OFF_AOT
        int cutOffAot = getCutOffAot(dsAcctNumInfo);
        String cutOffDt = calcuCutOffDt(cutOffAot, lastDayOfMonth, prevMthDt);

        // get days between slsDt and cutOffDt
        int dayDiff = ZYPDateUtil.getDiffDays(this.slsDt, cutOffDt);

        // compare (slsDt - cutOffdt) and MDS_ACCT_TNNT.NO_PROC_AOT
        int noProcAot = getNoProcAot(dsAcctNumInfo);
        String preStdDt = calcuPreStdDt(noProcAot, cutOffDt, dayDiff);

        // compare MDS_ACCT_TNNT.START_DT and preStdDt
        stdDt = preStdDt;
        if (dsAcctNumInfo.get("START_DT") != null && dsAcctNumInfo.get("START_DT").toString().compareTo(preStdDt) > 0) {
            stdDt = dsAcctNumInfo.get("START_DT").toString();
        }
        return stdDt;
    }

    private int getNoProcAot(Map<String, Object> dsAcctNumInfo) {
        BigDecimal noProcAot = ZYPCodeDataUtil.getNumConstValue(NUM_CONST_DEF_MDS_PROC_AOT, this.glblCmpyCd);
        if (dsAcctNumInfo.get("NO_PROC_AOT") != null) {
            noProcAot = (BigDecimal) dsAcctNumInfo.get("NO_PROC_AOT");
        }
        return noProcAot.intValue();
    }

    private int getCutOffAot(Map<String, Object> dsAcctNumInfo) {
        BigDecimal cutOffAotBd = ZYPCodeDataUtil.getNumConstValue(NUM_CONST_DEF_MDS_CUT_OFF_AOT, this.glblCmpyCd);
        if (dsAcctNumInfo.get("CUT_OFF_AOT") != null) {
            cutOffAotBd = (BigDecimal) dsAcctNumInfo.get("CUT_OFF_AOT");
        }
        int cutOffAot = cutOffAotBd.intValue();
        return cutOffAot;
    }

    private Calendar getPrevMonthDt() {
        Calendar prevDtCal = Calendar.getInstance();
        Date inParamDate = toDate(this.slsDt, DATE_FORMAT);
        prevDtCal.setTime(inParamDate);
        prevDtCal.add(Calendar.MONTH, -1);
        return prevDtCal;
    }

    private String calcuPreStdDt(int noProcAot, String cutOffDt, int dayDiff) {
        String preStdDt;

        if (dayDiff <= noProcAot) {
            preStdDt = cutOffDt.substring(0, YYYYMM_LENGTH).concat(START_DAY);
        } else {
            Calendar preStdCal = Calendar.getInstance();
            Date preStdDate = toDate(cutOffDt, DATE_FORMAT);
            preStdCal.setTime(preStdDate);
            preStdCal.add(Calendar.DAY_OF_MONTH, 1);
            preStdDt = this.formatYyyymmdd.format(preStdCal.getTime());
        }
        return preStdDt;
    }

    private String calcuCutOffDt(int cutOffAot, int lastDayOfMonth, String prevDt) {
        String cutOffDt;
        if (cutOffAot >= lastDayOfMonth) {
            cutOffDt = prevDt.substring(0, YYYYMM_LENGTH).concat(String.valueOf(lastDayOfMonth));
        } else {
            cutOffDt = prevDt.substring(0, YYYYMM_LENGTH).concat(toStr(cutOffAot));
        }
        return cutOffDt;
    }

    private Date toDate(String fromTs, String formatFrom) {

        SimpleDateFormat parser = new SimpleDateFormat(formatFrom);
        parser.setLenient(false);

        try {
            return parser.parse(fromTs);
        } catch (ParseException e) {
            return null;
        }
    }

    private String toStr(int day) {

        if (day < PAD_TARGET_NUM) {
            return ZYPCommonFunc.leftPad(String.valueOf(day), DAY_LENGTH, PAD_STR);
        }
        return String.valueOf(day);
    }
}
