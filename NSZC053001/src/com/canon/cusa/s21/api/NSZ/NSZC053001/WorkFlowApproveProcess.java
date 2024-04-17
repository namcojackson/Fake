/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC053001;

import static com.canon.cusa.s21.api.NSZ.NSZC053001.constant.NSZC053001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_CR_REBILTMsg;
import business.db.SVC_CR_REBIL_DTLTMsg;
import business.db.SVC_CR_REBIL_DTLTMsgArray;
import business.db.SVC_CR_REBIL_MTR_READTMsg;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.db.SVC_PHYS_MTR_READTMsgArray;
import business.parts.NSZC010001PMsg;
import business.parts.NSZC047099PMsg;
import business.parts.NSZC053001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC010001.NSZC010001;
import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Credit Rebill Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/01/2016   Hitachi         T.Aoyagi        Create          N/A
 * 04/08/2016   Hitachi         T.Aoyagi        Update          QC#6737
 * 04/18/2016   Hitachi         T.Aoyagi        Update          QC#7056
 * 04/26/2016   Hitachi         T.Aoyagi        Update          QC#7578
 * 05/09/2016   Hitachi         T.Aoyagi        Update          QC#7764
 * 05/09/2016   Hitachi         T.Aoyagi        Update          QC#7618
 * 05/10/2016   Hitachi         K.Kishimoto     Update          QC#7764
 * 06/06/2016   Hitachi         T.Tomita        Update          QC#1523, 4624
 * 09/12/2016   Hitachi         K.Kishimoto     Update          QC#5566
 * 01/24/2017   Hitachi         A.Kohinata      Update          QC#17261
 * 2017/09/26   Hitachi         T.Tomita        Update          QC#21212
 * 2017/10/17   Hitachi         T.Tomita        Update          QC#21792
 * 2017/12/19   Hitachi         K.Kojima        Update          QC#22385
 * 2019/06/13   Hitachi         K.Kitachi       Update          QC#50811
 * 2020/01/08   Hitachi         K.Kim           Update          QC#55170
 * 2020/06/25   Hitachi         K.Kitachi       Update          QC#56211
 * 2022/09/01   CITS            L.Mandanas      Update          QC#58350
 * 2022/11/02   CITS            L.Mandanas      Update          QC#60652
 *</pre>
 */
public class WorkFlowApproveProcess implements ZYPConstant {

    /** onBatchTp */
    private ONBATCH_TYPE onBatchTp;

