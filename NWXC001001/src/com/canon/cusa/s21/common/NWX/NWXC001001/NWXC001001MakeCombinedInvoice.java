/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.common.NWX.NWXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;

import business.db.DS_CMBN_INV_SUB_TOTTMsg;
import business.db.DS_CMBN_INV_SUB_TOTTMsgArray;
import business.db.DS_CMBN_INV_SUB_TOT_DTLTMsg;
import business.db.DS_CMBN_INV_SUB_TOT_DTLTMsgArray;
import business.db.ESPAC_CONTR_WRKTMsg;
import business.db.ESPAC_INV_LINE_WRKTMsg;
import business.db.ESPAC_INV_LINE_WRKTMsgArray;
import business.db.ESPAC_INV_SUB_DTL_WRKTMsg;
import business.db.ESPAC_INV_SUB_DTL_WRKTMsgArray;
import business.db.ESPAC_INV_SUB_WRKTMsg;
import business.db.ESPAC_INV_SUB_WRKTMsgArray;
import business.db.INVTMsg;
import business.db.INV_BOLTMsg;
import business.db.INV_BOLTMsgArray;
import business.db.INV_LINETMsg;
import business.db.INV_LINETMsgArray;
import business.db.INV_WRKTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Make a work table to make Combined Invoice book vote.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/08/16   Fujitsu         S.Tsunaki       Create          N/A
 *</pre>
 */
public class NWXC001001MakeCombinedInvoice {

    /** Result Message */
    private String message = "";

    /** Invoice Template Code */
    private String resultInvTmplCd = "";
    // [S21DS#134] 20121010 add start
    /** mdseNm is 'Freight */
    String MDSE_NM_FREIGHT = "Freight";

    // [S21DS#134] 20121010 add end
   
    // Message ID
    /** [@] is not found. */
    private static final String ZZZM9006E = "ZZZM9006E";

    /** [@] is invalid value */
    private static final String ZZZM9026E = "ZZZM9026E";

    /** Class */
    public static final Class<NWXC001001MakeCombinedInvoice> CLAZZ = NWXC001001MakeCombinedInvoice.class;

    /** Batch */
    private static final String C_BATCH = "Batch";

    /** Class Name */
    public static final String CLAZZ_NM = CLAZZ.getSimpleName();

    /** SSM Batch Client */
    public static final S21SsmBatchClient SSM_BATCH_CLIENT = S21SsmBatchClient.getClient(CLAZZ);

    /** Number Format */
    private static final String NUM_FORMAT = "%1$03d";

    /** Time Stamp */
    private String timeStamp;

    /** Operator Code */
    private String opCd;
    
    /** Global Company Code */
    private String glblCmpyCd;

    /** Business Application ID */
    private String bizAppId;

    /**
     * <pre>
     * Repay message.
     * </pre>
     * @return Message
     */
    public String getMessage() {
        return message;
    }

    /**
     * <pre>
     * Repay Invoice Template Code.
     * </pre>
     * @return Invoice Template Code.
     */
    public String getInvTmplCd() {
        return resultInvTmplCd;
    }

    /**
     * <pre>
     * Make a work table to make Combined Invoice pdf files.
     * </pre>
     * @param glblCmpyCd String
     * @param bizAppId String
     * @param userId String
     * @param invTs String
     * @param invNum String
     * @param rerun String
     * @return true/normal. false/abnormality.
     */
    public boolean createInvoicePrintWork(String glblCmpyCd,
                                            String bizAppId,
                                            String userId,
                                            String invTs,
                                            String invNum,
                                            boolean rerun) {

        this.glblCmpyCd = glblCmpyCd;
        this.bizAppId = bizAppId;
        this.opCd = userId;
        this.timeStamp = invTs;

        if (!checkInputParameter(glblCmpyCd, bizAppId, userId, invTs, invNum)) {
            return false;
        }
        this.message = null;
        this.resultInvTmplCd = null;

        timeStamp = invTs;

        if (!mainProcess(glblCmpyCd, bizAppId, opCd, invNum, rerun)) {
            return false;
        }

        return true;
 
    }

    /**
     * <pre>
     * Make a work table to make Combined Invoice pdf files.
     * </pre>
     * @param glblCmpyCd String
     * @param bizAppId String
     * @param userId String
     * @param invTs String
     * @param invNumList String List
     * @param rerun String
     * @return true/normal. false/abnormality.
     */
    public boolean createInvoicePrintWork(String glblCmpyCd,
                                            String bizAppId,
                                            String userId,
                                            String invTs,
                                            List<String> invNumList,
                                            boolean rerun) {

        this.glblCmpyCd = glblCmpyCd;
        this.bizAppId = bizAppId;
        this.opCd = userId;
        this.timeStamp = invTs;

        if (!checkInputParameter(glblCmpyCd, bizAppId, userId, invTs, invNumList)) {
            return false;
        }
        
        this.message = null;
        this.resultInvTmplCd = null;
        for (String invNum : invNumList) {

            if (!mainProcess(glblCmpyCd, bizAppId, opCd, invNum, rerun)) {
                return false;
            }

        }

        return true;
    }


