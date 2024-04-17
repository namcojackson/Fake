/*
 * <Pre>Copyright (c) 2020 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLA.NLAB091001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.WMS_INBD_TRXTMsg;
import business.db.WMS_TASKTMsg;

import com.canon.cusa.s21.batch.NLA.NLAB091001.constant.NLAB091001Constant;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TASK;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;


/**
 * <pre>
 * Batch Program Class for Receipt Notification from MNX
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/21/2020   CITS            A.Marte         Create          QC#57659
 * 03/19/2021   CITS            J.Evangelista   Update          QC#58418
 *</pre>
 */

public class NLAB091001 extends S21BatchMain {

    // *********************************************************
    // Instance Variables: Basic
    // *********************************************************

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    /** */
    private S21TransactionTableAccessor trxAccess = new S21TransactionTableAccessor();

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Interface ID */
    private String interfaceId = null;

    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList = null;

    /** Termination Code of Execution Process */
    private TERM_CD termCd = null;

    /** Counter of Records: Successful */
    private int successCount = 0;

    /** Counter of Records: Error */
    private int errorCount = 0;

    /** */
    private String trgtWhOwnrCd;

    /** */
    private String intfcTpId;

    /** */
    private String wmsPackCdMode;

    /**
     * Initialization Routine.
     */
    @Override
    protected void initRoutine() {

        // Initialize Variables
        termCd = TERM_CD.NORMAL_END;
        errMsgList = new ArrayList<Map<String, String>>();

        // Getting Global Company Code
        S21UserProfileServiceFactory profFactory = S21UserProfileServiceFactory.getInstance();
        S21UserProfileService prof = profFactory.getService();
        glblCmpyCd = prof.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NLAB091001Constant.NLGM0049E, new String[] {NLAB091001Constant.PARAM_NM_GLBL_CMPY_CD });
        }

        // Getting Interface ID
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NLAB091001Constant.NLGM0049E, new String[] {NLAB091001Constant.PARAM_NM_INTERFACE_ID });
        }

        // Get target WH code
        this.trgtWhOwnrCd = WH_OWNR.MNX;

        // Get INTFC_TP_ID for WMS_TASK_CD=RCVD
        this.intfcTpId = getIntfcTpId(glblCmpyCd, WMS_TASK.RCVD);

        // Aquiring mode for WMS_PACK_CD. Normally "Y"
        this.wmsPackCdMode = ZYPCodeDataUtil.getVarCharConstValue(NLAB091001Constant.VAR_CHAR_CONST_NM_WMS_PACK_CD_SET_OWNER_CD_FLG, glblCmpyCd);

    }

    /**
     * Main Routine.
     */
    @Override
    protected void mainRoutine() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

        // Get Target TRANSACTION_ID
        BigDecimal[] tranIds = trxAccess.getIntegrationRecord(this.interfaceId);

        if (tranIds.length <= 0) {
            return;
        }

        try {
            for (BigDecimal trxId : tranIds) {
                // SO
                createRcptConfInterface(this.interfaceId, trxId, this.glblCmpyCd);
                commit();
            }

        } catch (S21AbendException e) {
            rollback();
            throw e;
        } finally {
            if (errMsgList.size() > 0) {
                this.termCd = TERM_CD.WARNING_END;
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(NLAB091001Constant.BUSINESS_ID, errMsgList);
                commit();
            }
        }

    }

    /**
     * Termination Routine.
     */
    @Override
    protected void termRoutine() {

        // Setting Process Termination Code
        setTermState(termCd, this.successCount, this.errorCount, this.successCount + this.errorCount);
    }

    /** Startup
     * @param args arguments
     */
    public static void main(String[] args) {

        new NLAB091001().executeBatch(NLAB091001.class.getSimpleName());
    }

    private String getIntfcTpId(String glblCmpyCdVal, String wmsTaskCd) {
        String intfcTpIdVal = null;
        WMS_TASKTMsg tMsg = new WMS_TASKTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCdVal);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsTaskCd, wmsTaskCd);
        tMsg = (WMS_TASKTMsg) EZDTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            intfcTpIdVal = tMsg.intfcTpId.getValue();
        }
        return intfcTpId;
    }

    /**
     * Output Error Message.
     * @param msgId MessageId
     * @param msgParam MessageParameters
     */
    private void outputErr(String msgId, String[] msgParam) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
        errMsgList.add(mailParam);

        S21InfoLogOutput.println(msgId, msgParam);
    }

    /**
     * Receipt Notification Data Creation Process
     */
    private void createRcptConfInterface(String ifId, BigDecimal trxId, String glblCmpyCdVal) {

        List<Map<String, Object>> wmsHdrList = getWmsRcptConfHeaderList(ifId, trxId, glblCmpyCdVal);

        // Loop n UNIT_ID unit
        for (Map<String, Object> wmsHdr : wmsHdrList) {

            BigDecimal segmentId = (BigDecimal) wmsHdr.get(NLAB091001Constant.COL_SEGMENT_ID);
            BigDecimal unitId = (BigDecimal) wmsHdr.get(NLAB091001Constant.COL_UNIT_ID);
            List<Map<String, Object>> wmsDtlList = getWmsRcptConfDetailList(ifId, trxId, segmentId, unitId);

            //String glblCmpyCd = (String)wmsHdr.get(NLAB091001Constant.COL_GLBL_CMPY_CD);
            String rwsNum = (String) wmsHdr.get(NLAB091001Constant.COL_RWS_NUM);
            List<Map<String, Object>> rwsDtlList = Collections.emptyList();

            if (ZYPCommonFunc.hasValue(rwsNum)) {
                rwsDtlList = getRwsDetailList(glblCmpyCdVal, rwsNum);
            }

            populateWmsRcptConfDetail(wmsHdr, wmsDtlList, rwsDtlList);

            boolean valid = validateRcptConfIFData(wmsHdr, wmsDtlList);

            String positionKey = NLXCMsgHelper.toListedString(NLAB091001Constant.COL_INTERFACE_ID, NLAB091001Constant.COL_TRANSACTION_ID, NLAB091001Constant.COL_SEGMENT_ID, NLAB091001Constant.COL_UNIT_ID);
            String position = NLXCMsgHelper.toListedString(ifId, trxId, segmentId, unitId);

            List<WMS_INBD_TRXTMsg> wmsTrxTMsgList = createWmsInbdTrx(wmsHdr, wmsDtlList);
            int count = S21FastTBLAccessor.insert(wmsTrxTMsgList.toArray(new WMS_INBD_TRXTMsg[wmsTrxTMsgList.size()]));
            if (count != wmsTrxTMsgList.size()) {
                outputErr(NLAB091001Constant.NLGM0045E, new String[] {NLAB091001Constant.TBL_WMS_INBD_TRX, NLAB091001Constant.TBL_NLAI2420_01, positionKey, position });

                throw new S21AbendException(NLAB091001Constant.NLGM0045E, new String[] {NLAB091001Constant.TBL_WMS_INBD_TRX, NLAB091001Constant.TBL_NLAI2420_01, positionKey, position });
            }

            if (valid) {
                ++this.successCount;
            } else {
                ++this.errorCount;
            }
        }

        // Update the flag to processed in any case - success/fail -
        trxAccess.endIntegrationProcess(ifId, trxId);

    }

    /**
     * getWmsRcptConfDetail
     * @param ifId
     * @param trxId
     * @param glblCmpyCd
     * @return
     */
    private List<Map<String, Object>> getWmsRcptConfHeaderList(String ifId, BigDecimal trxId, String glblCmpyCdVal) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLAB091001Constant.COL_INTERFACE_ID, ifId);
        queryParam.put(NLAB091001Constant.COL_TRANSACTION_ID, trxId);
        queryParam.put(NLAB091001Constant.COL_GLBL_CMPY_CD, glblCmpyCdVal);
        queryParam.put(NLAB091001Constant.COL_INBD_OTBD_CD, INBD_OTBD.INBOUND); /* Inbound */
        queryParam.put(NLAB091001Constant.COL_WH_OWNR_CD, this.trgtWhOwnrCd); /* MNX */

        return this.ssmBatchClient.queryObjectList("getWmsRcptConfHeader", queryParam, execParam);
    }

    /**
     * getWmsRcptConfDetail
     * @param ifId
     * @param trxId
     * @param glblCmpyCd
     * @return
     */
    private List<Map<String, Object>> getWmsRcptConfDetailList(String ifId, BigDecimal trxId, BigDecimal segmentId, BigDecimal unitId) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLAB091001Constant.COL_INTERFACE_ID, ifId);
        queryParam.put(NLAB091001Constant.COL_TRANSACTION_ID, trxId);
        queryParam.put(NLAB091001Constant.COL_SEGMENT_ID, segmentId);
        queryParam.put(NLAB091001Constant.COL_UNIT_ID, unitId);

        return this.ssmBatchClient.queryObjectList("getWmsRcptConfDetail", queryParam, execParam);
    }

    /**
     * getWmsRcptConfDetail
     * @param ifId
     * @param tranId
     * @param glblCmpyCd
     * @return
     */
    private List<Map<String, Object>> getRwsDetailList(String glblCmpyCdVal, String rwsNum) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLAB091001Constant.COL_GLBL_CMPY_CD, glblCmpyCdVal);
        queryParam.put(NLAB091001Constant.COL_RWS_NUM, rwsNum);

        return this.ssmBatchClient.queryObjectList("getRwsDetail", queryParam, execParam);
    }

    private void populateWmsRcptConfDetail(Map<String, Object> wmsHdr, List<Map<String, Object>> wmsDtlList, List<Map<String, Object>> rwsDtlList) {

        ArrayList<Map<String, Object>> wmsExtraDtlList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> wmsDtl : wmsDtlList) {
            String wmsMdseCd = (String) wmsDtl.get(NLAB091001Constant.COL_ITEM_CD_TXT);
            String wmsQtyOrdTxt = (String) wmsDtl.get(NLAB091001Constant.COL_QTY_ORD_TXT);

            if (!(ZYPCommonFunc.hasValue(wmsMdseCd) && ZYPCommonFunc.hasValue(wmsQtyOrdTxt))) {
                continue;
            }

            // RWS_DTL_LINE_NUM is not in the response from Choice, and
            // response MIGHT have same MDSE in its details.
            // Try mapping RWS_DTL_LINE_NUM considering receipt quantity with the
            // lines in response.

            BigDecimal wmsRcptQty = new BigDecimal(wmsQtyOrdTxt);

            // Use single loop only to handle multiple items in RWS_DTL
            ArrayList<Map<String, Object>> wmsAddedDtlList = new ArrayList<Map<String, Object>>();
            for (int n = 0; BigDecimal.ZERO.compareTo(wmsRcptQty) < 0 && n < rwsDtlList.size(); ++n) {
                Map<String, Object> rwsDtl = rwsDtlList.get(n);
                String rwsMdseCd = (String) rwsDtl.get(NLAB091001Constant.COL_MDSE_CD);
                BigDecimal rwsUnrcptQty = (BigDecimal) rwsDtl.get(NLAB091001Constant.COL_UNRCPT_QTY);
                // TRY1: Search the line that matches a quantity - fully
                if (wmsMdseCd.equals(rwsMdseCd) && wmsRcptQty.compareTo(rwsUnrcptQty) == 0) {
                    HashMap<String, Object> wmsAddedDtl = new HashMap<String, Object>(wmsDtl);
                    wmsAddedDtl.put(NLAB091001Constant.COL_RWS_DTL_LINE_NUM, rwsDtl.get(NLAB091001Constant.COL_RWS_DTL_LINE_NUM));
                    wmsAddedDtl.put(NLAB091001Constant.COL_INVTY_LOC_CD, rwsDtl.get(NLAB091001Constant.COL_INVTY_LOC_CD));
                    wmsAddedDtl.put(NLAB091001Constant.COL_INVTY_STK_STS_CD, rwsDtl.get(NLAB091001Constant.COL_INVTY_STK_STS_CD));
                    wmsAddedDtl.put(NLAB091001Constant.COL_RTL_SWH_CD, rwsDtl.get(NLAB091001Constant.COL_RTL_SWH_CD));
                    wmsAddedDtl.put(NLAB091001Constant.COL_QTY_ORD_TXT, wmsRcptQty.toPlainString());
                    wmsAddedDtlList.add(wmsAddedDtl);
                    rwsUnrcptQty = BigDecimal.ZERO;
                    rwsDtl.put(NLAB091001Constant.COL_UNRCPT_QTY, rwsUnrcptQty);
                    wmsRcptQty = BigDecimal.ZERO;
                // TRY2: Search the line that rwsUnrcptQty is greater than
                } else if (wmsMdseCd.equals(rwsMdseCd) && wmsRcptQty.compareTo(rwsUnrcptQty) < 0) {
                    HashMap<String, Object> wmsAddedDtl = new HashMap<String, Object>(wmsDtl);
                    wmsAddedDtl.put(NLAB091001Constant.COL_RWS_DTL_LINE_NUM, rwsDtl.get(NLAB091001Constant.COL_RWS_DTL_LINE_NUM));
                    wmsAddedDtl.put(NLAB091001Constant.COL_INVTY_LOC_CD, rwsDtl.get(NLAB091001Constant.COL_INVTY_LOC_CD));
                    wmsAddedDtl.put(NLAB091001Constant.COL_INVTY_STK_STS_CD, rwsDtl.get(NLAB091001Constant.COL_INVTY_STK_STS_CD));
                    wmsAddedDtl.put(NLAB091001Constant.COL_RTL_SWH_CD, rwsDtl.get(NLAB091001Constant.COL_RTL_SWH_CD));
                    rwsUnrcptQty = rwsUnrcptQty.subtract(wmsRcptQty);
                    wmsAddedDtl.put(NLAB091001Constant.COL_QTY_ORD_TXT, wmsRcptQty.toPlainString());
                    wmsAddedDtlList.add(wmsAddedDtl);
                    rwsDtl.put(NLAB091001Constant.COL_UNRCPT_QTY, rwsUnrcptQty);
                    wmsRcptQty = BigDecimal.ZERO;
                // TRY3: Subtract quantity that matches mdseCd - received
                } else if (wmsMdseCd.equals(rwsMdseCd) && BigDecimal.ZERO.compareTo(rwsUnrcptQty) < 0) {
                    HashMap<String, Object> wmsAddedDtl = new HashMap<String, Object>(wmsDtl);
                    wmsAddedDtl.put(NLAB091001Constant.COL_RWS_DTL_LINE_NUM, rwsDtl.get(NLAB091001Constant.COL_RWS_DTL_LINE_NUM));
                    wmsAddedDtl.put(NLAB091001Constant.COL_INVTY_LOC_CD, rwsDtl.get(NLAB091001Constant.COL_INVTY_LOC_CD));
                    wmsAddedDtl.put(NLAB091001Constant.COL_INVTY_STK_STS_CD, rwsDtl.get(NLAB091001Constant.COL_INVTY_STK_STS_CD));
                    wmsAddedDtl.put(NLAB091001Constant.COL_RTL_SWH_CD, rwsDtl.get(NLAB091001Constant.COL_RTL_SWH_CD));
                    BigDecimal minQty = wmsRcptQty.min(rwsUnrcptQty);
                    wmsAddedDtl.put(NLAB091001Constant.COL_QTY_ORD_TXT, minQty.toPlainString());
                    wmsRcptQty = wmsRcptQty.subtract(minQty);
                    wmsAddedDtlList.add(wmsAddedDtl);
                    rwsUnrcptQty = rwsUnrcptQty.subtract(minQty);
                    rwsDtl.put(NLAB091001Constant.COL_UNRCPT_QTY, rwsUnrcptQty);
                // START 2021/03/19 J.Evangelista [QC#58418,ADD]
                // TRY4: Check if RWS is already received
                } else if (wmsMdseCd.equals(rwsMdseCd) && BigDecimal.ZERO.compareTo(rwsUnrcptQty) == 0
                        && RWS_STS.RECEIVED.equals(rwsDtl.get(NLAB091001Constant.COL_RWS_STS_CD))) {
                    HashMap<String, Object> wmsAddedDtl = new HashMap<String, Object>(wmsDtl);
                    wmsAddedDtl.put(NLAB091001Constant.COL_RWS_DTL_LINE_NUM, rwsDtl.get(NLAB091001Constant.COL_RWS_DTL_LINE_NUM));
                    wmsAddedDtl.put(NLAB091001Constant.COL_INVTY_LOC_CD, rwsDtl.get(NLAB091001Constant.COL_INVTY_LOC_CD));
                    wmsAddedDtl.put(NLAB091001Constant.COL_INVTY_STK_STS_CD, rwsDtl.get(NLAB091001Constant.COL_INVTY_STK_STS_CD));
                    wmsAddedDtl.put(NLAB091001Constant.COL_RTL_SWH_CD, rwsDtl.get(NLAB091001Constant.COL_RTL_SWH_CD));
                    wmsAddedDtl.put(NLAB091001Constant.COL_QTY_ORD_TXT, BigDecimal.ZERO.toPlainString());
                    wmsAddedDtlList.add(wmsAddedDtl);
                // END 2021/03/19 J.Evangelista [QC#58418,ADD]
                }
            }
            // Fix details
            if (wmsAddedDtlList.size() > 0) {
                Map<String, Object> wmsAddedDtl = wmsAddedDtlList.get(0);
                wmsDtl.clear();
                wmsDtl.putAll(wmsAddedDtl);
                wmsAddedDtlList.remove(0);
                wmsExtraDtlList.addAll(wmsAddedDtlList);
            }
        }
        // Merge extra details
        wmsDtlList.addAll(wmsExtraDtlList);
    }

    /**
     * validateRcptConfIFData
     * @param wmsHdr
     * @param wmsDtlList
     * @param tranId
     * @param unitId
     * @return
     */
    private boolean validateRcptConfIFData(Map<String, Object> wmsHdr, List<Map<String, Object>> wmsDtlList) {

        String ifId = (String) wmsHdr.get(NLAB091001Constant.COL_INTERFACE_ID);
        String ordNum = (String) wmsHdr.get(NLAB091001Constant.COL_INBD_ORD_NUM);
        BigDecimal trxId = (BigDecimal) wmsHdr.get(NLAB091001Constant.COL_TRANSACTION_ID);
        BigDecimal segmentId = (BigDecimal) wmsHdr.get(NLAB091001Constant.COL_SEGMENT_ID);
        BigDecimal unitId = (BigDecimal) wmsHdr.get(NLAB091001Constant.COL_UNIT_ID);

        // Assume record has invalid data.
        setIntfcProcStsAsError(wmsHdr, NLAB091001Constant.NLGM0089E);

        if (wmsHdr.get(NLAB091001Constant.COL_WH_CD) == null) {
            outputErr(NLAB091001Constant.NLGM0089E, new String[] {NLAB091001Constant.COL_WH_CD, ordNum, ifId, trxId.toPlainString(), segmentId.toPlainString() });
            return false;
        }
        if (wmsHdr.get(NLAB091001Constant.COL_INTERFACE_ID) == null) {
            outputErr(NLAB091001Constant.NLGM0089E, new String[] {NLAB091001Constant.COL_INTERFACE_ID, ordNum, ifId, trxId.toPlainString(), segmentId.toPlainString() });
            return false;
        }
        if (wmsHdr.get(NLAB091001Constant.COL_EZINTIME) == null) {
            outputErr(NLAB091001Constant.NLGM0089E, new String[] {NLAB091001Constant.COL_EZINTIME, ordNum, ifId, trxId.toPlainString(), segmentId.toPlainString() });
            return false;
        }
        if (wmsHdr.get(NLAB091001Constant.COL_TRANSACTION_ID) == null) {
            outputErr(NLAB091001Constant.NLGM0089E, new String[] {NLAB091001Constant.COL_TRANSACTION_ID, ordNum, ifId, trxId.toPlainString(), segmentId.toPlainString() });
            return false;
        }
        if (wmsHdr.get(NLAB091001Constant.COL_UNIT_ID) == null) {
            outputErr(NLAB091001Constant.NLGM0089E, new String[] {NLAB091001Constant.COL_UNIT_ID, ordNum, ifId, trxId.toPlainString(), segmentId.toPlainString() });
            return false;
        }
        if (wmsHdr.get(NLAB091001Constant.COL_INVTY_OWNR_CD) == null) {
            outputErr(NLAB091001Constant.NLGM0089E, new String[] {NLAB091001Constant.COL_INVTY_OWNR_CD, ordNum, ifId, trxId.toPlainString(), segmentId.toPlainString() });
            return false;
        }
        if (wmsHdr.get(NLAB091001Constant.COL_REQ_DT_TM_TS_TXT) == null) {
            outputErr(NLAB091001Constant.NLGM0089E, new String[] {NLAB091001Constant.COL_REQ_DT_TM_TS_TXT, ordNum, ifId, trxId.toPlainString(), segmentId.toPlainString() });
            return false;
        }
        if (wmsHdr.get(NLAB091001Constant.COL_INBD_ORD_NUM) == null) {
            outputErr(NLAB091001Constant.NLGM0089E, new String[] {NLAB091001Constant.COL_INBD_ORD_NUM, ordNum, ifId, trxId.toPlainString(), segmentId.toPlainString() });
            return false;
        }
        if (wmsHdr.get(NLAB091001Constant.COL_S80_ORD_TP_CD) == null) {
            outputErr(NLAB091001Constant.NLGM0089E, new String[] {NLAB091001Constant.COL_S80_ORD_TP_CD, ordNum, ifId, trxId.toPlainString(), segmentId.toPlainString() });
            return false;
        }

        for (Map<String, Object> wmsDtl : wmsDtlList) {
            String mdseCd = (String) wmsDtl.get(NLAB091001Constant.COL_ITEM_CD_TXT);
            String ordNumSegNumber = getConnectVal(ordNum, NLAB091001Constant.VAL_CONNECT_MDSE_CD, mdseCd);

            if (wmsDtl.get(NLAB091001Constant.COL_WMS_REC_ID) == null) {
                outputErr(NLAB091001Constant.NLGM0089E, new String[] {NLAB091001Constant.COL_WMS_REC_ID, ordNumSegNumber, ifId, trxId.toPlainString(), segmentId.toPlainString() });
                return false;
            }
            if (wmsDtl.get(NLAB091001Constant.COL_ITEM_CD_TXT) == null) {
                outputErr(NLAB091001Constant.NLGM0089E, new String[] {NLAB091001Constant.COL_ITEM_CD_TXT, ordNumSegNumber, ifId, trxId.toPlainString(), segmentId.toPlainString() });
                return false;
            }
            if (wmsDtl.get(NLAB091001Constant.COL_RTL_SWH_CD) == null) {
                outputErr(NLAB091001Constant.NLGM0089E, new String[] {NLAB091001Constant.COL_RTL_SWH_CD, ordNumSegNumber, ifId, trxId.toPlainString(), segmentId.toPlainString() });
                return false;
            }
            if (wmsDtl.get(NLAB091001Constant.COL_INVTY_STK_STS_CD) == null) {
                outputErr(NLAB091001Constant.NLGM0089E, new String[] {NLAB091001Constant.COL_INVTY_STK_STS_CD, ordNumSegNumber, ifId, trxId.toPlainString(), segmentId.toPlainString() });
                return false;
            }
            if (wmsDtl.get(NLAB091001Constant.COL_QTY_ORD_TXT) == null) {
                outputErr(NLAB091001Constant.NLGM0089E, new String[] {NLAB091001Constant.COL_QTY_ORD_TXT, ordNumSegNumber, ifId, trxId.toPlainString(), segmentId.toPlainString() });
                return false;
            }
            if (wmsDtl.get(NLAB091001Constant.COL_RWS_DTL_LINE_NUM) == null) {
                outputErr(NLAB091001Constant.NLGM0089E, new String[] {NLAB091001Constant.COL_RWS_DTL_LINE_NUM, ordNumSegNumber, ifId, trxId.toPlainString(), segmentId.toPlainString() });
                return false;
            }
        }

        // No error. Set status to success.
        setIntfcProcStsAsSuccess(wmsHdr);

        return true;
    }

    private String getConnectVal(String origVal, String connectVal, String addVal) {

        StringBuilder sb = new StringBuilder();
        sb.append(origVal);
        sb.append(connectVal);
        sb.append(addVal);

        return sb.toString();
    }

    private void setIntfcProcStsAsSuccess(Map<String, Object> wmsSoHdr) {
        wmsSoHdr.put(NLAB091001Constant.COL_INTFC_PROC_STS_CD, NLAB091001Constant.VAL_INTFC_PROC_STS_CD_SUCCESS);
        wmsSoHdr.put(NLAB091001Constant.COL_INTFC_ERR_MSG_CD, null);
    }

    private void setIntfcProcStsAsError(Map<String, Object> wmsSoHdr, String msgId) {
        wmsSoHdr.put(NLAB091001Constant.COL_INTFC_PROC_STS_CD, NLAB091001Constant.VAL_INTFC_PROC_STS_CD_ERROR);
        wmsSoHdr.put(NLAB091001Constant.COL_INTFC_ERR_MSG_CD, msgId);
    }

    private List<WMS_INBD_TRXTMsg> createWmsInbdTrx(Map<String, Object> wmsHdr, List<Map<String, Object>> wmsDtlList) {

        ArrayList<WMS_INBD_TRXTMsg> tMsgList = new ArrayList<WMS_INBD_TRXTMsg>();

        // GLBL_CMPY_CD
        String glblCmpyCdVal = this.glblCmpyCd;

        // TRANSACTION_ID
        String trxId = objNullToString(wmsHdr.get(NLAB091001Constant.COL_TRANSACTION_ID));
        trxId = ZYPCommonFunc.leftPad(trxId, 30, NLAB091001Constant.VAL_ZERO);

        // UNIT_ID
        String unitId = objNullToString(wmsHdr.get(NLAB091001Constant.COL_UNIT_ID));
        unitId = ZYPCommonFunc.leftPad(unitId, 30, NLAB091001Constant.VAL_ZERO);

        // INTFC_PROC_STS_CD
        String intfcProcStsCd = (String) wmsHdr.get(NLAB091001Constant.COL_INTFC_PROC_STS_CD);

        // INTFC_ERR_MSG_CD
        String intfcErrMsgCd = (String) wmsHdr.get(NLAB091001Constant.COL_INTFC_ERR_MSG_CD);

        // WMS_PKG_CD = INVTY_OWNR_CD+RTL_SWH_CD;
        String invtyOwnrCd = objNullToString(wmsHdr.get(NLAB091001Constant.COL_INVTY_OWNR_CD));

        // REQ_DT_TM_TS
        String reqDtTmTs = (String) wmsHdr.get(NLAB091001Constant.COL_REQ_DT_TM_TS_TXT);
        reqDtTmTs = adjustString(reqDtTmTs, 14);

        // INTFC_TP_ID
        String intfcTpIdVal = this.intfcTpId;

        for (Map<String, Object> wmsDtl : wmsDtlList) {
            WMS_INBD_TRXTMsg tMsg = new WMS_INBD_TRXTMsg();

            // WMS_INBD_TRX_PK
            BigDecimal wmsInbdTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ);

            // WMS_PKG_CD = INVTY_LOC_CD or INVTY_OWNR_CD+RTL_SWH_CD;
            String wmsPkgCd = (String) wmsDtl.get(NLAB091001Constant.COL_INVTY_LOC_CD);
            if (ZYPConstant.FLG_ON_Y.equals(this.wmsPackCdMode)) {
                String rtlSwhCd = objNullToString(wmsDtl.get(NLAB091001Constant.COL_RTL_SWH_CD));
                wmsPkgCd = invtyOwnrCd + rtlSwhCd;
            }

            // WMS_ORD_QTY
            BigDecimal wmsOrdQty = null;
            String qtyOrdTxt = (String) wmsDtl.get(NLAB091001Constant.COL_QTY_ORD_TXT);
            if (ZYPCommonFunc.hasValue(qtyOrdTxt)) {
                wmsOrdQty = new BigDecimal(qtyOrdTxt);
            }

            // INBD_ORD_LINE_NUM
            BigDecimal inbdOrdLineNum = null;
            String rwsDtlLineNum = (String) wmsDtl.get(NLAB091001Constant.COL_RWS_DTL_LINE_NUM);
            if (ZYPCommonFunc.hasValue(rwsDtlLineNum)) {
                inbdOrdLineNum = new BigDecimal(rwsDtlLineNum);
            }

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCdVal);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsInbdTrxPk, wmsInbdTrxPk);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsRecId, (String) wmsDtl.get(NLAB091001Constant.COL_WMS_REC_ID));
            ZYPEZDItemValueSetter.setValue(tMsg.whCd, (String) wmsHdr.get(NLAB091001Constant.COL_WH_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsTaskCd, WMS_TASK.RCVD);
            ZYPEZDItemValueSetter.setValue(tMsg.intfcId, (String) wmsHdr.get(NLAB091001Constant.COL_INTERFACE_ID));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcRcvTs, (String) wmsHdr.get(NLAB091001Constant.COL_EZINTIME));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcTrxId, trxId);
            ZYPEZDItemValueSetter.setValue(tMsg.intfcTrxSqNum, unitId);
            ZYPEZDItemValueSetter.setValue(tMsg.wrkTrxId, NLAB091001Constant.VAL_WRK_TRX_ID);
            ZYPEZDItemValueSetter.setValue(tMsg.intfcProcStsCd, intfcProcStsCd);
            ZYPEZDItemValueSetter.setValue(tMsg.intfcErrMsgCd, intfcErrMsgCd);
            ZYPEZDItemValueSetter.setValue(tMsg.procStsCd, NLAB091001Constant.VAL_PROC_STS_CD);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsMdseCd, (String) wmsDtl.get(NLAB091001Constant.COL_ITEM_CD_TXT));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsPkgCd, wmsPkgCd);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsStkStsCd, (String) wmsDtl.get(NLAB091001Constant.COL_INVTY_STK_STS_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsDmgFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOldDmgFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOrdQty, wmsOrdQty);
            ZYPEZDItemValueSetter.setValue(tMsg.usrId_03, PKG_UOM.EACH);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsRcptDtTmTs, reqDtTmTs);
            ZYPEZDItemValueSetter.setValue(tMsg.inbdOrdNum, (String) wmsHdr.get(NLAB091001Constant.COL_INBD_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.inbdOrdTpCd, (String) wmsHdr.get(NLAB091001Constant.COL_S80_ORD_TP_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.inbdOrdLineNum, inbdOrdLineNum);
            ZYPEZDItemValueSetter.setValue(tMsg.ordLineCpltFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.packMarkSeptFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsBatId, ZYPDateUtil.getCurrentSystemTime(NLAB091001Constant.FMT_YYYYMMDDHHMMSS));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, intfcTpIdVal);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOrgHostId, (String) wmsHdr.get(NLAB091001Constant.COL_WMS_ORG_HOST_ID));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsUpdHistNum, BigDecimal.ZERO);

            tMsgList.add(tMsg);
        }

        return tMsgList;
    }

    /**
     * objNullToString
     * @param obj
     * @return
     */
    private String objNullToString(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return obj.toString();
        }
    }

    private String adjustString(String val, int len) {
        if (val == null) {
            return null;
        }

        if (val.length() > len) {
            val = val.substring(0, len);
        }

        return val;
    }

}
