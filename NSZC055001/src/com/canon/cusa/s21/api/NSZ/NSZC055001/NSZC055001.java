/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC055001;

import static com.canon.cusa.s21.api.NSZ.NSZC055001.constant.NSZC0550001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_MSGTMsg;
import business.db.SVC_CONTR_BLLGTMsg;
import business.parts.NSZC055001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.common.NSX.NSXC001001.ContrTrkInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContractTracking;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CPLT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Preview Billing Action API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/17/2015   Hitachi         Y.Takeno        Create          N/A
 * 03/30/2016   Hitachi         T.Aoyagi        Update          QC#1467
 * 03/30/2016   Hitachi         T.Tomita        Update          QC#1523, 4624
 * 2018/04/23   Hitachi         K.Kojima        Update          QC#25446
 * 2018/06/29   Hitachi         K.Kitachi       Update          QC#22245
 * 2018/07/03   Hitachi         K.Kim           Update          QC#26726
 * 2018/12/20   Hitachi         K.Kitachi       Update          QC#29647
 * 2019/07/18   Hitachi         K.Kishimoto     Update          QC#51706
 * 2019/11/28   Hitachi         K.Kishimoto     Update          QC#53567
 * </pre>
 */
public class NSZC055001 extends S21ApiCommonBase {

    // add start 2016/06/01 CSA Defect#1523, 4624
    /** Online Batch Type */
    private ONBATCH_TYPE onBatTp = null;
    // add end 2016/06/01 CSA Defect#1523, 4624