    /**
     * <pre>
     * Delete a work table to make combined Invoice pdf files.
     * </pre>
     * @param glblCmpyCd String
     * @param bizAppId String
     * @param userId String
     * @param invTs String
     * @param invNum String
     * @return true / false
     */
    public boolean removeWork(String glblCmpyCd,
                                String bizAppId,
                                String userId,
                                String invTs,
                                String invNum) {

        this.glblCmpyCd = glblCmpyCd;
        this.bizAppId = bizAppId;
        this.opCd = userId;
        this.timeStamp = invTs;

        if (!checkInputParameter(glblCmpyCd, bizAppId, userId, invTs, invNum)) {
            return false;
        }

        removeEzpContrWrk(glblCmpyCd, bizAppId, userId, invTs, invNum);
        removeEzpInvSubTotWrk(glblCmpyCd, bizAppId, userId, invTs, invNum);
        removeEzpInvSubTotDtlWrk(glblCmpyCd, bizAppId, userId, invTs, invNum);
        removeEzpInvLineWrk(glblCmpyCd, bizAppId, userId, invTs, invNum);

        return true;
    }

    /**
     * <pre>
     * Delete a work table to make combined Invoice pdf files.
     * </pre>
     * @param glblCmpyCd String
     * @param bizAppId String
     * @param userId String
     * @return true / false
     */
    public boolean removeWork(String glblCmpyCd,
                                String bizAppId,
                                String userId) {

        this.glblCmpyCd = glblCmpyCd;
        this.bizAppId = bizAppId;
        this.opCd = userId;

        if (!checkInputParameter(glblCmpyCd, bizAppId, userId)) {
            return false;
        }

        removeEzpContrWrk(glblCmpyCd, bizAppId, userId, null, null);
        removeEzpInvSubTotWrk(glblCmpyCd, bizAppId, userId, null, null);
        removeEzpInvSubTotDtlWrk(glblCmpyCd, bizAppId, userId, null, null);
        removeEzpInvLineWrk(glblCmpyCd, bizAppId, userId, null, null);

        return true;
    }

    /**
     * <pre>
     * Delete a work table to make OCS Invoice pdf files.
     * </pre>
     * @param glblCmpyCd String
     * @param bizAppId String
     * @param userId String
     * @param invTs String
     * @param invNumList List<String>
     */
    public void removeWork(String glblCmpyCd,
                            String bizAppId,
                            String userId,
                            String invTs,
                            List<String> invNumList) {

        this.glblCmpyCd = glblCmpyCd;
        this.bizAppId = bizAppId;
        this.opCd = userId;
        this.timeStamp = invTs;

        for (String invNum : invNumList) {
            removeWork(glblCmpyCd, bizAppId, userId, invTs, invNum);
        }
    }

    private boolean checkInputParameter(String glblCmpyCd,
                                            String bizAppId,
                                            String userId,
                                            String invTs,
                                            String invNum) {

        if (!checkInputParameter(glblCmpyCd, bizAppId, userId, invTs)) {
            return false;
        }

        return chechInvNumParameter(invNum);
    }

    private boolean chechInvNumParameter(String invNum) {

        if (!hasValue(invNum)) {
            if (isBatch()) {
                S21InfoLogOutput.println(ZZZM9026E, new String[] {"invNum:"  + invNum });
                message = S21MessageFunc.clspGetMessage(ZZZM9026E, new String[] {"invNum:"  + invNum });
                return false;
            } else {
                throw new S21AbendException("no value is set for invNum.");
            }
        }

        return true;
    }

    private boolean checkInputParameter(String glblCmpyCd,
                                            String bizAppId,
                                            String userId,
                                            String invTs,
                                            List<String> invNumList) {

        if (!checkInputParameter(glblCmpyCd, bizAppId, userId, invTs)) {
            return false;
        }

        for (String invNum : invNumList) {
            if (!chechInvNumParameter(invNum)) {
                return false;
            }
        }

        return true;
    }

    private boolean checkInputParameter(String glblCmpyCd,
                                            String bizAppId,
                                            String userId,
                                            String invTs) {

        if (!checkInputParameter(glblCmpyCd, bizAppId, userId)) {
            return false;
        }

        if (!hasValue(invTs)) {
            if (isBatch()) {
                S21InfoLogOutput.println(ZZZM9026E, new String[] {"invTs:"  + invTs });
                message = S21MessageFunc.clspGetMessage(ZZZM9026E, new String[] {"invTs:"  + invTs });
                return false;
            } else {
                throw new S21AbendException("no value is set for invTs.");
            }
        }

        return true;
    }

    private boolean checkInputParameter(String glblCmpyCd, String bizAppId, String userId) {

        if (!hasValue(glblCmpyCd)) {
            if (isBatch()) {
                S21InfoLogOutput.println(ZZZM9026E, new String[] {"glblCmpyCd:"  + glblCmpyCd });
                message = S21MessageFunc.clspGetMessage(ZZZM9026E, new String[] {"glblCmpyCd:"  + glblCmpyCd });
                return false;
            } else {
                throw new S21AbendException("no value is set for glblCmpyCd.");
            }
        }

        if (!hasValue(bizAppId)) {
            if (isBatch()) {
                S21InfoLogOutput.println(ZZZM9026E, new String[] {"bizAppId:"  + bizAppId });
                message = S21MessageFunc.clspGetMessage(ZZZM9026E, new String[] {"bizAppId:"  + bizAppId });
                return false;
            } else {
                throw new S21AbendException("no value is set for bizAppId.");
            }
        }

        if (!hasValue(userId)) {
            if (isBatch()) {
                S21InfoLogOutput.println(ZZZM9026E, new String[] {"userId:"  + userId });
                message = S21MessageFunc.clspGetMessage(ZZZM9026E, new String[] {"userId:"  + userId });
                return false;
            } else {
                throw new S21AbendException("no value is set for userId.");
            }
        }

        return true;
    }