    /** NSZC0530Query */
    private NSZC0530Query query = NSZC0530Query.getInstance();

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        this.onBatchTp = onBatchType;
        workFlowApproveProcess(msgMap);
    }

    private void workFlowApproveProcess(S21ApiMessageMap msgMap) {

        // Get SVC_CR_REBIL
        SVC_CR_REBILTMsg crRebilTMsg = query.getSvcCrRebilTMsgForUpdate(msgMap);
        if (crRebilTMsg == null) {
            msgMap.addXxMsgId(NSZM0890E);
            return;
        }

        // START 2020/06/25 K.Kitachi [QC#56211, ADD]
        checkCustAcct(msgMap, crRebilTMsg.svcCrRebilPk.getValue());
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // END 2020/06/25 K.Kitachi [QC#56211, ADD]

        // START 2017/12/19 K.Kojima [QC#22385,ADD]
        List<BigDecimal> svcPhysMtrReadGrpSqListBefore = getSvcPhysMtrReadGrpSqList(msgMap);
        // END 2017/12/19 K.Kojima [QC#22385,ADD]

        // ----------------------------------------
        // Update SVC_CR_REBIL
        // ----------------------------------------
        updateSvcCrRebil(msgMap, crRebilTMsg);

        // ----------------------------------------
        // Call API
        // ----------------------------------------
        // START 04/18/2016 T.Aoyagi [QC#7056, MOD]
        callApi(msgMap, crRebilTMsg, FLG_OFF_N, FLG_OFF_N);
        // END 04/18/2016 T.Aoyagi [QC#7056, MOD]
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        //update customer care status
        // Add Start 09/12/2016 <QC#5566>
        StringBuilder notesDetail = new StringBuilder();
        notesDetail.append(" ");
        notesDetail.append(NSZC053001CommonLogic.getCurrTimestamp());
        notesDetail.append(CALL_PRC_CANON_E193_NOTES_DETAIL_SYSTEM_APPROVAL);
        // mod start 01/24/2017 CSA Defect#17261
        NSZC053001CommonLogic.callCustomerCareApi(msgMap, crRebilTMsg.custIncdtId.getValue(), crRebilTMsg.custIncdtLineId.getValue(), null, notesDetail.toString(), crRebilTMsg.ezUpUserID.getValue());
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // mod end 01/24/2017 CSA Defect#17261
        // Add End   09/12/2016 <QC#5566>
        // START 05/09/2016 T.Aoyagi [QC#7618, ADD]
        NSZC053001CommonLogic.updateCrRebilInfo(msgMap, crRebilTMsg);
        // END 05/09/2016 T.Aoyagi [QC#7618, ADD]

        // START 04/08/2016 T.Aoyagi [QC#6737, ADD]
        NSZC053001CommonLogic.releaseBllgHld(msgMap, crRebilTMsg, this.onBatchTp);
        // END 04/08/2016 T.Aoyagi [QC#6737, ADD]

        // START 2017/12/19 K.Kojima [QC#22385,ADD]
        List<BigDecimal> svcPhysMtrReadGrpSqListAfter = getSvcPhysMtrReadGrpSqList(msgMap);
        invalidMeterRead(msgMap, svcPhysMtrReadGrpSqListBefore, svcPhysMtrReadGrpSqListAfter);
        // END 2017/12/19 K.Kojima [QC#22385,ADD]

        // START 2019/06/13 K.Kitachi [QC#50811, ADD]
        BigDecimal dsContrPk = query.getDsContrPk(crRebilTMsg.glblCmpyCd.getValue(), crRebilTMsg.svcCrRebilPk.getValue());
        callBllgSchdApiRecovMode(msgMap, dsContrPk);
        // END 2019/06/13 K.Kitachi [QC#50811, ADD]

        // START 2020/01/08 [QC#55170, ADD]
        NSZC053001CommonLogic.recovPrntSvcContrBllgForRegAcc(msgMap, crRebilTMsg.svcCrRebilPk.getValue());
        // END 2020/01/08 [QC#55170, ADD]
    }

    private void updateSvcCrRebil(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg inMsg) {

        setValue(inMsg.svcCrRebilStsCd, SVC_CR_REBIL_STS.APPROVED);
        setValue(inMsg.svcCrRebilProcCd, SVC_CR_REBIL_PROC.CREDIT_AND_REBILL);
        S21ApiTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0877E);
        }
    }

    // START 04/18/2016 T.Aoyagi [QC#7056, ADD]
    /**
     * @param msgMap S21ApiMessageMap
     * @param onBatchTp ONBATCH_TYPE
     * @param crRebilTMsg SVC_CR_REBILTMsg
     * @param estFlg String
     * @param delFlg String
     */
    private void callApi(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg crRebilTMsg, String estFlg, String delFlg) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal crRebilPk = crRebilTMsg.svcCrRebilPk.getValue();

        // START 2022/09/01 L.Mandanas [QC#58350, MOD]
        //SVC_CR_REBIL_DTLTMsgArray crRebilDtlTMsgArray = query.getSvcCrRebilDtlTMsg(glblCmpyCd, crRebilPk);
        List<Map<String, Object>> getSvcCrRebilDtlSorted = query.getSvcCrRebilDtlSorted(glblCmpyCd, crRebilPk);
        //for (int i = 0; i < crRebilDtlTMsgArray.getValidCount(); i++) {
        for (int i = 0; i < getSvcCrRebilDtlSorted.size(); i++) {
            //SVC_CR_REBIL_DTLTMsg crRebilDtlTMsg =  crRebilDtlTMsgArray.no(i);
            String origSvcInvNum = (String) getSvcCrRebilDtlSorted.get(i).get(ORIG_SVC_INV_NUM);
            SVC_CR_REBIL_DTLTMsg crRebilDtlTMsg =  query.getSvcCrRebilDtlTMsg(glblCmpyCd, crRebilPk, origSvcInvNum);
        // END 2022/09/01 L.Mandanas [QC#58350, MOD]

            // ----------------------------------------
            // call Billing Schedule API
            // ----------------------------------------
            NSZC053001CommonLogic.callBllgShcdApi(msgMap, this.onBatchTp, crRebilDtlTMsg, estFlg, delFlg);
        }

        // START 2017/09/26 K.Kitachi [QC#21212, ADD]
        if (NSZC053001CommonLogic.isPriceChange(crRebilTMsg)) {
            NSZC053001CommonLogic.callBllgSchdApi(msgMap, crRebilTMsg, this.onBatchTp);
            // START 2017/10/17 K.Kitachi [QC#21792, ADD]
            NSZC053001CommonLogic.recreateXsCopy(msgMap, pMsg.svcCrRebilPk.getValue());
            // END 2017/10/17 K.Kitachi [QC#21792, ADD]
        }
        // END 2017/09/26 K.Kitachi [QC#21212, ADD]

        // ----------------------------------------
        // Update Meter Read
        // ----------------------------------------
        updateMtrRead(msgMap);

        // START 2022/09/01 L.Mandanas [QC#58350, MOD]
        //for (int i = 0; i < crRebilDtlTMsgArray.getValidCount(); i++) {
        for (int i = 0; i < getSvcCrRebilDtlSorted.size(); i++) {
            //SVC_CR_REBIL_DTLTMsg crRebilDtlTMsg =  crRebilDtlTMsgArray.no(i);
            String origSvcInvNum = (String) getSvcCrRebilDtlSorted.get(i).get(ORIG_SVC_INV_NUM);
            SVC_CR_REBIL_DTLTMsg crRebilDtlTMsg = query.getSvcCrRebilDtlTMsg(glblCmpyCd, crRebilPk, origSvcInvNum);
        // END 2022/09/01 L.Mandanas [QC#58350, MOD]
            String dsContrCatgCd = query.getDsContrCatgCd(glblCmpyCd, crRebilDtlTMsg.origSvcInvNum.getValue());
            BigDecimal crRebilDtlPk = crRebilDtlTMsg.svcCrRebilDtlPk.getValue();

            // ----------------------------------------
            // call Fleet Calculation API
            // ----------------------------------------
            if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                // START 04/11/2016 T.Aoyagi [QC#6715, MOD]
                // START 04/26/2016 T.Aoyagi [QC#7578, MOD]
                List<Map<String, Object>> schdInfoList = query.getSchdAndDsContr(glblCmpyCd, crRebilPk, crRebilDtlPk, DS_CONTR_DTL_TP.FLEET);
                for (Map<String, Object> schdInfoMap : schdInfoList) {
                    NSZC053001CommonLogic.callFleetCalcApi(msgMap, schdInfoMap, this.onBatchTp);
                }
                // END 04/26/2016 T.Aoyagi [QC#7578, MOD]
                // END 04/11/2016 T.Aoyagi [QC#6715, MOD]
            }

            // ----------------------------------------
            // call Billing Calculation API
            // ----------------------------------------
            NSZC053001CommonLogic.callBllgCalcApi(msgMap, this.onBatchTp, crRebilDtlTMsg, estFlg, dsContrCatgCd);

            // ----------------------------------------
            // call Aggregate Calculation API
            // ----------------------------------------
            if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
                // START 04/11/2016 T.Aoyagi [QC#6715, MOD]
                Map<String, Object> svcInvInfoMap = query.getSvcInvAndDsContr(glblCmpyCd, crRebilPk, crRebilDtlPk, DS_CONTR_DTL_TP.AGGREGATE);
                if (svcInvInfoMap != null) {
                    NSZC053001CommonLogic.callAggCalcApi(msgMap, svcInvInfoMap, this.onBatchTp);
                }
                // END 04/11/2016 T.Aoyagi [QC#6715, MOD]
            }
        }
    }
    // END 04/18/2016 T.Aoyagi [QC#7056, ADD]

    private void updateMtrRead(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        List<Map<String, Object>> crRebilMtrList = query.getCrRebilMtrInfo(msgMap);

        // for First Meter Read
        for (Map<String, Object> crRebilMtrMap : crRebilMtrList) {

            BigDecimal svcCrRebilDtlPk = (BigDecimal) crRebilMtrMap.get("SVC_CR_REBIL_DTL_PK");
            BigDecimal dsContrDtlPk = (BigDecimal) crRebilMtrMap.get("DS_CONTR_DTL_PK");
            BigDecimal svcMachMstrPk = (BigDecimal) crRebilMtrMap.get("SVC_MACH_MSTR_PK");
            String contrEffFromDt = (String) crRebilMtrMap.get("CONTR_EFF_FROM_DT");
            if (!hasValue(svcCrRebilDtlPk) || !hasValue(dsContrDtlPk)) {
                continue;
            }
            List<Map<String, Object>> firstMtrReadList = query.getUpdFirstMtrRead(msgMap, svcCrRebilDtlPk, dsContrDtlPk);
            if (firstMtrReadList.isEmpty()) {
                continue;
            }

            NSZC010001PMsg apiPMsg = new NSZC010001PMsg();
            setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(apiPMsg.mtrReadSrcTpCd, MTR_READ_SRC_TP.CUSTOMER_CARE);
            setValue(apiPMsg.dsMtrReadTpCd, DS_MTR_READ_TP.CUSTOMER_CARE);
            setValue(apiPMsg.dsMtrReadTpGrpCd, DS_MTR_READ_TP_GRP.BILLABLE_READS);
            setValue(apiPMsg.slsDt, pMsg.slsDt);
            setValue(apiPMsg.svcMachMstrPk, svcMachMstrPk);
            setValue(apiPMsg.xxWrnSkipFlg, FLG_OFF_N);
            setValue(apiPMsg.xxStartReadFlg, FLG_ON_Y);
            setValue(apiPMsg.dsContrDtlPk, dsContrDtlPk);
            setValue(apiPMsg.contrEffFromDt, contrEffFromDt);

            int cnt = 0;
            for (Map<String, Object> firstMtrReadMap : firstMtrReadList) {
                setValue(apiPMsg.A.no(cnt).mtrReadDt, (String) firstMtrReadMap.get("MTR_READ_DT"));
                setValue(apiPMsg.A.no(cnt).rgtnMtrDt, pMsg.slsDt);
                setValue(apiPMsg.A.no(cnt).readMtrCnt, (BigDecimal) firstMtrReadMap.get("READ_MTR_CNT"));
                setValue(apiPMsg.A.no(cnt).testMtrCnt, (BigDecimal) firstMtrReadMap.get("TEST_MTR_CNT"));
                setValue(apiPMsg.A.no(cnt).estFlg, FLG_OFF_N);
                setValue(apiPMsg.A.no(cnt).invProcFlg, FLG_OFF_N);
                setValue(apiPMsg.A.no(cnt).vldMtrFlg, FLG_ON_Y);
                setValue(apiPMsg.A.no(cnt).svcPhysMtrPk, (BigDecimal) firstMtrReadMap.get("SVC_PHYS_MTR_PK"));
                // START 05/09/2016 T.Aoyagi [QC#7764, ADD]
                setValue(apiPMsg.A.no(cnt).rgtnUsrId, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
                // END 05/09/2016 T.Aoyagi [QC#7764, ADD]
                cnt++;
            }
            apiPMsg.A.setValidCount(cnt);

            callMtrUpdateApi(msgMap, apiPMsg);
            // Add Start 05/11/2016 <QC#7764>
            for (Map<String, Object> firstMtrReadMap : firstMtrReadList) {
                SVC_CR_REBIL_MTR_READTMsg inMsg = new SVC_CR_REBIL_MTR_READTMsg();
                setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
                setValue(inMsg.svcCrRebilMtrReadPk, (BigDecimal) firstMtrReadMap.get("SVC_CR_REBIL_MTR_READ_PK"));
                inMsg = (SVC_CR_REBIL_MTR_READTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
                if (inMsg != null) {
                   setValue(inMsg.newStartMtrReadGrpSq, apiPMsg.A.no(0).svcPhysMtrReadGrpSq);
                   S21ApiTBLAccessor.update(inMsg);
                }
            }
            // Add End   05/11/2016 <QC#7764>
        }

        for (Map<String, Object> crRebilMtrMap : crRebilMtrList) {

            BigDecimal svcCrRebilDtlPk = (BigDecimal) crRebilMtrMap.get("SVC_CR_REBIL_DTL_PK");
            BigDecimal dsContrDtlPk = (BigDecimal) crRebilMtrMap.get("DS_CONTR_DTL_PK");
            BigDecimal svcMachMstrPk = (BigDecimal) crRebilMtrMap.get("SVC_MACH_MSTR_PK");
            if (!hasValue(svcCrRebilDtlPk) || !hasValue(dsContrDtlPk)) {
                continue;
            }
            List<Map<String, Object>> targetMtrReadList = query.getUpdTargetMtrRead(msgMap, svcCrRebilDtlPk, dsContrDtlPk);
            if (targetMtrReadList.isEmpty()) {
                continue;
            }

            NSZC010001PMsg apiPMsg = new NSZC010001PMsg();
            setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(apiPMsg.mtrReadSrcTpCd, MTR_READ_SRC_TP.CUSTOMER_CARE);
            setValue(apiPMsg.dsMtrReadTpCd, DS_MTR_READ_TP.CUSTOMER_CARE);
            setValue(apiPMsg.dsMtrReadTpGrpCd, DS_MTR_READ_TP_GRP.BILLABLE_READS);
            setValue(apiPMsg.svcMachMstrPk, svcMachMstrPk);
            setValue(apiPMsg.slsDt, pMsg.slsDt);
            setValue(apiPMsg.xxWrnSkipFlg, FLG_OFF_N);

            int cnt = 0;
            for (Map<String, Object> targetMtrReadMap : targetMtrReadList) {
                svcMachMstrPk = (BigDecimal) targetMtrReadMap.get("SVC_MACH_MSTR_PK");
                setValue(apiPMsg.A.no(cnt).mtrReadDt, (String) targetMtrReadMap.get("MTR_READ_DT"));
                setValue(apiPMsg.A.no(cnt).rgtnMtrDt, pMsg.slsDt);
                setValue(apiPMsg.A.no(cnt).readMtrCnt, (BigDecimal) targetMtrReadMap.get("READ_MTR_CNT"));
                setValue(apiPMsg.A.no(cnt).testMtrCnt, (BigDecimal) targetMtrReadMap.get("TEST_MTR_CNT"));
                setValue(apiPMsg.A.no(cnt).estFlg, FLG_OFF_N);
                setValue(apiPMsg.A.no(cnt).invProcFlg, FLG_OFF_N);
                setValue(apiPMsg.A.no(cnt).vldMtrFlg, FLG_ON_Y);
                setValue(apiPMsg.A.no(cnt).svcPhysMtrPk, (BigDecimal) targetMtrReadMap.get("SVC_PHYS_MTR_PK"));
                // START 05/09/2016 T.Aoyagi [QC#7764, ADD]
                setValue(apiPMsg.A.no(cnt).rgtnUsrId, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
                // END 05/09/2016 T.Aoyagi [QC#7764, ADD]
                cnt++;
            }
            apiPMsg.A.setValidCount(cnt);

            callMtrUpdateApi(msgMap, apiPMsg);
            // Add Start 05/11/2016 <QC#7764>
            for (Map<String, Object> targetMtrReadMap : targetMtrReadList) {
                SVC_CR_REBIL_MTR_READTMsg inMsg = new SVC_CR_REBIL_MTR_READTMsg();
                setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
                setValue(inMsg.svcCrRebilMtrReadPk, (BigDecimal) targetMtrReadMap.get("SVC_CR_REBIL_MTR_READ_PK"));
                inMsg = (SVC_CR_REBIL_MTR_READTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
                if (inMsg != null) {
                   setValue(inMsg.newEndMtrReadGrpSq, apiPMsg.A.no(0).svcPhysMtrReadGrpSq);
                   S21ApiTBLAccessor.update(inMsg);
                }
            }
            // Add End   05/11/2016 <QC#7764>
        }
        // Add Start 05/11/2016 <QC#7764>
        List<Map<String, Object>> adjMtrGrpSqInfo = query.getAdjMtrGrpSqInfo(msgMap);
        for (Map<String, Object> adjMtrGrpSq : adjMtrGrpSqInfo) {
            BigDecimal targetPk = (BigDecimal) adjMtrGrpSq.get("SVC_CR_REBIL_MTR_READ_PK");
            BigDecimal setSq = (BigDecimal) adjMtrGrpSq.get("NEW_END_MTR_READ_GRP_SQ");
            SVC_CR_REBIL_MTR_READTMsg inMsg = new SVC_CR_REBIL_MTR_READTMsg();
            setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(inMsg.svcCrRebilMtrReadPk, targetPk);
            inMsg = (SVC_CR_REBIL_MTR_READTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (inMsg != null) {
               setValue(inMsg.newStartMtrReadGrpSq, setSq);
               S21ApiTBLAccessor.update(inMsg);
            }
        }
        // Add End   05/11/2016 <QC#7764>
    }

    private void callMtrUpdateApi(S21ApiMessageMap msgMap, NSZC010001PMsg apiPMsg) {

        NSZC010001 api = new NSZC010001();
        api.execute(apiPMsg, this.onBatchTp);
        if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(apiPMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            msgMap.addXxMsgIdWithPrm(msgId, msgPrms);
        }
    }

    // START 2017/12/19 K.Kojima [QC#22385,ADD]
    private List<BigDecimal> getSvcPhysMtrReadGrpSqList(S21ApiMessageMap msgMap) {
        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal svcCrRebilPk = pMsg.svcCrRebilPk.getValue();

        List<BigDecimal> svcPhysMtrReadGrpSqList = NSZC0530Query.getInstance().getSvcPhysMtrReadGrpSq(glblCmpyCd, svcCrRebilPk);
        if (svcPhysMtrReadGrpSqList == null) {
            return new ArrayList<BigDecimal>();
        }
        return svcPhysMtrReadGrpSqList;
    }

    private void invalidMeterRead(S21ApiMessageMap msgMap, List<BigDecimal> svcPhysMtrReadGrpSqListBefore, List<BigDecimal> svcPhysMtrReadGrpSqListAfter) {
        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        for (BigDecimal svcPhysMtrReadGrpSq : svcPhysMtrReadGrpSqListBefore) {
            if (svcPhysMtrReadGrpSqListAfter.contains(svcPhysMtrReadGrpSq)) {
                continue;
            }
            SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
            inMsg.setSQLID("007");
            inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            inMsg.setConditionValue("svcPhysMtrReadGrpSq01", svcPhysMtrReadGrpSq);
            SVC_PHYS_MTR_READTMsgArray msgList = (SVC_PHYS_MTR_READTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);
            if (msgList == null || msgList.getValidCount() == 0) {
                continue;
            }
            for (int i = 0; i < msgList.getValidCount(); i++) {
                SVC_PHYS_MTR_READTMsg upMsg = (SVC_PHYS_MTR_READTMsg) msgList.get(i);
                ZYPEZDItemValueSetter.setValue(upMsg.vldMtrFlg, ZYPConstant.FLG_OFF_N);
                S21ApiTBLAccessor.update(upMsg);
            }
        }
    }
    // END 2017/12/19 K.Kojima [QC#22385,ADD]

    // START 2019/06/13 K.Kitachi [QC#50811, ADD]
    private boolean callBllgSchdApiRecovMode(S21ApiMessageMap msgMap, BigDecimal dsContrPk) {
        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        NSZC047099PMsg apiPMsg = new NSZC047099PMsg();
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.xxModeCd, "99");
        setValue(apiPMsg.slsDt, pMsg.slsDt);
        setValue(apiPMsg.dsContrPk, dsContrPk);
        NSZC047001 api = new NSZC047001();
        api.execute(apiPMsg, this.onBatchTp);
        if (ZYPCommonFunc.hasValue(apiPMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
            S21ApiMessage msg = msgList.get(0);
            msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            return false;
        }
        return true;
    }
    // END 2019/06/13 K.Kitachi [QC#50811, ADD]

    // START 2020/06/25 K.Kitachi [QC#56211, ADD]
    private void checkCustAcct(S21ApiMessageMap msgMap, BigDecimal svcCrRebilPk) {
        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        List<String> invalidCustAcctList = query.getInvalidCustAcct(pMsg.glblCmpyCd.getValue(), svcCrRebilPk);
        if (invalidCustAcctList.size() > 0) {
            String msgPrm = invalidCustAcctList.get(0);
            for (int i = 1; i < invalidCustAcctList.size(); i++) {
                msgPrm = msgPrm + NSZM1377E_DELIMITER + invalidCustAcctList.get(i);
            }
            if (hasValue(msgPrm) && msgPrm.length() > LEN_100) {
                msgPrm = msgPrm.substring(0, LEN_100);
            }
            msgMap.addXxMsgIdWithPrm(NSZM1377E, new String[] {msgPrm });
            // START 2022/11/02 L.Mandanas [QC#60652, ADD]
            return;
            // END 2022/11/02 L.Mandanas [QC#60652, ADD]
        }

        // START 2022/11/02 L.Mandanas [QC#60652, ADD]
        List<String> invalidBillToCustList = query.getInvalidBillToCust(pMsg.glblCmpyCd.getValue(), svcCrRebilPk);
        if (invalidBillToCustList.size() > 0) {
            String msgPrm = invalidBillToCustList.get(0);
            for (int i = 1; i < invalidBillToCustList.size(); i++) {
                msgPrm = msgPrm + NSZM1377E_DELIMITER + invalidBillToCustList.get(i);
            }
            if (hasValue(msgPrm) && msgPrm.length() > LEN_100) {
                msgPrm = msgPrm.substring(0, LEN_100);
            }
            msgMap.addXxMsgIdWithPrm(NSZM1380E, new String[] {msgPrm });
            return;
        }

        List<String> invalidShipToCustList = query.getInvalidShipToCust(pMsg.glblCmpyCd.getValue(), svcCrRebilPk);
        if (invalidShipToCustList.size() > 0) {
            String msgPrm = invalidShipToCustList.get(0);
            for (int i = 1; i < invalidShipToCustList.size(); i++) {
                msgPrm = msgPrm + NSZM1377E_DELIMITER + invalidShipToCustList.get(i);
            }
            if (hasValue(msgPrm) && msgPrm.length() > LEN_100) {
                msgPrm = msgPrm.substring(0, LEN_100);
            }
            msgMap.addXxMsgIdWithPrm(NSZM1381E, new String[] {msgPrm });
            return;
        }
        // END 2022/11/02 L.Mandanas [QC#60652, ADD]
    }
    // END 2020/06/25 K.Kitachi [QC#56211, ADD]
}