    /**
     * S21SsmBatchClient.
     */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Constructor.
     */
    public NSZC055001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute.
     * @param pMsg NSZC055001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NSZC055001PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        // add start 2016/06/01 CSA Defect#1523, 4624
        this.onBatTp = onBatchType;
        // add end 2016/06/01 CSA Defect#1523, 4624
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);

        if (checkParameter(msgMap)) {
            doProcess(msgMap);
        }

        msgMap.flush();
    }

    /**
     * execute.
     * @param pMsgs NSZC055001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NSZC055001PMsg> pMsgs, final ONBATCH_TYPE onBatchType) {

        for (NSZC055001PMsg pMsg : pMsgs) {
            execute(pMsg, onBatchType);
        }
    }

    private boolean checkParameter(S21ApiMessageMap msgMap) {

        NSZC055001PMsg pMsg = (NSZC055001PMsg) msgMap.getPmsg();

        mandatoryCheck(msgMap, pMsg.glblCmpyCd, NSZM0001E);
        mandatoryCheck(msgMap, pMsg.xxProcTpCd, NSZM0003E);
        // START 2018/06/29 K.Kitachi [QC#22245, MOD]
        mandatoryCheck(msgMap, pMsg.usrId, NSZM0293E);
        // END 2018/06/29 K.Kitachi [QC#22245, MOD]

        if (hasValue(pMsg.xxProcTpCd) && (!(XX_PROC_TP_CD_APPROVE.equals(pMsg.xxProcTpCd.getValue()) || XX_PROC_TP_CD_REASSIGN.equals(pMsg.xxProcTpCd.getValue())))) {
            msgMap.addXxMsgId(NSZM0004E);
        }

        if ((!hasValue(pMsg.dsContrNum)) && (!hasValue(pMsg.dsContrPk))) {
            msgMap.addXxMsgId(NSZM0785E);
        }

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }

        return true;
    }

    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    private void doProcess(S21ApiMessageMap msgMap) {

        NSZC055001PMsg pMsg = (NSZC055001PMsg) msgMap.getPmsg();

        if (XX_PROC_TP_CD_APPROVE.equals(pMsg.xxProcTpCd.getValue())) {
            approveServiceContractBilling(msgMap);
        }

        if (XX_PROC_TP_CD_REASSIGN.equals(pMsg.xxProcTpCd.getValue())) {
            reassignServiceContractBilling(msgMap);
        }
    }

    private void approveServiceContractBilling(S21ApiMessageMap msgMap) {

        List<Map<String, Object>> serviceContractBillingList = getServiceContractBilling(msgMap);
        if (serviceContractBillingList == null || serviceContractBillingList.isEmpty()) {
            msgMap.addXxMsgId(NSZM0786E);
            return;
        }

        for (Map<String, Object> serviceContractBilling : serviceContractBillingList) {
            BigDecimal svcContrBllgPk = (BigDecimal) serviceContractBilling.get("SVC_CONTR_BLLG_PK");
            BigDecimal dsContrPk = (BigDecimal) serviceContractBilling.get("DS_CONTR_PK");
            BigDecimal dsContrDtlPk = (BigDecimal) serviceContractBilling.get("DS_CONTR_DTL_PK");
            // START 2018/04/23 K.Kojima [QC#25446,ADD]
            String dsContrCatgCd = (String) serviceContractBilling.get("DS_CONTR_CATG_CD");
            // END 2018/04/23 K.Kojima [QC#25446,ADD]

            updateSvcContrBllg(msgMap, svcContrBllgPk);

            createDsMsg(msgMap, svcContrBllgPk, dsContrPk, dsContrDtlPk);

            // START 2018/07/03 K.Kim [QC#26726,ADD]
            if (existCreditRebill(msgMap, dsContrPk)) {
                continue;
            }
            // END 2018/07/03 K.Kim [QC#26726,ADD]

            // START 2018/12/20 K.Kitachi [QC#29647, MOD]
//            // START 2018/04/23 K.Kojima [QC#25446,ADD]
//            if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
//                boolean callTrkApiFlg = false;
//                if (!existUnapprovedUsgChrgForDtl(msgMap, dsContrDtlPk)) {
//                    updateDsContrDtl(msgMap, dsContrDtlPk);
//                    callTrkApiFlg = true;
//                }
//                if (!existUnapprovedUsgChrg(msgMap, dsContrPk)) {
//                    updateDsContr(msgMap, dsContrPk);
//                    callTrkApiFlg = true;
//                }
//                if (callTrkApiFlg) {
//                    callContrTrkAPI(msgMap, dsContrPk);
//                }
//            } else {
//            // END 2018/04/23 K.Kojima [QC#25446,ADD]
//                // START 03/30/2016 T.Aoyagi [QC#1467, ADD]
//                if (existUnapprovedUsgChrg(msgMap, dsContrPk)) {
//                    continue;
//                }
//                // END 03/30/2016 T.Aoyagi [QC#1467, ADD]
//                updateDsContr(msgMap, dsContrPk);
//                // add start 2016/06/01 CSA Defect#1523, 4624
//                callContrTrkAPI(msgMap, dsContrPk);
//                // add end 2016/06/01 CSA Defect#1523, 4624
//            // START 2018/04/23 K.Kojima [QC#25446,ADD]
//            }
//            // END 2018/04/23 K.Kojima [QC#25446,ADD]
            boolean callTrkApiFlg = false;
            if (!existUnapprovedUsgChrgForDtl(msgMap, dsContrDtlPk)) {
                // START 2019/07/18 [QC#51706, MOD]
                //updateDsContrDtlUnderInfo(msgMap, dsContrDtlPk);
                updateDsContrDtlUnderInfo(msgMap, dsContrDtlPk, dsContrPk);
                // END 2019/07/18 [QC#51706, MOD]
                callTrkApiFlg = true;
            }
            if (!existUnapprovedUsgChrg(msgMap, dsContrPk)) {
                if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
                    BigDecimal aggLinePk = getBillingHoldAggLine(msgMap, dsContrPk);
                    if (hasValue(aggLinePk)) {
                        // START 2019/07/18 [QC#51706, MOD]
                        //updateDsContrDtlUnderInfo(msgMap, aggLinePk);
                        updateDsContrDtlUnderInfo(msgMap, aggLinePk, dsContrPk);
                        // END 2019/07/18 [QC#51706, MOD]
                    }
                }
                updateDsContr(msgMap, dsContrPk);
                callTrkApiFlg = true;
            }
            if (callTrkApiFlg) {
                callContrTrkAPI(msgMap, dsContrPk);
            }
            // END 2018/12/20 K.Kitachi [QC#29647, MOD]
        }
    }

    private List<Map<String, Object>> getServiceContractBilling(S21ApiMessageMap msgMap) {
        NSZC055001PMsg pMsg = (NSZC055001PMsg) msgMap.getPmsg();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrPk", pMsg.dsContrPk.getValue());
        param.put("dsContrNum", pMsg.dsContrNum.getValue());
        // START 03/30/2016 T.Aoyagi [QC#1467, ADD]
        param.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        param.put("billToCustCd", pMsg.billToCustCd.getValue());
        param.put("svcContrBllgThruDt", pMsg.svcContrBllgThruDt.getValue());
        // END 03/30/2016 T.Aoyagi [QC#1467, ADD]
        param.put("bllgCpltStsCd", BLLG_CPLT_STS.SCHEDULED);
        // START 2018/12/20 K.Kitachi [QC#29647, ADD]
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        // END 2018/12/20 K.Kitachi [QC#29647, ADD]
        // START 2019/11/28 [QC#53567, Add]
        String negFlg = FLG_ON_Y;
        if (WF_PROCESS_NM.equals(pMsg.bizId.getValue())) {
            negFlg = FLG_OFF_N;
        }
        param.put("negFlg", negFlg);
        // END   2019/11/28 [QC#53567, Add]

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> serviceContractBilling = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getServiceContractBilling", param);
        return serviceContractBilling;
    }

    private void updateSvcContrBllg(S21ApiMessageMap msgMap, BigDecimal svcContrBllgPk) {

        NSZC055001PMsg pMsg = (NSZC055001PMsg) msgMap.getPmsg();

        SVC_CONTR_BLLGTMsg inMsg = new SVC_CONTR_BLLGTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.svcContrBllgPk, svcContrBllgPk);
        inMsg = (SVC_CONTR_BLLGTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);

        setValue(inMsg.bllgApvlCpltFlg, FLG_ON_Y);
        // START 2018/06/29 K.Kitachi [QC#22245, MOD]
        setValue(inMsg.bllgApvlPsnCd, pMsg.usrId);
        // END 2018/06/29 K.Kitachi [QC#22245, MOD]
        S21ApiTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0787E);
        }
    }

    private void createDsMsg(S21ApiMessageMap msgMap, BigDecimal svcContrBllgPk, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {

        NSZC055001PMsg pMsg = (NSZC055001PMsg) msgMap.getPmsg();

        DS_MSGTMsg inMsg = new DS_MSGTMsg();

        if (hasValue(pMsg.dsMsgTxt)) {
            BigDecimal dsMsgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_MSG_SQ);

            setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(inMsg.dsMsgPk, dsMsgPk);
            setValue(inMsg.dsMsgTpCd, DS_MSG_TP.BLLG_APPROVE_MSG);
            setValue(inMsg.dsMsgTrxNum, svcContrBllgPk.toPlainString());
            setValue(inMsg.dsMsgFirstTrxSubNum, dsContrPk.toPlainString());
            setValue(inMsg.dsMsgScdTrxSubNum, dsContrDtlPk.toPlainString());
            setValue(inMsg.dsMsgTrxNm, "SVC_CONTR_BLLG_PK");
            setValue(inMsg.dsMsgFirstTrxSubNm, "DS_CONTR_PK");
            setValue(inMsg.dsMsgScdTrxSubNm, "DS_CONTR_DTL_PK");
            setValue(inMsg.dsMsgTxt, pMsg.dsMsgTxt);

            S21ApiTBLAccessor.insert(inMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM0788E);
            }
        }
    }

    // START 03/30/2016 T.Aoyagi [QC#1467, ADD]
    private boolean existUnapprovedUsgChrg(S21ApiMessageMap msgMap, BigDecimal dsContrPk) {

        List<BigDecimal> svcContrBllgPkList = getUnapprovedUsgChrg(msgMap, dsContrPk);
        if (svcContrBllgPkList.isEmpty()) {
            return false;
        }
        return true;
    }

    private List<BigDecimal> getUnapprovedUsgChrg(S21ApiMessageMap msgMap, BigDecimal dsContrPk) {
        NSZC055001PMsg pMsg = (NSZC055001PMsg) msgMap.getPmsg();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrPk", dsContrPk);
        param.put("bllgCpltStsCd", BLLG_CPLT_STS.SCHEDULED);
        // START 2018/12/20 K.Kitachi [QC#29647, ADD]
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        // END 2018/12/20 K.Kitachi [QC#29647, ADD]
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getUnapprovedUsgChrg", param);
    }

    // END 03/30/2016 T.Aoyagi [QC#1467, ADD]

    // START 2018/04/23 K.Kojima [QC#25446,ADD]
    private boolean existUnapprovedUsgChrgForDtl(S21ApiMessageMap msgMap, BigDecimal dsContrDtlPk) {

        List<BigDecimal> svcContrBllgPkList = getUnapprovedUsgChrgForDtl(msgMap, dsContrDtlPk);
        if (svcContrBllgPkList.isEmpty()) {
            return false;
        }
        return true;
    }

    private List<BigDecimal> getUnapprovedUsgChrgForDtl(S21ApiMessageMap msgMap, BigDecimal dsContrDtlPk) {
        NSZC055001PMsg pMsg = (NSZC055001PMsg) msgMap.getPmsg();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("bllgCpltStsCd", BLLG_CPLT_STS.SCHEDULED);
        // START 2018/12/20 K.Kitachi [QC#29647, ADD]
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        // END 2018/12/20 K.Kitachi [QC#29647, ADD]
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getUnapprovedUsgChrgForDtl", param);
    }

    // END 2018/04/23 K.Kojima [QC#25446,ADD]

    private void updateDsContr(S21ApiMessageMap msgMap, BigDecimal dsContrPk) {
        NSZC055001PMsg pMsg = (NSZC055001PMsg) msgMap.getPmsg();

        // START 2019/07/18 [QC#51706,ADD]
        if (hasManualHld(msgMap, dsContrPk, null, null)) {
            return;
        }
        // END   2019/07/18 [QC#51706,ADD]
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.dsContrPk, dsContrPk);
        inMsg = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);

        setValue(inMsg.bllgHldFlg, FLG_OFF_N);

        S21ApiTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0789E);
        }
    }

    // START 2018/04/23 K.Kojima [QC#25446,ADD]
    // START 2019/07/18 [QC#51706, MOD]
    private void updateDsContrDtl(S21ApiMessageMap msgMap, BigDecimal dsContrDtlPk, BigDecimal dsContrPk) {
    // END 2019/07/18 [QC#51706, MOD]
        NSZC055001PMsg pMsg = (NSZC055001PMsg) msgMap.getPmsg();

        // START 2019/07/18 [QC#51706,ADD]
        //BigDecimal dsContrPk = pMsg.dsContrPk.getValue();
        if (hasManualHld(msgMap, dsContrPk, dsContrDtlPk, null)) {
            return;
        }
        // END   2019/07/18 [QC#51706,ADD]
        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
        inMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);

        setValue(inMsg.bllgHldFlg, FLG_OFF_N);

        S21ApiTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            // START 2018/12/20 K.Kitachi [QC#29647, MOD]
//            msgMap.addXxMsgId(NSZM0789E);
            msgMap.addXxMsgId(NSZM0875E);
            // END 2018/12/20 K.Kitachi [QC#29647, MOD]
        }
    }
    // END 2018/04/23 K.Kojima [QC#25446,ADD]

    // START 2018/12/20 K.Kitachi [QC#29647, ADD]
    // START 2019/07/18 [QC#51706, MOD]
    private void updateDsContrDtlUnderInfo(S21ApiMessageMap msgMap, BigDecimal dsContrDtlPk, BigDecimal dsContrPk) {
    // END 2019/07/18 [QC#51706, MOD]
        List<BigDecimal> dsContrPrcEffPkList = getBillingHoldPrcEff(msgMap, dsContrDtlPk);
        for (BigDecimal dsContrPrcEffPk : dsContrPrcEffPkList) {
            updateDsContrPrcEff(msgMap, dsContrPrcEffPk);
        }
        List<BigDecimal> dsContrBllgMtrPkList = getBillingHoldBllgMtr(msgMap, dsContrDtlPk);
        for (BigDecimal dsContrBllgMtrPk : dsContrBllgMtrPkList) {
            // START 2019/07/18 [QC#51706, MOD]
            //updateDsContrBllgMtr(msgMap, dsContrDtlPk, dsContrBllgMtrPk);
            updateDsContrBllgMtr(msgMap, dsContrDtlPk, dsContrBllgMtrPk, dsContrPk);
            // END 2019/07/18 [QC#51706, MOD]
        }
        // START 2019/07/18 [QC#51706, MOD]
        //updateDsContrDtl(msgMap, dsContrDtlPk);
        updateDsContrDtl(msgMap, dsContrDtlPk, dsContrPk);
        // END 2019/07/18 [QC#51706, MOD]
    }

    // START 2019/07/18 [QC#51706, MOD]
    private void updateDsContrBllgMtr(S21ApiMessageMap msgMap, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, BigDecimal dsContrPk) {
        NSZC055001PMsg pMsg = (NSZC055001PMsg) msgMap.getPmsg();

        // START 2019/07/18 [QC#51706,ADD]
        //BigDecimal dsContrPk = pMsg.dsContrPk.getValue();
        if (hasManualHld(msgMap, dsContrPk, dsContrDtlPk, dsContrBllgMtrPk)) {
            return;
        }
        // END   2019/07/18 [QC#51706,ADD]
        DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        inMsg = (DS_CONTR_BLLG_MTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);

        setValue(inMsg.bllgHldFlg, FLG_OFF_N);

        S21ApiTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0874E);
        }
    }
    // END 2019/07/18 [QC#51706, MOD]

    private void updateDsContrPrcEff(S21ApiMessageMap msgMap, BigDecimal dsContrPrcEffPk) {
        NSZC055001PMsg pMsg = (NSZC055001PMsg) msgMap.getPmsg();

        DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.dsContrPrcEffPk, dsContrPrcEffPk);
        inMsg = (DS_CONTR_PRC_EFFTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);

        setValue(inMsg.bllgHldFlg, FLG_OFF_N);

        S21ApiTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0876E);
        }
    }

    private List<BigDecimal> getBillingHoldBllgMtr(S21ApiMessageMap msgMap, BigDecimal dsContrDtlPk) {
        NSZC055001PMsg pMsg = (NSZC055001PMsg) msgMap.getPmsg();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getBillingHoldBllgMtr", paramMap);
    }

    private List<BigDecimal> getBillingHoldPrcEff(S21ApiMessageMap msgMap, BigDecimal dsContrDtlPk) {
        NSZC055001PMsg pMsg = (NSZC055001PMsg) msgMap.getPmsg();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getBillingHoldPrcEff", paramMap);
    }

    private BigDecimal getBillingHoldAggLine(S21ApiMessageMap msgMap, BigDecimal dsContrPk) {
        NSZC055001PMsg pMsg = (NSZC055001PMsg) msgMap.getPmsg();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("dsContrPk", dsContrPk);
        paramMap.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        return (BigDecimal) this.ssmBatchClient.queryObject("getBillingHoldAggLine", paramMap);
    }
    // END 2018/12/20 K.Kitachi [QC#29647, ADD]

    private void reassignServiceContractBilling(S21ApiMessageMap msgMap) {
        // TODO : Method specification is pending.
    }

    // add start 2016/06/01 CSA Defect#1523, 4624
    private void callContrTrkAPI(S21ApiMessageMap msgMap, BigDecimal dsContrPk) {
        S21UserProfileServiceFactory profileService = S21UserProfileServiceFactory.getInstance();
        S21UserProfileService profile;
        S21UserInfo userInfo = null;
        if (profileService != null) {
            profile = profileService.getService();
            if (profile != null) {
                userInfo = profile.getContextUserInfo();
            }
        }
        if (userInfo == null) {
            return;
        }

        NSZC055001PMsg pMsg = (NSZC055001PMsg) msgMap.getPmsg();
        if (!hasValue(dsContrPk)) {
            return;
        }

        ContrTrkInfo contrTrkInfo = NSXC001001ContractTracking.createContrTrkInfo(pMsg.glblCmpyCd.getValue(), ContrTrkProcMode.PREVIEW_BILLING.code, dsContrPk, null, null, null, null, null, null, userInfo.getUserId(), null, null, null);
        if (!NSXC001001ContractTracking.callContrTrkForDsContr(contrTrkInfo, this.onBatTp)) {
            msgMap.addXxMsgId(NSXC001001ContractTracking.ERR_MSG_ID);
        }
    }
    // add end 2016/06/01 CSA Defect#1523, 4624

    // START 2018/07/03 K.Kim [QC#26726,ADD]
    private boolean existCreditRebill(S21ApiMessageMap msgMap, BigDecimal dsContrPk) {
        NSZC055001PMsg pMsg = (NSZC055001PMsg) msgMap.getPmsg();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrPk", dsContrPk);
        List<String> svcCrRebilStsCdList = new ArrayList<String>();
        svcCrRebilStsCdList.add(SVC_CR_REBIL_STS.APPROVED);
        svcCrRebilStsCdList.add(SVC_CR_REBIL_STS.PROCESSED);
        svcCrRebilStsCdList.add(SVC_CR_REBIL_STS.CANCELLED);
        param.put("svcCrRebilStsCdList", svcCrRebilStsCdList);

        BigDecimal countCreditRebill = (BigDecimal) ssmBatchClient.queryObject("getCreditAndRebillCnt", param);
        if (countCreditRebill == null || countCreditRebill.compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        return true;
    }
    // END 2018/07/03 K.Kim [QC#26726,ADD]
    // START 2019/07/18 [QC#51706,ADD]
    private boolean hasManualHld(S21ApiMessageMap msgMap, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        NSZC055001PMsg pMsg = (NSZC055001PMsg) msgMap.getPmsg();
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("dsContrBllgMtrPk", dsContrBllgMtrPk);

        BigDecimal manulaHldCnt = (BigDecimal) ssmBatchClient.queryObject("getManualHldCnt", param);
        if (manulaHldCnt == null || manulaHldCnt.compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        return true;
    }
    // END   2019/07/18 [QC#51706,ADD]
}