    private boolean isBatch() {
        return C_BATCH.equals(this.opCd);
    }

    private void removeEzpContrWrk(String glblCmpyCd,
                                        String bizAppId,
                                        String userId,
                                        String invTs,
                                        String invNum) {

        ESPAC_CONTR_WRKTMsg inMsg = new ESPAC_CONTR_WRKTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.bizAppId.setValue(bizAppId);
        inMsg.invOtptOpCd.setValue(userId);

        if (ZYPCommonFunc.hasValue(invTs)) {
            inMsg.invTs.setValue(invTs);
        }
        if (ZYPCommonFunc.hasValue(invNum)) {
            inMsg.invNum.setValue(invNum);
        }

        S21ApiTBLAccessor.logicalRemoveByPartialKey(inMsg);

    }

    private void removeEzpInvSubTotWrk(String glblCmpyCd,
                                            String bizAppId,
                                            String userId,
                                            String invTs,
                                            String invNum) {

        ESPAC_INV_SUB_WRKTMsg inMsg = new ESPAC_INV_SUB_WRKTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.bizAppId.setValue(bizAppId);
        inMsg.invOtptOpCd.setValue(userId);

        if (ZYPCommonFunc.hasValue(invTs)) {
            inMsg.invTs.setValue(invTs);
        }
        if (ZYPCommonFunc.hasValue(invNum)) {
            inMsg.invNum.setValue(invNum);
        }

        S21ApiTBLAccessor.logicalRemoveByPartialKey(inMsg);

    }

    private void removeEzpInvSubTotDtlWrk(String glblCmpyCd,
                                            String bizAppId,
                                            String userId,
                                            String invTs,
                                            String invNum) {

        ESPAC_INV_SUB_DTL_WRKTMsg inMsg = new ESPAC_INV_SUB_DTL_WRKTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.bizAppId.setValue(bizAppId);
        inMsg.invOtptOpCd.setValue(userId);

        if (ZYPCommonFunc.hasValue(invTs)) {
            inMsg.invTs.setValue(invTs);
        }
        if (ZYPCommonFunc.hasValue(invNum)) {
            inMsg.invNum.setValue(invNum);
        }

        S21ApiTBLAccessor.logicalRemoveByPartialKey(inMsg);

    }

    private void removeEzpInvLineWrk(String glblCmpyCd,
                                        String bizAppId,
                                        String userId,
                                        String invTs,
                                        String invNum) {

        ESPAC_INV_LINE_WRKTMsg inMsg = new ESPAC_INV_LINE_WRKTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.bizAppId.setValue(bizAppId);
        inMsg.invOtptOpCd.setValue(userId);

        if (ZYPCommonFunc.hasValue(invTs)) {
            inMsg.invTs.setValue(invTs);
        }
        if (ZYPCommonFunc.hasValue(invNum)) {
            inMsg.invNum.setValue(invNum);
        }

        S21ApiTBLAccessor.logicalRemoveByPartialKey(inMsg);

    }





    private boolean mainProcess(String glblCmpyCd,
                                        String bizAppId,
                                        String opCd,
                                        String invNum,
                                        boolean rerun) {
        //*************************
        // Data Read
        //*************************
        
        ///////////////////////
        // Read Header Data
        ///////////////////////
        INVTMsg invMsg = findInv(invNum);
        if (invMsg == null) {
            if (isBatch()) {
                S21InfoLogOutput.println(ZZZM9006E, new String[] {"INV table:" + invNum });
                return false;
            } else {
                throw new S21AbendException("INV table is not found : INV_NUM [" + invNum + "]");
            }
        }

        ///////////////////////
        // Read BOL Data
        ///////////////////////
        DS_CMBN_INV_SUB_TOTTMsgArray dsCmbnSubTotArray = findDsCmbnSubTot(invNum);
        HashMap<BigDecimal, DS_CMBN_INV_SUB_TOT_DTLTMsgArray> mapSubTotDtl = new HashMap<BigDecimal, DS_CMBN_INV_SUB_TOT_DTLTMsgArray>();
        if (!makeCmbnSubTotMap(invNum, dsCmbnSubTotArray, mapSubTotDtl)) {
            return false;
        }
        if (dsCmbnSubTotArray.length() > 0 && mapSubTotDtl.isEmpty()) {
            if (isBatch()) {
                S21InfoLogOutput.println(ZZZM9006E, new String[] {"DS_CMBN_INV_SUB_TOT_DTL table:" + invNum });
                return false;
            } else {
                throw new S21AbendException("DS_CMBN_INV_SUB_TOT_DTL table is not found : INV_NUM [" + invNum + "]");
            }
        }
        ///////////////////////
        // Read Line Data
        ///////////////////////
        List<Map<String, Object>> listInvFreight;

        listInvFreight = findInvFreight(invNum);

        if (listInvFreight == null || listInvFreight.isEmpty()) {
            if (isBatch()) {
                S21InfoLogOutput.println(ZZZM9006E, new String[] {"INV_BOL, INV_LINE table:" + invNum });
                return false;
            } else {
                throw new S21AbendException("INV_BOL, INV_LINE table is not found : INV_NUM [" + invNum + "]");
            }
        }        
        ///////////////////////
        // Read Line Data
        ///////////////////////
        List<Map<String, Object>> listInvLine;

        listInvLine = findInvLine(invNum);

        if (listInvLine == null || listInvLine.isEmpty()) {
            if (isBatch()) {
                S21InfoLogOutput.println(ZZZM9006E, new String[] {"INV_BOL, INV_LINE table:" + invNum });
                return false;
            } else {
                throw new S21AbendException("INV_BOL, INV_LINE table is not found : INV_NUM [" + invNum + "]");
            }
        }
        //*************************
        // Create Work Data
        //*************************
        
        ///////////////////////
        // Create Header Wrk
        ///////////////////////
        ESPAC_CONTR_WRKTMsg ezpContrWrk = setEzpContrWrk(invMsg, opCd);

        ///////////////////////
        // Create Sub Total Wrk
        ///////////////////////
        List<ESPAC_INV_SUB_WRKTMsg> ezpInbSubTotWrkList = new ArrayList<ESPAC_INV_SUB_WRKTMsg>(dsCmbnSubTotArray.length());
        List<ESPAC_INV_SUB_DTL_WRKTMsg> ezpInvSubTotDtlWrkList = new ArrayList<ESPAC_INV_SUB_DTL_WRKTMsg>();

        for (int i = 0; i < dsCmbnSubTotArray.length(); i++) {

            // idx is Max 3
            String idx = "" + i;
            // Sub total
            DS_CMBN_INV_SUB_TOTTMsg dsCmbnSubTot = (DS_CMBN_INV_SUB_TOTTMsg) dsCmbnSubTotArray.get(i);
            ESPAC_INV_SUB_WRKTMsg ezpInvSubTotWrk = setEzpInvSubTotWrk(dsCmbnSubTot, idx, opCd);
            ezpInbSubTotWrkList.add(ezpInvSubTotWrk);

            // Sub total Detail
            DS_CMBN_INV_SUB_TOT_DTLTMsgArray dsCmbSubTotDtlArray = mapSubTotDtl.get(dsCmbnSubTot.dsCmbnInvSubTotPk.getValue());
            if (dsCmbSubTotDtlArray == null
                    || dsCmbSubTotDtlArray.length() == 0) {
                continue;
            }
            for (int j = 0; j < dsCmbSubTotDtlArray.length(); j++) {
                DS_CMBN_INV_SUB_TOT_DTLTMsg dsCmbSubTotDtl = (DS_CMBN_INV_SUB_TOT_DTLTMsg) dsCmbSubTotDtlArray.get(j);
                String dtlIdx = String.format(NUM_FORMAT, j);
                ESPAC_INV_SUB_DTL_WRKTMsg ezpInvSubTotDtlWrk = setEzpInvSubTotDtlWrk(dsCmbSubTotDtl, ezpInvSubTotWrk.espacInvTpCd.getValue(), dtlIdx, opCd);
                ezpInvSubTotDtlWrkList.add(ezpInvSubTotDtlWrk);
            }
        }
        ///////////////////////
        // Create Line Detail Wrk
        ///////////////////////
        int lineCounter = 0;
        List<ESPAC_INV_LINE_WRKTMsg> ezpInvLineWrkList = new ArrayList<ESPAC_INV_LINE_WRKTMsg>(listInvLine.size());

        for (Map<String, Object> mapLine : listInvLine) {
            String lineNum = String.format(NUM_FORMAT, lineCounter);
            ESPAC_INV_LINE_WRKTMsg ezpInvLineWrk = setEzpInvLineWrk(mapLine, glblCmpyCd, invNum, lineNum, opCd);
            ezpInvLineWrkList.add(ezpInvLineWrk);
            lineCounter++;
        }
        for (Map<String, Object> mapLine : listInvFreight) {
            String lineNum = String.format(NUM_FORMAT, lineCounter);
            ESPAC_INV_LINE_WRKTMsg ezpInvLineWrk = setEzpInvLineWrkFreight(mapLine, glblCmpyCd, invNum, lineNum, opCd);
            if (ezpInvLineWrk.invLineTotAmt.getValue() != null && BigDecimal.ZERO.compareTo(ezpInvLineWrk.invLineTotAmt.getValue()) != 0) {
                ezpInvLineWrkList.add(ezpInvLineWrk);
                lineCounter++;
                
            }
        }

        // toArray
        ESPAC_INV_SUB_WRKTMsgArray ezpInvSubTotWrkArray = new ESPAC_INV_SUB_WRKTMsgArray();
        ezpInvSubTotWrkArray.setMsgList(
                    (ESPAC_INV_SUB_WRKTMsg[]) ezpInbSubTotWrkList.toArray(
                        new ESPAC_INV_SUB_WRKTMsg[ezpInbSubTotWrkList.size()]));

        ESPAC_INV_SUB_DTL_WRKTMsgArray ezpInvSubTotDtlWrkArray = new ESPAC_INV_SUB_DTL_WRKTMsgArray();
        ezpInvSubTotDtlWrkArray.setMsgList(
                    (ESPAC_INV_SUB_DTL_WRKTMsg[]) ezpInvSubTotDtlWrkList.toArray(
                        new ESPAC_INV_SUB_DTL_WRKTMsg[ezpInbSubTotWrkList.size()]));

        ESPAC_INV_LINE_WRKTMsgArray ezpInvLinwWrkArray = new ESPAC_INV_LINE_WRKTMsgArray();
        ezpInvLinwWrkArray.setMsgList(
                    (ESPAC_INV_LINE_WRKTMsg[]) ezpInvLineWrkList.toArray(
                        new ESPAC_INV_LINE_WRKTMsg[ezpInvLineWrkList.size()]));

        //*************************
        // Data Write to DB
        //*************************
        writeMsg(ezpContrWrk, bizAppId);
        writeMsgArray(ezpInvSubTotWrkArray, bizAppId);
        writeMsgArray(ezpInvSubTotDtlWrkArray, bizAppId);
        writeMsgArray(ezpInvLinwWrkArray, bizAppId);

        return true;
    }

    private boolean makeCmbnSubTotMap(String invNum,
                                            DS_CMBN_INV_SUB_TOTTMsgArray dsCmbnSubTotArray,
                                            HashMap<BigDecimal, DS_CMBN_INV_SUB_TOT_DTLTMsgArray> mapSubTotDtl) {

        if (dsCmbnSubTotArray != null) {

            for (int i = 0; i < dsCmbnSubTotArray.length(); i++) {

                DS_CMBN_INV_SUB_TOTTMsg dsCmbnSubTot = (DS_CMBN_INV_SUB_TOTTMsg) dsCmbnSubTotArray.get(i);

                BigDecimal subTotNum = dsCmbnSubTot.dsCmbnInvSubTotPk.getValue();

                DS_CMBN_INV_SUB_TOT_DTLTMsgArray ezpInvSubTotDtlWrkArray = findDsCmbnSubTotDtl(invNum, subTotNum);

                if (ezpInvSubTotDtlWrkArray != null
                        && ezpInvSubTotDtlWrkArray.length() > 0) {
                    mapSubTotDtl.put(subTotNum, ezpInvSubTotDtlWrkArray);
                } else {
                    if (isBatch()) {
                        S21InfoLogOutput.println(ZZZM9006E, new String[] {"DS_CMBN_INV_SUB_TOT_DTL table:" + invNum });
                        return false;
                    } else {
                        throw new S21AbendException("DS_CMBN_INV_SUB_TOT_DTL table is not found : INV_NUM [" + invNum + "]");
                    }
                }
            }
        }

        return true;
    }

    private INVTMsg findInv(String invNum) {

        INVTMsg inMsg = new INVTMsg();

        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.invNum.setValue(invNum);

        return (INVTMsg) S21FastTBLAccessor.findByKey(inMsg);
    }

    private INV_BOLTMsgArray findInvBol(String invNum) {

        INV_BOLTMsg inMsg = new INV_BOLTMsg();

        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("invNum01", invNum);

        return (INV_BOLTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    private INV_LINETMsgArray findInvLine(String invNum, String invBolLineNum) {

        INV_LINETMsg inMsg = new INV_LINETMsg();

        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("invNum01", invNum);
        inMsg.setConditionValue("invBolLineNum01", invBolLineNum);

        return (INV_LINETMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    private DS_CMBN_INV_SUB_TOTTMsgArray findDsCmbnSubTot(String invNum) {

        DS_CMBN_INV_SUB_TOTTMsgArray array = new DS_CMBN_INV_SUB_TOTTMsgArray();

        Map<String, String> param = new HashMap<String, String>();

        param.put("glblCmpyCd", glblCmpyCd);
        param.put("invNum", invNum);

        List<DS_CMBN_INV_SUB_TOTTMsg> ssmRes = (List<DS_CMBN_INV_SUB_TOTTMsg>) SSM_BATCH_CLIENT.queryObjectList("queryCmbInvSubTot", param);

        array.setMsgList((DS_CMBN_INV_SUB_TOTTMsg[]) ssmRes.toArray(new DS_CMBN_INV_SUB_TOTTMsg[ssmRes.size()]));

        return array;

//        DS_CMBN_INV_SUB_TOTTMsg inMsg = new DS_CMBN_INV_SUB_TOTTMsg();
//
//        inMsg.setSQLID("001");
//        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inMsg.setConditionValue("invNum01", invNum);
//
//        return (DS_CMBN_INV_SUB_TOTTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    private DS_CMBN_INV_SUB_TOT_DTLTMsgArray findDsCmbnSubTotDtl(String invNum, BigDecimal subTotNum) {


        DS_CMBN_INV_SUB_TOT_DTLTMsgArray array = new DS_CMBN_INV_SUB_TOT_DTLTMsgArray();

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", glblCmpyCd);
        param.put("invNum", invNum);
        param.put("dsCmbnInvSubTotPk", subTotNum);

        List<DS_CMBN_INV_SUB_TOT_DTLTMsg> ssmRes = (List<DS_CMBN_INV_SUB_TOT_DTLTMsg>) SSM_BATCH_CLIENT.queryObjectList("queryCmbInvSubTotDtl", param);

        array.setMsgList((DS_CMBN_INV_SUB_TOT_DTLTMsg[]) ssmRes.toArray(new DS_CMBN_INV_SUB_TOT_DTLTMsg[ssmRes.size()]));

        return array;


//        DS_CMBN_INV_SUB_TOT_DTLTMsg inMsg = new DS_CMBN_INV_SUB_TOT_DTLTMsg();
//
//        inMsg.setSQLID("001");
//        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inMsg.setConditionValue("invNum01", invNum);
//        inMsg.setConditionValue("dsCmbnInvSubTotPk01", subTotNum);
//
//        return (DS_CMBN_INV_SUB_TOT_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    private List<Map<String, Object>> findInvLine(String invNum) {

        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("invNum", invNum);

        List<Map<String, Object>> result = SSM_BATCH_CLIENT.queryObjectList("queryInvLine", mapParam);

        if (result == null
                || result.isEmpty()) {
            return null;
        }

        return result;
    }
    private List<Map<String, Object>> findInvFreight(String invNum) {

        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("invNum", invNum);

        List<Map<String, Object>> result = SSM_BATCH_CLIENT.queryObjectList("queryInvBol", mapParam);

        if (result == null
                || result.isEmpty()) {
            return null;
        }

        return result;
    }

    private ESPAC_CONTR_WRKTMsg setEzpContrWrk(INVTMsg invMsg,
                                                    String opCd) {

        ESPAC_CONTR_WRKTMsg ezpContrWrk = new ESPAC_CONTR_WRKTMsg();

        // key
        ezpContrWrk.glblCmpyCd.setValue(glblCmpyCd);
        ezpContrWrk.bizAppId.setValue(bizAppId);
        ezpContrWrk.invNum.setValue(invMsg.invNum.getValue());
//        EZDMsg.copy(invMsg, "glblCmpyCd", ezpContrWrk, "glblCmpyCd");
//        EZDMsg.copy(invMsg, "invNum", ezpContrWrk, "invNum");
        ezpContrWrk.invTs.setValue(timeStamp);
        ezpContrWrk.invOtptOpCd.setValue(opCd);

        // values
//        EZDMsg.copy(invMsg, "sqaFeetRate", ezpContrWrk, "rate");
//        EZDMsg.copy(invMsg, "minSqaFeetRate", ezpContrWrk, "minUsg");
//        EZDMsg.copy(invMsg, "totShipSqaFeetRate", ezpContrWrk, "totShip");
//        EZDMsg.copy(invMsg, "subTotNetAmt", ezpContrWrk, "shipAmt");
//        EZDMsg.copy(invMsg, "subTotSvcAmt", ezpContrWrk, "shipTax");
//        EZDMsg.copy(invMsg, "subTotTaxAmt", ezpContrWrk, "shipTot");

        ezpContrWrk.espacContrRate.setValue(invMsg.easyPackRate.getValue());
        ezpContrWrk.espacContrMthQuotQty.setValue(invMsg.easyPackMthQuotSumQty.getValue());
        ezpContrWrk.espacContrTotShipQty.setValue(invMsg.easyPackTotShipSumQty.getValue());
        ezpContrWrk.espacContrShipAmt.setValue(invMsg.subTotSvcAmt.getValue());
        ezpContrWrk.espacContrShipTaxAmt.setValue(invMsg.subTotTaxAmt.getValue());
        ezpContrWrk.espacContrTotShipAmt.setValue(invMsg.subTotNetAmt.getValue());

        return ezpContrWrk;
    }

    private ESPAC_INV_SUB_WRKTMsg setEzpInvSubTotWrk(DS_CMBN_INV_SUB_TOTTMsg dsCmbnSubTotMsg,
                                                        String subTotNum,
                                                        String opCd) {

        ESPAC_INV_SUB_WRKTMsg ezpInvSubTotWrk = new ESPAC_INV_SUB_WRKTMsg();

        // key
        ezpInvSubTotWrk.glblCmpyCd.setValue(glblCmpyCd);
        ezpInvSubTotWrk.bizAppId.setValue(bizAppId);
        ezpInvSubTotWrk.invNum.setValue(dsCmbnSubTotMsg.invNum.getValue());
        ezpInvSubTotWrk.invLineNum.setValue(subTotNum);

//        EZDMsg.copy(dsCmbnSubTotMsg, "glblCmpyCd", ezpInvSubTotWrk, "glblCmpyCd");
//        EZDMsg.copy(dsCmbnSubTotMsg, "invNum", ezpInvSubTotWrk, "invNum");
        ezpInvSubTotWrk.invTs.setValue(timeStamp);
        ezpInvSubTotWrk.invOtptOpCd.setValue(opCd);

//        ezpInvSubTotWrk.subTotNum.setValue(subTotNum);

        // values
//        EZDMsg.copy(dsCmbnSubTotMsg, "dsCmbnSubTotTpCd", ezpInvSubTotWrk, "ezpInvTp");
//        EZDMsg.copy(dsCmbnSubTotMsg, "dsCmbnSubTotNetAmt", ezpInvSubTotWrk, "subTotAmt");
//        EZDMsg.copy(dsCmbnSubTotMsg, "dsCmbnSubTotTaxAmt", ezpInvSubTotWrk, "subTotTax");
//        EZDMsg.copy(dsCmbnSubTotMsg, "dsCmbnSubTotSlsAmt", ezpInvSubTotWrk, "subTotTotAmt");

        ezpInvSubTotWrk.espacInvTpCd.setValue(dsCmbnSubTotMsg.dsCmbnSubTotTpCd.getValue());
        ezpInvSubTotWrk.espacInvSubAmt.setValue(dsCmbnSubTotMsg.dsCmbnInvSubTotSlsAmt.getValue());
        ezpInvSubTotWrk.espacInvSubTaxAmt.setValue(dsCmbnSubTotMsg.dsCmbnInvSubTotTaxAmt.getValue());
        ezpInvSubTotWrk.espacInvSubTotAmt.setValue(dsCmbnSubTotMsg.dsCmbnInvSubTotNetAmt.getValue());

        return ezpInvSubTotWrk;
    }

    private ESPAC_INV_SUB_DTL_WRKTMsg setEzpInvSubTotDtlWrk(DS_CMBN_INV_SUB_TOT_DTLTMsg dsCmbnSubTotDtlMsg,
                                                                    String subTotNum,
                                                                    String lineNum,
                                                                    String opCd) {

        ESPAC_INV_SUB_DTL_WRKTMsg ezpInvSubTotDtlWrk = new ESPAC_INV_SUB_DTL_WRKTMsg();

        // key
        ezpInvSubTotDtlWrk.glblCmpyCd.setValue(glblCmpyCd);
        ezpInvSubTotDtlWrk.bizAppId.setValue(bizAppId);
        ezpInvSubTotDtlWrk.invNum.setValue(dsCmbnSubTotDtlMsg.invNum.getValue());
        ezpInvSubTotDtlWrk.invLineNum.setValue(lineNum);
//        EZDMsg.copy(dsCmbnSubTotDtlMsg, "glblCmpyCd", ezpInvSubTotDtlWrk, "glblCmpyCd");
//        EZDMsg.copy(dsCmbnSubTotDtlMsg, "invNum", ezpInvSubTotDtlWrk, "invNum");
        ezpInvSubTotDtlWrk.invTs.setValue(timeStamp);
        ezpInvSubTotDtlWrk.invOtptOpCd.setValue(opCd);

        ezpInvSubTotDtlWrk.espacInvSubNum.setValue(subTotNum);
//        ezpInvSubTotDtlWrk.espacInvSubNum.setValue(lineNum);

        // values
//        EZDMsg.copy(dsCmbnSubTotDtlMsg, "mdseCd", ezpInvSubTotDtlWrk, "mdseCd");
//        EZDMsg.copy(dsCmbnSubTotDtlMsg, "descTxt", ezpInvSubTotDtlWrk, "mdseNm");
//        EZDMsg.copy(dsCmbnSubTotDtlMsg, "shipQty", ezpInvSubTotDtlWrk, "qty");
//        EZDMsg.copy(dsCmbnSubTotDtlMsg, "netAmt", ezpInvSubTotDtlWrk, "amt");
//        EZDMsg.copy(dsCmbnSubTotDtlMsg, "taxAmt", ezpInvSubTotDtlWrk, "tax");
//        EZDMsg.copy(dsCmbnSubTotDtlMsg, "slsAmt", ezpInvSubTotDtlWrk, "totAmt");

        ezpInvSubTotDtlWrk.mdseCd.setValue(dsCmbnSubTotDtlMsg.mdseCd.getValue());
        ezpInvSubTotDtlWrk.mdseNm.setValue(dsCmbnSubTotDtlMsg.cmbnInvSubDtlDescTxt.getValue());
        ezpInvSubTotDtlWrk.espacInvSubDtlQty.setValue(dsCmbnSubTotDtlMsg.shipQty.getValue());
        ezpInvSubTotDtlWrk.espacInvSubDtlAmt.setValue(dsCmbnSubTotDtlMsg.slsAmt.getValue());
        ezpInvSubTotDtlWrk.espacInvSubDtlTaxAmt.setValue(dsCmbnSubTotDtlMsg.taxAmt.getValue());
        ezpInvSubTotDtlWrk.espacInvSubDtlTotAmt.setValue(dsCmbnSubTotDtlMsg.netAmt.getValue());

        return ezpInvSubTotDtlWrk;
    }

    private ESPAC_INV_LINE_WRKTMsg setEzpInvLineWrk(Map<String, Object> resultMap,
                                                        String glblCmpyCd,
                                                        String invNum,
                                                        String lineNum,
                                                        String opCd) {

        ESPAC_INV_LINE_WRKTMsg ezpInvLineWrk = new ESPAC_INV_LINE_WRKTMsg();

        // key
        ezpInvLineWrk.glblCmpyCd.setValue(glblCmpyCd);
        ezpInvLineWrk.bizAppId.setValue(bizAppId);
        ezpInvLineWrk.invNum.setValue(invNum);
        ezpInvLineWrk.invTs.setValue(timeStamp);
        ezpInvLineWrk.invOtptOpCd.setValue(opCd);

        ezpInvLineWrk.invLineNum.setValue(lineNum);

        // values
        ZYPEZDItemValueSetter.setValue(ezpInvLineWrk.cpoNum, (String) resultMap.get("CPO_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(ezpInvLineWrk.custPoNum, (String) resultMap.get("CUST_ISS_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(ezpInvLineWrk.shipToCustCd, (String) resultMap.get("SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(ezpInvLineWrk.shipToFirstLineAddr, (String) resultMap.get("SHIP_TO_FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(ezpInvLineWrk.shipToScdLineAddr, (String) resultMap.get("SHIP_TO_SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(ezpInvLineWrk.shipToCtyAddr, (String) resultMap.get("SHIP_TO_THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(ezpInvLineWrk.shipToStNm, (String) resultMap.get("SHIP_TO_CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(ezpInvLineWrk.mdseCd, (String) resultMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(ezpInvLineWrk.mdseNm, (String) resultMap.get("MDSE_NM"));
        ZYPEZDItemValueSetter.setValue(ezpInvLineWrk.invLineQty, (BigDecimal) resultMap.get("SHIP_QTY"));
        ZYPEZDItemValueSetter.setValue(ezpInvLineWrk.espacTotRate, (BigDecimal) resultMap.get("ESPAC_LINE_SHIP_QTY"));
        ZYPEZDItemValueSetter.setValue(ezpInvLineWrk.espacRate, (BigDecimal) resultMap.get("EASY_PACK_RATE"));

        BigDecimal netAmt = (BigDecimal) resultMap.get("INV_LINE_DEAL_NET_AMT");
        BigDecimal taxAmt = (BigDecimal) resultMap.get("INV_LINE_DEAL_TAX_AMT");
        BigDecimal totAmt = netAmt.add(taxAmt);

        ezpInvLineWrk.invLineAmt.setValue(netAmt);
        ezpInvLineWrk.invLineTaxAmt.setValue(taxAmt);
        ezpInvLineWrk.invLineTotAmt.setValue(totAmt);

        return ezpInvLineWrk;
    }
    private ESPAC_INV_LINE_WRKTMsg setEzpInvLineWrkFreight(Map<String, Object> resultMap,
            String glblCmpyCd,
            String invNum,
            String lineNum,
            String opCd) {

        ESPAC_INV_LINE_WRKTMsg ezpInvLineWrk = new ESPAC_INV_LINE_WRKTMsg();

        // key
        ezpInvLineWrk.glblCmpyCd.setValue(glblCmpyCd);
        ezpInvLineWrk.bizAppId.setValue(bizAppId);
        ezpInvLineWrk.invNum.setValue(invNum);
        ezpInvLineWrk.invTs.setValue(timeStamp);
        ezpInvLineWrk.invOtptOpCd.setValue(opCd);

        ezpInvLineWrk.invLineNum.setValue(lineNum);

        // values
        ZYPEZDItemValueSetter.setValue(ezpInvLineWrk.cpoNum, (String) resultMap.get("CPO_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(ezpInvLineWrk.custPoNum, (String) resultMap.get("CUST_ISS_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(ezpInvLineWrk.shipToCustCd, (String) resultMap.get("SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(ezpInvLineWrk.shipToFirstLineAddr, (String) resultMap.get("SHIP_TO_FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(ezpInvLineWrk.shipToScdLineAddr, (String) resultMap.get("SHIP_TO_SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(ezpInvLineWrk.shipToCtyAddr, (String) resultMap.get("SHIP_TO_THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(ezpInvLineWrk.shipToStNm, (String) resultMap.get("SHIP_TO_CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(ezpInvLineWrk.mdseNm, MDSE_NM_FREIGHT);
        ZYPEZDItemValueSetter.setValue(ezpInvLineWrk.invLineQty, BigDecimal.ONE);
        
        BigDecimal netAmt = (BigDecimal) resultMap.get("SHIP_DEAL_FRT_AMT");
        BigDecimal taxAmt = (BigDecimal) resultMap.get("FRT_DEAL_TAX_AMT");
        BigDecimal totAmt = netAmt.add(taxAmt);

        ezpInvLineWrk.invLineAmt.setValue(netAmt);
        ezpInvLineWrk.invLineTaxAmt.setValue(taxAmt);
        ezpInvLineWrk.invLineTotAmt.setValue(totAmt);

        return ezpInvLineWrk;
    }

    private void writeMsgArray(EZDTMsgArray inMsgArray, String bizAppId) {

        if (inMsgArray == null) {
            return;
        }

        for (int i = 0; i < inMsgArray.length(); i++) {
            writeMsg((EZDTMsg) inMsgArray.get(i), bizAppId);
        }
    }

    private void writeMsg(EZDTMsg inMsg, String bizAppId) {

        if (inMsg == null) {
            return;
        }

        if (isBatch()) {
            S21FastTBLAccessor.insert(inMsg);
        } else {
            S21ApiTBLAccessor.insert(inMsg);
        }
    }

    private BigDecimal add(BigDecimal val1, BigDecimal val2) {
        if (!hasValue(val1)) {
            val1 = ZERO;
        }
        if (!hasValue(val2)) {
            val2 = ZERO;
        }
        return val1.add(val2);

    }
}
